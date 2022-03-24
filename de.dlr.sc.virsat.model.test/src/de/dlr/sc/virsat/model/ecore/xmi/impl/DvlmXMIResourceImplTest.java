/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ecore.xmi.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;

/**
 * TestCases for dynamic EMF usage of Resource.
 * Still need to be implemented but not yet clear how to correctly use dynamic EMF
 * @author fisc_ph
 *
 */
public class DvlmXMIResourceImplTest {

	@Before
	public void setUp() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl(); 
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
	
		URI uri = URI.createPlatformPluginURI("de.dlr.sc.virsat.model/model/dvlm.ecore", true);
		Resource dvlmEcore = resourceSet.getResource(uri, true);
		dvlmPackage = (EPackage) dvlmEcore.getContents().get(0);
	}

	private EPackage dvlmPackage;
	
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Some helper method to retrieve the correct objects from our Ecore model
	 * @param rootObject the root object from where to start looking for the requested one
	 * @param objectName the name of the object to look for e.g. a class name or an attribute name
	 * @param eObjectClass The type of class to look for e.g. EClass or ERference
	 * @return the NamedElement looking for or null in case it could not be found
	 */
	private ENamedElement getClassFromEcore(ENamedElement rootObject, String objectName, Class<? extends ENamedElement> eObjectClass) {
		Object obj = null;
		
		if (rootObject.getName().equals(objectName)) {
			return rootObject;
		}
		
		for (TreeIterator<Object> iter = EcoreUtil.getAllContents(rootObject, true); iter.hasNext();  obj = iter.next()) {
			if ((obj != null) && eObjectClass.isAssignableFrom(obj.getClass())) {
				ENamedElement clazz = (ENamedElement) obj;
				if (clazz.getName().equals(objectName)) {
					return clazz;
				}
			}
		}
		return null;
	}
	
	@Test
	public void testDvlmXMIResourceImpl() {
		URI testUri = URI.createFileURI("Blabla");
		DvlmXMIResourceImpl resource = new DvlmXMIResourceImpl(testUri);
		
		assertTrue("Correct subtype", resource instanceof XMIResourceImpl);
		assertEquals("Correct URI", testUri, resource.getURI());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetEObjectByIDString() {
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.setName("de.dlr.sc.virsat.test.concept");
		repo.getActiveConcepts().add(concept);
		Category category = CategoriesFactory.eINSTANCE.createCategory();
		category.setName("TC");
		concept.getCategories().add(category);
		
		DvlmXMIResourceImpl resource = new DvlmXMIResourceImpl(null);
		resource.getContents().add(repo);
		
		EObject nothingById = resource.getEObjectByID("de.dlr.sc.virsat.test.concept.NONE"); 
		EObject categoryById = resource.getEObjectByID("de.dlr.sc.virsat.test.concept.TC"); 
		EObject conceptById = resource.getEObjectByID("de.dlr.sc.virsat.test.concept"); 

		assertEquals("Found the correct concept", concept, conceptById);
		assertEquals("Found the correct category", category, categoryById);
		assertNull("No Object found", nothingById);

		EPackage repositoryPackage = (EPackage) getClassFromEcore(dvlmPackage, "dvlm", EPackage.class);
		EPackage conceptsPackage = (EPackage) getClassFromEcore(dvlmPackage, "concepts", EPackage.class);
		EPackage categoriesPackage = (EPackage) getClassFromEcore(dvlmPackage, "categories", EPackage.class);

		EClass repositoryClass = (EClass) getClassFromEcore(repositoryPackage, "Repository", EClass.class);
		EClass conceptClass = (EClass) getClassFromEcore(conceptsPackage, "Concept", EClass.class);
		EClass categoryClass = (EClass) getClassFromEcore(categoriesPackage, "Category", EClass.class);

		EClass iQualifiedNameClass = (EClass) getClassFromEcore(dvlmPackage, "IQualifiedName", EClass.class);
		EAttribute nameAttribute = (EAttribute) getClassFromEcore(iQualifiedNameClass, "name", EAttribute.class); 
		EReference conceptsReference = (EReference) getClassFromEcore(repositoryClass, "activeConcepts", EReference.class);
		EReference categoriesReference = (EReference) getClassFromEcore(conceptClass, "categories", EReference.class);
		
		EObject dynRepository = repositoryPackage.getEFactoryInstance().create(repositoryClass);
		EObject dynConcept = conceptsPackage.getEFactoryInstance().create(conceptClass);
		EObject dynCategory = categoriesPackage.getEFactoryInstance().create(categoryClass);
		
		dynConcept.eSet(nameAttribute, "de.dlr.sc.virsat.test.concept");
		dynCategory.eSet(nameAttribute, "TC");
		
		((List<EObject>) dynRepository.eGet(conceptsReference)).add(dynConcept);
		((List<EObject>) dynConcept.eGet(categoriesReference)).add(dynCategory);
		
		resource.getContents().clear();
		resource.getContents().add(dynRepository);
		
		EObject dynNothingById = resource.getEObjectByID("de.dlr.sc.virsat.test.concept.NONE"); 
		EObject dynCategoryById = resource.getEObjectByID("de.dlr.sc.virsat.test.concept.TC"); 
		EObject dynConceptById = resource.getEObjectByID("de.dlr.sc.virsat.test.concept"); 

		assertEquals("Found the correct concept", dynConcept, dynConceptById);
		assertEquals("Found the correct category", dynCategory, dynCategoryById);
		assertNull("No Object found", dynNothingById);
		
		// If we remove the object from the resource set,
		// then it should no longer be found. Verifying this property
		// to ensure that caching doesn't uncontained objects.
		resource.getContents().remove(dynRepository);
		dynCategoryById = resource.getEObjectByID("de.dlr.sc.virsat.test.concept.TC");
		assertNull("No Object found", dynNothingById);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetURIFragmentEObject() {
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.setName("de.dlr.sc.virsat.test.concept");
		repo.getActiveConcepts().add(concept);
		Category category = CategoriesFactory.eINSTANCE.createCategory();
		category.setName("TC");
		concept.getCategories().add(category);
		
		DvlmXMIResourceImpl resource = new DvlmXMIResourceImpl(null);
		resource.getContents().add(repo);
		
		String categoryById = resource.getURIFragment(category); 
		String conceptById = resource.getURIFragment(concept);

		assertEquals("Found the correct concept", "de.dlr.sc.virsat.test.concept", conceptById);
		assertEquals("Found the correct category", "de.dlr.sc.virsat.test.concept.TC", categoryById);

		EPackage repositoryPackage = (EPackage) getClassFromEcore(dvlmPackage, "dvlm", EPackage.class);
		EPackage conceptsPackage = (EPackage) getClassFromEcore(dvlmPackage, "concepts", EPackage.class);
		EPackage categoriesPackage = (EPackage) getClassFromEcore(dvlmPackage, "categories", EPackage.class);

		EClass repositoryClass = (EClass) getClassFromEcore(repositoryPackage, "Repository", EClass.class);
		EClass conceptClass = (EClass) getClassFromEcore(conceptsPackage, "Concept", EClass.class);
		EClass categoryClass = (EClass) getClassFromEcore(categoriesPackage, "Category", EClass.class);

		EClass iQualifiedNameClass = (EClass) getClassFromEcore(dvlmPackage, "IQualifiedName", EClass.class);
		EAttribute nameAttribute = (EAttribute) getClassFromEcore(iQualifiedNameClass, "name", EAttribute.class); 
		EReference conceptsReference = (EReference) getClassFromEcore(repositoryClass, "activeConcepts", EReference.class);
		EReference categoriesReference = (EReference) getClassFromEcore(conceptClass, "categories", EReference.class);
		
		EObject dynRepository = repositoryPackage.getEFactoryInstance().create(repositoryClass);
		EObject dynConcept = conceptsPackage.getEFactoryInstance().create(conceptClass);
		EObject dynCategory = categoriesPackage.getEFactoryInstance().create(categoryClass);
		
		dynConcept.eSet(nameAttribute, "de.dlr.sc.virsat.test.concept");
		dynCategory.eSet(nameAttribute, "TC");
		
		((List<EObject>) dynRepository.eGet(conceptsReference)).add(dynConcept);
		((List<EObject>) dynConcept.eGet(categoriesReference)).add(dynCategory);
		
		resource.getContents().clear();
		resource.getContents().add(dynRepository);
		
		String dynCategoryById = resource.getURIFragment(dynCategory); 
		String dynConceptById = resource.getURIFragment(dynConcept); 

		assertEquals("Found the correct concept", "de.dlr.sc.virsat.test.concept", dynConceptById);
		assertEquals("Found the correct category", "de.dlr.sc.virsat.test.concept.TC", dynCategoryById);
	}
}
