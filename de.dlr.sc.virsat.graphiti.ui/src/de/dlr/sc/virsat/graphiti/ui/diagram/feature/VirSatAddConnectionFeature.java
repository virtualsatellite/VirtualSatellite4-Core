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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;

/**
 * Generic add feature. Extend this class if you want to add a connection type
 * object to a graphiti diagram.
 * @author muel_s8
 *
 */

public abstract class VirSatAddConnectionFeature extends AbstractAddFeature {
	
	/**
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	
	public VirSatAddConnectionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		if (context instanceof IAddConnectionContext) {
			IAddConnectionContext connectionContext = (IAddConnectionContext) context;
			return DiagramHelper.hasDiagramWritePermission(connectionContext.getSourceAnchor());
		} else {
			return DiagramHelper.hasDiagramWritePermission(context.getTargetContainer());
		}
	}
}
