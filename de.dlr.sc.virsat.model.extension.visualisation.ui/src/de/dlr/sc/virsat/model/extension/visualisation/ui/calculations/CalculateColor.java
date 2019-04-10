/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.calculations;

/**
 * This class provides static methodes th convert RGB color into integer value and vice versa
 * @author wulk_ja
 *
 */
public class CalculateColor {
	/**
	 * Private constructor
	 */
	private CalculateColor() { } 
	/**
	 * Calculate the RGB values from integer values
	 * 
	 * @param intColor color in integer representation
	 * @return RGB Colors (0-255)
	 */
	public static int[] calculateIntToRGB(int intColor) {
		final int COLOR_SIZE = 3;
		int[] color = new int[COLOR_SIZE];
		// CHECKSTYLE:OFF
		color[0] = (intColor % 0x1000000 / 0x10000);
		color[1] = (intColor % 0x10000 / 0x100);
		color[2] = (intColor % 0x100);
		// CHECKSTYLE:ON
		return color;
	}

	/**
	 * Calculate int value, which is representing the rgb combination
	 * @param red (0-255)
	 * @param green (0-255)
	 * @param blue (0-255)
	 * @return rgb Integer
	 */
	public static int calculateRGBToInt(int red, int green, int blue) {
		//CHECKSTYLE:OFF
		int rgb = ((red & 0x0ff) << 16) | ((green & 0x0ff) << 8) | (blue & 0x0ff);
		//CHECKSTYLE:ON
		return rgb;
	}
	/**
	 * Caluculate the RGB values from integer values
	 * @param intColor color in integer representation
	 * @return RGB Colors (0-1)
	 */
	public static double[] caluclateIntToRGBDouble(int intColor) {
		final int COLOR_SIZE = 3;
		final int MAX_COLOR_RANGE = 255;
		double[] colorDouble = new double[COLOR_SIZE];
		int[] color = calculateIntToRGB(intColor);
		colorDouble[0] = color[0] / (double) MAX_COLOR_RANGE;
		colorDouble[1] = color[1] / (double) MAX_COLOR_RANGE;
		colorDouble[2] = color[2] / (double) MAX_COLOR_RANGE;
		
		return colorDouble;
		
	}


}
