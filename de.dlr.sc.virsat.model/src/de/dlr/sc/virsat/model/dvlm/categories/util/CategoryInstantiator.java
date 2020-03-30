/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.calculation.AExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.ALiteral;
import de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.AdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput;
import de.dlr.sc.virsat.model.dvlm.calculation.SetFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.model.dvlm.calculation.util.CalculationSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IIntrinsicTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * This class will provide functionality to create an onthological instance of an existing category
 * 
 * @author kova_an
 *
 */
public class CategoryInstantiator {

	/**
	 * Method to create a category assignment for a given category
	 * 
	 * @param category
	 *            The category that will be a type of the created category assignment
	 * @param instanceName
	 *            The name that should be used for the category assignment
	 * @return a category assignment with corresponding property instances
	 */
	public CategoryAssignment generateInstance(Category category, String instanceName) {
		if (category.isIsAbstract()) {
			return null;
		}
		
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca.setType(category);
		ca.setName(instanceName);

		for (AProperty property : category.getAllProperties()) {
			addPropertyInstancesForPropertyIntoCategoryAssignment(ca, property);
		}
		
		EcoreUtil.getAllProperContents(ca, true).forEachRemaining(object -> {
			if (object instanceof IUnitPropertyInstance) {
				IUnitPropertyInstance upi = (IUnitPropertyInstance) object;
				setDefaultUnit(upi, upi.eContainer());
			}
		});
		
		if (category.getAllEquationDefinitions().size() > 0) {
			ca.setEquationSection(CalculationFactory.eINSTANCE.createEquationSection());
			for (EquationDefinition equationDefinition : category.getAllEquationDefinitions()) {
				addEquationForEquationDefinitionIntoCategoryAssignment(ca, equationDefinition);
			}	
		}
		
		return ca;
	}

	/**
	 * Call this method to create a new property instance within an array instance. This
	 * method does not add the newly created instance into the array. It just makes sure
	 * that it fits to the type defined by the arrayInstance
	 * 
	 * @param arrayInstance the arrayInstance in which to create a new property instance
	 * @param type the type of the instantiated typeInstance
	 * @return A newly create propertyInstance that can be attached and store in the given arrayInstance.
	 */
	public APropertyInstance generateInstance(ArrayInstance arrayInstance, Category type) {
		AProperty arrayPropertyType = (AProperty) arrayInstance.getType(); 
		APropertyInstance pi = createInstanceForProperty(arrayPropertyType, type);
		
		if (pi instanceof IUnitPropertyInstance) {
			IUnitPropertyInstance upi = (IUnitPropertyInstance) pi;
			setDefaultUnit(upi, arrayInstance);
		}
		
		EcoreUtil.getAllProperContents(pi, true).forEachRemaining(object -> {
			if (object instanceof IUnitPropertyInstance) {
				IUnitPropertyInstance upi = (IUnitPropertyInstance) object;
				setDefaultUnit(upi, arrayInstance);
			}
		});
		
		return pi;
	}
	
	/**
	 * Call this method to create a new property instance within an array instance. This
	 * method does not add the newly created instance into the array. It just makes sure
	 * that it fits to the type defined by the arrayInstance
	 * 
	 * @param arrayInstance the arrayInstance in which to create a new property instance
	 * @return A newly create propertyInstance that can be attached and store in the given arrayInstance.
	 */
	public APropertyInstance generateInstance(ArrayInstance arrayInstance) {
		return generateInstance(arrayInstance, null);
	}
	
