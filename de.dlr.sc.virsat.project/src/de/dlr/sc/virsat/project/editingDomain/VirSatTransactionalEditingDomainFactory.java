/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IResourceUndoContextPolicy;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMPropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.provider.DVLMQudvItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

/**
 * Editing Domain Factory
 * @author fisc_ph
 *
 */
public class VirSatTransactionalEditingDomainFactory extends WorkspaceEditingDomainFactory {

	/**
	 * the method creates the AdapterFactory for our editing domain. It registers the individual adapter factories of our model packages 
	 * @return ComposedAdapterFactory the composed adapter factory.
	 */
	private synchronized ComposedAdapterFactory createAdapterFactory() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new DVLMResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMQudvItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMPropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		return adapterFactory;
	}
	
	@Override
	public synchronized TransactionalEditingDomain createEditingDomain() {
		IOperationHistory operationHistory = OperationHistoryFactory.getOperationHistory();
		VirSatWorkspaceCommandStack workspaceCommandStack = new VirSatWorkspaceCommandStack(operationHistory);
		workspaceCommandStack.setResourceUndoContextPolicy(IResourceUndoContextPolicy.DEFAULT);
		
		TransactionalEditingDomain result = new VirSatTransactionalEditingDomain(createAdapterFactory(), workspaceCommandStack);
		mapResourceSet(result);
		return result;
	}

	@Override
	public synchronized TransactionalEditingDomain createEditingDomain(ResourceSet rset) {
		IOperationHistory operationHistory = OperationHistoryFactory.getOperationHistory();
		VirSatWorkspaceCommandStack workspaceCommandStack = new VirSatWorkspaceCommandStack(operationHistory);
		workspaceCommandStack.setResourceUndoContextPolicy(IResourceUndoContextPolicy.DEFAULT);
		
		TransactionalEditingDomain result = new VirSatTransactionalEditingDomain(createAdapterFactory(), workspaceCommandStack, rset);
		mapResourceSet(result);
		return result;
	}
}
