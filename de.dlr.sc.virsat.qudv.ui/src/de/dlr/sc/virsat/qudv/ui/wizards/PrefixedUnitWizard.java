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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;

import de.dlr.sc.virsat.qudv.ui.Activator;
import de.dlr.sc.virsat.qudv.ui.wizards.pages.PrefixedUnitWizardPageOne;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvModelCommandFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;

/**
 * the wizard to define a prefixed unit
 * @author scha_vo
 *
 */
public class PrefixedUnitWizard extends Wizard {

	private PrefixedUnitWizardPageOne one;

	private UnitManagement um;
	
	private TransactionalEditingDomain ed;
	private QudvModelCommandFactory qudvController;
	
	private PrefixedUnit unit = null;
	
	/**
	 * public constructor
	 * @param um the unit management
	 * @param unit the unit to work with
	 */
	public PrefixedUnitWizard(UnitManagement um, AUnit unit) {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
		setUnitManagement(um);
		this.unit = (PrefixedUnit) unit;
	}
	
	/**
	 * public constructor
	 * @param um the unit management
	 */
	public PrefixedUnitWizard(UnitManagement um) {
		super();
		setNeedsProgressMonitor(true);
		setUnitManagement(um);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png")); 
	}
	
	/**
	 * public constructor
	 */
	public PrefixedUnitWizard() {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png")); 
	}
	
	/**
	 * 
	 * @param um the unit management
	 */
	public void setUnitManagement(UnitManagement um) {
		this.um = um;
		this.ed = VirSatEditingDomainRegistry.INSTANCE.getEd(um);
		this.qudvController = new QudvModelCommandFactory(ed);
	}
	

	@Override
	public void addPages() {
		one = new PrefixedUnitWizardPageOne(unit, um, "Prefixed Unit Wizard");
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
		AUnit baseUnit = one.getBaseUnit();
		Prefix prefix = one.getPrefix();
		
		// create a new unit if it doesn't exist, set values otherwise
		if (unit == null) {
			unit = QudvUnitHelper.getInstance().createPrefixedUnit(name, symbol, description, definitionURI, quantityKind, prefix, baseUnit);
			Command cmd = qudvController.addPrefixedUnit(um, unit, prefix, baseUnit);
			ed.getCommandStack().execute(cmd);
		} else {
			Command cmd = qudvController.setPrefixedUnit(unit, name, symbol, description, definitionURI, quantityKind, prefix, baseUnit);
			ed.getCommandStack().execute(cmd);
		}		
		return true;
	}
}