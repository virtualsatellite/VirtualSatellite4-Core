/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import de.dlr.sc.virsat.build.marker.util.VirSatValidationMarkerHelper;
import de.dlr.sc.virsat.model.calculation.marker.VirSatEquationMarkerHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.DVLMPropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMPropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.provider.DVLMQudvItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Most of the Snippets are realized in their own Section of the EditorForm
 * here the sections are collapsible hence saving and restoring of the state is provided 
 * @author lobe_el
 *
 */
public abstract class AUiSectionSnippet implements IUiSnippet {

	protected static final String UI_SECTION_SNIPPET_ID = "virsat.genericEditor.sectionSnippet";
	
	private Section section; 
	private Boolean restoredSectionExpansionState = true;
	
	protected EObject model;
	
	protected ComposedAdapterFactory adapterFactory;

	protected IEditorSite site;
	
	@Override
	public IUiSnippet setWorkbenchPartSite(IEditorSite site) {
		this.site = site;
		return this;
	}
	
	/**
	 * The List of controls which are editable when the user has write access 
	 * and which are disabled if he has not
	 */
	private Set<Control> enOrDisabledControlsAccordingToWriteAccess;
	
	/**
	 * Constructor initializing the standard ComposedAdapterFactory with the standard ItemProviderAdapterFactories;
	 */
	public AUiSectionSnippet() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMPropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMPropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMQudvItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		
		enOrDisabledControlsAccordingToWriteAccess = new HashSet<Control>();
	}
	
	@Override
	public void dispose() {
		
	}
	
	/**
	 * Method to receive the Uuid of the model object of this section as a String
	 * @return the Uuid of the modelObject
	 */
	public String getSectionModelUuidString() {
		if (model instanceof IUuid) {
			return ((IUuid) model).getUuid().toString();
		}
		Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "There is a model object without a Uuid"));
		return "virsat.model.object.without.uuid";
	}

	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		section = toolkit.createSection(composite, Section.TITLE_BAR | ExpandableComposite.CLIENT_INDENT | ExpandableComposite.TREE_NODE | ExpandableComposite.EXPANDED | SWT.FILL);
		model = initModel;
		
		IFile file = (IFile) VirSatProjectCommons.getWorkspaceResource(model);
		if (file != null) {
			restoreExpansionState(file);
			setExpanded(restoredSectionExpansionState);
		}
	}
	
	/**
	 * Method to create the body of the section called by the inheriting methods
	 * @param toolkit The Toolkit in which it is created
	 * @param sectionHeading The Heading of the section
	 * @param sectionDescription The Description of the section placed under the heading
	 * @param numberColumns The number of equally sized columns
	 * @return The composite which forms the body of the section
	 */
	public Composite createSectionBody(FormToolkit toolkit, String sectionHeading, String sectionDescription, int numberColumns) {
		section.setText(sectionHeading);
		if (sectionDescription != null && !sectionDescription.equals("")) {
			Label labelDescription = toolkit.createLabel(section, sectionDescription);
			section.setDescriptionControl(labelDescription);
		}
		section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Composite sectionBody = toolkit.createComposite(section);
		sectionBody.setLayout(new GridLayout(numberColumns, true));

		section.setClient(sectionBody);
		
		// This line is needed for linux gtk to properly draw the lines
		// of text fields and tables. This has been taken from the ManifestEditor->
		// DependenciesPage->RequiresSection. Even though calling this on a composite
		// should apply for all its contained widgets, it does not work as expected when
		// Allying it on a scrolled form or the collapsable form etc
		toolkit.paintBordersFor(sectionBody);
		
		return sectionBody;
	}
	
	/**
	 * this method produces a key for the storage of the expansion state 
	 * uses the modelString to identify the property correctly 
	 * @return the QualifiedName 
	 */
	protected abstract QualifiedName getSectionExpansionStateKey();
	
	@Override
	public void saveExpansionState(IFile stateStorage) {
		QualifiedName key = getSectionExpansionStateKey();
		try {
			if (key != null) {
				stateStorage.setPersistentProperty(key, Boolean.valueOf(isExpanded()).toString());
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Could not save the expansion state"));
		}
	}
	
	@Override
	public void restoreExpansionState(IFile stateStorage) {
		QualifiedName key = getSectionExpansionStateKey();
		try {
			if (key != null && stateStorage.getPersistentProperty(key) != null) {
				restoredSectionExpansionState = Boolean.parseBoolean(stateStorage.getPersistentProperty(key));
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Could not get the restored expansion state"));
		}
	}
	
	/**
	 * Method to get Expansion status
	 * @return boolean - whether the section of the snippet is expanded or not
	 */
	public boolean isExpanded() {
		return section.isExpanded();
	}
	
	/**
	 * Method to set Expansion status
	 * @param expand whether the section of the snippet should be expanded or not
	 */
	public void setExpanded(boolean expand) {
		section.setExpanded(expand);
	}
	
	/**
	 * Method to easily access the created Section
	 * @return the section in the expandable section
	 */
	public Section getSection() {
		return section;
	}
	
	@Override
	public void setDataBinding(DataBindingContext dbCtx, EditingDomain editingDomain, EObject model) {
		this.model = model;	
	}
	
	
	@Override
	public void updateState(boolean state) {
		boolean hasWriteAccess = RightsHelper.hasSystemUserWritePermission(model);
		for (Control control : enOrDisabledControlsAccordingToWriteAccess) {
			control.setEnabled(hasWriteAccess);
		}
	}
	
	/**
	 * Method to add a control to set of controls which should be en- or disabled according to write access
	 * @param controls Several Controls to be checked
	 */
	public void checkWriteAccess(Control... controls) {
		for (Control control : controls) {
			enOrDisabledControlsAccordingToWriteAccess.add(control);
		}
	}
	
	/**
	 * Method to give the Standard GridData
	 * @return The Standard GridData
	 */
	public GridData createDefaultGridData() {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		return gridData;
	}
	
	/**
	 * Method to return the marker helpers which are used in the specific snippet to show markers 
	 * @return the set of marker helpers 
	 */
	protected Set<IMarkerHelper> getMarkerHelpers() {
		Set<IMarkerHelper> mhs = new HashSet<>();
		mhs.add(new VirSatValidationMarkerHelper());
		mhs.add(new VirSatEquationMarkerHelper());
		return mhs;
	}
	
	/**
	 * This method returns all the objects which are shown in the snippet and could potentially have a marker shown 
	 * @return the set of possibly markerd objects 
	 */
	protected Collection<? extends EObject> getPossiblyMarkedObjects() {
		return Collections.emptySet();
	}
	
	@Override
	public boolean hasMarkers() {
		Set<IMarkerHelper> mhs = getMarkerHelpers();
		if (mhs.isEmpty()) {
			return false;
		}
		
		Collection<? extends EObject> objs = getPossiblyMarkedObjects();
		for (EObject obj : objs) {
			for (IMarkerHelper mh : mhs) {
				Set<IMarker> markers = mh.getAllMarkersForObjectAndContents(obj);
				if (!markers.isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}
}
