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

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.PropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.model.extension.ps.util.ProductStructureHelper;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatComposedContentProvider;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatComposedLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.commonSorter.VirSatNavigatorSeiSorter;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatProjectContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatWorkspaceContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatProjectLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatWorkspaceLabelProvider;
/**
 * Page to create a new configuration tree from a product tree or create a new assembly tree from a configuration tree
 * @author bell_er
 *
 */

public class GenerateProductPage extends WizardPage {
	
	private ISelection selection;
	private ISelection preSelect;
	private VirSatTransactionalEditingDomain ed;
	private Repository rep;
	private Composite content;
	private StructuralElementInstance sc;
	private StructuralElementInstance sei;
	private boolean generateAsConfigurationTree;
	private Text treeName;
	private Tree tree;
	private TreeEditor editor;
	/**
	 * Create a new Generate page
	 * @param preSelect the initial selection to be used as a model
	 */
	
	protected GenerateProductPage(ISelection preSelect) {
		
		super("");
		sc = (StructuralElementInstance) ((IStructuredSelection) preSelect).getFirstElement();
		if (sc.getType().getName().equals(ConfigurationTree.class.getSimpleName())) {
			setTitle("Generate the Assembly Tree");
			generateAsConfigurationTree = false;
		}
		if (sc.getType().getName().equals(ProductTree.class.getSimpleName())) {
			setTitle("Generate the Configuration Tree");
			generateAsConfigurationTree = true;
		}
		setDescription("Right click on an element to rename or duplicate");
		this.preSelect = preSelect;
		
	}

