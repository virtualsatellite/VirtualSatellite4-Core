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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import org.eclipse.core.internal.events.ResourceDelta;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;
import org.xml.sax.SAXParseException;

import de.dlr.sc.virsat.model.calculation.marker.VirSatEquationMarkerHelper;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * Tests the incremental equation builder
 * @author muel_s8
 *
 */

@SuppressWarnings("restriction")
public class IncrementalEquationBuilderTest extends AEquationBuilderTest {

	// Incremental Equation builder that overrides some specific methods
	// to simplify test setup.
	class TestIncrementalEquationBuilder extends IncrementalEquationBuilder {
		@Override
		protected IProject getVirSatProject() {
			return project;
		}
		
		@Override
		protected VirSatResourceSet getResourceSet() {
			return resSet;
		}
		
		@Override
		public IUserContext getUserContext() {
			return UserRegistry.getInstance(); 
		}
	}
	
	@Test
	public void testIncrementalBuild() {
		IncrementalEquationBuilder builder = new TestIncrementalEquationBuilder();
		
		assertNull("Value is initially null", result.getResultText());
		
		IPath pathSeiSc = fileSc.getFullPath();
		IResourceDelta delta = new ResourceDelta(pathSeiSc, null) {
			@Override
			public int getKind() {
				return CHANGED;
			}
			
			@Override
			public IResource getResource() {
				return fileSc;
			}
			
			@Override
			public void accept(IResourceDeltaVisitor visitor, int memberFlags) throws CoreException {
				visitor.visit(this);
			}
		};
		
		builder.incrementalBuild(delta, null);
		assertEquals("Value is after executing equations at 1.0", "1.0", result.getResultText());
	}
	
	@Test
	public void testFullBuild() {
		IncrementalEquationBuilder builder = new TestIncrementalEquationBuilder();
		
		assertNull("Value is initially null", result.getResultText());
		builder.fullBuild(null);
		assertEquals("Value is after executing equations at 1.0", "1.0", result.getResultText());
	}
	
	@Test
	public void testMarkEquationEvaluationProblems() {
		IncrementalEquationBuilder builder = new TestIncrementalEquationBuilder();
		
		Discipline disc = RolesFactory.eINSTANCE.createDiscipline();
		disc.setUser("Test");
		
		UserRegistry.getInstance().setSuperUser(false);		
		
		seiEdSc.setAssignedDiscipline(disc);
		assertNull("Value is initially null", result.getResultText());
		builder.fullBuild(null);
		assertNull("Value is still null", result.getResultText());
		
		VirSatEquationMarkerHelper vemHelper = new VirSatEquationMarkerHelper();
		Equation equation = caEdSc.getEquationSection().getEquations().get(0);
		Set<IMarker> markers = vemHelper.getAllEvaluationProblemMarkers(equation);
		assertTrue("Object has not yet been marked out of date.", markers.isEmpty());
		
		builder.createEquationEvaluationProblemMarkers();
		
		markers = vemHelper.getAllEvaluationProblemMarkers(equation);
		assertTrue("Object has been marked out of date.", markers.isEmpty());
		
		UserRegistry.getInstance().setSuperUser(true);
	}

	@Test
	public void testResourceWithError() {
		IncrementalEquationBuilder builder = new TestIncrementalEquationBuilder() {
			@Override
			protected VirSatResourceSet getResourceSet() {
				try {
					File tempInvalidFile = File.createTempFile("virsat_test_invalid_file_", ".dvlm");
					URI uri = URI.createFileURI(tempInvalidFile.getAbsolutePath());
					Resource errorResource = resSet.createResource(uri);
					errorResource.load(Collections.EMPTY_MAP);
				} catch (IOException e) {
					if (!(e.getCause() instanceof SAXParseException)) {
						throw new RuntimeException(e);
					}
				}
				assertTrue("Resource set has an error", resSet.hasError());
				return resSet;
			}
		};

		assertNull("Value is initially null", result.getResultText());

		/**
		 * Local class to check that an error message has been written to the log
		 */
		class ErrorLogListener implements ILogListener {
			boolean errorWritten = false;

			@Override
			public void logging(IStatus status, String plugin) {
				if (status.getSeverity() == IStatus.ERROR && status.getException() != null
						&& status.getException().getCause() instanceof SAXParseException) {
					errorWritten = true;
				}
			}
		}

		ErrorLogListener errorLogListener = new ErrorLogListener();
		Platform.addLogListener(errorLogListener);

		builder.fullBuild(null);

		Platform.removeLogListener(errorLogListener);
		assertTrue("Error was written to the log", errorLogListener.errorWritten);
		assertNull("Value is still null because calculations didn't work", result.getResultText());
	}

}
