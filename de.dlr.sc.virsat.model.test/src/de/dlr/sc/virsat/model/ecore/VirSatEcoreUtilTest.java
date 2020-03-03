/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ecore;


import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Test Cases to test the VirSat Extensions on the EcoreUtil 
 */
public class VirSatEcoreUtilTest {

	private StructuralElementInstance sei;
	private StructuralElement se;
	private Category cat;
	
	@Before
	public void setUp() throws Exception {
		// Create a common root category
		cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setName("DataPort");
		cat.setIsApplicableForAll(true);
		
		// Add two simple properties that will convert into ValueInstanceProeprties
		IntProperty propSerialNo = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propSerialNo.setName("serialNo");
		IntProperty propConnections = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propConnections.setName("connections");
		StringProperty propComment = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		propComment.setName("comment");
		cat.getProperties().add(propSerialNo);
		cat.getProperties().add(propConnections);
		cat.getProperties().add(propComment);
		
		CategoryAssignment ca1 = new CategoryInstantiator().generateInstance(cat, "dataPort1");
		CategoryAssignment ca2 = new CategoryInstantiator().generateInstance(cat, "dataPort2");
		
		// Now add the Category assignment to a StructuralElementInstance
		se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setName("Rw");
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setName("Rw1");
		sei.setType(se);
		sei.getCategoryAssignments().add(ca1);
		sei.getCategoryAssignments().add(ca2);
		
		UserRegistry.getInstance().setSuperUser(true);
	}

	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Test
	public void testGetAllContentsOfTypeCollectionOfQClassOfTBoolean() {
		List<UnitValuePropertyInstance> allUvpi = VirSatEcoreUtil.getAllContentsOfType(sei.getCategoryAssignments(), UnitValuePropertyInstance.class, true);
		List<ValuePropertyInstance> allVpi = VirSatEcoreUtil.getAllContentsOfType(sei.getCategoryAssignments(), ValuePropertyInstance.class, true);
		
		final int EXPECTED_VPI = 6;
		final int EXPECTED_UVPI = 4;

		assertEquals("Found correct amount of Elements", EXPECTED_UVPI, allUvpi.size());
		assertEquals("Found correct amount of Elements", EXPECTED_VPI, allVpi.size());
	}

	@Test
	public void testGetAllContentsOfTypeResourceClassOfTBoolean() {
		Resource resource = new XMIResourceImpl();
		resource.getContents().add(sei);

		Iterator<EObject> allUvpiIterator = VirSatEcoreUtil.getAllContentsOfType(resource, UnitValuePropertyInstance.class, true);
		Iterator<EObject> allVpiIterator = VirSatEcoreUtil.getAllContentsOfType(resource, ValuePropertyInstance.class, true);
		
		List<EObject> allUvpi = new ArrayList<EObject>();
		allUvpiIterator.forEachRemaining(allUvpi::add);
		List<EObject> allVpi = new ArrayList<EObject>();
		allVpiIterator.forEachRemaining(allVpi::add);
		
		final int EXPECTED_VPI = 6;
		final int EXPECTED_UVPI = 4;
		
		assertEquals("Found correct amount of Elements", EXPECTED_UVPI, allUvpi.size());
		assertEquals("Found correct amount of Elements", EXPECTED_VPI, allVpi.size());
		
		Resource noResource = null;
		Iterator<EObject> emptyIterator = VirSatEcoreUtil.getAllContentsOfType(noResource, ValuePropertyInstance.class, true);
		
		assertFalse("Iterator is empty", emptyIterator.hasNext());
	}

	@Test
	public void testGetAllContentsOfTypeTreeIteratorOfQClassOfT() {
		
		Resource resource = new XMIResourceImpl();
		resource.getContents().add(sei);

		List<UnitValuePropertyInstance> allUvpi = VirSatEcoreUtil.getAllContentsOfType(EcoreUtil.getAllContents(resource, true), UnitValuePropertyInstance.class);
		List<ValuePropertyInstance> allVpi = VirSatEcoreUtil.getAllContentsOfType(EcoreUtil.getAllContents(resource, true), ValuePropertyInstance.class);
		
		final int EXPECTED_VPI = 6;
		final int EXPECTED_UVPI = 4;
		
		assertEquals("Found correct amount of Elements", EXPECTED_UVPI, allUvpi.size());
		assertEquals("Found correct amount of Elements", EXPECTED_VPI, allVpi.size());
	}

