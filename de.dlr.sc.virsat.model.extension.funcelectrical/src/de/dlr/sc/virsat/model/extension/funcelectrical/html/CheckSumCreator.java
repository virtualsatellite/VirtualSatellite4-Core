/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.html;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;

/**
 * Class for Calculating Checksum of Structural Element Instances
 * @author bell_er
 */
class CheckSumCreator {
	
	private static final int HEXCONVERTER = 16;
	private static final int MASK = 0xff;
	private static final int HASH = 0x100;
	private static final int LENGTH = 1024;
	private static final String HASHALGORITHM = "MD5";
	/**
	 * Class for Calculating Checksum of Structural Element Instances
	 * @author bell_er
	 */
	private CheckSumCreator() {
		
	}
	/**
	 * Method for Calculating Checksum of Structural Element Instances
	 * @param sc the structural element
	 * @return the calculated checksum
	 */
	public static String getObjectChecksum(StructuralElementInstance sc) {
		Resource resource = sc.eResource();
		if (resource != null) {
			URIConverter uriConverter = resource.getResourceSet().getURIConverter();
			try {
				//Use hash algorithm
				MessageDigest md5Digest = MessageDigest.getInstance(HASHALGORITHM);
				//get the inputStream
				InputStream fis = uriConverter.createInputStream(resource.getURI(), null);
				byte[] byteArray = new byte[LENGTH];
				int bytesCount = 0; 
				//Read file data and update in message digest
				while ((bytesCount = fis.read(byteArray)) != -1) {
					md5Digest.update(byteArray, 0, bytesCount);
				}
				//close the stream; We don't need it now.
				fis.close();

				//Get the hash's bytes
				byte[] bytes = md5Digest.digest();

				//Convert it from decimal to hexadecimal format
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < bytes.length; i++) {
					sb.append(Integer.toString((bytes[i] & MASK) + HASH, HEXCONVERTER).substring(1));
				}
				return sb.toString();
			} catch (NoSuchAlgorithmException | IOException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.OK, "Problem with checksum calculation", e));
			}
		} 
		return "";
	}



}
