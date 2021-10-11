/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.qudv;

import org.junit.Before;

import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind;

public class BeanQuantityKindSimpleTest extends ABeanQuantityKindTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		SimpleQuantityKind simpleQuantityKind = QudvFactory.eINSTANCE.createSimpleQuantityKind();
		BeanQuantityKindSimple simpleQuantityKindBean = new BeanQuantityKindSimple();
		simpleQuantityKindBean.setQuantityKind(simpleQuantityKind);
		
		aQuantityKind = simpleQuantityKind;
		aBeanQuantityKind = simpleQuantityKindBean;
	}
}
