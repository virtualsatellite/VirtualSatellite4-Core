/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.category;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.concept.types.util.BeanStructuralElementInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.command.DeleteStructuralElementInstanceCommand;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;

/**
 * Core functionality for a Concept Bean and abstract implementation to the interface
 * @author fisc_ph
 *
 */
public abstract class ABeanCategoryAssignment extends ABeanObject<CategoryAssignment> implements IBeanCategoryAssignment {

	protected CategoryAssignmentHelper helper = new CategoryAssignmentHelper(null);
	protected Concept concept;
	
	@Override
	public void setTypeInstance(CategoryAssignment ti) {
		super.setTypeInstance(ti);
		helper.setCategoryAssignment(ti);
	}
	
	@Override
	public void setATypeInstance(ATypeInstance ti) {
		super.setATypeInstance(ti);
		helper.setCategoryAssignment(this.ti);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (ti != null) {
			if (obj instanceof ABeanCategoryAssignment) {
				ABeanCategoryAssignment rhsBeanCategory = (ABeanCategoryAssignment) obj;
				return ti.equals(rhsBeanCategory.getTypeInstance());
			} else if (obj instanceof CategoryAssignment) {
				return ti.equals(obj);
			}
		}
		return false;
	}
	
	/**
	 * Call this method to get the parent SEI of this CA which is of a given type
	 * @param beanSeiClazz the BeanClass defining the type to look for
	 * @param <SEI_TYPE> Generic class type
	 * @return the type or null in case it could not be found
	 */
	public <SEI_TYPE extends IBeanStructuralElementInstance> SEI_TYPE getParentOfClass(Class<SEI_TYPE> beanSeiClazz) {
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		return bseiHelper.getParentOfClass(ti, beanSeiClazz);
	}
	
	/**
	 * Call this method the get the direct parent SEI of this CA 
	 * @return the parent SEI or null if there is no parent or the corresponding bean could not be instantiated
	 */
	public IBeanStructuralElementInstance getParent() {
		StructuralElementInstance sei = VirSatEcoreUtil.getEContainerOfClass(ti, StructuralElementInstance.class);
		if (sei != null) {
			BeanStructuralElementInstanceFactory beanSeiFactory = new BeanStructuralElementInstanceFactory();
			try {
				return beanSeiFactory.getInstanceFor(sei);
			} catch (CoreException e) {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Call this method to get a parent CA of this CA which is of a given type
	 * @param beanCaClazz the BeanClass defining the type to look for
	 * @param <CA_TYPE> Generic class type
	 * @return the type or null in case it could not be found
	 */
	public <CA_TYPE extends IBeanCategoryAssignment> CA_TYPE getParentCaBeanOfClass(Class<CA_TYPE> beanCaClazz) {
		BeanCategoryAssignmentHelper bcaHelper = new BeanCategoryAssignmentHelper();
		return bcaHelper.getParentCaBeanOfClass(ti, beanCaClazz);
	}

	/**
	 * Call this method to get a CA bean of a given type in the closest parent
	 * @param beanCaClazz the BeanClass defining the type to look for
	 * @param <CA_TYPE> Generic CA type
	 * @return CA bean from parent SEI or null in case it could not be found
	 */
	public <CA_TYPE extends IBeanCategoryAssignment> CA_TYPE getCaBeanFromParentSei(Class<CA_TYPE> beanCaClazz) {
		// Get the containing SEI bean
		IBeanStructuralElementInstance beanSei = getParent();
		if (beanSei != null) {
			BeanStructuralElementInstance parent = beanSei.getParentSeiBean();
			
			while (parent != null) {
				// Get the next parent and check if there is CA of interest attached
				CA_TYPE caBean = parent.getFirst(beanCaClazz);
				if (caBean != null) {
					return caBean;
				}
				// Nothing found. try the next parent
				parent = parent.getParentSeiBean();
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the concept associated for this bean
	 * @return the concept associatd for this bean
	 */
	public Concept getConcept() {
		if (concept == null && ti != null) {
			ATypeDefinition td = ti.getType();
			concept = VirSatEcoreUtil.getEContainerOfClass(td, Concept.class);
		}
		
		return concept;
	}
	
	@Override
	public int hashCode() {
		return ti.hashCode();
	}
	
	@Override
	public String getName() {
		return ti.getName();
	}
	
	@Override
	public void setName(String caName) {
		ti.setName(caName);
	}
	
	@Override
	public Command setName(EditingDomain ed, String caName) {
		return SetCommand.create(ed, ti, GeneralPackage.Literals.INAME__NAME, caName);
	}
	
	@Override
	public void delete() {
		EcoreUtil.delete(ti);
	}
	
	@Override
	public Command delete(EditingDomain ed) {
		return DeleteStructuralElementInstanceCommand.create(ed, ti);
	}
}
