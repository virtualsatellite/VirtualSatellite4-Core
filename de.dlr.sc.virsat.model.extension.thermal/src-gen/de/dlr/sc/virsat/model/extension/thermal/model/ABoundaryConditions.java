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
 * Modeling the temperature boundary conditions on the system
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ABoundaryConditions extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.BoundaryConditions";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_BOUNDARIES = "boundaries";
	public static final String PROPERTY_HEATFLOWFACE = "heatflowface";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ABoundaryConditions() {
	}
	
	public ABoundaryConditions(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "BoundaryConditions");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "BoundaryConditions");
		setTypeInstance(categoryAssignement);
	}
	
	public ABoundaryConditions(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Array Attribute: boundaries
	// *****************************************************************
	private IBeanList<TemperatureBoundary> boundaries = new TypeSafeComposedPropertyInstanceList<>(TemperatureBoundary.class);
	
	private void safeAccessBoundaries() {
		if (boundaries.getArrayInstance() == null) {
			boundaries.setArrayInstance((ArrayInstance) helper.getPropertyInstance("boundaries"));
		}
	}
	
	public IBeanList<TemperatureBoundary> getBoundaries() {
		safeAccessBoundaries();
		return boundaries;
	}
	
	private IBeanList<BeanPropertyComposed<TemperatureBoundary>> boundariesBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessBoundariesBean() {
		if (boundariesBean.getArrayInstance() == null) {
			boundariesBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("boundaries"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<TemperatureBoundary>> getBoundariesBean() {
		safeAccessBoundariesBean();
		return boundariesBean;
	}
	
	// *****************************************************************
	// * Array Attribute: heatflowface
	// *****************************************************************
	private IBeanList<HeatFlowToFace> heatflowface = new TypeSafeComposedPropertyInstanceList<>(HeatFlowToFace.class);
	
	private void safeAccessHeatflowface() {
		if (heatflowface.getArrayInstance() == null) {
			heatflowface.setArrayInstance((ArrayInstance) helper.getPropertyInstance("heatflowface"));
		}
	}
	
	public IBeanList<HeatFlowToFace> getHeatflowface() {
		safeAccessHeatflowface();
		return heatflowface;
	}
	
	private IBeanList<BeanPropertyComposed<HeatFlowToFace>> heatflowfaceBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessHeatflowfaceBean() {
		if (heatflowfaceBean.getArrayInstance() == null) {
			heatflowfaceBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("heatflowface"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<HeatFlowToFace>> getHeatflowfaceBean() {
		safeAccessHeatflowfaceBean();
		return heatflowfaceBean;
	}
	
	
}
