/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
 
/**
 * Provides behaviors for a tool .
 */
public class ToolBehaviorProvider extends DefaultToolBehaviorProvider {

	/**
	 * Default constructor.
	 * 
	 * @param dtp
	 *            the IDiagramTypeProvider
	 */
	public ToolBehaviorProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}
    
    @Override
    public boolean isDefaultBendPointRenderingActive() {
    	
    	// This deactivates the Bezier like smoothing of connection lines per default.
    	// Graphiti smoothing sometimes produces weird looking results and straight edges
    	// tend to look a lot better.
    	
    	// If desired otherwise, clients may override this function to explicitly turn it back on.
    	
    	return false;
    }
}