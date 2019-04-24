package org.eclipse.emf.codegen.ecore.templates.model.tests;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class PluginXML
{
  protected static String nl;
  public static synchronized PluginXML create(String lineSeparator)
  {
    nl = lineSeparator;
    PluginXML result = new PluginXML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<?eclipse version=\"3.0\"?>" + NL;
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "<!--";
  protected final String TEXT_4 = NL + " ";
  protected final String TEXT_5 = NL + "-->" + NL;
  protected final String TEXT_6 = NL + "<plugin>";
  protected final String TEXT_7 = NL + "<plugin" + NL + "      name=\"%pluginName\"" + NL + "      id=\"";
  protected final String TEXT_8 = "\"" + NL + "      version=\"1.0.0\"" + NL + "      provider-name=\"%providerName\">" + NL + "" + NL + "   <requires>";
  protected final String TEXT_9 = NL + "      <import plugin=\"";
  protected final String TEXT_10 = "\"";
  protected final String TEXT_11 = " export=\"true\"";
  protected final String TEXT_12 = "/>";
  protected final String TEXT_13 = NL + "   </requires>" + NL + "" + NL + "   <runtime>";
  protected final String TEXT_14 = NL + "      <library name=\"";
  protected final String TEXT_15 = ".jar\">";
  protected final String TEXT_16 = NL + "      <library name=\".\">";
  protected final String TEXT_17 = NL + "         <export name=\"*\"/>" + NL + "      </library>" + NL + "   </runtime>" + NL;
  protected final String TEXT_18 = NL + "</plugin>";
  protected final String TEXT_19 = NL;

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

    GenModel genModel = (GenModel)argument; /* Trick to import java.util.* without warnings */Iterator.class.getName();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_4);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_5);
    if (genModel.isBundleManifest()) {
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getTestsPluginID());
    stringBuffer.append(TEXT_8);
    for (String pluginID : genModel.getTestsRequiredPlugins()) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(pluginID);
    stringBuffer.append(TEXT_10);
    if (!pluginID.startsWith("org.eclipse.core.runtime")) {
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    if (genModel.isRuntimeJar()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getTestsPluginID());
    stringBuffer.append(TEXT_15);
    } else {
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TEXT_19);
    return stringBuffer.toString();
  }
}
