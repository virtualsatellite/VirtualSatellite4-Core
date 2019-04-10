package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class AdapterFactoryClass
{
  protected static String nl;
  public static synchronized AdapterFactoryClass create(String lineSeparator)
  {
    nl = lineSeparator;
    AdapterFactoryClass result = new AdapterFactoryClass();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Adapter Factory</b> for the model." + NL + " * It provides an adapter <code>createXXX</code> method for each class of the model." + NL + " * <!-- end-user-doc -->" + NL + " * @see ";
  protected final String TEXT_7 = NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_8 = " extends AdapterFactoryImpl" + NL + "{";
  protected final String TEXT_9 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_10 = " copyright = ";
  protected final String TEXT_11 = ";";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "\t/**" + NL + "\t * The cached model package." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static ";
  protected final String TEXT_14 = " modelPackage;" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the adapter factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_15 = "()" + NL + "\t{" + NL + "\t\tif (modelPackage == null)" + NL + "\t\t{" + NL + "\t\t\tmodelPackage = ";
  protected final String TEXT_16 = ".eINSTANCE;" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Returns whether this factory is applicable for the type of the object." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model." + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether this factory is applicable for the type of the object." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_17 = NL + "\t@Override";
  protected final String TEXT_18 = NL + "\tpublic boolean isFactoryForType(Object object)" + NL + "\t{" + NL + "\t\tif (object == modelPackage)" + NL + "\t\t{" + NL + "\t\t\treturn true;" + NL + "\t\t}" + NL + "\t\tif (object instanceof EObject)" + NL + "\t\t{" + NL + "\t\t\treturn ((EObject)object).eClass().getEPackage() == modelPackage;" + NL + "\t\t}" + NL + "\t\treturn false;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * The switch that delegates to the <code>createXXX</code> methods." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_19 = " modelSwitch =" + NL + "\t\tnew ";
  protected final String TEXT_20 = "()" + NL + "\t\t{";
  protected final String TEXT_21 = NL + "\t\t\t@Override";
  protected final String TEXT_22 = NL + "\t\t\tpublic ";
  protected final String TEXT_23 = " case";
  protected final String TEXT_24 = "(";
  protected final String TEXT_25 = " object)" + NL + "\t\t\t{" + NL + "\t\t\t\treturn create";
  protected final String TEXT_26 = "Adapter();" + NL + "\t\t\t}";
  protected final String TEXT_27 = NL + "\t\t\t@Override";
  protected final String TEXT_28 = NL + "\t\t\tpublic ";
  protected final String TEXT_29 = " defaultCase(EObject object)" + NL + "\t\t\t{" + NL + "\t\t\t\treturn create";
  protected final String TEXT_30 = "Adapter();" + NL + "\t\t\t}" + NL + "\t\t};" + NL + "" + NL + "\t/**" + NL + "\t * Creates an adapter for the <code>target</code>." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param target the object to adapt." + NL + "\t * @return the adapter for the <code>target</code>." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_31 = NL + "\t@Override";
  protected final String TEXT_32 = NL + "\tpublic Adapter createAdapter(Notifier target)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_33 = "modelSwitch.doSwitch((EObject)target);" + NL + "\t}" + NL;
  protected final String TEXT_34 = NL + NL + "\t/**" + NL + "\t * Creates a new adapter for an object of class '{@link ";
  protected final String TEXT_35 = " <em>";
  protected final String TEXT_36 = "</em>}'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * This default implementation returns null so that we can easily ignore cases;" + NL + "\t * it's useful to ignore a case when inheritance will catch all the cases anyway." + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the new adapter." + NL + "\t * @see ";
  protected final String TEXT_37 = NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Adapter create";
  protected final String TEXT_38 = "Adapter()" + NL + "\t{" + NL + "\t\treturn null;" + NL + "\t}";
  protected final String TEXT_39 = NL + NL + "\t/**" + NL + "\t * Creates a new adapter for the default case." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * This default implementation returns null." + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the new adapter." + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Adapter create";
  protected final String TEXT_40 = "Adapter()" + NL + "\t{" + NL + "\t\treturn null;" + NL + "\t}" + NL + "" + NL + "} //";
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

    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel(); /* Trick to import java.util.* without warnings */Iterator.class.getName();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getUtilitiesPackageName());
    stringBuffer.append(TEXT_5);
    genModel.addImport("org.eclipse.emf.common.notify.Adapter");
    genModel.addImport("org.eclipse.emf.common.notify.Notifier");
    genModel.addImport("org.eclipse.emf.common.notify.impl.AdapterFactoryImpl");
    genModel.addImport("org.eclipse.emf.ecore.EObject");
    if (!genPackage.hasJavaLangConflict() && !genPackage.getUtilitiesPackageName().equals(genPackage.getInterfacePackageName())) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    String typeArgument = genModel.useGenerics() ? "<Adapter>" : "";
    String returnType = genModel.useGenerics() ? "Adapter" : genModel.getImportedName("java.lang.Object");
    String adapterCast = genModel.useGenerics() ? "" : "(Adapter)";
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getAdapterFactoryClassName());
    stringBuffer.append(TEXT_8);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genPackage.getAdapterFactoryClassName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_16);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getSwitchClassName());
    stringBuffer.append(typeArgument);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getSwitchClassName());
    stringBuffer.append(typeArgument);
    stringBuffer.append(TEXT_20);
    for (GenClass genClass : genPackage.getAllSwitchGenClasses()) {
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genClass.getTypeParameters());
    stringBuffer.append(returnType);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getClassUniqueName(genClass));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getClassUniqueName(genClass));
    stringBuffer.append(TEXT_26);
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_27);
    }
    stringBuffer.append(TEXT_28);
    stringBuffer.append(returnType);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genPackage.getClassUniqueName(null));
    stringBuffer.append(TEXT_30);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_31);
    }
    stringBuffer.append(TEXT_32);
    stringBuffer.append(adapterCast);
    stringBuffer.append(TEXT_33);
    for (GenClass genClass : genPackage.getAllSwitchGenClasses()) {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genPackage.getClassUniqueName(genClass));
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genPackage.getClassUniqueName(null));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genPackage.getAdapterFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_41);
    return stringBuffer.toString();
  }
}
