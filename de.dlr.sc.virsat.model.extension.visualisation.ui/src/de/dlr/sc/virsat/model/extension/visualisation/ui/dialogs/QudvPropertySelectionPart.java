/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.dialogs;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.dlr.sc.virsat.model.concept.provider.DVLMConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatTransactionalAdapterFactoryContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;

/**
 * This class creates a selection part for picking Qudv properties
 * 
 */
public abstract class QudvPropertySelectionPart {
	
	private static final int VIEWER_HEIGHT = 300;
	
	private TreeViewer viewerProperty;
	private String selectedPropertyFQN;
	
	/**
	 * Default constructor
	 * @param parent the parent
	 * @param vsProject the virsat project
	 */
	public QudvPropertySelectionPart(Composite parent, VirSatProjectResource vsProject) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);
		
		Label labelProjectCompareTo = new Label(container, SWT.NONE);
		labelProjectCompareTo.setText("Compare property:");

		viewerProperty = new TreeViewer(container);

		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new DVLMConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		viewerProperty.setContentProvider(new VirSatTransactionalAdapterFactoryContentProvider(adapterFactory) {
			@Override
			public Object[] getElements(Object rootObject) {
				if (rootObject instanceof List) {
					@SuppressWarnings("rawtypes")
					List list = (List) rootObject;
					return list.toArray();
				}
				return super.getElements(rootObject);
			}
		});
		viewerProperty.setLabelProvider(new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory));

		IProject project = vsProject.getWrappedProject();
		Repository repo = VirSatResourceSet.getResourceSet(project).getRepository();

		viewerProperty.setInput(repo.getActiveConcepts());

		GridData gridDataViewer = new GridData();
		gridDataViewer.grabExcessHorizontalSpace = true;
		gridDataViewer.grabExcessVerticalSpace = true;
		gridDataViewer.horizontalAlignment = GridData.FILL;
		gridDataViewer.verticalAlignment = GridData.FILL;
		gridDataViewer.heightHint = VIEWER_HEIGHT;
		
		viewerProperty.getControl().setLayoutData(gridDataViewer);
		viewerProperty.addSelectionChangedListener((obj) -> {
			extractUserSelection();
			validateProperty();
		});

		viewerProperty.expandToLevel(2);
	}
	
	/**
	 * This method takes the suer input and stores it in class internal variables	 
	*/
	public void extractUserSelection() {
		IStructuredSelection selection = (IStructuredSelection) viewerProperty.getSelection();
		if (!selection.isEmpty()) {
			Object object = selection.getFirstElement();
			if (object instanceof ComposedProperty) {
				ComposedProperty cp = (ComposedProperty) object;
				
				Category category = cp.getType();
				boolean hasQudvTypeProperty = category.getAllProperties().stream()
						.filter(p -> p instanceof AQudvTypeProperty).map(p -> (AQudvTypeProperty) p).findFirst()
						.isPresent();
				if (hasQudvTypeProperty) {
					selectedPropertyFQN = cp.getFullQualifiedName();
				} else {
					selectedPropertyFQN = null;
				}
			} else if (object instanceof AQudvTypeProperty) {
				selectedPropertyFQN = ((AQudvTypeProperty) object).getFullQualifiedName();
			} else {
				selectedPropertyFQN = null;
			}
		}
	}
	
	/**
	 * The FQN of the property that was selected by the user
	 * 
	 * @return null in case no property was selected
	 */
	public String getComparisonProjectPropertyFQN() {
		return selectedPropertyFQN;
	}
	
	/**
	 * Validate the property selection
	 */
	public abstract void validateProperty();
}
