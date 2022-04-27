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
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import javax.xml.bind.annotation.XmlElement;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Maximum mesh element sizes for components can be defined here.
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AMeshSizes extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.MeshSizes";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_COMPONENTMESHSIZE = "componentMeshSize";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AMeshSizes() {
	}
	
	public AMeshSizes(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "MeshSizes");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "MeshSizes");
		setTypeInstance(categoryAssignement);
	}
	
	public AMeshSizes(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: componentMeshSize
	// *****************************************************************
	private IBeanList<ComponentMeshSize> componentMeshSize = new TypeSafeComposedPropertyInstanceList<>(ComponentMeshSize.class);
	
	private void safeAccessComponentMeshSize() {
		if (componentMeshSize.getArrayInstance() == null) {
			componentMeshSize.setArrayInstance((ArrayInstance) helper.getPropertyInstance("componentMeshSize"));
		}
	}
	
	public IBeanList<ComponentMeshSize> getComponentMeshSize() {
		safeAccessComponentMeshSize();
		return componentMeshSize;
	}
	
	private IBeanList<BeanPropertyComposed<ComponentMeshSize>> componentMeshSizeBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessComponentMeshSizeBean() {
		if (componentMeshSizeBean.getArrayInstance() == null) {
			componentMeshSizeBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("componentMeshSize"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<ComponentMeshSize>> getComponentMeshSizeBean() {
		safeAccessComponentMeshSizeBean();
		return componentMeshSizeBean;
	}
	
	
}
