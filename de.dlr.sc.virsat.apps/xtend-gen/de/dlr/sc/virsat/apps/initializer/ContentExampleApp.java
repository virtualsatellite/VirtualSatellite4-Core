/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.apps.initializer;

import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ContentExampleApp {
  public CharSequence getContents(final String appName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/*******************************************************************************");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* This program and the accompanying materials are made available under the");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* terms of the Eclipse Public License 2.0 which is available at");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* http://www.eclipse.org/legal/epl-2.0.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* SPDX-License-Identifier: EPL-2.0");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*******************************************************************************/");
    _builder.newLine();
    _builder.append("import de.dlr.sc.virsat.model.dvlm.Repository;");
    _builder.newLine();
    _builder.append("import de.dlr.sc.virsat.apps.api.external.ModelAPI;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Example App");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @author VirSat");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(appName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("public static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ModelAPI modelAPI = new ModelAPI();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Repository repository = modelAPI.getRepository();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("System.out.println(\"Infos of Repository: \" + repository);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("System.out.println(\"Currently Stored Units\");");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("modelAPI.getUnitManagement().getSystemOfUnit().getUnit().forEach((unit) -> System.out.println(unit));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
