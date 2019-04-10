/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.project.filecontent;

import de.dlr.sc.virsat.model.concept.project.IProjectBuilderInfo;

/**
 * Interface class for file generators based on xtend
 * @author fisc_ph
 *
 */
public interface IFileContentGenerator {

	/**
	 * Method to get the content that should be generated. This method is used
	 * quite often by the actual file descriptors.
	 * @param projectBuilderInfo Builder info to enable dynamic creating of the file content
	 * @return the content of the file to be generated as string
	 */
	String generateContent(IProjectBuilderInfo projectBuilderInfo);
}
