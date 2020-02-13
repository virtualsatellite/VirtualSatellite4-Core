/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts.impl;


import de.dlr.sc.virsat.model.dvlm.categories.Category;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport;
import de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept;
import de.dlr.sc.virsat.model.dvlm.concepts.IEImports;
import de.dlr.sc.virsat.model.dvlm.concepts.IImports;

import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IDescription;

import de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concept</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#getFullQualifiedName <em>Full Qualified Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#getShortName <em>Short Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#isActive <em>Active</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#getEcoreImports <em>Ecore Imports</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#getStructuralElements <em>Structural Elements</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#getRelations <em>Relations</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#isDMF <em>DMF</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl#isBeta <em>Beta</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConceptImpl extends MinimalEObjectImpl.Container implements Concept {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "de.dlr.sc.model.dvlm.noid";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFullQualifiedName() <em>Full Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullQualifiedName()
	 * @generated
	 * @ordered
	 */
	protected static final String FULL_QUALIFIED_NAME_EDEFAULT = "de.dlr.sc.model.dvlm.noid";

	/**
	 * The default value of the '{@link #getShortName() <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortName()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getShortName() <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortName()
	 * @generated
	 * @ordered
	 */
	protected String shortName = SHORT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected boolean active = ACTIVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<ConceptImport> imports;

	/**
	 * The cached value of the '{@link #getEcoreImports() <em>Ecore Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEcoreImports()
	 * @generated
	 * @ordered
	 */
	protected EList<EcoreImport> ecoreImports;

	/**
	 * The cached value of the '{@link #getCategories() <em>Categories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategories()
	 * @generated
	 * @ordered
	 */
	protected EList<Category> categories;

	/**
	 * The cached value of the '{@link #getStructuralElements() <em>Structural Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStructuralElements()
	 * @generated
	 * @ordered
	 */
	protected EList<StructuralElement> structuralElements;

	/**
	 * The cached value of the '{@link #getRelations() <em>Relations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelations()
	 * @generated
	 * @ordered
	 */
	protected EList<GeneralRelation> relations;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "1.0";

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #isDMF() <em>DMF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDMF()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DMF_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDMF() <em>DMF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDMF()
	 * @generated
	 * @ordered
	 */
	protected boolean dmf = DMF_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isBeta() <em>Beta</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBeta()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BETA_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBeta() <em>Beta</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBeta()
	 * @generated
	 * @ordered
	 */
	protected boolean beta = BETA_EDEFAULT;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConceptImpl() {
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
		return ConceptsPackage.Literals.CONCEPT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptsPackage.CONCEPT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullQualifiedName() {
		// *********************************
		//  VirSat Specific Code Generation
		// *********************************
     	return ActiveConceptHelper.getFullQualifiedId(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortName(String newShortName) {
		String oldShortName = shortName;
		shortName = newShortName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptsPackage.CONCEPT__SHORT_NAME, oldShortName, shortName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptsPackage.CONCEPT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActive(boolean newActive) {
		boolean oldActive = active;
		active = newActive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptsPackage.CONCEPT__ACTIVE, oldActive, active));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConceptImport> getImports() {
		if (imports == null) {
			imports = new EObjectContainmentEList.Resolving<ConceptImport>(ConceptImport.class, this, ConceptsPackage.CONCEPT__IMPORTS);
		 
		
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EcoreImport> getEcoreImports() {
		if (ecoreImports == null) {
			ecoreImports = new EObjectContainmentEList.Resolving<EcoreImport>(EcoreImport.class, this, ConceptsPackage.CONCEPT__ECORE_IMPORTS);
		 
		
		}
		return ecoreImports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Category> getCategories() {
		if (categories == null) {
			categories = new EObjectContainmentEList.Resolving<Category>(Category.class, this, ConceptsPackage.CONCEPT__CATEGORIES);
		 
		
		}
		return categories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StructuralElement> getStructuralElements() {
		if (structuralElements == null) {
			structuralElements = new EObjectContainmentEList.Resolving<StructuralElement>(StructuralElement.class, this, ConceptsPackage.CONCEPT__STRUCTURAL_ELEMENTS);
		 
		
		}
		return structuralElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GeneralRelation> getRelations() {
		if (relations == null) {
			relations = new EObjectContainmentEList.Resolving<GeneralRelation>(GeneralRelation.class, this, ConceptsPackage.CONCEPT__RELATIONS);
		 
		
		}
		return relations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptsPackage.CONCEPT__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDMF() {
		return dmf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDMF(boolean newDMF) {
		boolean oldDMF = dmf;
		dmf = newDMF;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptsPackage.CONCEPT__DMF, oldDMF, dmf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplayName(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptsPackage.CONCEPT__DISPLAY_NAME, oldDisplayName, displayName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBeta() {
		return beta;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBeta(boolean newBeta) {
		boolean oldBeta = beta;
		beta = newBeta;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptsPackage.CONCEPT__BETA, oldBeta, beta));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Category> getNonAbstractCategories() {
		EList<Category> allCategories = new org.eclipse.emf.common.util.BasicEList<>();
		
		for (Category category : getCategories()) {
			if (!category.isIsAbstract()) {
				allCategories.add(category);
			}
		}
					
		return allCategories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConceptsPackage.CONCEPT__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case ConceptsPackage.CONCEPT__ECORE_IMPORTS:
				return ((InternalEList<?>)getEcoreImports()).basicRemove(otherEnd, msgs);
			case ConceptsPackage.CONCEPT__CATEGORIES:
				return ((InternalEList<?>)getCategories()).basicRemove(otherEnd, msgs);
			case ConceptsPackage.CONCEPT__STRUCTURAL_ELEMENTS:
				return ((InternalEList<?>)getStructuralElements()).basicRemove(otherEnd, msgs);
			case ConceptsPackage.CONCEPT__RELATIONS:
				return ((InternalEList<?>)getRelations()).basicRemove(otherEnd, msgs);
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
			case ConceptsPackage.CONCEPT__NAME:
				return getName();
			case ConceptsPackage.CONCEPT__FULL_QUALIFIED_NAME:
				return getFullQualifiedName();
			case ConceptsPackage.CONCEPT__SHORT_NAME:
				return getShortName();
			case ConceptsPackage.CONCEPT__DESCRIPTION:
				return getDescription();
			case ConceptsPackage.CONCEPT__ACTIVE:
				return isActive();
			case ConceptsPackage.CONCEPT__IMPORTS:
				return getImports();
			case ConceptsPackage.CONCEPT__ECORE_IMPORTS:
				return getEcoreImports();
			case ConceptsPackage.CONCEPT__CATEGORIES:
				return getCategories();
			case ConceptsPackage.CONCEPT__STRUCTURAL_ELEMENTS:
				return getStructuralElements();
			case ConceptsPackage.CONCEPT__RELATIONS:
				return getRelations();
			case ConceptsPackage.CONCEPT__VERSION:
				return getVersion();
			case ConceptsPackage.CONCEPT__DMF:
				return isDMF();
			case ConceptsPackage.CONCEPT__DISPLAY_NAME:
				return getDisplayName();
			case ConceptsPackage.CONCEPT__BETA:
				return isBeta();
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
			case ConceptsPackage.CONCEPT__NAME:
				setName((String)newValue);
				return;
			case ConceptsPackage.CONCEPT__SHORT_NAME:
				setShortName((String)newValue);
				return;
			case ConceptsPackage.CONCEPT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ConceptsPackage.CONCEPT__ACTIVE:
				setActive((Boolean)newValue);
				return;
			case ConceptsPackage.CONCEPT__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends ConceptImport>)newValue);
				return;
			case ConceptsPackage.CONCEPT__ECORE_IMPORTS:
				getEcoreImports().clear();
				getEcoreImports().addAll((Collection<? extends EcoreImport>)newValue);
				return;
			case ConceptsPackage.CONCEPT__CATEGORIES:
				getCategories().clear();
				getCategories().addAll((Collection<? extends Category>)newValue);
				return;
			case ConceptsPackage.CONCEPT__STRUCTURAL_ELEMENTS:
				getStructuralElements().clear();
				getStructuralElements().addAll((Collection<? extends StructuralElement>)newValue);
				return;
			case ConceptsPackage.CONCEPT__RELATIONS:
				getRelations().clear();
				getRelations().addAll((Collection<? extends GeneralRelation>)newValue);
				return;
			case ConceptsPackage.CONCEPT__VERSION:
				setVersion((String)newValue);
				return;
			case ConceptsPackage.CONCEPT__DMF:
				setDMF((Boolean)newValue);
				return;
			case ConceptsPackage.CONCEPT__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case ConceptsPackage.CONCEPT__BETA:
				setBeta((Boolean)newValue);
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
			case ConceptsPackage.CONCEPT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ConceptsPackage.CONCEPT__SHORT_NAME:
				setShortName(SHORT_NAME_EDEFAULT);
				return;
			case ConceptsPackage.CONCEPT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ConceptsPackage.CONCEPT__ACTIVE:
				setActive(ACTIVE_EDEFAULT);
				return;
			case ConceptsPackage.CONCEPT__IMPORTS:
				getImports().clear();
				return;
			case ConceptsPackage.CONCEPT__ECORE_IMPORTS:
				getEcoreImports().clear();
				return;
			case ConceptsPackage.CONCEPT__CATEGORIES:
				getCategories().clear();
				return;
			case ConceptsPackage.CONCEPT__STRUCTURAL_ELEMENTS:
				getStructuralElements().clear();
				return;
			case ConceptsPackage.CONCEPT__RELATIONS:
				getRelations().clear();
				return;
			case ConceptsPackage.CONCEPT__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case ConceptsPackage.CONCEPT__DMF:
				setDMF(DMF_EDEFAULT);
				return;
			case ConceptsPackage.CONCEPT__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case ConceptsPackage.CONCEPT__BETA:
				setBeta(BETA_EDEFAULT);
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
			case ConceptsPackage.CONCEPT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ConceptsPackage.CONCEPT__FULL_QUALIFIED_NAME:
				return FULL_QUALIFIED_NAME_EDEFAULT == null ? getFullQualifiedName() != null : !FULL_QUALIFIED_NAME_EDEFAULT.equals(getFullQualifiedName());
			case ConceptsPackage.CONCEPT__SHORT_NAME:
				return SHORT_NAME_EDEFAULT == null ? shortName != null : !SHORT_NAME_EDEFAULT.equals(shortName);
			case ConceptsPackage.CONCEPT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ConceptsPackage.CONCEPT__ACTIVE:
				return active != ACTIVE_EDEFAULT;
			case ConceptsPackage.CONCEPT__IMPORTS:
				return imports != null && !imports.isEmpty();
			case ConceptsPackage.CONCEPT__ECORE_IMPORTS:
				return ecoreImports != null && !ecoreImports.isEmpty();
			case ConceptsPackage.CONCEPT__CATEGORIES:
				return categories != null && !categories.isEmpty();
			case ConceptsPackage.CONCEPT__STRUCTURAL_ELEMENTS:
				return structuralElements != null && !structuralElements.isEmpty();
			case ConceptsPackage.CONCEPT__RELATIONS:
				return relations != null && !relations.isEmpty();
			case ConceptsPackage.CONCEPT__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case ConceptsPackage.CONCEPT__DMF:
				return dmf != DMF_EDEFAULT;
			case ConceptsPackage.CONCEPT__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case ConceptsPackage.CONCEPT__BETA:
				return beta != BETA_EDEFAULT;
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
		if (baseClass == IDescription.class) {
			switch (derivedFeatureID) {
				case ConceptsPackage.CONCEPT__DESCRIPTION: return GeneralPackage.IDESCRIPTION__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IActiveConcept.class) {
			switch (derivedFeatureID) {
				case ConceptsPackage.CONCEPT__ACTIVE: return ConceptsPackage.IACTIVE_CONCEPT__ACTIVE;
				default: return -1;
			}
		}
		if (baseClass == IImports.class) {
			switch (derivedFeatureID) {
				case ConceptsPackage.CONCEPT__IMPORTS: return ConceptsPackage.IIMPORTS__IMPORTS;
				default: return -1;
			}
		}
		if (baseClass == IEImports.class) {
			switch (derivedFeatureID) {
				case ConceptsPackage.CONCEPT__ECORE_IMPORTS: return ConceptsPackage.IE_IMPORTS__ECORE_IMPORTS;
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
		if (baseClass == IDescription.class) {
			switch (baseFeatureID) {
				case GeneralPackage.IDESCRIPTION__DESCRIPTION: return ConceptsPackage.CONCEPT__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IActiveConcept.class) {
			switch (baseFeatureID) {
				case ConceptsPackage.IACTIVE_CONCEPT__ACTIVE: return ConceptsPackage.CONCEPT__ACTIVE;
				default: return -1;
			}
		}
		if (baseClass == IImports.class) {
			switch (baseFeatureID) {
				case ConceptsPackage.IIMPORTS__IMPORTS: return ConceptsPackage.CONCEPT__IMPORTS;
				default: return -1;
			}
		}
		if (baseClass == IEImports.class) {
			switch (baseFeatureID) {
				case ConceptsPackage.IE_IMPORTS__ECORE_IMPORTS: return ConceptsPackage.CONCEPT__ECORE_IMPORTS;
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
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case ConceptsPackage.CONCEPT___GET_NON_ABSTRACT_CATEGORIES:
				return getNonAbstractCategories();
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", shortName: ");
		result.append(shortName);
		result.append(", description: ");
		result.append(description);
		result.append(", active: ");
		result.append(active);
		result.append(", version: ");
		result.append(version);
		result.append(", DMF: ");
		result.append(dmf);
		result.append(", displayName: ");
		result.append(displayName);
		result.append(", beta: ");
		result.append(beta);
		result.append(')');
		return result.toString();
	}

} //ConceptImpl
