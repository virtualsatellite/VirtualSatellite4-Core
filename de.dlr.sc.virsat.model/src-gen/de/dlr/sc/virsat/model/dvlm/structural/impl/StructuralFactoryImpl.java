/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.impl;

import de.dlr.sc.virsat.model.dvlm.structural.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StructuralFactoryImpl extends EFactoryImpl implements StructuralFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return an initialized instance of 'StructuralFactory'.
	 * @generated
	 */
	public static StructuralFactory init() {
		try {
			StructuralFactory theStructuralFactory = (StructuralFactory)EPackage.Registry.INSTANCE.getEFactory(StructuralPackage.eNS_URI);
			if (theStructuralFactory != null) {
				return theStructuralFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StructuralFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case StructuralPackage.STRUCTURAL_ELEMENT: return createStructuralElement();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE: return createStructuralElementInstance();
			case StructuralPackage.GENERAL_RELATION: return createGeneralRelation();
			case StructuralPackage.RELATION_INSTANCE: return createRelationInstance();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralElement createStructuralElement() {
		StructuralElementImpl structuralElement = new StructuralElementImpl();
		return structuralElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralElementInstance createStructuralElementInstance() {
		StructuralElementInstanceImpl structuralElementInstance = new StructuralElementInstanceImpl();
		return structuralElementInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralRelation createGeneralRelation() {
		GeneralRelationImpl generalRelation = new GeneralRelationImpl();
		return generalRelation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationInstance createRelationInstance() {
		RelationInstanceImpl relationInstance = new RelationInstanceImpl();
		return relationInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralPackage getStructuralPackage() {
		return (StructuralPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @return an instance of StructuralPackage.
	 * @generated
	 */
	@Deprecated
	public static StructuralPackage getPackage() {
		return StructuralPackage.eINSTANCE;
	}

} //StructuralFactoryImpl
