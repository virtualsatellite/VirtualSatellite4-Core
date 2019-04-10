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

import java.io.IOException;
import java.util.List;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
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
import de.dlr.sc.virsat.model.extension.visualisation.ui.Activator;

/**
 * Standard dialog for color map and selecting a parameter
 *@author liu_yg
 */
public class ColorMapPropertyDialog extends TitleAreaDialog {	
	
	/**
	 * Constructor for the dialog
	 * @param parentShell the parent shell in which to create the dialog
	 * @param vsProject The virSatProject which is the baseline project for the comparison.
	 */
	public ColorMapPropertyDialog(Shell parentShell, VirSatProjectResource vsProject) {
		super(parentShell);
		this.baseProject = vsProject;
	}
	
	protected VirSatProjectResource baseProject;

	@Override
	public void create() {
		super.create();
		setTitle("Color Map Satellite Models Parameter");
		setMessage("Select the property to heat map!", IMessageProvider.INFORMATION);
		getButton(IDialogConstants.OK_ID).setEnabled(false);
		try {
			setTitleImage(new Image(null, Activator.getFileFromPlugin("/resources/icons/VirSat64.gif").openStream()));
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "ColorMapParameterdialog: Failed to load image", e));
		}	
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
		labelProjectCompareTo.setText("Choose parameter for Color Map:");

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
	protected boolean validateInput() {
		boolean inputsValid = false;
		if (selectedProperty != null) {
			inputsValid = true;
		} else {
			setMessage("Select the parameter to be used for the heat map!", IMessageProvider.ERROR);
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
	protected void extractUserSelection() {
		
		// Store the property that was selected
		IStructuredSelection selection = (IStructuredSelection) viewerProperty.getSelection();
		if (!selection.isEmpty()) {
			Object object = selection.getFirstElement();
			if (object instanceof ComposedProperty) {
				selectedProperty = (ComposedProperty) selection.getFirstElement();	
			} else {
				selectedProperty = null;
			}
		}
	}	
	
	/**
	 * The property that was selected by the user
	 * @return null in case no property was selected
	 */
	public ComposedProperty getProjectProperty() {
		return selectedProperty;
	}
}
