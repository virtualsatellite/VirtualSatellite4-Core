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
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.extension.visualisation.delta.CloneShapeDelta;
import de.dlr.sc.virsat.model.extension.visualisation.delta.ColorDelta;
import de.dlr.sc.virsat.model.extension.visualisation.delta.GhostShapeDelta;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModel;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;
import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;
import de.dlr.sc.virsat.model.extension.visualisation.shape.VisualisationShape;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.TreeManager;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.visproto.VisProto.SceneGraph;
import de.dlr.sc.visproto.VisProto.VisualisationMessage;

/**
 * Unit tests for VisualisationTreeInstantiator concerning the initial generation of
 * visualisation scene via IVisualisationTreeManager when traversing a StructuralElementInstance
 * @author kova_an
 *
 */
public class VisualisationTreeInstantiatorTest {

	private Concept visualisationConcept;
	private String visCategoryQualifiedName;
	private StructuralElement se;
	
	//private VisualisationTreeInstantiator instantiator;
	
	private MockVisualisationTreeManager mockVisTreeManager;
	private TreeManager treeManager;
	private IProject project;
	private VirSatResourceSet virSatProjectResourceSet;

	@Before
	public void setupTest() {
	    String conceptXmiPluginPath = "de.dlr.sc.virsat.model.extension.visualisation/concept/concept.xmi";
	    visualisationConcept = ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
	    visCategoryQualifiedName = new Visualisation().getFullQualifiedCategoryName();
	    se = StructuralFactory.eINSTANCE.createStructuralElement();
	    se.getApplicableFor().add(se);

	    mockVisTreeManager =  new MockVisualisationTreeManager();
	    treeManager = new TreeManager();
	    //treeManager.setCommunicationServer(CommunicationServer.getInstance(treeManager));
	}
	
	@After
	public void tearDown() {
		VirSatEditingDomainRegistry.INSTANCE.clear();
		VirSatResourceSet.clear();
	}

	@Test
	public final void testAllPrimitiveShapesAreCreated() {
		for (VisualisationShape shape : VisualisationShape.values()) {
			mockVisTreeManager = new MockVisualisationTreeManager();
			testPrimitiveShapeIsCreated(shape);
		}
	}

	/**
	 * 
	 * @param visShape 
	 */
	private void testPrimitiveShapeIsCreated(VisualisationShape visShape) {
		Shape shape = createShapeWithDifferentValues();
		shape.shape = visShape;
		
		StructuralElementInstance sei = createSeiWithVisualisationShape(shape);

		instantiateVisualisationTreeAndAddListeners(sei);
		
		mockVisTreeManager.assertNumberOfShapes(1);
		mockVisTreeManager.assertShapeIsCreated(shape);
		mockVisTreeManager.assertShapeHasNoParent(shape.id);
	}
	
	@Test
	public final void testParentChildHierarchy() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		Shape shapeChild = createSimpleShape();
		StructuralElementInstance seiChild = createSeiWithVisualisationShape(shapeChild);
		seiParent.getChildren().add(seiChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);
		
