/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.ui.editor.ProblemEditorPart;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.UnresolvedReferenceException;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.internal.UIPlugin;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.PropertySheetPage;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMPropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;
import de.dlr.sc.virsat.model.ui.editor.input.VirSatUriEditorInput;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain.IResourceEventListener;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet.IDiagnosticListener;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatTransactionalAdapterFactoryContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.uieingine.ui.DVLMEditorPlugin;
import de.dlr.sc.virsat.uiengine.ui.databinding.VirSatDataBindingContext;
import de.dlr.sc.virsat.uiengine.ui.editor.registry.GenericEditorRegistry;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSectionSnippet;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;
import de.dlr.sc.virsat.uiengine.ui.swt.forms.VirSatFormToolKit;

/**
 * Implements our generic editor which is building the UI
 *  depending on the displayed object and the registered UISNippets
 * @author fisc_ph
 *
 */
@SuppressWarnings("restriction")
public class GenericEditor extends FormEditor implements IEditingDomainProvider, ISelectionProvider, IMenuListener, IGotoMarker {

	public static final String EDITOR_ID = VirSatUriEditorInput.EDITOR_ID;

	private URI editorModelObjectUri;
	private EObject editorModelObject;
	
	private volatile boolean isDisposed;
	private boolean handleClosedResourceTriggered = false;

	private GenericEditorRegistry editorRegistry;
	private Set<IUiSnippet> actuallyCreatedSnippets = Collections.synchronizedSet(new HashSet<>());

		
	protected VirSatTransactionalEditingDomain editingDomain;

	// Members considering the Outline View
	protected ComposedAdapterFactory contentOutlineAdapterFactory;
	protected IContentOutlinePage contentOutlinePage;
	protected TreeViewer contentOutlineViewer;

	protected List<PropertySheetPage> propertySheetPages = new ArrayList<PropertySheetPage>();

	protected ISelectionChangedListener selectionChangedListener;
	protected Collection<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();
	protected ISelection editorSelection = StructuredSelection.EMPTY;

	protected MarkerHelper markerHelper = new EditUIMarkerHelper();

	protected IPartListener partListener = new IPartListener() {
			public void partActivated(IWorkbenchPart p) {
				// Check if some new UI part got created. In case we it is a property view or an Outline make it visible
				if (p instanceof ContentOutline) {
					if (((ContentOutline) p).getCurrentPage() == contentOutlinePage) {
						getActionBarContributor().setActiveEditor(GenericEditor.this);
						refreshSelectionChangeListeners(false);
					}
				} else if (p instanceof PropertySheet) {
					if (propertySheetPages.contains(((PropertySheet) p).getCurrentPage())) {
						getActionBarContributor().setActiveEditor(GenericEditor.this);
						handleActivate();
					}
				} else if (p == GenericEditor.this) {
					handleActivate();
					refreshSelectionChangeListeners(true);
				}
			}
			public void partBroughtToTop(IWorkbenchPart p) {
			}
			public void partClosed(IWorkbenchPart p) {
			}
			public void partDeactivated(IWorkbenchPart p) {
			}
			public void partOpened(IWorkbenchPart p) {
			}
		};

	// Dealing with Problems in the model
	protected boolean updateProblemIndication = true;
	
	private IDiagnosticListener diagnosticListener = resource ->  {
		if (GenericEditor.this.resource == resource) {
			getSite().getShell().getDisplay().asyncExec(() -> updateProblemIndication());
		}
	};
	
	/**
	 * VirSat Resource Event which is triggered by the Workspace Synchronizer
	 * of the Transactional Editing Domain. This listener is used to handle changes
	 * on the model which are done outside of the TED so the resources can be reloaded
	 * and the editor be updated accordingly.
	 */
	private IResourceEventListener eventListener = (Set<Resource> affectedResources, int event) -> {
		Display.getDefault().asyncExec(() -> {
			
			if (isDisposed || handleClosedResourceTriggered || editingDomain.isDisposed()) {
				return;
			}
				
			switch (event) {
				case VirSatTransactionalEditingDomain.EVENT_CHANGED:
					if (GenericEditor.this.editorModelObject.eResource() == null
							|| GenericEditor.this.resource.getResourceSet() == null) {
						// If the resource that this editor was responsible for has been removed from the resource set
						// we automatically close the editor. On the other hand, if the resource still exists but the model object
						// is no longer contained anywhere (e.g. if a category assignment has been deleted) then we can also close the editor.
						
						DVLMEditorPlugin.getPlugin().getLog().log(new Status(
							Status.INFO,
							DVLMEditorPlugin.getPlugin().getSymbolicName(),
							"GenericEditor: Received a Change Event with emty resource for (" + GenericEditor.this.getTitle() + ")"
						));
						handleClosedEditorResource();
						return;
					}
					
					firePropertyChange(IEditorPart.PROP_DIRTY);
					break;
				case VirSatTransactionalEditingDomain.EVENT_RELOAD:
					handleChangedResources(affectedResources);
					break;
				case VirSatTransactionalEditingDomain.EVENT_UNLOAD:
					handleClosedResources(affectedResources);
					break;
				default:
			}
			
			updateEditorUiSnippetsState();
		});
	};
	
