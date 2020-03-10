/*******************************************************************************
 * Copyright (c) 2008-2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.mat;


/**
 * Class for storing data for export and import to .mat
 */
public class MatHelper {
	
	private MatHelper() {
	}

	static final String UUID = "uuid";
	static final String VALUE = "value";
	static final String UNIT = "unit";
	static final String NAME = "name";
	static final String FULLNAME = "fullQualifiedInstanceName";
	static final String URI = "uri";
	static final String REF = "reference";
	static final String INPUTS = "inputs";
	static final String CHILDREN = "children";
	static final String TYPE = "type";

}
