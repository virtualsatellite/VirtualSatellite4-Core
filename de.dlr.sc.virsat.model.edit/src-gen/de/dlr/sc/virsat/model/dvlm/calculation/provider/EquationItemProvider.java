

/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.calculation.provider;



import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;

import de.dlr.sc.virsat.model.dvlm.command.UndoableAddCommand;

import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;

import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagementCheckCommand;

import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.command.CommandParameter;

import org.eclipse.emf.edit.domain.EditingDomain;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link de.dlr.sc.virsat.model.dvlm.calculation.Equation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EquationItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, IItemFontProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EquationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addSuperTisPropertyDescriptor(object);
			addIsInheritedPropertyDescriptor(object);
			addOverridePropertyDescriptor(object);
			addDefinitionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Super Tis feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSuperTisPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IInheritanceLink_superTis_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IInheritanceLink_superTis_feature", "_UI_IInheritanceLink_type"),
				 InheritancePackage.Literals.IINHERITANCE_LINK__SUPER_TIS,
				 false,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Inherited feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsInheritedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IInheritanceLink_isInherited_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IInheritanceLink_isInherited_feature", "_UI_IInheritanceLink_type"),
				 InheritancePackage.Literals.IINHERITANCE_LINK__IS_INHERITED,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Override feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOverridePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IOverridableInheritanceLink_override_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IOverridableInheritanceLink_override_feature", "_UI_IOverridableInheritanceLink_type"),
				 InheritancePackage.Literals.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Definition feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDefinitionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Equation_definition_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Equation_definition_feature", "_UI_Equation_type"),
				 CalculationPackage.Literals.EQUATION__DEFINITION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(CalculationPackage.Literals.EQUATION__EXPRESSION);
			childrenFeatures.add(CalculationPackage.Literals.EQUATION__RESULT);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * This returns Equation.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Equation")); 
	}
	
	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {

			Equation equation = (Equation)object;
			return getString("_UI_Equation_type") + " " + equation.isIsInherited();
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Equation.class)) {
			case CalculationPackage.EQUATION__IS_INHERITED:
			case CalculationPackage.EQUATION__OVERRIDE:
			case CalculationPackage.EQUATION__RESULT_TEXT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case CalculationPackage.EQUATION__EXPRESSION:
			case CalculationPackage.EQUATION__RESULT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createAdditionAndSubtraction()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createMultiplicationAndDivision()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createPowerFunction()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createFunction()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createAdvancedFunction()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createSetFunction()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createParenthesis()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createValuePi()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createValueE()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createNumberLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createReferencedInput()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__EXPRESSION,
				 CalculationFactory.eINSTANCE.createReferencedDefinitionInput()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__RESULT,
				 CalculationFactory.eINSTANCE.createEquationIntermediateResult()));

		newChildDescriptors.add
			(createChildParameter
				(CalculationPackage.Literals.EQUATION__RESULT,
				 CalculationFactory.eINSTANCE.createTypeInstanceResult()));
	}
	
	
	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
 	 * Override to the createAddCommand Method
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	*/
	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,	Collection<?> collection, int index) {
		// Override functionality with the undoable ADD Command that performs undo by taking out the collection from the containing list
		// rather than reducing the index and assuming the last objects on the list have been added by the current command
		return new UndoableAddCommand(domain, owner, feature, collection, index);
	}
	
	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
 	 * This pipes the command through our RoleManagmentCheckCommand, so we can check directly if a user is allowed to modify
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	*/
	@Override
	public Command createCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass, CommandParameter commandParameter) {
		
		// Set the UserContext either from the SystemUserRegistry or
		// from the Domain if it exists
		IUserContext userContext = UserRegistry.getInstance();
		if (domain instanceof IUserContext) {
			userContext = (IUserContext) domain;
		}
		
		// For all other commands get the original one
		Command originalCommand = super.createCommand(object, domain, commandClass, commandParameter);
		// A RolemanagementCheckCommand should not necessarily be wrapped into another RoleManagementCheck Command
		if (originalCommand instanceof RoleManagementCheckCommand) {
			return originalCommand;
		} else {
			// And wrap it into our command checking for the proper access rights
			return new RoleManagementCheckCommand(originalCommand, commandParameter, userContext);	
		}
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return DVLMEditPlugin.INSTANCE;
	}

}
