/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
 package de.dlr.sc.virsat.model.concept.generator.plugin

import de.dlr.sc.virsat.model.dvlm.categories.Category
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import java.nio.file.Files
import java.nio.file.Paths
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.generator.IFileSystemAccess
import de.dlr.sc.virsat.model.concept.Activator
import org.eclipse.core.resources.ResourcesPlugin
import java.io.IOException
import java.io.InputStream
import java.nio.file.FileAlreadyExistsException
import org.eclipse.core.runtime.Status
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName

class GenerateConceptImages {

	def serializeModel(Concept concept, IFileSystemAccess fsa) {
		var iterator = EcoreUtil.getAllContents(concept, true);
		val uiPlugin = concept.name + ".ui/"
		val pathTo = "../../" + uiPlugin + "resources/icons/"

		createDefaultImage(concept, pathTo, concept.fileNameStandard, concept.fileNameDisabled)					

		iterator.forEach [
			if (it instanceof Category || it instanceof StructuralElement) {

				val dvlmObject = it as IQualifiedName;
				val fileName = dvlmObject.fileNameStandard
				val fileNameDisabled = dvlmObject.fileNameDisabled
				
				// we copy some existing default.gif. 
				// copy some standard gif file, open it as Input Stream
				// then we create the target folder in the generated concept plugin and rename it with the correct component name!
				createDefaultImage(concept, pathTo, fileName, fileNameDisabled)					
			}
		]
	}
	
	def createDefaultImage(Concept concept, String pathTo, String fileName, String fileNameDisabled) {
		var InputStream stream; 
		var InputStream stream2; 
		try {
			stream = Activator.getDefault().getPluginResource("resources/icons/default.gif")
			stream2 = Activator.getDefault().getPluginResource("resources/icons/default.gif")
			val iProject = ResourcesPlugin.getWorkspace().getRoot().getProject(concept.name);
			if (iProject === null) {
				throw new RuntimeException("Could not find project: " + concept.name + " for finding the default image");
			}
			val iFile = iProject.getFile(pathTo);
			if (iFile === null) {
				throw new RuntimeException("Could not find required folder in project");
			}
			val osPath = iFile.getRawLocation().toOSString();
			
			Files.createDirectories(Paths.get(osPath)); 
			Files.copy(stream, Paths.get(osPath + "/" + fileName));
			Files.copy(stream2, Paths.get(osPath + "/" + fileNameDisabled));
		} catch (FileAlreadyExistsException e1) {
				Activator.^default.log.log(new Status(Status.ERROR, "ConceptPlugin:", "File already existing: we do not override!"));
		} catch (IOException e) {
			throw new RuntimeException("Could not copy image: " + fileName);
		} finally {
			if (stream !== null) stream.close();
			if (stream2 !== null) stream2.close();
		}
	}
	
	static def getFileNameStandard(IQualifiedName qualifiedNameObject) {
		return 	qualifiedNameObject.name + ".gif";
	}
	
	static def getFileNameDisabled(IQualifiedName qualifiedNameObject) {
		return 	qualifiedNameObject.name + "_disabled.gif";
	}
}

