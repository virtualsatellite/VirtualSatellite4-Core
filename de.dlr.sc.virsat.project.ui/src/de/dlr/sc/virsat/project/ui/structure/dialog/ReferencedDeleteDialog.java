/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.structure.dialog;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMPropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;

/**
 * This class provides a JFace MessageDialog based Dialog to show all the referencing
 * objects when deleting a set of given ones. The Dialog is intended to get called by the delete 
 * handlers to make the user aware that a delete action might break some things in the model.
 * In particular if there are references from another domain under different user rights, which
 * cannot be removed by teh user itself, but have to be removed by their respective owner.
 * 
 * @author fisc_ph
 *
 */
public class ReferencedDeleteDialog extends MessageDialog {

	private static final int DIALOG_REFERENCES_TABLE_HEIGHT_300 = 300;
	private Map<EObject, Set<EObject>> mapOfReferences;

	/**
	 * Constructor for the Dialog. For further details refer to the JFace Message dialog
	 * @param parentShell the Shell in which's context to display the dialog. Can be null.
	 * @param mapOfReferences the map of references from objects that are about to be deleted.
	 * @param dialogTitle the title of dialog. Can be null.
	 * @param dialogTitleImage the image to be used for the dialog title. Can be null.
	 * @param dialogMessage The actual message to be displayed
	 * @param dialogImageType the image to be used for the message
	 * @param defaultIndex The index of the default button
	 * @param dialogButtonLabels an array of labels to be used for the buttons
	 */
	protected ReferencedDeleteDialog(Shell parentShell, Map<EObject, Set<EObject>> mapOfReferences, String dialogTitle, Image dialogTitleImage, String dialogMessage,	int dialogImageType, int defaultIndex, String[] dialogButtonLabels) {
		super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, defaultIndex, dialogButtonLabels);
		this.mapOfReferences = mapOfReferences;
	}

	public static final String DIALOG_TITLE = "Deleting Referenced Element?";
	public static final String DIALOG_MESSAGE = "The element or at least one of it's sub-elements is referenced from somwhere else. Do you still want to delete it?";

	/**
	 * Static factory method to create the dialog.
	 * @param parent the shell in which's context to create the dialog
	 * @param mapOfReferences the content of referencing objects to be displayed
	 * @return true in case the user confirmed the delete action. false in all otehr cases.
	 */
    public static boolean openQuestion(Shell parent, Map<EObject, Set<EObject>> mapOfReferences) {
        String [] dialogButtons = new String[] {
        	IDialogConstants.YES_LABEL,
			IDialogConstants.NO_LABEL
		};
        ReferencedDeleteDialog dialog = new ReferencedDeleteDialog(parent, mapOfReferences, DIALOG_TITLE, null, DIALOG_MESSAGE, QUESTION, 0, dialogButtons);
		dialog.setShellStyle(dialog.getShellStyle() | SWT.SHEET);
		return dialog.open() == 0;
    }

    private TreeViewer treeViewer;
    
	@Override
	protected Control createMessageArea(Composite composite) {
		Control control =  super.createMessageArea(composite);
		
		treeViewer = new TreeViewer(composite);
		
		Tree tree = treeViewer.getTree();
		
		GridDataFactory
		.fillDefaults()
		.align(SWT.FILL, SWT.BEGINNING)
		.grab(true, false).span(2, 1)
		.hint(
				convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
				convertHorizontalDLUsToPixels(DIALOG_REFERENCES_TABLE_HEIGHT_300)
		).applyTo(tree);
		
		treeViewer.setContentProvider(new ITreeContentProvider() {

			@Override
			public Object[] getElements(Object inputElement) {
				return mapOfReferences.keySet().toArray();
			}

			@Override
			public Object[] getChildren(Object parentElement) {
				return mapOfReferences.get(parentElement).toArray();
			}

			@Override
			public Object getParent(Object element) {
				// Cannot be computed, since an object can reference more than one object
				// Thus one of the children referencing can be in multiple sub trees.
				return null;
			}

			@Override
			public boolean hasChildren(Object element) {
				return mapOfReferences.containsKey(element);
			}
		});
		
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMPropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		
		ILabelProvider labelProvider = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory) {
			@Override
			public String getText(Object object) {
				if (object instanceof IInstance) {
					IInstance iInstance = (IInstance) object;
					return iInstance.getFullQualifiedInstanceName();
				} else {
					return super.getText(object);
				}
				
			}
		};

		treeViewer.setLabelProvider(labelProvider);
		treeViewer.setInput(mapOfReferences);
		treeViewer.expandToLevel(2);
		
		return control;
	}
}
