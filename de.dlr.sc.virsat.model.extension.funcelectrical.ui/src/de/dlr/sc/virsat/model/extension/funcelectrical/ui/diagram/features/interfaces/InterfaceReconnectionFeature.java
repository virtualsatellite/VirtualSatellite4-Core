/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaces;

import org.eclipse.emf.common.command.Command;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.impl.DefaultReconnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Feature for interface reconnection in an interface diagram.
 * @author muel_s8
 *
 */

public class InterfaceReconnectionFeature extends DefaultReconnectionFeature {

	/**
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	
	public InterfaceReconnectionFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public boolean canReconnect(IReconnectionContext context) {
		Connection connection = context.getConnection();
		Interface i = (Interface) getBusinessObjectForPictogramElement(connection);
		
		if (!DiagramHelper.hasBothWritePermission(i, connection)) {
			return false;
		}
		
		return super.canReconnect(context);
	}
	
	@Override
	public void preReconnect(IReconnectionContext context) {
		Connection connection = context.getConnection();
		Interface i = (Interface) getBusinessObjectForPictogramElement(connection);
		
		Anchor oldAnchor = context.getOldAnchor();
		Anchor newAnchor = context.getNewAnchor();
		
		if (newAnchor == null) {
			ContainerShape cs = (ContainerShape) context.getTargetPictogramElement();
			cs = (ContainerShape) cs.getChildren().get(0);
			newAnchor = cs.getAnchors().get(0);
		}
		
		InterfaceEnd newInterfaceEnd = (InterfaceEnd) getBusinessObjectForPictogramElement(newAnchor);
		
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(i.getTypeInstance());
		Command command = null;
		if (oldAnchor == connection.getStart()) {
			command = i.setInterfaceEndFrom(ed, newInterfaceEnd);	
		} else if (oldAnchor == connection.getEnd()) {
			command = i.setInterfaceEndTo(ed, newInterfaceEnd);
		}
		
		ed.getCommandStack().execute(command);
	}

}
