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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.build.inheritance.AVirSatTransactionalBuilder;
import de.dlr.sc.virsat.commons.datastructures.DependencyTree;
import de.dlr.sc.virsat.model.calculation.compute.problem.EvaluationProblem;
import de.dlr.sc.virsat.model.calculation.marker.VirSatEquationMarkerHelper;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Builder for evaluating expressions and equations.
 */

public class IncrementalEquationBuilder extends AVirSatTransactionalBuilder {

	public static final String BUILDER_ID = "de.dlr.sc.virsat.model.calculation.compute.builder";

	private ExpressionHelper exprHelper = new ExpressionHelper();
	private EquationHelper dependencyHelper = new EquationHelper(exprHelper);
	
	private List<EvaluationProblem> equationProblems;
	private List<EObject> objectsWithOldMarkers;
	
	protected VirSatEquationMarkerHelper vemHelper;
	
	/**
	 * Public constructor
	 */
	public IncrementalEquationBuilder() {
		super("Equation Builder", new VirSatEquationMarkerHelper(), true, true);
		this.vemHelper = (VirSatEquationMarkerHelper) this.vpmHelper;
		objectsWithOldMarkers = new ArrayList<>();
	}

	@Override
	protected void transactionalFullBuildUpdateProblemMarkers() {
		createEquationEvaluationProblemMarkers();
	}
	
	@Override
	protected void transactionalIncrementalBuildRemoveProblemMarkers() {
		// Clean up old markers
		for (EObject objectWithOldMarkers : objectsWithOldMarkers) {
			vemHelper.deleteAllMarkers(objectWithOldMarkers);
		}
	}

	@Override
	protected void transactionalIncrementalBuildUpdateProblemMarkers() {
		createEquationEvaluationProblemMarkers();
	}

