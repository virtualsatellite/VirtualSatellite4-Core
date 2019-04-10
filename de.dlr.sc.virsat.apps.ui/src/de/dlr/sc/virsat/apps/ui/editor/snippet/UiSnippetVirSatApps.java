/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.ui.editor.snippet;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaApplicationLaunchShortcut;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import de.dlr.sc.virsat.apps.initializer.VirsatAppsInitializer;
import de.dlr.sc.virsat.apps.ui.Activator;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSectionSnippet;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * the class ui snippet active concepts implements the interface ui snippet for the active concept part
 * @author leps_je
 *
 */
public class UiSnippetVirSatApps extends AUiSectionSnippet implements IUiSnippet {

	private static final String SECTION_NAME = "VirSat Apps";
	private static final String SECTION_HEADING = "Table Section for: ";
	private static final String SECTION_EXPANSION_STATE_KEY_EXTENSION = ".apps";
	
	private TreeViewer treeViewer;
	
	private Button buttonActivate;
	private Button buttonAdd;
	private Button buttonRemove;
	private Button buttonEdit;
	private Button buttonExecute;

	private static final String ACTIVATION_TEXT = "Activate VirSat Apps for this project here. In case an App has compilation issues try to update the MANIFEST.MF file by pressing the button again.";
	private static final String JDT_LAUNCH_MODE_DEBUG = "debug";
	
	private static final String BUTTON_ACTIVATION_TEXT = "Activate / Update Apps";
	private static final String BUTTON_ADD_TEXT = "Add App";
	private static final String BUTTON_REMOVE_TEXT = "Remove App";
	private static final String BUTTON_EDIT_TEXT = "Edit App";
	private static final String BUTTON_EXECUTE_TEXT = "Run App";

	private static final int DEFAULT_TABLE_SIZE = 200;
	
