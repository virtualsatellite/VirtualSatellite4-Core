/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.labelProvider;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.ui.Activator;

/**
 * Label provider to show the names of the projects
 * 
 * @author fisc_ph
 *
 */
public class VirSatWorkspaceLabelProvider extends LabelProvider implements ILabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof VirSatProjectResource) {
			VirSatProjectResource virsatProjectResource = (VirSatProjectResource) element;
			return virsatProjectResource.getWrappedProject().getName();
		}
		return null;
	}

	private ResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources());

	@Override
	public Image getImage(Object element) {
		if (element instanceof VirSatProjectResource) {
			ImageDescriptor imageDescriptor = Activator.getImageDescriptor("resources/icons/VirSat_Project_DataBase.gif");
			return resourceManager.createImage(imageDescriptor);
		}

		return null;
	}

	@Override
	public void dispose() {
		super.dispose();

		// dispose the ResourceManager yourself
		resourceManager.dispose();
	}
}
