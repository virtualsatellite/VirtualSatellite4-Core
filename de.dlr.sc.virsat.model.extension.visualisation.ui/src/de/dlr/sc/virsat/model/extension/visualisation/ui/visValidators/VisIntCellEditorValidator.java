/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.visValidators;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.graphics.RGB;

/**
 * Own implementaion of an interger validator. This is necessary because the integer property is also used by the color parameter, which has a different cell editor (color picker)
 * @author wulk_ja
 *
 */
public class VisIntCellEditorValidator implements ICellEditorValidator {

	private static final String VALID_INPUT = null;
	/**
	 * Check if the object can be parsed
	 * @param value value, which should be parsed
	 */
	public void parseObject(Object value) {
		if (!(value instanceof RGB)) {
			Integer.parseInt((String) value);
		}
		
	}
	
	
	@Override
	public String isValid(Object value) {
		try {
			parseObject(value);
			return VALID_INPUT;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
