/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts.registry;



import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

/**
 * This test case will handle the copying of concepts into the repository.
 * In particular on concepts referencing each other the copying has to
 * follow some smart rules
 * @author fisc_ph
 *
 */
public class ActiveConceptConfigurationElementTest {

	private EditingDomain ourEditingDomain;
	
	private static final String TEST_CONCEPT_NAME = "de.dlr.sc.virsat.model.extension.tests";
	private static final String TEST_CONCEPT_URI = "de.dlr.sc.virsat.model.extension.tests/concept/concept.xmi";
	
	@Before
	public void setUp() throws Exception {
		// Setting up a EditingDomain for our Test and a Basic Command Stack which is used to execute the commands on the model.
		//and thus invokes the RoleManagementChecker!
				
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		BasicCommandStack fulkaCommandStack = new BasicCommandStack();
		ourEditingDomain = new AdapterFactoryEditingDomain(adapterFactory, fulkaCommandStack, new HashMap<Resource, Boolean>());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Rule
    public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testCreateAddActiveConceptCommand() {
	    ResourceSet resSetConceptSource = new ResourceSetImpl();
	    Resource resourceAConcept = resSetConceptSource.createResource(URI.createURI("conceptTest/conceptASource.xmi"));
	    Resource resourceBConcept = resSetConceptSource.createResource(URI.createURI("conceptTest/conceptBSource.xmi"));

	    ResourceSet resSetRepositoryTarget = new ResourceSetImpl();
	    Resource resourceRepository = resSetRepositoryTarget.createResource(URI.createURI("conceptTest/repositoryTarget.xmi"));

	    Concept conceptSourceA = ConceptsFactory.eINSTANCE.createConcept();
	    Concept conceptSourceB = ConceptsFactory.eINSTANCE.createConcept();
	    
	    resourceAConcept.getContents().add(conceptSourceA);
	    resourceBConcept.getContents().add(conceptSourceB);

	    StructuralElement seSourceA = StructuralFactory.eINSTANCE.createStructuralElement();
	    StructuralElement seSourceB = StructuralFactory.eINSTANCE.createStructuralElement();
	    seSourceB.getApplicableFor().add(seSourceA);
	    
	    conceptSourceA.getStructuralElements().add(seSourceA);
	    conceptSourceB.getStructuralElements().add(seSourceB);
	    
	    Repository repository = DVLMFactory.eINSTANCE.createRepository();
	    resourceRepository.getContents().add(repository);
	    
		// Adding Concept B before A will fail
		expectedException.expect(RuntimeException.class);
		Command failCommand = ActiveConceptConfigurationElement.createCopyConceptToRepository(ourEditingDomain, conceptSourceB, repository);
		failCommand.execute();
		assertTrue("ActiveConcepts are not present", repository.getActiveConcepts().isEmpty());
		
		// Concept A should be added without any trouble
		expectedException = ExpectedException.none();
		Command addConceptA = ActiveConceptConfigurationElement.createCopyConceptToRepository(ourEditingDomain, conceptSourceA, repository);
		addConceptA.execute();
		
		StructuralElement targetConceptASe = repository.getActiveConcepts().get(0).getStructuralElements().get(0);
		assertNotSame("Should not be the same object, should be copied", seSourceA, targetConceptASe);
		
		// Now copy concept B
		Command addConceptB = ActiveConceptConfigurationElement.createCopyConceptToRepository(ourEditingDomain, conceptSourceB, repository);
		addConceptB.execute();
	
		StructuralElement targetConceptBSe = repository.getActiveConcepts().get(1).getStructuralElements().get(0);
		assertNotSame("Should not be the same object, should be copied", seSourceB, targetConceptBSe);
		assertThat("The Copied SE in ConceptB is now pointing to the copied one in ConceptA", targetConceptBSe.getApplicableFor(), hasItem(targetConceptASe));
		assertThat("The Copied SE in ConceptB is not pointing to the original one in ConceptA", targetConceptBSe.getApplicableFor(), not(hasItem(seSourceA)));
	}
	
	@Test
	public void testCreateAddActiveConcept() {
		// SImple test of adding a concept without command
		// most of the cases are the same as with adding a Concept by command, thus this test case intends
		// to just execute the code once and check for some primitive result.
	    ResourceSet resSetConceptSource = new ResourceSetImpl();
	    Resource resourceAConcept = resSetConceptSource.createResource(URI.createURI("conceptTest/conceptASource.xmi"));

	    ResourceSet resSetRepositoryTarget = new ResourceSetImpl();
	    Resource resourceRepository = resSetRepositoryTarget.createResource(URI.createURI("conceptTest/repositoryTarget.xmi"));

	    Concept conceptSourceA = ConceptsFactory.eINSTANCE.createConcept();
	    resourceAConcept.getContents().add(conceptSourceA);
	    StructuralElement seSourceA = StructuralFactory.eINSTANCE.createStructuralElement();
	    conceptSourceA.getStructuralElements().add(seSourceA);
	    
	    Repository repository = DVLMFactory.eINSTANCE.createRepository();
	    resourceRepository.getContents().add(repository);
		
		// Concept A should be added without any trouble
		expectedException = ExpectedException.none();
		Concept activeConcept = ActiveConceptConfigurationElement.createCopyConceptToRepository(conceptSourceA, repository);
		
		assertNotSame("Should not be the same object, should be copied", activeConcept, conceptSourceA);
	}
	
	@Test
	public void testLoadConceptFromPlugin() {
		
		Concept testConcept = ActiveConceptConfigurationElement.loadConceptFromPlugin(TEST_CONCEPT_URI);
		assertNotNull("Test concept should exist and not be null", testConcept);
		assertEquals(TEST_CONCEPT_NAME, testConcept.getName());
		
		assertNull("Should not result in exception and be null", ActiveConceptConfigurationElement.loadConceptFromPlugin(null));
		
	}
	
	@Test
	public void testLoadConceptViaConceptName() {
		
		Concept testConcept = ActiveConceptConfigurationElement.loadConceptViaConceptName(TEST_CONCEPT_NAME);
		assertNotNull("Test concept should exist and not be null", testConcept);
		assertEquals(TEST_CONCEPT_NAME, testConcept.getName());
		
	}
	
	@Test
	public void testLoadConceptDMFResourceViaConceptName() {
		
		Resource dmfResource = ActiveConceptConfigurationElement.loadConceptDMFResourceViaConceptName(TEST_CONCEPT_NAME);
		assertNotNull("Test resource should exist and not be null", dmfResource);
		assertNotNull("Test resource should have content", dmfResource.getContents().get(0));
		assertTrue("Root element should be an the DMF EPackage", dmfResource.getContents().get(0) instanceof EPackage);
		
	}
}
