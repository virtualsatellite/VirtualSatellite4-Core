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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;

/**
 * helper methods for bean property, to capture shared functionality, such as
 * setting and retrieving the current Unit
 * @author fisc_ph
 *
 */
public class PropertyInstanceHelper {
	
	/**
	 * Returns the unit name
	 * @param iupi the unitPropertyInstance to be asked for its unit 
	 * @return unit name string
	 */
	public String getUnit(IUnitPropertyInstance iupi) {
		AUnit unit = iupi.getUnit();
		String unitString = (unit != null) ? Objects.toString(unit.getName(), PropertyInstanceValueSwitch.EMPTY_UNIT) : PropertyInstanceValueSwitch.EMPTY_UNIT;
		return unitString;
	}
	
	/**
	 * This method allows to set a unit by a given name
 	 * @param iupi the unitPropertyInstance where to change the unit
	 * @param unitName the name of the unit to be set, do not use the symbol name
	 * @return true in case the unit could be set, false in case the unit was not changed
	 */
	public boolean setUnit(IUnitPropertyInstance iupi, String unitName) {
		SystemOfUnits sou = CategoryAssignmentHelper.getSystemOfUnits(iupi);
		
		if (sou != null) {
			AUnit unit = QudvUnitHelper.getInstance().getUnitByName(sou, unitName);
			if (unit != null) {
				iupi.setUnit(unit);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This method creates a command to change the unit of the bean
	 * @param ed the editing domain to be used when creating the command
	 * @param iupi the unitPropertyInstance where to change the unit
	 * @param unitName the name of the unit but not the symbol name
	 * @return the command that changes the unit. Can lead to an UnexecutableCommand in case the unit cannot be changed.
	 */
	public Command setUnit(EditingDomain ed, IUnitPropertyInstance iupi, String unitName) {
		SystemOfUnits sou = CategoryAssignmentHelper.getSystemOfUnits(iupi);
		
		if (sou != null) {
			AUnit unit = QudvUnitHelper.getInstance().getUnitByName(sou, unitName);
			if (unit != null) {
				return SetCommand.create(ed, iupi, PropertyinstancesPackage.Literals.IUNIT_PROPERTY_INSTANCE__UNIT, unit);
			}
		}
		
		return UnexecutableCommand.INSTANCE;
	}
	
	/**
	 * This method determines whether the passed instance is calculated by some equation.
	 * That is, there is some equation either on the category assignment of the passed instance
	 * or in a parent that has an equation that sets this instance.
	 * @param instance the instance
	 * @return true iff an equation referencing this property instance exists
	 */
	public boolean isCalculated(ATypeInstance instance) {
		if (instance instanceof ComposedPropertyInstance) {
			instance = ((ComposedPropertyInstance) instance).getTypeInstance();
		}
		
		EObject container = (instance instanceof IEquationSectionContainer) ? instance : VirSatEcoreUtil.getEContainerOfClass(instance, IEquationSectionContainer.class);

		if (setterProviders ==  null) {
			setterProviders = loadExtensionPoint();
		}
		
		while (container != null && container instanceof IEquationSectionContainer) {
			IEquationSectionContainer equationSectionContainer = (IEquationSectionContainer) container;
			
			// As we crawl the equation section containers upwards we check each equation...
			
			if (equationSectionContainer.getEquationSection() != null) {
				List<Equation> equations = equationSectionContainer.getEquationSection().getEquations();
				for (Equation equation : equations) {
					
					// ... and see if this equation somehow affects the passed instance
					
					IEquationResult equationResult = equation.getResult();
					boolean isTypeInstanceResult = equationResult instanceof TypeInstanceResult;
					
					if (isTypeInstanceResult) {
						TypeInstanceResult instanceResult = (TypeInstanceResult) equationResult;
						ATypeInstance resultInstance = (ATypeInstance) instanceResult.getReference();
						ITypeInstanceSetter setter = getApplicableSetter(resultInstance);
						// In case there is a SetterProvider registered it should be used to evaluate if the calculation
						// is affecting this property. This is e.g. used for CEF where not a single property is calculated
						// but actually a hole category (Parameter)
						if (setter != null) {
							List<ATypeInstance> affectedTypeInstances = setter.getAffectedTypeInstances(resultInstance);
							if (affectedTypeInstances.contains(instance)) {
								return true;
							}
						} else {
							// In case there is no SetterProvider yet defined, the default case has to be checked. The default case
							// checks that the referenced instance by the equation result is excatly the one in question yb this method.
							return (resultInstance == instance);
						}
					}
				}
			}
			
			container = container.eContainer();
			
			if (container instanceof ComposedPropertyInstance) {
				container = container.eContainer();
			}
		}
		
		return false;
	}
	
	private List<ITypeInstanceSetterProvider<?>> setterProviders;
	
	
	/**
	 * This method loads the extension point and creates a list of available TypeInstanceSetters
	 * This method can be changed and overridden for tests
	 * @return A LIst of TypeInstanceSetter . Return value is never supposed to be null.
	 */
	protected List<ITypeInstanceSetterProvider<?>> loadExtensionPoint() {
		List<ITypeInstanceSetterProvider<?>> setterProviders = new ArrayList<>();
		
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry != null) {
			IConfigurationElement[] expressionExtenders = registry.getConfigurationElementsFor("de.dlr.sc.virsat.model.edit.TypeInstanceSetterProvider");
			
			for (IConfigurationElement configElement : expressionExtenders) {
				try {
					ITypeInstanceSetterProvider<?> setterProvider = (ITypeInstanceSetterProvider<?>) configElement.createExecutableExtension("class");
					setterProviders.add(setterProvider);
				} catch (CoreException e) {
					DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.ERROR, DVLMEditPlugin.PLUGIN_ID, "Could not resolve extension points for type instance setters", e));
				}
			}
		}
		
		return setterProviders;
	}
	
	/**
	 * Checks if there is an applicable type instance setter predefining the value
	 * of this type instance
	 * @param instance the type instance
	 * @return a type instance setter if an applicable one exists
	 */
	private ITypeInstanceSetter getApplicableSetter(ATypeInstance instance) {
		for (ITypeInstanceSetterProvider<?> setterProvider : setterProviders) {

			for (ITypeInstanceSetter setter : setterProvider.getTypeInstanceSetters()) {
				if (setter.isApplicableFor(instance)) {
					return setter;
				}
			}
		}
		return null;
	}
}
