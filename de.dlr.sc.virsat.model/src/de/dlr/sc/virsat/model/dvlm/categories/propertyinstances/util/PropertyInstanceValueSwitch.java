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

import java.util.Locale;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;

/**
 * This switch hands back the correct values for property instances
 * it deals with references and composed properties as well. it is generally used for UI 
 * purposes to display correct information to the user.
 * @author fisc_ph
 *
 */
public class PropertyInstanceValueSwitch extends PropertyinstancesSwitch<ATypeInstance> {
	
	public static final int PREFERENCE_ROUNDING_INIT = 3;
	
	private int roundDecimals = PREFERENCE_ROUNDING_INIT;

	public static final String EMPTY_URI = "No Resource";
	public static final String EMPTY_VALUE = "No Value";
	public static final String EMPTY_UNIT = "";
	public static final String UNSET_NAME = "UNDEFINED";
	public static final String UNSET_VALUE = "XXX";
	
	private boolean showUnitForUnitValues = true;
	private boolean showEnumValueDefinitionValues = true;
	private boolean showLocationForReferenceValues = true;
	
	/**
	 * Gets a boolean flag that decides whether to add unit to the value or not
	 * @return true if the unit is added, false otherwise
	 */
	public boolean getShowUnitForUnitValues() {
		return showUnitForUnitValues;
	}

	/**
	 * Sets a boolean flag that decides whether to add unit to the value or not
	 * @param showUnitForUnitValues new value for the flag
	 */
	public void setShowUnitForUnitValues(boolean showUnitForUnitValues) {
		this.showUnitForUnitValues = showUnitForUnitValues;
	}
	
	/**
	 * Gets a boolean flag that decides whether to append value to Enum Name string
	 * @return showEnumValueDefinitionValue 
	 */
	public boolean getShowEnumValueDefinitionValues() {
		return showEnumValueDefinitionValues;
	}
	
	/**
	 * Sets a boolean flag that decides whether to append value to Enum Name string
	 * @param showEnumValueDefinitionValue  
	 */
	public void setShowEnumValueDefinitionValues(boolean showEnumValueDefinitionValue) {
		this.showEnumValueDefinitionValues = showEnumValueDefinitionValue;
	}
	
	/**
	 * Get a boolean flag that decides whether the complete location path for a reference value is shown
	 * @return the showLocationForReferenceValues
	 */
	public boolean getShowLocationForReferenceValues() {
		return showLocationForReferenceValues;
	}

	/**
	 * Sets a boolean flag that decides whether the complete location path for a reference value is shown
	 * @param showLocationForReferenceValues the showLocationForReferenceValues to set
	 */
	public void setShowLocationForReferenceValues(boolean showLocationForReferenceValues) {
		this.showLocationForReferenceValues = showLocationForReferenceValues;
	}

	/**
	 * Constructor for the Switch that allows to set the rounding of the decimals
	 * @param roundDecimals number of decimals to be presented for float values after the comma
	 */
	public PropertyInstanceValueSwitch(int roundDecimals) {
		this.roundDecimals = roundDecimals;
	}
	
	/**
	 * Standard constructor which sets the decimalRounding to 3 digits after the comma
	 */
	public PropertyInstanceValueSwitch() {
	}
	
	@Override
	public ATypeInstance caseReferencePropertyInstance(ReferencePropertyInstance object) {
		// Return the name of the category assignment which is referenced
		// or return the value of the referenced propertyInstance
		ATypeInstance referencedInstance = object.getReference();
		if (referencedInstance instanceof CategoryAssignment) {
			return referencedInstance;
		} else {
			if (referencedInstance != null) {
				return this.doSwitch(referencedInstance);
			} else {
				return null;
			}
		}
	}
	
	@Override
	public ATypeInstance caseComposedPropertyInstance(ComposedPropertyInstance cpi) {
		return cpi.getTypeInstance();
	}

