/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.visualisation.shape;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.visualisation.IVisualisationTreeManager;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.IPausableSender;

/**
 * A class for extracting of shape data from a StructuralElementInstance,
 * creating, editing and arranging shapes via IVisualisationTreeManager
 * 
 * @author kova_an
 *
 */
public class ShapeHelper {

	protected IVisualisationTreeManager visualisationTreeManager;
	private ShapeEditObserver shapeEditObserver;
	
	/**
	 * @param visualisationTreeManager tree manager to create and edit shapes in
	 * @param shapeEditObserver observer which should recognize changes on visualisation child and updates the DVLM model
	 */
	public ShapeHelper(IVisualisationTreeManager visualisationTreeManager, ShapeEditObserver shapeEditObserver) {
		this.visualisationTreeManager = visualisationTreeManager;
		this.shapeEditObserver = shapeEditObserver;
	}

	/**
	 * Traverse a tree starting at given StructuralElementInstance
	 * and create a corresponding visualisation tree
	 * @param sei 
	 */
	public void traverseTreeAndCreateShapes(StructuralElementInstance sei) {
		pauseVisTreeManagerUpdateSending();
		StructuralElementInstance visParent = getVisualisationParent(sei);
		String visParentId = visParent != null ? visParent.getUuid().toString() : null;
		traverseTreeAndCreateShapes(sei, visParentId);
		resumeVisTreeManagerUpdateSending();
	}

	/**
	 * Recursively traverse a tree of StructuralElementInstances starting in a given StructuralElementInstance
	 * and create a corresponding visualisation tree
	 * @param sei 
	 * @param parentShapeId id of parent StructuralElementInstance or null if it is root
	 */
	private void traverseTreeAndCreateShapes(StructuralElementInstance sei, String parentShapeId) {
		if (seiHasVisualisationCategoryAssignment(sei)) {
			String shapeId = sei.getUuid().toString();
			createVisShape(sei);
			if (parentShapeId != null) {
				setShapeParent(parentShapeId, shapeId);
			}
			parentShapeId = shapeId;
		}
		
		for (StructuralElementInstance child : sei.getChildren()) {
			traverseTreeAndCreateShapes(child, parentShapeId);
		}
	}

	/**
	 * Creates child-parent link in visualisationTreeManager
	 * @param parentShapeId 
	 * @param shapeId 
	 */
	protected void setShapeParent(String parentShapeId, String shapeId) {
		visualisationTreeManager.setParent(shapeId, parentShapeId);
	}

	/**
	 * Create a visualisation shape for a given StructuralElementInstance
	 * @param sei 
	 */
	private void createVisShape(StructuralElementInstance sei) {
		Visualisation visBean = new BeanStructuralElementInstance(sei).getFirst(Visualisation.class);
		String shapeId = sei.getUuid().toString();
		createShape(visBean, shapeId);
	}
	
	/**
	 * Send a call to update visualisation shape for a given StructuralElementInstance with new values
	 * @param sei 
	 */
	public void editShapeFromStructuralElementInstance(StructuralElementInstance sei) {
		Visualisation visBean = new BeanStructuralElementInstance(sei).getFirst(Visualisation.class);
		String shapeId = sei.getUuid().toString();
		editShape(visBean, shapeId);
	}

	/**
	 * Send a call to reload geometry file for a given StructuralElementInstance
	 * @param sei 
	 */
	public void reloadGeometryFileForSei(StructuralElementInstance sei) {
		String shapeId = sei.getUuid().toString();
		visualisationTreeManager.reloadGeometryFile(shapeId);
	}
	
	/**
	 * Create a shape for given StructuralElementInstance and place it into visualisation tree
	 * @param sei 
	 */
	public void createShapeFromSeiAndPlaceItIntoVisualisationTree(StructuralElementInstance sei) {
		createVisShape(sei);

		setNewShapeVisParentAndChildren(sei);
	}

	/**
	 * Removes a given StructuralElementInstance from visualisation tree setting correct child-parent relations between its parent and children
	 * @param sei 
	 */
	public void removeVisualisationCategoryFromSei(StructuralElementInstance sei) {
		visualisationTreeManager.removeShape(sei.getUuid().toString());

		moveChildrenOfRemovedShapeUnderItsVisParent(sei);
	}

	/**
	 * Removes a given StructuralElementInstance from visualisation tree
	 * @param sei 
	 */
	public void removeSei(StructuralElementInstance sei) {
		if (seiHasVisualisationCategoryAssignment(sei)) {
			visualisationTreeManager.removeShapeWithSubtree(sei.getUuid().toString());
		} else {
			List<StructuralElementInstance> visChildren = new ArrayList<>();
			collectVisualisationChildren(sei, visChildren);
			for (StructuralElementInstance visChild: visChildren) {
				visualisationTreeManager.removeShapeWithSubtree(visChild.getUuid().toString());
			}
		}
	}
	
