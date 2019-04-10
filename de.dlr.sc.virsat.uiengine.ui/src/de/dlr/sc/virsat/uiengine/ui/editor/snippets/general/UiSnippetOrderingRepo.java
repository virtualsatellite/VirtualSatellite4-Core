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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetEStructuralFeatureTable;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * This class provides the UI Snippet to manipulate the Inheritance of Structural Elements
 * @author fisc_ph
 *
 */
public class UiSnippetOrderingRepo extends AUiSnippetEStructuralFeatureTable implements IUiSnippet {

	private static final String SECTION_NAME = "Structural Element Instance Ordering";
	private static final String SECTION_DESCRIPTION = "Use this Table to change the order of Structural Element Instances on the next level";

	private Button buttonMoveUpSei;
	private Button buttonMoveDownSei;

	private static final String BUTTON_MOVEUP_TEXT = "Move Up";
	private static final String BUTTON_MOVEDOWN_TEXT = "Move Down";
	private static final String COLUMN_TEXT_SEI_NAME = "Structural Element Instance Name";
	private static final String COLUMN_TEXT_SE_NAME = "Structural Element Name";
	
	/**
	 * Constructor for this class to instantiate a UI Snippet
	 */
	public UiSnippetOrderingRepo() {
		super(DVLMPackage.Literals.REPOSITORY__ROOT_ENTITIES);
	}

	@Override
	protected void createDropTarget(EditingDomain editingDomain, Table table) {
		// Don't create a drop target for this UiWidget Snippet, hence don't call the super
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
		columnSEI.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, true, GeneralPackage.Literals.INAME__NAME));
				
		// Column StructuralElement Name
		TableViewerColumn columnSE = createDefaultColumn(tableViewer, COLUMN_TEXT_SE_NAME);
		columnSE.setLabelProvider(getDefaultColumnLabelProvider(editingDomain, false, StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__TYPE, GeneralPackage.Literals.IQUALIFIED_NAME__NAME));
	}

	@Override
	protected Composite createButtons(FormToolkit toolkit, Composite sectionBody) {
		Composite compositeButtonsSei = super.createButtons(toolkit, sectionBody);
	
		buttonMoveUpSei = toolkit.createButton(compositeButtonsSei, BUTTON_MOVEUP_TEXT, SWT.PUSH);
		buttonMoveDownSei = toolkit.createButton(compositeButtonsSei, BUTTON_MOVEDOWN_TEXT, SWT.PUSH);

		// Mark the Controls which should be checked for write access
		checkWriteAccess(buttonMoveDownSei, buttonMoveUpSei);
		
		return compositeButtonsSei;
	}

	@Override
	protected void addButtonSelectionListeners(Composite composite, EditingDomain editingDomain) {
		buttonMoveUpSei.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					boolean seiSelected = selection.get(0) instanceof StructuralElementInstance;
					boolean isRepoModel = model instanceof Repository;
					if (seiSelected && isRepoModel) {
						Command cmd = new RecordingCommand((TransactionalEditingDomain) editingDomain, "Move Sei Up In Order") {
							@Override
							protected void doExecute() {
								StructuralElementInstance selectedSei = (StructuralElementInstance) selection.get(0);
								Repository openedRepo = (Repository) model;
								int oldPositionOfSelectedSei = openedRepo.getRootEntities().indexOf(selectedSei);
								if (oldPositionOfSelectedSei > 0) {
									openedRepo.getRootEntities().move(oldPositionOfSelectedSei - 1, oldPositionOfSelectedSei);
								}
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
		
		buttonMoveDownSei.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					boolean seiSelected = selection.get(0) instanceof StructuralElementInstance;
					boolean isRepoModel = model instanceof Repository;
					if (seiSelected && isRepoModel) {
						Command cmd = new RecordingCommand((TransactionalEditingDomain) editingDomain, "Move Sei Down In order") {
							@Override
							protected void doExecute() {
								StructuralElementInstance selectedSei = (StructuralElementInstance) selection.get(0);
								Repository openedSei = (Repository) model;
								int oldPositionOfSelectedSei = openedSei.getRootEntities().indexOf(selectedSei);
								if (oldPositionOfSelectedSei < openedSei.getRootEntities().size() - 1) {
									openedSei.getRootEntities().move(oldPositionOfSelectedSei + 1, oldPositionOfSelectedSei);
								}
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
	
	@Override
	public boolean isActive(EObject model) {
		return model instanceof Repository;
	}
}
