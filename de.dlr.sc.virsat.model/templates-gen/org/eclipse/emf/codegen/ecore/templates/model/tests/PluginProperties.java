package org.eclipse.emf.codegen.ecore.templates.model.tests;

import org.eclipse.emf.codegen.ecore.genmodel.*;

public class PluginProperties
{
  protected static String nl;
  public static synchronized PluginProperties create(String lineSeparator)
  {
    nl = lineSeparator;
    PluginProperties result = new PluginProperties();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "# ";
  protected final String TEXT_2 = "#";
  protected final String TEXT_3 = NL + NL + "pluginName = ";
  protected final String TEXT_4 = " Tests" + NL + "providerName = www.example.org";
  protected final String TEXT_5 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

    GenModel genModel = (GenModel)argument;
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_1);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_2);
    }}
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
