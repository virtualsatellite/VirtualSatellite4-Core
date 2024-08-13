/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.json;

import java.util.HashMap;
import java.util.Map;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;

/**
 * Adapter for a IUuid from/to a UUID
 */
public class IUuidAdapter extends XmlAdapter<String, IUuid> {

	private ResourceSet resourceSet;
	private IUserContext userContext;
	private Map<String, IUuid> objectMap = new HashMap<String, IUuid>();
	
	public IUuidAdapter() { }
	
	public IUuidAdapter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	public IUuidAdapter(ResourceSet resourceSet, IUserContext userContext) {
		this.resourceSet = resourceSet;
		this.userContext = userContext;
	}
	
	@Override
	public String marshal(IUuid iuuid) throws Exception {
		return iuuid.getUuid().toString();
	}

	@Override
	public IUuid unmarshal(String uuid) throws Exception {
		if (resourceSet == null) {
			throw new NullPointerException("No resource set for unmarshalling set in the adapter");
		}
		if (uuid == null) {
			throw new NullPointerException("No uuid provided");
		}
		
		// Search for the type instance with the same uuid
		EcoreUtil.getAllContents(resourceSet, true).forEachRemaining(object -> {
			if (object instanceof IUuid) {
				IUuid iuuid = (IUuid) object;
				if (iuuid.getUuid().toString().equals(uuid)) {
					objectMap.put(uuid, iuuid);
				}
			}
		});
		
		IUuid iuuid = objectMap.get(uuid);
		
		if (iuuid == null) {
			throw new IllegalArgumentException("IUuid with uuid " + uuid + " not found");
		}
		
		// Check rights
		if (!(userContext == null) && !RightsHelper.hasWritePermission(iuuid, userContext)) {
			throw new IllegalArgumentException("User " + userContext.getUserName() + " has no write permission for " + uuid);
		}
		
		return iuuid;
	}
	
}
