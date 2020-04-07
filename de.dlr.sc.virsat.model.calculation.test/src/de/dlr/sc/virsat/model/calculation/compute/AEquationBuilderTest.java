/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.compute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.Before;

import de.dlr.sc.virsat.model.calculation.test.util.ExpressionUtil;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Abstract test Class for validators, providing some Test Data + Resources
 * @author fisc_ph
 *
 */
public abstract class AEquationBuilderTest extends AEquationTest {

	protected CategoryAssignment caEdSc;
	protected Category catEd;
	
	protected StructuralElementInstance seiEdSc;
	protected StructuralElement seEd;

	protected VirSatResourceSet resSet;
	protected Equation result;
	protected IFile fileSc;
	
	protected Resource resSc;
	
	protected IWorkspaceRoot wsRoot;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		// Build concept for this test. will consist of Definitions and Configurations
		catEd = CategoriesFactory.eINSTANCE.createCategory();
		catEd.setIsApplicableForAll(true);
		catEd.setName("CatDefinition");
		
		seEd = StructuralFactory.eINSTANCE.createStructuralElement();
		seEd.setIsApplicableForAll(true);
		seEd.setName("SeDefinition");
		
		// Now construct the instances
		caEdSc = new CategoryInstantiator().generateInstance(catEd, "S");
		seiEdSc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEdSc.setType(seEd);
		seiEdSc.setName("Sei");
		seiEdSc.getCategoryAssignments().add(caEdSc);
		
		wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IWorkspaceDescription wsd = ResourcesPlugin.getWorkspace().getDescription();
		wsd.setAutoBuilding(false);
		ResourcesPlugin.getWorkspace().setDescription(wsd);
		wsRoot.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		
		project = wsRoot.getProject("equationBuilderTest");
		if (project.exists()) {
			project.delete(true, null);
		}
		
		project.create(null);
		project.open(null);
		
		contents.add(caEdSc);
		createResources();
		
		List<Equation> equations = ExpressionUtil.getAllEquationsFrom(esResourceSet, esResource, "Calc: x = 1");
		caEdSc.setEquationSection(CalculationFactory.eINSTANCE.createEquationSection());
		caEdSc.getEquationSection().getEquations().addAll(equations);
		
		resSet = VirSatResourceSet.createUnmanagedResourceSet(project);
		resSet.getResources().clear();
		
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(project);
		projectCommons.createProjectStructure(null);
		fileSc = projectCommons.getStructuralElementInstanceFile(seiEdSc);
		resSc = resSet.getAndAddStructuralElementInstanceResource(seiEdSc);
		resSc.getContents().add(caEdSc);
		
		result = caEdSc.getEquationSection().getEquations().get(0);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		esResourceSet.getResources().clear();
		resSet.getResources().clear();
		project.delete(true, null);
	}
	
	protected IProject project;
	
	/**
	 * Simple method to help cleaning all markers in the workspace and
	 * verify that they are gone before executing the next part of a test case
	 */
	protected void clearAllMarkers() {
		try {
			wsRoot.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			assertEquals("Check all markers are deleted", 0, wsRoot.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		} catch (CoreException e) {
			fail("Failed to delete all markers");
		}
	}
}
