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
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult;

import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Equation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationImpl#getSuperTis <em>Super Tis</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationImpl#isIsInherited <em>Is Inherited</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationImpl#isOverride <em>Override</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationImpl#getResult <em>Result</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationImpl#getResultText <em>Result Text</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationImpl#getDefinition <em>Definition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EquationImpl extends MinimalEObjectImpl.Container implements Equation {
	
	@Override
	public void eSetProxyURI(URI uri) {
		// TODO Auto-generated method stub
		super.eSetProxyURI(uri);
	}
	
	/**
	 * The cached value of the '{@link #getSuperTis() <em>Super Tis</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperTis()
	 * @generated
	 * @ordered
	 */
	protected EList<IInheritanceLink> superTis;

	/**
	 * The default value of the '{@link #isIsInherited() <em>Is Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInherited()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_INHERITED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsInherited() <em>Is Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInherited()
	 * @generated
	 * @ordered
	 */
	protected boolean isInherited = IS_INHERITED_EDEFAULT;

	/**
	 * The default value of the '{@link #isOverride() <em>Override</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverride()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERRIDE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOverride() <em>Override</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverride()
	 * @generated
	 * @ordered
	 */
	protected boolean override = OVERRIDE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected AExpression expression;

	/**
	 * The cached value of the '{@link #getResult() <em>Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected IEquationResult result;

	/**
	 * The default value of the '{@link #getResultText() <em>Result Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultText()
	 * @generated
	 * @ordered
	 */
	protected static final String RESULT_TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResultText() <em>Result Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultText()
	 * @generated
	 * @ordered
	 */
	protected String resultText = RESULT_TEXT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDefinition() <em>Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected EquationDefinition definition;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EquationImpl() {
		super();
		
	}
	
	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * This method handles unresolved references into otehr resources.
	 * When ever a proxy object in the DVLM model is resolved
	 * This method will check the object and place an error to the resource
	 * if it cannot be resolved 
	 * @generated
	 */
	@Override
	public EObject eResolveProxy(InternalEObject proxy) {
		EObject resolvedProxy = super.eResolveProxy(proxy);
		return DVLMUnresolvedReferenceException.checkProxyObject(resolvedProxy, proxy, this.eResource());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CalculationPackage.Literals.EQUATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<IInheritanceLink>}'.
	 * @generated
	 */
	public EList<IInheritanceLink> getSuperTis() {
		if (superTis == null) {
			superTis = new EObjectResolvingEList<IInheritanceLink>(IInheritanceLink.class, this, CalculationPackage.EQUATION__SUPER_TIS);
		 
		
		}
		return superTis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code boolean}'.
	 * @generated
	 */
	public boolean isIsInherited() {
		return isInherited;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsInherited(boolean newIsInherited) {
		boolean oldIsInherited = isInherited;
		isInherited = newIsInherited;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION__IS_INHERITED, oldIsInherited, isInherited));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code boolean}'.
	 * @generated
	 */
	public boolean isOverride() {
		return override;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverride(boolean newOverride) {
		boolean oldOverride = override;
		override = newOverride;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION__OVERRIDE, oldOverride, override));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code AExpression}'.
	 * @generated
	 */
	public AExpression getExpression() {
		if (expression != null && expression.eIsProxy()) {
			InternalEObject oldExpression = (InternalEObject)expression;
			expression = (AExpression)eResolveProxy(oldExpression);
			if (expression != oldExpression) {
				InternalEObject newExpression = (InternalEObject)expression;
				NotificationChain msgs = oldExpression.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION__EXPRESSION, null, null);
				if (newExpression.eInternalContainer() == null) {
					msgs = newExpression.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION__EXPRESSION, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalculationPackage.EQUATION__EXPRESSION, oldExpression, expression));
			}
		}
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code AExpression}'.
	 * @generated
	 */
	public AExpression basicGetExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newExpression new value to be of type '{@code AExpression}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetExpression(AExpression newExpression, NotificationChain msgs) {
		AExpression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION__EXPRESSION, oldExpression, newExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(AExpression newExpression) {
		if (newExpression != expression) {
			NotificationChain msgs = null;
			if (expression != null)
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code IEquationResult}'.
	 * @generated
	 */
	public IEquationResult getResult() {
		if (result != null && result.eIsProxy()) {
			InternalEObject oldResult = (InternalEObject)result;
			result = (IEquationResult)eResolveProxy(oldResult);
			if (result != oldResult) {
				InternalEObject newResult = (InternalEObject)result;
				NotificationChain msgs = oldResult.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION__RESULT, null, null);
				if (newResult.eInternalContainer() == null) {
					msgs = newResult.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION__RESULT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalculationPackage.EQUATION__RESULT, oldResult, result));
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code IEquationResult}'.
	 * @generated
	 */
	public IEquationResult basicGetResult() {
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newResult new value to be of type '{@code IEquationResult}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetResult(IEquationResult newResult, NotificationChain msgs) {
		IEquationResult oldResult = result;
		result = newResult;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION__RESULT, oldResult, newResult);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResult(IEquationResult newResult) {
		if (newResult != result) {
			NotificationChain msgs = null;
			if (result != null)
				msgs = ((InternalEObject)result).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION__RESULT, null, msgs);
			if (newResult != null)
				msgs = ((InternalEObject)newResult).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION__RESULT, null, msgs);
			msgs = basicSetResult(newResult, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION__RESULT, newResult, newResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public String getResultText() {
		return resultText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultText(String newResultText) {
		String oldResultText = resultText;
		resultText = newResultText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION__RESULT_TEXT, oldResultText, resultText));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EquationDefinition}'.
	 * @generated
	 */
	public EquationDefinition getDefinition() {
		if (definition != null && definition.eIsProxy()) {
			InternalEObject oldDefinition = (InternalEObject)definition;
			definition = (EquationDefinition)eResolveProxy(oldDefinition);
			if (definition != oldDefinition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalculationPackage.EQUATION__DEFINITION, oldDefinition, definition));
			}
		}
		return definition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code EquationDefinition}'.
	 * @generated
	 */
	public EquationDefinition basicGetDefinition() {
		return definition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinition(EquationDefinition newDefinition) {
		EquationDefinition oldDefinition = definition;
		definition = newDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION__DEFINITION, oldDefinition, definition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CalculationPackage.EQUATION__EXPRESSION:
				return basicSetExpression(null, msgs);
			case CalculationPackage.EQUATION__RESULT:
				return basicSetResult(null, msgs);
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
			case CalculationPackage.EQUATION__SUPER_TIS:
				return getSuperTis();
			case CalculationPackage.EQUATION__IS_INHERITED:
				return isIsInherited();
			case CalculationPackage.EQUATION__OVERRIDE:
				return isOverride();
			case CalculationPackage.EQUATION__EXPRESSION:
				if (resolve) return getExpression();
				return basicGetExpression();
			case CalculationPackage.EQUATION__RESULT:
				if (resolve) return getResult();
				return basicGetResult();
			case CalculationPackage.EQUATION__RESULT_TEXT:
				return getResultText();
			case CalculationPackage.EQUATION__DEFINITION:
				if (resolve) return getDefinition();
				return basicGetDefinition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CalculationPackage.EQUATION__SUPER_TIS:
				getSuperTis().clear();
				getSuperTis().addAll((Collection<? extends IInheritanceLink>)newValue);
				return;
			case CalculationPackage.EQUATION__IS_INHERITED:
				setIsInherited((Boolean)newValue);
				return;
			case CalculationPackage.EQUATION__OVERRIDE:
				setOverride((Boolean)newValue);
				return;
			case CalculationPackage.EQUATION__EXPRESSION:
				setExpression((AExpression)newValue);
				return;
			case CalculationPackage.EQUATION__RESULT:
				setResult((IEquationResult)newValue);
				return;
			case CalculationPackage.EQUATION__RESULT_TEXT:
				setResultText((String)newValue);
				return;
			case CalculationPackage.EQUATION__DEFINITION:
				setDefinition((EquationDefinition)newValue);
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
			case CalculationPackage.EQUATION__SUPER_TIS:
				getSuperTis().clear();
				return;
			case CalculationPackage.EQUATION__IS_INHERITED:
				setIsInherited(IS_INHERITED_EDEFAULT);
				return;
			case CalculationPackage.EQUATION__OVERRIDE:
				setOverride(OVERRIDE_EDEFAULT);
				return;
			case CalculationPackage.EQUATION__EXPRESSION:
				setExpression((AExpression)null);
				return;
			case CalculationPackage.EQUATION__RESULT:
				setResult((IEquationResult)null);
				return;
			case CalculationPackage.EQUATION__RESULT_TEXT:
				setResultText(RESULT_TEXT_EDEFAULT);
				return;
			case CalculationPackage.EQUATION__DEFINITION:
				setDefinition((EquationDefinition)null);
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
			case CalculationPackage.EQUATION__SUPER_TIS:
				return superTis != null && !superTis.isEmpty();
			case CalculationPackage.EQUATION__IS_INHERITED:
				return isInherited != IS_INHERITED_EDEFAULT;
			case CalculationPackage.EQUATION__OVERRIDE:
				return override != OVERRIDE_EDEFAULT;
			case CalculationPackage.EQUATION__EXPRESSION:
				return expression != null;
			case CalculationPackage.EQUATION__RESULT:
				return result != null;
			case CalculationPackage.EQUATION__RESULT_TEXT:
				return RESULT_TEXT_EDEFAULT == null ? resultText != null : !RESULT_TEXT_EDEFAULT.equals(resultText);
			case CalculationPackage.EQUATION__DEFINITION:
				return definition != null;
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
		result.append(" (isInherited: ");
		result.append(isInherited);
		result.append(", override: ");
		result.append(override);
		result.append(", resultText: ");
		result.append(resultText);
		result.append(')');
		return result.toString();
	}

} //EquationImpl