	/**
	 * Handles activation of the editor or it's associated views.
	 */
	protected void handleActivate() {
		// Recompute the read only state.
		if (editingDomain.getResourceToReadOnlyMap() != null) {
			editingDomain.getResourceToReadOnlyMap().clear();

			// Refresh any actions that may become enabled or disabled.
			setSelection(getSelection());
		}
	}

	/**
	 * Handles what to do with changed resources on activation.
	 * @param affectedResources The resource that was mentioned by the change event 
	 */
	protected void handleChangedResources(Set<Resource> affectedResources) {
		// Search for the resource for which to create load the model for
		affectedResources.forEach((affectedResource) -> {
			if (resource == affectedResource) {
				createModel();
			}
		});
		
		// now only trigger the update once! avoid blinking UI with thousands of refreshs
		updateDataBindingAndProblemIndication();
	}

	/**
	 * This method updates the DataBinding which may need to be done if a resource
	 * got reloaded and has actually a different set of EObjects as its contents. The viewers
	 * are still bound to the one of the previous resource which are now dangling and need to be updated.
	 */
	protected void updateDataBindingAndProblemIndication() {
		updateDataBinding();

		updateProblemIndication = true;
		updateProblemIndication();
	}
	
	/**
	 * This method is used to handle the events from the reosurceSet event listener.
	 * It checks if the resource that should be closed, maybe because it got unloaded, is 
	 * the one from the editor, which should make the editor close, or if it is another one, which is maybe 
	 * referenced and therefore references should be updated.
	 * @param closedResources The resource that got closed or unloaded
	 */
	protected void handleClosedResources(Set<Resource> closedResources) {
		// in case the resource got already close,d we should not do anything anymore
		// The editor already received the signal to shut down, no other UI action should be triggered on the
		// already closing editor
		if (!handleClosedResourceTriggered) {
			// Now check if the resource is the one of the editor,
			// if yes make it close including the editor. In case it is not, just tell
			// the editor that some other has been closed, and this editor with referenced to it maybe needs to react.
			for (Resource closedResource : closedResources) {
				if (closedResource == GenericEditor.this.resource) {
					handleClosedEditorResource();
					return;
				} else {
					handleClosedReferencedResource();
				}
			}
		}
	}
	
	/**
	 * Handles what to do with unloaded resources on activation
	 */
	protected void handleClosedEditorResource() {
		DVLMEditorPlugin.getPlugin().getLog().log(new Status(
			Status.INFO,
			DVLMEditorPlugin.getPlugin().getSymbolicName(),
			"GenericEditor: Closing Editor for (" + GenericEditor.this.getTitle() + ")"
		));

		handleClosedResourceTriggered = true;
		getSite().getPage().closeEditor(this, false);
	}
	

	/**
	 * Handles what to do with unloaded resources on activation
	 */
	protected void handleClosedReferencedResource() {
		updateDataBinding();
	}

	/**
	 * Updates the problems indication with the information described in the specified diagnostic.
	 */
	protected void updateProblemIndication() {
		if (isDisposed) {
			return;
		}
		
		if (updateProblemIndication) {
			// Get the basic diagnostic for the resource set itself
			BasicDiagnostic diagnostic = new BasicDiagnostic(Diagnostic.OK, "de.dlr.sc.virsat.uiengine.ui", 0, null, new Object [] { editingDomain.getResourceSet() });
			
			// And combine it with the specific diagnostic of the displayed resource
			Diagnostic childDiagnostic = editingDomain.getResourceSet().getResourceToDiagnosticsMap().get(resource);
			if (childDiagnostic != null && childDiagnostic.getSeverity() != Diagnostic.OK) {
				diagnostic.add(childDiagnostic);
			}

			int lastEditorPage = getPageCount() - 1;
			if (lastEditorPage >= 0 && getEditor(lastEditorPage) instanceof ProblemEditorPart) {
				((ProblemEditorPart) getEditor(lastEditorPage)).setDiagnostic(diagnostic);
				if (diagnostic.getSeverity() != Diagnostic.OK) {
					setActivePage(lastEditorPage);
				}
			} else if (diagnostic.getSeverity() != Diagnostic.OK) {
				ProblemEditorPart problemEditorPart = new ProblemEditorPart();
				problemEditorPart.setDiagnostic(diagnostic);
				problemEditorPart.setMarkerHelper(markerHelper);
				try {
					addPage(++lastEditorPage, problemEditorPart, getEditorInput());
					setPageText(lastEditorPage, problemEditorPart.getPartName());
					setActivePage(lastEditorPage);
				} catch (PartInitException exception) {
					DVLMEditorPlugin.INSTANCE.log(exception);
				}
			}

			if (markerHelper.hasMarkers(editingDomain.getResourceSet())) {
				markerHelper.deleteMarkers(editingDomain.getResourceSet());
				if (diagnostic.getSeverity() != Diagnostic.OK) {
					try {
						markerHelper.createMarkers(diagnostic);
					} catch (CoreException exception) {
						DVLMEditorPlugin.INSTANCE.log(exception);
					}
				}
			}
			
			// Our VirSat Call to set the correct problem indication of the Editor Form Heading
			updateEditorFormHeaderWithResourceProblems();
		}
	}

	
	/**
	 * This creates a model editor.
	 */
	public GenericEditor() {
		super();
	}

