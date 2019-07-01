/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.ui.impl;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.swt.graphics.Image;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatComposedContentProvider;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatComposedLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatProjectContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatWorkspaceContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatProjectLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatWorkspaceLabelProvider;
import de.dlr.sc.virsat.requirements.tracing.Activator;
import de.dlr.sc.virsat.requirements.tracing.ui.ISystemModelContentProvider;

/**
 * @author Tobias Franz tobias.franz@dlr.de
 *
 */
public class DVLMSystemModelContentProvider implements ISystemModelContentProvider {

	@Override
	public IContentProvider getContentProvider() {
		VirSatComposedContentProvider cp = new VirSatComposedContentProvider();
		cp.registerSubContentProvider(new VirSatWorkspaceContentProvider());
		cp.registerSubContentProvider(new VirSatProjectContentProvider());
		VirSatFilteredWrappedTreeContentProvider filteredCP = new VirSatFilteredWrappedTreeContentProvider(cp);
		filteredCP.setCheckContainedForFilter(true);
		filteredCP.addClassFilter(Resource.class);
		filteredCP.addClassFilter(StructuralElementInstance.class);
		filteredCP.addClassFilter(ICategoryAssignmentContainer.class);
		filteredCP.addClassFilter(CategoryAssignment.class);
		return filteredCP;
	}

	@Override
	public ILabelProvider getLabelProvider() {
		VirSatComposedLabelProvider lp = new VirSatComposedLabelProvider();
		lp.registerSubLabelProvider(new VirSatWorkspaceLabelProvider());
		lp.registerSubLabelProvider(new VirSatProjectLabelProvider());
		return lp;
	}

	@Override
	public Object getInput(IProject project) {
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
		VirSatResourceSet virSatResourceSet = ed.getResourceSet();
		Repository rep = virSatResourceSet.getRepository();
		return rep;
	}

	@Override
	public Object getTypeListInput(IProject project) {
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
		VirSatResourceSet virSatResourceSet = ed.getResourceSet();
		Repository repository = virSatResourceSet.getRepository();
		ArrayList<Object> list = new ArrayList<>();

		for (Concept concept : repository.getActiveConcepts()) {
			list.addAll(concept.getStructuralElements());
			list.addAll(concept.getCategories());
		}
		return list;
	}

	@Override
	public IContentProvider getTypeListContentProvider() {
		return new IStructuredContentProvider() {
			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof ArrayList<?>) {
					try {
						@SuppressWarnings("unchecked")
						ArrayList<StructuralElement> list = (ArrayList<StructuralElement>) inputElement;
						return list.toArray();
					} catch (Exception e1) {
						Activator.getDefault().getLog()
								.log(new Status(Status.ERROR, Activator.getPluginId(), "failed to do the job", e1));
						return null;
					}
				}
				return null;
			}
		};
	}

	@Override
	public ILabelProvider getTypeListLabelProvider() {
		return new ILabelProvider() {

			@Override
			public void removeListener(ILabelProviderListener listener) {
			}

			@Override
			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			@Override
			public void dispose() {
			}

			@Override
			public void addListener(ILabelProviderListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public String getText(Object element) {
				if (element instanceof StructuralElement) {
					return ((StructuralElement) element).getName();
				} else if (element instanceof Category) {
					return ((Category) element).getName();
				}
				return null;
			}

			@Override
			public Image getImage(Object element) {
				if (element instanceof StructuralElement) {
					StructuralElement structuralElement = (StructuralElement) element;
					Object identifier = DVLMEditPlugin.getImageRegistry().get(structuralElement.getFullQualifiedName());
					Image image = ExtendedImageRegistry.INSTANCE.getImage(identifier);
					return image;
				} else if (element instanceof Category) {
					Category category = (Category) element;
					Object identifier = DVLMEditPlugin.getImageRegistry().get(category.getFullQualifiedName());
					Image image = ExtendedImageRegistry.INSTANCE.getImage(identifier);
					return image;
				}
				return null;
			}
		};
	}


}
