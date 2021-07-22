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
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.concept.types.factory.BeanQuantityKindFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanQuantityKindSimple;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitSimple;
import de.dlr.sc.virsat.model.concept.types.qudv.IBeanQuantityKind;
import de.dlr.sc.virsat.model.concept.types.qudv.IBeanUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.server.resources.AModelAccessResourceTest;
import de.dlr.sc.virsat.server.resources.ModelAccessResource;

public class QudvResourceTest extends AModelAccessResourceTest {
	
	@Test
	public void testPrefixesGet() throws Exception {
		List<String> entity = webTarget
				.path(ModelAccessResource.QUDV)
				.path(QudvResource.PREFIXES)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.get(new GenericType<List<String>>() { });
		
		List<Prefix> prefixes = resourceSet.getRepository().getUnitManagement().getSystemOfUnit().getPrefix();
		assertEquals(entity.size(), prefixes.size());
		assertTrue(entity.contains(prefixes.get(0).getUuid().toString()));
	}
	
	@Test
	public void testQuantityKindsGet() throws Exception {
		SystemOfQuantities soq = resourceSet.getRepository().getUnitManagement().getSystemOfUnit().getSystemOfQuantities().get(0);
		 
		List<String> entity = webTarget
				.path(ModelAccessResource.QUDV)
				.path(QudvResource.QUANTITY_KINDS)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.get(new GenericType<List<String>>() { });
		
		List<AQuantityKind> quantityKinds = soq.getQuantityKind();
		assertEquals(entity.size(), quantityKinds.size());
		assertTrue(entity.contains(quantityKinds.get(0).getUuid().toString()));
	}
	
	@Test
	public void testUnitsGet() throws Exception {
		List<String> entity = webTarget
				.path(ModelAccessResource.QUDV)
				.path(QudvResource.UNITS)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.get(new GenericType<List<String>>() { });
		
		List<AUnit> units = resourceSet.getRepository().getUnitManagement().getSystemOfUnit().getUnit();
		assertEquals(entity.size(), units.size());
		assertTrue(entity.contains(units.get(0).getUuid().toString()));
	}
	
	@Test
	public void testUnitGet() throws Exception {
		AUnit unit = resourceSet.getRepository().getUnitManagement().getSystemOfUnit().getUnit().get(0);
		IBeanUuid unitBean = new BeanUnitFactory().getInstanceFor(unit);
		testGet(unitBean, ModelAccessResource.QUDV + "/" + QudvResource.UNIT, new Class[] {unitBean.getClass()});
	}
	
	@Test
	public void testUnitPut() {
		AUnit unit = resourceSet.getRepository().getUnitManagement().getSystemOfUnit().getUnit().get(0);
		IBeanUnit<? extends AUnit> unitBean = new BeanUnitFactory().getInstanceFor(unit);
		
		Response response = webTarget
				.path(ModelAccessResource.QUDV)
				.path(QudvResource.UNIT)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.entity((BeanUnitSimple) unitBean, MediaType.APPLICATION_JSON_TYPE));
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
	
	@Test
	public void testQuantityKindGet() throws Exception {
		AQuantityKind quantityKind = resourceSet.getRepository().getUnitManagement().getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().get(0);
		IBeanUuid quantityKindBean = new BeanQuantityKindFactory().getInstanceFor(quantityKind);
		testGet(quantityKindBean, ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND, new Class[] {quantityKindBean.getClass()});
	}
	
	@Test
	public void testQuantityKindPut() throws JAXBException {
		AQuantityKind quantityKind = resourceSet.getRepository().getUnitManagement().getSystemOfUnit().getSystemOfQuantities().get(0).getQuantityKind().get(0);
		IBeanQuantityKind<? extends AQuantityKind> quantityKindBean = new BeanQuantityKindFactory().getInstanceFor(quantityKind);
		
		Response response = webTarget
				.path(ModelAccessResource.QUDV)
				.path(QudvResource.QUANTITY_KIND)
				.request()
				.header(HttpHeaders.AUTHORIZATION, USER_WITH_REPO_HEADER)
				.put(Entity.entity((BeanQuantityKindSimple) quantityKindBean, MediaType.APPLICATION_JSON_TYPE));
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
}