	private IFile file;
	
	private VirSatTransactionalAdapterFactoryLabelProvider labelProvider;
	
	/**
	 * This sets up the editing domain for the model editor.
	 */
	protected void initializeEditingDomain() {
		contentOutlineAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		contentOutlineAdapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		contentOutlineAdapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		contentOutlineAdapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		contentOutlineAdapterFactory.addAdapterFactory(new DVLMPropertyinstancesItemProviderAdapterFactory());

		labelProvider = new VirSatTransactionalAdapterFactoryLabelProvider(contentOutlineAdapterFactory);
		
		if (getEditorInput() instanceof URIEditorInput) {
			URIEditorInput uriEditorInput = (URIEditorInput) getEditorInput();
			URI uri = uriEditorInput.getURI();
			file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
			IProject project = file.getProject();
			VirSatResourceSet resourceSet = VirSatResourceSet.getResourceSet(project);
			editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(resourceSet);
			editorModelObjectUri = uri;
		}
		
		if (getEditorInput() instanceof IFileEditorInput) {
			IFileEditorInput fileEditorInput = (IFileEditorInput) getEditorInput();
			file = fileEditorInput.getFile();
			IProject project = file.getProject();
			VirSatResourceSet resourceSet = VirSatResourceSet.getResourceSet(project);
			editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(resourceSet);
			editorModelObjectUri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		}
	} 

	
	@Override
	protected void firePropertyChange(int action) {
		
		super.firePropertyChange(action);	
	}

	/**
	 * This method iterates over all available UiSnippets to tell
	 * them that there was a potential selection for them
	 * @param selection the selection of objects which should be notified to the UiSnippets 
	 */
	public void setSelectionInSnippets(ISelection selection) {
		getSite().getShell().getDisplay().asyncExec(() -> {
			if (!hasProblematicModelObject) {
				editorRegistry.executeForOrderedAssignableSnippets(editorModelObject, actuallyCreatedSnippets, uiSnippet -> {
					uiSnippet.setSelection(selection);
				});
			}
		});
	}

	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	/**
	 * This method updates the selection change listeners adds them and removes them if necessary.
	 * for example this needs to happen if the Outline view is activated so that the outline view is not
	 * setting selections in the editor which in return is directly setting a selection in the outline view
	 * @param isEditorActive set to true if the actual editor is activated by the user
	 */
	public void refreshSelectionChangeListeners(boolean isEditorActive) {
		if (selectionChangedListener == null) {
			// Create the listener on demand.
			selectionChangedListener = selectionChangedEvent -> setSelection(selectionChangedEvent.getSelection());
		}

		if (hasProblematicModelObject) {
			return;
		}
		
		// In case the editor is active we set the selection change listeners to the UI Snippets
		// in this case the selections will be forwarded to the property and outline view
		// In the second case if the outline view is active we remove them again
		// then we will use the 
		if (isEditorActive) {
			editorRegistry.executeForOrderedAssignableSnippets(editorModelObject, actuallyCreatedSnippets, uiSnippet -> {
				uiSnippet.addSelectionChangedListener(selectionChangedListener);
			});
		} else {
			editorRegistry.executeForOrderedAssignableSnippets(editorModelObject, uiSnippet -> {
				uiSnippet.removeSelectionChangedListener(selectionChangedListener);
			});
		}
	}

	private Resource resource;
	