	/**
	 * initial method to add an equation into a categoryAssignemnt. 
	 * @param ca the category assignment where to add the equations to
	 * @param equationDefinition the type of equation
	 */
	public void addEquationForEquationDefinitionIntoCategoryAssignment(CategoryAssignment ca, EquationDefinition equationDefinition) {
		Equation equation = CalculationFactory.eINSTANCE.createEquation();
		equation.setDefinition(equationDefinition);
		
		EcoreUtil.Copier copier = new EcoreUtil.Copier(true, true);
		IEquationDefinitionResult definitionResult = equationDefinition.getResult();
		if (definitionResult instanceof EquationIntermediateResult) {
			equation.setResult((IEquationResult) copier.copy(definitionResult));
		} else if (definitionResult instanceof TypeDefinitionResult) {
			TypeDefinitionResult tdr = (TypeDefinitionResult) definitionResult;
			ATypeDefinition reference = tdr.getReference();
			CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(ca);
			APropertyInstance instance = caHelper.getPropertyInstance((AProperty) reference);
			
			TypeInstanceResult tir = CalculationFactory.eINSTANCE.createTypeInstanceResult();
			if (instance instanceof ComposedPropertyInstance) {
				CategoryAssignment otherCa = ((ComposedPropertyInstance) instance).getTypeInstance();
				tir.setReference(otherCa);
			} else {
				tir.setReference(instance);
			}
			equation.setResult(tir);
		}
		
		AExpression expression = createExpressionForEquation(ca, equationDefinition.getExpression());
		equation.setExpression(expression);
		
		ca.getEquationSection().getEquations().add(equation);
	}

	/**
	 * initial method to add a property as instance into a categoryAssignemnt. It handles the case
	 * of dealing with arrays or non array properties 
	 * @param ca the category assignment where to add the property instance to
	 * @param property the type of property the instance should refer to
	 */
	public void addPropertyInstancesForPropertyIntoCategoryAssignment(CategoryAssignment ca, AProperty property) {
		
		IArrayModifier arrayModifier = property.getArrayModifier();
		
		APropertyInstance pi = null;
		
		if (arrayModifier != null) {
			pi = createArrayInstanceForProperty(property);
		} else {
			pi = createInstanceForProperty(property, null);
		}
		
		ca.getPropertyInstances().add(pi);
	}

	/**
	 * This method handles the generation of array instances. Once a property contains
	 * a modifier indicating an array an arrayInstance has to generated for the CategoryAssignment.
	 * in case the array is of a fixed size, the correct amount of propertyinstances will be added to the
	 * array instance.
	 * @param property the property which will be used to type the arrayinstance.
	 * @return the newly created arrayinstance
	 */
	private ArrayInstance createArrayInstanceForProperty(AProperty property) {
		ArrayInstance arrayInstance = PropertyinstancesFactory.eINSTANCE.createArrayInstance();
		arrayInstance.setType(property);

		IArrayModifier arrayModifier = property.getArrayModifier();
		if (arrayModifier instanceof StaticArrayModifier) {
			StaticArrayModifier staticArrayModifier = (StaticArrayModifier) arrayModifier;
			for (int i = 0; i < staticArrayModifier.getArraySize(); i++) {
				APropertyInstance pi = createInstanceForProperty(property, null);
				arrayInstance.getArrayInstances().add(pi);
			}
		}
		
		return arrayInstance;
	}

	/**
	 * Adds or removes Property Instances in the Category Assignment if in the corresponding Category some Properties have been added or removed. Recursively
	 * updates contained category assignments in case of composed properties
	 * 
	 * @param ca Category Assignment which will be updated
	 */
	public void updateCategoryAssignment(CategoryAssignment ca) {
		Category category = (Category) ca.getType();

		for (Iterator<APropertyInstance> iterator = ca.getPropertyInstances().iterator(); iterator.hasNext();) {
			APropertyInstance propertyInstance = iterator.next();
			boolean propertyWasRemovedFromCategory = (!category.getProperties().contains(propertyInstance.getType()));

			if (propertyWasRemovedFromCategory) {
				iterator.remove();
			} else if (propertyInstance instanceof ComposedPropertyInstance) {
				// cascade update on composed properties
				ComposedPropertyInstance cpi = (ComposedPropertyInstance) propertyInstance;
				CategoryAssignment containedCa = cpi.getTypeInstance();
				updateCategoryAssignment(containedCa);
			}
		}

		List<ATypeDefinition> propertiesReferencedByCa = new ArrayList<>();
		for (APropertyInstance propertyInstance : ca.getPropertyInstances()) {
			propertiesReferencedByCa.add(propertyInstance.getType());
		}

		// added properties
		for (AProperty property : category.getProperties()) {
			if (!propertiesReferencedByCa.contains(property)) {
				addPropertyInstancesForPropertyIntoCategoryAssignment(ca, property);
			}
		}
	}

