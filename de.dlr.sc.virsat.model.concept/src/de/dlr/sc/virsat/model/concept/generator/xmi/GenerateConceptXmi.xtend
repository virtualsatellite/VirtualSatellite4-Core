/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.xmi

import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import java.io.ByteArrayOutputStream
import java.io.IOException
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.common.util.URI
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider

class GenerateConceptXmi {
	
	public static final String MODEL_TYPE_XMI_EXTENSION = "xmi";
	public static final String CONCEPT_BASE_FILENAME = "concept"
	public static final String CONCEPT_XMI_FILENAME = CONCEPT_BASE_FILENAME + "." + MODEL_TYPE_XMI_EXTENSION;
	
	/**
	 * This method serialized the data model into the given format
	 * @param fileNameExtension the extension of the target format to serialize to. Can be either XMI or XML for the  moment
	 * @param dataModel access to the root model object
	 * @param fsa access to the local file system
	 */
	def serializeModel(Concept dataModel, IFileSystemAccess fsa) {
		// Register the factories for XMI and XML serialization		
		val resourceRegistry = Resource.Factory.Registry.INSTANCE;
		resourceRegistry.extensionToFactoryMap.put(MODEL_TYPE_XMI_EXTENSION, new XMIResourceFactoryImpl());

		// Create a resource set and a resource to store it as XMI
		try {
			val targetResourceSet = new ResourceSetImpl();
			val targetResource = targetResourceSet.createResource(URI.createURI(CONCEPT_XMI_FILENAME));
			targetResource.getContents().add(dataModel);
			val outputStream = new ByteArrayOutputStream();
			targetResource.save(outputStream, null);
			
			fsa.generateFile(CONCEPT_XMI_FILENAME, ConceptOutputConfigurationProvider::GENERATOR_OUTPUT_ID_CONCEPT, outputStream.toString());
			
		} catch (IOException e) {
			throw new RuntimeException(e);	
		}
		
		// Also create the xmi file that has the version information encoded into its filename
		try {
			val targetResourceSet = new ResourceSetImpl();
			val filenameWithVersion = dataModel.conceptWithVersionName;
			val targetResource = targetResourceSet.createResource(URI.createURI(filenameWithVersion));
			targetResource.getContents().add(dataModel);
			val outputStream = new ByteArrayOutputStream();
			targetResource.save(outputStream, null);
			
			fsa.generateFile(filenameWithVersion, ConceptOutputConfigurationProvider::GENERATOR_OUTPUT_ID_CONCEPT, outputStream.toString());
			
		} catch (IOException e) {
			throw new RuntimeException(e);	
		}
	}
	
	def static String getConceptWithVersionName(Concept concept) {
		val version = concept.version.replace(".", "_");
		return CONCEPT_BASE_FILENAME + "_v" + version + "." + MODEL_TYPE_XMI_EXTENSION;
	}
}