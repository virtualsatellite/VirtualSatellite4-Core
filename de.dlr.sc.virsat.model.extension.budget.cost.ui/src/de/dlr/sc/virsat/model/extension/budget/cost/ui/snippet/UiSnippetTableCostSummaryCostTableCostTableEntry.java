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

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostSummary;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostTableEntry;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostType;
import de.dlr.sc.virsat.model.extension.budget.cost.summaryTypes.SummaryTypes;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableCostSummaryCostTableCostTableEntry extends AUiSnippetTableCostSummaryCostTableCostTableEntry
		implements IUiSnippet {
	protected StructuralElementInstance sei;

	@Override
	// created a Button in CostSummary (Update CostEquipment)
	public void createButtons(FormToolkit toolkit, EditingDomain editingDomain, Composite sectionBody) {

		super.createButtons(toolkit, editingDomain, sectionBody);
		Button addCurrencyButton = toolkit.createButton(sectionBody, "Update CostEquipments", SWT.PUSH);

		// add the Button Function
		addCurrencyButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// created a VisSatEditingDomain in the Variable (virSatEd)
				VirSatTransactionalEditingDomain virSatEd = VirSatEditingDomainRegistry.INSTANCE.getEd(model);

				if (model instanceof CategoryAssignment) {
					// created a CostSummary in the Variable (costSummary)
					CostSummary costSummary = new CostSummary((CategoryAssignment) model);
					// created a SummaryTypes in the Variable (summaryTypes)
					SummaryTypes summaryTypes = new SummaryTypes();
					// called the SummaryTypes.class with the costSummary and returned the Values of the costSummary
					// in the Map and splited the Values to the different CostTypes
					Map<CostType, CostTableEntry> summaryMap = summaryTypes.summaryTyp(costSummary);
					// put the Values of the Map in a Collection (mapValues)
					Collection<CostTableEntry> mapValues = summaryMap.values();
						
					costSummary.getCostTable().clear();

					// created a loop and put every Value of the (mapValues) in (values)
					for (CostTableEntry values : mapValues) {
						// every one Value become a Command to put the Value to the CostTableEntry
						Command valuesCommand = costSummary.getCostTable().add(virSatEd, values);
						virSatEd.getCommandStack().execute(valuesCommand);
					}
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}
}
