/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances;


import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;

import de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Unit Property Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage#getEnumUnitPropertyInstance()
 * @model
 * @generated
 */
public interface EnumUnitPropertyInstance extends APropertyInstance, IEquationInput, IOverridableInheritanceLink, IUnitPropertyInstance {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' reference.
	 * @see #setValue(EnumValueDefinition)
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage#getEnumUnitPropertyInstance_Value()
	 * @model
	 * @generated
	 */
	EnumValueDefinition getValue();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance#getValue <em>Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(EnumValueDefinition value);

} // EnumUnitPropertyInstance
