/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.ui.diagram.feature;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddBendpointFeature;
import org.eclipse.graphiti.features.IMoveBendpointFeature;
import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.pattern.DefaultFeatureProviderWithPatterns;

import de.dlr.sc.virsat.graphiti.diagram.BeanIndependenceSolver;

public class VirsatDiagramFeatureProviderWithPatterns extends DefaultFeatureProviderWithPatterns {

	private BeanIndependenceSolver beanIndependenceSolver;

	public VirsatDiagramFeatureProviderWithPatterns(IDiagramTypeProvider dtp) {
		super(dtp);
		beanIndependenceSolver = new BeanIndependenceSolver(dtp);
		setIndependenceSolver(beanIndependenceSolver);
	}

	@Override
	public IAddBendpointFeature getAddBendpointFeature(IAddBendpointContext context) {
		return new VirSatAddBendpointFeature(this);
	}

	@Override
	public IMoveBendpointFeature getMoveBendpointFeature(IMoveBendpointContext context) {
		return new VirSatMoveBendpointFeature(this);
	}

	public BeanIndependenceSolver getBeanIndependenceSolver() {
		return beanIndependenceSolver;
	}

}
