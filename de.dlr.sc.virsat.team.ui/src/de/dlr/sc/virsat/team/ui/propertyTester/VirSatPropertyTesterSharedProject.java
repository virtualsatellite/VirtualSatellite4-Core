/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.team.ui.propertyTester;

import org.eclipse.core.internal.propertytester.ResourcePropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * a class to define property tester to check if project is connected to svn or not
 * @author desh_me
 *
 */

@SuppressWarnings("restriction")
public class VirSatPropertyTesterSharedProject extends ResourcePropertyTester {

	public static final String SVN_ENABLED_PROPERTY = "svnEnabled";
	public static final String GIT_ENABLED_PROPERTY = "gitEnabled";
	
	/**
	 * The constructor
	 */
	public VirSatPropertyTesterSharedProject() {
		super();
	}
	
	@Override
	public boolean test(Object receiver, String method, Object[] args, Object expectedValue) {
		IStructuredSelection structuredSelection = new StructuredSelection(receiver);
		VirSatSelectionHelper virSatSelectionHelper = new VirSatSelectionHelper(structuredSelection);
		IProject iProject = virSatSelectionHelper.getProjectResource();
		
		if (iProject != null) {
			if (method.equals(GIT_ENABLED_PROPERTY)) {
				RepositoryMapping mapping = RepositoryMapping.getMapping(iProject);
				return mapping != null && mapping.getRepository() != null;
			} else if (method.equals(SVN_ENABLED_PROPERTY)) {
				return super.test(iProject, ResourcePropertyTester.PROJECT_PERSISTENT_PROPERTY, args, expectedValue);
			} else {
				throw new IllegalArgumentException("Unknown persistence property check: " +  method);
			}
		} else {
			return false;
		}
	}
}
