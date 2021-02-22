/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.label;

import org.apache.commons.lang.WordUtils;

/**
 * Formating a multi line label
 *
 */
public class MultilineLabelFormatter {
	
	public static final int WRAP_COLUMN_NUMBER = 1;
	
	
	/**
	 * Formating a multi line label so that it wraps if following the camel case convention
	 * @param originalLabel the original label
	 * @return the formatted label
	 */
	public String getLabel(String originalLabel) {
		StringBuilder builder = new StringBuilder();
		originalLabel = originalLabel.replace('_', ' ');
		char[] charArray = originalLabel.toCharArray();
		builder.append(charArray[0]);
        for (int i = 1; i < charArray.length; i++) {
        	if (Character.isUpperCase(charArray[i]) && !Character.isUpperCase(charArray[i - 1])) {
            	builder.append(' ');
            }
            builder.append(charArray[i]);
        }
        return WordUtils.wrap(builder.toString(), WRAP_COLUMN_NUMBER);
	}

}
