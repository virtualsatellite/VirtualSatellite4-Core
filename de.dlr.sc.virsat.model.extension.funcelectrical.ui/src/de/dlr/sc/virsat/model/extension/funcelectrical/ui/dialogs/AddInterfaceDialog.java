/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.dialogs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.uiengine.ui.dialog.provider.ReferenceSelectionFilteredTransactionalContentProvider;


/**
 * this class extend the element tree selection dialog class for adding Interfaces
 * @author bell_Er
 *
 */
public class AddInterfaceDialog extends ElementTreeSelectionDialog {

	/**
	 * Creates a add interface dialog for adding an interface
	 * @param parent the shell
	 * @param se type that wants to inherit
	 * @param adapterFactory the adapter factory
	 * @return the created add interface dialog
	 */
	public static AddInterfaceDialog createRefernceSelectionDialogForInheritance(Shell parent, StructuralElement se, AdapterFactory adapterFactory) {
		VirSatFilteredWrappedTreeContentProvider cpTree = new ReferenceSelectionFilteredTransactionalContentProvider(adapterFactory);
		VirSatTransactionalAdapterFactoryLabelProvider lpTree = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory);

		cpTree.addClassFilter(StructuralElementInstance.class);
		cpTree.addStructuralElementIdFilter(ElementConfiguration.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		cpTree.addStructuralElementIdFilter(ElementOccurence.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		cpTree.addStructuralElementIdFilter(ConfigurationTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		cpTree.addStructuralElementIdFilter(AssemblyTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		
		
		return new AddInterfaceDialog(parent, lpTree, cpTree);
	}
	
	/**
	 * constructor of the referenced property selection dialog instantiate the content provider and the label provider
	 * @param parent The parent shell to be used for the dialog
	 * @param labelProviderTree The label provider used for the left hand side tree viewer
	 * @param contentProviderTree The content provider used for the left hand side tree viewer
	 */
	private AddInterfaceDialog(Shell parent, VirSatTransactionalAdapterFactoryLabelProvider labelProviderTree, ITreeContentProvider contentProviderTree) {
		super(parent, labelProviderTree, contentProviderTree);
		setDoubleClickSelects(true);
		setTitle("Select the Location of the Interface");
	}

	//CHECKSTYLE:OFF
	protected int fWidth = 90;
	protected int fHeight = 18;
	//CHECKSTYLE:ON

	@Override
	public void setDoubleClickSelects(boolean doubleClickSelects) {
		super.setDoubleClickSelects(doubleClickSelects);
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.fHeight = height;
		this.fWidth = width;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		// Create the  tree viewer 
	
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, true);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		layout.verticalSpacing = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		applyDialogFont(composite);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		TreeViewer treeViewer = createTreeViewer(composite);

		GridData dataTree = new GridData(GridData.FILL_BOTH);
		dataTree.widthHint = convertWidthInCharsToPixels(fWidth / 2);
		dataTree.heightHint = convertHeightInCharsToPixels(fHeight);

		Tree treeWidged = treeViewer.getTree();
		treeWidged.setLayoutData(dataTree);
		treeWidged.setFont(parent.getFont());
	
		return composite;
	}
	
	private TreeViewer treeViewer;
	@Override
	protected void computeResult() {
	}
	
	@Override
	protected void updateStatus(IStatus status) {
		super.updateStatus(status);
	}
	
	@Override
	public void setInput(Object input) {
		if (input instanceof Resource) {
			Resource resource = (Resource) input;
			Object leftViewerInput = resource.getResourceSet();
			super.setInput(leftViewerInput);
		}
		
	}
	/**
	 * this method create the tree viewer 
	 * @param parent the parent composite
	 * @return the tree viewer for the right side
	 */
	protected TreeViewer createTreeViewer(Composite parent) {
		treeViewer = super.createTreeViewer(parent); 
		return treeViewer;
	}
	
	@Override
	protected void updateOKStatus() {
		super.updateOKStatus();
		StructuralElementInstance sc = (StructuralElementInstance) ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (sc.getType().getName().equals(ConfigurationTree.class.getSimpleName()) || sc.getType().getName().equals(AssemblyTree.class.getSimpleName())) {
			okButton.setEnabled(false);
		} else {
			okButton.setEnabled(true);
		}

	}
}




