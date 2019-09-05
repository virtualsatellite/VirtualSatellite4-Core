/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.serializer;

import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput;

/**
 * This class tests the SafeEquationDSLSemanticSequencer class
 * @author muel_s8
 *
 */

public class SafeEquationDSLSemanticSequencerTest {

	@Test
	public void testNullReference() {
		// There should be no runtime exception thrown
		ReferencedInput ri = CalculationFactory.eINSTANCE.createReferencedInput();
		SafeEquationDSLSemanticSequencer sequencer = new SafeEquationDSLSemanticSequencer();
		sequencer.sequence_ReferencedInput(null, ri);
	}

}
