/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure.command;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.junit.Test;


import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMPropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.provider.DVLMQudvItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * This class tests the CreateAddSeiWithFileStructureCommand class
 * @author muel_s8
 *
 */
public class CreateAddSeiWithFileStructureCommandTest extends AProjectTestCase {

	private StructuralElementInstance sei1;
	private StructuralElementInstance sei2;
	
	@Override
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsApplicableForAll(true);
		se.setIsRootStructuralElement(true);
		
		sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		sei1.setType(se);
		sei2.setType(se);
		
		Command cmd = rs.initializeModelsAndResourceSet(null, editingDomain);
		editingDomain.getCommandStack().execute(cmd);
		editingDomain.saveAll();
		
		repository = rs.getRepository();
	}
	
	@Override
	public void tearDown() throws CoreException {
		super.tearDown();
		UserRegistry.getInstance().setSuperUser(false);
		
		VirSatResourceSet.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();
	}

//	@Test
//	/**
//	 * The purpose of this test case is to see how surefire reacts when a test case gets stuck
//	 */
//	public void testStalledTest() {
//		int i = 2;
//		
//		while (i < 3) {
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	
	@Test
	public void testCreateAndAddSeiInRepo() {
		Command cmd = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, sei1);
		editingDomain.getCommandStack().execute(cmd);
		
		IFile file = projectCommons.getStructuralElementInstanceFile(sei1);
		IFolder folder = projectCommons.getStructuralElemntInstanceFolder(sei1);
		
		// Check that the necessary files have been created
		assertTrue("File got correctly created", file.exists());
		assertTrue("Folder got correctly creatred", folder.exists());
		
		// Make sure the resource contained in the resource set is the newly created one
		Resource resourceO = rs.getStructuralElementInstanceResource(sei1);
		assertThat("Contains correct resource", rs.getResources(), hasItems(resourceO));
		
		// Check that the sei got added to the repository
		assertThat("Structural element instance is correctly added to repository", repository.getRootEntities(), hasItems(sei1));
	}
	
	@Test
	public void testCreateAndAddSeiInSei() {
		// Create file structure and everything for parent sei
		Command cmd = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, sei1);
		editingDomain.getCommandStack().execute(cmd);
		
		editingDomain.saveAll();
		
		// Create the actual command to test
		cmd = CreateAddSeiWithFileStructureCommand.create(editingDomain, sei1, sei2);
		editingDomain.getCommandStack().execute(cmd);
		
		IFile file = projectCommons.getStructuralElementInstanceFile(sei1);
		IFolder folder = projectCommons.getStructuralElemntInstanceFolder(sei1);
		
		// Check that the necessary files have been created
		assertTrue("File got correctly created", file.exists());
		assertTrue("Folder got correctly creatred", folder.exists());
		
		// Make sure the resource contained in the resource set is the newly created one
		Resource resourceO = rs.getStructuralElementInstanceResource(sei1);
		assertThat("Contains correct resource", rs.getResources(), hasItems(resourceO));
		
		// Check that the sei got added to the repository
		assertThat("Structural element instance is correctly added to parent structural element instance", sei1.getChildren(), hasItems(sei2));
	}
	
	@Test
	public void testCreateAndAddSeiWithStandardEd() {
		// prepare a standard Editing Domain to test the command with non Virtual Satellite context.
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new DVLMResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMQudvItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMPropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		EditingDomain ed = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack());
		
		// Add a SEI to the Repository but don't create the file structures here
		Command cmd = CreateAddSeiWithFileStructureCommand.create(ed, repository, sei1);
		ed.getCommandStack().execute(cmd);
		
		// Add a second SEI to the first one
		cmd = CreateAddSeiWithFileStructureCommand.create(ed, sei1, sei2);
		ed.getCommandStack().execute(cmd);

		// Check that the files were not created
		// This command actually creates the folder structure, but not the file.
		IFile file1 = projectCommons.getStructuralElementInstanceFile(sei1);
		IFile file2 = projectCommons.getStructuralElementInstanceFile(sei2);
		assertFalse("File were not created", file1.exists());
		assertFalse("File were not created", file2.exists());

		// Check that the SEI got added to the other SEI and Repo
		assertThat("SEI is correctly added to parent Repo", repository.getRootEntities(), hasItems(sei1));
		assertThat("SEI is correctly added to parent SEI", sei1.getChildren(), hasItems(sei2));
	}
	
	@Test
	public void testCreateAndAddSeis() {
		Set<StructuralElementInstance> seis = new HashSet<>();
		seis.add(sei1);
		seis.add(sei2);
		
		Command cmd = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, seis);
		editingDomain.getCommandStack().execute(cmd);
		
		IFile fileSei1 = projectCommons.getStructuralElementInstanceFile(sei1);
		IFolder folderSei1 = projectCommons.getStructuralElemntInstanceFolder(sei1);
		
		IFile fileSei2 = projectCommons.getStructuralElementInstanceFile(sei2);
		IFolder folderSei2 = projectCommons.getStructuralElemntInstanceFolder(sei2);
		
		// Check that the necessary files have been created
		assertTrue("File got correctly created", fileSei1.exists());
		assertTrue("Folder got correctly creatred", folderSei1.exists());
		assertTrue("File got correctly created", fileSei2.exists());
		assertTrue("Folder got correctly creatred", folderSei2.exists());
		
		// Make sure the resource contained in the resource set is the newly created one
		Resource resource1 = rs.getStructuralElementInstanceResource(sei1);
		Resource resource2 = rs.getStructuralElementInstanceResource(sei2);
		assertThat("Contains correct resource", rs.getResources(), hasItems(resource1, resource2));
		
		// Check that the seis got added to the repository
		assertThat("Structural element instance is correctly added to repository", repository.getRootEntities(), hasItems(sei1, sei2));
	}
	
	@Test
	public void testCreateAndAddSeiWithChild() {
		sei1.getChildren().add(sei2);
		
		Command cmd = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, sei1);
		editingDomain.getCommandStack().execute(cmd);
		
		IFile fileSei1 = projectCommons.getStructuralElementInstanceFile(sei1);
		IFolder folderSei1 = projectCommons.getStructuralElemntInstanceFolder(sei1);
		
		IFile fileSei2 = projectCommons.getStructuralElementInstanceFile(sei2);
		IFolder folderSei2 = projectCommons.getStructuralElemntInstanceFolder(sei2);
		
		// Check that the necessary files have been created
		assertTrue("File got correctly created", fileSei1.exists());
		assertTrue("Folder got correctly creatred", folderSei1.exists());
		assertTrue("File got correctly created", fileSei2.exists());
		assertTrue("Folder got correctly creatred", folderSei2.exists());
		
		// Make sure the resource contained in the resource set is the newly created one
		Resource resource1 = rs.getStructuralElementInstanceResource(sei1);
		Resource resource2 = rs.getStructuralElementInstanceResource(sei2);
		assertThat("Contains correct resource", rs.getResources(), hasItems(resource1, resource2));
		
		// Check that the seis got added to the repository
		assertThat("Structural element instance is correctly added to repository", repository.getRootEntities(), hasItems(sei1));
		assertThat("Child Structural element instance is correctly is still a child", sei1.getChildren(), hasItems(sei2));
	}

}
