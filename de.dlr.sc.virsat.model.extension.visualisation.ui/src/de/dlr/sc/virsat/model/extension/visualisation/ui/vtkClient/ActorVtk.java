/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.vtkClient;

import java.util.ArrayList;
import java.util.List;

import vtk.vtkActor;

/**
 * This class is a wrapper arround the vtkActors to implement a small
 * parent-children relationship
 *
 */
public class ActorVtk extends vtkActor {
	
	private List<ActorVtk> children = new ArrayList<ActorVtk>();
	private String mId;
	
	/**
	 * Create a new Node
	 * 
	 * @param id
	 *                ID of the node
	 */
	public ActorVtk(String id) {
		super();
		mId = id;
	}

	/**
	 * get the vtk child from the list of childs
	 * 
	 * @param index
	 *                index
	 * @return children
	 */
	public ActorVtk getChild(int index) {
		return children.get(index);
	}

	/**
	 * remove a children from the subtree
	 * 
	 * @param child
	 *                child which should be disconnected
	 */
	public void disconnectChild(ActorVtk child) {
		children.remove(child);
	}

	/**
	 * Create a new ActorVtk as a child
	 * 
	 * @param id
	 *                ID of child
	 * @return new child
	 */
	public ActorVtk newChild(String id) {
		ActorVtk child = new ActorVtk(id);
		children.add(child);
		return child;
	}

	/**
	 * Get the id of this node
	 * 
	 * @return ID of this node
	 */
	public String getId() {
		return mId;
	}

	/**
	 * Get the number of children
	 * 
	 * @return number of children
	 */
	public int getNumChildren() {
		return children.size();
	}

}
