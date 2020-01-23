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
import java.util.Objects;

import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;

/**
 * Core functionality for the type safe referenced property instance list and implementation to the interface bean list
 * @author leps_je
 *
 * @param <BEAN_TYPE>
 */
public class TypeSafeReferencePropertyInstanceList<BEAN_TYPE extends IBeanObject<? extends ATypeInstance>> extends AArrayInstanceList<BEAN_TYPE> implements IBeanList<BEAN_TYPE> {

	/**
	 * class constructor of the type sage reference property instance list 
	 * @param beanClazz 
	 */
	public TypeSafeReferencePropertyInstanceList(Class<BEAN_TYPE> beanClazz) {
		super();
		this.beanClazz = beanClazz;
	}
	
	/**
	 * class constructor of the type sage reference property instance list 
	 * @param beanClazz 
	 * @param ai the array Instance
	 */
	public TypeSafeReferencePropertyInstanceList(Class<BEAN_TYPE> beanClazz, ArrayInstance ai) {
		super(ai);
		this.beanClazz = beanClazz;
	}
	
	protected Class<BEAN_TYPE> beanClazz;
	
	/**
	 * this method get the bean class of the type safe reference property instance list
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
		return indexOf(o) >= 0;
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
				bean.setATypeInstance(((ReferencePropertyInstance) cpi).getReference());
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
		ReferencePropertyInstance cpi = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		cpi.setType(ai.getType());
		cpi.setReference(e.getTypeInstance());
		return cpi;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected APropertyInstance createRemovePi(Object o) {
		if (o instanceof IBeanObject) {
			BEAN_TYPE bean = (BEAN_TYPE) o;
			ATypeInstance ca = bean.getTypeInstance();
			
			for (APropertyInstance pi : ai.getArrayInstances()) {
				ReferencePropertyInstance rpi = (ReferencePropertyInstance) pi;
				ATypeInstance referencedObject = rpi.getReference();
				if (referencedObject != null && referencedObject.equals(ca)) {
					return rpi;
				}
			}
		}
		return null;
	}

	@Override
	public boolean addAll(int index, Collection<? extends BEAN_TYPE> c) {
		super.addAll(index, c);
		List<ReferencePropertyInstance> addCas = new ArrayList<>();
		c.forEach((bean) -> {
			ReferencePropertyInstance cpi = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
			cpi.setType(ai.getType());
			cpi.setReference(bean.getTypeInstance());
			addCas.add(cpi);
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> c) {
		super.retainAll(c);
		// First collect information of which CAs should be kept in the list
		List<ATypeInstance> retainCas = new ArrayList<>();
		c.forEach((object) -> {
			if (object instanceof IBeanObject) {
				BEAN_TYPE bean = (BEAN_TYPE) object;
				retainCas.add(bean.getTypeInstance());
			}
		});
		
		// Now loop over all current Beans and check for their CA
		// In case it is not in the retain list remember it for removal
		List<ReferencePropertyInstance> removeCpis = new ArrayList<>();
		for (APropertyInstance pi : ai.getArrayInstances()) {
			ReferencePropertyInstance cpi = (ReferencePropertyInstance) pi;
			if (!retainCas.contains(cpi.getReference())) {
				removeCpis.add(cpi);
			}
		}
		
		ai.getArrayInstances().removeAll(removeCpis);
		
		return !removeCpis.isEmpty();
	}

	@Override
	public BEAN_TYPE get(int index) {
		BEAN_TYPE bean = null;
		try {
			bean = beanClazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		ATypeInstance ca = ((ReferencePropertyInstance) ai.getArrayInstances().get(index)).getReference();
		bean.setATypeInstance(ca);
		return bean;
	}

	@Override
	public BEAN_TYPE set(int index, BEAN_TYPE element) {
		super.set(index, element);
		BEAN_TYPE oldBean = get(index);
		ReferencePropertyInstance cpi = (ReferencePropertyInstance) ai.getArrayInstances().get(index);
		cpi.setType(ai.getType());
		cpi.setReference(element.getTypeInstance());
		return oldBean;
	}

	@Override
	public void add(int index, BEAN_TYPE element) {
		super.add(index, element);
		ReferencePropertyInstance cpi = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		cpi.setType(ai.getType());
		cpi.setReference(element.getTypeInstance());
		ai.getArrayInstances().add(index, cpi);
	}

	@Override
	public BEAN_TYPE remove(int index) {
		super.remove(index);
		BEAN_TYPE oldBean = get(index);
		ai.getArrayInstances().remove(index);
		return oldBean;
	}

	
	@Override
	public int indexOf(Object o) {
		if (o != null && !(o instanceof IBeanObject<?>)) {
			return INDEX_DOES_NOT_EXIST;
		}

		ATypeInstance ti = null;
		if (o != null) {
			IBeanObject<?> categoryBean = (IBeanObject<?>) o;
			ti = categoryBean.getTypeInstance();
		}
		
		for (int i = 0; i < ai.getArrayInstances().size(); i++) {
			APropertyInstance pi = ai.getArrayInstances().get(i);
			ReferencePropertyInstance cpi = (ReferencePropertyInstance) pi;
			
			if (Objects.equals(cpi.getReference(), ti)) {
				return i;
			}
		}
		return INDEX_DOES_NOT_EXIST;
	}

	@Override
	public int lastIndexOf(Object o) {
		if (o != null && !(o instanceof IBeanObject<?>)) {
			return INDEX_DOES_NOT_EXIST;
		}
		
		ATypeInstance ti = null;
		if (o != null) {
			IBeanObject<?> categoryBean = (IBeanObject<?>) o;
			ti = categoryBean.getTypeInstance();
		}
		
		for (int i = ai.getArrayInstances().size() - 1; i >= 0; i--) {
			APropertyInstance pi = ai.getArrayInstances().get(i);
			ReferencePropertyInstance cpi = (ReferencePropertyInstance) pi;
			
			if (Objects.equals(cpi.getReference(), ti)) {
				return i;
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
