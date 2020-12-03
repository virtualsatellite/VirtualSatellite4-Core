/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.unit;

import java.util.List;

import org.eclipse.emf.common.command.Command;

import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvModelCommandFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

public class EuroUnitCreator {
	
	private VirSatTransactionalEditingDomain virSatEd;
	
	public EuroUnitCreator(VirSatTransactionalEditingDomain virSatEd) {
		this.virSatEd = virSatEd;
	}
	/**
	 * The addEuro Method create the Unit (Euro) and put the Unit in the Unit-Management 
	 */
	public void addEuro() {
		
		UnitManagement unitManagement = virSatEd.getResourceSet().getUnitManagement();
		List<SystemOfQuantities> systemOfQuantities = unitManagement.getSystemOfUnit().getSystemOfQuantities();
		AQuantityKind dimensionless = QudvUnitHelper.getInstance().getQuantityKindByName(systemOfQuantities.get(0), "Dimensionless");
		SimpleUnit euro = QudvUnitHelper.getInstance().createSimpleUnit("Euro", "€", "European Economic and Monetary Union", " ", dimensionless);
		QudvModelCommandFactory qudvController = new QudvModelCommandFactory(virSatEd);
		Command addEuroCommand = qudvController.addSimpleUnit(unitManagement, euro);
		virSatEd.getCommandStack().execute(addEuroCommand);
	}

}
