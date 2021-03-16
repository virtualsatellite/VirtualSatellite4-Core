/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.reqif;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.rmf.reqif10.AttributeDefinition;
import org.eclipse.rmf.reqif10.AttributeDefinitionString;
import org.eclipse.rmf.reqif10.AttributeDefinitionXHTML;
import org.eclipse.rmf.reqif10.AttributeValue;
import org.eclipse.rmf.reqif10.AttributeValueString;
import org.eclipse.rmf.reqif10.AttributeValueXHTML;
import org.eclipse.rmf.reqif10.ReqIF10Factory;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.rmf.reqif10.SpecObject;
import org.eclipse.rmf.reqif10.SpecObjectType;
import org.eclipse.rmf.reqif10.XhtmlContent;
import org.eclipse.rmf.reqif10.common.util.ReqIF10XHtmlContentAdapter;
import org.eclipse.rmf.reqif10.common.util.ReqIF10XHtmlContentAdapterFactory;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;

/**
 * Test class for the ReqIF utils
 *
 */
public class ReqIfUtilsTest extends AConceptProjectTestCase {
	
	private static final String TEST_NATIVE_ATTRIBUTE_NAME = "Native";
	private static final String TEST_ID_ATTRIBUTE_NAME = "ID";
	private static final String TEST_FIXED_ATTRIBUTE_NAME = "Fixed";
	private static final String TEST_DEFAULT_ATTRIBUTE_NAME = "Normal";
	private static final String TEST_DEFAULT_REQ_TYPE_NAME = "ReqType";
	
	private static final String TEST_ID_ATTRIBUTE_VALUE = "52";
	private static final String TEST_DEFAULT_ATTRIBUTE_VALUE = "Some requirement value";
	private static final String TEST_GROUP_NAME = "RequirementGroup";
	private static final String TEST_REQUIREMENT_NAME = "TestRequirement";
	private static final int TEST_FIXED_INDEX = 0;
	private AttributeValue nativeAttributeValue = null;
	private ReqIfUtils reqIfUtils;
	private Concept reqConcept;
	private RequirementAttribute attDefLocal;
	private RequirementAttribute attIdDefLocal;
	private AttributeDefinition attDefReqIf;
	private AttributeDefinition attDefIdReqIf;
	private de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue attValueLocal;
	
	class TestMapping implements INativeRequirementAttributeMapping {

		@Override
		public boolean isNativeAttribute(AttributeDefinition reqIFAttDef) {
			if (reqIFAttDef.getLongName().equals(TEST_NATIVE_ATTRIBUTE_NAME)) {
				return true;
			}
			return false;
		}

		@Override
		public boolean isIdentifierAttribute(AttributeDefinition reqIFAttDef) {
			if (reqIFAttDef.getLongName().equals(TEST_ID_ATTRIBUTE_NAME)) {
				return true;
			}
			return false;
		}

		@Override
		public Integer getNativeIndex(AttributeDefinition reqIfAttDef) {
			if (reqIfAttDef.getLongName().equals(TEST_FIXED_ATTRIBUTE_NAME)) {
				return TEST_FIXED_INDEX;
			}
			return null;
		}

		@Override
		public Command setNativeValues(EditingDomain editingDomain, Requirement requirement, AttributeValue attValue) {
			nativeAttributeValue = attValue;
			return null;
		}