	@Override
	public QualifiedName getSectionExpansionStateKey() {
		return new QualifiedName(UI_SECTION_SNIPPET_ID + SECTION_EXPANSION_STATE_KEY_EXTENSION, SECTION_NAME);
	}

	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);

		Composite sectionBody = createSectionBody(toolkit, SECTION_HEADING + SECTION_NAME, null, 1);

		setUpActivationComposite(toolkit, sectionBody);
		
		setUpTreeViewer(toolkit, sectionBody);
		
		createButtonsBelowTree(toolkit, sectionBody);
	}

	/**
	 * Method to set up the activation part
	 * @param toolkit The Toolkit which creates the Composites
	 * @param sectionBody The Composite in which the activation part is created
	 */
	private void setUpActivationComposite(FormToolkit toolkit, Composite sectionBody) {
		toolkit.createLabel(sectionBody, ACTIVATION_TEXT);
		buttonActivate = toolkit.createButton(sectionBody, BUTTON_ACTIVATION_TEXT, SWT.PUSH);
		checkWriteAccess(buttonActivate);
	}

	/**
	 * Method to set up the TreeViewer
	 * @param toolkit The Toolkit which creates the Composites
	 * @param sectionBody The Composite in which the TreeViewer is created
	 */
	private void setUpTreeViewer(FormToolkit toolkit, Composite sectionBody) {
		// Start placing the table
		GridData gridDataTable = createDefaultGridData();
		gridDataTable.horizontalSpan = 1;
		gridDataTable.minimumHeight = DEFAULT_TABLE_SIZE;
		gridDataTable.heightHint = DEFAULT_TABLE_SIZE;

		Tree tree = toolkit.createTree(sectionBody, SWT.MULTI | SWT.FULL_SELECTION);
		tree.setLayoutData(gridDataTable);
		tree.setHeaderVisible(false);
	
		// Now start creating the table with the columns following the category
		treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new WorkbenchContentProvider());
		treeViewer.setLabelProvider(new WorkbenchLabelProvider());
	}

	/**
	 * Method to set up the buttons below the TreeViewer
	 * @param toolkit The Toolkit which creates the Composites
	 * @param sectionBody The Composite in which the buttons are created
	 */
	private void createButtonsBelowTree(FormToolkit toolkit, Composite sectionBody) {
		// Put another composite under the table to add the buttons
		Composite compositeButtons = toolkit.createComposite(sectionBody);
		compositeButtons.setLayoutData(new GridData());

		// Now start adding the buttons
		FillLayout compositeButtonsLayout = new FillLayout(SWT.HORIZONTAL);
		compositeButtons.setLayout(compositeButtonsLayout);

		buttonAdd = toolkit.createButton(compositeButtons, BUTTON_ADD_TEXT, SWT.PUSH);
		buttonRemove = toolkit.createButton(compositeButtons, BUTTON_REMOVE_TEXT, SWT.PUSH);
		buttonEdit = toolkit.createButton(compositeButtons, BUTTON_EDIT_TEXT, SWT.PUSH);
		buttonExecute = toolkit.createButton(compositeButtons, BUTTON_EXECUTE_TEXT, SWT.PUSH);

		addButtonSelectionListeners();
		checkWriteAccess(buttonAdd, buttonRemove, buttonEdit, buttonExecute);
	}
	
	/**
	 * This method sets up all the listeners for the buttons
	 */
	private void addButtonSelectionListeners() {
		
		buttonAdd.addSelectionListener(new SelectionListener() { 
			@Override
			public void widgetSelected(SelectionEvent e) {
				VirsatAppsInitializer jslInit = new VirsatAppsInitializer();
				IProject currentProject = VirSatEditingDomainRegistry.INSTANCE.getEd(model).getResourceSet().getProject();
				Shell shell = Display.getCurrent().getActiveShell();
				try {
					new ProgressMonitorDialog(shell).run(true, false, (pm) -> jslInit.initializeVirSatExampleApp(currentProject, pm));
				} catch (InvocationTargetException | InterruptedException e1) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed to execute activation", e1));
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		buttonRemove.addSelectionListener(new SelectionListener() { 
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				for (Object object : selection.toArray()) {
					if (object instanceof IResource) {
						IResource resource = (IResource) object;
						try {
							resource.delete(true, null);
						} catch (CoreException e1) {
							Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed to delete resource: " + resource.getName(), e1));
						}
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		buttonExecute.addSelectionListener(new SelectionListener() { 
			@Override
			public void widgetSelected(SelectionEvent event) {
				JavaApplicationLaunchShortcut javaAppplicationLaunch = new JavaApplicationLaunchShortcut();
				javaAppplicationLaunch.launch(treeViewer.getSelection(), JDT_LAUNCH_MODE_DEBUG);
				
				try {
					ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed refresh workspace", e));
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
			
		});

		buttonEdit.addSelectionListener(new SelectionListener() { 
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				IWorkbench workbench = PlatformUI.getWorkbench();
				IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
				IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
				for (Object object : selection.toArray()) {
					if (object instanceof IFile) {
						IFile file = (IFile) object;
						try {
							IDE.openEditor(workbenchPage, file, true);
						} catch (CoreException e1) {
							Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed to open editor for: " + file.getName(), e1));
						}
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		buttonActivate.addSelectionListener(new SelectionListener() { 
			@Override
			public void widgetSelected(SelectionEvent e) {
				VirsatAppsInitializer jslInit = new VirsatAppsInitializer();
				IProject currentProject = VirSatEditingDomainRegistry.INSTANCE.getEd(model).getResourceSet().getProject();
				Repository repo = VirSatEditingDomainRegistry.INSTANCE.getEd(model).getResourceSet().getRepository();
				Shell shell = Display.getCurrent().getActiveShell();
				try {
					new ProgressMonitorDialog(shell).run(true, false, (pm) -> jslInit.initializeProject(currentProject, repo, pm));
				} catch (InvocationTargetException | InterruptedException e1) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed to execute activation", e1));
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}
	
	@Override
	public void setDataBinding(DataBindingContext dbCtx, EditingDomain editingDomain, EObject model) {
		this.model = model;
		IProject currentProject;
		if (editingDomain instanceof VirSatTransactionalEditingDomain) {
			currentProject = ((VirSatTransactionalEditingDomain) editingDomain).getResourceSet().getProject();
		} else {
			currentProject = VirSatEditingDomainRegistry.INSTANCE.getEd(model).getResourceSet().getProject();
		}
		treeViewer.setInput(currentProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS));
	}

	@Override
	public boolean isActive(EObject model) {
		return model instanceof Repository;
	}

	@Override
	public void setSelection(ISelection selection) {
		treeViewer.setSelection(selection);
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		treeViewer.addSelectionChangedListener(listener);
	}

	@Override
	public ISelection getSelection() {
		return treeViewer.getSelection();
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		treeViewer.removeSelectionChangedListener(listener);
	}
	
}
