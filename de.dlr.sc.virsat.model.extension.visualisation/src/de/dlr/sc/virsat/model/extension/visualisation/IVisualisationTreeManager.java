/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.visualisation;

import de.dlr.sc.virsat.model.extension.visualisation.shape.IShapeEditObserver;
import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;

/**
 * This interface describes a contract for the component which performs the visualisation.
 * @author kova_an
 *
 */
public interface IVisualisationTreeManager {

	/**
	 * Create a shape.
	 * @param shape data transfer object that contains all shape values.
	 */
	void createShape(Shape shape);
	
	/**
	 * Edit a shape. Finds an old shape by id and updates old values with new values from the passed shape object if they are different.
	 * @param shape data transfer object that contains all shape values.
	 */
	void editShape(Shape shape);
	
	/**
	 * Sets a child-parent relationship between two shapes found by their ids.
	 * @param idChild ID of the child shape to where to set the relation.
	 * @param idParent  ID of the parent from where to set the relation.
	 */
	void setParent(String idChild, String idParent);
	
	/**
	 * Removes the shape and makes all its children root elements.
	 * @param shapeId ID of the shape to be removed.
	 */
	void removeShape(String shapeId);

	/**
	 * Removes the shape and all the subtree under it.
	 * @param shapeId ID of the shape to be removed.
	 */
	void removeShapeWithSubtree(String shapeId);
	
	/**
	 * Forces to reload a file for this shape.
	 * @param shapeId ID of the shape of which to reload the geometry file.
	 */
	void reloadGeometryFile(String shapeId);

	/**
	 * Set the observer to pass shape changes that came from network.
	 * @param observer An observer being triggered when a shape is edited.
	 */
	void setShapeEditObserver(IShapeEditObserver observer);
}
