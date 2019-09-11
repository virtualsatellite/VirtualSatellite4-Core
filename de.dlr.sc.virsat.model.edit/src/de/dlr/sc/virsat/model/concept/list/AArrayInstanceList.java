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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;

/**
 * the abstract class for array instance lists implements the interface bean list
 * @author leps_je
 *
 * @param <TYPE>
 */
public abstract class AArrayInstanceList<TYPE> implements IBeanList<TYPE> {

	protected ArrayInstance ai;
	protected IArrayModifier  arrayModifier;

	public static final int INDEX_DOES_NOT_EXIST = -1;
	
	/**
	 * the private default constructor of AArrayInstanceList
	 */
	public AArrayInstanceList() {
	}
	
	/**
	 * the constructor of the AArrayInstanceList put the ArrayInstance and there arrayModifier type
	 * @param ai the arrayInstance
	 */
	public AArrayInstanceList(ArrayInstance ai) {
		this.ai = ai;
		arrayModifier = ((AProperty) ai.getType()).getArrayModifier();
	}
	
	/**
	 * this method returns the arrayInstance
	 * @return the Instance of the array
	 */
	public ArrayInstance getArrayInstance() {
		return ai;
	}

	/**
	 * with this method, the arrayModifier and type will be set
	 * @param ai the array instance 
	 */
	public void setArrayInstance(ArrayInstance ai) {
		this.ai = ai;
		arrayModifier = ((AProperty) ai.getType()).getArrayModifier();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int size() {
		return ai.getArrayInstances().size();
	}

	@Override
	public boolean isEmpty() {
		return ai.getArrayInstances().isEmpty();
	}
	
	@Override
	public Iterator<TYPE> iterator() {
		return new ArrayInstanceListIterator(this);
	}
	
	/**
	 * this method checks the type of the ArrayModifier, 
	 * if the ArrayModifier is static, the array can not be changed
	 */
	private void checkAndhrowStaticAccessDenied() {
		if (arrayModifier instanceof StaticArrayModifier) {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * Abstract method that provides the property instance to be added to the list for a given bean
	 * @param e the bean object for which to get the actual property instance
	 * @return the property instance that represents the given bean
	 */
	protected abstract APropertyInstance createAddPi(TYPE e);

	@Override
	public boolean add(TYPE e) {
		checkAndhrowStaticAccessDenied();
		APropertyInstance pi = createAddPi(e);
		return ai.getArrayInstances().add(pi);
	}
	
	@Override
	public Command add(EditingDomain ed, TYPE e) {
		APropertyInstance pi = createAddPi(e);
		Command command = AddCommand.create(ed, ai, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, pi);
		return command;
	}
	
	/**
	 * Abstract method that provides the property instance to be removed from the list for a given bean
	 * @param o the bean object for which to get the actual property instance
	 * @return the property instance that represents the given bean
	 */
	protected abstract APropertyInstance createRemovePi(Object o);

	@Override
	public boolean remove(Object o) {
		checkAndhrowStaticAccessDenied();
		APropertyInstance pi = createRemovePi(o);
		return ai.getArrayInstances().remove(pi);
	}

	@Override
	public Command remove(EditingDomain ed, Object o) {
		APropertyInstance pi = createRemovePi(o);
		if (ai.getArrayInstances().contains(pi)) {
			Command command = RemoveCommand.create(ed, ai, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, pi);
			return command;
		} else {
			return UnexecutableCommand.INSTANCE;
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends TYPE> c) {
		checkAndhrowStaticAccessDenied();
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		checkAndhrowStaticAccessDenied();
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		checkAndhrowStaticAccessDenied();
		return false;
	}

	@Override
	public void clear() {
		checkAndhrowStaticAccessDenied();
		ai.getArrayInstances().clear();
	}

	@Override
	public TYPE set(int index, TYPE element) {
		checkAndhrowStaticAccessDenied();
		return null;
	}

	@Override
	public void add(int index, TYPE element) {
		checkAndhrowStaticAccessDenied();
	}
	
	@Override
	public boolean addAll(Collection<? extends TYPE> c) {
		checkAndhrowStaticAccessDenied();
		boolean listChanged = false;
		for (TYPE bean : c) {
			listChanged |= add(bean);
		}
		return listChanged;
	}

	@Override
	public TYPE remove(int index) {
		checkAndhrowStaticAccessDenied();
		return null;
	}

	/**
	 * a concrete inner class to iterate on array lists
	 * @author leps_je
	 *
	 */
	protected class ArrayInstanceListIterator implements ListIterator<TYPE> {

		/**
		 * the constructor set the list 
		 * @param list the iterated list
		 */
		public ArrayInstanceListIterator(List<TYPE> list) {
			this.list = list;
		}

		/**
		 * this constructor set the list and the start index of the list
		 * @param list will be iterated on
		 * @param startIndex the start index of this list
		 */
		public ArrayInstanceListIterator(List<TYPE> list, int startIndex) {
			this.list = list;
			this.currIndex = startIndex;
		}

		private List<TYPE> list;
		private int currIndex = 0;
		
		@Override
		public boolean hasNext() {
			return currIndex < size();
		}

		private TYPE lastReturnedElement;
		
		@Override
		public TYPE next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			lastReturnedElement = get(currIndex++);
			return lastReturnedElement;
		}

		@Override
		public boolean hasPrevious() {
			return currIndex > 0;
		}

		@Override
		public TYPE previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			lastReturnedElement = get(--currIndex);
			return lastReturnedElement;
		}

		@Override
		public int nextIndex() {
			return currIndex;
		}

		@Override
		public int previousIndex() {
			return currIndex - 1;
		}

		@Override
		public void remove() {
			if (lastReturnedElement == null) {
				throw new IllegalStateException();
			}
			list.remove(--currIndex);
			lastReturnedElement = null;
		}

		@Override
		public void set(TYPE e) {
			if (lastReturnedElement == null) {
				throw new IllegalStateException();
			}
			list.set(--currIndex, e);
			lastReturnedElement = null;
		}

		@Override
		public void add(TYPE e) {
			if (lastReturnedElement == null) {
				throw new IllegalStateException();
			}
			list.add(currIndex++, e);
			lastReturnedElement = null;
		}
	}
	
	@Override
	public ListIterator<TYPE> listIterator() {
		return new ArrayInstanceListIterator(this);
	}

	@Override
	public ListIterator<TYPE> listIterator(int index) {
		return new ArrayInstanceListIterator(this, index);
	}
}
