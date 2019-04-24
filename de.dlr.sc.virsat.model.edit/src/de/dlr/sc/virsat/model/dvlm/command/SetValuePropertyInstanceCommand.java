/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.command;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;


/**
 * Defines a set command for PropertyValueIsnatnces that check for the actual Property Type.
 * The set command will take care of checking if the new value can be parsed into an Integer or Double format.
 * All other formats will avoid the command from being executed
 * @author fisc_ph
 *
 */
public class SetValuePropertyInstanceCommand extends SetCommand {

	private PropertyInstanceHelper piHelper = new PropertyInstanceHelper();
	
	/**
	 * Standard constructor to initialize the Set Command
	 * @param domain the domain on which the set command should act
	 * @param owner the owning object usually a ValuePropertyInstanhce or one of its sub types
	 * @param feature the structural feature on which this command should act usually referencing the value string of the VPI
	 * @param value The actual value to be set as String
	 */
	public SetValuePropertyInstanceCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,	Object value) {
		super(domain, owner, feature, value);
	}

	@Override
	protected boolean prepare() {
		boolean superPrepare = super.prepare();
		
		if (superPrepare && feature == PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE) {
			APropertyInstance pi = (APropertyInstance) owner;
			
			if (piHelper.isCalculated(pi)) {
				return false;
			}
			
			boolean newValueOk = true;
	
			// In case the type of the Value property is an INT or a Float we should do some checks
			// on the value that is about to be set
			ATypeDefinition td = pi.getType();
			
			boolean isFloat = td != null && td instanceof FloatProperty;
			boolean isInt = td != null && td instanceof IntProperty;
			
			if (value != null && value instanceof String) {
				String stringValue = (String) value;
				if (!stringValue.isEmpty()) { 
					if (isFloat) {
						try {
							Double.parseDouble(stringValue);
						} catch (NumberFormatException e) {
							DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.INFO, DVLMEditPlugin.PLUGIN_ID, "Failed to parse a double " + e.getMessage()));
							newValueOk = false;
						}
					} else if (isInt) {
						try {
							Integer.parseInt(stringValue);
						} catch (NumberFormatException e) {
							DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.INFO, DVLMEditPlugin.PLUGIN_ID, "Failed to parse a integer " + e.getMessage()));
							newValueOk = false;
						}
					}
				}
			}
			return newValueOk;
		}
		
		return superPrepare;
	}
}
