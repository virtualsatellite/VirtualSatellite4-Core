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
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import org.eclipse.emf.common.util.URI;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;


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
public abstract class ARequirementsSpecification extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.RequirementsSpecification";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_EXPORTFILE = "exportFile";
	public static final String PROPERTY_REQUIREMENTS = "requirements";
	public static final String PROPERTY_LINKS = "links";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ARequirementsSpecification() {
	}
	
	public ARequirementsSpecification(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "RequirementsSpecification");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "RequirementsSpecification");
		setTypeInstance(categoryAssignement);
	}
	
	public ARequirementsSpecification(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: exportFile
	// *****************************************************************
	private BeanPropertyResource exportFile = new BeanPropertyResource();
	
	private void safeAccessExportFile() {
		if (exportFile.getTypeInstance() == null) {
			exportFile.setTypeInstance((ResourcePropertyInstance) helper.getPropertyInstance("exportFile"));
		}
	}
	
	public Command setExportFile(EditingDomain ed, URI value) {
		safeAccessExportFile();
		return this.exportFile.setValue(ed, value);
	}
	
	public void setExportFile(URI value) {
		safeAccessExportFile();
		this.exportFile.setValue(value);
	}
	
	public URI getExportFile() {
		safeAccessExportFile();
		return exportFile.getValue();
	}
	
	public BeanPropertyResource getExportFileBean() {
		safeAccessExportFile();
		return exportFile;
	}
	
	// *****************************************************************
	// * Array Attribute: requirements
	// *****************************************************************
	private IBeanList<RequirementObject> requirements = new TypeSafeComposedPropertyInstanceList<>(RequirementObject.class);
	
	private void safeAccessRequirements() {
		if (requirements.getArrayInstance() == null) {
			requirements.setArrayInstance((ArrayInstance) helper.getPropertyInstance("requirements"));
		}
	}
	
	public IBeanList<RequirementObject> getRequirements() {
		safeAccessRequirements();
		return requirements;
	}
	
	private IBeanList<BeanPropertyComposed<RequirementObject>> requirementsBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessRequirementsBean() {
		if (requirementsBean.getArrayInstance() == null) {
			requirementsBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("requirements"));
		}
	}
	
	public IBeanList<BeanPropertyComposed<RequirementObject>> getRequirementsBean() {
		safeAccessRequirementsBean();
		return requirementsBean;
	}
	
	// *****************************************************************
	// * Array Attribute: links
	// *****************************************************************
	private IBeanList<RequirementLink> links = new TypeSafeComposedPropertyInstanceList<>(RequirementLink.class);
	
	private void safeAccessLinks() {
		if (links.getArrayInstance() == null) {
			links.setArrayInstance((ArrayInstance) helper.getPropertyInstance("links"));
		}
	}
	
	public IBeanList<RequirementLink> getLinks() {
		safeAccessLinks();
		return links;
	}
	
	private IBeanList<BeanPropertyComposed<RequirementLink>> linksBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessLinksBean() {
		if (linksBean.getArrayInstance() == null) {
			linksBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("links"));
		}
	}
	
	public IBeanList<BeanPropertyComposed<RequirementLink>> getLinksBean() {
		safeAccessLinksBean();
		return linksBean;
	}
	
	
}
