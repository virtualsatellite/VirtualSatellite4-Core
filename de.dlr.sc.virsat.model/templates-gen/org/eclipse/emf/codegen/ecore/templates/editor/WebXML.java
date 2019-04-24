package org.eclipse.emf.codegen.ecore.templates.editor;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class WebXML
{
  protected static String nl;
  public static synchronized WebXML create(String lineSeparator)
  {
    nl = lineSeparator;
    WebXML result = new WebXML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<!DOCTYPE web-app PUBLIC \"-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN\" \"http://java.sun.com/dtd/web-app_2_3.dtd\">";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "<!--";
  protected final String TEXT_4 = NL + " ";
  protected final String TEXT_5 = NL + "-->" + NL + "<web-app>" + NL + "  <servlet>" + NL + "    <servlet-name>uriServlet</servlet-name>" + NL + "    <servlet-class>org.eclipse.emf.server.ecore.resource.URIServiceImpl</servlet-class>" + NL + "  </servlet>" + NL + "  " + NL + "  <servlet-mapping>" + NL + "    <servlet-name>uriServlet</servlet-name>" + NL + "    <url-pattern>/";
  protected final String TEXT_6 = "/uriService</url-pattern>" + NL + "  </servlet-mapping>" + NL + "" + NL + "  <welcome-file-list>" + NL + "    <welcome-file>";
  protected final String TEXT_7 = ".html</welcome-file>" + NL + "  </welcome-file-list>" + NL + "</web-app>";
  protected final String TEXT_8 = NL;

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
    stringBuffer.append(genModel.getQualifiedEditorModuleName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getEditorHomePageName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    return stringBuffer.toString();
  }
}
