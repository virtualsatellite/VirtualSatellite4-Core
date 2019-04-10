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
 * A representation of the model object '<em><b>System Of Quantities</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDescription <em>Description</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDefinitionURI <em>Definition URI</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getQuantityKind <em>Quantity Kind</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDimension <em>Dimension</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfQuantities()
 * @model
 * @generated
 */
public interface SystemOfQuantities extends IName, IUuid {
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
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfQuantities_Symbol()
	 * @model
	 * @generated
	 */
	String getSymbol();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getSymbol <em>Symbol</em>}' attribute.
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
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfQuantities_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDescription <em>Description</em>}' attribute.
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
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfQuantities_DefinitionURI()
	 * @model
	 * @generated
	 */
	String getDefinitionURI();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDefinitionURI <em>Definition URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition URI</em>' attribute.
	 * @see #getDefinitionURI()
	 * @generated
	 */
	void setDefinitionURI(String value);

	/**
	 * Returns the value of the '<em><b>Quantity Kind</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantity Kind</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantity Kind</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfQuantities_QuantityKind()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<AQuantityKind> getQuantityKind();

	/**
	 * Returns the value of the '<em><b>Dimension</b></em>' reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.qudv.Dimension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dimension</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dimension</em>' reference list.
	 * @see #isSetDimension()
	 * @see #unsetDimension()
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getSystemOfQuantities_Dimension()
	 * @model unsettable="true"
	 * @generated
	 */
	EList<Dimension> getDimension();

	/**
	 * Unsets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDimension <em>Dimension</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDimension()
	 * @see #getDimension()
	 * @generated
	 */
	void unsetDimension();

	/**
	 * Returns whether the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDimension <em>Dimension</em>}' reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Dimension</em>' reference list is set.
	 * @see #unsetDimension()
	 * @see #getDimension()
	 * @generated
	 */
	boolean isSetDimension();

} // SystemOfQuantities
