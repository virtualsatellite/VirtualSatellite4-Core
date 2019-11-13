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

import java.util.List;

import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapCellLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;
import de.dlr.sc.virsat.model.ui.editor.input.VirSatUriEditorInput;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.uiengine.ui.dialog.ReferenceSelectionDialog;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetEStructuralFeatureTable;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * This class provides the UI Snippet to manipulate the Inheritance of
 * Structural Elements
 * 
 * @author leps_je
 *
 */
public class UiSnippetIInheritsFrom extends AUiSnippetEStructuralFeatureTable implements IUiSnippet {

	private static final String SECTION_NAME = "Inheritance";
	private static final String SECTION_DESCRIPTION = "Place the Structural Elements of which the current one can inherit from. The table represents the order in which inheritance will occur. ";

	private Button buttonAdd;
	private Button buttonMoveUp;
	private Button buttonMoveDown;
	private Button buttonRemove;
	private Button buttonUpdate;
	private Button buttonDrillDown;

	private static final String BUTTON_ADD_TEXT = "Add Inheritance";
	private static final String BUTTON_MOVEUP_TEXT = "Move Up";
	private static final String BUTTON_MOVEDOWN_TEXT = "Move Down";
	private static final String BUTTON_REMOVE_TEXT = "Remove Inheritance";
	private static final String BUTTON_DRILLDOWN_TEXT = "Drill-Down";
	private static final String BUTTON_UPDATE_TEXT = "Perform Update";

	private static final String COLUMN_TEXT_SEI_NAME = "Structural Element Instance Name";
	private static final String COLUMN_TEXT_SEI_FQN = "Structural Element Instance FQN";
	private static final String COLUMN_TEXT_SE_NAME = "Structural Element Name";

	/**
	 * Constructor for this class to instantiate a UI Snippet
	 */
	public UiSnippetIInheritsFrom() {
		super(InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS);
	}

	@Override
	protected String getSectionHeading() {
		return super.getSectionHeading() + SECTION_NAME;
	}

	@Override
	protected String getSectionDescription() {
		return SECTION_DESCRIPTION;
	}

	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		// Column StructuralElementInstance Name
		TableViewerColumn columnSEI = createDefaultColumn(tableViewer, COLUMN_TEXT_SEI_NAME);
		columnSEI.setLabelProvider(
				getDefaultColumnLabelProvider(editingDomain, true, GeneralPackage.Literals.INAME__NAME));

		TableViewerColumn columnSEIFqn = createDefaultColumn(tableViewer, COLUMN_TEXT_SEI_FQN);
		columnSEIFqn.getColumn().setWidth(DEFAULT_COLUMN_SIZE * 2);

		// A Hack to support an EMFEdit compatible CellLabelProvider that supports
		// EOperation call to FQN
		@SuppressWarnings({ "unchecked", "rawtypes" })
		IObservableSet<EObject> knownElements = ((ObservableListContentProvider) getTableContentProvider())
				.getKnownElements();

		@SuppressWarnings("unchecked")
		IValueProperty<EObject, ?> property = EMFEditProperties.value(editingDomain,
				FeaturePath.fromList(GeneralPackage.Literals.INAME__NAME));
		IObservableMap<EObject, ?> attributeMap = property.observeDetail(knownElements);

		ObservableMapCellLabelProvider labelProvider = new ObservableMapCellLabelProvider(attributeMap) {
			@Override
			public void update(ViewerCell cell) {
				Object element = cell.getElement();
				Object value = attributeMaps[0].get(element);
				if (element instanceof IInstance) {
					IInstance iInstance = (IInstance) element;
					value = iInstance.getFullQualifiedInstanceName();
				}
				cell.setText(value == null ? "" : value.toString()); //$NON-NLS-1$
			}
		};
		columnSEIFqn.setLabelProvider(labelProvider);

