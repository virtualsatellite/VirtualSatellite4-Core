/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package testConcept.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.external.tests.ExternalTestType;
import de.dlr.sc.virsat.model.concept.list.TypeSafeEReferenceArrayInstanceList;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;


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
public abstract class ATestCategory extends ABeanCategoryAssignment implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "testConcept.TestCategory";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TESTEREFERENCE = "testEReference";
	public static final String PROPERTY_TESTEREFERENCEARRAY = "testEReferenceArray";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCategory() {
	}
	
	public ATestCategory(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "TestCategory");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "TestCategory");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCategory(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: testEReference
	// *****************************************************************
	private BeanPropertyEReference<ExternalTestType> testEReference = new BeanPropertyEReference<ExternalTestType>();
	
	private void safeAccessTestEReference() {
		if (testEReference.getTypeInstance() == null) {
			testEReference.setTypeInstance((EReferencePropertyInstance) helper.getPropertyInstance("testEReference"));
		}
	}
	
	public Command setTestEReference(EditingDomain ed, ExternalTestType value) {
		safeAccessTestEReference();
		return this.testEReference.setValue(ed, value);
	}
	
	public void setTestEReference(ExternalTestType value) {
		safeAccessTestEReference();
		this.testEReference.setValue(value);
	}
	
	public ExternalTestType getTestEReference() {
		safeAccessTestEReference();
		return testEReference.getValue();
	}
	
	
	// *****************************************************************
	// * Array Attribute: testEReferenceArray
	// *****************************************************************
	private IBeanList<BeanPropertyEReference<ExternalTestType>> testEReferenceArrayBean = new TypeSafeEReferenceArrayInstanceList<ExternalTestType>();
	
	private void safeAccessTestEReferenceArrayBean() {
		if (testEReferenceArrayBean.getArrayInstance() == null) {
			testEReferenceArrayBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("testEReferenceArray"));
		}
	}
	
	public IBeanList<BeanPropertyEReference<ExternalTestType>> getTestEReferenceArrayBean() {
		safeAccessTestEReferenceArrayBean();
		return testEReferenceArrayBean;
	}
	
	
}
