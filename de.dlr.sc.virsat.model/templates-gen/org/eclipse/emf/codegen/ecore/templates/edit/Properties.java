package org.eclipse.emf.codegen.ecore.templates.edit;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class Properties
{
  protected static String nl;
  public static synchronized Properties create(String lineSeparator)
  {
    nl = lineSeparator;
    Properties result = new Properties();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public interface ";
  protected final String TEXT_7 = "Properties extends ";
  protected final String TEXT_8 = NL + "{";
  protected final String TEXT_9 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_CreateChild_text\")" + NL + "\t@DefaultMessage(\"{0}\")" + NL + "\tString createChildText(Object type);" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_CreateChild_text2\")" + NL + "\t@DefaultMessage(\"{1} ";
  protected final String TEXT_10 = "| ";
  protected final String TEXT_11 = "{0}\")" + NL + "\tString createChildText2(Object type, Object feature);" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_CreateChild_text3\")" + NL + "\t@DefaultMessage(\"{0}\")" + NL + "\tString createChildText3(Object feature);" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_CreateChild_tooltip\")" + NL + "\t@DefaultMessage(\"Create New {0} Under {1} Feature\")" + NL + "\tString createChildTooltip(Object type, Object feature);" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_CreateChild_description\")" + NL + "\t@DefaultMessage(\"Create a new child of type {0} for the {1} feature of the selected {2}.\")" + NL + "\tString createChildDescripition(Object type, Object feature, Object selection);" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_CreateSibling_description\")" + NL + "\t@DefaultMessage(\"Create a new sibling of type {0} for the selected {2}, under the {1} feature of their parent.\")" + NL + "\tString createSiblingDescription(Object type, Object feature, Object selection);" + NL;
  protected final String TEXT_12 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_PropertyDescriptor_description\")" + NL + "\t@DefaultMessage(\"The {0} of the {1}\")" + NL + "\tString propertyDescriptorDescription(Object feature, Object type);" + NL;
  protected final String TEXT_13 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_";
  protected final String TEXT_14 = "_type\")" + NL + "\t@DefaultMessage(\"";
  protected final String TEXT_15 = "\")" + NL + "\tString ";
  protected final String TEXT_16 = "Type();" + NL;
  protected final String TEXT_17 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_Unknown_type\")" + NL + "\t@DefaultMessage(\"Object\")" + NL + "\tString unknownType();" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_Unknown_datatype\")" + NL + "\t@DefaultMessage(\"Value\")" + NL + "\tString unknownDatatype();" + NL;
  protected final String TEXT_18 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_";
  protected final String TEXT_19 = "_";
  protected final String TEXT_20 = "_feature\")" + NL + "\t@DefaultMessage(\"";
  protected final String TEXT_21 = "\")" + NL + "\tString ";
  protected final String TEXT_22 = "_";
  protected final String TEXT_23 = "Feature();" + NL;
  protected final String TEXT_24 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_";
  protected final String TEXT_25 = "_";
  protected final String TEXT_26 = "_description\")" + NL + "\t@DefaultMessage(\"";
  protected final String TEXT_27 = "\")" + NL + "\tString ";
  protected final String TEXT_28 = "_";
  protected final String TEXT_29 = "Description();" + NL;
  protected final String TEXT_30 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_Unknown_feature\")" + NL + "\t@DefaultMessage(\"Unspecified\")" + NL + "\tString unknownFeature();" + NL;
  protected final String TEXT_31 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"_UI_";
  protected final String TEXT_32 = "_";
  protected final String TEXT_33 = "_literal\")" + NL + "\t@DefaultMessage(\"";
  protected final String TEXT_34 = "\")" + NL + "\tString ";
  protected final String TEXT_35 = "_";
  protected final String TEXT_36 = "Literal();" + NL;
  protected final String TEXT_37 = NL;
  protected final String TEXT_38 = " = ";
  protected final String TEXT_39 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Key(\"";
  protected final String TEXT_40 = "\")" + NL + "\t@DefaultMessage(\"";
  protected final String TEXT_41 = "\")" + NL + "\tString ";
  protected final String TEXT_42 = "();" + NL;
  protected final String TEXT_43 = NL + "}";
  protected final String TEXT_44 = NL;

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
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genModel.getEditPluginPackageName());
    stringBuffer.append(TEXT_5);
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.i18n.client.Messages"));
    stringBuffer.append(TEXT_8);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_9);
    if (genModel.isCreationSubmenus()) {
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) { 
    for (GenClass genClass : genPackage.getGenClasses()) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genClass.getUncapName());
    stringBuffer.append(TEXT_16);
    }
    }
    }
    stringBuffer.append(TEXT_17);
    for (GenFeature genFeature : genModel.getFilteredAllGenFeatures()) { String description = genFeature.getPropertyDescription();
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genFeature.getGenClass().getUncapName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_23);
    if (description != null && description.length() > 0) {
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(description);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genFeature.getGenClass().getUncapName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_29);
    }
    }
    stringBuffer.append(TEXT_30);
    for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) {
    for (GenEnum genEnum : genPackage.getGenEnums()) {
    for (GenEnumLiteral genEnumLiteral : genEnum.getGenEnumLiterals()) {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genEnum.getName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genEnumLiteral.getName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genEnumLiteral.getLiteral());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genEnum.getSafeUncapName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genEnumLiteral.getName());
    stringBuffer.append(TEXT_36);
    }
    }
    }
    }
    for (String category : genModel.getPropertyCategories()) {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getPropertyCategoryKey(category));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(category);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getPropertyCategoryKey(category));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(category);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genModel.getPropertyCategoryKey(category));
    stringBuffer.append(TEXT_42);
    }
    stringBuffer.append(TEXT_43);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_44);
    return stringBuffer.toString();
  }
}
