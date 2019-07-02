/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.rmf.reqif10.SpecObject;

import de.dlr.sc.virsat.requirements.reqif.util.ReqIFUtil;
import de.dlr.sc.virsat.requirements.tracing.builder.history.RequirementsHistoryComparator;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceabilityLinkContainer;
import de.dlr.sc.virsat.requirements.tracing.util.TraceHelper;
import de.dlr.sc.virsat.requirements.tracing.validation.TracedArtifactRequirementValidator;

/**
 * @author Tobias Franz tobias.franz@dlr.de
 *
 */
public class TraceBuilder extends IncrementalProjectBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder#build(int,
	 * java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		if (kind == IncrementalProjectBuilder.FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	/**
	 * @param delta
	 *            the differences
	 * @param monitor
	 *            monitor
	 * @throws CoreException
	 *             exception
	 */
	private void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {

		// validate all existing traces
		// validateAllTraceabilityLinkContainers(getProject());

		// validate changed requirements
		List<IResourceDelta> leafChildren;
		leafChildren = (ArrayList<IResourceDelta>) getDeepChildren(delta);
		for (IResourceDelta newDelta : leafChildren) {
			URI fileUri = URI.createPlatformResourceURI(newDelta.getFullPath().toString(), true);

			// If traces changed validate these first (only necessary for validation of
			// actual artifact values)
			if (TraceHelper.isTraceModel(fileUri)) {
				runValidation(fileUri, null);
			}

			// If requirements changed validate their trace
			List<SpecObject> changedSpecs = null;
			if (ReqIFUtil.isReqIF(fileUri)) {
				RequirementsHistoryComparator comparer = new RequirementsHistoryComparator();
				changedSpecs = comparer.compareWithLatestFromHistory(newDelta.getFullPath().toString());
				runValidation(fileUri, changedSpecs);
			}
		}
	}

	/**
	 * The full build
	 * 
	 * @param monitor
	 *            the monitor
	 * @throws CoreException
	 *             a core exception if it cannot be validated
	 */
	protected void fullBuild(IProgressMonitor monitor) throws CoreException {
		validateAllExistingTraces(getProject());
		
		//Also check for recent changes in requirements
		IResourceDelta delta = getDelta(getProject());
		List<IResourceDelta> leafChildren;
		leafChildren = (ArrayList<IResourceDelta>) getDeepChildren(delta);
		for (IResourceDelta newDelta : leafChildren) {
			URI fileUri = URI.createPlatformResourceURI(newDelta.getFullPath().toString(), true);
			if (ReqIFUtil.isReqIF(fileUri)) {
				RequirementsHistoryComparator comparer = new RequirementsHistoryComparator();
				runValidation(fileUri, comparer.compareWithLatestFromHistory(newDelta.getFullPath().toString()));
			}
		}
	}

	/**
	 * @param delta
	 *            the changes
	 * @return all of the changes done recently
	 */
	List<IResourceDelta> getDeepChildren(IResourceDelta delta) {
		List<IResourceDelta> newList = new ArrayList<>();
		if (delta != null) {
			if (delta.getAffectedChildren().length == 0) {
				newList.add(delta);
				return newList;
			}
			for (IResourceDelta children : delta.getAffectedChildren()) {
				newList.addAll(getDeepChildren(children));
			}
		}
		return newList;
	}

	/**
	 * runs the automatic validation on all of the trace links within a project
	 * 
	 * @param container
	 *            the project
	 * @throws CoreException
	 *             throws the exception
	 */
	protected void validateAllExistingTraces(IContainer container) throws CoreException {
		IResource[] members = container.members();
		for (IResource member : members) {
			if (member instanceof IContainer) {
				validateAllExistingTraces((IContainer) member);
			} else if (member instanceof IFile) {
				if (TraceHelper.isTraceModel((IFile) member)) {
					URI fileUri = URI.createPlatformResourceURI(member.getFullPath().toString(), true);
					runValidation(fileUri, null);
				}
			}
		}
	}

	/**
	 * Runs the validation on the given file
	 * 
	 * @param fileUri
	 *            the file uri to run the validation on
	 * @param changedSpecs
	 *            the specs which are recently changed
	 * @throws CoreException
	 *             if validation fails
	 */
	private void runValidation(URI fileUri, List<SpecObject> changedSpecs) throws CoreException {

		ResourceSet set = new ResourceSetImpl();
		Resource resource = set.createResource(fileUri);
		try {
			if (set.getURIConverter().exists(fileUri, null)) {
				resource.load(null);
				TraceabilityLinkContainer container = TraceHelper.getTraceModel(resource);
				if (container != null) {
					TracedArtifactRequirementValidator validator = new TracedArtifactRequirementValidator();
					validator.validate(container, changedSpecs);
				}
			}
		} catch (IOException e) {
			Activator.getDefault().getLog().log(
					new Status(Status.ERROR, Activator.getPluginId(), "TraceBuilder: Could not run validation!", e));
		}
	}


}
