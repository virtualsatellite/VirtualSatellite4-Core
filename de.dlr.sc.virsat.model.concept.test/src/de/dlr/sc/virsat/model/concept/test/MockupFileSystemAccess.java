/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.test;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.generator.IFileSystemAccess;

/**
 * A simple mock-up class for a file system access that stores the content 
 * and filenames that should have been generated
 */
public class MockupFileSystemAccess implements IFileSystemAccess {

	private List<CharSequence> contents = new ArrayList<CharSequence>();
	private List<String> generatedFilenames = new ArrayList<String>();
	private List<String> deletedFilenames = new ArrayList<String>();
	
	@Override
	public void generateFile(String fileName, CharSequence contents) {
		this.contents.add(contents);
		this.generatedFilenames.add(fileName);
	}

	@Override
	public void generateFile(String fileName, String outputConfigurationName, CharSequence contents) {
		this.contents.add(contents);
		this.generatedFilenames.add(fileName);
	}

	@Override
	public void deleteFile(String fileName) {
		this.deletedFilenames.add(fileName);
	}

	/**
	 * @return the contents
	 */
	public List<CharSequence> getContents() {
		return contents;
	}

	/**
	 * @return the generatedFilenames
	 */
	public List<String> getGeneratedFilenames() {
		return generatedFilenames;
	}

	/**
	 * @return the deletedFilenames
	 */
	public List<String> getDeletedFilenames() {
		return deletedFilenames;
	}

}
