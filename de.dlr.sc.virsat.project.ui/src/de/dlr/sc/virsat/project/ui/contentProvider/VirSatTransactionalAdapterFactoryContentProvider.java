/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.contentProvider;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalPropertySource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySource;

import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.ABeanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.util.VirSatTransactionalEditingDomainHelper;

/**
 * This class provides a transactional AdpaterFactoryContentProvider that deals with the case
 * that there is a different say unique Editing Domain for each Individual project/ResourceSet
 * @author fisc_ph
 *
 */
public class VirSatTransactionalAdapterFactoryContentProvider extends org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider {

	/**
	 * Constructor for the Content Provider
	 * @param adapterFactory The Compsoed Adapater Factory from the EMF.Edit to be used 
	 */
	public VirSatTransactionalAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		notificationRedirections = new HashMap<>();
	}

	@Override
	protected IPropertySource createPropertySource(final Object object, final IItemPropertySource itemPropertySource) {
		TransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd((EObject) object);
		if (ed != null) {
			return wrap(ed, VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<IPropertySource>() {
				public void run() {
					setResult(VirSatTransactionalAdapterFactoryContentProvider.super.createPropertySource(object, itemPropertySource));
				}
			}));
		} else {
			return VirSatTransactionalAdapterFactoryContentProvider.super.createPropertySource(object, itemPropertySource);
		}
	}

	@Override
	public Object[] getChildren(final Object parentObject) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(parentObject, new RunnableWithResult.Impl<Object[]>() {
			public void run() {
				setResult(VirSatTransactionalAdapterFactoryContentProvider.super.getChildren(parentObject));
			}
		});
	}

	@Override
	public Object[] getElements(final Object rootObject) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(rootObject, new RunnableWithResult.Impl<Object[]>() {
			public void run() {
				setResult(VirSatTransactionalAdapterFactoryContentProvider.super.getElements(rootObject));
			}
		});
	}

	@Override
	public Object getParent(final Object childObject) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(childObject, new RunnableWithResult.Impl<Object>() {
			public void run() {
				setResult(VirSatTransactionalAdapterFactoryContentProvider.super.getParent(childObject));
			}
		});
	}

	@Override
	public IPropertySource getPropertySource(final Object object) {
		TransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd((EObject) object);
		if (ed != null) {
			return wrap(ed, VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<IPropertySource>() {
				public void run() {
					setResult(VirSatTransactionalAdapterFactoryContentProvider.super.getPropertySource(object));
				}
			}));
		} else {
			return VirSatTransactionalAdapterFactoryContentProvider.super.getPropertySource(object);
		}
	}

	@Override
	public boolean hasChildren(final Object parentObject) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(parentObject, new RunnableWithResult.Impl<Boolean>() {
			public void run() {
				setResult(VirSatTransactionalAdapterFactoryContentProvider.super.hasChildren(parentObject));
			}
		});
	}

	@Override
	public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) { 
		VirSatTransactionalEditingDomainHelper.tryRunExclusive(newInput, new RunnableWithResult.Impl<Object>() {
			public void run() {
				VirSatTransactionalAdapterFactoryContentProvider.super.inputChanged(viewer, oldInput, newInput);
			}
		});
	}

	/**
	 * Wraps the property Source into an Transactional One
	 * @param ed The Editing Domain to be used to wrap the object
	 * @param propertySource The property source to be wrapped
	 * @return a wrapped property source
	 */
	protected IPropertySource wrap(TransactionalEditingDomain ed, IPropertySource propertySource) {
		return (propertySource == null) ? null : new TransactionalPropertySource(ed, propertySource);
	}
	
	private Map<Object, Object> notificationRedirections;
	
	/**
	 * Use this method to redirect notifications. This is needed if for example you display some sub object
	 * of the ones which are provided by the content provider. E.G. the String attribute of a StringPropertyInstance
	 * Referenced by a ReferencePropertyInstance.
	 * @param changedObject The object that will be changed for example the StringPropertyInstance
	 * @param notifyObject The object that should also receive a notification such as the ReferencePropertyInstance so it can notify its content provider correctly
	 */
	public void redirectNotification(Object changedObject, Object notifyObject) {
		if (changedObject ==  notifyObject) {
			return;
		}
		// Adapt the changing Object so that we will receive change notifications from it in the future
		// The Adapt from the EMF Provider makes sure that an Adapter will be stored to the EObject
		// That will notify this LabelProvider accordingly
		adapterFactory.adapt(changedObject, ITreeContentProvider.class);
		notificationRedirections.put(changedObject, notifyObject);
	}
	
	/**
	 * Use this method to redirect notifications. This is needed if for example you display some sub object
	 * of the ones which are provided by the content provider. E.G. the String attribute of a StringPropertyInstance
	 * Referenced by a ReferencePropertyInstance.
	 * @param changedObject The object that will be changed for example the StringPropertyInstance
	 * @param notifyObject The object that should also receive a notification such as the ReferencePropertyInstance so it can notify its content provider correctly
	 */
	protected void redirectNotification(ABeanProperty<?> changedObject, Object notifyObject) {
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
	
	private boolean updateCaContainerLabels = false;
	
	/**
	 * Method to globally force label updates on CaContainers in case their children are changed. 
	 * This functionality has been added in the frame of implementing the overview tree tables for mass and power budgets
	 * @param updateCaContainerLabels boolean set to true to force label updates on CaContainers
	 * @return this Content provider Instance
	 */
	public VirSatTransactionalAdapterFactoryContentProvider setUpdateCaContainerLabels(boolean updateCaContainerLabels) {
		this.updateCaContainerLabels = updateCaContainerLabels;
		return this;
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		Display.getDefault().asyncExec(() -> {
			// Usually when adding or removing an EquipmentMass or Summary from A SEI, the content provider
			// gets a notification that children of the SEI have changed and it throws a notification.
			// But this notification does not trigger label update of this SEI as standard behavior.
			// This way, the labels are not updated and the tree view does not show the expected CAs being overlayed over
			// their containers/SEIs. Accordingly we catch events on the CAContainer and we force it to be 
			// a notification with label update and now things are overlayed correctly on the fly as soon as a correct CA
			// is added or removed
			Object notificationObject = notification.getNotifier();
			
			if (updateCaContainerLabels && (notificationObject instanceof ICategoryAssignmentContainer)) {
				super.notifyChanged(new ViewerNotification(notification, notificationObject, true, true));
			} else {
				// For the other notifications we stick to the standard behavior
				super.notifyChanged(notification);
			}
			
			// Check for aredirection and execute it
			if (notificationRedirections.containsKey(notificationObject)) {
				Object notificationParentObject = notificationRedirections.get(notificationObject);
				Notification redirectedViewerNotification = new ViewerNotification(notification, notificationParentObject, true, true);
				super.notifyChanged(redirectedViewerNotification);
			}
		});
	}
}
