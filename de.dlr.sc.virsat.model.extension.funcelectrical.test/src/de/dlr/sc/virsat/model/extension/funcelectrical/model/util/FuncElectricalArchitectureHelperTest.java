/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.model.util;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.BasicCommandStack;
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
import de.dlr.sc.virsat.model.dvlm.structural.command.CreateAddStructuralElementInstanceCommand;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Test Case for Functional Electric Architecture Helper 
 * @author lobe_el
 *
 */

public class FuncElectricalArchitectureHelperTest {
	
	private static final String CONCEPT_ID_PS = de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId();
	private static final String CONCEPT_ID_FUNCELECTRICAL = Activator.getPluginId();
	private static final int THREE = 3;
	
	Concept conceptPs;
	Concept conceptFea;
	
	InterfaceTypeCollection itc;
	InterfaceType it1;
	InterfaceType it2;
	InterfaceType it3;

	EditingDomain ed;
	Repository repository;
	VirSatResourceSet resSet;
	VirSatProjectCommons projectCommons;
	IResource fileRepo;
	IResource fileItc;
	
	@Before
	public void setUp() throws CoreException {
		IProject project;

		StructuralElement seEc;
		Category catIf;
		
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
		ed = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack());

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
		projectCommons = new VirSatProjectCommons(project);

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
	}
	
	@After
	public void tearDown() {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testgetAllInterfaceTypes() throws IOException {
		// Here we start to create the Test Model
		it1 = new InterfaceType(conceptFea);
		it1.setName("IFT_MILBUS");
		it2 = new InterfaceType(conceptFea);
		it2.setName("IFT_CANBUS");
		it3 = new InterfaceType(conceptFea);
		it3.setName("IFT_WHATEVERBUS");

		itc = new InterfaceTypeCollection(conceptFea);
		itc.setName("INTERFACE_TYPE_COLLECTION");
		itc.add(it1);
		itc.add(it2);
		
		CreateAddStructuralElementInstanceCommand.create(ed, repository, itc.getStructuralElementInstance()).execute();
		
		FuncElectricalArchitectureHelper feaHelper = new FuncElectricalArchitectureHelper();
		List<InterfaceType> ifts = feaHelper.getAllInterfaceTypes(repository);
		
		assertEquals("List has correct amount of elements", 2, ifts.size());
		assertThat("List contains correct elements", ifts, hasItems(it1, it2));

		itc.add(it3);
		ifts = feaHelper.getAllInterfaceTypes(repository);
		
		assertEquals("List has correct amount of elements", THREE, ifts.size());
		assertThat("List contains correct elements", ifts, hasItems(it1, it2, it3));

	}
}
