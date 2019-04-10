/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.nebula.widgets.treemapper.INewMappingListener;
import org.eclipse.nebula.widgets.treemapper.TreeMapper;
import org.eclipse.nebula.widgets.treemapper.TreeMapperUIConfigProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopier;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeDomain;
import de.dlr.sc.virsat.model.extension.ps.util.ProductStructureHelper;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatComposedContentProvider;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatComposedLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.commonSorter.VirSatNavigatorSeiSorter;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatProjectContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatWorkspaceContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatProjectLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatWorkspaceLabelProvider;
/**
 * Page to configure an existing configuration tree from a product tree or configure an existing  assembly tree from a configuration tree
 * @author bell_er
 *
 */

public class ConfigureProductPage extends WizardPage {

	private static final int SELECTION_THICKNESS = 13;
	private static final int COLUMN_COUNT = 3;
	private static final int BUTTONS = 5;
	private static final RGB DEFAULT_RGB = new RGB(0, 206, 0);
	private static final RGB SELECTED_RGB = new RGB(0, 0, 111);

	private ISelection selection;
	private Composite content;
	private ISelection preSelect;

	private StructuralElementInstance mainRootSc;
	private StructuralElementInstance rootSc;
	private VirSatTransactionalEditingDomain ed; 
	private Repository rep;


	private TreeMapper<StructuralElementInstanceMappingBean, StructuralElementInstance, StructuralElementInstance> mapper;
	private TreeEditor editor;
	private Tree rightTree;
	private Tree leftTree;
	private List<StructuralElementInstanceMappingBean> mappings;

	private List<VirSatUuid> deleted; 

	private boolean isModifyConfigurationTree;
	/**
	 * Create a new regenerate page
	 * @param preselect the root element for the selection
	 */
	protected ConfigureProductPage(ISelection preselect) {	
		super("");
		this.preSelect = preselect;
		mainRootSc = (StructuralElementInstance) ((IStructuredSelection) preSelect).getFirstElement();
		deleted = new ArrayList<>();
		rootSc = (StructuralElementInstance) DVLMCopier.makeCopyKeepUuids(mainRootSc);
		ed = VirSatEditingDomainRegistry.INSTANCE.getEd(mainRootSc); 
		rep = ed.getResourceSet().getRepository();

		if (rootSc.getType().getName().equals(AssemblyTree.class.getSimpleName())) {
			setTitle("Modify the Assembly Tree");
			isModifyConfigurationTree = false;
		}
		if (rootSc.getType().getName().equals(ConfigurationTree.class.getSimpleName())) {
			setTitle("Modify the Configuration Tree");
			isModifyConfigurationTree = true;
		}
	}

	@Override
	public void createControl(Composite parent) {
		content = new Composite(parent, SWT.NONE);
		GridLayout glContent = new GridLayout();
		glContent.numColumns = COLUMN_COUNT;
		content.setLayout(glContent);
		content.setLayout(new GridLayout());
		content.setLayoutData(new GridData(GridData.FILL_BOTH));

		createTreeMapperUI(content);
		createButtons();
	}

	/**
	 * Creates 4 buttons
	 */
	private void createButtons() {
		Composite composite = new Composite(content, SWT.LEFT);
		composite.setLayout(new GridLayout(BUTTONS, false));
		composite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));


		Button addElement = new Button(composite, SWT.NONE);
		addElement.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				StructuralElementInstance sc = (StructuralElementInstance) ((IStructuredSelection) selection).getFirstElement();


				ActiveConceptHelper acHelper = new ActiveConceptHelper(rep);
				Concept activeConcept = acHelper.getConcept(ProductStructureHelper.getConcept());
				StructuralElementInstance createdSei = ProductStructureHelper.createStructuralElementInstance(activeConcept, sc);


				sc.getChildren().add(createdSei);
				updateMapper();
			}
		});
		addElement.setText("Add New Element");



		Button btnRename = new Button(composite, SWT.NONE);
		btnRename.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Identify the selected row

				TreeItem[] items = mapper.getRightTreeViewer().getTree().getSelection();

				if (items[0] == null) {
					return;
				}
				// The control that will be the editor must be a child of the Tree
				Text newEditor = new Text(rightTree, SWT.NONE);
				StructuralElementInstance temp = (StructuralElementInstance) ((IStructuredSelection) selection).getFirstElement();
				newEditor.setText(temp.getName());
				newEditor.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent e) {
						Text text = (Text) editor.getEditor();
						editor.getItem().setText(text.getText());
						StructuralElementInstance sc = (StructuralElementInstance) ((IStructuredSelection) selection).getFirstElement();
						sc.setName(text.getText());

					}
				});
				newEditor.selectAll();
				newEditor.setFocus();
				editor.setEditor(newEditor, items[0]);
			}
		});
		btnRename.setText("Rename");

		Button btnDuplicate = new Button(composite, SWT.NONE);
		btnDuplicate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuralElementInstance sc = (StructuralElementInstance) ((IStructuredSelection) selection).getFirstElement();
				ProductStructureHelper.duplicate(sc);
				updateMapper();
			}
		});
		btnDuplicate.setText("Duplicate");


		Button btnDeleteElement = new Button(composite, SWT.NONE);
		btnDeleteElement.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuralElementInstance sc = (StructuralElementInstance) ((IStructuredSelection) selection).getFirstElement();
				sc.getParent().getChildren().remove(sc);
				deleted.add(sc.getUuid());
				updateMapper();

			}
		});
		btnDeleteElement.setText("Delete Element");

		Button btnRemoveInheritance = new Button(composite, SWT.NONE);
		btnRemoveInheritance.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {		
				IStructuredSelection s = (IStructuredSelection) mapper.getSelection();
				StructuralElementInstanceMappingBean smb = 	(StructuralElementInstanceMappingBean) s.getFirstElement();
				smb.getRight().getSuperSeis().remove(smb.getLeft());
				updateMapper();

			}
		});
		btnRemoveInheritance.setText("Remove Inheritance");	
	}



	/**
	 * Create the Tree Mapper
	 * @param parent the parent composite
	 */
	private void createTreeMapperUI(Composite parent) {

		Color defaultColor = new Color(parent.getShell().getDisplay(), DEFAULT_RGB);
		Color selectedColor = new Color(parent.getShell().getDisplay(), SELECTED_RGB);

		TreeMapperUIConfigProvider uiConfig = new TreeMapperUIConfigProvider(defaultColor, 2, selectedColor, SELECTION_THICKNESS);


		VirSatComposedLabelProvider lp = new VirSatComposedLabelProvider();
		lp.registerSubLabelProvider(new VirSatWorkspaceLabelProvider());
		lp.registerSubLabelProvider(new VirSatProjectLabelProvider());

		VirSatComposedContentProvider cp = new VirSatComposedContentProvider();
		cp.registerSubContentProvider(new VirSatWorkspaceContentProvider());
		cp.registerSubContentProvider(new VirSatProjectContentProvider());
		cp.registerSubContentProvider(new VirSatWorkspaceContentProvider());

		// Filter left tree
		VirSatFilteredWrappedTreeContentProvider filteredCPLeft = new VirSatFilteredWrappedTreeContentProvider(cp);
		filteredCPLeft.setCheckContainedForFilter(true);
		filteredCPLeft.addClassFilter(StructuralElementInstance.class);
		filteredCPLeft.addClassFilter(Repository.class);
		filteredCPLeft.addClassFilter(VirSatProjectResource.class);

		if (isModifyConfigurationTree) {
			filteredCPLeft.addStructuralElementIdFilter(ProductTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
			filteredCPLeft.addStructuralElementIdFilter(ProductTreeDomain.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
			filteredCPLeft.addStructuralElementIdFilter(ElementDefinition.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		} else {
			filteredCPLeft.addStructuralElementIdFilter(ConfigurationTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
			filteredCPLeft.addStructuralElementIdFilter(ElementConfiguration.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		}
		
		// Filter right tree
		VirSatFilteredWrappedTreeContentProvider filteredCPRight = new VirSatFilteredWrappedTreeContentProvider(cp);
		filteredCPRight.setCheckContainedForFilter(true);
		filteredCPRight.addClassFilter(StructuralElementInstance.class);
		
		VirsatSemanticTreeMapperSupport sd = new VirsatSemanticTreeMapperSupport();
		mapper = new TreeMapper<StructuralElementInstanceMappingBean, StructuralElementInstance, StructuralElementInstance>(parent, sd, uiConfig);
		
		mapper.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		mapper.setContentProviders(filteredCPLeft, filteredCPRight);
		mappings = getMappings(rootSc, rep);
		mapper.setInput(rep, rootSc, mappings);
		mapper.setLabelProviders(lp, lp);
		mapper.addNewMappingListener(new INewMappingListener<StructuralElementInstanceMappingBean>() {
			@Override
			public void mappingCreated(StructuralElementInstanceMappingBean mapping) {
				mapping.getRight().getSuperSeis().add(mapping.getLeft());
				updateMapper();
			}
		});

		TreeViewer rightTreeViewer = mapper.getRightTreeViewer();
		rightTreeViewer.setComparator(new VirSatNavigatorSeiSorter());
		rightTree = mapper.getRightTreeViewer().getTree();
		rightTree.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (event.keyCode == SWT.DEL && rightTree.getSelectionCount() == 1) {
					StructuralElementInstance sc = (StructuralElementInstance) ((IStructuredSelection) selection).getFirstElement();
					sc.getParent().getChildren().remove(sc);
					updateMapper();
				}
			}
		});
		
		TreeViewer leftTreeViewer = mapper.getLeftTreeViewer();
		leftTreeViewer.setComparator(new VirSatNavigatorSeiSorter());
		leftTree = mapper.getLeftTreeViewer().getTree();
		leftTree.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (event.keyCode == SWT.DEL && leftTree.getSelectionCount() == 1) {
					StructuralElementInstance sc = (StructuralElementInstance) ((IStructuredSelection) selection).getFirstElement();
					sc.getParent().getChildren().remove(sc);
					updateMapper();
				}
			}
		});
		
		rightTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Control oldEditor = editor.getEditor();
				if (oldEditor != null) {
					oldEditor.dispose();
				}
				selection = event.getSelection();
			}
		});
		editor = new TreeEditor(rightTree);
		setControl(content);
		expandAllMapper();
		mapper.refresh();
	}
	


	/**
	 * Expands the both trees
	 */
	private void expandAllMapper() {
		mapper.getRightTreeViewer().expandAll();
		mapper.getLeftTreeViewer().expandAll();	
	}


	/**
	 * Get the mainRootSc
	 * @return the mainRootSc
	 */
	public StructuralElementInstance getMainRootSc() {
		return mainRootSc;
	}

	/**
	 * Get the rootSc
	 * @return the rootSc
	 */
	public StructuralElementInstance getRootSc() {
		return rootSc;
	}

	/**
	 * Get the ed
	 * @return the ed
	 */
	public VirSatTransactionalEditingDomain getEditingDomain() {
		return ed;
	}

	/**
	 * @return canFlipToNextPage
	 */
	@Override
	public boolean canFlipToNextPage() {
		return false;
	}

	/**
	 * @param sc the structuralElementInstance
	 * @param rep the repository
	 * @return the list of mappings
	 */
	private List<StructuralElementInstanceMappingBean> getMappings(StructuralElementInstance sc, Repository rep) {
		ArrayList<StructuralElementInstanceMappingBean> mappings = new ArrayList<StructuralElementInstanceMappingBean>();		
		for (StructuralElementInstance sei : sc.getSuperSeis()) {
			StructuralElementInstanceMappingBean mb = new StructuralElementInstanceMappingBean();
			mb.setLeft(sei); 
			mb.setRight(sc);
			mappings.add(mb);
		}				
		for (StructuralElementInstance scChild : sc.getChildren()) {
			mappings.addAll(getMappings(scChild, rep));
		}
		return mappings;
	}
	/**
	 * Updates the treeMapper
	 */
	private void updateMapper() {
		mappings = getMappings(rootSc, rep);
		mapper.setInput(rep, rootSc, mappings);
		mapper.refresh();
		expandAllMapper();
	}
}
