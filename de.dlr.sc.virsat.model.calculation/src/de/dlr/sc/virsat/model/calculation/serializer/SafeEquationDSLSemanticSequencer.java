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

import org.eclipse.xtext.serializer.ISerializationContext;

import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput;

/**
 * This class adjusts the error behavior of the generated semantic sequencer
 * such that it can handle cases such as missing reference values.
 * @author muel_s8
 *
 */

public class SafeEquationDSLSemanticSequencer extends EquationDSLSemanticSequencer {
	@Override
	protected void sequence_ReferencedInput(ISerializationContext context, ReferencedInput semanticObject) {
		if (semanticObject.getReference() == null) {
			return;
		}
		
		super.sequence_ReferencedInput(context, semanticObject);
	}
}