	@Test
	public void testGetAllContentsOfTypeResourceSetResourceClassOfTBoolean() {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource1 = new XMIResourceImpl();
		resource1.getContents().add(sei);
		Resource resource2 = new XMIResourceImpl();
		
		CategoryAssignment ca1 = new CategoryInstantiator().generateInstance(cat, "dataPort1");
		CategoryAssignment ca2 = new CategoryInstantiator().generateInstance(cat, "dataPort2");
		
		// Now add the Category assignment to a StructuralElementInstance
		StructuralElementInstance sei2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei2.setName("Rw2");
		sei2.setType(se);
		sei2.getCategoryAssignments().add(ca1);
		sei2.getCategoryAssignments().add(ca2);
		resource2.getContents().add(sei2);
		
		resourceSet.getResources().add(resource1);
		resourceSet.getResources().add(resource2);

		List<UnitValuePropertyInstance> allUvpi = VirSatEcoreUtil.getAllContentsOfType(resourceSet, null, UnitValuePropertyInstance.class, true);
		List<ValuePropertyInstance> allVpi = VirSatEcoreUtil.getAllContentsOfType(resourceSet, null, ValuePropertyInstance.class, true);
		
		final int EXPECTED_VPI_TWICE = 12;
		final int EXPECTED_UVPI_TWICE = 8;
		
		assertEquals("Found correct amount of Elements", EXPECTED_UVPI_TWICE, allUvpi.size());
		assertEquals("Found correct amount of Elements", EXPECTED_VPI_TWICE, allVpi.size());

		List<UnitValuePropertyInstance> allUvpi2 = VirSatEcoreUtil.getAllContentsOfType(resourceSet, resource1, UnitValuePropertyInstance.class, true);
		List<ValuePropertyInstance> allVpi2 = VirSatEcoreUtil.getAllContentsOfType(resourceSet, resource1, ValuePropertyInstance.class, true);
		
		final int EXPECTED_VPI_SINGLE = 6;
		final int EXPECTED_UVPI_SINGLE = 4;
		
		assertEquals("Found correct amount of Elements", EXPECTED_UVPI_SINGLE, allUvpi2.size());
		assertEquals("Found correct amount of Elements", EXPECTED_VPI_SINGLE, allVpi2.size());
	}

	@Test
	public void testGetAllContentsOfTypefromResourceSets() {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource1 = new XMIResourceImpl();
		resource1.getContents().add(sei);
		Resource resource2 = new XMIResourceImpl();
		
		CategoryAssignment ca1 = new CategoryInstantiator().generateInstance(cat, "dataPort1");
		CategoryAssignment ca2 = new CategoryInstantiator().generateInstance(cat, "dataPort2");
		
		// Now add the Category assignment to a StructuralElementInstance
		StructuralElementInstance sei2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei2.setName("Rw2");
		sei2.setType(se);
		sei2.getCategoryAssignments().add(ca1);
		sei2.getCategoryAssignments().add(ca2);
		resource2.getContents().add(sei2);
		
		resourceSet.getResources().add(resource1);
		resourceSet.getResources().add(resource2);
		
		List<ResourceSet> resourceSets = new ArrayList<>();
		resourceSets.add(resourceSet);

		List<UnitValuePropertyInstance> allUvpi = VirSatEcoreUtil.getAllContentsOfTypefromResourceSets(resourceSets, null, UnitValuePropertyInstance.class, true);
		List<ValuePropertyInstance> allVpi = VirSatEcoreUtil.getAllContentsOfTypefromResourceSets(resourceSets, null, ValuePropertyInstance.class, true);
		
		final int EXPECTED_VPI_TWICE = 12;
		final int EXPECTED_UVPI_TWICE = 8;
		
		assertEquals("Found correct amount of Elements", EXPECTED_UVPI_TWICE, allUvpi.size());
		assertEquals("Found correct amount of Elements", EXPECTED_VPI_TWICE, allVpi.size());
		
		List<UnitValuePropertyInstance> allUvpi2 = VirSatEcoreUtil.getAllContentsOfTypefromResourceSets(resourceSets, resource1, UnitValuePropertyInstance.class, true);
		List<ValuePropertyInstance> allVpi2 = VirSatEcoreUtil.getAllContentsOfTypefromResourceSets(resourceSets, resource1, ValuePropertyInstance.class, true);
		
		final int EXPECTED_VPI_SINGLE = 6;
		final int EXPECTED_UVPI_SINGLE = 4;
		
		assertEquals("Found correct amount of Elements", EXPECTED_UVPI_SINGLE, allUvpi2.size());
		assertEquals("Found correct amount of Elements", EXPECTED_VPI_SINGLE, allVpi2.size());
	}
	
