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
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

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
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Evaluates expressions and equations.
 * @author muel_s8
 *
 */

public class IncrementalEquationBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "de.dlr.sc.virsat.model.calculation.compute.builder";

	private ExpressionHelper exprHelper = new ExpressionHelper();
	private EquationHelper dependencyHelper = new EquationHelper(exprHelper);
	
	private List<EvaluationProblem> equationProblems;
	private List<EObject> objectsWithOldMarkers;
	
	VirSatEquationMarkerHelper vemHelper;
	
	/**
	 * Public constructor
	 */
	public IncrementalEquationBuilder() {
		vemHelper = new VirSatEquationMarkerHelper();
		objectsWithOldMarkers = new ArrayList<>();
	}
	
	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		IProject project = getVirSatProject();
		IResourceDelta delta = getDelta(project);
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "IncrementalEquationBuilder: Try to trigger build", null));
		
		if (isInterrupted() || monitor.isCanceled()) {
			rememberLastBuiltState();
			return null;
		}
		
		// The builder is supposed to build the project in an unmanaged resourceSet
		// but since we can have inconsistencies between the resource on the eclipse workspace and the in memory representation
		// of EMF currently displayed by the managed ResourceSet in the navigator, it can happen that newly added elements disappear because the resources in the WS doesn't knwo references
		// and stores them once the builder is finished. This behavior is prevented by first asking the projects managed resource set
		// if there are unsaved changes pending. If yes, we do not allow the builder to start
		
		switch (kind) {
			case FULL_BUILD:
				transactionalFullBuild(monitor);
				break;
			case INCREMENTAL_BUILD:
				transactionaIncrementalBuild(delta, monitor);
				break;
			case AUTO_BUILD:
				if (delta == null) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "IncrementalEquationBuilder: Performing full build", null));
					transactionalFullBuild(monitor);
				} else {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "IncrementalEquationBuilder: Performing incremental build", null));
					transactionaIncrementalBuild(delta, monitor);
				}
				break;
			default:
				break;
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "IncrementalEquationBuilder: Finished build", null));

		return null;
	}
	
	/**
	 * Performs a transactional full build
	 * @param monitor progress monitor
	 * @throws CoreException 
	 */
	private void transactionalFullBuild(IProgressMonitor monitor) {
		VirSatTransactionalEditingDomain virSatTed = VirSatEditingDomainRegistry.INSTANCE.getEd(getVirSatProject());
		
		if (virSatTed == null) {
			return;
		}
		
		Command cmd = new AbstractCommand() {
			@Override
			public void execute() {
				fullBuild(monitor);
				virSatTed.saveAll(true, true);
			}

			@Override
			public void redo() {
				
			}
			
			@Override
			public boolean canUndo() {
				return true;
			}
			
			@Override
			public boolean canExecute() {
				return true;
			}
		};
		
		if (virSatTed.getActiveTransaction() != null) {
			rememberLastBuiltState();
			return;
		}
		
		virSatTed.getVirSatCommandStack().executeNoUndo(cmd);
		
		// Clean up old markers
		vemHelper.deleteAllMarkersInWorkspace();
		
		createEquationEvaluationProblemMarkers();
	}
	
	/**
	 * Performs an incremental build
	 * @param delta build delta
	 * @param monitor progress monitor
	 * @throws CoreException 
	 */
	private void transactionaIncrementalBuild(IResourceDelta delta, IProgressMonitor monitor) {
		VirSatTransactionalEditingDomain virSatTed = VirSatEditingDomainRegistry.INSTANCE.getEd(getVirSatProject());
		
		if (virSatTed == null) {
			return;
		}
		
		Command cmd = new AbstractCommand() {
			@Override
			public void execute() {
				incrementalBuild(delta, monitor);
				virSatTed.saveAll(true, true);
			}

			@Override
			public void redo() {
				
			}
			
			@Override
			public boolean canUndo() {
				return true;
			}
			
			@Override
			public boolean canExecute() {
				return true;
			}
		};
		
		if (virSatTed.getActiveTransaction() != null) {
			rememberLastBuiltState();
			return;
		}
		
		virSatTed.getVirSatCommandStack().executeNoUndo(cmd);
		
		// Clean up old markers
		for (EObject objectWithOldMarkers : objectsWithOldMarkers) {
			vemHelper.deleteAllMarkers(objectWithOldMarkers);
		}
		
		createEquationEvaluationProblemMarkers();
	}

	/**
	 * Performs a full build
	 * @param monitor progress monitor
	 * @throws CoreException 
	 */
	public void fullBuild(IProgressMonitor monitor) {
		VirSatResourceSet resourceSet = getResourceSet();
		
		if (!resourceSet.isOpen()) {
			return;
		}
		
		// Build the dependency tree
		List<Equation> equations = getAllEquationsInProject(resourceSet);
		DependencyTree<EObject> tree = dependencyHelper.createDependencyTree(equations);
		buildEquations(tree, monitor);
	}
	
	/**
	 * Performs an incremental build
	 * @param delta build delta
	 * @param monitor progress monitor
	 * @throws CoreException 
	 */
	public void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) {
		final int MAX_TASKS = 4;
		SubMonitor subMonitor = SubMonitor.convert(monitor, MAX_TASKS);
		subMonitor.beginTask("Building dependency tree...", MAX_TASKS);
		
		VirSatResourceSet resourceSet = getResourceSet();
		
		if (!resourceSet.isOpen()) {
			return;
		}
		
		// Set of equations we definitely have to update because they are in a changed resource
		Set<EObject> equationResults = new HashSet<>();
		
		try {
			delta.accept(new IResourceDeltaVisitor() {
				@Override
				public boolean visit(IResourceDelta delta) throws CoreException {
					IResource iResource = delta.getResource();
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "IncrementalEquationBuilder: Obtained equations from Resource (" + iResource + ")", null));
				    	
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
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "IncrementalEquationBuilder: Could not obtain equations", e));
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
	
	/**
	 * Facade method to be able to override getProject method
	 * for the test cases of this tester
	 * @return hands back the current project the builder is triggered on
	 */
	protected IProject getVirSatProject() {
		return getProject();
	}
	
	/**
	 * Overrideable method to provide the resource set for the use of non transactional testers
	 * @return gets the resource set this builder operates on
	 */
	protected VirSatResourceSet getResourceSet() {
		return VirSatResourceSet.getResourceSet(getVirSatProject());
	}
	
	@Override
	public ISchedulingRule getRule(int kind, Map<String, String> args) {
		return null;
	}
}
