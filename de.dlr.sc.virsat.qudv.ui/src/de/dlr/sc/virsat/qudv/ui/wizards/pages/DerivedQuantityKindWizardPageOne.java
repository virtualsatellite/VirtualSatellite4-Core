/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.qudv.ui.wizards.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.qudv.ui.Activator;

/**
 * class to provide the first page of the derived quantity kind 
 * @author scha_vo
 *
 */
public class DerivedQuantityKindWizardPageOne extends AQuantityKindWizardPage {
	
	private List<Combo> refQKComboList = new ArrayList<Combo>();
	private List<ComboViewer> refQKComboViewerList = new ArrayList<ComboViewer>();
	
	private List<Text> exponentList = new ArrayList<Text>();
	
	private Button buttonAdd;
	private Label placeholder1;
	private Label placeholder2;
	private Label conversionLabel;
	private Text conversionPreview;
	
	private HashMap<AQuantityKind, Double> quantityKindFactors = new HashMap<AQuantityKind, Double>();
	
	private int count;
	
	/**
	 * public constructor
	 * @param quantityKind the qunatity kind
	 * @param um the unit management
	 * @param pageName the name of the page
	 */
	public DerivedQuantityKindWizardPageOne(AQuantityKind quantityKind, UnitManagement um, String pageName) {
		super(quantityKind, um, pageName);
		setTitle("Derived Quantity Kind Wizard First Page");
		setDescription("This wizard help to create and edit derived quantity kinds of the QUDV Model.");
	}

