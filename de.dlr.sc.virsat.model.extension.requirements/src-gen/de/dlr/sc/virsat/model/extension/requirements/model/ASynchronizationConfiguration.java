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
public abstract class ASynchronizationConfiguration extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.requirements.SynchronizationConfiguration";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_SERVERURL = "serverUrl";
	public static final String PROPERTY_PROJECTNAME = "projectName";
	public static final String PROPERTY_MAPPING = "mapping";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ASynchronizationConfiguration() {
	}
	
	public ASynchronizationConfiguration(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "SynchronizationConfiguration");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "SynchronizationConfiguration");
		setTypeInstance(categoryAssignement);
	}
	
	public ASynchronizationConfiguration(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: serverUrl
	// *****************************************************************
	private BeanPropertyString serverUrl = new BeanPropertyString();
	
	private void safeAccessServerUrl() {
		if (serverUrl.getTypeInstance() == null) {
			serverUrl.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("serverUrl"));
		}
	}
	
	public Command setServerUrl(EditingDomain ed, String value) {
		safeAccessServerUrl();
		return this.serverUrl.setValue(ed, value);
	}
	
	public void setServerUrl(String value) {
		safeAccessServerUrl();
		this.serverUrl.setValue(value);
	}
	
	public String getServerUrl() {
		safeAccessServerUrl();
		return serverUrl.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getServerUrlBean() {
		safeAccessServerUrl();
		return serverUrl;
	}
	
	// *****************************************************************
	// * Attribute: projectName
	// *****************************************************************
	private BeanPropertyString projectName = new BeanPropertyString();
	
	private void safeAccessProjectName() {
		if (projectName.getTypeInstance() == null) {
			projectName.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("projectName"));
		}
	}
	
	public Command setProjectName(EditingDomain ed, String value) {
		safeAccessProjectName();
		return this.projectName.setValue(ed, value);
	}
	
	public void setProjectName(String value) {
		safeAccessProjectName();
		this.projectName.setValue(value);
	}
	
	public String getProjectName() {
		safeAccessProjectName();
		return projectName.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getProjectNameBean() {
		safeAccessProjectName();
		return projectName;
	}
	
	// *****************************************************************
	// * Attribute: mapping
	// *****************************************************************
	private BeanPropertyReference<ImportConfiguration> mapping = new BeanPropertyReference<>();
	
	private void safeAccessMapping() {
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("mapping");
		mapping.setTypeInstance(propertyInstance);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public ImportConfiguration getMapping() {
		safeAccessMapping();
		return mapping.getValue();
	}
	
	public Command setMapping(EditingDomain ed, ImportConfiguration value) {
		safeAccessMapping();
		return mapping.setValue(ed, value);
	}
	
	public void setMapping(ImportConfiguration value) {
		safeAccessMapping();
		mapping.setValue(value);
	}
	
	public BeanPropertyReference<ImportConfiguration> getMappingBean() {
		safeAccessMapping();
		return mapping;
	}
	
	
}
