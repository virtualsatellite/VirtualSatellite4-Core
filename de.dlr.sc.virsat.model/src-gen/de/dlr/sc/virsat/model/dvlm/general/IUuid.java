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


import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IUuid</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This Interface provides a Universal Unique Identifier to the model
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.general.IUuid#getUuid <em>Uuid</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.general.GeneralPackage#getIUuid()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IUuid extends EObject {
	/**
	 * Returns the value of the '<em><b>Uuid</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uuid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uuid</em>' attribute.
	 * @see #setUuid(VirSatUuid)
	 * @see de.dlr.sc.virsat.model.dvlm.general.GeneralPackage#getIUuid_Uuid()
	 * @model default="" id="true" dataType="de.dlr.sc.virsat.model.dvlm.types.Uuid"
	 * @generated
	 */
	VirSatUuid getUuid();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.general.IUuid#getUuid <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uuid</em>' attribute.
	 * @see #getUuid()
	 * @generated
	 */
	void setUuid(VirSatUuid value);

} // IUuid
