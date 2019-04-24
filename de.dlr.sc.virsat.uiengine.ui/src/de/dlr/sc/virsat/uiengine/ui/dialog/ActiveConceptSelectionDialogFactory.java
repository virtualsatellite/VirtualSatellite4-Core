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
import de.dlr.sc.virsat.model.ui.provider.ActiveConceptsContentProvider;
import de.dlr.sc.virsat.model.ui.provider.ActiveConceptsLabelProvider;

/**
 * Creates a selection dialog for active concept selection
 *
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

					private void checkRequired(ActiveConceptConfigurationElement checkedElement) {
						Concept concept = checkedElement.loadConceptFromPlugin();
						for (ConceptImport conceptImport : concept.getImports()) {
							String importedConceptName = conceptImport.getImportedNamespace().replace(".*", "");
							Object[] objects = contentProvider.getElements(getViewer().getInput());
							
							for (Object object : objects) {
								ActiveConceptConfigurationElement acce = (ActiveConceptConfigurationElement) object;
								
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
