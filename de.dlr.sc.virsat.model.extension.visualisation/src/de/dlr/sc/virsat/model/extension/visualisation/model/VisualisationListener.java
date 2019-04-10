/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.visualisation.model;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;
import de.dlr.sc.virsat.model.extension.visualisation.shape.ShapeHelper;
import de.dlr.sc.virsat.model.extension.visualisation.shape.VisualisationShape;


/**
 * Listener for changes related to visualisation in the project tree
 * @author kova_an
 *
 */
public class VisualisationListener extends EContentAdapter {

	private ShapeHelper shapeHelper;
    private boolean enabled = true;
    
	/**
	 * @param shapeHelper 
	 */
    public VisualisationListener(ShapeHelper shapeHelper) {
		super();
		this.shapeHelper = shapeHelper;
	}

    /**
     * Disables this listener
     */
    public void disableListening() {
    	enabled = false;
    }

    /**
     * Enables this listener
     */
    public void enableListening() {
    	enabled = true;
    }
    
    @Override
	public void notifyChanged(Notification notification) {
    	if (!enabled) {
    		return;
    	}
    	
        super.notifyChanged(notification);

        if (notification.isTouch()) {
        	if (geometryPathSet(notification) && notificationFromGeometryShape(notification)) {
        		handleGeometryReloadNotification(notification);
        	} else {
            	return;
        	}
        }
        
        if (addRootSEI(notification)) {
        	handleAddRootSEI(notification);
        	handleAddSEI(notification);
    	} else if (addSEI(notification)) {
        	handleAddSEI(notification);
        } else if (addVisCA(notification)) {
        	handleAddVisCA(notification);
        } else if (removeVisCA(notification)) {
        	handleRemoveVisCA(notification);
        } else if (removeChildSEI(notification)) {
        	handleRemoveChildSEI(notification);
        } else if (changeVisParameterValue(notification)) {
        	handleChangeVisParameterValue(notification);
        }
    }

	/**
     * Checks if a notification refers to Visualisation geometryFile field
     * @param notification 
     * @return true/false
     */
	private boolean geometryPathSet(Notification notification) {
		return (notification.getNotifier() instanceof ResourcePropertyInstance)
    		&& notification.getEventType() == Notification.SET
    		&& ((CategoryAssignment) ((APropertyInstance) notification.getNotifier()).eContainer()).getType().getFullQualifiedName().equals(Visualisation.FULL_QUALIFIED_CATEGORY_NAME)
    		&& ((APropertyInstance) notification.getNotifier()).getType().getName().equals(Visualisation.PROPERTY_GEOMETRYFILE)
    		&& notification.getFeatureID(ResourcePropertyInstance.class) == PropertyinstancesPackage.RESOURCE_PROPERTY_INSTANCE__RESOURCE_URI;
	}

	/**
	 * @param notification 
	 * @return true if notification came from Visualisation with shape Geometry, false otherwise
	 */
	private boolean notificationFromGeometryShape(Notification notification) {
		CategoryAssignment ca = (CategoryAssignment) ((APropertyInstance) notification.getNotifier()).eContainer();
		Visualisation visBean = new Visualisation(ca);
		return visBean.getShape().equals(VisualisationShape.GEOMETRY.toString());
	}
	
	/**
	 * Checks whether the notifier is adding a root SEI to the repository
	 * @param notification 
	 * @return boolean
	 */
	private boolean addRootSEI(Notification notification) {
		return notification.getNotifier() instanceof Repository
        		&& notification.getFeatureID(Repository.class) == DVLMPackage.REPOSITORY__ROOT_ENTITIES
        		&& notification.getEventType() == Notification.ADD;
	}

	/**
	 * @param notification 
	 * @return true if it is add of a non-root SEI notification
	 */
	private boolean addSEI(Notification notification) {
		return notification.getNotifier() instanceof StructuralElementInstance
        		&& notification.getFeatureID(StructuralElementInstance.class) == StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN
        		&& notification.getEventType() == Notification.ADD;
	}

	/**
	 * @param notification 
	 * @return if it is Add Visualisation Category Assignment Notification
	 */
	private boolean addVisCA(Notification notification) {
		return notification.getNotifier() instanceof StructuralElementInstance
        		&& notification.getFeatureID(StructuralElementInstance.class) == StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CATEGORY_ASSIGNMENTS
        		&& notification.getEventType() == Notification.ADD
        		&& ((CategoryAssignment) notification.getNewValue()).getType().getFullQualifiedName().equals(Visualisation.FULL_QUALIFIED_CATEGORY_NAME);
	}

