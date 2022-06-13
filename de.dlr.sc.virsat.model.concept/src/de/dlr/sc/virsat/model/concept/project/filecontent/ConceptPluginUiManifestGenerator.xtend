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
class ConceptPluginUiManifestGenerator implements IFileContentGenerator {
	
	override generateContent(IProjectBuilderInfo projectBuilderInfo) {
		return manifestContent(projectBuilderInfo).toString;
	}
	
	def getProjectNameForUiPlugin(IProjectBuilderInfo builderInfo) {
		builderInfo.projectName.replace(".ui", "");
	}
	
	def manifestContent(IProjectBuilderInfo builderInfo) '''
	Manifest-Version: 1.0
	Bundle-ManifestVersion: 2
	Bundle-Name: VirSat Model Extension Concept UI
	Bundle-SymbolicName: «builderInfo.projectName»;singleton:=true
	Bundle-Version: 0.0.1.qualifier
	Require-Bundle: «getProjectNameForUiPlugin(builderInfo)»,
	 org.eclipse.ui,
	 org.eclipse.core.runtime,
	 org.eclipse.core.expressions,
	 org.eclipse.emf.edit,
	 org.eclipse.emf.transaction,
	 org.eclipse.core.resources,
	 de.dlr.sc.virsat.model,
	 de.dlr.sc.virsat.project,
	 de.dlr.sc.virsat.project.ui,
	 de.dlr.sc.virsat.uiengine.ui
	Bundle-RequiredExecutionEnvironment: JavaSE-11
	Bundle-ActivationPolicy: lazy
	'''
}