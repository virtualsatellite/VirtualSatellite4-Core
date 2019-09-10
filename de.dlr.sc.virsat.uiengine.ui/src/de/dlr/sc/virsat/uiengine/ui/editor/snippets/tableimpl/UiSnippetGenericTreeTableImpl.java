/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets.tableimpl;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetGenericCategoryAssignmentTable;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetGenericCategoryAssignmentTable.UiSnippetGenericTableImpl;

/**
 * This class provides the implementation for a tree table. Tree tables
 * are used very intensively in CEF
 * @author fisc_ph
 *
 */
public class UiSnippetGenericTreeTableImpl extends UiSnippetGenericTableImpl {

	/**
	 * Constructor for the implementation
	 * @param genericCategoryTable the pinter to the current Table
	 */
	public UiSnippetGenericTreeTableImpl(AUiSnippetGenericCategoryAssignmentTable genericCategoryTable) {
		// This is some awesome java hack. This call to the constructor hands over the pointer to the
		// current table in the implementation of the default table implementation. Thus the table implementation
		// as an inline class of the category assignment table, can access the method and super methods of the correct
		// instance. This call basically sets the AUiSnippetGenericCategoryAssignmentTable.this = genericCategoryTable
		genericCategoryTable.super();
	}

	@Override
	protected ColumnViewer createColumnViewer(FormToolkit toolkit) {
		Tree tree = createDefaultTree(toolkit);
		return new TreeViewer(tree);
	}

	/**
	 * Method to create the tree table column
	 * @param columnText The Name of the Column
	 * @return The Column itself
	 */
	@Override
	protected ViewerColumn createDefaultColumn(String columnText) {
		TreeViewerColumn column = new TreeViewerColumn((TreeViewer) getColumnViewer(), SWT.NONE);
		column.getColumn().setText(columnText);
		column.getColumn().setWidth(AUiSnippetGenericCategoryAssignmentTable.DEFAULT_COLUMN_SIZE);
		
		return column;
	}

	/**
	 * Creates SWT Tree using the provided toolkit
	 * @param toolkit the toolkit to create SWT widget
	 * @return tree
	 */
	protected Tree createDefaultTree(FormToolkit toolkit) {
		GridData gridDataTable = new GridData();
		gridDataTable.horizontalAlignment = GridData.FILL;
		gridDataTable.grabExcessHorizontalSpace = true;
		gridDataTable.horizontalSpan = 2;
		gridDataTable.minimumHeight = AUiSnippetGenericCategoryAssignmentTable.DEFAULT_TABLE_HEIGHT;
		gridDataTable.heightHint = AUiSnippetGenericCategoryAssignmentTable.DEFAULT_TABLE_HEIGHT;
		gridDataTable.widthHint = AUiSnippetGenericCategoryAssignmentTable.DEFAULT_TABLE_WIDTH;
	
		Tree tree = toolkit.createTree(getSectionBody(), SWT.FULL_SELECTION | SWT.BORDER);
		tree.setLinesVisible(true);
		tree.setLayoutData(gridDataTable);
		tree.setHeaderVisible(true);
		return tree;
	}
}
