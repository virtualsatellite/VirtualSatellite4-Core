/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util;

import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.*;

import de.dlr.sc.virsat.model.dvlm.general.IComment;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage
 * @generated
 */
public class PropertyinstancesAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PropertyinstancesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyinstancesAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PropertyinstancesPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyinstancesSwitch<Adapter> modelSwitch =
		new PropertyinstancesSwitch<Adapter>() {
			@Override
			public Adapter caseIUnitPropertyInstance(IUnitPropertyInstance object) {
				return createIUnitPropertyInstanceAdapter();
			}
			@Override
			public Adapter caseAPropertyInstance(APropertyInstance object) {
				return createAPropertyInstanceAdapter();
			}
			@Override
			public Adapter caseValuePropertyInstance(ValuePropertyInstance object) {
				return createValuePropertyInstanceAdapter();
			}
			@Override
			public Adapter caseUnitValuePropertyInstance(UnitValuePropertyInstance object) {
				return createUnitValuePropertyInstanceAdapter();
			}
			@Override
			public Adapter caseReferencePropertyInstance(ReferencePropertyInstance object) {
				return createReferencePropertyInstanceAdapter();
			}
			@Override
			public Adapter caseEReferencePropertyInstance(EReferencePropertyInstance object) {
				return createEReferencePropertyInstanceAdapter();
			}
			@Override
			public Adapter caseComposedPropertyInstance(ComposedPropertyInstance object) {
				return createComposedPropertyInstanceAdapter();
			}
			@Override
			public Adapter caseArrayInstance(ArrayInstance object) {
				return createArrayInstanceAdapter();
			}
			@Override
			public Adapter caseResourcePropertyInstance(ResourcePropertyInstance object) {
				return createResourcePropertyInstanceAdapter();
			}
			@Override
			public Adapter caseEnumUnitPropertyInstance(EnumUnitPropertyInstance object) {
				return createEnumUnitPropertyInstanceAdapter();
			}
			@Override
			public Adapter caseIUuid(IUuid object) {
				return createIUuidAdapter();
			}
			@Override
			public Adapter caseIComment(IComment object) {
				return createICommentAdapter();
			}
			@Override
			public Adapter caseIInstance(IInstance object) {
				return createIInstanceAdapter();
			}
			@Override
			public Adapter caseIInheritanceLink(IInheritanceLink object) {
				return createIInheritanceLinkAdapter();
			}
			@Override
			public Adapter caseATypeInstance(ATypeInstance object) {
				return createATypeInstanceAdapter();
			}
			@Override
			public Adapter caseIEquationInput(IEquationInput object) {
				return createIEquationInputAdapter();
			}
			@Override
			public Adapter caseIOverridableInheritanceLink(IOverridableInheritanceLink object) {
				return createIOverridableInheritanceLinkAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance <em>IUnit Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance
	 * @generated
	 */
	public Adapter createIUnitPropertyInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance <em>AProperty Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance
	 * @generated
	 */
	public Adapter createAPropertyInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance <em>Value Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance
	 * @generated
	 */
	public Adapter createValuePropertyInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance <em>Unit Value Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance
	 * @generated
	 */
	public Adapter createUnitValuePropertyInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance <em>Reference Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance
	 * @generated
	 */
	public Adapter createReferencePropertyInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance <em>EReference Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance
	 * @generated
	 */
	public Adapter createEReferencePropertyInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance <em>Composed Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance
	 * @generated
	 */
	public Adapter createComposedPropertyInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance <em>Array Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance
	 * @generated
	 */
	public Adapter createArrayInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance <em>Resource Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance
	 * @generated
	 */
	public Adapter createResourcePropertyInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance <em>Enum Unit Property Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance
	 * @generated
	 */
	public Adapter createEnumUnitPropertyInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.general.IUuid <em>IUuid</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IUuid
	 * @generated
	 */
	public Adapter createIUuidAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.general.IComment <em>IComment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IComment
	 * @generated
	 */
	public Adapter createICommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.general.IInstance <em>IInstance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IInstance
	 * @generated
	 */
	public Adapter createIInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink <em>IInheritance Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink
	 * @generated
	 */
	public Adapter createIInheritanceLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance <em>AType Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance
	 * @generated
	 */
	public Adapter createATypeInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput <em>IEquation Input</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput
	 * @generated
	 */
	public Adapter createIEquationInputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink <em>IOverridable Inheritance Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink
	 * @generated
	 */
	public Adapter createIOverridableInheritanceLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //PropertyinstancesAdapterFactory
