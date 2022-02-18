/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.visualisation.shape;

import org.eclipse.emf.common.util.URI;

/**
 * Class for representing visualisation shapes
 * @author kova_an
 *
 */
public class Shape {
	
	//CHECKSTYLE:OFF
	// This is a data transfer object without any behaviour, we want all fields public
	public String id;
	public URI geometryFile;
	public float sizeX = 0;
	public float sizeY = 0;
	public float sizeZ = 0;
	public float radius = 0;
	public double positionX = 0;
	public double positionY = 0;
	public double positionZ = 0;
	public double rotationX = 0;
	public double rotationY = 0;
	public double rotationZ = 0;
	public int color = 0;
	public float transparency = 0;
	public VisualisationShape shape = VisualisationShape.NONE;
	//CHECKSTYLE:ON
	
	/**
	 * Create a shape with given parameters
	 * @param id 
	 * @param geometryFile 
	 * @param sizeX 
	 * @param sizeY 
	 * @param sizeZ 
	 * @param radius 
	 * @param positionX 
	 * @param positionY 
	 * @param positionZ 
	 * @param rotationX 
	 * @param rotationY 
	 * @param rotationZ 
	 * @param color 
	 * @param transparency 
	 * @param shape 
	 */
	public Shape(String id, URI geometryFile, float sizeX, float sizeY, float sizeZ, float radius, double positionX, 
			double positionY, double positionZ, double rotationX, double rotationY, double rotationZ, int color, float transparency,
			VisualisationShape shape) {
		this.id = id;
		this.geometryFile = geometryFile;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeZ = sizeZ;
		this.radius = radius;
		this.positionX = positionX;
		this.positionY = positionY;
		this.positionZ = positionZ;
		this.rotationX = rotationX;
		this.rotationY = rotationY;
		this.rotationZ = rotationZ;
		this.color = color;
		this.transparency = transparency;
		this.shape = shape;
	}
	
	
	/**
	 * Create shape without specifying any parameters
	 */
	public Shape() {
	}

