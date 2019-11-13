/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.validator;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

import de.dlr.sc.virsat.model.calculation.compute.AEquationBuilderTest;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;

/**
 * Test Cases for for the Validator that makes sure that in certain cases
 * a value and a unit is set to a property value.
 * 
 * @author fisc_ph
 *
 */
public class UnitPropertyValueValidatorTest extends AEquationBuilderTest {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		// Remove all CAs since they are not relevant for this test case
		seiEdSc.getCategoryAssignments().clear();		
	}
	
	@Test
	public void testValidateOnEnum() throws CoreException {
		// Create a Category with an ENUM Property which will be assigned to the SEI
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setIsApplicableForAll(true);
		EnumProperty ep = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		cat.getProperties().add(ep);
		
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(cat, "Test1");
		EnumUnitPropertyInstance eupi = (EnumUnitPropertyInstance) ca.getPropertyInstances().get(0);
		seiEdSc.getCategoryAssignments().add(ca);
		
		UnitPropertyValueValidator validator = new UnitPropertyValueValidator();
		assertTrue("Validationw as ok no QK is set", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 0, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		// Now set a qk which changes expectations now a marker should be set to the resource and the validator should fail
		ep.setQuantityKindName("mass");
		assertFalse("Validation was not ok. QK is set and a value and unit is expected", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 1, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		clearAllMarkers();

		// Now we set a unit only which is not enough, the marker should come back
		eupi.setUnit(QudvFactory.eINSTANCE.createSimpleUnit());
		eupi.setValue(null);
		assertFalse("Validation was not ok. QK is set and a value and unit is expected", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 1, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		clearAllMarkers();

		// Now we set a value only which is not enough, the marker should come back
		eupi.setUnit(null);
		eupi.setValue(PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition());
		assertFalse("Validation was not ok. QK is set and a value and unit is expected", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 1, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		clearAllMarkers();

		// Now we set a both value and unit and there should be no warning about the Property instance
		eupi.setValue(PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition());
		eupi.setUnit(QudvFactory.eINSTANCE.createSimpleUnit());
		assertTrue("Validation is ok. QK expects unit and value, both are set", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 0, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
	}
	
	@Test
	public void testValidateOnUVPI() throws CoreException {
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setIsApplicableForAll(true);
		IntProperty ip = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		cat.getProperties().add(ip);
		
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(cat, "Test1");
		UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) ca.getPropertyInstances().get(0);
		seiEdSc.getCategoryAssignments().add(ca);
		
		UnitPropertyValueValidator validator = new UnitPropertyValueValidator();
		assertTrue("Validationw as ok no QK is set", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 0, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		// Now set a qk which changes expectations now a marker should be set to the resource and the validator should fail
		ip.setQuantityKindName("mass");
		assertFalse("Validation was not ok. QK is set and a value and unit is expected", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 1, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		clearAllMarkers();

		// Now we set a unit only which is not enough, the marker should come back
		uvpi.setUnit(QudvFactory.eINSTANCE.createSimpleUnit());
		uvpi.setValue(null);
		assertFalse("Validation was not ok. QK is set and a value and unit is expected", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 1, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		clearAllMarkers();

		// Same for an empty string
		uvpi.setUnit(QudvFactory.eINSTANCE.createSimpleUnit());
		uvpi.setValue("");
		assertFalse("Validation was not ok. QK is set and a value and unit is expected", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 1, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		clearAllMarkers();

		// Now we set a value only which is not enough, the marker should come back
		uvpi.setUnit(null);
		uvpi.setValue("1234");
		assertFalse("Validation was not ok. QK is set and a value and unit is expected", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 1, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		clearAllMarkers();

		// Now we set a both value and unit and there should be no warning about the Property instance
		uvpi.setUnit(QudvFactory.eINSTANCE.createSimpleUnit());
		uvpi.setValue("1234");
		assertTrue("Validation is ok. QK expects unit and value, both are set", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 0, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
	}
	
	@Test
	public void testValidateMultipleViolations() throws CoreException {
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setIsApplicableForAll(true);
		IntProperty ip = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		ip.setQuantityKindName("mass");
		cat.getProperties().add(ip);
		
		CategoryAssignment ca1 = new CategoryInstantiator().generateInstance(cat, "Test1");
		UnitValuePropertyInstance uvpi1 = (UnitValuePropertyInstance) ca1.getPropertyInstances().get(0);
		seiEdSc.getCategoryAssignments().add(ca1);

		CategoryAssignment ca2 = new CategoryInstantiator().generateInstance(cat, "Test1");
		UnitValuePropertyInstance uvpi2 = (UnitValuePropertyInstance) ca2.getPropertyInstances().get(0);
		seiEdSc.getCategoryAssignments().add(ca2);

		
		UnitPropertyValueValidator validator = new UnitPropertyValueValidator();
		
		// Now we set a unit only which is not enough, the marker should come back
		uvpi1.setUnit(QudvFactory.eINSTANCE.createSimpleUnit());
		uvpi1.setValue(null);
		uvpi2.setUnit(QudvFactory.eINSTANCE.createSimpleUnit());
		uvpi2.setValue(null);
		assertFalse("Validation was not ok. QK is set and a value and unit is expected", validator.validate(seiEdSc));		
		assertEquals("Correct amount of markers", 2, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
	}
}
