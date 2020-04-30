/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomainTest;
import de.dlr.sc.virsat.project.editingDomain.VirSatWorkspaceCommandStackTest;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatClipboardCommandHelperTest;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatCopyToClipboardCommandTest;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatCutToClipboardCommandTest;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoardTest;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatPasteFromClipboardCommandTest;
import de.dlr.sc.virsat.project.editingDomain.commands.dnd.VirSatDragAndDropInheritanceCommandHelperTest;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelperTest;
import de.dlr.sc.virsat.project.resources.VirSatDanglingReferencesTest;
import de.dlr.sc.virsat.project.resources.VirSatProjectResourceTest;
import de.dlr.sc.virsat.project.resources.VirSatResourceSetTest;
import de.dlr.sc.virsat.project.resources.VirSatResourceSetUtilTest;
import de.dlr.sc.virsat.project.resources.VirSatResourceSetUtilTest2;
import de.dlr.sc.virsat.project.resources.command.AssignDisciplineCommandTest;
import de.dlr.sc.virsat.project.resources.command.CreateSeiResourceAndFileCommandTest;
import de.dlr.sc.virsat.project.resources.dmf.DmfResourceSaveCommandTest;
import de.dlr.sc.virsat.project.structure.VirSatProjectBuilderTest;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommonsTest;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommandTest;
import de.dlr.sc.virsat.project.structure.command.CreateRemoveSeiWithFileStructureCommandTest;
import de.dlr.sc.virsat.project.structure.command.RemoveFileStructureCommandTest;
import de.dlr.sc.virsat.project.structure.nature.VirSatProjectNatureTest;
import de.dlr.sc.virsat.project.structure.operation.NoUndoDeleteResourceOperationTest;
import junit.framework.JUnit4TestAdapter;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
				VirSatProjectCommonsTest.class,
				VirSatResourceSetTest.class,
				VirSatTransactionalEditingDomainTest.class,
				VirSatProjectResourceTest.class,
				VirSatProjectNatureTest.class,
				VirSatProjectBuilderTest.class,
				VirSatResourceSetUtilTest2.class,
				VirSatDanglingReferencesTest.class,
				VirSatResourceSetUtilTest.class,
				CreateSeiResourceAndFileCommandTest.class,
				VirSatWorkspaceCommandStackTest.class,
				VirSatClipboardCommandHelperTest.class,
				VirSatCopyToClipboardCommandTest.class,
				VirSatCutToClipboardCommandTest.class,
				VirSatPasteFromClipboardCommandTest.class,
				VirSatDragAndDropInheritanceCommandHelperTest.class,
				CreateAddSeiWithFileStructureCommandTest.class,
				CreateRemoveSeiWithFileStructureCommandTest.class,
				RemoveFileStructureCommandTest.class,
				NoUndoDeleteResourceOperationTest.class,
				AssignDisciplineCommandTest.class,
				VirSatProblemMarkerHelperTest.class,
				VirSatEditingDomainClipBoardTest.class,
				DmfResourceSaveCommandTest.class
				})

/**
 * 
 * Test Collection
 *
 */
public class AllTests {

	/**
	 * Constructor
	 */
	private AllTests() {
	}
	
	/**
	 * Test Adapter
	 * @return Executable JUnit Tests
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}