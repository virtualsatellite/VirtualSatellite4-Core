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
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;

import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;
import de.dlr.sc.virsat.model.extension.visualisation.shape.VisualisationShape;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.TreeManager;
import de.dlr.sc.visproto.VisProto.SceneGraph;
import de.dlr.sc.visproto.VisProto.VisualisationMessage;

/**
 * Test for the functionality of the TreeManager
 */
public class VisualisationTreeManagerTest {

	private static TreeManager treeManager = new TreeManager();
	private static final double DELTA = 1e-15;
	
	@Test
	public final void testCreateSimpleShapesAndSynchronize() throws InvalidProtocolBufferException {
		//CHECKSTYLE:OFF
		Shape shape1 = new Shape("Box001", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape shape2 = new Shape("Sphere002", URI.createURI(""), 1f, 2f, 2f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 25500, 0, VisualisationShape.SPHERE);
		Shape shape3 = new Shape("Cone003", URI.createURI(""), 1f, 1f, 1f, 1f, 0, 0, 0, 0, 2, 1, 30000, 0, VisualisationShape.CONE);
		Shape shape4 = new Shape("Cylinder004", URI.createURI(""), 1f, 1f, 3f, 2f, 0, 0, 2, 1, 7, 0, 10000, 0.6f, VisualisationShape.CYLINDER);
		Shape shape5 = new Shape("None005", URI.createURI(""), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, VisualisationShape.NONE);
		//CHECKSTYLE:ON
		treeManager.createShape(shape1);
		treeManager.createShape(shape2);
		treeManager.createShape(shape3);
		treeManager.createShape(shape4);
		treeManager.createShape(shape5);
		
		VisualisationMessage.Builder messageBuilder = VisualisationMessage.newBuilder();
		messageBuilder.setSceneGraph(SceneGraph.parseFrom(treeManager.getSceneGraph().build().toByteArray()));
		
		TreeManager secondaryTreeManager = new TreeManager();
		secondaryTreeManager.update(messageBuilder.build());
		assertEquals(treeManager.getSceneGraph().build(), secondaryTreeManager.getSceneGraph().build());
	}

	@Test
	public final void testEditShape() {
		//CHECKSTYLE:OFF
		Shape shape = new Shape("Box001", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		treeManager.createShape(shape);
		shape.shape = VisualisationShape.CYLINDER;
		shape.sizeY = 2f;
		shape.radius = 1f;
		shape.color = 25;
		//CHECKSTYLE:ON
		treeManager.editShape(shape);
		assertEquals(2F, treeManager.getShape("Box001").sizeY, DELTA);
	}

	@Test
	public final void testRemoveRootShapeWithSubtree() {
		//CHECKSTYLE:OFF
		Shape shape1 = new Shape("Box001", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape shape2 = new Shape("Sphere002", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		Shape shape3 = new Shape("Box003", URI.createURI(""), 0.5f, 0.5f, 0.5f, 0f, 1f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape shape4 = new Shape("Box004", URI.createURI(""), 1, 1, 1, 0, 0, 0, 4, 0, 0, 0, 100, 0, VisualisationShape.CONE);
		//CHECKSTYLE:ON
		treeManager.createShape(shape1);
		treeManager.createShape(shape2);
		treeManager.createShape(shape3);
		treeManager.createShape(shape4);
		treeManager.setParent("Sphere002", "Box001");
		treeManager.setParent("Box003", "Sphere002");
		treeManager.removeShapeWithSubtree("Box001");
		assertTrue(treeManager.hasShape(TreeManager.VISUALISATION_GLOBAL_ROOT_ID));
		assertTrue(treeManager.hasShape("Box004"));
		assertEquals(1, treeManager.getShapesCount());
		assertFalse(treeManager.hasShape("Box001"));
		assertFalse(treeManager.hasShape("Sphere002"));
		assertFalse(treeManager.hasShape("Box003"));
	}

	@Test
	public final void testRemoveRootShapeWithoutSubtree() {
		
		//CHECKSTYLE:OFF
		Shape root = new Shape("Root", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape child1 = new Shape("Child1", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		Shape child2 = new Shape("Child2", URI.createURI(""), 0.5f, 0.5f, 0.5f, 0f, 1f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		//CHECKSTYLE:ON
		
		treeManager.createShape(root);
		treeManager.createShape(child1);
		treeManager.createShape(child2);
		
		treeManager.setParent(child1.id, root.id);
		treeManager.setParent(child2.id, root.id);
		treeManager.removeShape(root.id);
		assertTrue(treeManager.hasShape(TreeManager.VISUALISATION_GLOBAL_ROOT_ID));
		assertFalse(treeManager.hasShape(root.id));
		assertTrue(treeManager.hasShape(child1.id));
		assertTrue(treeManager.isParent(child1.id, TreeManager.VISUALISATION_GLOBAL_ROOT_ID));
		assertTrue(treeManager.hasShape(child2.id));
		assertTrue(treeManager.isParent(child2.id, TreeManager.VISUALISATION_GLOBAL_ROOT_ID));
	}

	@Test
	public final void testRemoveNonRootShapeWithoutSubtree() {
		
		//CHECKSTYLE:OFF
		Shape root = new Shape("Root", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape parent = new Shape("Parent", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape child1 = new Shape("Child1", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		Shape child2 = new Shape("Child2", URI.createURI(""), 0.5f, 0.5f, 0.5f, 0f, 1f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		//CHECKSTYLE:ON
		
		treeManager.createShape(root);
		treeManager.createShape(parent);
		treeManager.createShape(child1);
		treeManager.createShape(child2);
		
		treeManager.setParent(parent.id, root.id);
		treeManager.setParent(child1.id, parent.id);
		treeManager.setParent(child2.id, parent.id);
		treeManager.removeShape(parent.id);
		assertTrue(treeManager.hasShape(TreeManager.VISUALISATION_GLOBAL_ROOT_ID));
		assertTrue(treeManager.hasShape(root.id));
		assertFalse(treeManager.hasShape(parent.id));
		assertTrue(treeManager.isParent(root.id, TreeManager.VISUALISATION_GLOBAL_ROOT_ID));
		assertTrue(treeManager.hasShape(child1.id));
		assertTrue(treeManager.isParent(child1.id, TreeManager.VISUALISATION_GLOBAL_ROOT_ID));
		assertTrue(treeManager.hasShape(child2.id));
		assertTrue(treeManager.isParent(child2.id, TreeManager.VISUALISATION_GLOBAL_ROOT_ID));
	}
	
	@Test
	public final void testSetParent() {
		
		//CHECKSTYLE:OFF
		Shape shape1 = new Shape("Box001", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape shape2 = new Shape("Sphere002", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		//CHECKSTYLE:ON
		
		treeManager.createShape(shape1);
		treeManager.createShape(shape2);
		
		treeManager.setParent("Sphere002", "Box001");
		assertTrue(treeManager.isParent("Box001", TreeManager.VISUALISATION_GLOBAL_ROOT_ID));
		assertTrue(treeManager.isParent("Sphere002", "Box001"));
	}

	@Test
	public final void testDeleteWholeScenegraph() {
		
		//CHECKSTYLE:OFF
		Shape s1 = new Shape("s1", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape s2 = new Shape("s2", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		Shape s21 = new Shape("s21", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		Shape s22 = new Shape("s22", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		//CHECKSTYLE:ON
		
		treeManager.createShape(s1);
		treeManager.createShape(s2);
		treeManager.createShape(s21);
		treeManager.createShape(s22);
		
		treeManager.setParent(s21.id, s2.id);
		treeManager.setParent(s22.id, s2.id);
		
		treeManager.deleteWholeSceneGraph();
		
		assertEquals(0, treeManager.getShapesCount());
	}

	@Test
	public final void testSetParentNonRootWithSubtree() {
		
		//CHECKSTYLE:OFF
		Shape r = new Shape("root", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape s1 = new Shape("s1", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape s2 = new Shape("s2", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		Shape s21 = new Shape("s21", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		Shape s22 = new Shape("s22", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		Shape s3 = new Shape("s3", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		//CHECKSTYLE:ON
		
		treeManager.createShape(r);
		treeManager.createShape(s1);
		treeManager.createShape(s2);
		treeManager.createShape(s21);
		treeManager.createShape(s22);
		treeManager.createShape(s3);
		
		treeManager.setParent(s1.id, r.id);
		treeManager.setParent(s2.id, r.id);
		treeManager.setParent(s3.id, r.id);
		treeManager.setParent(s21.id, s2.id);
		treeManager.setParent(s22.id, s2.id);
		
		treeManager.setParent(s2.id, s3.id);
		
		//CHECKSTYLE:OFF
		assertEquals(6, treeManager.getShapesCount());
		//CHECKSTYLE:ON
		assertTrue(treeManager.isParent(r.id, TreeManager.VISUALISATION_GLOBAL_ROOT_ID));
		assertTrue(treeManager.isParent(s1.id, r.id));
		assertTrue(treeManager.isParent(s3.id, r.id));
		assertTrue(treeManager.isParent(s2.id, s3.id));
		assertTrue(treeManager.isParent(s21.id, s2.id));
		assertTrue(treeManager.isParent(s22.id, s2.id));
	}

	@Test
	public final void testRemoveNonRootWithSubtree() {
		
		//CHECKSTYLE:OFF
		Shape r = new Shape("root", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape s1 = new Shape("s1", URI.createURI(""), 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 255, 0.5f, VisualisationShape.BOX);
		Shape s2 = new Shape("s2", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		Shape s21 = new Shape("s21", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		Shape s22 = new Shape("s22", URI.createURI(""), 0F, 0F, 0F, 1, 0F, 2F, 0F, 0F, 0F, 0F, 255, 0F, VisualisationShape.SPHERE);
		//CHECKSTYLE:ON
		
		treeManager.createShape(r);
		treeManager.createShape(s1);
		treeManager.createShape(s2);
		treeManager.createShape(s21);
		treeManager.createShape(s22);
		
		treeManager.setParent(s1.id, r.id);
		treeManager.setParent(s2.id, r.id);
		treeManager.setParent(s21.id, s2.id);
		treeManager.setParent(s22.id, s2.id);
		
		treeManager.removeShapeWithSubtree(s2.id);
		
		//CHECKSTYLE:OFF
		assertEquals(2, treeManager.getShapesCount());
		//CHECKSTYLE:ON
		assertTrue(treeManager.isParent(r.id, TreeManager.VISUALISATION_GLOBAL_ROOT_ID));
		assertTrue(treeManager.isParent(s1.id, r.id));
	}
	
	@After
	public final void clearTreeManager() {
		treeManager.deleteWholeSceneGraph();
	}
}
