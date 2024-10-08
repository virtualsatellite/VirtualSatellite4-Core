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
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyInstanceList;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;


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
public abstract class ARequirementLink extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.RequirementLink";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_SUBJECT = "subject";
	public static final String PROPERTY_TARGETS = "targets";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ARequirementLink() {
	}
	
	public ARequirementLink(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "RequirementLink");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "RequirementLink");
		setTypeInstance(categoryAssignement);
	}
	
	public ARequirementLink(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: type
	// *****************************************************************
	private BeanPropertyReference<RequirementLinkType> type = new BeanPropertyReference<>();
	
	private void safeAccessType() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("type");
		type.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public RequirementLinkType getType() {
		safeAccessType();
		return type.getValue();
	}
	
	public Command setType(EditingDomain ed, RequirementLinkType value) {
		safeAccessType();
		return type.setValue(ed, value);
	}
	
	public void setType(RequirementLinkType value) {
		safeAccessType();
		type.setValue(value);
	}
	
	public BeanPropertyReference<RequirementLinkType> getTypeBean() {
		safeAccessType();
		return type;
	}
	
	// *****************************************************************
	// * Attribute: subject
	// *****************************************************************
	private BeanPropertyReference<Requirement> subject = new BeanPropertyReference<>();
	
	private void safeAccessSubject() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("subject");
		subject.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public Requirement getSubject() {
		safeAccessSubject();
		return subject.getValue();
	}
	
	public Command setSubject(EditingDomain ed, Requirement value) {
		safeAccessSubject();
		return subject.setValue(ed, value);
	}
	
	public void setSubject(Requirement value) {
		safeAccessSubject();
		subject.setValue(value);
	}
	
	public BeanPropertyReference<Requirement> getSubjectBean() {
		safeAccessSubject();
		return subject;
	}
	
	// *****************************************************************
	// * Array Attribute: targets
	// *****************************************************************
		private IBeanList<Requirement> targets = new TypeSafeReferencePropertyInstanceList<>(Requirement.class);
	
		private void safeAccessTargets() {
			if (targets.getArrayInstance() == null) {
				targets.setArrayInstance((ArrayInstance) helper.getPropertyInstance("targets"));
			}
		}
	
		public IBeanList<Requirement> getTargets() {
			safeAccessTargets();
			return targets;
		}
		
		private IBeanList<BeanPropertyReference<Requirement>> targetsBean = new TypeSafeReferencePropertyBeanList<>();
		
		private void safeAccessTargetsBean() {
			if (targetsBean.getArrayInstance() == null) {
				targetsBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("targets"));
			}
		}
		
		@XmlElement
		public IBeanList<BeanPropertyReference<Requirement>> getTargetsBean() {
			safeAccessTargetsBean();
			return targetsBean;
		}
	
	
}
