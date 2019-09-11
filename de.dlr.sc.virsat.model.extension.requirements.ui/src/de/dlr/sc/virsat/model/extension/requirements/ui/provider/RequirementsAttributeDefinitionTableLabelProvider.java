/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.provider;

import org.eclipse.emf.common.notify.AdapterFactory;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.ui.propertyinstance.util.PreferencedPropertyInstanceValueSwitchFactory;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;

/**
 * @author fran_tb
 *
 */
public class RequirementsAttributeDefinitionTableLabelProvider extends VirSatTransactionalAdapterFactoryLabelProvider {
	
	protected PropertyInstanceValueSwitch valueSwitch = PreferencedPropertyInstanceValueSwitchFactory.createInstance();
	
	/**
	 * 
	 * @param adapterFactory the adapter factory
	 */
	public RequirementsAttributeDefinitionTableLabelProvider(AdapterFactory adapterFactory) {
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

		if (object instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) object;
			
			switch (columnIndex) {
				case 0:
					return ca.getName();
	
				case 1:
					return getAttributeTypeLabel(new RequirementAttribute(ca));
					
				default:
					return null;
			}
		}
		return ((IName) object).getName();
	}
	
	/**
	 * Show type value of the attribute
	 * @param attDef the attribute definition
	 * @return the type string
	 */
	protected String getAttributeTypeLabel(RequirementAttribute attDef) {
		if (attDef == null || attDef.getType() == null) {
			return "";
		}
		return attDef.getType();
	}
}
