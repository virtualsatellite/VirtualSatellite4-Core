/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.model;

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
 * Category to describe documents such as specifications
 * 
 */	
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ADocument extends GenericCategory implements IBeanCategoryAssignment {

	public static final String FULL_QUALIFIED_CATEGORY_NAME = "de.dlr.sc.virsat.model.extension.ps.Document";
	
	/**
 	* Call this method to get the full qualified name of the underlying category
 	* @return The FQN of the category as String
 	*/
	public String getFullQualifiedCategoryName() {
		return FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	// property name constants
	public static final String PROPERTY_DOCUMENTNAME = "documentName";
	public static final String PROPERTY_NOTE = "note";
	public static final String PROPERTY_URL = "url";
	public static final String PROPERTY_FILE = "file";
	
	
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public ADocument() {
	}
	
	public ADocument(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "Document");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "Document");
		setTypeInstance(categoryAssignement);
	}
	
	public ADocument(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}
	
	
	// *****************************************************************
	// * Attribute: documentName
	// *****************************************************************
	private BeanPropertyString documentName = new BeanPropertyString();
	
	private void safeAccessDocumentName() {
		if (documentName.getTypeInstance() == null) {
			documentName.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("documentName"));
		}
	}
	
	public Command setDocumentName(EditingDomain ed, String value) {
		safeAccessDocumentName();
		return this.documentName.setValue(ed, value);
	}
	
	public void setDocumentName(String value) {
		safeAccessDocumentName();
		this.documentName.setValue(value);
	}
	
	public String getDocumentName() {
		safeAccessDocumentName();
		return documentName.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getDocumentNameBean() {
		safeAccessDocumentName();
		return documentName;
	}
	
	// *****************************************************************
	// * Attribute: note
	// *****************************************************************
	private BeanPropertyString note = new BeanPropertyString();
	
	private void safeAccessNote() {
		if (note.getTypeInstance() == null) {
			note.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("note"));
		}
	}
	
	public Command setNote(EditingDomain ed, String value) {
		safeAccessNote();
		return this.note.setValue(ed, value);
	}
	
	public void setNote(String value) {
		safeAccessNote();
		this.note.setValue(value);
	}
	
	public String getNote() {
		safeAccessNote();
		return note.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getNoteBean() {
		safeAccessNote();
		return note;
	}
	
	// *****************************************************************
	// * Attribute: url
	// *****************************************************************
	private BeanPropertyString url = new BeanPropertyString();
	
	private void safeAccessUrl() {
		if (url.getTypeInstance() == null) {
			url.setTypeInstance((ValuePropertyInstance) helper.getPropertyInstance("url"));
		}
	}
	
	public Command setUrl(EditingDomain ed, String value) {
		safeAccessUrl();
		return this.url.setValue(ed, value);
	}
	
	public void setUrl(String value) {
		safeAccessUrl();
		this.url.setValue(value);
	}
	
	public String getUrl() {
		safeAccessUrl();
		return url.getValue();
	}
	
	@XmlElement
	public BeanPropertyString getUrlBean() {
		safeAccessUrl();
		return url;
	}
	
	// *****************************************************************
	// * Attribute: file
	// *****************************************************************
	private BeanPropertyResource file = new BeanPropertyResource();
	
	private void safeAccessFile() {
		if (file.getTypeInstance() == null) {
			file.setTypeInstance((ResourcePropertyInstance) helper.getPropertyInstance("file"));
		}
	}
	
	public Command setFile(EditingDomain ed, URI value) {
		safeAccessFile();
		return this.file.setValue(ed, value);
	}
	
	public void setFile(URI value) {
		safeAccessFile();
		this.file.setValue(value);
	}
	
	public URI getFile() {
		safeAccessFile();
		return file.getValue();
	}
	
	@XmlElement
	public BeanPropertyResource getFileBean() {
		safeAccessFile();
		return file;
	}
	
	
}
