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
import de.dlr.sc.virsat.qudv.ui.wizards.pages.DerivedUnitWizardPageOne;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvModelCommandFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;

/**
 * the wizard to define derived units
 * @author scha_vo
 *
 */
public class DerivedUnitWizard extends Wizard {

	private DerivedUnitWizardPageOne one;

	private UnitManagement um;
	private TransactionalEditingDomain ed;
	private QudvModelCommandFactory qudvController;
	private DerivedUnit unit = null;
	
	/**
	 * public constructor
	 * @param um the unit management
	 * @param unit the unit to work with
	 */
	public DerivedUnitWizard(UnitManagement um, AUnit unit) {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
		setUnitManagement(um);
		this.unit = (DerivedUnit) unit;
	}
	
	/**
	 * public constructor
	 * @param um the unit management
	 */
	public DerivedUnitWizard(UnitManagement um) {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png")); 
		setUnitManagement(um);
		
	}
	/**
	 * public constructor
	 */
	public DerivedUnitWizard() {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png")); 
	}
	
	/**
	 * public setter method to set the unit management
	 * @param um the unit management
	 */
	public void setUnitManagement(UnitManagement um) {
		this.um = um;
		this.ed = VirSatEditingDomainRegistry.INSTANCE.getEd(um);
		this.qudvController = new QudvModelCommandFactory(ed);
	}
	

	@Override
	public void addPages() {
		one = new DerivedUnitWizardPageOne(unit, um, "Derived Unit Wizard");
		addPage(one);
	}

	@Override
	public boolean performFinish() {

		//collect data from the wizard pages
		String name = one.getName();
		String symbol = one.getSymbol();
		String description = one.getDescription();
		String definitionURI = one.getDefinitionURI();
		AQuantityKind quantityKind = one.getRefQuantityKind();
		HashMap<AUnit, Double> unitFactors = one.getUnitFactors();
		
		// create a new unit if it doesn't exist, set values otherwise, all executed via CommandStack
		if (unit == null) {
			//create a new derived unit
			unit = QudvUnitHelper.getInstance().createDerivedUnit(name, symbol, description, definitionURI, quantityKind);
			Command cmd = qudvController.addDerivedUnit(um, unit, unitFactors);
			ed.getCommandStack().execute(cmd);
		} else {
			//update the existing derived unit
			Command cmd = qudvController.setDerivedUnit(unit, name, symbol, description, definitionURI, quantityKind, unitFactors);
			ed.getCommandStack().execute(cmd);
		}		
		return true;
	}
}