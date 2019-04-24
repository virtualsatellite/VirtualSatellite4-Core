/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.types.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * 
 * @author fisc_ph
 *
 */
public class VirSatUuidTest {

	@Test
	public void testVirSatUuid() {
		VirSatUuid uuidOne = new VirSatUuid("");
		assertFalse("UUID is not empty", uuidOne.toString().isEmpty());
	}

	@Test
	public void testVirSatUuidToString() {
		String uuid = UUID.randomUUID().toString();
		VirSatUuid uuidTwo = new VirSatUuid(uuid);
		
		assertEquals("UUID is correctly set", uuid, uuidTwo.toString());
	}
	
	@Test
	public void testEqualsObject() {
		StructuralElementInstance seOne = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seTwo = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		Object otherObject = new Object();
		
		// two uuids never be equals, apart from the case that a uuid compares to himself
		assertTrue("UUIDs have to be equals", seOne.getUuid().equals(seOne.getUuid()));
		assertFalse("UUIDs have to be different", seOne.getUuid().equals(seTwo.getUuid()));
		assertFalse("One object has no UUID thus it is different", seOne.getUuid().equals(otherObject));
	}

	@Test
	public void testUuidUniquenessForStructuralElements() {
		StructuralElementInstance seOne = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seTwo = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		assertFalse("UUIDs have to be different", seOne.getUuid().equals(seTwo.getUuid()));
	}
	
	@Test
	public void testRoundTrip() throws IOException {
		// Create some TestContent with a UUID
		StructuralElementInstance sc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		assertFalse("UUID is not empty", sc.getUuid().toString().isEmpty());
		
		// Serialize this TestContent
		ResourceSet resourceSetSerialize = new ResourceSetImpl();
		resourceSetSerialize.getResourceFactoryRegistry().getProtocolToFactoryMap().put("dvlmtest", new XMIResourceFactoryImpl());
		Resource resourceSerialize = resourceSetSerialize.createResource(URI.createURI("dvlmTest/UuidRoundTripSerialize.dvlmtest"));
		resourceSerialize.getContents().add(sc);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		resourceSerialize.save(outputStream, null);
		String serializedModel = outputStream.toString();

		// Now deserialize it and compare UUID
		ResourceSet resourceSetDeSerialize = new ResourceSetImpl();
		resourceSetDeSerialize.getResourceFactoryRegistry().getProtocolToFactoryMap().put("dvlmtest", new XMIResourceFactoryImpl());
		Resource resourceDeSerialize =	resourceSetDeSerialize.createResource(URI.createURI("dvlmTest/UuidRoundTripDeserialize.dvlmtest"));
		
		ByteArrayInputStream inputStream = new ByteArrayInputStream(serializedModel.getBytes());
		resourceDeSerialize.load(inputStream, null);
		
		// Now compare the objects
		StructuralElementInstance loadedSc = (StructuralElementInstance) resourceDeSerialize.getContents().get(0);
		String uuidWrite = sc.getUuid().toString();
		String uuidLoad = loadedSc.getUuid().toString();
		
		assertEquals("UUID made the whole roundtrip", uuidWrite, uuidLoad);
	}
	
	@Test
	public void testUuidUniquenessForUnits() {
		AUnit unitOne = QudvFactory.eINSTANCE.createSimpleUnit();
		AUnit unitTwo = QudvFactory.eINSTANCE.createSimpleUnit();
		
		assertFalse("UUIDs have to be different", unitOne.getUuid().equals(unitTwo.getUuid()));
	}
	
}
