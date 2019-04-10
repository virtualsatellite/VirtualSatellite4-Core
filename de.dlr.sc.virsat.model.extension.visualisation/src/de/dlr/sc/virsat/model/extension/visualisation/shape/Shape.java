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
				&& this.positionX == other.positionX
				&& this.positionY == other.positionY
				&& this.positionZ == other.positionZ
				&& this.rotationX == other.rotationX
				&& this.rotationY == other.rotationY
				&& this.rotationZ == other.rotationZ
				&& this.color == other.color
				&& this.transparency == other.transparency
				&& (this.geometryFile == other.geometryFile || this.shape != VisualisationShape.GEOMETRY)
				&& (this.sizeX == other.sizeX || this.shape != VisualisationShape.BOX)
				&& (this.sizeY == other.sizeY || (this.shape != VisualisationShape.BOX && this.shape != VisualisationShape.CONE && this.shape != VisualisationShape.CYLINDER))
				&& (this.sizeZ == other.sizeZ || this.shape != VisualisationShape.BOX)
				&& (this.radius == other.radius || (this.shape != VisualisationShape.SPHERE && this.shape != VisualisationShape.CONE && this.shape != VisualisationShape.CYLINDER))
				);
	}
	
	@Override
	public int hashCode() {
		
		//CHECKSTYLE:OFF
		int result = 41;
		result = 31 * result + ((id == null) ? 0 : id.hashCode());
		result = 31 * result + ((shape == null) ? 0 : shape.hashCode());
		result = 31 * result + new Double(positionX).hashCode();
		result = 31 * result + new Double(positionY).hashCode();
		result = 31 * result + new Double(positionZ).hashCode();
		result = 31 * result + new Double(rotationX).hashCode();
		result = 31 * result + new Double(rotationY).hashCode();
		result = 31 * result + new Double(rotationZ).hashCode();
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
