/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.general;


import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IInstance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This interface should be atatched to everything which is considered a runtime instance of the DVLM. This can be StructuralElementInstance, CategoryAssignments or PropertyInstances. Definitions are no Instances and should never carry this interface.
 * <!-- end-model-doc -->
 *
 *
 * @see de.dlr.sc.virsat.model.dvlm.general.GeneralPackage#getIInstance()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IInstance extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This method hands back the full qualified path of the current instance in the DVLM. The FAN consists of all relevant names from the current object to its top container delimited by a "."
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getFullQualifiedInstanceName();

} // IInstance
