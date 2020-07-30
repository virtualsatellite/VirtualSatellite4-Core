/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.commonSorter;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;

/**
 * This class provides capabilties to sort table columns according to
 * the text given by a label provider and a sorting direction (ascending, descending).
 *
 */

public class LabelColumnSorter extends ViewerComparator {
	
	private TableViewer tableViewer;
	private ITableLabelProvider labelProvider;
	
	private int lastSelectedColumnIndex;
	private boolean isAscending;

	public LabelColumnSorter(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}
	
	public void setLabelProvider(ITableLabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	@Override
	public int compare(Viewer viewer, Object o1, Object o2) {
		String text1 = labelProvider.getColumnText(o1, lastSelectedColumnIndex);
		String text2 = labelProvider.getColumnText(o2, lastSelectedColumnIndex);
		
		if (isAscending) {
			return text1.compareTo(text2);
		} else {
			return text2.compareTo(text1);
		}
	}

	/**
	 * React to the selection of a column
	 * @param column the the selected column
	 * @param columnIndex the index of the selected column
	 */
	public void onSelectColumn(TableColumn column, int columnIndex) {
		if (columnIndex == lastSelectedColumnIndex) {
			// We selected the same column again, so we now change the sorting order
			isAscending = !isAscending;
		} else {
			// We selected a new column, so now we sort according to that one
			lastSelectedColumnIndex = columnIndex;
			isAscending = true;
		}
		
		tableViewer.getTable().setSortColumn(column);
		tableViewer.getTable().setSortDirection(isAscending ? SWT.UP : SWT.DOWN);
		
		// The comparator requires the label provider, so if that is not set at this point, we must not refresh
		if (labelProvider != null) {
			// We only want to update the order (and not the labels) so we turn label update off
			tableViewer.refresh(false);
		}
	}
}
