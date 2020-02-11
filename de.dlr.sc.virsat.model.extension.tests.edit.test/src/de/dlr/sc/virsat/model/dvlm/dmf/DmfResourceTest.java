/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.dmf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.EReferenceTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReference;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum;
import de.dlr.sc.virsat.model.extension.tests.tests.TestsFactory;
import de.dlr.sc.virsat.model.external.tests.ExternalTestType;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.resources.dmf.DmfResource;
import de.dlr.sc.virsat.project.resources.dmf.DmfResourceFactory;

/**
 * This class tests the methods of DmfResource
 * @author muel_s8
 *
 */

public class DmfResourceTest extends AConceptTestCase {

	private IProject project;
	private VirSatResourceSet resSet;
	private VirSatTransactionalEditingDomain ed;
	private Concept concept;
	
	protected EPackage testExternalPackage;
	private static final String PLATFORM_MODEL_PATH = "/de.dlr.sc.virsat.model.external.tests/model/ExternalModel.ecore";
	
	@Before
	public void setUp() throws Exception {
		concept = loadConceptFromPlugin();
		
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IWorkspaceDescription wsd = ResourcesPlugin.getWorkspace().getDescription();
		wsd.setAutoBuilding(false);
		ResourcesPlugin.getWorkspace().setDescription(wsd);
		wsRoot.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		
		project = wsRoot.getProject("dmfResourceTests");
		if (project.exists()) {
			project.delete(true, null);
		}
		
		project.create(null);
		project.open(null);

		UserRegistry.getInstance().setSuperUser(true);
		
		resSet = VirSatResourceSet.getResourceSet(project, false);
		ed = VirSatEditingDomainRegistry.INSTANCE.getEd(resSet);
		ed.getCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getResources().clear();
				resSet.initializeModelsAndResourceSet();
				resSet.getRepository().getActiveConcepts().add(concept);
			}
		});
		
		URI metamodelURI = URI.createPlatformPluginURI(PLATFORM_MODEL_PATH, true);
		Resource metamodelResource = resSet.getResource(metamodelURI, true);
		testExternalPackage = (EPackage) metamodelResource.getContents().get(0);
	}

	@After
	public void tearDown() throws Exception {
		if (project.exists()) {
			project.delete(true, null);
		}
		UserRegistry.getInstance().setSuperUser(false);
		ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
	}
	
	@Test
	public void testLoadSingleCategoryAssignment() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		TestCategoryAllProperty beanTestCategoryAssignment = new TestCategoryAllProperty(concept);
		final double TEST_VALUE_DOUBLE = 10.5d;
		final int TEST_VALUE_INT = 10;
		final String TEST_VALUE_STRING = "Hello";
		final boolean TEST_VALUE_BOOL = true;
		final URI TEST_VALUE_RESOURCE = URI.createPlatformResourceURI("testdata/test.data", true);
		final String TEST_BEAN_NAME = "World";
		final String TEST_VALUE_ENUM = "HIGH";
		
		beanTestCategoryAssignment.setTestBool(TEST_VALUE_BOOL);
		beanTestCategoryAssignment.setTestFloat(TEST_VALUE_DOUBLE);
		beanTestCategoryAssignment.setTestInt(TEST_VALUE_INT);
		beanTestCategoryAssignment.setTestString(TEST_VALUE_STRING);
		beanTestCategoryAssignment.setTestResource(TEST_VALUE_RESOURCE);
		beanTestCategoryAssignment.setTestEnum(TEST_VALUE_ENUM);
		beanTestCategoryAssignment.setName(TEST_BEAN_NAME);
		
		beanTestStructuralElement.add(beanTestCategoryAssignment);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		// DMF Resource has a containment object as root content and it only has exactly one obejct as contents
		assertEquals("Make sure there is only one object", 1, dmfResource.getContents().size());
		assertTrue("The first obejct in the resource is correct", dmfResource.getContents().get(0) instanceof DObjectContainer);

		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		assertEquals("There is one DObject inside of dObjectContainer", 1, dObjectContainer.getObjects().size());
		assertTrue("Category inside of dObjectContainer", dObjectContainer.getObjects().get(0) instanceof de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty);
		
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignment = (de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty) dObjectContainer.getObjects().get(0);
		final double EPSILON = 0.0000001d;
		assertEquals("Name same as bean Name", beanTestCategoryAssignment.getName(), dmfCategoryAssignment.getName());
		assertEquals("UUID same as bean UUID", beanTestCategoryAssignment.getUuid().toString(), dmfCategoryAssignment.getUuid().toString());
		assertEquals("DMF property same as Bean property", beanTestCategoryAssignment.getTestFloatBean().getValueToBaseUnit(), dmfCategoryAssignment.getTestFloat(), EPSILON);
		assertEquals("DMF property same as Bean property", TEST_VALUE_INT, dmfCategoryAssignment.getTestInt());
		assertEquals("DMF property same as Bean property", TEST_VALUE_RESOURCE.toPlatformString(true), dmfCategoryAssignment.getTestResource());
		assertEquals("DMF property same as Bean property", TEST_VALUE_STRING, dmfCategoryAssignment.getTestString());
		assertEquals("DMF property same as Bean property", TEST_VALUE_ENUM, dmfCategoryAssignment.getTestEnum().getLiteral());
		assertEquals("DMF property same as Bean property", TEST_VALUE_BOOL, dmfCategoryAssignment.isTestBool());
	}
	
	/**
	 * Method to create a test property
	 * @param ai the array instance
	 * @param value the value
	 * @return a new BeanPropertyString
	 */
	private BeanPropertyString createNewStringProperty(ArrayInstance ai, String value) {
		APropertyInstance pi = new CategoryInstantiator().generateInstance(ai);
		BeanPropertyString newBeanProperty = new BeanPropertyString();
		newBeanProperty.setTypeInstance((ValuePropertyInstance) pi);
		newBeanProperty.setValue(value);
		return newBeanProperty;
	}
	
	@Test
	public void testLoadCategoryAssignmentWithArray() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		TestCategoryIntrinsicArray beanTestCategoryAssignment = new TestCategoryIntrinsicArray(concept);
		final String TEST_VALUE_STRING_0 = "Hello";
		final String TEST_VALUE_STRING_1 = "World";
		
		ArrayInstance aiDynamic = beanTestCategoryAssignment.getTestStringArrayDynamic().getArrayInstance();
		
		beanTestCategoryAssignment.getTestStringArrayDynamic().add(createNewStringProperty(aiDynamic, TEST_VALUE_STRING_0));
		beanTestCategoryAssignment.getTestStringArrayDynamic().add(createNewStringProperty(aiDynamic, TEST_VALUE_STRING_1));
		beanTestCategoryAssignment.getTestStringArrayStatic().get(0).setValue(TEST_VALUE_STRING_0);
		beanTestCategoryAssignment.getTestStringArrayStatic().get(1).setValue(TEST_VALUE_STRING_1);
		
		beanTestStructuralElement.add(beanTestCategoryAssignment);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		// DMF Resource has a containment object as root content and it only has exactly one obejct as contents
		assertEquals("Make sure there is only one object", 1, dmfResource.getContents().size());
		assertTrue("The first obejct in the resource is correct", dmfResource.getContents().get(0) instanceof DObjectContainer);

		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		assertEquals("There is one DObject inside of dObjectContainer", 1, dObjectContainer.getObjects().size());
		assertTrue("Category inside of dObjectContainer", dObjectContainer.getObjects().get(0) instanceof de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray);
		
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray dmfCategoryAssignment = (de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray) dObjectContainer.getObjects().get(0);
		assertEquals("DMF property same as Bean property", TEST_VALUE_STRING_0, dmfCategoryAssignment.getTestStringArrayDynamic().get(0));
		assertEquals("DMF property same as Bean property", TEST_VALUE_STRING_1, dmfCategoryAssignment.getTestStringArrayDynamic().get(1));
		assertEquals("DMF property same as Bean property", TEST_VALUE_STRING_0, dmfCategoryAssignment.getTestStringArrayStatic().get(0));
		assertEquals("DMF property same as Bean property", TEST_VALUE_STRING_1, dmfCategoryAssignment.getTestStringArrayStatic().get(1));
	}

	@Test
	public void testLoadCategoryAssignmentWithReferenceArray() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		TestCategoryReferenceArray beanTestCategoryAssignmentArray = new TestCategoryReferenceArray(concept);
		TestCategoryAllProperty beanTestCategoryAssignmentReferenced = new TestCategoryAllProperty(concept);
		
		beanTestCategoryAssignmentArray.getTestCategoryReferenceArrayDynamic().add(beanTestCategoryAssignmentReferenced);
		ReferencePropertyInstance rpi = (ReferencePropertyInstance) beanTestCategoryAssignmentArray.getTestCategoryReferenceArrayStatic().getArrayInstance().getArrayInstances().get(0);
		rpi.setReference(beanTestCategoryAssignmentReferenced.getATypeInstance());
		
		beanTestStructuralElement.add(beanTestCategoryAssignmentArray);
		beanTestStructuralElement.add(beanTestCategoryAssignmentReferenced);
		
		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		// DMF Resource has a containment object as root content and it only has exactly one obejct as contents
		assertEquals("Make sure there is only one object", 1, dmfResource.getContents().size());
		assertTrue("The first obejct in the resource is correct", dmfResource.getContents().get(0) instanceof DObjectContainer);

		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		assertEquals("There are two DObjects inside of dObjectContainer", 2, dObjectContainer.getObjects().size());
		assertTrue("Category inside of dObjectContainer", dObjectContainer.getObjects().get(0) instanceof de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray);
		assertTrue("Category inside of dObjectContainer", dObjectContainer.getObjects().get(1) instanceof de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty);
		
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray dmfCategoryAssignmentArray = (de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray) dObjectContainer.getObjects().get(0);
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignmentReferenced = (de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty) dObjectContainer.getObjects().get(1);
		assertEquals("DMF property same as Bean property", dmfCategoryAssignmentReferenced, dmfCategoryAssignmentArray.getTestCategoryReferenceArrayDynamic().get(0));
		assertEquals("DMF property same as Bean property", dmfCategoryAssignmentReferenced, dmfCategoryAssignmentArray.getTestCategoryReferenceArrayStatic().get(0));
	}

	@Test
	public void testLoadCategoryAssignmentWithEReference() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		EReferenceTest beanTestCategoryAssignment = new EReferenceTest(concept);
		
		final ExternalTestType TEST_EREFERENCE_VALUE = de.dlr.sc.virsat.model.external.tests.TestsFactory.eINSTANCE.createExternalTestType();
		
		beanTestCategoryAssignment.setEReferenceTest(TEST_EREFERENCE_VALUE);
		
		beanTestStructuralElement.add(beanTestCategoryAssignment);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		// DMF Resource has a containment object as root content and it only has exactly one obejct as contents
		assertEquals("Make sure there is only one object", 1, dmfResource.getContents().size());
		assertTrue("The first obejct in the resource is correct", dmfResource.getContents().get(0) instanceof DObjectContainer);

		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		assertEquals("There is one DObject inside of dObjectContainer", 1, dObjectContainer.getObjects().size());
		assertTrue("Category inside of dObjectContainer", dObjectContainer.getObjects().get(0) instanceof de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest);
		
		de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest dmfCategoryAssignment = (de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest) dObjectContainer.getObjects().get(0);
		assertNotNull("The EReference value should be transfered to the DMF object", dmfCategoryAssignment.getEReferenceTest());
		assertEquals("The EReference value should be the same as set on the DVLM category", TEST_EREFERENCE_VALUE, dmfCategoryAssignment.getEReferenceTest());
	}
	
	
	@Test
	public void testLoadReferencedCategorySameContainment() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		TestCategoryAllProperty beanTestCategoryAssignment = new TestCategoryAllProperty(concept);
		TestCategoryReference beanTestCategoryAssignmentReference = new TestCategoryReference(concept);

		beanTestCategoryAssignmentReference.setTestRefCategory(beanTestCategoryAssignment);
		
		beanTestStructuralElement.add(beanTestCategoryAssignmentReference);
		beanTestStructuralElement.add(beanTestCategoryAssignment);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		// DMF Resource has a containment object as root content and it only has exactly one obejct as contents
		assertEquals("Make sure there is only one object", 1, dmfResource.getContents().size());
		assertTrue("The first obejct in the resource is correct", dmfResource.getContents().get(0) instanceof DObjectContainer);

		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		assertEquals("There are two DObject inside of dObjectContainer", 2, dObjectContainer.getObjects().size());
		
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference dmfCategoryAssignmentReference = (de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference) dObjectContainer.getObjects().get(0);
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignment = (de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty) dObjectContainer.getObjects().get(1);
		
		assertEquals("reference is set correctly", dmfCategoryAssignment, dmfCategoryAssignmentReference.getTestRefCategory());
	}
	
	@Test
	public void testLoadReferencedCategoryDifferentContainment() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		TestStructuralElement beanTestStructuralElementOther = new TestStructuralElement(concept);
		TestCategoryAllProperty beanTestCategoryAssignment = new TestCategoryAllProperty(concept);
		TestCategoryReference beanTestCategoryAssignmentReference = new TestCategoryReference(concept);

		beanTestCategoryAssignmentReference.setTestRefCategory(beanTestCategoryAssignment);
		
		beanTestStructuralElement.add(beanTestCategoryAssignmentReference);
		beanTestStructuralElementOther.add(beanTestCategoryAssignment);
		
		final int TEST_VALUE_INT = 10;
		beanTestCategoryAssignment.setTestInt(TEST_VALUE_INT);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElementOther.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());

		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		// DMF Resource has a containment object as root content and it only has exactly one obejct as contents
		assertEquals("Make sure there is only one object", 1, dmfResource.getContents().size());
		assertTrue("The first obejct in the resource is correct", dmfResource.getContents().get(0) instanceof DObjectContainer);

		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		assertEquals("There is one DObject inside of dObjectContainer", 1, dObjectContainer.getObjects().size());
		
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference dmfCategoryAssignmentReference = (de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference) dObjectContainer.getObjects().get(0);
		assertEquals("ResourceSet has loaded 1 resource", 1, dmfResourceSet.getResources().size());
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignment = dmfCategoryAssignmentReference.getTestRefCategory();
		assertEquals("ResourceSet has loaded 2 resources", 2, dmfResourceSet.getResources().size());
		assertEquals("DMF property same as Bean property", TEST_VALUE_INT, dmfCategoryAssignment.getTestInt());
	}
	

	@Test
	public void testLoadComposedCategory() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		TestCategoryComposition beanTestCategoryAssignmentComposed = new TestCategoryComposition(concept);

		final int TEST_VALUE_INT = 10;
		beanTestCategoryAssignmentComposed.getTestSubCategory().setTestInt(TEST_VALUE_INT);
		beanTestStructuralElement.add(beanTestCategoryAssignmentComposed);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		// DMF Resource has a containment object as root content and it only has exactly one obejct as contents
		assertEquals("Make sure there is only one object", 1, dmfResource.getContents().size());
		assertTrue("The first object in the resource is correct", dmfResource.getContents().get(0) instanceof DObjectContainer);

		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		assertEquals("There is one DObject inside of dObjectContainer", 1, dObjectContainer.getObjects().size());
		
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryComposition dmfCategoryAssignmentComposed = (de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryComposition) dObjectContainer.getObjects().get(0);
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignment = dmfCategoryAssignmentComposed.getTestSubCategory();
		
		assertEquals("DMF property of contained DMF Object is set correctly", TEST_VALUE_INT, dmfCategoryAssignment.getTestInt());
	}
	
	@Test
	public void testLoadUnresolved() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.setName("NoDmfConcept");
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setName("NoDmfCat");
		concept.getCategories().add(cat);
		cat.setIsApplicableForAll(true);
		CategoryAssignment noDmfCa = new CategoryInstantiator().generateInstance(cat, "NoDmfCa");
		
		beanTestStructuralElement.getStructuralElementInstance().getCategoryAssignments().add(noDmfCa);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		// DMF Resource has a containment object as root content and it only has exactly one obejct as contents
		assertEquals("Make sure there is only one object", 1, dmfResource.getContents().size());
		assertTrue("The first object in the resource is correct", dmfResource.getContents().get(0) instanceof DObjectContainer);

		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		assertEquals("There is one DObject inside of dObjectContainer", 1, dObjectContainer.getObjects().size());
		
		DObject dObject = dObjectContainer.getObjects().get(0);
		assertEquals("Name of unresolvable dObject is set correctly", noDmfCa.getName(), dObject.getName());
		assertEquals("Uuid of unresolvable dObject is set correctly", noDmfCa.getUuid(), dObject.getUuid());
		assertTrue("The dObject is an unresolvable dObject",  dObject instanceof UnresolveableDObject);
	}
	
	@Test
	public void testSaveSingleDObject() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		
		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignment = TestsFactory.eINSTANCE.createTestCategoryAllProperty();
		dObjectContainer.getObjects().add(dmfCategoryAssignment);
		
		final double TEST_VALUE_DOUBLE = 10.5d;
		final int TEST_VALUE_INT = 10;
		final String TEST_VALUE_STRING = "Hello";
		final String TEST_VALUE_ENUM = "HIGH";
		final boolean TEST_VALUE_BOOL = true;
		final String TEST_VALUE_RESOURCE = "/testdata/test.data";
		final String TEST_NAME = "World";	
	
		dmfCategoryAssignment.setTestFloat(TEST_VALUE_DOUBLE);
		dmfCategoryAssignment.setTestInt(TEST_VALUE_INT);
		dmfCategoryAssignment.setTestString(TEST_VALUE_STRING);
		dmfCategoryAssignment.setTestResource(TEST_VALUE_RESOURCE);
		dmfCategoryAssignment.setTestBool(TEST_VALUE_BOOL);
		dmfCategoryAssignment.setTestEnum(EnumTestEnum.HIGH);
		dmfCategoryAssignment.setName(TEST_NAME);
		dmfResource.save(Collections.EMPTY_MAP);
		
		resSei.unload();
		resSei.load(Collections.EMPTY_MAP);
		
		StructuralElementInstance sei = (StructuralElementInstance) resSei.getContents().get(0);
		assertFalse("Sei has a category assignment", sei.getCategoryAssignments().isEmpty());
		CategoryAssignment ca = sei.getCategoryAssignments().get(0);
		assertEquals("Category Assignment has correct type", TestCategoryAllProperty.class.getSimpleName(), ca.getType().getName());
		TestCategoryAllProperty beanTestCategoryAssignment = new TestCategoryAllProperty(ca);
		
		final double EPSILON = 0.0000001d;
		assertEquals("Name same as bean Name", beanTestCategoryAssignment.getName(), dmfCategoryAssignment.getName());
		assertEquals("UUID same as bean UUID", beanTestCategoryAssignment.getUuid().toString(), dmfCategoryAssignment.getUuid().toString());
		assertEquals("DMF property same as Bean property", TEST_VALUE_DOUBLE, beanTestCategoryAssignment.getTestFloatBean().getValueToBaseUnit(), EPSILON);
		assertEquals("DMF property same as Bean property", TEST_VALUE_INT, beanTestCategoryAssignment.getTestInt());
		assertEquals("DMF property same as Bean property", TEST_VALUE_RESOURCE, beanTestCategoryAssignment.getTestResource().toPlatformString(true));
		assertEquals("DMF property same as Bean property", TEST_VALUE_STRING, beanTestCategoryAssignment.getTestString());
		assertEquals("DMF property same as Bean property", TEST_VALUE_ENUM, beanTestCategoryAssignment.getTestEnum());
		assertEquals("DMF property same as Bean property", TEST_VALUE_BOOL, beanTestCategoryAssignment.getTestBool());
	}
	
	@Test
	public void testSaveDObjectWithEReference() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		
		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		de.dlr.sc.virsat.model.extension.tests.tests.EReferenceTest dmfCategoryAssignment = TestsFactory.eINSTANCE.createEReferenceTest();
		dObjectContainer.getObjects().add(dmfCategoryAssignment);
		
		final ExternalTestType TEST_EREFERENCE_VALUE = de.dlr.sc.virsat.model.external.tests.TestsFactory.eINSTANCE.createExternalTestType();
		URI testExternalModelInstanceURI = originalSeiUri.trimFileExtension().appendFileExtension("etests");
		Resource testExternalModelInstanceResource = resSet.createResource(testExternalModelInstanceURI);
		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				testExternalModelInstanceResource.getContents().add(TEST_EREFERENCE_VALUE);
			}
		});
		
		dmfCategoryAssignment.setEReferenceTest(TEST_EREFERENCE_VALUE);
		
		testExternalModelInstanceResource.save(Collections.EMPTY_MAP);
		dmfResource.save(Collections.EMPTY_MAP);
		
		resSei.unload();
		resSei.load(Collections.EMPTY_MAP);
		
		StructuralElementInstance sei = (StructuralElementInstance) resSei.getContents().get(0);
		assertFalse("Sei has a category assignment", sei.getCategoryAssignments().isEmpty());
		CategoryAssignment ca = sei.getCategoryAssignments().get(0);
		EReferenceTest beanTestCategoryAssignment = new EReferenceTest(ca);
		
		assertNotNull("EReference value should have bean saved and loaded on model level", ((EReferencePropertyInstance) ca.getPropertyInstances().get(0)).getReference());
		assertNotNull("EReference value should have bean saved and loaded on bean level", beanTestCategoryAssignment.getEReferenceTest());
		assertEquals("EReference value should be same as set in DMF", TEST_EREFERENCE_VALUE, beanTestCategoryAssignment.getEReferenceTest());
		assertEquals("EReference value should be same as set in DMF", TEST_EREFERENCE_VALUE, beanTestCategoryAssignment.getEReferenceTest());
		assertEquals("EReference value should be same as set in DMF", TEST_EREFERENCE_VALUE.eResource().getURI().toString(), beanTestCategoryAssignment.getEReferenceTest().eResource().getURI().toString());
	}
	
	@Test
	public void testSaveReferencedDObjectSameContainment() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference dmfCategoryAssignmentReference = TestsFactory.eINSTANCE.createTestCategoryReference();
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignment = TestsFactory.eINSTANCE.createTestCategoryAllProperty();
		dObjectContainer.getObjects().add(dmfCategoryAssignmentReference);
		dObjectContainer.getObjects().add(dmfCategoryAssignment);
		
		dmfCategoryAssignmentReference.setTestRefCategory(dmfCategoryAssignment);
		dmfResource.save(Collections.EMPTY_MAP);
		
		resSei.unload();
		resSei.load(Collections.EMPTY_MAP);
		
		StructuralElementInstance sei = (StructuralElementInstance) resSei.getContents().get(0);
		assertEquals("Sei has correct number of category assignments", 2, sei.getCategoryAssignments().size());
		CategoryAssignment referencingCa = sei.getCategoryAssignments().get(0);
		CategoryAssignment referencedCa = sei.getCategoryAssignments().get(1);
		assertEquals("Category Assignment has correct type", TestCategoryReference.class.getSimpleName(), referencingCa.getType().getName());
		assertEquals("Category Assignment has correct type", TestCategoryAllProperty.class.getSimpleName(), referencedCa.getType().getName());
		
		TestCategoryAllProperty beanTestCategoryAssignment = new TestCategoryAllProperty(referencedCa);
		TestCategoryReference beanTestCategoryAssignmentReference = new TestCategoryReference(referencingCa);
		
		assertEquals("Reference is set correctly", beanTestCategoryAssignment, beanTestCategoryAssignmentReference.getTestRefCategory());
	}
	
	@Test
	public void testSaveReferencedDObjectDifferentContainment() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		TestStructuralElement beanTestStructuralElementOther = new TestStructuralElement(concept);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElementOther.getStructuralElementInstance());
			}
		});

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		Resource resSeiOther = resSet.getStructuralElementInstanceResource(beanTestStructuralElementOther.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUriOther = resSeiOther.getURI();
		URI dmfSeiUriOther = originalSeiUriOther.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);
		
		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);
		
		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResourceOther = dmfResourceSet.getResource(dmfSeiUriOther, true);
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		ed.saveAll();
		
		DObjectContainer dObjectContainerOther = (DObjectContainer) dmfResourceOther.getContents().get(0);
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference dmfCategoryAssignmentReference = TestsFactory.eINSTANCE.createTestCategoryReference();
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignment = TestsFactory.eINSTANCE.createTestCategoryAllProperty();

		dObjectContainerOther.getObjects().add(dmfCategoryAssignment);
		dmfResourceOther.save(Collections.EMPTY_MAP);
		
		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		dmfCategoryAssignmentReference.setTestRefCategory(dmfCategoryAssignment);
		dObjectContainer.getObjects().add(dmfCategoryAssignmentReference);
		dmfResource.save(Collections.EMPTY_MAP);
		
		resSei.unload();
		resSei.load(Collections.EMPTY_MAP);

		resSeiOther.unload();
		resSeiOther.load(Collections.EMPTY_MAP);
		
		StructuralElementInstance sei = (StructuralElementInstance) resSei.getContents().get(0);
		assertEquals("Sei has correct number of category assignments", 1, sei.getCategoryAssignments().size());
		StructuralElementInstance seiOther = (StructuralElementInstance) resSeiOther.getContents().get(0);
		assertEquals("Sei has correct number of category assignments", 1, seiOther.getCategoryAssignments().size());
		CategoryAssignment referencingCa = sei.getCategoryAssignments().get(0);
		CategoryAssignment referencedCa = seiOther.getCategoryAssignments().get(0);
		assertEquals("Category Assignment has correct type", TestCategoryReference.class.getSimpleName(), referencingCa.getType().getName());
		assertEquals("Category Assignment has correct type", TestCategoryAllProperty.class.getSimpleName(), referencedCa.getType().getName());
		
		TestCategoryAllProperty beanTestCategoryAssignment = new TestCategoryAllProperty(referencedCa);
		TestCategoryReference beanTestCategoryAssignmentReference = new TestCategoryReference(referencingCa);
		
		assertEquals("Reference is set correctly", beanTestCategoryAssignment, beanTestCategoryAssignmentReference.getTestRefCategory());
		
	}
	
	@Test
	public void testSaveReferencedDObjectDifferentUnloadedContainment() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		TestStructuralElement beanTestStructuralElementOther = new TestStructuralElement(concept);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElementOther.getStructuralElementInstance());
			}
		});

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		Resource resSeiOther = resSet.getStructuralElementInstanceResource(beanTestStructuralElementOther.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUriOther = resSeiOther.getURI();
		URI dmfSeiUriOther = originalSeiUriOther.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);
		
		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);
		
		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResourceOther = dmfResourceSet.getResource(dmfSeiUriOther, true);
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		ed.saveAll();
		
		DObjectContainer dObjectContainerOther = (DObjectContainer) dmfResourceOther.getContents().get(0);
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReference dmfCategoryAssignmentReference = TestsFactory.eINSTANCE.createTestCategoryReference();
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignment = TestsFactory.eINSTANCE.createTestCategoryAllProperty();
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfNewTargetCategoryAssignment = TestsFactory.eINSTANCE.createTestCategoryAllProperty();

		dObjectContainerOther.getObjects().add(dmfCategoryAssignment);
		dObjectContainerOther.getObjects().add(dmfNewTargetCategoryAssignment);
		dmfResourceOther.save(Collections.EMPTY_MAP);
		
		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		dmfCategoryAssignmentReference.setTestRefCategory(dmfCategoryAssignment);
		dObjectContainer.getObjects().add(dmfCategoryAssignmentReference);
		dmfResource.save(Collections.EMPTY_MAP);
		
		//Unload original DVLM resources to ensure that they are properly loaded when saving changes to a DMF resource
		resSei.unload();
		resSeiOther.unload();
		assertFalse("The DVLM resource should be unloaded now to check if saving the DMF resource properly loads this resource again", 
				resSei.isLoaded());
		assertFalse("The DVLM resource should be unloaded now to check if saving the DMF resource properly loads this resource again", 
				resSeiOther.isLoaded());
		
		//Check that its still possible to save changes
		final String changedNameReference = "NewNameReference";
		final String changedNameReferenced = "NewNameReferenced";
		dmfCategoryAssignmentReference.setName(changedNameReference);
		dmfCategoryAssignmentReference.setTestRefCategory(dmfNewTargetCategoryAssignment);
		dmfNewTargetCategoryAssignment.setName(changedNameReferenced);
		dmfResource.save(Collections.EMPTY_MAP);
		dmfResourceOther.save(Collections.EMPTY_MAP);
		
		resSei.load(Collections.EMPTY_MAP);
		resSeiOther.load(Collections.EMPTY_MAP);
		StructuralElementInstance sei = (StructuralElementInstance) resSei.getContents().get(0);
		assertEquals("Sei has correct number of category assignments", 1, sei.getCategoryAssignments().size());
		StructuralElementInstance seiOther = (StructuralElementInstance) resSeiOther.getContents().get(0);
		assertEquals("Sei has correct number of category assignments", 2, seiOther.getCategoryAssignments().size());
		CategoryAssignment referencingCa = sei.getCategoryAssignments().get(0);
		ReferencePropertyInstance refProp = (ReferencePropertyInstance) referencingCa.getPropertyInstances().get(0);
		CategoryAssignment referencedCa = (CategoryAssignment) refProp.getReference();
		assertEquals("Category assignments should be updated even with unloaded DVLM resources", referencingCa.getName(), changedNameReference);
		assertEquals("Category assignments should be updated even with unloaded DVLM resources", referencedCa.getName(), changedNameReferenced);
		
	}
	
	
	@Test
	public void testSaveContainedDObject() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryComposition dmfCategoryAssignmentComposed = TestsFactory.eINSTANCE.createTestCategoryComposition();
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignment = TestsFactory.eINSTANCE.createTestCategoryAllProperty();
		dmfCategoryAssignmentComposed.setTestSubCategory(dmfCategoryAssignment);
		dObjectContainer.getObjects().add(dmfCategoryAssignmentComposed);
		
		final int TEST_VALUE_INT = 10;
		dmfCategoryAssignment.setTestInt(TEST_VALUE_INT);
		dmfResource.save(Collections.EMPTY_MAP);
		
		resSei.unload();
		resSei.load(Collections.EMPTY_MAP);
		
		StructuralElementInstance sei = (StructuralElementInstance) resSei.getContents().get(0);
		assertEquals("Sei has correct number of category assignments", 1, sei.getCategoryAssignments().size());
		CategoryAssignment composedCa = sei.getCategoryAssignments().get(0);
		assertEquals("Category Assignment has correct type", TestCategoryComposition.class.getSimpleName(), composedCa.getType().getName());

		TestCategoryComposition beanTestCategoryAssignmentComposed = new TestCategoryComposition(composedCa);
		TestCategoryAllProperty beanTestCategoryAssignment = beanTestCategoryAssignmentComposed.getTestSubCategory();
		
		assertEquals("DMF property of contained DMF Object is set correctly", TEST_VALUE_INT, beanTestCategoryAssignment.getTestInt());
	}
	
	@Test
	public void testSaveDObjectWithArray() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryIntrinsicArray dmfCategoryAssignmentArray = TestsFactory.eINSTANCE.createTestCategoryIntrinsicArray();
		dObjectContainer.getObjects().add(dmfCategoryAssignmentArray);
		
		final String TEST_VALUE_STRING_0 = "Hello";
		final String TEST_VALUE_STRING_1 = "World";
		dmfCategoryAssignmentArray.getTestStringArrayDynamic().add(TEST_VALUE_STRING_0);
		dmfCategoryAssignmentArray.getTestStringArrayDynamic().add(TEST_VALUE_STRING_1);
		dmfCategoryAssignmentArray.getTestStringArrayStatic().add(TEST_VALUE_STRING_0);
		dmfCategoryAssignmentArray.getTestStringArrayStatic().add(TEST_VALUE_STRING_1);
		dmfResource.save(Collections.EMPTY_MAP);
		
		resSei.unload();
		resSei.load(Collections.EMPTY_MAP);
		
		StructuralElementInstance sei = (StructuralElementInstance) resSei.getContents().get(0);
		assertEquals("Sei has correct number of category assignments", 1, sei.getCategoryAssignments().size());
		CategoryAssignment arrayCa = sei.getCategoryAssignments().get(0);
		assertEquals("Category Assignment has correct type", TestCategoryIntrinsicArray.class.getSimpleName(), arrayCa.getType().getName());

		TestCategoryIntrinsicArray beanTestCategoryAssignmentArray = new TestCategoryIntrinsicArray(arrayCa);
		assertEquals("DMF property same as Bean property", TEST_VALUE_STRING_0, beanTestCategoryAssignmentArray.getTestStringArrayDynamic().get(0).getValue());
		assertEquals("DMF property same as Bean property", TEST_VALUE_STRING_1, beanTestCategoryAssignmentArray.getTestStringArrayDynamic().get(1).getValue());
		assertEquals("DMF property same as Bean property", TEST_VALUE_STRING_0, beanTestCategoryAssignmentArray.getTestStringArrayStatic().get(0).getValue());
		assertEquals("DMF property same as Bean property", TEST_VALUE_STRING_1, beanTestCategoryAssignmentArray.getTestStringArrayStatic().get(1).getValue());
	}
	
	@Test
	public void testSaveDObjectWithReferenceArray() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryReferenceArray dmfCategoryAssignmentArray = TestsFactory.eINSTANCE.createTestCategoryReferenceArray();
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignmentReferenced = TestsFactory.eINSTANCE.createTestCategoryAllProperty();
		dObjectContainer.getObjects().add(dmfCategoryAssignmentArray);
		dObjectContainer.getObjects().add(dmfCategoryAssignmentReferenced);
		
		dmfCategoryAssignmentArray.getTestCategoryReferenceArrayDynamic().add(dmfCategoryAssignmentReferenced);
		dmfCategoryAssignmentArray.getTestCategoryReferenceArrayStatic().add(dmfCategoryAssignmentReferenced);
		dmfResource.save(Collections.EMPTY_MAP);
		
		resSei.unload();
		resSei.load(Collections.EMPTY_MAP);
		
		StructuralElementInstance sei = (StructuralElementInstance) resSei.getContents().get(0);
		assertEquals("Sei has correct number of category assignments", 2, sei.getCategoryAssignments().size());
		CategoryAssignment arrayCa = sei.getCategoryAssignments().get(0);
		CategoryAssignment referencedCa = sei.getCategoryAssignments().get(1);
		assertEquals("Category Assignment has correct type", TestCategoryReferenceArray.class.getSimpleName(), arrayCa.getType().getName());
		assertEquals("Category Assignment has correct type", TestCategoryAllProperty.class.getSimpleName(), referencedCa.getType().getName());
		
		TestCategoryReferenceArray beanTestCategoryAssignmentArray = new TestCategoryReferenceArray(arrayCa);
		TestCategoryAllProperty beanTestCategoryAssignmentReferenced = new TestCategoryAllProperty(referencedCa);
		assertEquals("DMF property same as Bean property", beanTestCategoryAssignmentReferenced, beanTestCategoryAssignmentArray.getTestCategoryReferenceArrayDynamic().get(0));
		assertEquals("DMF property same as Bean property", beanTestCategoryAssignmentReferenced, beanTestCategoryAssignmentArray.getTestCategoryReferenceArrayStatic().get(0));
	}
	
	@Test
	public void testSaveUnresolvedDObject() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.setName("NoDmfConcept");
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setName("NoDmfCat");
		concept.getCategories().add(cat);
		cat.setIsApplicableForAll(true);
		CategoryAssignment noDmfCa = new CategoryInstantiator().generateInstance(cat, "NoDmfCa");
		
		beanTestStructuralElement.getStructuralElementInstance().getCategoryAssignments().add(noDmfCa);
		
		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				ed.getResourceSet().getRepository().getActiveConcepts().add(concept);
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		dmfResource.save(Collections.EMPTY_MAP);
		
		resSei.unload();
		resSei.load(Collections.EMPTY_MAP);
		
		StructuralElementInstance sei = (StructuralElementInstance) resSei.getContents().get(0);
		assertFalse("Sei has a category assignment", sei.getCategoryAssignments().isEmpty());
		CategoryAssignment ca = sei.getCategoryAssignments().get(0);
		
		assertEquals("Category Assignment has correct type", noDmfCa.getType().getName(), ca.getType().getName());
		assertEquals("Name same as ca Name", noDmfCa.getName(), ca.getName());
		assertEquals("UUID same as ca UUID", noDmfCa.getUuid().toString(), ca.getUuid().toString());
	}
	
	@Test
	public void testLoadAndSaveSingleExistingDObject() throws IOException {
		TestStructuralElement beanTestStructuralElement = new TestStructuralElement(concept);
		TestCategoryAllProperty beanTestCategoryAssignment = new TestCategoryAllProperty(concept);
		beanTestStructuralElement.add(beanTestCategoryAssignment);

		ed.getVirSatCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
			}
		});

		ed.saveAll();

		Resource resSei = resSet.getStructuralElementInstanceResource(beanTestStructuralElement.getStructuralElementInstance());
		
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(DmfResource.DMF_FILENAME_EXTENSION, new DmfResourceFactory());

		URI originalSeiUri = resSei.getURI();
		URI dmfSeiUri = originalSeiUri.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

		ResourceSet dmfResourceSet = new ResourceSetImpl();
		Resource dmfResource = dmfResourceSet.getResource(dmfSeiUri, true);
		
		// DMF Resource has a containment object as root content and it only has exactly one obejct as contents
		assertEquals("Make sure there is only one object", 1, dmfResource.getContents().size());
		assertTrue("The first obejct in the resource is correct", dmfResource.getContents().get(0) instanceof DObjectContainer);

		DObjectContainer dObjectContainer = (DObjectContainer) dmfResource.getContents().get(0);
		assertEquals("There is one DObject inside of dObjectContainer", 1, dObjectContainer.getObjects().size());
		assertTrue("Category inside of dObjectContainer", dObjectContainer.getObjects().get(0) instanceof de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty);
		
		de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty dmfCategoryAssignment = (de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty) dObjectContainer.getObjects().get(0);
		final double EPSILON = 0.0000001d;
		
		final double TEST_VALUE_DOUBLE = 10.5d;
		final int TEST_VALUE_INT = 10;
		final String TEST_VALUE_STRING = "Hello";
		final boolean TEST_VALUE_BOOL = true;
		final String TEST_VALUE_RESOURCE = "testdata/test.data";
		final String TEST_BEAN_NAME = "World";
		final EnumTestEnum TEST_VALUE_ENUM = EnumTestEnum.HIGH;
		
		dmfCategoryAssignment.setTestBool(TEST_VALUE_BOOL);
		dmfCategoryAssignment.setTestFloat(TEST_VALUE_DOUBLE);
		dmfCategoryAssignment.setTestInt(TEST_VALUE_INT);
		dmfCategoryAssignment.setTestString(TEST_VALUE_STRING);
		dmfCategoryAssignment.setTestResource(TEST_VALUE_RESOURCE);
		dmfCategoryAssignment.setTestEnum(TEST_VALUE_ENUM);
		dmfCategoryAssignment.setName(TEST_BEAN_NAME);
		
		dmfResource.save(Collections.EMPTY_MAP);
		
		resSei.unload();
		resSei.load(Collections.EMPTY_MAP);
		
		StructuralElementInstance sei = (StructuralElementInstance) resSei.getContents().get(0);
		assertEquals("Sei has exactly one category assignment", 1, sei.getCategoryAssignments().size());
		CategoryAssignment ca = sei.getCategoryAssignments().get(0);
		beanTestCategoryAssignment = new TestCategoryAllProperty(ca);
		
		assertEquals("Name same as bean Name", beanTestCategoryAssignment.getName(), dmfCategoryAssignment.getName());
		assertEquals("UUID same as bean UUID", beanTestCategoryAssignment.getUuid().toString(), dmfCategoryAssignment.getUuid().toString());
		assertEquals("DMF property same as Bean property", beanTestCategoryAssignment.getTestFloatBean().getValueToBaseUnit(), dmfCategoryAssignment.getTestFloat(), EPSILON);
		assertEquals("DMF property same as Bean property", beanTestCategoryAssignment.getTestInt(), dmfCategoryAssignment.getTestInt());
		assertEquals("DMF property same as Bean property", TEST_VALUE_RESOURCE, dmfCategoryAssignment.getTestResource());
		assertEquals("DMF property same as Bean property", beanTestCategoryAssignment.getTestString(), dmfCategoryAssignment.getTestString());
		assertEquals("DMF property same as Bean property", beanTestCategoryAssignment.getTestEnum(), dmfCategoryAssignment.getTestEnum().getLiteral());
		assertEquals("DMF property same as Bean property", beanTestCategoryAssignment.getTestBool(), dmfCategoryAssignment.isTestBool());
	}

}
