/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;

public class ModelAccessResourceTest extends AServerRepositoryTest {

	private static VirSatResourceSet resourceSet;
	private static ValuePropertyInstance vpi;

	@BeforeClass
	public static void setUpModel() throws Exception {

		VirSatTransactionalEditingDomain ed = testServerRepository.getEd();
		resourceSet = ed.getResourceSet();
		
		RecordingCommand recordingCommand = new RecordingCommand(ed) {

			@Override
			protected void doExecute() {
				vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
				Resource resourceImpl = new ResourceImpl();
				resourceSet.getResources().add(resourceImpl);
				resourceImpl.getContents().add(vpi);
			}

		};
		ed.getCommandStack().execute(recordingCommand);		
		
	}
	
	@Test
	public void test() {
		
		Response serverResponse = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.request()
				.get();
		
		System.out.println(serverResponse.toString());
		assertEquals(serverResponse.getStatus(), HttpStatus.OK_200);
		
		String entity = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.request()
				.get(String.class);
		System.out.println(entity);
	}
	
	@Test
	public void testPost() {
		BeanPropertyString testBean = new BeanPropertyString(vpi);
		Response response = webTarget.path(ModelAccessResource.PATH)
				.path(projectName)
				.path(ModelAccessResource.PROPERTY)
				.request()
				.post(Entity.entity(testBean, MediaType.APPLICATION_JSON_TYPE));
		System.out.println(response.toString());
		assertEquals(response.getStatus(), HttpStatus.OK_200);
	}
}
