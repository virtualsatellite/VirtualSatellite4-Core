/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.thermal.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Emissivity of specific face
 * 
 */	
public abstract class AFaceEmissivity extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.FaceEmissivity";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_FREECADFACENUMBER = "freeCADFaceNumber";
	public static final String PROPERTY_FACEEMISSIVITY = "faceEmissivity";
	public static final String PROPERTY_FACEABSORPTIVITY = "faceAbsorptivity";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AFaceEmissivity() {
	}
	
	public AFaceEmissivity(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "FaceEmissivity");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "FaceEmissivity");
		setTypeInstance(categoryAssignement);
	}
	
	public AFaceEmissivity(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: freeCADFaceNumber
	// *****************************************************************
	private BeanPropertyInt freeCADFaceNumber = new BeanPropertyInt();
	
	private void safeAccessFreeCADFaceNumber() {
		if (freeCADFaceNumber.getTypeInstance() == null) {
			freeCADFaceNumber.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("freeCADFaceNumber"));
		}
	}
	
	public Command setFreeCADFaceNumber(EditingDomain ed, long value) {
		safeAccessFreeCADFaceNumber();
		return this.freeCADFaceNumber.setValue(ed, value);
	}
	
	public void setFreeCADFaceNumber(long value) {
		safeAccessFreeCADFaceNumber();
		this.freeCADFaceNumber.setValue(value);
	}
	
	public long getFreeCADFaceNumber() {
		safeAccessFreeCADFaceNumber();
		return freeCADFaceNumber.getValue();
	}
	
	public boolean isSetFreeCADFaceNumber() {
		safeAccessFreeCADFaceNumber();
		return freeCADFaceNumber.isSet();
	}
	
	public BeanPropertyInt getFreeCADFaceNumberBean() {
		safeAccessFreeCADFaceNumber();
		return freeCADFaceNumber;
	}
	
	// *****************************************************************
	// * Attribute: faceEmissivity
	// *****************************************************************
	private BeanPropertyFloat faceEmissivity = new BeanPropertyFloat();
	
	private void safeAccessFaceEmissivity() {
		if (faceEmissivity.getTypeInstance() == null) {
			faceEmissivity.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("faceEmissivity"));
		}
	}
	
	public Command setFaceEmissivity(EditingDomain ed, double value) {
		safeAccessFaceEmissivity();
		return this.faceEmissivity.setValue(ed, value);
	}
	
	public void setFaceEmissivity(double value) {
		safeAccessFaceEmissivity();
		this.faceEmissivity.setValue(value);
	}
	
	public double getFaceEmissivity() {
		safeAccessFaceEmissivity();
		return faceEmissivity.getValue();
	}
	
	public boolean isSetFaceEmissivity() {
		safeAccessFaceEmissivity();
		return faceEmissivity.isSet();
	}
	
	public BeanPropertyFloat getFaceEmissivityBean() {
		safeAccessFaceEmissivity();
		return faceEmissivity;
	}
	
	// *****************************************************************
	// * Attribute: faceAbsorptivity
	// *****************************************************************
	private BeanPropertyFloat faceAbsorptivity = new BeanPropertyFloat();
	
	private void safeAccessFaceAbsorptivity() {
		if (faceAbsorptivity.getTypeInstance() == null) {
			faceAbsorptivity.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("faceAbsorptivity"));
		}
	}
	
	public Command setFaceAbsorptivity(EditingDomain ed, double value) {
		safeAccessFaceAbsorptivity();
		return this.faceAbsorptivity.setValue(ed, value);
	}
	
	public void setFaceAbsorptivity(double value) {
		safeAccessFaceAbsorptivity();
		this.faceAbsorptivity.setValue(value);
	}
	
	public double getFaceAbsorptivity() {
		safeAccessFaceAbsorptivity();
		return faceAbsorptivity.getValue();
	}
	
	public boolean isSetFaceAbsorptivity() {
		safeAccessFaceAbsorptivity();
		return faceAbsorptivity.isSet();
	}
	
	public BeanPropertyFloat getFaceAbsorptivityBean() {
		safeAccessFaceAbsorptivity();
		return faceAbsorptivity;
	}
	
	
}
