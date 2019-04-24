/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.comparison;

import org.eclipse.core.runtime.SubMonitor;

/**
 * Abstract class on which other implementations should be build on
 * @author fisc_ph
 *
 */
public abstract class ACompareModelAlgorithm {

	protected static final int COLOR_RED = 0xFF0000;
	protected static final int COLOR_GREY = 0xDDDDDD;
	protected static final int COLOR_GREEN = 0x00FF00;
	protected static final int COLOR_BLUE = 0x0000FF;
	protected static final int COLOR_CYAN = 0x00FFFF;
	
	
	
	protected static final int GEO_ADDED_COLOR = COLOR_RED;     
	protected static final int GEO_REMOVED_COLOR = COLOR_GREEN;   
	protected static final int GEO_UNCHANGED_COLOR = COLOR_GREY; 
	protected static final int GEO_EDITED_COLOR = COLOR_BLUE;    
	
	protected static final int PARAMETER_ADDED_COLOR = COLOR_RED;         
	protected static final int PARAMETER_REMOVED_COLOR = COLOR_GREEN;       
	protected static final int PARAMETER_UNCHANGED_COLOR = COLOR_GREY;     
	protected static final int PARAMETER_EDITED_HIGHER_COLOR = COLOR_BLUE; 
	protected static final int PARAMETER_EDITED_LOWER_COLOR = COLOR_CYAN;  

	protected SubMonitor subMonitor;
	
	/**
	 * Constructor of abstract algorithm that prepares the usage of a progress monitor
	 * @param subMonitor the subMonitor of a progressmonitor to be used with the algorithm
	 */
	public ACompareModelAlgorithm(SubMonitor subMonitor) {
		this.subMonitor = subMonitor;
	}
}