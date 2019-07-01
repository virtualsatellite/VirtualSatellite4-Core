/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.ui.reqif.util;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;

/**
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public class ReqIFUtil {
	
	/**
	 * Protect constructor
	 */
	protected ReqIFUtil() {
		
	}
	
	public static final String REQ_IF_FILE_EXTENSION = "reqif";
	
	/**
	 * @param fileUri uri of the file under question
	 * @return checks if the file is Reqif file, if so return true
	 * TODO: Check root element instead
	 */
	public static boolean isReqIF(URI fileUri) {
		if (fileUri == null || fileUri.fileExtension() == null) {
			return false;
		} else if (fileUri.fileExtension().equals(REQ_IF_FILE_EXTENSION)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param resource of the file under question
	 * @return checks if the file is Reqif file, if so return true
	 * TODO: Check root element instead
	 */
	public static boolean isReqIF(IResource resource) {
		if (resource == null || resource.getFullPath().getFileExtension() == null) {
			return false;
		} else if (resource.getFullPath().getFileExtension().equals(REQ_IF_FILE_EXTENSION)) {
			return true;
		}
		return false;
	}

}
