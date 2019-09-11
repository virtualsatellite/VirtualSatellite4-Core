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
import java.util.List;

import de.dlr.sc.virsat.model.concept.types.property.IBeanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;

/**
 * Core functionality for the type safe array instance list and implementation to the interface bean list
 * @author leps_je
 *
 * @param <BEAN_TYPE> 
 */
public class TypeSafeArrayInstanceList<BEAN_TYPE extends IBeanProperty<? extends APropertyInstance, ?>> extends AArrayInstanceList<BEAN_TYPE> implements IBeanList<BEAN_TYPE> {

	/**
	 * constructor of the type safe array instance list class
	 * @param beanClazz 
	 */
	public TypeSafeArrayInstanceList(Class<BEAN_TYPE> beanClazz) {
		super();
		this.beanClazz = beanClazz;
	}
	
	/**
	 * constructor of the type safe array instance list class
	 * @param beanClazz 
	 * @param ai the array instance
	 */
	public TypeSafeArrayInstanceList(Class<BEAN_TYPE> beanClazz, ArrayInstance ai) {
		super(ai);
		this.beanClazz = beanClazz;
	}
	
	protected Class<BEAN_TYPE> beanClazz;
	
	/**
	 * this method returns the bean class
	 * @return the bean class
	 */
	public Class<BEAN_TYPE> getBeanClazz() {
		return beanClazz;
	}

	/**
	 * this method set the bean class
	 * @param beanClazz 
	 */
	public void setBeanClazz(Class<BEAN_TYPE> beanClazz) {
		this.beanClazz = beanClazz;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		if (o instanceof IBeanProperty) {
			BEAN_TYPE categoryBean = (BEAN_TYPE) o;
			APropertyInstance ca = categoryBean.getTypeInstance();
			return ai.getArrayInstances().contains(ca);
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

		ai.getArrayInstances().forEach((pi) -> {
			try {
				BEAN_TYPE bean;
				bean = beanClazz.newInstance();
				bean.setATypeInstance(pi);
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
		return e.getTypeInstance();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected APropertyInstance createRemovePi(Object o) {
		if (o instanceof IBeanProperty) {
			BEAN_TYPE bean = (BEAN_TYPE) o;
			APropertyInstance pi = bean.getTypeInstance();
			return pi;
		}
		return null;
	}

	@Override
	public boolean addAll(int index, Collection<? extends BEAN_TYPE> c) {
		super.addAll(index, c);
		List<APropertyInstance> addCas = new ArrayList<>();
		c.forEach((bean) -> addCas.add(bean.getTypeInstance()));
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> c) {
		super.retainAll(c);
		// First collect information of which CAs should be kept in the list
		List<APropertyInstance> retainCas = new ArrayList<>();
		c.forEach((object) -> {
			if (object instanceof IBeanProperty) {
				BEAN_TYPE bean = (BEAN_TYPE) object;
				retainCas.add(bean.getTypeInstance());
			}
		});
		
		// Now loop over all current Beans and check for their CA
		// In case it is not in the retain list remember it for removal
		List<APropertyInstance> removeCas = new ArrayList<>();
		for (APropertyInstance pi : ai.getArrayInstances()) {
			if (!retainCas.contains(pi)) {
				removeCas.add(pi);
			}
		}
		
		ai.getArrayInstances().removeAll(removeCas);
		
		return !removeCas.isEmpty();
	}

	@Override
	public BEAN_TYPE get(int index) {
		BEAN_TYPE bean = null;
		try {
			bean = beanClazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		bean.setATypeInstance(ai.getArrayInstances().get(index));
		return bean;
	}

	@Override
	public BEAN_TYPE set(int index, BEAN_TYPE element) {
		super.set(index, element);
		BEAN_TYPE oldBean = get(index);
		ai.getArrayInstances().set(index, element.getTypeInstance());
		return oldBean;
	}

	@Override
	public void add(int index, BEAN_TYPE element) {
		super.add(index, element);
		ai.getArrayInstances().add(index, element.getTypeInstance());
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
		if (o instanceof IBeanProperty) {
			BEAN_TYPE bean = (BEAN_TYPE) o;
			return ai.getArrayInstances().indexOf(bean.getTypeInstance());
		}
		return INDEX_DOES_NOT_EXIST;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int lastIndexOf(Object o) {
		if (o instanceof IBeanProperty) {
			BEAN_TYPE bean = (BEAN_TYPE) o;
			return ai.getArrayInstances().lastIndexOf(bean.getTypeInstance());
		}
		return INDEX_DOES_NOT_EXIST;
	}
	
	@Override
	public List<BEAN_TYPE> subList(int fromIndex, int toIndex) {
		List<BEAN_TYPE> beanList = new ArrayList<>();
		
		for (int index = fromIndex; index < toIndex; index++) {
			BEAN_TYPE bean = get(index);
			beanList.add(bean);
		}

		return beanList;
	}
}
