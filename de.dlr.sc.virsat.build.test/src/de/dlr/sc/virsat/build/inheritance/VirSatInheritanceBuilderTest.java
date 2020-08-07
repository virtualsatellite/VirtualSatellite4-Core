/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.inheritance;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Set;

import org.eclipse.core.internal.events.ResourceDelta;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.build.test.ABuilderTest;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * test Case for the Inheritance Builder
 */
@SuppressWarnings("restriction")
public class VirSatInheritanceBuilderTest extends ABuilderTest {

	/**
	 * Inheritance Copier Stub
	 *
	 */
	private static class TestInheritanceCopier implements IInheritanceCopier {

		boolean buildRepoGotCalled = false;
		boolean buildSeiGotCalled = false;
		
		Repository repo;
		StructuralElementInstance sei;
		int calls = 0;
		
		@Override
		public boolean needsUpdateInOrder(StructuralElementInstance subSei) {
			return true;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Set<CategoryAssignment> updateAllInOrder(Repository repo, IProgressMonitor monitor) {
			calls++;
			this.repo = repo;
			buildRepoGotCalled = true;
			return Collections.EMPTY_SET;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Set<CategoryAssignment> updateInOrderFrom(StructuralElementInstance subSei, Repository repo, IProgressMonitor monitor) {
			calls++;
			this.repo = repo;
			this.sei = subSei;
			buildSeiGotCalled = true;
			return Collections.EMPTY_SET;
		}
	}

	private TestInheritanceCopier tic;
	private TestVirSatInheritanceBuilder builder;
	
	class TestVirSatInheritanceBuilder extends VirSatInheritanceBuilder {
		@Override
		protected IProject getVirSatProject() {
			return project;
		}
		
		@Override
		protected VirSatResourceSet getResourceSet() {
			return resSet;
		}
		
		@Override
		protected IInheritanceCopier createInheritanceCopier() {
			return tic;
		}
		
		protected boolean saveGotTriggered = false;
		
		@Override
		protected void triggerSaveAfterIncrementalBuild() {
			saveGotTriggered = true;
			super.triggerSaveAfterIncrementalBuild();
		}
	}
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		tic = new TestInheritanceCopier();
		builder = new TestVirSatInheritanceBuilder();
	}
	
	@Test
	public void testFullBuild() {
		assertEquals("Correct Amount of Calls", 0, tic.calls);
		assertNull("No Repo yet Called", tic.repo);
		assertNull("No SEI yet Called", tic.sei);
		assertFalse("Not yet called", tic.buildSeiGotCalled);
		assertFalse("Not yet called", tic.buildRepoGotCalled);
		
		builder.fullBuild(null);
		
		//CHECKSTYLE:OFF
		assertEquals("Correct Amount of Calls", 1, tic.calls);
		assertEquals("Repo got Called", repo.getUuid(), tic.repo.getUuid());
		assertNull("No SEI yet Called", tic.sei);
		assertFalse("Not yet called", tic.buildSeiGotCalled);
		assertTrue("Got called", tic.buildRepoGotCalled);
		//CHECKSTYLE:On
	}

	@Test
	public void testIncrementalBuildOnDvlmFile() {
		assertEquals("Correct Amount of Calls", 0, tic.calls);
		assertNull("No Repo yet Called", tic.repo);
		assertNull("No SEI yet Called", tic.sei);
		assertFalse("Not yet called", tic.buildSeiGotCalled);
		assertFalse("Not yet called", tic.buildRepoGotCalled);
		
		IPath pathSeiSc = fileSc.getFullPath();
		IResourceDelta delta = new ResourceDelta(pathSeiSc, null) {
			@Override
			public int getKind() {
				return CHANGED;
			}
			
			@Override
			public IResource getResource() {
				return fileSc;
			}
			
			@Override
			public void accept(IResourceDeltaVisitor visitor, int memberFlags) throws CoreException {
				visitor.visit(this);
			}
		};
		
		builder.incrementalBuild(delta, null);
		
		assertTrue("Trigger save got called since dvlm files have been changed", builder.saveGotTriggered);
		assertEquals("Correct Amount of Calls", 1, tic.calls);
		assertEquals("Repo got Called", repo.getUuid(), tic.repo.getUuid());
		assertEquals("SEI got Called", seiEdSc.getUuid(), tic.sei.getUuid());
		assertFalse("Not yet called", tic.buildRepoGotCalled);
		assertTrue("Got called", tic.buildSeiGotCalled);
	}
	
	@Test
	public void testIncrementalBuildNonDvlmFile() throws CoreException {
		assertEquals("Correct Amount of Calls", 0, tic.calls);
		assertNull("No Repo yet Called", tic.repo);
		assertNull("No SEI yet Called", tic.sei);
		assertFalse("Not yet called", tic.buildSeiGotCalled);
		assertFalse("Not yet called", tic.buildRepoGotCalled);
		
		// CHECKSTYLE:OFF
		IFile noDvlmFile = project.getFile("NoDVLMFile.Test");
		noDvlmFile.create(new ByteArrayInputStream(new byte [20]), IResource.NONE, null);
		// CHECKSTYLE:ON

		IResourceDelta delta = new ResourceDelta(noDvlmFile.getFullPath(), null) {
			@Override
			public int getKind() {
				return CHANGED;
			}
			
			@Override
			public IResource getResource() {
				return noDvlmFile;
			}
			
			@Override
			public void accept(IResourceDeltaVisitor visitor, int memberFlags) throws CoreException {
				visitor.visit(this);
			}
		};
		
		builder.incrementalBuild(delta, null);
		
		assertFalse("Trigger save did not get called since no dvlm files have been changed", builder.saveGotTriggered);
		assertEquals("The TestInheritanceCopier did not get called", 0, tic.calls);
	}
}
