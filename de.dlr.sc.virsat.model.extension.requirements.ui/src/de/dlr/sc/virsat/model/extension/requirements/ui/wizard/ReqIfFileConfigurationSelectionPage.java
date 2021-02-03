/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.wizard;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.rmf.reqif10.impl.ReqIFImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;

/**
 * A page to select a CSV file to import and the target model element
 *
 */
public class ReqIfFileConfigurationSelectionPage extends AImportExportPage {

	private static final String PAGE_TITEL = "Requirements ReqIF Import";
	
	private static final String[] FILE_EXTENSIONS = { "*.reqif" };
	protected static final int COLUMNS = 2;
	protected static final int WITH_TEXT = 200;
	
	private ReqIfMappingPage mappingPage;

	protected List<String> csvHeader;
	protected RequirementType reqType = null;

	/**
	 * Standard constructor
	 * 
	 * @param model
	 *            the root model
	 * @param containingWizard the containing wizard
	 * @param typeReviewPage
	 * 			  the page that recieves the requirement type
	 */
	protected ReqIfFileConfigurationSelectionPage(IContainer model, ReqIfMappingPage typeReviewPage) {
		super(PAGE_TITEL);
		setTitle(PAGE_TITEL);
		setModel(model);
		this.mappingPage = typeReviewPage;
		setDescription("Please select a ReqIF file and a requirement configuration collection to store the configurations in.");
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);

		createFileDestinationUI();
		createReqIfPropertiesUI(parent);

		createTreeViewer();

	}

	/**
	 * Create the UI for ReqIF properties
	 * 
	 * @param parent
	 *            the parent composite
	 */
	private void createReqIfPropertiesUI(Composite parent) {
		Composite propertiesComposite = new Composite((Composite) getControl(), SWT.FILL);
		propertiesComposite.setLayout(new GridLayout(COLUMNS, false));
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		propertiesComposite.setLayoutData(data);

	}

	/**
	 * Create a tree viewer with filters to show only relevant tree elements for
	 * ReqIF import /export
	 */
	protected void createTreeViewer() {
		TreeViewer treeViewer = createTreeUI();
		VirSatFilteredWrappedTreeContentProvider filteredCp = (VirSatFilteredWrappedTreeContentProvider) treeViewer
				.getContentProvider();
		filteredCp.addClassFilter(ArrayInstance.class);
		filteredCp.addClassFilter(ComposedPropertyInstance.class);
		filteredCp.addStructuralElementIdFilter(
				RequirementsConfigurationCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);

	}

	@Override
	public boolean isSelectionValid() {
		Object selection = getSelection();
		if (selection instanceof StructuralElementInstance) {
			String seiFqn = ((StructuralElementInstance) selection).getType().getFullQualifiedName();
			return seiFqn.equals(RequirementsConfigurationCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		}
		return false;
	}

	@Override
	protected String[] getSupportedFileEndings() {
		return FILE_EXTENSIONS;
	}

	@Override
	public boolean isComplete() {

		if (isDestinationSelected 
				&& isCurrentPage() 
				&& isSelectionValid()) {

			StructuralElementInstance selection = (StructuralElementInstance) getSelection();
			Repository repository = VirSatResourceSet.getVirSatResourceSet(selection).getRepository();
			
			final String destination = getDestination();
			VirSatResourceSet rs = VirSatEditingDomainRegistry.INSTANCE.getEd(repository).getResourceSet();
			Resource resource = rs.getResource(URI.createFileURI(destination), true);
			EObject reqIFContent = resource.getContents().get(0);
			if (reqIFContent instanceof ReqIFImpl) {
				mappingPage.setInput((ReqIFImpl) reqIFContent, repository);
				return true;
			}
			
		}
		return false;
	}

	@Override
	public Object getSelection() {
		Object selected = super.getSelection();
		if (selected instanceof ComposedPropertyInstance) {
			selected = ((ComposedPropertyInstance) selected).getTypeInstance();
		}
		return selected;
	}

	@Override
	public ReqIfImportWizard getWizard() {
		return (ReqIfImportWizard) super.getWizard();
	}

}
