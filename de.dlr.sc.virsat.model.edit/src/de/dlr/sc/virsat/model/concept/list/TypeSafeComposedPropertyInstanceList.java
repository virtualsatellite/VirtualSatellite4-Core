/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;

/**
 * Core functionality for the type safe composed property instance list and implementation to the interface bean list
 * @author leps_je
 *
 * @param <BEAN_TYPE>
 */
public class TypeSafeComposedPropertyInstanceList<BEAN_TYPE extends IBeanCategoryAssignment> extends AArrayInstanceList<BEAN_TYPE> implements IBeanList<BEAN_TYPE> {

	/**
	 * constructor for the class type safe composed property instance list
	 * @param beanClazz 
	 */
	public TypeSafeComposedPropertyInstanceList(Class<BEAN_TYPE> beanClazz) {
		super();
		this.beanClazz = beanClazz;
	}
	
	/**
	 * constructor for the class type safe composed property instance list
	 * @param beanClazz 
	 * @param ai the array instance
	 */
	public TypeSafeComposedPropertyInstanceList(Class<BEAN_TYPE> beanClazz, ArrayInstance ai) {
		super(ai);
		this.beanClazz = beanClazz;
	}
	
	protected Class<BEAN_TYPE> beanClazz;
	
	/**
	 * this method get the bean clazz
	 * @return the bean clazz
	 */
	public Class<BEAN_TYPE> getBeanClazz() {
		return beanClazz;
	}

	/**
	 * this method set the bean clazz 
	 * @param beanClazz 
	 */
	public void setBeanClazz(Class<BEAN_TYPE> beanClazz) {
		this.beanClazz = beanClazz;
	}
	
