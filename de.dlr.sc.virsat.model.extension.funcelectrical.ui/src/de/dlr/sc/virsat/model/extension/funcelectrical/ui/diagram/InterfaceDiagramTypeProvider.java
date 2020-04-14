/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.InterfaceDiagramFeatureProvider;

/**
 * The interface diagram type provider devlives all feature providers for interface diagrams.
 * @author muel_s8
 *
 */

public class InterfaceDiagramTypeProvider extends AbstractDiagramTypeProvider {

	public static final String CONCEPT_ID = Activator.getPluginId();
	
	private IToolBehaviorProvider[] toolBehaviorProviders;
	
	/**
	 * Default Constructor
	 */
	public InterfaceDiagramTypeProvider() {
		super();
		setFeatureProvider(new InterfaceDiagramFeatureProvider(this));
	}
	
	@Override
    public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
        if (toolBehaviorProviders == null) {
            toolBehaviorProviders =
                new IToolBehaviorProvider[] { new InterfaceDiagramToolBehaviorProvider(this) };
        }
        return toolBehaviorProviders.clone();
    }
}
