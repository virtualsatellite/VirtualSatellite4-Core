/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.statemachines.ui.wizard.ConstraintTableWizard;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;



/**
 * Handler to open the constraintTable wizard
 * @author bell_er
 *
 */
public class ConstraintTableHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
		ConstraintTableWizard wizard = new ConstraintTableWizard();
		wizard.setSelection(selection);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open(); 			
		return null;
	}
	@Override
	public boolean isEnabled() {
		ISelectionService  selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IStructuredSelection selection = (IStructuredSelection) selectionService.getSelection();
		EObject eObject = (EObject) selection.getFirstElement();

		TransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(eObject);
		VirSatResourceSet resSet = (VirSatResourceSet) ed.getResourceSet();
		Repository repository = resSet.getRepository();
		boolean hasRights = RightsHelper.hasSystemUserWritePermission(repository);
		if (eObject instanceof StructuralElementInstance && hasRights) {
			StructuralElementInstance sc = (StructuralElementInstance) eObject;
			String type = sc.getType().getName();
			return (type.equals(ElementConfiguration.class.getSimpleName()) || type.equals(ElementOccurence.class.getSimpleName()));
		}
		return false;
	}
}
