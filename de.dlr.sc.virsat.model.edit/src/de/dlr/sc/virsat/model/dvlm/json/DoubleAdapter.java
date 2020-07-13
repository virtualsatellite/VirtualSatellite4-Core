/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.json;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * This adapter is a workaround, because NaN throws an error when being unmarshalled
 */
public class DoubleAdapter extends XmlAdapter<Double, Double> {

	@Override
	public Double unmarshal(Double v) throws Exception {
		return v;
	}

	@Override
	public Double marshal(Double v) throws Exception {
		if (v.isNaN()) {
			return null;
		} else {
			return v;
		}
	}

}
