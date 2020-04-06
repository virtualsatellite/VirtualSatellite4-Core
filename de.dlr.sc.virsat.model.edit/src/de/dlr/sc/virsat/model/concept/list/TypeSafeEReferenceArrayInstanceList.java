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

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;

public class TypeSafeEReferenceArrayInstanceList<ETYPE extends EObject> extends AArrayInstanceList<BeanPropertyEReference<ETYPE>> {

	public TypeSafeEReferenceArrayInstanceList() {
		super();
	}

	public TypeSafeEReferenceArrayInstanceList(ArrayInstance ai) {
		super(ai);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		if (o instanceof BeanPropertyEReference<?>) {
			BeanPropertyEReference<ETYPE> categoryBean = (BeanPropertyEReference<ETYPE>) o;
			APropertyInstance ca = categoryBean.getTypeInstance();
			for (APropertyInstance pi : ai.getArrayInstances()) {
				if (pi.getUuid().equals(ca.getUuid())) {
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
	private List<BeanPropertyEReference<ETYPE>> getBeanList() {
		List<BeanPropertyEReference<ETYPE>> beanList = new ArrayList<>();

		ai.getArrayInstances().forEach((pi) -> {
			try {
				BeanPropertyEReference<ETYPE> bean = new BeanPropertyEReference<ETYPE>();
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
	protected APropertyInstance createAddPi(BeanPropertyEReference<ETYPE> bean) {
		EReferencePropertyInstance cpi;
		if (bean.getTypeInstance() != null) {
			cpi = bean.getTypeInstance();
		} else {
			cpi = PropertyinstancesFactory.eINSTANCE.createEReferencePropertyInstance();
		}
		cpi.setType(ai.getType());
		return cpi;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected APropertyInstance createRemovePi(Object o) {
		if (o instanceof BeanPropertyEReference<?>) {
			BeanPropertyEReference<ETYPE> bean = (BeanPropertyEReference<ETYPE>) o;
			APropertyInstance pi = bean.getTypeInstance();
			return pi;
		}
		return null;
	}

	@Override
	public boolean addAll(int index, Collection<? extends BeanPropertyEReference<ETYPE>> c) {
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
			if (object instanceof BeanPropertyEReference<?>) {
				BeanPropertyEReference<ETYPE> bean = (BeanPropertyEReference<ETYPE>) object;
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
	public BeanPropertyEReference<ETYPE> get(int index) {
		BeanPropertyEReference<ETYPE> bean = new BeanPropertyEReference<ETYPE>();
		bean.setATypeInstance(ai.getArrayInstances().get(index));
		return bean;
	}

	@Override
	public BeanPropertyEReference<ETYPE> set(int index, BeanPropertyEReference<ETYPE> element) {
		super.set(index, element);
		BeanPropertyEReference<ETYPE> oldBean = get(index);
		ai.getArrayInstances().set(index, element.getTypeInstance());
		return oldBean;
	}

	@Override
	public void add(int index, BeanPropertyEReference<ETYPE> element) {
		super.add(index, element);
		ai.getArrayInstances().add(index, element.getTypeInstance());
	}

	@Override
	public BeanPropertyEReference<ETYPE> remove(int index) {
		super.remove(index);
		BeanPropertyEReference<ETYPE> oldBean = get(index);
		ai.getArrayInstances().remove(index);
		return oldBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(Object o) {
		if (o instanceof BeanPropertyEReference<?>) {
			BeanPropertyEReference<ETYPE> bean = (BeanPropertyEReference<ETYPE>) o;
			return ai.getArrayInstances().indexOf(bean.getTypeInstance());
		}
		return INDEX_DOES_NOT_EXIST;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int lastIndexOf(Object o) {
		if (o instanceof BeanPropertyEReference<?>) {
			BeanPropertyEReference<ETYPE> bean = (BeanPropertyEReference<ETYPE>) o;
			return ai.getArrayInstances().lastIndexOf(bean.getTypeInstance());
		}
		return INDEX_DOES_NOT_EXIST;
	}
	
	@Override
	public List<BeanPropertyEReference<ETYPE>> subList(int fromIndex, int toIndex) {
		List<BeanPropertyEReference<ETYPE>> beanList = new ArrayList<>();
		
		for (int index = fromIndex; index < toIndex; index++) {
			BeanPropertyEReference<ETYPE> bean = get(index);
			beanList.add(bean);
		}

		return beanList;
	}

}
