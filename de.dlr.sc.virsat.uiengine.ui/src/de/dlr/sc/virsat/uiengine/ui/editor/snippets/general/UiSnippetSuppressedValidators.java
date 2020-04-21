/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets.general;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.validator.RepoValidatorsInstantiator;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetEStructuralFeatureTable;

/**
 * Snippet for adding suppressed validators to a Repository.
 */
public class UiSnippetSuppressedValidators extends AUiSnippetEStructuralFeatureTable {

	private static final String SECTION_NAME = "Suppressed Validators";

	private Button buttonAdd;
	private Button buttonRemove;

	private static final String BUTTON_ADD_TEXT = "Add";
	private static final String BUTTON_REMOVE_TEXT = "Remove";

	private static final String COLUMN_TEXT_VALIDATOR = "Validator";

	public UiSnippetSuppressedValidators() {
		super(DVLMPackage.Literals.REPOSITORY__SUPPRESSED_VALIDATORS);
	}

	@Override
	protected String getSectionHeading() {
		return super.getSectionHeading() + SECTION_NAME;
	}

	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		TableViewerColumn columnValidatorClass = createDefaultColumn(tableViewer, COLUMN_TEXT_VALIDATOR);
		columnValidatorClass.setLabelProvider(new ColumnLabelProvider());
	}

	@Override
	protected Composite createButtons(FormToolkit toolkit, Composite sectionBody) {
		Composite compositeButtons = super.createButtons(toolkit, sectionBody);

		buttonAdd = toolkit.createButton(compositeButtons, BUTTON_ADD_TEXT, SWT.PUSH);
		buttonRemove = toolkit.createButton(compositeButtons, BUTTON_REMOVE_TEXT, SWT.PUSH);
		checkWriteAccess(buttonAdd, buttonRemove);

		return compositeButtons;
	}

	@Override
	protected void addButtonSelectionListeners(Composite composite, EditingDomain editingDomain) {
		buttonAdd.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<String> validatorClasses = getActiveValidatorClasses((Repository) model);
				ListSelectionDialog dialog = new ListSelectionDialog(composite.getShell(), validatorClasses,
						ArrayContentProvider.getInstance(), new LabelProvider(), "Select a Validator to suppress");

				if (dialog.open() == Dialog.OK) {
					List<?> selectedValidators = Arrays.asList(dialog.getResult());
					if (!selectedValidators.isEmpty()) {
						Command addSuppressedValidatorsCommand = AddCommand.create(editingDomain, model,
								DVLMPackage.eINSTANCE.getRepository_SuppressedValidators(), selectedValidators);
						editingDomain.getCommandStack().execute(addSuppressedValidatorsCommand);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			private List<String> getActiveValidatorClasses(Repository repository) {
				RepoValidatorsInstantiator validatorsInstantiator = new RepoValidatorsInstantiator(repository);
				return Stream
						.concat(validatorsInstantiator.getRepoValidators().stream(),
								validatorsInstantiator.getSeiValidators().stream())
						.map(v -> v.getClass().getName()).sorted().collect(Collectors.toList());
			}
		});

		buttonRemove.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					Command removeSuppressedValidatorsCommand = new RemoveCommand(editingDomain, model,
							DVLMPackage.eINSTANCE.getRepository_SuppressedValidators(), selection);
					editingDomain.getCommandStack().execute(removeSuppressedValidatorsCommand);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	@Override
	protected Set<EObject> getPossiblyMarkedObjects() {
		return Collections.emptySet();
	}
}
