/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfacetypes;

import org.eclipse.emf.common.command.Command;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * This feature allows setting an interface type of an interface end
 * via drag & drop
 * @author muel_s8
 *
 */

public class InterfaceTypeAddFeature extends AbstractAddFeature {

	/**
	 * The standard constructor
	 * @param fp the feature provider
	 */
	public InterfaceTypeAddFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public boolean canAdd(IAddContext context) {
		Object bo = getBusinessObjectForPictogramElement(context.getTargetContainer());
		
		if (bo instanceof InterfaceEnd) {
			InterfaceEnd ie = (InterfaceEnd) bo;
			return RightsHelper.hasSystemUserWritePermission(ie.getTypeInstance()) && DiagramHelper.hasDiagramWritePermission(context.getTargetContainer());
		}
		
		return false;
	}
	
	@Override
	public PictogramElement add(IAddContext context) {
		Object bo = getBusinessObjectForPictogramElement(context.getTargetContainer());
		InterfaceEnd ie = (InterfaceEnd) bo;
		InterfaceType it = new InterfaceType((CategoryAssignment) context.getNewObject());
		
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(ie.getTypeInstance());
		Command cmd = ie.setType(ed, it);
		ed.getCommandStack().execute(cmd);
		
		updatePictogramElement(context.getTargetContainer().getContainer());
		
		return null;
	}

}
