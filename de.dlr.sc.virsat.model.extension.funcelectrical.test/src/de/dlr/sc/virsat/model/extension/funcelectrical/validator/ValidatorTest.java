/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.validator;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.PropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.extension.funcelectrical.marker.VirSatFuncelectricalMarkerHelper;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Test Case for Validator of Interfaces and InterfaceEnds
 * 
 * @author lobe_el
 *
 */

public class ValidatorTest {

	private static final String CONCEPT_ID_PS = de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId();
	private static final String CONCEPT_ID_FUNCELECTRICAL = "de.dlr.sc.virsat.model.extension.funcelectrical";

	IProject project;
	VirSatResourceSet resSet;
	Repository repository;

	Concept conceptPs;
	Concept conceptFea;

	ConfigurationTree ct;
	ElementConfiguration ecObc;
	ElementConfiguration ecRw;
	ElementConfiguration ecHns;
	
	InterfaceTypeCollection itc;
	InterfaceType it1;
	InterfaceType it2;

	InterfaceEnd ifeObc;
	InterfaceEnd ifeRw;
	InterfaceEnd ifeBad;
	Interface ifHns;

	
	private StructuralElement seEc;
	private Category catIf;

	@Before
	public void setUp() throws CoreException, IOException {
		UserRegistry.getInstance().setSuperUser(true);
		
		// Create an Editing Domain
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new DVLMResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		EditingDomain ed = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack());

		// Now get the workspace and create a new Project. Deactivate the auto-building to no t let
		// the eclipse platform place markers to our resources
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IWorkspaceDescription wsd = ResourcesPlugin.getWorkspace().getDescription();
		wsd.setAutoBuilding(false);
		ResourcesPlugin.getWorkspace().setDescription(wsd);
		wsRoot.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		project = wsRoot.getProject("coreFeaTests");
		if (project.exists()) {
			project.delete(true, null);
		}
		project.create(null);
		project.open(null);

		// Now create a repository object and attach it to a resource
		// use the VirSatProjectCommons to follow our directory structure etc.
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(project);

		repository = DVLMFactory.eINSTANCE.createRepository();
		resSet = VirSatResourceSet.createUnmanagedResourceSet(project);
		resSet.getResources().clear();
		resSet.getRepositoryResource().getContents().add(repository);

		//CHECKSTYLE:OFF
		ActiveConceptConfigurationElement accePs = new ActiveConceptConfigurationElement(null) {
			public String getXmi() { return "concept/concept.xmi"; };
			public String getId() { return CONCEPT_ID_PS; };
		};

		ActiveConceptConfigurationElement acceFea = new ActiveConceptConfigurationElement(null) {
			public String getXmi() { return "concept/concept.xmi"; };
			public String getId() { return CONCEPT_ID_FUNCELECTRICAL; };
		};
		//CHECKSTYLE:ON
		
		// Now load the PS and IF concept into the repository
		// we need the full set of repository loaded concepts etc to provide
		// correctly set up workspace resources for setting and detecting the markers
		accePs.createAddActiveConceptCommand(ed, repository).execute();
		acceFea.createAddActiveConceptCommand(ed, repository).execute();

		ActiveConceptHelper acHelper = new ActiveConceptHelper(repository);
		conceptPs = acHelper.getConcept(CONCEPT_ID_PS);
		conceptFea = acHelper.getConcept(CONCEPT_ID_FUNCELECTRICAL);

		// Check that concepts are correctly loaded
		// We used to have badly connected concepts, due to the persistence of resources
		// on the second run of the test case the repository resource was already containing
		// a resource and the test setup was adding a second one. We now changed the code, that
		// the whole project in the workspace gets deleted first and then recreated.
		//CHECKSTYLE:OFF
		seEc = ActiveConceptHelper.getStructuralElement(conceptPs, ElementConfiguration.class.getSimpleName());
		catIf = ActiveConceptHelper.getCategory(conceptFea, Interface.class.getSimpleName());
		assertThat("Concepts correctly connected", catIf.getApplicableFor(), hasItem(seEc));
		//CHECKSTYLE:ON
		
		// Here we start to create the Test Model
		it1 = new InterfaceType(conceptFea);
		it1.setName("IFT_MILBUS");
		it2 = new InterfaceType(conceptFea);
		it2.setName("IFT_CANBUS");

		itc = new InterfaceTypeCollection(conceptFea);
		itc.setName("INTERFACE_TYPE_COLLECTION");
		itc.add(it1);
		itc.add(it2);

		ifeObc = new InterfaceEnd(conceptFea);
		ifeObc.setName("IFE_OBC");
		ifeObc.setType(it1);
		
		ecObc = new ElementConfiguration(conceptPs);
		ecObc.setName("EC_OBC");
		ecObc.add(ifeObc);
		
		ifeRw = new InterfaceEnd(conceptFea);
		ifeRw.setName("IFE_RW");
		ifeRw.setType(it1);
		
