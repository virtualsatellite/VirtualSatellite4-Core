/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;

/**
 * This class implements a factory for the various different editing supports
 * It has capabilities to decide on the type of property which editing support
 * should be provided
 * @author fisc_ph
 *
 */
public class PropertyInstanceEditingSupportFactory {

	public static final PropertyInstanceEditingSupportFactory INSTANCE = new PropertyInstanceEditingSupportFactory();

	/**
	 * default constuctor
	 */
	private PropertyInstanceEditingSupportFactory() {
	}
	
	/**
	 * This method hands back the correct EditingSupport for a given property
	 * @param editingDomain The editing Domain that will be used with the EditingSupport
	 * @param viewer The viewer such as a Table in which this editor will be used
	 * @param property The property for which to get the corresponding Cell Editor
	 * @return The cell editor that fits best to the given type
	 */
	public synchronized EditingSupport createEditingSupportFor(EditingDomain editingDomain, ColumnViewer viewer, AProperty property) {
		return new PropertydefinitionsSwitch<EditingSupport>() {
    		
			@Override
    		public EditingSupport caseIntProperty(IntProperty property) {
    			return new ValuePropertyCellEditingSupport(editingDomain, viewer, property);
    		}
    		
    		@Override
    		public EditingSupport caseStringProperty(StringProperty property) {
    			return new ValuePropertyCellEditingSupport(editingDomain, viewer, property);
    		}
    		
    		@Override
    		public EditingSupport caseEnumProperty(EnumProperty property) {
    			return new EnumPropertyCellEditingSupport(editingDomain, viewer, property);
    		}
    		
    		@Override
    		public EditingSupport caseFloatProperty(FloatProperty property) {
    			return new ValuePropertyCellEditingSupport(editingDomain, viewer, property);
    		}
    		
    		@Override
    		public EditingSupport caseBooleanProperty(BooleanProperty property) {
    			return new BooleanPropertyCellEditingSupport(editingDomain, viewer, property);
    		}
    		
    		@Override
    		public EditingSupport caseReferenceProperty(ReferenceProperty property) {
				return new ReferencePropertyCellEditingSupport(editingDomain, viewer, property);
    		};
    		
    		@Override
    		public EditingSupport caseEReferenceProperty(EReferenceProperty property) {
				return new EReferencePropertyCellEditingSupport(editingDomain, viewer, property);
    		};
    		
    		@Override
    		public EditingSupport caseResourceProperty(ResourceProperty property) {
    			return new ResourcePropertyCellEditingSupport(editingDomain, viewer, property);
    		}

    		public EditingSupport defaultCase(EObject object) {
    			return null;
    		};
    		
    	}.doSwitch(property);	
	}
}