	@Override
	public void createControl(Composite parent) {
		content = new Composite(parent, SWT.NONE);
		GridLayout glContent = new GridLayout();
		glContent.numColumns = 2;
		content.setLayout(glContent);
		content.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));	
		
		createTreeUI();
	    setControl(content);
	    
	    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	    
	    Composite composite = new Composite(content, SWT.NONE);
	    composite.setLayout(new GridLayout(2, false));
	    Button btnRename = new Button(composite, SWT.NONE);
	    btnRename.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		// Identify the selected row
	    		TreeItem[] items = tree.getSelection();

	    		if (items[0] == null) {
	    			return;
	    		}
	    		// The control that will be the editor must be a child of the Tree
	    		Text newEditor = new Text(tree, SWT.NONE);
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
	    	}
	    });
	    btnDuplicate.setText("Duplicate");
	    new Label(content, SWT.NONE);
	    Label lblTreeName = new Label(content, SWT.NONE);
	    lblTreeName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	    treeName = new Text(content, SWT.BORDER);
	    treeName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	  
	 
	    if (generateAsConfigurationTree) {
	    	lblTreeName.setText("Configuration Tree Name");
	    	treeName.setText(ConfigurationTree.class.getSimpleName());
		} else {
			lblTreeName.setText("Assembly Tree Name");
			treeName.setText(AssemblyTree.class.getSimpleName());
		}
	   
	}
	
	/**
	 * Create the dialog for renaming and duplicating items
	 */
	private void createTreeUI() {
			
		TreeViewer treeViewer =  new TreeViewer(content, SWT.BORDER);
		treeViewer.setComparator(new VirSatNavigatorSeiSorter());
		VirSatComposedContentProvider cp = new VirSatComposedContentProvider();
		cp.registerSubContentProvider(new VirSatWorkspaceContentProvider());
		cp.registerSubContentProvider(new VirSatProjectContentProvider());
		
		VirSatComposedLabelProvider lp = new VirSatComposedLabelProvider();
		lp.registerSubLabelProvider(new VirSatWorkspaceLabelProvider());
		lp.registerSubLabelProvider(new VirSatProjectLabelProvider());
		
		VirSatFilteredWrappedTreeContentProvider filteredCP = new VirSatFilteredWrappedTreeContentProvider(cp);
		filteredCP.setCheckContainedForFilter(true);
		
		// Filter for elements that will be Generated

		filteredCP.addClassFilter(StructuralElementInstance.class);
		filteredCP.addClassFilter(Repository.class);
		filteredCP.addClassFilter(VirSatProjectResource.class);
		
		treeViewer.setContentProvider(filteredCP);
		treeViewer.setLabelProvider(lp);	
		// create the new Structural element 
		StructuralElementInstance sc = (StructuralElementInstance) ((IStructuredSelection) preSelect).getFirstElement();
		this.ed = VirSatEditingDomainRegistry.INSTANCE.getEd(sc); 
		VirSatResourceSet virSatResourceSet = ed.getResourceSet();
		this.rep = virSatResourceSet.getRepository();
		
		// Realize what to generate
		if (generateAsConfigurationTree) {
			ConfigurationTree ct = (ConfigurationTree) ProductStructureHelper.createTreeModel(sc);
			this.sei = ct.getStructuralElementInstance();
			treeViewer.setInput(ct.getStructuralElementInstance());
		} else {
			AssemblyTree at = (AssemblyTree) ProductStructureHelper.createTreeModel(sc);
			this.sei = at.getStructuralElementInstance();
			treeViewer.setInput(at.getStructuralElementInstance());
		}
		treeViewer.expandAll();
		tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		editor = new TreeEditor(tree);
		

		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		tree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MenuManager menuMgr = new MenuManager();
				menuMgr.addMenuListener(new IMenuListener() {
					@Override
					public void menuAboutToShow(IMenuManager manager) {
						manager.removeAll();
						// initialize the action to perform
						Action rename = new Action() {
							public void run() {
								// Identify the selected row
								TreeItem item = (TreeItem) e.item;
								if (item == null) {
									return;
								}
								// The control that will be the editor must be a child of the Tree
								Text newEditor = new Text(tree, SWT.NONE);
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
								editor.setEditor(newEditor, item);
								
							}
						};
						rename.setText("Rename");
						manager.add(rename);
						Action duplicate = new Action() {

							public void run() {
								StructuralElementInstance sc = (StructuralElementInstance) ((IStructuredSelection) selection).getFirstElement();
								ProductStructureHelper.duplicate(sc);
							}
						};
						duplicate.setText("Duplicate");
						manager.add(duplicate);
					}
				});
				Menu menu = menuMgr.createContextMenu(treeViewer.getTree());
				treeViewer.getTree().setMenu(menu);
			}
		});
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Control oldEditor = editor.getEditor();
				if (oldEditor != null) {
					oldEditor.dispose();
				}
				selection = event.getSelection();
			}
		});
	}
	/**
	 * @return canFlipToNextPage
	 */
	@Override
	public boolean canFlipToNextPage() {
		return false;
	}

	/**
	 * Get the selected object
	 * @return the selected object
	 */
	public Object getSelection() {
		return selection;
	}

	/**
	 * Checks if the page has been sufficiently filled with user data
	 * @return true iff the page is complete
	 */
	public boolean isComplete() {			
		return true;	
	}
	/**
	 * @return rep Repository
	 */
	public Repository getRep() {
		return rep;
	}
	/**
	 * @param rep Repository
	 */
	public void setRep(Repository rep) {
		this.rep = rep;
	}
	/**
	 * @return ed VirSatTransactionalEditingDomain
	 */
	public VirSatTransactionalEditingDomain getEd() {
		return ed;
	}
	/**
	 * @param ed VirSatTransactionalEditingDomain
	 */
	public void setEd(VirSatTransactionalEditingDomain ed) {
		this.ed = ed;
	}
	/**
	 * @return sei StructuralElementInstance
	 */
	public StructuralElementInstance getSei() {
		return sei;
	}
	/**
	 * @param sei StructuralElementInstance
	 */
	public void setSei(StructuralElementInstance sei) {
		this.sei = sei;
	}
	/**
	 * @return sei StructuralElementInstance
	 */
	public String getSeiName() {
		return treeName.getText();
	}
}