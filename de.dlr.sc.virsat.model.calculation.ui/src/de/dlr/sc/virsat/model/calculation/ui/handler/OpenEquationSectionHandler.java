/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.ui.handler;

import java.util.Arrays;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.model.calculation.ui.EquationDSLUiModule;
import de.dlr.sc.virsat.model.calculation.ui.editor.EquationSectionUriEditorInput;
import de.dlr.sc.virsat.model.calculation.ui.editor.EquationSectionXtextEditor;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * handler to open the Equation Editor
 * @author fisc_ph
 *
 */
public class OpenEquationSectionHandler extends AbstractHandler implements IHandler {
	
	public static final String COMMAND_HANDLER_ID = "de.dlr.sc.virsat.model.calculation.ui.command.openEquationSectionEditor";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// get file from selection via the event
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(selection);
		
		EObject eObject = selectionHelper.getFirstEObject();
		
		if (eObject instanceof Equation) {
			eObject = eObject.eContainer().eContainer();
		} else if (eObject instanceof EquationSection) {
			eObject = eObject.eContainer();			
		} else if (eObject.eContainer() instanceof Equation) {
			eObject = eObject.eContainer().eContainer().eContainer();
		}
		
		openXtextEquationEditor(eObject);
		
		return null;
	}
	
	/**
	 * Opens another editor on the given type instance
	 * @param eObject the typeInstance for which to open an editor
	 */
	public static void openXtextEquationEditor(EObject eObject) {
		EquationSectionUriEditorInput editorInput = createXtextEquationEditorInput(eObject);
		try {
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
			workbenchPage.openEditor(editorInput, EquationSectionXtextEditor.EQUATION_SECTION_EDITOR_ID);
		} catch (PartInitException ex) {
			EquationDSLUiModule.LOGGER.error("de.dlr.sc.virsat.model.calculation.ui.handler: Part Init Exception" + Arrays.toString(ex.getStackTrace()));
		}
	}
	
	/**
	 * Creates another editor on the given type instance
	 * @param eObject the typeInstance for which to open an editor
	 * @return the editor
	 */
	public static EquationSectionUriEditorInput createXtextEquationEditorInput(EObject eObject) {
		Resource resource = eObject.eResource();
		URI resourceUri = resource.getURI();
		URI resourceAndEObjectUri = resourceUri.appendFragment(resource.getURIFragment(eObject));
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(resourceAndEObjectUri.toPlatformString(true)));
		return new EquationSectionUriEditorInput(file, resourceAndEObjectUri);
	}
}


