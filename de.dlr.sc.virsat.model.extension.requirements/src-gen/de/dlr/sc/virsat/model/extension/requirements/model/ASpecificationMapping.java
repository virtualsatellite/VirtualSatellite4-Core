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
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import org.eclipse.emf.edit.domain.EditingDomain;
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
public abstract class ASpecificationMapping extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.SpecificationMapping";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_EXTERNALIDENTIFIER = "externalIdentifier";
	public static final String PROPERTY_SPECIFICATION = "specification";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ASpecificationMapping() {
	}
	
	public ASpecificationMapping(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "SpecificationMapping");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "SpecificationMapping");
		setTypeInstance(categoryAssignement);
	}
	
	public ASpecificationMapping(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: externalIdentifier
	// *****************************************************************
	private BeanPropertyString externalIdentifier = new BeanPropertyString();
	
	private void safeAccessExternalIdentifier() {
		if (externalIdentifier.getTypeInstance() == null) {
			externalIdentifier.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("externalIdentifier"));
		}
	}
	
	public Command setExternalIdentifier(EditingDomain ed, String value) {
		safeAccessExternalIdentifier();
		return this.externalIdentifier.setValue(ed, value);
	}
	
	public void setExternalIdentifier(String value) {
		safeAccessExternalIdentifier();
		this.externalIdentifier.setValue(value);
	}
	
	public String getExternalIdentifier() {
		safeAccessExternalIdentifier();
		return externalIdentifier.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getExternalIdentifierBean() {
		safeAccessExternalIdentifier();
		return externalIdentifier;
	}
	
	// *****************************************************************
	// * Attribute: specification
	// *****************************************************************
	private BeanPropertyReference<RequirementsSpecification> specification = new BeanPropertyReference<>();
	
	private void safeAccessSpecification() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("specification");
		specification.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public RequirementsSpecification getSpecification() {
		safeAccessSpecification();
		return specification.getValue();
	}
	
	public Command setSpecification(EditingDomain ed, RequirementsSpecification value) {
		safeAccessSpecification();
		return specification.setValue(ed, value);
	}
	
	public void setSpecification(RequirementsSpecification value) {
		safeAccessSpecification();
		specification.setValue(value);
	}
	
	public BeanPropertyReference<RequirementsSpecification> getSpecificationBean() {
		safeAccessSpecification();
		return specification;
	}
	
	
}
