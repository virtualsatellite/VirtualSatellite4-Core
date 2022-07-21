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
class ConceptFragmentTestManifestGenerator implements IFileContentGenerator {
	
	override generateContent(IProjectBuilderInfo projectBuilderInfo) {
		return manifestContent(projectBuilderInfo).toString;
	}
	
	def getProjectNameForTestPlugin(IProjectBuilderInfo builderInfo) {
		builderInfo.projectName.replace(".test", "");
	}
	
	def manifestContent(IProjectBuilderInfo builderInfo) '''
	Manifest-Version: 1.0
	Bundle-ManifestVersion: 2
	Bundle-Name: VirSat Model Extension Concept Tests
	Bundle-SymbolicName: «builderInfo.projectName»;singleton:=true
	Bundle-Version: 0.0.1.qualifier
	Fragment-Host: «getProjectNameForTestPlugin(builderInfo)»;bundle-version="0.0.1"
	Bundle-ClassPath: .
	Bundle-Vendor: DLR (German Aerospace Center)
	Bundle-RequiredExecutionEnvironment: JavaSE-11
	Bundle-ActivationPolicy: lazy
	Require-Bundle: org.junit,
	 org.eclipse.emf.edit,
	 org.eclipse.emf.ecore.xmi,
	 de.dlr.sc.virsat.model,
	 de.dlr.sc.virsat.model.edit,
	 de.dlr.sc.virsat.concept.unittest.util
	Export-Package: «builderInfo.projectName»
	'''
}