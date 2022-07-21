/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.comparison;


/**
 * The ColorMap class calculate the color of accordingly mass and power  
 *
 */
public class ColorMap {
	
	public static final int MAX_COLOR = 255;
	public static final double SEG_0 = 0;
	public static final double SEG_1 = 0.25;
	public static final double SEG_2 = 0.5;
	public static final double SEG_3 = 0.75;
	public static final double SEG_4 = 1;
	public static final double SEG_NUM = 4;
	public static final double VALUE_MIN_GAP = 0.0001;
	public static final int OFFSETRED = 16;
	public static final int OFFSETGREEN = 8;	
	
	
	//min 0     blue
	//0.25      cyan
	//0.5       green
	//0.75      yellows
	//max 1     red
	
	private double min = 0;
	private double max = 0;
	
	
	/**
	 * The constructor
	 * @param min min
	 * @param max max
	 */
	public ColorMap(double min, double max) {
		this.min = min;
		this.max = max;
	};

	/**
	 * Transfer double value to Color value, in integer style	  
	 * @param value massOrPowerValue
	 * @return color
	 */
	public int valueToColor(double value) {
		
		int color = 0;
		
		int r = 0;
		int g = 0;
		int b = 0;
		
		if (max - min <= VALUE_MIN_GAP) {
			r = MAX_COLOR;
			g = 0;
			b = 0;
		} else {
		    double part = (value - min) / (max - min);
		    if (part < SEG_0) {
				r = 0;
				g = 0;
		        b = 0;
			} else if (part >= SEG_0 && part <= SEG_1) {
				r = 0;
				g = (int) (SEG_NUM * part * MAX_COLOR);
		        b = MAX_COLOR;
			} else if (part > SEG_1 && part <= SEG_2) {
				r = 0;
        		g = MAX_COLOR;
                b = (int) ((1 - SEG_NUM * (part - SEG_1)) * MAX_COLOR);
			} else if (part > SEG_2 && part <= SEG_3) {
				r = (int) (SEG_NUM * (part - SEG_2) * MAX_COLOR);
				g = MAX_COLOR;
				b = 0;
			} else if (part > SEG_3 && part <= SEG_4) {
				r = MAX_COLOR;
				g = (int) ((1 - SEG_NUM * (part - SEG_3)) * MAX_COLOR);
				b = 0;
			} else if (part > SEG_4) {
				r = MAX_COLOR;
				g = MAX_COLOR;
				b = MAX_COLOR;
			}
		}
		
		color = (r << OFFSETRED) + (g << OFFSETGREEN) + b;		
		return color; 
	}
	
	/**
	 * Returns the min value
	 * @return min 
	 */
	public double getMin() {
		return min;
	}
	/**
	 * Returns the max value
	 * @return max 
	 */
	public double getMax() {
		return max;
	}
	/**
	 * Set the min value
	 * @param min the minimum value for the color map
	 */
	public void setMin(double min) {
		this.min = min;
	}
	/**
	 * Set the max value
	 * @param max the maximum value for the color map
	 */
	public void setMax(double max) {
		this.max = max;
	}
}

