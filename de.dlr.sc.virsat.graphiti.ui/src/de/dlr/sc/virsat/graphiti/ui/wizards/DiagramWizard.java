/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.ui.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import de.dlr.sc.virsat.graphiti.ui.Activator;
import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * The Class DiagramWizard.
 */
public class DiagramWizard extends BasicNewResourceWizard {

	private static final String PAGE_NAME_DIAGRAM_NAME = "Diagram Name";
	private static final String WIZARD_WINDOW_TITLE = "Diagram Type";

	private DiagramWizardPage diagramWizardPage = new DiagramWizardPage(PAGE_NAME_DIAGRAM_NAME);
	
	@Override
	public void addPages() {
		super.addPages();
		addPage(diagramWizardPage);
	}

	@Override
	public boolean canFinish() {
		return diagramWizardPage.isPageComplete();
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		setWindowTitle(WIZARD_WINDOW_TITLE);
	}

	@Override
	public boolean performFinish() {
		final String diagramTypeId = diagramWizardPage.getType();

		final String diagramName = diagramWizardPage.getText();

		IProject project = null;
		IFolder diagramFolder = null;

		Object element = getSelection().getFirstElement();
		if (element instanceof IProject) {
			project = (IProject) element;
		} else if (element instanceof IFolder) {
			diagramFolder = (IFolder) element;
			project = diagramFolder.getProject();
		} else if (element instanceof IAdaptable) {
			project = (IProject) ((IAdaptable) element).getAdapter(IProject.class);
		}

		if (project == null || !project.isAccessible()) {
			String error = "No open project was found for the current selection. Select a project and restart the wizard";
			IStatus status = new Status(IStatus.ERROR, Activator.getPluginId(), error);
			ErrorDialog.openError(getShell(), "No Project Found", null, status);
			return false;
		}
		
		VirSatResourceSet resourceSet = VirSatResourceSet.getResourceSet(project);	
		Diagram diagram = Graphiti.getPeCreateService().createDiagram(diagramTypeId, diagramName, true);
		if (diagramFolder == null) {
			diagramFolder = project.getFolder("src/diagrams/");  
		}
		String editorID = DiagramEditor.DIAGRAM_EDITOR_ID;
		String editorExtension = "diagram";  
		String diagramTypeProviderId = GraphitiUi.getExtensionManager().getDiagramTypeProviderId(diagramTypeId);
		String namingConventionID = diagramTypeProviderId + ".editor";  
		IEditorDescriptor specificEditor = PlatformUI.getWorkbench().getEditorRegistry().findEditor(namingConventionID);
		// If there is a specific editor get the file extension
		if (specificEditor != null) {
			editorID = namingConventionID;
			IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint("org.eclipse.ui.editors");  
			IExtension[] extensions = extensionPoint.getExtensions();
			for (IExtension ext : extensions) {
				IConfigurationElement[] configurationElements = ext.getConfigurationElements();
				for (IConfigurationElement ce : configurationElements) {
					String id = ce.getAttribute("id");  
					if (editorID.equals(id)) {
						String fileExt = ce.getAttribute("extensions");  
						if (fileExt != null) {
							editorExtension = fileExt;
							break;
						}
					}

				}
			}
		}

		IFile diagramFile = diagramFolder.getFile(diagramName + "." + editorExtension);  
		URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);

		DiagramHelper.createDiagram(uri, diagram, resourceSet);
		String providerId = GraphitiUi.getExtensionManager().getDiagramTypeProviderId(diagram.getDiagramTypeId());
		DiagramEditorInput editorInput = new DiagramEditorInput(EcoreUtil.getURI(diagram), providerId);
		
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(editorInput, editorID);
		} catch (PartInitException e) {
			String error = "Error while opening diagram editor";
			IStatus status = new Status(IStatus.ERROR, Activator.getPluginId(), error, e);
			ErrorDialog.openError(getShell(), "An error occurred", null, status);
			return false;
		}

		return true;
	}
}