		ecRw = new ElementConfiguration(conceptPs);
		ecRw.setName("EC_RW");
		ecRw.add(ifeRw);
		
		ifeBad = new InterfaceEnd(conceptFea);
		ifeBad.setName("IFE_WITHOUT_TYPE");
		
		ifHns = new Interface(conceptFea);
		ifHns.setName("IF_HNS");
		
		ecHns = new ElementConfiguration(conceptPs);
		ecHns.setName("EC_HARNESS");
		
		ct = new ConfigurationTree(conceptPs);
		ct.setName("CONF_TREE");
		ct.add(ecObc);
		ct.add(ecRw);
		ct.add(ecHns);
		
		// And finally we use the project commons to create the correct workspace paths
		// for the individual resources of the SEIs. We still have to attach the SEI eObjects
		// to their EMF Resources and save them
		fileRepo = projectCommons.getRepositoryFile();
		fileItc = projectCommons.getStructuralElementInstanceFile(itc.getStructuralElementInstance());
		fileCt = projectCommons.getStructuralElementInstanceFile(ct.getStructuralElementInstance());
		fileObc = projectCommons.getStructuralElementInstanceFile(ecObc.getStructuralElementInstance());
		fileRw = projectCommons.getStructuralElementInstanceFile(ecRw.getStructuralElementInstance());
		fileHns = projectCommons.getStructuralElementInstanceFile(ecHns.getStructuralElementInstance());
		
		Resource resRepo = resSet.getRepositoryResource();
		Resource resItc = resSet.getStructuralElementInstanceResource(itc.getStructuralElementInstance());
		Resource resCt = resSet.getStructuralElementInstanceResource(ct.getStructuralElementInstance());
		Resource resObc = resSet.getStructuralElementInstanceResource(ecObc.getStructuralElementInstance());
		Resource resRw = resSet.getStructuralElementInstanceResource(ecRw.getStructuralElementInstance());
		Resource resHns = resSet.getStructuralElementInstanceResource(ecHns.getStructuralElementInstance());
		
		resItc.getContents().add(itc.getStructuralElementInstance());
		resCt.getContents().add(ct.getStructuralElementInstance());
		resObc.getContents().add(ecObc.getStructuralElementInstance());
		resRw.getContents().add(ecRw.getStructuralElementInstance());
		resHns.getContents().add(ecHns.getStructuralElementInstance());
		
