/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;

/**
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public interface ISystemModelContentProvider {
	/**
	 * @return proper content provider for the project
	 */
	IContentProvider getContentProvider();
	
	/**
	 * @return proper label provider for the project
	 */
	ILabelProvider getLabelProvider();
	
	/**
	 * @param project the project
	 * @return proper input
	 */
	Object getInput(IProject project);
	
	/**
	 * @param project the project
	 * @return possible artifacts to generate from requirements
	 */
	Object getTypeListInput(IProject project);
	
	/**
	 * @return proper content provider for artifact generation
	 */
	IContentProvider getTypeListContentProvider();
	
	/**
	 * @return proper label provider for artifact generation
	 */
	ILabelProvider getTypeListLabelProvider();
	
}
