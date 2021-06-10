/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources.modelaccess;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.server.resources.AModelAccessResourceTest;
import de.dlr.sc.virsat.server.resources.ModelAccessResource;

public class PropertyResourceTest extends AModelAccessResourceTest {
	
	@Test
	public void testPropertyStringGet() throws Exception {
		testGetProperty(beanString);
	}
	
	@Test
	public void testPropertyBoolGet() throws Exception {
		testGetProperty(beanBool);
	}
	
	@Test
	public void testPropertyEnumGet() throws Exception {
		testGetProperty(beanEnum);
	}
	
	@Test
	public void testPropertyFloatGet() throws Exception {
		testGetProperty(beanFloat);
	}
	
	@Test
	public void testPropertyIntGet() throws Exception {
		testGetProperty(beanInt);
	}
	
	@Test
	public void testPropertyResourceGet() throws Exception {
		testGetProperty(beanResource);
	}
	
	@Test
	public void testPropertyReferenceGet() throws Exception {
		testGetProperty(beanReferenceProp, new Class[] {beanReferenceProp.getClass(), beanString.getClass()});
		testGetProperty(beanReferenceCa, new Class[] {beanReferenceProp.getClass(), tcAllProperty.getClass()});
	}
	
	@Test
	public void testPropertyComposedGet() throws Exception {
		testGetProperty(beanComposed, new Class[] {beanComposed.getClass(), tcAllProperty.getClass()});
	}
	
	@Test
	public void testPropertyBoolPut() throws Exception {
		testPutProperty(beanBool);
	}
	
	@Test
	public void testPropertyStringPut() throws Exception {
		testPutProperty(beanString);
	}
	
	@Test
	public void testPropertyEnumPut() throws Exception {
		testPutProperty(beanEnum);
	}
	
	@Test
	public void testPropertyFloatPut() throws Exception {
		testPutProperty(beanFloat);
	}
	
	@Test
	public void testPropertyIntPut() throws Exception {
		testPutProperty(beanInt);
	}
	
	@Test
	public void testPropertyResourcePut() throws Exception {
		testPutProperty(beanResource);
	}
	
	@Test
	public void testPropertyReferencePut() throws Exception {
		testPutProperty(beanReferenceProp);
		testPutProperty(beanReferenceCa);
	}
	
	@Test
	public void testPropertyComposedPut() throws JAXBException {
		
		// Manually marshall the Class because Entity.entity doesn't marshall
		// the TestCategoryAllProperty right because of generics
		JAXBUtility jaxbUtility = new JAXBUtility(new Class[] {BeanPropertyComposed.class, TestCategoryAllProperty.class});
		StringWriter sw = new StringWriter();
		jaxbUtility.getJsonMarshaller().marshal(beanComposed, sw);
		String jsonIn = sw.toString();
		
		Response response = webTarget
				.path(ModelAccessResource.PROPERTY)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.json(jsonIn));
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
	
	@Test
	public void testErrorResponses() {
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.PROPERTY + "/unknown").get());
		
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.PROPERTY + "/unknown/" + PropertyResource.RESOURCE).get());
		// A boolean property doesn't have a resource
		assertBadRequestResponse(
				getTestRequestBuilder(ModelAccessResource.PROPERTY + "/" + beanBool.getUuid().toString() + "/" + PropertyResource.RESOURCE).get(),
				PropertyResource.PROPERTY_DOES_NOT_CONTAIN_A_SERVEABLE_RESOURCE);
	}
	
	@Test
	public void testResourceGet() throws CoreException {
		// Initial the bean does not contain a valid resource
		assertBadRequestResponse(
				getTestRequestBuilder(ModelAccessResource.PROPERTY + "/" + beanResource.getUuid().toString() + "/" + PropertyResource.RESOURCE).get(),
				PropertyResource.PROPERTY_DOES_NOT_CONTAIN_A_SERVEABLE_RESOURCE);
		
		// Now create a valid file
		IFile file = ed.getResourceSet().getProject().getFile("file[2].xls");
		file.create(new ByteArrayInputStream(TEST_STRING.getBytes()), IResource.FORCE, null);
		
		// Add it to the resource
		Command setCommand = beanResource.setValue(ed, URI.createPlatformResourceURI(file.getFullPath().toString(), true));
		ed.getCommandStack().execute(setCommand);
		
		// Check that we can access the contents via the API
		Response response = getTestRequestBuilder(ModelAccessResource.PROPERTY + "/" + beanResource.getUuid().toString() + "/" + PropertyResource.RESOURCE).get();
		assertEquals(HttpStatus.OK_200, response.getStatus());
		
		String entity = response.readEntity(String.class);
		assertEquals(TEST_STRING, entity);
	}
}
