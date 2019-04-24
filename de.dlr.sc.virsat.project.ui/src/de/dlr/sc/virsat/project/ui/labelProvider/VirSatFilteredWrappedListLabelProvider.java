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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;


/**
 * A Label provider that is used for VuirSat Lists that should display some items such as in
 * the reference Selection Dialog. This provider wraps another provider allowing to reuse
 * the EMF generated providers.
 * @author fisc_ph
 *
 */
public class VirSatFilteredWrappedListLabelProvider implements ILabelProvider {

	private ILabelProvider wrappedLabelProvider;
	
	/**
	 * Constructor for the List Label provider. The Constructor needs to know the original
	 * provider which should be wrapped.
	 * @param wrappedLabelProvider The label provider to be wrapped usually a Transactional EMF Adapter Factory label provider
	 */
	public VirSatFilteredWrappedListLabelProvider(ILabelProvider wrappedLabelProvider) {
		super();
		this.wrappedLabelProvider = wrappedLabelProvider;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		wrappedLabelProvider.addListener(listener);
	}

	@Override
	public void dispose() {
		wrappedLabelProvider.dispose();
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return wrappedLabelProvider.isLabelProperty(element, property);
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		wrappedLabelProvider.removeListener(listener);
	}

	@Override
	public Image getImage(Object element) {
		return wrappedLabelProvider.getImage(element);
	}

	@Override
	public String getText(Object element) {
		String baseText = wrappedLabelProvider.getText(element);
		baseText += " - ";
		if (element instanceof IQualifiedName) {
			String fqn = ((IQualifiedName) element).getFullQualifiedName();
			baseText += fqn;
		} else if (element instanceof IInstance) {
			String fqn = ((IInstance) element).getFullQualifiedInstanceName();
			baseText += fqn;
		} else {
			baseText += NO_QUALIFIED_NAME;
		}
		
		return baseText;
	}
	
	public static final String NO_QUALIFIED_NAME = "...";
}
