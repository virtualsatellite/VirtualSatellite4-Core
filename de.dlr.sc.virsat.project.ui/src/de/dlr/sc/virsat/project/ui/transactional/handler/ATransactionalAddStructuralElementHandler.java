/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.transactional.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;
import de.dlr.sc.virsat.model.ui.preferences.PreferencesEditorAutoOpen;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;
import de.dlr.sc.virsat.project.ui.navigator.VirSatNavigator;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * Abstract handler to add StructuralElementInstances
 *
 */
public abstract class ATransactionalAddStructuralElementHandler extends AbstractHandler implements ITransactionalVirSatAddHandler {

	protected EObject parentObject;
	protected StructuralElementInstance createdSei;
	protected VirSatTransactionalEditingDomain ed;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		initializeFieldsFromSelection(selection);

		Command addStructuralElementInstance = createObjectAndAddCommand(ed, parentObject);
		ed.getCommandStack().execute(addStructuralElementInstance);
		
		expandAndSelectObjectInNavigator();
		
		// Try to open the CA in case it is preferred
		PreferencesEditorAutoOpen.openEditorIfPreferredForCollection(addStructuralElementInstance.getResult());
		
		return null;
	}

	@Override
	public Command createObjectAndAddCommand(EditingDomain ed, EObject parent) {
		Concept activeConcept = getActiveConcept();
		if (activeConcept == null) {
			return UnexecutableCommand.INSTANCE;
		}

		createdSei = createStructuralElementInstance(activeConcept);
		Command addStructuralElementInstance = createAddCommand(parentObject, createdSei);
		return addStructuralElementInstance;
	}
	
	/**
	 * Expands the navigator tree to the passed object and selects it
	 */
	private void expandAndSelectObjectInNavigator() {
		VirSatNavigator navigator = (VirSatNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(VirSatNavigator.VIRSAT_NAVIGATOR_ID);
		if (navigator != null) {
			navigator.getCommonViewer().refresh(parentObject);
			navigator.getCommonViewer().expandToLevel(createdSei, 0);
			navigator.getCommonViewer().setSelection(new StructuredSelection(createdSei));
		}
	}

	@Override
	public boolean isEnabled() {
		// Get the info of where to execute this handler
		ISelectionService  selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = selectionService.getSelection();

		initializeFieldsFromSelection(selection);
		
		Command addStructuralCommand = createObjectAndAddCommand(ed, parentObject);
			
		// And ask the command if it can be executed here in this location
		return addStructuralCommand.canExecute();
	}
	
	/**
	 * Initializes current selected object and editing domain from selection
	 * @param selection 
	 */
	protected void initializeFieldsFromSelection(ISelection  selection) {
		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(selection);
		initializeFieldsFromParentObject(selectionHelper.getFirstEObject());
	}
	
	@Override
	public void initializeFieldsFromParentObject(EObject parentObject) {
		this.parentObject = parentObject;
		ed = VirSatEditingDomainRegistry.INSTANCE.getEd(parentObject);
	}

	/**
	 * Gets active concept or null
	 * @return Concept for the concrete Add SEI handler or null if it is not in active concepts
	 */
	protected Concept getActiveConcept() {
		VirSatResourceSet virSatResourceSet = ed.getResourceSet();
		Repository currentRepository = virSatResourceSet.getRepository();
		ActiveConceptHelper acHelper = new ActiveConceptHelper(currentRepository);
		return acHelper.getConcept(getConceptName());
	}

	/**
	 * The name returned by this method will be used to determine the StructuralElement to be instantiated 
	 * @return the name of the StructuralElement in the concept.
	 */
	protected abstract String getStructuralElementName();
	
	/**
	 * Get concept path and name to create URI
	 * @return name in following format "de.dlr.sc.virsat.model.extension.interfaces/concept/Interfaces.concept" 
	 */
	protected abstract String getConceptName();

	/**
	 * Create a command to add structuralElementInstance to owner
	 * @param owner 
	 * @param structuralElementInstance 
	 * @return command
	 */
	protected Command createAddCommand(EObject owner, StructuralElementInstance structuralElementInstance) {
		return CreateAddSeiWithFileStructureCommand.create(ed, owner, structuralElementInstance);
	}

	/**
	 * Override this method to actually create the correct StructuralElementInstance that should
	 * be added by the concrete implementation of this handler
	 * @param activeConcept which will be used to type the StructuralElementInstance with a registered StructuralElement
	 * @return the StructuralElementInstance which is well typed by the currently loaded concepts
	 */
	protected StructuralElementInstance createStructuralElementInstance(Concept activeConcept) {
		String structuralElementName = getStructuralElementName();
		StructuralElement structuralElement = ActiveConceptHelper.getStructuralElement(activeConcept, structuralElementName);
		StructuralInstantiator structuralInstantiator = new StructuralInstantiator();
		StructuralElementInstance structuralElementInstance = structuralInstantiator.generateInstance(structuralElement, structuralElementName);
		
		if (parentObject instanceof IAssignedDiscipline) {
			Discipline parentDiscipline = ((IAssignedDiscipline) parentObject).getAssignedDiscipline();
			structuralElementInstance.setAssignedDiscipline(parentDiscipline);
		}
		
		return structuralElementInstance;
	}
}
