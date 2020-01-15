/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources.dmf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import de.dlr.sc.virsat.model.dvlm.dmf.DObject;
import de.dlr.sc.virsat.model.dvlm.dmf.DObjectContainer;
import de.dlr.sc.virsat.model.dvlm.dmf.DmfFactory;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * Provides a DMF view on a DVLM resource.
 * 
 * @author muel_s8
 *
 */

public class DmfResource extends XMIResourceImpl {

	public static final String DMF_FILENAME_EXTENSION = "dmf";

	private VirSatTransactionalEditingDomain virSatEd;
	private VirSatResourceSet virSatResourceSet;
	private Resource virSatDvlmResource;

	@Override
	public void setURI(URI uri) {
		super.setURI(uri);

		String dvlmUriString = uri.toString().replaceAll("\\." + DMF_FILENAME_EXTENSION, "");
		URI dvlmUri = URI.createURI(dvlmUriString);

		IWorkspaceRoot iWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile dvlmFile = iWorkspaceRoot.getFile(new Path(uri.toPlatformString(true)));

		IProject virSatProject = dvlmFile.getProject();

		virSatEd = VirSatEditingDomainRegistry.INSTANCE.getEd(virSatProject);
		virSatResourceSet = virSatEd.getResourceSet();
		virSatDvlmResource = virSatResourceSet.getResource(dvlmUri, true);
	}

	@Override
	public void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		saveDMFResource(options);
	}

	@Override
	protected void saveOnlyIfChangedWithMemoryBuffer(Map<?, ?> options) throws IOException {
		saveDMFResource(options);
	}
	
	/**
	 * Save the DMF resource by copying its value to the underling DVLM resource
	 * @param options Save options 
	 * @throws IOException If underling resource cant be saved
	 */
	protected void saveDMFResource(Map<?, ?> options) throws IOException {
		// Load the DVLM Resource in case it did not yet happen.
		if (virSatDvlmResource.getContents().isEmpty()) {
			virSatDvlmResource.load(Collections.EMPTY_MAP);
		}
		
		// Start saving the Resource
		StructuralElementInstance sei = (StructuralElementInstance) virSatDvlmResource.getContents().get(0);
		DObjectContainer dObjectContainer = (DObjectContainer) super.getContents().get(0);
		DmfResourceSaveCommand dmfResourceSaveCommand = new DmfResourceSaveCommand(virSatEd, sei, dObjectContainer.getObjects());

		// Use NoUndo to suppress notification of this infrastructure-related
		// operation
		virSatEd.getVirSatCommandStack().executeNoUndo(dmfResourceSaveCommand);
		virSatDvlmResource.save(options);
	}

	//If the element to be referenced is of type Iuuid, then use the uuid as ID
	@Override
	public String getID(EObject eObject) {
		if (eObject instanceof IUuid) {
			return ((IUuid) eObject).getUuid().toString();
		} else {
			return super.getID(eObject);
		}
	}
	
	@Override
	public void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		DObjectContainer dObjectContainer = DmfFactory.eINSTANCE.createDObjectContainer();
		super.getContents().add(dObjectContainer);

		StructuralElementInstance sei = (StructuralElementInstance) virSatDvlmResource.getContents().get(0);
		List<DObject> dObjects = new DmfResourceLoader().loadDmfResource(sei);

		dObjectContainer.getObjects().addAll(dObjects);
	}
	

	@Override
	public void load(Map<?, ?> options) throws IOException {
		InputStream inputStream = null;
		if (!isLoaded) {
			Notification notification = setLoaded(true);
			isLoading = true;

			if (errors != null) {
				errors.clear();
			}

			if (warnings != null) {
				warnings.clear();
			}

			try {
				options = mergeMaps(options, defaultLoadOptions);
				URIConverter.Cipher cipher = options != null ? (URIConverter.Cipher) options.get(Resource.OPTION_CIPHER)
						: null;

				if (cipher != null) {
					try {
						inputStream = cipher.decrypt(inputStream);
					} catch (Exception e) {
						throw new IOWrappedException(e);
					}
				}

				doLoad(inputStream, options);

				if (cipher != null) {
					try {
						cipher.finish(inputStream);
					} catch (Exception e) {
						throw new IOWrappedException(e);
					}
				}
			} finally {
				isLoading = false;

				if (notification != null) {
					eNotify(notification);
				}

				setModified(false);
			}
		}
	}

	/**
	 * Getter for the DVLM Resource. Necessary to access the virsat data model from a DObject
	 * @return the DVLM resource
	 */
	public Resource getVirSatDvlmResource() {
		return virSatDvlmResource;
	}
	
	
	


	
}
