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
public class ConceptFragmentTestManifestGenerator implements IFileContentGenerator {
  @Override
  public String generateContent(final IProjectBuilderInfo projectBuilderInfo) {
    return this.manifestContent(projectBuilderInfo).toString();
  }
  
  public String getProjectNameForTestPlugin(final IProjectBuilderInfo builderInfo) {
    return builderInfo.getProjectName().replace(".test", "");
  }
  
  public CharSequence manifestContent(final IProjectBuilderInfo builderInfo) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Manifest-Version: 1.0");
    _builder.newLine();
    _builder.append("Bundle-ManifestVersion: 2");
    _builder.newLine();
    _builder.append("Bundle-Name: VirSat Model Extension Concept Tests");
    _builder.newLine();
    _builder.append("Bundle-SymbolicName: ");
    String _projectName = builderInfo.getProjectName();
    _builder.append(_projectName);
    _builder.append(";singleton:=true");
    _builder.newLineIfNotEmpty();
    _builder.append("Bundle-Version: 0.0.1.qualifier");
    _builder.newLine();
    _builder.append("Fragment-Host: ");
    String _projectNameForTestPlugin = this.getProjectNameForTestPlugin(builderInfo);
    _builder.append(_projectNameForTestPlugin);
    _builder.append(";bundle-version=\"0.0.1\"");
    _builder.newLineIfNotEmpty();
    _builder.append("Bundle-ClassPath: .");
    _builder.newLine();
    _builder.append("Bundle-Vendor: DLR (German Aerospace Center)");
    _builder.newLine();
    _builder.append("Bundle-RequiredExecutionEnvironment: JavaSE-11");
    _builder.newLine();
    _builder.append("Bundle-ActivationPolicy: lazy");
    _builder.newLine();
    _builder.append("Require-Bundle: org.junit,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.emf.edit,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.emf.ecore.xmi,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("de.dlr.sc.virsat.model,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("de.dlr.sc.virsat.model.edit,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("de.dlr.sc.virsat.concept.unittest.util");
    _builder.newLine();
    _builder.append("Export-Package: ");
    String _projectName_1 = builderInfo.getProjectName();
    _builder.append(_projectName_1);
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
