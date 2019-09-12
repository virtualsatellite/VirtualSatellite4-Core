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

import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.statushandlers.StatusManager;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.model.extension.requirements.ui.Activator;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;

/**
 * A page to select a CSV file to import and the target model element
 *
 */
public class CsvFileReqTypeSelectionPage extends AImportExportPage implements ModifyListener {

	private static final String[] FILE_EXTENSIONS = { "*.csv" };
	protected static final int COLUMNS = 2;
	protected static final int WITH_TEXT = 200;

	private static final String SPARATOR_INPUT_LABEL = "Column sperator:";
	private static final String DEFAULT_SEPARATOR = ";";

	private static final String HEADER_LINE_LABEL = "CSV header line number:";
	private static final String DATA_LINE_LABEL = "CSV data line number:";

	private Text seperatorField;
	private Text headerNumberField;
	private Text dataNumberField;
	
	private CsvTypeReviewPage typeReviewPage;
	private CsvImportWizard wizard;

	protected List<String> csvHeader;
	protected RequirementType reqType;

	/**
	 * Standard constructor
	 * 
	 * @param model
	 *            the root model
	 * @param containingWizard the containing wizard
	 * @param typeReviewPage
	 * 			  the page that recieves the requirement type
	 */
	protected CsvFileReqTypeSelectionPage(IContainer model, CsvImportWizard containingWizard, CsvTypeReviewPage typeReviewPage) {
		super("Requirements CSV Import");
		setTitle("Requirements CSV Import");
		setModel(model);
		this.typeReviewPage = typeReviewPage;
		this.wizard = containingWizard;
		setDescription(
				"Please select a CSV file and a requirement type for the imported requirements. To create a new type, select a container configuration.");
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);

		createFileDestinationUI();
		createCsvPropertiesUI(parent);

