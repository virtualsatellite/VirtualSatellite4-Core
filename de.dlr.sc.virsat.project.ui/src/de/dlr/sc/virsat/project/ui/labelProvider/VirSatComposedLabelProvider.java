/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.labelProvider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * Class provides functionality to compose several other label providers into one common label provider
 * 
 * @author muel_s8
 *
 */
public class VirSatComposedLabelProvider implements ILabelProvider {

	private List<ILabelProvider> subLabelProvider = new ArrayList<>();
	
	/**
	 * Use this method to attach a new SubLabelProvider
	 * @param lp the label provider to be added to this composed label provider
	 */
	public void registerSubLabelProvider(ILabelProvider lp) {
		subLabelProvider.add(lp);
	}
	
	@Override
	public void dispose() {
		subLabelProvider.forEach((lp) -> lp.dispose());
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		subLabelProvider.forEach((lp) -> lp.addListener(listener));
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		for (ILabelProvider lp : subLabelProvider) {
			boolean isLabelProperty = lp.isLabelProperty(element, property);
			if (isLabelProperty) {
				return true;
			}
		}

		return false;
	}
	
	@Override
	public void removeListener(ILabelProviderListener listener) {
		subLabelProvider.forEach((lp) -> lp.removeListener(listener));
	}

	@Override
	public Image getImage(Object element) {
		for (ILabelProvider lp : subLabelProvider) {
			Image image = lp.getImage(element);
			if (image != null) {
				return image;
			}
		}

		return null;
	}

	@Override
	public String getText(Object element) {
		for (ILabelProvider lp : subLabelProvider) {
			String text = lp.getText(element);
			if (text != null) {
				return text;
			}
		}

		return null;
	}
}
