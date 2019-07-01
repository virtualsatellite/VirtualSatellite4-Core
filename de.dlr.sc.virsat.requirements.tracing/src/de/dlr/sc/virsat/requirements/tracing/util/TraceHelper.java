/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.rmf.reqif10.SpecHierarchy;

import de.dlr.sc.virsat.requirements.tracing.Activator;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TMFactory;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceabilityLinkContainer;

/**
 * @author Tobias Franz tobias.franz@dlr.de
 *
 */
public class TraceHelper {
	
	/**
	 * To protect the constructor
	 */
	protected TraceHelper() {
		
	}

	public static final String TRACE_MODEL_EXTENSION = "tm";
	public static final String REQUIREMENTS_MODEL_EXTENSION = "reqif";

	/**
	 * Get trace model URI from reqIF URI. If it is a trace URI already then it is
	 * not changed
	 * 
	 * @param reqModelURI
	 *            the reqIF URI
	 * @return the trace model URI
	 */
	public static URI getTraceModelURI(URI reqModelURI) {
		URI newUri = reqModelURI.trimSegments(1);
		String reqFileName = reqModelURI.lastSegment();
		String traceFileName = reqFileName.replace(REQUIREMENTS_MODEL_EXTENSION, TRACE_MODEL_EXTENSION);
		newUri = newUri.appendSegment(traceFileName);
		return newUri;
	}

	/**
	 * returns the IResource of an element
	 * 
	 * @param element
	 *            the trace element
	 * @return the IResource
	 */
	public static IResource getIResourceValue(EObject element) {
		if (element.eResource() != null) {
			Path path = new Path(element.eResource().getURI().toPlatformString(false));
			IResource iResource = ResourcesPlugin.getWorkspace().getRoot().findMember(path.toString());
			return iResource;
		}
		return null;
	}


	/**
	 * Validates if the given resources is a trace model
	 * 
	 * @param file
	 *            the resource
	 * @return boolean if it is a trace model
	 */
	public static boolean isTraceModel(IFile file) {
		boolean isTraceModel = false;
		String fileExtension = file.getFileExtension();
		if (fileExtension != null && fileExtension.equals(TRACE_MODEL_EXTENSION)) {
			isTraceModel = true;
		}
		return isTraceModel;
	}
	
	/**
	 * Validates if the given resources is a trace model
	 * 
	 * @param fileUri
	 *            the resource URI
	 * @return boolean if it is a trace model
	 */
	public static boolean isTraceModel(URI fileUri) {
		if (fileUri == null || fileUri.fileExtension() == null) {
			return false;
		} else if (fileUri.fileExtension().equals(TRACE_MODEL_EXTENSION)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the trace model root of the given resource
	 * 
	 * @param resource
	 *            the given resource
	 * @return the respected trace model container
	 */
	public static TraceabilityLinkContainer getTraceModel(Resource resource) {
		URI resourceUri = resource.getURI();
		ResourceSet set = new ResourceSetImpl();
		URI traceUri = getTraceModelURI(resourceUri);
		resource = set.createResource(traceUri);
		try {
			if (set.getURIConverter().exists(traceUri, null)) {
				resource.load(null);
			}
		} catch (IOException e) {
			Activator.getDefault().getLog()
					.log(new Status(Status.ERROR, Activator.getPluginId(), "Could not load the trace model!", e));
			return null;
		}
		for (EObject object : resource.getContents()) {
			if (object instanceof TraceabilityLinkContainer) {
				TraceabilityLinkContainer container = (TraceabilityLinkContainer) object;
				return container;
			}
		}
		return null;
	}

	/**
	 * Adds the given trace element the corresponding trace model
	 * 
	 * @param traceElement
	 *            the given trace Element
	 */
	public static void persistTraceElement(TraceElement traceElement) {
		SpecHierarchy specHierarchy = (SpecHierarchy) traceElement.getSourceTraceElement().get(0);
		URI traceURI = getTraceModelURI(specHierarchy.eResource().getURI());

		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put(TRACE_MODEL_EXTENSION, new XMIResourceFactoryImpl());

		ResourceSet resourceSet = specHierarchy.eResource().getResourceSet();

		Resource traceabilityModelResource = null;
		EObject traceabilityModelObject = null;
		if (resourceSet.getURIConverter().exists(traceURI, m)) {

			// Load existing resource
			traceabilityModelResource = resourceSet.getResource(traceURI, true);
			traceabilityModelObject = traceabilityModelResource.getContents().get(0);
			if (traceabilityModelObject instanceof TraceabilityLinkContainer) {
				TraceabilityLinkContainer model = (TraceabilityLinkContainer) traceabilityModelObject;
				model.getTraceElements().add(((TraceElement) traceElement));

			}
		} else {

			// Create new resource
			traceabilityModelObject = TMFactory.eINSTANCE.createTraceabilityLinkContainer();
			if (traceabilityModelObject instanceof TraceabilityLinkContainer) {
				TraceabilityLinkContainer model = (TraceabilityLinkContainer) traceabilityModelObject;
				model.getTraceElements().add(((TraceElement) traceElement));
			}
			traceabilityModelResource = resourceSet.createResource(traceURI);
			traceabilityModelResource.getContents().add(traceabilityModelObject);
		}
		try {
			traceabilityModelResource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			Activator.getDefault().getLog()
					.log(new Status(Status.ERROR, Activator.getPluginId(), "Could not save trace model!", e));
		}
	}

}