	@Override
	public void createControl(Composite parent) {
		//somehow I need to reset the count, because the garbage collection messes around with the count variable
		count = 0;

		super.createControl(parent);
		
		symbol.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				updateWidgets();
			}

		});
		
		
		//we have to change the layout of this wizard!
		GridLayout layout = new GridLayout();
		final int NUMBER_OF_COLUMNS = 3;
		layout.numColumns = NUMBER_OF_COLUMNS;
		container.setLayout(layout);
		GridData gd = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gd.horizontalSpan = 2;

		name.setLayoutData(gd);
		symbol.setLayoutData(gd);
		description.setLayoutData(gd);
		definitionURI.setLayoutData(gd);
		//Separation Line
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData.horizontalSpan = NUMBER_OF_COLUMNS;
		separator.setLayoutData(gridData);

		createAddButtonAndPreviewControls();

		//per default set 3 lines of controls;  
		addSetOfQuantityKindFactorControls();
		addSetOfQuantityKindFactorControls();
		addSetOfQuantityKindFactorControls();

		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);
		initializeValues();
		container.layout(true);
		container.pack();
		//container.redraw();
	}
	
	/**
	 * method to initialize the values in the wizard page. It reads from the object and fills out the widgets in the wizard
	 */
	protected void initializeValues() {
		if (quantityKind != null) {
			name.setText(quantityKind.getName());
			symbol.setText(quantityKind.getSymbol());
			description.setText(quantityKind.getDescription());
			definitionURI.setText(quantityKind.getDefinitionURI());
			if (quantityKind instanceof DerivedQuantityKind) {
				DerivedQuantityKind derivedQK = (DerivedQuantityKind) quantityKind; 
				List<QuantityKindFactor> listQKFactors = derivedQK.getFactor();
				
				int numberOfUnitFactors = listQKFactors.size();
				
				//add the right amount of controls
				int toAdd = numberOfUnitFactors - count;
				if (toAdd > 0) {
					for (int i = 0; i < toAdd; i = i + 1) {
						addSetOfQuantityKindFactorControls();
					}
				}
				
				//fill the comboBoxes and Textfields with proper values
				for (int i = 0; i < numberOfUnitFactors; i = i + 1) {
					AQuantityKind refQuantityKind = listQKFactors.get(i).getQuantityKind();
					refQKComboViewerList.get(i).setSelection(new StructuredSelection(refQuantityKind));
					Double d = listQKFactors.get(i).getExponent();
					exponentList.get(i).setText("" + d);
				}
			}
			setPageComplete(true);
			updateWidgets();
		}
		
	}	
	
	/**
	 * adds controls for add and preview
	 */
	private void createAddButtonAndPreviewControls() {
		
		buttonAdd = new Button(container, SWT.PUSH);
        buttonAdd.setText("Add new quantity kind factor");
        buttonAdd.addSelectionListener(new SelectionAdapter() {
        	public void widgetSelected(SelectionEvent e) {
        		addSetOfQuantityKindFactorControls();
        	}
        });
        placeholder1 = new Label(container, SWT.NULL);
        placeholder2 = new Label(container, SWT.NULL);
        
        GridData gd = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gd.horizontalSpan = 2;
        
    	//Conversion preview widgets
    	conversionLabel = new Label(container, SWT.NULL);
    	conversionLabel.setText("Conversion preview:");
		conversionPreview = new Text(container, SWT.NULL);
		conversionPreview.setText("preview cannot be created");
		conversionPreview.setLayoutData(gd);
    	conversionPreview.setEnabled(false);
	}
	
	/**
	 * dispose method which removes several controls
	 */
	private void removeButtonAndPreviewControls() {
		buttonAdd.dispose();
		placeholder1.dispose();
		placeholder2.dispose();
		conversionLabel.dispose();
		conversionPreview.dispose();
	}
	
	/**
	 * adds the controls for adding a quantity kind
	 */
	private void addSetOfQuantityKindFactorControls() {

		removeButtonAndPreviewControls();
		
		count = count + 1;
		
		//add a Label:
		Label myLabel = new Label(container, SWT.NULL);
    	myLabel.setText("Quantity Kind factor #" + count);
		
    	//add comboBox and comboViewer
    	Combo combo = new Combo(container, SWT.NULL);
    	ComboViewer comboViewer = new ComboViewer(combo);
    	comboViewer.setLabelProvider(new LabelProvider() {
    		@Override
    		public String getText(Object element) {
    			if (element instanceof AQuantityKind) {
    				AQuantityKind qk = (AQuantityKind) element;
    				String result = qk.getName() + " - " + qk.getSymbol();
    				return result;
    			}
    			return super.getText(element);
    		}
    	});
    	comboViewer.setContentProvider(new ArrayContentProvider());
    	List<AQuantityKind> listOfUnits = systemOfUnits.getSystemOfQuantities().get(0).getQuantityKind();
    	comboViewer.setInput(listOfUnits);
    	comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = event.getSelection();
                if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
                    Object o = ((IStructuredSelection) selection).getFirstElement();
                    if (o instanceof AQuantityKind) {
                    	updateWidgets();
                    }
                }
            }
        });
    	
    	//create Text field
    	final Text expText = new Text(container, SWT.NULL);
		expText.setText("");
		expText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setErrorMessage(null);
				if (!expText.getText().isEmpty()) {
					try {
						Double.valueOf(expText.getText()).floatValue();
					} catch (NumberFormatException exception) {
						setErrorMessage("Factor value connot be converted to a float. Enter a correct number.");
					}					
				}
				updateWidgets();
			}
    	});
	
		//addControls to their lists
		refQKComboList.add(combo);
		refQKComboViewerList.add(comboViewer);
		exponentList.add(expText);
		
		createAddButtonAndPreviewControls();
		
		container.layout(true);
		container.pack();
		container.redraw();
		
	};

	/**
	 * a method to update the widgets
	 */
	protected void updateWidgets() {
		
		HashMap<AQuantityKind, Double> hm = getQuantityKindFactors();
		
		boolean symbolNotEmpty = !symbol.getText().isEmpty();
		boolean nameNotEmpty = !name.getText().isEmpty();
		
		boolean unitFactorsNotEmpty = !hm.isEmpty();
		
		//we need at least one entry in the hashmap and symbol and name of the unit
		if (symbolNotEmpty && nameNotEmpty && unitFactorsNotEmpty) { 
			String convPrevStr = (symbol.getText() + " = ");
			//we can calculate the preview
			convPrevStr += QudvUnitHelper.getInstance().convertToString(hm);
			conversionPreview.setText(convPrevStr);
			setPageComplete(true);
		} else {
			conversionPreview.setText("preview cannot be created");
			setPageComplete(false);
		}
	};
	
	/**
	 * a method to retrieve the referenced quantity kinds
	 * @return a map of quantity kind factors
	 */
	public HashMap<AQuantityKind, Double> getQuantityKindFactors() {
		
		quantityKindFactors.clear();
		
		int numberOfQKFactors = refQKComboViewerList.size();
		
		ComboViewer cv;
		Text expText;
		AQuantityKind selectedBaseUnit;
		Double selectedExponent;
		for (int i = 0; i < numberOfQKFactors; i = i + 1) {
			cv = refQKComboViewerList.get(i);
			ISelection selection = cv.getSelection();
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			expText = exponentList.get(i);
			if (!structuredSelection.isEmpty() && !expText.getText().isEmpty()) {
				selectedBaseUnit = (AQuantityKind) structuredSelection.getFirstElement();
				try {
					selectedExponent = Double.valueOf(expText.getText());
					quantityKindFactors.put(selectedBaseUnit, selectedExponent);
				} catch (NumberFormatException exception) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "QUDV wizard: invalid format of float value ignored", null));
				}
				
			}
		}
		return quantityKindFactors;
	}
	

	/**
	 * public getter method to get the name
	 * @return the name as String
	 */
	public String getName() {
		return name.getText();
	}
	
	/**
	 * public getter method to get the symbol
	 * @return the symbol as String
	 */
	public String getSymbol() {
		return symbol.getText();
	}
	
	/**
	 * public getter method to get the description
	 * @return the description as String
	 */
	public String getDescription() {
		return description.getText();
	}
	
	/**
	 * public getter method to get the definition URI
	 * @return the uri as String
	 */
	public String getDefinitionURI() {
		return definitionURI.getText();
	}
	
}
