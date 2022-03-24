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
	}

	@Override
	protected EObject getEObjectByID(String id) {
		EObject returnObject = getIDToEObjectMap().get(id);
		if (returnObject != null) {
			// There exists a cached object for this id
			return returnObject;
		}
		
		final EAttribute eAttributeFqn = GeneralPackage.Literals.IQUALIFIED_NAME__FULL_QUALIFIED_NAME;
		final String fqnEAttributeFqn = VirSatEcoreUtil.getFullQualifiedAttributeName(eAttributeFqn);
		
		for (TreeIterator<EObject> i = getAllProperContents(getContents()); i.hasNext();) {			
			EObject eObject = i.next();
			// Only handle dynamic EObjects. Static EMF is handled as usual.
			if (eObject instanceof DynamicEObjectImpl) {
				EClass eClass = eObject.eClass();
				EAttribute eIDAttribute = eClass.getEIDAttribute();

				// if there is no ID attribute nothing more needs to be done
				if (eIDAttribute != null) {
					String fqnEAttributeCurrent = VirSatEcoreUtil.getFullQualifiedAttributeName(eIDAttribute);
					boolean isFqnEattributeFqn = fqnEAttributeCurrent.equals(fqnEAttributeFqn);

					// In case the object is of type IQualifedName then we have to act as if we
					// try to get the FQN using the static EMF model
					if (isFqnEattributeFqn) {
						String eObjectId = ActiveConceptHelper.getFullQualifiedId(eObject);
						if (id.equals(eObjectId)) {
							setID(eObject, id);
							return eObject;
						}
					}
				}
			}
		}
		
		// All other calls are forwarded to the standard XMIResource functionality
		returnObject = super.getEObjectByID(id);
		setID(returnObject, id);
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
}
