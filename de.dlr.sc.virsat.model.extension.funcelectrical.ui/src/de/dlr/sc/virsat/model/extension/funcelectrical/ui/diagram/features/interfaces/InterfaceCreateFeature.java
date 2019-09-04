/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaces;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.dialogs.AddInterfaceDialog;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;


/**
 * Feature for creating new interfaces in an interface diagram.
 * @author muel_s8
 *
 */

public class InterfaceCreateFeature extends AbstractCreateConnectionFeature {

	/**
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	
	public InterfaceCreateFeature(IFeatureProvider fp) {
		super(fp, "Interface", "Create Interface");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// Check if the desired anchors are properly linked
		
		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();
		
		if (sourceAnchor == null && context.getSourcePictogramElement() instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) context.getSourcePictogramElement();
			if (!cs.getChildren().isEmpty() && cs.getChildren().get(0) instanceof ContainerShape) {
				cs = (ContainerShape) cs.getChildren().get(0);
				if (!cs.getAnchors().isEmpty()) {
					sourceAnchor = cs.getAnchors().get(0);
				}
			}
		}
		
		if (targetAnchor == null && context.getTargetPictogramElement() instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) context.getTargetPictogramElement();
			if (!cs.getChildren().isEmpty() && cs.getChildren().get(0) instanceof ContainerShape) {
				cs = (ContainerShape) cs.getChildren().get(0);
				if (!cs.getAnchors().isEmpty()) {
					targetAnchor = cs.getAnchors().get(0);
				}
			}
		}
		
		Object source = getBusinessObjectForPictogramElement(sourceAnchor);
		Object target = getBusinessObjectForPictogramElement(targetAnchor);
		if (source instanceof InterfaceEnd && target instanceof InterfaceEnd && source != target) {
			InterfaceEnd ie1 = (InterfaceEnd) source;
			StructuralElementInstance sei1 = (StructuralElementInstance) ie1.getATypeInstance().eContainer();
			if (sei1.getType().getName().equals(ElementDefinition.class.getSimpleName())) {
				return false;
			}
			InterfaceEnd ie2 = (InterfaceEnd) target;
			StructuralElementInstance sei2 = (StructuralElementInstance) ie2.getATypeInstance().eContainer();
			if (sei2.getType().getName().equals(ElementDefinition.class.getSimpleName())) {
				return false;
			}
			
			return DiagramHelper.hasDiagramWritePermission(sourceAnchor);
		}
		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {

		Connection newConnection = null;
		
		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();
		
		if (sourceAnchor == null) {
			ContainerShape cs = (ContainerShape) context.getSourcePictogramElement();
			cs = (ContainerShape) cs.getChildren().get(0);
			sourceAnchor = cs.getAnchors().get(0);
		}
		
		if (targetAnchor == null) {
			ContainerShape cs = (ContainerShape) context.getTargetPictogramElement();
			cs = (ContainerShape) cs.getChildren().get(0);
			targetAnchor = cs.getAnchors().get(0);
		}
		
		InterfaceEnd source = (InterfaceEnd) getBusinessObjectForPictogramElement(sourceAnchor);
		InterfaceEnd target = (InterfaceEnd) getBusinessObjectForPictogramElement(targetAnchor);
		
		if (source != null && target != null) {
			VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(source.getTypeInstance());
			Concept concept = source.getConcept();
			Interface i = new Interface(concept);
			i.setInterfaceEndFrom(source);
			i.setInterfaceEndTo(target);
			i.setName(source.getName() + target.getName());
			// Add the interface to the structural element instance of the source anchor

			ContainerShape parent = ((ContainerShape) sourceAnchor.getParent()).getContainer();
			ABeanStructuralElementInstance parentBean = (ABeanStructuralElementInstance) getBusinessObjectForPictogramElement(parent.getContainer());
			
			ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
			adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
			
			// Check if there is a good default position to put the interface into
			// If the diagram is in a document folder of some sei that can have interfaces
			// We automatically put the interface to this sei
			StructuralElementInstance owner = DiagramHelper.getOwningStructuralElementInstance(sourceAnchor.eResource().getContents().get(0));
			BeanStructuralElementInstance bSei = new BeanStructuralElementInstance();
			
			if (owner != null) {
				// We need to make sure that the owner we got actually can have interfaces attached
				
				bSei.setStructuralElementInstance(owner);
				Command cmd = bSei.add(ed, i);
				if (!cmd.canExecute()) {
					owner = null;
				}
			}
			
			if (owner == null) {
				StructuralElementInstance sei =  parentBean.getStructuralElementInstance();
				StructuralElement se = sei.getType();
				AddInterfaceDialog dialog = AddInterfaceDialog.createRefernceSelectionDialogForInheritance(Display.getCurrent().getActiveShell(), se, adapterFactory);
				dialog.setAllowMultiple(false);
				dialog.setDoubleClickSelects(true);
				dialog.setInput(parentBean.getStructuralElementInstance().eResource());
				dialog.setInitialSelection(parentBean.getStructuralElementInstance());
				if (dialog.open() == Dialog.OK) {
					Object selection = dialog.getFirstResult();
					if (selection instanceof StructuralElementInstance) {
						owner = (StructuralElementInstance) selection;
						bSei.setStructuralElementInstance(owner);
					}
				} 
			}
			
			Command command = bSei.add(ed, i);
			
			if (command.canExecute()) {
				ed.getCommandStack().execute(command);
				
				AddConnectionContext addContext = new AddConnectionContext(sourceAnchor, targetAnchor);
				addContext.setNewObject(i.getTypeInstance());
				newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
			}
		}
		
		return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// Check if start anchor is properly linked
		Anchor anchor = context.getSourceAnchor();
		PictogramElement pe = context.getSourcePictogramElement();
		Object sourceAnchor = getBusinessObjectForPictogramElement(anchor);
		Object sourcePe = getBusinessObjectForPictogramElement(pe);
		if (sourceAnchor instanceof InterfaceEnd || sourcePe instanceof InterfaceEnd) {
			return DiagramHelper.hasDiagramWritePermission(pe);
		}
		
		return false;
	}
	
	@Override
	public String getCreateImageId() {
		return Interface.FULL_QUALIFIED_CATEGORY_NAME;
	}

}
