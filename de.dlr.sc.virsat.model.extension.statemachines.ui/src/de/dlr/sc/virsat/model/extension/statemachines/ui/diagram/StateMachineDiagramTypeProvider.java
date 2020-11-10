/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.StateMachineDiagramFeatureProvider;



/**
 * The StateMachine diagram type provider delivers all feature providers for StateMachine diagrams.
 * @author muel_s8
 *
 */
public class StateMachineDiagramTypeProvider extends AbstractDiagramTypeProvider  {

	/**
	 * Default Constructor
	 */
	public StateMachineDiagramTypeProvider() {
		super();
		setFeatureProvider(new StateMachineDiagramFeatureProvider(this));
	}
	
	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		return new IToolBehaviorProvider[] { new StateMachineDiagramToolBehaviorProvider(this)};
	}
	
	@Override
	public boolean isAutoUpdateAtStartup() {
		return true;
	}

	@Override
	public boolean isAutoUpdateAtRuntime() {
		return true;
	}

	@Override
	public boolean isAutoUpdateAtRuntimeWhenEditorIsSaved() {
		return true;
	}
}





