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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.concept.migrator.ConceptMigrator;
import de.dlr.sc.virsat.model.concept.util.ConceptActivationHelper;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.project.ui.migrator.handler.MigrateConceptToLatestHandler;
import de.dlr.sc.virsat.uieingine.ui.DVLMEditorPlugin;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.emfattributes.EBooleanCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.dialog.ActiveConceptSelectionDialogFactory;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetEStructuralFeatureTable;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * the class ui snippet active concepts implements the interface ui snippet for the active concept part
 */
public class UiSnippetActiveConcepts extends AUiSnippetEStructuralFeatureTable implements IUiSnippet {

	private static final String SECTION_NAME = "Active Concepts";
	
	private Button buttonAdd;
	private Button buttonUpdateAll;

	private static final String BUTTON_ADD_TEXT = "Add from Registry";
	private static final String BUTTON_UPDATEALL_TEXT = "Update all Concepts";
	
	private static final String COLUMN_TEXT_CONCEPT = "Concept Name";
	private static final String COLUMN_TEXT_CONCEPT_ID = "Concept ID";
	private static final String COLUMN_TEXT_ACTIVE = "Active";
	private static final String COLUMN_TEXT_VERSION = "Version";
	
	/**
	 * Constructor for this class to instantiate a UI Snippet
	 */
	public UiSnippetActiveConcepts() {
		super(DVLMPackage.Literals.REPOSITORY__ACTIVE_CONCEPTS); 
	}

	@Override
	protected String getSectionHeading() {
		return super.getSectionHeading() + SECTION_NAME;
	}

	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		// Column Concept Concept Name
		TableViewerColumn columnConceptDisplayName = createDefaultColumn(tableViewer, COLUMN_TEXT_CONCEPT);
		columnConceptDisplayName.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, true, ConceptsPackage.Literals.CONCEPT__DISPLAY_NAME));
		
		// Column Concept Id
		TableViewerColumn columnConceptId = createDefaultColumn(tableViewer, COLUMN_TEXT_CONCEPT_ID);
		columnConceptId.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, true, GeneralPackage.Literals.IQUALIFIED_NAME__NAME));
				
		// Column Concept Is Active
		TableViewerColumn columnConceptIsActive = createDefaultColumn(tableViewer, COLUMN_TEXT_ACTIVE);
		columnConceptIsActive.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, false, ConceptsPackage.Literals.IACTIVE_CONCEPT__ACTIVE));
		columnConceptIsActive.setEditingSupport(new EBooleanCellEditingSupport(editingDomain, tableViewer, ConceptsPackage.Literals.IACTIVE_CONCEPT__ACTIVE));

		// Column Concept Version - gets different LabelProvider with ImageProvider
		TableViewerColumn columnVersion = createDefaultColumn(tableViewer, COLUMN_TEXT_VERSION);
		columnVersion.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, false, ConceptsPackage.Literals.CONCEPT__VERSION));
	}
	
	@Override
	protected Composite createButtons(FormToolkit toolkit, Composite sectionBody) {
		Composite compositeButtons = super.createButtons(toolkit, sectionBody);
		
		buttonAdd = toolkit.createButton(compositeButtons, BUTTON_ADD_TEXT, SWT.PUSH);
		buttonUpdateAll = toolkit.createButton(compositeButtons, BUTTON_UPDATEALL_TEXT, SWT.PUSH);

		// Mark the Controls which should be checked for write access
		checkWriteAccess(buttonAdd, buttonUpdateAll);
		
		return compositeButtons;
	}
	
	@Override
	protected void addButtonSelectionListeners(Composite composite, EditingDomain editingDomain) {
		
		buttonAdd.addSelectionListener(new SelectionListener() { 
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				ConceptActivationHelper activationHelper = new ConceptActivationHelper((Repository) model);
				ListSelectionDialog dialog = ActiveConceptSelectionDialogFactory.createActiveConceptSelectionDialog(composite.getShell(), (Repository) model, "Select a Concept to be added");
				if (dialog.open() == Dialog.OK) {
					Object[] selectedObjects = dialog.getResult();
					WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
						@Override
						protected void execute(IProgressMonitor progressMonitor) throws CoreException {
							activationHelper.activateConcepts(selectedObjects, editingDomain, progressMonitor);
						}
					};
					
					try {
						operation.run(new NullProgressMonitor());
					} catch (InvocationTargetException | InterruptedException exception) {
						Status status = new Status(Status.ERROR, DVLMEditorPlugin.ID, "Failure while enabling concept! ", exception);
						DVLMEditPlugin.getPlugin().getLog().log(status);
						ErrorDialog.openError(Display.getDefault().getActiveShell(), "Failed to add Concept", "Failed to add Concept", status);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		buttonUpdateAll.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Repository repository = (Repository) model;
				
				List<Concept> toUpgrade = new ArrayList<Concept>();
				
				// Get all concepts that need upgrading
				for (Concept concept : repository.getActiveConcepts()) {
					ConceptMigrator conceptMigrator = new ConceptMigrator(concept);
					if (conceptMigrator.needsMigration()) {
						toUpgrade.add(concept);
					}
				}
				
				if (toUpgrade.size() > 0) {
					// Upgrade all of them in one go
					MigrateConceptToLatestHandler.migrateToLatest(toUpgrade, (TransactionalEditingDomain) editingDomain);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}
	

}
