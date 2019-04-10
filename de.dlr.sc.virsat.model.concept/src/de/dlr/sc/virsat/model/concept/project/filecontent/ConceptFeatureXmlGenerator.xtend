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
import de.dlr.sc.virsat.model.concept.project.ConceptFeatureBuilderInfo

/**
 * Class to create the content for the Feature.xml file
 */
class ConceptFeatureXmlGenerator implements IFileContentGenerator {
	
	override generateContent(IProjectBuilderInfo projectBuilderInfo) {
		return manifestContent(projectBuilderInfo).toString;
	}
	
	def manifestContent(IProjectBuilderInfo builderInfo) '''
	<?xml version="1.0" encoding="UTF-8"?>
	<feature
	      id="«builderInfo.projectName»"
	      label="Feature"
		  version="1.0.0.qualifier"
		  plugin="de.dlr.sc.virsat.branding.ui"
	      license-feature="de.dlr.sc.virsat.license.feature">

	<description>
		Feature for documentation and release notes.
		German Aerospace Center (DLR e.V.)
		Simulation and Software Technology
	</description>
	
	<copyright>
		Copyright 2017
		by German Aerospace Center (DLR e.V.)
	</copyright>
	
	<license url="%licenseURL">
		%license
	</license>
	
	   <plugin
	         id="«builderInfo.projectName.replace(ConceptFeatureBuilderInfo.PROJECT_NAME_FEATURE_EXTENSION, "")»"
	         download-size="0"
	         install-size="0"
	         version="0.0.0"
	         unpack="false"/>
	
	   <plugin
	         id="«builderInfo.projectName.replace(ConceptFeatureBuilderInfo.PROJECT_NAME_FEATURE_EXTENSION, ".ui")»"
	         download-size="0"
	         install-size="0"
	         version="0.0.0"
	         unpack="false"/>
	
	</feature>
	'''
}