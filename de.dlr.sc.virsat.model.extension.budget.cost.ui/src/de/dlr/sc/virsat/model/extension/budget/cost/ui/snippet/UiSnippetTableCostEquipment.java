/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.ui.snippet;


import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.extension.budget.cost.unit.EuroUnitCreator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 *  Definition of the equipment cost
 * 
 */
public class UiSnippetTableCostEquipment extends AUiSnippetTableCostEquipment {

	public static final int SINGLE_LINE_TABLE_HEIGHT = 55;
	
	@Override
	protected Table createDefaultTable(FormToolkit toolkit, Composite sectionBody) {
		Table table = super.createDefaultTable(toolkit, sectionBody);
	
		GridData gridDataTable = (GridData) table.getLayoutData();
		gridDataTable.heightHint = SINGLE_LINE_TABLE_HEIGHT;
		
		table.setLayoutData(gridDataTable);
		return table;
	}
	
	@Override
	protected void createButtons(FormToolkit toolkit, EditingDomain editingDomain, Composite sectionBody) {
		
		super.createButtons(toolkit, editingDomain, sectionBody);
		Button addCurrencyButton = toolkit.createButton(sectionBody, "Add Currencies to Model", SWT.PUSH);

		addCurrencyButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				VirSatTransactionalEditingDomain virSatEd = VirSatEditingDomainRegistry.INSTANCE.getEd(model);
				EuroUnitCreator euroCreater = new EuroUnitCreator(virSatEd);
				euroCreater.addEuro();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}
}
