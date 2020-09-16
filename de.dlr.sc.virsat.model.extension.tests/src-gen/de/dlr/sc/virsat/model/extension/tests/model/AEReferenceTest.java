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
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.external.tests.ExternalTestType;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
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
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AEReferenceTest extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.tests.EReferenceTest";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_EREFERENCETEST = "eReferenceTest";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AEReferenceTest() {
	}
	
	public AEReferenceTest(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "EReferenceTest");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "EReferenceTest");
		setTypeInstance(categoryAssignement);
	}
	
	public AEReferenceTest(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: eReferenceTest
	// *****************************************************************
	private BeanPropertyEReference<ExternalTestType> eReferenceTest = new BeanPropertyEReference<ExternalTestType>();
	
	private void safeAccessEReferenceTest() {
		if (eReferenceTest.getTypeInstance() == null) {
			eReferenceTest.setTypeInstance((EReferencePropertyInstance) helper.getPropertyInstance("eReferenceTest"));
		}
	}
	
	public Command setEReferenceTest(EditingDomain ed, ExternalTestType value) {
		safeAccessEReferenceTest();
		return this.eReferenceTest.setValue(ed, value);
	}
	
	public void setEReferenceTest(ExternalTestType value) {
		safeAccessEReferenceTest();
		this.eReferenceTest.setValue(value);
	}
	
	public ExternalTestType getEReferenceTest() {
		safeAccessEReferenceTest();
		return eReferenceTest.getValue();
	}
	
	
	
}
