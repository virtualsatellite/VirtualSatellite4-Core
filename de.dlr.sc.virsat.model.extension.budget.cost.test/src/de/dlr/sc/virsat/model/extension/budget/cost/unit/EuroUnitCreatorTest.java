package de.dlr.sc.virsat.model.extension.budget.cost.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

public class EuroUnitCreatorTest extends AProjectTestCase {

	@Override
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
	}
	
	@Test
	public void testAddEuro() {
		SystemOfUnits systemOfUnits = editingDomain.getResourceSet().getUnitManagement().getSystemOfUnit();
		AUnit euro = QudvUnitHelper.getInstance().getUnitByName(systemOfUnits, "Euro");
		assertNull("Euro initially dosent exist", euro);
		
		EuroUnitCreator euroCreator = new EuroUnitCreator(editingDomain);
		euroCreator.addEuro();
		euro = QudvUnitHelper.getInstance().getUnitByName(systemOfUnits, "Euro");
		assertNotNull(euro);
	}

}
