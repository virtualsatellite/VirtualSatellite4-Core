/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.ui.propertyTester;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Platform;

import de.dlr.sc.virsat.model.concept.ui.handlers.AddResourceBuilder;
/**
 * This class tests if the resource builder is enabled or not
 * @author bell_er
 */
public class TestResourceBuilderEnabled extends PropertyTester {

	private static final String IS_ENABLED = "isEnabled";

	@Override
	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {

		if (IS_ENABLED.equals(property)) {
			final IProject project = (IProject) Platform.getAdapterManager().getAdapter(receiver, IProject.class);

			if (project != null) {
				return AddResourceBuilder.hasBuilder(project);
			}
		}

		return false;
	}
}