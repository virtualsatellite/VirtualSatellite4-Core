/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons.ui.jface.viewer;

import org.eclipse.jface.viewers.ILabelProvider;

/**
 * LabelProvider for the PieChart Viewer
 * @author fisc_ph
 *
 */
public interface IKeyValueLabelProvider extends ILabelProvider {

	/**
	 * Implement this method to hand back the Name of a Key Value pair
	 * @param object the object from the content provider of which to derive the key
	 * @return the key usually as string
	 */
	String getKey(Object object);
		
	/**
	 * Implement this method to hand back the value of a key value pair
	 * @param object the object from the content provider of which to derive the value
	 * @return the value derived from the object
	 */
	double getValue(Object object);
}
