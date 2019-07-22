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
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.extension.requirements.ui.celleditor.RequirementsAttributeTypeEnumerationEditingSupport;
import de.dlr.sc.virsat.model.extension.requirements.ui.provider.RequirementsAttributeDefinitionTableLabelProvider;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.emfattributes.EStringCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;


/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementTypeAttributesRequirementAttribute extends AUiSnippetTableRequirementTypeAttributesRequirementAttribute implements IUiSnippet {

	
	private static final String COLUMN_TEXT_NAME = "Name";
	private static final String NAME_TYPE_PROPERTY = "type";
	private static final String NAME_ENUMERATION_PROPERTY = "enumeration";
	
	private FormToolkit toolKit;
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetGenericCategoryAssignmentTable#createTableColumns(org.eclipse.emf.edit.domain.EditingDomain)
	 */
	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		TableViewerColumn colName = (TableViewerColumn) createDefaultColumn(COLUMN_TEXT_NAME);
		
		//we know that createDefaultColumn returns tableViewerColumn so we cast it to tableViewerColumn
		colName.setEditingSupport(new EStringCellEditingSupport(editingDomain, (TableViewer) columnViewer, GeneralPackage.Literals.INAME__NAME));
		
		//Type property table
		EnumProperty typeProp = (EnumProperty) ActiveConceptHelper.getProperty(categoryModel, NAME_TYPE_PROPERTY);
		TableViewerColumn colType = (TableViewerColumn) createDefaultColumn(typeProp.getName());
		colType.setEditingSupport(new RequirementsAttributeTypeEnumerationEditingSupport(toolKit, editingDomain, columnViewer, typeProp));
		colType.getColumn().setToolTipText(typeProp.getDescription());
		
		//Enumeration containment property
		AProperty enumerationTypeProp = ActiveConceptHelper.getProperty(categoryModel, NAME_ENUMERATION_PROPERTY);
		TableViewerColumn colProperty = (TableViewerColumn) createDefaultColumn("");
		colProperty.getColumn().setToolTipText(enumerationTypeProp.getDescription());
		colProperty.setEditingSupport(createEditingSupport(editingDomain, enumerationTypeProp));
		

	}

	
	// Show attributes in the order of their creation
	@Override
	protected void setUpTableViewer(EditingDomain editingDomain, FormToolkit toolkit) {
		super.setUpTableViewer(editingDomain, toolkit);
		
		List<?> tableObjects = getTableObjects();
		
		// Sort the array entries by their index
		columnViewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				int lhsIndex = tableObjects.indexOf(e1);
				int rhsIndex = tableObjects.indexOf(e2);
				return Integer.compare(lhsIndex, rhsIndex);
			}
		});
	}
	
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetGenericCategoryAssignmentTable#getTableLabelProvider()
	 */
	@Override
	protected ITableLabelProvider getTableLabelProvider() {
		return new RequirementsAttributeDefinitionTableLabelProvider(adapterFactory);
	}


	@Override
	protected List<APropertyInstance> getTableObjects() {
		return getArrayInstance(model).getArrayInstances();
	}
	


	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSectionSnippet#createSectionBody(org.eclipse.ui.forms.widgets.FormToolkit, java.lang.String, java.lang.String, int)
	 */
	@Override
	public Composite createSectionBody(FormToolkit toolkit, String sectionHeading, String sectionDescription,
			int numberColumns) {
		this.toolKit = toolkit;
		return super.createSectionBody(toolkit, sectionHeading, sectionDescription, numberColumns);
	}
	
	
	
}
