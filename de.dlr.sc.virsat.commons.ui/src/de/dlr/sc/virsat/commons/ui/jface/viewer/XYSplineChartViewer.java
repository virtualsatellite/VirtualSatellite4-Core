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

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.jfree.chart.swt.ChartComposite;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * An XY Spline Chart Viewer based on the idea of JFace viewers
 * It uses EMF based content and Label providers and can
 * therefore handle all updates in the background correctly
 * @author fisc_ph
 *
 */
public class XYSplineChartViewer extends Viewer {
	
	private XYSeriesCollection xyDataSet;
	private ChartComposite chartComposite;
	
	/**
	 * Constructor of the viewer that needs the DataSet to forward notifications from EMF correctly
	 * @param xyDataset the XY Spline DataSet
	 * @param chartComposite the composite used by the pie chart
	 */
	public XYSplineChartViewer(XYSeriesCollection xyDataset, ChartComposite chartComposite) {
		super();
		this.xyDataSet = xyDataset;
		this.chartComposite = chartComposite;
	}
	
	private IStructuredContentProvider contentProvider;
	private ISeriesXYValueLabelProvider labelProvider;
	private Object input;
	
	/**
	 * The ContentProivder to be used
	 * @param contentProvider A ContentProvider based on JFace but EMF ones should be preferred
	 */
	public void setContentProvider(IStructuredContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}
	
	/**
	 * The Label provider that has to follow our own interface 
	 * @param labelProvider The Label provider
	 */
	public void setLabelProvider(ISeriesXYValueLabelProvider labelProvider) {
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
		
		xyDataSet.removeAllSeries();
		
		contentElements.forEach((object) -> {
			String seriesKey = labelProvider.getSeries(object);
			XYSeries xySeries = new XYSeries(seriesKey);
			
			Double [] valuesX = labelProvider.getValuesX(object);
			Double [] valuesY = labelProvider.getValuesY(object);
			
			if (valuesX.length == valuesY.length) {
				for (int i = 0; i < valuesX.length; i++) {
					xySeries.add(valuesX[i], valuesY[i]);
				}
			}
			
			if (!checkDataSetForEntry(seriesKey)) {
				xyDataSet.addSeries(xySeries);
			}
		});
	}

	/**
	 * This method checks if a given Series is already part of the series collection
	 * @param key the key of the series to look for
	 * @return true in case it is already present otherwise false
	 */
	private boolean checkDataSetForEntry(String key) {
		for (int i = 0; i < xyDataSet.getSeriesCount(); i++) {
			@SuppressWarnings("rawtypes")
			Comparable comp = xyDataSet.getSeriesKey(i);
			if (key.equals(comp)) {
				return true;
			}
		}
		
		return false;
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
