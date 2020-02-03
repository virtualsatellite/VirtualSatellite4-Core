/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.dialog;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListSelectionDialog;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.ui.provider.ActiveConceptsContentProvider;
import de.dlr.sc.virsat.model.ui.provider.ActiveConceptsLabelProvider;

/**
 * Creates a selection dialog for active concept selection.
 * It also deals as a factory for creating ActiveConfigurationElements
 * for the selected Concepts.
 */
public class ActiveConceptSelectionDialogFactory {
	
	/**
	 * Creates a selection dialog for activating a concept
	 * @param parent the parent shell
	 * @param repository the repository
	 * @param message the dialog message
	 * @return the selection dialog
	 */
	public static ListSelectionDialog createActiveConceptSelectionDialog(Shell parent, Repository repository, String message) {
		ActiveConceptsContentProvider contentProvider = new ActiveConceptsContentProvider();
		contentProvider.setRepository(repository);
		ActiveConceptsLabelProvider labelProvider = new ActiveConceptsLabelProvider();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		
		ListSelectionDialog activeConceptSelectionDialog = new ListSelectionDialog(parent, registry, contentProvider, labelProvider, message) {
			@Override
			protected Control createDialogArea(Composite parent) {
				Control control = super.createDialogArea(parent);
				
				getViewer().addCheckStateListener(new ICheckStateListener() {
					@Override
					public void checkStateChanged(CheckStateChangedEvent event) {
						if (event.getChecked()) {
							Object checkedElement = event.getElement();
							checkRequired((ActiveConceptConfigurationElement) checkedElement);
						}
					}

					/**
					 * recursive method to check all required imports and imports of imports of a
					 * given ActiveConceptConfigurationElement
					 * @param checkedElement the ActiveConfigurationElement with a concept for which to tick the downstream imports
					 */
					private void checkRequired(ActiveConceptConfigurationElement checkedElement) {
						// actually load the concept
						Concept concept = checkedElement.loadConceptFromPlugin();
						
						// Loop over all imports and identify the actual concept
						for (ConceptImport conceptImport : concept.getImports()) {
							String importedConceptName = ActiveConceptHelper.getConceptFromImport(conceptImport);

							// Now loop over all possible objects in the list of possible concepts in the dialog
							Object[] objects = contentProvider.getElements(getViewer().getInput());
							for (Object object : objects) {
								ActiveConceptConfigurationElement acce = (ActiveConceptConfigurationElement) object;
								
								// If the object in the dialogs list matches the name of the imported namespace,
								// than tick it and check if that one requires further imports.
								if (importedConceptName.equals(acce.getId())) {
									getViewer().setChecked(acce, true);
									checkRequired(acce);
								}
							}
						}
					}
				});
				
				return control;
			}
		};
		
		return activeConceptSelectionDialog;
	}
	
	/**
	 * For checkstyle
	 */
	private ActiveConceptSelectionDialogFactory() { };
}
