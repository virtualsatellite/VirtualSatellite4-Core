/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.exception;

import de.dlr.sc.virsat.model.dvlm.structural.RelationInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;

/**
 * This class of errors is thrown in case things go wrong concerning
 * the "applicable for" constraints in our data model.
 * @author fisc_ph
 *
 */
public class DVLMMissingTypeException extends ADVLMModelException {

	private static final long serialVersionUID = 2592710093412794541L;

	private static final String MISSING_TYPE_CATEGORY_INSTANCE = "The CategoryInstance (Name: %s - UUID: %s) is not typed by a Category!";
	private static final String MISSING_TYPE_RELATION_INSTANCE = "The RelationInstance (Name: %s - UUID: %s) is not typed by a Relation!";
	private static final String MISSING_TYPE_STRUCTURAL_ELEMENT_INSTANCE = "The StructuralElementInstance (Name: %s - UUID: %s) is not typed by a StructralElement!";
	
	/**
	 * Hidden Constructor to create an Exception with a given message
	 * @param message the message that should be used for the error
	 */
	protected DVLMMissingTypeException(String message) {
		super(message);
	}

	/**
	 * Call this method to check if the Type has been correctly set. In case it is not a correct Exception is thrown
	 * @param ca The CategoryAssignment to be checked for its Type
	 */
	public static void checkAndThrowMissingCategory(CategoryAssignment ca) {
		if (ca.getType() == null || !(ca.getType() instanceof Category)) {
			throw new DVLMMissingTypeException(String.format(MISSING_TYPE_CATEGORY_INSTANCE, ca.getName(), ca.getUuid()));
		}
	}
	
	/**
	 * Call this method to check if the Type has been correctly set. In case it is not a correct Exception is thrown
	 * @param ri The RelationInstance to be checked for its Type
	 */
	public static void checkAndThrowMissingRelation(RelationInstance ri) {
		if (ri.getType() == null) {
			throw new DVLMMissingTypeException(String.format(MISSING_TYPE_RELATION_INSTANCE, ri.getName(), ri.getUuid()));
		}
	}

	/**
	 * Call this method to check if the Type has been correctly set. In case it is not a correct Exception is thrown
	 * @param sei The StructralElementInstance to be checked for its Type
	 */
	public static void checkAndThrowMissingStructuralElement(StructuralElementInstance sei) {
		if (sei.getType() == null) {
			throw new DVLMMissingTypeException(String.format(MISSING_TYPE_STRUCTURAL_ELEMENT_INSTANCE, sei.getName(), sei.getUuid()));
		}
	}
}
