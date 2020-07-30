/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.impl.UpdateNoBoFeature;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Utility class for getting information of Graphiti diagram objects.
 */

public class DiagramHelper {
	
	/**
	 * Hidden constructor
	 */
	
	private DiagramHelper() {
		
	}
	
	/**
	 * Gets the structural element instance owning the diagram of the passed graphiti object, if there is one.
	 * @param graphitiObject the graphiti object
	 * @return a structural element instance that owns the diagram of the graphiti object
	 */
	
	public static StructuralElementInstance getOwningStructuralElementInstance(EObject graphitiObject) {
		Resource resource = graphitiObject.eResource();
		if (resource == null) {
			return null;
		}
		
		URI resourceUri = resource.getURI();
		
		// Check if we can go at least 2 levels up + we have the resource prefix
		final int MIN_SEGMENTS = 3;
		if (resourceUri.segmentCount() >= MIN_SEGMENTS) {
			
			// Navigate to the Structural Element Instance URI
			List<String> seiSegmentsList = new ArrayList<String>(resourceUri.segmentsList());
			seiSegmentsList = seiSegmentsList.subList(1, seiSegmentsList.size() - 2);
			String[] seiSegments = new String[seiSegmentsList.size()];
			URI seiUri = URI.createHierarchicalURI(seiSegmentsList.toArray(seiSegments), resourceUri.query(), resourceUri.fragment());
			seiUri = seiUri.appendSegment(VirSatProjectCommons.FILENAME_STRUCTURAL_ELEMENT_SEGMENT);
			seiUri = seiUri.appendFileExtension(VirSatProjectCommons.FILENAME_EXTENSION);
			seiUri = URI.createPlatformResourceURI(seiUri.toFileString(), true);
			
			Path path = new Path(resourceUri.toPlatformString(false));
			IResource iResource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
			IProject project = iResource.getProject();
			ResourceSet resourceSet = VirSatResourceSet.getResourceSet(project);
			
			// Now that we have the correct URI, grab the corresponding resource from the resource set
			Resource seiResource = resourceSet.getResource(seiUri, false);
			if (seiResource != null) {
				// Finally we can get the structural element instance out of the resource
				seiResource = resourceSet.getResource(seiUri, true);
				return (StructuralElementInstance) seiResource.getContents().get(0);
			}
		}
		
		
		return null;
	}
	
	/**
	 * Checks if we have write permission to the diagram of this graphiti object.
	 * @param graphitiObject the graphiti object
	 * @return true iff the structural element instance associated with the documents folder
	 * the diagram is contained in is writeable. If no such structural element instance exists
	 * then we also have write access
	 */
	public static boolean hasDiagramWritePermission(EObject graphitiObject) {
		StructuralElementInstance sei = getOwningStructuralElementInstance(graphitiObject);
		if (sei != null) {
			return RightsHelper.hasSystemUserWritePermission(sei); 
		} else {
			return true;
		}
	}
	
	/**
	 * Gets an eObject for the passed business object if possible
	 * @param businessObject the business object
	 * @return the associated eObject if there is one
	 */
	public static EObject getEObject(Object businessObject) {
		if (businessObject instanceof EObject) {
			return (EObject) businessObject;
		} else if (businessObject instanceof ABeanCategoryAssignment) {
			return ((ABeanCategoryAssignment) businessObject).getTypeInstance();
		} else if (businessObject instanceof ABeanStructuralElementInstance) {
			return ((ABeanStructuralElementInstance) businessObject).getStructuralElementInstance();
		}
		
		return null;
	}
	
	/**
	 * Checks if we have write permission both on the businessobject and the graphiti object
	 * @param businessObject the business object
	 * @param graphitiObject the graphiti object
	 * @return true iff we have write access to both objects
	 */
	
	public static boolean hasBothWritePermission(Object businessObject, EObject graphitiObject) {
		EObject eObject = getEObject(businessObject);
		
		if (eObject == null) {
			return true;
		}
		
		return RightsHelper.hasSystemUserWritePermission(eObject) && DiagramHelper.hasDiagramWritePermission(graphitiObject);
	}
	/**
	 * Creates the diagram
	 * @param diagramResourceUri the uri of the diagram
	 * @param diagram the diagram
	 * @param resourceSet the resource set
	 */
	public static void createDiagram(URI diagramResourceUri, final Diagram diagram, VirSatResourceSet resourceSet) {
		// Create a resource set and EditingDomain
		//IEmfService emfService = new EmfService();
		// Create a resource for this file.
		final Resource resource = resourceSet.createResource(diagramResourceUri);
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(resourceSet);
		final CommandStack commandStack = ed.getCommandStack();
		commandStack.execute(new RecordingCommand(ed) {

			@Override
			protected void doExecute() {
				resource.setTrackingModification(true);
				resource.getContents().add(diagram);
				resourceSet.saveResource(resource, UserRegistry.getInstance());
			}
		});
	}
	
	/**
	 * Gets the concept from the passed editing domain
	 * @param ed the editing domain
	 * @param conceptID the conceptID
	 * @return the  concept
	 */
	public static Concept getConcept(VirSatTransactionalEditingDomain ed, String conceptID) {
		Repository repository = ed.getResourceSet().getRepository();
		ActiveConceptHelper acHelper = new ActiveConceptHelper(repository);
		Concept concept = acHelper.getConcept(conceptID);
		
		return concept;
	}
	
	private static final int[] ARROW_HEAD_POLYLINE = new int[] { -10, 5, 0, 0, -10, -5 };
	
	/**
	 * Creates an arrow head for the line connection
	 * @param gaContainer the connection which we want to equip with the arrow head
	 * @param color the arrow head color
	 * @return an arrow head polyline
	 */
	public static Polyline createArrow(GraphicsAlgorithmContainer gaContainer, Color color) {
		IGaService gaService = Graphiti.getGaService();
		Polyline polyline = gaService.createPolyline(gaContainer, ARROW_HEAD_POLYLINE);
		polyline.setForeground(color);
		polyline.setLineWidth(2);
		return polyline;
	}
	
	/**
	 * Use this method to get a container shape containing the passed pictogram element
	 * that knows how its contents should be updated
	 * @param fp the feature provider for associating diagram elements with update features
	 * @param pe the pictogram element that we want to update and need the updateable container for
	 * @return an updateable container that contains the passed pictogram element
	 */
	public static ContainerShape getUpdateableContainer(IFeatureProvider fp, PictogramElement pe) {
		ContainerShape cs = ((Shape) pe).getContainer();
		IUpdateFeature updateFeature = fp.getUpdateFeature(new UpdateContext(cs));
		// Crawl upwards until we have a valid update feature to update this element
		while (updateFeature == null || updateFeature instanceof UpdateNoBoFeature) {
			cs = cs.getContainer();
			updateFeature = fp.getUpdateFeature(new UpdateContext(cs));
		}
		
		return cs;
	}
}
