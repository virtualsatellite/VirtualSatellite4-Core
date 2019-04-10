/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

import de.dlr.sc.virsat.project.ui.Activator;

/**
 * Opening an Object in an Editor, this MatchingStrategy checks if already another Editor is opened 
 * to this Object, as there are different IEditorInputs, we have to compare them in the correct way 
 * @author lobe_el
 *
 */
public class GenericEditorMatchingStrategy implements IEditorMatchingStrategy {

	@Override
	public boolean matches(IEditorReference openEditorRef, IEditorInput toBeOpenedEditorInput) {
		try {
			IEditorInput openEditorInput = openEditorRef.getEditorInput();
		
			if (toBeOpenedEditorInput.equals(openEditorInput)) {
				return true;
			}
			
			//												Superclass() 		getName()								should open
			// Navigator: 	URIEditorInput 					-> Object			-> ElementConfiguration 				-> GenericEditor
			// ProblemView:	FileEditorInput 				-> PlatformObject	-> StructuralElement.dvlm				-> GenericEditor
			// Equations:	EquationSectionURIEditorInput	-> FileEditorInput	-> Equations of ElementConfiguration 	-> EquationEditor
			// 
			// => if the names are close to each other but the inputs are not equal, than the editors don't match
			// => if the inputs are of the same instances but are not equal, than the editors don't match
			// => only need to check when there are inputs of different instances
			
			boolean toBeOpenedEIextendsFEI = toBeOpenedEditorInput instanceof IFileEditorInput && !toBeOpenedEditorInput.getClass().equals(FileEditorInput.class);
			boolean openEIextendsFEI = openEditorInput instanceof IFileEditorInput && !openEditorInput.getClass().equals(FileEditorInput.class);
			
			if (toBeOpenedEIextendsFEI || openEIextendsFEI) {
				// if one EditorInput extends FileEditorInput and the inputs are different, we can say they don't match 
				return false;
			}
			
			// This code covers the case that an editor is open from the Navigator
			// And we try to open it again from the Problem View. The Problem View will open the editor using
			// A FileEditorInput referencing the resource in the Eclipse Workspace while the Navigator
			// used a UriEditorInput
			if ((toBeOpenedEditorInput instanceof IFileEditorInput) && (openEditorInput instanceof URIEditorInput)) {
				IFileEditorInput toBeOpenedFileEditorInput = (IFileEditorInput) toBeOpenedEditorInput;
				IFile toBeOpenedFile = toBeOpenedFileEditorInput.getFile();
				URI toBeOpenedUri = URI.createPlatformResourceURI(toBeOpenedFile.getFullPath().toString(), true);

				URIEditorInput openUriEditorInput = (URIEditorInput) openEditorInput;
				URI openUri = openUriEditorInput.getURI();
				
				return (toBeOpenedUri.equals(openUri));
			}
			
			// This code covers the case that an editor is open from the Problem View
			// And we try to open it again from the Navigator View
			if ((toBeOpenedEditorInput instanceof URIEditorInput) && (openEditorInput instanceof IFileEditorInput)) {
				IFileEditorInput openFileEditorInput = (IFileEditorInput) openEditorInput;
				IFile openFile = openFileEditorInput.getFile();
				URI openUri = URI.createPlatformResourceURI(openFile.getFullPath().toString(), true);

				URIEditorInput toBeOpenedUriEditorInput = (URIEditorInput) toBeOpenedEditorInput;
				URI toBeOpenedUri = toBeOpenedUriEditorInput.getURI();

				return (toBeOpenedUri.equals(openUri));
			}
		} catch (PartInitException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), Status.WARNING, "Failed to match Open Editors", e));
		}
		return false;
	}
}
