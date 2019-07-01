/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.builder.history;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.match.DefaultComparisonFactory;
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.rmf.reqif10.SpecObject;

import de.dlr.sc.virsat.requirements.tracing.builder.Activator;

/**
 * Compares a model to its latest version from history
 * 
 * @author Tobias Franz tobias.franz@dlr.de
 *
 */
public class RequirementsHistoryComparator {

	/**
	 * @param path
	 *            the file path
	 * @return the changed requirements
	 * @throws CoreException
	 *             the exception
	 */
	public List<SpecObject> compareWithLatestFromHistory(String path) throws CoreException {
		URI fileUri = URI.createPlatformResourceURI(path, true);
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
		IFileState[] states = file.getHistory(new NullProgressMonitor());
		ResourceSet oldSet = null;
		Resource oldRes = null;
		// load old version
		if (states.length > 0) {
			IFileState lastFromHistory = states[0];
			oldSet = new ResourceSetImpl();
			oldRes = oldSet.createResource(fileUri);
			try {
				oldRes.load(lastFromHistory.getContents(), Collections.emptyMap());
			} catch (IOException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
						"ModelHistoryComparator: Unable to laod old version of the model", e));
			}
		}
		// load new version
		ResourceSet newSet = new ResourceSetImpl();
		Resource newRes = newSet.createResource(fileUri);
		try {
			newRes.load(null);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"ModelHistoryComparator: Unable to laod current version of the model", e));
		}
		return compare(oldRes, newRes);
	}
	
	
	/**
	 * Compares two models
	 * @param oldRes old resource
	 * @param newRes new resource
	 * @return the differences
	 */
	public List<SpecObject> compare(Resource oldRes, Resource newRes) {
		// Configure EMF Compare
		IEObjectMatcher matcher = DefaultMatchEngine.createDefaultEObjectMatcher(UseIdentifiers.WHEN_AVAILABLE);
		IComparisonFactory comparisonFactory = new DefaultComparisonFactory(new DefaultEqualityHelperFactory());
		IMatchEngine.Factory matchEngineFactory = new MatchEngineFactoryImpl(matcher, comparisonFactory);
		IMatchEngine.Factory.Registry matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
		matchEngineRegistry.add(matchEngineFactory);
		EMFCompare comparator = EMFCompare.builder().setMatchEngineFactoryRegistry(matchEngineRegistry).build();

		DefaultComparisonScope scope = new DefaultComparisonScope(oldRes, newRes, null);
		Comparison asd = comparator.compare(scope);
		Set<SpecObject> changedObjects = new HashSet<>();
		for (Diff d: asd.getDifferences()) {
			Match match = d.getMatch();
			if (match.getLeft() instanceof SpecObject && match.getRight() instanceof SpecObject) {
				SpecObject specObject = (SpecObject) match.getLeft();
				specObject.eContainer();
				changedObjects.add((SpecObject) match.getLeft());
			}
		}

		List<SpecObject> list = new ArrayList<>();
		list.addAll(changedObjects);
		return list;
	}

}