	@Override
	public boolean contains(Object o) {
		if (o instanceof IBeanCategoryAssignment) {
			IBeanCategoryAssignment categoryBean = (IBeanCategoryAssignment) o;
			CategoryAssignment ca = categoryBean.getTypeInstance();

			for (APropertyInstance pi : ai.getArrayInstances()) {
				ComposedPropertyInstance cpi = (ComposedPropertyInstance) pi;
				if (cpi.getTypeInstance().equals(ca)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method creates a list of beans from the
	 * internal representation of CategoryAssignments
	 * @return a List of Beans wrapping the CategoryAssignments
	 */
	private List<BEAN_TYPE> getBeanList() {
		List<BEAN_TYPE> beanList = new ArrayList<>();

		ai.getArrayInstances().forEach((cpi) -> {
			try {
				BEAN_TYPE bean;
				bean = beanClazz.newInstance();
				bean.setTypeInstance(((ComposedPropertyInstance) cpi).getTypeInstance());
				beanList.add(bean);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		return beanList;
	}
	
	@Override
	public Object[] toArray() {
		return getBeanList().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return getBeanList().toArray(a);
	}

	@Override
	protected APropertyInstance createAddPi(BEAN_TYPE e) {
		ComposedPropertyInstance cpi = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
		cpi.setType(ai.getType());
		cpi.setTypeInstance(e.getTypeInstance());
		return cpi;
	}

	@Override
	public boolean add(BEAN_TYPE e) {
		if (composedTypeInstanceInList(e.getTypeInstance())) {
			return false;
		}
		return super.add(e);
	}
	
	@Override
	public Command add(EditingDomain ed, BEAN_TYPE e) {
		if (composedTypeInstanceInList(e.getTypeInstance())) {
			return UnexecutableCommand.INSTANCE;
		}
		return super.add(ed, e);
	}

	/**
	 * This method loops all items of the current array
	 * and checks if it is already composing the given type instance
	 * @param ti the TypeInstance to be checked if it is already part of the array
	 * @return true in case it could be found
	 */
	private boolean composedTypeInstanceInList(ATypeInstance ti) {
		for (APropertyInstance pi : ai.getArrayInstances()) {
			if (pi instanceof ComposedPropertyInstance) {
				ComposedPropertyInstance cpi = (ComposedPropertyInstance) pi;
				if (ti.equals(cpi.getTypeInstance())) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	protected APropertyInstance createRemovePi(Object o) {
		if (o instanceof IBeanCategoryAssignment) {
			IBeanCategoryAssignment bean = (IBeanCategoryAssignment) o;
			CategoryAssignment ca = bean.getTypeInstance();
			
			for (APropertyInstance pi : ai.getArrayInstances()) {
				ComposedPropertyInstance cpi = (ComposedPropertyInstance) pi;
				if (cpi.getTypeInstance().equals(ca)) {
					return cpi;
				}
			}
		}
		return null;
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends BEAN_TYPE> c) {
		super.addAll(index, c);
		List<ComposedPropertyInstance> addCas = new ArrayList<>();
		c.forEach((bean) -> {
			ATypeInstance composedTypeInstance = bean.getTypeInstance();
			if (!composedTypeInstanceInList(composedTypeInstance)) {
				ComposedPropertyInstance cpi = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
				cpi.setType(ai.getType());
				cpi.setTypeInstance(bean.getTypeInstance());
				addCas.add(cpi);
			}
		});
		return ai.getArrayInstances().addAll(index, addCas);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		super.removeAll(c);
		boolean listChanged = false;
		for (Object bean : c) {
			listChanged |= remove(bean);
		}
		return listChanged;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		super.retainAll(c);
		// First collect information of which CAs should be kept in the list
		List<CategoryAssignment> retainCas = new ArrayList<>();
		c.forEach((object) -> {
			if (object instanceof IBeanCategoryAssignment) {
				IBeanCategoryAssignment bean = (IBeanCategoryAssignment) object;
				retainCas.add(bean.getTypeInstance());
			}
		});
		
		// Now loop over all current Beans and check for their CA
		// In case it is not in the retain list remember it for removal
		List<ComposedPropertyInstance> removeCpis = new ArrayList<>();
		for (APropertyInstance pi : ai.getArrayInstances()) {
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) pi;
			if (!retainCas.contains(cpi.getTypeInstance())) {
				removeCpis.add(cpi);
			}
		}
		
		ai.getArrayInstances().removeAll(removeCpis);
		
		return !removeCpis.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public BEAN_TYPE get(int index) {
		BEAN_TYPE bean = null;
		CategoryAssignment ca = ((ComposedPropertyInstance) ai.getArrayInstances().get(index)).getTypeInstance();
		try {
			BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
			bean = (BEAN_TYPE) beanCaFactory.getInstanceFor(ca);
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
		return bean;
	}

	@Override
	public BEAN_TYPE set(int index, BEAN_TYPE element) {
		super.set(index, element);
		BEAN_TYPE oldBean = get(index);
		ComposedPropertyInstance cpi = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
		cpi.setType(ai.getType());
		cpi.setTypeInstance(element.getTypeInstance());
		ai.getArrayInstances().set(index, cpi);
		return oldBean;
	}

	@Override
	public void add(int index, BEAN_TYPE element) {
		super.add(index, element);
		if (!composedTypeInstanceInList(element.getTypeInstance())) {
			ComposedPropertyInstance cpi = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
			cpi.setType(ai.getType());
			cpi.setTypeInstance(element.getTypeInstance());
			ai.getArrayInstances().add(index, cpi);
		}
	}

	@Override
	public BEAN_TYPE remove(int index) {
		super.remove(index);
		BEAN_TYPE oldBean = get(index);
		ai.getArrayInstances().remove(index);
		return oldBean;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(Object o) {
		if (o instanceof IBeanCategoryAssignment) {
			BEAN_TYPE categoryBean = (BEAN_TYPE) o;
			CategoryAssignment ca = categoryBean.getTypeInstance();

			for (APropertyInstance pi : ai.getArrayInstances()) {
				ComposedPropertyInstance cpi = (ComposedPropertyInstance) pi;
				if (cpi.getTypeInstance().equals(ca)) {
					return ai.getArrayInstances().indexOf(cpi);
				}
			}
		}
		return INDEX_DOES_NOT_EXIST;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int lastIndexOf(Object o) {
		if (o instanceof IBeanCategoryAssignment) {
			BEAN_TYPE categoryBean = (BEAN_TYPE) o;
			CategoryAssignment ca = categoryBean.getTypeInstance();

			for (APropertyInstance pi : ai.getArrayInstances()) {
				ComposedPropertyInstance cpi = (ComposedPropertyInstance) pi;
				if (cpi.getTypeInstance().equals(ca)) {
					return ai.getArrayInstances().lastIndexOf(cpi);
				}
			}
		}
		return INDEX_DOES_NOT_EXIST;
	}

	@Override
	public List<BEAN_TYPE> subList(int fromIndex, int toIndex) {
		List<BEAN_TYPE> beanList = new LinkedList<>();
		
		for (int index = fromIndex; index < toIndex; index++) {
			beanList.add(get(index));
		}

		return beanList;
	}
}
