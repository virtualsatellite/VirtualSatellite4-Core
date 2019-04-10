package org.eclipse.emf.codegen.ecore.templates.edit;

import java.util.*;
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
  protected final String TEXT_4 = " Edit Support" + NL + "providerName = www.example.org";
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + "_UI_CreateChild_text = {0}" + NL + "_UI_CreateChild_text2 = {1} ";
  protected final String TEXT_7 = "| ";
  protected final String TEXT_8 = "{0}" + NL + "_UI_CreateChild_text3 = {1}" + NL + "_UI_CreateChild_tooltip = Create New {0} Under {1} Feature" + NL + "_UI_CreateChild_description = Create a new child of type {0} for the {1} feature of the selected {2}." + NL + "_UI_CreateSibling_description = Create a new sibling of type {0} for the selected {2}, under the {1} feature of their parent." + NL;
  protected final String TEXT_9 = NL + "_UI_PropertyDescriptor_description = The {0} of the {1}" + NL;
  protected final String TEXT_10 = NL + "_UI_";
  protected final String TEXT_11 = "_type = ";
  protected final String TEXT_12 = NL + "_UI_Unknown_type = Object" + NL + "" + NL + "_UI_Unknown_datatype= Value" + NL;
  protected final String TEXT_13 = NL + "_UI_";
  protected final String TEXT_14 = "_";
  protected final String TEXT_15 = "_feature = ";
  protected final String TEXT_16 = NL + "_UI_";
  protected final String TEXT_17 = "_";
  protected final String TEXT_18 = "_description = ";
  protected final String TEXT_19 = NL + "_UI_Unknown_feature = Unspecified" + NL;
  protected final String TEXT_20 = NL + "_UI_";
  protected final String TEXT_21 = "_";
  protected final String TEXT_22 = "_literal = ";
  protected final String TEXT_23 = NL;
  protected final String TEXT_24 = " = ";
  protected final String TEXT_25 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

    GenModel genModel = (GenModel)argument; /* Trick to import java.util.* without warnings */Iterator.class.getName();
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
    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT) {
    stringBuffer.append(TEXT_5);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_6);
    if (genModel.isCreationSubmenus()) {
    stringBuffer.append(TEXT_7);
    }
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) { 
    for (GenClass genClass : genPackage.getGenClasses()) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genClass.getFormattedName());
    }
    }
    }
    stringBuffer.append(TEXT_12);
    for (GenFeature genFeature : genModel.getFilteredAllGenFeatures()) { String description = genFeature.getPropertyDescription();
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genFeature.getFormattedName());
    if (description != null && description.length() > 0) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(description);
    }
    }
    stringBuffer.append(TEXT_19);
    for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) {
    for (GenEnum genEnum : genPackage.getGenEnums()) {
    for (GenEnumLiteral genEnumLiteral : genEnum.getGenEnumLiterals()) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genEnum.getName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genEnumLiteral.getName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genEnumLiteral.getLiteral());
    }
    }
    }
    }
    for (String category : genModel.getPropertyCategories()) {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getPropertyCategoryKey(category));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(category);
    }
    }
    stringBuffer.append(TEXT_25);
    return stringBuffer.toString();
  }
}
