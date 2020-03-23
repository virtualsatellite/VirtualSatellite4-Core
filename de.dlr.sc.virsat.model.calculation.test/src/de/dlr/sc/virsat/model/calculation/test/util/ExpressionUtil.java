/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.test.util;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import de.dlr.sc.virsat.model.calculation.EquationDSLStandaloneSetup;
import de.dlr.sc.virsat.model.dvlm.calculation.AExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;

/**
 * Utility class for creating expressions and equatiosn for the purpose of testing calculations
 * @author muel_s8
 *
 */

public class ExpressionUtil {
	
	/**
	 * Private constructor.
	 */
	
	private ExpressionUtil() {
		
	}
	
	/**
	 * helper method  to get access to expressions
	 * @param expressionString the Expression String
	 * @return The first Expression of Type AExpression
	 */
	public static AExpression getFirstExpressionFrom(String expressionString) {			
		return getAllEquationsFrom(expressionString).get(0).getExpression();
	}

	/**
	 * Method to get all expressions from a resourceSet
	 * @param resourceSet the resourceSet in which to load the expression
	 * @param resource the resource to be loaded into that resourceSet
	 * @param equationString the equation string used for loading
	 * @return A List of equations taken from the model
	 */
	public static List<Equation> getAllEquationsFrom(ResourceSet resourceSet, Resource resource, String equationString) {
		InputStream in = new ByteArrayInputStream(equationString.getBytes());
		try {
			resource.unload();
			resource.load(in, resourceSet.getLoadOptions());
		} catch (IOException e) {
			fail("Could not load equation....");
		}
		EquationSection model = (EquationSection) resource.getContents().get(0);

		return new ArrayList<Equation>(model.getEquations());
	}

	/**
	 * Wrapper method that calls a resourceSet and resource to extract expressions from a given string
	 * @param equationString the string representing a set of equations
	 * @return a List of Equations
	 */
	public static List<Equation> getAllEquationsFrom(String equationString) {
		Injector injector = new EquationDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		Resource resource = resourceSet.createResource(URI.createFileURI("VirsatTest.dvlmeq"));

		return getAllEquationsFrom(resourceSet, resource, equationString);
	} 
}