	/**
	 * This is the method called to load a resource into the editing domain's resource set based on the editor's input.
	 */
	public void createModel() {
		URI resourceURI = EditUIUtil.getURI(getEditorInput(), editingDomain.getResourceSet().getURIConverter());
		Exception exception = null;
		try {
			// Load the resource through the editing domain.
			resource = editingDomain.getResourceSet().getResource(resourceURI, true);
		} catch (Exception e) {
			exception = e;
			resource = editingDomain.getResourceSet().getResource(resourceURI, false);
		}

		// In case loading failed with an exception and if there are no other exceptions at all, than place one
		// based on the exception we got. Thi8s exception will be displayed on the errors page of the editor.
		Diagnostic resourceDiagnostic = editingDomain.getResourceSet().analyzeResourceProblems(resource);
		if (resourceDiagnostic.getSeverity() == Diagnostic.OK && exception != null) {
			resourceDiagnostic = new BasicDiagnostic(Diagnostic.ERROR, "de.dlr.sc.virsat.uiengine.ui", 0, getString("_UI_CreateModelError_message", resource.getURI()), new Object[] { exception });
		}
			
		if (!resource.getWarnings().isEmpty()) {
			DVLMEditorPlugin.getPlugin().getLog().log(new Status(Status.INFO, DVLMEditorPlugin.ID, Status.OK, "GenericEditor: Resource Warnings: " + resource.getWarnings(), exception));
		}
		
		if (!resource.getErrors().isEmpty()) {
			DVLMEditorPlugin.getPlugin().getLog().log(new Status(Status.INFO, DVLMEditorPlugin.ID, Status.OK, "GenericEditor: Resource Errors: " + resource.getErrors(), exception));
		}
		
		String uriFragment = editorModelObjectUri.fragment();
		if ((uriFragment != null) && (!uriFragment.isEmpty())) {
			editorModelObject = resource.getEObject(uriFragment);
		} else {
			if (resource.getContents().size() == 1) {
				editorModelObject = resource.getContents().get(0);
			} else {
				editorModelObject = null;
			}
		}
	
		updateHasProblematicModelObject();
	}
	
	/**
	 * Updates the hasProblematicModelObject flag
	 */
	private void updateHasProblematicModelObject() {
		hasProblematicModelObject = editorModelObject == null || editorModelObject.eIsProxy() || editorModelObject.eResource() == null;
	}
	
	protected boolean hasProblematicModelObject = false;

	private DataBindingContext bindingContext = null; 

	/**
	 * This method is called on data model changes such as if a resource has been reloaded
	 * the objects in the memory will actually change. The DataBinding needs to be updated accordingly, otherwise the
	 * UI may alter the stalled objects in memory rather than the ones from the reloaded resource
	 */
	public void updateDataBinding() {
		if (bindingContext != null) {
			bindingContext.dispose();
		}
		
		bindingContext = new VirSatDataBindingContext();
	
		if (!hasProblematicModelObject) {
			editorRegistry.executeForOrderedAssignableSnippets(editorModelObject, actuallyCreatedSnippets, (IUiSnippet uiSnippet) -> {
				uiSnippet.setDataBinding(bindingContext, editingDomain, editorModelObject);
			});
	
			// Update the outline view
			if (contentOutlineViewer != null) {
				contentOutlineViewer.setInput(editorModelObject);
			}
	
			// Data Binding happens after creating all SWT therefore we can safely fire an update to the controls
			updateEditorUiSnippetsState();
		}
	}
	
	
	/**
	 * this method updates the editor state
	 */
	private void updateEditorUiSnippetsState() {
		if (!hasProblematicModelObject) {
			editorRegistry.executeForOrderedAssignableSnippets(editorModelObject, actuallyCreatedSnippets, uiSnippet -> {
				uiSnippet.updateState(true);
			});
			imForm.getForm().setText(getEditorObjectcName(editorModelObject));
			imForm.getForm().setImage(labelProvider.getImage(editorModelObject));
			setPartName(getEditorObjectcName(editorModelObject));
		}
	}
	
	@Override
	protected void addPages() {
		try {
			addPage(generalEditingPage);
		} catch (PartInitException e) {
			e.printStackTrace();
		}

		Diagnostic diagnostic = editingDomain.getResourceSet().getResourceToDiagnosticsMap().get(resource);
		boolean hasErrors = diagnostic != null && diagnostic.getSeverity() != Diagnostic.OK;
		if (!hasErrors) {
			getSite().getShell().getDisplay().asyncExec(new Runnable() { 
				public void run() {
					setActivePage(0);
				}
			});
		}
	}

	@Override
	protected void setActivePage(int pageIndex) {
		super.setActivePage(pageIndex);
		updateDataBinding();
	}
	
	private FormPage generalEditingPage;
	private IManagedForm imForm;
	
	/**
	 * Class which gives us an action to collapse all the snippets in the editor
	 * @author lobe_el
	 *
	 */
	class CollapseAllAction extends Action {
		@Override
		public void run() {
			expandAllEditorUiSnippets(false);
		}
	}
	
	/**
	 * Class which gives us an action to expand all the snippets in the editor
	 * @author lobe_el
	 *
	 */
	class ExpandAllAction extends Action {
		@Override
		public void run() {
			expandAllEditorUiSnippets(true);
		}
	}

	private Action formCollapseAllAction;
	private Action formExpandAllAction;
	
