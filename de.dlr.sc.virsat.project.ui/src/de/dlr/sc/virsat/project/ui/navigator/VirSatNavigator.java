/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISaveablesLifecycleListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.SaveablesLifecycleEvent;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain.IResourceEventListener;
import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.project.ui.navigator.dropAssist.PaletteObjectDropAdapterAssistant;
import de.dlr.sc.virsat.project.ui.navigator.saveablesprovider.VirSatProjectSaveablesProvider;

/**
 * Own Implementation overriding the Common Navigator
 */
public class VirSatNavigator extends CommonNavigator implements IResourceEventListener {

	public static final String VIRSAT_NAVIGATOR_ID = "de.dlr.sc.virsat.project.ui.navigator.view";
	
	/**
	 * Constructor
	 */
	public VirSatNavigator() {
		super();
	}

	/**
	 * Implementation of a Listener which is used when creating the SaveablesProvider.
	 * The listener is attached to the SaveablesProvider to forward its notifications
	 * to the Eclipse Workbench. 
	 */
	private ISaveablesLifecycleListener saveablesProviderChangeListener = new ISaveablesLifecycleListener() {
		@Override
		public void handleLifecycleEvent(SaveablesLifecycleEvent event) {
			VirSatNavigator.this.firePropertyChange(PROP_DIRTY);
		}
	};
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		// Initialize our own SaveblesProvider and make sure
		// it is capable of informing this view about its changes
		saveablesProvider = new VirSatProjectSaveablesProvider();
		saveablesProvider.init(saveablesProviderChangeListener);
		VirSatTransactionalEditingDomain.addResourceEventListener(this);
		
		
	}
	
	@Override
	public void createPartControl(Composite aParent) {
		final int TREE_LEVEL = 3;
		super.createPartControl(aParent);
		TreeViewer treeViewer = getCommonViewer();
		treeViewer.expandToLevel(TREE_LEVEL);
	}
	
	private VirSatProjectSaveablesProvider saveablesProvider;
	
	@Override
	public Saveable[] getSaveables() {
		// Override to this method to provide our Saveables as well.
		// Attaching our SaveablesProvider directly to the COntentProcvider as
		// intended by the CNF does not work as needed. The CNF expects a proper
		// containment hierarchy following the default settings of EMF.
		// Since our tree is displaying also referenced Objects as children the
		// reverse path from the children to their parents is not one identical anymore.
		// the CNF fails to deal with this.
		List<Saveable> saveables = new LinkedList<>();
		Saveable[] virSatSaveables = saveablesProvider.getSaveables();
		Saveable[] commonSaveables = super.getSaveables();
		
		saveables.addAll(Arrays.asList(virSatSaveables));
		saveables.addAll(Arrays.asList(commonSaveables));
		return saveables.toArray(new Saveable[0]);
	}
	
	@Override
	public void dispose() {
		VirSatTransactionalEditingDomain.addResourceEventListener(this);
		saveablesProvider.dispose();
		super.dispose();
	}
	
	@Override
	protected void handleDoubleClick(DoubleClickEvent anEvent) {
		
		ICommandService commandService = getViewSite().getService(ICommandService.class);
		Command openProjectCommand = commandService.getCommand("de.dlr.sc.virsat.model.editor.command.openCustomEditor");
		
		if (openProjectCommand != null && openProjectCommand.isHandled()) {
			IStructuredSelection selection = (IStructuredSelection) anEvent.getSelection();
			Object element = selection.getFirstElement();
			
			if ((element instanceof StructuralElementInstance)
					  || (element instanceof Repository)
					  || (element instanceof RoleManagement)
					  || (element instanceof UnitManagement)
					  || (element instanceof CategoryAssignment)
					  || (element instanceof APropertyInstance)) {
				try {
					openProjectCommand.executeWithChecks(new ExecutionEvent());
				} catch (CommandException ex) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "'Open System Component Editor' failed", ex));
				}
				return;
			}
		}
		super.handleDoubleClick(anEvent);
	}

	@Override
	public void resourceEvent(Set<Resource> resources, int event) {
		// In case that there is a reload or unload event in one of the domains
		// make sure that nothing is selected anymore to avoid updates on stale objects or similar
		if ((event == VirSatTransactionalEditingDomain.EVENT_UNLOAD) || (event == VirSatTransactionalEditingDomain.EVENT_RELOAD)) {
			Display.getDefault().asyncExec(() -> this.getCommonViewer().setSelection(new StructuredSelection()));
		}
	}
	
	protected static final boolean ENABLE_ADVANCED_REFRESH_STACK_TRACE_DEBUGGING = false;
	
	/**
	 * Method for debugging. Tells the stack trace and provides details to the given object.
	 * Mainly used in the refresh methods to analyze what is going in there.
	 * @param loggingObject The object for which to get more detail information.
	 * @return String that  contains detailed debugging information
	 */
	protected String createImprovedLoggingMessage(Object loggingObject) {
		StringBuilder stackTraceBuilder = new StringBuilder("No Stack Trace");

		// Give some detailed information from where the method got called
		if (ENABLE_ADVANCED_REFRESH_STACK_TRACE_DEBUGGING) {
			for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
				stackTraceBuilder.append(ste.toString() + "\n");
			}
		}
	
		String elementClass = "ClassName: Null";
		String nameOfIName = "IName: N/A";
		
		if (loggingObject != null) {
			elementClass = "ClassName: " + loggingObject.getClass().getSimpleName();
			nameOfIName = "IName: " + ((loggingObject instanceof IName) ? ((IName) loggingObject).getName() : "N/A");
		}
		
		// Now start logging and processing
		return  elementClass + "\n"
			+ nameOfIName + "\n"
			+ "StackTrace:\n"
			+ stackTraceBuilder.toString();
	}
	
	@Override
	protected CommonViewer createCommonViewerObject(Composite aParent) {
		// Here we override the functionality of the common viewer which is used in the background of a Navigator
		// It fixes the Issue of Ticket #315 of collapsing navigator trees. We could figure out, that once an element
		// is added to the middle of the tree by a content provider, that all trees below the new object collapse.
		// This is due to the way the internal refresh handles that issue. It adds a new treeItem to the end of the tree
		// and re-associates the second tree item to the new SEI for example. The new TreeItem is than re-associates to the old
		// previous SEI but the expanded state on the new item got lost
		return new CommonViewer(getViewSite().getId(), aParent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL) {
			
			private PaletteObjectDropAdapterAssistant paletteObjectDropAdapterAssistant = new PaletteObjectDropAdapterAssistant(); 
			
			@Override
			protected void internalRefresh(Object element, boolean updateLabels) {
				// Save the expanded state of all elements and try to unfold them later
				// This method is used in particular in case resources get reloaded
				// Usually the navigator maps already opened elements by their providers and
				// the actual objects. This does not work for a resource reload, since it will
				// be different objects
				
				// Now start logging and processing
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(),
						"VirSatNavigator internal refresh\n" + createImprovedLoggingMessage(element)));
			
				try {
					// Get the currently expanded objects
					Object[] expandedObjects = getExpandedElements();
					
					// Disable drawing so that restoring the expansion state doesn't cause intermediate draws
					getTree().setRedraw(false);
					super.internalRefresh(element, updateLabels);
					
					// In case a reload happened the expanded objects maybe stale and need to be
					// updated to the ones that got reloaded by the Virtual Satellite ResourceSets
					expandedObjects = rebindStaleExpandedObjects(expandedObjects);
					setExpandedElements(expandedObjects);
				} catch (Exception e) {
					Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(),
							"VirSatNavigator: Unexpected Exception when refreshing the Navigator Tree", e));
				} finally {
					// Re-enable the draw to draw the refreshed navigator with the correctly expanded objects
					getTree().setRedraw(true);
				}
				
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(),
						"VirSatNavigator: Finished and internal refresh"));
			}
			
			/**
			 * Find the newly reloaded objects that correspond to the old now potentially unloaded objects
			 * @param expandedObjects the object that were expanded before the refresh
			 * @return the new objects corresponding to the old expanded objects
			 */
			private Object[] rebindStaleExpandedObjects(Object[] expandedObjects) {
				List<Object> newExpandedObjects = new ArrayList<>();
				for (Object expandedObject : expandedObjects) {
					if (expandedObject instanceof EObject) {
						EObject expandedEObject = (EObject) expandedObject;
						URI expandedObjectUri = EcoreUtil.getURI(expandedEObject);
						EditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(expandedObjectUri);
						
						// Only if we have an editing domain (possible to not have one if the project got deleted)
						if (ed != null) {
							EObject newExpandedEObject = ed.getResourceSet().getEObject(expandedObjectUri, true);
							// Only if there actually is a new object (possible to not have one if there was a deletion)
							if (newExpandedEObject != null) {
								newExpandedObjects.add(newExpandedEObject);
							}
						} 
					} else {
						// If the object is not an EObject, then we do not need to worry about reloading logic
						// and can simply take the old object 
						newExpandedObjects.add(expandedObject);
					}
				}
				
				return newExpandedObjects.toArray(new Object[0]);
			}
			
			@Override
			public void addDropSupport(int operations, Transfer[] transferTypes, DropTargetListener listener) {
				Control control = getControl();
				DropTarget dropTarget = new DropTarget(control, operations);
				dropTarget.setTransfer(transferTypes);
				dropTarget.addDropListener(new DropTargetListener() {
					@Override
					public void dropAccept(DropTargetEvent event) {
						Object target = getTarget(event);
						IStatus status = paletteObjectDropAdapterAssistant.validateDrop(target, DND.DROP_MOVE);
						if (status.equals(Status.CANCEL_STATUS)) {
							listener.dropAccept(event);
						}
					}
					
					@Override
					public void drop(DropTargetEvent event) {
						Object target = getTarget(event);
						IStatus status = paletteObjectDropAdapterAssistant.validateDrop(target, DND.DROP_MOVE);
						if (status.equals(Status.OK_STATUS)) {
							paletteObjectDropAdapterAssistant.handleDrop(event, target);
							expandToLevel(target, 1);
						} else {
							listener.drop(event);
						}
					}
					
					@Override
					public void dragOver(DropTargetEvent event) {			
						Object target = getTarget(event);
						IStatus status = paletteObjectDropAdapterAssistant.validateDrop(target, DND.DROP_MOVE);
						if (status.equals(Status.CANCEL_STATUS)) {
							listener.dragOver(event);
						}  else {
							event.detail = DND.DROP_MOVE;
						}
					}
					
					@Override
					public void dragOperationChanged(DropTargetEvent event) {
						listener.dragOperationChanged(event);
					}
					
					@Override
					public void dragLeave(DropTargetEvent event) {
						listener.dragLeave(event);
					}
					
					@Override
					public void dragEnter(DropTargetEvent event) {
						listener.dragEnter(event);
					}
					
					/**
					 * Gets the drop target of the event
					 * @param event the drop target event
					 * @return the drop target
					 */
					public Object getTarget(DropTargetEvent event) {
						return event.item == null ? null : event.item.getData();
					}
				});
			}
		};
	}
}
