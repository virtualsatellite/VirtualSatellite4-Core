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
import de.dlr.sc.virsat.qudv.ui.wizards.pages.SimpleQuantityKindWizardPageOne;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvModelCommandFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * a simple wizard which allows to add simple quantity kinds
 * @author scha_vo
 *
 */
public class SimpleQuantityKindWizard extends Wizard {

	private SimpleQuantityKindWizardPageOne one;

	private UnitManagement um;
	
	private TransactionalEditingDomain ed;
	private QudvModelCommandFactory qudvController;
	
	private SimpleQuantityKind quantityKind = null;
	
	/**
	 * wizard to define quantity kinds 
	 * @param um the unit management
	 * @param quantityKind the quantity kind
	 */
	
	public SimpleQuantityKindWizard(UnitManagement um, SimpleQuantityKind quantityKind) {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
		setUnitManagement(um);
		this.quantityKind = quantityKind;
	}
	
	/**
	 * public constructor
	 * @param um the unit management
	 */
	public SimpleQuantityKindWizard(UnitManagement um) {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
		setUnitManagement(um);
	}
	
	/**
	 * public constructor
	 */
	public SimpleQuantityKindWizard() {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png")); 
	}

	/**
	 * public setter to set the unit management
	 * @param um the unit management
	 */
	public void setUnitManagement(UnitManagement um) {
		this.um = um;
		this.ed = VirSatEditingDomainRegistry.INSTANCE.getEd(um);
		this.qudvController = new QudvModelCommandFactory(ed);
	}
	
	@Override
	public void addPages() {
		one = new SimpleQuantityKindWizardPageOne(quantityKind, um, "Simple Quantity Kind Wizard");
		addPage(one);
	}

	@Override
	public boolean performFinish() {
		// collect data from the wizard pages
		String name = one.getName();
		String symbol = one.getSymbol();
		String description = one.getDescription();
		String definitionURI = one.getDefinitionURI();
		
		// create a new unit if it doesn't exist, set values otherwise
		if (quantityKind == null) {
			quantityKind = QudvUnitHelper.getInstance().createSimpleQuantityKind(name, symbol, description, definitionURI);
			Command cmd = qudvController.addSimpleQuantityKind(um, quantityKind);
			ed.getCommandStack().execute(cmd);
		} else {
			Command cmd = qudvController.setSimpleQuantityKind(quantityKind, name, symbol, description, definitionURI);
			ed.getCommandStack().execute(cmd);
		}		
		return true;
	}
}