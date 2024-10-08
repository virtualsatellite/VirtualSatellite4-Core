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

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.command.CreateSeiResourceAndFileCommand;
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;

public class TransactionalJsonProviderTest extends AServerRepositoryTest {

	private TransactionalJsonProvider provider;
	private BeanStructuralElementInstance testBean;
	private Class<?> type;
	private Set<Class<?>> beanClass = new HashSet<>();
	private MediaType mediaType;
	private String testString = "testString";
	private String newString = "new";	
	private StructuralElementInstance testSei;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		UserRegistry.getInstance().setSuperUser(true);
		
		provider = new TransactionalJsonProvider();
		provider.setServerRepository(testServerRepository);
		VirSatTransactionalEditingDomain ed = testServerRepository.getEd();
		
		StructuralElement testSe = StructuralFactory.eINSTANCE.createStructuralElement();
		testSe.setIsRootStructuralElement(true);
		testSe.setName("testSe");

		testSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		testSei.setType(testSe);
		testSei.setName(testString);
		
		Command addSeiToRepo = AddCommand.create(ed, ed.getResourceSet().getRepository(), 
				DVLMPackage.eINSTANCE.getRepository_RootEntities(), testSei);
		ed.getCommandStack().execute(addSeiToRepo);
		
		Command createSei = new CreateSeiResourceAndFileCommand(ed.getResourceSet(), testSei);
		ed.getCommandStack().execute(createSei);

		ed.getResourceSet().saveAllResources(new NullProgressMonitor(), ed);

		testBean = new BeanStructuralElementInstance(testSei);

		type = BeanStructuralElementInstance.class;
		beanClass.add(type);
		
		mediaType = MediaType.APPLICATION_JSON_TYPE;
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		
		UserRegistry.getInstance().setSuperUser(false);
	}

	/**
	 * Call writeTo and assert that the the output String contains the test value
	 * @return the output String
	 * @throws WebApplicationException
	 * @throws IOException
	 */
	private String writeToAndAssert(String testString) throws WebApplicationException, IOException {
		OutputStream entityStream = new ByteArrayOutputStream();
		provider.writeTo(testBean, type, type, null, mediaType, (MultivaluedMap<String, Object>) null, entityStream);
		
		String output = entityStream.toString();
		assertTrue(output.contains(testString));
		
		return output;
	}

	// Test the marshalling
	@Test
	public void testWriteTo() throws WebApplicationException, IOException, NoHeadException, GitAPIException, InterruptedException {
		writeToAndAssert(testString);
	}
	
	// Test the unmarshalling
	@SuppressWarnings("unchecked")
	@Test
	public void testReadFrom() throws WebApplicationException, IOException, NoHeadException, GitAPIException, InterruptedException {
		
		String output = writeToAndAssert(testString);
		
		// Write did not cause any changes
		InputStream entityStream = new ByteArrayInputStream(output.getBytes());
		provider.readFrom((Class<Object>) type, type, null, mediaType, null, entityStream);
		
		assertEquals(testString, testBean.getName());
		
		// Changes
		output = output.replace(testString, newString);
		entityStream = new ByteArrayInputStream(output.getBytes());
		provider.readFrom((Class<Object>) type, type, null, mediaType, null, entityStream);
		
		assertEquals(newString, testBean.getName());
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
