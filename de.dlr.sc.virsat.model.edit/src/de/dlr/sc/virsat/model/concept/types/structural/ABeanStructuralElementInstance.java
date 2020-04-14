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

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.concept.types.util.BeanStructuralElementInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;
import de.dlr.sc.virsat.model.dvlm.structural.command.DeleteStructuralElementInstanceCommand;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;

/**
 * Core functionality for a Concept Bean  that wraps SturcturalElementInstances
 * @author fisc_ph
 *
 */
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
	public BeanStructuralElementInstance getParentSeiBean() {
		StructuralElementInstance parentSei = VirSatEcoreUtil.getEContainerOfClass(sei, StructuralElementInstance.class);
		if (parentSei != null) {
			return new BeanStructuralElementInstance(parentSei);
		} else {
			return null;
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
}
