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

import de.dlr.sc.virsat.model.concept.project.ConceptFeatureBuilderInfo;
import de.dlr.sc.virsat.model.concept.project.IProjectBuilderInfo;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * Class to create the content for the Feature.xml file
 */
@SuppressWarnings("all")
public class ConceptFeatureXmlGenerator implements IFileContentGenerator {
  @Override
  public String generateContent(final IProjectBuilderInfo projectBuilderInfo) {
    return this.manifestContent(projectBuilderInfo).toString();
  }
  
  public CharSequence manifestContent(final IProjectBuilderInfo builderInfo) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<feature");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("id=\"");
    String _projectName = builderInfo.getProjectName();
    _builder.append(_projectName, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("label=\"Feature\"");
    _builder.newLine();
    _builder.append("\t  ");
    _builder.append("version=\"1.0.0.qualifier\"");
    _builder.newLine();
    _builder.append("\t  ");
    _builder.append("plugin=\"de.dlr.sc.virsat.branding.ui\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("license-feature=\"de.dlr.sc.virsat.license.feature\">");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<description>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Feature for documentation and release notes.");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("German Aerospace Center (DLR e.V.)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Simulation and Software Technology");
    _builder.newLine();
    _builder.append("</description>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<copyright>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Copyright 2017");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("by German Aerospace Center (DLR e.V.)");
    _builder.newLine();
    _builder.append("</copyright>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<license url=\"%licenseURL\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("%license");
    _builder.newLine();
    _builder.append("</license>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("   ");
    _builder.append("<plugin");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("id=\"");
    String _replace = builderInfo.getProjectName().replace(ConceptFeatureBuilderInfo.PROJECT_NAME_FEATURE_EXTENSION, "");
    _builder.append(_replace, "         ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("         ");
    _builder.append("download-size=\"0\"");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("install-size=\"0\"");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("version=\"0.0.0\"");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("unpack=\"false\"/>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("   ");
    _builder.append("<plugin");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("id=\"");
    String _replace_1 = builderInfo.getProjectName().replace(ConceptFeatureBuilderInfo.PROJECT_NAME_FEATURE_EXTENSION, ".ui");
    _builder.append(_replace_1, "         ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("         ");
    _builder.append("download-size=\"0\"");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("install-size=\"0\"");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("version=\"0.0.0\"");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("unpack=\"false\"/>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("</feature>");
    _builder.newLine();
    return _builder;
  }
}
