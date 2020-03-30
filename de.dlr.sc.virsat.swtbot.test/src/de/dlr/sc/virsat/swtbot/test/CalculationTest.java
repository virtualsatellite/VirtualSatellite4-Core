/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.swtbot.test;

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.swt.SWT;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeDomain;
import de.dlr.sc.virsat.swtbot.util.SwtBotDebugHelper;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassEquipment;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassSummary;

/**
 * This class tests the calculation mass equipment etc
 * @author bell_Er
 *
 */
public class CalculationTest extends ASwtBotTestCase {
	
	private static final int THREE = 3;
	private SWTBotTreeItem repositoryNavigatorItem;

	private SWTBotTreeItem productTree;
	private SWTBotTreeItem productTreeDomain;
	
	private SWTBotTreeItem massEquipment1;
	private SWTBotTreeItem massEquipment2;
	private SWTBotTreeItem massSummary;
	
	private Concept conceptMass;
	
	@Before
	public void before() throws Exception {
		super.before();
		// create the necessary items for the test
		conceptMass = ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.budget.mass.Activator.getPluginId() + "/concept/concept.xmi");	
		repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		productTree = addElement(ProductTree.class, conceptPs, repositoryNavigatorItem);
		massEquipment1 = addElement(MassEquipment.class, conceptMass, productTree);
		openEditor(massEquipment1);
	}
	
	@Test
	public void calculationsForMassEquipmentTest() {
		// test margin calculation
		SwtBotDebugHelper.logCodeLine();
		renameField(MassEquipment.PROPERTY_MASS, "45");
		
		SwtBotDebugHelper.logCodeLine();
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		
		SwtBotDebugHelper.logCodeLine();
		assertText("54.0", bot.textWithLabel(MassEquipment.PROPERTY_MASSWITHMARGIN));
		// add another mass equipment

		SwtBotDebugHelper.logCodeLine();
		productTreeDomain = addElement(ProductTreeDomain.class, conceptPs, productTree);	

		SwtBotDebugHelper.logCodeLine();
		massEquipment2 = addElement(MassEquipment.class, conceptMass, productTreeDomain);

		SwtBotDebugHelper.logCodeLine();
		openEditor(massEquipment2);

		SwtBotDebugHelper.logCodeLine();
		renameField(MassEquipment.PROPERTY_MASS, "55");

		SwtBotDebugHelper.logCodeLine();
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		 
		// add massSummary and test calculation
		SwtBotDebugHelper.logCodeLine();
		massSummary = addElement(MassSummary.class, conceptMass, productTree);

		SwtBotDebugHelper.logCodeLine();
		openEditor(massSummary);

		SwtBotDebugHelper.logCodeLine();
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		
		SwtBotDebugHelper.logCodeLine();
		assertText("100.0", bot.textWithLabel(MassEquipment.PROPERTY_MASS));
 
		SwtBotDebugHelper.logCodeLine();
		assertText("120.0", bot.textWithLabel(MassEquipment.PROPERTY_MASSWITHMARGIN));

		SwtBotDebugHelper.logCodeLine();
	}
	
	@Test
	public void addRemoveEquationTest() {

		SwtBotDebugHelper.logCodeLine();
		bot.button("Add Equation").click();

		SwtBotDebugHelper.logCodeLine();
		SWTBotTable allPropertyTable = getSWTBotTable(massEquipment1, "Table Section for: Equations");

		SwtBotDebugHelper.logCodeLine();
		assertEquals(THREE, allPropertyTable.rowCount());

		SwtBotDebugHelper.logCodeLine();
		allPropertyTable.click(0, 0);

		SwtBotDebugHelper.logCodeLine();
		bot.button("Remove Equation").click();

		SwtBotDebugHelper.logCodeLine();
		assertEquals(2, allPropertyTable.rowCount());	

		SwtBotDebugHelper.logCodeLine();
	}
	
	@Test
	public void calculatedFieldsAreReadOnlyTest() {
		assertTrue(SWTUtils.hasStyle(bot.textWithLabel(MassEquipment.PROPERTY_MASSWITHMARGIN).widget, SWT.READ_ONLY));
	}
}
