/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.dialog;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.CopyFilesAndFoldersOperation;

import de.dlr.sc.virsat.project.Activator;

/**
 * Use this dialog in case you want to automatically decide if you want to just
 * select the file or upload it from a foreign location into a given documents
 * folder. We use this dialog for the file property, so that the user can easily
 * attach files ot the study.
 * 
 * @author fisc_ph
 *
 */
public class SelectOrUploadFileDialog {

	public static final String RESULT_NULL = null;
	private static final String SELECT_FILE_FOR_UPLOAD_AND_REFERENCE = "Select file for upload and reference.";
	
	private FileDialog fileDialog;
	private IFolder documentFolder;
	private String filterPath;
	private Shell currentShell;
	
	/**
	 * Constructor for the dialog
	 * @param parent The shell on which to act
	 * @param style the style to be used with the dialog
	 * @param documentFolder the document folder in which files should be referenced or copied to
	 */
	public SelectOrUploadFileDialog(Shell parent, int style, IFolder documentFolder) {
		
		this.fileDialog = new FileDialog(parent, style);
		
		this.currentShell = parent;
		this.documentFolder = documentFolder;
		this.filterPath = documentFolder.getRawLocation().toOSString();
		
		fileDialog.setFilterExtensions(new String [] {"*.*"});
		fileDialog.setText(SELECT_FILE_FOR_UPLOAD_AND_REFERENCE);
		
		fileDialog.setFilterPath(filterPath);
	}

	/**
	 * Hands back the URI of the file that has been either selected or uploaded to
	 * the documents folder. The URI is handed back as a String and it is a Platform
	 * Resource URI without encoding. The URI is handed back as String for convenience
	 * interacting with our EMF based DVLM model.
	 * @return the URI as String of the uploaded file or null.
	 */
    public String open() {
		String superOpenResult = fileDialog.open();
		
		if (superOpenResult != null) {
			// In case the selected file is already within the documents folder of the
			// current resource
			// things are fine. in case it is outside it needs to be copied before. We try
			// to
			// use the eclipse core functionality for this task.
			Path selectedFilePath = Paths.get(superOpenResult);
			Path selectedFileNamePath = selectedFilePath.getFileName();
			if (selectedFileNamePath != null) {

				String selectedFileName = selectedFileNamePath.toString();
				boolean isSelectedFileInDocumentsPath = selectedFilePath.startsWith(filterPath);

				// Copy the files into the documents folder if needed
				if (!isSelectedFileInDocumentsPath) {
					CopyFilesAndFoldersOperation copyOperaton = new CopyFilesAndFoldersOperation(currentShell);
					copyOperaton.copyOrLinkFiles(new String[] { selectedFilePath.toString() }, documentFolder,
							DND.DROP_COPY);
				}

				try {
					documentFolder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(),
							"SelectOrUploadFileDialog: Failed to refresh documents folder"));
				}

				// Hand back the correct URI if present.
				IFile copiedFile = documentFolder.getFile(selectedFileName);
				if (copiedFile.exists()) {
					String copiedFilePath = copiedFile.getFullPath().toOSString();
					return URI.createPlatformResourceURI(copiedFilePath, false).toPlatformString(false);
				}
			}
		} 
		
		return null;
	}
}