		resRepo.save(Collections.EMPTY_MAP);
		resItc.save(Collections.EMPTY_MAP);
		resCt.save(Collections.EMPTY_MAP);
		resObc.save(Collections.EMPTY_MAP);
		resRw.save(Collections.EMPTY_MAP);
		resHns.save(Collections.EMPTY_MAP);
	}
	
	@After
	public void tearDown() {
		UserRegistry.getInstance().setSuperUser(false);
	}

	private IResource fileRepo;
	private IResource fileItc;
	private IResource fileCt;
	private IResource fileObc;
	private IResource fileRw;
	private IResource fileHns;
	
	@Test
	public void testInterfaceEndTypeNotSet() throws Exception {

		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		StructuralElementInstanceValidator seiValidator = new StructuralElementInstanceValidator();

		assertTrue("validator brings no error", seiValidator.validate(ecObc.getStructuralElementInstance()));
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		ecObc.add(ifeBad);
		
		assertFalse("validator brings no error", seiValidator.validate(ecObc.getStructuralElementInstance()));

		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There is one marker now", 1,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

	}
	
	@Test
	public void testInterfaceEndsNotSetForInterface() throws Exception {

		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		StructuralElementInstanceValidator seiValidator = new StructuralElementInstanceValidator();

		assertTrue("validator brings no error", seiValidator.validate(ecHns.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecObc.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecRw.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ct.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(itc.getStructuralElementInstance()));
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		assertSame("Concepts correctly connected", ecHns.getStructuralElementInstance().getType(), seEc);
		assertSame("Concepts correctly connected", ifHns.getTypeInstance().getType(), catIf);
		
		ecHns.add(ifHns);
		
		assertFalse("validator brings no error", seiValidator.validate(ecHns.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecObc.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecRw.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ct.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(itc.getStructuralElementInstance()));
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are markers now", 2,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		fileHns.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		ifHns.setInterfaceEndFrom(ifeObc);
		
		assertFalse("validator brings no error", seiValidator.validate(ecHns.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecObc.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecRw.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ct.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(itc.getStructuralElementInstance()));
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There is still a marker", 1,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		VirSatFuncelectricalMarkerHelper vfmHelper = new VirSatFuncelectricalMarkerHelper();
		IMarker marker = fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE)[0];
		assertTrue("Marker is correct", vfmHelper.isAssociatedWith(marker, ifHns.getIfeToReferenceProperty()));
		
		fileHns.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		ifHns.setInterfaceEndTo(ifeRw);
		
		assertTrue("validator brings no error", seiValidator.validate(ecHns.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecObc.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecRw.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ct.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(itc.getStructuralElementInstance()));
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There is no marker anymore", 0, fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
	}
	
	@Test
	public void testInterfaceEndsHaveDifferentTypes() throws Exception {
		
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		StructuralElementInstanceValidator seiValidator = new StructuralElementInstanceValidator();

		assertTrue("validator brings no error", seiValidator.validate(ecHns.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecObc.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecRw.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ct.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(itc.getStructuralElementInstance()));
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		assertSame("Concepts correctly connected", ecHns.getStructuralElementInstance().getType(), seEc);
		assertSame("Concepts correctly connected", ifHns.getTypeInstance().getType(), catIf);
		
		ifHns.setInterfaceEndFrom(ifeObc);
		ifeRw.setType(it2);
		ifHns.setInterfaceEndTo(ifeRw);
		ecHns.add(ifHns);
		
		assertFalse("validator brings no error", seiValidator.validate(ecHns.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecObc.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecRw.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ct.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(itc.getStructuralElementInstance()));
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are markers now", 1,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		IMarker marker = fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE)[0];
		ReferencePropertyInstance rpiTo = ifHns.getIfeToReferenceProperty();
		ReferencePropertyInstance rpiFrom = ifHns.getIfeFromReferenceProperty();
		VirSatFuncelectricalMarkerHelper vfmHelper = new VirSatFuncelectricalMarkerHelper();
		assertTrue("Marker should point to correct IUuids", vfmHelper.isAssociatedWith(marker, rpiFrom) && vfmHelper.isAssociatedWith(marker, rpiTo));
	
	}
	
	@Test
	public void testInterfaceEndsAreSame() throws Exception {
		
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);

		StructuralElementInstanceValidator seiValidator = new StructuralElementInstanceValidator();

		assertTrue("validator brings no error", seiValidator.validate(ecHns.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecObc.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecRw.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ct.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(itc.getStructuralElementInstance()));
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		ifHns.setInterfaceEndFrom(ifeObc);
		ifHns.setInterfaceEndTo(ifeObc);
		ecHns.add(ifHns);
		
		assertFalse("validator brings no error", seiValidator.validate(ecHns.getStructuralElementInstance()));
		assertTrue("validator brings no error",  seiValidator.validate(ecObc.getStructuralElementInstance()));
		assertTrue("validator brings no error",  seiValidator.validate(ecRw.getStructuralElementInstance()));
		assertTrue("validator brings no error",  seiValidator.validate(ct.getStructuralElementInstance()));
		assertTrue("validator brings no error",  seiValidator.validate(itc.getStructuralElementInstance()));
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are markers now", 	 1,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		IMarker marker = fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE)[0];
		ReferencePropertyInstance rpiTo = ifHns.getIfeToReferenceProperty();
		ReferencePropertyInstance rpiFrom = ifHns.getIfeFromReferenceProperty();
		VirSatFuncelectricalMarkerHelper vfmHelper = new VirSatFuncelectricalMarkerHelper();
		assertTrue("Marker should point to correct IUuids", vfmHelper.isAssociatedWith(marker, rpiFrom) && vfmHelper.isAssociatedWith(marker, rpiTo));
	
	}
	
	@Test
	public void testInterfaceEndsInSameTree() throws CoreException {
		
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		StructuralElementInstanceValidator seiValidator = new StructuralElementInstanceValidator();
		
		InterfaceEnd ifeEdObc = new InterfaceEnd(conceptFea);
		ifeEdObc.setName("IFE_OBC");
		ifeEdObc.setType(it1);
		
		ElementDefinition edObc = new ElementDefinition(conceptPs);
		edObc.setName("ED_OBC");
		edObc.add(ifeEdObc);
		
		ecHns.add(ifHns);
		ifHns.setInterfaceEndFrom(ifeRw);
		ifHns.setInterfaceEndTo(ifeObc);
	
		assertTrue("validator brings no error", seiValidator.validate(ecHns.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecObc.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecRw.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ct.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(itc.getStructuralElementInstance()));
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		ifHns.setInterfaceEndFrom(ifeEdObc);
		
		assertFalse("validator brings error",   seiValidator.validate(ecHns.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecObc.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ecRw.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(ct.getStructuralElementInstance()));
		assertTrue("validator brings no error", seiValidator.validate(itc.getStructuralElementInstance()));
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileItc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileCt.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0,	fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are markers now", 	 1, fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		IMarker marker = fileHns.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE)[0];
		ReferencePropertyInstance rpiTo = ifHns.getIfeToReferenceProperty();
		ReferencePropertyInstance rpiFrom = ifHns.getIfeFromReferenceProperty();
		VirSatFuncelectricalMarkerHelper vfmHelper = new VirSatFuncelectricalMarkerHelper();
		assertTrue("Marker should point to correct IUuids", vfmHelper.isAssociatedWith(marker, rpiFrom) && vfmHelper.isAssociatedWith(marker, rpiTo));
	
	}

}
