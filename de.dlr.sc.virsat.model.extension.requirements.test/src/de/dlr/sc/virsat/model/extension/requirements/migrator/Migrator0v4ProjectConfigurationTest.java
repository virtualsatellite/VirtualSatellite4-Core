/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.migrator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.Match;

// *****************************************************************
// * Class Declaration
// *****************************************************************

import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.project.structure.nature.VirSatProjectNature;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * Extension for Requirement Specification
 * 
 */
public class Migrator0v4ProjectConfigurationTest extends AConceptProjectTestCase {		
	
	private Migrator0v4 migrator;
	private IProjectDescription desc;
	
	@Override
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		migrator = new Migrator0v4();
		
		// Configure VirSat project to be updated
		desc = testProject.getDescription();
		String[] natures = new String[1];
		natures[0] = VirSatProjectNature.NATURE_ID;
		desc.setNatureIds(natures);
		testProject.setDescription(desc, new NullProgressMonitor());
	}
	
	@Test
	public void testDataModelMigration() throws CoreException {
		Match match = CompareFactory.eINSTANCE.createMatch();
		Diff fakeDiff = CompareFactory.eINSTANCE.createAttributeChange();
		fakeDiff.setMatch(match);
		match.setRight(repository);
		List<Diff> diffs = new ArrayList<Diff>();
		diffs.add(fakeDiff);
		
		// Remove verification builder to check that it is enabled via migration
		IProjectDescription description = testProject.getDescription();
		ICommand[] commands = description.getBuildSpec();
		ICommand[] newCommands = new ICommand[commands.length - 1];
		System.arraycopy(commands, 0, newCommands, 0, commands.length - 1);
		description.setBuildSpec(newCommands);
		testProject.setDescription(description, new NullProgressMonitor());
		
		assertFalse("Builder is initally not acitvated", hasVerificationBuilderEnabled());
		migrator.dataModelMigration(diffs);
		assertTrue("Builder got added", hasVerificationBuilderEnabled());
	}
	
	/**
	 * Checks weather the project has an active verification builder
	 * @return if the builder is active
	 * @throws CoreException 
	 */
	protected boolean hasVerificationBuilderEnabled() throws CoreException {
		ICommand[] commands = testProject.getDescription().getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(VirSatProjectNature.BUILDER_VERIFICATION_ID)) {
				return true;
			} 
		}
		return false;
	}
	
}
