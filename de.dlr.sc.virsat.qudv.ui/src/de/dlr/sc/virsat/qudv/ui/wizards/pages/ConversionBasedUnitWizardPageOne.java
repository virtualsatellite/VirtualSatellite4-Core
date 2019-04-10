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

import java.util.List;

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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;

/**
 * first page of the conversion based unit wizard
 * @author scha_vo
 *
 */
public class ConversionBasedUnitWizardPageOne extends AUnitWizardPage {
	private Combo baseUnitCombo;
	private Text factor;
	private Text offset;
	private Text conversionPreview;
	private ComboViewer baseUnitComboViewer;
	private AUnit selectedRefUnit;
	
	/**
	 * 
	 * @param unit the unit to work on
	 * @param um the unit management
	 * @param pageName the name of the page
	 */
	public ConversionBasedUnitWizardPageOne(AUnit unit, UnitManagement um, String pageName) {
		super(unit, um, pageName);
		setTitle("Conversion Based Unit Wizard First Page");
		setDescription("This wizard help to create and edit Conversion Based Units of the QUDV Model.");
	}

	@Override
	public void createControl(Composite parent) {
		//Standard Controls come from the super class
		super.createControl(parent);
	
    	GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
    	gridData.horizontalSpan = 2;
    	Label separator = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
    	separator.setLayoutData(gridData);
    	
    	//base unit widgets
    	Label label5 = new Label(container, SWT.NULL);
    	label5.setText("Select Reference Unit");
    	label5.setToolTipText("The unit you want to reference to.");
    	baseUnitCombo = new Combo(container, SWT.NULL);
    	baseUnitComboViewer = new ComboViewer(baseUnitCombo);
    	baseUnitComboViewer.setLabelProvider(new LabelProvider() {
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
    	baseUnitComboViewer.setContentProvider(new ArrayContentProvider());
    	List<AUnit> listOfUnits = systemOfUnits.getUnit();
    	baseUnitComboViewer.setInput(listOfUnits);
   
    	Label label9 = new Label(container, SWT.NULL);
    	label9.setText("Factor");
    	factor = new Text(container, SWT.NULL);
    	factor.setText("");
    	factor.setLayoutData(gd);
    	factor.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setErrorMessage(null);
				if (Float.isNaN(getFactor())) {
					setErrorMessage("Factor value connot be converted to a float. Enter a correct number.");				
				}
				updateWidgets();
			}
    	});
		
    	Label label8 = new Label(container, SWT.NULL);
    	label8.setText("Offset");
    	offset = new Text(container, SWT.NULL);
    	offset.setText("");
    	offset.setLayoutData(gd);
    	offset.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setErrorMessage(null);
				if (Float.isNaN(getOffset())) {
					setErrorMessage("Offset value connot be converted to a float. Enter a correct number.");
				}
				updateWidgets();
			}
    	});
    	
    	//Conversion preview widgets
    	Label conversionLabel = new Label(container, SWT.NULL);
    	conversionLabel.setText("Conversion preview:");
		conversionPreview = new Text(container, SWT.NULL);
		conversionPreview.setText("preview cannot be created");
		conversionPreview.setLayoutData(gd);
    	conversionPreview.setEnabled(false);
		
    	//Listeners at last because they need existing other widgets
    	//change Listener for the baseUnitCombo
    	baseUnitComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = event.getSelection();
                if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
                    Object o = ((IStructuredSelection) selection).getFirstElement();
                    if (o instanceof AUnit) {
                    	selectedRefUnit = (AUnit) o;
                    	updateWidgets();
                    }
                }
            }
        });
    	   	
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);
		initializeValues();
	}
	

	@Override
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
			setPageComplete(true);
			baseUnitComboViewer.setSelection(new StructuredSelection(unit));
			if (unit instanceof AConversionBasedUnit) {
				AConversionBasedUnit convUnit  = (AConversionBasedUnit) unit;
				AUnit refUnit = convUnit.getReferenceUnit();
				baseUnitComboViewer.setSelection(new StructuredSelection(refUnit));
			}
			if (unit instanceof AffineConversionUnit) {
				AffineConversionUnit affUnit  = (AffineConversionUnit) unit;
				factor.setText("" + affUnit.getFactor());
				offset.setText("" + affUnit.getOffset());
			}
			if (unit instanceof LinearConversionUnit) {
				LinearConversionUnit linUnit  = (LinearConversionUnit) unit;
				factor.setText("" + linUnit.getFactor());
				offset.setText("");
				offset.setEnabled(false);
				offset.setToolTipText("Offset is disabled because LinearConversionUnit type does not support an offset value. To enable offset field, please creat a new unit.");
			}
			updateWidgets();
		}
	}
	@Override
	protected void updateWidgets() {
		if (selectedRefUnit != null && !symbol.getText().isEmpty() && !name.getText().isEmpty() && !Float.isNaN(getFactor())) { 
			if (offset.getText().isEmpty()) {
				conversionPreview.setText("1" + symbol.getText() + " = " + factor.getText() + selectedRefUnit.getSymbol());
			} else {
				conversionPreview.setText("1" + symbol.getText() + " = " + factor.getText() + selectedRefUnit.getSymbol() + " + " + offset.getText());
			}
			setPageComplete(true);
		} else {
			conversionPreview.setText("preview cannot be created");
			setPageComplete(false);
		}
	};
	
	/**
	 * public getter method to get the factor value
	 * @return the float value of the factor
	 */
	public float getFactor() {
		try {
			return Float.parseFloat(factor.getText());
		} catch (NumberFormatException e) {
			return Float.NaN;
		}
	}
	/**
	 * public getter method to get the offset value
	 * @return the float value of the offset
	 */
	public float getOffset() {
		try {
			return Float.parseFloat(offset.getText());
		} catch (NumberFormatException e) {
			return Float.NaN;
		}
	}
	/**
	 * public getter method to read the referenceUnit from the page 
	 * @return the reference unit
	 */
	public AUnit getRefUnit() {
		
		ISelection selection = baseUnitComboViewer.getSelection();
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		if (!structuredSelection.isEmpty()) {
			selectedRefUnit = (AUnit) structuredSelection.getFirstElement();
		} 
		return selectedRefUnit;	
	}

}