	/**
	 * Call this method to get the actual value that should be displayed
	 * @param ti Type Instance of which to display the Value
	 * @return The Value of it or in case it is a reference the name of the reference
	 */
	public String getValueString(ATypeInstance ti) {
		// CategoryAssignment is part of another package therefore not part of the switch itself
		if (ti instanceof CategoryAssignment) {
			return ((CategoryAssignment) ti).getName();
		}
		
		// Now use the switch to actually decide on the type of PropertyInstance
		PropertyinstancesSwitch<String> valueSwitch = new PropertyinstancesSwitch<String>() {
			@Override
			public String caseValuePropertyInstance(ValuePropertyInstance object) {
				return getSafeValue(object);
			}
			
			@Override
			public String caseUnitValuePropertyInstance(UnitValuePropertyInstance object) {
				return getSafeValue(object) + (showUnitForUnitValues ? " " + getSafeUnit(object) : "");
			}
			
			@Override
			public String caseEnumUnitPropertyInstance(EnumUnitPropertyInstance eupi) {
				EnumValueDefinition evd = eupi.getValue();	
				String propertyValue = EMPTY_VALUE;
				if (evd != null) {
					propertyValue = showEnumValueDefinitionValues ? PropertyInstanceValueSwitch.getEnumValueDefinitionString(evd) : evd.getName();
				}
				return propertyValue + (showUnitForUnitValues ? " " + getSafeUnit(eupi) : "");
			}
			
			@Override
			public String caseArrayInstance(ArrayInstance ai) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < ai.getArrayInstances().size(); ++i) {
					APropertyInstance pi = ai.getArrayInstances().get(i);
					ATypeInstance ti = PropertyInstanceValueSwitch.this.doSwitch(pi);
					
					if (ti instanceof ComposedPropertyInstance) {
						ComposedPropertyInstance cpi = (ComposedPropertyInstance) ti;
						ti = cpi.getTypeInstance();
					}
					
					String valueString = getValueString(ti);
					sb.append(valueString);
				
					if (i != ai.getArrayInstances().size() - 1) {
						sb.append(",");
					}
				}
				String result = sb.toString();
				return result;
			}
			
			@Override
			public String caseResourcePropertyInstance(ResourcePropertyInstance object) {
				URI uri = object.getUri();
				return (uri == null) ? EMPTY_URI : uri.lastSegment();
			}
			
			@Override
			public String caseReferencePropertyInstance(ReferencePropertyInstance object) {
				PropertyInstanceValueSwitch pivSwitch = new PropertyInstanceValueSwitch();
				if (object.getReference() != null) {
					ATypeInstance referencedTi = pivSwitch.doSwitch(object);
					String valueString = ActiveConceptHelper.getContainerQualifedNameForInstance(referencedTi);
					if (referencedTi instanceof IName) {
						if (showLocationForReferenceValues) {
							valueString = ((IName) referencedTi).getName() + " - " + valueString;
						} else {
							valueString = ((IName) referencedTi).getName();
						}
					}
					return valueString;
				} else {
					return "";
				}
			}
			
			public String caseEReferencePropertyInstance(EReferencePropertyInstance object) {
				if (object.getReference() != null) {
					
					EObject value = object.getReference();
					String valueString = value.toString() + " - " + value.eResource().getURI().lastSegment().toString();
					return valueString;
				} else {
					return "";
				}
			}
		};
		return valueSwitch.doSwitch(ti);
	}
	
	@Override
	public ATypeInstance defaultCase(EObject object) {
		if (object instanceof ATypeInstance) {
			return (ATypeInstance) object;
		}
		return super.defaultCase(object);
	}

	/**
	 * Method to safely access the Value of a Property
	 * @param vpi The Property of which to get the Value
	 * @return the Value or EMPTY_VALAUE
	 */
	private String getSafeValue(ValuePropertyInstance vpi) {
		AProperty property = (AProperty) vpi.getType();
		
		String valueString = vpi.getValue();
		try {
			if ((valueString != null) && (property instanceof FloatProperty)) {
				double parsedDouble = Double.parseDouble(valueString);
				String formatString = "%." + roundDecimals + "f";
				valueString = String.format(Locale.ROOT, formatString, parsedDouble);
			} else if (valueString == null) {
				valueString = EMPTY_VALUE;
			}
		
		} catch (NumberFormatException e) {
			return "NaN";
		}
		
		return valueString;
	}

	/**
	 * Method to safely access the Unit of a Property
	 * @param uvpi the Property of which to get the Unit
	 * @return the Unit or an empty unit.
	 */
	private String getSafeUnit(IUnitPropertyInstance uvpi) {
		AUnit unit = uvpi.getUnit();
		if (unit == null || unit.getSymbol() == null || unit.getSymbol().isEmpty()) {
			return "";
		}
		return "[" + unit.getSymbol() + "]";
	}
	
	/**
	 * This method hands back the correct string for an evd
	 * @param evd The EnumValueProeprty for which to get the correct string representation
	 * @return the string consisting of value and name of the EVD
	 */
	public static final String getEnumValueDefinitionString(EnumValueDefinition evd) {
		return Objects.toString(evd.getName(), UNSET_NAME) + "=" + Objects.toString(evd.getValue(), UNSET_VALUE);
	}
}
