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
  protected final String TEXT_23 = "\"";
  protected final String TEXT_24 = NL + "            class=\"";
  protected final String TEXT_25 = "\"" + NL + "            genModel=\"";
  protected final String TEXT_26 = "\"/>";
  protected final String TEXT_27 = NL + "            class=\"";
  protected final String TEXT_28 = "\"/>";
  protected final String TEXT_29 = NL + "   </extension>";
  protected final String TEXT_30 = NL + NL + "   <extension point=\"org.eclipse.emf.ecore.content_parser\">";
  protected final String TEXT_31 = NL + "      <!-- @generated ";
  protected final String TEXT_32 = " -->";
  protected final String TEXT_33 = NL + "      <parser" + NL + "            contentTypeIdentifier=\"";
  protected final String TEXT_34 = "\"" + NL + "            class=\"";
  protected final String TEXT_35 = "\"/>" + NL + "   </extension>" + NL + "" + NL + "   <extension point=\"org.eclipse.core.contenttype.contentTypes\">";
  protected final String TEXT_36 = NL + "      <!-- @generated ";
  protected final String TEXT_37 = " -->";
  protected final String TEXT_38 = NL + "      <content-type" + NL + "            base-type=\"";
  protected final String TEXT_39 = "\"" + NL + "            file-extensions=\"";
  protected final String TEXT_40 = "\"" + NL + "            id=\"";
  protected final String TEXT_41 = "\"" + NL + "            name=\"%_UI_";
  protected final String TEXT_42 = "_content_type\"" + NL + "            priority=\"normal\">" + NL + "         <describer class=\"org.eclipse.emf.ecore.xmi.impl.RootXMLContentHandlerImpl$Describer\">";
  protected final String TEXT_43 = NL + "            <parameter name=\"namespace\" value=\"";
  protected final String TEXT_44 = "\"/>";
  protected final String TEXT_45 = NL + "            <parameter name=\"kind\" value=\"xmi\"/>";
  protected final String TEXT_46 = NL + "         </describer>" + NL + "      </content-type>" + NL + "   </extension>";
  protected final String TEXT_47 = NL + NL + "   <extension point=\"org.eclipse.emf.ecore.extension_parser\">";
  protected final String TEXT_48 = NL + "      <!-- @generated ";
  protected final String TEXT_49 = " -->";
  protected final String TEXT_50 = NL + "      <parser" + NL + "            type=\"";
  protected final String TEXT_51 = "\"" + NL + "            class=\"";
  protected final String TEXT_52 = "\"/>";
  protected final String TEXT_53 = NL + "   </extension>";
  protected final String TEXT_54 = NL + NL + "   <extension point=\"org.eclipse.emf.edit.itemProviderAdapterFactories\">";
  protected final String TEXT_55 = NL + "      <!-- @generated ";
  protected final String TEXT_56 = " -->";
  protected final String TEXT_57 = NL + "      <factory" + NL + "            uri=\"";
  protected final String TEXT_58 = "\"" + NL + "            class=\"";
  protected final String TEXT_59 = "\"" + NL + "            supportedTypes=";
  protected final String TEXT_60 = NL + "              ";
  protected final String TEXT_61 = "\"/>";
  protected final String TEXT_62 = NL + "   </extension>";
  protected final String TEXT_63 = NL + NL + "   <extension point=\"org.eclipse.emf.edit.childCreationExtenders\">";
  protected final String TEXT_64 = NL + "      <!-- @generated ";
  protected final String TEXT_65 = " -->";
  protected final String TEXT_66 = NL + "      <extender" + NL + "            uri=\"";
  protected final String TEXT_67 = "\"" + NL + "            class=\"";
  protected final String TEXT_68 = "$";
  protected final String TEXT_69 = "\"/>";
  protected final String TEXT_70 = NL + "   </extension>";
  protected final String TEXT_71 = NL + NL + "   <extension" + NL + "         point=\"org.eclipse.core.runtime.applications\"" + NL + "         id=\"";
  protected final String TEXT_72 = "Application\">";
  protected final String TEXT_73 = NL + "      <!-- @generated ";
  protected final String TEXT_74 = " -->";
  protected final String TEXT_75 = NL + "      <application>" + NL + "         <run class=\"";
  protected final String TEXT_76 = "$Application\"/>" + NL + "      </application>" + NL + "   </extension>" + NL + "" + NL + "   <extension point=\"org.eclipse.ui.perspectives\">";
  protected final String TEXT_77 = NL + "      <!-- @generated ";
  protected final String TEXT_78 = " -->";
  protected final String TEXT_79 = NL + "      <perspective" + NL + "            name=\"%_UI_Perspective_label\"" + NL + "            class=\"";
  protected final String TEXT_80 = "$Perspective\"" + NL + "            id=\"";
  protected final String TEXT_81 = "Perspective\">" + NL + "      </perspective>" + NL + "   </extension>" + NL + "" + NL + "   <extension point=\"org.eclipse.ui.commands\">";
  protected final String TEXT_82 = NL + "      <!-- @generated ";
  protected final String TEXT_83 = " -->";
  protected final String TEXT_84 = NL + "      <command" + NL + "            name=\"%_UI_Menu_OpenURI_label\"" + NL + "            description=\"%_UI_Menu_OpenURI_description\"" + NL + "            categoryId=\"org.eclipse.ui.category.file\"" + NL + "            id=\"";
  protected final String TEXT_85 = "OpenURICommand\"/>";
  protected final String TEXT_86 = NL + "      <command" + NL + "            name=\"%_UI_Menu_Open_label\"" + NL + "            description=\"%_UI_Menu_Open_description\"" + NL + "            categoryId=\"org.eclipse.ui.category.file\"" + NL + "            id=\"";
  protected final String TEXT_87 = "OpenCommand\"/>";
  protected final String TEXT_88 = NL + "   </extension>" + NL;
  protected final String TEXT_89 = NL + "   <extension point=\"org.eclipse.ui.bindings\">";
  protected final String TEXT_90 = NL + "      <!-- @generated ";
  protected final String TEXT_91 = " -->";
  protected final String TEXT_92 = NL + "      <key" + NL + "            commandId=\"";
  protected final String TEXT_93 = "OpenURICommand\"" + NL + "            sequence=\"M1+U\"" + NL + "            schemeId=\"org.eclipse.ui.defaultAcceleratorConfiguration\"/>" + NL + "      <key" + NL + "            commandId=\"";
  protected final String TEXT_94 = "OpenCommand\"" + NL + "            sequence=\"M1+O\"" + NL + "            schemeId=\"org.eclipse.ui.defaultAcceleratorConfiguration\"/>" + NL + "   </extension>";
  protected final String TEXT_95 = NL + NL + "   <extension point=\"org.eclipse.ui.actionSets\">";
  protected final String TEXT_96 = NL + "      <!-- @generated ";
  protected final String TEXT_97 = " -->";
  protected final String TEXT_98 = NL + "      <actionSet" + NL + "            label=\"%_UI_";
  protected final String TEXT_99 = "_ActionSet_label\"" + NL + "            visible=\"true\"" + NL + "            id=\"";
  protected final String TEXT_100 = "ActionSet\">" + NL + "         <action" + NL + "               label=\"%_UI_Menu_About_label\"" + NL + "               class=\"";
  protected final String TEXT_101 = "$AboutAction\"" + NL + "               menubarPath=\"help/additions\"" + NL + "               id=\"";
  protected final String TEXT_102 = "AboutAction\"/>" + NL + "         <action" + NL + "               label=\"%_UI_Menu_OpenURI_label\"" + NL + "               definitionId=\"";
  protected final String TEXT_103 = "OpenURICommand\"" + NL + "               class=\"";
  protected final String TEXT_104 = "$OpenURIAction\"" + NL + "               menubarPath=\"file/additions\"" + NL + "               id=\"";
  protected final String TEXT_105 = "OpenURIAction\"/>";
  protected final String TEXT_106 = NL + "         <action" + NL + "               label=\"%_UI_Menu_Open_label\"" + NL + "               definitionId=\"";
  protected final String TEXT_107 = "OpenCommand\"" + NL + "               class=\"";
  protected final String TEXT_108 = "$OpenAction\"" + NL + "               menubarPath=\"file/additions\"" + NL + "               id=\"";
  protected final String TEXT_109 = "OpenAction\"/>";
  protected final String TEXT_110 = NL + "      </actionSet>" + NL + "   </extension>";
  protected final String TEXT_111 = NL + NL + "   <extension point=\"org.eclipse.ui.actionSets\">";
  protected final String TEXT_112 = NL + "      <!-- @generated ";
  protected final String TEXT_113 = " -->";
  protected final String TEXT_114 = NL + "      <actionSet" + NL + "            label=\"%_UI_";
  protected final String TEXT_115 = "_ActionSet_label\"" + NL + "            visible=\"true\"" + NL + "            id=\"";
  protected final String TEXT_116 = "ActionSet\">" + NL + "         <action" + NL + "               label=\"%_UI_";
  protected final String TEXT_117 = "_label\"" + NL + "               class=\"";
  protected final String TEXT_118 = "$NewAction\"" + NL + "               menubarPath=\"file/new/additions\"" + NL + "               id=\"";
  protected final String TEXT_119 = "NewAction\"/>" + NL + "      </actionSet>" + NL + "   </extension>";
  protected final String TEXT_120 = NL + NL + "   <extension point=\"org.eclipse.ui.newWizards\">";
  protected final String TEXT_121 = NL + "      <!-- @generated ";
  protected final String TEXT_122 = " -->";
  protected final String TEXT_123 = NL + "      <category" + NL + "            id=\"org.eclipse.emf.ecore.Wizard.category.ID\"" + NL + "            name=\"%_UI_Wizard_category\"/>" + NL + "      <wizard" + NL + "            id=\"";
  protected final String TEXT_124 = "ID\"" + NL + "            name=\"%_UI_";
  protected final String TEXT_125 = "_label\"" + NL + "            class=\"";
  protected final String TEXT_126 = "\"" + NL + "            category=\"org.eclipse.emf.ecore.Wizard.category.ID\"" + NL + "            icon=\"icons/full/obj16/";
  protected final String TEXT_127 = "ModelFile.gif\">" + NL + "         <description>%_UI_";
  protected final String TEXT_128 = "_description</description>" + NL + "         <selection class=\"org.eclipse.core.resources.IResource\"/>" + NL + "      </wizard>" + NL + "   </extension>";
  protected final String TEXT_129 = NL + NL + "   <extension point=\"org.eclipse.ui.editors\">";
  protected final String TEXT_130 = NL + "      <!-- @generated ";
  protected final String TEXT_131 = " -->";
  protected final String TEXT_132 = NL + "      <editor" + NL + "            id=\"";
  protected final String TEXT_133 = "ID\"" + NL + "            name=\"%_UI_";
  protected final String TEXT_134 = "_label\"" + NL + "            icon=\"icons/full/obj16/";
  protected final String TEXT_135 = "ModelFile.gif\"";
  protected final String TEXT_136 = NL + "            extensions=\"";
  protected final String TEXT_137 = "\"";
  protected final String TEXT_138 = NL + "            class=\"";
  protected final String TEXT_139 = "\"" + NL + "            contributorClass=\"";
  protected final String TEXT_140 = "\">";
  protected final String TEXT_141 = NL + "         <contentTypeBinding contentTypeId=\"";
  protected final String TEXT_142 = "\"/>";
  protected final String TEXT_143 = NL + "      </editor>" + NL + "   </extension>";
  protected final String TEXT_144 = NL + NL + "</plugin>";
  protected final String TEXT_145 = NL;

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
    stringBuffer.append(TEXT_23);
    if (genModel.hasLocalGenModel()) {
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getRelativeGenModelLocation());
    stringBuffer.append(TEXT_26);
    } else {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_28);
    }
    stringBuffer.append(TEXT_29);
    if (genPackage.isContentType()) {
    stringBuffer.append(TEXT_30);
    if (hasKey) {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_32);
    }
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genPackage.getContentTypeIdentifier());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genPackage.getQualifiedEffectiveResourceFactoryClassName());
    stringBuffer.append(TEXT_35);
    if (hasKey) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_37);
    }
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genPackage.isXMIResource() ? "org.eclipse.emf.ecore.xmi" : "org.eclipse.core.runtime.xml");
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genPackage.getFileExtensions());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genPackage.getContentTypeIdentifier());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_42);
    if (genPackage.hasTargetNamespace()) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_44);
    }
    if (genPackage.isXMIResource()) {
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_46);
    } else if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
    stringBuffer.append(TEXT_47);
    if (hasKey) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_49);
    }
    for (String fileExtension : genPackage.getFileExtensionList()) {
    stringBuffer.append(TEXT_50);
    stringBuffer.append(fileExtension);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genPackage.getQualifiedResourceFactoryClassName());
    stringBuffer.append(TEXT_52);
    }
    stringBuffer.append(TEXT_53);
    }
    }
    }
    if (genModel.sameEditEditorProject()) {
    for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
    if (!genPackage.getGenClasses().isEmpty()) {
    stringBuffer.append(TEXT_54);
    if (hasKey) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_56);
    }
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genPackage.getQualifiedItemProviderAdapterFactoryClassName());
    stringBuffer.append(TEXT_59);
    for (ListIterator<?> j = genPackage.getProviderSupportedTypes().listIterator(); j.hasNext(); ) {
    stringBuffer.append(TEXT_60);
    stringBuffer.append(j.hasPrevious() ? " " : "\"");
    stringBuffer.append(j.next());
    if (!j.hasNext()) {
    stringBuffer.append(TEXT_61);
    }
    }
    stringBuffer.append(TEXT_62);
    if (genPackage.isChildCreationExtenders()) { Map<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> extendedChildCreationData = genPackage.getExtendedChildCreationData();
    if (!extendedChildCreationData.isEmpty()) {
    stringBuffer.append(TEXT_63);
    for (Map.Entry<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> entry : extendedChildCreationData.entrySet()) {
    if (hasKey) {
    stringBuffer.append(TEXT_64);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_65);
    }
    stringBuffer.append(TEXT_66);
    stringBuffer.append(entry.getKey().getNSURI());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genPackage.getQualifiedItemProviderAdapterFactoryClassName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genPackage.getChildCreationExtenderName(entry.getKey()));
    stringBuffer.append(TEXT_69);
    }
    stringBuffer.append(TEXT_70);
    }
    }
    }
    }
    }
    if (genModel.isRichClientPlatform()) {
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_72);
    if (hasKey) {
    stringBuffer.append(TEXT_73);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_74);
    }
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_76);
    if (hasKey) {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_78);
    }
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_81);
    if (hasKey) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_85);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_87);
    }
    stringBuffer.append(TEXT_88);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_89);
    if (hasKey) {
    stringBuffer.append(TEXT_90);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_94);
    }
    stringBuffer.append(TEXT_95);
    if (hasKey) {
    stringBuffer.append(TEXT_96);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_97);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_105);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_109);
    }
    stringBuffer.append(TEXT_110);
    }
    for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
    if (genPackage.hasConcreteClasses()){
    if (genPackage.isGenerateModelWizard()) {
    if (genModel.isRichClientPlatform()) {
    stringBuffer.append(TEXT_111);
    if (hasKey) {
    stringBuffer.append(TEXT_112);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_113);
    }
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_119);
    } else {
    stringBuffer.append(TEXT_120);
    if (hasKey) {
    stringBuffer.append(TEXT_121);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_122);
    }
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genPackage.getQualifiedModelWizardClassName());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genPackage.getQualifiedModelWizardClassName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_128);
    }
    }
    stringBuffer.append(TEXT_129);
    if (hasKey) {
    stringBuffer.append(TEXT_130);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_131);
    }
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genPackage.getQualifiedEditorClassName());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_135);
    if (!genPackage.isContentType()) {
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genPackage.getFileExtensions());
    stringBuffer.append(TEXT_137);
    }
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genPackage.getQualifiedEditorClassName());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_140);
    if (genPackage.isContentType()) {
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genPackage.getQualifiedContentTypeIdentifier());
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_143);
    }
    }
    stringBuffer.append(TEXT_144);
    stringBuffer.append(TEXT_145);
    return stringBuffer.toString();
  }
}
