/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.labelProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.util.VirSatTransactionalEditingDomainHelper;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * This class provides and AdapterFactoryLabelProvider with base functionality to be transactional
 * Read actions to the data model are encapsulated into corresponding Runnables
 * @author fisc_ph
 *
 */
public class VirSatTransactionalAdapterFactoryLabelProvider extends org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider implements IColorProvider, IFontProvider {
	
	/**
	 * Constructor for the Label Provider
	 * @param adapterFactory The Composed Adapter Factory from the EMF.Edit to be used 
	 */
	public VirSatTransactionalAdapterFactoryLabelProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		notificationRedirections = new HashMap<>();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);
	}
	
	
	@Override
	public Image getColumnImage(final Object object, final int columnIndex) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<Image>() {
			public void run() {
				setResult(VirSatTransactionalAdapterFactoryLabelProvider.super.getColumnImage(object, columnIndex));
			}
		});
	}

	@Override
	public String getColumnText(final Object object, final int columnIndex) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<String>() {
			public void run() {
				setResult(VirSatTransactionalAdapterFactoryLabelProvider.super.getColumnText(object, columnIndex));
			}
		});
	}

	@Override
	public Image getImage(final Object object) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<Image>() {
			public void run() {
				setResult(VirSatTransactionalAdapterFactoryLabelProvider.super.getImage(object));
			}
		});
	}

	@Override
	public String getText(final Object object) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<String>() {
			public void run() {
				setResult(VirSatTransactionalAdapterFactoryLabelProvider.super.getText(object));
			}
		});
	}

	@Override
	public boolean isLabelProperty(final Object object, final String id) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<Boolean>() {
			public void run() {
				setResult(VirSatTransactionalAdapterFactoryLabelProvider.super.isLabelProperty(object, id));
			}
		});
	}
	
	private Map<Object, Set<Object>> notificationRedirections;
	
	/**
	 * Use this method to redirect notifications. This is needed if for example you display some sub object
	 * of the ones which are provided by the content provider. E.G. the String attribute of a StringPropertyInstance
	 * Referenced by a ReferencePropertyInstance.
	 * @param changedObject The object that will be changed for example the StringPropertyInstance
	 * @param notifyObject The object that should also receive a notification such as the ReferencePropertyInstance so it can notify its content provider correctly
	 */
	protected void redirectNotification(Object changedObject, Object notifyObject) {
		if (changedObject ==  notifyObject) {
			return;
		}
		// Adapt the changing Object so that we will receive change notifications from it in the future
		// The Adapt from the EMF Provider makes sure that an Adapter will be stored to the EObject
		// That will notify this LabelProvider accordingly
		adapterFactory.adapt(changedObject, ITableItemLabelProvider.class);
		
		Set<Object> notifiedObjects = notificationRedirections.get(changedObject);
		if (notifiedObjects == null) {
			notifiedObjects = new HashSet<>();
			notificationRedirections.put(changedObject, notifiedObjects);
		}
		
		notifiedObjects.add(notifyObject);
	}
	
	/**
	 * Use this method to redirect notifications. This is needed if for example you display some sub object
	 * of the ones which are provided by the content provider. E.G. the String attribute of a StringPropertyInstance
	 * Referenced by a ReferencePropertyInstance.
	 * @param changedObject The object that will be changed for example the StringPropertyInstance
	 * @param notifyObject The object that should also receive a notification such as the ReferencePropertyInstance so it can notify its content provider correctly
	 */
	protected void redirectNotification(ABeanObject<? extends ATypeInstance> changedObject, Object notifyObject) {
		redirectNotification(changedObject.getTypeInstance(), notifyObject);
	}
	
	/**
	 * Use this method to redirect notifications. This is needed if for example you display some sub object
	 * of the ones which are provided by the content provider. E.G. the String attribute of a StringPropertyInstance
	 * Referenced by a ReferencePropertyInstance. This class handles the notification of a whole CategoryAssignment Bean.
	 * If needed the method can register the included properties for redirection as well
	 * @param changedObject The object that will be changed for example the StringPropertyInstance
	 * @param notifyObject The object that should also receive a notification such as the ReferencePropertyInstance so it can notify its content provider correctly
	 * @param includeProperties redirect the properties of the CatgeoryAssignemnt as well
	 */
	protected void redirectNotification(ABeanCategoryAssignment changedObject, Object notifyObject, boolean includeProperties) {
		redirectNotification(changedObject.getTypeInstance(), notifyObject);
		changedObject.getTypeInstance().getPropertyInstances().forEach((api) -> {
			redirectNotification(api, notifyObject);
		});
	}
	
	@Override
	public boolean isFireLabelUpdateNotifications() {
		return true;
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		Display.getDefault().asyncExec(() -> {
			super.notifyChanged(notification);
			Object notificationObject = notification.getNotifier();
			notifyRedirectedObjects(notificationObject);
		});
	}
	
	/**
	 * Notifies all objects who have a registered redirection
	 * @param notificationObject the notification object
	 */
	private void notifyRedirectedObjects(Object notificationObject) {
		if (notificationRedirections.containsKey(notificationObject)) {
			Set<Object> redirectedObjects = new HashSet<>(notificationRedirections.get(notificationObject));
			for (Object redirectedObject : redirectedObjects) {
				for (ILabelProviderListener labelProviderListener : labelProviderListeners) {
					labelProviderListener.labelProviderChanged(new LabelProviderChangedEvent(this, redirectedObject));
			    }
				
				notifyRedirectedObjects(redirectedObject);
			}
		}
	}
	
	/**
	 * This listener is sued to trigger the labels that correspond to changed resource markers
	 * we need this to make the decorators realize that something has changed, and that they update accordingly.
	 */
	private IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {

		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			
			// Step out if event seems to be corrupt
			if (event == null || event.getDelta() == null) {
		        return;
			}
			
			try {
				event.getDelta().accept(new IResourceDeltaVisitor() {
					@Override
					public boolean visit(IResourceDelta delta) throws CoreException {
						// The general Idea is to open the workspace resource in case it is a DVLM resource
						// by loading it with the current VirSatResourceSet. Once loaded we have an EMF Resource
						// which we can crawl for UUID objects. UUID because markers are only attached to UUID objects
						// Once found we trigger all notifiers to this label provider to update on the contained UUID objects
						// the list could be quite long and bigger than the actual displayed objects
						IResource wsResource = delta.getResource();
						IProject resourceProject = wsResource.getProject();
					
						boolean isCorrectKind = delta.getKind() == IResourceDelta.CHANGED;
						boolean isChangedMarker = (delta.getFlags() & IResourceDelta.MARKERS) != 0;

						// Only react to marker changes, and then try to load the EMF resource
						if (isChangedMarker && isCorrectKind && (wsResource instanceof IFile) && VirSatProjectCommons.isDvlmFile((IFile) wsResource)) {
							VirSatResourceSet resSet = VirSatResourceSet.getResourceSet(resourceProject);
							IFile dvlmFile = (IFile) wsResource;
							Resource emfResource = resSet.safeGetResource(dvlmFile, false);
							List<Object> notifyObjects = new ArrayList<>();
							EcoreUtil.getAllContents(emfResource, true).forEachRemaining((object) -> {
								if (object instanceof IUuid) {
									notifyObjects.add(object);
								}
							});
							
							// Now where we collected all the objects that need to be notified
							// we use the displayThread to trigger all of the listeners
							Display.getDefault().asyncExec(() -> {
								for (ILabelProviderListener labelProviderListener : labelProviderListeners) {
									labelProviderListener.labelProviderChanged(new LabelProviderChangedEvent(VirSatTransactionalAdapterFactoryLabelProvider.this, notifyObjects.toArray()));
							    }
							});
						}
						return true;
					}
				});
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed to handle resource change in VirSatTransactionalAdapterFactoryLabelProvider!", e));
			}
		}
	};
}
