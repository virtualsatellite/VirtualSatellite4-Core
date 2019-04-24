/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions;


import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The Enum proeprty is used to give users a pre selection of values. This preselection is available in the values. The instance to this enumproperty will hold a reference to one of these values
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty#getValues <em>Values</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty#getDefaultValue <em>Default Value</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage#getEnumProperty()
 * @model
 * @generated
 */
public interface EnumProperty extends AQudvTypeProperty {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage#getEnumProperty_Values()
	 * @model containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	EList<EnumValueDefinition> getValues();

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' reference.
	 * @see #setDefaultValue(EnumValueDefinition)
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage#getEnumProperty_DefaultValue()
	 * @model
	 * @generated
	 */
	EnumValueDefinition getDefaultValue();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty#getDefaultValue <em>Default Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' reference.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(EnumValueDefinition value);

} // EnumProperty
