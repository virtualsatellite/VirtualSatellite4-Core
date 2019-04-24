package org.eclipse.emf.codegen.ecore.templates.editor;

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
  protected final String TEXT_4 = " Editor" + NL + "providerName = www.example.org";
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + "_UI_";
  protected final String TEXT_7 = "_menu = &";
  protected final String TEXT_8 = " Editor";
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = NL + "_UI_CreateChild_menu_item = &New Child" + NL + "_UI_CreateSibling_menu_item = N&ew Sibling" + NL;
  protected final String TEXT_11 = NL + "_UI_ShowPropertiesView_menu_item = Show &Properties View" + NL + "_UI_RefreshViewer_menu_item = &Refresh" + NL + "" + NL + "_UI_SelectionPage_label = Selection";
  protected final String TEXT_12 = NL + "_UI_ParentPage_label = Parent" + NL + "_UI_ListPage_label = List" + NL + "_UI_TreePage_label = Tree" + NL + "_UI_TablePage_label = Table" + NL + "_UI_TreeWithColumnsPage_label = Tree with Columns" + NL + "_UI_ObjectColumn_label = Object" + NL + "_UI_SelfColumn_label = Self";
  protected final String TEXT_13 = NL + NL + "_UI_NoObjectSelected = Selected Nothing" + NL + "_UI_SingleObjectSelected = Selected Object: {0}" + NL + "_UI_MultiObjectSelected = Selected {0} Objects" + NL + "" + NL + "_UI_OpenEditorError_label = Open Editor" + NL + "" + NL + "_UI_Wizard_category = Example EMF Model Creation Wizards" + NL + "" + NL + "_UI_CreateModelError_message = Problems encountered in file \"{0}\"" + NL;
  protected final String TEXT_14 = NL + "_UI_";
  protected final String TEXT_15 = "_label = ";
  protected final String TEXT_16 = " Model" + NL + "_UI_";
  protected final String TEXT_17 = "_description = Create a new ";
  protected final String TEXT_18 = " model" + NL;
  protected final String TEXT_19 = NL + "_UI_";
  protected final String TEXT_20 = "_label = ";
  protected final String TEXT_21 = " Model Editor" + NL + "" + NL + "_UI_";
  protected final String TEXT_22 = "FilenameDefaultBase = My" + NL + "_UI_";
  protected final String TEXT_23 = "FilenameExtensions = ";
  protected final String TEXT_24 = NL;
  protected final String TEXT_25 = NL + "_UI_Wizard_label = New" + NL + "" + NL + "_WARN_FilenameExtension = The file name must end in ''.{0}''" + NL + "_WARN_FilenameExtensions = The file name must have one of the following extensions: {0}" + NL + "" + NL + "_UI_ModelObject = &Model Object" + NL + "_UI_XMLEncoding = &XML Encoding" + NL + "_UI_XMLEncodingChoices = ";
  protected final String TEXT_26 = NL + "_UI_Wizard_initial_object_description = Specify a file name and select a model object to create";
  protected final String TEXT_27 = NL + "_UI_Wizard_initial_object_description = Select a model object to create";
  protected final String TEXT_28 = NL + NL + "_UI_FileConflict_label = File Conflict" + NL + "_WARN_FileConflict = There are unsaved changes that conflict with changes made outside the editor.  Do you wish to discard this editor's changes?" + NL;
  protected final String TEXT_29 = NL + "_UI_";
  protected final String TEXT_30 = "_ActionSet_label = ";
  protected final String TEXT_31 = " Action Set" + NL + "_UI_Perspective_label = ";
  protected final String TEXT_32 = NL + NL + "_UI_Browse_label = &Browse..." + NL + "_UI_File_label = &File" + NL + "_UI_Question_title = Question" + NL + "_WARN_FileConflict = The file \"{0}\" already exists.  Do you want to replace the existing file?" + NL + "_UI_Error_title = Error" + NL + "_WARN_No_Editor = There is no editor registered for the file \"{0}\"." + NL + "" + NL + "_UI_Application_title = ";
  protected final String TEXT_33 = " Application" + NL + "_UI_Menu_File_label = &File" + NL + "_UI_Menu_New_label = &New" + NL + "_UI_Menu_Open_label = &Open..." + NL + "_UI_Menu_Open_description = Opens a model object file" + NL + "_UI_Menu_OpenURI_label = &Open URI..." + NL + "_UI_Menu_OpenURI_description = Opens a model object loading it from a URI" + NL + "_UI_Menu_Edit_label = &Edit" + NL + "_UI_Menu_Window_label = &Window" + NL + "_UI_Menu_Help_label = &Help" + NL + "_UI_Menu_About_label = ";
  protected final String TEXT_34 = " &About..." + NL + "_UI_About_title = ";
  protected final String TEXT_35 = " Application" + NL + "_UI_About_text = ";
  protected final String TEXT_36 = " Application about box goes here." + NL;
  protected final String TEXT_37 = NL + "_UI_";
  protected final String TEXT_38 = "_ActionSet_label = ";
  protected final String TEXT_39 = " Model Action Set";
  protected final String TEXT_40 = NL;
  protected final String TEXT_41 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
    boolean hasMultiPageEditor = false;
    for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {if (genPackage.isMultipleEditorPages()) { hasMultiPageEditor = true; }
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    if (hasMultiPageEditor) {
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
    if (genPackage.isGenerateModelWizard()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getFileExtensions());
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getXMLEncodingChoices());
    if (genModel.isRichClientPlatform()) {
    stringBuffer.append(TEXT_26);
    } else {
    stringBuffer.append(TEXT_27);
    }
    stringBuffer.append(TEXT_28);
    if (genModel.isRichClientPlatform()) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_36);
    for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {if (genPackage.isGenerateModelWizard()) {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_39);
    }}
    stringBuffer.append(TEXT_40);
    }
    }
    stringBuffer.append(TEXT_41);
    return stringBuffer.toString();
  }
}
