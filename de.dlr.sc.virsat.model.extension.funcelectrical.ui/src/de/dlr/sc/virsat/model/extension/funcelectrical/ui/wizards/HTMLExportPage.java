/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeDomain;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatComposedContentProvider;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatComposedLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatProjectContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatWorkspaceContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatProjectLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatWorkspaceLabelProvider;

/**
 * Page to select the resource that is to be exported as HMTL
 * @author bell_er
 *
 */
public class HTMLExportPage extends WizardPage {
	
	private static final String DIALOG_TEXT = "Select target";
	
	private static final String BUTTON_TEXT = "Browse";
	private static final String FILE_LABEL = "Destination";
	
	private static final int ROWS = 3;
	// Keys for the dialog settings
	private static final String DESTINATION_FILE_KEY = "DESTINATION_FILE";
	
	private IContainer model;
	private Object selection;
	private ISelection preSelect;
	private Composite content;
	private Combo destinationField;
	private boolean destination;
	private IDialogSettings wizardSettings;
	/**
	 * Create a new page 
	 * @param model the root element for the selection on what to export/where to integrate the
	 * import data
	 * @param preSelect the initial selection to be transferred into import/export window
	 */
	
	protected HTMLExportPage(IContainer model,  ISelection preSelect) {
		super("Select element and destination");
		setTitle("Select element and destination");
		setDescription("Export the selected element to an HTML file.");
		this.model = model;
		this.preSelect = preSelect;
		
	}

	@Override
	public void createControl(Composite parent) {
		wizardSettings = getDialogSettings();
		
		content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout());
		content.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		
		createTreeUI();
		createExportUI();
	    setControl(content);
	    
	
	}
	
	/**
	 * Create the Tree viewer
	 */
	
	private void createTreeUI() {
		
		TreeViewer treeViewer = new TreeViewer(content, SWT.BORDER);
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		
		VirSatComposedContentProvider cp = new VirSatComposedContentProvider();
		cp.registerSubContentProvider(new VirSatWorkspaceContentProvider());
		cp.registerSubContentProvider(new VirSatProjectContentProvider());
		
		VirSatComposedLabelProvider lp = new VirSatComposedLabelProvider();
		lp.registerSubLabelProvider(new VirSatWorkspaceLabelProvider());
		lp.registerSubLabelProvider(new VirSatProjectLabelProvider());
		
		VirSatFilteredWrappedTreeContentProvider filteredCP = new VirSatFilteredWrappedTreeContentProvider(cp);
		filteredCP.setCheckContainedForFilter(true);
		
		// Filter for elements that can be imported and exported together with their parents

		
		filteredCP.addStructuralElementIdFilter(AssemblyTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCP.addStructuralElementIdFilter(ConfigurationTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCP.addStructuralElementIdFilter(ProductTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCP.addStructuralElementIdFilter(ProductTreeDomain.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCP.addStructuralElementIdFilter(ElementDefinition.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCP.addStructuralElementIdFilter(InterfaceTypeCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCP.addClassFilter(StructuralElementInstance.class);
		filteredCP.addClassFilter(Repository.class);
		filteredCP.addClassFilter(VirSatProjectResource.class);

		
		treeViewer.setContentProvider(filteredCP);
		treeViewer.setLabelProvider(lp);
		
		if (null == preSelect) {
			treeViewer.setInput(model);
		} else {
			IStructuredSelection selection2 = (IStructuredSelection) preSelect;
			StructuralElementInstance sc = (StructuralElementInstance) selection2.getFirstElement();
			VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(sc); 
			Repository rep = ed.getResourceSet().getRepository();
			treeViewer.setInput(rep);
			treeViewer.setSelection((TreeSelection) preSelect);
		}
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				selection = treeViewer.getStructuredSelection().getFirstElement();
				setPageComplete(isComplete());
			}
		});
	}

	/**
	 * Create the dialog for selecting the file which will be exported 
	 */
	
	private void createExportUI() {
		Label label = new Label(content, SWT.FILL);
		label.setText("Select the HTML export destination:");
		
		Composite composite = new Composite(content, SWT.FILL);
		composite.setLayout(new GridLayout(ROWS, false));
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		composite.setLayoutData(data);
		label = new Label(composite, SWT.NONE);
		label.setText(FILE_LABEL);		
		
        // destination name entry field
		destinationField = new Combo(composite, SWT.SINGLE | SWT.BORDER);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        final int widthHint = 250;
        data.widthHint = widthHint;
        destinationField.setLayoutData(data);
		destinationField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				String selectedDirectoryName = destinationField.getText();
				if (selectedDirectoryName.equals("")) {
					destination = false;
				} else {
					destination = true;
					getDialogSettings().put(DESTINATION_FILE_KEY, selectedDirectoryName);
				}
				
				setPageComplete(isComplete());
			}
		});
        
      
        String defaultDestination = wizardSettings.get(DESTINATION_FILE_KEY);
        if (defaultDestination != null) {
        	destinationField.setText(defaultDestination);
        }
        
        // destination browse button
        final Button destinationBrowseButton = new Button(composite, SWT.PUSH);
        destinationBrowseButton.setText(BUTTON_TEXT);
        destinationBrowseButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
		        DirectoryDialog dialog = new DirectoryDialog(getContainer().getShell(), SWT.SAVE | SWT.SHEET);
		        dialog.setText(DIALOG_TEXT);
		        
		        String selectedDirectoryName = dialog.open();
		        
		        if (selectedDirectoryName != null) {
		            setErrorMessage(null);
		            destinationField.setText(selectedDirectoryName);
		        }
			}
        });
        setButtonLayoutData(destinationBrowseButton);
	}

	/**
	 * Get the selected object
	 * @return the selected object
	 */
	
	public Object getSelection() {
		return selection;
	}
	
	/**
	 * Get the destination of the target file
	 * @return path to the target file
	 */
	
	public String getDestination() {
		return destinationField.getText();
	}
	/**
	 * Checks if the page has been sufficiently filled with user data
	 * @return true iff the page is complete
	 */
	public boolean isComplete() {
		return selection != null && destination;
	}
}
