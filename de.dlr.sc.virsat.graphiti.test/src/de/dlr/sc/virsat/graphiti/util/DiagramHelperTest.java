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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * This class tests the DiagramHelper
 * @author muel_s8
 *
 */

public class DiagramHelperTest extends AProjectTestCase {
	
	private static final String UUID = "ea816464-cea3-4db7-ae91-31d37c60a63c";
	
	private VirSatResourceSet resSet;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		resSet = VirSatResourceSet.getResourceSet(testProject, false);
		editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
	}
	
	@After
	public void tearDown() throws CoreException {
		super.tearDown();
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testHasDiagramWritePermission() {
		Diagram diagram = Graphiti.getPeCreateService().createDiagram("test", "testDiagram", true);
		IFolder diagramFolder = testProject.getFolder("data/ise_" + UUID + "/documents");  
		IFile diagramFile = diagramFolder.getFile("testDiagram" + "." + "test");  
		URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		DiagramHelper.createDiagram(uri, diagram, resSet);
		
		UserRegistry.getInstance().setSuperUser(false);
		
		// No owning SEI -> always have permission
		assertTrue(DiagramHelper.hasDiagramWritePermission(diagram));
		
		// Owning sei for which we have no rights -> no permissions
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setUuid(new VirSatUuid(UUID));
		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				resSet.getAndAddStructuralElementInstanceResource(sei);
			}
		});
		assertFalse(DiagramHelper.hasDiagramWritePermission(diagram));
		
		// Owning sei for which we have rights -> have permissions
		UserRegistry.getInstance().setSuperUser(true);
		assertTrue(DiagramHelper.hasDiagramWritePermission(diagram));
	}
}
