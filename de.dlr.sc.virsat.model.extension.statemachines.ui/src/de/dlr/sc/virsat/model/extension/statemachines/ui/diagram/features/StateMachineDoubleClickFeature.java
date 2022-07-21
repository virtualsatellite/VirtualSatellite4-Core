/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirsatCategoryAssignmentOpenEditorFeature;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.transitions.ITransitionLabelProvider;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.transitions.LabelProviderInstantiator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.uiengine.ui.dialog.ReferenceSelectionDialog;

/**
 * Implements the editor's double click behavior 
 *
 */
public class StateMachineDoubleClickFeature extends VirsatCategoryAssignmentOpenEditorFeature {

	public StateMachineDoubleClickFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public void execute(ICustomContext context) {
		PictogramElement pe = context.getPictogramElements()[0];
		IBeanCategoryAssignment bean = (IBeanCategoryAssignment) getBusinessObjectForPictogramElement(pe);
		if (bean instanceof Transition) {
			openReferenceSelectionDoalog((Transition) bean, context);
		} else if (bean instanceof State) {
			super.execute(context);
		}
	}
	
	/**
	 * Update the reference to the transition trigger
	 * @param transition the transition to be updated
	 * @param context the update context
	 */
	protected void openReferenceSelectionDoalog(Transition transition, ICustomContext context) {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(transition.getTypeInstance());
		ReferencePropertyInstance seqRefPI = (ReferencePropertyInstance) caHelper.getPropertyInstance(Transition.PROPERTY_TRIGGER);
		ATypeDefinition referencePropertyType = ((ReferenceProperty) seqRefPI.getType()).getReferenceType();
		
		ReferenceSelectionDialog dialog = ReferenceSelectionDialog.createRefernceSelectionDialog(Display.getCurrent().getActiveShell(), referencePropertyType, adapterFactory);
		dialog.setAllowMultiple(false);
		dialog.setDoubleClickSelects(true);
		dialog.setInput(seqRefPI.eResource());
		if (dialog.open() == Dialog.OK) {
			Object selection = dialog.getFirstResult();
			if (selection instanceof CategoryAssignment || selection == null) {

				CategoryAssignment selectedCA = (CategoryAssignment) selection;
				
				VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(transition.getATypeInstance());
				Command command = SetCommand.create(ed, seqRefPI, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, selectedCA);
				ed.getCommandStack().execute(command);
				
				//Update diagram label
				PictogramElement subject = context.getPictogramElements()[0];
				Connection connection = null;
				Text text = null;
				if (subject instanceof Connection) {
					connection = (Connection) subject;
					text = (Text) connection.getConnectionDecorators().get(0).getGraphicsAlgorithm();
				} else {
					text = (Text) subject.getGraphicsAlgorithm();
				}
				ITransitionLabelProvider labelProvider = new LabelProviderInstantiator().getLabelProvider();
				text.setValue(labelProvider.getLabel(transition));	
			}
		}
	}


}
