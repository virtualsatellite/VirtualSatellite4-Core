/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.marker;

import static org.junit.Assert.assertTrue;

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
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Class to get the base structure of a test case 
 * @author lobe_el
 *
 */
public class ATestCase {

	protected Repository repo;
	protected Concept concept;

	protected StructuralElement se;
	protected Category category;
	protected IntProperty ip;
	
	protected StructuralElementInstance seiContaining;
	protected StructuralElementInstance seiContained;
	
	protected CategoryAssignment caOfContainingSei;
	protected CategoryAssignment ca1OfContainedSei;
	protected CategoryAssignment ca2OfContainedSei;
	
	protected ValuePropertyInstance vpiOfCaOfContainingSei;
	protected ValuePropertyInstance vpiOfCa1OfContainedSei;
	protected ValuePropertyInstance vpiOfCa2OfContainedSei;
	
	protected CategoryAssignment caEq;
	protected EquationSection eqSectionOfCaEqOfContainingSei;
	
	protected IProject project;
	protected VirSatResourceSet resSet;
	
	protected IResource fileRepo;
	protected IResource fileSeiContaining;
	protected IResource fileSeiContained;
	
	/**
	 * 	repo 	- concept
	 * 	 |
	 * 	 |______ seiContaining	- caOfContainingSei			- vpiOfCaOfContainingSei
	 * 				|			- eqSectionOfContainingSei
	 * 				|
	 * 				|_______ seiContained		- ca1OfContainedSei		- vpiOfCa1OfContainedSei
	 * 											- ca2OfContainedSei		- vpiOfCa2OfContainedSei
	 * 		
	 */
	
	@Before
	public void setUp() {
		
		repo = DVLMFactory.eINSTANCE.createRepository();
		
		// Concept Stuff
		concept = ConceptsFactory.eINSTANCE.createConcept();
		repo.getActiveConcepts().add(concept);
		
		se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsApplicableForAll(true);
		se.setName("SomeStucturalElement");
		concept.getStructuralElements().add(se);
		
		category = CategoriesFactory.eINSTANCE.createCategory();
		category.setName("SomeCategory");
		category.getApplicableFor().add(se);
		concept.getCategories().add(category);
		
		ip = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		ip.setName("SomeProperty");
		category.getProperties().add(ip);
		
		// Instances
		seiContaining = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiContaining.setName("SEIContainingAnotherSEI");
		seiContaining.setType(se);
		repo.getRootEntities().add(seiContaining);

		caOfContainingSei = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		caOfContainingSei.setType(category);
		caOfContainingSei.setName("CAofTheSEIContainingAnotherSEI");
		seiContaining.getCategoryAssignments().add(caOfContainingSei);

		vpiOfCaOfContainingSei = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		vpiOfCaOfContainingSei.setType(ip);
		caOfContainingSei.getPropertyInstances().add(vpiOfCaOfContainingSei);

		seiContained = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiContained.setName("SEIContainedByAnotherSEI");
		seiContained.setType(se);
		seiContaining.getChildren().add(seiContained);
		
		ca1OfContainedSei = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca1OfContainedSei.setType(category);
		ca1OfContainedSei.setName("FirstCAofTheSEIContainedByAnotherSEI");
		seiContained.getCategoryAssignments().add(ca1OfContainedSei);

		vpiOfCa1OfContainedSei = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		vpiOfCa1OfContainedSei.setType(ip);
		ca1OfContainedSei.getPropertyInstances().add(vpiOfCa1OfContainedSei);
		
		ca2OfContainedSei = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca2OfContainedSei.setType(category);
		ca2OfContainedSei.setName("SecondCAofTheSEIContainedByAnotherSEI");
		seiContained.getCategoryAssignments().add(ca2OfContainedSei);
		
		vpiOfCa2OfContainedSei = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		vpiOfCa2OfContainedSei.setType(ip);
		ca2OfContainedSei.getPropertyInstances().add(vpiOfCa2OfContainedSei);
		
		caEq = new CategoryInstantiator().generateInstance(category, "Some Ca");
		eqSectionOfCaEqOfContainingSei = CalculationFactory.eINSTANCE.createEquationSection();
		caEq.setEquationSection(eqSectionOfCaEqOfContainingSei);
		seiContaining.getCategoryAssignments().add(caEq);
		
		// Resources and Files Stuff
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IWorkspaceDescription wsd = ResourcesPlugin.getWorkspace().getDescription();
		wsd.setAutoBuilding(false);
		
		try {
			ResourcesPlugin.getWorkspace().setDescription(wsd);
			wsRoot.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			
			project = wsRoot.getProject("VirSatProblemMarkerHelperTest");
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
			fileSeiContaining = projectCommons.getStructuralElementInstanceFile(seiContaining);
			fileSeiContained = projectCommons.getStructuralElementInstanceFile(seiContained);
			
			Resource resRepo = resSet.getRepositoryResource();
			Resource resSeiContaining = resSet.getStructuralElementInstanceResource(seiContaining);
			Resource resSeiContained = resSet.getStructuralElementInstanceResource(seiContained);
			
			resRepo.getContents().clear();
			resSeiContaining.getContents().clear();
			resSeiContained.getContents().clear();
			
			resRepo.getContents().add(repo);
			resSeiContaining.getContents().add(seiContaining);
			resSeiContained.getContents().add(seiContained);
			
			resRepo.save(Collections.EMPTY_MAP);
			resSeiContaining.save(Collections.EMPTY_MAP);
			resSeiContained.save(Collections.EMPTY_MAP);
		} catch (Exception e) {
			assertTrue("Got an unexpected exception in Set Up of VirSatProblemMarkerHelperTest", false);
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		try {
			resSet.getResources().clear();
			project.delete(true, null);
		} catch (Exception e) {
			assertTrue("Got an unexpected exception in Tear Down of VirSatProblemMarkerHelperTest", false);
			e.printStackTrace();
		}
	}
}
