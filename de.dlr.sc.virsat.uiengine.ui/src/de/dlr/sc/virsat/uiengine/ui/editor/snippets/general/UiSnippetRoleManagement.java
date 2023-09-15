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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.emfattributes.EStringCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetEStructuralFeatureTable;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * class ui snippet role management implements the interface ui snippet for the role management
 * @author leps_je
 *
 */
public class UiSnippetRoleManagement extends AUiSnippetEStructuralFeatureTable implements IUiSnippet {

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

	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		// Column Discipline Name
		TableViewerColumn columnName = createDefaultColumn(tableViewer, COLUMN_TEXT_DISCIPLINE);
		columnName.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, true, GeneralPackage.Literals.INAME__NAME));
		columnName.setEditingSupport(new EStringCellEditingSupport(editingDomain, tableViewer, GeneralPackage.Literals.INAME__NAME));
		
		/*// Column of the user name
		TableViewerColumn columnUser = createDefaultColumn(tableViewer, COLUMN_TEXT_USER);
		columnUser.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, false, RolesPackage.Literals.DISCIPLINE__USER));
		columnUser.setEditingSupport(new EStringCellEditingSupport(editingDomain, tableViewer, RolesPackage.Literals.DISCIPLINE__USER));*/		
	    
		// Column of the user names (modified for multiple users)
		TableViewerColumn columnUsers = createDefaultColumn(tableViewer, COLUMN_TEXT_USER);
		columnUsers.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, false, RolesPackage.Literals.DISCIPLINE__USER));
		// Use a custom editing support for multiple users
		columnUsers.setEditingSupport(new EListStringCellEditingSupport(editingDomain, tableViewer, RolesPackage.Literals.DISCIPLINE__USER)); 
	
	}
	
	/**
	 * Custom editing support for handling multiple users as a list of strings.
	 */
	private class EListStringCellEditingSupport extends EStringCellEditingSupport {

	    private EListStringCellEditingSupport(EditingDomain editingDomain, TableViewer viewer, EAttribute feature) {
	        super(editingDomain, viewer, feature);
	    }

	    @Override
	    protected Object getValue(Object element) {
	        Object value = super.getValue(element);
	        if (value instanceof String) {
	            return Arrays.asList(((String) value).split(", ")); // Split the string into a list of user names
	        }
	        return Collections.emptyList();
	    }

	    /*@Override
	    protected void setValue(Object element, Object value) {
	        if (value instanceof List<?>) {
	            // Join the list of user names into a single string separated by ", "
	        	String newValue = String.join(", ", ((List<?>) value).toArray(new String[0]));
	            super.setValue(element, newValue);
	        }
	    }*/
	    
	    @Override
	    protected void setValue(Object element, Object userInputValue) {
	        if (element instanceof Discipline && userInputValue instanceof String) {
	            Discipline discipline = (Discipline) element;
	            String[] userArray = ((String) userInputValue).split(", ");
	            discipline.getUser().clear(); // Clear existing users
	            discipline.getUser().addAll(Arrays.asList(userArray));
	            getViewer().update(element, null);
	        }
	    }

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
	            // Create a new Discipline with the name of the super user as default user name
	            Discipline newDiscipline = RolesFactory.eINSTANCE.createDiscipline();
	            newDiscipline.setName("New Discipline");
	            // Initialize the user list with at least one user (current user or default user)
	            newDiscipline.getUser().add(UserRegistry.getInstance().getUserName());
	            
	            Command addCommand = AddCommand.create(editingDomain, model, RolesPackage.eINSTANCE.getRoleManagement_Disciplines(), newDiscipline);
	            editingDomain.getCommandStack().execute(addCommand);
	        }

	        @Override
	        public void widgetDefaultSelected(SelectionEvent e) {
	            widgetSelected(e);
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

}