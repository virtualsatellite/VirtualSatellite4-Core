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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.dlr.sc.virsat.model.extension.visualisation.shape.IShapeEditObserver;
import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;

/**
 * Mock implementation of IVisualisationTreeManager for tests
 */
public class MockVisualisationTreeManager implements IVisualisationTreeManager {

	Map<String, Shape> createdShapes = new HashMap<>();
	Map<String, String> parents = new HashMap<>();
	Set<String> removedSubtrees = new HashSet<>();
	Set<String> editedShapes = new HashSet<>();
	Set<String> refreshedGeometryShapes = new HashSet<>();
	
	@Override
	public void createShape(Shape shape) {
		Shape shapeCopy = new Shape(shape);
		createdShapes.put(shape.id, shapeCopy);
	}

	@Override
	public void editShape(Shape shape) {
		Shape shapeCopy = new Shape(shape);
		assertTrue("Shape which is being edited has been previously created", createdShapes.containsKey(shape.id));
		createdShapes.put(shape.id, shapeCopy);
		editedShapes.add(shape.id);
	}

	@Override
	public void setParent(String idChild, String idParent) {
		parents.put(idChild, idParent);
	}

	@Override
	public void removeShape(String shapeId) {
		createdShapes.remove(shapeId);
		parents.remove(shapeId);
		parents.entrySet().removeIf(entry -> entry.getValue().equals(shapeId));
	}

	@Override
	public void removeShapeWithSubtree(String shapeId) {
		removedSubtrees.add(shapeId);
	}
	
	/**
	 * Asserts that n different shapes were created
	 * @param n 
	 */
	public void assertNumberOfShapes(int n) {
		assertEquals("Instatiator has created the expected number of shapes", n, createdShapes.size());
	}
	
	/**
	 * Asserts a particular shape was created
	 * @param shape 
	 */
	public void assertShapeIsCreated(Shape shape) {
		Shape createdShape = createdShapes.get(shape.id);
		assertNotNull("Shape was created", createdShape);
		assertEquals("Instatiator has created an expected shape", shape, createdShape);
	}

	/**
	 * Asserts that a shape with childShapeId was added as a child of parentShapeId
	 * @param childShapeId 
	 * @param parentShapeId 
	 */
	public void assertShapeHasParent(String childShapeId, String parentShapeId) {
		assertEquals("A shape has an expected parent", parentShapeId, parents.get(childShapeId));
	}
	
	/**
	 * Asserts the shape with a specific id has no parent
	 * @param shapeId 
	 */
	public void assertShapeHasNoParent(String shapeId) {
		assertFalse("A shape is not expected to have a parent", parents.containsKey(shapeId));
	}

	/**
	 * Asserts the shape with a specific id was removed with subtree
	 * @param shapeId 
	 */
	public void assertNodeRemovedWithSubtree(String shapeId) {
		assertTrue("A node was removed with subtree", removedSubtrees.contains(shapeId));
	}

	/**
	 * Asserts that n nodes were removed with subtrees
	 * @param n 
	 */
	public void assertNodesRemovedWithSubtreeCount(int n) {
		assertEquals("Instantiator removed a correct number of nodes with subtrees", n,  removedSubtrees.size());
	}

	
	/**
	 * Asserts the shape with a specific id has been edited
	 * @param shapeId 
	 */
	public void assertShapeWasEdited(String shapeId) {
		assertTrue("A shape was edited", editedShapes.contains(shapeId));
	}

	/**
	 * Asserts the shape with a specific id has not been edited
	 * @param shapeId 
	 */
	public void assertShapeWasNotEdited(String shapeId) {
		assertFalse("A shape was not edited", editedShapes.contains(shapeId));
	}

	@Override
	public void setShapeEditObserver(IShapeEditObserver observer) {
	}

	@Override
	public void reloadGeometryFile(String shapeId) {
		assertTrue("Shape which is being edited has been previously created", createdShapes.containsKey(shapeId));
		refreshedGeometryShapes.add(shapeId);
	}
	
	/**
	 * Asserts that geometry reload method was called for a shape with a specific id
	 * @param shapeId 
	 */
	public void assertShapeGeometryWasReloaded(String shapeId) {
		assertTrue("Shape geometry was reloaded", refreshedGeometryShapes.contains(shapeId));
	}

	/**
	 * Asserts that geometry reload method was not called for a shape with a specific id
	 * @param shapeId 
	 */
	public void assertShapeGeometryWasNotReloaded(String shapeId) {
		assertFalse("Shape geometry was not reloaded", refreshedGeometryShapes.contains(shapeId));
	}
};