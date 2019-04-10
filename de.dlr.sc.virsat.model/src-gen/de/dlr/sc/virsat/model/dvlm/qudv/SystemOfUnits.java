/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv;


import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Of Units</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getDescription <em>Description</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getDefinitionURI <em>Definition URI</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getUnit <em>Unit</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getBaseUnit <em>Base Unit</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getSystemOfQuantities <em>System Of Quantities</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfUnits()
 * @model
 * @generated
 */
public interface SystemOfUnits extends IName, IUuid {
	/**
	 * Returns the value of the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Symbol</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Symbol</em>' attribute.
	 * @see #setSymbol(String)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfUnits_Symbol()
	 * @model
	 * @generated
	 */
	String getSymbol();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getSymbol <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Symbol</em>' attribute.
	 * @see #getSymbol()
	 * @generated
	 */
	void setSymbol(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfUnits_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition URI</em>' attribute.
	 * @see #setDefinitionURI(String)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfUnits_DefinitionURI()
	 * @model
	 * @generated
	 */
	String getDefinitionURI();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getDefinitionURI <em>Definition URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition URI</em>' attribute.
	 * @see #getDefinitionURI()
	 * @generated
	 */
	void setDefinitionURI(String value);

	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.qudv.Prefix}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefix</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefix</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfUnits_Prefix()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Prefix> getPrefix();

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.qudv.AUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfUnits_Unit()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<AUnit> getUnit();

	/**
	 * Returns the value of the '<em><b>Base Unit</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.qudv.AUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Unit</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Unit</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfUnits_BaseUnit()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<AUnit> getBaseUnit();

	/**
	 * Returns the value of the '<em><b>System Of Quantities</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Of Quantities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Of Quantities</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfUnits_SystemOfQuantities()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<SystemOfQuantities> getSystemOfQuantities();

} // SystemOfUnits
