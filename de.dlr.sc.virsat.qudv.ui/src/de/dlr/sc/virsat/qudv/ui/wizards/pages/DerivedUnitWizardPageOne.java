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
import java.util.Map.Entry;

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
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;

/**
 * a first page of the derived unit wizard
 * @author scha_vo
 *
 */
public class DerivedUnitWizardPageOne extends AUnitWizardPage {
	
	private List<Combo> baseUnitComboList = new ArrayList<Combo>();
	private List<ComboViewer> baseUnitComboViewerList = new ArrayList<ComboViewer>();
	
	private List<Text> exponentList = new ArrayList<Text>();
	
	private Button buttonAdd;
	private Label placeholder1;
	private Label placeholder2;
	private Label conversionLabel;
	private Text conversionPreview;
	
	private HashMap<AUnit, Double> unitFactors = new HashMap<AUnit, Double>();
	
	private int count;
	
	/**
	 * public constructor
	 * @param unit the unit for the wizard
	 * @param um the unit management 
	 * @param pageName the name of the page
	 */
	public DerivedUnitWizardPageOne(AUnit unit, UnitManagement um, String pageName) {
		super(unit, um, pageName);
		setTitle("Derived Unit Wizard First Page");
		setDescription("This wizard help to create and edit Derived Units of the QUDV Model.");
	}

