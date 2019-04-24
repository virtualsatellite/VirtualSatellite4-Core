/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Error that is thrown by the DVLM data model in case a referenced
 * object in another resource cannot be resolved. This means when a Proxy should
 * be resolved but it isn't this class will be fired and wrapped into a diagnostic
 * of its resource
 * @author fisc_ph
 *
 */
public class DVLMUnresolvedReferenceException extends Exception implements Resource.Diagnostic {

	private static final long serialVersionUID = 265184815586257902L;
	protected EObject proxy;

	/**
	 * Constructor of the Exception that indicates an unresolvable reference 
	 * @param proxy the proxy EObject which cannot be resolved
	 */
	public DVLMUnresolvedReferenceException(EObject proxy) {
		this.proxy = proxy;
	}

	public static final String ERR_MESSAGE = "Cannot resolve proxy object to resource: ";
	
	@Override
	public String getMessage() {
		URI proxyURI = ((InternalEObject) proxy).eProxyURI();
		return ERR_MESSAGE + ((proxyURI != null) ? proxyURI.toString() : ERR_NO_RESOURCE_LOCATION);
	}
	
	/**
	 * Call this static method to check if the given object is in a proxy state.
	 * in case it is in a proxy  state, the method will create a diagnostic and
	 * place it to the resource errors. This should trigger all listeners such
	 * as EContentAdapters of the resource to indicate issues with resolving this
	 * object
	 * @param resolvedProxy the object that has been potantially resolved
	 * @param proxy The object which should be checked for its proxy state
	 * @param resource The resource where to place the error. otherwise nothing will happen if resource is null
	 * @return Hands back the proxy object unmodified
	 */
	public static EObject checkProxyObject(EObject resolvedProxy, EObject proxy, Resource resource) {
 		if (proxy != null) {
			if (resource != null) {
				Resource.Diagnostic diagnostic = new DVLMUnresolvedReferenceException(proxy);
				// CVheck if the diagnostic is already there if yes don't add it otherwise
				// it will notify the editor which will trigger the update of everything, which calls the method to
				// resolve the linked resource, which will call this method, which will go into an infinite loop
				if (!resource.getErrors().contains(diagnostic) && resolvedProxy.eIsProxy()) {
					resource.getErrors().add(diagnostic);
				}
				
				// But in case it is not a proxy anymore and if an error for that proxy is still pending than remove it.
				if (resource.getErrors().contains(diagnostic) && !resolvedProxy.eIsProxy()) {
					resource.getErrors().remove(diagnostic);
				}
			}
		}
		return resolvedProxy;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof DVLMUnresolvedReferenceException) {
			DVLMUnresolvedReferenceException rhsException = (DVLMUnresolvedReferenceException) obj;
			return this.proxy.equals(rhsException.proxy);
		}

		return super.equals(obj);
	}

	/**
	 * Returns the proxy this diagnostic refers to
	 * @return the proxy object
	 */
	public EObject getProxy() {
		return proxy;
	}
	
	@Override
	public int hashCode() {
		return proxy.hashCode();
	}
	
	public static final String ERR_NO_RESOURCE_LOCATION = "No resource Location!";
	public static final int LOCATION_CURSOR_UNKNOWN = 0;
	
	@Override
	public String getLocation() {
		Resource res = proxy.eResource();
		return (res != null) ? res.getURI().toString() : ERR_NO_RESOURCE_LOCATION;
	}

	@Override
	public int getLine() {
		return LOCATION_CURSOR_UNKNOWN;
	}

	@Override
	public int getColumn() {
		return LOCATION_CURSOR_UNKNOWN;
	}
}