	/**
	 * Creates a corresponding instance for a given property, e.g. IntProperty -> UnitValuePropertyInstance
	 * @param property where the instance will be created
	 * @param category the category type of the new instance if property is a composed property. If null, the type
	 * of the composed property will be used. This should be a category extending the type of the composed property.
	 * @return the created property instance
	 */
	private APropertyInstance createInstanceForProperty(AProperty property, Category category) {
		APropertyInstance propertyInstance;

		propertyInstance = new PropertydefinitionsSwitch<APropertyInstance>() {
			@Override
			public APropertyInstance caseIntProperty(IntProperty object) {
				return PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
			}

			@Override
			public APropertyInstance caseFloatProperty(FloatProperty object) {
				return PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
			}

			@Override
			public APropertyInstance caseStringProperty(StringProperty object) {
				return PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
			}

			@Override
			public APropertyInstance caseBooleanProperty(BooleanProperty object) {
				return PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
			}

			@Override
			public APropertyInstance caseEnumProperty(EnumProperty ep) {
				// Create the instance of the ENUM Property
				EnumUnitPropertyInstance eupi =  PropertyinstancesFactory.eINSTANCE.createEnumUnitPropertyInstance();
				
				// In case the property has a default value defined for the ENUM, than set it accordingly
				if (ep.getDefaultValue() != null) {
					eupi.setValue(ep.getDefaultValue());
				}
				return eupi;
			}

			@Override
			public APropertyInstance caseReferenceProperty(ReferenceProperty object) {
				return PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
			}
			
			@Override
			public APropertyInstance caseEReferenceProperty(EReferenceProperty object) {
				return PropertyinstancesFactory.eINSTANCE.createEReferencePropertyInstance();
			}
			
			@Override
			public APropertyInstance caseResourceProperty(ResourceProperty object) {
				return PropertyinstancesFactory.eINSTANCE.createResourcePropertyInstance();
			}

			@Override
			public APropertyInstance caseComposedProperty(ComposedProperty object) {
				ComposedPropertyInstance composedPropertyInstance = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
				String containedCategoryAssignmentName = object.getName();
				Category type = category != null ? category : object.getType();
				CategoryAssignment containedCategoryAssignment = generateInstance(type, containedCategoryAssignmentName);
				composedPropertyInstance.setTypeInstance(containedCategoryAssignment);
				return composedPropertyInstance;
			}
		}.doSwitch(property);
		
		if (propertyInstance == null) {
			throw new IllegalArgumentException("Unknown property " + property.getName());
		}

		propertyInstance.setType(property);
		
		// Set the default value of the property isntance in case one is defined in the property definition
		if (property instanceof IIntrinsicTypeProperty && propertyInstance instanceof ValuePropertyInstance) {
			String defaultValue = ((IIntrinsicTypeProperty) property).getDefaultValue();
			if (defaultValue != null) {
				((ValuePropertyInstance) propertyInstance).setValue(defaultValue);
			}
		}
		
		return propertyInstance;
	}
	
