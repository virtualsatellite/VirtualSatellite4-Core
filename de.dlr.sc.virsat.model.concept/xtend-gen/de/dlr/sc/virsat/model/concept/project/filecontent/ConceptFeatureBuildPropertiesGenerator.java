/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.project.filecontent;

import de.dlr.sc.virsat.model.concept.project.IProjectBuilderInfo;
import de.dlr.sc.virsat.model.concept.project.filecontent.IFileContentGenerator;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * Class to create the content for the Manifest of the Concept Plugin
 */
@SuppressWarnings("all")
public class ConceptFeatureBuildPropertiesGenerator implements IFileContentGenerator {
  @Override
  public String generateContent(final IProjectBuilderInfo projectBuilderInfo) {
    return this.buildProeprtiesContent(projectBuilderInfo).toString();
  }
  
  public CharSequence buildProeprtiesContent(final IProjectBuilderInfo builderInfo) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("bin.includes = feature.xml");
    _builder.newLine();
    return _builder;
  }
}
