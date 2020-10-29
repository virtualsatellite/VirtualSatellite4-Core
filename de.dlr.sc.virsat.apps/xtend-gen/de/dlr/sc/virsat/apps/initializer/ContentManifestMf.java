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
public class ContentManifestMf {
  public CharSequence getContents(final String projectName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Manifest-Version: 1.0");
    _builder.newLine();
    _builder.append("Bundle-ManifestVersion: 2");
    _builder.newLine();
    _builder.append("Bundle-Name: ");
    _builder.append(projectName);
    _builder.newLineIfNotEmpty();
    _builder.append("Bundle-SymbolicName: ");
    _builder.append(projectName);
    _builder.newLineIfNotEmpty();
    _builder.append("Bundle-Version: 1.0.0.qualifier");
    _builder.newLine();
    _builder.append("Bundle-RequiredExecutionEnvironment: JavaSE-1.8");
    _builder.newLine();
    _builder.append("Require-Bundle: de.dlr.sc.virsat.apps");
    _builder.newLine();
    _builder.append("Automatic-Module-Name: ");
    _builder.append(projectName);
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
