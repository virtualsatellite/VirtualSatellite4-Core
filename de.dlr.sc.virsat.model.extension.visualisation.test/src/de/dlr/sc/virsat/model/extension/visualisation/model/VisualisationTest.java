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


import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;
import de.dlr.sc.virsat.model.extension.visualisation.shape.VisualisationShape;

// *****************************************************************
// * Import Statements
// *****************************************************************



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
public class VisualisationTest extends AVisualisationTest {

	private Visualisation vis;
	private Shape shape;
	private static final double EPSILON = 0.0001;

	@Before
	public void setupTest() {
		shape = new Shape();
		vis = new Visualisation(concept);
	}
	
	/**
	 * This method sets some basic values for the shape object
	 */
	private void setupShape() {
		//CHECKSTYLE:OFF
		shape.geometryFile = URI.createFileURI("test.stl");
		shape.sizeX = 10;
		shape.sizeY = 20;
		shape.sizeZ = 30;
		shape.radius = 10;
		shape.positionX = 50;
		shape.positionY = 60;
		shape.positionZ = 70;
		shape.rotationX = 10;
		shape.rotationY = 20;
		shape.rotationZ = 30;
		shape.color = 0x0000FF;
		shape.transparency = 0.5f;
		shape.shape = VisualisationShape.BOX;
		//CHECKSTYLE:ON		
	}
	
	/**
	 * This method prepares some values for the Visualisation Category Assignment
	 */
	private void setupVisualisation() {
		//CHECKSTYLE:OFF
		vis.setGeometryFile(URI.createFileURI("test.stl"));
		vis.setSizeX(10);
		vis.setSizeY(20);
		vis.setSizeZ(30);
		vis.setRadius(10);
		vis.setPositionX(50);
		vis.setPositionY(60);
		vis.setPositionZ(70);
		vis.setRotationX(Math.toRadians(10));
		vis.setRotationY(Math.toRadians(20));
		vis.setRotationZ(Math.toRadians(30));
		vis.setColor(0x0000FF);
		vis.setTransparency(0.5);
		vis.setShape(VisualisationShape.BOX.toString());
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testWriteToShape() {
		// Prepare the test
		setupVisualisation();

		// Execute the test
		vis.writeToShape(shape);

		// Check for the results
		// CHECKSTYLE:OFF
		assertEquals("Value is correct", 10, shape.sizeX, EPSILON);
		assertEquals("Value is correct", 20, shape.sizeY, EPSILON);
		assertEquals("Value is correct", 30, shape.sizeZ, EPSILON);

		assertEquals("Value is correct", 10, shape.radius, EPSILON);

		assertEquals("Value is correct", 50, shape.positionX, EPSILON);
		assertEquals("Value is correct", 60, shape.positionY, EPSILON);
		assertEquals("Value is correct", 70, shape.positionZ, EPSILON);

		assertEquals("Value is correct", 10, shape.rotationX, EPSILON);
		assertEquals("Value is correct", 20, shape.rotationY, EPSILON);
		assertEquals("Value is correct", 30, shape.rotationZ, EPSILON);

		assertEquals("Value is correct", 0.5f, shape.transparency, EPSILON);
		assertEquals("Value is correct", 0x0000FF, shape.color);
		assertEquals("Value is correct", VisualisationShape.BOX.toString(), shape.shape.toString());
		// CHECKSTYLE:ON

		// now setting some units and test again to see if the base unit conversion
		// works as expected
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU",
				"the system of Units", "http://the.system.of.units.de");

		AUnit unitKm = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		AUnit unitDegree = QudvUnitHelper.getInstance().getUnitByName(sou, "Degree");
		AUnit unitPercent = QudvUnitHelper.getInstance().getUnitByName(sou, "Percent");

		vis.getSizeXBean().getTypeInstance().setUnit(unitKm);
		vis.getSizeYBean().getTypeInstance().setUnit(unitKm);
		vis.getSizeZBean().getTypeInstance().setUnit(unitKm);

		vis.getRadiusBean().getTypeInstance().setUnit(unitKm);

		vis.getPositionXBean().getTypeInstance().setUnit(unitKm);
		vis.getPositionYBean().getTypeInstance().setUnit(unitKm);
		vis.getPositionZBean().getTypeInstance().setUnit(unitKm);

		vis.getRotationXBean().getTypeInstance().setUnit(unitDegree);
		vis.getRotationYBean().getTypeInstance().setUnit(unitDegree);
		vis.getRotationZBean().getTypeInstance().setUnit(unitDegree);
		
		// CHECKSTYLE:OFF
		vis.setRotationX(30);
		vis.setRotationY(35);
		vis.setRotationZ(40);
		// CHECKSTYLE:ON

		vis.getTransparencyBean().getTypeInstance().setUnit(unitPercent);

		vis.writeToShape(shape);

		// CHECKSTYLE:OFF
		assertEquals("Value is correct", 10000, shape.sizeX, EPSILON);
		assertEquals("Value is correct", 20000, shape.sizeY, EPSILON);
		assertEquals("Value is correct", 30000, shape.sizeZ, EPSILON);

		assertEquals("Value is correct", 10000, shape.radius, EPSILON);

		assertEquals("Value is correct", 50000, shape.positionX, EPSILON);
		assertEquals("Value is correct", 60000, shape.positionY, EPSILON);
		assertEquals("Value is correct", 70000, shape.positionZ, EPSILON);

		assertEquals("Value is correct", 30, shape.rotationX, EPSILON);
		assertEquals("Value is correct", 35, shape.rotationY, EPSILON);
		assertEquals("Value is correct", 40, shape.rotationZ, EPSILON);

		assertEquals("Value is correct", 0.005f, shape.transparency, EPSILON);
		// CHECKSTYLE:ON
	}
	
