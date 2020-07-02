/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.power.ui.snippet;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.swt.ChartComposite;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import de.dlr.sc.virsat.commons.ui.jface.viewer.IKeyColumnValueLabelProvider;
import de.dlr.sc.virsat.commons.ui.jface.viewer.IKeyValueLabelProvider;
import de.dlr.sc.virsat.commons.ui.jface.viewer.PieChartViewer;
import de.dlr.sc.virsat.commons.ui.jface.viewer.StackedBarChartViewer;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.budget.power.model.APowerParameters;
import de.dlr.sc.virsat.model.extension.budget.power.ui.snippet.chart.provider.PowerConceptSummariesContentProvider;
import de.dlr.sc.virsat.model.extension.budget.power.util.PowerConceptHelper;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatTransactionalAdapterFactoryContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * Summary of all nested equipment powers
 * 
 */
public class UiSnippetTablePowerSummary extends AUiSnippetTablePowerSummary {
	private static final int SINGLE_LINE_TABLE_HEIGHT = 50;
	private static final int PLOT_VIEW_ANGLE_290 = 290;
	private static final float PLOT_ALPHA = 0.5f;
	private static final int EQUIPMENT_LEVEL = 3;

	private static final int COLOR_GRAY_VAL = 240;
	private static final Color COLOR_GREY = new Color(null, COLOR_GRAY_VAL, COLOR_GRAY_VAL, COLOR_GRAY_VAL);
	private static final String ROW_AVERAGE_POWER = "avgPower";
	private static final String ROW_POWER_MARGIN = "margin";
	private static final String [] ROWS = new String [] { ROW_AVERAGE_POWER, ROW_POWER_MARGIN }; 
	
	protected TreeViewer treeViewer;
	protected Tree treeTable;
	protected TreeViewerColumn colName;
	protected TreeViewerColumn colPower;
	protected TreeViewerColumn colMassWithMargin;
	protected TreeViewerColumn colMargin;
	protected TreeViewerColumn colMinPower;
	protected TreeViewerColumn colMaxPower;
	
	protected PieChartViewer pieChartViewerPowerDistribution;
	protected StackedBarChartViewer barChartViewerPowerMargins;
	
	private Font boldFont;
	private JFreeChart pieChartPowerDistribution;
	private JFreeChart stackedBarChartMargins;
	
	@Override
	protected Table createDefaultTable(FormToolkit toolkit, Composite sectionBody) {
		Table table = super.createDefaultTable(toolkit, sectionBody);
	
		GridData gridDataTable = (GridData) table.getLayoutData();
		gridDataTable.heightHint = SINGLE_LINE_TABLE_HEIGHT;
		
		table.setLayoutData(gridDataTable);
		return table;
	}
	
	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
		
		GridData gridDataTable = createDefaultGridData();
		gridDataTable.horizontalSpan = 2;
		gridDataTable.minimumHeight = DEFAULT_TABLE_HEIGHT;
		gridDataTable.heightHint = DEFAULT_TABLE_HEIGHT;
		gridDataTable.widthHint = DEFAULT_TABLE_WIDTH;

		treeTable = toolkit.createTree(sectionBody, SWT.FULL_SELECTION | SWT.BORDER);
		treeTable.setLinesVisible(true);
		treeTable.setLayoutData(gridDataTable);
		treeTable.setHeaderVisible(true);
		
		treeViewer = new TreeViewer(treeTable);

		colName = createTreeViewerColumn(treeViewer, "Name");
		colPower = createTreeViewerColumn(treeViewer, "Avg Power");
		colMassWithMargin = createTreeViewerColumn(treeViewer, "Avg Power With Margin");
		colMargin = createTreeViewerColumn(treeViewer, "Margin");
		colMinPower = createTreeViewerColumn(treeViewer, "Min Power");
		colMaxPower = createTreeViewerColumn(treeViewer, "Max Power");
		
		// Now wrap the CP so that only CA Container and SEIs are presented by the CP		
		VirSatFilteredWrappedTreeContentProvider cpTree = new VirSatFilteredWrappedTreeContentProvider(new VirSatTransactionalAdapterFactoryContentProvider(adapterFactory).setUpdateCaContainerLabels(true)) {
			@Override
			public Object[] getElements(Object rootObject) {
				Object[] objects = (Object[]) rootObject;
				return new Object [] {objects[0]};
			}
		};
		
		PowerConceptTableLabelProvider lpTree = new PowerConceptTableLabelProvider(adapterFactory);
		cpTree.addClassFilter(StructuralElementInstance.class);
		cpTree.addClassFilter(ICategoryAssignmentContainer.class);

		treeViewer.setContentProvider(cpTree);
		treeViewer.setLabelProvider(lpTree);
		treeViewer.setInput(new Object[] {model});

		createExcelButton(toolkit, sectionBody, treeViewer);
		