	@Override
	public void fullBuild(IProgressMonitor monitor) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "IncrementalEquationBuilder: Starting full build"));
		VirSatResourceSet resourceSet = getResourceSet();
		
		if (!resourceSet.isOpen()) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "IncrementalEquationBuilder: Exited full build since project is closed"));
			return;
		}
		
		// Build the dependency tree
		List<Equation> equations = getAllEquationsInProject(resourceSet);
		DependencyTree<EObject> tree = dependencyHelper.createDependencyTree(equations);
		buildEquations(tree, monitor);
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "IncrementalEquationBuilder: Finished full build"));
	}

	@Override
	public void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "IncrementalEquationBuilder: Starting incremental build"));
		final int MAX_TASKS = 4;
		SubMonitor subMonitor = SubMonitor.convert(monitor, MAX_TASKS);
		subMonitor.beginTask("Building dependency tree...", MAX_TASKS);
		
		VirSatResourceSet resourceSet = getResourceSet();
		
		if (!resourceSet.isOpen()) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "IncrementalEquationBuilder: Exited incremental build since project is closed"));
			return;
		}
		
		// Set of equations we definitely have to update because they are in a changed resource
		Set<EObject> equationResults = new HashSet<>();
		
		try {
			delta.accept(new IResourceDeltaVisitor() {
				@Override
				public boolean visit(IResourceDelta delta) throws CoreException {
					IResource iResource = delta.getResource();
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "IncrementalEquationBuilder: Obtained equations from Resource (" + iResource + ")"));
				    	
					int resourceDeltaKind = delta.getKind();
					boolean isRemoved = resourceDeltaKind == IResourceDelta.REMOVED;
					if ((iResource instanceof IFile) && (!isRemoved)) {
						IFile iFile = (IFile) iResource;

						// Only build model files
						boolean isDvlmModelFile = VirSatProjectCommons.isDvlmFile(iFile);
						if (isDvlmModelFile) {
							Resource resource = resourceSet.safeGetResource(iFile, false);

							// In case the resource could not be loaded continue with the next delta.
							if (resource == null) {
								return true;
							}

							equationResults.addAll(getAllEquationResultsInResource(resource));
						}
					}

					return true;
				}
			});
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "IncrementalEquationBuilder: Could not obtain equations", e));
			return;
		}
		
		// Build the dependency tree
		List<Equation> equations = getAllEquationsInProject(resourceSet);
		DependencyTree<EObject> tree = dependencyHelper.createDependencyTree(equations);

		tree.trim(equationResults);		
		subMonitor.worked(1);
		
		objectsWithOldMarkers.clear();
		objectsWithOldMarkers.addAll(tree.getNodes());
		tree.getNodes().forEach(node -> {
			// We also need to get all the instances that custom result setters may affect
			if (node instanceof TypeInstanceResult) {
				ATypeInstance resultInstance = ((TypeInstanceResult) node).getReference();
				IResultSetter resultSetter = exprHelper.getResultSetter(resultInstance);
				if (resultSetter != null) {
					objectsWithOldMarkers.addAll(resultSetter.getAffectedTypeInstances(resultInstance));
				}
			}
		});
		objectsWithOldMarkers.addAll(equations);
		
		subMonitor.beginTask("Evaluating equations...", MAX_TASKS);
		buildEquations(tree, monitor);
		subMonitor.worked(1);
		
		subMonitor.beginTask("Saving resources...", MAX_TASKS);
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "IncrementalEquationBuilder: Finsihed incremental build"));
	}
	
	/**
	 * the method getAllEquationsInProject crawls through the whole project and gathers all Equations.
	 * It also resolves all proxied elements in our project
	 * @param resourceSet resource set of the project
	 * @return a list of Equations
	 */
	@SuppressWarnings("unchecked")
	private List<Equation> getAllEquationsInProject(VirSatResourceSet resourceSet) {
		List<Equation> equations = new ArrayList<>();
		
		try {
			resourceSet.getRepository();
			EcoreUtil.resolveAll(resourceSet);
			
			if (resourceSet.hasError()) {
				reportResourceSetErrors(resourceSet);
				return Collections.EMPTY_LIST;
			}
			
			List<IEquationSectionContainer> equationSectionContainers = VirSatEcoreUtil.getAllContentsOfType(resourceSet, null, IEquationSectionContainer.class, true);
			
			for (IEquationSectionContainer container : equationSectionContainers) {
				EquationSection section = container.getEquationSection();
				if (section != null) {
					equations.addAll(section.getEquations());
				}
			}
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "Could not read active Equation from Repository", e));
		}
	
		return equations;
	};
	
	/**
	 * Writes all errors from resourceSet to log
	 * @param resourceSet resource set to get errors from
	 */
	private void reportResourceSetErrors(VirSatResourceSet resourceSet) {
		for (Resource resource : resourceSet.getResources()) {
			for (Resource.Diagnostic error : resource.getErrors()) {
				Status status = getResourceErrorStatus(resource, error);
				Activator.getDefault().getLog().log(status);
			}
		}
	};
	
	/**
	 * Creates a status for an error in a resource
	 * @param resource EMF resource with an error
	 * @param error 
	 * @return status for writing to log
	 */
	private Status getResourceErrorStatus(Resource resource, Resource.Diagnostic error) {
		Status status;
		Throwable exception = error instanceof Throwable ? (Throwable) error : null;
		status = new Status(Status.ERROR, Activator.getPluginId(),
				String.format("IncrementalEquationBuilder: error in resource %s: %s", resource.toString(),
						error.getMessage()),
				exception);
		return status;
	}

	/**
	 * Call this method to get all Equations from a given EMF Resource
	 * @param resource The EMF Resource to look in for equations
	 * @return A list with all contained SEIs
	 */
	private Set<IEquationResult> getAllEquationResultsInResource(Resource resource) {
		Set<IEquationResult> equations = new HashSet<>();
		
		try {
			EcoreUtil.resolveAll(resource);
			EcoreUtil.getAllContents(resource, true).forEachRemaining((object) -> {
				if (object instanceof Equation) {
					Equation equation = (Equation) object;
					equations.add(equation.getResult());
				}
			});
				
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "Could not read active Equation from Repository", e));
		}
	
		return equations;
	};
	
	/**
	 * Evaluate the equations and perform the assignments
	 * @param tree dependency tree of the equations
	 * @param monitor a progress monitor
	 * @throws CoreException 
	 */
	private void buildEquations(DependencyTree<EObject> tree, IProgressMonitor monitor) {
		
		final int MAX_TASKS = 3;
		SubMonitor subMonitor = SubMonitor.convert(monitor, MAX_TASKS);
		
		subMonitor.beginTask("Checking for cycles...", MAX_TASKS);
		
		// Handle cycles
		tree.removeCycles((cycle) -> vemHelper.createCyclicEquationMarker(cycle));
		
		subMonitor.worked(1);
		subMonitor.beginTask("Evaluating equations...", MAX_TASKS);
		
		// Evaluate the tree in the correct order
		equationProblems = dependencyHelper.evaluate(tree);
		
		subMonitor.worked(1);
	}
	
	/**
	 * Create markers for out of date objects
	 */
	public void createEquationEvaluationProblemMarkers() {
		VirSatResourceSet resourceSet = getResourceSet();
		
		if (!resourceSet.isOpen()) {
			return;
		}
		
		for (EvaluationProblem equationProblem : equationProblems) {			
			vemHelper.createEvaluationProblemMarker(equationProblem);
		}
	}
}
