/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.initializer

class ContentManifestMf {
	
	def getContents(String projectName) '''
	Manifest-Version: 1.0
	Bundle-ManifestVersion: 2
	Bundle-Name: «projectName»
	Bundle-SymbolicName: «projectName»
	Bundle-Version: 1.0.0.qualifier
	Bundle-RequiredExecutionEnvironment: JavaSE-1.8
	Require-Bundle: de.dlr.sc.virsat.apps
	Automatic-Module-Name: «projectName»
	'''
}
