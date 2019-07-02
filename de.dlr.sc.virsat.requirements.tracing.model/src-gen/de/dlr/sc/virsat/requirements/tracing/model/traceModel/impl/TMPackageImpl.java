/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
/**
 */
package de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMFactory;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMPackage;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceabilityLinkContainer;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TMPackageImpl extends EPackageImpl implements TMPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceabilityLinkContainerEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TMPackageImpl() {
		super(eNS_URI, TMFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TMPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TMPackage init() {
		if (isInited) return (TMPackage)EPackage.Registry.INSTANCE.getEPackage(TMPackage.eNS_URI);

		// Obtain or create and register package
		TMPackageImpl theTMPackage = (TMPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TMPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TMPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		DVLMPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theTMPackage.createPackageContents();

		// Initialize created meta-data
		theTMPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTMPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TMPackage.eNS_URI, theTMPackage);
		return theTMPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTraceElement() {
		return traceElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceElement_SourceTraceElement() {
		return (EReference)traceElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceElement_TargetTraceElement() {
		return (EReference)traceElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTraceElement_ValidationEngineName() {
		return (EAttribute)traceElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTraceElement_Description() {
		return (EAttribute)traceElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTraceabilityLinkContainer() {
		return traceabilityLinkContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceabilityLinkContainer_TraceElements() {
		return (EReference)traceabilityLinkContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMFactory getTMFactory() {
		return (TMFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		traceElementEClass = createEClass(TRACE_ELEMENT);
		createEReference(traceElementEClass, TRACE_ELEMENT__SOURCE_TRACE_ELEMENT);
		createEReference(traceElementEClass, TRACE_ELEMENT__TARGET_TRACE_ELEMENT);
		createEAttribute(traceElementEClass, TRACE_ELEMENT__VALIDATION_ENGINE_NAME);
		createEAttribute(traceElementEClass, TRACE_ELEMENT__DESCRIPTION);

		traceabilityLinkContainerEClass = createEClass(TRACEABILITY_LINK_CONTAINER);
		createEReference(traceabilityLinkContainerEClass, TRACEABILITY_LINK_CONTAINER__TRACE_ELEMENTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		GeneralPackage theGeneralPackage = (GeneralPackage)EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		traceElementEClass.getESuperTypes().add(theGeneralPackage.getIUuid());
		traceElementEClass.getESuperTypes().add(theGeneralPackage.getIName());

		// Initialize classes, features, and operations; add parameters
		initEClass(traceElementEClass, TraceElement.class, "TraceElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTraceElement_SourceTraceElement(), ecorePackage.getEObject(), null, "sourceTraceElement", null, 0, 10, TraceElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTraceElement_TargetTraceElement(), ecorePackage.getEObject(), null, "targetTraceElement", null, 0, 10, TraceElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTraceElement_ValidationEngineName(), ecorePackage.getEString(), "ValidationEngineName", null, 0, 1, TraceElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTraceElement_Description(), ecorePackage.getEString(), "Description", null, 0, 1, TraceElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(traceabilityLinkContainerEClass, TraceabilityLinkContainer.class, "TraceabilityLinkContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTraceabilityLinkContainer_TraceElements(), this.getTraceElement(), null, "traceElements", null, 0, -1, TraceabilityLinkContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //TMPackageImpl
