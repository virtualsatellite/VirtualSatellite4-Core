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
public class ContentBuildProperties {
  public CharSequence getContents() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("bin.includes = \t.,\\");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("META-INF/");
    _builder.newLine();
    _builder.append("source.. = apps/");
    _builder.newLine();
    _builder.append("output.. = target/classes/");
    _builder.newLine();
    return _builder;
  }
}
