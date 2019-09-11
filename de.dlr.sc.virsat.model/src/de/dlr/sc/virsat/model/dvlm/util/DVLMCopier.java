/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Copier that first sets types of the copied objects to pass ApplicableFor check
 */
public class DVLMCopier extends EcoreUtil.Copier {
	
	private static final long serialVersionUID = 4952309684167129102L;
	
	// Per default we copy super TIs
	private boolean copySuperTis = true;
	
	// Per default we don't want to copy UUIDS
	private boolean copyUuids = false;

	/**
	 * Set the flag that determines whether super TIs will be copied or not.
	 * @param copySuperTis the new flag value
	 * @return the current Copier
	 */
	public DVLMCopier setCopySuperTis(boolean copySuperTis) {
		this.copySuperTis  = copySuperTis;
		return this;
	}
	
	/**
	 * Set the flag that determines whether super TIs will be copied or not.
	 * @param copyUuids the new flag value
	 * @return the current Copier
	 */
	public DVLMCopier setCopyUuids(boolean copyUuids) {
		this.copyUuids  = copyUuids;
		return this;
	}
	
	
	@Override
	protected EObject createCopy(EObject eObject) {
		EObject copiedEObject = super.createCopy(eObject);
		
		if (eObject instanceof StructuralElementInstance) {
			StructuralElementInstance sourceSei = (StructuralElementInstance) eObject;
			StructuralElementInstance targetSei = (StructuralElementInstance) copiedEObject;
			StructuralElement sourceType = sourceSei.getType();
			targetSei.setType(sourceType);
		} else if (eObject instanceof CategoryAssignment) {
			CategoryAssignment sourceCa = (CategoryAssignment) eObject;
			CategoryAssignment targetCa = (CategoryAssignment) copiedEObject;
			ATypeDefinition sourceCategory = sourceCa.getType();
			targetCa.setType(sourceCategory);
		}
		
		// Add Code to set non inheritance flag of copied TA around here
		if (copiedEObject instanceof IInheritanceLink) {
			IInheritanceLink iLink = (IInheritanceLink) copiedEObject;
			iLink.setIsInherited(false);
		}
		
		return copiedEObject;
	}
	
	@Override
	protected void copyAttribute(EAttribute eAttribute, EObject eObject, EObject copyEObject) {
		boolean skipCopyUuid = (eAttribute == GeneralPackage.Literals.IUUID__UUID) && !copyUuids;
		
		//skip overwriting of UUID value so that the copy has another UUID
		if (!skipCopyUuid && eAttribute != InheritancePackage.Literals.IINHERITANCE_LINK__IS_INHERITED) {
			super.copyAttribute(eAttribute, eObject, copyEObject);
		}
	}
	
	@Override
	protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject) {
		if (!copySuperTis) {
			// skip copying of super TIs
			if (eReference != InheritancePackage.Literals.IINHERITANCE_LINK__SUPER_TIS) {
				super.copyReference(eReference, eObject, copyEObject);
			}
		} else {
			super.copyReference(eReference, eObject, copyEObject);
		}
	}
	
	/**
	 * Makes a copy of a given source object will hand back objects with new UUIDs
	 * @param source EObject to copy
	 * @param <T> Generic Type that has to extend EObject
	 * @return copy
	 */
	public static <T extends EObject> T makeCopy(EObject source) {
		DVLMCopier copier = new DVLMCopier();
		return makeCopy(source, copier);
	}
	
	/**
	 * Makes a copy of a given source object and keeps UUIDs
	 * @param source EObject to copy
	 * @param <T> Generic Type that has to extend EObject
	 * @return copy
	 */
	public static <T extends EObject> T makeCopyKeepUuids(EObject source) {
		DVLMCopier copier = new DVLMCopier().setCopyUuids(true);
		return makeCopy(source, copier);
	}

	/**
	 * Static call to create a copy of an object
	 * @param source The source object to be copied
	 * @param copier the copier to be used for that copy
	 * @param <T> Generic Type that has to extend EObject
	 * @return the copied object
	 */
	@SuppressWarnings("unchecked")
	private static <T extends EObject> T makeCopy(EObject source, DVLMCopier copier) {
		T result = (T) copier.copy(source);
		copier.copyReferences();
		
		return result;
	}

}
