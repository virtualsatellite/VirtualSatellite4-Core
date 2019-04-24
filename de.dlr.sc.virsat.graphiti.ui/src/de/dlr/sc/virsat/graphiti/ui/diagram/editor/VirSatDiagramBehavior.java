/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.ui.diagram.editor;

import org.eclipse.graphiti.ui.editor.DefaultPersistencyBehavior;
import org.eclipse.graphiti.ui.editor.DefaultUpdateBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;

/**
 * Provides behaviors for a diagram editor.
 * @author muel_s8
 *
 */

public class VirSatDiagramBehavior extends DiagramBehavior {

	/**
	 * Default constructor.
	 * @param diagramContainer the diagramContainer
	 */
	
	public VirSatDiagramBehavior(IDiagramContainerUI diagramContainer) {
		super(diagramContainer);
	}
	
	@Override
	protected DefaultUpdateBehavior createUpdateBehavior() {
		
		// Create an update behavior that uses the VirSatTransactionalEditingDomain
		
		return new VirSatDiagramUpdateBehavior(this);
	}
	
	@Override
	protected DefaultPersistencyBehavior createPersistencyBehavior() {
		return new VirSatDiagramPersistencyBehavior(this);
	}
}
