/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.qudv.ui.wizards;

import java.util.HashMap;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;

import de.dlr.sc.virsat.qudv.ui.Activator;
import de.dlr.sc.virsat.qudv.ui.wizards.pages.DerivedQuantityKindWizardPageOne;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvModelCommandFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;

/**
 * the wizard for derived quantity kinds 
 * @author scha_vo
 *
 */
public class DerivedQuantityKindWizard extends Wizard {

	private DerivedQuantityKindWizardPageOne one;
	private UnitManagement um;
	
	private TransactionalEditingDomain ed;
	private QudvModelCommandFactory qudvController;
	
	private DerivedQuantityKind quantityKind = null;
	
	/**
	 * public constructor
	 * @param um the unit management
	 * @param quantityKind the quantity kind to work on
	 */
	public DerivedQuantityKindWizard(UnitManagement um, DerivedQuantityKind quantityKind) {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
		setUnitManagement(um);
		this.quantityKind = quantityKind;
	}
	
	/**
	 * public constructor
	 * @param um unit management
	 */
	public DerivedQuantityKindWizard(UnitManagement um) {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
		setUnitManagement(um);
	}
	
	/**
	 * public constructor
	 */
	public DerivedQuantityKindWizard() {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png")); 
	}
	
	/**
	 * public setter method to set the unit management
	 * @param um unit management
	 */
	public void setUnitManagement(UnitManagement um) {
		this.um = um;
		this.ed = VirSatEditingDomainRegistry.INSTANCE.getEd(um);
		this.qudvController = new QudvModelCommandFactory(ed);
	}
	
	@Override
	public void addPages() {
		one = new DerivedQuantityKindWizardPageOne(quantityKind, um, "Derived Quantity Kind Wizard");
		addPage(one);
	}

	@Override
	public boolean performFinish() {

		//collect data from the wizard pages
		String name = one.getName();
		String symbol = one.getSymbol();
		String description = one.getDescription();
		String definitionURI = one.getDefinitionURI();
		
		HashMap<AQuantityKind, Double> quantityKindFactors = one.getQuantityKindFactors();
		
		// create a new unit if it doesn't exist, set values otherwise
		if (quantityKind == null) {
			quantityKind = QudvUnitHelper.getInstance().createDerivedQuantityKind(name, symbol, description, definitionURI);
			Command cmd = qudvController.addDerivedQuantityKind(um, quantityKind, quantityKindFactors);	
			ed.getCommandStack().execute(cmd);
		} else {
			Command cmd = qudvController.setDerivedQuantityKind(um, quantityKind, name, symbol, description, definitionURI, quantityKindFactors);
			ed.getCommandStack().execute(cmd);
		}		
		return true;
	}
}