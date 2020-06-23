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
import de.dlr.sc.virsat.model.concept.list.TypeSafeArrayInstanceList;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
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
public abstract class ATestCategory extends ABeanCategoryAssignment implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "testConcept.testCategory";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TPSRINGARRAYDYNAMIC = "tpSringArrayDynamic";
	public static final String PROPERTY_TPSRINGARRAYSTATIC = "tpSringArrayStatic";
	public static final String PROPERTY_TPINTARRAYDYNAMIC = "tpIntArrayDynamic";
	public static final String PROPERTY_TPINTARRAYSTATIC = "tpIntArrayStatic";
	public static final String PROPERTY_TPFLOATARRAYDYNAMIC = "tpFloatArrayDynamic";
	public static final String PROPERTY_TPFLOATARRAYSTATIC = "tpFloatArrayStatic";
	public static final String PROPERTY_TPBOOLEANARRAYDYNAMIC = "tpBooleanArrayDynamic";
	public static final String PROPERTY_TPBOOLEANARRAYSTATIC = "tpBooleanArrayStatic";
	public static final String PROPERTY_TPRESOURCEDYNAMICH = "tpResourceDynamich";
	public static final String PROPERTY_TPRESOURCESTATIC = "tpResourceStatic";
	public static final String PROPERTY_TPENUMDYNAMICH = "tpEnumDynamich";
	public static final String PROPERTY_TPENUMSTATIC = "tpEnumStatic";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ATestCategory() {
	}
	
	public ATestCategory(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "testCategory");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "testCategory");
		setTypeInstance(categoryAssignement);
	}
	
	public ATestCategory(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: tpSringArrayDynamic
	// *****************************************************************
	private IBeanList<BeanPropertyString> tpSringArrayDynamic = new TypeSafeArrayInstanceList<>(BeanPropertyString.class);
	
	private void safeAccessTpSringArrayDynamic() {
		tpSringArrayDynamic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpSringArrayDynamic"));
	}
	
	public IBeanList<BeanPropertyString> getTpSringArrayDynamic() {
		safeAccessTpSringArrayDynamic();
		return tpSringArrayDynamic;
	}
	
	// *****************************************************************
	// * Array Attribute: tpSringArrayStatic
	// *****************************************************************
	private IBeanList<BeanPropertyString> tpSringArrayStatic = new TypeSafeArrayInstanceList<>(BeanPropertyString.class);
	
	private void safeAccessTpSringArrayStatic() {
		tpSringArrayStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpSringArrayStatic"));
	}
	
	public IBeanList<BeanPropertyString> getTpSringArrayStatic() {
		safeAccessTpSringArrayStatic();
		return tpSringArrayStatic;
	}
	
	// *****************************************************************
	// * Array Attribute: tpIntArrayDynamic
	// *****************************************************************
	private IBeanList<BeanPropertyInt> tpIntArrayDynamic = new TypeSafeArrayInstanceList<>(BeanPropertyInt.class);
	
	private void safeAccessTpIntArrayDynamic() {
		tpIntArrayDynamic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpIntArrayDynamic"));
	}
		
	public IBeanList<BeanPropertyInt> getTpIntArrayDynamic() {
		safeAccessTpIntArrayDynamic();
		return tpIntArrayDynamic;
	}
	
	// *****************************************************************
	// * Array Attribute: tpIntArrayStatic
	// *****************************************************************
	private IBeanList<BeanPropertyInt> tpIntArrayStatic = new TypeSafeArrayInstanceList<>(BeanPropertyInt.class);
	
	private void safeAccessTpIntArrayStatic() {
		tpIntArrayStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpIntArrayStatic"));
	}
		
	public IBeanList<BeanPropertyInt> getTpIntArrayStatic() {
		safeAccessTpIntArrayStatic();
		return tpIntArrayStatic;
	}
	
	// *****************************************************************
	// * Array Attribute: tpFloatArrayDynamic
	// *****************************************************************
	private IBeanList<BeanPropertyFloat> tpFloatArrayDynamic = new TypeSafeArrayInstanceList<>(BeanPropertyFloat.class);
	
	private void safeAccessTpFloatArrayDynamic() {
		tpFloatArrayDynamic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpFloatArrayDynamic"));
	}
		
	public IBeanList<BeanPropertyFloat> getTpFloatArrayDynamic() {
		safeAccessTpFloatArrayDynamic();
		return tpFloatArrayDynamic;
	}
	
	// *****************************************************************
	// * Array Attribute: tpFloatArrayStatic
	// *****************************************************************
	private IBeanList<BeanPropertyFloat> tpFloatArrayStatic = new TypeSafeArrayInstanceList<>(BeanPropertyFloat.class);
	
	private void safeAccessTpFloatArrayStatic() {
		tpFloatArrayStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpFloatArrayStatic"));
	}
		
	public IBeanList<BeanPropertyFloat> getTpFloatArrayStatic() {
		safeAccessTpFloatArrayStatic();
		return tpFloatArrayStatic;
	}
	
	// *****************************************************************
	// * Array Attribute: tpBooleanArrayDynamic
	// *****************************************************************
	private IBeanList<BeanPropertyBoolean> tpBooleanArrayDynamic = new TypeSafeArrayInstanceList<>(BeanPropertyBoolean.class);
	
	private void safeAccessTpBooleanArrayDynamic() {
		tpBooleanArrayDynamic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpBooleanArrayDynamic"));
	}
	
	public IBeanList<BeanPropertyBoolean> getTpBooleanArrayDynamic() {
		safeAccessTpBooleanArrayDynamic();
		return tpBooleanArrayDynamic;
	}
	
	// *****************************************************************
	// * Array Attribute: tpBooleanArrayStatic
	// *****************************************************************
	private IBeanList<BeanPropertyBoolean> tpBooleanArrayStatic = new TypeSafeArrayInstanceList<>(BeanPropertyBoolean.class);
	
	private void safeAccessTpBooleanArrayStatic() {
		tpBooleanArrayStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpBooleanArrayStatic"));
	}
	
	public IBeanList<BeanPropertyBoolean> getTpBooleanArrayStatic() {
		safeAccessTpBooleanArrayStatic();
		return tpBooleanArrayStatic;
	}
	
	// *****************************************************************
	// * Array Attribute: tpResourceDynamich
	// *****************************************************************
	private IBeanList<BeanPropertyResource> tpResourceDynamich = new TypeSafeArrayInstanceList<>(BeanPropertyResource.class);
	
	private void safeAccessTpResourceDynamich() {
		tpResourceDynamich.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpResourceDynamich"));
	}
	
	public IBeanList<BeanPropertyResource> getTpResourceDynamich() {
		safeAccessTpResourceDynamich();
		return tpResourceDynamich;
	}
	
	// *****************************************************************
	// * Array Attribute: tpResourceStatic
	// *****************************************************************
	private IBeanList<BeanPropertyResource> tpResourceStatic = new TypeSafeArrayInstanceList<>(BeanPropertyResource.class);
	
	private void safeAccessTpResourceStatic() {
		tpResourceStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpResourceStatic"));
	}
	
	public IBeanList<BeanPropertyResource> getTpResourceStatic() {
		safeAccessTpResourceStatic();
		return tpResourceStatic;
	}
	
	// *****************************************************************
	// * Array Attribute: tpEnumDynamich
	// *****************************************************************
	private IBeanList<BeanPropertyEnum> tpEnumDynamich = new TypeSafeArrayInstanceList<>(BeanPropertyEnum.class);
	
	private void safeAccessTpEnumDynamich() {
		tpEnumDynamich.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpEnumDynamich"));
	}
	
	public IBeanList<BeanPropertyEnum> getTpEnumDynamich() {
		safeAccessTpEnumDynamich();
		return tpEnumDynamich;
	}
	
	// *****************************************************************
	// * Array Attribute: tpEnumStatic
	// *****************************************************************
	private IBeanList<BeanPropertyEnum> tpEnumStatic = new TypeSafeArrayInstanceList<>(BeanPropertyEnum.class);
	
	private void safeAccessTpEnumStatic() {
		tpEnumStatic.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpEnumStatic"));
	}
	
	public IBeanList<BeanPropertyEnum> getTpEnumStatic() {
		safeAccessTpEnumStatic();
		return tpEnumStatic;
	}
	
	
}

