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
import de.dlr.sc.virsat.qudv.ui.wizards.pages.SimpleUnitWizardPageOne;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvModelCommandFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * wizard base class for simple units
 * @author scha_vo
 *
 */
public class SimpleUnitWizard extends Wizard {

	private SimpleUnitWizardPageOne one;

	private TransactionalEditingDomain ed;
	private QudvModelCommandFactory qudvController;
	private UnitManagement um;
	private AUnit unit = null;
	
	/**
	 * public constructor
	 * @param um the unit management
	 * @param unit the unit to work on
	 */
	public SimpleUnitWizard(UnitManagement um, AUnit unit) {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
		setUnitManagement(um);
		this.unit = unit;
	}
	
	/**
	 * public constructor
	 * @param um the unit management
	 */
	public SimpleUnitWizard(UnitManagement um) {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
		setUnitManagement(um);
	}
	
	/**
	 * public constructor
	 */
	public SimpleUnitWizard() {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png")); 
	}
	
	/**
	 * public getter method
	 * @param um unit management
	 */
	public void setUnitManagement(UnitManagement um) {
		this.um = um;
		this.ed = VirSatEditingDomainRegistry.INSTANCE.getEd(um);
		this.qudvController = new QudvModelCommandFactory(ed);
	}
	
	@Override
	public void addPages() {
		one = new SimpleUnitWizardPageOne(unit, um, "Simple Unit Wizard");
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
		
		// create a new unit if it doesn't exist, set values otherwise
		if (unit == null) {
			unit = QudvUnitHelper.getInstance().createSimpleUnit(name, symbol, description, definitionURI, quantityKind);
			Command cmd = qudvController.addSimpleUnit(um, unit);
			ed.getCommandStack().execute(cmd);
		} else {
			Command cmd = qudvController.setSimpleUnit(unit, name, symbol, description, definitionURI, quantityKind);
			ed.getCommandStack().execute(cmd);
		}		
		return true;
	}
}