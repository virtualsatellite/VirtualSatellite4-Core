/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.catia;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;

/**
 * This class creates .json file and copies .stl files
 */

public class CatiaFileWriter {
	
	/**
	 * Exports productRoot to json and copies it to outputJsonFilePath
	 * Also copies all geometry files to the same directory
	 * @param outputJsonFilePath 
	 * @param productRoot 
	 */
	public void writeFiles(String outputJsonFilePath, IBeanStructuralElementInstance productRoot) {
	}
}
