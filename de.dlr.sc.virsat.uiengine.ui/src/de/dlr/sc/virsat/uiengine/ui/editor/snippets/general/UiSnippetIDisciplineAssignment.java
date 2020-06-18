/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets.general;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.resources.command.AssignDisciplineCommand;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiEStructuralFeatureSectionSnippet;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * the class ui snippet IDiscipline Assignment implements the interface ui snippet for discipline assignment part
 * @author leps_je
 *
 */
public class UiSnippetIDisciplineAssignment extends AUiEStructuralFeatureSectionSnippet implements IUiSnippet {

	protected static final String SECTION_HEADING = "Discipline Assignment Section";
	
	private ComboViewer comboViewer;
	
	private Button buttonApply;
	private Button buttonApplyRecursive;
	
	private static final int GRIDLAYOUT_SIZE_THREE = 3;
	private static final int SIZE_TWO_HUNDRED = 200;
	
	private static final String BUTTON_APPLY_TEXT = "Apply Discipline";
	private static final String BUTTON_APPLY_RECURSIVE_TEXT = "Apply Discipline Recursive";
	
	/**
	 * constructor for the ui snippet IDiscipline assignment 
	 */
	public UiSnippetIDisciplineAssignment() {
		super(GeneralPackage.Literals.IASSIGNED_DISCIPLINE__ASSIGNED_DISCIPLINE);
	}

	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
		
		Composite sectionBody = createSectionBody(toolkit, SECTION_HEADING, null, GRIDLAYOUT_SIZE_THREE);
		
		comboViewer = new ComboViewer(sectionBody, SWT.READ_ONLY);
		buttonApply = toolkit.createButton(sectionBody, BUTTON_APPLY_TEXT, SWT.PUSH);
		buttonApplyRecursive = toolkit.createButton(sectionBody, BUTTON_APPLY_RECURSIVE_TEXT, SWT.PUSH);
		
		setUpButtons(editingDomain);

		checkWriteAccess(buttonApply);
	    checkWriteAccess(buttonApplyRecursive);
	    checkWriteAccess(comboViewer.getCombo());

	}

	/**
	 * Method to set up all the Buttons
	 * @param editingDomain The EditingDomain to get the ResourceSet from
	 */
	private void setUpButtons(EditingDomain editingDomain) {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.widthHint = SIZE_TWO_HUNDRED;
		
		setUpComboViewer(gridData);
	    
	    buttonApply.setLayoutData(gridData);
	    buttonApplyRecursive.setLayoutData(gridData);
	    boolean recursiveSetDisciplineEnabled = !((IAssignedDiscipline) model).getContainedIAssignedDisciplines().isEmpty();
	    buttonApplyRecursive.setEnabled(recursiveSetDisciplineEnabled);
	    
	    addButtonSelectionListeners(editingDomain);
	}

	/**
	 * Adds the click behavior to the buttons
	 * @param editingDomain the editing domain
	 */
	private void addButtonSelectionListeners(EditingDomain editingDomain) {
	    
	    buttonApplyRecursive.addSelectionListener(new SelectionListener() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		
				Object selectedObject = comboViewer.getStructuredSelection().getFirstElement();
	    		
	    		if ((selectedObject != null) && (selectedObject instanceof Discipline)) {
	    			Discipline discipline = (Discipline) selectedObject;
	    		
	    			CompoundCommand cmpCmd = new CompoundCommand();
	    			
	    			Command cmd = new AssignDisciplineCommand(editingDomain, model, discipline);
					if (cmd.canExecute()) {
						cmpCmd.append(cmd);
					}

					// Only add the command to update the element which is allowed to be changed by the current user
					EList<IAssignedDiscipline> containedIAssignedDisciplines = ((IAssignedDiscipline) model).getContainedIAssignedDisciplines();
					for (IAssignedDiscipline contained : containedIAssignedDisciplines) {
						Command cmdUpdateContained = new AssignDisciplineCommand(editingDomain, contained, discipline);
						if (cmdUpdateContained.canExecute()) {
							cmpCmd.append(cmdUpdateContained);
						}
					}

					// Execute the whole command in one go
	    			editingDomain.getCommandStack().execute(cmpCmd);
	    		}
	    	}

	    	@Override
	    	public void widgetDefaultSelected(SelectionEvent e) {
	    		widgetSelected(e);
	    	}
	    });	
		
		buttonApply.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object selectedObject = comboViewer.getStructuredSelection().getFirstElement();

				if ((selectedObject != null) && (selectedObject instanceof Discipline)) {
					Discipline discipline = (Discipline) selectedObject;

					Command cmd = new AssignDisciplineCommand(editingDomain, model, discipline);
					editingDomain.getCommandStack().execute(cmd);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	/**
	 * Method to set up the ComboBox for the Disciplines
	 * @param gridData The GridData for to look good
	 */
	private void setUpComboViewer(GridData gridData) {
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(gridData);

		VirSatFilteredWrappedTreeContentProvider cp = new VirSatFilteredWrappedTreeContentProvider(adapterFactory);

		cp.addClassFilter(RoleManagement.class);
		cp.addClassFilter(Discipline.class);

		comboViewer.setContentProvider(cp);
		comboViewer.setLabelProvider(new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory));

		setComboViewer(model);
	}

	/**
	 * this method set the combo viewer with all disciplines
	 * @param model for which to get the managing VirSatResourceSet
	 */
	private void setComboViewer(EObject model) {
		if (model instanceof IAssignedDiscipline) {
			Discipline currentDiscipline = ((IAssignedDiscipline) model).getAssignedDiscipline();
			if (currentDiscipline != null) {
				comboViewer.setSelection(new StructuredSelection(currentDiscipline));
			}
		}
	}
	
	@Override
	public void setDataBinding(DataBindingContext dbCtx, EditingDomain editingDomain, EObject model) {
		super.setDataBinding(dbCtx, editingDomain, model);
		
		VirSatResourceSet virSatResourceSet = VirSatResourceSet.getVirSatResourceSet(model); 
		if (virSatResourceSet != null) {
			RoleManagement rm = virSatResourceSet.getRoleManagement();
			comboViewer.setInput(rm);
			setComboViewer(model);
		}
	}
	
}
