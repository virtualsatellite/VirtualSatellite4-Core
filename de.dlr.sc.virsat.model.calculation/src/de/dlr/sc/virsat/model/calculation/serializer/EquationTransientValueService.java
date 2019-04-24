/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.parsetree.reconstr.ITransientValueService;
import org.eclipse.xtext.parsetree.reconstr.impl.DefaultTransientValueService;

import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;

/**
 * This class implements the Transient Value Service. It handles for example the UUID
 * when serializing the Equations from EMF into their textual representation. The Xtext
 * language has no feature for UUID. Accordingly it cannot be serialized. Xtext should 
 * therefore not know that the UUID has to be serialized. There might be further features 
 * in the future that should not be serialized such as features concerned with inheritance
 * @author fisc_ph
 *
 */
public class EquationTransientValueService extends DefaultTransientValueService implements ITransientValueService {

	@Override
	public boolean isCheckElementsIndividually(EObject owner, EStructuralFeature feature) {
		return super.isCheckElementsIndividually(owner, feature);
	}

	@Override
	public boolean isTransient(EObject owner, EStructuralFeature feature, int index) {
		// In case the feature is pointing to the Attribute UUID we will sayy it is transient
		// In the XMI serialization it is not but the xtext language doesn't know it
		// In case it would be set to yes, the language would complain that it cannot be processed
		if (feature.equals(GeneralPackage.Literals.IUUID__UUID)) {
			return true;
		}
		
		// From the Equations Section we do not serialize the serializedEquations attribute
		// it is only intended for backup purposes. in case the equations cannot be deserialized.
		if (owner instanceof EquationSection && feature.equals(CalculationPackage.Literals.EQUATION_SECTION__SERIALIZED_STATEMENTS)) {
			return true;
		}
		
		// Handle all others as exepcted.
		return super.isTransient(owner, feature, index);
	}

}
