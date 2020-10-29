/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model;

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
public abstract class ATestCrossLinkedParametersWithCalculation extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.tests.TestCrossLinkedParametersWithCalculation";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_CALCEDTRL = "calcedTrl";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCrossLinkedParametersWithCalculation() {
	}
	
	public ATestCrossLinkedParametersWithCalculation(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestCrossLinkedParametersWithCalculation");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestCrossLinkedParametersWithCalculation");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCrossLinkedParametersWithCalculation(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: calcedTrl
	// *****************************************************************
	private BeanPropertyFloat calcedTrl = new BeanPropertyFloat();
	
	private void safeAccessCalcedTrl() {
		if (calcedTrl.getTypeInstance() == null) {
			calcedTrl.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("calcedTrl"));
		}
	}
	
	public Command setCalcedTrl(EditingDomain ed, double value) {
		safeAccessCalcedTrl();
		return this.calcedTrl.setValue(ed, value);
	}
	
	public void setCalcedTrl(double value) {
		safeAccessCalcedTrl();
		this.calcedTrl.setValue(value);
	}
	
	public double getCalcedTrl() {
		safeAccessCalcedTrl();
		return calcedTrl.getValue();
	}
	
	public boolean isSetCalcedTrl() {
		safeAccessCalcedTrl();
		return calcedTrl.isSet();
	}
	
	public BeanPropertyFloat getCalcedTrlBean() {
		safeAccessCalcedTrl();
		return calcedTrl;
	}
	
	
}
