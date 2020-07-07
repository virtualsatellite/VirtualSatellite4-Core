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
import de.dlr.sc.virsat.model.extension.requirements.csv.CsvFileReader;
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

	private static final String PAGE_TITEL = "Requirements CSV Import";
	
	private static final String[] FILE_EXTENSIONS = { "*.csv" };
	protected static final int COLUMNS = 2;
	protected static final int WITH_TEXT = 200;

	private static final String SPARATOR_INPUT_LABEL = "Column sperator (Single char):";

	private static final String HEADER_LINE_LABEL = "CSV header line number:";
	private static final String DATA_LINE_START_LABEL = "CSV start data line number:";
	private static final String DATA_LINE_END_LABEL = "CSV end data line number (Optional):";

	private Text seperatorField;
	private Text headerNumberField;
	private Text dataNumberStartField;
	private Text dataNumberEndField;
	
	private CsvTypeReviewPage typeReviewPage;

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
	protected CsvFileReqTypeSelectionPage(IContainer model, CsvTypeReviewPage typeReviewPage) {
		super(PAGE_TITEL);
		setTitle(PAGE_TITEL);
		setModel(model);
		this.typeReviewPage = typeReviewPage;
		setDescription("Please select a CSV file and a requirement type for the imported requirements. To create a new type, select a container configuration.");
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
		seperatorField.setText(CsvFileReader.CSV_DEFAULT_SPLIT_STRING + "");
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
		labelDataNumber.setText(DATA_LINE_START_LABEL);

		dataNumberStartField = new Text(propertiesComposite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.GRAB_HORIZONTAL);
		data.widthHint = WITH_TEXT;
		data.horizontalAlignment = SWT.END;
		dataNumberStartField.setLayoutData(data);
		dataNumberStartField.addModifyListener(this);
		dataNumberStartField.setText("2");
		
		Label labelDataEndNumber = new Label(propertiesComposite, SWT.NONE);
		labelDataEndNumber.setText(DATA_LINE_END_LABEL);

		dataNumberEndField = new Text(propertiesComposite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.GRAB_HORIZONTAL);
		data.widthHint = WITH_TEXT;
		data.horizontalAlignment = SWT.END;
		dataNumberEndField.setLayoutData(data);
		dataNumberEndField.addModifyListener(this);
	}

	/**
	 * Create a tree viewer with filters to show only relevant tree elements for
	 * CSV import /export
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
			String categoryFqn = ((CategoryAssignment) selection).getType().getFullQualifiedName();
			return categoryFqn.equals(RequirementsConfiguration.FULL_QUALIFIED_CATEGORY_NAME) 
					|| categoryFqn.equals(RequirementType.FULL_QUALIFIED_CATEGORY_NAME);
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
				&& !headerNumberField.getText().equals("")
				&& seperatorField.getText().length() == 1   //Only allow exactly one char
				&& !dataNumberStartField.getText().equals("")
				&& isSelectionValid()) {

			CategoryAssignment selection = (CategoryAssignment) getSelection();
			Repository repository = VirSatResourceSet.getVirSatResourceSet(selection).getRepository();
			ActiveConceptHelper activeConceptHelper = new ActiveConceptHelper(repository);
			Concept activeReqConcept = activeConceptHelper
					.getConcept(de.dlr.sc.virsat.model.extension.requirements.Activator.getPluginId());
			
			// Configure CSV reader
			final String destination = getDestination();
			CsvFileReader reader = getWizard().getReader();
			reader.setSeparator(getSeparator());
			reader.setHeaderLine(getHeaderLineNumber());
			
			//Do the parsing
			try {
				csvHeader = reader.readCsvHeadline(destination);
			} catch (IOException e) {
				Status status = new Status(Status.ERROR, Activator.getPluginId(),
						"CSVImportWizard: Failed to perform parsing of CSV file!");
				StatusManager.getManager().handle(status, StatusManager.LOG);
				return false;
			}
			
			// If existing requirement type is selected then use this one 
			if (selection.getType().getFullQualifiedName().equals(RequirementType.FULL_QUALIFIED_CATEGORY_NAME)) {
				reqType = new RequirementType(selection);
				typeReviewPage.setInput(csvHeader, reqType);
			}
			
			// If configuration container is selected then create a new type with an attribute per column
			if (selection.getType().getFullQualifiedName()
					.equals(RequirementsConfiguration.FULL_QUALIFIED_CATEGORY_NAME)) {
				reqType = getWizard().getImporter().prepareRequirementType(activeReqConcept, csvHeader);
				typeReviewPage.setInput(csvHeader, reqType);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Get the CSV separator
	 * @return the separator string
	 */
	public char getSeparator() {
		return seperatorField.getText().charAt(0);
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
		return Integer.parseInt(dataNumberStartField.getText()) - 1;
	}
	
	/**
	 * Get the line number of the first data
	 * @return the line number as integer
	 */
	public int getLastDataLineNumber() {
		String value = dataNumberEndField.getText();
		if (value.equals("")) {
			return -1;
		} else {
			return Integer.parseInt(value) - 1;
		}
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
	public void modifyText(ModifyEvent e) {
		getWizard().getReader().setDataStartLine(getFristDataLineNumber());
		getWizard().getReader().setDataEndLine(getLastDataLineNumber());
		getWizard().getReader().setHeaderLine(getHeaderLineNumber());
		getWizard().getReader().setSeparator(getSeparator());

		setPageComplete(isComplete());
	}
	
	@Override
	public CsvImportWizard getWizard() {
		return (CsvImportWizard) super.getWizard();
	}

}
