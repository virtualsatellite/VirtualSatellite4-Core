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

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;

/**
 * interface to a Bean that will represent a list
 * @author leps_je
 *
 * @param <TYPE>
 */
public interface IBeanList<TYPE> extends List<TYPE> {

	/**
	 * Method to create an EMF command to add an element to the list 
	 * @param ed the editing domain to be used for creating the command
	 * @param e the element to be added
	 * @return the command for adding the element
	 */
	Command add(EditingDomain ed, TYPE e);

//	Command add(EditingDomain ed,int index, TYPE element);
//
	/**
	 * Method to create an EMF command to remove an element from this list
	 * @param ed the editing domain  to be used for creating the command
	 * @param o the object to be removed from the list
	 * @return the command that will finally remove the object
	 */
	Command remove(EditingDomain ed, Object o);

//	Command addAll(EditingDomain ed,Collection<? extends TYPE> c);
//
//	Command addAll(EditingDomain ed,int index, Collection<? extends TYPE> c);
//
//	Command remove(EditingDomain ed,int index);
//
//	Command removeAll(EditingDomain ed,Collection<?> c);
//
//	Command retainAll(EditingDomain ed,Collection<?> c);
//	
//	Command clear(EditingDomain ed);
//
//	Command set(EditingDomain ed,int index, TYPE element);

	/**
	 * this method returns the array instance of the bean list
	 * @return the array instance of the bean list
	 */
	ArrayInstance getArrayInstance();

	/**
	 * this method set the array instance on the bean list
	 * @param ai the array instance
	 */
	void setArrayInstance(ArrayInstance ai);
	
}
