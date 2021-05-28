/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.qudv;

import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;

public class BeanUnitPrefixed extends ABeanConversionBasedUnit<PrefixedUnit> {
	
	BeanPrefix getPrefix() {
		return new BeanPrefix(unit.getPrefix());
	}
	
	void setPrefix(BeanPrefix beanPrefix) {
		unit.setPrefix(beanPrefix.getPrefix());
	}
}
