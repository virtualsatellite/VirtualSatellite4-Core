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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.concept.types.factory.BeanQuantityKindFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanPrefix;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanQuantityKindSimple;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitSimple;
import de.dlr.sc.virsat.model.concept.types.qudv.IBeanQuantityKind;
import de.dlr.sc.virsat.model.concept.types.qudv.IBeanUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.resources.AModelAccessResourceTest;
import de.dlr.sc.virsat.server.resources.ApiErrorHelper;
import de.dlr.sc.virsat.server.resources.ModelAccessResource;

public class QudvResourceTest extends AModelAccessResourceTest {
	
	private List<Prefix> prefixes;
	private List<AQuantityKind> quantityKinds;
	private List<AUnit> units;
	private Prefix prefix;
	private AUnit unitNotDerived;
	private AQuantityKind qkNotDerived;
	private DerivedUnit unitDerived;
	private DerivedQuantityKind qkDerived;
	
	@Before
	public void setUpModel() throws Exception {
		super.setUpModel();
		
		prefixes = unitManagement.getSystemOfUnit().getPrefix();
		quantityKinds = systemOfQuantities.getQuantityKind();
		units = unitManagement.getSystemOfUnit().getUnit();
		
		prefix = unitManagement.getSystemOfUnit().getPrefix().get(0);
		unitNotDerived = unitManagement.getSystemOfUnit().getUnit().get(0);
		qkNotDerived = systemOfQuantities.getQuantityKind().get(0);
		unitDerived = (DerivedUnit) QudvUnitHelper.getInstance().getUnitByName(unitManagement.getSystemOfUnit(), "Meter Per Second");
		qkDerived = (DerivedQuantityKind) unitDerived.getQuantityKind();
	}
	
	@Test
	public void testPrefixesGet() throws Exception {
		List<String> entity = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.PREFIXES)
				.get(new GenericType<List<String>>() { });
		
