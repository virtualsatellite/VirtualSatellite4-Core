/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features;


import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICopyFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveConnectionDecoratorFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IPasteFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.BeanDirectEditNameFeature;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatCategoryAssingmentCopyFeature;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatChangeColorFeature;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatDiagramFeatureProvider;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatMoveConnectionDecoratorFeature;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatStructuralElementInstanceCopyFeature;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatStructuralElementInstancePasteFeature;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirsatCategoryAssignmentOpenEditorFeature;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.elements.ElementAddFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.elements.ElementLayoutFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.elements.ElementResizeFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.elements.ElementUpdateFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaceends.InterfaceEndAddFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaceends.InterfaceEndChangeColorFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaceends.InterfaceEndCreateFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaceends.InterfaceEndLayoutFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaceends.InterfaceEndMoveFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaceends.InterfaceEndPasteFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaces.InterfaceAddFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaces.InterfaceCreateFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaces.InterfaceReconnectionFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaces.InterfaceUpdateFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfacetypes.InterfaceTypeAddFeature;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;

/**
 * The interface feature provider provides all features for interface diagrams.
 * @author muel_s8
 *
 */

public class InterfaceDiagramFeatureProvider extends VirSatDiagramFeatureProvider {
	

	
	/**
	 * Default constructor
	 * @param dtp the diagram type provider
	 */
	public InterfaceDiagramFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);

	}
	
	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		Object newObject = context.getNewObject();
		
		if (newObject instanceof StructuralElementInstance) {
			StructuralElementInstance sei = (StructuralElementInstance) newObject;
			StructuralElement se = (StructuralElement) sei.getType();
			
			if (se.getName().equals(ElementDefinition.class.getSimpleName())
					||	se.getName().equals(ElementConfiguration.class.getSimpleName())
					|| 	se.getName().equals(ElementOccurence.class.getSimpleName())) {
				return new ElementAddFeature(this);
			}
		}
		
		if (newObject instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) newObject;
			Category cat = (Category) ca.getType();
			
			if (cat.getName().equals(InterfaceEnd.class.getSimpleName())) {
				return new InterfaceEndAddFeature(this);
			}
			
			if (cat.getName().equals(Interface.class.getSimpleName())) {
				return new InterfaceAddFeature(this);
			}
			
			if (ActiveConceptHelper.getAllNames(cat).contains(InterfaceType.class.getSimpleName())) {
				return new InterfaceTypeAddFeature(this);
			}
		}
		
		return super.getAddFeature(context);
	}
	
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object object = getBusinessObjectForPictogramElement(pictogramElement);
		
		if (pictogramElement instanceof Diagram) {
			return super.getUpdateFeature(context);
		}
		
		if ((object instanceof ABeanStructuralElementInstance || object == null) && pictogramElement instanceof ContainerShape) {
			return new ElementUpdateFeature(this);
		}
		
		if (object instanceof ABeanStructuralElementInstance && pictogramElement instanceof Text) {
			return new ElementUpdateFeature(this);
		}
		
		if ((object instanceof Interface || object == null) && pictogramElement instanceof FreeFormConnection) {
			return new InterfaceUpdateFeature(this);
		}
		
		return super.getUpdateFeature(context);
	}
	
	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { new InterfaceEndCreateFeature(this) };
	}
	
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] { new InterfaceCreateFeature(this) };
	}
	
	@Override
	public IReconnectionFeature getReconnectionFeature(IReconnectionContext context) {
		return new InterfaceReconnectionFeature(this);
	}
	
	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		
		PictogramElement pe = context.getPictogramElement();
		// Connection decorators (Like interface name) shold not be deleted. Interface itself can be deleted
		if (pe.getGraphicsAlgorithm() instanceof Text) {
			return null;
		}
		
		return super.getDeleteFeature(context);
	}
	
	@Override
	public IMoveConnectionDecoratorFeature getMoveConnectionDecoratorFeature(IMoveConnectionDecoratorContext context) {
		return new VirSatMoveConnectionDecoratorFeature(this);
	}
	
	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getPictogramElement());
		
		if (object instanceof ABeanStructuralElementInstance) {
			return new ElementLayoutFeature(this);
		}
		
		if (object instanceof InterfaceEnd) {
			return new InterfaceEndLayoutFeature(this);
		}
		
		return super.getLayoutFeature(context);
	}
	
	@Override
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getPictogramElement());
		
		if (object instanceof InterfaceEnd || object instanceof Interface || object instanceof ABeanStructuralElementInstance) {
			return new BeanDirectEditNameFeature(this);
		}
		
		return super.getDirectEditingFeature(context);
	}
	
	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getShape());
		
		if (object instanceof InterfaceEnd) {
			return new InterfaceEndMoveFeature(this);
		}
		
		return super.getMoveShapeFeature(context);
	}
	
	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
	    
	    PictogramElement[] pictogramElements = context.getPictogramElements();
		Object object = getBusinessObjectForPictogramElement(pictogramElements[0]);
		
		if (object instanceof ABeanStructuralElementInstance) {
			return new ICustomFeature[] {  new VirSatChangeColorFeature(this)};
		}
		
		if (object instanceof InterfaceEnd) {
			return new ICustomFeature[] { new VirsatCategoryAssignmentOpenEditorFeature(this), new InterfaceEndChangeColorFeature(this)};
		}
		return super.getCustomFeatures(context);
	} 
	
	@Override
	public ICopyFeature getCopyFeature(ICopyContext context) {
		PictogramElement[] pictogramElements = context.getPictogramElements();
		Object object = getBusinessObjectForPictogramElement(pictogramElements[0]);
		
		if (object instanceof ABeanStructuralElementInstance) {
			return new VirSatStructuralElementInstanceCopyFeature(this);
		}
		if (object instanceof StructuralElementInstance) {
			return new VirSatStructuralElementInstanceCopyFeature(this);
		}
		if (object instanceof InterfaceEnd) {
			return new VirSatCategoryAssingmentCopyFeature(this);
		}
		
		return super.getCopyFeature(context);
	}
	
	@Override
	public IPasteFeature getPasteFeature(IPasteContext context) {

		PictogramElement[] pictogramElements = context.getPictogramElements();
		
		if (pictogramElements.length != 1 || !(pictogramElements[0] instanceof Diagram)) {
			return new InterfaceEndPasteFeature(this);
		}
		
		return new VirSatStructuralElementInstancePasteFeature(this);
		
	}
	
	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getShape());
		
		if (object instanceof ABeanStructuralElementInstance) {
			return new ElementResizeFeature(this);
		}
		
		return null;
	}
	
	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pe = context.getPictogramElement();
		// Connection decorators (Like interface name) shold not be removed. Interface itself can be removed
		if (pe.getGraphicsAlgorithm() instanceof Text) {
			return null;
		}
		return super.getRemoveFeature(context);
	}
}
