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
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
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
 * Here all general information for the analysis is stored
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AThermalAnalysis extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.ThermalAnalysis";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_BOUNDARYCONDITIONS = "boundaryConditions";
	public static final String PROPERTY_MESHSIZES = "meshsizes";
	public static final String PROPERTY_THERMALANALYSISRESULTS = "thermalanalysisResults";
	public static final String PROPERTY_ANALYSISTYPE = "analysisType";
	public static final String PROPERTY_REPORTS = "reports";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AThermalAnalysis() {
	}
	
	public AThermalAnalysis(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "ThermalAnalysis");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "ThermalAnalysis");
		setTypeInstance(categoryAssignement);
	}
	
	public AThermalAnalysis(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: boundaryConditions
	// *****************************************************************
	private BeanPropertyComposed<BoundaryConditions> boundaryConditions = new BeanPropertyComposed<>();
	
	private void safeAccessBoundaryConditions() {
		if (boundaryConditions.getTypeInstance() == null) {
			ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) helper.getPropertyInstance("boundaryConditions");
			boundaryConditions.setTypeInstance(propertyInstance);
		}
	}
	
	@XmlElement(nillable = true)
	public BoundaryConditions getBoundaryConditions() {
		safeAccessBoundaryConditions();
		return boundaryConditions.getValue();
	}
	
	public BeanPropertyComposed<BoundaryConditions> getBoundaryConditionsBean() {
		safeAccessBoundaryConditions();
		return boundaryConditions;
	}
	
	// *****************************************************************
	// * Array Attribute: meshsizes
	// *****************************************************************
	private IBeanList<ComponentMeshSize> meshsizes = new TypeSafeComposedPropertyInstanceList<>(ComponentMeshSize.class);
	
	private void safeAccessMeshsizes() {
		if (meshsizes.getArrayInstance() == null) {
			meshsizes.setArrayInstance((ArrayInstance) helper.getPropertyInstance("meshsizes"));
		}
	}
	
	public IBeanList<ComponentMeshSize> getMeshsizes() {
		safeAccessMeshsizes();
		return meshsizes;
	}
	
	private IBeanList<BeanPropertyComposed<ComponentMeshSize>> meshsizesBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessMeshsizesBean() {
		if (meshsizesBean.getArrayInstance() == null) {
			meshsizesBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("meshsizes"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<ComponentMeshSize>> getMeshsizesBean() {
		safeAccessMeshsizesBean();
		return meshsizesBean;
	}
	
	// *****************************************************************
	// * Array Attribute: thermalanalysisResults
	// *****************************************************************
	private IBeanList<AnalysisResult> thermalanalysisResults = new TypeSafeComposedPropertyInstanceList<>(AnalysisResult.class);
	
	private void safeAccessThermalanalysisResults() {
		if (thermalanalysisResults.getArrayInstance() == null) {
			thermalanalysisResults.setArrayInstance((ArrayInstance) helper.getPropertyInstance("thermalanalysisResults"));
		}
	}
	
	public IBeanList<AnalysisResult> getThermalanalysisResults() {
		safeAccessThermalanalysisResults();
		return thermalanalysisResults;
	}
	
	private IBeanList<BeanPropertyComposed<AnalysisResult>> thermalanalysisResultsBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessThermalanalysisResultsBean() {
		if (thermalanalysisResultsBean.getArrayInstance() == null) {
			thermalanalysisResultsBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("thermalanalysisResults"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<AnalysisResult>> getThermalanalysisResultsBean() {
		safeAccessThermalanalysisResultsBean();
		return thermalanalysisResultsBean;
	}
	
	// *****************************************************************
	// * Attribute: analysisType
	// *****************************************************************
	private BeanPropertyComposed<AnalysisType> analysisType = new BeanPropertyComposed<>();
	
	private void safeAccessAnalysisType() {
		if (analysisType.getTypeInstance() == null) {
			ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) helper.getPropertyInstance("analysisType");
			analysisType.setTypeInstance(propertyInstance);
		}
	}
	
	@XmlElement(nillable = true)
	public AnalysisType getAnalysisType() {
		safeAccessAnalysisType();
		return analysisType.getValue();
	}
	
	public BeanPropertyComposed<AnalysisType> getAnalysisTypeBean() {
		safeAccessAnalysisType();
		return analysisType;
	}
	
	// *****************************************************************
	// * Array Attribute: reports
	// *****************************************************************
	private IBeanList<Reports> reports = new TypeSafeComposedPropertyInstanceList<>(Reports.class);
	
	private void safeAccessReports() {
		if (reports.getArrayInstance() == null) {
			reports.setArrayInstance((ArrayInstance) helper.getPropertyInstance("reports"));
		}
	}
	
	public IBeanList<Reports> getReports() {
		safeAccessReports();
		return reports;
	}
	
	private IBeanList<BeanPropertyComposed<Reports>> reportsBean = new TypeSafeComposedPropertyBeanList<>();
	
	private void safeAccessReportsBean() {
		if (reportsBean.getArrayInstance() == null) {
			reportsBean.setArrayInstance((ArrayInstance) helper.getPropertyInstance("reports"));
		}
	}
	
	@XmlElement
	public IBeanList<BeanPropertyComposed<Reports>> getReportsBean() {
		safeAccessReportsBean();
		return reportsBean;
	}
	
	
}
