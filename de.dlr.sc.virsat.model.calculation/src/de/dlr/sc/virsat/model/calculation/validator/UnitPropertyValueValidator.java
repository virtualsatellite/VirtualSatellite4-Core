/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.validator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;

import de.dlr.sc.virsat.build.marker.util.VirSatValidationMarkerHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;


/**
 * Validator to check if the value and units have been set.
 * 
 * This value enforces the rule, that when a Quantity Kind QK is defined for a property
 * in a concept, a value and a unit has to be defined for the property instance. 
 * 
 * @author fisc_ph
 *
 */
public class UnitPropertyValueValidator implements IStructuralElementInstanceValidator {

	protected VirSatValidationMarkerHelper vvmHelper = new VirSatValidationMarkerHelper();
	
	@Override
	public boolean validate(StructuralElementInstance sei) {
		List<IUnitPropertyInstance> upis = new ArrayList<>();
		
		// First collect all UPIs from the current SEI
		// but don't iterate over the children of the current SEI.
		// Thus only search in the CAs of the current SEI.
		sei.getCategoryAssignments().forEach(ca -> {
			ca.eAllContents().forEachRemaining((eObject) -> {
				if (eObject instanceof IUnitPropertyInstance) {
					upis.add((IUnitPropertyInstance) eObject);
				}
			});
		});
		
		// Now loop over all SEIs and see if a UPI has been set incorrectly
		boolean hasSucceded = true;
		for (IUnitPropertyInstance upi : upis) {
			AQudvTypeProperty qtp = (AQudvTypeProperty) upi.getType();
			boolean hasQkBeingSet = (qtp.getQuantityKindName() != null && !qtp.getQuantityKindName().isEmpty());
			boolean hasSelectedUnit = upi.getUnit() != null;
			boolean hasValue = hasValue(upi);
			
			if (hasQkBeingSet) {
				if (!hasSelectedUnit || !hasValue) {
					vvmHelper.createDVLMValidationMarker(IMarker.SEVERITY_WARNING, "A quantity kind is set for the value: " + upi.getFullQualifiedInstanceName() +  " . Accordingly a value and unit needs to be specified.", upi);
					hasSucceded = false;
				}
			}
		}
		
		return hasSucceded;
	}
	
	/**
	 * Method that safely checks on a UnitPropertyInstance if there is a value attached to it.
	 * Both the UnitValuePropertyInstance and the EnumUnitPropertyInstance may have a value, but
	 * they are treated differently since one is having a value as string where as the other one
	 * is just referencing the enum from the property definition
	 * @param upi A UnitValueProperty which should be checked if a value is set
	 * @return true in case a value is set, or false if the value is null or empty
	 */
	private boolean hasValue(IUnitPropertyInstance upi) {
		// First Check the string of the UnitValuePropertyInstance than check for the Enum if it is set.
		if (upi instanceof UnitValuePropertyInstance) {
			UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) upi;
			return (uvpi.getValue() != null && !uvpi.getValue().isEmpty());
		} else if (upi instanceof EnumUnitPropertyInstance) {
			EnumUnitPropertyInstance eupi = (EnumUnitPropertyInstance) upi;
			return (eupi.getValue() != null);
		}
		
		// For all other types return false since we don't know them yet.
		return false;
	}
}
