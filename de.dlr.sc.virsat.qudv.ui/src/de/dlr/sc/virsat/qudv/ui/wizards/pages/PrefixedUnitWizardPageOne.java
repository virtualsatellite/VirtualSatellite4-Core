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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvModelCommandFactory;

/**
 * 
 * @author scha_vo
 *
 */
public class PrefixedUnitWizardPageOne extends AUnitWizardPage {
	private Combo prefixCombo;
	private Combo baseUnitCombo;
	
	private ComboViewer prefixComboViewer;
	private ComboViewer baseUnitComboViewer;
	
	private Prefix selectedPrefix;
	private AUnit selectedBaseUnit;
	
	/**
	 * public constructor
	 * @param unit the unit to work on
	 * @param um the unit management
	 * @param pageName the name of the page
	 */
	public PrefixedUnitWizardPageOne(AUnit unit, UnitManagement um, String pageName) {
		super(unit, um, pageName);
		setTitle("Prefixed Unit Wizard First Page");
		setDescription("This wizard help to create and edit Prefixed Units of the QUDV Model.");
	}

	@Override
	public void createControl(Composite parent) {	
		super.createControl(parent);
		Label separator2 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData.horizontalSpan = 2;
		separator2.setLayoutData(gridData);
		
		//base unit widgets
		Label label5 = new Label(container, SWT.NULL);
		label5.setText("Select Base Unit");
		label5.setToolTipText("The base unit you want to reference to.");
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
		List<AUnit> listOfUnits = QudvModelCommandFactory.getListOfNonPrefixedUnits(systemOfUnits);
    	baseUnitComboViewer.setInput(listOfUnits);	
		
		//Prefix widgets
		Label label6 = new Label(container, SWT.NULL);
		label6.setText("Select Prefix");
		label6.setToolTipText("The prefix for the Unit is e.g. 'k' for kilo in kilogramm, or 'M' Mega as in MegaByte.");
		prefixCombo = new Combo(container, SWT.NULL);
		prefixComboViewer = new ComboViewer(prefixCombo);
		prefixComboViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Prefix) {
					Prefix p = (Prefix) element;
					String result = p.getName() + " - " + p.getSymbol();
					return result;
				}
				return super.getText(element);
			}
		});
    	prefixComboViewer.setContentProvider(new ArrayContentProvider());
    	List<Prefix> prefixList =  systemOfUnits.getPrefix();
    	prefixComboViewer.setInput(prefixList);
    	
    	//Listeners at last because they need existing other widgets
    	//change Listener for the baseUnitCombo
    	baseUnitComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = event.getSelection();
                if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
                    Object o = ((IStructuredSelection) selection).getFirstElement();
                    if (o instanceof AUnit) {
                    	selectedBaseUnit = (AUnit) o;
                    	updateWidgets();
                    }
                }

            }
        });
    	//change Listener for the prefixCombo
    	prefixComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = event.getSelection();
                if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
                    Object o = ((IStructuredSelection) selection).getFirstElement();
                    if (o instanceof Prefix) {
                    	selectedPrefix = (Prefix) o;
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
			if (unit instanceof PrefixedUnit) {
				PrefixedUnit pu = (PrefixedUnit) unit;
				Prefix p = pu.getPrefix();
				prefixComboViewer.setSelection(new StructuredSelection(p));
				AUnit refUnit = pu.getReferenceUnit();
				baseUnitComboViewer.setSelection(new StructuredSelection(refUnit));
				setPageComplete(true);
			} else {
				setPageComplete(false);
			}	
		}
	}
	
	/**
	 * a mthod to update the widgets
	 */
	protected void updateWidgets() {
		if (selectedBaseUnit != null && selectedPrefix != null) {			
			setPageComplete(true);
		} else {
			setPageComplete(false);
		}
	};
	
	/**
	 * getter method to get the Prefix
	 * @return the Prefix
	 */
	public Prefix getPrefix() {
		
		ISelection selection = prefixComboViewer.getSelection();
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		if (!structuredSelection.isEmpty()) {
			selectedPrefix = (Prefix) structuredSelection.getFirstElement();
		} 
		return selectedPrefix;	
	}
	
	/**
	 * getter method to get the BaseUnit
	 * @return the base unit
	 */
	public AUnit getBaseUnit() {
		ISelection selection = baseUnitComboViewer.getSelection();
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		if (!structuredSelection.isEmpty()) {
			selectedBaseUnit = (AUnit) structuredSelection.getFirstElement();
		} 
		return selectedBaseUnit;
	}
}
