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
import de.dlr.sc.virsat.model.concept.list.TypeSafeArrayInstanceList;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
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
	private IBeanList<BeanPropertyString> tpSringArrayDynamicBean = new TypeSafeArrayInstanceList<>(BeanPropertyString.class);
	
	private void safeAccessTpSringArrayDynamicBean() {
		if (tpSringArrayDynamicBean.getArrayInstance() == null) {
			tpSringArrayDynamicBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpSringArrayDynamic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyString> getTpSringArrayDynamicBean() {
		safeAccessTpSringArrayDynamicBean();
		return tpSringArrayDynamicBean;
	}
	
	// *****************************************************************
	// * Array Attribute: tpSringArrayStatic
	// *****************************************************************
	private IBeanList<BeanPropertyString> tpSringArrayStaticBean = new TypeSafeArrayInstanceList<>(BeanPropertyString.class);
	
	private void safeAccessTpSringArrayStaticBean() {
		if (tpSringArrayStaticBean.getArrayInstance() == null) {
			tpSringArrayStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpSringArrayStatic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyString> getTpSringArrayStaticBean() {
		safeAccessTpSringArrayStaticBean();
		return tpSringArrayStaticBean;
	}
	
	// *****************************************************************
	// * Array Attribute: tpIntArrayDynamic
	// *****************************************************************
	private IBeanList<BeanPropertyInt> tpIntArrayDynamicBean = new TypeSafeArrayInstanceList<>(BeanPropertyInt.class);
	
	private void safeAccessTpIntArrayDynamicBean() {
		if (tpIntArrayDynamicBean.getArrayInstance() == null) {
			tpIntArrayDynamicBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpIntArrayDynamic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyInt> getTpIntArrayDynamicBean() {
		safeAccessTpIntArrayDynamicBean();
		return tpIntArrayDynamicBean;
	}
	
	// *****************************************************************
	// * Array Attribute: tpIntArrayStatic
	// *****************************************************************
	private IBeanList<BeanPropertyInt> tpIntArrayStaticBean = new TypeSafeArrayInstanceList<>(BeanPropertyInt.class);
	
	private void safeAccessTpIntArrayStaticBean() {
		if (tpIntArrayStaticBean.getArrayInstance() == null) {
			tpIntArrayStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpIntArrayStatic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyInt> getTpIntArrayStaticBean() {
		safeAccessTpIntArrayStaticBean();
		return tpIntArrayStaticBean;
	}
	
	// *****************************************************************
	// * Array Attribute: tpFloatArrayDynamic
	// *****************************************************************
	private IBeanList<BeanPropertyFloat> tpFloatArrayDynamicBean = new TypeSafeArrayInstanceList<>(BeanPropertyFloat.class);
	
	private void safeAccessTpFloatArrayDynamicBean() {
		if (tpFloatArrayDynamicBean.getArrayInstance() == null) {
			tpFloatArrayDynamicBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpFloatArrayDynamic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyFloat> getTpFloatArrayDynamicBean() {
		safeAccessTpFloatArrayDynamicBean();
		return tpFloatArrayDynamicBean;
	}
	
	// *****************************************************************
	// * Array Attribute: tpFloatArrayStatic
	// *****************************************************************
	private IBeanList<BeanPropertyFloat> tpFloatArrayStaticBean = new TypeSafeArrayInstanceList<>(BeanPropertyFloat.class);
	
	private void safeAccessTpFloatArrayStaticBean() {
		if (tpFloatArrayStaticBean.getArrayInstance() == null) {
			tpFloatArrayStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpFloatArrayStatic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyFloat> getTpFloatArrayStaticBean() {
		safeAccessTpFloatArrayStaticBean();
		return tpFloatArrayStaticBean;
	}
	
	// *****************************************************************
	// * Array Attribute: tpBooleanArrayDynamic
	// *****************************************************************
	private IBeanList<BeanPropertyBoolean> tpBooleanArrayDynamicBean = new TypeSafeArrayInstanceList<>(BeanPropertyBoolean.class);
	
	private void safeAccessTpBooleanArrayDynamicBean() {
		if (tpBooleanArrayDynamicBean.getArrayInstance() == null) {
			tpBooleanArrayDynamicBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpBooleanArrayDynamic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyBoolean> getTpBooleanArrayDynamicBean() {
		safeAccessTpBooleanArrayDynamicBean();
		return tpBooleanArrayDynamicBean;
	}
	
	// *****************************************************************
	// * Array Attribute: tpBooleanArrayStatic
	// *****************************************************************
	private IBeanList<BeanPropertyBoolean> tpBooleanArrayStaticBean = new TypeSafeArrayInstanceList<>(BeanPropertyBoolean.class);
	
	private void safeAccessTpBooleanArrayStaticBean() {
		if (tpBooleanArrayStaticBean.getArrayInstance() == null) {
			tpBooleanArrayStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpBooleanArrayStatic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyBoolean> getTpBooleanArrayStaticBean() {
		safeAccessTpBooleanArrayStaticBean();
		return tpBooleanArrayStaticBean;
	}
	
	// *****************************************************************
	// * Array Attribute: tpResourceDynamich
	// *****************************************************************
	private IBeanList<BeanPropertyResource> tpResourceDynamichBean = new TypeSafeArrayInstanceList<>(BeanPropertyResource.class);
	
	private void safeAccessTpResourceDynamichBean() {
		if (tpResourceDynamichBean.getArrayInstance() == null) {
			tpResourceDynamichBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpResourceDynamich"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyResource> getTpResourceDynamichBean() {
		safeAccessTpResourceDynamichBean();
		return tpResourceDynamichBean;
	}
	
	// *****************************************************************
	// * Array Attribute: tpResourceStatic
	// *****************************************************************
	private IBeanList<BeanPropertyResource> tpResourceStaticBean = new TypeSafeArrayInstanceList<>(BeanPropertyResource.class);
	
	private void safeAccessTpResourceStaticBean() {
		if (tpResourceStaticBean.getArrayInstance() == null) {
			tpResourceStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpResourceStatic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyResource> getTpResourceStaticBean() {
		safeAccessTpResourceStaticBean();
		return tpResourceStaticBean;
	}
	
	// *****************************************************************
	// * Array Attribute: tpEnumDynamich
	// *****************************************************************
	private IBeanList<BeanPropertyEnum> tpEnumDynamichBean = new TypeSafeArrayInstanceList<>(BeanPropertyEnum.class);
	
	private void safeAccessTpEnumDynamichBean() {
		if (tpEnumDynamichBean.getArrayInstance() == null) {
			tpEnumDynamichBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpEnumDynamich"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyEnum> getTpEnumDynamichBean() {
		safeAccessTpEnumDynamichBean();
		return tpEnumDynamichBean;
	}
	
	// *****************************************************************
	// * Array Attribute: tpEnumStatic
	// *****************************************************************
	private IBeanList<BeanPropertyEnum> tpEnumStaticBean = new TypeSafeArrayInstanceList<>(BeanPropertyEnum.class);
	
	private void safeAccessTpEnumStaticBean() {
		if (tpEnumStaticBean.getArrayInstance() == null) {
			tpEnumStaticBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("tpEnumStatic"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyEnum> getTpEnumStaticBean() {
		safeAccessTpEnumStaticBean();
		return tpEnumStaticBean;
	}
	
	
}
