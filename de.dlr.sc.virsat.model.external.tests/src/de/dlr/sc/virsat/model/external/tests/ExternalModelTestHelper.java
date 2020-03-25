/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.external.tests;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class ExternalModelTestHelper {
	
	protected EPackage testExternalPackage;
	protected ResourceSet resourceSet;
	private static final String PLATFORM_MODEL_PATH = "/de.dlr.sc.virsat.model.external.tests/model/ExternalModel.ecore";
	private static final String PLATFORM_INSTANCE_MODEL_PATH = "/de.dlr.sc.virsat.model.external.tests/instance/InstanceModel.etests";
	
	public EPackage loadExternalPackage() {
		resourceSet = new ResourceSetImpl();
		URI metamodelURI = URI.createPlatformPluginURI(PLATFORM_MODEL_PATH, true);
		Resource metamodelResource = resourceSet.getResource(metamodelURI, true);
		testExternalPackage = (EPackage) metamodelResource.getContents().get(0);
		return testExternalPackage;
	}
	
	public ExternalTestType loadExternalTypeInstance() {
		resourceSet = new ResourceSetImpl();
		URI modelURI = URI.createPlatformPluginURI(PLATFORM_INSTANCE_MODEL_PATH, true);
		Resource modelResource = resourceSet.getResource(modelURI, true);
		return (ExternalTestType) modelResource.getContents().get(0);
	}
	
	public ResourceSet getResourceSet() {
		return resourceSet;
	}

}
