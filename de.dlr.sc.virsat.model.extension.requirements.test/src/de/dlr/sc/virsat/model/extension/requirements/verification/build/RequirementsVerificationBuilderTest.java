/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.verification.build;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.internal.events.ResourceDelta;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.build.test.ABuilderTest;
import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 *
 */
@SuppressWarnings("restriction")
public class RequirementsVerificationBuilderTest extends ABuilderTest {
	
	private static class TestVerificationStep implements IVerificationStep {

		Set<RequirementsSpecification> verifiedSpecs = new HashSet<RequirementsSpecification>();
		
		@Override
		public void execute(RequirementsSpecification specification, EditingDomain editingDomain,
				IProgressMonitor monitor) {
			verifiedSpecs.add(specification);
		}
		
	}
	
	private TestVerificationStep testVerificationStep = new TestVerificationStep();
	
	class TestRequirementsVerificationBuilder extends RequirementsVerificationBuilder {
		
		@Override
		protected void initVerificationSteps() {
			verificationSteps.add(testVerificationStep);
		}
		
		@Override
		protected IProject getVirSatProject() {
			return project;
		}
		
		@Override
		protected VirSatResourceSet getResourceSet() {
			return resSet;
		}
		
	}
	
	private TestRequirementsVerificationBuilder builder;
	private Concept requirementsConcept;
	private RequirementsSpecification specification1;
	private RequirementsSpecification specification2;
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		builder = new TestRequirementsVerificationBuilder();
		
		requirementsConcept = ConceptXmiLoader.loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.requirements/concept/concept.xmi");
		specification1 = new RequirementsSpecification(requirementsConcept);
		specification2 = new RequirementsSpecification(requirementsConcept);
	}
	
	@Test
	public void testFullBuild() {
		seiEdSc.getCategoryAssignments().add(specification1.getTypeInstance());
		seiEdRw.getCategoryAssignments().add(specification2.getTypeInstance());
		
		assertEquals(0, testVerificationStep.verifiedSpecs.size());
		builder.fullBuild(null);
		
		final int NUMBER_OF_SPECS = 2;
		assertEquals(NUMBER_OF_SPECS, testVerificationStep.verifiedSpecs.size());
		List<CategoryAssignment> specCAs =  testVerificationStep.verifiedSpecs.stream().
				map((specBean) -> specBean.getTypeInstance()).
				collect(Collectors.toList());
		assertTrue("Builder found correct spec in project's data model", specCAs.contains(specification1.getTypeInstance()));
		assertTrue("Builder found correct spec in project's data model", specCAs.contains(specification2.getTypeInstance()));

	}
	
	@Test
	public void testIncrementalBuild() {
		seiEdSc.getCategoryAssignments().add(specification1.getTypeInstance());
		seiEdRw.getCategoryAssignments().add(specification2.getTypeInstance());
		
		// Create change event for only first spec file
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
		
		assertEquals(0, testVerificationStep.verifiedSpecs.size());
		builder.incrementalBuild(delta, null);
		assertEquals("Only specification in changed file found", 1, testVerificationStep.verifiedSpecs.size());
		assertEquals("Builder found correct spec in project's changed model file", specification1.getTypeInstance(), 
				testVerificationStep.verifiedSpecs.iterator().next().getTypeInstance());
	}

}