	/**
	 * Sets child-parent relations in visualisation hierarchy if a given StructuralElementInstance is removed from it
	 * @param sei 
	 */
	private void setNewShapeVisParentAndChildren(StructuralElementInstance sei) {
		String newShapeId = sei.getUuid().toString();
		StructuralElementInstance visParent = getVisualisationParent(sei);
		if (visParent != null) {
			visualisationTreeManager.setParent(newShapeId, visParent.getUuid().toString());
		}
		
		List<StructuralElementInstance> visChildren = new ArrayList<>();
		collectVisualisationChildren(sei, visChildren);
		for (StructuralElementInstance visChild: visChildren) {
			visualisationTreeManager.setParent(visChild.getUuid().toString(), newShapeId);
		}
	}

	/**
	 * Sets all closest visualisation children of a given StructuralElementInstance to be children of its visualisation parent
	 * @param sei 
	 */
	private void moveChildrenOfRemovedShapeUnderItsVisParent(StructuralElementInstance sei) {
		StructuralElementInstance visParent = getVisualisationParent(sei);
		if (visParent != null) {
			String visParentId = visParent.getUuid().toString();
			
			List<StructuralElementInstance> visChildren = new ArrayList<>();
			collectVisualisationChildren(sei, visChildren);
			for (StructuralElementInstance visChild: visChildren) {
				visualisationTreeManager.setParent(visChild.getUuid().toString(), visParentId);
			}
		}
	}
	
	/**
	 * Gets a StructuralElementInstance which has Visualisation attached which is the closest ancestor of a given StructuralElementInstance
	 * @param sei 
	 * @return Visualisation parent or null if there is none
	 */
	private StructuralElementInstance getVisualisationParent(StructuralElementInstance sei) {
		EObject parent = sei.eContainer();
		while (parent instanceof StructuralElementInstance) {
			StructuralElementInstance parentSei = (StructuralElementInstance) parent;
			if (seiHasVisualisationCategoryAssignment(parentSei)) {
				return parentSei;
			}
			parent = parent.eContainer();
		}
		return null;
	}

	/**
	 * Recursively collects all children of a given StructuralElementInstance into a given list
	 * @param sei 
	 * @param visualChildrenList 
	 */
	private void collectVisualisationChildren(StructuralElementInstance sei, List<StructuralElementInstance> visualChildrenList) {
		for (StructuralElementInstance child : sei.getChildren()) {
			if (seiHasVisualisationCategoryAssignment(child)) {
				visualChildrenList.add(child);
			} else {
				collectVisualisationChildren(child, visualChildrenList);
			}
		}
	}
	
	/**
	 * Checks that a given StructuralElementInstance has Visualisation category assignment
	 * @param sei The Structural Element Instance to look at if there is a type compatible Visualisation CA
	 * @return true or false
	 */
	private static boolean seiHasVisualisationCategoryAssignment(StructuralElementInstance sei) {
		Visualisation visualisation = new BeanStructuralElementInstance(sei).getFirst(Visualisation.class);
		return visualisation != null;
	}

	/**
	 * Edits a shape with a given id with new values from a given visualisation bean in visualisationTreeManager
	 * @param visBean 
	 * @param shapeId 
	 */
	protected void createShape(Visualisation visBean, String shapeId) {
		Shape shape = createShapeFromVisualisationBean(visBean, shapeId);
		visualisationTreeManager.createShape(shape);
		shapeEditObserver.startObservingBean(shapeId, visBean);
	}

	/**
	 * Creates a shape with values from a given visualisation bean and with given id in visualisationTreeManager
	 * @param visBean 
	 * @param shapeId 
	 */
	private void editShape(Visualisation visBean, String shapeId) {
		Shape shape = createShapeFromVisualisationBean(visBean, shapeId);
		visualisationTreeManager.editShape(shape);
	}

	/**
	 * Creates a Shape from values in a given visualisation bean
	 * @param visBean 
	 * @param shapeId id for a new shape
	 * @return created shape
	 */
	protected Shape createShapeFromVisualisationBean(Visualisation visBean, String shapeId) {
		return createShapeFromBean(visBean, shapeId);
	}

	/**
	 * tries to pause update sending until all the scenegraph is created
	 */
	protected void pauseVisTreeManagerUpdateSending() {
		if (visualisationTreeManager instanceof IPausableSender) {
			((IPausableSender) visualisationTreeManager).pauseSending();
		}
	}

	/**
	 * tries to resume update sending
	 */
	protected void resumeVisTreeManagerUpdateSending() {
		if (visualisationTreeManager instanceof IPausableSender) {
			((IPausableSender) visualisationTreeManager).resumeSending();
		}
	}
	
	/**
	 * Creates Shape from Vis Bean
	 * @param visBean 
	 * @param shapeId 
	 * @return shape
	 */
	private static Shape createShapeFromBean(Visualisation visBean, String shapeId) {
		Shape shape = new Shape();
		shape.id = shapeId;
		visBean.writeToShape(shape);
		return shape;
	}
	
	/**
	 * Create Shape object from SEI
	 * @param sei SEI with Visualisation category attached
	 * @return shape
	 */
	public static Shape createShapeFromSei(StructuralElementInstance sei) {
		Visualisation visBean = new BeanStructuralElementInstance(sei).getFirst(Visualisation.class);
		return createShapeFromBean(visBean, sei.getUuid().toString());
	}
}
