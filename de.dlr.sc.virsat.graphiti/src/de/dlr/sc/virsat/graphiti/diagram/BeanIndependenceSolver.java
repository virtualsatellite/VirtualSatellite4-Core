/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.diagram;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.impl.IIndependenceSolver;

import de.dlr.sc.virsat.graphiti.Activator;
import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * Performs mapping Diagram elements <-> Domain Model objects
 * @author muel_s8
 *
 */

public class BeanIndependenceSolver implements IIndependenceSolver {

	private Map<String, Object> objectMap = new HashMap<String, Object>();
	private IDiagramTypeProvider dtp;

	/**
	 * Standard constructor. Required diagram type provider of the diagrams to later on
	 * the obejcts corresponding to a virsat ID can be looked up from the respective
	 * resource set.
	 * @param dtp the diagram type provider
	 */

	public BeanIndependenceSolver(IDiagramTypeProvider dtp) {
		this.dtp = dtp;
	}

	@Override
	public String getKeyForBusinessObject(Object object) {
		String result = null;
		if (object != null) {
			if (object instanceof ABeanStructuralElementInstance) {
				result = ((ABeanStructuralElementInstance) object).getStructuralElementInstance().getUuid().toString();

				if (!objectMap.containsKey(result)) {
					objectMap.put(result, object);
				}
			}

			if (object instanceof ABeanObject) {
				result = ((ABeanObject<?>) object).getTypeInstance().getUuid().toString();

				if (!objectMap.containsKey(result)) {
					objectMap.put(result, object);
				}
			}
		}
		return result;
	}

	@Override
	public Object getBusinessObjectForKey(String key) {
		if (objectMap.containsKey(key)) {
			Object object = objectMap.get(key);

			if (object instanceof ABeanStructuralElementInstance) {
				Resource resource = ((ABeanStructuralElementInstance) object).getStructuralElementInstance().eResource();
				if (resource == null) {
					objectMap.remove(key);
				}
			}

			if (object instanceof ABeanObject) {
				Resource resource = ((ABeanObject<?>) object).getTypeInstance().eResource();
				if (resource == null) {
					objectMap.remove(key);
				}
			}
		}

		if (!objectMap.containsKey(key)) {
			URI uri = dtp.getDiagram().eResource().getURI();
			Path path = new Path(uri.toPlatformString(false));
			IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
			IProject project = resource.getProject();
			VirSatResourceSet resourceSet = VirSatResourceSet.getResourceSet(project);
			EcoreUtil.getAllContents(resourceSet, true).forEachRemaining(object -> {
				if (object instanceof IUuid) {
					IUuid uuid = (IUuid) object;
					if (uuid.getUuid().toString().equals(key)) {
						if (object instanceof StructuralElementInstance) {
							BeanStructuralElementInstanceFactory bsf = new BeanStructuralElementInstanceFactory();
							try {
								objectMap.put(key, bsf.getInstanceFor((StructuralElementInstance) object));
							} catch (CoreException e) {
								Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform an operation! ", e);
								DVLMEditPlugin.getPlugin().getLog().log(status);
							}
						} else if (object instanceof CategoryAssignment) {
							BeanCategoryAssignmentFactory bcf = new BeanCategoryAssignmentFactory();
							try {
								objectMap.put(key, bcf.getInstanceFor((CategoryAssignment) object));
							} catch (CoreException e) {
								Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform an operation! ", e);
								DVLMEditPlugin.getPlugin().getLog().log(status);
							}
						} else if (object instanceof ATypeInstance) {
							BeanPropertyFloat bean = new BeanPropertyFloat();
							bean.setATypeInstance((ATypeInstance) object);
							objectMap.put(key, bean);
						}
					}
				}
			});
		}

		return objectMap.get(key);
	}

}
