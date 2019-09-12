/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.test;

import java.util.Collections;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.Before;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Abstract test Class for validators, providing some Test Data + Resources
 * @author fisc_ph
 *
 */
public abstract class ABuilderTest {

	protected Repository repo;
	protected Concept concept;

	protected StructuralElement se;
	protected Category category;
	protected IntProperty ip;
	
	
	protected StructuralElementInstance seiEdSc;
	protected StructuralElementInstance seiEdRw;
	protected StructuralElementInstance seiEdObc;
	
	
	protected CategoryAssignment caIftMil;
	protected CategoryAssignment caIftCan;
	
	protected ValuePropertyInstance vpi;

	protected IProject project;
	protected VirSatResourceSet resSet;
	
	protected IResource fileRepo;
	
	protected IResource fileSc;
	protected IResource fileRw;
	protected IResource fileObc;
	
	/**
	 * Builds the PT Test Data
	 */
	private void buildPT() {
		seiEdSc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEdRw = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEdObc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiEdSc.setName("edSc");
		seiEdRw.setName("edRw");
		seiEdObc.setName("edObc");

		seiEdSc.setType(se);
		seiEdRw.setType(se);
		seiEdObc.setType(se);

		seiEdSc.getChildren().add(seiEdRw);
		seiEdSc.getChildren().add(seiEdObc);
	}

	
	@Before
	public void setUp() throws Exception {
		repo = DVLMFactory.eINSTANCE.createRepository();
		
		// Build StructuralElement concept for this test. will consist of Definitions and Configurations
		se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsApplicableForAll(true);
		se.setName("Definition");
		
		// Now construct the instances for the StructuralElements for the ProductTree
		buildPT();

		ip = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		ip.setName("SomeProperty");
		
		vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		vpi.setType(ip);
		
		// Build up the InterfaceTypes
		category = CategoriesFactory.eINSTANCE.createCategory();
		category.setName("InterfaceType");
		category.getApplicableFor().add(se);
		category.getProperties().add(ip);

		// Build up the InterfaceTypes
		caIftMil = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		caIftMil.setType(category);
		caIftMil.setName("iftMil");
		caIftMil.getPropertyInstances().add(vpi);
		seiEdSc.getCategoryAssignments().add(caIftMil);
		
		caIftCan = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		caIftCan.setType(category);
		caIftCan.setName("iftCan");
		seiEdSc.getCategoryAssignments().add(caIftCan);
		
		concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.getStructuralElements().add(se);
		concept.getCategories().add(category);
		
		repo = DVLMFactory.eINSTANCE.createRepository();
		repo.getActiveConcepts().add(concept);
		repo.getRootEntities().add(seiEdSc);

		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IWorkspaceDescription wsd = ResourcesPlugin.getWorkspace().getDescription();
		wsd.setAutoBuilding(false);
		ResourcesPlugin.getWorkspace().setDescription(wsd);
		wsRoot.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		
		project = wsRoot.getProject("builderValidatorTest");
		if (project.exists()) {
			project.delete(true, null);
		}
		
		project.create(null);
		project.open(null);

		resSet = VirSatResourceSet.createUnmanagedResourceSet(project);
		resSet.getResources().clear();
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(project);
		projectCommons.createProjectStructure(null);
		
		fileRepo = projectCommons.getRepositoryFile();
		fileSc = projectCommons.getStructuralElementInstanceFile(seiEdSc);
		fileRw = projectCommons.getStructuralElementInstanceFile(seiEdRw);
		fileObc = projectCommons.getStructuralElementInstanceFile(seiEdObc);
		
		Resource resRepo = resSet.getRepositoryResource();
		Resource resSc = resSet.getStructuralElementInstanceResource(seiEdSc);
		Resource resRw = resSet.getStructuralElementInstanceResource(seiEdRw);
		Resource resObc = resSet.getStructuralElementInstanceResource(seiEdObc);

		resRepo.getContents().clear();
		resSc.getContents().clear();
		resRw.getContents().clear();
		resObc.getContents().clear();

		resRepo.getContents().add(repo);
		resSc.getContents().add(seiEdSc);
		resRw.getContents().add(seiEdRw);
		resObc.getContents().add(seiEdObc);
		
		resRepo.save(Collections.EMPTY_MAP);
		resSc.save(Collections.EMPTY_MAP);
		resRw.save(Collections.EMPTY_MAP);
		resObc.save(Collections.EMPTY_MAP);
	}

	@After
	public void tearDown() throws Exception {
		resSet.getResources().clear();
		project.delete(true, null);
	}
}
