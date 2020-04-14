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

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Test;

/**
 * This class tests the wizard for creating new projects.
 * @author muel_s8
 *
 */
public class NewProjectWizardTest extends ASwtBotTestCase {
	
	@Test
	public void testCreateNewProject() {
		SWTBotTree tree = bot.tree();
		
		tree.expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		tree.expandNode(SWTBOT_TEST_PROJECTNAME, "Role Management");
		tree.expandNode(SWTBOT_TEST_PROJECTNAME, "Unit Management");
	}
}
