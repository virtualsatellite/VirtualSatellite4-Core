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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * General helper class for managing problem markers in VirSat.
 * Markers in Virsat are always associated with objects which have an Identifier: either Uuid or QualifiedName 
 * and they are always attached to the resource of the object
 * 
 * Every extending MarkerHelper should make sure that the following methods are adjusted to its needs:
 * 		- getMarkerID			(always return the top level Marker type of the specific helper)
 * 		- isAssociatedWith		(especially if marker belongs to several objects)
 * 		- setSnippetProperty 	(probably, to get the correct snippet later) 
 * 
 * @author lobe_el
 *
 */
public class VirSatProblemMarkerHelper implements IMarkerHelper {

	static final String ID_VIRSAT_PROBLEM_MARKER = "de.dlr.sc.virsat.problem.marker";
	
	static final String ID_TYPE_IUUID = "IUuid";
	static final String ID_TYPE_IQUALIFIEDNAME = "IQualifiedName";
	
	static final String ID_TYPE_NOT_VALID = "there.is.no.valid.identifier.attribute";
	static final String ID_VALUE_NOT_VALID = "eobject.has.no.valid.identifier";
	
	private static final String ATTRIBUTE_ID_TYPE = "IdentifierType";
	private static final String ATTRIBUTE_ID_VALUE = "IdentifierValue";
	private static final String ID_EXTENSIONPOINT_MARKERHELPER = "de.dlr.sc.virsat.project.markerHelper";
	private static final String ID_EXTENSIONPOINT_MARKERHELPER_ATTR_TYPE = "markerType";
	private static final String ID_EXTENSIONPOINT_MARKERHELPER_ATTR_HELPER = "markerHelper";
	
	private static Map<String, IMarkerHelper> registeredMarkerHelper = Collections.synchronizedMap(new HashMap<>());
	
	/**
	 * Method to get the MarkerID (respectively the type of the marker)
	 * Is intended to be overridden by extending MarkerHelpers to return their markerID 
	 * will influence all methods with "all" inside
	 * @return The marker's id
	 */
	protected String getMarkerID() {
		return ID_VIRSAT_PROBLEM_MARKER;
	}
	
