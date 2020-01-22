/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.compute;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.After;
import org.junit.Before;

import com.google.inject.Injector;

import de.dlr.sc.virsat.model.calculation.EquationDSLStandaloneSetup;
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionVirSatAwareXtextResourceSet;
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * Abstract base class that needs to test something involved with equations.
 * @author muel_s8
 *
 */

public class AEquationTest {
	
	protected static final double EPSILON = 0.0001;
	protected static final String CALC_STRING = "Calc: ";
	
	protected EquationSectionVirSatAwareXtextResourceSet esResourceSet;
	protected EquationSectionXtextResource esResource;
	protected List<EObject> contents;
	
	@Before 
	public void setUp() throws Exception {
		contents = new ArrayList<>();
		UserRegistry.getInstance().setSuperUser(true);
	}
	
	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	/**
	 * connect ResourceSets and CrossReference so that the Xtext links to ValuePropertyInstances can be resolved.
	 */
	protected void createResources() {
		Injector injector = new EquationDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
		esResourceSet = (EquationSectionVirSatAwareXtextResourceSet) injector.getInstance(EquationSectionVirSatAwareXtextResourceSet.class);
		esResourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		esResource = (EquationSectionXtextResource) esResourceSet.createResource(URI.createFileURI("VirsatTestAwesomness.dvlmeq"));

		ResourceSet resSetVirSat = new ResourceSetImpl();
		Resource resourceVirSat = new XMIResourceImpl();
		resourceVirSat.getContents().addAll(contents);
		resSetVirSat.getResources().add(resourceVirSat);

		esResourceSet.addReferencedResourceSet(resSetVirSat);
		esResource.setEquationSectionContainerResource(resourceVirSat, null);
	}
	
}
