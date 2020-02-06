/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.util.svn;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.IDecorationContext;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.team.svn.ui.decorator.SVNLightweightDecorator;

/**
 * Util Class to access the SvnLightweightDeocrator in
 * a more simple and encapsulated way, to finally get the branch
 * and revision string from a given workspace resource.
 */
public class VirSatSvnRevisionStatusUtil {

	protected String decorationSuffix;
	protected String decorationPrefix;

	/**
	 * Builds a String using the SvnLightweightDecorator to show the current Revision
	 * and the current Branch when checking on a Project resource.
	 * @param resource The resource for which to get the deocration String
	 * @return The decoration String
	 */
	public String getResourceStatus(IResource resource) {

		// Instantiate the SVN Lightweight Decorator to get the same Revion-string 
		// as it is used in the Eclipse based navigators
		ILightweightLabelDecorator decorator = new SVNLightweightDecorator() {

			/**
			 * Override to the decorate method that simply just initializes the SVNLightweightdecorator
			 * and asks to decorate the given resource.
			 */
			@Override
			public void decorate(Object element, IDecoration decoration) {
				this.loadConfiguration();
				this.decorateResource((IResource) element, decoration);
			}
		};

		// Now call the decorator and use the decoration interface to get the Revision String
		decorationPrefix = "";
		decorationSuffix = "";
		decorator.decorate(resource, new IDecoration() {
			@Override
			public void addPrefix(String prefix) {
				decorationPrefix += prefix;
			}
			@Override
			public void addSuffix(String suffix) {
				decorationSuffix += suffix;
			}
			@Override
			public void addOverlay(ImageDescriptor overlay) {
			}
			@Override
			public void addOverlay(ImageDescriptor overlay, int quadrant) {
			}
			@Override
			public void setForegroundColor(Color color) {
			}
			@Override
			public void setBackgroundColor(Color color) {
			}
			@Override
			public void setFont(Font font) {
			}
			@Override
			public IDecorationContext getDecorationContext() {
				return null;
			}
		});

		return decorationPrefix + resource.getFullPath().toOSString() + decorationSuffix;
	}

	/**
	 * Tells if there was a prefix when getResourceStatus was called
	 * @return true in case a prefix was found
	 */
	public boolean hasPrefix() {
		return !this.decorationPrefix.isEmpty();
	}

	/**
	 * Call this method to get a String showing all files of the current resource
	 * which have a status/change compared to the repository.
	 * @param resource The root resource from which to look for changes
	 * @return the concatenated multi-line string with the status of the changed files
	 * @throws CoreException In case things go wrong an exception may be thrown
	 */
	public String getWorkspaceChangedStatus(IResource resource) throws CoreException {
		StringBuilder recursiveString = new StringBuilder();

		// Walk over all resources in the project
		resource.accept(new IResourceVisitor() {
			@Override
			public boolean visit(IResource resource) throws CoreException {
				// Only get the status of files and projects
				if (resource instanceof IFile || resource instanceof IProject) {
					String resourceStatus = VirSatSvnRevisionStatusUtil.this.getResourceStatus(resource);

					// On files only print the status if it has a prefix
					// On projects always print so that the revision and branch can be seen.
					if (resource instanceof IProject || VirSatSvnRevisionStatusUtil.this.hasPrefix()) {
						recursiveString.append(resourceStatus + System.lineSeparator());
					}
				}

				// Iterate down to the deepest files
				return true;
			}
		}, IResource.DEPTH_INFINITE, IContainer.INCLUDE_HIDDEN);

		// And hand back the result from the string builder
		return recursiveString.toString();
	}
}
