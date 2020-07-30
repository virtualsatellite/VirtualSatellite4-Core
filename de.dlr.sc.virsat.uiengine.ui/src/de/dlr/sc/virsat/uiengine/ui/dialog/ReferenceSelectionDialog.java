/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.dialog;

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.ui.contentProvider.EClassFilteredListContentProvider;
import de.dlr.sc.virsat.project.ui.contentProvider.ResourceFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredListContentProvider;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatFilteredWrappedListLabelProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.commonSorter.VirSatNavigatorSeiSorter;
import de.dlr.sc.virsat.uiengine.ui.dialog.provider.ReferenceSelectionFilteredTransactionalContentProvider;

/**
 * this class extend the element tree selection dialog class for referenced properties
 * @author leps_je
 *
 */
public class ReferenceSelectionDialog extends ElementTreeSelectionDialog {

	private IStructuredContentProvider contentProviderList;
	private ILabelProvider labelProviderList;
	
	/**
	 * Creates a reference selection dialog for selecting an super object
	 * @param parent the shell
	 * @param se type that wants to inherit
	 * @param adapterFactory the adapter factory
	 * @return the created reference selection dialog
	 */
	public static ReferenceSelectionDialog createRefernceSelectionDialogForInheritance(Shell parent, StructuralElement se, AdapterFactory adapterFactory) {
		VirSatFilteredWrappedTreeContentProvider cpTree = new ReferenceSelectionFilteredTransactionalContentProvider(adapterFactory);
		VirSatTransactionalAdapterFactoryLabelProvider lpTree = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory);
		
		cpTree.addClassFilter(Resource.class);
		cpTree.addClassFilter(StructuralElementInstance.class);
		cpTree.setCheckExtendedTypesForFilter(true);

		VirSatFilteredListContentProvider cpList = new VirSatFilteredListContentProvider();
		cpList.addClassFilterToGetElement(StructuralElementInstance.class);
		
