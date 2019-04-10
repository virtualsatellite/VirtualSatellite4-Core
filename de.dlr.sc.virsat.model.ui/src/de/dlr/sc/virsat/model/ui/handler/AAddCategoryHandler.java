/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.ui.Activator;
import de.dlr.sc.virsat.model.ui.preferences.PreferencesEditorAutoOpen;

/**
 * This is a generic command handler to add a category to our data model
 * @author fisc_ph
 *
 */
public abstract class AAddCategoryHandler extends AbstractHandler implements IHandler {

	/**
	 * Return the repository in which to add the category as an active concept
	 * @return the Repository Object
	 */
	protected abstract Repository getRepository();
	
	/**
	 * To decide in which domain this command should be executed.
	 * This method is initializing a lot of information it should be called
	 * before the other getters are called
	 * @param selection 
	 * @return The Editing domain to execute the command
	 */
	protected abstract EditingDomain getEditingDomain(ISelection selection);
	
	/**
	 * this method get the first selected object
	 * @return the first selected object
	 */
	protected abstract EObject getFirstSelectedObject();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		EditingDomain ed = getEditingDomain(selection);
		EObject selectedObject = getFirstSelectedObject();
		
		Command addCategoryAssignment = createObjectAndAddCommand(ed, selectedObject);

		// We execute the chained command right here
		ed.getCommandStack().execute(addCategoryAssignment);
		
		// Try to open the CA in case it is preferred
		PreferencesEditorAutoOpen.openEditorIfPreferredForCollection(addCategoryAssignment.getResult());
		
		return null;
	}
	
	/**
	 * Creates the Category Assignment and the corresponding add command
	 * @param ed the editing domain
	 * @param selectedObject the selected object
	 * @return the add command
	 */
	public Command createObjectAndAddCommand(EditingDomain ed, EObject selectedObject) {
		// Check if the concept is already set in the repository
		// if it is we consider it as being active. Therefore we are not going to add it
		// in case it is not present we will add it to the repository
		Repository currentRepository = getRepository();
		ActiveConceptHelper acHelper = new ActiveConceptHelper(currentRepository);
		Concept activeConcept = acHelper.getConcept(getConceptName());
		if (activeConcept == null) {
			return UnexecutableCommand.INSTANCE;
		}
		
		// Build the command, logic for ensuring that the add is valid is handled on the command level
		Command addCategoryAssignment = createAddCommand(ed, selectedObject, activeConcept);
		return addCategoryAssignment;
		
	}
		
	/**
	 * Get concept path and name to create URI
	 * @return name in following format "de.dlr.sc.virsat.model.extension.interfaces/concept/Interfaces.concept" 
	 */
	protected abstract String getConceptName();
	
	/**
	 * this method added an command
	 * @param editingDomain 
	 * @param owner 
	 * @param activeConcept 
	 * @return the added command
	 */
	protected abstract Command createAddCommand(EditingDomain editingDomain, EObject owner, Concept activeConcept);
	
	/**
	 * this method calculate the command id consisting of the concept id and the category id
	 * @param conceptId the id of an concept
	 * @param categoryId the id of an category
	 * @return command id
	 */
	public static String calculateCommandId(String conceptId, String categoryId) {
		return conceptId + ".ui.command.Add" + categoryId;
	}
	
	private static final String HANDLER_COMMAND_ID = "commandId";
	private static final String HANDLER_EXTENSION_ID = "org.eclipse.ui.handlers";
	
	/**
	 * Gets the add handler for the passed concept type definition object
	 * @param conceptName the concept name
	 * @param conceptTypeDefinitionName the concept type definition name
	 * @return the add handler for instantiating and adding the desired object instance
	 */
	public static IHandler getAddHandler(String conceptName, String conceptTypeDefinitionName) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry != null) {
			String commandId = AAddCategoryHandler.calculateCommandId(conceptName, conceptTypeDefinitionName);
			IConfigurationElement[] configHandlers = registry.getConfigurationElementsFor(HANDLER_EXTENSION_ID);
			for (IConfigurationElement configHandler : configHandlers) {
				String handlerCommandId = configHandler.getAttribute(HANDLER_COMMAND_ID);
				if (commandId.equals(handlerCommandId)) {
					try {
						IHandler handler = (IHandler) configHandler.createExecutableExtension("class");
						return handler;
					} catch (CoreException e) {
						Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Could not resolve extension points for extending expressions", e));
					}
				}
			}
		}
		
		return null;
	}
	
	@Override
	public boolean isEnabled() {
		// Get the info of where to execute this handler
		ISelectionService  selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = selectionService.getSelection();

		// get the Editing Domain which will also store the current selection etc.
		EditingDomain ed = getEditingDomain(selection);
		
		// Finally try to build the command
		Command addCategoryAssignment = createObjectAndAddCommand(ed, getFirstSelectedObject());
			
		// And ask the command if it can be executed here in this location
		return addCategoryAssignment.canExecute();
	}
	
}
