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
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanPrefix;
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

// TODO: rename to qudv?
// TODO: improve field doc
public class BeanUnitTest {
	
	private JAXBUtility jaxbUtility;
	private BeanUnitSimple beanUnitSimple;
	private BeanUnitPrefixed beanUnitPrefixed;
	private BeanUnitAffineConversion beanUnitAffineConversion;
	private BeanUnitLinearConversion beanUnitLinearConversion;
	private BeanUnitDerived beanUnitDerived;
	private BeanPrefix beanPrefix;
	
	// TODO: rename?
	private static final String RESOURCE_PATH = "/resources/json/unit/%s.json";
	
	@Before
	public void setup() throws JAXBException {
		jaxbUtility = new JAXBUtility(new Class[] {
			BeanUnitSimple.class,
			BeanUnitPrefixed.class,
			BeanUnitAffineConversion.class,
			BeanUnitLinearConversion.class,
			BeanUnitDerived.class,
			BeanPrefix.class
		});
		
		Repository repo = JsonTestHelper.createRepositoryWithUnitManagement(null);
		SystemOfUnits sou = repo.getUnitManagement().getSystemOfUnit();
		
		AUnit meterUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Meter");
		beanUnitSimple = (BeanUnitSimple) new BeanUnitFactory().getInstanceFor(meterUnit);
		
		AUnit kmUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		beanUnitPrefixed = (BeanUnitPrefixed) new BeanUnitFactory().getInstanceFor(kmUnit);
		beanPrefix = beanUnitPrefixed.getPrefixBean();
		
		AUnit minuteUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Minute");
		beanUnitAffineConversion = (BeanUnitAffineConversion) new BeanUnitFactory().getInstanceFor(minuteUnit);
		
		AUnit byteUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Byte");
		beanUnitLinearConversion = (BeanUnitLinearConversion) new BeanUnitFactory().getInstanceFor(byteUnit);
		
		AUnit meterPerSecondUnit = QudvUnitHelper.getInstance().getUnitByName(sou, "Meter Per Second");
		beanUnitDerived = (BeanUnitDerived) new BeanUnitFactory().getInstanceFor(meterPerSecondUnit);
	}
	
	private String getResource(Object testSubject) {
		return String.format(RESOURCE_PATH, testSubject.getClass().getSimpleName());
	}
	
	public void testMarshall(Object testSubject) throws JAXBException, IOException {
		JsonTestHelper.assertMarshall(jaxbUtility, getResource(testSubject), testSubject);
	}
	
	public void testUnmarshall(Object testSubject, EObject testESubject) throws JAXBException, IOException {
		testUnmarshall(testSubject, Arrays.asList(testESubject));
	}
	
	public void testUnmarshall(Object testSubject, List<EObject> testESubjects) throws JAXBException, IOException {
		Unmarshaller jsonUnmarshaller = JsonTestHelper.getUnmarshaller(jaxbUtility, testESubjects);
		
		StreamSource inputSource = JsonTestHelper.getResourceAsStreamSource(getResource(testSubject));
		
		jsonUnmarshaller.unmarshal(inputSource, testSubject.getClass());
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		// Units
		testMarshall(beanUnitSimple);
		testMarshall(beanUnitPrefixed);
		testMarshall(beanUnitAffineConversion);
		testMarshall(beanUnitLinearConversion);
		testMarshall(beanUnitDerived);
		
		// QuantityKinds
		
		// Referenced classes
		testMarshall(beanPrefix);
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		// Units
		testUnmarshall(beanUnitSimple, beanUnitSimple.getUnit());
		
		testUnmarshall(beanUnitPrefixed, Arrays.asList(
				beanUnitPrefixed.getUnit(), 
				beanUnitPrefixed.getPrefixBean().getPrefix()));
		
		testUnmarshall(beanUnitAffineConversion, beanUnitAffineConversion.getUnit());
		
		testUnmarshall(beanUnitLinearConversion, beanUnitLinearConversion.getUnit());
		
		testUnmarshall(beanUnitDerived, Arrays.asList(
				beanUnitDerived.getUnit(),
				beanUnitDerived.getUnit().getFactor().get(0),
				beanUnitDerived.getUnit().getFactor().get(1),
				beanUnitDerived.getUnit().getFactor().get(0).getUnit(),
				beanUnitDerived.getUnit().getFactor().get(1).getUnit()));
		
		// QuantityKinds
		
		// Referenced classes
		testUnmarshall(beanPrefix, beanPrefix.getPrefix());
	}

}
