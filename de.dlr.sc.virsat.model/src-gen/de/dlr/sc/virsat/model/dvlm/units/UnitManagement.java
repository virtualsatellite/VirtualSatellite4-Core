/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.units;


import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unit Management</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.units.UnitManagement#getSystemOfUnit <em>System Of Unit</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.units.UnitsPackage#getUnitManagement()
 * @model
 * @generated
 */
public interface UnitManagement extends IAssignedDiscipline, IUuid {
	/**
	 * Returns the value of the '<em><b>System Of Unit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Of Unit</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Of Unit</em>' containment reference.
	 * @see #setSystemOfUnit(SystemOfUnits)
	 * @see de.dlr.sc.virsat.model.dvlm.units.UnitsPackage#getUnitManagement_SystemOfUnit()
	 * @model containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	SystemOfUnits getSystemOfUnit();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.units.UnitManagement#getSystemOfUnit <em>System Of Unit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Of Unit</em>' containment reference.
	 * @see #getSystemOfUnit()
	 * @generated
	 */
	void setSystemOfUnit(SystemOfUnits value);

} // UnitManagement
