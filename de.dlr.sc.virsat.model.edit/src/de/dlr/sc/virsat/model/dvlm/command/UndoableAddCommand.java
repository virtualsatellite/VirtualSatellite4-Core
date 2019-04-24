/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.command;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Modified EMF ADD Command that does some smarter work when 
 * executing the UNDO functionality. Command does not collide
 * with objects created out of the command stack Anymore
 * @author fisc_ph
 *
 */
public class UndoableAddCommand extends AddCommand {

	/**
	 * Constructor for the Add Command
	 * @param domain The Editing Domain on which to act
	 * @param owner The owner where to add the objects
	 * @param feature The feature of the owner where to add the new objects
	 * @param collection a collection of objects to be added
	 * @param index an index where to start to add them in the feature
	 */
	public UndoableAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection<?> collection, int index) {
		super(domain, owner, feature, collection, index);
	}

	@Override
	public void doUndo() {
		//Remove the collection by the objects
	    ownerList.removeAll(collection);    
	  
	    updateEMap(owner, feature);
	    affectedObjects = owner == null ? Collections.EMPTY_SET : Collections.singleton(owner);
	}
}
