/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.compute.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;

/**
 * Class representing a warning that the equation helper has encountered
 * incompatible quantity kinds.
 * @author muel_s8
 *
 */
public class IncompatibleQuantityKindsProblem extends EvaluationProblem {
	public static final String WARNING_MESSAGE_PREFIX = "Computed quantity kinds incompatible. Quantity kinds should be: ";
	public static final String WARNING_MESSAGE_INFIX = ", but was: ";
	public static final String WANRING_MESSAGE_SUFFIX = ".";
	
	private final String warningMessage;
	
	/**
	 * Default constructor
	 * @param problematicObject the problematic object
	 * @param expectedBaseQuantityKinds the base quantity kinds that were expected
	 * @param actualBaseQuantityKinds the base quantity kinds that were actually found
	 */
	public IncompatibleQuantityKindsProblem(EObject problematicObject, Map<AQuantityKind, Double> expectedBaseQuantityKinds, Map<AQuantityKind, Double> actualBaseQuantityKinds) {
		super(problematicObject);
		String expectedString = getQuantityKindsString(expectedBaseQuantityKinds);
		String actualString = getQuantityKindsString(actualBaseQuantityKinds);
		this.warningMessage = WARNING_MESSAGE_PREFIX + expectedString + WARNING_MESSAGE_INFIX + actualString + WANRING_MESSAGE_SUFFIX;
	}
	
	/**
	 * Tries to get a unit management from the problematic eobject and then searches
	 * for a matching quantity kind derived from the base quantity kinds
	 * @param quantityKinds the base quantity kinds
	 * @return the matching derived quantity kinds
	 */
	private List<AQuantityKind> getDerivedQuantityKind(Map<AQuantityKind, Double> quantityKinds) {
		ATypeInstance containingTi = VirSatEcoreUtil.getEContainerOfClass(getProblematicObject(), ATypeInstance.class);
		Repository repo = CategoryAssignmentHelper.getRepository(containingTi);
		List<AQuantityKind> derivedQuantityKinds = new ArrayList<>();
		
		if (repo != null) {
			List<SystemOfQuantities> allSoqs = repo.getUnitManagement().getSystemOfUnit().getSystemOfQuantities();
			for (SystemOfQuantities soq : allSoqs) {
				List<AQuantityKind> derivedQks = QudvUnitHelper.getInstance().getQuantityKindByBaseKinds(soq, quantityKinds);
				derivedQuantityKinds.addAll(derivedQks);
			}
		}
		
		return derivedQuantityKinds;
	}
	
	/**
	 * Gets a string representation of the passed quantity kinds
	 * @param baseQuantityKinds the base quantity kinds to turn into a string version
	 * @return a string representation of the passed quantity kinds
	 */
	private String getQuantityKindsString(Map<AQuantityKind, Double> baseQuantityKinds) {
		List<AQuantityKind> derivedQuantityKinds = getDerivedQuantityKind(baseQuantityKinds);
		String baseQuantityKindsString = QudvUnitHelper.getInstance().convertToString(baseQuantityKinds);
		if (derivedQuantityKinds.isEmpty()) {
			return baseQuantityKindsString;
		} else {
			String qkString = baseQuantityKindsString;
			qkString += " (";
			qkString += derivedQuantityKinds.stream()
					.map(AQuantityKind::getName)
					.collect(Collectors.joining(", "));
			qkString += ")";
			return qkString;
		}
	}
	
	@Override
	public String toString() {
		return warningMessage;
	}
}
