/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.markers;

import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

/**
 * Interface for all MarkerHelpers
 * All Markers which are created by a Helper need to be registered in the plugin.xml together with their MarkerHelper
 * @author lobe_el
 *
 */
public interface IMarkerHelper {

	/**
	 * This method checks whether the given VirSatProblemMarker is associated with the given DVLMObject
	 * should be implemented by every extension which marks several objects with a single marker 
	 * @param marker The VirSatMarker to be checked
	 * @param eObject The DVLMObject to be checked
	 * @return Whether the VirSatMarker is associated with the DVLMObject
	 */
	boolean isAssociatedWith(IMarker marker, EObject eObject);

	/**
	 * This method hands back a Set with all Markers for the given DVLMObject that belong to the specific marker.
	 * @param eObject The DLVMObject for which to check if VirSatProblemMarkers exist
	 * @return a Set of VirSatProblemMarkers which are relevant for the given DVLMObject
	 */
	Set<IMarker> getAllMarkers(EObject eObject);

	/**
	 * Method which hands back all Markers of the specific helper that belong to the whole resource, if the DVLMObject is the head of this resource.
	 * If the DVLMObject is only contained in another DVLMObject, hence in its resource, the method will only hand back the Markers which 
	 * are associated to itself and the objects which it contains in turn. 
	 * @param eObject The DVLMObject which is checked 
	 * @return the Set of VirSatProblemMarkers for the DVLMObject and its contained objects 
	 */
	Set<IMarker> getAllMarkersForObjectAndContents(EObject eObject);

	/**
	 * Method to delete all Markers which are associated with this object and belong to the specific helper
	 * @param eObject The object whose markers should be deleted
	 */
	void deleteAllMarkers(EObject eObject);

	/**
	 * Method to delete all Markers belonging to the specific helper which are associated with this object and 
	 * all objects which are contained by this object and saved in the same resource
	 * @param eObject The object whose markers should be deleted
	 */
	void deleteAllMarkersForObjectAndContents(EObject eObject);

	/**
	 * Method to delete all the markers the Helper cares about in the workspace
	 */
	void deleteAllMarkersInWorkspace();

}