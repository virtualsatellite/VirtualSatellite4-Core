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
import de.dlr.sc.virsat.qudv.ui.wizards.pages.ConversionBasedUnitWizardPageOne;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvModelCommandFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * a wizard for the ConversionBased Units
 * @author scha_vo
 *
 */
public class ConversionBasedUnitWizard extends Wizard {

	private ConversionBasedUnitWizardPageOne one;
	private TransactionalEditingDomain ed;
	private QudvModelCommandFactory qudvController;
	private UnitManagement um;
	private AConversionBasedUnit unit = null;
	
	/**
	 * public constructor
	 * @param um the unit management where the unit will be added
	 * @param unit the unit which will be modified
	 */
	public ConversionBasedUnitWizard(UnitManagement um, AUnit unit) {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
		setUnitManagement(um);
		this.unit = (AConversionBasedUnit) unit;
	}
	
	/**
	 * public constructor
	 * @param um the unit management where the unit will be added
	 */
	public ConversionBasedUnitWizard(UnitManagement um) {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
		setUnitManagement(um);
		 
	}
	
	/**
	 * public constructor
	 */
	public ConversionBasedUnitWizard() {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png")); 
	}
	
	/**
	 * public setter method to set the unit management
	 * @param um the unit management to be set
	 */
	public void setUnitManagement(UnitManagement um) {
		this.um = um;
		this.ed = VirSatEditingDomainRegistry.INSTANCE.getEd(um);
		this.qudvController = new QudvModelCommandFactory(ed);
	}
	

	@Override
	public void addPages() {
		one = new ConversionBasedUnitWizardPageOne(unit, um, "Conversion Based Wizard");
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
		double factor = one.getFactor();
		double offset = one.getOffset();
		AUnit refUnit = one.getRefUnit();
		
		// create a new unit if it doesn't exist, set values otherwise, all executed via CommandStack
		if (unit == null) {
			if (Double.isNaN(offset)) {
				//create a linear conversion unit
				unit = QudvUnitHelper.getInstance().createLinearConversionUnit(name, symbol, description, definitionURI, quantityKind, refUnit, factor);
			} else {
				//create a affine conversion unit
				unit = QudvUnitHelper.getInstance().createAffineConversionUnit(name, symbol, description, definitionURI, quantityKind, refUnit, factor, offset);
			}
			Command cmd = qudvController.addConversionBasedUnit(um, unit, refUnit);
			ed.getCommandStack().execute(cmd);
		} else {
			if (unit instanceof AffineConversionUnit) {
				Command cmd = qudvController.setAffineConversionUnit(unit, name, symbol, description, definitionURI, quantityKind, factor, offset, refUnit);
				ed.getCommandStack().execute(cmd);
			}
			if (unit instanceof LinearConversionUnit) {
				Command cmd = qudvController.setLinearConversionUnit(unit, name, symbol, description, definitionURI, quantityKind, factor, refUnit);
				ed.getCommandStack().execute(cmd);
			}
		}		
		return true;
	}
}