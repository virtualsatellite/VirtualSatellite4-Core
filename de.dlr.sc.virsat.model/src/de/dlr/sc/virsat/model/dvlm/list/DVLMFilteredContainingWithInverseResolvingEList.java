/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.list;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;

import de.dlr.sc.virsat.model.dvlm.util.ADVLMExtendedModelCapabilityCheck;
import de.dlr.sc.virsat.model.dvlm.util.DVLMApplicableForCheck;

/**
 * This class is used by the EMF Code generator to replace the standard Containment EList of the StructralElementinstance
 * with this one. This list offers additional functionality to check for the "ApplicableFor" paradigm that we use in the
 * DVLM Data Model.
 * 
 * @author fisc_ph
 *
 * @param <DVLM_OBJECT> The Object Type to specialize this List.
 */
public class DVLMFilteredContainingWithInverseResolvingEList<DVLM_OBJECT> extends EObjectContainmentWithInverseEList.Resolving<DVLM_OBJECT> implements EList<DVLM_OBJECT> {

	private static final long serialVersionUID = 465480109333148691L;

	private ADVLMExtendedModelCapabilityCheck applicableForCheck;
	
	
	
	/**
	 * Constructor which is compatible to the EMF EList Constructor as they are used in the places where this one should substitude 
	 * @param dataClass Not sure what the dataCloass is
	 * @param owner The owner of this list e.g. the StructuralElementInstance
	 * @param featureID The feature id telling if the list is operating on relations or CatgeoryAssignments etc.
	 * @param inverseFeatureID The feature id telling the inverse the list is working on
	 */
	public DVLMFilteredContainingWithInverseResolvingEList(Class<?> dataClass, InternalEObject owner, int featureID, int inverseFeatureID) {
		super(dataClass, owner, featureID, inverseFeatureID);
		applicableForCheck = new DVLMApplicableForCheck(owner, true);
	}
	
	@Override
	public boolean add(DVLM_OBJECT object) {
		if (applicableForCheck.isValidObject(object)) {
			return super.add(object);
		}
		return false;
	}
	
	@Override
	public void add(int index, DVLM_OBJECT object) {
		if (applicableForCheck.isValidObject(object)) {
			super.add(index, object);
		}
	}
	
	@Override
	public boolean addAll(Collection<? extends DVLM_OBJECT> collection) {
		if (applicableForCheck.isValidObjectCollection(collection)) {
			return super.addAll(collection);
		}
		return false;
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends DVLM_OBJECT> collection) {
		if (applicableForCheck.isValidObjectCollection(collection)) {
			return super.addAll(index, collection);
		}
		return false;
	}
	
	@Override
	public DVLM_OBJECT set(int index, DVLM_OBJECT object) {
		if (applicableForCheck.isValidObject(object)) {
			return super.set(index, object);
		}
		return object;
	}
}
