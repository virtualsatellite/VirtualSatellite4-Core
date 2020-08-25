/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.dataaccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

public class CustomJsonProviderTest extends AProjectTestCase {

	private CustomJsonProvider provider;
	private BeanPropertyString testBean;
	private Class<?> type;
	private Set<Class<?>> beanClass = new HashSet<>();
	private MediaType mediaType;
	private String testString = "test";

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		
		provider = new CustomJsonProvider();
		provider.setEd(editingDomain);
		
		StructuralElement testSe = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance testSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		Category testCategory = CategoriesFactory.eINSTANCE.createCategory();
		CategoryAssignment testCa = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		StringProperty testProperty = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		ValuePropertyInstance testPropertyInstance = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		
		testPropertyInstance.setType(testProperty);
		
		testCategory.setIsApplicableForAll(true);
		testCategory.getProperties().add(testProperty);
		
		testCa.setType(testCategory);
		testCa.getPropertyInstances().add(testPropertyInstance);
		
		testSe.setIsRootStructuralElement(true);
		
		testSei.setType(testSe);
		testSei.getCategoryAssignments().add(testCa);
		testSei.getCategoryAssignments();
		
		RecordingCommand recordingCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				rs.getAndAddStructuralElementInstanceResource(testSei);
			}
		};
		
		editingDomain.getVirSatCommandStack().execute(recordingCommand);
		
		testBean = new BeanPropertyString(testPropertyInstance);
		editingDomain.getVirSatCommandStack().execute(testBean.setValue(editingDomain, testString));

		type = BeanPropertyString.class;
		beanClass.add(type);
		
		mediaType = MediaType.APPLICATION_JSON_TYPE;
	}

	/**
	 * Call writeTo and assert that the the output String is as expected
	 * @return the output String
	 * @throws WebApplicationException
	 * @throws IOException
	 */
	private String writeToAndAssert() throws WebApplicationException, IOException {
		OutputStream entityStream = new ByteArrayOutputStream();
		provider.writeTo(testBean, type, type, null, mediaType, (MultivaluedMap<String, Object>) null, entityStream);
		
		String output = entityStream.toString();
		assertTrue(output.contains(testString));
		
		return output;
	}

	// Test the marshalling
	@Test
	public void testWriteTo() throws WebApplicationException, IOException {
		writeToAndAssert();
	}
	
	// Test the unmarshalling
	@SuppressWarnings("unchecked")
	@Test
	public void testReadFrom() throws WebApplicationException, IOException {
		String output = writeToAndAssert();
		
		StringBuffer buf = new StringBuffer(output);
		InputStream entityStream = new ByteArrayInputStream(buf.toString().getBytes());
		BeanPropertyString bean = (BeanPropertyString) provider.readFrom((Class<Object>) type, type, null, mediaType, null, entityStream);
		assertEquals(testBean, bean);
	}

	@Test
	public void testGetJAXBContext() throws JAXBException {
		JAXBContext context = provider.getJAXBContext(beanClass, null, mediaType, null);
		assertNotNull(context);
		
		JAXBContext context2 = provider.getJAXBContext(beanClass, null, mediaType, null);
		assertSame("Context got cashed", context, context2);
		
		beanClass.add(BeanPropertyInt.class);
		JAXBContext context3 = provider.getJAXBContext(beanClass, null, mediaType, null);
		assertNotSame("New context because the classes to register changed", context, context3);
	}

}
