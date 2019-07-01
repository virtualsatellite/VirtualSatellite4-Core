/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.validation;

import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement;

/**
 * Interface for validation engines
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public interface IValidationEngine {

	/**
	 * @param traceElement the trace element
	 * @return true if validated
	 */
	boolean validate(TraceElement traceElement);
	
	/**
	 * @param traceElement the trace element
	 * @return true if can  be validated
	 */
	boolean canValidate(TraceElement traceElement);
	
	/**
	 * @return the name of the validation Engine
	 */
	String getValidationEngineName();

	
	/**
	 * @return true if the engine can provide semantics
	 */
	boolean canProvideSemantic();
	
	/**
	 * @param traceElement the traceElement
	 * @return the generated boilerplate
	 */
	String generateBoilerPlate(TraceElement traceElement);
	
	/**
	 * @return the semantic of the engine
	 */
	String getSemantic();
	
}
