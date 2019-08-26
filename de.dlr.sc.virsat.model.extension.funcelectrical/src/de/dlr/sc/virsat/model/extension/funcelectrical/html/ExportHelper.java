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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;


/**
 * Class to provide useful methods for exporting html documents
 * @author bell_er
 *
 */
public class ExportHelper {

	public static final String TYPE = ".htm";
	public static final String INDEX = "index" + TYPE;
	public static final String TARGETFOLDER = "\\resources";
	public static final String RESOURCEFOLDER = "/resources";
	public static final String PRINTERLOGO = "PrinterLogo.png";
	public static final String PROJECTLOGO = "ProjectLogo.png";
	
	/**
	 * Method to export interface end and interface tables for all the selected structural element instances
	 * @author bell_er
	 * @param sc the root structural element
	 * @param f the location of the file which will contain all sub html pages 
	 * @param areas the coordinates of the links in block diagrams
	 */
	public void exportSubHtmlPages(StructuralElementInstance sc, File f, List<SEILink> areas) {
		
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		HTMLExporter htmlExporter = new HTMLExporter();
		

		File f1 = new File(f.getAbsolutePath() + "\\" + sc.getName() + TYPE);	
		List<Interface> seiInterfaces = bCaHelper.getAllBeanCategories(sc, Interface.class);	
		List<InterfaceEnd> seiInterfaceEnds = bCaHelper.getAllBeanCategories(sc, InterfaceEnd.class);
		List<InterfaceType> seiInterfaceTypes = bCaHelper.getAllBeanCategories(sc, InterfaceType.class);
		
		try {
			CharSequence subPages = htmlExporter.getSubPages(sc, seiInterfaceEnds, seiInterfaces, seiInterfaceTypes, areas);
			com.google.common.io.Files.write(subPages, f1, Charset.defaultCharset());
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.OK, "Problem with exporting subHTML pages", e));
		}
		for (StructuralElementInstance c : sc.getChildren()) {
			exportSubHtmlPages(c, f, areas);
		}
	
	}
	/**
	 * Method to create resource folder and put necessary logos etc. into that folder
	 * @author bell_er
	 * @param sc the root structural element
	 * @param path the location of the file which will contain all sub html pages 
	 */
	public void export(StructuralElementInstance sc, String path) {
		HTMLExporter htmlExporter = new HTMLExporter();
		try {
			// Create resources folder
			File resources = new File(path + TARGETFOLDER);		
			if (resources.exists()) {
				resources.delete();
			}
			resources.mkdir();	
			
			// Copy necessary files into the folder
			copyFile(path + TARGETFOLDER + "\\" + PROJECTLOGO, RESOURCEFOLDER + "/" + PROJECTLOGO);
			copyFile(path + TARGETFOLDER + "\\" + PRINTERLOGO, RESOURCEFOLDER + "/" + PRINTERLOGO);
			
			// Export Block Diagrams
			ImageProvider ip = new ImageProvider();
			ip.exportImage(path + TARGETFOLDER, sc);
			
			// Export html pages for leaf elements
			exportSubHtmlPages(sc, resources, ip.getSeiLinks());	
			
			// Export the index html page 	
			String indexPath = path + "/" + INDEX;
			File file = new File(indexPath);
			com.google.common.io.Files.write(htmlExporter.someHTML(sc), file, Charset.defaultCharset());
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.OK, "Problem with exporting HTML", e));
		}
	}
	/**
	 * Method to copy files from resources folder to the destination folder
	 * @author bell_er
	 * @param exportPath the export location of the file (generally user selects it)
	 * @param resourceLocation the location of the file in resources
	 * 
	 */
	private void copyFile(String exportPath, String resourceLocation) {	
		try {
			File file = new File(exportPath);	
			InputStream is = Activator.getResourceContentAsString(resourceLocation);
			Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Problem with copying resources from " +  resourceLocation +  " to " + exportPath, e));
		}
	}
	
}
