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
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.emf.common.util.URI;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
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
	public static final String PROPERTY_TPSRING = "tpSring";
	public static final String PROPERTY_TPINT = "tpInt";
	public static final String PROPERTY_TPFLOAT = "tpFloat";
	public static final String PROPERTY_TPBOOLEAN = "tpBoolean";
	public static final String PROPERTY_TPRESOURCE = "tpResource";
	public static final String PROPERTY_TPENUM = "tpEnum";
	
	
	
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
	// * Attribute: tpSring
	// *****************************************************************
	private BeanPropertyString tpSring = new BeanPropertyString();
	
	private void safeAccessTpSring() {
		if (tpSring.getTypeInstance() == null) {
			tpSring.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("tpSring"));
		}
	}
	
	public Command setTpSring(EditingDomain ed, String value) {
		safeAccessTpSring();
		return this.tpSring.setValue(ed, value);
	}
	
	public void setTpSring(String value) {
		safeAccessTpSring();
		this.tpSring.setValue(value);
	}
	
	public String getTpSring() {
		safeAccessTpSring();
		return tpSring.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getTpSringBean() {
		safeAccessTpSring();
		return tpSring;
	}
	
	// *****************************************************************
	// * Attribute: tpInt
	// *****************************************************************
	private BeanPropertyInt tpInt = new BeanPropertyInt();
	
	private void safeAccessTpInt() {
		if (tpInt.getTypeInstance() == null) {
			tpInt.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("tpInt"));
		}
	}
	
	public Command setTpInt(EditingDomain ed, long value) {
		safeAccessTpInt();
		return this.tpInt.setValue(ed, value);
	}
	
	public void setTpInt(long value) {
		safeAccessTpInt();
		this.tpInt.setValue(value);
	}
	
	public long getTpInt() {
		safeAccessTpInt();
		return tpInt.getValue();
	}
	
	public boolean isSetTpInt() {
		safeAccessTpInt();
		return tpInt.isSet();
	}
	
	@XmlElement
	public BeanPropertyInt getTpIntBean() {
		safeAccessTpInt();
		return tpInt;
	}
	
	// *****************************************************************
	// * Attribute: tpFloat
	// *****************************************************************
	private BeanPropertyFloat tpFloat = new BeanPropertyFloat();
	
	private void safeAccessTpFloat() {
		if (tpFloat.getTypeInstance() == null) {
			tpFloat.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("tpFloat"));
		}
	}
	
	public Command setTpFloat(EditingDomain ed, double value) {
		safeAccessTpFloat();
		return this.tpFloat.setValue(ed, value);
	}
	
	public void setTpFloat(double value) {
		safeAccessTpFloat();
		this.tpFloat.setValue(value);
	}
	
	public double getTpFloat() {
		safeAccessTpFloat();
		return tpFloat.getValue();
	}
	
	public boolean isSetTpFloat() {
		safeAccessTpFloat();
		return tpFloat.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getTpFloatBean() {
		safeAccessTpFloat();
		return tpFloat;
	}
	
	// *****************************************************************
	// * Attribute: tpBoolean
	// *****************************************************************
	private BeanPropertyBoolean tpBoolean = new BeanPropertyBoolean();
	
	private void safeAccessTpBoolean() {
		if (tpBoolean.getTypeInstance() == null) {
			tpBoolean.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("tpBoolean"));
		}
	}
	
	public Command setTpBoolean(EditingDomain ed, boolean value) {
		safeAccessTpBoolean();
		return this.tpBoolean.setValue(ed, value);
	}
	
	public void setTpBoolean(boolean value) {
		safeAccessTpBoolean();
		this.tpBoolean.setValue(value);
	}
	
	public boolean getTpBoolean() {
		safeAccessTpBoolean();
		return tpBoolean.getValue();
	}
	
	@XmlElement
	public BeanPropertyBoolean getTpBooleanBean() {
		safeAccessTpBoolean();
		return tpBoolean;
	}
	
	// *****************************************************************
	// * Attribute: tpResource
	// *****************************************************************
	private BeanPropertyResource tpResource = new BeanPropertyResource();
	
	private void safeAccessTpResource() {
		if (tpResource.getTypeInstance() == null) {
			tpResource.setTypeInstance((ResourcePropertyInstance) helper.getPropertyInstance("tpResource"));
		}
	}
	
	public Command setTpResource(EditingDomain ed, URI value) {
		safeAccessTpResource();
		return this.tpResource.setValue(ed, value);
	}
	
	public void setTpResource(URI value) {
		safeAccessTpResource();
		this.tpResource.setValue(value);
	}
	
	public URI getTpResource() {
		safeAccessTpResource();
		return tpResource.getValue();
	}
	
	@XmlElement
	public BeanPropertyResource getTpResourceBean() {
		safeAccessTpResource();
		return tpResource;
	}
	
	// *****************************************************************
	// * Attribute: tpEnum
	// *****************************************************************
	private BeanPropertyEnum tpEnum = new BeanPropertyEnum();
	
	private void safeAccessTpEnum() {
		if (tpEnum.getTypeInstance() == null) {
			tpEnum.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("tpEnum"));
		}
	}
	
	public Command setTpEnum(EditingDomain ed, String value) {
		safeAccessTpEnum();
		return this.tpEnum.setValue(ed, value);
	}
	
	public void setTpEnum(String value) {
		safeAccessTpEnum();
		this.tpEnum.setValue(value);
	}
	
	public String getTpEnum() {
		safeAccessTpEnum();
		return tpEnum.getValue();
	}
	
	public double getTpEnumEnum() {
		safeAccessTpEnum();
		return tpEnum.getEnumValue();
	}
	
	@XmlElement
	public BeanPropertyEnum getTpEnumBean() {
		safeAccessTpEnum();
		return tpEnum;
	}
	
	
}
