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
 * LabelProvider for the XYSpline Viewer
 * @author fisc_ph
 *
 */
public interface ISeriesXYValueLabelProvider extends ILabelProvider {

	/**
	 * get the Series names from the given object
	 * @param object the object which is managing the series of some data, like a SEI with several arrays
	 * @return the name of the series of the given object
	 */
	String getSeries(Object object);
	
	/**
	 * Get all the x Values for a given Object
	 * @param object the object of which to extract the X Values
	 * @return an array of Double X Values. Array should be as long as the one from the Y Values
	 */
	Double [] getValuesX(Object object);
	
	/**
	 * Get all the y Values for a given Object
	 * @param object the object of which to extract the y Values
	 * @return an array of Double y Values. Array should be as long as the one from the Y Values
	 */
	Double [] getValuesY(Object object);
}