		Composite subSectionBody = toolkit.createComposite(sectionBody);
		subSectionBody.setLayout(new GridLayout(2, true));
		subSectionBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		GridData gridDataPieChartMassDistribution = createDefaultGridData();
		gridDataPieChartMassDistribution.horizontalSpan = 1;
		gridDataPieChartMassDistribution.minimumHeight = DEFAULT_TABLE_HEIGHT;
		gridDataPieChartMassDistribution.heightHint = DEFAULT_TABLE_HEIGHT * 2;
		gridDataPieChartMassDistribution.widthHint = DEFAULT_TABLE_WIDTH / 2;

		DefaultPieDataset pieChartDatasetMassDistribution = new DefaultPieDataset();
		pieChartPowerDistribution = createPieChart(pieChartDatasetMassDistribution, "Power Distribution");
		ChartComposite compositePieChartMassDistribution = new ChartComposite(subSectionBody, SWT.NONE, pieChartPowerDistribution, true);
		compositePieChartMassDistribution.setLayoutData(gridDataPieChartMassDistribution);
		
		PowerConceptSummariesContentProvider cpPieChartDistribution = new PowerConceptSummariesContentProvider(adapterFactory);
		PowerConceptTableLabelProvider lpPieChartDistribution = new PowerConceptTableLabelProvider(adapterFactory);
		
		pieChartViewerPowerDistribution = new PieChartViewer(pieChartDatasetMassDistribution, compositePieChartMassDistribution);
		pieChartViewerPowerDistribution.setContentProvider(cpPieChartDistribution);
		pieChartViewerPowerDistribution.setLabelProvider(lpPieChartDistribution);
		
		GridData gridDataPieChartMassMargins = createDefaultGridData();
		gridDataPieChartMassMargins.horizontalSpan = 1;
		gridDataPieChartMassMargins.minimumHeight = DEFAULT_TABLE_HEIGHT;
		gridDataPieChartMassMargins.heightHint = DEFAULT_TABLE_HEIGHT * 2;
		gridDataPieChartMassMargins.widthHint = DEFAULT_TABLE_WIDTH / 2;

		DefaultCategoryDataset stackedBarChartDataSetMargins = new DefaultCategoryDataset();
		stackedBarChartMargins = createStackedBarChart(stackedBarChartDataSetMargins, "Power Margins", "avg power in base-unit");
		ChartComposite compositeStackedBarChartMargin = new ChartComposite(subSectionBody, SWT.NONE, stackedBarChartMargins, true);
		compositeStackedBarChartMargin.setLayoutData(gridDataPieChartMassMargins);
		
		PowerConceptSummariesContentProvider cpBarChartMassMargins = new PowerConceptSummariesContentProvider(adapterFactory);
		PowerConceptTableLabelProvider lpBarChartMassMargins = new PowerConceptTableLabelProvider(adapterFactory);
		
		barChartViewerPowerMargins = new StackedBarChartViewer(stackedBarChartDataSetMargins, compositeStackedBarChartMargin);
		barChartViewerPowerMargins.setContentProvider(cpBarChartMassMargins);
		barChartViewerPowerMargins.setLabelProvider(lpBarChartMassMargins);
		
		treeViewer.addSelectionChangedListener(event -> {
			IStructuredSelection tSelection = (IStructuredSelection) event.getSelection();
			Object object = tSelection.getFirstElement();
			setChartInputsAndTitles(object);
		});
		
		Font font = treeTable.getFont();
		FontDescriptor boldDescriptor = FontDescriptor.createFrom(font).setStyle(SWT.BOLD);
		boldFont = boldDescriptor.createFont(Display.getDefault());
		
		subTitlePowerDistribution = new TextTitle();
		subTitlePowerMargins = new TextTitle();
		
