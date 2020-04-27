/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

public abstract class AbstractConceptLanguageValidator extends AbstractDeclarativeValidator {
	
	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> result = new ArrayList<EPackage>();
		result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.virsat.sc.dlr.de/dvlm/v8/c"));
		result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.virsat.sc.dlr.de/dvlm/v8/s"));
		result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.virsat.sc.dlr.de/dvlm/v8/cp"));
		result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.virsat.sc.dlr.de/dvlm/v8/cp/cppd"));
		result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.virsat.sc.dlr.de/dvlm/v8/calc"));
		return result;
	}
}
