/**
 * This file is part of the VirSat project.
 *
 * Copyright (c) 2008-2015
 * German Aerospace Center (DLR), Simulation and Software Technology, Germany
 * All rights reserved
 *
 */
package de.dlr.sc.virsat.dot.exporter;


import java.io.File;
import java.net.URL;

/**
 * Class for common code to export smv
 */
public class DotExportHelper {
	
	protected static final int ADDZEROIFLESS = 10;
	protected File file;

	/**
	* Simple constructor
	*/
	public DotExportHelper() {
	}

	/**
	* returns the smv file
	* @return file returns the smv file
	*/
	public File getFile() {
		return file;
	}

	/**
	* sets the smv file
	* @param wb the smv file
	*/
	public void setFile(File file) {
		if (file != null) {
			this.file = new File(file.getAbsolutePath());
		}
	}

	/**
	* sets the file
	* @param iStream input stream for the template or default template
	*/
	public void setFile(URL url) {
		file = new File(url.getFile());
	}
}