		createTreeViewer();

	}

	/**
	 * Create the UI for CSV properties
	 * 
	 * @param parent
	 *            the parent composite
	 */
	private void createCsvPropertiesUI(Composite parent) {
		Composite propertiesComposite = new Composite((Composite) getControl(), SWT.FILL);
		propertiesComposite.setLayout(new GridLayout(COLUMNS, false));
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		propertiesComposite.setLayoutData(data);

		Label label = new Label(propertiesComposite, SWT.NONE);
		label.setText(SPARATOR_INPUT_LABEL);

		seperatorField = new Text(propertiesComposite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.horizontalAlignment = SWT.END;
		data.widthHint = WITH_TEXT;
		seperatorField.setLayoutData(data);
		seperatorField.setText(DEFAULT_SEPARATOR);
		seperatorField.addModifyListener(this);

		Label labelHeaderNumber = new Label(propertiesComposite, SWT.NONE);
		labelHeaderNumber.setText(HEADER_LINE_LABEL);

		headerNumberField = new Text(propertiesComposite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.widthHint = WITH_TEXT;
		data.horizontalAlignment = SWT.END;
		headerNumberField.setLayoutData(data);
		headerNumberField.addModifyListener(this);
		headerNumberField.setText("1");

		Label labelDataNumber = new Label(propertiesComposite, SWT.NONE);
		labelDataNumber.setText(DATA_LINE_LABEL);

		dataNumberField = new Text(propertiesComposite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.GRAB_HORIZONTAL);
		data.widthHint = WITH_TEXT;
		data.horizontalAlignment = SWT.END;
		dataNumberField.setLayoutData(data);
		dataNumberField.addModifyListener(this);
		dataNumberField.setText("2");

	}

	/**
	 * Create a tree viewer with filters to show only relevant tree elements for
	 * CATIA import /export
	 */
	protected void createTreeViewer() {
		TreeViewer treeViewer = createTreeUI();
		VirSatFilteredWrappedTreeContentProvider filteredCp = (VirSatFilteredWrappedTreeContentProvider) treeViewer
				.getContentProvider();
		filteredCp.addClassFilter(CategoryAssignment.class);
		filteredCp.addClassFilter(ArrayInstance.class);
		filteredCp.addClassFilter(ComposedPropertyInstance.class);
		filteredCp.addStructuralElementIdFilter(
				RequirementsConfigurationCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);

	}

	@Override
	public boolean isSelectionValid() {
		Object selection = getSelection();
		if (selection instanceof CategoryAssignment) {
			return ((CategoryAssignment) selection).getType().getFullQualifiedName()
					.equals(RequirementsConfiguration.FULL_QUALIFIED_CATEGORY_NAME)
					|| ((CategoryAssignment) selection).getType().getFullQualifiedName()
							.equals(RequirementType.FULL_QUALIFIED_CATEGORY_NAME);
		}
		return false;
	}

	@Override
	protected String[] getSupportedFileEndings() {
		return FILE_EXTENSIONS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage#isComplete()
	 */
	@Override
	public boolean isComplete() {

		if (isDestinationSelected && isCurrentPage()) {
			if (!headerNumberField.getText().equals("") & !seperatorField.getText().equals("") 
				& !dataNumberField.getText().equals("")) {

				final String destination = getDestination();
	
				if (isSelectionValid()) {
					CategoryAssignment selection = (CategoryAssignment) getSelection();
					Repository repository = VirSatResourceSet.getVirSatResourceSet(selection).getRepository();
					ActiveConceptHelper activeConceptHelper = new ActiveConceptHelper(repository);
					Concept activeReqConcept = activeConceptHelper.getConcept(de.dlr.sc.virsat.model.extension.requirements.Activator.getPluginId());
			
					try {
						wizard.getReader().setSeparator(getSeparator());
						wizard.getReader().setHeaderLine(getHeaderLineNumber());
						csvHeader = wizard.getReader().readCsvHeadline(destination);
					} catch (IOException e) {
						Status status = new Status(Status.ERROR, Activator.getPluginId(),
								"CatiaImportWizard: Failed to perform import! Selected file not valid!", e);
						StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
					}
					
					if (selection.getType().getFullQualifiedName().equals(RequirementType.FULL_QUALIFIED_CATEGORY_NAME)) {
						typeReviewPage.setInput(csvHeader, new RequirementType(selection));
					}
					if (selection.getType().getFullQualifiedName().equals(RequirementsConfiguration.FULL_QUALIFIED_CATEGORY_NAME)) {
						reqType = wizard.getImporter().prepareRequirementType(activeReqConcept, csvHeader);
						typeReviewPage.setInput(csvHeader, reqType);
					}
					
					return true;
				}
				
			}
		}

		return false;
	}
	
	/**
	 * Get the CSV separator
	 * @return the separator string
	 */
	public String getSeparator() {
		return seperatorField.getText();
	}
	
	/**
	 * Get the line number of the header
	 * @return the integer header line number
	 */
	public int getHeaderLineNumber() {
		return Integer.parseInt(headerNumberField.getText()) - 1;
	}
	
	/**
	 * Get the line number of the first data
	 * @return the line number as integer
	 */
	public int getFristDataLineNumber() {
		return Integer.parseInt(dataNumberField.getText()) - 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.dlr.sc.virsat.uiengine.ui.wizard.ATreeViewerPage#getSelection()
	 */
	@Override
	public Object getSelection() {
		Object selected = super.getSelection();
		if (selected instanceof ComposedPropertyInstance) {
			selected = ((ComposedPropertyInstance) selected).getTypeInstance();
		}
		return selected;
	}

	/**
	 * Get the selected requirement type 
	 * @return the requirement type for import
	 */
	public RequirementType getRequirementsImportType() {
		Object selection = getSelection();
		if (selection instanceof CategoryAssignment) {
			if (((CategoryAssignment) selection).getType().getFullQualifiedName()
					.equals(RequirementsConfiguration.FULL_QUALIFIED_CATEGORY_NAME)) {
				
				return new RequirementType((CategoryAssignment) selection);

			} else if (((CategoryAssignment) selection).getType().getFullQualifiedName()
					.equals(RequirementType.FULL_QUALIFIED_CATEGORY_NAME)) {

				return reqType;
				
			}
		}
		return null;
	}


	@Override
	public void modifyText(ModifyEvent e) {
		wizard.getReader().setDataLine(getFristDataLineNumber());
		wizard.getReader().setHeaderLine(getHeaderLineNumber());
		wizard.getReader().setSeparator(getSeparator());
		setPageComplete(isComplete());
	}

}
