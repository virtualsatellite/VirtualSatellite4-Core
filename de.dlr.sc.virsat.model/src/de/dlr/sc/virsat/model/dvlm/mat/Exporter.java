/*******************************************************************************
 * Copyright (c) 2008-2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.mat;

import java.io.IOException;

import org.eclipse.emf.common.util.EList;

import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;

/**
 * Class for exporting data to .mat
 */
public class Exporter {
	
	/**
	 * creates a new .mat if needed
	 * @param path path of the new .Mat
	 */
	public static void newMatFile(String path) throws IOException {
		MatFile matfile = Mat5.newMatFile();
		matfile.addArray("a", Mat5.newScalar(54));
		Mat5.writeToFile(matfile, path);
	}
	
	/**
	 * creates a new .mat if needed
	 * @param seiRoot path of the new .Mat
	 */
	public static MatFile exportSei(StructuralElementInstance seiRoot) throws IOException {
		MatFile matfile = Mat5.newMatFile();
		
		Struct myStruct = Mat5.newStruct();
		myStruct.set("Type", Mat5.newString(seiRoot.getType().getName()))
			.set("UUID", Mat5.newString(seiRoot.getUuid().toString()))
			.set("Children", Mat5.newString(seiRoot.getChildren().toString()));
		for (int i = 0; i < seiRoot.getCategoryAssignments().size(); i++) {
			myStruct.set(seiRoot.getCategoryAssignments().get(i).getName(), Mat5.newString(" "));
		}
		matfile.addArray(seiRoot.getName(), myStruct);
		return matfile;
	}
}
