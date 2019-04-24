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

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * This class sets up generic capabilities for creating virsat objects in graphiti
 * diagrams.
 * @author muel_s8
 *
 */

public abstract class VirSatCreateFeature extends AbstractCreateFeature {

	/**
	 * Default construtor
	 * @param fp the feature provider
	 * @param name the feature name
	 * @param description the feature description
	 */
	
	public VirSatCreateFeature(IFeatureProvider fp, String name, String description) {
		super(fp, name, description);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		VirSatTransactionalEditingDomain ed = (VirSatTransactionalEditingDomain) getDiagramBehavior().getEditingDomain();
		return DiagramHelper.hasDiagramWritePermission(context.getTargetContainer()) && createCreateCommand(ed, context).canExecute();
	}

	@Override
	public Object[] create(ICreateContext context) {
		VirSatTransactionalEditingDomain ed = (VirSatTransactionalEditingDomain) getDiagramBehavior().getEditingDomain();
		Command createCommand = createCreateCommand(ed, context);
		createCommand.execute();
		
		Collection<?> results = createCommand.getResult();
		
		for (Object object : createCommand.getResult()) {
			if (object instanceof ComposedPropertyInstance) {
				ComposedPropertyInstance cpi = (ComposedPropertyInstance) object;
				addGraphicalRepresentation(context, cpi.getTypeInstance());
			} else {
				addGraphicalRepresentation(context, object);
			}
		}
		
		return results.toArray();
	}
	
	/**
	 * To be implemented by clients. Creates the actual creation command.
	 * @param ed the editing domain
	 * @param context the creation context
	 * @return a creation command
	 */
	protected abstract Command createCreateCommand(VirSatTransactionalEditingDomain ed, ICreateContext context);
}
