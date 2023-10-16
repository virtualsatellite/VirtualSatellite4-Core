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



import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.emfattributes.EListStringCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.emfattributes.EStringCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetEStructuralFeatureTable;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * UI Snippet for role management. Implements the IUiSnippet interface for role management.
 * Manages disciplines and associated users.
 *
 */
public class UiSnippetRoleManagement extends AUiSnippetEStructuralFeatureTable implements IUiSnippet, FeatureUpdateCallback {

	private static final String SECTION_NAME = "Disciplines";
	private static final String SECTION_DESCRIPTION_PREFIX = "You are currently logged in as: ";
	
	private Button buttonAdd;
	private Button buttonRemove;
	
	private static final String BUTTON_ADD_TEXT = "Add Discipline";
	private static final String BUTTON_REMOVE_TEXT = "Remove Discipline";
	
	private static final String COLUMN_TEXT_DISCIPLINE = "Discipline Name";
	private static final String COLUMN_TEXT_USER = "User Name";
	
	/**
	 * Constructor for this class to instantiate a UI Snippet
	 */
	public UiSnippetRoleManagement() {
		super(RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES);
	}

	@Override
	protected String getSectionHeading() {
		return super.getSectionHeading() + SECTION_NAME;
	}
	
	@Override
	protected String getSectionDescription() {
		String user = UserRegistry.getInstance().getUserName();
		if (UserRegistry.getInstance().isSuperUser()) {
			user = user + " (Super User)";
		}
		return SECTION_DESCRIPTION_PREFIX + user;
	}

    /**
     * Open a custom dialog to manage users for a discipline.
     */
	private void openCustomDialog() {
	    Table table = tableViewer.getTable();
	    int selectionIndex = table.getSelectionIndex();

	    if (selectionIndex != -1) {
	        Discipline selectedDiscipline = (Discipline) tableViewer.getElementAt(selectionIndex);

	        // Extract the users for the selected discipline
	        String[] existingUsernames = selectedDiscipline.getUsers().toArray(new String[0]);

	        // Open the CustomDialog and pass the callback
	        CustomDialog customDialog = new CustomDialog(Display.getCurrent().getActiveShell(), existingUsernames, this);
	        customDialog.open();
	    }
	}


	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		
		// Column Discipline Name
		TableViewerColumn columnName = createDefaultColumn(tableViewer, COLUMN_TEXT_DISCIPLINE);
		columnName.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, true, GeneralPackage.Literals.INAME__NAME));
		columnName.setEditingSupport(new EStringCellEditingSupport(editingDomain, tableViewer, GeneralPackage.Literals.INAME__NAME));
		
		// Column of the user name
		TableViewerColumn columnUser = createDefaultColumn(tableViewer, COLUMN_TEXT_USER);
		columnUser.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, false, RolesPackage.Literals.DISCIPLINE__USERS));
		columnUser.setEditingSupport(new EListStringCellEditingSupport(editingDomain, tableViewer, RolesPackage.Literals.DISCIPLINE__USERS));
	    // Pack the column to adjust its width based on the content
	    columnUser.getColumn().pack();
	    
	 // Add a mouse listener to the columnUser to invoke the custom dialog
	    columnUser.getViewer().getControl().addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseDown(MouseEvent e) {
	            if (tableViewer.getTable().getColumnCount() > 1 && e.x > tableViewer.getTable().getColumn(0).getWidth()) {
	                openCustomDialog();
	            }
	        }
	    });
		 
	}
	
	@Override
	protected Composite createButtons(FormToolkit toolkit, Composite sectionBody) {
		Composite compositeButtons = super.createButtons(toolkit, sectionBody);
		
		buttonAdd = toolkit.createButton(compositeButtons, BUTTON_ADD_TEXT, SWT.PUSH);
		buttonRemove = toolkit.createButton(compositeButtons, BUTTON_REMOVE_TEXT, SWT.PUSH);		

		// Mark the Controls which should be checked for write access
		checkWriteAccess(buttonAdd, buttonRemove);

		return compositeButtons;
	}
	
	@Override
	protected void addButtonSelectionListeners(Composite composite, EditingDomain editingDomain) {
		
		buttonAdd.addSelectionListener(new SelectionListener() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		        // create a new Discipline with the name of the super user as default user name 
		        Discipline newDiscipline = RolesFactory.eINSTANCE.createDiscipline();
		        newDiscipline.setName("New Discipline");
		        newDiscipline.getUsers().add(UserRegistry.getInstance().getUserName());

		        Command addCommand = AddCommand.create(editingDomain, model, RolesPackage.eINSTANCE.getRoleManagement_Disciplines(), newDiscipline);
		        editingDomain.getCommandStack().execute(addCommand);
		        
		     // Refresh the viewer to update the display
		        tableViewer.refresh();
		    }

		    @Override
		    public void widgetDefaultSelected(SelectionEvent e) {
		        widgetSelected(e);
			     // Refresh the viewer to update the display
		        tableViewer.refresh();
		    }
		});

		buttonRemove.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					Command cmd = DeleteCommand.create(editingDomain, selection);
					editingDomain.getCommandStack().execute(cmd);
			
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}
		
	@Override
	protected Set<IMarkerHelper> getMarkerHelpers() {
		return Collections.singleton(new VirSatProblemMarkerHelper());
	}

	@Override
	public void onFeaturesChanged(String[] updatedFeatures) {
	    EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(model);
	    if (editingDomain != null) {
	        editingDomain.getCommandStack().execute((Command) new RecordingCommand((TransactionalEditingDomain) editingDomain) {
	            @Override
	            protected void doExecute() {
	                Discipline selectedDiscipline = getSelectedDiscipline(); 
	                if (selectedDiscipline != null) {
	                    selectedDiscipline.getUsers().clear();
	                    selectedDiscipline.getUsers().addAll(Arrays.asList(updatedFeatures));
	                }
	            }
	        });
	    }
	    // Refresh the viewer to update the display
	    tableViewer.refresh();
	}

	private Discipline getSelectedDiscipline() {
		int selectionIndex = tableViewer.getTable().getSelectionIndex();
        if (selectionIndex >= 0 && selectionIndex < tableViewer.getTable().getItemCount()) {
            return (Discipline) tableViewer.getElementAt(selectionIndex);
        }
        return null;
	}
	
}
