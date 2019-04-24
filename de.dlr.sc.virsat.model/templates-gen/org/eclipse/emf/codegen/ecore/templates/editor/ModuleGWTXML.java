package org.eclipse.emf.codegen.ecore.templates.editor;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ModuleGWTXML
{
  protected static String nl;
  public static synchronized ModuleGWTXML create(String lineSeparator)
  {
    nl = lineSeparator;
    ModuleGWTXML result = new ModuleGWTXML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<!DOCTYPE module PUBLIC \"-//Google Inc.//DTD Google Web Toolkit 2.0.1//EN\" \"http://google-web-toolkit.googlecode.com/svn/tags/2.0.1/distro-source/core/src/gwt-module.dtd\">" + NL;
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "<!--";
  protected final String TEXT_4 = NL + " ";
  protected final String TEXT_5 = NL + "-->" + NL + "" + NL + "<module>" + NL + "  <inherits name='com.google.gwt.user.theme.standard.Standard'/>";
  protected final String TEXT_6 = NL + "  <inherits name=\"";
  protected final String TEXT_7 = "\" />";
  protected final String TEXT_8 = NL + NL + "  <entry-point class='";
  protected final String TEXT_9 = "'/>" + NL;
  protected final String TEXT_10 = NL + "  <source path=\"";
  protected final String TEXT_11 = "\"/>";
  protected final String TEXT_12 = NL + "</module>";
  protected final String TEXT_13 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2010 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Ed Merks - Initial API and implementation
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
    for (String inherits : genModel.getEditorModuleInherits()) {
    stringBuffer.append(TEXT_6);
    stringBuffer.append(inherits);
    stringBuffer.append(TEXT_7);
    }
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genModel.getQualifiedEditorEntryPointClassName());
    stringBuffer.append(TEXT_9);
    for (String source : genModel.getEditorModuleSources()) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(source);
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    stringBuffer.append(TEXT_13);
    return stringBuffer.toString();
  }
}
