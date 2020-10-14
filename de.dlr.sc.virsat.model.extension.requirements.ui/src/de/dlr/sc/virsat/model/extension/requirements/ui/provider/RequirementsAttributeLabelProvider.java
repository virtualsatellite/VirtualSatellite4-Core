/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
/**
 * 
 */
package de.dlr.sc.virsat.model.extension.requirements.ui.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.swt.graphics.Image;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.ui.propertyinstance.util.PreferencedPropertyInstanceValueSwitchFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;

/**
 *
 */
public class RequirementsAttributeLabelProvider extends VirSatTransactionalAdapterFactoryLabelProvider {

	public static final int STATUS_COLUMN = 0;

	public static final int TRACE_COLUMN = 1;

	public static final int REQUIREMENT_STATUS_PROPERTY_NUMBER = 2;
	public static final int REQUIREMENT_ELEMENTS_PROPERTY_NUMBER = 1;
	public static final int REQUIREMENT_TRACE_PROPERTY_NUMBER = 3;
	public static final int REQUIREMENT_TRACE_TARGET_PROPERTY_NUMBER = 0;

	public static final String EMPTY_TRACE_STRING = "-";

	protected PropertyInstanceValueSwitch valueSwitch = PreferencedPropertyInstanceValueSwitchFactory.createInstance();

	/**
	 * @param adapterFactory the adapter factory
	 */
	public RequirementsAttributeLabelProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		valueSwitch.setShowEnumValueDefinitionValues(false);
	}

	@Override
	public String getColumnText(Object object, int columnIndex) {
		if (object == null) {
			return "";
		}

		if (object instanceof ComposedPropertyInstance) {
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) object;
			CategoryAssignment ca = cpi.getTypeInstance();
			redirectNotification(ca, object);
			return getColumnText(cpi.getTypeInstance(), columnIndex);
		}

		super.getColumnText(object, columnIndex);

		if (object instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) object;

			if (columnIndex == STATUS_COLUMN) {

				APropertyInstance propertyInstance = ca.getPropertyInstances().get(REQUIREMENT_STATUS_PROPERTY_NUMBER);
				redirectNotification(propertyInstance, object);
				ATypeInstance ti = valueSwitch.doSwitch(propertyInstance);
				redirectNotification(ti, object);

				return valueSwitch.getValueString(propertyInstance);

			} else if (columnIndex == TRACE_COLUMN) {

				APropertyInstance propertyInstance = ca.getPropertyInstances().get(REQUIREMENT_TRACE_PROPERTY_NUMBER);
				redirectNotification(propertyInstance, object);
				ATypeInstance ti = valueSwitch.doSwitch(propertyInstance);
				redirectNotification(ti, object);
				
				return getVerificationLabel(new Requirement(ca));
				

			} else if (columnIndex > STATUS_COLUMN) {
				APropertyInstance propertyInstance = ca.getPropertyInstances()
						.get(REQUIREMENT_ELEMENTS_PROPERTY_NUMBER);
				redirectNotification(propertyInstance, object);

				if (propertyInstance instanceof ArrayInstance) {
					int attIndex = columnIndex - 2; // Status + Trace Column
					return getValueOfAttributeIndex((ArrayInstance) propertyInstance, attIndex);
				}
			}
		}
		return null;
	}

	/**
	 * Return the value to the array element of a given index
	 * 
	 * @param arrayInstance  the array
	 * @param attributeIndex the index
	 * @return the value
	 */
	protected String getValueOfAttributeIndex(ArrayInstance arrayInstance, int attributeIndex) {
		for (APropertyInstance instance : arrayInstance.getArrayInstances()) {
			if (instance instanceof ComposedPropertyInstance) {
				ComposedPropertyInstance prInstance = (ComposedPropertyInstance) instance;
				AttributeValue value = new AttributeValue(prInstance.getTypeInstance());

				// Clean up values which don't have a type definition anymore
				if (value.getAttType() == null) {
					cleanUpAttribue(value);
					return null;
				}

				// Find out initial column index of attribute
				RequirementType requirementType = value.getAttType().getParentCaBeanOfClass(RequirementType.class);
				if (requirementType.getAttributes().indexOf(value.getAttType()) == attributeIndex) {
					return value.getValue();
				}

			}
		}
		return null;
	}

	@Override
	public Image getColumnImage(Object object, int columnIndex) {
		if (object == null) {
			return null;
		}

		if (object instanceof ComposedPropertyInstance) {
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) object;
			CategoryAssignment ca = cpi.getTypeInstance();
			redirectNotification(ca, object);
			return getColumnImage(cpi.getTypeInstance(), columnIndex);
		}

		if (object instanceof CategoryAssignment) {
			Requirement requirement = new Requirement((CategoryAssignment) object);

			if (columnIndex == STATUS_COLUMN) {
				return super.getColumnImage(requirement.getStatus(), columnIndex);
			}

			if (columnIndex > STATUS_COLUMN) {
				int attIndex = columnIndex - 1; // Status Column
				if (requirement.getReqType() == null || requirement.getReqType().getAttributes() == null
						|| attIndex >= requirement.getReqType().getAttributes().size()) {
					return null;
				}
				RequirementAttribute attDef = requirement.getReqType().getAttributes().get(attIndex);

				return super.getColumnImage(attDef, columnIndex);

			}

		}

		return super.getColumnImage(object, columnIndex);
	}

	/**
	 * Clean up requirements if their type or a type of an attribute does not exist
	 * anymore
	 * 
	 * @param att the attribute value
	 */
	protected void cleanUpAttribue(AttributeValue att) {
		Requirement containingRequirement = att.getCaBeanFromParentSei(Requirement.class);
		VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE
				.getEd(att.getTypeInstance());

		if (containingRequirement != null && containingRequirement.getReqType() == null) {
			// requirement does not have a type anymore... completely delete it

			editingDomain.getVirSatCommandStack().execute(containingRequirement.delete(editingDomain));

		} else if (att.getAttType() == null) {
			// Only the attribute does not have a type anymore... clean the attribute value

			editingDomain.getVirSatCommandStack().execute(att.delete(editingDomain));
		}
	}
	
	/**
	 * Create a label for requirements verification methods customized to this table 
	 * 
	 * @param req the requirement for which a verification method label is created
	 * @return the label
	 */
	protected String getVerificationLabel(Requirement req) {
		if (req.getVerification().isEmpty()) {
			return EMPTY_TRACE_STRING;
		} else {
			List<String> verificationStringArtifacts = new ArrayList<String>();
			
			for (IVerification verification : req.getVerification()) {
				verificationStringArtifacts.add(verification.getName());
			}

			return "{" + String.join(", ", verificationStringArtifacts) + "}";
		}
	}

}
