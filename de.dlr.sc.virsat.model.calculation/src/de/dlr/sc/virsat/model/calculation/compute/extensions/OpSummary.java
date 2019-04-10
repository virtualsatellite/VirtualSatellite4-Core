/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.compute.extensions;

import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.summary.Sum;

import de.dlr.sc.virsat.model.calculation.compute.AAdvancedFunctionOp;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.SetFunction;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * Operation that sums all the inputs
 * @author muel_s8
 *
 */

public class OpSummary extends AAdvancedFunctionOp {

	@Override
	public double apply(double[] inputs) {
		Sum sum = new Sum();
		return sum.evaluate(inputs);
	}

	@Override
	public Map<AQuantityKind, Double> applyOnQuantityKinds(AAdvancedFunction advancedFunction, Map<AQuantityKind, Double>[] inputQuantityKinds) {
		
		if (inputQuantityKinds.length > 0) {
			// If we have at least one input just use the default implementation
			return super.applyOnQuantityKinds(advancedFunction, inputQuantityKinds);
		}
		
		// The empty sum is defined to be 0 dimensionless in the default case
		// But if we know the type of the object we want to sum, then we can specify
		// a better default quantity kind.
		
		// This is helpful when equations and default quantity kinds are already defined in 
		// the concept.
		
		if (advancedFunction instanceof SetFunction) {
			SetFunction setFunction = (SetFunction) advancedFunction;
			
			ATypeDefinition td = setFunction.getTypeDefinition();
			String quantityKindName = null;
			
			if (td instanceof ComposedProperty) {
				ComposedProperty cp = (ComposedProperty) td;
				quantityKindName = cp.getQuantityKindName();
			} else if (td instanceof AQudvTypeProperty) {
				AQudvTypeProperty qudvTypeProperty = (AQudvTypeProperty) td;
				quantityKindName = qudvTypeProperty.getQuantityKindName();
			}
			
			if (quantityKindName != null) {
				AQuantityKind qk = getEmptySumQuantityKind(td, quantityKindName);
				if (qk != null) {
					return QudvUnitHelper.getInstance().getBaseQuantityKinds(qk);
				}
			}
		}
		
		return super.applyOnQuantityKinds(advancedFunction, inputQuantityKinds);
	}
	
	/**
	 * Get the default quantity kind of this sum operation if there are no inputs.
	 * @param td the type definition (used to get the concept)
	 * @param quantityKindName the quantity kind name
	 * @return a matching quantity kind if it exists in a system of quantity kinds
	 */
	private AQuantityKind getEmptySumQuantityKind(ATypeDefinition td, String quantityKindName) {
		Repository repo = (Repository) ActiveConceptHelper.getConcept(td).eContainer();
		if (repo != null) {
			List<SystemOfQuantities> allSoqs = repo.getUnitManagement().getSystemOfUnit().getSystemOfQuantities();
			for (SystemOfQuantities soq : allSoqs) {
				AQuantityKind qk = QudvUnitHelper.getInstance().getQuantityKindByName(soq, quantityKindName);
				if (qk != null) {
					return qk;
				}
			}
		}
		
		return null;
	}
}