	@Override
	public void createControl(Composite parent) {
		//somehow I need to reset the count, because the garbage collection messes around with the count variable
		count = 0;
		
		super.createControl(parent);
    	
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
		
		new Label(container, SWT.NULL);
		Label separator2 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator2.setLayoutData(gridData);
		
    	createAddButtonAndPreviewControls();
    	
    	//per default set 3 lines of controls;  
        addSetOfUnitFactorControls();
        addSetOfUnitFactorControls();
        addSetOfUnitFactorControls();
 
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);
		initializeValues();
		container.layout(true);
		container.pack();
	}
	
	/**
	 * method to initialize the values in the wizard page. It reads from the object and fills out the widgets in the wizard
	 */
	protected void initializeValues() {
		if (unit != null) {
			name.setText(unit.getName());
			symbol.setText(unit.getSymbol());
			description.setText(unit.getDescription());
			definitionURI.setText(unit.getDefinitionURI());
			AQuantityKind quantityKind = unit.getQuantityKind();
			if (quantityKind == null) {
				comboViewer.setSelection(new StructuredSelection("no reference"));
			} else {
				comboViewer.setSelection(new StructuredSelection(quantityKind));
			}
			if (unit instanceof DerivedUnit) {
				DerivedUnit derivedUnit = (DerivedUnit) unit; 
				List<UnitFactor> listUnitFactors = derivedUnit.getFactor();
				
				int numberOfUnitFactors = listUnitFactors.size();
				
				//add the right amount of controls
				int toAdd = numberOfUnitFactors - count;
				if (toAdd > 0) {
					for (int i = 0; i < toAdd; i = i + 1) {
						addSetOfUnitFactorControls();
					}
				}
				
				//fill the comboBoxes and Textfields with proper values
				for (int i = 0; i < numberOfUnitFactors; i = i + 1) {
					AUnit baseUnit = listUnitFactors.get(i).getUnit();
					baseUnitComboViewerList.get(i).setSelection(new StructuredSelection(baseUnit));
					Double d = listUnitFactors.get(i).getExponent();
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
        buttonAdd.setText("Add new unit factor");
        buttonAdd.addSelectionListener(new SelectionAdapter() {
        	public void widgetSelected(SelectionEvent e) {
        		addSetOfUnitFactorControls();
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
	 * adds the controls for setting the unit factors
	 */
	private void addSetOfUnitFactorControls() {

		removeButtonAndPreviewControls();
		
		count = count + 1;
		
		//add a Label:
		Label myLabel = new Label(container, SWT.NULL);
    	myLabel.setText("Unit factor #" + count);
		
    	//add comboBox and comboViewer
    	Combo combo = new Combo(container, SWT.NULL);
    	ComboViewer comboViewer = new ComboViewer(combo);
    	comboViewer.setLabelProvider(new LabelProvider() {
    		@Override
    		public String getText(Object element) {
    			if (element instanceof AUnit) {
    				AUnit u = (AUnit) element;
    				String result = u.getName() + " - " + u.getSymbol();
    				return result;
    			}
    			return super.getText(element);
    		}
    	});
    	comboViewer.setContentProvider(new ArrayContentProvider());
    	List<AUnit> listOfUnits = systemOfUnits.getUnit();
    	comboViewer.setInput(listOfUnits);
    	comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = event.getSelection();
                if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
                    Object o = ((IStructuredSelection) selection).getFirstElement();
                    if (o instanceof AUnit) {
                    	updateWidgets();
                    }
                }
            }
        });
    	
    	//create Text field
    	final Text expText = new Text(container, SWT.NULL);
		expText.setText("");
		expText.setLayoutData(gd);
		expText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setErrorMessage(null);
				if (Double.isNaN(getValue(expText))) {
					setErrorMessage("Factor value connot be converted to a float. Enter a correct number.");			
				}
				updateWidgets();
			}
    	});
	
		//addControls to their lists
		baseUnitComboList.add(combo);
		baseUnitComboViewerList.add(comboViewer);
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
		
		HashMap<AUnit, Double> hm = getUnitFactors();
		
		boolean symbolNotEmpty = !symbol.getText().isEmpty();
		boolean nameNotEmpty = !name.getText().isEmpty();
		boolean unitFactorsNotEmpty = !hm.isEmpty();
		
		//we need at least one entry in the hashmap and symbol and name of the unit
		if (symbolNotEmpty && nameNotEmpty && unitFactorsNotEmpty) { 
			StringBuilder convPrevStr = new StringBuilder();
			convPrevStr.append(symbol.getText());
			convPrevStr.append(" = ");
			//we can calculate the preview
			for (Entry<AUnit, Double> entry : hm.entrySet()) {
				AUnit currentUnit = entry.getKey();
				Double value = entry.getValue();
				
				if (Double.isNaN(value)) {
					cannotCreatePreview();
					return;
				} else {
					convPrevStr.append(currentUnit.getSymbol());
					convPrevStr.append(getUnicodeEscapeStatement(value));
				}
			}
			conversionPreview.setText(convPrevStr.toString());
			setPageComplete(true);
		} else {
			cannotCreatePreview();
		}
	};
	
	/**
	 * Marks page as not complete and notifies user that the preview cannot be created
	 */
	private void cannotCreatePreview() {
		conversionPreview.setText("preview cannot be created");
		setPageComplete(false);
	}
	
	/**
	 * Getter method to get the factor value
	 * @param text the text field containing a floating point number
	 * @return the numeric value or NaN if it is not numeric
	 */
	private double getValue(Text text) {
		try {
			return Double.parseDouble(text.getText());
		} catch (NumberFormatException e) {
			return Double.NaN;
		}
	}
	
	/**
	 * a method to retrieve the unicode escape statement of the double value
	 * @param f the double value
	 * @return the unicode statement as String
	 */
	private String getUnicodeEscapeStatement(Double f) {
		String str = "";
		final Double epsilon = 1E-5;
		int intOfF = f.intValue();  
		if (Math.abs(f - intOfF) < epsilon) {
			//CHECKSTYLE:OFF
			switch (intOfF) {
				case 1: 
					break; //do nothing
				case 2: 
					str = "\u00b2"; break;
				case 3: 
					str = "\u00b3"; break;
				case 4: 
					str = "\u2074"; break;
				case 5: 
					str = "\u2075"; break;
				case 6: 
					str = "\u2076"; break;
				case 7: 
					str = "\u2077"; break;
				case 8: 
					str = "\u2078"; break;
				case 9: 
					str = "\u2079"; break;
				case -1:
					str = "\u207b\u00b9"; break;
				case -2:
					str = "\u207b\u00b2"; break;
				case -3:
					str = "\u207b\u00b3"; break;
				case -4:
					str = "\u207b\u2074"; break;
				case -5:
					str = "\u207b\u2075"; break;
				case -6:
					str = "\u207b\u2076"; break;
				case -7:
					str = "\u207b\u2077"; break;
				case -8:
					str = "\u207b\u2078"; break;
				case -9:
					str = "\u207b\u2079"; break;
				default: 
					str = "^" + f;
				//CHECKSTYLE:ON
			}
			
		} else { //difference is larger that epsilon
			str = "^" + f;
		}
		str += " "; //add a space
		return str;
	}
	
	/**
	 * a method to retrieve the unit factor
	 * @return a map of unit factors
	 */
	public HashMap<AUnit, Double> getUnitFactors() {
		
		unitFactors.clear();
		
		int numberOfUnitFactors = baseUnitComboViewerList.size();

		for (int i = 0; i < numberOfUnitFactors; i = i + 1) {
			ComboViewer cv = baseUnitComboViewerList.get(i);
			ISelection selection = cv.getSelection();
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Text expText = exponentList.get(i);
			if (!structuredSelection.isEmpty() && !expText.getText().isEmpty()) {
				AUnit selectedBaseUnit = (AUnit) structuredSelection.getFirstElement();
				Double selectedExponent = getValue(expText);
				unitFactors.put(selectedBaseUnit, selectedExponent);
			}
		}
		return unitFactors;
	}
}