	/**
	 * Handle setting a default unit if one is set
	 * @param uvpi the unit value property instance
	 * @param parentInstance the parent instance of the property instance
	 */
	protected void setDefaultUnit(IUnitPropertyInstance uvpi, EObject parentInstance) {
		
		if (!(uvpi instanceof APropertyInstance)) {
			return;
		}
		
		AProperty property = (AProperty) ((ATypeInstance) uvpi).getType();
		
		if (!(property instanceof AQudvTypeProperty)) {
			return;
		}
		
		AQudvTypeProperty qudvTypeProperty = (AQudvTypeProperty) property;
		String defaultUnitName = qudvTypeProperty.getUnitName();
		
		if (defaultUnitName == null) {
			// If no default unit is specified directly for the property, 
			// check if there is some parent composed property that has a default unit
			
			EObject container = parentInstance;
			
			while (defaultUnitName == null && container != null) {
				if (container instanceof ComposedPropertyInstance) {
					ComposedPropertyInstance cpi = (ComposedPropertyInstance) container;
					ComposedProperty cp = (ComposedProperty) cpi.getType();
					defaultUnitName = cp.getUnitName();
				} 
				
				container = container.eContainer();
			}
		}
		
		if (defaultUnitName != null) {
			
			// Search for the repository by getting the concept from the property
			// And by design the concept should be contained in a repository 
			// The repository can be null in the case of test cases, so we need to check for this
			// After we have found the repository we can search for the unit by name
			
			Concept concept = ActiveConceptHelper.getConcept(qudvTypeProperty);
			if (concept != null) {
				EObject container = concept.eContainer();
				if (container != null && container instanceof Repository) {
					Repository repo = (Repository) container;
					SystemOfUnits sof = repo.getUnitManagement().getSystemOfUnit();
					AUnit unit = QudvUnitHelper.getInstance().getUnitByName(sof, defaultUnitName);
					uvpi.setUnit(unit);
				}
			}
		} 
	}
	
	/**
	 * Creates a corresponding expression for a given expression of an equationDefintion, 
	 * e.g. ReferencedDefinitionInput -> ReferencedInput
	 * @param ca the category assignment of this 
	 * @param expressionDefinition expression from an equationDefinition
	 * @return the created expression
	 */
	public AExpression createExpressionForEquation(CategoryAssignment ca, AExpression expressionDefinition) {
		EcoreUtil.Copier copier = new EcoreUtil.Copier(true, true);

		AExpression expression = new CalculationSwitch<AExpression>() {
			@Override
			public AExpression caseReferencedDefinitionInput(ReferencedDefinitionInput object) {
				ReferencedInput referencedInput = CalculationFactory.eINSTANCE.createReferencedInput();
				referencedInput.setDefinition(object);
				return referencedInput;
			}
			
			@Override
			public AExpression caseAOpRightExpression(AOpRightExpression object) {
				AOpRightExpression expression = (AOpRightExpression) copier.copy(object);
				AExpression right = object.getRight();
				expression.setRight(createExpressionForEquation(ca, right));
				return expression;
			}
			
			@Override
			public AExpression caseALeftOpRightExpression(ALeftOpRightExpression object) {
				ALeftOpRightExpression expression = (ALeftOpRightExpression) copier.copy(object);
				AExpression left = object.getLeft();
				AExpression right = object.getRight();
				expression.setLeft(createExpressionForEquation(ca, left));
				expression.setRight(createExpressionForEquation(ca, right));
				return expression;
			}
			
			public AExpression caseSetFunction(SetFunction object) {
				SetFunction setFunction = (SetFunction) copier.copy(object);
				setFunction.setTypeDefinition(object.getTypeDefinition());
				return setFunction;
			};
			
			public AExpression caseAdvancedFunction(AdvancedFunction object) {
				AdvancedFunction advancedFunction = (AdvancedFunction) copier.copy(object);
				advancedFunction.getInputs().clear();
				
				for (AExpression input : object.getInputs()) {
					advancedFunction.getInputs().add(createExpressionForEquation(ca, input));
				}
				
				return advancedFunction;
			};
			
			public AExpression caseALiteral(ALiteral object) {
				return (AExpression) copier.copy(object);
			};
			
		}.doSwitch(expressionDefinition);
		
		if (expression == null) {
			throw new IllegalArgumentException("Unknown expression " + expressionDefinition);
		}

		return expression;
	}

}
