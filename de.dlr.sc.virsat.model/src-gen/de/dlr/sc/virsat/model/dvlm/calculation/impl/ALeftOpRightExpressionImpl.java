/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.calculation.impl;


import de.dlr.sc.virsat.model.dvlm.calculation.AExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.MathOperator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ALeft Op Right Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ALeftOpRightExpressionImpl#getLeft <em>Left</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ALeftOpRightExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.ALeftOpRightExpressionImpl#getRight <em>Right</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ALeftOpRightExpressionImpl extends AExpressionImpl implements ALeftOpRightExpression {
	/**
	 * The cached value of the '{@link #getLeft() <em>Left</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeft()
	 * @generated
	 * @ordered
	 */
	protected AExpression left;

	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final MathOperator OPERATOR_EDEFAULT = MathOperator.PLUS;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected MathOperator operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRight() <em>Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRight()
	 * @generated
	 * @ordered
	 */
	protected AExpression right;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ALeftOpRightExpressionImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CalculationPackage.Literals.ALEFT_OP_RIGHT_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code AExpression}'.
	 * @generated
	 */
	public AExpression getLeft() {
		if (left != null && left.eIsProxy()) {
			InternalEObject oldLeft = (InternalEObject)left;
			left = (AExpression)eResolveProxy(oldLeft);
			if (left != oldLeft) {
				InternalEObject newLeft = (InternalEObject)left;
				NotificationChain msgs = oldLeft.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT, null, null);
				if (newLeft.eInternalContainer() == null) {
					msgs = newLeft.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT, oldLeft, left));
			}
		}
		return left;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code AExpression}'.
	 * @generated
	 */
	public AExpression basicGetLeft() {
		return left;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newLeft new value to be of type '{@code AExpression}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetLeft(AExpression newLeft, NotificationChain msgs) {
		AExpression oldLeft = left;
		left = newLeft;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT, oldLeft, newLeft);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeft(AExpression newLeft) {
		if (newLeft != left) {
			NotificationChain msgs = null;
			if (left != null)
				msgs = ((InternalEObject)left).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT, null, msgs);
			if (newLeft != null)
				msgs = ((InternalEObject)newLeft).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT, null, msgs);
			msgs = basicSetLeft(newLeft, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT, newLeft, newLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code MathOperator}'.
	 * @generated
	 */
	public MathOperator getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(MathOperator newOperator) {
		MathOperator oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code AExpression}'.
	 * @generated
	 */
	public AExpression getRight() {
		if (right != null && right.eIsProxy()) {
			InternalEObject oldRight = (InternalEObject)right;
			right = (AExpression)eResolveProxy(oldRight);
			if (right != oldRight) {
				InternalEObject newRight = (InternalEObject)right;
				NotificationChain msgs = oldRight.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT, null, null);
				if (newRight.eInternalContainer() == null) {
					msgs = newRight.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT, oldRight, right));
			}
		}
		return right;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code AExpression}'.
	 * @generated
	 */
	public AExpression basicGetRight() {
		return right;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newRight new value to be of type '{@code AExpression}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetRight(AExpression newRight, NotificationChain msgs) {
		AExpression oldRight = right;
		right = newRight;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT, oldRight, newRight);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRight(AExpression newRight) {
		if (newRight != right) {
			NotificationChain msgs = null;
			if (right != null)
				msgs = ((InternalEObject)right).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT, null, msgs);
			if (newRight != null)
				msgs = ((InternalEObject)newRight).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT, null, msgs);
			msgs = basicSetRight(newRight, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT, newRight, newRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT:
				return basicSetLeft(null, msgs);
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT:
				return basicSetRight(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT:
				if (resolve) return getLeft();
				return basicGetLeft();
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__OPERATOR:
				return getOperator();
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT:
				if (resolve) return getRight();
				return basicGetRight();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT:
				setLeft((AExpression)newValue);
				return;
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__OPERATOR:
				setOperator((MathOperator)newValue);
				return;
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT:
				setRight((AExpression)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT:
				setLeft((AExpression)null);
				return;
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT:
				setRight((AExpression)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__LEFT:
				return left != null;
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
			case CalculationPackage.ALEFT_OP_RIGHT_EXPRESSION__RIGHT:
				return right != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (operator: ");
		result.append(operator);
		result.append(')');
		return result.toString();
	}

} //ALeftOpRightExpressionImpl
