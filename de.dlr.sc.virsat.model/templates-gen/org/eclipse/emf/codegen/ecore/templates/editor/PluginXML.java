package org.eclipse.emf.codegen.ecore.templates.editor;

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
  protected final String TEXT_8 = "\"" + NL + "      version=\"1.0.0\"" + NL + "      provider-name=\"%providerName\"" + NL + "      class=\"";
  protected final String TEXT_9 = "$Implementation\">" + NL + "" + NL + "   <requires>";
  protected final String TEXT_10 = NL + "      <import plugin=\"";
  protected final String TEXT_11 = "\"";
  protected final String TEXT_12 = " export=\"true\"";
  protected final String TEXT_13 = "/>";
  protected final String TEXT_14 = NL + "   </requires>" + NL + "" + NL + "   <runtime>";
  protected final String TEXT_15 = NL + "      <library name=\"";
  protected final String TEXT_16 = ".jar\">";
  protected final String TEXT_17 = NL + "      <library name=\".\">";
  protected final String TEXT_18 = NL + "         <export name=\"*\"/>" + NL + "      </library>" + NL + "   </runtime>";
  protected final String TEXT_19 = NL + NL + "   <extension point=\"org.eclipse.emf.ecore.generated_package\">";
  protected final String TEXT_20 = NL + "      <!-- @generated ";
  protected final String TEXT_21 = " -->";
  protected final String TEXT_22 = NL + "      <package" + NL + "            uri=\"";
  protected final String TEXT_23 = NL + "            class=\"";
  protected final String TEXT_24 = "\"" + NL + "            genModel=\"";
  protected final String TEXT_25 = "\"/>";
  protected final String TEXT_26 = NL + "   </extension>";
  protected final String TEXT_27 = NL + NL + "   <extension point=\"org.eclipse.emf.ecore.content_parser\">";
  protected final String TEXT_28 = NL + "      <parser" + NL + "            contentTypeIdentifier=\"";
  protected final String TEXT_29 = "\"" + NL + "            class=\"";
  protected final String TEXT_30 = "\"/>" + NL + "   </extension>" + NL + "" + NL + "   <extension point=\"org.eclipse.core.contenttype.contentTypes\">";
  protected final String TEXT_31 = NL + "      <content-type" + NL + "            base-type=\"";
  protected final String TEXT_32 = "\"" + NL + "            file-extensions=\"";
  protected final String TEXT_33 = "\"" + NL + "            id=\"";
  protected final String TEXT_34 = "\"" + NL + "            name=\"%_UI_";
  protected final String TEXT_35 = "_content_type\"" + NL + "            priority=\"normal\">" + NL + "         <describer class=\"org.eclipse.emf.ecore.xmi.impl.RootXMLContentHandlerImpl$Describer\">";
  protected final String TEXT_36 = NL + "            <parameter name=\"namespace\" value=\"";
  protected final String TEXT_37 = NL + "            <parameter name=\"kind\" value=\"xmi\"/>";
  protected final String TEXT_38 = NL + "         </describer>" + NL + "      </content-type>" + NL + "   </extension>";
  protected final String TEXT_39 = NL + NL + "   <extension point=\"org.eclipse.emf.ecore.extension_parser\">";
  protected final String TEXT_40 = NL + "      <parser" + NL + "            type=\"";
  protected final String TEXT_41 = NL + NL + "   <extension point=\"org.eclipse.emf.edit.itemProviderAdapterFactories\">";
  protected final String TEXT_42 = NL + "      <factory" + NL + "            uri=\"";
  protected final String TEXT_43 = "\"" + NL + "            supportedTypes=";
  protected final String TEXT_44 = NL + "              ";
  protected final String TEXT_45 = NL + NL + "   <extension point=\"org.eclipse.emf.edit.childCreationExtenders\">";
  protected final String TEXT_46 = NL + "      <extender" + NL + "            uri=\"";
  protected final String TEXT_47 = "$";
  protected final String TEXT_48 = NL + NL + "   <extension" + NL + "         point=\"org.eclipse.core.runtime.applications\"" + NL + "         id=\"";
  protected final String TEXT_49 = "Application\">";
  protected final String TEXT_50 = NL + "      <application>" + NL + "         <run class=\"";
  protected final String TEXT_51 = "$Application\"/>" + NL + "      </application>" + NL + "   </extension>" + NL + "" + NL + "   <extension point=\"org.eclipse.ui.perspectives\">";
  protected final String TEXT_52 = NL + "      <perspective" + NL + "            name=\"%_UI_Perspective_label\"" + NL + "            class=\"";
  protected final String TEXT_53 = "$Perspective\"" + NL + "            id=\"";
  protected final String TEXT_54 = "Perspective\">" + NL + "      </perspective>" + NL + "   </extension>" + NL + "" + NL + "   <extension point=\"org.eclipse.ui.commands\">";
  protected final String TEXT_55 = NL + "      <command" + NL + "            name=\"%_UI_Menu_OpenURI_label\"" + NL + "            description=\"%_UI_Menu_OpenURI_description\"" + NL + "            categoryId=\"org.eclipse.ui.category.file\"" + NL + "            id=\"";
  protected final String TEXT_56 = "OpenURICommand\"/>";
  protected final String TEXT_57 = NL + "      <command" + NL + "            name=\"%_UI_Menu_Open_label\"" + NL + "            description=\"%_UI_Menu_Open_description\"" + NL + "            categoryId=\"org.eclipse.ui.category.file\"" + NL + "            id=\"";
  protected final String TEXT_58 = "OpenCommand\"/>";
  protected final String TEXT_59 = NL + "   </extension>" + NL;
  protected final String TEXT_60 = NL + "   <extension point=\"org.eclipse.ui.bindings\">";
  protected final String TEXT_61 = NL + "      <key" + NL + "            commandId=\"";
  protected final String TEXT_62 = "OpenURICommand\"" + NL + "            sequence=\"M1+U\"" + NL + "            schemeId=\"org.eclipse.ui.defaultAcceleratorConfiguration\"/>" + NL + "      <key" + NL + "            commandId=\"";
  protected final String TEXT_63 = "OpenCommand\"" + NL + "            sequence=\"M1+O\"" + NL + "            schemeId=\"org.eclipse.ui.defaultAcceleratorConfiguration\"/>" + NL + "   </extension>";
  protected final String TEXT_64 = NL + NL + "   <extension point=\"org.eclipse.ui.actionSets\">";
  protected final String TEXT_65 = NL + "      <actionSet" + NL + "            label=\"%_UI_";
  protected final String TEXT_66 = "_ActionSet_label\"" + NL + "            visible=\"true\"" + NL + "            id=\"";
  protected final String TEXT_67 = "ActionSet\">" + NL + "         <action" + NL + "               label=\"%_UI_Menu_About_label\"" + NL + "               class=\"";
  protected final String TEXT_68 = "$AboutAction\"" + NL + "               menubarPath=\"help/additions\"" + NL + "               id=\"";
  protected final String TEXT_69 = "AboutAction\"/>" + NL + "         <action" + NL + "               label=\"%_UI_Menu_OpenURI_label\"" + NL + "               definitionId=\"";
  protected final String TEXT_70 = "OpenURICommand\"" + NL + "               class=\"";
  protected final String TEXT_71 = "$OpenURIAction\"" + NL + "               menubarPath=\"file/additions\"" + NL + "               id=\"";
  protected final String TEXT_72 = "OpenURIAction\"/>";
  protected final String TEXT_73 = NL + "         <action" + NL + "               label=\"%_UI_Menu_Open_label\"" + NL + "               definitionId=\"";
  protected final String TEXT_74 = "OpenCommand\"" + NL + "               class=\"";
  protected final String TEXT_75 = "$OpenAction\"" + NL + "               menubarPath=\"file/additions\"" + NL + "               id=\"";
  protected final String TEXT_76 = "OpenAction\"/>";
  protected final String TEXT_77 = NL + "      </actionSet>" + NL + "   </extension>";
  protected final String TEXT_78 = "ActionSet\">" + NL + "         <action" + NL + "               label=\"%_UI_";
  protected final String TEXT_79 = "_label\"" + NL + "               class=\"";
  protected final String TEXT_80 = "$NewAction\"" + NL + "               menubarPath=\"file/new/additions\"" + NL + "               id=\"";
  protected final String TEXT_81 = "NewAction\"/>" + NL + "      </actionSet>" + NL + "   </extension>";
  protected final String TEXT_82 = NL + NL + "   <extension point=\"org.eclipse.ui.newWizards\">";
  protected final String TEXT_83 = NL + "      <category" + NL + "            id=\"org.eclipse.emf.ecore.Wizard.category.ID\"" + NL + "            name=\"%_UI_Wizard_category\"/>" + NL + "      <wizard" + NL + "            id=\"";
  protected final String TEXT_84 = "ID\"" + NL + "            name=\"%_UI_";
  protected final String TEXT_85 = "_label\"" + NL + "            class=\"";
  protected final String TEXT_86 = "\"" + NL + "            category=\"org.eclipse.emf.ecore.Wizard.category.ID\"" + NL + "            icon=\"icons/full/obj16/";
  protected final String TEXT_87 = "ModelFile.gif\">" + NL + "         <description>%_UI_";
  protected final String TEXT_88 = "_description</description>" + NL + "         <selection class=\"org.eclipse.core.resources.IResource\"/>" + NL + "      </wizard>" + NL + "   </extension>";
  protected final String TEXT_89 = NL + NL + "   <extension point=\"org.eclipse.ui.editors\">";
  protected final String TEXT_90 = NL + "      <editor" + NL + "            id=\"";
  protected final String TEXT_91 = "_label\"" + NL + "            icon=\"icons/full/obj16/";
  protected final String TEXT_92 = "ModelFile.gif\"";
  protected final String TEXT_93 = NL + "            extensions=\"";
  protected final String TEXT_94 = "\"" + NL + "            contributorClass=\"";
  protected final String TEXT_95 = "\">";
  protected final String TEXT_96 = NL + "         <contentTypeBinding contentTypeId=\"";
  protected final String TEXT_97 = NL + "      </editor>" + NL + "   </extension>";
  protected final String TEXT_98 = NL + NL + "</plugin>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

    GenModel genModel = (GenModel)argument; String key = genModel.getPluginKey(); boolean hasKey = key != null && !key.equals("");
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
    stringBuffer.append(genModel.getEditorPluginID());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genModel.getQualifiedEditorPluginClassName());
    stringBuffer.append(TEXT_9);
    for (String pluginID : genModel.getEditorRequiredPlugins()) { 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(pluginID);
    stringBuffer.append(TEXT_11);
    if (!pluginID.startsWith("org.eclipse.core.runtime")) {
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    }
    stringBuffer.append(TEXT_14);
    if (genModel.isRuntimeJar()) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getEditorPluginID());
    stringBuffer.append(TEXT_16);
    } else {
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    }
    if (genModel.sameModelEditorProject()) {
    for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
    stringBuffer.append(TEXT_19);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_11);
    if (genModel.hasLocalGenModel()) {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genModel.getRelativeGenModelLocation());
    stringBuffer.append(TEXT_25);
    } else {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    if (genPackage.isContentType()) {
    stringBuffer.append(TEXT_27);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genPackage.getContentTypeIdentifier());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genPackage.getQualifiedEffectiveResourceFactoryClassName());
    stringBuffer.append(TEXT_30);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genPackage.isXMIResource() ? "org.eclipse.emf.ecore.xmi" : "org.eclipse.core.runtime.xml");
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genPackage.getFileExtensions());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genPackage.getContentTypeIdentifier());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_35);
    if (genPackage.hasTargetNamespace()) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_25);
    }
    if (genPackage.isXMIResource()) {
    stringBuffer.append(TEXT_37);
    }
    stringBuffer.append(TEXT_38);
    } else if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
    stringBuffer.append(TEXT_39);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    for (String fileExtension : genPackage.getFileExtensionList()) {
    stringBuffer.append(TEXT_40);
    stringBuffer.append(fileExtension);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genPackage.getQualifiedResourceFactoryClassName());
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    }
    }
    }
    if (genModel.sameEditEditorProject()) {
    for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
    if (!genPackage.getGenClasses().isEmpty()) {
    stringBuffer.append(TEXT_41);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genPackage.getQualifiedItemProviderAdapterFactoryClassName());
    stringBuffer.append(TEXT_43);
    for (ListIterator<?> j = genPackage.getProviderSupportedTypes().listIterator(); j.hasNext(); ) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(j.hasPrevious() ? " " : "\"");
    stringBuffer.append(j.next());
    if (!j.hasNext()) {
    stringBuffer.append(TEXT_25);
    }
    }
    stringBuffer.append(TEXT_26);
    if (genPackage.isChildCreationExtenders()) { Map<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> extendedChildCreationData = genPackage.getExtendedChildCreationData();
    if (!extendedChildCreationData.isEmpty()) {
    stringBuffer.append(TEXT_45);
    for (Map.Entry<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> entry : extendedChildCreationData.entrySet()) {
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(entry.getKey().getNSURI());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genPackage.getQualifiedItemProviderAdapterFactoryClassName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genPackage.getChildCreationExtenderName(entry.getKey()));
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    }
    }
    }
    }
    }
    if (genModel.isRichClientPlatform()) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_49);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_51);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_54);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_56);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_58);
    }
    stringBuffer.append(TEXT_59);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_60);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_63);
    }
    stringBuffer.append(TEXT_64);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_72);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_76);
    }
    stringBuffer.append(TEXT_77);
    }
    for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
    if (genPackage.hasConcreteClasses()){
    if (genPackage.isGenerateModelWizard()) {
    if (genModel.isRichClientPlatform()) {
    stringBuffer.append(TEXT_64);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_81);
    } else {
    stringBuffer.append(TEXT_82);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genPackage.getQualifiedModelWizardClassName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genPackage.getQualifiedModelWizardClassName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_88);
    }
    }
    stringBuffer.append(TEXT_89);
    if (hasKey) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genPackage.getQualifiedEditorClassName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_92);
    if (!genPackage.isContentType()) {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genPackage.getFileExtensions());
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getQualifiedEditorClassName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_95);
    if (genPackage.isContentType()) {
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genPackage.getQualifiedContentTypeIdentifier());
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_97);
    }
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
