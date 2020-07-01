/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.inheritance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.build.test.ABuilderTest;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

public class VirSatTransactionalBuilderTest extends ABuilderTest {
	
	private AVirSatTransactionalBuilder builder;
	private boolean transactionalIncrementalBuildCalled = false;
	private boolean transactionalFullBuildCalled = false;
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		builder = new AVirSatTransactionalBuilder("MockBuilder", null, false, false) {
			@Override
			protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) { }
			@Override
			protected void fullBuild(IProgressMonitor monitor) { }
			
			@Override
			protected void transactionalIncrementalBuild(IResourceDelta delta, IProgressMonitor monitor) {
				transactionalIncrementalBuildCalled = true;
			}
			
			@Override
			protected void transactionalFullBuild(IProgressMonitor monitor) {
				transactionalFullBuildCalled = true;
			}
			
			@Override
			public IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) {
				// Overwrite to increase visibility of method for testing
				return super.build(kind, args, monitor);
			}
			
			@Override
			protected IProject getVirSatProject() {
				return project;
			}
			
			@Override
			protected VirSatResourceSet getResourceSet() {
				return resSet;
			}
		};
	}
	
	@Test
	public void testBuild() {		
		assertFalse("Full Build was not called", transactionalFullBuildCalled);
		assertFalse("Transactional Build was not called", transactionalIncrementalBuildCalled);
		
		builder.build(-1, Collections.emptyMap(), new NullProgressMonitor());
		
		assertFalse("Full Build was not called", transactionalFullBuildCalled);
		assertFalse("Transactional Build was not called", transactionalIncrementalBuildCalled);
		
		builder.build(VirSatInheritanceBuilder.FULL_BUILD, Collections.emptyMap(), new NullProgressMonitor());
		
		assertTrue("Full Build was called", transactionalFullBuildCalled);
		assertFalse("Transactional Build was not called", transactionalIncrementalBuildCalled);
		
		transactionalFullBuildCalled = false;
		builder.build(VirSatInheritanceBuilder.INCREMENTAL_BUILD, Collections.emptyMap(), new NullProgressMonitor());
		
		assertFalse("Full Build was not called", transactionalFullBuildCalled);
		assertTrue("Transactional Build was called", transactionalIncrementalBuildCalled);
		
		transactionalIncrementalBuildCalled = false;
		builder.build(VirSatInheritanceBuilder.AUTO_BUILD, Collections.emptyMap(), new NullProgressMonitor());
		
		assertTrue("Full Build was called", transactionalFullBuildCalled);
		assertFalse("Transactional Build was not called", transactionalIncrementalBuildCalled);
	}
}
