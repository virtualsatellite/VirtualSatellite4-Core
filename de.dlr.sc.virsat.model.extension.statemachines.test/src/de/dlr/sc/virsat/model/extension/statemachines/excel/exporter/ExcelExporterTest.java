/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.excel.exporter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.excel.AExcelIo;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.statemachines.Activator;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;

/**
 * Test Case for Exporting to Excel
 * @author bell_er
 *
 */
public class ExcelExporterTest {

	private ABeanStructuralElementInstance aBean;
	private StateMachine sm;
	
	private Concept conceptStateMachines;
		
	@Before
	public void setUp() throws CoreException {
		UserRegistry.getInstance().setSuperUser(true);
	
		conceptStateMachines = ConceptXmiLoader.loadConceptFromPlugin(Activator.getPluginId() + "/concept/concept.xmi");
		
		sm = new StateMachine(conceptStateMachines);
		
		State state1 = new State(conceptStateMachines);
		State state2 = new State(conceptStateMachines);
		State state3 = new State(conceptStateMachines);
		Transition transition1 = new Transition(conceptStateMachines);
		transition1.setStateFrom(state1);
		transition1.setStateTo(state2);

		sm.getStates().add(state1);
		sm.getStates().add(state2);
		sm.getStates().add(state3);
		sm.getTransitions().add(transition1);
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(ActiveConceptHelper.getCategory(conceptStateMachines, StateMachine.class.getSimpleName()).getApplicableFor().get(0));
		aBean = new ElementDefinition(sei);
		aBean.getStructuralElementInstance().setUuid(new VirSatUuid("74ccc93a-281b-4ab8-ace4-cb7f2b927d4b"));
		aBean.setName("BATTERY"); 
		aBean.add(sm);
	}
	
	@Test
	public void test() throws IOException  { 		
		InputStream is = Activator.getResourceContentAsString("/resources/SampleTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(is);
		StateMachineExporter sme = new StateMachineExporter();
		sme.setWb(wb);
		sme.exportData(sm.getTypeInstance());
		wb = sme.getWb();
		Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_STATES);
		
		for (int i = 0; i < sm.getStates().size(); ++i) {
			State state = sm.getStates().get(i);
			Cell cell = sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + i).getCell(AExcelIo.STATE_COLUMN_STATE_NAME);
			assertEquals("State " + i + "exported correctly", state.getName(), cell.toString());
		}
	}
}
