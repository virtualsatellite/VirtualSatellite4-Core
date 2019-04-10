/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.project.filecontent

import de.dlr.sc.virsat.model.concept.project.filecontent.IFileContentGenerator
import de.dlr.sc.virsat.model.concept.project.IProjectBuilderInfo

/**
 * Class to create the content for the Manifest of the Concept Plugin
 */
class ConceptPluginBuildPropertiesGenerator implements IFileContentGenerator {
	
	override generateContent(IProjectBuilderInfo projectBuilderInfo) {
		return buildProeprtiesContent(projectBuilderInfo).toString;
	}
	
	def buildProeprtiesContent(IProjectBuilderInfo builderInfo) '''
	source.. = 	concept/,\
				src/,\
	       		src-gen/,\
	       		xtend-gen/
	output.. = 	target/classes/
	bin.includes = META-INF/,\
	           		.,\
	           		plugin.xml,\
	           		concept/
	additional.bundles = org.eclipse.emf.mwe2.runtime,\
	                     org.eclipse.xtend.typesystem.emf,\
	                     org.eclipse.xpand,\
	                     org.eclipse.xtend,\
	                     org.apache.commons.logging,\
	                     org.eclipse.jdt.core,\
	                     com.ibm.icu,\
	                     org.antlr.runtime,\
	                     org.eclipse.core.runtime,\
	                     org.eclipse.emf.mwe.utils,\
	                     org.eclipse.emf.ecore.xmi,\
	                     org.eclipse.jface.text,\
	                     org.eclipse.xpand,\
	                     org.eclipse.xtend,\
	                     org.eclipse.xtend.typesystem.emf,\
	                     org.eclipse.emf.mwe2.launch,\
	                     org.apache.log4j,\
	                     org.eclipse.core.resources
	'''
}