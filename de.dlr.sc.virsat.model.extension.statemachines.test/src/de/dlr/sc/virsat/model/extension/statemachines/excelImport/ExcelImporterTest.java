/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.statemachines.excelImport;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.excel.AExcelIo;
import de.dlr.sc.virsat.excel.Fault;
import de.dlr.sc.virsat.excel.FaultType;
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
 * Test Case for Importing from Excel
 * @author bell_er
 *
 */
public class ExcelImporterTest {
	private static final String CONCEPT_ID_MACHINES = "de.dlr.sc.virsat.model.extension.statemachines";

	private static final int FIVE = 5;
	private static final int FOUR = 4;
	private static final int EXPECTEDSTATECOUNT = 4;
	private static final int EXPECTEDTRANSITIONCOUNT = 4;
	
	ABeanStructuralElementInstance ed;

	Concept conceptStateMachines;

	StateMachine sm;
	
	@Before
	public void setUp() throws Exception {
		
		UserRegistry.getInstance().setSuperUser(true);

	    conceptStateMachines = ConceptXmiLoader.loadConceptFromPlugin(CONCEPT_ID_MACHINES + "/concept/concept.xmi");
  
	    sm = new StateMachine(conceptStateMachines);
		
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
		sm.getStates().add(state1);
		sm.getStates().add(state2);
		sm.getStates().add(state3);
		sm.getStates().add(state4);
		sm.getTransitions().add(transition1);
		sm.getTransitions().add(transition2);
		sm.getTransitions().add(transition3);
		sm.getTransitions().add(transition4);
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(ActiveConceptHelper.getCategory(conceptStateMachines, StateMachine.class.getSimpleName()).getApplicableFor().get(0));
		ed = new ElementDefinition(sei);
		ed.getStructuralElementInstance().setUuid(new VirSatUuid("74ccc93a-281b-4ab8-ace4-cb7f2b927d4b"));
		ed.setName("BATTERY"); 
		ed.add(sm);
	}
	
	@Test
	public void test() throws IOException  { 
		InputStream is = Activator.getResourceContentAsString("/resources/StateMachineTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(is);
					
		SMImporter se = new SMImporter();
		se.importExcel(sm.getTypeInstance(), null, wb);
		assertEquals(EXPECTEDSTATECOUNT, sm.getStates().size());
		assertEquals(EXPECTEDTRANSITIONCOUNT, sm.getTransitions().size());
		assertEquals("statenew2", sm.getStates().get(0).getName());
		assertEquals("transitionnew2", sm.getTransitions().get(0).getName());
		assertEquals("state4", sm.getTransitions().get(1).getStateFrom().getName());
		assertEquals("state3", sm.getTransitions().get(1).getStateTo().getName());
	}
	
	@Test
	public void importValidatortest() throws IOException  { 
		InputStream is = Activator.getResourceContentAsString("/resources/StateMachineIVTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(is);
		
		int headerSheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_HEADER);
		int stateSheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_STATES);
		
		ArrayList<Fault> expectedFault = new ArrayList<Fault>();
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, headerSheetIndex, AExcelIo.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH, headerSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + 1));

		expectedFault.add(new Fault(FaultType.STATE_NAME_IS_NOT_SET, stateSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + 2));
		expectedFault.add(new Fault(FaultType.STATE_UUID_NOT_FOUND, stateSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + FOUR));
		expectedFault.add(new Fault(FaultType.CANT_DELETE_NON_EXISTING_STATE, stateSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + FIVE));
		expectedFault.add(new Fault(FaultType.STATE_NAME_IS_NOT_SET, stateSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + FIVE));

		ImportValidator iv = new ImportValidator(sm.getTypeInstance(), wb);
		
		ArrayList<Fault> fault = (ArrayList<Fault>) iv.validate();
		assertEquals(expectedFault, fault);
	}
	
	@Test
	public void withoutSheetTest() throws IOException  { 
		InputStream is = Activator.getResourceContentAsString("/resources/StateMachineTestWithoutSheets.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(is);
		
		assertEquals(wb.getNumberOfSheets(), 1);
		wb.close();
		
		is = Activator.getResourceContentAsString("/resources/StateMachineTestWithoutTransitionsSheet.xlsx");
		wb = new XSSFWorkbook(is);	
			
		assertEquals(wb.getNumberOfSheets(), 2);
		wb.close();
	}
}
