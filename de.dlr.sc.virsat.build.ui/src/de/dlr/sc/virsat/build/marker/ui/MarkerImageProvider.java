/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.marker.ui;

import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.dlr.sc.virsat.build.ui.Activator;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;

/**
 * This class deals with the UI related parts such as
 * Images of the Resource Problems related to DVLM
 * @author fisc_ph
 *
 */
public class MarkerImageProvider {
	
	protected IMarkerHelper vpmHelper;
	
	/**
	 * Method to return the marker helper
	 * @return The marker Helper
	 */
	public IMarkerHelper getVpmHelper() {
		return vpmHelper;
	}

	private Image imageOk;
	private Image imageInfo;
	private Image imageWarn;
	private Image imageError;

	private ImageDescriptor descriptorImageWarn;
	private ImageDescriptor descriptorImageError;

	/**
	 * Constructor of the helper that initializes and manages the images needed for UI
	 * @param vpmh helper for getting the problem markers
	 */
	public MarkerImageProvider(IMarkerHelper vpmh) {
		this.vpmHelper = vpmh;
		imageOk = Activator.getDefault().getImageRegistry().get(Activator.IMG_THUMBS_UP);
		imageInfo = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
		imageWarn = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
		imageError = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		
		descriptorImageWarn = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);
		descriptorImageError = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);
	}

	/**
	 * Get problem image for a given eObject
	 * @param eObject 
	 * @return image or null if there are no resource markers for this eObject
	 */
	public Image getProblemImageForEObject(EObject eObject) {
		Set<IMarker> markers = vpmHelper.getAllMarkersForObjectAndContents(eObject);
		return getProblemImageForSeverity(getHighestSeverityForMarkers(markers));
	}

	/**
	 * Get problem image tooltip for a given element
	 * @param eObject 
	 * @return tooltip text
	 */
	public String getProblemImageToolTipForEObject(EObject eObject) {
		Set<IMarker> markers = vpmHelper.getAllMarkersForObjectAndContents(eObject);
		return getToolTipForMarkers(markers);
	}
	
	/**
	 * Get problem image descriptor a given object
	 * @param eObject 
	 * @return image descriptor or null if there are no resource markers for this object or severity is below warning
	 */
	public ImageDescriptor getProblemImageDescriptorForEobject(EObject eObject) {
		Set<IMarker> markers = vpmHelper.getAllMarkersForObjectAndContents(eObject);
		return getProblemImageDescriptorForSeverity(getHighestSeverityForMarkers(markers));
	}
	
	/**
	 * Returns the highest severity of given resource markers
	 * @param markers 
	 * @return IMarker.SEVERITY or IMarker.SEVERITY_WARNING or IMarker.SEVERITY_INFO. If the Set is empty returns -1
	 */
	protected int getHighestSeverityForMarkers(Set<IMarker> markers) {
		int noneValue = -1;
		boolean hasWarning = false;
		boolean hasInfo = false;
		try {
			for (IMarker marker : markers) {
				Integer severity = (Integer) marker.getAttribute(IMarker.SEVERITY);
				if (severity.equals(IMarker.SEVERITY_ERROR)) {
					return IMarker.SEVERITY_ERROR;
				} else if (severity.equals(IMarker.SEVERITY_WARNING)) {
					hasWarning = true;
				} else if (severity.equals(IMarker.SEVERITY_INFO)) {
					hasInfo = true;
				}
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(),
					"Failed to retrieve correct severity image for resource marker"));
		}

		if (hasWarning) {
			return IMarker.SEVERITY_WARNING;
		} else if (hasInfo) {
			return IMarker.SEVERITY_INFO;
		} else {
			return noneValue;
		}
	}
	
	/**
	 * Get problem image for given severity
	 * @param severity 
	 * @return if severity is IMarker.SEVERITY or IMarker.SEVERITY_WARNING or IMarker.SEVERITY_INFO returns corresponding image, otherwise returns null
	 */
	public Image getProblemImageForSeverity(int severity) {
		if (severity == IMarker.SEVERITY_ERROR) {
			return imageError;
		}
		if (severity == IMarker.SEVERITY_WARNING) {
			return imageWarn;
		}
		if (severity == IMarker.SEVERITY_INFO) {
			return imageInfo;
		}
		return null;
	}

	/**
	 * Get problem image descriptor for given severity
	 * @param severity 
	 * @return if severity is IMarker.SEVERITY or IMarker.SEVERITY_WARNING returns corresponding image descriptor, otherwise returns null
	 */
	private ImageDescriptor getProblemImageDescriptorForSeverity(int severity) {
		if (severity == IMarker.SEVERITY_ERROR) {
			return descriptorImageError;
		}
		if (severity == IMarker.SEVERITY_WARNING) {
			return descriptorImageWarn;
		}
		return null;
	}

	/**
	 * Returns OK image
	 * @return OK image
	 */
	public Image getOkImage() {
		return imageOk;
	}
	
	/**
	 * Get tooltip text for markers
	 * @param markers 
	 * @return tooltip text
	 */
	protected String getToolTipForMarkers(Set<IMarker> markers) {
		StringBuilder toolTipText = new StringBuilder();
		for (IMarker marker : markers) {
			String markerText = (String) marker.getAttribute(IMarker.MESSAGE, "");
			if (toolTipText.length() != 0 && !markerText.isEmpty()) {
				toolTipText.append(System.lineSeparator());
			}
			toolTipText.append(markerText);
		}

		return toolTipText.toString();
	}
}
