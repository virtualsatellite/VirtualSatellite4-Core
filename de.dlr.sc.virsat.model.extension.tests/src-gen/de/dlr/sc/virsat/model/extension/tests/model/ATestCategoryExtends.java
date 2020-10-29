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
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
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
public abstract class ATestCategoryExtends extends TestCategoryBase implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.tests.TestCategoryExtends";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TESTEXTENDSPROPERTY = "testExtendsProperty";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCategoryExtends() {
	}
	
	public ATestCategoryExtends(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestCategoryExtends");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestCategoryExtends");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCategoryExtends(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: testExtendsProperty
	// *****************************************************************
	private BeanPropertyInt testExtendsProperty = new BeanPropertyInt();
	
	private void safeAccessTestExtendsProperty() {
		if (testExtendsProperty.getTypeInstance() == null) {
			testExtendsProperty.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("testExtendsProperty"));
		}
	}
	
	public Command setTestExtendsProperty(EditingDomain ed, long value) {
		safeAccessTestExtendsProperty();
		return this.testExtendsProperty.setValue(ed, value);
	}
	
	public void setTestExtendsProperty(long value) {
		safeAccessTestExtendsProperty();
		this.testExtendsProperty.setValue(value);
	}
	
	public long getTestExtendsProperty() {
		safeAccessTestExtendsProperty();
		return testExtendsProperty.getValue();
	}
	
	public boolean isSetTestExtendsProperty() {
		safeAccessTestExtendsProperty();
		return testExtendsProperty.isSet();
	}
	
	public BeanPropertyInt getTestExtendsPropertyBean() {
		safeAccessTestExtendsProperty();
		return testExtendsProperty;
	}
	
	
}
