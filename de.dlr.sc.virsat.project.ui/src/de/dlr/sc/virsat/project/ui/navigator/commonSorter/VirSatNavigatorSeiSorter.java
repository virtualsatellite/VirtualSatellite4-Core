/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.commonSorter;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Sorter for the Navigator which sorts the Added SEIs by their names.
 * Does also work for Repositories
 * @author fisc_ph
 *
 */
public class VirSatNavigatorSeiSorter extends ViewerComparator {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		boolean e1Sei = e1 instanceof StructuralElementInstance;
		boolean e2Sei = e2 instanceof StructuralElementInstance;

		boolean e1Ca = e1 instanceof CategoryAssignment;
		boolean e2Ca = e2 instanceof CategoryAssignment;

		// In case two SEIs exist try to order them by their Index
		if ((e1Sei) && (e2Sei)) {
			StructuralElementInstance sei1 = (StructuralElementInstance) e1;
			StructuralElementInstance sei2 = (StructuralElementInstance) e2;
			
			int indexSei1 = -1;
			int indexSei2 = -1;

			// In case the containers are null, it is very likely that the SEIs are root Entities in a Repository
			if (sei1.eContainer() == null || sei2.eContainer() == null) {
				VirSatTransactionalEditingDomain edSei1 = VirSatEditingDomainRegistry.INSTANCE.getEd(sei1);
				VirSatTransactionalEditingDomain edSei2 = VirSatEditingDomainRegistry.INSTANCE.getEd(sei2);

				indexSei1 = edSei1.getResourceSet().getRepository().getRootEntities().indexOf(sei1);
				indexSei2 = edSei2.getResourceSet().getRepository().getRootEntities().indexOf(sei2);
			} else {
				indexSei1 = indexOfEObjectInSei(sei1);
				indexSei2 = indexOfEObjectInSei(sei2);
			}
			
			return Integer.compare(indexSei1, indexSei2);
		}
		
		// now check for CAs
		if ((e1Ca) && (e2Ca)) {
			CategoryAssignment ca1 = (CategoryAssignment) e1;
			CategoryAssignment ca2 = (CategoryAssignment) e2;

			int indexSei1 = indexOfEObjectInSei(ca1);
			int indexSei2 = indexOfEObjectInSei(ca2);
		
			return Integer.compare(indexSei1, indexSei2);
		}
		
		// In case one object is SEI, and the other isn't
		// Make sure that the SEIs come first
		if ((e1Sei) && !(e2Sei)) {
			return -1;
		} else if (!(e1Sei) && (e2Sei)) {
			return 1;
		}
		
		// In all other cases ask the general sorter
		return super.compare(viewer, e1, e2);
	}
	
	/**
	 * This method gets the index of the EObject in case it is contained somewhere
	 * @param sei The EObject to get the index of
	 * @return the index of the SEI or CA (EObject) in its containment or -1 in case there is no containment
	 */
	private int indexOfEObjectInSei(EObject sei) {
		EStructuralFeature containingFeature = sei.eContainingFeature();
		EObject sei1Container = sei.eContainer();
		if (sei1Container != null) {
			Object potentialList = sei1Container.eGet(containingFeature);
			if (potentialList instanceof List) {
				List<?> list = (List<?>) potentialList;
				return list.indexOf(sei);
			}
		}
		return -1;
	}
}
