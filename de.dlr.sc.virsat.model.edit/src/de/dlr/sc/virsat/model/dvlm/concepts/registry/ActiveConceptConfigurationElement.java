/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts.registry;

import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.migrator.AMigrator;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.concept.util.ConceptActivationHelper;
import de.dlr.sc.virsat.model.ecore.xmi.impl.DvlmXMIResourceFactoryImpl;

/**
 * This class allows to conveniently access a Configuration Element for
 * an ActiveConcept in the Concept registry 
 * @author fisc_ph
 *
 */
public class ActiveConceptConfigurationElement {

	public static final String EXTENSION_POINT_ID_CONCEPT = "de.dlr.sc.virsat.model.Concept";
	private static final String EXTENSION_POINT_ID_SNIPPETS_ID = "id";
	private static final String EXTENSION_POINT_ID_SNIPPETS_XMI = "xmi";
	private static final String EXTENSION_POINT_ID_SNIPPETS_VERSION = "version";
	
	private IConfigurationElement configElement;
	
	/**
	 * Constructor with the Configuration Element of which to retrieve the In formation
	 * @param configElement Config Element from the PLatform registry
	 */
	public ActiveConceptConfigurationElement(IConfigurationElement configElement) {
		this.configElement = configElement;
	}
	
	/**
	 * Method to get the version of the registered concept
	 * @return Version as string
	 */
	public String getVersion() {
		String version = configElement.getAttribute(EXTENSION_POINT_ID_SNIPPETS_VERSION); 
		return version;
	}
	
	/**
	 * Method to get the Id / Qualified name of the concept in the regiostry 
	 * @return the name as string
	 */
	public String getId() {
		String id = configElement.getAttribute(EXTENSION_POINT_ID_SNIPPETS_ID);
		return id;
	}

	/**
	 * This method get the xmi of the configuration element
	 * @return xmi of the configuration element
	 */
	public String getXmi() {
		String xmi = configElement.getAttribute(EXTENSION_POINT_ID_SNIPPETS_XMI);
		return xmi;
	}

	/**
	 * This method get the displayName of the concept
	 * @return displayName of the concept
	 */
	public String getConceptNameWithVersion() {
		Concept concept = loadConceptFromPlugin();
		String conceptNameWithVersion = ActiveConceptHelper.getConceptNameWithVersion(concept);
		return conceptNameWithVersion;
	}
	
	/**
	 * This method tells if the ACCE registers a given Concept. This method can be used
	 * to test if a given concept is handled by the given configuration element from the
	 * eclipse / equinox platform. 
	 * @param concept The concept to be checked
	 * @return true in case the ACCE is handling the given Concept
	 */
	public boolean registersConcept(Concept concept) {
		boolean sameId = getId().equals(concept.getName());
		boolean sameVer = getVersion().equals(concept.getVersion());
		return sameId && sameVer;
	}
	
	/**
	 * Override this method for customization of the concept to be loaded
	 * @return the String that should be used to create the uri pointing to the given concept
	 */
	protected String getConceptXmiPluginPath() {
		return getId() + "/" + getXmi();
	}
	
	/**
	 * This method load the concept from the Plugin
	 * @return the loaded concept
	 */
	public Concept loadConceptFromPlugin() {
		Resource.Factory.Registry factoryRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> extensionMap = factoryRegistry.getExtensionToFactoryMap();
		extensionMap.put("xmi", new DvlmXMIResourceFactoryImpl());

		String conceptXmiPluginPath = getConceptXmiPluginPath();
		URI conceptResourceUri = URI.createPlatformPluginURI(conceptXmiPluginPath, true);
		
		ResourceSet resourceSet = AMigrator.performMigration(conceptResourceUri);
		
		Resource resource = resourceSet.getResource(conceptResourceUri, true);
		Concept concept = (Concept) resource.getContents().get(0);
		return concept;
	}

	/**
	 * This method copies the concept and makes sure that IDs referencing other concepts are
	 * redirected accordingly, since some References are stored on XMI rather then DVLM related URIs.
	 * @param concept which will be copied adjusted to be compatible with the given repository
	 * @param repository where other concepts may already be stored in 
	 * @return the active Concept that has been created
	 */
	private static Concept createActiveConcept(Concept concept, Repository repository) {
		ConceptActivationHelper helper = new ConceptActivationHelper(repository);
		EcoreUtil.Copier copier = new EcoreUtil.Copier() {

			private static final long serialVersionUID = 5925167870311468118L;
			
			@Override
			public EObject get(Object key) {
				
				EObject eObject = super.get(key);
				
				// Activate types 
				if (eObject == null && key instanceof EObject) {
					return helper.getActiveType((EObject) key);
				}
				
				return eObject;
			}
		};
		Concept activeConcept = (Concept) copier.copy(concept);
		copier.copyReferences();
		
		return activeConcept;
	}

	/**
	 * This method copies the concept to the repository and makes sure that IDs referencing other concepts are
	 * redirected accordingly, since some References are stored on XMI rather then DVLM related URIs.
	 * @param concept which will be copied to the repository
	 * @param repository where the copy will be saved
	 * @return the active Concept that has been added to the repository.
	 */
	public static Concept createCopyConceptToRepository(Concept concept, Repository repository) {
		Concept activeConcept = createActiveConcept(concept, repository);		
		repository.getActiveConcepts().add(activeConcept);
		return activeConcept;
	}
	
	/**
	 * This method copies the concept to the repository and makes sure that IDs referencing other concepts are
	 * redirected accordingly, since some References are stored on XMI rather then DVLM related URIs.
	 * @param ed editing domain where adding the Concept to the repository will be handled in. 
	 * @param concept which will be copied to the repository
	 * @param repository where the copy will be saved
	 * @return the added command
	 */
	public static Command createCopyConceptToRepository(EditingDomain ed, Concept concept, Repository repository) {
		Concept activeConcept = createActiveConcept(concept, repository);		
		return AddCommand.create(ed, repository, DVLMPackage.eINSTANCE.getRepository_ActiveConcepts(), activeConcept);		
	}
	
	/**
	 * This method add the active concept
	 * @param ed editing domain
	 * @param repository where the copy will be saved
	 * @return the adding command
	 */
	public Command createAddActiveConceptCommand(EditingDomain ed, Repository repository) {
		Concept concept = loadConceptFromPlugin();
		return createCopyConceptToRepository(ed, concept, repository);
	}
	/**
	 * Concept finder method
	 * @param configElements The list of config elements
	 * @param id desired concept id
	 * @return acElement desired ActiveConceptConfigurationElement
	 * @author bell_er
	 *
	 */
	public static ActiveConceptConfigurationElement getPropperAddActiveConceptConfigurationElement(IConfigurationElement [] configElements, String id) {
		for (IConfigurationElement configElement : configElements) {
			ActiveConceptConfigurationElement acElement = new ActiveConceptConfigurationElement(configElement);
			String acElementId = acElement.getId();
			if (id.equals(acElementId)) {
				return acElement;
			}
		}
		
		return null;
	}

	/**
	 * Method to load a concept from an XMI file
	 * @param conceptXmiFilePath path to the concept.xmi, e.g. "de.dlr.sc.virsat.model.extension.tests/concept/concept.xmi"
	 * @return loaded concept
	 */
	public static Concept loadConceptFromPlugin(String conceptXmiFilePath) {
		ActiveConceptConfigurationElement acce = new ActiveConceptConfigurationElement(null) {
			@Override
			protected String getConceptXmiPluginPath() {
				return conceptXmiFilePath;
			}
		};
		
		Concept concept = acce.loadConceptFromPlugin();
		return concept;
	}
};