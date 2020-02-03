/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain.commands;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;


/**
 * This class implements an extension to the AdapterFactoryEditingDomain ClipBoard.
 * This class can handle the state of the ClipBoard as well. it is implemented as singleton.
 * The registration of editing domains happens by the EditingDomainRegistry of VirSat.
 * @author fisc_ph
 *
 */
public class VirSatEditingDomainClipBoard {

	public static final VirSatEditingDomainClipBoard INSTANCE = new VirSatEditingDomainClipBoard();

	/**
	 * Private Constructor because it is a singleton. Initializes the map of ClipBoard states
	 */
	private VirSatEditingDomainClipBoard() {
		editingDomainClipboardStates = new HashMap<>();
	}
	
	Map<AdapterFactoryEditingDomain, ClipboardState> editingDomainClipboardStates;
	
	/**
	 * Enums describing the current State of the CLipboard
	 * The ClipBoard is used for Cut Copy and Paste Action
	 */
	public enum ClipboardState {
		CUT, COPY, EMPTY
	}
	
	/**
	 * Call this method to register a new EditingDomain and set its state to empty
	 * @param ed the EditingDomain to be registered
	 */
	public void registerEd(AdapterFactoryEditingDomain ed) {
		editingDomainClipboardStates.put(ed, ClipboardState.EMPTY);
	}
	
	/**
	 * Method to de-register an editing domain
	 * @param ed the ED that should be removed from control of this ClipBoard
	 */
	public void deregisterEd(AdapterFactoryEditingDomain ed) {
		editingDomainClipboardStates.remove(ed);
	}
	
	
	/**
	 * Method to set a new CLipBoard State
	 * @param ed the EditingDomain for which to set a new state
	 * @param newState The new State to be set
	 */
	protected void setClipboardState(AdapterFactoryEditingDomain ed, ClipboardState newState) {
		editingDomainClipboardStates.put(ed, newState);		
	}
	
	/**
	 * Method to retrieve the current CLipboardd State
	 * @param ed the EditingDomain for which to get the current State
	 * @return the Current State of the ClipBoard
	 */
	public ClipboardState getClipboardState(AdapterFactoryEditingDomain ed) {
		return editingDomainClipboardStates.get(ed);
	}
	
	/**
	 * Method to get the current Collection of objects in the ClipBoard
	 * @param ed The editing domain for which to get the Objects
	 * @return the current collection of objects in the ClipBoard
	 */
	public Collection<Object> getCollection(AdapterFactoryEditingDomain ed) {
		return ed.getClipboard();
	}
	
	/**
	 * Call this method to flush and clear the ClipBoard
	 * @param ed The editing domain for which to clear the ClipBoard
	 */
	public void flushClipboard(AdapterFactoryEditingDomain ed) {
		ed.setClipboard(null);
		setClipboardState(ed, ClipboardState.EMPTY);
	}
	
	/**
	 * This method sets the given content as copy content into the ClipBoard
	 * @param ed the editing domain for which to set the content
	 * @param collection the collection of objects to be stored in the ClipBoard
	 */
	@SuppressWarnings("unchecked")
	public void copyClipboard(AdapterFactoryEditingDomain ed, Collection<?> collection) {
		ed.setClipboard((Collection<Object>) collection);
		setClipboardState(ed, ClipboardState.COPY);
	}

	/**
	 * This method sets the given content as cut content into the ClipBoard
	 * @param ed the editing domain for which to set the content
	 * @param collection the collection of objects to be stored in the ClipBoard
	 */
	@SuppressWarnings("unchecked")
	public void cutClipboard(AdapterFactoryEditingDomain ed, Collection<?> collection) {
		ed.setClipboard((Collection<Object>) collection);
		setClipboardState(ed, ClipboardState.CUT);
	}

	public void clear() {
		editingDomainClipboardStates.clear();
	}
}
