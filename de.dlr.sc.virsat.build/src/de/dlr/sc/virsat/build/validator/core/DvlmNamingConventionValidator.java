/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.validator.core;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;

/**
 * Implementation of a Validator that checks if a Name matches the
 * CamelCase Convention as usually used in Java or not
 *  
 * @author fisc_ph
 *
 */
public class DvlmNamingConventionValidator extends ADvlmCoreValidator implements IStructuralElementInstanceValidator {

	//includes capital and small letters, numbers and underline "_" but no spaces and especially no dots
	public static final String CAMELCASE_PATTERN_ARBITRARY = "^[a-zA-z]+\\w*";
	public static final String WARNING_PREFIX = "The name of \'";
	public static final String WARNING_EMPTY_NAME_SUFFIX = "\' is not set.";
	public static final String WARNING_DOTS_SUFFIX = "\' contains dots.";
	public static final String WARNING_CAMEL_CASE_SUFFIX = "\' does not match the camelCase convention.";
	
	@Override
	public boolean validate(StructuralElementInstance sei) {
		boolean validationSuccessful = true;

		Set<IName> iNames = new HashSet<>();
		
		// Loop over all contained IName interfaces and see if one provides
		// exactly the same full qualified name as another
		EcoreUtil.getAllContents(sei.getCategoryAssignments(), true).forEachRemaining((object) -> {
			if (object instanceof CategoryAssignment) {
				CategoryAssignment iInstance = (CategoryAssignment) object;
				iNames.add(iInstance);
			}
		});
		
		// Add the current SEI to be checked as well
		iNames.add(sei);
		
		// Now see if there is a set with more than one object
		// if yes report these two objects and set a resource marker
		for (IName iName : iNames) {
			IInstance iInstance = (IInstance) iName;
			IUuid iUuid = (IUuid) iInstance;
			String name = iName.getName();
			String fqn = iInstance.getFullQualifiedInstanceName();
			boolean isCamelCase = name != null && name.matches(CAMELCASE_PATTERN_ARBITRARY);
			if (name == null || name.isEmpty()) {
				vvmHelper.createEMFValidationMarker(IMarker.SEVERITY_WARNING, WARNING_PREFIX + fqn + WARNING_EMPTY_NAME_SUFFIX, iUuid, GeneralPackage.Literals.INAME__NAME);
				validationSuccessful = false;
			} else if (!isCamelCase) {
				if (name.contains(".")) {
					vvmHelper.createEMFValidationMarker(IMarker.SEVERITY_ERROR, WARNING_PREFIX + fqn + WARNING_DOTS_SUFFIX, iUuid, GeneralPackage.Literals.INAME__NAME);
					validationSuccessful = false;
				} else {
					vvmHelper.createEMFValidationMarker(IMarker.SEVERITY_WARNING, WARNING_PREFIX + fqn + WARNING_CAMEL_CASE_SUFFIX, iUuid, GeneralPackage.Literals.INAME__NAME);
					validationSuccessful = false;
				}
			}
		}
		
		return validationSuccessful;
	}
}
