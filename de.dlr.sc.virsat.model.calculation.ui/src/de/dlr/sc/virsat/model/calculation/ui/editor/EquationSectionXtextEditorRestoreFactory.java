/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

/**
 * This class is responsible for handling the reload of the editors.
 * It will be asked by the Mementos to tell the corresponding object.
 * This factory is registered via the extension points 
 * @author fisc_ph
 *
 */
public class EquationSectionXtextEditorRestoreFactory implements IElementFactory {

	public static final String FACTORY_ID = "de.dlr.sc.virsat.model.calculation.ui.editor.EquationSectionXtextEditorRestoreFactory";

	@Override
	public IAdaptable createElement(IMemento memento) {
		String uriString = memento.getString(EquationSectionUriEditorInput.MEMENTO_ID_URI);
		
		URI platformUri = URI.createPlatformResourceURI(uriString, true);
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformUri.toPlatformString(true)));
		
		IEditorInput editorInput = new EquationSectionUriEditorInput(file, platformUri);
		
		return editorInput;
	}
}
