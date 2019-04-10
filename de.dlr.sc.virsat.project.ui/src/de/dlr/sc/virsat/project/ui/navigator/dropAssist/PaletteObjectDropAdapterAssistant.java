/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.dropAssist;

import java.util.Collection;

import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.ui.handler.AAddCategoryHandler;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.project.ui.navigator.VirSatNavigator;
import de.dlr.sc.virsat.project.ui.transactional.handler.ITransactionalVirSatAddHandler;

/**
 * VirSat Drop Assistant to instantiate SEIs and CAs in the Navigator
 * @author muel_s8
 *
 */
public class PaletteObjectDropAdapterAssistant {

	private static final String HANDLER_COMMAND_ID = "commandId";
	private static final String HANDLER_EXTENSION_ID = "org.eclipse.ui.handlers";

	/**
	 * Method to create a Drop Command based on the Copy Paste functionality
	 * @param target The object to witch to drop to
	 * @param operation the type of DND operation
	 * @return the command if it is creatable or null in case it could not be created
	 */
	private Command createDropCommand(Object target, int operation) {
		CompoundCommand dropCommand = new CompoundCommand();
		
		if (target instanceof EObject && operation == DND.DROP_MOVE) {
			EObject eObject = (EObject) target;
			VirSatTransactionalEditingDomain virSatEd = VirSatEditingDomainRegistry.INSTANCE.getEd(eObject);
			ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
			
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structerdSelection = (IStructuredSelection) selection;
				
				@SuppressWarnings("unchecked")
				Collection<Object> dndObjects = structerdSelection.toList();
				
				Repository repository = virSatEd.getResourceSet().getRepository();
				ActiveConceptHelper acHelper = new ActiveConceptHelper(repository);
				
				// For each selected object we try to instantiate it by getting
				// the corresponding AddHandler. This way custom functionality from extending
				// the handlers or commands should be preserved
				
				dndObjects.forEach(object -> {
					if (object instanceof IConceptTypeDefinition) {
						IConceptTypeDefinition conceptTypeDefinition = (IConceptTypeDefinition) object;
						Concept conceptFromPalette = (Concept) (conceptTypeDefinition).eContainer();
						Concept conceptLocal = acHelper.getConcept(conceptFromPalette.getName());
						
						if (conceptLocal != null) {
							Command addCommand = createAddCommand(virSatEd, conceptLocal, conceptTypeDefinition, eObject);
							dropCommand.append(addCommand);
						}
					}
				});
				
			}
		}
		return dropCommand;
	}
	
	/**
	 * Creates the actual add command for the desired object
	 * @param ed the editing domain
	 * @param concept the active concept in the repository
	 * @param conceptTypeDefinition the concept type definition
	 * @param parent the parent
	 * @return the add command for the desired object
	 */
	public Command createAddCommand(EditingDomain ed, Concept concept, IConceptTypeDefinition conceptTypeDefinition, EObject parent) {
		IHandler addHandler = getAddHandler(concept, conceptTypeDefinition);
		
		if (addHandler instanceof ITransactionalVirSatAddHandler) {
			ITransactionalVirSatAddHandler addElementHandler = (ITransactionalVirSatAddHandler) addHandler;
			addElementHandler.initializeFieldsFromParentObject(parent);
			return addElementHandler.createObjectAndAddCommand(ed, parent);
		}
		
		return UnexecutableCommand.INSTANCE;
	}
	
	/**
	 * Gets the add handler for the passed concept type definition object
	 * @param concept the active concept in the repository
	 * @param conceptTypeDefinition the concept type definition object
	 * @return the add handler for instantiating and adding the desired object instance
	 */
	private IHandler getAddHandler(Concept concept, IConceptTypeDefinition conceptTypeDefinition) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry != null) {
			String commandId = AAddCategoryHandler.calculateCommandId(concept.getName(), conceptTypeDefinition.getName());
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
	
	/**
	 * CHecks if this is a valid drop target
	 * @param target the target
	 * @param operation the drop operation
	 * @return OK_STATUS iff the target is a valid dorp target, CANCEL_STATUS otherwise
	 */
	public IStatus validateDrop(Object target, int operation) {	
		Command dropCommand = createDropCommand(target, operation);
		if ((dropCommand != null) && (dropCommand.canExecute())) {
			return Status.OK_STATUS;
		} else {
			return Status.CANCEL_STATUS;
		}
	}

	/**
	 * Handles a drop
	 * @param aDropTargetEvent the drop event
	 * @param aTarget the target
	 * @return OK_STATUS iff the drop was handled, CANCEL_STATUS otherwise
	 */
	public IStatus handleDrop(DropTargetEvent aDropTargetEvent, Object aTarget) {
		Command dropCommand = createDropCommand(aTarget, aDropTargetEvent.detail);
		if (aTarget instanceof EObject) {
			EObject eObject = (EObject) aTarget;
			VirSatTransactionalEditingDomain virSatEd = VirSatEditingDomainRegistry.INSTANCE.getEd(eObject);
			if (virSatEd != null) {
				virSatEd.getCommandStack().execute(dropCommand);
				
				// Trying to set the selection in the Navigator to the target of the newly dropped object
				VirSatNavigator navigator = (VirSatNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(VirSatNavigator.VIRSAT_NAVIGATOR_ID);
				if (navigator != null) {
					CommonViewer commonViewer = navigator.getCommonViewer();
					if (commonViewer != null) {
						commonViewer.setSelection(new StructuredSelection(aTarget));
						return Status.OK_STATUS;
					}
				}
			}
		}
		
		return Status.CANCEL_STATUS;
	}

}