	/**
	 * @param notification 
	 * @return if it is Remove Visualisation Category Assignment Notification
	 */
	private boolean removeVisCA(Notification notification) {
		return notification.getNotifier() instanceof StructuralElementInstance
        		&& notification.getFeatureID(StructuralElementInstance.class) == StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CATEGORY_ASSIGNMENTS
        		&& notification.getEventType() == Notification.REMOVE
        		&& ((CategoryAssignment) notification.getOldValue()).getType().getFullQualifiedName().equals(Visualisation.FULL_QUALIFIED_CATEGORY_NAME);
	}

	/**
	 * Checks if this is a removal of a SEI from either a Repository or another SEI
	 * @param notification 
	 * @return boolean
	 */
	private boolean removeChildSEI(Notification notification) {
		return removeRootSEI(notification) || removeNonRootSEI(notification);
	}
	
	/**
	 * @param notification 
	 * @return if it is Remove Child Structural Element Instance Notification
	 */
	private boolean removeNonRootSEI(Notification notification) {
		return notification.getNotifier() instanceof StructuralElementInstance
        		&& notification.getFeatureID(StructuralElementInstance.class) == StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN
        		&& notification.getEventType() == Notification.REMOVE;
	}

	/**
	 * @param notification 
	 * @return if it is Remove Root Structural Element Instance from Repository Notification
	 */
	private boolean removeRootSEI(Notification notification) {
		return notification.getNotifier() instanceof Repository
        		&& notification.getFeatureID(Repository.class) == DVLMPackage.REPOSITORY__ROOT_ENTITIES
        		&& notification.getEventType() == Notification.REMOVE;
	}
	
	/**
	 * @param notification 
	 * @return if it is Change Visualisation Parameter Value Notification
	 */
	private boolean changeVisParameterValue(Notification notification) {
		return (notification.getNotifier() instanceof ValuePropertyInstance 
					|| notification.getNotifier() instanceof EnumUnitPropertyInstance
					|| notification.getNotifier() instanceof ResourcePropertyInstance)
        		&& notification.getEventType() == Notification.SET
        		&& ((CategoryAssignment) ((APropertyInstance) notification.getNotifier()).eContainer()).getType().getFullQualifiedName().equals(Visualisation.FULL_QUALIFIED_CATEGORY_NAME);
	}

	/**
	 * Forward a geometry reload notification to shapeHelper
	 * @param notification 
	 */
    private void handleGeometryReloadNotification(Notification notification) {
		StructuralElementInstance sei = (StructuralElementInstance) ((APropertyInstance) notification.getNotifier()).eContainer().eContainer();
		shapeHelper.reloadGeometryFileForSei(sei);
	}
    
	/**
	 * Adds this listener to the newly added Root SEI. This doesn't happen automatically because the link between repository
	 * and root SEIs is not a containment.
	 * @param notification 
	 */
	private void handleAddRootSEI(Notification notification) {
		StructuralElementInstance newRootSei = (StructuralElementInstance) notification.getNewValue();
		newRootSei.eAdapters().add(this);
	}

	/**
	 * Creates corresponding subtree in visualisation tree
	 * @param notification 
	 */
	private void handleAddSEI(Notification notification) {
		StructuralElementInstance newSei = (StructuralElementInstance) notification.getNewValue();
		shapeHelper.traverseTreeAndCreateShapes(newSei);
	}
	
	/**
	 * 
	 * @param notification 
	 */
	private void handleAddVisCA(Notification notification) {
		StructuralElementInstance seiWithNewVisualisationCategoryAssignment = (StructuralElementInstance) notification.getNotifier();
		shapeHelper.createShapeFromSeiAndPlaceItIntoVisualisationTree(seiWithNewVisualisationCategoryAssignment);
	}
	
	/**
	 * 
	 * @param notification 
	 */
	private void handleRemoveVisCA(Notification notification) {
		StructuralElementInstance seiWithVisualisationCategoryAssignmentRemoved = (StructuralElementInstance) notification.getNotifier();
		shapeHelper.removeVisualisationCategoryFromSei(seiWithVisualisationCategoryAssignmentRemoved);
	}
	
	/**
	 * 
	 * @param notification 
	 */
	private void handleRemoveChildSEI(Notification notification) {
		StructuralElementInstance removedSei = (StructuralElementInstance) notification.getOldValue();
		shapeHelper.removeSei(removedSei);
	}
	
	/**
	 * 
	 * @param notification 
	 */
	private void handleChangeVisParameterValue(Notification notification) {
		StructuralElementInstance editedSei = (StructuralElementInstance) ((CategoryAssignment) ((APropertyInstance) notification.getNotifier()).eContainer()).eContainer();
		shapeHelper.editShapeFromStructuralElementInstance(editedSei);
	}
}