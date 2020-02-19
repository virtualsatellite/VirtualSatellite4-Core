/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.statemachines.excel.importer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.excel.fault.FaultType;
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
 * Test Case for Importing from Excel
 */
public class ExcelImporterTest {
	private static final String CONCEPT_ID_MACHINES = "de.dlr.sc.virsat.model.extension.statemachines";

	private static final int STATE_NOT_EXISTING = 6;
	private static final int STATE_WITHOUT_UUID = 5;
	private static final int STATE_WITHOUT_NAME = 3;
	private static final int EXPECTED_STATECOUNT = 4;
	private static final int EXPECTED_TRANSITIONCOUNT = 4;

	ABeanStructuralElementInstance elementDefinition;

	Concept conceptStateMachines;

	StateMachine stateMaschine;

	@Before
	public void setUp() throws Exception {

		UserRegistry.getInstance().setSuperUser(true);

		conceptStateMachines = ConceptXmiLoader.loadConceptFromPlugin(CONCEPT_ID_MACHINES + "/concept/concept.xmi");

		stateMaschine = new StateMachine(conceptStateMachines);

		State state1 = new State(conceptStateMachines);
		state1.setName("state1");
		state1.getTypeInstance().setUuid(new VirSatUuid("a899a13e-8f6e-434f-befe-6399240b1bfd"));
		State state2 = new State(conceptStateMachines);
		state2.setName("state2");
		state2.getTypeInstance().setUuid(new VirSatUuid("fbaceea2-9652-418e-9228-ee04cfcb4b62"));
		State state3 = new State(conceptStateMachines);
		state3.setName("state3");
		state3.getTypeInstance().setUuid(new VirSatUuid("a20f5060-647d-4eca-a8c3-9bc7d779fb60"));
		State state4 = new State(conceptStateMachines);
		state4.setName("state4");
		state4.getTypeInstance().setUuid(new VirSatUuid("6e94afae-acea-42c9-b912-c15bab44d5ff"));
		Transition transition1 = new Transition(conceptStateMachines);
		transition1.setName("transition1");
		transition1.getTypeInstance().setUuid(new VirSatUuid("44ba4f71-4a52-4245-a195-bd830aa0a23c"));
		transition1.setStateFrom(state1);
		transition1.setStateTo(state2);
		Transition transition2 = new Transition(conceptStateMachines);
		transition2.setName("transition2");
		transition2.getTypeInstance().setUuid(new VirSatUuid("45ba4f71-4a52-4245-a195-bd830aa0a23c"));
		transition2.setStateFrom(state2);
		transition2.setStateTo(state3);
		Transition transition3 = new Transition(conceptStateMachines);
		transition3.setName("transition3");
		transition3.getTypeInstance().setUuid(new VirSatUuid("46ba4f71-4a52-4245-a195-bd830aa0a23c"));
		transition3.setStateFrom(state3);
		transition3.setStateTo(state4);
		Transition transition4 = new Transition(conceptStateMachines);
		transition4.setName("transition4");
		transition4.getTypeInstance().setUuid(new VirSatUuid("47ba4f71-4a52-4245-a195-bd830aa0a23c"));
		transition4.setStateFrom(state4);
		transition4.setStateTo(state1);
		stateMaschine.getStates().add(state1);
		stateMaschine.getStates().add(state2);
		stateMaschine.getStates().add(state3);
		stateMaschine.getStates().add(state4);
		stateMaschine.getTransitions().add(transition1);
		stateMaschine.getTransitions().add(transition2);
		stateMaschine.getTransitions().add(transition3);
		stateMaschine.getTransitions().add(transition4);
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(ActiveConceptHelper.getCategory(conceptStateMachines, StateMachine.class.getSimpleName()).getApplicableFor().get(0));
		elementDefinition = new ElementDefinition(sei);
		elementDefinition.getStructuralElementInstance().setUuid(new VirSatUuid("74ccc93a-281b-4ab8-ace4-cb7f2b927d4b"));
		elementDefinition.setName("BATTERY");
		elementDefinition.add(stateMaschine);
	}

	@Test
	public void importExcelTest() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/StateMachineTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		SMImporter se = new SMImporter();
		se.importExcel(stateMaschine.getTypeInstance(), null, wb);
		assertEquals(EXPECTED_STATECOUNT, stateMaschine.getStates().size());
		assertEquals(EXPECTED_TRANSITIONCOUNT, stateMaschine.getTransitions().size());
		assertEquals("statenew2", stateMaschine.getStates().get(0).getName());
		assertEquals("transitionnew2", stateMaschine.getTransitions().get(0).getName());
		assertEquals("state4", stateMaschine.getTransitions().get(1).getStateFrom().getName());
		assertEquals("state3", stateMaschine.getTransitions().get(1).getStateTo().getName());
	}

	@Test
	public void importValidatortest() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/StateMachineIVTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		int headerSheetIndex = wb.getSheetIndex(AExcelStatIO.TEMPLATE_SHEETNAME_HEADER);
		int stateSheetIndex = wb.getSheetIndex(AExcelStatIO.TEMPLATE_SHEETNAME_STATES);

		ArrayList<Fault> expectedFault = new ArrayList<Fault>();
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, headerSheetIndex, AExcelStatIO.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH, headerSheetIndex, AExcelStatIO.COMMON_ROW_START_TABLE + 1));

		expectedFault.add(new Fault(StatFaultType.STATE_NAME_IS_NOT_SET, stateSheetIndex, AExcelStatIO.COMMON_ROW_START_TABLE + STATE_WITHOUT_NAME - 1));
		expectedFault.add(new Fault(StatFaultType.STATE_UUID_NOT_FOUND, stateSheetIndex, AExcelStatIO.COMMON_ROW_START_TABLE + STATE_WITHOUT_UUID - 1));
		expectedFault.add(new Fault(StatFaultType.CANT_DELETE_NON_EXISTING_STATE, stateSheetIndex, AExcelStatIO.COMMON_ROW_START_TABLE + STATE_NOT_EXISTING - 1));
		expectedFault.add(new Fault(StatFaultType.STATE_NAME_IS_NOT_SET, stateSheetIndex, AExcelStatIO.COMMON_ROW_START_TABLE + STATE_NOT_EXISTING - 1));

		ImportValidator iValidator = new ImportValidator(stateMaschine.getTypeInstance(), wb);

		ArrayList<Fault> fault = (ArrayList<Fault>) iValidator.validate();
		assertEquals(expectedFault, fault);
	}

	@Test
	public void withoutSheetTest() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/StateMachineTestWithoutSheets.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		assertEquals(wb.getNumberOfSheets(), 1);
		wb.close();

		iStream = Activator.getResourceContentAsString("/resources/StateMachineTestWithoutTransitionsSheet.xlsx");
		wb = new XSSFWorkbook(iStream);

		assertEquals(wb.getNumberOfSheets(), 2);
		wb.close();
	}
}
