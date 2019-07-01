/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.ui.property;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.requirements.tracing.builder.TraceNature;
import de.dlr.sc.virsat.requirements.tracing.ui.Activator;

/**
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public class ProjectPropertyTraceNatureTester extends PropertyTester {

	private static final String PROPERTY_NAME_IS_ENABLED = "isEnabled";
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (PROPERTY_NAME_IS_ENABLED.equals(property)) {
			final IProject project = (IProject) Platform.getAdapterManager().getAdapter(receiver, IProject.class);

			if (project != null) {
				return hasNature(project);
			}
		}

		return false;
	}
	/**
	 * Checks if the project has the traceability nature
	 * @param project the project
	 * @return if it has true  else false
	 * @author bell_er
	 */
	public static final boolean hasNature(final IProject project) {
		try {
			IProjectNature nature = project.getNature(TraceNature.NATURE_ID);
			return !(nature == null);

		} catch (final CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Could not check if trace nature is enabled", e));
		}
		return false;
	}

}