		mockVisTreeManager.assertNumberOfShapes(2);
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild.id, shapeParent.id);
	}

	@Test
	public final void testParentWithTwoChildren() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		Shape shapeChild1 = createSimpleShape();
		StructuralElementInstance seiChild1 = createSeiWithVisualisationShape(shapeChild1);
		Shape shapeChild2 = createSimpleShape();
		StructuralElementInstance seiChild2 = createSeiWithVisualisationShape(shapeChild2);
		seiParent.getChildren().add(seiChild1);
		seiParent.getChildren().add(seiChild2);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);
		
		//CHECKSTYLE:OFF
		mockVisTreeManager.assertNumberOfShapes(3);
		//CHECKSTYLE:ON
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeChild1);
		mockVisTreeManager.assertShapeIsCreated(shapeChild2);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild1.id, shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild2.id, shapeParent.id);
	}

	@Test
	public final void testParentChildAndGrandchild() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		Shape shapeChild = createSimpleShape();
		StructuralElementInstance seiChild = createSeiWithVisualisationShape(shapeChild);
		Shape shapeGrandChild = createSimpleShape();
		StructuralElementInstance seiGrandChild = createSeiWithVisualisationShape(shapeGrandChild);
		seiParent.getChildren().add(seiChild);
		seiChild.getChildren().add(seiGrandChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);
		
		//CHECKSTYLE:OFF
		mockVisTreeManager.assertNumberOfShapes(3);
		//CHECKSTYLE:ON
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeChild);
		mockVisTreeManager.assertShapeIsCreated(shapeGrandChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild.id, shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeGrandChild.id, shapeChild.id);
	}
	
	@Test
	public final void testSeiWithoutVisualisation() {
		StructuralElementInstance seiParent = createSeiWithoutVisualisation();
		StructuralElementInstance seiChild1 = createSeiWithoutVisualisation();
		StructuralElementInstance seiChild2 = createSeiWithoutVisualisation();
		StructuralElementInstance seiGrandChild = createSeiWithoutVisualisation();
		
		seiParent.getChildren().add(seiChild1);
		seiParent.getChildren().add(seiChild2);
		seiChild2.getChildren().add(seiGrandChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);
		
		mockVisTreeManager.assertNumberOfShapes(0);
	}

	/**
	 * 
	 * @param sei 
	 */
	private void instantiateVisualisationTreeAndAddListeners(StructuralElementInstance sei) {
		Repository repo = createTestRepositoryContainingSei(sei);
		VisualisationTreeInstantiator instantiator = new VisualisationTreeInstantiator(mockVisTreeManager, repo);
		instantiator.createVisualisationObjects();
		instantiator.attachVisualisationChangeListeners();
	}
	
	/**
	 * 
	 * @param sei 
	 */
	private void instantiateRealVisualisationTreeAndAddListeners(StructuralElementInstance sei) {
		Repository repo = createTestRepositoryContainingSei(sei);
		VisualisationTreeInstantiator instantiator = new VisualisationTreeInstantiator(treeManager, repo);
		instantiator.createVisualisationObjects();
		instantiator.attachVisualisationChangeListeners();
	}

	/**
	 * Creates a test repository and adds a given SEI as a root entity
	 * @param sei 
	 * @return repository
	 */
	private Repository createTestRepositoryContainingSei(StructuralElementInstance sei) {
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		repo.getRootEntities().add(sei);
		return repo;
	}
	
	@Test
	public final void testNoVisParentWithVisChild() {
		StructuralElementInstance seiParent = createSeiWithoutVisualisation();
		Shape shapeChild = createSimpleShape();
		StructuralElementInstance seiChild = createSeiWithVisualisationShape(shapeChild);
		
		seiParent.getChildren().add(seiChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);
		
		mockVisTreeManager.assertNumberOfShapes(1);
		mockVisTreeManager.assertShapeIsCreated(shapeChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeChild.id);
	}

	@Test
	public final void testNoVisParentWithTwoVisChildren() {
		StructuralElementInstance seiParent = createSeiWithoutVisualisation();
		Shape shapeChild1 = createSimpleShape();
		StructuralElementInstance seiChild1 = createSeiWithVisualisationShape(shapeChild1);
		Shape shapeChild2 = createSimpleShape();
		StructuralElementInstance seiChild2 = createSeiWithVisualisationShape(shapeChild2);
		
		seiParent.getChildren().add(seiChild1);
		seiParent.getChildren().add(seiChild2);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);
		
		mockVisTreeManager.assertNumberOfShapes(2);
		mockVisTreeManager.assertShapeIsCreated(shapeChild1);
		mockVisTreeManager.assertShapeIsCreated(shapeChild2);
		mockVisTreeManager.assertShapeHasNoParent(shapeChild1.id);
		mockVisTreeManager.assertShapeHasNoParent(shapeChild2.id);
	}

	@Test
	public final void testVisSeiWithVisAndNonVisChildren() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		StructuralElementInstance seiChild1 = createSeiWithoutVisualisation();
		Shape shapeChild2 = createSimpleShape();
		StructuralElementInstance seiChild2 = createSeiWithVisualisationShape(shapeChild2);
		
		seiParent.getChildren().add(seiChild1);
		seiParent.getChildren().add(seiChild2);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);
		
		mockVisTreeManager.assertNumberOfShapes(2);
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeChild2);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild2.id, shapeParent.id);
	}

	@Test
	public final void testVisSeiWithNonVisChildButVisGrandChild() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		StructuralElementInstance seiChild = createSeiWithoutVisualisation();
		Shape shapeGrandChild = createSimpleShape();
		StructuralElementInstance seiGrandChild = createSeiWithVisualisationShape(shapeGrandChild);
		
		seiParent.getChildren().add(seiChild);
		seiChild.getChildren().add(seiGrandChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);
		
		mockVisTreeManager.assertNumberOfShapes(2);
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeGrandChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeGrandChild.id, shapeParent.id);
	}

	@Test
	public final void testSeiWithTreeOfVisAndNonVisChildren() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		
		StructuralElementInstance seiChild1 = createSeiWithoutVisualisation();
		Shape shapeChild2 = createSimpleShape();
		StructuralElementInstance seiChild2 = createSeiWithVisualisationShape(shapeChild2);
		StructuralElementInstance seiChild3 = createSeiWithoutVisualisation();

		Shape shapeChild11 = createSimpleShape();
		StructuralElementInstance seiChild11 = createSeiWithVisualisationShape(shapeChild11);
		StructuralElementInstance seiChild31 = createSeiWithoutVisualisation();
		Shape shapeChild311 = createSimpleShape();
		StructuralElementInstance seiChild311 = createSeiWithVisualisationShape(shapeChild311);
		
		seiParent.getChildren().add(seiChild1);
		seiParent.getChildren().add(seiChild2);
		seiParent.getChildren().add(seiChild3);
		seiChild1.getChildren().add(seiChild11);
		seiChild3.getChildren().add(seiChild31);
		seiChild31.getChildren().add(seiChild311);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);
		
		//CHECKSTYLE:OFF
		mockVisTreeManager.assertNumberOfShapes(4);
		//CHECKSTYLE:ON
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeChild2);
		mockVisTreeManager.assertShapeIsCreated(shapeChild11);
		mockVisTreeManager.assertShapeIsCreated(shapeChild311);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild2.id, shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild11.id, shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild311.id, shapeParent.id);
	}

	@Test
	public final void testSeiEdited() {
		Shape originalShape = createSimpleShape();
		StructuralElementInstance sei = createSeiWithVisualisationShape(originalShape);

		instantiateVisualisationTreeAndAddListeners(sei);
		
		Shape modifiedShape = createShapeWithDifferentValues();
		
		updateSeiVisualisationParams(sei, modifiedShape);
		
		mockVisTreeManager.assertShapeIsCreated(modifiedShape);
		mockVisTreeManager.assertShapeWasEdited(originalShape.id);
	}

	@Test
	public final void testSeiEditedTwice() {
		Shape originalShape = createSimpleShape();
		StructuralElementInstance sei = createSeiWithVisualisationShape(originalShape);

		instantiateVisualisationTreeAndAddListeners(sei);
		
		Shape modifiedShape = createShapeWithDifferentValues();
		
		updateSeiVisualisationParams(sei, modifiedShape);
		updateSeiVisualisationParams(sei, originalShape);
		
		mockVisTreeManager.assertShapeIsCreated(originalShape);
		mockVisTreeManager.assertShapeWasEdited(originalShape.id);
	}

	@Test
	public final void testGeometryFileReload() {
		Shape shape = new Shape();
		shape.shape = VisualisationShape.GEOMETRY;
		String testFakePath = "TEST/FAKE/FILE";
		URI uriBefore = URI.createFileURI(testFakePath);
		shape.geometryFile = uriBefore;

		StructuralElementInstance sei = createSeiWithVisualisationShape(shape);

		instantiateVisualisationTreeAndAddListeners(sei);
		
		URI uriAfter = URI.createFileURI(testFakePath);
		shape.geometryFile = uriAfter;
		
		updateSeiVisualisationParams(sei, shape);
		
		mockVisTreeManager.assertShapeGeometryWasReloaded(shape.id);
	}
	
	@Test
	public final void testGeometryFileReloadDoesnotWorkForNonGeometryShape() {
		Shape shape = new Shape();
		shape.shape = VisualisationShape.BOX;
		String testFakePath = "TEST/FAKE/FILE";
		URI uriBefore = URI.createFileURI(testFakePath);
		shape.geometryFile = uriBefore;

		StructuralElementInstance sei = createSeiWithVisualisationShape(shape);

		instantiateVisualisationTreeAndAddListeners(sei);
		
		URI uriAfter = URI.createFileURI(testFakePath);
		shape.geometryFile = uriAfter;
		
		updateSeiVisualisationParams(sei, shape);
		
		mockVisTreeManager.assertShapeGeometryWasNotReloaded(shape.id);
	}
	
	@Test
	public final void addVisCategoryToRootSei() {
		StructuralElementInstance sei = createSeiWithoutVisualisation();
		
		instantiateVisualisationTreeAndAddListeners(sei);

		Shape shape = createSimpleShape();
		addVisualisationCategoryAssignmentWithShapeToSei(sei, shape);
		
		mockVisTreeManager.assertNumberOfShapes(1);
		mockVisTreeManager.assertShapeIsCreated(shape);
		mockVisTreeManager.assertShapeHasNoParent(shape.id);
	}

	@Test
	public final void addVisCategoryToParentSeiWithVisChild() {
		StructuralElementInstance seiParent = createSeiWithoutVisualisation();
		Shape shapeChild = createSimpleShape();
		StructuralElementInstance seiChild = createSeiWithVisualisationShape(shapeChild);
		seiParent.getChildren().add(seiChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		Shape shapeParent = createSimpleShape();
		addVisualisationCategoryAssignmentWithShapeToSei(seiParent, shapeParent);
		
		mockVisTreeManager.assertNumberOfShapes(2);
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild.id, shapeParent.id);
	}

	@Test
	public final void addVisCategoryToParentSeiWithNonVisChild() {
		StructuralElementInstance seiParent = createSeiWithoutVisualisation();
		StructuralElementInstance seiChild = createSeiWithoutVisualisation();
		seiParent.getChildren().add(seiChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		Shape shapeParent = createSimpleShape();
		addVisualisationCategoryAssignmentWithShapeToSei(seiParent, shapeParent);
		
		mockVisTreeManager.assertNumberOfShapes(1);
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
	}

	@Test
	public final void addVisCategoryToChildSeiWithVisParent() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		StructuralElementInstance seiChild = createSeiWithoutVisualisation();
		seiParent.getChildren().add(seiChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		Shape shapeChild = createSimpleShape();
		addVisualisationCategoryAssignmentWithShapeToSei(seiChild, shapeChild);
		
		mockVisTreeManager.assertNumberOfShapes(2);
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild.id, shapeParent.id);
	}

	@Test
	public final void addVisCategoryToChildSeiWithNonVisParent() {
		StructuralElementInstance seiParent = createSeiWithoutVisualisation();
		StructuralElementInstance seiChild = createSeiWithoutVisualisation();
		seiParent.getChildren().add(seiChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		Shape shapeChild = createSimpleShape();
		addVisualisationCategoryAssignmentWithShapeToSei(seiChild, shapeChild);
		
		mockVisTreeManager.assertNumberOfShapes(1);
		mockVisTreeManager.assertShapeIsCreated(shapeChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeChild.id);
	}
	
	@Test
	public final void addSeiAndThenVisCategory() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);

		instantiateVisualisationTreeAndAddListeners(seiParent);
		
		StructuralElementInstance seiChild = createSeiWithoutVisualisation();
		seiParent.getChildren().add(seiChild);
		
		Shape shapeChild = createSimpleShape();
		addVisualisationCategoryAssignmentWithShapeToSei(seiChild, shapeChild);
		
		mockVisTreeManager.assertNumberOfShapes(2);
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild.id, shapeParent.id);
	}

	@Test
	public final void addSeiWithExistingVisCategory() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);

		instantiateVisualisationTreeAndAddListeners(seiParent);
		
		StructuralElementInstance seiChild = createSeiWithoutVisualisation();
		
		Shape shapeChild = createSimpleShape();
		addVisualisationCategoryAssignmentWithShapeToSei(seiChild, shapeChild);

		seiParent.getChildren().add(seiChild);
		
		mockVisTreeManager.assertNumberOfShapes(2);
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild.id, shapeParent.id);
	}	
	@Test
	public final void addVisCategoryToNonVisSeiBetweenVisAndVisSeis() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		StructuralElementInstance seiChild = createSeiWithoutVisualisation();
		Shape shapeGrandChild = createSimpleShape();
		StructuralElementInstance seiGrandChild = createSeiWithVisualisationShape(shapeGrandChild);
		
		seiParent.getChildren().add(seiChild);
		seiChild.getChildren().add(seiGrandChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		Shape shapeChild = createSimpleShape();
		addVisualisationCategoryAssignmentWithShapeToSei(seiChild, shapeChild);
		
		//CHECKSTYLE:OFF
		mockVisTreeManager.assertNumberOfShapes(3);
		//CHECKSTYLE:ON
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeChild);
		mockVisTreeManager.assertShapeIsCreated(shapeGrandChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeChild.id, shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeGrandChild.id, shapeChild.id);
	}

	@Test
	public final void removeVisCategoryFromRootSei() {
		Shape shapeRoot = createSimpleShape();
		StructuralElementInstance seiRoot = createSeiWithVisualisationShape(shapeRoot);
		
		instantiateVisualisationTreeAndAddListeners(seiRoot);
		
		removeVisualisationCategoryAssignmentFromSei(seiRoot);
		
		mockVisTreeManager.assertNumberOfShapes(0);
	}

	@Test
	public final void removeVisCategoryFromParentSeiWithVisChild() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		Shape shapeChild = createSimpleShape();
		StructuralElementInstance seiChild = createSeiWithVisualisationShape(shapeChild);
		seiParent.getChildren().add(seiChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		removeVisualisationCategoryAssignmentFromSei(seiParent);
		
		mockVisTreeManager.assertNumberOfShapes(1);
		mockVisTreeManager.assertShapeIsCreated(shapeChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeChild.id);
	}

	@Test
	public final void removeVisCategoryFromParentSeiWithNonVisChild() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		StructuralElementInstance seiChild = createSeiWithoutVisualisation();
		seiParent.getChildren().add(seiChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		removeVisualisationCategoryAssignmentFromSei(seiParent);
		
		mockVisTreeManager.assertNumberOfShapes(0);
	}

	@Test
	public final void removeVisCategoryFromChildSeiWithVisParent() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		Shape shapeChild = createSimpleShape();
		StructuralElementInstance seiChild = createSeiWithVisualisationShape(shapeChild);
		seiParent.getChildren().add(seiChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		removeVisualisationCategoryAssignmentFromSei(seiChild);
		
		mockVisTreeManager.assertNumberOfShapes(1);
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
	}

	@Test
	public final void removeVisCategoryFromChildSeiWithNonVisParent() {
		StructuralElementInstance seiParent = createSeiWithoutVisualisation();
		Shape shapeChild = createSimpleShape();
		StructuralElementInstance seiChild = createSeiWithVisualisationShape(shapeChild);
		seiParent.getChildren().add(seiChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		removeVisualisationCategoryAssignmentFromSei(seiChild);
		
		mockVisTreeManager.assertNumberOfShapes(0);
	}

	@Test
	public final void removeSeiWithVisCategory() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		Shape shapeChild = createSimpleShape();
		StructuralElementInstance seiChild = createSeiWithVisualisationShape(shapeChild);
		seiParent.getChildren().add(seiChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		seiParent.getChildren().remove(seiChild);
		
		mockVisTreeManager.assertNodesRemovedWithSubtreeCount(1);
		mockVisTreeManager.assertNodeRemovedWithSubtree(shapeChild.id);
	}

	@Test
	public final void removeVisSeiWithChildren() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		Shape shapeChild = createSimpleShape();
		StructuralElementInstance seiChild = createSeiWithVisualisationShape(shapeChild);
		Shape shapeGrandChild = createSimpleShape();
		StructuralElementInstance seiGrandChild = createSeiWithVisualisationShape(shapeGrandChild);
		seiParent.getChildren().add(seiChild);
		seiChild.getChildren().add(seiGrandChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		seiParent.getChildren().remove(seiChild);
		
		mockVisTreeManager.assertNodesRemovedWithSubtreeCount(1);
		mockVisTreeManager.assertNodeRemovedWithSubtree(shapeChild.id);
	}

	@Test
	public final void removeNoVisSeiWithVisChildren() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		StructuralElementInstance seiChild = createSeiWithoutVisualisation();
		Shape shapeGrandChild1 = createSimpleShape();
		StructuralElementInstance seiGrandChild1 = createSeiWithVisualisationShape(shapeGrandChild1);
		Shape shapeGrandChild2 = createSimpleShape();
		StructuralElementInstance seiGrandChild2 = createSeiWithVisualisationShape(shapeGrandChild2);
		seiParent.getChildren().add(seiChild);
		seiChild.getChildren().add(seiGrandChild1);
		seiChild.getChildren().add(seiGrandChild2);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		seiParent.getChildren().remove(seiChild);
		
		mockVisTreeManager.assertNodesRemovedWithSubtreeCount(2);
		mockVisTreeManager.assertNodeRemovedWithSubtree(shapeGrandChild1.id);
		mockVisTreeManager.assertNodeRemovedWithSubtree(shapeGrandChild2.id);
	}
	
	@Test
	public final void removeVisCategoryFromRootWithVisChildren() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		Shape shapeChild1 = createSimpleShape();
		StructuralElementInstance seiChild1 = createSeiWithVisualisationShape(shapeChild1);
		Shape shapeChild2 = createSimpleShape();
		StructuralElementInstance seiChild2 = createSeiWithVisualisationShape(shapeChild2);
		seiParent.getChildren().add(seiChild1);
		seiParent.getChildren().add(seiChild2);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		removeVisualisationCategoryAssignmentFromSei(seiParent);
		
		mockVisTreeManager.assertNumberOfShapes(2);
		mockVisTreeManager.assertShapeIsCreated(shapeChild1);
		mockVisTreeManager.assertShapeHasNoParent(shapeChild1.id);
		mockVisTreeManager.assertShapeIsCreated(shapeChild2);
		mockVisTreeManager.assertShapeHasNoParent(shapeChild2.id);
	}

	@Test
	public final void removeVisCategoryFromRootWithVisChildAndVisGrandchild() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		Shape shapeChild = createSimpleShape();
		StructuralElementInstance seiChild = createSeiWithVisualisationShape(shapeChild);
		Shape shapeGrandChild = createSimpleShape();
		StructuralElementInstance seiGrandChild = createSeiWithVisualisationShape(shapeGrandChild);
		seiParent.getChildren().add(seiChild);
		seiChild.getChildren().add(seiGrandChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		removeVisualisationCategoryAssignmentFromSei(seiParent);
		
		mockVisTreeManager.assertNumberOfShapes(2);
		mockVisTreeManager.assertShapeIsCreated(shapeChild);
		mockVisTreeManager.assertShapeIsCreated(shapeGrandChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeChild.id);
		mockVisTreeManager.assertShapeHasParent(shapeGrandChild.id, shapeChild.id);
	}
	
	@Test
	public final void removeVisCategoryFromSeiWithVisChildrenAndVisParent() {
		Shape shapeParent = createSimpleShape();
		StructuralElementInstance seiParent = createSeiWithVisualisationShape(shapeParent);
		Shape shapeChild = createSimpleShape();
		StructuralElementInstance seiChild = createSeiWithVisualisationShape(shapeChild);
		Shape shapeGrandChild = createSimpleShape();
		StructuralElementInstance seiGrandChild = createSeiWithVisualisationShape(shapeGrandChild);
		seiParent.getChildren().add(seiChild);
		seiChild.getChildren().add(seiGrandChild);
		
		instantiateVisualisationTreeAndAddListeners(seiParent);

		removeVisualisationCategoryAssignmentFromSei(seiChild);
		
		mockVisTreeManager.assertNumberOfShapes(2);
		mockVisTreeManager.assertShapeIsCreated(shapeParent);
		mockVisTreeManager.assertShapeIsCreated(shapeGrandChild);
		mockVisTreeManager.assertShapeHasNoParent(shapeParent.id);
		mockVisTreeManager.assertShapeHasParent(shapeGrandChild.id, shapeParent.id);
	}
	
	/**
	 * Update visualisation params in a given StructuralElementInstance from a given Shape
	 * @param sei 
	 * @param modifiedShape 
	 */
	private void updateSeiVisualisationParams(StructuralElementInstance sei, Shape modifiedShape) {
		modifiedShape.id = sei.getUuid().toString();
		
		BeanStructuralElementInstance beanSei = new BeanStructuralElementInstance(sei);
		Visualisation visualisation = beanSei.getFirst(Visualisation.class);
		visualisation.readFromShape(modifiedShape);
	}

	/**
	 * Create a StructuralElementInstance with Visualisation category with values from a given Shape
	 * @param shape 
	 * @return new StructuralElementInstance
	 */
	private StructuralElementInstance createSeiWithVisualisationShape(Shape shape) {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		
		addVisualisationCategoryAssignmentWithShapeToSei(sei, shape);
		return sei;
	}

	/**
	 * @param sei 
	 * @param shape 
	 */
	private void addVisualisationCategoryAssignmentWithShapeToSei(StructuralElementInstance sei, Shape shape) {
		shape.id = sei.getUuid().toString();
		BeanStructuralElementInstance beanSei = new BeanStructuralElementInstance(sei);
		
		Visualisation visualisation = new Visualisation(visualisationConcept);
		visualisation.readFromShape(shape);
		beanSei.add(visualisation);
	}

	/**
	 * @param sei 
	 */
	private void removeVisualisationCategoryAssignmentFromSei(StructuralElementInstance sei) {
		List<CategoryAssignment> visCategoryAssignments = CategoryAssignmentHelper.getCategoryAssignmentsOfType(sei, visCategoryQualifiedName);
		assertEquals(1, visCategoryAssignments.size());

		sei.getCategoryAssignments().remove(visCategoryAssignments.get(0));
	}

	/**
	 * 
	 * @param sei 
	 * @return Created Shape
	 */
	private Shape getShapeFromSei(StructuralElementInstance sei) {
		Shape shape = new Shape();
		
		BeanStructuralElementInstance beanSei = new BeanStructuralElementInstance(sei);
		Visualisation visualisation = beanSei.getFirst(Visualisation.class);
		visualisation.writeToShape(shape);
		
		shape.id = sei.getUuid().toString();
		
		return shape;
	}

	/**
	 * Creates a StructuralElementInstance without visualisation category assignment
	 * @return new StructuralElementInstance
	 */
	private StructuralElementInstance createSeiWithoutVisualisation() {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		return sei;
	}

	/**
	 * @return Shape with some predefined values
	 */
	private Shape createSimpleShape() {
		Shape shape = new Shape();

		//CHECKSTYLE:OFF
		shape.id = "To be filled with SEI UUID after SEI is created";
		shape.sizeX = 10;
		shape.sizeY = 20;
		shape.sizeZ = 30;
		shape.radius = 10;
		shape.positionX = 50;
		shape.positionY = 60;
		shape.positionZ = 70;
		shape.rotationX = 0;
		shape.rotationY = 0;
		shape.rotationZ = 0;
		shape.color = 0x0000FF;
		shape.transparency = 0.5f;
		shape.shape = VisualisationShape.BOX;
		//CHECKSTYLE:ON

		return shape;
	}
	
	/**
	 * @return Shape with some predefined values
	 */
	private Shape createShapeWithDifferentValues() {
		Shape shape = new Shape();

		//CHECKSTYLE:OFF
		shape.id = "To be filled with SEI UUID after SEI is created";
		shape.sizeX = 0;
		shape.sizeY = 5.5f;
		shape.sizeZ = 20;
		shape.radius = 20;
		shape.positionX = 5;
		shape.positionY = -6;
		shape.positionZ = 0;
		shape.rotationX = -50;
		shape.rotationY = 360;
		shape.rotationZ = 720;
		shape.color = 0x00FF00;
		shape.transparency = 0.3f;
		shape.shape = VisualisationShape.CONE;
		//CHECKSTYLE:ON

		return shape;
	}

	@Test
	public final void testShapeIdEqualsToSeiId() {
		Shape box = createShapeWithDifferentValues();
		StructuralElementInstance sei = createSeiWithVisualisationShape(box);
		assertEquals(sei.getUuid().toString(), box.id);
	}
	
	@Test
	public final void testEditSimpleShapeExternally() throws CoreException {
		Shape cone = createShapeWithDifferentValues();
		StructuralElementInstance sei = createSeiWithVisualisationShapeInResource(cone);
		instantiateRealVisualisationTreeAndAddListeners(sei);
		TreeManager secondaryTreeManager = new TreeManager();
		secondaryTreeManager.createShape(cone);
		
		SceneGraph treeManagerSceneGraph = treeManager.getSceneGraph().build();
		SceneGraph secondaryTreeManagerSceneGraph = secondaryTreeManager.getSceneGraph().build();
		assertEquals(secondaryTreeManagerSceneGraph, treeManagerSceneGraph);
		
		cone.positionY = 2;
		cone.sizeY = 0;
		cone.sizeZ = 0;
		
		secondaryTreeManager.editShape(cone);
		
		secondaryTreeManagerSceneGraph = secondaryTreeManager.getSceneGraph().build();
		VisualisationMessage.Builder visualisationMessage = VisualisationMessage.newBuilder();
		visualisationMessage.setSceneGraph(secondaryTreeManagerSceneGraph);
		treeManager.update(visualisationMessage.build());
		
		Shape newCone = getShapeFromSei(sei);
		assertTrue(cone.equals(newCone));
	}
	
	@Test
	public final void testEditShapeWithParentExternally() throws CoreException {
		Shape parent = createSimpleShape();
		Shape child = createShapeWithDifferentValues();
		StructuralElementInstance parentSei = createSeiWithVisualisationShapeInResource(parent);
		StructuralElementInstance childSei = createSeiWithVisualisationShapeInResource(child);
		parentSei.getChildren().add(childSei);
		instantiateRealVisualisationTreeAndAddListeners(parentSei);
		TreeManager secondaryTreeManager = new TreeManager();
		
		SceneGraph treeManagerSceneGraph = treeManager.getSceneGraph().build();
		VisualisationMessage.Builder protobufMessage = VisualisationMessage.newBuilder();
		protobufMessage.setSceneGraph(treeManagerSceneGraph);
		secondaryTreeManager.update(protobufMessage.build());
		SceneGraph secondaryTreeManagerSceneGraph = secondaryTreeManager.getSceneGraph().build();
		assertEquals(secondaryTreeManagerSceneGraph, treeManagerSceneGraph);
		
		child.positionY = 2;
		child.sizeY = 0;
		child.sizeZ = 0;
		
		secondaryTreeManager.editShape(child);
		secondaryTreeManagerSceneGraph = secondaryTreeManager.getSceneGraph().build();
		protobufMessage = VisualisationMessage.newBuilder();
		protobufMessage.setSceneGraph(secondaryTreeManagerSceneGraph);
		treeManager.update(protobufMessage.build());
		
		Shape newParent = getShapeFromSei(parentSei);
		Shape newChild = getShapeFromSei(childSei);
		
		assertTrue(newParent.equals(parent));
		assertTrue(newChild.equals(child));
	}

	/**
	 * Creates a SEI and a resource for it and puts SEI into the resource
	 * @param shape 
	 * @return sei
	 * @throws CoreException 
	 */
	private StructuralElementInstance createSeiWithVisualisationShapeInResource(Shape shape) throws CoreException {
		if (project == null) {
			setUpProject();
		}
		StructuralElementInstance sei = createSeiWithVisualisationShape(shape);
		Resource resource = virSatProjectResourceSet.getStructuralElementInstanceResource(sei);
		resource.eSetDeliver(false);
		resource.getContents().add(sei);
		resource.eSetDeliver(true);
		return sei;
	}

	/**
	 * Creates a project and a resource set
	 * @throws CoreException 
	 */
	private void setUpProject() throws CoreException {
		UserRegistry.getInstance().setSuperUser(true);
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();

		project = wsRoot.getProject("VisualisationTestProject");
		if (project.exists()) {
			project.delete(IResource.ALWAYS_DELETE_PROJECT_CONTENT, null);
		}
		project.create(null);
		project.open(null);
		
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(project);
		projectCommons.createProjectStructure(null);
		
		virSatProjectResourceSet = VirSatResourceSet.getResourceSet(project, false);
	}
	

	@Test
	public void testColorDelta() {
		Shape shape = createSimpleShape();
		
		StructuralElementInstance sei = createSeiWithVisualisationShape(shape);
		
		VisualisationDeltaModel deltaModel = new VisualisationDeltaModel();
		ColorDelta colorDelta = new ColorDelta();
		int newColor = 1;
		colorDelta.shapeId = sei.getUuid().toString();
		colorDelta.newColor = newColor;
		deltaModel.colorDeltas.put(colorDelta.shapeId, colorDelta);

		VisualisationTreeInstantiator instantiator = new VisualisationTreeInstantiator(mockVisTreeManager, createTestRepositoryContainingSei(sei), deltaModel);
		instantiator.createVisualisationObjects();
		
		assertEquals(newColor, mockVisTreeManager.createdShapes.get(sei.getUuid().toString()).color);
	}

	@Test
	public void testGhostShapesDelta() {
		Shape shape = createSimpleShape();
		StructuralElementInstance sei = createSeiWithVisualisationShape(shape);
		
		VisualisationDeltaModel deltaModel = new VisualisationDeltaModel();
		GhostShapeDelta ghost1 = new GhostShapeDelta();
		ghost1.parentId = sei.getUuid().toString();
		ghost1.shape = createShapeWithDifferentValues();
		ghost1.shape.id = "ghost1";
		deltaModel.ghostShapeDeltas.add(ghost1);
		GhostShapeDelta ghost2 = new GhostShapeDelta();
		ghost2.parentId = ghost1.shape.id;
		ghost2.shape = createSimpleShape();
		ghost2.shape.id = "ghost2";
		deltaModel.ghostShapeDeltas.add(ghost2);

		VisualisationTreeInstantiator instantiator = new VisualisationTreeInstantiator(mockVisTreeManager, createTestRepositoryContainingSei(sei), deltaModel);
		instantiator.createVisualisationObjects();
		
		mockVisTreeManager.assertNumberOfShapes(1 + 2); // one real plus two ghosts
		for (Shape ghostShape : mockVisTreeManager.createdShapes.values()) {
			assertTrue(ghostShape.id.contains(ghost1.shape.id) || ghostShape.id.contains(ghost2.shape.id) || ghostShape.id.equals(sei.getUuid().toString()));
			if (ghostShape.id.contains(ghost1.shape.id)) {
				ghost1.shape.id = ghostShape.id;
				mockVisTreeManager.assertShapeIsCreated(ghost1.shape);
				mockVisTreeManager.assertShapeHasParent(ghost1.shape.id, sei.getUuid().toString());
			} else if (ghostShape.id.contains(ghost2.shape.id)) {
				ghost2.shape.id = ghostShape.id;
				mockVisTreeManager.assertShapeIsCreated(ghost2.shape);
			}
		}
		mockVisTreeManager.assertShapeHasParent(ghost2.shape.id, ghost1.shape.id);
	}

	@Test
	public final void testCloneShape() {
		Shape shape = createSimpleShape();
		StructuralElementInstance sei = createSeiWithVisualisationShape(shape);

		VisualisationDeltaModel deltaModel = new VisualisationDeltaModel();
		CloneShapeDelta clone1 = new CloneShapeDelta();
		clone1.shape = new Shape(shape);
		clone1.shape.rotationX = 1;
		clone1.shape.positionY = 1;
		clone1.shape.shape = VisualisationShape.SPHERE;
		deltaModel.cloneShapeDeltas.add(clone1);
		
		VisualisationTreeInstantiator instantiator = new VisualisationTreeInstantiator(mockVisTreeManager, createTestRepositoryContainingSei(sei), deltaModel);
		instantiator.createVisualisationObjects();
		
		mockVisTreeManager.assertNumberOfShapes(2);
		for (Shape cloneShape : mockVisTreeManager.createdShapes.values()) {
			if (!cloneShape.id.equals(sei.getUuid().toString())) {
				clone1.shape.id = cloneShape.id;
				mockVisTreeManager.assertShapeIsCreated(clone1.shape);
			}
		}
	}
}