	@Test
	public void testIsRootComponentofResource() {
		assertFalse("Resource is not contained", VirSatEcoreUtil.isRootComponentofResource(sei));
		
		Resource resource1 = new XMIResourceImpl();
		resource1.getContents().add(sei);

		assertTrue("Now it is contaiend", VirSatEcoreUtil.isRootComponentofResource(sei));

		CategoryAssignment ca1 = new CategoryInstantiator().generateInstance(cat, "dataPort1");
		sei.getCategoryAssignments().add(ca1);
		
		assertFalse("CA is not a root component", VirSatEcoreUtil.isRootComponentofResource(ca1));
	}
	
	@Test
	public void testGetFullQualifiedAttributeName() {
		String attributeFqn1 = VirSatEcoreUtil.getFullQualifiedAttributeName(PropertydefinitionsPackage.Literals.STATIC_ARRAY_MODIFIER__ARRAY_SIZE);
		
		assertEquals("Correct fqn", "dvlm.categories.propertydefinitions.StaticArrayModifier.arraySize", attributeFqn1);
		
		String attributeFqn2 = VirSatEcoreUtil.getFullQualifiedAttributeName(GeneralPackage.Literals.IQUALIFIED_NAME__FULL_QUALIFIED_NAME);
		
		assertEquals("Correct fqn", "dvlm.general.IQualifiedName.fullQualifiedName", attributeFqn2);
	}
	
	@Test
	public void testGetFullQualifiedClassName() {
		String attributeFqn1 = VirSatEcoreUtil.getFullQualifiedClassName(PropertydefinitionsPackage.Literals.BOOLEAN_PROPERTY);
		
		assertEquals("Correct fqn", "dvlm.categories.propertydefinitions.BooleanProperty", attributeFqn1);
	}
	
	@Test
	public void testCopy() {
		StructuralElementInstance copiedSei = VirSatEcoreUtil.copy(sei);
		
		assertNotSame("Created a new object", copiedSei, sei);
		assertEquals("Sei is typed correctly", se, copiedSei.getType());
	}
	
	@Test
	public void testCopyAll() {
		List<CategoryAssignment> copiedCas = (List<CategoryAssignment>) VirSatEcoreUtil.copyAll(sei.getCategoryAssignments());
		
		assertEquals("there are two CAs copied", 2, copiedCas.size());
		
		assertEquals("Both of them are correctly typed", cat, copiedCas.get(0).getType());
		assertEquals("Both of them are correctly typed", cat, copiedCas.get(1).getType());
	}
	
	@Test
	public void testGetContainerFor() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(cat, "dataPort1");
		APropertyInstance pi1 = ca.getPropertyInstances().get(0);
		APropertyInstance pi2 = ca.getPropertyInstances().get(1);
		
		assertNull("CA is not yet contained", VirSatEcoreUtil.getEContainerOfClass(ca, ICategoryAssignmentContainer.class));
		assertNull("CA is not yet contained", VirSatEcoreUtil.getEContainerOfClass(pi1, ICategoryAssignmentContainer.class));
		assertNull("CA is not yet contained", VirSatEcoreUtil.getEContainerOfClass(pi2, ICategoryAssignmentContainer.class));
		
		sei.getCategoryAssignments().add(ca);
		
