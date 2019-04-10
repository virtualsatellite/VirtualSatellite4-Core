/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons.debug;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

/**
 * This Listener is intended to just display what happened on the resources of the workspace
 * it is intended for debugging since it is sometimes difficult to fully understand
 * the behavior of the WorkspaceSychronizer
 * @author fisc_ph
 *
 */
public class VirSatDebugResourceChangeListener implements IResourceChangeListener {

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		//we are only interested in POST_CHANGE events
        if (event.getType() != IResourceChangeEvent.POST_CHANGE) {
        	return;
        }
        
        IResourceDelta rootDelta = event.getDelta();
		try {
			rootDelta.accept(new IResourceDeltaVisitor() {
				@Override
				public boolean visit(IResourceDelta delta) throws CoreException {
					IResource resource = delta.getResource();
					if (resource != null) {
						String resourceLocation = resource.getLocation().toOSString();
						if (delta.getKind() == IResourceDelta.ADDED) {
							System.out.println("DebugResourceChangeListener: Resource: " + resourceLocation + " Kind: ADDED");
						}
						if (delta.getKind() == IResourceDelta.CHANGED) {
							System.out.println("DebugResourceChangeListener: Resource: " + resourceLocation + " Kind: CHANGED");
						}
						if (delta.getKind() == IResourceDelta.REMOVED) {
							System.out.println("DebugResourceChangeListener: Resource: " + resourceLocation + " Kind: CHANGED");
						}
					}
					return true;
				}
			});
		} catch (CoreException e) {
			
			e.printStackTrace();
		}
	}
}
