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
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.emf.common.util.URI;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import javax.xml.bind.annotation.XmlElement;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;


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
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AVisualisation extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.visualisation.Visualisation";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_SHAPE = "shape";
	public static final String PROPERTY_GEOMETRYFILE = "geometryFile";
	public static final String PROPERTY_RADIUS = "radius";
	public static final String PROPERTY_SIZEX = "sizeX";
	public static final String PROPERTY_SIZEY = "sizeY";
	public static final String PROPERTY_SIZEZ = "sizeZ";
	public static final String PROPERTY_COLOR = "color";
	public static final String PROPERTY_TRANSPARENCY = "transparency";
	public static final String PROPERTY_POSITIONX = "positionX";
	public static final String PROPERTY_POSITIONY = "positionY";
	public static final String PROPERTY_POSITIONZ = "positionZ";
	public static final String PROPERTY_ROTATIONX = "rotationX";
	public static final String PROPERTY_ROTATIONY = "rotationY";
	public static final String PROPERTY_ROTATIONZ = "rotationZ";
	
	// Shape enumeration value names
	public static final String SHAPE_NONE_NAME = "NONE";
	public static final String SHAPE_BOX_NAME = "BOX";
	public static final String SHAPE_SPHERE_NAME = "SPHERE";
	public static final String SHAPE_CYLINDER_NAME = "CYLINDER";
	public static final String SHAPE_CONE_NAME = "CONE";
	public static final String SHAPE_GEOMETRY_NAME = "GEOMETRY";
	// Shape enumeration values
	public static final String SHAPE_NONE_VALUE = "1";
	public static final String SHAPE_BOX_VALUE = "2";
	public static final String SHAPE_SPHERE_VALUE = "3";
	public static final String SHAPE_CYLINDER_VALUE = "4";
	public static final String SHAPE_CONE_VALUE = "5";
	public static final String SHAPE_GEOMETRY_VALUE = "6";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AVisualisation() {
	}
	
	public AVisualisation(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "Visualisation");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "Visualisation");
		setTypeInstance(categoryAssignement);
	}
	
	public AVisualisation(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: shape
	// *****************************************************************
	private BeanPropertyEnum shape = new BeanPropertyEnum();
	
	private void safeAccessShape() {
		if (shape.getTypeInstance() == null) {
			shape.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("shape"));
		}
	}
	
	public Command setShape(EditingDomain ed, String value) {
		safeAccessShape();
		return this.shape.setValue(ed, value);
	}
	
	public void setShape(String value) {
		safeAccessShape();
		this.shape.setValue(value);
	}
	
	public String getShape() {
		safeAccessShape();
		return shape.getValue();
	}
	
	public double getShapeEnum() {
		safeAccessShape();
		return shape.getEnumValue();
	}
	
	@XmlElement
	public BeanPropertyEnum getShapeBean() {
		safeAccessShape();
		return shape;
	}
	
	// *****************************************************************
	// * Attribute: geometryFile
	// *****************************************************************
	private BeanPropertyResource geometryFile = new BeanPropertyResource();
	
	private void safeAccessGeometryFile() {
		if (geometryFile.getTypeInstance() == null) {
			geometryFile.setTypeInstance((ResourcePropertyInstance) helper.getPropertyInstance("geometryFile"));
		}
	}
	
	public Command setGeometryFile(EditingDomain ed, URI value) {
		safeAccessGeometryFile();
		return this.geometryFile.setValue(ed, value);
	}
	
	public void setGeometryFile(URI value) {
		safeAccessGeometryFile();
		this.geometryFile.setValue(value);
	}
	
	public URI getGeometryFile() {
		safeAccessGeometryFile();
		return geometryFile.getValue();
	}
	
	@XmlElement
	public BeanPropertyResource getGeometryFileBean() {
		safeAccessGeometryFile();
		return geometryFile;
	}
	
	// *****************************************************************
	// * Attribute: radius
	// *****************************************************************
	private BeanPropertyFloat radius = new BeanPropertyFloat();
	
	private void safeAccessRadius() {
		if (radius.getTypeInstance() == null) {
			radius.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("radius"));
		}
	}
	
	public Command setRadius(EditingDomain ed, double value) {
		safeAccessRadius();
		return this.radius.setValue(ed, value);
	}
	
	public void setRadius(double value) {
		safeAccessRadius();
		this.radius.setValue(value);
	}
	
	public double getRadius() {
		safeAccessRadius();
		return radius.getValue();
	}
	
	public boolean isSetRadius() {
		safeAccessRadius();
		return radius.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getRadiusBean() {
		safeAccessRadius();
		return radius;
	}
	
	// *****************************************************************
	// * Attribute: sizeX
	// *****************************************************************
	private BeanPropertyFloat sizeX = new BeanPropertyFloat();
	
	private void safeAccessSizeX() {
		if (sizeX.getTypeInstance() == null) {
			sizeX.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("sizeX"));
		}
	}
	
	public Command setSizeX(EditingDomain ed, double value) {
		safeAccessSizeX();
		return this.sizeX.setValue(ed, value);
	}
	
	public void setSizeX(double value) {
		safeAccessSizeX();
		this.sizeX.setValue(value);
	}
	
	public double getSizeX() {
		safeAccessSizeX();
		return sizeX.getValue();
	}
	
	public boolean isSetSizeX() {
		safeAccessSizeX();
		return sizeX.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getSizeXBean() {
		safeAccessSizeX();
		return sizeX;
	}
	
	// *****************************************************************
	// * Attribute: sizeY
	// *****************************************************************
	private BeanPropertyFloat sizeY = new BeanPropertyFloat();
	
	private void safeAccessSizeY() {
		if (sizeY.getTypeInstance() == null) {
			sizeY.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("sizeY"));
		}
	}
	
	public Command setSizeY(EditingDomain ed, double value) {
		safeAccessSizeY();
		return this.sizeY.setValue(ed, value);
	}
	
	public void setSizeY(double value) {
		safeAccessSizeY();
		this.sizeY.setValue(value);
	}
	
	public double getSizeY() {
		safeAccessSizeY();
		return sizeY.getValue();
	}
	
	public boolean isSetSizeY() {
		safeAccessSizeY();
		return sizeY.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getSizeYBean() {
		safeAccessSizeY();
		return sizeY;
	}
	
	// *****************************************************************
	// * Attribute: sizeZ
	// *****************************************************************
	private BeanPropertyFloat sizeZ = new BeanPropertyFloat();
	
	private void safeAccessSizeZ() {
		if (sizeZ.getTypeInstance() == null) {
			sizeZ.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("sizeZ"));
		}
	}
	
	public Command setSizeZ(EditingDomain ed, double value) {
		safeAccessSizeZ();
		return this.sizeZ.setValue(ed, value);
	}
	
	public void setSizeZ(double value) {
		safeAccessSizeZ();
		this.sizeZ.setValue(value);
	}
	
	public double getSizeZ() {
		safeAccessSizeZ();
		return sizeZ.getValue();
	}
	
	public boolean isSetSizeZ() {
		safeAccessSizeZ();
		return sizeZ.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getSizeZBean() {
		safeAccessSizeZ();
		return sizeZ;
	}
	
	// *****************************************************************
	// * Attribute: color
	// *****************************************************************
	private BeanPropertyInt color = new BeanPropertyInt();
	
	private void safeAccessColor() {
		if (color.getTypeInstance() == null) {
			color.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("color"));
		}
	}
	
	public Command setColor(EditingDomain ed, long value) {
		safeAccessColor();
		return this.color.setValue(ed, value);
	}
	
	public void setColor(long value) {
		safeAccessColor();
		this.color.setValue(value);
	}
	
	public long getColor() {
		safeAccessColor();
		return color.getValue();
	}
	
	public boolean isSetColor() {
		safeAccessColor();
		return color.isSet();
	}
	
	@XmlElement
	public BeanPropertyInt getColorBean() {
		safeAccessColor();
		return color;
	}
	
	// *****************************************************************
	// * Attribute: transparency
	// *****************************************************************
	private BeanPropertyFloat transparency = new BeanPropertyFloat();
	
	private void safeAccessTransparency() {
		if (transparency.getTypeInstance() == null) {
			transparency.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("transparency"));
		}
	}
	
	public Command setTransparency(EditingDomain ed, double value) {
		safeAccessTransparency();
		return this.transparency.setValue(ed, value);
	}
	
	public void setTransparency(double value) {
		safeAccessTransparency();
		this.transparency.setValue(value);
	}
	
	public double getTransparency() {
		safeAccessTransparency();
		return transparency.getValue();
	}
	
	public boolean isSetTransparency() {
		safeAccessTransparency();
		return transparency.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getTransparencyBean() {
		safeAccessTransparency();
		return transparency;
	}
	
	// *****************************************************************
	// * Attribute: positionX
	// *****************************************************************
	private BeanPropertyFloat positionX = new BeanPropertyFloat();
	
	private void safeAccessPositionX() {
		if (positionX.getTypeInstance() == null) {
			positionX.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("positionX"));
		}
	}
	
	public Command setPositionX(EditingDomain ed, double value) {
		safeAccessPositionX();
		return this.positionX.setValue(ed, value);
	}
	
	public void setPositionX(double value) {
		safeAccessPositionX();
		this.positionX.setValue(value);
	}
	
	public double getPositionX() {
		safeAccessPositionX();
		return positionX.getValue();
	}
	
	public boolean isSetPositionX() {
		safeAccessPositionX();
		return positionX.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getPositionXBean() {
		safeAccessPositionX();
		return positionX;
	}
	
	// *****************************************************************
	// * Attribute: positionY
	// *****************************************************************
	private BeanPropertyFloat positionY = new BeanPropertyFloat();
	
	private void safeAccessPositionY() {
		if (positionY.getTypeInstance() == null) {
			positionY.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("positionY"));
		}
	}
	
	public Command setPositionY(EditingDomain ed, double value) {
		safeAccessPositionY();
		return this.positionY.setValue(ed, value);
	}
	
	public void setPositionY(double value) {
		safeAccessPositionY();
		this.positionY.setValue(value);
	}
	
	public double getPositionY() {
		safeAccessPositionY();
		return positionY.getValue();
	}
	
	public boolean isSetPositionY() {
		safeAccessPositionY();
		return positionY.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getPositionYBean() {
		safeAccessPositionY();
		return positionY;
	}
	
	// *****************************************************************
	// * Attribute: positionZ
	// *****************************************************************
	private BeanPropertyFloat positionZ = new BeanPropertyFloat();
	
	private void safeAccessPositionZ() {
		if (positionZ.getTypeInstance() == null) {
			positionZ.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("positionZ"));
		}
	}
	
	public Command setPositionZ(EditingDomain ed, double value) {
		safeAccessPositionZ();
		return this.positionZ.setValue(ed, value);
	}
	
	public void setPositionZ(double value) {
		safeAccessPositionZ();
		this.positionZ.setValue(value);
	}
	
	public double getPositionZ() {
		safeAccessPositionZ();
		return positionZ.getValue();
	}
	
	public boolean isSetPositionZ() {
		safeAccessPositionZ();
		return positionZ.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getPositionZBean() {
		safeAccessPositionZ();
		return positionZ;
	}
	
	// *****************************************************************
	// * Attribute: rotationX
	// *****************************************************************
	private BeanPropertyFloat rotationX = new BeanPropertyFloat();
	
	private void safeAccessRotationX() {
		if (rotationX.getTypeInstance() == null) {
			rotationX.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("rotationX"));
		}
	}
	
	public Command setRotationX(EditingDomain ed, double value) {
		safeAccessRotationX();
		return this.rotationX.setValue(ed, value);
	}
	
	public void setRotationX(double value) {
		safeAccessRotationX();
		this.rotationX.setValue(value);
	}
	
	public double getRotationX() {
		safeAccessRotationX();
		return rotationX.getValue();
	}
	
	public boolean isSetRotationX() {
		safeAccessRotationX();
		return rotationX.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getRotationXBean() {
		safeAccessRotationX();
		return rotationX;
	}
	
	// *****************************************************************
	// * Attribute: rotationY
	// *****************************************************************
	private BeanPropertyFloat rotationY = new BeanPropertyFloat();
	
	private void safeAccessRotationY() {
		if (rotationY.getTypeInstance() == null) {
			rotationY.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("rotationY"));
		}
	}
	
	public Command setRotationY(EditingDomain ed, double value) {
		safeAccessRotationY();
		return this.rotationY.setValue(ed, value);
	}
	
	public void setRotationY(double value) {
		safeAccessRotationY();
		this.rotationY.setValue(value);
	}
	
	public double getRotationY() {
		safeAccessRotationY();
		return rotationY.getValue();
	}
	
	public boolean isSetRotationY() {
		safeAccessRotationY();
		return rotationY.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getRotationYBean() {
		safeAccessRotationY();
		return rotationY;
	}
	
	// *****************************************************************
	// * Attribute: rotationZ
	// *****************************************************************
	private BeanPropertyFloat rotationZ = new BeanPropertyFloat();
	
	private void safeAccessRotationZ() {
		if (rotationZ.getTypeInstance() == null) {
			rotationZ.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("rotationZ"));
		}
	}
	
	public Command setRotationZ(EditingDomain ed, double value) {
		safeAccessRotationZ();
		return this.rotationZ.setValue(ed, value);
	}
	
	public void setRotationZ(double value) {
		safeAccessRotationZ();
		this.rotationZ.setValue(value);
	}
	
	public double getRotationZ() {
		safeAccessRotationZ();
		return rotationZ.getValue();
	}
	
	public boolean isSetRotationZ() {
		safeAccessRotationZ();
		return rotationZ.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getRotationZBean() {
		safeAccessRotationZ();
		return rotationZ;
	}
	
	
}
