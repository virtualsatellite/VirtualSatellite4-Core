/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredContentProvider;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.uiengine.ui.editor.provider.ArrayInstanceFilteredTransactionalAdapterFactoryContentProvider;

/**
 * This abstract class extends the generic table. It is used to display Arrays of other Types. For example it allows to display #
 * an array of Categories within a Category. This is the Case of composing information into another category either by static
 * or dynamic arrays. This table is not used for a referenced category which actually happens by an Array of ReferenceProperties.
 * Therefore the other available Tables have to be used.
 * 
 * @author leps_je
 *
 */
public abstract class AUiSnippetArrayInstanceCategoryTable extends AUiSnippetGenericCategoryAssignmentTable {

	protected String arrayInstanceId;
	protected String activeForCategoryId;
	
	/**
	 * constructor of the abstract UI snippet array instance category table instantiate the ID of the concept and the active category id
	 * @param conceptId the id of the concept
	 * @param categoryId the id of the category
	 * @param arrayInstanceId the array instance id
	 * @param activeForCategoryId the active for the category id
	 * @param fullQualifiedCategoryId the full qualified category id
	 * @param style The style as binary mask defining which buttons should be displayed with the given table (e.g. Add/Remove/Drill-Down)
	 */
	public AUiSnippetArrayInstanceCategoryTable(String conceptId, String categoryId, String arrayInstanceId, String activeForCategoryId, String fullQualifiedCategoryId, int style) {
		super(conceptId, categoryId, fullQualifiedCategoryId, style);
		this.arrayInstanceId = arrayInstanceId;
		this.activeForCategoryId = activeForCategoryId;
	}
	
	@Override
	protected String getTypeInformationFull() {
		return arrayInstanceId + " - " + categoryId;
	}

	@Override
	protected IStructuredContentProvider getTableContentProvider() {
		ArrayInstanceFilteredTransactionalAdapterFactoryContentProvider contentProvider = new ArrayInstanceFilteredTransactionalAdapterFactoryContentProvider(adapterFactory);
		contentProvider.addClassFilterToGetElement(CategoryAssignment.class);
		contentProvider.addCategoryIdFilter(fullQualifiedCategoryId);
		
		return contentProvider;
	}
	
	@Override
	protected void setTableViewerInput() {
		if (model instanceof CategoryAssignment) {
			columnViewer.setInput(getArrayInstance(model));
		}
	}

	@Override
	public boolean isActive(EObject model) {
		if (!(model instanceof CategoryAssignment)) {
			return false;
		}
		
		CategoryAssignment ca = (CategoryAssignment) model;
		String typeName = ca.getType().getName();
		
		return typeName.equals(activeForCategoryId);
	}
	
	/**
	 * this method get the array instance. The Id is defined with the constructor of this table
	 * @param model In this case it should be the current category of which the array instance for the given ID (ArrayInstanceID) should be retrieved
	 * @return array instance in case it could be found otherwise null. 
	 */
	protected ArrayInstance getArrayInstance(EObject model) {
		if (model instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) model;
			CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(ca);
			ArrayInstance arrayInstance = (ArrayInstance) caHelper.getPropertyInstance(arrayInstanceId);
			return arrayInstance;
		}
		return null;
	}
	
	@Override
	protected Collection<APropertyInstance> getTableObjects() {
		return getArrayInstance(model).getArrayInstances();
	}
}
