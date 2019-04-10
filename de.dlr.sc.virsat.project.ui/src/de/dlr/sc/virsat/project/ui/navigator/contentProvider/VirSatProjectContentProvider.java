/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.contentProvider;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.ITreeContentProvider;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.calculation.AExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.project.editingDomain.util.VirSatTransactionalEditingDomainHelper;
import de.dlr.sc.virsat.project.resources.IVirsatWrappedResource;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatTransactionalAdapterFactoryContentProvider;

/**
 * Content Provider for Virtual Satellite based on an EMF Adapter Factory enriched by
 * methods to make reading of the model transactional
 * @author fisc_ph
 *
 */
public class VirSatProjectContentProvider extends VirSatTransactionalAdapterFactoryContentProvider implements ITreeContentProvider {
	
	Map<EObject, VirSatProjectResourceItemProvider> repoToItemProvider = new HashMap<>();
	
	@Override
	public Object[] getChildren(Object object) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<Object[]>() {
			public void run() {
				Object[] superObjects = VirSatProjectContentProvider.super.getChildren(object);
				Set<Object> objects = new HashSet<>();
				
				if (object instanceof VirSatProjectResource) {
					
					VirSatProjectResource virSatProject = (VirSatProjectResource) object;
					IProject project = virSatProject.getWrappedProject();
					if (VirSatResourceSet.getResourceSet(project).isOpen()) {
						Repository repo = VirSatResourceSet.getResourceSet(project).getRepository(); 
						
						// Create an itemprovider that pretends that the VirSatProjectResource
						// is an EMF object and gets the Repo, Unit Management and Role Management
						// accordingly.
						VirSatProjectResourceItemProvider itemProvider = repoToItemProvider.get(repo);
						if (itemProvider == null) {
							itemProvider = new VirSatProjectResourceItemProvider(adapterFactory, repo);
							repoToItemProvider.put(repo, itemProvider);
						}
						
						Collection<?> children = itemProvider.getChildren(object);
						objects.addAll(children);
					}
				}

				objects.addAll(Arrays.asList(superObjects));
				Object[] result = objects.stream().filter(Objects::nonNull).filter(child -> 
						!(child instanceof EquationSection)
					&&	!(child instanceof Equation)
					&&  !(child instanceof AExpression)
					&&  !(child instanceof Concept)
				).toArray();
				setResult(result);
			}
		});
	}
	
	@Override
	public boolean hasChildren(Object object) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<Boolean>() {
			public void run() {
				boolean hasChildren = getChildren(object).length > 0;
				boolean isWrappedResource = object instanceof IVirsatWrappedResource;
				boolean realyHasChildren = hasChildren | isWrappedResource;
				setResult(realyHasChildren);
			}
		});
	}

	private static ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	
	/**
	 * Constructor which initializes the adapterFactory already
	 */
	public VirSatProjectContentProvider() {
		super(adapterFactory);
		adapterFactory.addAdapterFactory(new DVLMResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	}
}
