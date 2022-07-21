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
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
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
 * Static or Transient
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AAnalysisType extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.AnalysisType";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_ANALYSISTYPE = "analysisType";
	public static final String PROPERTY_TIMESTEP = "timeStep";
	public static final String PROPERTY_TOTALTIME = "totalTime";
	public static final String PROPERTY_INCLUDEORBITRADIATION = "includeOrbitRadiation";
	
	// AnalysisType enumeration value names
	public static final String ANALYSISTYPE_Static_NAME = "Static";
	public static final String ANALYSISTYPE_Transient_NAME = "Transient";
	// AnalysisType enumeration values
	public static final String ANALYSISTYPE_Static_VALUE = "0";
	public static final String ANALYSISTYPE_Transient_VALUE = "1";
	// IncludeOrbitRadiation enumeration value names
	public static final String INCLUDEORBITRADIATION_Include_NAME = "Include";
	public static final String INCLUDEORBITRADIATION_NotInclude_NAME = "NotInclude";
	// IncludeOrbitRadiation enumeration values
	public static final String INCLUDEORBITRADIATION_Include_VALUE = "0";
	public static final String INCLUDEORBITRADIATION_NotInclude_VALUE = "1";
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AAnalysisType() {
	}
	
	public AAnalysisType(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "AnalysisType");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "AnalysisType");
		setTypeInstance(categoryAssignement);
	}
	
	public AAnalysisType(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: analysisType
	// *****************************************************************
	private BeanPropertyEnum analysisType = new BeanPropertyEnum();
	
	private void safeAccessAnalysisType() {
		if (analysisType.getTypeInstance() == null) {
			analysisType.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("analysisType"));
		}
	}
	
	public Command setAnalysisType(EditingDomain ed, String value) {
		safeAccessAnalysisType();
		return this.analysisType.setValue(ed, value);
	}
	
	public void setAnalysisType(String value) {
		safeAccessAnalysisType();
		this.analysisType.setValue(value);
	}
	
	public String getAnalysisType() {
		safeAccessAnalysisType();
		return analysisType.getValue();
	}
	
	public double getAnalysisTypeEnum() {
		safeAccessAnalysisType();
		return analysisType.getEnumValue();
	}
	
	@XmlElement
	public BeanPropertyEnum getAnalysisTypeBean() {
		safeAccessAnalysisType();
		return analysisType;
	}
	
	// *****************************************************************
	// * Attribute: timeStep
	// *****************************************************************
	private BeanPropertyFloat timeStep = new BeanPropertyFloat();
	
	private void safeAccessTimeStep() {
		if (timeStep.getTypeInstance() == null) {
			timeStep.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("timeStep"));
		}
	}
	
	public Command setTimeStep(EditingDomain ed, double value) {
		safeAccessTimeStep();
		return this.timeStep.setValue(ed, value);
	}
	
	public void setTimeStep(double value) {
		safeAccessTimeStep();
		this.timeStep.setValue(value);
	}
	
	public double getTimeStep() {
		safeAccessTimeStep();
		return timeStep.getValue();
	}
	
	public boolean isSetTimeStep() {
		safeAccessTimeStep();
		return timeStep.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getTimeStepBean() {
		safeAccessTimeStep();
		return timeStep;
	}
	
	// *****************************************************************
	// * Attribute: totalTime
	// *****************************************************************
	private BeanPropertyFloat totalTime = new BeanPropertyFloat();
	
	private void safeAccessTotalTime() {
		if (totalTime.getTypeInstance() == null) {
			totalTime.setTypeInstance((UnitValuePropertyInstance) helper.getPropertyInstance("totalTime"));
		}
	}
	
	public Command setTotalTime(EditingDomain ed, double value) {
		safeAccessTotalTime();
		return this.totalTime.setValue(ed, value);
	}
	
	public void setTotalTime(double value) {
		safeAccessTotalTime();
		this.totalTime.setValue(value);
	}
	
	public double getTotalTime() {
		safeAccessTotalTime();
		return totalTime.getValue();
	}
	
	public boolean isSetTotalTime() {
		safeAccessTotalTime();
		return totalTime.isSet();
	}
	
	@XmlElement
	public BeanPropertyFloat getTotalTimeBean() {
		safeAccessTotalTime();
		return totalTime;
	}
	
	// *****************************************************************
	// * Attribute: includeOrbitRadiation
	// *****************************************************************
	private BeanPropertyEnum includeOrbitRadiation = new BeanPropertyEnum();
	
	private void safeAccessIncludeOrbitRadiation() {
		if (includeOrbitRadiation.getTypeInstance() == null) {
			includeOrbitRadiation.setTypeInstance((EnumUnitPropertyInstance) helper.getPropertyInstance("includeOrbitRadiation"));
		}
	}
	
	public Command setIncludeOrbitRadiation(EditingDomain ed, String value) {
		safeAccessIncludeOrbitRadiation();
		return this.includeOrbitRadiation.setValue(ed, value);
	}
	
	public void setIncludeOrbitRadiation(String value) {
		safeAccessIncludeOrbitRadiation();
		this.includeOrbitRadiation.setValue(value);
	}
	
	public String getIncludeOrbitRadiation() {
		safeAccessIncludeOrbitRadiation();
		return includeOrbitRadiation.getValue();
	}
	
	public double getIncludeOrbitRadiationEnum() {
		safeAccessIncludeOrbitRadiation();
		return includeOrbitRadiation.getEnumValue();
	}
	
	@XmlElement
	public BeanPropertyEnum getIncludeOrbitRadiationBean() {
		safeAccessIncludeOrbitRadiation();
		return includeOrbitRadiation;
	}
	
	
}
