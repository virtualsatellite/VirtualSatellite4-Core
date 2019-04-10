/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.marker.util;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.dlr.sc.virsat.build.Activator;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;

/**
 * Helper class for managing markers which appear during the validation 
 * @author lobe_el
 *
 */
public class VirSatValidationMarkerHelper extends VirSatProblemMarkerHelper implements IMarkerHelper {
	
	static final String ID_VALIDATION_PROBLEM_MARKER = "de.dlr.sc.virsat.problem.markers.validation";
	static final String ID_DVLM_VALIDATION_PROBLEM_MARKER = "de.dlr.sc.virsat.problem.markers.validation.dvlm";
	static final String ID_EMF_VALIDATION_PROBLEM_MARKER = "de.dlr.sc.virsat.problem.markers.validation.emf";
	static final String ID_INH_VALIDATION_PROBLEM_MARKER = "de.dlr.sc.virsat.problem.markers.validation.inheritance";
	
	private static final String ATTRIBUTE_ESTRUCTURALFEATURE = "eStructuralFeature";
	private static final String ESTRUCTURALFEATURE_EMPTY = "EmptyESF";
	
	private static final String ATTRIBUTE_SUPERIUUID = "SuperIUuid";
	private static final String SUPERIUUID_EMPTY = "EmptySuperIUuid";
	
	/**
	 * VIRSAT_PROBLEM_MARKER
	 * 		|
	 *		L___ VALIDATION_PROBLEM_MARKER
	 *		 		|
	 *		 		L___ DVLM_VALIDATION_PROBLEM_MARKER
	 * 				|
	 * 				L___ EMF_VALIDATION_PROBLEM_MARKER
	 * 						|
	 * 						L___ INHERITANCE_VALIDATION_PROBLEM_MARKER
	 * 
	 */		
	
	@Override
	protected String getMarkerID() {
		return ID_VALIDATION_PROBLEM_MARKER;
	}
	
	@Override
	public boolean isAssociatedWith(IMarker marker, EObject eObject) {
		// There are no changes for this marker
		return super.isAssociatedWith(marker, eObject);
	}
	
	/**
	 * Method to attach a DVLMValidationProblemMarker to the Resource of the given object
	 * @param severity A Severity Level to be displayed with the Marker
	 * @param message A Message to be displayed in the Problem View
	 * @param eObject An eObject on which the Problem actually occurred.
	 *  This information should be evaluated in out Editor to show the exact place of problem
	 * @return The created Marker
	 */
	public IMarker createDVLMValidationMarker(int severity, String message, EObject eObject) {
		IMarker marker = createMarker(ID_DVLM_VALIDATION_PROBLEM_MARKER, severity, message, eObject);
		return marker;
	}
	
	/**
	 * Method to attach an EMFValidationProblemMarker to the Resource of the given object
	 * @param severity A Severity Level to be displayed with the Marker
	 * @param message A Message to be displayed in the Problem View
	 * @param iUuid An eObject on which the Problem actually occurred.
	 *  This information should be evaluated in out Editor to show the exact place of problem
	 * @param eFeature An eFeature detailing where on the eObject the Issues is happening
	 * @return The created Marker
	 */
	public IMarker createEMFValidationMarker(int severity, String message, IUuid iUuid, EStructuralFeature eFeature) {
		IMarker marker = createMarker(ID_EMF_VALIDATION_PROBLEM_MARKER, severity, message, iUuid);
		if (marker == null) {
			return null;
		}
		try {
			marker.setAttribute(ATTRIBUTE_ESTRUCTURALFEATURE, (eFeature != null) ? eFeature.getName() : ESTRUCTURALFEATURE_EMPTY);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Could not create marker"));
			return null;
		}
		return marker;
	}
	
	/**
	 * Method to attach an InheritanceValidationProblemMarker to the Resource of the given object
	 * @param severity A Severity Level to be displayed with the Marker
	 * @param message A Message to be displayed in the Problem View
	 * @param iUuid An eObject on which the Problem actually occurred.
	 *  This information should be evaluated in out Editor to show the exact place of problem
	 * @param superIUuid The IUuid of the sei which the object is inheriting from
	 * @return The created Marker
	 */
	public IMarker createInheritanceValidationMarker(int severity, String message, IUuid iUuid, IUuid superIUuid) {
		IMarker marker = createMarker(ID_INH_VALIDATION_PROBLEM_MARKER, severity, message, iUuid);
		EStructuralFeature eFeature = InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS; 
		try {
			marker.setAttribute(ATTRIBUTE_ESTRUCTURALFEATURE, eFeature.getName());
			marker.setAttribute(ATTRIBUTE_SUPERIUUID, (superIUuid != null) ? superIUuid.getUuid().toString() : SUPERIUUID_EMPTY);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Could not create marker"));
			return null;
		}
		return marker;
	}
	/**
	 * Use this Method to filter all the given EMFValidationProblemMarkers to hand back only the ones which 
	 * refer to a given EStructuralFeature
	 * @param unfilteredMarkers The List of Markers to be filtered
	 * @param eStructuralFeature The Feature which is of interest
	 * @return The List of Markers referring to this feature
	 */
	private static Set<IMarker> filterMarkersByEStructuralFeature(Set<IMarker> unfilteredMarkers, EStructuralFeature eStructuralFeature) {
		Set<IMarker> filteredMarkers = new HashSet<>();
		
		for (IMarker marker : unfilteredMarkers) {
			String esfName = marker.getAttribute(ATTRIBUTE_ESTRUCTURALFEATURE, ESTRUCTURALFEATURE_EMPTY);
			if (esfName.equals(eStructuralFeature.getName())) {
				filteredMarkers.add(marker);
			}
			
		}
		return filteredMarkers;
	}
	
	/**
	 * Method to get those EMFValidationProblemMarkers associated with the object which belong to a certain eStructuralFeature
	 * @param eObject The object whose markers to get 
	 * @param eStructuralFeature The Feature the markers should belong to 
	 * @return The markers which belong to the feature of the object
	 */
	public Set<IMarker> getEMFValidationMarkers(EObject eObject, EStructuralFeature eStructuralFeature) {
		Set<IMarker> markersForUuid = getMarkers(eObject, ID_EMF_VALIDATION_PROBLEM_MARKER);
		Set<IMarker> markersForEsf = filterMarkersByEStructuralFeature(markersForUuid, eStructuralFeature);
		return markersForEsf;
	} 

	/**
	 * Method to get those ValidationProblemMarkers which not belong to a eStructuralFeature
	 * @param eObject The object whose markers to get 
	 * @return The markers
	 */
	public Set<IMarker> getNotEMFValidationMarkers(EObject eObject) {
		Set<IMarker> markers = getMarkers(eObject, ID_VALIDATION_PROBLEM_MARKER);
		Set<IMarker> markersEmf = getMarkers(eObject, ID_EMF_VALIDATION_PROBLEM_MARKER);
		markers.removeAll(markersEmf);
		return markers;
	} 
}