		@Override
		public void setNativeValues(Requirement requirement, AttributeValue attValue) {
			nativeAttributeValue = attValue;
		}
		
	}
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		reqConcept = loadConceptFromPlugin(Activator.getPluginId());
		List<INativeRequirementAttributeMapping> mappings = new ArrayList<INativeRequirementAttributeMapping>();
		mappings.add(new TestMapping());
		reqIfUtils = new ReqIfUtils(mappings);
	}
	
	@Test
	public void testIsIdentifier() {
		
		AttributeDefinitionString attDefIdentifier = createIdentifierAttibuteDefinition();
		AttributeDefinitionString attDefNonIdentifier = createDefaultAttibuteDefinition();
		
		assertTrue("Attribute Value should be mapped as identifying attribute", reqIfUtils.isIdentifier(attDefIdentifier));
		assertFalse("Attribute Value should not be mapped as identifying attribute", reqIfUtils.isIdentifier(attDefNonIdentifier));
		
	}
	
	@Test
	public void testNativeIndex() {
		
		AttributeDefinitionString attDefFixed = createFixedAttibuteDefinition();
		AttributeDefinitionString attDefault = createDefaultAttibuteDefinition();
		
		int returnedIndex = reqIfUtils.nativeIndex(attDefFixed);
		assertEquals("Index should be as definied in the test mapping class", TEST_FIXED_INDEX, returnedIndex);
		assertNull("For the defualt attribute there should be no index defined", reqIfUtils.nativeIndex(attDefault));

	}
	
	@Test
	public void testHasNativeAttributeImpl() {
		
		AttributeDefinitionString attDefNative = createNativeAttibuteDefinition();
		AttributeDefinitionString attDefNonNative = createDefaultAttibuteDefinition();
		
		assertTrue("Attribute Value should have a mapping defined", reqIfUtils.hasNativeAttributeImpl(attDefNative));
		assertFalse("Attribute Value should not have a mappping defined", reqIfUtils.hasNativeAttributeImpl(attDefNonNative));

	}
	
	@Test
	public void testSetNativeRequirementAttributeValue() {
		AttributeDefinitionString nativeAttDef = createNativeAttibuteDefinition();
		AttributeValueString attValue = createAttributeValue(nativeAttDef);
		
		assertNull("Native attribute field should not have been set yet", nativeAttributeValue);
		reqIfUtils.setNativeRequirementAttributeValue(null, attValue, nativeAttDef);
		assertEquals("Attribute value should have been loaded into native value field", nativeAttributeValue, attValue);
		
		
		
		AttributeDefinitionString nonNativeAttDef = createDefaultAttibuteDefinition();
		AttributeValueString attValueOther = createAttributeValue(nonNativeAttDef);
		
		reqIfUtils.setNativeRequirementAttributeValue(null, attValueOther, nonNativeAttDef);
		assertNotEquals("New attribute value should have not have been loaded into native value field", nativeAttributeValue, attValueOther);
		assertEquals("Should have been keept the old value", nativeAttributeValue, attValue);

	}
	
	@Test
	public void testContains() {
		Requirement testReq = new Requirement(reqConcept);
		IBeanList<de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue> beanList = testReq.getElements();
		
		de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue localValue = new de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue(reqConcept);
		final String ATT_NAME = "attName";
		localValue.setName(ATT_NAME);
		
		assertFalse("Not yet added", reqIfUtils.contains(beanList, ATT_NAME));
		testReq.getElements().add(localValue);
		assertTrue("Att 1 should be found", reqIfUtils.contains(beanList, ATT_NAME));
	}
	
	@Test
	public void testFindExistingRequirementObject() throws IOException {
		RequirementsSpecification reqSpecLocal = new RequirementsSpecification(reqConcept);
		
		SpecHierarchy reqIfObject = createBasicReqIfRequirement(TEST_DEFAULT_ATTRIBUTE_VALUE, TEST_ID_ATTRIBUTE_VALUE);
		assertNull("No exisiting element should be found", reqIfUtils.findExisting(reqSpecLocal.getRequirements(), reqIfObject));
		
		Requirement localReqObject = createBasicLocalRequirement(TEST_DEFAULT_ATTRIBUTE_VALUE, TEST_ID_ATTRIBUTE_VALUE);
		Requirement someOtherLocalReq = createBasicLocalRequirement("Something else", "435");
		reqSpecLocal.getRequirements().add(localReqObject);
		reqSpecLocal.getRequirements().add(someOtherLocalReq);
		assertEquals("Now mapping should be found to local req with same ID", localReqObject, reqIfUtils.findExisting(reqSpecLocal.getRequirements(), reqIfObject));
		
		SpecHierarchy reqIfObjectGroup = createBasicRequriementGroup(TEST_GROUP_NAME);
		RequirementGroup group = new RequirementGroup(reqConcept);
		group.setName(TEST_GROUP_NAME);
		assertNull("No exisiting group element should be found", reqIfUtils.findExisting(reqSpecLocal.getRequirements(), reqIfObjectGroup));
		reqSpecLocal.getRequirements().add(group);
		assertEquals("Group should be found", group, reqIfUtils.findExisting(reqSpecLocal.getRequirements(), reqIfObjectGroup));


	}
	
	@Test
	public void testFindExistingAttributeValue() throws IOException {
		
		Requirement localRequirement = new Requirement(reqConcept);
		createBasicReqIfRequirement(TEST_DEFAULT_ATTRIBUTE_VALUE, TEST_ID_ATTRIBUTE_VALUE); // Creates attDefReqIf
		
		assertNull("Local requirement is new so there should not be an attribute", reqIfUtils.findExisting(localRequirement, attDefReqIf));
		localRequirement = createBasicLocalRequirement(TEST_DEFAULT_ATTRIBUTE_VALUE, TEST_ID_ATTRIBUTE_VALUE);
		
		assertEquals("Now there should be one", attValueLocal, reqIfUtils.findExisting(localRequirement, attDefReqIf));

	}
	
	@Test
	public void testFindAttributeDefinition() throws IOException {
		Requirement localRequirement = new Requirement(reqConcept);
		RequirementType reqType = new RequirementType(reqConcept);
		localRequirement.setReqType(reqType);
		createBasicReqIfRequirement(TEST_DEFAULT_ATTRIBUTE_VALUE, TEST_ID_ATTRIBUTE_VALUE); // Creates attDefReqIf
		
		assertNull("Local requirement is new so there should not be an attribute", reqIfUtils.findAttributeDefinition(localRequirement, attDefIdReqIf));
		
		localRequirement = createBasicLocalRequirement(TEST_DEFAULT_ATTRIBUTE_VALUE, TEST_ID_ATTRIBUTE_VALUE); // Creates attDefLocal, which should be mapped to reqIF attribute
		
		assertEquals("Now there should be one", attDefLocal, reqIfUtils.findAttributeDefinition(localRequirement, attDefReqIf));

	}
	
	@Test
	public void testGetReqIFRequirementIdentifier() throws IOException {
		SpecHierarchy reqIFReq = createBasicReqIfRequirement(TEST_DEFAULT_ATTRIBUTE_VALUE, TEST_ID_ATTRIBUTE_VALUE);
		
		assertEquals("ID should be as created", TEST_ID_ATTRIBUTE_VALUE, reqIfUtils.getReqIFRequirementIdentifier(reqIFReq));
	}
	
	@Test
	public void testGetReqIFRequirementName() throws IOException {
		SpecObject specObject = ReqIF10Factory.eINSTANCE.createSpecObject();
		SpecHierarchy hierarchyObject = ReqIF10Factory.eINSTANCE.createSpecHierarchy();
		hierarchyObject.setObject(specObject);
		
		assertNull("New ReqIF does not have a name configured", reqIfUtils.getReqIFRequirementName(hierarchyObject));
		
		hierarchyObject = createBasicReqIfRequirement(TEST_DEFAULT_ATTRIBUTE_VALUE, TEST_ID_ATTRIBUTE_VALUE);
		setReqIFName(hierarchyObject, TEST_REQUIREMENT_NAME);
		assertEquals("Name should be as set", TEST_REQUIREMENT_NAME, reqIfUtils.getReqIFRequirementName(hierarchyObject));
		
		hierarchyObject = createBasicRequriementGroup(TEST_GROUP_NAME);
		assertEquals("Name should be as set", TEST_GROUP_NAME, reqIfUtils.getReqIFRequirementName(hierarchyObject));
	}
	
	@Test
	public void testCleanAttName() {
		String crazyAttributeName = " .. \\ \n\r " + System.lineSeparator() + TEST_DEFAULT_ATTRIBUTE_NAME + " .. / ";
		
		String cleanedName = reqIfUtils.cleanAttName(crazyAttributeName);
		assertEquals("String should have been cleaned", TEST_DEFAULT_ATTRIBUTE_NAME, cleanedName);
	}
	
	private AttributeDefinitionString createIdentifierAttibuteDefinition() {
		AttributeDefinitionString attDefIdentifier = ReqIF10Factory.eINSTANCE.createAttributeDefinitionString();
		attDefIdentifier.setLongName(TEST_ID_ATTRIBUTE_NAME);
		return attDefIdentifier;
	}
	
	private AttributeDefinitionString createFixedAttibuteDefinition() {
		AttributeDefinitionString attDefIdentifier = ReqIF10Factory.eINSTANCE.createAttributeDefinitionString();
		attDefIdentifier.setLongName(TEST_FIXED_ATTRIBUTE_NAME);
		return attDefIdentifier;
	}
	
	private AttributeDefinitionString createNativeAttibuteDefinition() {
		AttributeDefinitionString attDefIdentifier = ReqIF10Factory.eINSTANCE.createAttributeDefinitionString();
		attDefIdentifier.setLongName(TEST_NATIVE_ATTRIBUTE_NAME);
		return attDefIdentifier;
	}
	
	private AttributeDefinitionString createDefaultAttibuteDefinition() {
		AttributeDefinitionString attDefIdentifier = ReqIF10Factory.eINSTANCE.createAttributeDefinitionString();
		attDefIdentifier.setLongName(TEST_DEFAULT_ATTRIBUTE_NAME);
		return attDefIdentifier;
	}
	
	private AttributeValueString createDefaultAttributeValue() {
		AttributeValueString attValue = ReqIF10Factory.eINSTANCE.createAttributeValueString();
		AttributeDefinitionString attDef = createDefaultAttibuteDefinition();
		attValue.setDefinition(attDef);
		return attValue;
	}
	
	private AttributeValueString createIdAttributeValue() {
		AttributeValueString attValue = ReqIF10Factory.eINSTANCE.createAttributeValueString();
		AttributeDefinitionString attDef = createIdentifierAttibuteDefinition();
		attValue.setDefinition(attDef);
		return attValue;
	}
	
	private AttributeValueString createAttributeValue(AttributeDefinitionString attDef) {
		AttributeValueString attValue = ReqIF10Factory.eINSTANCE.createAttributeValueString();
		attValue.setDefinition(attDef);
		return attValue;
	}
	
	/**
	 * Create a basic ReqIf requirement type
	 * @return the req type
	 */
	private SpecObjectType createBasicReqIfRequirementType() {
		SpecObjectType reqType = ReqIF10Factory.eINSTANCE.createSpecObjectType();
		reqType.setLongName(TEST_DEFAULT_REQ_TYPE_NAME);
		
		attDefReqIf = createDefaultAttibuteDefinition();
		reqType.getSpecAttributes().add(attDefReqIf);
		
		attDefIdReqIf = createIdentifierAttibuteDefinition();
		reqType.getSpecAttributes().add(attDefIdReqIf);
		
		return reqType;
	}
	
	/**
	 * Create a basic requirement object
	 * @param value the default value of this requirement
	 * @param id the id value
	 * @return the requirement
	 * @throws IOException 
	 */
	private SpecHierarchy createBasicReqIfRequirement(String value, String id) throws IOException {
		SpecObject specObject = ReqIF10Factory.eINSTANCE.createSpecObject();
		SpecHierarchy hierarchyObject = ReqIF10Factory.eINSTANCE.createSpecHierarchy();
		SpecObjectType type = createBasicReqIfRequirementType();
		specObject.setType(type);
		
		AttributeValueString stringDefaultValue = createDefaultAttributeValue();
		stringDefaultValue.setTheValue(value);
		specObject.getValues().add(stringDefaultValue);
		
		AttributeValueString stringIdValue = createIdAttributeValue();
		stringIdValue.setTheValue(id);
		specObject.getValues().add(stringIdValue);
		
		hierarchyObject.setObject(specObject);
		return hierarchyObject;
	}
	
	/**
	 * Create a basic ReqIF requirement group
	 * @param name the name of the group
	 * @return the hierarchy element
	 * @throws IOException 
	 */
	private SpecHierarchy createBasicRequriementGroup(String name) throws IOException {
		// Add child element
		SpecHierarchy groupHierarchyObject = createBasicReqIfRequirement("Group", "7");
		SpecHierarchy childHierarchyObject = createBasicReqIfRequirement(TEST_DEFAULT_ATTRIBUTE_VALUE, TEST_ID_ATTRIBUTE_VALUE);
		groupHierarchyObject.getChildren().add(childHierarchyObject);
		
		// Configure group
		setReqIFName(groupHierarchyObject, name);
		
		return groupHierarchyObject;
	}
	
	/**
	 * Set the reqIF name attribute 
	 * @param reqIFObject the reqIF requirement object to be configured
	 * @param name the name
	 * @throws IOException
	 */
	private void setReqIFName(SpecHierarchy reqIFObject, String name) throws IOException {
		ReqIF10XHtmlContentAdapterFactory factory = new ReqIF10XHtmlContentAdapterFactory();
		AttributeDefinitionXHTML nameAttribute = ReqIF10Factory.eINSTANCE.createAttributeDefinitionXHTML();
		nameAttribute.setLongName(ReqIfUtils.REQIF_NAME_ATTRIBUTE_NAME);
		AttributeValueXHTML attValue = ReqIF10Factory.eINSTANCE.createAttributeValueXHTML();
		attValue.setDefinition(nameAttribute);

		XhtmlContent xhtmlContent = ReqIF10Factory.eINSTANCE.createXhtmlContent();
		ReqIF10XHtmlContentAdapter adapter = (ReqIF10XHtmlContentAdapter) factory.adapt(xhtmlContent, ReqIF10XHtmlContentAdapter.class);
		adapter.setXhtmlString("<div xmlns=\"http://www.w3.org/1999/xhtml\">" + name + "</div>"); //$NON-NLS-1$
		attValue.setTheValue(xhtmlContent);
		
		reqIFObject.getObject().getValues().add(attValue);
	}
	
	/**
	 * Create a basic requirement type
	 * @return the requirement type
	 */
	private RequirementType createBasicLocalRequirementType() {
		RequirementType reqType = new RequirementType(reqConcept);
		reqType.setName(TEST_DEFAULT_REQ_TYPE_NAME);
		attDefLocal = new RequirementAttribute(reqConcept);
		attDefLocal.setName(TEST_DEFAULT_ATTRIBUTE_NAME);
		attDefLocal.setType(RequirementAttribute.TYPE_String_NAME);
		attIdDefLocal = new RequirementAttribute(reqConcept);
		attIdDefLocal.setName(TEST_ID_ATTRIBUTE_NAME);
		attIdDefLocal.setType(RequirementAttribute.TYPE_Identifier_NAME);
		reqType.getAttributes().add(attDefLocal);
		reqType.getAttributes().add(attIdDefLocal);
		return reqType;
	}
	
	/**
	 * Create a basic requirement 
	 * @param value the default value of this requirement
	 * @param id the id value of this requirement
	 * @return the requirement instance
	 */
	private Requirement createBasicLocalRequirement(String value, String id) {
		Requirement testReq = new Requirement(reqConcept);
		testReq.setReqType(createBasicLocalRequirementType());
		
		attValueLocal = new de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue(reqConcept);
		attValueLocal.setName(de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue.ATTRIBUTE_NAME_PREFIX + TEST_DEFAULT_ATTRIBUTE_NAME);
		attValueLocal.setValue(value);
		attValueLocal.setAttType(attDefLocal);
		testReq.getElements().add(attValueLocal);
		
		de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue attId = new de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue(reqConcept);
		attId.setName(de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue.ATTRIBUTE_NAME_PREFIX + TEST_ID_ATTRIBUTE_NAME);
		attId.setValue(id);
		attId.setAttType(attIdDefLocal);
		testReq.getElements().add(attId);
		
		testReq.updateNameFromAttributes();
		
		return testReq;
	}
	

}