		assertEquals(entity.size(), prefixes.size());
		assertTrue(entity.contains(prefixes.get(0).getUuid().toString()));
	}
	
	@Test
	public void testQuantityKindsGet() throws Exception {
		List<String> entity = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KINDS)
				.get(new GenericType<List<String>>() { });
		
		assertEquals(entity.size(), quantityKinds.size());
		assertTrue(entity.contains(quantityKinds.get(0).getUuid().toString()));
	}
	
	@Test
	public void testUnitsGet() throws Exception {
		List<String> entity = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.UNITS)
				.get(new GenericType<List<String>>() { });
		
		assertEquals(entity.size(), units.size());
		assertTrue(entity.contains(units.get(0).getUuid().toString()));
	}
	
	@Test
	public void testUnitGet() throws Exception {
		IBeanUuid unitBean = new BeanUnitFactory().getInstanceFor(unitNotDerived);
		testGet(unitBean, ModelAccessResource.QUDV + "/" + QudvResource.UNIT, new Class[] {unitBean.getClass()});
	}
	
	@Test
	public void testUnitPut() {
		IBeanUnit<? extends AUnit> unitBean = new BeanUnitFactory().getInstanceFor(unitNotDerived);
		
		Response response = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.UNIT)
				.put(Entity.entity((BeanUnitSimple) unitBean, MediaType.APPLICATION_JSON_TYPE));
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
	
	@Test
	public void testUnitCreate() {
		int unitCount = units.size();
		Response response = getTestRequestBuilderWithQueryParam(
				ModelAccessResource.QUDV + "/" + QudvResource.UNIT, ModelAccessResource.QP_NAME, BeanUnitSimple.class.getSimpleName())
				.post(Entity.json(null));
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("One new unit added", unitCount + 1, units.size());
		
		String uuid = response.readEntity(String.class);
		AUnit obj = RepositoryUtility.findUnit(uuid, resourceSet.getRepository());
		assertNotNull(obj);
		assertThat(units, hasItem(obj));
		assertTrue(obj instanceof SimpleUnit);
	}
	
	@Test
	public void testUnitDelete() {
		int unitCount = units.size();
		Response response = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.UNIT + "/" + unitNotDerived.getUuid().toString()).delete();
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("One unit removed", unitCount - 1, unitManagement.getSystemOfUnit().getUnit().size());
	}
	
	@Test
	public void testQuantityKindGet() throws Exception {
		IBeanUuid quantityKindBean = new BeanQuantityKindFactory().getInstanceFor(qkNotDerived);
		testGet(quantityKindBean, ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND, new Class[] {quantityKindBean.getClass()});
	}
	
	@Test
	public void testQuantityKindPut() throws JAXBException {
		IBeanQuantityKind<? extends AQuantityKind> quantityKindBean = new BeanQuantityKindFactory().getInstanceFor(qkNotDerived);
		
		Response response = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND)
				.put(Entity.entity((BeanQuantityKindSimple) quantityKindBean, MediaType.APPLICATION_JSON_TYPE));
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
	
	@Test
	public void testQuantityKindCreate() {
		int qkCount = quantityKinds.size();
		Response response = getTestRequestBuilderWithQueryParam(
				ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND, ModelAccessResource.QP_NAME, BeanQuantityKindSimple.class.getSimpleName())
				.post(Entity.json(null));
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("One new qk added", qkCount + 1, quantityKinds.size());
		
		String uuid = response.readEntity(String.class);
		AQuantityKind obj = RepositoryUtility.findQuantityKind(uuid, resourceSet.getRepository());
		assertNotNull(obj);
		assertThat((List<AQuantityKind>) quantityKinds, hasItem(obj));
		assertTrue(obj instanceof SimpleQuantityKind);
	}
	
	@Test
	public void testQuantityKindDelete() {
		int qkCount = quantityKinds.size();
		Response response = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND + "/" + qkNoReference.getUuid().toString()).delete();
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("One quantity kind removed", qkCount - 1, quantityKinds.size());
	}
	
	@Test
	public void testPrefixGet() throws Exception {
		IBeanUuid prefixBean = new BeanPrefix(prefix);
		testGet(prefixBean, ModelAccessResource.QUDV + "/" + QudvResource.PREFIX, new Class[] {prefixBean.getClass()});
	}
	
	@Test
	public void testPrefixPut() throws JAXBException {
		IBeanUuid prefixBean = new BeanPrefix(prefix);
		
		Response response = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.PREFIX)
				.put(Entity.entity(prefixBean, MediaType.APPLICATION_JSON_TYPE));
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
	}
	
	@Test
	public void testPrefixCreate() {
		int prefixCount = prefixes.size();
		Response response = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.PREFIX)
				.post(Entity.json(null));
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("One new prefix added", prefixCount + 1, prefixes.size());
		
		String uuid = response.readEntity(String.class);
		Prefix obj = RepositoryUtility.findPrefix(uuid, resourceSet.getRepository());
		assertNotNull(obj);
		assertThat((List<Prefix>) prefixes, hasItem(obj));
	}
	
	@Test
	public void testPrefixDelete() {
		int prefixCount = prefixes.size();
		Response response = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.PREFIX + "/" + prefix.getUuid().toString()).delete();
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("One prefix removed", prefixCount - 1, prefixes.size());
	}
	
	@Test
	public void testQuantityKindFactorCreate() {
		int qkFactorCount = qkDerived.getFactor().size();
		Response response = getTestRequestBuilder(
				ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND_FACTOR + "/" + qkDerived.getUuid().toString())
				.post(Entity.json(null));
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("One new qk factor added", qkFactorCount + 1, qkDerived.getFactor().size());
	}
	
	@Test
	public void testQuantityKindFactorDelete() {
		int qkFactorCount = qkDerived.getFactor().size();
		Response response = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND_FACTOR + "/" + qkDerived.getFactor().get(0).getUuid().toString()).delete();
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("One unit factor removed", qkFactorCount - 1, qkDerived.getFactor().size());
	}
	
	@Test
	public void testUnitFactorCreate() {
		int unitFactorCount = unitDerived.getFactor().size();
		Response response = getTestRequestBuilder(
				ModelAccessResource.QUDV + "/" + QudvResource.UNIT_FACTOR + "/" + unitDerived.getUuid().toString())
				.post(Entity.json(null));
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("One new unit factor added", unitFactorCount + 1, unitDerived.getFactor().size());
	}
	
	@Test
	public void testUnitFactorDelete() {
		int unitFactorCount = unitDerived.getFactor().size();
		Response response = getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.UNIT_FACTOR + "/" + unitDerived.getFactor().get(0).getUuid().toString()).delete();
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertEquals("One unit factor removed", unitFactorCount - 1, unitDerived.getFactor().size());
	}
	
	@Test
	public void testErrorResponses() {
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.UNIT + "/unknown").get());
		assertBadRequestResponse(getTestRequestBuilderWithQueryParam(
				ModelAccessResource.QUDV + "/" + QudvResource.UNIT, ModelAccessResource.QP_NAME, "unknown").post(Entity.json(null)),
				ApiErrorHelper.INVALID_TYPE_ERROR + ": unknown");
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.UNIT + "/unknown").delete());
		
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND + "/unknown").get());
		assertBadRequestResponse(getTestRequestBuilderWithQueryParam(
				ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND, ModelAccessResource.QP_NAME, "unknown").post(Entity.json(null)),
				ApiErrorHelper.INVALID_TYPE_ERROR + ": unknown");
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND + "/unknown").delete());
		assertNotExecuteableErrorResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND + "/" + qkNotDerived.getUuid().toString()).delete());
		
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.PREFIX + "/unknown").get());
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.PREFIX + "/unknown").delete());
		
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.UNIT_FACTOR + "/unknown").post(Entity.json(null)));
		assertBadRequestResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.UNIT_FACTOR + "/"
				+ unitNotDerived.getUuid().toString()).post(Entity.json(null)),
				ApiErrorHelper.INVALID_TYPE_ERROR + ": " + QudvResource.NOT_DERIVED);
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.UNIT_FACTOR + "/unknown").delete());
		
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND_FACTOR + "/unknown").post(Entity.json(null)));
		assertBadRequestResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND_FACTOR + "/"
				+ qkNotDerived.getUuid().toString()).post(Entity.json(null)),
				ApiErrorHelper.INVALID_TYPE_ERROR + ": " + QudvResource.NOT_DERIVED);
		assertNotFoundResponse(getTestRequestBuilder(ModelAccessResource.QUDV + "/" + QudvResource.QUANTITY_KIND_FACTOR + "/unknown").delete());
	}
}
