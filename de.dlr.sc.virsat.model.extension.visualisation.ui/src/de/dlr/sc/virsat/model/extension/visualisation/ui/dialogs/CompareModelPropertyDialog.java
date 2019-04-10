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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.dlr.sc.virsat.model.concept.provider.DVLMConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatTransactionalAdapterFactoryContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;

/**
 * Standard dialog to compare a model with another one and selecting a parameter
 * for comparison
 * 
 * @author fisc_ph
 *
 */
public class CompareModelPropertyDialog extends CompareModelDialog {

	/**
	 * Constructor for the simple comparison dialog
	 * @param parentShell the parent shell in which to create the dialog
	 * @param vsProject The virSatProject which is the baseline project for the comparison.
	 */
	public CompareModelPropertyDialog(Shell parentShell, VirSatProjectResource vsProject) {
		super(parentShell, vsProject);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);
	
		createPropertySelection(container);
		
		return area;
	}

	private TreeViewer viewerProperty;
	
	/**
	 * Creates the dialog are where to select the correct property
	 * @param container the container in which to create it
	 */
	private void createPropertySelection(Composite container) {
		Label labelProjectCompareTo = new Label(container, SWT.NONE);
		labelProjectCompareTo.setText("Comapre property:");

		viewerProperty = new TreeViewer(container);
		
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
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
		
		IProject project = baseProject.getWrappedProject();
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
			validateInput();
		});
		
		viewerProperty.expandToLevel(2);
	}
	
	/**
	 * This method validates the inputs
	 * @return returns true in case inputs are valid
	 */
	@Override
	protected boolean validateInput() {
		boolean inputsValid = super.validateInput();
		if (inputsValid && selectedProperty != null) {
			inputsValid = true;
		} else {
			setMessage("Select the Model to compare to and select the property to be used for the heat map!", IMessageProvider.ERROR);
		}

		getButton(IDialogConstants.OK_ID).setEnabled(inputsValid);
		return inputsValid;
	}
	
	private static final int VIEWER_HEIGHT = 300;
	
	@Override
	protected boolean isResizable() {
		return true;
	}
	
	private ComposedProperty selectedProperty;
	
	@Override
	protected void okPressed() {
		extractUserSelection();
		super.okPressed();
	}

	/**
	 * This method takes the suer input and stores it in class internal variables
	 */
	@Override
	protected void extractUserSelection() {
		super.extractUserSelection();
		
		// Store the property that was selected
		IStructuredSelection selection2 = (IStructuredSelection) viewerProperty.getSelection();
		if (!selection2.isEmpty()) {
			Object object = selection2.getFirstElement();
			if (object instanceof ComposedProperty) {
				selectedProperty = (ComposedProperty) selection2.getFirstElement();	
			} else {
				selectedProperty = null;
			}
		}
	}
	
	/**
	 * The property that was selected by the user
	 * @return null in case no property was selected
	 */
	public ComposedProperty getComparisonProjectProperty() {
		return selectedProperty;
	}
}
