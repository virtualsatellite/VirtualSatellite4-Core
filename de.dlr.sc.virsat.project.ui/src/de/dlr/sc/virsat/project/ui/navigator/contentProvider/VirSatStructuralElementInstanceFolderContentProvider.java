/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.contentProvider;

import java.util.Collections;

import org.eclipse.core.resources.IFolder;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;


/**
 * This class provides the document folder to the VirSat Navigator.
 * Each StructuralElementInstance Carries one Folder that the user can use
 * for showing specifications etc.
 * @author fisc_ph
 *
 */
public class VirSatStructuralElementInstanceFolderContentProvider extends VirSatObjectFolderContentProvider {
	
	/**
	 * Constructor that already attaches a listener to the VirSatTransactioanlEditingDomain
	 */
	public VirSatStructuralElementInstanceFolderContentProvider() {
		super();
	}
	
	@Override
	public Object[] getChildren(Object element) {
		
		if (element instanceof StructuralElementInstance) {
			if (((StructuralElementInstance) element).eResource() == null) {
				return Collections.EMPTY_LIST.toArray();
			} else {
				IFolder [] documentFolders = new IFolder[1];
				documentFolders[0] = VirSatProjectCommons.getDocumentFolder((StructuralElementInstance) element);
				return documentFolders;
			}

		}

		Object[] children = super.getChildren(element);
		
		return children;
	}
}
