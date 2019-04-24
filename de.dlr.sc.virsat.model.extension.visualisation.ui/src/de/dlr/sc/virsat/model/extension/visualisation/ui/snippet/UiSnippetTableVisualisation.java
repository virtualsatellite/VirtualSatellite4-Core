/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.snippet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerColumn;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.extension.visualisation.ui.cellEditorSupport.VisValuePropertyCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.BooleanPropertyCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.EnumPropertyCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.ReferencePropertyCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.ResourcePropertyCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.emfattributes.EStringCellEditingSupport;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 */
public class UiSnippetTableVisualisation extends AUiSnippetTableVisualisation {
	
	private static final String COLUMN_TEXT_NAME = "Name";
	
	/**
	 * 
	 */
	public UiSnippetTableVisualisation() {
		snippetImplementation.getPropertyInstanceValueSwitch().setShowEnumValueDefinitionValues(false);
	}
	
	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		ViewerColumn columnName = createDefaultColumn(COLUMN_TEXT_NAME);
		columnName.setEditingSupport(new EStringCellEditingSupport(editingDomain, (TableViewer) columnViewer, GeneralPackage.Literals.INAME__NAME));

		for (AProperty property : categoryModel.getAllProperties()) {
			TableViewerColumn colProperty = (TableViewerColumn) createDefaultColumn(property.getName());
			colProperty.getColumn().setToolTipText(property.getDescription());
			colProperty.setEditingSupport(createEditingSupportFor(editingDomain, columnViewer, property, snippetImplementation.getPropertyInstanceValueSwitch()));
		}
	}
	
	/**
	 * This method hands back the correct EditingSupport for a given property
	 * @param editingDomain The editing Domain that will be used with the EditingSupport
	 * @param tableViewer The viewer such as a Table in which this editor will be used
	 * @param property The property for which to get the corresponding Cell Editor
	 * @param valueSwitch 
	 * @return The cell editor that fits best to the given type
	 */
	private EditingSupport createEditingSupportFor(EditingDomain editingDomain, ColumnViewer tableViewer, AProperty property, 
			PropertyInstanceValueSwitch valueSwitch) {
		return new PropertydefinitionsSwitch<EditingSupport>() {
	    		
			@Override
    		public EditingSupport caseIntProperty(IntProperty property) {
    			return new VisValuePropertyCellEditingSupport(editingDomain, tableViewer, property);
    		}
    		
    		@Override
    		public EditingSupport caseStringProperty(StringProperty property) {
    			return new VisValuePropertyCellEditingSupport(editingDomain, tableViewer, property);
    		}
    		
    		@Override
    		public EditingSupport caseFloatProperty(FloatProperty property) {
    			return new VisValuePropertyCellEditingSupport(editingDomain, tableViewer, property);
    		}
    		
    		@Override
    		public EditingSupport caseBooleanProperty(BooleanProperty property) {
    			return new BooleanPropertyCellEditingSupport(editingDomain, tableViewer, property);
    		}
    		
    		@Override
    		public EditingSupport caseReferenceProperty(ReferenceProperty property) {
				return new ReferencePropertyCellEditingSupport(editingDomain, tableViewer, property);
    		};
    		
    		@Override
    		public EditingSupport caseResourceProperty(ResourceProperty property) {
    			return new ResourcePropertyCellEditingSupport(editingDomain, tableViewer, property);
    		}
    		
    		@Override
    		public EditingSupport caseEnumProperty(EnumProperty property) {
    			return new EnumPropertyCellEditingSupport(editingDomain, tableViewer, property, valueSwitch, false);
    		}

    		public EditingSupport defaultCase(EObject object) {
    			return null;
    		};
    		
    	}.doSwitch(property);	
	}
}
