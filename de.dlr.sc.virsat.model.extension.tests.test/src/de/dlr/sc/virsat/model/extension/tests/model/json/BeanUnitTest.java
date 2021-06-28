/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model.json;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.concept.types.qudv.ABeanUnit;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitAffineConversion;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitDerived;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitLinearConversion;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitPrefixed;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitSimple;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

public class BeanUnitTest {
	
	private JAXBUtility jaxbUtility;
	private ABeanUnit<? extends AUnit> beanUnitSimple;
	private ABeanUnit<? extends AUnit> beanUnitPrefixed;
	private ABeanUnit<? extends AUnit> beanUnitAffineConversion;
	private ABeanUnit<? extends AUnit> beanUnitLinearConversion;
	private ABeanUnit<? extends AUnit> beanUnitDerived;
	
	private static final String RESOURCE_PATH = "/resources/json/unit/%s.json";
	
	@Before
	public void setup() throws JAXBException {
		jaxbUtility = new JAXBUtility(new Class[] {
			BeanUnitSimple.class,
			BeanUnitPrefixed.class,
			BeanUnitAffineConversion.class,
			BeanUnitLinearConversion.class,
			BeanUnitDerived.class
		});
		
		Repository repo = JsonTestHelper.createRepositoryWithUnitManagement(null);
		SystemOfUnits sou = repo.getUnitManagement().getSystemOfUnit();
		
		AUnit meterUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Meter");
		beanUnitSimple = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(meterUnit);
		
		AUnit kmUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		beanUnitPrefixed = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(kmUnit);
		
		AUnit minuteUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Minute");
		beanUnitAffineConversion = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(minuteUnit);
		
		AUnit byteUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Byte");
		beanUnitLinearConversion = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(byteUnit);
		
		AUnit meterPerSecondUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Meter Per Second");
		beanUnitDerived = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(meterPerSecondUnit);
	}
	
	private String getResource(Object testSubject) {
		return String.format(RESOURCE_PATH, testSubject.getClass().getSimpleName());
	}
	
	public void testMarshall(Object testSubject) throws JAXBException, IOException {
		JsonTestHelper.assertMarshall(jaxbUtility, getResource(testSubject), testSubject);
	}
	
	// TODO: enable fields
	public void testUnmarshall(Object testSubject, EObject testESubject) throws JAXBException, IOException {
		Unmarshaller jsonUnmarshaller = JsonTestHelper.getUnmarshaller(jaxbUtility, testESubject);
		
		StreamSource inputSource = JsonTestHelper.getResourceAsStreamSource(getResource(testSubject));
		
		jsonUnmarshaller.unmarshal(inputSource, testSubject.getClass());
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		testMarshall(beanUnitSimple);
		testMarshall(beanUnitPrefixed);
		testMarshall(beanUnitAffineConversion);
		testMarshall(beanUnitLinearConversion);
		testMarshall(beanUnitDerived);
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		testUnmarshall(beanUnitSimple, beanUnitSimple.getUnit());
		testUnmarshall(beanUnitPrefixed, beanUnitPrefixed.getUnit());
		testUnmarshall(beanUnitAffineConversion, beanUnitAffineConversion.getUnit());
		testUnmarshall(beanUnitLinearConversion, beanUnitLinearConversion.getUnit());
		testUnmarshall(beanUnitDerived, beanUnitDerived.getUnit());
	}

}
