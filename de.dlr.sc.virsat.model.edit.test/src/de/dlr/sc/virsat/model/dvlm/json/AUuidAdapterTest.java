/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.json;

import static org.junit.Assert.assertThrows;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

public abstract class AUuidAdapterTest {

	protected XmlAdapter<?, ?> adapter;
	protected static final VirSatUuid UUID = new VirSatUuid();
	protected Resource resourceImpl;
	protected ResourceSet resourceSet;
	protected XmlAdapter<?, ?> adapterWithoutResourceSet;
	protected XmlAdapter<?, ?> adapterWithEmptyResourceSet;
	
	@Before
	public void setUp() throws Exception {
		resourceSet = new ResourceSetImpl();
		resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
	}

	@Test
	public abstract void testMarshallNull() throws Exception;
	
	@Test
	public abstract void testMarhall() throws Exception;

	@Test
	public void testUnmarshallNull() {
		assertThrows("A resource set should be set",
			NullPointerException.class, () -> {
				adapterWithoutResourceSet.unmarshal(null);
			}
		);
		
		assertThrows("Uuid is null",
				NullPointerException.class, () -> {
				adapter.unmarshal(null);
			}
		);
	}

	@Test
	public abstract void testUnmarshallEmptyRs();

	@Test
	public abstract void testUnmarhall() throws Exception;
	
}
