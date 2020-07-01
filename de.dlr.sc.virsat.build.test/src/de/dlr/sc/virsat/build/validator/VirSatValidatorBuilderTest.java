/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.validator;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.build.test.ABuilderTest;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.validator.IRepositoryValidator;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * test Case for the Validator Builder
 */
public class VirSatValidatorBuilderTest extends ABuilderTest {

	List<IStructuralElementInstanceValidator> seiValidators = new LinkedList<>();

	/**
	 * test Validator to be injected in the validation builder
	 * will remember how it got called 
	 */
	private static class TestSeiValidator implements IStructuralElementInstanceValidator {

		private List<StructuralElementInstance> seis = new ArrayList<>();
		private boolean gotCalled = false;
		
		@Override
		public boolean validate(StructuralElementInstance sei) {
			seis.add(sei);
			gotCalled = true;
			return true;
		}
	}
	
	private class TestVirSatValidatorBuilder extends VirSatValidatorBuilder {
		@Override
		protected IProject getVirSatProject() {
			return project;
		}
		
		@Override
		protected VirSatResourceSet getResourceSet() {
			return resSet;
		}
		
		@Override
		protected List<IStructuralElementInstanceValidator> getSeiValidators() {
			return seiValidators;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		protected List<IRepositoryValidator> getRepoValidators() {
			return Collections.EMPTY_LIST;
		}
		
		@Override
		public IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) {
			// Declare with increased visibility give access to this method in the test case
			return super.build(kind, args, monitor);
		}
	};
	
	private TestSeiValidator testSeiValidator;
	private TestVirSatValidatorBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		testSeiValidator = new TestSeiValidator();
		seiValidators.clear();
		seiValidators.add(testSeiValidator);
		builder = new TestVirSatValidatorBuilder();
	}

	@Test
	public void testFullBuild() {
		assertEquals("tested Correct Amount of Seis", 0, testSeiValidator.seis.size());
		assertFalse("Not yet called", testSeiValidator.gotCalled);
		
		builder.fullBuild(null);
		
		//CHECKSTYLE:OFF
		assertEquals("tested Correct Amount of Seis", 3, testSeiValidator.seis.size());
		assertTrue("Got called", testSeiValidator.gotCalled);
		//CHECKSTYLE:On
		
		// Create a pending SEI and see if the detection of SEIs works as expected.
		seiEdSc.getChildren().remove(seiEdRw);
		resSet.saveAllResources(null, UserRegistry.getInstance());
		testSeiValidator.gotCalled = false;
		testSeiValidator.seis.clear();
		
		builder.fullBuild(null);
		
		assertEquals("tested Correct Amount of Seis", 2, testSeiValidator.seis.size());
		assertTrue("Got called", testSeiValidator.gotCalled);
		
	}

	@Test
	public void testIncrementalBuild() {
		assertEquals("tested Correct Amount of Seis", 0, testSeiValidator.seis.size());
		assertFalse("Not yet called", testSeiValidator.gotCalled);
		
		builder.incrementalBuild(null, null);
		
		//CHECKSTYLE:OFF
		assertEquals("tested Correct Amount of Seis", 3, testSeiValidator.seis.size());
		assertTrue("Got called", testSeiValidator.gotCalled);
		//CHECKSTYLE:On
	}
	
	@Test
	public void testAutoBuild() {
		assertEquals("tested Correct Amount of Seis", 0, testSeiValidator.seis.size());
		assertFalse("Not yet called", testSeiValidator.gotCalled);
		
		// Create a pending SEI and see if the detection of SEIs works as expected.
		seiEdSc.getChildren().remove(seiEdRw);
		resSet.saveAllResources(null, UserRegistry.getInstance());
		
		builder.build(VirSatValidatorBuilder.AUTO_BUILD, Collections.emptyMap(), new NullProgressMonitor());
		
		//CHECKSTYLE:OFF
		assertEquals("tested Correct Amount of Seis", 2, testSeiValidator.seis.size());
		assertTrue("Got called", testSeiValidator.gotCalled);
		//CHECKSTYLE:On
	}
	
	@Test
	public void testUnknownBuild() {
		assertEquals("tested Correct Amount of Seis", 0, testSeiValidator.seis.size());
		assertFalse("Not yet called", testSeiValidator.gotCalled);
		
		builder.build(-1, Collections.emptyMap(), new NullProgressMonitor());
		
		assertTrue("No Sei got validated", testSeiValidator.seis.isEmpty());
		assertFalse("No Validator got called", testSeiValidator.gotCalled);
	}
	
	@Test
	public void testClosedProjectBuild() throws CoreException {
		assertEquals("tested Correct Amount of Seis", 0, testSeiValidator.seis.size());
		assertFalse("Not yet called", testSeiValidator.gotCalled);
		
		project.close(new NullProgressMonitor());
		builder.build(VirSatValidatorBuilder.FULL_BUILD, Collections.emptyMap(), new NullProgressMonitor());
		
		assertTrue("No Sei got validated", testSeiValidator.seis.isEmpty());
		assertFalse("No Validator got called", testSeiValidator.gotCalled);
	}
}
