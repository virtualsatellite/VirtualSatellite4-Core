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

import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;

public class BeanPrefix {

	private Prefix prefix;
	
	public BeanPrefix() { }
	
	public BeanPrefix(Prefix prefix) {
		this.prefix = prefix;
	}
	
	public Prefix getPrefix() {
		return prefix;
	}
	
	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}
	
	public String getSymbol() {
		return prefix.getSymbol();
	}

	public void setSymbol(String symbol) {
		prefix.getSymbol();
	}
	
	Double getFactor() {
		return prefix.getFactor();
	}
	
	void setFactor(Double factor) {
		prefix.setFactor(factor);
	}
}
