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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rmf.reqif10.SpecObject;

import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceabilityLinkContainer;
import de.dlr.sc.virsat.requirements.tracing.validation.engines.InspectionWarningEngine;

/**
 * 
 * Class to run validation on model elements based on requirements
 * 
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public class TracedArtifactRequirementValidator {
	
	
	private static final String VALIDATION_ENGINE_EXTENSION_POINT_ID = "de.dlr.sc.virsat.requirements.tracing.validation.IValidationEngine";
	private static final String EXTENSION_EXECUTABLE_ELEMENT_NAME = "class";
	
	/**
	 * @param container The trace model container
	 * @param changedSpecs the requirements which have changed
	 * @throws CoreException the exception
	 * validates all of the automatic ones, gives warnings on recently changed requirements
	 * if their validation engine type is inspection
	 */
	public void validate(TraceabilityLinkContainer container, List<SpecObject> changedSpecs) throws CoreException {
		
		//Run new validation
		for (TraceElement traceElement: container.getTraceElements()) {
			validateTraceElement(traceElement, changedSpecs);
		}
	}
	
	/**
	 * @param traceElement the trace element
	 * @param changedSpecs the requirements which have changed
	 * @return true if the element is validated
	 * @throws CoreException the exception
	 */
	public boolean validateTraceElement(TraceElement traceElement, List<SpecObject> changedSpecs) throws CoreException {
		
		//Default engine
		IValidationEngine engine = new InspectionWarningEngine();
		
		//Check if matching specific engine is configured
		if (traceElement.getValidationEngineName() != null) {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IConfigurationElement[] config = registry.getConfigurationElementsFor(VALIDATION_ENGINE_EXTENSION_POINT_ID);
			for (IConfigurationElement e : config) {
				Object o = e.createExecutableExtension(EXTENSION_EXECUTABLE_ELEMENT_NAME);
				if (o instanceof IValidationEngine) {
					engine = (IValidationEngine) o;
				}
			}
		}
		
		//If history is required pass input
		if (engine instanceof IHistoryBased) {
			((IHistoryBased) engine).setChangedSpecObjects(changedSpecs);
		}
		
		//Do validation if possible
		boolean canValidate = ((IValidationEngine) engine).canValidate(traceElement);
		if (canValidate) {
			canValidate = ((IValidationEngine) engine).validate(traceElement);
		}
		
		return canValidate;
	}
	

}