		VirSatFilteredWrappedListLabelProvider lpList = new VirSatFilteredWrappedListLabelProvider(new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory));
		
		se.getCanInheritFrom().forEach((inheritsFromSe) -> {
			cpTree.addStructuralElementIdFilter(ActiveConceptHelper.getFullQualifiedId(inheritsFromSe));
			cpList.addStructuralElementIdFilterToGetElement(ActiveConceptHelper.getFullQualifiedId(inheritsFromSe));
		});
		
		
		return new ReferenceSelectionDialog(parent, lpTree, cpTree, lpList, cpList);
	}
	
	/**
	 * Creates a reference selection dialog for choosing a reference to a category
	 * @param parent the shell
	 * @param referencedType the type that wants that we want to reference
	 * @param adapterFactory the adapter factory
	 * @return the created reference selection dialog
	 */
	public static ReferenceSelectionDialog createRefernceSelectionDialog(Shell parent, ATypeDefinition referencedType, AdapterFactory adapterFactory) {
		VirSatFilteredWrappedTreeContentProvider cpTree = new ReferenceSelectionFilteredTransactionalContentProvider(adapterFactory);
		VirSatTransactionalAdapterFactoryLabelProvider lpTree = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory);
		
		cpTree.addClassFilter(Resource.class);
		cpTree.addClassFilter(StructuralElementInstance.class);
		cpTree.addClassFilter(ICategoryAssignmentContainer.class);
		cpTree.addClassFilter(CategoryAssignment.class);
		cpTree.addCategoryIdFilter(ActiveConceptHelper.getFullQualifiedId(referencedType));
		cpTree.setCheckExtendedTypesForFilter(true);
		
		VirSatFilteredListContentProvider cpList = new VirSatFilteredListContentProvider();
		cpList.addClassFilterToGetElement(CategoryAssignment.class);
		cpList.addCategoryIdFilterToGetElement(ActiveConceptHelper.getFullQualifiedId(referencedType));
		
		VirSatFilteredWrappedListLabelProvider lpList = new VirSatFilteredWrappedListLabelProvider(new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory));
		
		return new ReferenceSelectionDialog(parent, lpTree, cpTree, lpList, cpList);
	}
	
	/**
	 * Creates a reference selection dialog for choosing a reference to a category
	 * @param parent the shell
	 * @param referencedType the type that wants that we want to reference
	 * @param fileEndings the file endings of the instance model of the ECLass
	 * @param adapterFactory the adapter factory
	 * @return the created reference selection dialog
	 */
	public static ReferenceSelectionDialog createERefernceSelectionDialog(Shell parent, EClass referencedType, Set<String> fileEndings, AdapterFactory adapterFactory) {
		

		ResourceFilteredWrappedTreeContentProvider cpTree = new ResourceFilteredWrappedTreeContentProvider(adapterFactory);
		VirSatTransactionalAdapterFactoryLabelProvider lpTree = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory);
		
		cpTree.addSupportedFileEndings(fileEndings);
		
		EClassFilteredListContentProvider cpList = new EClassFilteredListContentProvider();
		cpList.addSupportedEClass(referencedType);
		
		VirSatFilteredWrappedListLabelProvider lpList = new VirSatFilteredWrappedListLabelProvider(new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory));
		
		return new ReferenceSelectionDialog(parent, lpTree, cpTree, lpList, cpList);
	}
	
	/**
	 * Creates a reference selection dialog for choosing a reference to a category
	 * @param parent the shell
	 * @param referencedTypes the type that wants that we want to reference
	 * @param adapterFactory the adapter factory
	 * @return the created reference selection dialog
	 */
	public static ReferenceSelectionDialog createRefernceSelectionDialog(Shell parent, List<ATypeDefinition> referencedTypes, AdapterFactory adapterFactory) {
		VirSatFilteredWrappedTreeContentProvider cpTree = new ReferenceSelectionFilteredTransactionalContentProvider(adapterFactory);
		VirSatTransactionalAdapterFactoryLabelProvider lpTree = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory);
		
		cpTree.addClassFilter(Resource.class);
		cpTree.addClassFilter(StructuralElementInstance.class);
		cpTree.addClassFilter(ICategoryAssignmentContainer.class);
		cpTree.addClassFilter(CategoryAssignment.class);
		for (ATypeDefinition referencedType : referencedTypes) {
			cpTree.addCategoryIdFilter(ActiveConceptHelper.getFullQualifiedId(referencedType));
		}
		
		VirSatFilteredListContentProvider cpList = new VirSatFilteredListContentProvider();
		cpList.addClassFilterToGetElement(CategoryAssignment.class);
		for (ATypeDefinition referencedType : referencedTypes) {
			cpList.addCategoryIdFilterToGetElement(ActiveConceptHelper.getFullQualifiedId(referencedType));
		}
		
		VirSatFilteredWrappedListLabelProvider lpList = new VirSatFilteredWrappedListLabelProvider(new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory));
		
		return new ReferenceSelectionDialog(parent, lpTree, cpTree, lpList, cpList);
	}
	
	/**
	 * constructor of the referenced property selection dialog instantiate the content provider and the label provider
	 * @param parent The parent shell to be used for the dialog
	 * @param labelProviderTree The label provider used for the left hand side tree viewer
	 * @param contentProviderTree The content provider used for the left hand side tree viewer
	 * @param labelProviderList The label provider used for the right hand side list viewer
	 * @param contentProviderList The content provider used for the right hand side list viewer
	 */
	private ReferenceSelectionDialog(Shell parent, VirSatTransactionalAdapterFactoryLabelProvider labelProviderTree, ITreeContentProvider contentProviderTree, VirSatFilteredWrappedListLabelProvider labelProviderList, IStructuredContentProvider contentProviderList) {
		super(parent, labelProviderTree, contentProviderTree);
		this.contentProviderList = contentProviderList;
		this.labelProviderList = labelProviderList; 
		
		setDoubleClickSelects(true);
		setTitle("Select Reference to Object");
	}

	//CHECKSTYLE:OFF
	protected int fWidth = 120;
	protected int fHeight = 18;
	//CHECKSTYLE:ON

	protected boolean fDoubleClickSelects;

	@Override
	public void setDoubleClickSelects(boolean doubleClickSelects) {
		super.setDoubleClickSelects(doubleClickSelects);
		fDoubleClickSelects = doubleClickSelects;
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.fHeight = height;
		this.fWidth = width;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		
		// Create the top region containing the dialog for filtering for reference names
		
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label labelFilter = new Label(composite, SWT.NONE);
		labelFilter.setText("Select a reference to an object");
		
		textFilter = new Text(composite, SWT.BORDER);
		textFilter.setEditable(true);
		GridData dataText = new GridData(GridData.FILL_BOTH);
		textFilter.setLayoutData(dataText);
		
		textFilter.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				tableViewer.refresh();
			}
		});
		
		// Create the bottom region containing the left hand side tree viewer and the right hand
		// side list viewer.
		
		composite = new Composite(composite, SWT.NONE);
		layout = new GridLayout(2, true);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		layout.verticalSpacing = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		applyDialogFont(composite);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label messageLabel = createMessageArea(composite);
		GridData dataMessageLabel = new GridData(GridData.FILL_HORIZONTAL);
		dataMessageLabel.horizontalSpan = 2;
		messageLabel.setLayoutData(dataMessageLabel);

		Label labelLeftViewer = new Label(composite, SWT.NONE);
		labelLeftViewer.setText("Select from current Project: ");
		
		Label labelRightViewer = new Label(composite, SWT.NONE);
		labelRightViewer.setText("Select from current Object: ");

		TreeViewer treeViewerLeft = createTreeViewerLeft(composite);
		
		GridData dataTreeLeft = new GridData(GridData.FILL_BOTH);
		dataTreeLeft.widthHint = convertWidthInCharsToPixels(fWidth / 2);
		dataTreeLeft.heightHint = convertHeightInCharsToPixels(fHeight);

		Tree treeWidgetLeft = treeViewerLeft.getTree();
		treeWidgetLeft.setLayoutData(dataTreeLeft);
		treeWidgetLeft.setFont(parent.getFont());

		TableViewer tableViewerRight = createListViewerRight(composite);

		GridData dataTreeRight = new GridData(GridData.FILL_BOTH);
		dataTreeRight.widthHint = convertWidthInCharsToPixels(fWidth / 2);
		dataTreeRight.heightHint = convertHeightInCharsToPixels(fHeight);

		Table tabletWidgetRight = tableViewerRight.getTable();
		tabletWidgetRight.setLayoutData(dataTreeRight);
		tabletWidgetRight.setFont(parent.getFont());
	
		return composite;
	}
	
	private TreeViewer treeViewer;
	private TableViewer tableViewer;
	private Text textFilter;
	
	/**
	 * this method create the tree viewer for the left side 
	 * @param parent the parent composite
	 * @return the tree viewer
	 */
	protected TableViewer createListViewerRight(Composite parent) {
		int style = SWT.BORDER | SWT.SINGLE;

		tableViewer = new TableViewer(parent, style);
		
		tableViewer.setContentProvider(contentProviderList);
		tableViewer.setLabelProvider(labelProviderList);
		tableViewer.setComparator(new VirSatNavigatorSeiSorter());
		tableViewer.addSelectionChangedListener((event) -> {
			IStructuredSelection selection = (IStructuredSelection) event.getSelection();
			access$setResult(selection.toList());
			updateOKStatus();
		});
		
		if (fDoubleClickSelects) {
			Table table = tableViewer.getTable();
			table.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					updateOKStatus();
					if (status.isOK() && fDoubleClickSelects) {
						access$superButtonPressed(IDialogConstants.OK_ID);
					}
				}
			});
		}
		
		treeViewer.addSelectionChangedListener((event) -> {
			updateInputList();
		});

		tableViewer.addDoubleClickListener((event) -> {
			updateOKStatus();
		});
		
		tableViewer.setInput(treeViewer.getSelection());
		
		tableViewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				
				// Only filter if there is actual user input
				
				if (textFilter.getText().equals("")) {
					return true;
				}
				
				// Filter for all names that contain the pattern *user_input*
				// Also ignore capitalization
				
				String nameFilter = (".*" + textFilter.getText() + ".*").toLowerCase(); 
				
				ILabelProvider labelProvider = (ILabelProvider) tableViewer.getLabelProvider();
				String label = labelProvider.getText(element);
				return label.toLowerCase().matches(nameFilter);
			}
		});
		
		tableViewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				// Sort entries by their label
				return labelProviderList.getText(e1).compareTo(labelProviderList.getText(e2));
			}
			
		});
		
		return tableViewer;
	}

	@Override
	protected void computeResult() {
	}
	
	protected IStatus status =  new Status(IStatus.OK, PlatformUI.PLUGIN_ID, IStatus.OK, "", null); //$NON-NLS-1$
	
	@Override
	protected void updateStatus(IStatus status) {
		super.updateStatus(status);
		this.status = status;
	}
	
	@Override
	public void setInput(Object input) {
		if (input instanceof Resource) {
			Resource resource = (Resource) input;
			Object leftViewerInput = resource.getResourceSet();
			super.setInput(leftViewerInput);
		} else {
			super.setInput(input);
		}
		
		// Check if both viewers are available and if so
		// Set the input of the list viewer to be the selection in the tree
		
		if (treeViewer != null && tableViewer != null) {
			updateInputList();
		}
	}
	
	/**
	 * Updates the input of the right hand side table viewer using the current
	 * selection in the tree viewer
	 */
	
	private void updateInputList() {
		IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
		tableViewer.setInput(selection.getFirstElement());
	}
	
	/**
	 * this method create the tree viewer for the left side 
	 * @param parent the parent composite
	 * @return the tree viewer for the left side
	 */
	protected TreeViewer createTreeViewerLeft(Composite parent) {
		treeViewer = super.createTreeViewer(parent); 
		treeViewer.setComparator(new VirSatNavigatorSeiSorter());
		return treeViewer;
	}
	
	public IStructuredContentProvider getContentProvider() {
		return contentProviderList;
	}
}
