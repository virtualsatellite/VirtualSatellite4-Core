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
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.statemachines.Activator;
import de.dlr.sc.virsat.model.extension.statemachines.excel.AExcelStatIO;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;

/**
 * Test Case for Exporting to Excel
 */
public class ExcelExporterTest {

	private static final String CONCEPT_ID_STATE_MACHINES = "de.dlr.sc.virsat.model.extension.statemachines";

	private ABeanStructuralElementInstance aBeanSei;
	private StateMachine stateMaschine;

	private Concept conceptStateMachines;
	protected LocalDateTime localDateTime;
	
	@Before
	public void setUp() throws CoreException {
		UserRegistry.getInstance().setSuperUser(true);

		conceptStateMachines = ConceptXmiLoader.loadConceptFromPlugin(CONCEPT_ID_STATE_MACHINES + "/concept/concept.xmi");

		stateMaschine = new StateMachine(conceptStateMachines);

		State state1 = new State(conceptStateMachines);
		State state2 = new State(conceptStateMachines);
		State state3 = new State(conceptStateMachines);
		Transition transition1 = new Transition(conceptStateMachines);
		transition1.setStateFrom(state1);
		transition1.setStateTo(state2);

		stateMaschine.getStates().add(state1);
		stateMaschine.getStates().add(state2);
		stateMaschine.getStates().add(state3);
		stateMaschine.getTransitions().add(transition1);
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(ActiveConceptHelper.getCategory(conceptStateMachines, StateMachine.class.getSimpleName()).getApplicableFor().get(0));
		aBeanSei = new ElementDefinition(sei);
		aBeanSei.getStructuralElementInstance().setUuid(new VirSatUuid("74ccc93a-281b-4ab8-ace4-cb7f2b927d4b"));
		aBeanSei.setName("BATTERY");
		aBeanSei.add(stateMaschine);
		
		//CHECKSTYLE:OFF
		localDateTime = LocalDateTime.of(2020, 04, 21, 13, 27);
		//CHECKSTYLE:ON
	}

	@Test
	public void exportDateTest() throws IOException {		
		InputStream iStream = Activator.getResourceContentAsString("/resources/SampleTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);
		StateMachineExporter sme = new StateMachineExporter(localDateTime);
		sme.helper.setWb(wb);
		sme.exportData(stateMaschine.getTypeInstance());
		wb = sme.helper.getWb();
		Sheet sheet = wb.getSheet(AExcelStatIO.TEMPLATE_SHEETNAME_STATES);

		for (int i = 0; i < stateMaschine.getStates().size(); ++i) {
			State state = stateMaschine.getStates().get(i);
			Cell cell = sheet.getRow(AExcelStatIO.COMMON_ROW_START_TABLE + i).getCell(AExcelStatIO.STATE_COLUMN_STATE_NAME);
			assertEquals("State " + i + "exported correctly", state.getName(), cell.toString());
		}
	}
}
