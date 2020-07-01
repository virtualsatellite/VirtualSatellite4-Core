/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.test;

import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

import de.dlr.sc.virsat.project.structure.nature.VirSatProjectNature;

public class TestNature extends VirSatProjectNature implements IProjectNature {

	public static final String NATURE_ID = "de.dlr.sc.virsat.project.test.natures.TestNature";
	
	public static final String BUILDER_INVOCATIONCHECK_ID = "de.dlr.sc.virsat.project.test.builders.TestInvocationCheckBuilder";
	
	
	@Override
	public void configure() throws CoreException {
		addBuilder(getProject().getDescription(), BUILDER_INVOCATIONCHECK_ID);
	}

}
