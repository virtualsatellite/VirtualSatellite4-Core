/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import de.dlr.sc.virsat.model.external.tests.Container;
import de.dlr.sc.virsat.model.external.tests.ExternalTestType;
import de.dlr.sc.virsat.model.external.tests.TestsFactory;
import de.dlr.sc.virsat.project.resources.command.CreateSeiResourceAndFileCommand;

public class VirSatTransactionalEditingDomainNonDVLMTest extends ATestConceptTestCase {
	
	private static final String EXTERNAL_RESOURCE_NAME = "test/ExternalModel.etests";
	private Resource externalModelResourceVirSatDomain;
	private Container containerVirsatContext;
	private IFile modelFile;
	
	
	@Before
	public void setUpModel() throws CoreException, IOException {
		super.setUp();
		
		// Setup VirSat project context to also respect external changes
		addEditingDomainAndRepository(true);
		executeAsCommand(() -> loadConceptAndInstallToRepository(CONCEPT_ID_CORE));
		executeAsCommand(() -> loadTestConcept());
		TestStructuralElement tsei = new TestStructuralElement(testConcept);
		StructuralElementInstance sei = tsei.getStructuralElementInstance();
		executeAsCommand(() -> repository.getRootEntities().add(sei));
		editingDomain.getCommandStack().execute(new CreateSeiResourceAndFileCommand(rs, sei));
		
		// Create external model resource
		modelFile = testProject.getFile(EXTERNAL_RESOURCE_NAME);
		externalModelResourceVirSatDomain = rs.safeGetResource(modelFile, true);
		
		// Create external model content
		containerVirsatContext = TestsFactory.eINSTANCE.createContainer();
		executeAsCommand(() -> externalModelResourceVirSatDomain.getContents().add(containerVirsatContext));
		externalModelResourceVirSatDomain.save(Collections.emptyMap());
	}
	
	@Test
	public void testSaveAll() throws IOException {
		// Do some external changes
		Container newExternalModelContainer = doExternalModelChange();
		
		// Save model from Virtual Satellite to check that its save does not overwrite the external change
		editingDomain.saveAll();
		assertNotNull("External change should not be overwritten", newExternalModelContainer.getObjects());
	}
	
	@Test
	public void testReloadModel() throws IOException, CoreException {
		// Do some external changes
		doExternalModelChange();
		editingDomain.saveAll();

		// Check reload of changes
		assertTrue("Oudated model element should be set to proxy state", containerVirsatContext.eIsProxy());
		Container reloadedContainer = (Container) externalModelResourceVirSatDomain.getContents().get(0);
		assertNotNull("Change should have been loaded into virsat model resource", reloadedContainer.getObjects());
		
	}
	
	@Test
	public void testRemoveResource() throws CoreException {
		
		assertNotNull("Resource should be in VirSat resource set", rs.getResource(modelFile, false));
		
		// Delete external model resource from file system
		modelFile.delete(true, null);
		assertNull("Resource should have been deleted from VirSat resource set", rs.getResource(modelFile, false));
		
	}
	
	/**
	 * Do some model change in external domain
	 * @throws IOException
	 */
	private Container doExternalModelChange() throws IOException {
		
		TransactionalEditingDomain externalEditingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
		ResourceSet externalResourceSet = externalEditingDomain.getResourceSet();
		Resource externalResourceHandle = externalResourceSet.getResource(externalModelResourceVirSatDomain.getURI(), true);
		Container newContainerHandle = (Container) externalResourceHandle.getContents().get(0);
		ExternalTestType type = TestsFactory.eINSTANCE.createExternalTestType();
		externalEditingDomain.getCommandStack().execute(new RecordingCommand(externalEditingDomain) {
			@Override
			protected void doExecute() {
				newContainerHandle.setObjects(type);
			}
		});
		
		assertNull("Change should be done in external editing domain", containerVirsatContext.getObjects());
		externalResourceHandle.save(Collections.emptyMap());
		
		return newContainerHandle;
	}

}
