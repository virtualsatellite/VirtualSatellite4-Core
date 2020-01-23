/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.provider;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * This class tests the additional DVLM functionality of the RepositoryItemProvider
 * @author lobe_el
 *
 */
public class DVLMRepositoryItemProviderTest {

	ComposedAdapterFactory adapterFactory;
	private EditingDomain editingDomain;
	
	public static final String TEST_MODEL_EXTENSION = "repositoryItemProviderTest";
	
	@Before
	public void setup() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		BasicCommandStack commandStack = new BasicCommandStack();
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
	}
	
	@After
	public void tearDown() {
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetChildren() throws IOException {
	    StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
	    se.setIsApplicableForAll(true);
		se.setName("Test_StructuralElement");

		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance sei2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei1.setName("Test_StructuralElementInstance_1");
		sei2.setName("Test_StructuralElementInstance_2");
		sei1.setType(se);
		sei2.setType(se); 
	    
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.setName("Test_Concept");
		concept.getStructuralElements().add(se);
		
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		repo.getActiveConcepts().add(concept);
		repo.getRootEntities().add(sei1);
		repo.getRootEntities().add(sei2);
		
		DVLMRepositoryItemProvider rip = new DVLMRepositoryItemProvider(adapterFactory);
		
		//Copied from DVLMResourceItemProviderTest 
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put(TEST_MODEL_EXTENSION, new XMIResourceFactoryImpl());
	    
	    ResourceSet resSet = editingDomain.getResourceSet();
	    Resource resourceSei1 = resSet.createResource(URI.createURI("repositoryItemProviderTest/ResourceSei1." + TEST_MODEL_EXTENSION));
	    Resource resourceSei2 = resSet.createResource(URI.createURI("repositoryItemProviderTest/ResourceSei2." + TEST_MODEL_EXTENSION));
	    Resource resourceRepo = resSet.createResource(URI.createURI("repositoryItemProviderTest/ResourceRepo." + TEST_MODEL_EXTENSION));
		
	    resourceSei1.getContents().add(sei1);
	    resourceSei2.getContents().add(sei2);
	    resourceRepo.getContents().add(repo);
	    
	    assertEquals("Make sure the StructuralElement is well contained in the resource", resourceSei1, sei1.eResource());
	    assertEquals("Make sure the StructuralElement is well contained in the resource", resourceSei2, sei2.eResource());
	    assertEquals("Make sure the Repository is well contained in the resource", resourceRepo, repo.eResource());
		
		Collection<Object> children = (Collection<Object>) rip.getChildren(repo);
	    
		assertThat("The Children Collection contains the correct elements", children, hasItems(concept, sei1, sei2));
		
		resourceSei1.delete(null);
		
		children = (Collection<Object>) rip.getChildren(repo);
		    
		assertThat("The Children Collection contains the correct elements", children, hasItems(concept, sei2));
		assertThat("The Children Collection contains the correct elements", children, not(hasItem(sei1)));
			
	}
	
}
