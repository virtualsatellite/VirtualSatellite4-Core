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
import javax.xml.bind.annotation.XmlAccessorType;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyInstanceList;
import org.eclipse.emf.edit.domain.EditingDomain;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 * 
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ARequirementsView extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.RequirementsView";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TREEELEMENTTOVIEW = "treeElementToView";
	public static final String PROPERTY_SHOWDEEPCHILDREN = "showDeepChildren";
	public static final String PROPERTY_REQTYPESTOVIEW = "reqTypesToView";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ARequirementsView() {
	}
	
	public ARequirementsView(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "RequirementsView");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "RequirementsView");
		setTypeInstance(categoryAssignement);
	}
	
	public ARequirementsView(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: treeElementToView
	// *****************************************************************
	private BeanPropertyReference<IRequirementTreeElement> treeElementToView = new BeanPropertyReference<>();
	
	private void safeAccessTreeElementToView() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("treeElementToView");
		treeElementToView.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public IRequirementTreeElement getTreeElementToView() {
		safeAccessTreeElementToView();
		return treeElementToView.getValue();
	}
	
	public Command setTreeElementToView(EditingDomain ed, IRequirementTreeElement value) {
		safeAccessTreeElementToView();
		return treeElementToView.setValue(ed, value);
	}
	
	public void setTreeElementToView(IRequirementTreeElement value) {
		safeAccessTreeElementToView();
		treeElementToView.setValue(value);
	}
	
	public BeanPropertyReference<IRequirementTreeElement> getTreeElementToViewBean() {
		safeAccessTreeElementToView();
		return treeElementToView;
	}
	
	// *****************************************************************
	// * Attribute: showDeepChildren
	// *****************************************************************
	private BeanPropertyBoolean showDeepChildren = new BeanPropertyBoolean();
	
	private void safeAccessShowDeepChildren() {
		if (showDeepChildren.getTypeInstance() == null) {
			showDeepChildren.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("showDeepChildren"));
		}
	}
	
	public Command setShowDeepChildren(EditingDomain ed, boolean value) {
		safeAccessShowDeepChildren();
		return this.showDeepChildren.setValue(ed, value);
	}
	
	public void setShowDeepChildren(boolean value) {
		safeAccessShowDeepChildren();
		this.showDeepChildren.setValue(value);
	}
	
	public boolean getShowDeepChildren() {
		safeAccessShowDeepChildren();
		return showDeepChildren.getValue();
	}
	
	@XmlElement
	public BeanPropertyBoolean getShowDeepChildrenBean() {
		safeAccessShowDeepChildren();
		return showDeepChildren;
	}
	
	// *****************************************************************
	// * Array Attribute: reqTypesToView
	// *****************************************************************
		private IBeanList<RequirementType> reqTypesToView = new TypeSafeReferencePropertyInstanceList<>(RequirementType.class);
	
		private void safeAccessReqTypesToView() {
			if (reqTypesToView.getArrayInstance() == null) {
				reqTypesToView.setArrayInstance((ArrayInstance) helper.getPropertyInstance("reqTypesToView"));
			}
		}
	
		public IBeanList<RequirementType> getReqTypesToView() {
			safeAccessReqTypesToView();
			return reqTypesToView;
		}
		
		private IBeanList<BeanPropertyReference<RequirementType>> reqTypesToViewBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessReqTypesToViewBean() {
			if (reqTypesToViewBean.getArrayInstance() == null) {
				reqTypesToViewBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("reqTypesToView"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<RequirementType>> getReqTypesToViewBean() {
			safeAccessReqTypesToViewBean();
			return reqTypesToViewBean;
		}
	
	
}
