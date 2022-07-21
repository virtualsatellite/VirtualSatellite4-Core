/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.structural;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.category.BeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.roles.BeanDiscipline;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.concept.types.util.BeanStructuralElementInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.dvlm.json.ABeanStructuralElementInstanceAdapter;
import de.dlr.sc.virsat.model.dvlm.json.BeanDisciplineAdapter;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;
import de.dlr.sc.virsat.model.dvlm.structural.command.DeleteStructuralElementInstanceCommand;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Core functionality for a Concept Bean  that wraps SturcturalElementInstances
 * @author fisc_ph
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
// Ensure that the sei (by uuid) gets unmarshalled first
@XmlType(propOrder = {"structuralElementInstance", "name", "parent", "jaxbCategoryAssignments", "jaxbChildren", "jaxbSuperSeis", "assignedDiscipline"})
@ApiModel(description = "Abstract model class for bean SEIs."
		+ " Instead return a concrete bean SEI that is identified by a type field."
		+ " Currently concrete SEIs have no additional fields.")
public abstract class ABeanStructuralElementInstance implements IBeanStructuralElementInstance {

	protected StructuralElementInstance sei;
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ABeanStructuralElementInstance) {
			ABeanStructuralElementInstance rhsBeanSei = (ABeanStructuralElementInstance) obj;
			return sei.equals(rhsBeanSei.getStructuralElementInstance());
		} else if (obj instanceof CategoryAssignment) {
			return sei.equals(obj);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return sei.hashCode();
	}
	
	@Override
	public String getName() {
		return sei.getName();
	}
	
	@XmlElement(nillable = true)
	@ApiModelProperty(required = true)
	@Override
	public void setName(String seiName) {
		sei.setName(seiName);
	}
	
	@Override
	public Command setName(EditingDomain ed, String seiName) {
		return SetCommand.create(ed, sei, GeneralPackage.Literals.INAME__NAME, seiName);
	}

	@Override
	public StructuralElementInstance getStructuralElementInstance() {
		return sei;
	}
	
	@XmlElement(name = "uuid")
	@XmlJavaTypeAdapter(IUuidAdapter.class)
	@ApiModelProperty(name = "uuid", required = true,
		value = "Unique identifier for a bean",
		example = "b168b0df-84b6-4b7f-bede-69298b215f40")
	@Override
	public	void setStructuralElementInstance(StructuralElementInstance sei) {
		this.sei = sei;
	}
	
	@Override
	public void add(IBeanCategoryAssignment bca) {
		sei.getCategoryAssignments().add(bca.getTypeInstance());
	}
	
	@Override
	public void remove(IBeanCategoryAssignment bca) {
		sei.getCategoryAssignments().remove(bca.getTypeInstance());
	}
	
	@Override
	public Command add(EditingDomain ed, IBeanCategoryAssignment bca) {
		return AddCommand.create(ed, sei, CategoriesPackage.Literals.ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS, bca.getATypeInstance());
	}
	
	@Override
	public Command remove(EditingDomain ed, IBeanCategoryAssignment bca) {
		return RemoveCommand.create(ed, sei, CategoriesPackage.Literals.ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS, bca.getATypeInstance());
	}
	
	@Override
	public <BEAN_TYPE extends IBeanCategoryAssignment> List<BEAN_TYPE> getAll(Class<BEAN_TYPE> catBeanClazz) {
		BeanCategoryAssignmentHelper bcaHelper = new BeanCategoryAssignmentHelper();
		return bcaHelper.getAllBeanCategories(sei, catBeanClazz);
	}
	
	@Override
	public List<BeanCategoryAssignment> getCategoryAssignments() {
		BeanCategoryAssignmentHelper bcaHelper = new BeanCategoryAssignmentHelper();
		return bcaHelper.getAllBeanCategories(sei);
	}
	
	@Override
	public void setCategoryAssignments(List<BeanCategoryAssignment> newCaBeans) {
		List<CategoryAssignment> currentCas = sei.getCategoryAssignments();
		
		List<CategoryAssignment> newCas = new ArrayList<CategoryAssignment>();
		for (BeanCategoryAssignment aBeanCa : newCaBeans) {
			ATypeInstance ti = aBeanCa.getATypeInstance();
			newCas.add((CategoryAssignment) ti);
		}
		
		currentCas.clear();
		currentCas.addAll(newCas);
	}
	
	/**
	 * Shadows the original function, but makes the list modifiable
	 * so it can be used by JAXB
	 * @return modifiable list with all super seis
	 */
	public List<BeanCategoryAssignment> getJaxbCategoryAssignments() {
		return new ArrayList<BeanCategoryAssignment>(getCategoryAssignments());
	}
	
	@XmlElement(name = "categoryAssignments")
	@ApiModelProperty(required = true,
		name = "categoryAssignments",
		value = "List of the CA beans")
	public void setJaxbCategoryAssignments(List<BeanCategoryAssignment> newCaBeans) {
		setCategoryAssignments(newCaBeans);
	}
	
	@Override
	public <BEAN_TYPE extends IBeanCategoryAssignment> BEAN_TYPE getFirst(Class<BEAN_TYPE> catBeanClazz) {
		BeanCategoryAssignmentHelper beanCaHelper = new BeanCategoryAssignmentHelper();
		return beanCaHelper.getFirstBeanCategory(sei, catBeanClazz);
	}
	
	@Override
	public void add(IBeanStructuralElementInstance bsei) {
		sei.getChildren().add(bsei.getStructuralElementInstance());
	}
	
	@Override
	public void remove(IBeanStructuralElementInstance bsei) {
		sei.getChildren().remove(bsei.getStructuralElementInstance());
	}
	
	@Override
	public <SEI_TYPE extends IBeanStructuralElementInstance> List<SEI_TYPE> getChildren(Class<SEI_TYPE> beanSeiClazz) {
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		return bseiHelper.getAllSubBeanSeis(sei, beanSeiClazz);
	}
	
	@Override
	public List<ABeanStructuralElementInstance> getChildren() {
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		return bseiHelper.getAllSubBeanSeis(sei, ABeanStructuralElementInstance.class);
	}
	
	@Override
	public void setChildren(List<ABeanStructuralElementInstance> newBeanSeis) {
		List<StructuralElementInstance> currentChildren = sei.getChildren();
		
		List<StructuralElementInstance> newChildren = new ArrayList<StructuralElementInstance>();
		for (ABeanStructuralElementInstance beanSei : newBeanSeis) {
			StructuralElementInstance sei = beanSei.getStructuralElementInstance();
			newChildren.add(sei);
		}
		
		currentChildren.clear();
		currentChildren.addAll(newChildren);
	}
	
	/**
	 * Create a list of BeanStructuralElementInstanceReference from a list of seis
	 * @param seis list of seis
	 * @return list of BeanStructuralElementInstanceReference
	 */
	private List<BeanStructuralElementInstanceReference> getReferenceList(List<StructuralElementInstance> seis) {
		ArrayList<BeanStructuralElementInstanceReference> children = new ArrayList<BeanStructuralElementInstanceReference>();
		
		for (StructuralElementInstance sei : seis) {
			children.add(new BeanStructuralElementInstanceReference(sei));
		}
		
		return children;
	}
	
	/**
	 * Update list of seis from a list of BeanStructuralElementInstanceReference
	 * @param currentSeis list of seis
	 * @param newBeanSeis list of BeanStructuralElementInstanceReference
	 */
	private void setReferenceList(List<StructuralElementInstance> currentSeis, List<BeanStructuralElementInstanceReference> newBeanSeis) {
		List<StructuralElementInstance> newChildren = new ArrayList<StructuralElementInstance>();
		
		for (BeanStructuralElementInstanceReference beanSei : newBeanSeis) {
			StructuralElementInstance sei = beanSei.getStructuralElementInstance();
			newChildren.add(sei);
		}
		
		currentSeis.clear();
		currentSeis.addAll(newChildren);
	}
	
	public List<BeanStructuralElementInstanceReference> getJaxbChildren() {
		return getReferenceList(sei.getChildren());
	}
	
	@XmlElement(name = "children")
	@ApiModelProperty(name = "children", required = true,
		value = "List of the child beans")
	public void setJaxbChildren(List<BeanStructuralElementInstanceReference> newBeanSeis) {
		setReferenceList(sei.getChildren(), newBeanSeis);
	}
	
	@Override
	public <SEI_TYPE extends IBeanStructuralElementInstance> List<SEI_TYPE> getDeepChildren(Class<SEI_TYPE> beanSeiClazz) {
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		return bseiHelper.wrapAllBeanSeisOfType(sei.getDeepChildren(), beanSeiClazz);
	}
	
	@Override
	public Command add(EditingDomain ed, IBeanStructuralElementInstance bsei) {
		return AddCommand.create(ed, sei, StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN, bsei.getStructuralElementInstance());
	}
	
	@Override
	public Command remove(EditingDomain ed, IBeanStructuralElementInstance bsei) {
		return RemoveCommand.create(ed, sei, StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN, bsei.getStructuralElementInstance());
	}
	
	@Override
	public Command addSuperSei(EditingDomain ed, IBeanStructuralElementInstance bsei) {
		return AddCommand.create(ed, sei, InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS, bsei.getStructuralElementInstance());
	}
	
	@Override
	public void addSuperSei(IBeanStructuralElementInstance bsei) {
		sei.getSuperSeis().add(bsei.getStructuralElementInstance());
	}
	
	@Override
	public Command removeSuperSei(EditingDomain ed, IBeanStructuralElementInstance bsei) {
		return RemoveCommand.create(ed, sei, InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS, bsei.getStructuralElementInstance());
	}
	
	@Override
	public void removeSuperSei(IBeanStructuralElementInstance bsei) {
		sei.getSuperSeis().remove(bsei.getStructuralElementInstance());
	}
	
	@Override
	public <SEI_TYPE extends IBeanStructuralElementInstance> List<SEI_TYPE> getSuperSeis(Class<SEI_TYPE> beanSeiClazz) {
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		return bseiHelper.getSuperBeanSeis(sei, beanSeiClazz);
	}
	
	@Override
	public List<ABeanStructuralElementInstance> getSuperSeis() {
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		return bseiHelper.getSuperBeanSeis(sei, ABeanStructuralElementInstance.class);
	}
	
	@Override
	public void setSuperSeis(List<ABeanStructuralElementInstance> newBeanSeis) {
		List<StructuralElementInstance> currentSuperSeis = sei.getSuperSeis();
		
		List<StructuralElementInstance> newSuperSeis = new ArrayList<StructuralElementInstance>();
		for (ABeanStructuralElementInstance beanSei : newBeanSeis) {
			StructuralElementInstance sei = beanSei.getStructuralElementInstance();
			newSuperSeis.add(sei);
		}
		
		currentSuperSeis.clear();
		currentSuperSeis.addAll(newSuperSeis);
	}
	
	public List<BeanStructuralElementInstanceReference> getJaxbSuperSeis() {
		return getReferenceList(sei.getSuperSeis());
	}

	@XmlElement(name = "superSeis")
	@ApiModelProperty(name = "superSeis", required = true,
		value = "List of the super SEI beans")
	public void setJaxbSuperSeis(List<BeanStructuralElementInstanceReference> newBeanSeis) {
		setReferenceList(sei.getSuperSeis(), newBeanSeis);
	}
	
	@Override
	public <SEI_TYPE extends IBeanStructuralElementInstance> Set<SEI_TYPE> getAllSuperSeis(Class<SEI_TYPE> beanSeiClazz) {
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		return bseiHelper.getAllSuperBeanSeis(sei, beanSeiClazz);
	}
	
	@Override
	public String getUuid() {
		return sei.getUuid().toString();
	}
	
	@Override
	public <SEI_TYPE extends IBeanStructuralElementInstance> SEI_TYPE getParentOfClass(Class<SEI_TYPE> beanSeiClazz) {
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		return bseiHelper.getParentOfClass(sei, beanSeiClazz);
	}

	@Override
	@XmlElement(name = "parent", nillable = true)
	@ApiModelProperty(required = true,
		value = "Unique identifier for the parent bean",
		example = "b168b0df-84b6-4b7f-bede-69298b215f40")
	@XmlJavaTypeAdapter(ABeanStructuralElementInstanceAdapter.class)
	public BeanStructuralElementInstance getParent() {
		StructuralElementInstance parentSei = VirSatEcoreUtil.getEContainerOfClass(sei, StructuralElementInstance.class);
		if (parentSei != null) {
			return new BeanStructuralElementInstance(parentSei);
		} else {
			return null;
		}
	}
	
	@Override
	public void setParent(BeanStructuralElementInstance newParent) {
		if (newParent == null) {
			sei.setParent(null);
		} else {
			sei.setParent(newParent.getStructuralElementInstance());
		}
	}
	
	@Override
	public void delete() {
		EcoreUtil.delete(sei);
	}
	
	@Override
	public Command delete(EditingDomain ed) {
		return DeleteStructuralElementInstanceCommand.create(ed, sei);
	}
	
	@Override
	public void removeAllCategoryAssignment(List<? extends IBeanCategoryAssignment> beanList) {
		for (IBeanCategoryAssignment bca : beanList) {
			remove(bca);
		}	
	}
	
	@Override
	public Command removeAllCategoryAssignment(EditingDomain ed, List<IBeanCategoryAssignment> beanList) {
		CompoundCommand cmd = new CompoundCommand();
		for (IBeanCategoryAssignment bca : beanList) {
			cmd.append(remove(ed, bca));
		}
		return cmd;
	}
	
	@Override
	public void removeAllStructuralElementInstance(List<IBeanStructuralElementInstance> beanList) {
		for (IBeanStructuralElementInstance bse : beanList) {
			remove(bse);
		}
	}
	
	@Override
	public Command removeAllStructuralElementInstance(EditingDomain ed, List<IBeanStructuralElementInstance> beanList) {
		CompoundCommand cmd = new CompoundCommand();
		for (IBeanStructuralElementInstance bsei : beanList) {
			cmd.append(remove(ed, bsei)); 
		}
		return cmd;
	}
	
	@Override
	public <BEAN_TYPE extends IBeanCategoryAssignment> void removeAll(Class<BEAN_TYPE> catBeanClazz) {
		List<BEAN_TYPE> caList = getAll(catBeanClazz);
		removeAllCategoryAssignment(caList);
	}
	
	@Override
	public <BEAN_TYPE extends IBeanCategoryAssignment> boolean hasCategory(Class<BEAN_TYPE> catBeanClazz) {
		BeanCategoryAssignmentHelper beanCaHelper = new BeanCategoryAssignmentHelper();
		BEAN_TYPE beanVariable = beanCaHelper.getFirstBeanCategory(sei, catBeanClazz);
		if (beanVariable != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canBeRoot() {
		if (sei != null && sei.getType() != null) {
			StructuralElement se = sei.getType();
			return se.isIsRootStructuralElement();
		}
		return false;
	}
	
	@XmlElement(nillable = true)
	@ApiModelProperty(value = "Uuid of the referenced Discipline that can edit this SEI")
	@XmlJavaTypeAdapter(BeanDisciplineAdapter.class)
	@Override
	public BeanDiscipline getAssignedDiscipline() {
		Discipline assignedDiscipline = sei.getAssignedDiscipline();
		
		if (assignedDiscipline == null) {
			return null;
		}
		
		return new BeanDiscipline(assignedDiscipline);
	}
	
	@Override
	public void setAssignedDiscipline(BeanDiscipline disciplineBean) {
		if (disciplineBean != null) {
			sei.setAssignedDiscipline(disciplineBean.getDiscipline());
		}
	}
}
