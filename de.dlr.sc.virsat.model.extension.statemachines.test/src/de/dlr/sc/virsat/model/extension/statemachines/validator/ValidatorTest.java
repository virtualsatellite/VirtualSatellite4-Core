/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.validator;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
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
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.statemachines.model.AllowsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;


/**
 * Test Case for Validator of State Machines
 * 
 * @author bell_er
 *
 */
public class ValidatorTest {
	private static final String CONCEPT_ID_PS = "de.dlr.sc.virsat.model.extension.ps";
	private static final String CONCEPT_ID_STATE_MACHINES = "de.dlr.sc.virsat.model.extension.statemachines";

	IProject project;
	VirSatResourceSet resSet;
	Repository repository;

	Concept conceptPs;
	Concept conceptStateMachines;


	ConfigurationTree ct;
	ElementConfiguration ecObc;
	ElementConfiguration ecRw;

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

		if (project.exists()) {
			project.delete(true, null);
		}
		project.create(null);
		project.open(null);


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
			public String getId() { return CONCEPT_ID_STATE_MACHINES; };
		};
		//CHECKSTYLE:ON

		// Now load the PS and IF concept into the repository
		// we need the full set of repository loaded concepts etc to provide
		// correctly set up workspace resources for setting and detecting the markers
		accePs.createAddActiveConceptCommand(ed, repository).execute();
		acceFea.createAddActiveConceptCommand(ed, repository).execute();

		ActiveConceptHelper acHelper = new ActiveConceptHelper(repository);
		conceptPs = acHelper.getConcept(CONCEPT_ID_PS);
		conceptStateMachines = acHelper.getConcept(CONCEPT_ID_STATE_MACHINES);

		// We used to have badly connected concepts, due to the persistence of resources
		// on the second run of the test case the repository resource was already containing
		// a resource and the test setup was adding a second one. We now changed the code, that
		// the whole project in the workspace gets deleted first and then recreated.
		//CHECKSTYLE:OFF
		seEc = ActiveConceptHelper.getStructuralElement(conceptPs, ElementConfiguration.class.getSimpleName());
		catIf = ActiveConceptHelper.getCategory(conceptStateMachines, StateMachine.class.getSimpleName());
		assertThat("Concepts correctly connected", catIf.getApplicableFor(), hasItem(seEc));
		//CHECKSTYLE:ON

		// Here we start to create the Test Model
		ecObc = new ElementConfiguration(conceptPs);
		ecObc.setName("EC_OBC");


		ecRw = new ElementConfiguration(conceptPs);
		ecRw.setName("EC_RW");



		ct = new ConfigurationTree(conceptPs);
		ct.setName("CONF_TREE");
		ct.add(ecObc);

		// And finally we use the project commons to create the correct workspace paths
		// for the individual resources of the SEIs. We still have to attach the SEI eObjects
		// to their EMF Resources and save them


		Resource resRepo = resSet.getRepositoryResource();

		Resource resCt = resSet.getStructuralElementInstanceResource(ct.getStructuralElementInstance());
		Resource resObc = resSet.getStructuralElementInstanceResource(ecObc.getStructuralElementInstance());
		Resource resRw = resSet.getStructuralElementInstanceResource(ecRw.getStructuralElementInstance());



		resCt.getContents().add(ct.getStructuralElementInstance());
		resObc.getContents().add(ecObc.getStructuralElementInstance());
		resRw.getContents().add(ecRw.getStructuralElementInstance());


		resRepo.save(Collections.EMPTY_MAP);

		resCt.save(Collections.EMPTY_MAP);
		resObc.save(Collections.EMPTY_MAP);
		resRw.save(Collections.EMPTY_MAP);


	}
	@After
	public void tearDown() {
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Test
	public void testValidator() throws Exception {
		StateMachine sm  = new StateMachine(conceptStateMachines);
		StructuralElementInstanceValidator seiValidator = new StructuralElementInstanceValidator();
		
		State s1 = new State(conceptStateMachines);
		State s2 = new State(conceptStateMachines);
		Transition t1 = new Transition(conceptStateMachines);
		Transition t2 = new Transition(conceptStateMachines);
		t1.setStateFrom(s1);
		t1.setStateTo(s2);
		s1.setName("s1");
		s2.setName("s2");
		
		sm.getStates().add(s2);
		sm.getStates().add(s1);
		sm.getTransitions().add(t1);
		ecObc.add(sm);
		
		
		
		
		assertFalse("InitialState is not selected", seiValidator.validate(ecObc.getStructuralElementInstance()));
	
		sm.setInitialState(s2);
		assertTrue("Everthing is okay", seiValidator.validate(ecObc.getStructuralElementInstance()));
		
		sm.getTransitions().add(t2);
		
		assertFalse("Transition states are not set", seiValidator.validate(ecObc.getStructuralElementInstance()));
		sm.getTransitions().remove(t2);
		
		s1.setName("s2");

		assertFalse("Same state Names", seiValidator.validate(ecObc.getStructuralElementInstance()));
	
		AllowsConstraint ac = new AllowsConstraint(conceptStateMachines);
		sm.getConstraints().add(ac);
		
		assertFalse("AllowsConstraint states are not set", seiValidator.validate(ecObc.getStructuralElementInstance()));
		
		ac.setStateInfluenced(s1);
		ac.setStateConstraining(s1);
		
		assertFalse("AllowsConstraint states are from the same state machine", seiValidator.validate(ecObc.getStructuralElementInstance()));
		
	}



}