	@Override
	public void createPages() {
		DVLMEditorPlugin.getPlugin().getLog().log(new Status(
			Status.INFO,
			DVLMEditorPlugin.getPlugin().getSymbolicName(),
			"GenericEditor: Started creating editor pages for (" + GenericEditor.this.getTitle() + ")"
		));

		formCollapseAllAction = new CollapseAllAction();
		formCollapseAllAction.setText("Collapse All");
		formCollapseAllAction.setToolTipText("Press Button to collapse all Sections of the current Editor.");
		formCollapseAllAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));
		
		formExpandAllAction = new ExpandAllAction();
		formExpandAllAction.setText("Expand All");
		formExpandAllAction.setToolTipText("Press Button to expand all Sections of the current Editor.");
		ImageDescriptor expandAllImageDescriptor = UIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/elcl16/expandall.png");
		formExpandAllAction.setImageDescriptor(expandAllImageDescriptor);
		
		
		// Only creates the other pages if there is something that can be edited
		boolean hasResources = !getEditingDomain().getResourceSet().getResources().isEmpty();
		
		if (hasResources) {
			// Create a page for the selection tree view.
			generalEditingPage = new FormPage(this, "de.dlr.sc.uiengine.editor.page.general", "General") {
				
				@Override
				protected void createFormContent(IManagedForm managedForm) {
					super.createFormContent(managedForm);
					
					FormToolkit toolkit = managedForm.getToolkit();
					ScrolledForm scrollForm = managedForm.getForm();
					
					IToolBarManager toolBarManager = scrollForm.getToolBarManager();
					toolBarManager.add(formCollapseAllAction);
					toolBarManager.add(formExpandAllAction);
					toolBarManager.update(true);
					
					toolkit.decorateFormHeading(scrollForm.getForm());
					
					scrollForm.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
					
					Composite formBody = scrollForm.getBody();
					
					GridLayout formBodyLayout = new GridLayout(1, false);
					formBodyLayout.marginHeight = 0;
					formBodyLayout.marginWidth = 0;
					formBodyLayout.horizontalSpacing = 0;
					formBodyLayout.verticalSpacing = 0;
					formBody.setLayout(formBodyLayout);
					
					managedForm.getMessageManager().setAutoUpdate(true);
					
					// if height is 0 then nothing will be shown
					managedForm.getForm().getForm().getHead().setSize(1, 1);
					
					imForm = managedForm;
					imForm.getForm().getForm().addMessageHyperlinkListener(new HyperlinkAdapter() {
						@Override
						public void linkActivated(HyperlinkEvent e) {
							expandAllEditorUiSnippets(false);
							// expand those snippets which are marked with problems
							for (AUiSectionSnippet snippet : snippetsWithProblems) {
								snippet.setExpanded(true);
							}
						}
					});

					IEditorSite site = getEditorSite();
					if (!hasProblematicModelObject) {
						scrollForm.setText(createEditorCaption(editorModelObject));
						// Now ask the registry for what may be displayed
						actuallyCreatedSnippets.clear();
						editorRegistry.executeForOrderedAssignableSnippets(editorModelObject, (IUiSnippet uiSnippet) -> {
							uiSnippet.setWorkbenchPartSite(site).createSwt(getToolkit(), editingDomain, formBody, editorModelObject);
							actuallyCreatedSnippets.add(uiSnippet);
						});
					} else {
						scrollForm.setText("VirSat - Generic Editor");
					}
					scrollForm.pack();
				}
			};
		}
	
		getSite().getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				updateProblemIndication();
			}
		});
		
		super.createPages();
		
		DVLMEditorPlugin.getPlugin().getLog().log(new Status(
			Status.INFO,
			DVLMEditorPlugin.getPlugin().getSymbolicName(),
			"GenericEditor: Finalized creating editor pages for (" + GenericEditor.this.getTitle() + ")"
		));
	}

	
	/**
	 * this method expands or collapses all the snippets in the editor which have a collapsible section
	 * @param expand - true for expansion, false for collapsing
	 */
	private void expandAllEditorUiSnippets(boolean expand) {
		if (!hasProblematicModelObject) {
			editorRegistry.executeForOrderedAssignableSnippets(editorModelObject, actuallyCreatedSnippets, uiSnippet -> {
				if (uiSnippet instanceof AUiSectionSnippet) {
					((AUiSectionSnippet) uiSnippet).setExpanded(expand);
				}
			});
		}
	}
	
	/**
	 * Creates an editor caption for a model EObject to be edited
	 * @param model object that is being edited
	 * @return caption for the editor
	 */
	private String createEditorCaption(EObject model) {
		String formCaption;
		if (editorModelObject instanceof IName) {
			formCaption = ((IName) editorModelObject).getName();
		} else {
			formCaption = editorModelObject.eClass().getName();
		}
		return formCaption;
	}
	
	@Override
	protected void pageChange(int pageIndex) {
		super.pageChange(pageIndex);
		if (contentOutlinePage != null) {
			setSelection(contentOutlinePage.getSelection());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class key) {
		if (key.equals(IContentOutlinePage.class)) {
			return getContentOutlinePage();
		} else if (key.equals(IPropertySheetPage.class)) {
			return getPropertySheetPage();
		} else if (key.equals(IGotoMarker.class)) {
			return this;
		} else {
			return super.getAdapter(key);
		}
	}

	/**
	 * This method is called to create the outline content page
	 * @return the content outline page
	 */
	public IContentOutlinePage getContentOutlinePage() {
		if (contentOutlinePage == null) {
			/**
			 * The content outline is just a tree.
			 * @author leps_je
			 *
			 */
			class GenericEditorContentOutlinePage extends ContentOutlinePage {
				@Override
				public void createControl(Composite parent) {
					super.createControl(parent);
					contentOutlineViewer = getTreeViewer();
					contentOutlineViewer.addSelectionChangedListener(this);

					// Set up the tree viewer.
					contentOutlineViewer.setContentProvider(new VirSatTransactionalAdapterFactoryContentProvider(contentOutlineAdapterFactory));
					contentOutlineViewer.setLabelProvider(new VirSatTransactionalAdapterFactoryLabelProvider(contentOutlineAdapterFactory));
					contentOutlineViewer.setInput(editorModelObject);
				}

				@Override
				public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager) {
					super.makeContributions(menuManager, toolBarManager, statusLineManager);
				}

				@Override
				public void setActionBars(IActionBars actionBars) {
					super.setActionBars(actionBars);
					getActionBarContributor().shareGlobalActions(this, actionBars);
				}
			}

			contentOutlinePage = new GenericEditorContentOutlinePage();

			// Listen to selection so that we can handle it is a special way.
			contentOutlinePage.addSelectionChangedListener(event -> GenericEditor.this.setSelectionInSnippets(event.getSelection()));
		}

		return contentOutlinePage;
	}

	/**
	 * This accesses a cached version of the property sheet.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @return property sheet page
	 */
	public IPropertySheetPage getPropertySheetPage() {
		PropertySheetPage propertySheetPage = new ExtendedPropertySheetPage(editingDomain) {
				@Override
				public void setActionBars(IActionBars actionBars) {
					super.setActionBars(actionBars);
					getActionBarContributor().shareGlobalActions(this, actionBars);
				}
			};
		propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(contentOutlineAdapterFactory));
		propertySheetPages.add(propertySheetPage);

		return propertySheetPage;
	}

	@Override
	public boolean isDirty() {
		updateHasProblematicModelObject();
		boolean hasWriteAccess = RightsHelper.hasSystemUserWritePermission(editorModelObject);
		boolean allowRemoveDanglingReferences = allowRemoveDanglingReferences();
		boolean isDirty = (!hasProblematicModelObject) && editingDomain.isDirty(editorModelObject.eResource());
		return hasWriteAccess && (isDirty || allowRemoveDanglingReferences);
	}

	/**
	 * Call this method to check if there are XMI dangling references with the resource or
	 * DVLM UNresolved proxies etc.
	 * @return true in case one of them was found
	 */
	private boolean allowRemoveDanglingReferences() {
		if (editorModelObject == null || editorModelObject.eResource() == null) {
			// In case there is no resource associated with this model we dont allow to remove dangling references
			return false;
		}
		
		boolean hasProxyObjects = editorModelObject.eResource().getErrors().stream()
				.filter((diag) -> diag instanceof DVLMUnresolvedReferenceException).count() > 0;
		boolean hasDanglingReferences = editorModelObject.eResource().getErrors().stream()
				.filter((diag) -> diag instanceof UnresolvedReferenceException).count() > 0;
		return hasProxyObjects || hasDanglingReferences;
	}
	
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		boolean hasWriteAccess = RightsHelper.hasSystemUserWritePermission(editorModelObject);
		
		if (!hasProblematicModelObject && hasWriteAccess) {
			updateProblemIndication = false;
			
			editingDomain.saveAll();
			
			updateProblemIndication = true;
			updateProblemIndication();
			
			imForm.getForm().setText(createEditorCaption(editorModelObject));
			setPartName(getEditorObjectcName(editorModelObject));
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void gotoMarker(IMarker marker) {
		List<?> targetObjects = markerHelper.getTargetObjects(editingDomain, marker);
		if (!targetObjects.isEmpty()) {
			setSelectionInSnippets(new StructuredSelection(targetObjects));
		}
	}

	@Override
	public void init(IEditorSite site, IEditorInput editorInput) {
		DVLMEditorPlugin.getPlugin().getLog().log(new Status(
			Status.INFO,
			DVLMEditorPlugin.getPlugin().getSymbolicName(),
			"GenericEditor: Started Initializing Editor for (" + GenericEditor.this.getTitle() + ")"
		));
		isDisposed = false;
		setSite(site);
		setInputWithNotify(editorInput);
		site.setSelectionProvider(this);
		site.getPage().addPartListener(partListener);
		initializeEditingDomain();
		createModel();
		setPartName(getEditorObjectcName(editorModelObject));
		setContentDescription(getEditorObjectcName(editorModelObject));
		
		editorRegistry = new GenericEditorRegistry(this);
		editorRegistry.readPlatformRegistry(); 
		VirSatTransactionalEditingDomain.addResourceEventListener(eventListener);
		editingDomain.getResourceSet().addDiagnosticListener(diagnosticListener);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(updateProblemResourceMarkerChangeListener, IResourceChangeEvent.POST_BUILD);
		DVLMEditorPlugin.getPlugin().getLog().log(new Status(
			Status.INFO,
			DVLMEditorPlugin.getPlugin().getSymbolicName(),
			"GenericEditor: Finalized Initializing Editor for (" + GenericEditor.this.getTitle() + ")"
		));
	}
	
	/**
	 * Constructs the name of the edited object that shall be displayed in the editor form
	 * @param editorObject the edited object 
	 * @return the name to be displayer
	 */
	private String getEditorObjectcName(EObject editorObject) {
		if (editorObject instanceof IInstance) {
			IInstance iInstance = (IInstance) editorObject;
			return labelProvider.getText(editorObject) + " -> " + iInstance.getFullQualifiedInstanceName();
		} 
		
		return labelProvider.getText(editorObject);		
	}

	/**
	 * When the Resource is change, here especially when a new marker is added to or one removed
	 * from the resource, then this change should be recognized and the editor shall update
	 * @author fisc_ph
	 *
	 */
	private IResourceChangeListener updateProblemResourceMarkerChangeListener = new IResourceChangeListener() {
		
		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			// If the object model has not been correctly loaded 
			// we do not need to care about setting the Problem Markers
			// to the correct UI Widgets
			if (hasProblematicModelObject) {
				return;
			}
			
			// If the model object seems to be correctly loaded start to see
			// what has changed on the resource in case it is a resource marker
			// than try to update the widgets which displays the result/severity of that given marker
			try {
				event.getDelta().accept((delta) -> {
					// Check that we are having a marker change on a relevant resource
					IResource wsResource = VirSatProjectCommons.getWorkspaceResource(editorModelObject);
					boolean isCorrectResource = delta.getResource().equals(wsResource);
					boolean isCorrectKind = delta.getKind() == IResourceDelta.CHANGED;
					boolean isChangedMarker = (delta.getFlags() & IResourceDelta.MARKERS) != 0;
					
					// If yes then update the editor state in the UI thread
					if (isCorrectResource && isCorrectKind && isChangedMarker) {
						Display.getDefault().asyncExec(() -> {
							if (isDisposed) {
								return;
							}
							updateEditorUiSnippetsState();
							updateEditorFormHeaderWithResourceProblems();
						});
						return false;
					}
					return true;
				});
			} catch (CoreException e) {
				DVLMEditorPlugin.getPlugin().getLog().log(new Status(Status.WARNING, DVLMEditorPlugin.ID, Status.OK, "Could not trigger state update on resource marker change", e));
			}
		}
	};

	@Override
	public void setFocus() {
		getControl(getActivePage()).setFocus();
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	@Override
	public ISelection getSelection() {
		return editorSelection;
	}

	@Override
	public void setSelection(ISelection selection) {
		editorSelection = selection;

		for (ISelectionChangedListener listener : selectionChangedListeners) {
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}
		
		setStatusLineManager(selection);
	}

	/**
	 * this method set the status line manager
	 * @param selection 
	 */
	public void setStatusLineManager(ISelection selection) {
		IStatusLineManager statusLineManager = getActionBars().getStatusLineManager();
		if (statusLineManager != null) {
			if (selection instanceof IStructuredSelection) {
				Collection<?> collection = ((IStructuredSelection) selection).toList();
				switch (collection.size()) {
					case 0: 
						statusLineManager.setMessage(getString("_UI_NoObjectSelected"));
						break;
					case 1: 
						String text = new AdapterFactoryItemDelegator(contentOutlineAdapterFactory).getText(collection.iterator().next());
						statusLineManager.setMessage(getString("_UI_SingleObjectSelected", text));
						break;
					default: statusLineManager.setMessage(getString("_UI_MultiObjectSelected", Integer.toString(collection.size())));
						break;
				}
			} else {
				statusLineManager.setMessage("");
			}
		}
	}

	/**
	 * This looks up a string in the plugin's plugin.properties file.
	 * @param key 
	 * @return string
	 */
	private static String getString(String key) {
		return DVLMEditorPlugin.INSTANCE.getString(key);
	}

	/**
	 * This looks up a string in plugin.properties, making a substitution.
	 * @param key 
	 * @param s1 
	 * @return String
	 */
	private static String getString(String key, Object s1) {
		return DVLMEditorPlugin.INSTANCE.getString(key, new Object [] { s1 });
	}

	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
		((IMenuListener) getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @return the editing domain action bar constributor
	 */
	public EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor) getEditorSite().getActionBarContributor();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @return the action bar
	 */
	public IActionBars getActionBars() {
		return getActionBarContributor().getActionBars();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @return the adapter factory
	 */
	public AdapterFactory getAdapterFactory() {
		return contentOutlineAdapterFactory;
	}

	@Override
	public void dispose() {
		updateProblemIndication = false;
		isDisposed = true;
	
		// From here just continue with the eclipse editor standard behavior of disposing
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(updateProblemResourceMarkerChangeListener);
		VirSatTransactionalEditingDomain.removeResourceEventListener(eventListener);
		editingDomain.getResourceSet().removeDiagnosticListener(diagnosticListener);
		getSite().getPage().removePartListener(partListener);

		contentOutlineAdapterFactory.dispose();
		bindingContext.dispose();
		
		// In case the editor was asked externally to close, then we should not do any magic with the loaded model
		// such as saving the last editor state. In case closing the editor is triggered by the user, we can assume, that the currently 
		//loaded object is not in a stale state and the editor can use and access it to store the current editor state.
		if (editorModelObject != null && !handleClosedResourceTriggered) {
			saveSnippetsExpansionState();
		}
		
		if (getActionBarContributor().getActiveEditor() == this) {
			getActionBarContributor().setActiveEditor(null);
		}

		for (PropertySheetPage propertySheetPage : propertySheetPages) {
			propertySheetPage.dispose();
		}

		if (contentOutlinePage != null) {
			contentOutlinePage.dispose();
		}

		super.dispose();
	}

	@Override
	public void doSaveAs() {
		// Do save as will not be allowed
	}

	private Set<AUiSectionSnippet> snippetsWithProblems;


	/**
	 * Updates the Form Header of the Editor with error and warning messages for the model object and the resource
	 */
	private void updateEditorFormHeaderWithResourceProblems() {
		imForm.getMessageManager().removeAllMessages();
		snippetsWithProblems = new HashSet<AUiSectionSnippet>();
		
		if (editorModelObject == null) {
			imForm.getMessageManager().addMessage(editorModelObject, "No Model Object got loaded", null, IMessageProvider.ERROR);
			return;
		}
		
		placeMarkers();
	}

	VirSatProblemMarkerHelper vmpHelper = new VirSatProblemMarkerHelper();

	private static final int OFFSET_MARKER_TO_MESSAGE_SEVERITY = 1;
	
	/**
	 * Method which adds the markers to the Form Heading
	 */
	public void placeMarkers() {
		Set<IMarker> markers = vmpHelper.getAllMarkersForObjectAndContents(editorModelObject);
		// to avoid that some markers are not found anymore, check if they exist
		markers = vmpHelper.checkExistence(markers);
		try {
			for (IMarker marker : markers) {
				Integer severity = (Integer) marker.getAttribute(IMarker.SEVERITY);
				String message = marker.getAttribute(IMarker.MESSAGE, "There is some problem!");
				imForm.getMessageManager().addMessage(marker, message, null, severity + OFFSET_MARKER_TO_MESSAGE_SEVERITY);
			}
		} catch (CoreException e) {
			DVLMEditorPlugin.getPlugin().getLog().log(new Status(Status.WARNING, DVLMEditorPlugin.ID, "Failed to correctly display markers in Form header"));
		}
		addProblematicSnippets();
	}
	
	/**
	 * method to check if there is a snippet in the editor which refers to an Object with a marker
	 * this is added to a list of problematic snippets which is called by the HyperlinkListener of the FormHeading
	 */
	private void addProblematicSnippets() {
		editorRegistry.executeForOrderedAssignableSnippets(editorModelObject, actuallyCreatedSnippets, uiSnippet -> {
			if (uiSnippet.hasMarkers()) {
				snippetsWithProblems.add((AUiSectionSnippet) uiSnippet);
			}
		});
	}
	
	/**
	 *  Saves for all Snippets whether they are collapsed or not
	 */
	private void saveSnippetsExpansionState() {
		editorRegistry.executeForOrderedAssignableSnippets(editorModelObject, actuallyCreatedSnippets, (IUiSnippet uiSnippet) -> {
			// Only store the state if the file exists. It may not exist when the Project got deleted
			if (file.exists()) {
				uiSnippet.saveExpansionState(file);
			}
		});
	}

	/**
	 * Gets the editor model object
	 * @return editorModelObject
	 */
	public EObject getEditorModelObject() {
		return editorModelObject;
	}
	
	@Override
	protected FormToolkit createToolkit(Display display) {
		return new VirSatFormToolKit(display);
	}
}
