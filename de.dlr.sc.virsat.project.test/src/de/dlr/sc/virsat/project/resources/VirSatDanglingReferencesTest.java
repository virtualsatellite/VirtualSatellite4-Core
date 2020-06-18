/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * Test Cases for the wrapping VirSatProject Resource
 * @author kova_an, lobe_el
 *
 */
public class VirSatDanglingReferencesTest extends AProjectTestCase {
	
	protected VirSatResourceSet resSet;
	
	protected Concept concept;
	protected StructuralElement seDefinition;
	protected StructuralElement seConfiguration;

	protected Repository repository;
	protected StructuralElementInstance seiConfiguration;
	protected StructuralElementInstance seiDefinition;

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		createConcept();
		createRepository();
		createAndFillResources();
	}
	
	/**
	 * Adding StructuralElements "Definition" and "Configuration" to a new Concept
	 */
	private void createConcept() {
		concept = ConceptsFactory.eINSTANCE.createConcept();
		
		seDefinition = StructuralFactory.eINSTANCE.createStructuralElement();
		seDefinition.setIsApplicableForAll(true);
		seDefinition.setName("Definition");
		
		seConfiguration = StructuralFactory.eINSTANCE.createStructuralElement();
		seConfiguration.setIsApplicableForAll(true);
		seConfiguration.setName("Configuration");
		
		seConfiguration.setIsCanInheritFromAll(true);
		
		concept.getStructuralElements().add(seDefinition);
		concept.getStructuralElements().add(seConfiguration);
	}
	
	/**
	 * Putting StructuralElementInstances in a new Repository
	 */
	private void createRepository() {
		repository = DVLMFactory.eINSTANCE.createRepository();

		seiConfiguration = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiDefinition = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiConfiguration.setName("SeiConfiguration");
		seiDefinition.setName("SeiDefinition");
		
		seiConfiguration.setType(seConfiguration);
		seiDefinition.setType(seDefinition);
		
		seiConfiguration.getSuperSeis().add(seiDefinition);
		
		repository.getActiveConcepts().add(concept);
		repository.getRootEntities().add(seiDefinition);
		repository.getRootEntities().add(seiConfiguration);
	}
	
	/**
	 * Adding the StructuralElementInstances to the ResourceSet
	 * @throws IOException if saving of resources fails
	 */
	private void createAndFillResources() {
		resSet = VirSatResourceSet.createUnmanagedResourceSet(testProject);
		resSet.getResources().clear();

		Resource resRepo = resSet.getRepositoryResource();
		Resource resSeiConfiguration = resSet.getStructuralElementInstanceResource(seiConfiguration);
		Resource resSeiDefinition = resSet.getStructuralElementInstanceResource(seiDefinition);

		resRepo.getContents().clear();
		resSeiConfiguration.getContents().clear();
		resSeiDefinition.getContents().clear();
		
		resRepo.getContents().add(repository);
		resSeiConfiguration.getContents().add(seiConfiguration);
		resSeiDefinition.getContents().add(seiDefinition);
		
		resSet.saveAllResources(null, UserRegistry.getInstance());
	}
	
	/**
	 * To not struggle with some corruption the ResourceSet is loaded again
	 * @return the ResourcesSet of the Project
	 */
	private VirSatResourceSet reloadAndGetNewResourceSet() {
		VirSatResourceSet reloadedResourceSet = VirSatResourceSet.createUnmanagedResourceSet(testProject);
		return reloadedResourceSet;
	}
	
	@Test
	public void testRemovedSeiFromResource() throws IOException {
		Resource resSeiDefinition = resSet.getStructuralElementInstanceResource(seiDefinition);
		resSeiDefinition.getContents().remove(seiDefinition);
		resSet.saveResource(resSeiDefinition, UserRegistry.getInstance(), true);
		
		checkThereAreErrorsInitiallyAndSaveFixesThem();
	}

	@Test
	public void testDeletedReferencedFile() throws Exception {
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(testProject);
		IResource fileDefinition = projectCommons.getStructuralElementInstanceFile(seiDefinition);
		fileDefinition.delete(true, null);

		checkThereAreErrorsInitiallyAndSaveFixesThem();
	}
	
	/**
	 * When the resource or the file of a StructuralElementInstance which is referenced is deleted, these references
	 * point to proxies which should be gone when we have saved it correctly
	 */
	private void checkThereAreErrorsInitiallyAndSaveFixesThem() {
		VirSatResourceSet reloadedResourceSet = reloadAndGetNewResourceSet();
		Repository repoWithDanglingReferenceToDefinition = reloadedResourceSet.getRepository();
		
		//assert that it's corrupted
		EList<StructuralElementInstance> rootEntities = repoWithDanglingReferenceToDefinition.getRootEntities();
		assertEquals("Reloaded repository has two root elements", 2, rootEntities.size());
		StructuralElementInstance reloadedSeiConfiguration;
		StructuralElementInstance unresolvableProxyOfDefinitionWithRandomId;

		if (rootEntities.get(0).getUuid().equals(seiConfiguration.getUuid())) {
			reloadedSeiConfiguration = rootEntities.get(0);
			unresolvableProxyOfDefinitionWithRandomId = rootEntities.get(1);
		} else {
			reloadedSeiConfiguration = rootEntities.get(1);
			unresolvableProxyOfDefinitionWithRandomId = rootEntities.get(0);
		}

		assertUnresolvableProxy(unresolvableProxyOfDefinitionWithRandomId, reloadedResourceSet);
		
		assertEquals("Configuration SEI inherits from one super SEI", 1, reloadedSeiConfiguration.getSuperSeis().size());
		StructuralElementInstance superSeiProxy = reloadedSeiConfiguration.getSuperSeis().get(0);
		assertUnresolvableProxy(superSeiProxy, reloadedResourceSet);

		assertEcoreUnresolvedReferenceFinderDetectsExpectedUnresolvedReferences(reloadedResourceSet);
		
		Resource resSeiConfiguration = reloadedResourceSet.getStructuralElementInstanceResource(reloadedSeiConfiguration);
		Resource resRepository = reloadedResourceSet.getRepositoryResource();
		assertFalse("Configuration resource contains errors", resSeiConfiguration.getErrors().isEmpty());
		assertFalse("Repository resource contains errors", resRepository.getErrors().isEmpty());

		for (Resource resource : reloadedResourceSet.getResources()) {
			VirSatResourceSetUtil.removeDanglingReferences(resource);
			reloadedResourceSet.saveResource(resource, UserRegistry.getInstance(), true);
		}
		
		reloadedResourceSet = reloadAndGetNewResourceSet();
		Repository repoWithDanglingReferencesRemoved = reloadedResourceSet.getRepository();
		
		//assert not corrupted
		assertEquals("Reloaded repository has one root element, unresolvable second element is removed", 1, repoWithDanglingReferencesRemoved.getRootEntities().size());
		reloadedSeiConfiguration = repoWithDanglingReferencesRemoved.getRootEntities().get(0);
		assertEquals("The only remaining root object in the reloaded repository is SEI Configuration", seiConfiguration.getUuid(), reloadedSeiConfiguration.getUuid());
		assertTrue("Dangling inheritance reference is removed", reloadedSeiConfiguration.getSuperSeis().isEmpty());
		
		assertEcoreUnresolvedReferenceFinderDoesntDetectUnresolvedReferences(reloadedResourceSet);
		
		resSeiConfiguration = reloadedResourceSet.getStructuralElementInstanceResource(reloadedSeiConfiguration);
		resRepository = reloadedResourceSet.getRepositoryResource();
		assertTrue("Configuration resource contains errors", resSeiConfiguration.getErrors().isEmpty());
		assertTrue("Repository resource contains errors", resRepository.getErrors().isEmpty());
	}

	/**
	 * The ResourceSet should have two unresolved References 
	 * @param reloadedResourceSet the ResourceSet we investigate
	 */
	private void assertEcoreUnresolvedReferenceFinderDetectsExpectedUnresolvedReferences(VirSatResourceSet reloadedResourceSet) {
		Map<EObject, Collection<Setting>> ecoreUnresolvedReferences = EcoreUtil.UnresolvedProxyCrossReferencer.find(reloadedResourceSet);

		assertEquals("EcoreUtils found two unresolved references", 2, ecoreUnresolvedReferences.size());
		List<String> brokenFeaturesNames = new ArrayList<>();
		ecoreUnresolvedReferences.values().forEach(
				brokenFeaturesCollection -> 
				brokenFeaturesCollection.forEach(
						feature -> brokenFeaturesNames.add(feature.getEStructuralFeature().getName())));
		assertTrue("Repository.rootEntities contains a dangling reference", brokenFeaturesNames.contains(DVLMPackage.Literals.REPOSITORY__ROOT_ENTITIES.getName()));
		assertTrue("Configuration.superSeis contains a dangling reference", brokenFeaturesNames.contains(InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS.getName()));
	}

	/**
	 * After saving it correctly the dangling references should be gone 
	 * @param reloadedResourceSet the ResourceSet we investigate
	 */
	private void assertEcoreUnresolvedReferenceFinderDoesntDetectUnresolvedReferences(VirSatResourceSet reloadedResourceSet) {
		Map<EObject, Collection<Setting>> ecoreUnresolvedReferences = EcoreUtil.UnresolvedProxyCrossReferencer.find(reloadedResourceSet);
		assertTrue("EcoreUtils did not find any unresolved references", ecoreUnresolvedReferences.isEmpty());
	}

	/**
	 * doing some assertions about the StructuralElementInstance 
	 * @param expectedProxy The StructuralElementInstance whose resource/file is deleted and for that reason only should exist as a proxy
	 * @param resourceSet The ResourceSet we investigate
	 */
	private void assertUnresolvableProxy(StructuralElementInstance expectedProxy,
			VirSatResourceSet resourceSet) {
		assertTrue("We follow a reference to a removed model object and find proxy there", expectedProxy.eIsProxy());
		EcoreUtil.resolve(expectedProxy, resourceSet);
		assertTrue("Proxy is unresolvable", expectedProxy.eIsProxy());
	}

	@Test
	public void testReferenceEObjectWithoutResource() throws Exception {
		StructuralElementInstance seiWithoutResource = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiWithoutResource.setType(seDefinition);
		
		repository.getRootEntities().add(seiWithoutResource);
		seiConfiguration.getSuperSeis().add(seiWithoutResource);

		
		Resource repositoryResource = resSet.getRepositoryResource();
		Resource seiConfigResource = resSet.getStructuralElementInstanceResource(seiConfiguration);

		assertEquals("Two normal reference and one Dangling reference are present in the repository", 2 + 1, repository.getRootEntities().size());
		
		saveResourceWithDanglingHrefOption(repositoryResource, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
		assertTrue("Repository resource does not have errors", repositoryResource.getErrors().isEmpty());

		saveResourceWithDanglingHrefOption(seiConfigResource, XMLResource.OPTION_PROCESS_DANGLING_HREF_RECORD);
		assertFalse("Configuration resource has error", seiConfigResource.getErrors().isEmpty());

		VirSatResourceSet reloadedResourceSet = reloadAndGetNewResourceSet();
		reloadedResourceSet.getRepositoryResource();
		
		Repository reloadedRepository = reloadedResourceSet.getRepository();
		assertEquals("Dangling reference from the repositoty was discarded", 2, reloadedRepository.getRootEntities().size());
		
		StructuralElementInstance reloadedConfigurationSei = null;
		
		for (StructuralElementInstance rootSei : reloadedRepository.getRootEntities()) {
			if (rootSei.getUuid().equals(seiConfiguration.getUuid())) {
				reloadedConfigurationSei = rootSei;
			}
		}
		
		assertEquals("Dangling reference is discarded from configuration sei", 1, reloadedConfigurationSei.getSuperSeis().size());
	}

	/**
	 * Using the save Options to get rid of dangling references and proxy objects 
	 * @param resource the Resource which we are saving
	 * @param option says how to handle the exception about the dangling reference 
	 * @throws IOException if saving fails
	 */
	private void saveResourceWithDanglingHrefOption(Resource resource, String option) throws IOException {
		Map<String, String> saveOptions = new HashMap<>();
		saveOptions.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, option);
		resource.save(saveOptions);
	}
	
}
