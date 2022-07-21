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
import org.eclipse.emf.common.util.URI;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import javax.xml.bind.annotation.XmlRootElement;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import javax.xml.bind.annotation.XmlAccessType;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
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
 * Analysis reports can be referenced here
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AReports extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.thermal.Reports";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_REPORTNAME = "reportName";
	public static final String PROPERTY_COMMENT = "comment";
	public static final String PROPERTY_REPORT = "report";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public AReports() {
	}
	
	public AReports(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "Reports");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "Reports");
		setTypeInstance(categoryAssignement);
	}
	
	public AReports(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: reportName
	// *****************************************************************
	private BeanPropertyString reportName = new BeanPropertyString();
	
	private void safeAccessReportName() {
		if (reportName.getTypeInstance() == null) {
			reportName.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("reportName"));
		}
	}
	
	public Command setReportName(EditingDomain ed, String value) {
		safeAccessReportName();
		return this.reportName.setValue(ed, value);
	}
	
	public void setReportName(String value) {
		safeAccessReportName();
		this.reportName.setValue(value);
	}
	
	public String getReportName() {
		safeAccessReportName();
		return reportName.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getReportNameBean() {
		safeAccessReportName();
		return reportName;
	}
	
	// *****************************************************************
	// * Attribute: comment
	// *****************************************************************
	private BeanPropertyString comment = new BeanPropertyString();
	
	private void safeAccessComment() {
		if (comment.getTypeInstance() == null) {
			comment.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("comment"));
		}
	}
	
	public Command setComment(EditingDomain ed, String value) {
		safeAccessComment();
		return this.comment.setValue(ed, value);
	}
	
	public void setComment(String value) {
		safeAccessComment();
		this.comment.setValue(value);
	}
	
	public String getComment() {
		safeAccessComment();
		return comment.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getCommentBean() {
		safeAccessComment();
		return comment;
	}
	
	// *****************************************************************
	// * Attribute: report
	// *****************************************************************
	private BeanPropertyResource report = new BeanPropertyResource();
	
	private void safeAccessReport() {
		if (report.getTypeInstance() == null) {
			report.setTypeInstance((ResourcePropertyInstance) helper.getPropertyInstance("report"));
		}
	}
	
	public Command setReport(EditingDomain ed, URI value) {
		safeAccessReport();
		return this.report.setValue(ed, value);
	}
	
	public void setReport(URI value) {
		safeAccessReport();
		this.report.setValue(value);
	}
	
	public URI getReport() {
		safeAccessReport();
		return report.getValue();
	}
	
	@XmlElement
	public BeanPropertyResource getReportBean() {
		safeAccessReport();
		return report;
	}
	
	
}