		// Column StructuralElement Name
		TableViewerColumn columnSE = createDefaultColumn(tableViewer, COLUMN_TEXT_SE_NAME);
		columnSE.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, false,
				StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__TYPE,
				GeneralPackage.Literals.IQUALIFIED_NAME__NAME));
	}

	@Override
	protected Composite createButtons(FormToolkit toolkit, Composite sectionBody) {
		Composite compositeButtons = super.createButtons(toolkit, sectionBody);

		buttonAdd = toolkit.createButton(compositeButtons, BUTTON_ADD_TEXT, SWT.PUSH);
		buttonMoveUp = toolkit.createButton(compositeButtons, BUTTON_MOVEUP_TEXT, SWT.PUSH);
		buttonMoveDown = toolkit.createButton(compositeButtons, BUTTON_MOVEDOWN_TEXT, SWT.PUSH);
		buttonRemove = toolkit.createButton(compositeButtons, BUTTON_REMOVE_TEXT, SWT.PUSH);
		buttonDrillDown = toolkit.createButton(compositeButtons, BUTTON_DRILLDOWN_TEXT, SWT.PUSH);
		buttonUpdate = toolkit.createButton(compositeButtons, BUTTON_UPDATE_TEXT, SWT.PUSH);

		// Mark the Controls which should be checked for write access
		checkWriteAccess(buttonAdd, buttonMoveDown, buttonMoveUp, buttonRemove);

		return compositeButtons;
	}

	@Override
	protected void addButtonSelectionListeners(Composite composite, EditingDomain editingDomain) {

		buttonAdd.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuralElementInstance sei = (StructuralElementInstance) model;
				StructuralElement se = sei.getType();
				ReferenceSelectionDialog dialog = ReferenceSelectionDialog.createRefernceSelectionDialogForInheritance(
						Display.getCurrent().getActiveShell(), se, adapterFactory);
				dialog.setAllowMultiple(false);
				dialog.setDoubleClickSelects(true);
				dialog.setInput(model.eResource());
				dialog.setInitialSelection(model);
				if (dialog.open() == Dialog.OK) {
					Object selection = dialog.getFirstResult();
					if (selection instanceof StructuralElementInstance) {
						StructuralElementInstance selectedStructuralElementInstance = (StructuralElementInstance) selection;
						Command cmd = AddCommand.create(editingDomain, model,
								InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS,
								selectedStructuralElementInstance);
						editingDomain.getCommandStack().execute(cmd);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		buttonDrillDown.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					VirSatUriEditorInput.openDrillDownEditor(selection.get(0));
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);

			}
		});

		buttonRemove.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					boolean seiSelected = selection.get(0) instanceof StructuralElementInstance;
					boolean seiCalled = model instanceof StructuralElementInstance;
					if (seiSelected && seiCalled) {
						Command cmd = new RecordingCommand((TransactionalEditingDomain) editingDomain,
								"Delete Inheritance Link", "Deletes the Link to the Sei inherited from") {
							@Override
							protected void doExecute() {
								removeExecute(selection);
							}
						};
						editingDomain.getCommandStack().execute(cmd);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		buttonUpdate.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Command cmd = new RecordingCommand((TransactionalEditingDomain) editingDomain, "Update SEI",
						"Updates the SEIs inherited TIs") {
					@Override
					protected void doExecute() {
						updateExecute();
					}
				};
				editingDomain.getCommandStack().execute(cmd);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		buttonMoveUp.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					boolean seiSelected = selection.get(0) instanceof StructuralElementInstance;
					boolean seiCalled = model instanceof StructuralElementInstance;
					if (seiSelected && seiCalled) {
						Command cmd = new RecordingCommand((TransactionalEditingDomain) editingDomain,
								"Delete Inheritance Link", "Deletes the Link to the Sei inherited from") {
							@Override
							protected void doExecute() {
								moveUpExecute(selection);
							}
						};
						editingDomain.getCommandStack().execute(cmd);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		buttonMoveDown.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					boolean seiSelected = selection.get(0) instanceof StructuralElementInstance;
					boolean seiCalled = model instanceof StructuralElementInstance;
					if (seiSelected && seiCalled) {
						Command cmd = new RecordingCommand((TransactionalEditingDomain) editingDomain,
								"Delete Inheritance Link", "Deletes the Link to the Sei inherited from") {
							@Override
							protected void doExecute() {
								moveDownExecute(selection);
							}
						};
						editingDomain.getCommandStack().execute(cmd);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	/**
	 * DoExecute of MoveDown
	 * 
	 * @param selection
	 *            List of StructureElements
	 */
	public void moveDownExecute(List<?> selection) {
		StructuralElementInstance selectedSei = (StructuralElementInstance) selection.get(0);
		StructuralElementInstance openedSei = (StructuralElementInstance) model;
		int oldPositionOfSelectedSei = openedSei.getSuperSeis().indexOf(selectedSei);
		if (oldPositionOfSelectedSei < openedSei.getSuperSeis().size() - 1) {
			openedSei.getSuperSeis().move(oldPositionOfSelectedSei + 1, oldPositionOfSelectedSei);
		}
	}

	/**
	 * DoExecute of MoveUp
	 * 
	 * @param selection
	 *            List of StructureElements
	 */
	public void moveUpExecute(List<?> selection) {
		StructuralElementInstance selectedSei = (StructuralElementInstance) selection.get(0);
		StructuralElementInstance openedSei = (StructuralElementInstance) model;
		int oldPositionOfSelectedSei = openedSei.getSuperSeis().indexOf(selectedSei);
		if (oldPositionOfSelectedSei > 0) {
			openedSei.getSuperSeis().move(oldPositionOfSelectedSei - 1, oldPositionOfSelectedSei);
		}
	}

	/**
	 * DoExecute of Update
	 */
	public void updateExecute() {
		ICategoryAssignmentContainer icac = (ICategoryAssignmentContainer) model;
		IInheritanceCopier ic = new InheritanceCopier();
		ResourceSet resSet = icac.eResource().getResourceSet();
		if (resSet instanceof VirSatResourceSet) {
			VirSatResourceSet vsResSet = (VirSatResourceSet) resSet;
			Repository repo = vsResSet.getRepository();
			ic.updateInOrderFrom((StructuralElementInstance) icac, repo, new NullProgressMonitor());
		}
	}

	/**
	 * DoExecute of Remove
	 * 
	 * @param selection
	 *            List of StructureElements
	 */
	public void removeExecute(List<?> selection) {
		StructuralElementInstance selectedSei = (StructuralElementInstance) selection.get(0);
		StructuralElementInstance openedSei = (StructuralElementInstance) model;
		openedSei.getSuperSeis().remove(selectedSei);
	}

	@Override
	public boolean isActive(EObject model) {
		boolean canInherit = true;

		if (model instanceof StructuralElementInstance) {
			StructuralElementInstance sei = (StructuralElementInstance) model;
			StructuralElement se = sei.getType();
			canInherit = !se.getCanInheritFrom().isEmpty() || se.isIsCanInheritFromAll();
		}

		return model instanceof IInheritsFrom && canInherit;
	}

}
