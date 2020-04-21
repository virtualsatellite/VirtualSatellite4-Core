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

import org.eclipse.core.resources.IProject;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.build.test.ABuilderTest;
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
	
	private TestSeiValidator testSeiValidator;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		testSeiValidator = new TestSeiValidator();
		seiValidators.clear();
		seiValidators.add(testSeiValidator);
	}

	@Test
	public void testFullBuild() {
		VirSatValidatorBuilder builder = new VirSatValidatorBuilder() {
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
		};
		
		assertEquals("tested Correct Amount of Seis", 0, testSeiValidator.seis.size());
		assertFalse("Not yet called", testSeiValidator.gotCalled);
		
		builder.fullBuild(null);
		
		//CHECKSTYLE:OFF
		assertEquals("tested Correct Amount of Seis", 3, testSeiValidator.seis.size());
		assertTrue("Got called", testSeiValidator.gotCalled);
		//CHECKSTYLE:On
		
		// Create a pending SEI and see if the detection of SEIs works as expected.
		seiEdSc.getChildren().remove(seiEdRw);
		resSet.saveAllResources(null);
		testSeiValidator.gotCalled = false;
		testSeiValidator.seis.clear();
		
		builder.fullBuild(null);
		
		assertEquals("tested Correct Amount of Seis", 2, testSeiValidator.seis.size());
		assertTrue("Not yet called", testSeiValidator.gotCalled);
		
	}

	@Test
	public void testIncrementalBuild() {
		VirSatValidatorBuilder builder = new VirSatValidatorBuilder() {
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
		};
		
		assertEquals("tested Correct Amount of Seis", 0, testSeiValidator.seis.size());
		assertFalse("Not yet called", testSeiValidator.gotCalled);
		
		builder.incrementalBuild(null, null);
		
		//CHECKSTYLE:OFF
		assertEquals("tested Correct Amount of Seis", 3, testSeiValidator.seis.size());
		assertTrue("Not yet called", testSeiValidator.gotCalled);
		//CHECKSTYLE:On
	}
}
