/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ecore.xmi.impl;

import java.util.HashMap;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;

/**
 * Resource to be used in EMF ResourceSets which is explicitly developed to deal with
 * the FullQualifiedName of the Concepts which is a derived feature but an ID as well. 
 * The EDAPT Migrator is loading the model using dynamic EMF which does not understand
 * the code behind the FullQualifiedName. accordingly the Resource has to deal with it.
 * to resolve the IDs
 * @author fisc_ph
 *
 */
public class DvlmXMIResourceImpl extends XMIResourceImpl implements Resource {

	/**
	 * Constructor for XMIResourceImpl.
	 * 
	 * @param uri the Uri to be used when creating the resource
	 */
	public DvlmXMIResourceImpl(URI uri) {
		super(uri);
		setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
	}

	private static final EAttribute E_ATTRIBUTE_FQN = GeneralPackage.Literals.IQUALIFIED_NAME__FULL_QUALIFIED_NAME;
	private static final String FQN_E_ATTRIBUTE_FQN = VirSatEcoreUtil.getFullQualifiedAttributeName(E_ATTRIBUTE_FQN);
	
	@Override
	protected EObject getEObjectByID(String id) {
		EObject returnObject = super.getEObjectByID(id);
		if (returnObject != null) {
			// There exists a cached object for this id
			// If the object is also contained in this resource,
			// then we can simply hand it back.
			Resource containedResource = returnObject.eResource();
			boolean isLoading = this.isLoading();
			if (isLoading || containedResource == this) {
				return returnObject;
			} else {
				// Otherwise, remove it from the cache and return null
				getIntrinsicIDToEObjectMap().remove(id);
				return null;
			}
		}

		// It is possible, e.g. when migrating, that DynamicEObjects reference to another
		// instance of the Ecore Model. Therefore even if an EObject and and DynamicEobject are
		// of the same EClass, they will not be handled as such. Therefore we compare 
		for (TreeIterator<EObject> i = getAllProperContents(getContents()); i.hasNext();) {			
			EObject eObject = i.next();
			// Only handle dynamic EObjects. Static EMF is handled as usual.
			if (eObject instanceof DynamicEObjectImpl) {
				EClass eClass = eObject.eClass();
				EAttribute eIDAttribute = eClass.getEIDAttribute();

				// if there is no ID attribute nothing more needs to be done
				// If the ID attribute is the same as from the interface IQualifiedName
				// We can use it to retrieve the actual ID of the Object.
				if (eIDAttribute != null) {
					String fqnEAttributeCurrent = VirSatEcoreUtil.getFullQualifiedAttributeName(eIDAttribute);
					boolean isFqnEattributeFqn = fqnEAttributeCurrent.equals(FQN_E_ATTRIBUTE_FQN);

					// In case the object is of type IQualifedName then we have to act as if we
					// try to get the FQN using the static EMF model
					if (isFqnEattributeFqn) {
						String eObjectId = ActiveConceptHelper.getFullQualifiedId(eObject);
						if (id.equals(eObjectId)) {
							getIntrinsicIDToEObjectMap().put(id, eObject);
							return eObject;
						}
					}
				}
			}
		}
		
		return returnObject;
	}
	
	@Override
	public String getURIFragment(EObject eObject) {
		if (eObject instanceof DynamicEObjectImpl) {
			if (ActiveConceptHelper.isSafeAssignableFrom(GeneralPackage.Literals.IQUALIFIED_NAME, eObject)) {
				String eObjectFqnId = ActiveConceptHelper.getFullQualifiedId(eObject);
				return eObjectFqnId;
			}
		}
	
		return super.getURIFragment(eObject);
	}
	
	@Override
	protected void doUnload() {
		getIntrinsicIDToEObjectMap().clear();
		super.doUnload();
	}
}
