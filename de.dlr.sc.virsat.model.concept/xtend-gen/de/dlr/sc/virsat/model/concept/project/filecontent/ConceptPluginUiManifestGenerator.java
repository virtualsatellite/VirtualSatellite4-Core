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
public class ConceptPluginUiManifestGenerator implements IFileContentGenerator {
  @Override
  public String generateContent(final IProjectBuilderInfo projectBuilderInfo) {
    return this.manifestContent(projectBuilderInfo).toString();
  }
  
  public String getProjectNameForUiPlugin(final IProjectBuilderInfo builderInfo) {
    return builderInfo.getProjectName().replace(".ui", "");
  }
  
  public CharSequence manifestContent(final IProjectBuilderInfo builderInfo) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Manifest-Version: 1.0");
    _builder.newLine();
    _builder.append("Bundle-ManifestVersion: 2");
    _builder.newLine();
    _builder.append("Bundle-Name: VirSat Model Extension Concept UI");
    _builder.newLine();
    _builder.append("Bundle-SymbolicName: ");
    String _projectName = builderInfo.getProjectName();
    _builder.append(_projectName);
    _builder.append(";singleton:=true");
    _builder.newLineIfNotEmpty();
    _builder.append("Bundle-Version: 0.0.1.qualifier");
    _builder.newLine();
    _builder.append("Require-Bundle: ");
    String _projectNameForUiPlugin = this.getProjectNameForUiPlugin(builderInfo);
    _builder.append(_projectNameForUiPlugin);
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("org.eclipse.ui,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.core.runtime,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.core.expressions,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.emf.edit,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.emf.transaction,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.core.resources,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("de.dlr.sc.virsat.model,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("de.dlr.sc.virsat.project,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("de.dlr.sc.virsat.project.ui,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("de.dlr.sc.virsat.uiengine.ui");
    _builder.newLine();
    _builder.append("Bundle-RequiredExecutionEnvironment: JavaSE-11");
    _builder.newLine();
    _builder.append("Bundle-ActivationPolicy: lazy");
    _builder.newLine();
    return _builder;
  }
}
