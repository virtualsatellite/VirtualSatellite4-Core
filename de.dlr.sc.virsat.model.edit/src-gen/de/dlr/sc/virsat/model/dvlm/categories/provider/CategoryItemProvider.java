

/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.provider;



import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;

import de.dlr.sc.virsat.model.dvlm.command.UndoableAddCommand;

import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagementCheckCommand;

import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.command.CommandParameter;

import org.eclipse.emf.edit.domain.EditingDomain;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link de.dlr.sc.virsat.model.dvlm.categories.Category} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CategoryItemProvider extends ATypeDefinitionItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CategoryItemProvider(AdapterFactory adapterFactory) {
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

			addApplicableForPropertyDescriptor(object);
			addIsApplicableForAllPropertyDescriptor(object);
			addCardinalityPropertyDescriptor(object);
			addIsAbstractPropertyDescriptor(object);
			addExtendsCategoryPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Applicable For feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addApplicableForPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IApplicableFor_applicableFor_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IApplicableFor_applicableFor_feature", "_UI_IApplicableFor_type"),
				 StructuralPackage.Literals.IAPPLICABLE_FOR__APPLICABLE_FOR,
				 false,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Applicable For All feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsApplicableForAllPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IApplicableFor_isApplicableForAll_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IApplicableFor_isApplicableForAll_feature", "_UI_IApplicableFor_type"),
				 StructuralPackage.Literals.IAPPLICABLE_FOR__IS_APPLICABLE_FOR_ALL,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Cardinality feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCardinalityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IApplicableFor_cardinality_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IApplicableFor_cardinality_feature", "_UI_IApplicableFor_type"),
				 StructuralPackage.Literals.IAPPLICABLE_FOR__CARDINALITY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Abstract feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsAbstractPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Category_isAbstract_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Category_isAbstract_feature", "_UI_Category_type"),
				 CategoriesPackage.Literals.CATEGORY__IS_ABSTRACT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Extends Category feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addExtendsCategoryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Category_extendsCategory_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Category_extendsCategory_feature", "_UI_Category_type"),
				 CategoriesPackage.Literals.CATEGORY__EXTENDS_CATEGORY,
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
			childrenFeatures.add(CalculationPackage.Literals.IEQUATION_DEFINITION_SECTION_CONTAINER__EQUATION_DEFINITIONS);
			childrenFeatures.add(CategoriesPackage.Literals.CATEGORY__PROPERTIES);
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
	 * This returns Category.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) { 
		Object rtrnObj = overlayImage(object, getResourceLocator().getImage("full/obj16/Category")); 
		
		// In case we can find a trace to an object typed by IQualifedName we might have an alternative image
		IQualifiedName qualifiedNameObject = null;
		if (object instanceof ATypeInstance) {
			ATypeInstance typeInstance = (ATypeInstance) object;
			qualifiedNameObject = typeInstance.getType();
		} else if (object instanceof StructuralElementInstance) { 
			StructuralElementInstance structuralElementInstance = (StructuralElementInstance) object;
			qualifiedNameObject = structuralElementInstance.getType();			
		} else if (object instanceof StructuralElement) { 
			qualifiedNameObject = (StructuralElement) object;		
		} else if (object instanceof Category) { 
			qualifiedNameObject = (Category) object;		
		}
		
		// In case we could trace an object of IQualifedName we now ask the image registry for an alternative image
		if (qualifiedNameObject != null) {
			String fullQualifiedID = qualifiedNameObject.getFullQualifiedName();
			URL imageUrl = DVLMEditPlugin.getImageRegistry().get(fullQualifiedID);
			if (imageUrl != null) {
				rtrnObj = overlayImage(object, imageUrl);
			}
		}
		return rtrnObj;
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

			String label = ((Category)object).getName();
			return label == null || label.length() == 0 ?
				getString("_UI_Category_type") :
				getString("_UI_Category_type") + " " + label;
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

		switch (notification.getFeatureID(Category.class)) {
			case CategoriesPackage.CATEGORY__IS_APPLICABLE_FOR_ALL:
			case CategoriesPackage.CATEGORY__CARDINALITY:
			case CategoriesPackage.CATEGORY__IS_ABSTRACT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case CategoriesPackage.CATEGORY__EQUATION_DEFINITIONS:
			case CategoriesPackage.CATEGORY__PROPERTIES:
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
				(CalculationPackage.Literals.IEQUATION_DEFINITION_SECTION_CONTAINER__EQUATION_DEFINITIONS,
				 CalculationFactory.eINSTANCE.createEquationDefinition()));

		newChildDescriptors.add
			(createChildParameter
				(CategoriesPackage.Literals.CATEGORY__PROPERTIES,
				 PropertydefinitionsFactory.eINSTANCE.createComposedProperty()));

		newChildDescriptors.add
			(createChildParameter
				(CategoriesPackage.Literals.CATEGORY__PROPERTIES,
				 PropertydefinitionsFactory.eINSTANCE.createReferenceProperty()));

		newChildDescriptors.add
			(createChildParameter
				(CategoriesPackage.Literals.CATEGORY__PROPERTIES,
				 PropertydefinitionsFactory.eINSTANCE.createEReferenceProperty()));

		newChildDescriptors.add
			(createChildParameter
				(CategoriesPackage.Literals.CATEGORY__PROPERTIES,
				 PropertydefinitionsFactory.eINSTANCE.createIntProperty()));

		newChildDescriptors.add
			(createChildParameter
				(CategoriesPackage.Literals.CATEGORY__PROPERTIES,
				 PropertydefinitionsFactory.eINSTANCE.createFloatProperty()));

		newChildDescriptors.add
			(createChildParameter
				(CategoriesPackage.Literals.CATEGORY__PROPERTIES,
				 PropertydefinitionsFactory.eINSTANCE.createStringProperty()));

		newChildDescriptors.add
			(createChildParameter
				(CategoriesPackage.Literals.CATEGORY__PROPERTIES,
				 PropertydefinitionsFactory.eINSTANCE.createBooleanProperty()));

		newChildDescriptors.add
			(createChildParameter
				(CategoriesPackage.Literals.CATEGORY__PROPERTIES,
				 PropertydefinitionsFactory.eINSTANCE.createEnumProperty()));

		newChildDescriptors.add
			(createChildParameter
				(CategoriesPackage.Literals.CATEGORY__PROPERTIES,
				 PropertydefinitionsFactory.eINSTANCE.createResourceProperty()));
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

}
