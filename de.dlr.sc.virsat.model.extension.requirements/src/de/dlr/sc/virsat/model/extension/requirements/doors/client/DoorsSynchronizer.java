/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.requirements.doors.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.StmtIterator;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.lyo.client.exception.ResourceNotFoundException;
import org.eclipse.lyo.oslc.domains.rm.RequirementCollection;
import org.eclipse.lyo.oslc4j.core.model.OslcMediaType;
import org.eclipse.lyo.oslc4j.core.model.Property;
import org.eclipse.lyo.oslc4j.core.model.ResourceShape;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.ImportConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementObject;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.model.extension.requirements.model.SpecificationMapping;

public class DoorsSynchronizer {

	private static final String DESCRIPTION = "Description";
	private static final String MODIFIED_ON = "ModifiedOn";
	private static final String MODIFIED_BY = "ModifiedBy";
	private static final String TEXT = "Text";

	protected ImportConfiguration importConfiguration;
	protected EditingDomain ed;
	protected ResourceShape resourceShapeDoors = null;
	
	/**
	 * Inits the synchronizer by handing over the import configuration location and
	 * editing domain
	 * 
	 * @param importConfig  The import configuration
	 * @param editingDomain The editing domain for the synchronization
	 */
	public void init(ImportConfiguration importConfig, EditingDomain editingDomain) {
		this.importConfiguration = importConfig;
		this.ed = editingDomain;
	}
	
	/**
	 * Synchronizes the requirements in the import configuration with corresponding
	 * requirements of the current doors connection
	 * 
	 * @return The command to be executed
	 */
	public Command synchronizeRequirements(EditingDomain editingDomain) {
		CompoundCommand cc = new CompoundCommand();
		IBeanList<SpecificationMapping> specs = importConfiguration.getMappedSpecifications();
		for (SpecificationMapping spec : specs) {
			RequirementsSpecification specification = spec.getSpecification();
			String projectName = spec.getExternalIdentifier();
			IBeanList<RequirementObject> reqsToSynchronize = specification.getRequirements();
			try {
				ArrayList<RequirementCollection> reqCollections = DoorsSynchroClient
						.queryRequirementsSpecifications(projectName);
				for (RequirementCollection reqCollection : reqCollections) {
					if (reqCollection.getTitle().equals(projectName)) {
						ArrayList<org.eclipse.lyo.oslc.domains.rm.Requirement> reqsFromDoors = DoorsSynchroClient
								.getReqsFromCollection(reqCollection);
						for (org.eclipse.lyo.oslc.domains.rm.Requirement reqFromDoors : reqsFromDoors) {
							findReqWithId(reqsToSynchronize, reqFromDoors, cc);
							
						}
					}
				}
			} catch (ResourceNotFoundException | IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cc;
	}

	/**
	 * Finds the requirement with identical id to requirement in doors
	 * 
	 * @param reqObjects The list with requirements to be synchronized
	 * @param reqIdDoors The id of a requirement in doors
	 * @return The requirement or null
	 */
	private CompoundCommand findReqWithId(IBeanList<RequirementObject> reqObjects,
			org.eclipse.lyo.oslc.domains.rm.Requirement reqDoors, CompoundCommand cc) {
		for (RequirementObject rObject : reqObjects) {
			if (rObject instanceof RequirementGroup)
				findReqWithId(((RequirementGroup) rObject).getChildren(), reqDoors, cc);
			else {
				Requirement req = (Requirement) rObject;
				String reqId = req.getIdentifier();
				if (reqId.contentEquals(reqDoors.getIdentifier())) {
					if (isModified(req, reqDoors)) {
						resourceShapeDoors = DoorsSynchroClient.getResourceShape(reqDoors);
						updateValuesFromDoors(req, reqDoors, cc);
					}
				}
			}
		}
		return cc;
	}

	/**
	 * Updates every attribute of requirement with values of the requirement in doors
	 * 
	 * @param requirement The requirement to be synchronized
	 * @param reqFromDoors The requirement from doors
	 * @param cc The compound command in which commands should be added
	 * @return The command to be executed
	 */
	private CompoundCommand updateValuesFromDoors(Requirement requirement,
			org.eclipse.lyo.oslc.domains.rm.Requirement reqFromDoors, CompoundCommand cc) {
		IBeanList<AttributeValue> attributesFromReq = requirement.getElements();
		for (AttributeValue att : attributesFromReq) {
			String attName = att.getName();
			if (attName.contains(DESCRIPTION)) {
				String descriptionDoors = reqFromDoors.getDescription();
				cc.append(att.setValue(ed, descriptionDoors));
			} else if (attName.contains(MODIFIED_ON)) {
				String modifiedOnDoors = reqFromDoors.getModified().toInstant().toString();
				cc.append(att.setValue(ed, modifiedOnDoors));
			} else if (attName.contains(TEXT)) {
				String titleDoors = reqFromDoors.getTitle();
				cc.append(att.setValue(ed, titleDoors));
			} else if (attName.contains(MODIFIED_BY)) {
				if (!reqFromDoors.getContributor().isEmpty()) {
					String stringModifiedByDoors = reqFromDoors.getContributor().iterator().next().getValue().getPath();
					String modifiedByDoors = reqFromDoors.getContributor().iterator().next().getValue().getPath()
							.substring(stringModifiedByDoors.lastIndexOf('/') + 1);
					cc.append(att.setValue(ed, modifiedByDoors));
				}
			} else {
				String typeSpecificAttributeValue = getTypeSpecificAttributeValue(reqFromDoors, attName);
				if (typeSpecificAttributeValue != null) {
					cc.append(att.setValue(ed, typeSpecificAttributeValue));
				}
			}
		}
		return cc;
	}

	/**
	 * Gets the value of an extended property in doors which is specified for a certain requirement type
	 * 
	 * @param reqFromDoors The requirement from doors
	 * @param attName The name of the attribute to be updated
	 * @return The value of the attribute
	 */
	private String getTypeSpecificAttributeValue(org.eclipse.lyo.oslc.domains.rm.Requirement reqFromDoors,
			String attName) {
		Map<QName, Object> extendedPropertiesDoors = reqFromDoors.getExtendedProperties();
		for (QName qname : extendedPropertiesDoors.keySet()) {
			Property propertyDoors;
			try {
				propertyDoors = resourceShapeDoors.getProperty(new URI(qname.getNamespaceURI() + qname.getLocalPart()));
				String propertyNameDoors = null;
				if (propertyDoors != null) {
					propertyNameDoors = propertyDoors.getTitle();
					if (propertyNameDoors != null) {
						String valueExtendedPropertyDoors = null;
						if (attName.contains(propertyNameDoors)) {
							Object propertyDefinitionDoors = reqFromDoors.getExtendedProperties().get(qname);
							if (propertyDefinitionDoors instanceof URI) {
								Response response = DoorsSynchroClient.queryResource(propertyDefinitionDoors.toString(),
										OslcMediaType.APPLICATION_RDF_XML);
								String uriPropertyDoors = propertyDefinitionDoors.toString();
								try {
									valueExtendedPropertyDoors = readValueFromProperty(response,
											uriPropertyDoors);
								} catch (IOException e) {
									Activator.getDefault().getLog().error(e.getMessage());
								}
								return valueExtendedPropertyDoors;
							}
						}
					}
				}
			} catch (URISyntaxException e) {
				Activator.getDefault().getLog().error(e.getMessage());
			}
		}
		return null;
	}

	/**
	 * Reads the RDF/XML response to get the value of the extended property in doors
	 * 
	 * @param response The response of the property definition in doors
	 * @param uriToProp The URI to the property definition in doors
	 * @return the value of the property
	 * @throws IOException
	 */
	private static String readValueFromProperty(Response response, String uriToProp) throws IOException {
		InputStream is = response.readEntity(InputStream.class);
		Model model = ModelFactory.createDefaultModel();
		model.read(is, "RDF/XML");

		StmtIterator statementIterator = model.getResource(uriToProp).listProperties();
		String value = statementIterator.next().getObject().toString();
		return value;
	}

	/**
	 * Checks if the requirement has been modified in doors
	 * 
	 * @param req The requirement to be synchronized
	 * @param reqFromDoors The corresponding requirement in doors
	 * @return true if requirement has been modified or false 
	 */
	private boolean isModified(Requirement req, org.eclipse.lyo.oslc.domains.rm.Requirement reqFromDoors) {
		for (AttributeValue att : req.getElements()) {
			if (att.getName().contains(MODIFIED_ON)) {
				String lastModifiedReq = att.getValue();
				String lastModifiedReqFromDoors = reqFromDoors.getModified().toInstant().toString();
				String lastModifiedReqFromDoorsWithoutZ = lastModifiedReqFromDoors.substring(0,
						lastModifiedReqFromDoors.length() - 1);
				if (!lastModifiedReq.contentEquals(lastModifiedReqFromDoorsWithoutZ)) {
					return true;
				}
			}
		}
		return false;
	}
}