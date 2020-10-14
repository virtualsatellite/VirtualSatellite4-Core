/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.snippet;

import java.util.List;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.ui.celleditor.RequirementsAttributeValueEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementElementsAttributeValue extends AUiSnippetTableRequirementElementsAttributeValue
		implements IUiSnippet {

	private static final String VALUE_PROPERTY_NAME = "value";
	private static final int VALUE_COLUMN_SIZE = 700;
	private static final int TABLE_HIGHT = 400;
	private static final int COLUMN_HIGHT = 30;
	private static final String SECTION_HEADING = "Requirement Attributes";
	private static final String SECTION_DESCRIPTION = "Specify the different requirment attributes as defined in its type.";

	/**
	 * Constructor for this editor snippet
	 */
	public UiSnippetTableRequirementElementsAttributeValue() {
		this.style = STYLE_EDITOR_BUTTON;
		hideNameColumn = true;
		snippetImplementation.getPropertyInstanceValueSwitch().setShowLocationForReferenceValues(false);
	}
	

	@Override
	protected void createTableColumns(EditingDomain editingDomain) {

		//Hide the name column... The super class's label provider still expects it so we create a dummy column
		TableViewerColumn colName = (TableViewerColumn) createDefaultColumn("");
		if (hideNameColumn) {
			colName.getColumn().setWidth(0);
		}
		
		for (AProperty property : categoryModel.getAllProperties()) {

			TableViewerColumn colProperty = (TableViewerColumn) createDefaultColumn(property.getName());
			colProperty.getColumn().setToolTipText(property.getDescription());
			
			if (property.getName().equals(VALUE_PROPERTY_NAME)) {
				colProperty.getColumn().setWidth(VALUE_COLUMN_SIZE);
				colProperty.setEditingSupport(
						new RequirementsAttributeValueEditingSupport(editingDomain, columnViewer));
			}
		}

	}

	// Show attributes in the order of their creation
	@Override
	protected void setUpTableViewer(EditingDomain editingDomain, FormToolkit toolkit) {
		super.setUpTableViewer(editingDomain, toolkit);
		
		
		// Sort the array entries by their index
		columnViewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				RequirementAttribute attE1 = new AttributeValue(((ComposedPropertyInstance) e1).getTypeInstance()).getAttType();
				RequirementAttribute attE2 = new AttributeValue(((ComposedPropertyInstance) e2).getTypeInstance()).getAttType();
				ArrayInstance array = (ArrayInstance) attE1.getATypeInstance().eContainer().eContainer();
	
				int lhsIndex = array.getArrayInstances().indexOf(attE1.getTypeInstance().eContainer());
				int rhsIndex = array.getArrayInstances().indexOf(attE2.getTypeInstance().eContainer());
				return Integer.compare(lhsIndex, rhsIndex);
			}
		});
	}
	
	@Override
	protected Table createDefaultTable(FormToolkit toolkit, Composite sectionBody) {
		int numberAtts = new Requirement(((CategoryAssignment) model)).getElements().size();
		Table table = super.createDefaultTable(toolkit, sectionBody);
		GridData gridDataTable = (GridData) table.getLayoutData();
		gridDataTable.heightHint = Math.min(COLUMN_HIGHT * numberAtts, TABLE_HIGHT);
		return table;
	}
	
	@Override
	public Composite createSectionBody(FormToolkit toolkit, String sectionHeading, String sectionDescription,
			int numberColumns) {
		return super.createSectionBody(toolkit, SECTION_HEADING, SECTION_DESCRIPTION, numberColumns);
	}
	
	@Override
	protected List<APropertyInstance> getTableObjects() {
		return getArrayInstance(model).getArrayInstances();
	}

}
