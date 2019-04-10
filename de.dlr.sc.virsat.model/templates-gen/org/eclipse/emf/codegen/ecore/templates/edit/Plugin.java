package org.eclipse.emf.codegen.ecore.templates.edit;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class Plugin
{
  protected static String nl;
  public static synchronized Plugin create(String lineSeparator)
  {
    nl = lineSeparator;
    Plugin result = new Plugin();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = NL;
  protected final String TEXT_9 = NL + NL + "/**" + NL + " * This is the central singleton for the ";
  protected final String TEXT_10 = " edit plugin." + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public final class ";
  protected final String TEXT_11 = " extends EMFPlugin" + NL + "{";
  protected final String TEXT_12 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_13 = " copyright = ";
  protected final String TEXT_14 = ";";
  protected final String TEXT_15 = NL;
  protected final String TEXT_16 = NL + NL + "\t/**" + NL + "\t * *********************************" + NL + "\t * VirSat Specific Code Generation" + NL + "\t * *********************************" + NL + "\t * Keep track of the image registry." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final HashMap<String, URL> imageRegistry = new HashMap<String, URL>();" + NL + "" + NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_17 = " INSTANCE = new ";
  protected final String TEXT_18 = "();" + NL;
  protected final String TEXT_19 = NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static Implementation plugin;" + NL;
  protected final String TEXT_20 = NL + "\t/**" + NL + "\t * Create the instance." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_21 = "()" + NL + "\t{" + NL + "\t\tsuper" + NL + "\t\t  (new ResourceLocator [] " + NL + "\t\t   {";
  protected final String TEXT_22 = NL + "\t\t     ";
  protected final String TEXT_23 = ".INSTANCE,";
  protected final String TEXT_24 = NL + "\t\t   });" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Returns the singleton instance of the Eclipse plugin." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_25 = NL + "\t@Override";
  protected final String TEXT_26 = NL + "\tpublic ResourceLocator getPluginResourceLocator()" + NL + "\t{";
  protected final String TEXT_27 = NL + "\t\treturn null;";
  protected final String TEXT_28 = NL + "\t\treturn plugin;";
  protected final String TEXT_29 = NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * *********************************" + NL + "\t * VirSat Specific Code Generation" + NL + "\t * *********************************" + NL + "\t * Getter for the Image registry of the Edit Plugin." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return A Hashmap with registered ConceptImages" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static HashMap<String, URL> getImageRegistry() {" + NL + "\t\treturn imageRegistry;" + NL + "\t}" + NL + "\t" + NL;
  protected final String TEXT_30 = NL + "\t/**" + NL + "\t * Returns the singleton instance of the Eclipse plugin." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance." + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static Implementation getPlugin()" + NL + "\t{" + NL + "\t\treturn plugin;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * The actual implementation of the Eclipse <b>Plugin</b>." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static class Implementation extends EclipsePlugin" + NL + "\t{" + NL + "\t\tpublic static final String EXTENSION_POINT_ID_CONCEPT_IMAGE = \"de.dlr.sc.virsat.model.edit.ConceptImageContribution\";" + NL + "\t\t" + NL + "\t\t/**" + NL + "\t\t * Creates an instance." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->";
  protected final String TEXT_31 = NL + "\t\t * @param descriptor the description of the plugin.";
  protected final String TEXT_32 = NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic Implementation(";
  protected final String TEXT_33 = " descriptor";
  protected final String TEXT_34 = ")" + NL + "\t\t{" + NL + "\t\t\tsuper(";
  protected final String TEXT_35 = "descriptor";
  protected final String TEXT_36 = ");" + NL + "" + NL + "\t\t\t// Remember the static instance." + NL + "\t\t\t//" + NL + "\t\t\tplugin = this;" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t@Override" + NL + "\t\tpublic void start(BundleContext context) throws Exception {" + NL + "\t\t\tsuper.start(context);" + NL + "\t\t\treadConceptImageExtensionPoints();" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t/**" + NL + "\t\t * *********************************" + NL + "\t \t * VirSat Specific Code Generation" + NL + "\t     * *********************************" + NL + "\t\t * call this method to read the extension point for the concept images" + NL + "\t\t * It registers the found images in the bundles ImgageRegistry" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * " + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic void readConceptImageExtensionPoints() {" + NL + "\t\t\tIExtensionRegistry registry = Platform.getExtensionRegistry();" + NL + "\t\t\t" + NL + "\t\t\t// Read all concept images through the extension point definitions in the various concept plugins" + NL + "\t\t\tIConfigurationElement[] conceptImagesConfigurations = registry.getConfigurationElementsFor(EXTENSION_POINT_ID_CONCEPT_IMAGE);" + NL + "\t\t\t" + NL + "\t\t\tfor (IConfigurationElement configElement : conceptImagesConfigurations) {" + NL + "\t\t\t\tString key = configElement.getAttribute(\"fullQualifiedID\");" + NL + "\t\t\t\tString pathToImage = configElement.getAttribute(\"pathToImage\");" + NL + "\t\t\t\tString bundleID = configElement.getContributor().getName();" + NL + "\t\t\t\t" + NL + "\t\t\t\tBundle bundle = Platform.getBundle(bundleID);" + NL + "\t\t        IPath path = new Path(pathToImage);" + NL + "\t\t        URL url = FileLocator.find(bundle, path, null);" + NL + "\t\t\t\t" + NL + "\t\t\t\timageRegistry.put(key, url);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t@Override" + NL + "\t\tpublic void stop(BundleContext context) throws Exception {" + NL + "\t\t\tsuper.stop(context);" + NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_37 = NL + "\t" + NL + "\t\t/**" + NL + "\t\t * The actual implementation of the purely OSGi-compatible <b>Bundle Activator</b>." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic static final class Activator extends ";
  protected final String TEXT_38 = ".OSGiDelegatingBundleActivator" + NL + "\t\t{";
  protected final String TEXT_39 = NL + "\t\t\t@Override";
  protected final String TEXT_40 = NL + "\t\t\tprotected ";
  protected final String TEXT_41 = " createBundle()" + NL + "\t\t\t{" + NL + "\t\t\t\treturn new Implementation();" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_42 = NL + "\t}" + NL;
  protected final String TEXT_43 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_44 = " PROPERTIES = ";
  protected final String TEXT_45 = ".create(";
  protected final String TEXT_46 = ".class);" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic String getString(String key, boolean translate)" + NL + "\t{";
  protected final String TEXT_47 = NL + "\t\t";
  protected final String TEXT_48 = "else ";
  protected final String TEXT_49 = "if (\"_UI_";
  protected final String TEXT_50 = "_type\".equals(key)) return PROPERTIES.";
  protected final String TEXT_51 = "Type();";
  protected final String TEXT_52 = NL + "\t\t";
  protected final String TEXT_53 = "else ";
  protected final String TEXT_54 = " if (\"_UI_Unknown_type\".equals(key)) return PROPERTIES.unknownType();" + NL + "\t\telse if (\"_UI_Unknown_datatype\".equals(key)) return PROPERTIES.unknownDatatype();";
  protected final String TEXT_55 = NL + "\t\telse if (\"_UI_";
  protected final String TEXT_56 = "_";
  protected final String TEXT_57 = "_feature\".equals(key)) return PROPERTIES.";
  protected final String TEXT_58 = "_";
  protected final String TEXT_59 = "Feature();";
  protected final String TEXT_60 = NL + "\t\telse if (\"_UI_";
  protected final String TEXT_61 = "_";
  protected final String TEXT_62 = "_description\".equals(key)) return PROPERTIES.";
  protected final String TEXT_63 = "_";
  protected final String TEXT_64 = "Description();";
  protected final String TEXT_65 = NL + "\t\telse if (\"_UI_Unknown_feature\".equals(key)) return PROPERTIES.unknownFeature();";
  protected final String TEXT_66 = NL + "\t\telse if (\"_UI_";
  protected final String TEXT_67 = "_";
  protected final String TEXT_68 = "_literal\".equals(key)) return PROPERTIES.";
  protected final String TEXT_69 = "_";
  protected final String TEXT_70 = "Literal();";
  protected final String TEXT_71 = NL;
  protected final String TEXT_72 = " = ";
  protected final String TEXT_73 = NL + "\t\telse if (\"";
  protected final String TEXT_74 = "\".equals(key)) return PROPERTIES.";
  protected final String TEXT_75 = "();";
  protected final String TEXT_76 = NL + "\t\telse return key;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic String getString(String key, Object [] substitutions, boolean translate)" + NL + "\t{";
  protected final String TEXT_77 = NL + "\t\tif (\"_UI_CreateChild_text\".equals(key)) return PROPERTIES.createChildText(substitutions[0]);" + NL + "\t\telse if (\"_UI_CreateChild_text2\".equals(key)) return PROPERTIES.createChildText2(substitutions[0], substitutions[1]);" + NL + "\t\telse if (\"_UI_CreateChild_text3\".equals(key)) return PROPERTIES.createChildText3(substitutions[1]);" + NL + "\t\telse if (\"_UI_CreateChild_tooltip\".equals(key)) return PROPERTIES.createChildTooltip(substitutions[0], substitutions[1]);" + NL + "\t\telse if (\"_UI_CreateChild_description\".equals(key)) return PROPERTIES.createChildDescripition(substitutions[0], substitutions[1], substitutions[2]);" + NL + "\t\telse if (\"_UI_CreateSibling_description\".equals(key)) return PROPERTIES.createSiblingDescription(substitutions[0], substitutions[1], substitutions[2]);";
  protected final String TEXT_78 = NL + "\t\t";
  protected final String TEXT_79 = "else ";
  protected final String TEXT_80 = "if (\"_UI_PropertyDescriptor_description\".equals(key)) return PROPERTIES.propertyDescriptorDescription(substitutions[0], substitutions[1]);" + NL + "\t\telse return key;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_81 = " IMAGES = ";
  protected final String TEXT_82 = ".create(";
  protected final String TEXT_83 = ".class);" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic Object getImage(String key)" + NL + "\t{";
  protected final String TEXT_84 = NL + "\t\t";
  protected final String TEXT_85 = "else ";
  protected final String TEXT_86 = "if (\"";
  protected final String TEXT_87 = "\".equals(key)) return IMAGES.";
  protected final String TEXT_88 = "();";
  protected final String TEXT_89 = NL + "\t\t";
  protected final String TEXT_90 = "else ";
  protected final String TEXT_91 = "return key;" + NL + "\t}" + NL;
  protected final String TEXT_92 = NL + "}";
  protected final String TEXT_93 = NL;

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
 *   DLR - customization for Virtual Satellite
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
    genModel.addImport("java.net.URL");
    genModel.addImport("java.util.HashMap");
    genModel.addImport("org.eclipse.core.runtime.FileLocator");
    genModel.addImport("org.eclipse.core.runtime.IConfigurationElement");
    genModel.addImport("org.eclipse.core.runtime.IExtensionRegistry");
    genModel.addImport("org.eclipse.core.runtime.IPath");
    genModel.addImport("org.eclipse.core.runtime.Path");
    genModel.addImport("org.eclipse.core.runtime.Platform");
    stringBuffer.append(TEXT_6);
    genModel.addImport("org.eclipse.emf.common.EMFPlugin");
    genModel.addImport("org.eclipse.emf.common.util.ResourceLocator");
    stringBuffer.append(TEXT_7);
    genModel.addImport("org.osgi.framework.Bundle");
    genModel.addImport("org.osgi.framework.BundleContext");
    stringBuffer.append(TEXT_8);
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_11);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_18);
    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT) {
    stringBuffer.append(TEXT_19);
    }
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_21);
    for (String pluginClassName : genModel.getEditResourceDelegateImportedPluginClassNames()) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(pluginClassName);
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT) {
    stringBuffer.append(TEXT_27);
    } else {
    stringBuffer.append(TEXT_28);
    }
    stringBuffer.append(TEXT_29);
    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT) {
    stringBuffer.append(TEXT_30);
    if (genModel.needsRuntimeCompatibility()) {
    stringBuffer.append(TEXT_31);
    }
    stringBuffer.append(TEXT_32);
    if (genModel.needsRuntimeCompatibility()) {
    stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.IPluginDescriptor"));
    stringBuffer.append(TEXT_33);
    }
    stringBuffer.append(TEXT_34);
    if (genModel.needsRuntimeCompatibility()) {
    stringBuffer.append(TEXT_35);
    }
    stringBuffer.append(TEXT_36);
    if (genModel.isOSGiCompatible()) {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.EMFPlugin"));
    stringBuffer.append(TEXT_38);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getImportedName("org.osgi.framework.BundleActivator"));
    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    } else {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Properties"));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.core.client.GWT"));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Properties"));
    stringBuffer.append(TEXT_46);
    boolean first = true; for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) { 
    for (GenClass genClass : genPackage.getGenClasses()) {
    stringBuffer.append(TEXT_47);
    if (first) { first = false; } else {
    stringBuffer.append(TEXT_48);
    }
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genClass.getUncapName());
    stringBuffer.append(TEXT_51);
    }
    }
    }
    stringBuffer.append(TEXT_52);
    if (first) { first = false; } else {
    stringBuffer.append(TEXT_53);
    }
    stringBuffer.append(TEXT_54);
    for (GenFeature genFeature : genModel.getFilteredAllGenFeatures()) { String description = genFeature.getPropertyDescription();
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getGenClass().getUncapName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_59);
    if (description != null && description.length() > 0) {
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genFeature.getGenClass().getUncapName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_64);
    }
    }
    stringBuffer.append(TEXT_65);
    for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) {
    for (GenEnum genEnum : genPackage.getGenEnums()) {
    for (GenEnumLiteral genEnumLiteral : genEnum.getGenEnumLiterals()) {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genEnum.getName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genEnumLiteral.getName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genEnum.getSafeUncapName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genEnumLiteral.getName());
    stringBuffer.append(TEXT_70);
    }
    }
    }
    }
    for (String category : genModel.getPropertyCategories()) {
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genModel.getPropertyCategoryKey(category));
    stringBuffer.append(TEXT_72);
    stringBuffer.append(category);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genModel.getPropertyCategoryKey(category));
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genModel.getPropertyCategoryKey(category));
    stringBuffer.append(TEXT_75);
    }
    stringBuffer.append(TEXT_76);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_77);
    }
    stringBuffer.append(TEXT_78);
    if (!genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_79);
    }
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Images"));
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.core.client.GWT"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Images"));
    stringBuffer.append(TEXT_83);
    first = true; for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (genClass.isImage()) { String image = genClass.getItemIconFileName(); image = image.substring(image.lastIndexOf("/icons/") + 7, image.length() - 4); 
    stringBuffer.append(TEXT_84);
    if (first) { first = false; } else {
    stringBuffer.append(TEXT_85);
    }
    stringBuffer.append(TEXT_86);
    stringBuffer.append(image);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genClass.getItemIconAccessorName());
    stringBuffer.append(TEXT_88);
    }
    }
    }
    stringBuffer.append(TEXT_89);
    if (first) { first = false; } else {
    stringBuffer.append(TEXT_90);
    }
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_92);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_93);
    return stringBuffer.toString();
  }
}
