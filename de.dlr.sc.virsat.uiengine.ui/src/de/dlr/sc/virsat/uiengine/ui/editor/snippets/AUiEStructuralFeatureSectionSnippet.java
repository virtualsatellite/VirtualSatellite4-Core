/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets;

import java.util.Collections;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;

import de.dlr.sc.virsat.build.marker.util.VirSatValidationMarkerHelper;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;

/**
 * These Snippets are are based on eStructuralFeatures of the modelObject which always has an Uuid
 * @author lobe_el
 *
 */
public abstract class AUiEStructuralFeatureSectionSnippet extends AUiSectionSnippet {

	protected EStructuralFeature eStructuralFeature;
	
	/**
	 * Constructor setting the EStructuralFeature
	 * @param eStructuralFeature The ESF corresponding to the snippet
	 */
	public AUiEStructuralFeatureSectionSnippet(EStructuralFeature eStructuralFeature) {
		this.eStructuralFeature = eStructuralFeature;
	}
	
	/**
	 * Getter method
	 * @return the corresponding EStructuralFeature
	 */
	public EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}
	
	@Override
	protected QualifiedName getSectionExpansionStateKey() {
		return new QualifiedName(UI_SECTION_SNIPPET_ID + "." + eStructuralFeature.getName(), getSectionModelUuidString());
	};
	
	@Override
	public boolean isActive(EObject model) {
		// class comparism instead of instanceof
		EClass esfClass = eStructuralFeature.getEContainingClass();
		EClass modelClass = model.eClass();
		return esfClass.isSuperTypeOf(modelClass);
	}
	
	@Override
	public void setSelection(ISelection selection) {
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
	}

	@Override
	public ISelection getSelection() {
		return null;
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
	}
	
	@Override
	protected Set<EObject> getPossiblyMarkedObjects() {
		return Collections.singleton(model);
	}
	
	@Override
	public boolean hasMarkers() {
		if (super.hasMarkers()) {
			Set<IMarkerHelper> mhs = getMarkerHelpers();
			for (EObject obj : getPossiblyMarkedObjects()) {
				for (IMarkerHelper mh : mhs) {
					if (mh instanceof VirSatValidationMarkerHelper) {
						// For ValidationMarkers we need to differ between markers on EStructuralFeatures or different markers
						Set<IMarker> emfMarkers = ((VirSatValidationMarkerHelper) mh).getEMFValidationMarkers(obj, eStructuralFeature);
						Set<IMarker> notEmfMarkers = ((VirSatValidationMarkerHelper) mh).getNotEMFValidationMarkers(obj);
						if (!emfMarkers.isEmpty() || !notEmfMarkers.isEmpty()) {
							return true;
						}
					} else {
						if (!mh.getAllMarkers(obj).isEmpty()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}