	/**
	 * Copy constructor
	 * @param shapeToCopyFrom 
	 */
	public Shape(Shape shapeToCopyFrom) {
		this.id = shapeToCopyFrom.id;
		this.geometryFile = shapeToCopyFrom.geometryFile;
		this.sizeX = shapeToCopyFrom.sizeX;
		this.sizeY = shapeToCopyFrom.sizeY;
		this.sizeZ = shapeToCopyFrom.sizeZ;
		this.radius = shapeToCopyFrom.radius;
		this.positionX = shapeToCopyFrom.positionX;
		this.positionY = shapeToCopyFrom.positionY;
		this.positionZ = shapeToCopyFrom.positionZ;
		this.rotationX = shapeToCopyFrom.rotationX;
		this.rotationY = shapeToCopyFrom.rotationY;
		this.rotationZ = shapeToCopyFrom.rotationZ;
		this.color = shapeToCopyFrom.color;
		this.transparency = shapeToCopyFrom.transparency;
		this.shape = shapeToCopyFrom.shape;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Shape)) {
			return false;
		}
		Shape other = (Shape) obj;
		
		return (this.id.equals(other.id)
				&& this.shape == other.shape
				&& this.color == other.color
				&& equalsPosition(this, other)
				&& equalsRotation(this, other)
				&& this.transparency == other.transparency
				&& equalsBoxSizes(this, other)
				&& equalsSphereSizes(this, other)
				&& equalsConeSizes(this, other)
				&& equalsCylinderSizes(this, other)
				&& (this.geometryFile == other.geometryFile || this.shape != VisualisationShape.GEOMETRY)
				);
	}
	
	/**
	 * Use this method to check for equal positions
	 * @param left the left shape
	 * @param right the right shape
	 * @return true in case the values are equal
	 */
	private static boolean equalsPosition(Shape left, Shape right) {
		return (left.positionX == right.positionX
				&& left.positionY == right.positionY
				&& left.positionZ == right.positionZ);
	}
	
	/**
	 * Use this method to check for equal rotation
	 * @param left the left shape
	 * @param right the right shape
	 * @return true in case the values are equal
	 */
	private static boolean equalsRotation(Shape left, Shape right) {
		return (left.rotationX == right.rotationX
				&& left.rotationY == right.rotationY
				&& left.rotationZ == right.rotationZ);
	}
	
	/**
	 * Use this method to check for equal sizes in case both are a box
	 * @param left the left shape
	 * @param right the right shape
	 * @return true in case the relevant values are equal or in case unequal things are compared e.g. box vs. cyclinder
	 */
	private static boolean equalsBoxSizes(Shape left, Shape right) {
		if (left.shape == VisualisationShape.BOX && right.shape == VisualisationShape.BOX) {
			return (left.sizeX == right.sizeX 
					&& left.sizeY == right.sizeY
					&& left.sizeZ == right.sizeZ);
		}
		return true;
	}
	
	/**
	 * Use this method to check for equal sizes in case both are a cone
	 * @param left the left shape
	 * @param right the right shape
	 * @return true in case the relevant values are equal or in case unequal things are compared e.g. box vs. cyclinder
	 */
	private static boolean equalsConeSizes(Shape left, Shape right) {
		if (left.shape == VisualisationShape.CONE && right.shape == VisualisationShape.CONE) {
			return (left.sizeY == right.sizeY
					&& left.radius == right.radius);
		}
		return true;
	}
	
	/**
	 * Use this method to check for equal sizes in case both are a cylinder
	 * @param left the left shape
	 * @param right the right shape
	 * @return true in case the relevant values are equal or in case unequal things are compared e.g. box vs. cyclinder
	 */
	private static boolean equalsCylinderSizes(Shape left, Shape right) {
		if (left.shape == VisualisationShape.CYLINDER && right.shape == VisualisationShape.CYLINDER) {
			return (left.sizeY == right.sizeY
					&& left.radius == right.radius);
		}
		return true;
	}
	
	/**
	 * Use this method to check for equal sizes in case both are a Sphere
	 * @param left the left shape
	 * @param right the right shape
	 * @return true in case the relevant values are equal or in case unequal things are compared e.g. box vs. cyclinder
	 */
	private static boolean equalsSphereSizes(Shape left, Shape right) {
		if (left.shape == VisualisationShape.SPHERE && right.shape == VisualisationShape.SPHERE) {
			return (left.radius == right.radius);
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		
		//CHECKSTYLE:OFF
		int result = 41;
		result = 31 * result + ((id == null) ? 0 : id.hashCode());
		result = 31 * result + ((shape == null) ? 0 : shape.hashCode());
		result = 31 * result + Double.valueOf(positionX).hashCode();
		result = 31 * result + Double.valueOf(positionY).hashCode();
		result = 31 * result + Double.valueOf(positionZ).hashCode();
		result = 31 * result + Double.valueOf(rotationX).hashCode();
		result = 31 * result + Double.valueOf(rotationY).hashCode();
		result = 31 * result + Double.valueOf(rotationZ).hashCode();
		result = 31 * result + color;
		result = 31 * result + Float.floatToIntBits(transparency);

		if (shape == VisualisationShape.GEOMETRY) {
			result = 31 * result + ((geometryFile == null) ? 0 : geometryFile.hashCode());
		}
		if (shape == VisualisationShape.BOX) {
			result = 31 * result + Float.floatToIntBits(sizeX);
			result = 31 * result + Float.floatToIntBits(sizeZ);
		}
		if (shape == VisualisationShape.BOX || shape == VisualisationShape.CONE || shape == VisualisationShape.CYLINDER) {
			result = 31 * result + Float.floatToIntBits(sizeY);
		}
		if (shape == VisualisationShape.SPHERE || shape == VisualisationShape.CONE || shape == VisualisationShape.CYLINDER) {
			result = 31 * result + Float.floatToIntBits(radius);
		}
		//CHECKSTYLE:ON
		return result;
	}
}
