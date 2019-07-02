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
package de.dlr.sc.virsat.requirements.tracing.model.traceModel;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMFactory
 * @model kind="package"
 * @generated
 */
public interface TMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "traceModel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/requirements/tracing/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TMPackage eINSTANCE = de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TMPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceElementImpl <em>Trace Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceElementImpl
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TMPackageImpl#getTraceElement()
	 * @generated
	 */
	int TRACE_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_ELEMENT__UUID = GeneralPackage.IUUID__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_ELEMENT__NAME = GeneralPackage.IUUID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Trace Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_ELEMENT__SOURCE_TRACE_ELEMENT = GeneralPackage.IUUID_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target Trace Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_ELEMENT__TARGET_TRACE_ELEMENT = GeneralPackage.IUUID_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Validation Engine Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_ELEMENT__VALIDATION_ENGINE_NAME = GeneralPackage.IUUID_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_ELEMENT__DESCRIPTION = GeneralPackage.IUUID_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Trace Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_ELEMENT_FEATURE_COUNT = GeneralPackage.IUUID_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Trace Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_ELEMENT_OPERATION_COUNT = GeneralPackage.IUUID_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceabilityLinkContainerImpl <em>Traceability Link Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceabilityLinkContainerImpl
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TMPackageImpl#getTraceabilityLinkContainer()
	 * @generated
	 */
	int TRACEABILITY_LINK_CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Trace Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABILITY_LINK_CONTAINER__TRACE_ELEMENTS = 0;

	/**
	 * The number of structural features of the '<em>Traceability Link Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABILITY_LINK_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Traceability Link Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABILITY_LINK_CONTAINER_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement <em>Trace Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trace Element</em>'.
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement
	 * @generated
	 */
	EClass getTraceElement();

	/**
	 * Returns the meta object for the reference list '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getSourceTraceElement <em>Source Trace Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Source Trace Element</em>'.
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getSourceTraceElement()
	 * @see #getTraceElement()
	 * @generated
	 */
	EReference getTraceElement_SourceTraceElement();

	/**
	 * Returns the meta object for the reference list '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getTargetTraceElement <em>Target Trace Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Target Trace Element</em>'.
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getTargetTraceElement()
	 * @see #getTraceElement()
	 * @generated
	 */
	EReference getTraceElement_TargetTraceElement();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getValidationEngineName <em>Validation Engine Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Validation Engine Name</em>'.
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getValidationEngineName()
	 * @see #getTraceElement()
	 * @generated
	 */
	EAttribute getTraceElement_ValidationEngineName();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement#getDescription()
	 * @see #getTraceElement()
	 * @generated
	 */
	EAttribute getTraceElement_Description();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceabilityLinkContainer <em>Traceability Link Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Traceability Link Container</em>'.
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceabilityLinkContainer
	 * @generated
	 */
	EClass getTraceabilityLinkContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceabilityLinkContainer#getTraceElements <em>Trace Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Trace Elements</em>'.
	 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceabilityLinkContainer#getTraceElements()
	 * @see #getTraceabilityLinkContainer()
	 * @generated
	 */
	EReference getTraceabilityLinkContainer_TraceElements();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TMFactory getTMFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceElementImpl <em>Trace Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceElementImpl
		 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TMPackageImpl#getTraceElement()
		 * @generated
		 */
		EClass TRACE_ELEMENT = eINSTANCE.getTraceElement();

		/**
		 * The meta object literal for the '<em><b>Source Trace Element</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE_ELEMENT__SOURCE_TRACE_ELEMENT = eINSTANCE.getTraceElement_SourceTraceElement();

		/**
		 * The meta object literal for the '<em><b>Target Trace Element</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE_ELEMENT__TARGET_TRACE_ELEMENT = eINSTANCE.getTraceElement_TargetTraceElement();

		/**
		 * The meta object literal for the '<em><b>Validation Engine Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACE_ELEMENT__VALIDATION_ENGINE_NAME = eINSTANCE.getTraceElement_ValidationEngineName();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACE_ELEMENT__DESCRIPTION = eINSTANCE.getTraceElement_Description();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceabilityLinkContainerImpl <em>Traceability Link Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceabilityLinkContainerImpl
		 * @see de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TMPackageImpl#getTraceabilityLinkContainer()
		 * @generated
		 */
		EClass TRACEABILITY_LINK_CONTAINER = eINSTANCE.getTraceabilityLinkContainer();

		/**
		 * The meta object literal for the '<em><b>Trace Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACEABILITY_LINK_CONTAINER__TRACE_ELEMENTS = eINSTANCE.getTraceabilityLinkContainer_TraceElements();

	}

} //TMPackage
