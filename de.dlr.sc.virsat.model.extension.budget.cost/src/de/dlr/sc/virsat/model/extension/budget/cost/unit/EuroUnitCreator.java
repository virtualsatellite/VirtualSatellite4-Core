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
	
	public void addEuro() {
		
		
		UnitManagement unitManagment = virSatEd.getResourceSet().getUnitManagement();
		List<SystemOfQuantities> systemOfQuantities = unitManagment.getSystemOfUnit().getSystemOfQuantities();
		AQuantityKind dimensionless = QudvUnitHelper.getInstance().getQuantityKindByName(systemOfQuantities.get(0), "Dimensionless");
		SimpleUnit euro = QudvUnitHelper.getInstance().createSimpleUnit("Euro", "€", "European Economic and Monetary Union", " ", dimensionless);
		QudvModelCommandFactory qudvController = new QudvModelCommandFactory(virSatEd);
		Command addEuroCommand = qudvController.addSimpleUnit(unitManagment, euro);
		virSatEd.getCommandStack().execute(addEuroCommand);
	}

}