		stackedBarChartMargins.addSubtitle(subTitlePowerMargins);
		pieChartPowerDistribution.addSubtitle(subTitlePowerDistribution);
		setChartInputsAndTitles(model);
	}
	
	private TextTitle subTitlePowerDistribution;
	private TextTitle subTitlePowerMargins;
	
	/**
	 * Method that correctly sets the inputs for the viewers
	 * @param inputObject the root object to be used as input for the viewer
	 */
	private void setChartInputsAndTitles(Object inputObject) {
		PowerConceptHelper pch = new PowerConceptHelper();
		if (pch.initBeansForObject(inputObject) && (pch.powerSummary != null)) {
			subTitlePowerDistribution.setText("Power distribution of equipment and summaries in: " + pch.bSei.getName());
			subTitlePowerMargins.setText("Amount of power of equipment and summaries in: " + pch.bSei.getName());
			barChartViewerPowerMargins.setInput(inputObject);
			pieChartViewerPowerDistribution.setInput(inputObject);
		}
	}
	
	/**
	 * Method to create the PieChart
	 * @param dataset The DatSet to be used with the Pie Chart
	 * @param title the Title to be used
	 * @return The chart for further processing
	 */
	private JFreeChart createPieChart(final PieDataset dataset, final String title) {
		final JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
		final PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(PLOT_VIEW_ANGLE_290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(PLOT_ALPHA);

		return chart;
	}
	
	/**
	 * Method to create the BarChart
	 * @param dataset The DatSet to be used with the Bar Chart
	 * @param title the Title to be used
	 * @param axisLabel label for the axis
	 * @return The chart for further processing
	 */
	private JFreeChart createStackedBarChart(final CategoryDataset dataset, final String title, final String axisLabel) {
		final JFreeChart chart = ChartFactory.createStackedBarChart3D(title, null, axisLabel, dataset);
		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setForegroundAlpha(PLOT_ALPHA);

		return chart;
	}
	 
	/**
	 * Method to create the standard column
	 * @param treeViewer The Viewer to which to add a column
	 * @param columnText The Name of the Column
	 * @return The Column itself
	 */
	private TreeViewerColumn createTreeViewerColumn(TreeViewer treeViewer, String columnText) {
		TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.NONE);
		column.getColumn().setText(columnText);
		column.getColumn().setWidth(DEFAULT_COLUMN_SIZE);
		
		return column;
	}
	
	@Override
	protected void setTableViewerInput() {
		super.setTableViewerInput();
		if (treeViewer != null) {
			treeViewer.setInput(new Object [] {model});
			treeViewer.expandToLevel(EQUIPMENT_LEVEL);
		}
		if ((pieChartViewerPowerDistribution != null) && (barChartViewerPowerMargins != null)) {
			setChartInputsAndTitles(model);
		}
	}
		
	/**
	 * The common label provider to display mass summary and mass equipment CAs correctly
	 * it also supports the interface needed by the pie chart viewers
	 * @author muel_s8
	 *
	 */
	class PowerConceptTableLabelProvider 
		extends VirSatTransactionalAdapterFactoryLabelProvider 
		implements IKeyColumnValueLabelProvider, IKeyValueLabelProvider {
		
		private PowerConceptHelper pch = new PowerConceptHelper();
		
		/**
		 * Constructor with injected EMF AdapterfActory for correct notification etc.
		 * @param adapterFactory The EMF AdapterFactory to display the correct content
		 */ 
		PowerConceptTableLabelProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}
		
		@Override
		public Font getFont(Object object) {
			if (pch.initBeansForObject(object)) {
				if (pch.powerSummary != null) {
					return boldFont;
				} 
			}
			return super.getFont(object);
		}
		
		@Override
		public Color getBackground(Object object) {
			if (pch.initBeansForObject(object)) {
				if (pch.powerSummary != null) {
					return COLOR_GREY;
				} 
			}
			return super.getBackground(object);
		}
		
		@Override
		public Image getColumnImage(Object object, int columnIndex) {
			TreeColumn column = treeViewer.getTree().getColumn(columnIndex);

			if (column != colName.getColumn()) {
				return null;
			}
			
			if (pch.initBeansForObject(object)) {
				APowerParameters powerParameters = pch.getPowerParameters();
				if (powerParameters != null) {
					return super.getColumnImage(powerParameters.getTypeInstance(), columnIndex);
				} else {
					return super.getColumnImage(object, columnIndex);
				}
			}
			
			return super.getColumnImage(object, columnIndex);
		}
		
		@Override
		public String getColumnText(Object object, int columnIndex) {
			TreeColumn column = treeViewer.getTree().getColumn(columnIndex);

			if (pch.initBeansForObject(object)) {
				if (column == colName.getColumn()) {
					return pch.bSei.getName();
				} 
				
				APowerParameters powerParameters = pch.getPowerParameters();
				if (powerParameters != null) {
					redirectNotification(powerParameters, object, true);
					if (column == colPower.getColumn()) {
						return powerParameters.getAvgPowerBean().getValueWithUnit();
					} else if (column == colMargin.getColumn()) {
						return powerParameters.getAvgPowerMarginBean().getValueWithUnit();
					} else if (column == colMassWithMargin.getColumn()) {
						return powerParameters.getAvgPowerWithMarginBean().getValueWithUnit();
					} else if (column == colMinPower.getColumn()) {
						return powerParameters.getMinPowerBean().getValueWithUnit();
					} else if (column == colMaxPower.getColumn()) {
						return powerParameters.getMaxPowerBean().getValueWithUnit();
					} 
				}
			}
			return "";
		}

		@Override
		public String getKey(Object object) {
			return pch.initBeansForObject(object) ? pch.bSei.getName() : null;
		}

		@Override
		public double getValue(String row, Object object) {
			if (pch.initBeansForObject(object)) {
				
				APowerParameters powerParameters = pch.getPowerParameters();
				if (powerParameters != null) {
					redirectNotification(powerParameters, object, true);
					if (ROW_AVERAGE_POWER.equals(row)) {
						return powerParameters.getAvgPowerBean().getValueToBaseUnit();
					} else 	if (ROW_POWER_MARGIN.equals(ROW_POWER_MARGIN)) {
						return powerParameters.getAvgPowerMarginBean().getValueToBaseUnit();
					}
					return powerParameters.getAvgPowerWithMarginBean().getValueToBaseUnit();
				}
			}
			return 0;
		}
		
		@Override
		public String[] getRows(Object object) {
			return ROWS;
		}

		@Override
		public double getValue(Object object) {
			return getValue(null, object);
		}
	}
}
