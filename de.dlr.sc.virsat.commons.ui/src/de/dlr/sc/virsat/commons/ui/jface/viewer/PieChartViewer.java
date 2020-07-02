/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons.ui.jface.viewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.jfree.chart.swt.ChartComposite;
import org.jfree.data.general.DefaultPieDataset;


/**
 * A Pie Chart Viewer based on the idea of JFace viewers
 * It uses EMF based content and Label providers and can
 * therefore handle all updates in the background correctly
 * @author fisc_ph
 *
 */
public class PieChartViewer extends Viewer {
	
	private DefaultPieDataset defaultPieDataset;
	private ChartComposite chartComposite;
	
	/**
	 * Constructor of the viewer that needs the DataSet to forward notifications from EMF correctly
	 * @param defaultPieDataset the PieChart DataSet
	 * @param chartComposite the composite used by the pie chart
	 */
	public PieChartViewer(DefaultPieDataset defaultPieDataset, ChartComposite chartComposite) {
		super();
		this.defaultPieDataset = defaultPieDataset;
		this.chartComposite = chartComposite;
	}
	
	private IStructuredContentProvider contentProvider;
	private IKeyValueLabelProvider labelProvider;
	private Object input;
	
	/**
	 * The ContentProivder to be used
	 * @param contentProvider A ContentProvider based on JFace but EMF ones hsould be preferred
	 */
	public void setContentProvider(IStructuredContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}
	
	/**
	 * The Label provider that has to follow our own interface 
	 * @param labelProvider The Label provider
	 */
	public void setLabelProvider(IKeyValueLabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}
	
	@Override
	public Control getControl() {
		return chartComposite;
	}

	@Override
	public Object getInput() {
		return input;
	}

	@Override
	public ISelection getSelection() {
		return null;
	}

	@Override
	public void refresh() {
		List<Object> contentElements = Arrays.asList(contentProvider.getElements(input));
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<String> currentKeys = new ArrayList(defaultPieDataset.getKeys());
		
		contentElements.forEach((object) -> {
			String key = labelProvider.getKey(object);
			double value = labelProvider.getValue(object);
			if (key != null) {
				defaultPieDataset.setValue(key, value);
				currentKeys.remove(key);
			}
		});
		
		currentKeys.forEach((key) -> {
			defaultPieDataset.remove(key);
		});
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {
	}

	@Override
	public void setInput(Object input) {
		Object oldInput = input;
		this.input = input;
		contentProvider.inputChanged(this, oldInput, input);
		refresh();
	}
}
