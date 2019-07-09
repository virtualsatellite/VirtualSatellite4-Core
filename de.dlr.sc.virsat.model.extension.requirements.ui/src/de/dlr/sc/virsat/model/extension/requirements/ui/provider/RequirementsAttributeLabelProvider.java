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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.swt.graphics.Image;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.ui.propertyinstance.util.PreferencedPropertyInstanceValueSwitchFactory;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;

/**
 * @author fran_tb
 *
 */
public class RequirementsAttributeLabelProvider extends VirSatTransactionalAdapterFactoryLabelProvider {

	public static final int STATUS_COLUMN = 0;

	public static final int REQUIREMENT_STATUS_PROPERTY_NUMBER = 2;
	public static final int REQUIREMENT_ELEMENTS_PROPERTY_NUMBER = 1;
	
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

			// COlumn 0 is always the name where as column 1 means the first property thus
			// accessing it by 0
			if (columnIndex == STATUS_COLUMN) {

				APropertyInstance propertyInstance = ca.getPropertyInstances()
						.get(REQUIREMENT_STATUS_PROPERTY_NUMBER);
				redirectNotification(propertyInstance, object);
				ATypeInstance ti = valueSwitch.doSwitch(propertyInstance);
				redirectNotification(ti, object);

				return valueSwitch.getValueString(propertyInstance);

			} else if (columnIndex > STATUS_COLUMN) {
				APropertyInstance propertyInstance = ca.getPropertyInstances()
						.get(REQUIREMENT_ELEMENTS_PROPERTY_NUMBER);
				redirectNotification(propertyInstance, object);

				if (propertyInstance instanceof ArrayInstance) {
					int attIndex = columnIndex - 1; // Status Column
					return getValueOfAttributeIndex((ArrayInstance) propertyInstance, attIndex);
				}
			}
		}
		return null;
	}
	
	/**
	 * Return the value to the array element of a given index
	 * 
	 * @param arrayInstance
	 *            the array
	 * @param attributeIndex
	 *            the index
	 * @return the value
	 */
	protected String getValueOfAttributeIndex(ArrayInstance arrayInstance, int attributeIndex) {
		String attributValue = null;
		for (APropertyInstance instance : arrayInstance.getArrayInstances()) {

			if (instance instanceof ComposedPropertyInstance) {
				ComposedPropertyInstance prInstance = (ComposedPropertyInstance) instance;
				AttributeValue value = new AttributeValue(prInstance.getTypeInstance());

				// Find out initial column index of attribute
				RequirementType requirementType = value.getAttType().getParentCaBeanOfClass(RequirementType.class);
				if (requirementType.getAttributes().indexOf(value.getAttType()) == attributeIndex) {
					return value.getValue();
				}

			}
		}

		return attributValue;
	}

	@Override
	public Image getColumnImage(Object object, int columnIndex) {
		return null;
	}
	
	

}
