/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.marker;

import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.calculation.compute.problem.EvaluationProblem;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;

/**
 * Helper Class for Markers within the equations
 * @author lobe_el
 *
 */
public class VirSatEquationMarkerHelper extends VirSatProblemMarkerHelper implements IMarkerHelper {

	public static final String ID_EQUATION_MARKER = "de.dlr.sc.virsat.model.calculation.marker";
	
	public static final String ID_CYCLIC_EQUATION_MARKER = "de.dlr.sc.virsat.model.calculation.problem.cyclic";
	public static final String ID_EVALUATION_MARKER = "de.dlr.sc.virsat.model.calculation.problem.evaluation";
	public static final String ID_EVALUATION_OUT_OF_DATE_MARKER = "de.dlr.sc.virsat.model.calculation.problem.evaluation.outOfDate";
	
	private static final String MESSAGE_CYCLIC_EQUATION_MARKER = "Cyclic equation dependency detected";
	
	/**
	 * VIRSAT_PROBLEM_MARKER
	 * 		|
	 *		L___ EQUATION_MARKER
	 *		 		|
	 *		 		L___ CYCLIC_EQUATION_MARKER
	 * 				|
	 * 				L___ EQUATION_EVALUATION_PROBLEM_MARKER
	 * 
	 */		
	
	@Override
	protected String getMarkerID() {
		return ID_EQUATION_MARKER;
	}
	
	@Override
	public boolean isAssociatedWith(IMarker marker, EObject eObject) {
		if (!super.isAssociatedWith(marker, eObject)) {
			eObject = resolveReferences(eObject);
		}
		return super.isAssociatedWith(marker, eObject);
	}
	
	/**
	 * Method to get all the markers which are related to evaluation problems
	 * @param eObject The object whose markers shall be deleted
	 * @return the Set of markers 
	 */
	public Set<IMarker> getAllEvaluationProblemMarkers(EObject eObject) {
		return getMarkersForObjectAndContents(eObject, ID_EVALUATION_MARKER);
	}
	
	/**
	 * Method to get all the markers which are related to evaluation out of date problems
	 * @param eObject The object whose markers shall be deleted
	 * @return the Set of markers 
	 */
	public Set<IMarker> getAllEvaluationOutOfDateProblemMarkers(EObject eObject) {
		return getMarkersForObjectAndContents(eObject, ID_EVALUATION_OUT_OF_DATE_MARKER);
	}
	
	/**
	 * Method to delete all the markers which are related to equation evaluation problems
	 * @param eObject The object whose markers shall be deleted
	 */
	public void deleteAllEquationEvaluationProblemMarkers(EObject eObject) {
		deleteMarkers(eObject, ID_EVALUATION_MARKER);
	}
	
	/**
	 * Handle the creation of error markers for dependency cycles
	 * @param obj the eObject on a cycle
	 * @return The created marker
	 */
	public IMarker createCyclicEquationMarker(EObject obj) {
		obj = resolveReferences(obj);
		IMarker marker = createMarker(ID_CYCLIC_EQUATION_MARKER, IMarker.SEVERITY_ERROR, MESSAGE_CYCLIC_EQUATION_MARKER, obj);
		return marker;
	}
	
	/**
	 * Handle the creation of warning markers for outdated equations
	 * @param evaluationProblem the equation evaluation object
	 * @return The created marker
	 */
	public IMarker createEvaluationProblemMarker(EvaluationProblem evaluationProblem) {
		EObject obj = resolveReferences(evaluationProblem.getProblematicObject());
		IMarker marker = createMarker(evaluationProblem.getMarkerID(), IMarker.SEVERITY_WARNING, evaluationProblem.toString(), obj);
		return marker;
	}
	
	/**
	 * Method to resolve the references to get the name of the actual object
	 * @param obj The object to be resolved 
	 * @return The resolved object
	 */
	private EObject resolveReferences(EObject obj) {
		if (obj instanceof Equation) {
			Equation equation = (Equation) obj;
			obj = equation.getResult();	
		}
		if (obj instanceof TypeInstanceResult) {
			TypeInstanceResult tir = (TypeInstanceResult) obj;
			obj = tir.getReference();
		} else if (obj instanceof ReferencedInput) {
			ReferencedInput ri = (ReferencedInput) obj;
			obj = ri.getReference();
		} 
		return obj;
	}
}
