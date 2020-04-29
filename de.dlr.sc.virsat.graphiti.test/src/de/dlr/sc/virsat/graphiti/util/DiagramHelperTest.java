/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.concept.types.category.BeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * This class tests the DiagramHelper
 */
public class DiagramHelperTest extends AConceptProjectTestCase {
	
	private static final String UUID = "ea816464-cea3-4db7-ae91-31d37c60a63c";
	
	private VirSatResourceSet resSet;
	private Diagram diagram;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		resSet = VirSatResourceSet.getResourceSet(testProject, false);
		editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
		
		diagram = Graphiti.getPeCreateService().createDiagram("test", "testDiagram", true);
		IFolder diagramFolder = testProject.getFolder("data/ise_" + UUID + "/documents");  
		IFile diagramFile = diagramFolder.getFile("testDiagram" + "." + "test");  
		URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		DiagramHelper.createDiagram(uri, diagram, resSet);
	}
	
	@After
	public void tearDown() throws CoreException {
		super.tearDown();
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testHasDiagramWritePermission() {		
		UserRegistry.getInstance().setSuperUser(false);
		
		// No owning SEI -> always have permission
		assertTrue(DiagramHelper.hasDiagramWritePermission(diagram));
		
		// Owning sei for which we have no rights -> no permissions
		StructuralElementInstance owningSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		owningSei.setUuid(new VirSatUuid(UUID));
		
		executeAsCommand(() -> resSet.getAndAddStructuralElementInstanceResource(owningSei));
		assertFalse(DiagramHelper.hasDiagramWritePermission(diagram));
		
		// Owning sei for which we have rights -> have permissions
		UserRegistry.getInstance().setSuperUser(true);
		assertTrue(DiagramHelper.hasDiagramWritePermission(diagram));
	}
	
	@Test
	public void testGetOwningStructuralElementInstance() {		
		
		assertNull("There is no owning SEI attached yet", DiagramHelper.getOwningStructuralElementInstance(diagram));
		
		// Owning sei for which we have no rights -> no permissions
		StructuralElementInstance owningSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		owningSei.setUuid(new VirSatUuid(UUID));
		
		executeAsCommand(() -> resSet.getAndAddStructuralElementInstanceResource(owningSei));
		assertEquals("Resolved correct SEI via URI", owningSei, DiagramHelper.getOwningStructuralElementInstance(diagram));
		
		diagram.eResource().setURI(URI.createPlatformResourceURI("badURI", true));
		
		assertNull("There is no owning SEI found in case URIs don't match", DiagramHelper.getOwningStructuralElementInstance(diagram));
	}
	
	private static final String USER_NAME = "hans";
	private static final int VALIDITY_DAY = 365;
	
	@Test
	public void testHasBothWritePermission() {
		UserRegistry.getInstance().setSuperUser(false);
		UserRegistry.getInstance().setUser(USER_NAME, VALIDITY_DAY);
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		discipline.setUser(USER_NAME);
		
		StructuralElementInstance owningSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		owningSei.setUuid(new VirSatUuid(UUID));
		owningSei.setAssignedDiscipline(discipline);
		
		executeAsCommand(() -> resSet.getAndAddStructuralElementInstanceResource(owningSei));
		
		StructuralElementInstance businessObject = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		assertFalse("There is no write access for the businessObject", DiagramHelper.hasBothWritePermission(businessObject, diagram));
		
		businessObject.setAssignedDiscipline(discipline);
		
		assertTrue("There is access for the businessObject", DiagramHelper.hasBothWritePermission(businessObject, diagram));
		
		executeAsCommand(() -> owningSei.setAssignedDiscipline(null));
		
		assertFalse("There is no write access for the businessObject", DiagramHelper.hasBothWritePermission(businessObject, diagram));
		
		assertTrue("A random object always ahs write permission", DiagramHelper.hasBothWritePermission(new Object(), diagram));
	}
	
	@Test
	public void testGetEObject() {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		assertEquals(sei, DiagramHelper.getEObject(sei));
		
		BeanStructuralElementInstance beanSei = new BeanStructuralElementInstance(sei);
		assertEquals(sei, DiagramHelper.getEObject(beanSei));
		
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		BeanCategoryAssignment beanCa = new BeanCategoryAssignment();
		beanCa.setATypeInstance(ca);
		assertEquals(ca, DiagramHelper.getEObject(ca));
		assertEquals(ca, DiagramHelper.getEObject(beanCa));
		
		assertNull(DiagramHelper.getEObject("test"));
	}
}