	@Test
	public void testReadFromShape() {
		// Prepare the test
		setupShape();
		
		// Execute the test
		vis.readFromShape(shape);
		
		// Check for the results
		//CHECKSTYLE:OFF
		assertEquals("Value is correct", 10, vis.getSizeX(), EPSILON);
		assertEquals("Value is correct", 20, vis.getSizeY(), EPSILON);
		assertEquals("Value is correct", 30, vis.getSizeZ(), EPSILON);
		
		assertEquals("Value is correct", 10, vis.getRadius(), EPSILON);
		
		assertEquals("Value is correct", 50, vis.getPositionX(), EPSILON);
		assertEquals("Value is correct", 60, vis.getPositionY(), EPSILON);
		assertEquals("Value is correct", 70, vis.getPositionZ(), EPSILON);
		
		assertEquals("Value is correct", Math.toRadians(10), vis.getRotationX(), EPSILON);
		assertEquals("Value is correct", Math.toRadians(20), vis.getRotationY(), EPSILON);
		assertEquals("Value is correct", Math.toRadians(30), vis.getRotationZ(), EPSILON);
		
		assertEquals("Value is correct", 0.5f, vis.getTransparency(), EPSILON);
		assertEquals("Value is correct", 0x0000FF, vis.getColor() );
		assertEquals("Value is correct", VisualisationShape.BOX.toString(), vis.getShape());
		//CHECKSTYLE:ON
		
		// now setting some units and test again to see if the base unit conversion works as expected
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		AUnit unitKm = QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		AUnit unitDegree = QudvUnitHelper.getInstance().getUnitByName(sou, "Degree");
		AUnit unitPercent = QudvUnitHelper.getInstance().getUnitByName(sou, "Percent");

		vis.getSizeXBean().getTypeInstance().setUnit(unitKm);
		vis.getSizeYBean().getTypeInstance().setUnit(unitKm);
		vis.getSizeZBean().getTypeInstance().setUnit(unitKm);
		
		vis.getRadiusBean().getTypeInstance().setUnit(unitKm);
		
		vis.getPositionXBean().getTypeInstance().setUnit(unitKm);
		vis.getPositionYBean().getTypeInstance().setUnit(unitKm);
		vis.getPositionZBean().getTypeInstance().setUnit(unitKm);
		
		vis.getRotationXBean().getTypeInstance().setUnit(unitDegree);
		vis.getRotationYBean().getTypeInstance().setUnit(unitDegree);
		vis.getRotationZBean().getTypeInstance().setUnit(unitDegree);

		vis.getTransparencyBean().getTypeInstance().setUnit(unitPercent);
		
		vis.readFromShape(shape);
		
		//CHECKSTYLE:OFF
		assertEquals("Value is correct", 0.010, vis.getSizeX(), EPSILON);
		assertEquals("Value is correct", 0.020, vis.getSizeY(), EPSILON);
		assertEquals("Value is correct", 0.030, vis.getSizeZ(), EPSILON);
		
		assertEquals("Value is correct", 0.010, vis.getRadius(), EPSILON);
		
		assertEquals("Value is correct", 0.050, vis.getPositionX(), EPSILON);
		assertEquals("Value is correct", 0.060, vis.getPositionY(), EPSILON);
		assertEquals("Value is correct", 0.070, vis.getPositionZ(), EPSILON);
		
		assertEquals("Value is correct", 10, vis.getRotationX(), EPSILON);
		assertEquals("Value is correct", 20, vis.getRotationY(), EPSILON);
		assertEquals("Value is correct", 30, vis.getRotationZ(), EPSILON);
		
		assertEquals("Value is correct", 50.0 , vis.getTransparency(), EPSILON);
		//CHECKSTYLE:ON
	}
}
