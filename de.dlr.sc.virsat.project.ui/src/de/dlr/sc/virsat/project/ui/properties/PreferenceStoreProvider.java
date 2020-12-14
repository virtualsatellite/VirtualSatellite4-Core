/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * 
 *
 */
public class PreferenceStoreProvider {

	private static final String QUALIFIER = "de.dlr.sc.virsat.project";
	
	private PreferenceStoreProvider() {
	}	
	
	static ScopedPreferenceStore scopedPreferenceStore;
	

	/**
	 * Get scoped preference store for the context object
 	 * @param context Context object
	 * @return scoped preference store
	 */
	public static ScopedPreferenceStore getScopedPreferenceStore() {
		IProject project = getProject();
		return getScopedPreferenceStore(project);
	}
	
	/**
	 * Get scoped preference store for the project
	 * @param project Project
	 * @return scopred preference store
	 */
	private static ScopedPreferenceStore getScopedPreferenceStore(IProject project) {
		if (scopedPreferenceStore != null) {
			return scopedPreferenceStore;
		} else {
			ProjectScope projectScope = new ProjectScope(project);
			scopedPreferenceStore = new ScopedPreferenceStore(projectScope, QUALIFIER);
			scopedPreferenceStore.setSearchContexts(new IScopeContext[] { projectScope, InstanceScope.INSTANCE });			
			return scopedPreferenceStore;
		}
	}
	
	/**
	 * Get project for the selection
	 * @return project object
	 */
	public static IProject getProject() {
		ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		ISelection selection = selectionService.getSelection();
		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(selection);
		return selectionHelper.getProjectResource();
	}
	
}
