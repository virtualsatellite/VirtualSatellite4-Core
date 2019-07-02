/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
/**
 * 
 */
package de.dlr.sc.virsat.requirements.tracing.test.mockup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.rmf.reqif10.SpecObject;

import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.validation.IHistoryBased;
import de.dlr.sc.virsat.requirements.tracing.validation.IValidationEngine;

/**
 * @author fran_tb
 *
 */
public class TestValidationEngine implements IValidationEngine, IHistoryBased {
	
	
	public static final String ENGINE_NAME = "TestValidationEngine";
	
	protected static List<EObject> traceTargets = new ArrayList<>();
	protected static List<EObject> changedRequirementsElements = new ArrayList<>();

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.tracing.validation.IHistoryBased#setChangedSpecObjects(java.util.List)
	 */
	@Override
	public void setChangedSpecObjects(List<SpecObject> changedObjects) {
		if (changedObjects != null) {
			changedRequirementsElements.addAll(changedObjects);
		}
	}

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.tracing.validation.IValidationEngine#validate(de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement)
	 */
	@Override
	public boolean validate(TraceElement traceElement) {
		traceTargets.addAll(traceElement.getTargetTraceElement());
		return true;
	}

	/**
	 * @return the traceTargets
	 */
	public static List<EObject> getTraceTargets() {
		return traceTargets;
	}

	/**
	 * @param traceTargets the traceTargets to set
	 */
	public static void setTraceTargets(List<EObject> traceTargets) {
		TestValidationEngine.traceTargets = traceTargets;
	}

	/**
	 * @return the changedRequirementsElements
	 */
	public static List<EObject> getChangedRequirementsElements() {
		return changedRequirementsElements;
	}

	/**
	 * @param changedRequirementsElements the changedRequirementsElements to set
	 */
	public static void setChangedRequirementsElements(List<EObject> changedRequirementsElements) {
		TestValidationEngine.changedRequirementsElements = changedRequirementsElements;
	}

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.tracing.validation.IValidationEngine#canValidate(de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement)
	 */
	@Override
	public boolean canValidate(TraceElement traceElement) {
		return true;
	}

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.tracing.validation.IValidationEngine#getValidationEngineName()
	 */
	@Override
	public String getValidationEngineName() {
		return ENGINE_NAME;
	}

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.tracing.validation.IValidationEngine#canProvideSemantic()
	 */
	@Override
	public boolean canProvideSemantic() {
		return false;
	}

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.tracing.validation.IValidationEngine#generateBoilerPlate(de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement)
	 */
	@Override
	public String generateBoilerPlate(TraceElement traceElement) {
		return null;
	}

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.tracing.validation.IValidationEngine#getSemantic()
	 */
	@Override
	public String getSemantic() {
		return null;
	}

}