		assertEquals("Ca is now contained", sei, VirSatEcoreUtil.getEContainerOfClass(ca, ICategoryAssignmentContainer.class));
		assertEquals("Ca is now contained", sei, VirSatEcoreUtil.getEContainerOfClass(pi1, ICategoryAssignmentContainer.class));
		assertEquals("Ca is now contained", sei, VirSatEcoreUtil.getEContainerOfClass(pi2, ICategoryAssignmentContainer.class));
	}
	
	@Test
	public void testGetNonContainedReferencingObjects() {

		/**
		 * # ResSet
		 * \-> ResourceA
		 * 		\-> SEI
		 * 			\-> CA               (Non Contained)
		 * 				\-> RPI - - - \
		 * \-> ResourceB              :
		 * 		\-> SEI               :
		 *          |      <- - - - - /
		 * 			\-> CA <- - - - - \  (Referenced)
		 * 			    |  <- - - - \ :
		 * 				\-> RPI - - / :
		 * 		\-> SEI				  :
		 * 			\-> CA            :  (Contained)
		 * 				\-> RPI - - - /
		 * 
		 */
		
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        reg.getExtensionToFactoryMap().put("dvlm", new XMIResourceFactoryImpl());

        ResourceSet resSet = new ResourceSetImpl();
        Resource resourceA = resSet.createResource(URI.createURI("ecoreutiltest/ResA.dvlm"));
        Resource resourceB = resSet.createResource(URI.createURI("ecoreutiltest/ResB.dvlm"));
        
        // Build the model
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		
		StructuralElementInstance seiReferenced = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiReferenced.setType(se);
		StructuralElementInstance seiReferencingNonContained = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiReferencingNonContained.setType(se);
		StructuralElementInstance seiReferencingContained = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiReferencingContained.setType(se);
		
		resourceA.getContents().add(seiReferencingNonContained);
		resourceB.getContents().add(seiReferenced);
		resourceB.getContents().add(seiReferencingContained);
		
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setIsApplicableForAll(true);
		
		ReferenceProperty rp = PropertydefinitionsFactory.eINSTANCE.createReferenceProperty();
		rp.setReferenceType(cat);
		cat.getProperties().add(rp);
		
		CategoryAssignment caReferenced = new CategoryInstantiator().generateInstance(cat, "CA_REFERENCED");
		CategoryAssignment caContained = new CategoryInstantiator().generateInstance(cat, "CA_CONTAINED");
		CategoryAssignment caNonContained = new CategoryInstantiator().generateInstance(cat, "CA_NON_CONTAINED");
		
		ReferencePropertyInstance rpiReferenced = (ReferencePropertyInstance) caReferenced.getPropertyInstances().get(0);
		ReferencePropertyInstance rpiContained = (ReferencePropertyInstance) caContained.getPropertyInstances().get(0);
		ReferencePropertyInstance rpiNonContained = (ReferencePropertyInstance) caNonContained.getPropertyInstances().get(0);
		
		rpiReferenced.setReference(caReferenced);
		rpiContained.setReference(caReferenced);
		rpiNonContained.setReference(caReferenced);
		
		seiReferenced.getCategoryAssignments().add(caReferenced);
		seiReferencingNonContained.getCategoryAssignments().add(caNonContained);
		seiReferencingContained.getCategoryAssignments().add(caContained);
		
		rpiReferenced.setReference(caReferenced);
		rpiContained.setReference(caReferenced);
		rpiNonContained.setReference(caReferenced);
		
		// Test Case 1: We remove the SEI with the reference which is not contained in the same resource
		// No objects are referencing the objects questioned for deletion thus the map should be empty.
		// resourceA.unload(); Intended for a future extension of the test case which will handle reload of the resources as well
		Map<EObject, List<EObject>> resultCase1 = VirSatEcoreUtil.getReferencingObjectsForDelete(Collections.singleton(seiReferencingNonContained), resSet);
		assertTrue("Nothing should be referenced", resultCase1.isEmpty());
		
		// Test Case 2: We remove the CA which is referenced by all others including the RPI of itself
		// This delete should hand back two references to the CA which is questioned for delete. The RPI
		// of itself should not be mentioned it will be removed as well
		// resourceA.unload();
		Map<EObject, List<EObject>> resultCase2 = VirSatEcoreUtil.getReferencingObjectsForDelete(Collections.singleton(caReferenced), resSet);
		
		seiReferencingNonContained = (StructuralElementInstance) resourceA.getContents().get(0);
		caNonContained = seiReferencingNonContained.getCategoryAssignments().get(0);
		rpiNonContained = (ReferencePropertyInstance) caNonContained.getPropertyInstances().get(0);
		
		assertThat("found referencing Objects", resultCase2.get(caReferenced), hasItems(rpiContained, rpiNonContained));
		assertThat("found referencing Objects", resultCase2.get(caReferenced), not(hasItems(rpiReferenced)));

		// Test Case 3: Same as Test Case 2 but we delete the SEI instead of the CA the result is the same
		// resourceA.unload();
		Map<EObject, List<EObject>> resultCase3 = VirSatEcoreUtil.getReferencingObjectsForDelete(Collections.singleton(seiReferenced), resSet);
		
		seiReferencingNonContained = (StructuralElementInstance) resourceA.getContents().get(0);
		caNonContained = seiReferencingNonContained.getCategoryAssignments().get(0);
		rpiNonContained = (ReferencePropertyInstance) caNonContained.getPropertyInstances().get(0);
		
		assertThat("found referencing Objects", resultCase3.get(caReferenced), hasItems(rpiContained, rpiNonContained));
		assertThat("found referencing Objects", resultCase3.get(caReferenced), not(hasItems(rpiReferenced)));
		
		// Test Case 4: Similar to 1 but this time we delete the reference from the contained resource
		// resourceA.unload();
		Map<EObject, List<EObject>> resultCase4 = VirSatEcoreUtil.getReferencingObjectsForDelete(Collections.singleton(seiReferencingContained), resSet);
		
		seiReferencingNonContained = (StructuralElementInstance) resourceA.getContents().get(0);
		caNonContained = seiReferencingNonContained.getCategoryAssignments().get(0);
		rpiNonContained = (ReferencePropertyInstance) caNonContained.getPropertyInstances().get(0);
		
		assertTrue("Nothing should be referenced", resultCase4.isEmpty());
	}
	
	@Test
	public void testGetReferencingObjectsWithInheritance() {

		// In this test case crate two SEIs which inherit from each other.
		// The super SEI contains one CA, which is inherited to the sub SEI.
		// Now deleting the CA from the super SEI should not list references even
		// though there are actual references in the model by the superTis
		
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		reg.getExtensionToFactoryMap().put("dvlm", new XMIResourceFactoryImpl());

		// Build the model
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsCanInheritFromAll(true);
		
		StructuralElementInstance superSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		superSei.setType(se);
		StructuralElementInstance subSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		subSei.setType(se);
		subSei.getSuperSeis().add(superSei);
		
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		repo.getRootEntities().add(subSei);
		repo.getRootEntities().add(superSei);
		
		ResourceSet resSet = new ResourceSetImpl();
		Resource resourceRepo = resSet.createResource(URI.createURI("ecoreutiltest/ResInheritRepo.dvlm"));
		Resource resourceSuperSei = resSet.createResource(URI.createURI("ecoreutiltest/ResInheritSeuperSei.dvlm"));
		Resource resourceSubSei = resSet.createResource(URI.createURI("ecoreutiltest/ResInheritSubSei.dvlm"));
		resourceRepo.getContents().add(repo);
		resourceSuperSei.getContents().add(superSei);
		resourceSubSei.getContents().add(subSei);
		
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setIsApplicableForAll(true);
		
		ReferenceProperty rp = PropertydefinitionsFactory.eINSTANCE.createReferenceProperty();
		rp.setReferenceType(cat);
		cat.getProperties().add(rp);
		
		CategoryAssignment superCa = new CategoryInstantiator().generateInstance(cat, "CA_REFERENCED");
		superSei.getCategoryAssignments().add(superCa);
		
		new InheritanceCopier().updateInOrderFrom(subSei, repo, new NullProgressMonitor());
		
		assertFalse("the subSei now has a CA as well", subSei.getCategoryAssignments().isEmpty());
		assertThat("the ca in the sub is correctly linked", subSei.getCategoryAssignments().get(0).getSuperTis(), hasItem(superCa));
		
		// Now try to delete the CA which should not tell us, that there are references
		Map<EObject, List<EObject>> resultCase1 = VirSatEcoreUtil.getReferencingObjectsForDelete(Collections.singleton(superCa), resSet);
		assertTrue("Nothing should be referenced", resultCase1.isEmpty());

		// Different story with the super SEI, it should tell us that it is referenced by the sub SEI
		Map<EObject, List<EObject>> resultCase2 = VirSatEcoreUtil.getReferencingObjectsForDelete(Collections.singleton(superSei), resSet);
		assertThat("found referencing sub sei", resultCase2.get(superSei), hasItem(subSei));
	}
}
