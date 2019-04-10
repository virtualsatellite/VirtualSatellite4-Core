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

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.command.SetArrayInstanceCommand;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.ui.propertyinstance.util.PreferencedPropertyInstanceValueSwitchFactory;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.uieingine.ui.dnd.ADropSelectionTargetListener;
import de.dlr.sc.virsat.uieingine.ui.dnd.DropHelper;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.PropertyInstanceEditingSupportFactory;

/**
 * this abstract class extends the ui snippet generic table and implements the ui snippet for the ui array instance property table
 * @author leps_je
 *
 */
public abstract class AUiSnippetArrayInstancePropertyTable extends AUiSnippetGenericTable implements IUiSnippet {
	
	protected String arrayInstanceId;
	
	private static final String COLUMN_TEXT_INDEX = "Index";
	private static final String COLUMN_TEXT_VALUE = "Value";
	
	/**
	 * the constructor for the abstract ui snippet array instance property table instantiate the array instance id and active the category id 
	 * @param conceptId the id of the concept 
	 * @param arrayInstanceId the id of the array instance
	 * @param categoryId the id of the active category
	 * @param style table style
	 */
	public AUiSnippetArrayInstancePropertyTable(String conceptId, String arrayInstanceId, String categoryId, int style) {
		super(conceptId, categoryId, style);
		this.arrayInstanceId = arrayInstanceId;
	}
	
	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
		
		DropHelper.createDropTarget(columnViewer.getControl(), new ADropSelectionTargetListener(editingDomain) {
			@Override
			public Command createDropCommand(StructuredSelection selection) {
				Concept activeConcept = acHelper.getConcept(conceptId);
				CompoundCommand dropCommand = new CompoundCommand("Drop");
				
				List<?> selectedObjects = selection.toList();
				for (int i = 0; i < selectedObjects.size(); ++i) {
					// We need to do two things:
					// 1. Create a new instance for the array
					// 2. Correctly initialize the newly created instance using the selection
					Command addCommand = createAddCommand(editingDomain, activeConcept);
					dropCommand.append(addCommand);
					
					ArrayInstance ai = getArrayInstance(model);
					int index = ai.getArrayInstances().size() + i;
					Command initCommand = SetArrayInstanceCommand.create(editingDomain, ai, index, selectedObjects.get(i));
					if (initCommand != null) {
						dropCommand.append(initCommand);
					}
				}
				
				return dropCommand;
			}
		});
	}
	
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
	
	/**
	 * this method get the content provider
	 * @return the content provider
	 */
	protected IStructuredContentProvider getTableContentProvider() {
		VirSatFilteredWrappedTreeContentProvider contentProvider = new VirSatFilteredWrappedTreeContentProvider(adapterFactory);
		contentProvider.addClassFilterToGetElement(APropertyInstance.class);
		return contentProvider;
	}
	
	/**
	 * this method get the label provider
	 * @return the label provider
	 */
	protected ITableLabelProvider getTableLabelProvider() {
		VirSatTransactionalAdapterFactoryLabelProvider labelProvider;
		PropertyInstanceValueSwitch valueSwitch = PreferencedPropertyInstanceValueSwitchFactory.createInstance();
		
		labelProvider = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory) {
			@Override
			public String getColumnText(Object object, int columnIndex) {
				super.getColumnText(object, columnIndex);
				if (object instanceof APropertyInstance) {
					APropertyInstance pi = (APropertyInstance) object;
					
					// Column 0 is always the name where as column 1 means the first property thus accessing it by 0
					if (columnIndex == 0) {
						return Integer.toString(getTableObjects().indexOf(pi));
					} else {
						ATypeInstance ti = valueSwitch.doSwitch(pi);
						redirectNotification(ti, pi);

						return valueSwitch.getValueString(ti);
					}
				}
				return ((IName) object).getName();
			}
		};
		
		return labelProvider;		
	}
	
	@Override
	protected String getTypeInformation() {
		return arrayInstanceId;
	}
	
	@Override
	protected AProperty getType() {
		return (AProperty) getArrayInstance(model).getType();
	}
	
	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		createDefaultColumn(COLUMN_TEXT_INDEX);
		
		ViewerColumn columnValue = createDefaultColumn(COLUMN_TEXT_VALUE);
		AProperty property = getType();
		columnValue.setEditingSupport(PropertyInstanceEditingSupportFactory.INSTANCE.createEditingSupportFor(editingDomain, columnViewer, property));
	}

	@Override
	protected void setTableViewerInput() {
		if (model instanceof CategoryAssignment) {
			columnViewer.setInput(getArrayInstance(model));
		}
	}
	
	/**
	 * this method get the array instance of
	 * @param model 
	 * @return the array instance if there is any
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
	protected List<APropertyInstance> getTableObjects() {
		return getArrayInstance(model).getArrayInstances();
	}
}
