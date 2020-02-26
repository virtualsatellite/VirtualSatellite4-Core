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
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IMoveBendpointFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

import de.dlr.sc.virsat.graphiti.diagram.VirSatIndependenceSolver;
import de.dlr.sc.virsat.model.concept.types.IBeanDelete;

/**
 * Default diagram provider for VirSat diagrams.
 * @author muel_s8
 *
 */
public class VirSatDiagramFeatureProvider extends DefaultFeatureProvider {

	private VirSatIndependenceSolver beanIndependenceSolver;
	
	/**
	 * Default constructor
	 * @param dtp the diagram type provider
	 */
	public VirSatDiagramFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
		beanIndependenceSolver = new VirSatIndependenceSolver(dtp);
		setIndependenceSolver(beanIndependenceSolver);
	}
	
	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		return new VirSatRemoveFeature(this);
	}
	
	@Override
	public IAddBendpointFeature getAddBendpointFeature(IAddBendpointContext context) {
		return new VirSatAddBendpointFeature(this);
	}
	
	@Override
	public IMoveBendpointFeature getMoveBendpointFeature(IMoveBendpointContext context) {
		return new VirSatMoveBendpointFeature(this);
	}
	
	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		return new VirSatMoveShapeFeature(this);
	}
	
	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getPictogramElement());
		
		if (object instanceof IBeanDelete) {
			return new BeanDeleteFeature(this);
		}
		
		return super.getDeleteFeature(context);
	}
	
	/**
	 * Get the bean independence solver this feature provider uses
	 * @return the bean independence solver used by this feature provider
	 */
	public VirSatIndependenceSolver getBeanIndependenceSolver() {
		return beanIndependenceSolver;
	}

}
