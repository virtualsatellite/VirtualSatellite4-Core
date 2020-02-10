/**
 */
package de.dlr.sc.virsat.model.external.tests;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.external.tests.Container#getObjects <em>Objects</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.external.tests.TestsPackage#getContainer()
 * @model
 * @generated
 */
public interface Container extends EObject {
	/**
	 * Returns the value of the '<em><b>Objects</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects</em>' containment reference.
	 * @see #setObjects(SuperType)
	 * @see de.dlr.sc.virsat.model.external.tests.TestsPackage#getContainer_Objects()
	 * @model containment="true"
	 * @generated
	 */
	SuperType getObjects();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.external.tests.Container#getObjects <em>Objects</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Objects</em>' containment reference.
	 * @see #getObjects()
	 * @generated
	 */
	void setObjects(SuperType value);

} // Container