	/**
	 * Method to get the correct Helper for the given marker
	 * especially neccessary 
	 * @param marker The marker whose registered Helper shall be created
	 * @return The markerHelper
	 */
	public static IMarkerHelper createMarkerHelper(IMarker marker) {
		try {
			// Fill an empty marker helper map with the ones that we get from the registered concepts
			if (registeredMarkerHelper.isEmpty()) {
				IConfigurationElement[] configElementsMarkerHelper = Platform.getExtensionRegistry().getConfigurationElementsFor(ID_EXTENSIONPOINT_MARKERHELPER);
				for (IConfigurationElement configElementMarkerHelper : configElementsMarkerHelper) {
					String type = configElementMarkerHelper.getAttribute(ID_EXTENSIONPOINT_MARKERHELPER_ATTR_TYPE);
					IMarkerHelper iMarkerHelper = (IMarkerHelper) configElementMarkerHelper.createExecutableExtension(ID_EXTENSIONPOINT_MARKERHELPER_ATTR_HELPER);
					registeredMarkerHelper.put(type, iMarkerHelper);
				}
			}
			if (marker != null && marker.exists()) {
				String markerType = marker.getType();		
				return registeredMarkerHelper.get(markerType);
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), Status.OK, "VirSatProblemMarkerHelper: Could not initialize Helpers", e));
		}
		return new VirSatProblemMarkerHelper();
	}
	
	/**
	 * Method to check whether there are some markers in the Set which have been deleted in the meantime and 
	 * therefore do not exist anymore 
	 * (always have some issues with markers disappearing)
	 * @param markers The Set of markers to be checked
	 * @return The Set of existing markers
	 */
	public Set<IMarker> checkExistence(Set<IMarker> markers) {
		Set<IMarker> existingMarker = new HashSet<>();
		for (IMarker marker : markers) {
			if (marker.exists()) {
				existingMarker.add(marker);
			}
		}
		return existingMarker;
	}
	
	/**
	 * Each DVLMObject in VirSat has an identifier (at the moment either an IUuid or an IQualifiedName). 
	 * This method returns the value of this identifier. 
	 * It should be overridden if we want to identify our objects in a different way. 
	 * @param object The object (usually a DVLMObject) to get the identifier from
	 * @return The value of the identifier of the object
	 */
	protected String getIdentifierValue(Object object) {
		if (object instanceof IUuid) {
			return ((IUuid) object).getUuid().toString();
		} else if (object instanceof IQualifiedName) {
			return ((IQualifiedName) object).getFullQualifiedName();
		}
		return ID_VALUE_NOT_VALID;
	}
	
	/**
	 * Each DVLMObject in VirSat has an identifier (at the moment either an IUuid or an IQualifiedName). 
	 * This method returns the type of this identifier. 
	 * It should be overridden if we want to identify our objects in a different way. 
	 * @param object The object (usually a DVLMObject) to get the identifier from
	 * @return The identifier type of the object
	 */
	protected String getIdentifierType(Object object) {
		if (object instanceof IUuid) {
			return ID_TYPE_IUUID;
		} else if (object instanceof IQualifiedName) {
			return ID_TYPE_IQUALIFIEDNAME;
		}
		return ID_TYPE_NOT_VALID;
	}

	/**
	 * Method which creates a VirSatProblemMarker attached to the resource, 
	 * since we do not validate resources themselves, this method is not intended to be used outside of this class. 
	 * @param markerId The type of the VirSatProblemMarker (needs to be defined as an extension point of the org.eclipse.core.resources.problemmarker in plugin.xml)
	 * @param severity The severity of the VirSatProblemMarker
	 * @param message The message the VirSatProblemMarker should show
	 * @param resource The resource to be marked
	 * @return The created VirSatProblemMarker
	 */
	protected IMarker createMarker(String markerId, int severity, String message, IResource resource) {
		if (resource == null) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatProblemMarkerHelper: Could not create marker because resource is null"));
			return null;
		}
		try {
			IMarker marker = resource.createMarker(markerId);
			// Here only markers should be created which are subtypes of our virsat problem markers
			if (!marker.isSubtypeOf(ID_VIRSAT_PROBLEM_MARKER)) {
				marker.delete();
				return null;
			}
			marker.setAttribute(IMarker.SEVERITY, severity);
			marker.setAttribute(IMarker.MESSAGE, message);
			return marker;
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatProblemMarkerHelper: Could not create marker on resource: " + resource));
			return null;
		}
	}
	
	/**
	 * Method to create a VirSatProblemMarker
	 * only intended to be used in test cases
	 * @param severity The severity
	 * @param message The message
	 * @param eObject The object to be marked
	 * @return The marker 
	 */
	public IMarker createVirSatMarker(int severity, String message, EObject eObject) { 
		return createMarker(ID_VIRSAT_PROBLEM_MARKER, severity, message, eObject);
	}
	
	/**
	 * Method which creates a VirSatProblemMarker attached to the resource of the given DVLMObject.  
	 * This VirSatProblemMarker is then associated with the single DVLMObject, 
	 * by having attributes telling about the type and value of the identifier of the DVLMObject. 
	 * An object without a resource will be ignored. 
	 * @param markerId The type of the VirSatProblemMarker (needs to be defined as an extension point of the org.eclipse.core.resources.problemmarker in plugin.xml)
	 * @param severity The severity of the VirSatProblemMarker
	 * @param message The message the VirSatProblemMarker should show
	 * @param eObject The DVLMObject whose resource is marked
	 * @return The created VirSatProblemMarker
	 */
	protected IMarker createMarker(String markerId, int severity, String message, EObject eObject) {
		IResource resource = VirSatProjectCommons.getWorkspaceResource(eObject);
		
		String idType = getIdentifierType(eObject);
		String idValue = getIdentifierValue(eObject); 
		
		if (ID_TYPE_NOT_VALID.equals(idType) || ID_VALUE_NOT_VALID.equals(idValue)) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatProblemMarkerHelper: EObject has no valid identifier type or value: " + eObject));			
			return null;
		}
		
		IMarker marker = createMarker(markerId, severity, message, resource);
		if (marker == null) {
			return null;
		}
		
		try {
			marker.setAttribute(ATTRIBUTE_ID_TYPE, idType);
			marker.setAttribute(ATTRIBUTE_ID_VALUE, idValue);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatProblemMarkerHelper: Could not set marker attributes for eObject: " + eObject));
			return null;
		}
		
		return marker;
	}

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.project.markers.IMarkerHelper#isAssociatedWith(org.eclipse.core.resources.IMarker, org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public boolean isAssociatedWith(IMarker marker, EObject eObject) {
		if (marker == null || !marker.exists()) {
			return false;
		}
		try {
			if (!marker.isSubtypeOf(ID_VIRSAT_PROBLEM_MARKER)) {
				return false;
			}
		} catch (CoreException e1) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatProblemMarkerHelper: Could not check subtype of marker" + marker));			
			return false;
		}
		
		String objectIdType = getIdentifierType(eObject);
		String objectIdValue = getIdentifierValue(eObject);
		if (ID_TYPE_NOT_VALID.equals(objectIdType) || ID_VALUE_NOT_VALID.equals(objectIdValue)) {
			// annoying in log but probably somewhen necessary 
			//Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatProblemMarkerHelper: EObject has no valid identifier type or value: " + eObject));			
			return false;
		}

		String markerIdType = "";
		String markerIdValue = null;
		
		try {
			markerIdType = (String) marker.getAttribute(ATTRIBUTE_ID_TYPE);
			markerIdValue = (String) marker.getAttribute(ATTRIBUTE_ID_VALUE);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatProblemMarkerHelper: Could not get attributes for marker: " + marker));
			return false;
		}
		
		if (markerIdType.equals(objectIdType)) {
			if (markerIdValue.equals(objectIdValue)) {
				return true;
			}
		} 
		return false;
	}
	
	/**
	 * Method which gets the MarkerHelper which was responsible for creating the marker
	 * hence which knows how the markers are associated with the objects 
	 * @param marker The marker to be checked 
	 * @param eObject The object to be checked
	 * @return Whether the marker is associated with the object
	 */
	public boolean isAssociatedWithCheckAllHelpers(IMarker marker, EObject eObject) {
		IMarkerHelper markerHelper = VirSatProblemMarkerHelper.createMarkerHelper(marker);
		return markerHelper.isAssociatedWith(marker, eObject);
	}
	
	/**
	 * Method to get the VirSatProblemMarkers with a certain type from the resource.  
	 * Since we do not validate resources themselves, this method is not intended to be used outside of this class. 
	 * @param resource The resource to get the VirSatProblemMarkers from 
	 * @param markerId The type of the VirSatProblemMarkers
	 * @return The Set of VirSatProblemMarkers 
	 */
	protected Set<IMarker> getMarkers(IResource resource, String markerId) {
		try {
			IMarker[] markersArray = resource.findMarkers(markerId, true, IResource.DEPTH_ZERO);
			return new HashSet<IMarker>(Arrays.asList(markersArray));
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatProblemMarkerHelper: Could not retrieve markers of resource: " + resource));
		}
		return Collections.emptySet();
	}

	/**
	 * This method hands back those VirSatProblemMarkers of the certain type that
	 * are attached to the resource in which the given DVLMObject is contained in. 
	 * @param eObject the DVLMObject for which to get all VirSatProblemMarkers of its resource
	 * @param markerId The type of the VirSatProblemMarkers
	 * @return a Set of VirSatProblemMarkers of the resource
	 */
	protected Set<IMarker> getMarkersOfResource(EObject eObject, String markerId) {
		IResource resource = VirSatProjectCommons.getWorkspaceResource(eObject);
		
		if (resource != null && resource.exists()) {
			return getMarkers(resource, markerId);
		}
		return Collections.emptySet();
	}
	
	/**
	 * This method hands back a Set of VirSatProblemMarkers of a certain type for the given DVLMObject. 
	 * The VirSatProblemMarkers are retrieved by first getting the VirSatProblemMarkers of a certain type which are in the containing resource of the DVLMObject
	 * and then they are filtered by whether the VirSatProblemMarkers are associated with the given DVLMObject.
	 * @param eObject The DLVMObject for which to check if VirSatProblemMarkers exist
	 * @param markerId The type of the VirSatProblemMarkers
	 * @return a Set of VirSatProblemMarkers which are relevant for the given DVLMObject
	 */
	protected Set<IMarker> getMarkers(EObject eObject, String markerId) {
		Set<IMarker> filteredProblemMarkers = new HashSet<>();
		Set<IMarker> markers = getMarkersOfResource(eObject, markerId);
		
		for (IMarker marker : markers) {
			if (isAssociatedWithCheckAllHelpers(marker, eObject)) {
				filteredProblemMarkers.add(marker);
			}
		}

		return filteredProblemMarkers;
	}
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.project.markers.IMarkerHelper#getAllMarkers(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public Set<IMarker> getAllMarkers(EObject eObject) {
		return getMarkers(eObject, getMarkerID());
	}
	
	/**
	 * Method which hands back the VirSatProblemMarkers of a certain type that belong to the whole resource, if the DVLMObject is the head of this resource.
	 * If the DVLMObject is only contained in another DVLMObject, hence in its resource, the method will only hand back the VirSatProblemMarkers 
	 * of a cetain type which are associated to itself and the objects which it contains in turn. 
	 * @param eObject The DVLMObject which is checked 
	 * @param markerId The type of the VirSatProblemMarkers
	 * @return the Set of VirSatProblemMarkers for the DVLMObject and its contained objects 
	 */
	protected Set<IMarker> getMarkersForObjectAndContents(EObject eObject, String markerId) {
		Set<IMarker> markers;
		// if it is the RootElement in this resource then get all the ResourceProblemMarkers 
		if (VirSatEcoreUtil.isRootComponentofResource(eObject)) {
			markers = getMarkersOfResource(eObject, markerId);
		} else {
			// get resource markers for given element and its contents
			markers = getMarkers(eObject, markerId);
			TreeIterator<EObject> contentsIterator = eObject.eAllContents();

			try {
				while (contentsIterator.hasNext()) {
					EObject child = contentsIterator.next();
					markers.addAll(getMarkers(child, markerId));
				}
			} catch (Exception e) {
				// We can get an exception if the contents are changed by a new command while we are traversing it
				// It is probably safe to just ignore it, since all the refreshing will be called again after the new command
				Activator.getDefault().getLog().log(
						new Status(Status.WARNING, Activator.getPluginId(), "Error getting problem markers", e));
			}
		}
		
		return markers;
	}
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.project.markers.IMarkerHelper#getAllMarkersForObjectAndContents(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public Set<IMarker> getAllMarkersForObjectAndContents(EObject eObject) {
		return getMarkersForObjectAndContents(eObject, getMarkerID());
	}
	
	
	/**
	 * Method to delete all VirSatProblemMarkers of a certain type from a given resource. 
	 * Calling this method removes the VirSatProblemMarkers on infinite depth
	 * @param resource The resource from which to remove all VirSatProblemMarkers
	 * @param markerId The type of the VirSatProblemMarkers that should be removed from the resource
	 */
	protected void deleteMarkers(IResource resource, String markerId) {
		try {
			resource.deleteMarkers(markerId, true, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatProblemMarkerHelper: Could not delete marker from resource: " + resource));
		}
	}

	/**
	 * Method to delete the VirSatProblemMarkers of a certain type which are in the resource of the given object
	 * @param eObject The object whose markers shall be deleted
	 * @param markerId The type of marker which shall be deleted
	 */
	protected void deleteMarkersOfResource(EObject eObject, String markerId) {
		IResource resource = VirSatProjectCommons.getWorkspaceResource(eObject);
		if (resource != null && resource.exists()) {
			deleteMarkers(resource, markerId);
		}
	}
	
	/**
	 * Method to delete the VirSatProblemMarkers of a certain type which are associated with the given object
	 * @param eObject The object whose markers shall be deleted
	 * @param markerId The type of marker which shall be deleted
	 */
	protected void deleteMarkers(EObject eObject, String markerId) {
		Set<IMarker> markers = getMarkers(eObject, markerId);
		
		for (IMarker marker : markers) {
			try {
				marker.delete();
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatProblemMarkerHelper: Could not delete marker: " + marker));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.project.markers.IMarkerHelper#deleteAllMarkers(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void deleteAllMarkers(EObject eObject) {
		deleteMarkers(eObject, getMarkerID());
	}
	
	/**
	 * Method to delete the VirSatProblemMarkers of a certain type from an object and the objects it is containing and 
	 * which are in the same resource 
	 * @param eObject The Object to delete its markers
	 * @param markerId The marker type to be deleted
	 */
	protected void deleteMarkersForObjectAndContents(EObject eObject, String markerId) {
		Set<IMarker> markers = getMarkersForObjectAndContents(eObject, markerId);
		
		for (IMarker marker : markers) {
			try {
				marker.delete();
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatProblemMarkerHelper: Could not delete marker: " + marker));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.project.markers.IMarkerHelper#deleteAllMarkersForObjectAndContents(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void deleteAllMarkersForObjectAndContents(EObject eObject) {
		deleteMarkersForObjectAndContents(eObject, getMarkerID());
	}
	
	/**
	 * Method to delete all Markers of a certain type in the whole workspace
	 * @param markerId The type of marker to be removed
	 */
	protected void deleteMarkersInWorkspace(String markerId) {
		deleteMarkers(ResourcesPlugin.getWorkspace().getRoot(), markerId);
	}
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.project.markers.IMarkerHelper#deleteAllMarkersInWorkspace()
	 */
	@Override
	public void deleteAllMarkersInWorkspace() {
		deleteMarkersInWorkspace(getMarkerID());
	}

}
