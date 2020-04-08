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
import de.dlr.sc.virsat.model.concept.generator.beans.GenerateCategoryBeans
import de.dlr.sc.virsat.model.concept.generator.validator.GenerateValidator

/**
 * Class to create the content for the Manifest of the Concept Plugin
 */
class ConceptPluginManifestGenerator implements IFileContentGenerator {
	
	override generateContent(IProjectBuilderInfo projectBuilderInfo) {
		return manifestContent(projectBuilderInfo).toString;
	}
	
	def manifestContent(IProjectBuilderInfo builderInfo) '''
	Manifest-Version: 1.0
	Bundle-ManifestVersion: 2
	Bundle-Name: VirSat Model Extension Concept
	Bundle-SymbolicName: «builderInfo.projectName»;singleton:=true
	Bundle-Version: 0.0.1.qualifier
	Bundle-Vendor: German Aerospace Center (DLR)
	Require-Bundle: org.eclipse.ui,
	 org.eclipse.core.runtime,
	 de.dlr.sc.virsat.model,
	 de.dlr.sc.virsat.model.edit,
	 de.dlr.sc.virsat.project,
	 de.dlr.sc.virsat.model.ext.core;visibility:=reexport
	Bundle-RequiredExecutionEnvironment: JavaSE-1.8
	Bundle-ActivationPolicy: lazy
	Eclipse-ExtensibleAPI: true
	Export-Package: «builderInfo.projectName».«GenerateCategoryBeans.PACKAGE_FOLDER»,
	 «builderInfo.projectName».«GenerateValidator.PACKAGE_FOLDER»
	'''
}