/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.marker;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.build.marker.util.VirSatValidationMarkerHelper;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Helper class for managing markers which appear during the validation 
 * @author lobe_el
 *
 */
public class VirSatFuncelectricalMarkerHelper extends VirSatValidationMarkerHelper implements IMarkerHelper {
	
	static final String ID_FEA_VALIDATION_PROBLEM_MARKER = "de.dlr.sc.virsat.problem.markers.validation.fea";
	
	private static final String ATTRIBUTE_SECONDIUUID = "SecondIUuid";
	private static final String SECONDIUUID_EMPTY = "EmptySecondIUuid";
	
	/**
	 * VIRSAT_PROBLEM_MARKER
	 * 		|
	 *		L___ VALIDATION_PROBLEM_MARKER
	 *		 		|
	 *		 		L___ ...
	 * 				|
	 * 				L___ FEA_VALIDATION_PROBLEM_MARKER
	 */		
	
	@Override
	protected String getMarkerID() {
		return ID_FEA_VALIDATION_PROBLEM_MARKER;
	}
	
	@Override
	public boolean isAssociatedWith(IMarker marker, EObject eObject) {
		// strange issues with markers disappearing / not being found... 
		// Therefore always check if they exist, if not, no issue on object
		if (!marker.exists()) {
			return false;
		}
		try {
			if (marker.isSubtypeOf(ID_FEA_VALIDATION_PROBLEM_MARKER) && eObject instanceof IUuid) {
				String markerSecondIUuid = (String) marker.getAttribute(ATTRIBUTE_SECONDIUUID, SECONDIUUID_EMPTY);
				if (markerSecondIUuid.equals(((IUuid) eObject).getUuid().toString())) {
					return true;
				}
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), Status.OK, "Could not check marker", e));
			return false;
		}
		return super.isAssociatedWith(marker, eObject);
	}

	/**
	 * Method to attach a FEAValidationProblemMarker to the Resource of the given object
	 * @param severity A Severity Level to be displayed with the Marker
	 * @param message A Message to be displayed in the Problem View
	 * @param iUuid An eObject on which the Problem actually occurred.
	 *  This information should be evaluated in out Editor to show the exact place of problem
	 * @return The created Marker
	 */
	public IMarker createFEAValidationMarker(int severity, String message, IUuid iUuid) {
		IMarker marker = createMarker(ID_FEA_VALIDATION_PROBLEM_MARKER, severity, message, iUuid);
		return marker;
	}
	
	/**
	 * Method to attach a FEAValidationProblemMarker to the common Resource of the given objects
	 * @param severity A Severity Level to be displayed with the Marker
	 * @param message A Message to be displayed in the Problem View
	 * @param iUuid An eObject on which the Problem actually occurred.
	 * @param secondIUuid A second eObject on which the Problem actually occurred.
	 *  This information should be evaluated in out Editor to show the exact place of problem
	 * @return The created Marker
	 */
	public IMarker createFEAValidationMarker(int severity, String message, IUuid iUuid, IUuid secondIUuid) {
		// check whether both objects are in the same resource
		IResource resource1 = VirSatProjectCommons.getWorkspaceResource(iUuid);
		IResource resource2 = VirSatProjectCommons.getWorkspaceResource(secondIUuid);
		if (!resource1.equals(resource2)) {
			// if wanted here could be implemented that simply two different markers are created
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Could not create marker because objects have different resource"));
			return null;
		}
		
		IMarker marker = createMarker(ID_FEA_VALIDATION_PROBLEM_MARKER, severity, message, iUuid);
		try {
			marker.setAttribute(ATTRIBUTE_SECONDIUUID, (secondIUuid != null) ? secondIUuid.getUuid().toString() : SECONDIUUID_EMPTY);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Could not create marker"));
			return null;
		}
		return marker;
	}
	
}
