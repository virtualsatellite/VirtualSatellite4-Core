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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.ui.handler.AAddCategoryHandler;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.navigator.VirSatNavigator;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * The transactional version of the AddCategory Handler
 * 
 * @author fisc_ph
 *
 */
public abstract class ATransactionalAddCategoryHandler extends AAddCategoryHandler implements ITransactionalVirSatAddHandler {

	protected VirSatTransactionalEditingDomain ed;
	
	@Override
	protected EObject getFirstSelectedObject() {
		return firstSelectedObject;
	}

	@Override
	protected Repository getRepository() {
		return virSatResourceSet.getRepository();
	}

	protected EObject firstSelectedObject;
	private VirSatResourceSet virSatResourceSet;
	
	@Override
	protected EditingDomain getEditingDomain(ISelection  selection) {
		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(selection);
		initializeFieldsFromParentObject(selectionHelper.getFirstEObject());
		return ed;
	}
	
	@Override
	public void initializeFieldsFromParentObject(EObject parentObject) {
		firstSelectedObject = parentObject;
		ed = VirSatEditingDomainRegistry.INSTANCE.getEd(firstSelectedObject);
		virSatResourceSet = ed.getResourceSet();
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Set<EObject> oldChildren = new HashSet<>(firstSelectedObject.eContents());
		
		super.execute(event);
		
		//following code is getting the newly added category assignment
		//we are not able to get it directly from the command
		//since the AddCommand is wrapped under Rollmanagement command
		EObject newChild = null;
		EList<EObject> newChildren = firstSelectedObject.eContents();
		for (EObject eObject : newChildren) {
			if (!oldChildren.contains(eObject)) {
				newChild = eObject;
			}
		}
		expantAndSelectObjectInNavigator(newChild);
		return null;
	}
	
	/**
	 * Expands the navigator tree to the passed object and selects it
	 * @param ca 
	 */
	private void expantAndSelectObjectInNavigator(Object ca) {
		VirSatNavigator navigator = (VirSatNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(VirSatNavigator.VIRSAT_NAVIGATOR_ID);
		if (navigator != null) {
			navigator.getCommonViewer().refresh(firstSelectedObject);
			navigator.getCommonViewer().expandToLevel(ca, 0);
			navigator.getCommonViewer().setSelection(new StructuredSelection(ca));
		}
	}
}
