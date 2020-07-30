/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.project.Activator;

/**
 * Utility class for VirSat ResourceSets
 * @author fisc_ph
 *
 */
public class VirSatResourceSetUtil {
	
	/**
	 * Should not be called in utility class
	 */
	private VirSatResourceSetUtil() {
	}
	
	/**
	 * Checks if a resource has been changed by performing a byte-by-byte comparison.
	 * Method copied from: ResourceImpl in the EMF library.
	 * NOTE: Support for compression using ZIP streams has been removed since carrying this over
	 * would be a pain and we don't use it right now.
	 * @param resource the resource which will be checked if it is different than the one
	 * currently saved on the filesystem
	 * @param saveOptions EMF save options to be used
	 * @param loadOptions EMF load options to be used
	 * @return true iff the resource on the file system is different than the passed in memory resource
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isChanged(Resource resource, Map saveOptions, Map loadOptions) {
		try {
			ResourceSet rs = resource.getResourceSet();
			if (rs == null) {
				return false;
			}
			
			URIConverter uriConverter = rs.getURIConverter();
			
			/**
			 * Internal class
			 * @author muel_s8
			 *
			 */
			class InMemoryByteArrayOutputStream extends ByteArrayOutputStream {
				/**
				 * Get the buffer
				 * @return internal buffer
				 */
				public byte[] buffer() {
					return buf;
				}
	
				/**
				 * Get the buffer length
				 * @return buffer length
				 */
				public int length() {
					return count;
				}
			}
			
			// First try to store the current Resource into Memory
			InMemoryByteArrayOutputStream inMemoryBuffer = new InMemoryByteArrayOutputStream();
			try {
				boolean resourceSendsNotifications = resource.eDeliver();
				resource.eSetDeliver(false);
				resource.save(inMemoryBuffer, saveOptions);
				resource.eSetDeliver(resourceSendsNotifications);
			} finally {
				inMemoryBuffer.close();
			}
	
			byte [] inMemoryContentBuffer = inMemoryBuffer.buffer();
			int inMemoryContentBufferLength = inMemoryBuffer.length();
	
			// Now load the resource from disk
			InputStream resourceContentInputStream = null;
			try {
				resourceContentInputStream = uriConverter.createInputStream(resource.getURI(), loadOptions);
			} catch (IOException exception) {
				return true;
			} 
			
			// Start comparing them
			if (resourceContentInputStream != null) {
				try {
					byte [] resourceContentBuffer = new byte [inMemoryContentBufferLength];
					int count = resourceContentInputStream.read(resourceContentBuffer); 
					
					// Now load from the original Resource Input Stream but not more than the
					// in memory representation has, if there is more content in the resource,
					// there is already a difference
					while (count > 0 && count < inMemoryContentBufferLength) {
						int more = resourceContentInputStream.read(resourceContentBuffer, count, resourceContentBuffer.length - count);
						if (more <= 0) {
							break;
						} else {
							count += more;
						}
					}
					// if both contents are equally long and the reading has come to an end
					// than start to compare byte by byte
					boolean resourceContentEOF = resourceContentInputStream.read() == -1;
					
					if (count == inMemoryContentBufferLength && resourceContentEOF) {
						for (int i = 0; i < inMemoryContentBufferLength; ++i) {
							if (resourceContentBuffer[i] != inMemoryContentBuffer[i]) {
								return true;
							}
						}
					} else {
						return true;
					}
				} finally {
					resourceContentInputStream.close();
				}
			}
			
			return false;
		
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to check if Resource has changed " + e.getMessage()));				
		}
		
		return true;
	}
	
	/**
	 * Removes upon execution removes all dangling references contained in a resource
	 * @param resource the resource we wish to free from dangling references
	 */
	public static void removeDanglingReferences(Resource resource) {
		Map<EObject, Collection<Setting>> unresolvedProxies = EcoreUtil.UnresolvedProxyCrossReferencer.find(resource);
		
		for (Entry<EObject, Collection<Setting>> entry : unresolvedProxies.entrySet()) {
			EObject proxy = entry.getKey();
			Collection<Setting> settings = entry.getValue();
			for (Setting setting : settings) {
				EObject eContainer = setting.getEObject();
				EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
				
				// Ignore EReference types
				boolean isEReferenceType = eStructuralFeature.equals(PropertydefinitionsPackage.Literals.EREFERENCE_PROPERTY__REFERENCE_TYPE);
				
				if (!isEReferenceType) {
					if (eStructuralFeature.isMany()) {
						((EList<?>) eContainer.eGet(eStructuralFeature)).remove(proxy);
					} else {
						eContainer.eUnset(eStructuralFeature);
					}
				} 
				
			}
		}
	}
}
