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


import de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink;

import org.eclipse.emf.common.util.URI;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Property Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance#getResourceUri <em>Resource Uri</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage#getResourcePropertyInstance()
 * @model
 * @generated
 */
public interface ResourcePropertyInstance extends APropertyInstance, IOverridableInheritanceLink {
	/**
	 * Returns the value of the '<em><b>Resource Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Uri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Uri</em>' attribute.
	 * @see #setResourceUri(String)
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage#getResourcePropertyInstance_ResourceUri()
	 * @model
	 * @generated
	 */
	String getResourceUri();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance#getResourceUri <em>Resource Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Uri</em>' attribute.
	 * @see #getResourceUri()
	 * @generated
	 */
	void setResourceUri(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience Method to set the Uri String via an EMF URI
	 * <!-- end-model-doc -->
	 * @model uriDataType="de.dlr.sc.virsat.model.dvlm.types.URI"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (uri != null) {\r\n\tsetResourceUri(uri.toPlatformString(false));\r\n} else {\r\n\tsetResourceUri(null);\r\n}'"
	 * @generated
	 */
	void setUri(URI uri);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience Method to get the curently set Uri as EMF Platform Uri
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="de.dlr.sc.virsat.model.dvlm.types.URI"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (resourceUri == null) {\r\n\treturn null;\r\n}\r\nreturn URI.createPlatformResourceURI(resourceUri, false);'"
	 * @generated
	 */
	URI getUri();

} // ResourcePropertyInstance
