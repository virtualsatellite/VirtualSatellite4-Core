/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;
import de.dlr.sc.virsat.model.extension.visualisation.shape.VisualisationShape;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * 
 * 
 */	
public class Visualisation extends AVisualisation {
	
	/**
	 * Constructor of Concept Class
	 */
	public Visualisation() {
		super();
	}

	/**
	 * Constructor of Concept Class which will instantiate 
	 * a CategoryAssignment in the background from the given concept
	 * @param concept the concept where it will find the correct Category to instantiate from
	 */
	public Visualisation(Concept concept) {
		super(concept);
	}	

	/**
	 * Constructor of Concept Class that can be initialized manually by a given Category Assignment
	 * @param categoryAssignment The category Assignment to be used for background initialization of the Category bean
	 */
	public Visualisation(CategoryAssignment categoryAssignment) {
		super(categoryAssignment);
	}
	
	/**
	 * This method writes all Visualization parameters into a Shape
	 * @param shape the shape in which to write the values
	 * @return the current Visualisation object
	 */
	public Visualisation writeToShape(Shape shape) {
		String shapeTypeString = this.getShape();
		VisualisationShape shapeType = VisualisationShape.valueOf(shapeTypeString);
		
		shape.geometryFile = this.getGeometryFile();
		shape.sizeX = (float) this.getSizeXBean().getValueToBaseUnit(); 
		shape.sizeY = (float) this.getSizeYBean().getValueToBaseUnit();
		shape.sizeZ = (float) this.getSizeZBean().getValueToBaseUnit();
		shape.radius = (float) this.getRadiusBean().getValueToBaseUnit();
		shape.positionX = this.getPositionXBean().getValueToBaseUnit();
		shape.positionY = this.getPositionYBean().getValueToBaseUnit();
		shape.positionZ = this.getPositionZBean().getValueToBaseUnit();
		shape.rotationX = Math.toDegrees(this.getRotationXBean().getValueToBaseUnit());
		shape.rotationY = Math.toDegrees(this.getRotationYBean().getValueToBaseUnit());
		shape.rotationZ = Math.toDegrees(this.getRotationZBean().getValueToBaseUnit());
		shape.color = (int) this.getColor();
		shape.transparency = (float) this.getTransparencyBean().getValueToBaseUnit();
		shape.shape = shapeType;
		return this;
	}
	
	/**
	 * This method reads all infos from a given shape and writes them to the visualization
	 * @param shape the shape from which to read
	 * @return the current visualisation object
	 */
	public Visualisation readFromShape(Shape shape) {
		this.getSizeXBean().setValueAsBaseUnit(shape.sizeX);
		this.getSizeYBean().setValueAsBaseUnit(shape.sizeY);
		this.getSizeZBean().setValueAsBaseUnit(shape.sizeZ);
		this.getRadiusBean().setValueAsBaseUnit(shape.radius);

		this.setColor(shape.color);
		this.getTransparencyBean().setValueAsBaseUnit(shape.transparency);
		
		this.getPositionXBean().setValueAsBaseUnit(shape.positionX);
		this.getPositionYBean().setValueAsBaseUnit(shape.positionY);
		this.getPositionZBean().setValueAsBaseUnit(shape.positionZ);
		this.getRotationXBean().setValueAsBaseUnit(Math.toRadians(shape.rotationX));
		this.getRotationYBean().setValueAsBaseUnit(Math.toRadians(shape.rotationY));
		this.getRotationZBean().setValueAsBaseUnit(Math.toRadians(shape.rotationZ));
		this.setShape(shape.shape.toString());

		this.getGeometryFileBean().setValue(shape.geometryFile);
		
		return this;
	}
}
