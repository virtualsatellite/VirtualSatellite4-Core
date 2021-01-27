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


import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.Import;

import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Equation Section</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationSectionImpl#getEquationSection <em>Equation Section</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationSectionImpl#getSuperTis <em>Super Tis</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationSectionImpl#isIsInherited <em>Is Inherited</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationSectionImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationSectionImpl#getEquations <em>Equations</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.calculation.impl.EquationSectionImpl#getSerializedStatements <em>Serialized Statements</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EquationSectionImpl extends MinimalEObjectImpl.Container implements EquationSection {
	/**
	 * The cached value of the '{@link #getEquationSection() <em>Equation Section</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEquationSection()
	 * @generated
	 * @ordered
	 */
	protected EquationSection equationSection;

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
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<Import> imports;

	/**
	 * The cached value of the '{@link #getEquations() <em>Equations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEquations()
	 * @generated
	 * @ordered
	 */
	protected EList<Equation> equations;

	/**
	 * The default value of the '{@link #getSerializedStatements() <em>Serialized Statements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSerializedStatements()
	 * @generated
	 * @ordered
	 */
	protected static final String SERIALIZED_STATEMENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSerializedStatements() <em>Serialized Statements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSerializedStatements()
	 * @generated
	 * @ordered
	 */
	protected String serializedStatements = SERIALIZED_STATEMENTS_EDEFAULT;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EquationSectionImpl() {
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

	
	@Override
	public void eSetProxyURI(URI uri) {
		// TODO Auto-generated method stub
		super.eSetProxyURI(uri);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CalculationPackage.Literals.EQUATION_SECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EquationSection}'.
	 * @generated
	 */
	public EquationSection getEquationSection() {
		if (equationSection != null && equationSection.eIsProxy()) {
			InternalEObject oldEquationSection = (InternalEObject)equationSection;
			equationSection = (EquationSection)eResolveProxy(oldEquationSection);
			if (equationSection != oldEquationSection) {
				InternalEObject newEquationSection = (InternalEObject)equationSection;
				NotificationChain msgs = oldEquationSection.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION_SECTION__EQUATION_SECTION, null, null);
				if (newEquationSection.eInternalContainer() == null) {
					msgs = newEquationSection.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION_SECTION__EQUATION_SECTION, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalculationPackage.EQUATION_SECTION__EQUATION_SECTION, oldEquationSection, equationSection));
			}
		}
		return equationSection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code EquationSection}'.
	 * @generated
	 */
	public EquationSection basicGetEquationSection() {
		return equationSection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newEquationSection new value to be of type '{@code EquationSection}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetEquationSection(EquationSection newEquationSection, NotificationChain msgs) {
		EquationSection oldEquationSection = equationSection;
		equationSection = newEquationSection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION_SECTION__EQUATION_SECTION, oldEquationSection, newEquationSection);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEquationSection(EquationSection newEquationSection) {
		if (newEquationSection != equationSection) {
			NotificationChain msgs = null;
			if (equationSection != null)
				msgs = ((InternalEObject)equationSection).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION_SECTION__EQUATION_SECTION, null, msgs);
			if (newEquationSection != null)
				msgs = ((InternalEObject)newEquationSection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalculationPackage.EQUATION_SECTION__EQUATION_SECTION, null, msgs);
			msgs = basicSetEquationSection(newEquationSection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION_SECTION__EQUATION_SECTION, newEquationSection, newEquationSection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<IInheritanceLink>}'.
	 * @generated
	 */
	public EList<IInheritanceLink> getSuperTis() {
		if (superTis == null) {
			superTis = new EObjectResolvingEList<IInheritanceLink>(IInheritanceLink.class, this, CalculationPackage.EQUATION_SECTION__SUPER_TIS);
		 
		
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
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION_SECTION__IS_INHERITED, oldIsInherited, isInherited));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<Import>}'.
	 * @generated
	 */
	public EList<Import> getImports() {
		if (imports == null) {
			imports = new EObjectContainmentEList.Resolving<Import>(Import.class, this, CalculationPackage.EQUATION_SECTION__IMPORTS);
		 
		
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<Equation>}'.
	 * @generated
	 */
	public EList<Equation> getEquations() {
		if (equations == null) {
			equations = new EObjectContainmentEList.Resolving<Equation>(Equation.class, this, CalculationPackage.EQUATION_SECTION__EQUATIONS);
		 
		
		}
		return equations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public String getSerializedStatements() {
		return serializedStatements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSerializedStatements(String newSerializedStatements) {
		String oldSerializedStatements = serializedStatements;
		serializedStatements = newSerializedStatements;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CalculationPackage.EQUATION_SECTION__SERIALIZED_STATEMENTS, oldSerializedStatements, serializedStatements));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CalculationPackage.EQUATION_SECTION__EQUATION_SECTION:
				return basicSetEquationSection(null, msgs);
			case CalculationPackage.EQUATION_SECTION__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case CalculationPackage.EQUATION_SECTION__EQUATIONS:
				return ((InternalEList<?>)getEquations()).basicRemove(otherEnd, msgs);
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
			case CalculationPackage.EQUATION_SECTION__EQUATION_SECTION:
				if (resolve) return getEquationSection();
				return basicGetEquationSection();
			case CalculationPackage.EQUATION_SECTION__SUPER_TIS:
				return getSuperTis();
			case CalculationPackage.EQUATION_SECTION__IS_INHERITED:
				return isIsInherited();
			case CalculationPackage.EQUATION_SECTION__IMPORTS:
				return getImports();
			case CalculationPackage.EQUATION_SECTION__EQUATIONS:
				return getEquations();
			case CalculationPackage.EQUATION_SECTION__SERIALIZED_STATEMENTS:
				return getSerializedStatements();
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
			case CalculationPackage.EQUATION_SECTION__EQUATION_SECTION:
				setEquationSection((EquationSection)newValue);
				return;
			case CalculationPackage.EQUATION_SECTION__SUPER_TIS:
				getSuperTis().clear();
				getSuperTis().addAll((Collection<? extends IInheritanceLink>)newValue);
				return;
			case CalculationPackage.EQUATION_SECTION__IS_INHERITED:
				setIsInherited((Boolean)newValue);
				return;
			case CalculationPackage.EQUATION_SECTION__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends Import>)newValue);
				return;
			case CalculationPackage.EQUATION_SECTION__EQUATIONS:
				getEquations().clear();
				getEquations().addAll((Collection<? extends Equation>)newValue);
				return;
			case CalculationPackage.EQUATION_SECTION__SERIALIZED_STATEMENTS:
				setSerializedStatements((String)newValue);
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
			case CalculationPackage.EQUATION_SECTION__EQUATION_SECTION:
				setEquationSection((EquationSection)null);
				return;
			case CalculationPackage.EQUATION_SECTION__SUPER_TIS:
				getSuperTis().clear();
				return;
			case CalculationPackage.EQUATION_SECTION__IS_INHERITED:
				setIsInherited(IS_INHERITED_EDEFAULT);
				return;
			case CalculationPackage.EQUATION_SECTION__IMPORTS:
				getImports().clear();
				return;
			case CalculationPackage.EQUATION_SECTION__EQUATIONS:
				getEquations().clear();
				return;
			case CalculationPackage.EQUATION_SECTION__SERIALIZED_STATEMENTS:
				setSerializedStatements(SERIALIZED_STATEMENTS_EDEFAULT);
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
			case CalculationPackage.EQUATION_SECTION__EQUATION_SECTION:
				return equationSection != null;
			case CalculationPackage.EQUATION_SECTION__SUPER_TIS:
				return superTis != null && !superTis.isEmpty();
			case CalculationPackage.EQUATION_SECTION__IS_INHERITED:
				return isInherited != IS_INHERITED_EDEFAULT;
			case CalculationPackage.EQUATION_SECTION__IMPORTS:
				return imports != null && !imports.isEmpty();
			case CalculationPackage.EQUATION_SECTION__EQUATIONS:
				return equations != null && !equations.isEmpty();
			case CalculationPackage.EQUATION_SECTION__SERIALIZED_STATEMENTS:
				return SERIALIZED_STATEMENTS_EDEFAULT == null ? serializedStatements != null : !SERIALIZED_STATEMENTS_EDEFAULT.equals(serializedStatements);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IInheritanceLink.class) {
			switch (derivedFeatureID) {
				case CalculationPackage.EQUATION_SECTION__SUPER_TIS: return InheritancePackage.IINHERITANCE_LINK__SUPER_TIS;
				case CalculationPackage.EQUATION_SECTION__IS_INHERITED: return InheritancePackage.IINHERITANCE_LINK__IS_INHERITED;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IInheritanceLink.class) {
			switch (baseFeatureID) {
				case InheritancePackage.IINHERITANCE_LINK__SUPER_TIS: return CalculationPackage.EQUATION_SECTION__SUPER_TIS;
				case InheritancePackage.IINHERITANCE_LINK__IS_INHERITED: return CalculationPackage.EQUATION_SECTION__IS_INHERITED;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(", serializedStatements: ");
		result.append(serializedStatements);
		result.append(')');
		return result.toString();
	}

} //EquationSectionImpl
