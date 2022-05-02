/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.concept.list.TypeSafeArrayInstanceList;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import javax.xml.bind.annotation.XmlElement;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Mapping specification of imported requirements
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AImportConfiguration extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.ImportConfiguration";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_SELECTEDTYPEKEYS = "selectedTypeKeys";
	public static final String PROPERTY_TYPEDEFINITIONSCONTAINER = "typeDefinitionsContainer";
	public static final String PROPERTY_MAPPEDSPECIFICATIONS = "mappedSpecifications";
	public static final String PROPERTY_GROUPSUPPORT = "groupSupport";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AImportConfiguration() {
	}
	
	public AImportConfiguration(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "ImportConfiguration");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "ImportConfiguration");
		setTypeInstance(categoryAssignement);
	}
	
	public AImportConfiguration(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: selectedTypeKeys
	// *****************************************************************
	private IBeanList<BeanPropertyString> selectedTypeKeysBean = new TypeSafeArrayInstanceList<>(BeanPropertyString.class);
	
	private void safeAccessSelectedTypeKeysBean() {
		if (selectedTypeKeysBean.getArrayInstance() == null) {
			selectedTypeKeysBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("selectedTypeKeys"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyString> getSelectedTypeKeysBean() {
		safeAccessSelectedTypeKeysBean();
		return selectedTypeKeysBean;
	}
	
	// *****************************************************************
	// * Attribute: typeDefinitionsContainer
	// *****************************************************************
	private BeanPropertyReference<RequirementsConfiguration> typeDefinitionsContainer = new BeanPropertyReference<>();
	
	private void safeAccessTypeDefinitionsContainer() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("typeDefinitionsContainer");
		typeDefinitionsContainer.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public RequirementsConfiguration getTypeDefinitionsContainer() {
		safeAccessTypeDefinitionsContainer();
		return typeDefinitionsContainer.getValue();
	}
	
	public Command setTypeDefinitionsContainer(EditingDomain ed, RequirementsConfiguration value) {
		safeAccessTypeDefinitionsContainer();
		return typeDefinitionsContainer.setValue(ed, value);
	}
	
	public void setTypeDefinitionsContainer(RequirementsConfiguration value) {
		safeAccessTypeDefinitionsContainer();
		typeDefinitionsContainer.setValue(value);
	}
	
	public BeanPropertyReference<RequirementsConfiguration> getTypeDefinitionsContainerBean() {
		safeAccessTypeDefinitionsContainer();
		return typeDefinitionsContainer;
	}
	
	// *****************************************************************
	// * Array Attribute: mappedSpecifications
	// *****************************************************************
	private IBeanList<SpecificationMapping> mappedSpecifications = new TypeSafeComposedPropertyInstanceList<>(SpecificationMapping.class);
	
	private void safeAccessMappedSpecifications() {
		if (mappedSpecifications.getArrayInstance() == null) {
			mappedSpecifications.setArrayInstance((ArrayInstance) helper.getPropertyInstance("mappedSpecifications"));
		}
	}
	
	public IBeanList<SpecificationMapping> getMappedSpecifications() {
		safeAccessMappedSpecifications();
		return mappedSpecifications;
	}
	
	private IBeanList<BeanPropertyComposed<SpecificationMapping>> mappedSpecificationsBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessMappedSpecificationsBean() {
		if (mappedSpecificationsBean.getArrayInstance() == null) {
			mappedSpecificationsBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("mappedSpecifications"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<SpecificationMapping>> getMappedSpecificationsBean() {
		safeAccessMappedSpecificationsBean();
		return mappedSpecificationsBean;
	}
	
	// *****************************************************************
	// * Attribute: groupSupport
	// *****************************************************************
	private BeanPropertyBoolean groupSupport = new BeanPropertyBoolean();
	
	private void safeAccessGroupSupport() {
		if (groupSupport.getTypeInstance() == null) {
			groupSupport.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("groupSupport"));
		}
	}
	
	public Command setGroupSupport(EditingDomain ed, boolean value) {
		safeAccessGroupSupport();
		return this.groupSupport.setValue(ed, value);
	}
	
	public void setGroupSupport(boolean value) {
		safeAccessGroupSupport();
		this.groupSupport.setValue(value);
	}
	
	public boolean getGroupSupport() {
		safeAccessGroupSupport();
		return groupSupport.getValue();
	}
	
	@XmlElement
	public BeanPropertyBoolean getGroupSupportBean() {
		safeAccessGroupSupport();
		return groupSupport;
	}
	
	
}
