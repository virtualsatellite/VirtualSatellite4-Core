/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.dataaccess;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.command.SetCommand;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumPropertyHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyinstancesSwitch;
import de.dlr.sc.virsat.model.dvlm.command.SetValuePropertyInstanceCommand;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

public class FlattenedPropertyInstance {

	// API read only
	private String uuid;
	private String fullQualifiedInstanceName;
	private String typeFullQualifiedInstanceName;
	
	// API read and write
	private String value;
	
	// Type specific
	// API read only
	private String quanitityKindName;
	// API read and write
	private String unitName;
	
	public FlattenedPropertyInstance() { }
	
	/**
	 * Creates a FlattenedPropertyInstance from the existing propertyInstance
	 * @param propertyInstance
	 */
	public FlattenedPropertyInstance(APropertyInstance propertyInstance) {

		setUuid(propertyInstance.getUuid().toString());
		setTypeFullQualifiedInstanceName(propertyInstance.getType().getFullQualifiedName());
		setFullQualifiedInstanceName(propertyInstance.getFullQualifiedInstanceName());
		
		// set value
		PropertyInstanceValueSwitch valueSwitch = new PropertyInstanceValueSwitch();
		valueSwitch.setShowUnitForUnitValues(false);
		valueSwitch.setShowResourceOnlyLastSegment(false);
		valueSwitch.setShowEnumValueDefinitionValues(false);
		setValue(valueSwitch.getValueString(propertyInstance));
		
		// set type specific properties
		new PropertydefinitionsSwitch<String>() {
			@Override
			public String caseAQudvTypeProperty(AQudvTypeProperty object) {
				setQuanitityKindName(object.getQuantityKindName());
				setUnitName(object.getUnitName());
				return null;
			}
			
		}.doSwitch(propertyInstance.getType());
		
	}

	/**
	 * Create a command to unflatten the properties of this instance into a existing property
	 * @param editingDomain
	 * @param property
	 * @return Command
	 */
	public Command unflatten(VirSatTransactionalEditingDomain editingDomain, APropertyInstance property) {
		CompoundCommand updatePropertyCommand = new CompoundCommand();
		System.out.println(property.getFullQualifiedInstanceName());
		
		PropertyinstancesSwitch<Command> valueSwitch = new PropertyinstancesSwitch<Command>() {
			@Override
			public Command caseValuePropertyInstance(ValuePropertyInstance object) {
				return SetValuePropertyInstanceCommand.create(
					editingDomain, 
					object, 
					PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, 
					getValue());
			};
			
			@Override
			public Command caseResourcePropertyInstance(ResourcePropertyInstance object) {
				return SetValuePropertyInstanceCommand.create(
					editingDomain, 
					object, 
					PropertyinstancesPackage.Literals.RESOURCE_PROPERTY_INSTANCE__RESOURCE_URI, 
					URI.createURI(getValue()).toPlatformString(true));
			}
			@Override
			public Command caseEnumUnitPropertyInstance(EnumUnitPropertyInstance object) {
				return SetValuePropertyInstanceCommand.create(
					editingDomain, 
					object, 
					PropertyinstancesPackage.Literals.ENUM_UNIT_PROPERTY_INSTANCE__VALUE, 
					new EnumPropertyHelper().getEvdForName((EnumProperty) object.getType(), getValue()));
			}
		};
		
		Command setValueCommand = valueSwitch.doSwitch(property);
		addCommandIfExecuteable(updatePropertyCommand, setValueCommand, "Can't update value to " + getValue());
		
		Command test = new PropertydefinitionsSwitch<Command>() {
			@Override
			public Command caseAQudvTypeProperty(AQudvTypeProperty object) {
				Command commandSetUnit = SetCommand.create(editingDomain, object, PropertydefinitionsPackage.Literals.AQUDV_TYPE_PROPERTY__UNIT_NAME, getUnitName());
				return commandSetUnit;
			}
			
		}.doSwitch(property.getType());
		
		addCommandIfExecuteable(updatePropertyCommand, test, "Can't update unit to " + getUnitName());
		
		return updatePropertyCommand;
	}
	
	/**
	 * Appends a command to an addCommand if it is not null and executable
	 * Will print msgNotExecutable if it is not executable
	 * @param command
	 * @param addCommand
	 * @param msgNotExecutable
	 */
	private void addCommandIfExecuteable(CompoundCommand command, Command addCommand, String msgNotExecutable) {
		if (addCommand != null) {
			if (addCommand.canExecute()) {
				command.append(addCommand);
			} else {
				System.out.println(msgNotExecutable);
			}
		}
	}

	public String getFullQualifiedInstanceName() {
		return fullQualifiedInstanceName;
	}

	public void setFullQualifiedInstanceName(String fullQualifiedInstanceName) {
		this.fullQualifiedInstanceName = fullQualifiedInstanceName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getQuanitityKindName() {
		return quanitityKindName;
	}

	public void setQuanitityKindName(String quanitityKindName) {
		this.quanitityKindName = quanitityKindName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getTypeFullQualifiedInstanceName() {
		return typeFullQualifiedInstanceName;
	}

	public void setTypeFullQualifiedInstanceName(String typeFullQualifiedInstanceName) {
		this.typeFullQualifiedInstanceName = typeFullQualifiedInstanceName;
	}
}
