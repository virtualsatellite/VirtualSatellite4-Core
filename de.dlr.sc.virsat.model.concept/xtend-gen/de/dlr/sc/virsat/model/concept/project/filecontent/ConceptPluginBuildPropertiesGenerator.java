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
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * Class to create the content for the Manifest of the Concept Plugin
 */
@SuppressWarnings("all")
public class ConceptPluginBuildPropertiesGenerator implements IFileContentGenerator {
  @Override
  public String generateContent(final IProjectBuilderInfo projectBuilderInfo) {
    return this.buildProeprtiesContent(projectBuilderInfo).toString();
  }
  
  public CharSequence buildProeprtiesContent(final IProjectBuilderInfo builderInfo) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("source.. = \tconcept/,\\");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("src/,\\");
    _builder.newLine();
    _builder.append("       \t\t");
    _builder.append("src-gen/,\\");
    _builder.newLine();
    _builder.append("       \t\t");
    _builder.append("xtend-gen/");
    _builder.newLine();
    _builder.append("output.. = \ttarget/classes/");
    _builder.newLine();
    _builder.append("bin.includes = META-INF/,\\");
    _builder.newLine();
    _builder.append("           \t\t");
    _builder.append(".,\\");
    _builder.newLine();
    _builder.append("           \t\t");
    _builder.append("plugin.xml,\\");
    _builder.newLine();
    _builder.append("           \t\t");
    _builder.append("concept/");
    _builder.newLine();
    _builder.append("additional.bundles = org.eclipse.emf.mwe2.runtime,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.xtend.typesystem.emf,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.xpand,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.xtend,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.apache.commons.logging,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.jdt.core,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("com.ibm.icu,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.antlr.runtime,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.core.runtime,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.emf.mwe.utils,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.emf.ecore.xmi,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.jface.text,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.xpand,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.xtend,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.xtend.typesystem.emf,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.emf.mwe2.launch,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.apache.log4j,\\");
    _builder.newLine();
    _builder.append("                     ");
    _builder.append("org.eclipse.core.resources");
    _builder.newLine();
    return _builder;
  }
}
