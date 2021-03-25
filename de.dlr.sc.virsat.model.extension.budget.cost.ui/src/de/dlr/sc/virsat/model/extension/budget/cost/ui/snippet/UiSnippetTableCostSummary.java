/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.ui.snippet;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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
import de.dlr.sc.virsat.model.extension.budget.cost.ui.snippet.chart.provider.CostConceptSummariesContentProvider;
import de.dlr.sc.virsat.model.extension.budget.cost.util.CostConceptHelper;
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
 * Summary of all nested equipment costs
 * 
 */
public class UiSnippetTableCostSummary extends AUiSnippetTableCostSummary {
	private static final int PLOT_VIEW_ANGLE_290 = 290;
	public static final int SINGLE_LINE_TABLE_HEIGHT = 35;
	private static final float PLOT_ALPHA = 0.5f;

	@Override
	protected Table createDefaultTable(FormToolkit toolkit, Composite sectionBody) {
		Table table = super.createDefaultTable(toolkit, sectionBody);

		GridData gridDataTable = (GridData) table.getLayoutData();
		gridDataTable.heightHint = SINGLE_LINE_TABLE_HEIGHT;

		table.setLayoutData(gridDataTable);
		return table;
	}

	protected TreeViewer treeViewer;
	protected Tree treeTable;
	protected TreeViewerColumn colName;
	protected TreeViewerColumn colCost;
	protected TreeViewerColumn colCostWithMargin;
	protected TreeViewerColumn colMargin;

	protected PieChartViewer pieChartViewerCostDistribution;
	protected StackedBarChartViewer barChartViewerCostMargins;

	private Font boldFont;
	private JFreeChart pieChartCostDistribution;
	private JFreeChart stackedBarChartMargins;

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
		colCost = createTreeViewerColumn(treeViewer, "Cost");
		colCostWithMargin = createTreeViewerColumn(treeViewer, "Cost With Margin");
		colMargin = createTreeViewerColumn(treeViewer, "Margin");

		// Now wrap the CP so that only CA Container and SEIs are presented by the CP
		VirSatFilteredWrappedTreeContentProvider cpTree = new VirSatFilteredWrappedTreeContentProvider(
				new VirSatTransactionalAdapterFactoryContentProvider(adapterFactory).setUpdateCaContainerLabels(true)) {
			@Override
			public Object[] getElements(Object rootObject) {
				Object[] objects = (Object[]) rootObject;
				return new Object[] { objects[0] };
			}
		};

		CostConceptTableLabelProvider lpTree = new CostConceptTableLabelProvider(adapterFactory);
		cpTree.addClassFilter(StructuralElementInstance.class);
		cpTree.addClassFilter(ICategoryAssignmentContainer.class);

		treeViewer.setContentProvider(cpTree);
		treeViewer.setLabelProvider(lpTree);
		treeViewer.setInput(new Object[] { model });

		createExcelButton(toolkit, sectionBody, treeViewer);

		Composite subSectionBody = toolkit.createComposite(sectionBody);
		subSectionBody.setLayout(new GridLayout(2, true));
		subSectionBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridData gridDataPieChartCostDistribution = createDefaultGridData();
		gridDataPieChartCostDistribution.horizontalSpan = 1;
		gridDataPieChartCostDistribution.minimumHeight = DEFAULT_TABLE_HEIGHT;
		gridDataPieChartCostDistribution.heightHint = DEFAULT_TABLE_HEIGHT * 2;
		gridDataPieChartCostDistribution.widthHint = DEFAULT_TABLE_WIDTH / 2;

		DefaultPieDataset pieChartDatasetCostDistribution = new DefaultPieDataset();
		pieChartCostDistribution = createPieChart(pieChartDatasetCostDistribution, "Cost Distribution");
		ChartComposite compositePieChartCostDistribution = new ChartComposite(subSectionBody, SWT.NONE,
				pieChartCostDistribution, true);
		compositePieChartCostDistribution.setLayoutData(gridDataPieChartCostDistribution);

		CostConceptSummariesContentProvider cpPieChartDistribution = new CostConceptSummariesContentProvider(
				adapterFactory);
		CostConceptTableLabelProvider lpPieChartDistribution = new CostConceptTableLabelProvider(adapterFactory);

		pieChartViewerCostDistribution = new PieChartViewer(pieChartDatasetCostDistribution,
				compositePieChartCostDistribution);
		pieChartViewerCostDistribution.setContentProvider(cpPieChartDistribution);
		pieChartViewerCostDistribution.setLabelProvider(lpPieChartDistribution);

		GridData gridDataPieChartCostMargins = createDefaultGridData();
		gridDataPieChartCostMargins.horizontalSpan = 1;
		gridDataPieChartCostMargins.minimumHeight = DEFAULT_TABLE_HEIGHT;
		gridDataPieChartCostMargins.heightHint = DEFAULT_TABLE_HEIGHT * 2;
		gridDataPieChartCostMargins.widthHint = DEFAULT_TABLE_WIDTH / 2;

		DefaultCategoryDataset stackedBarChartDataSetMargins = new DefaultCategoryDataset();
		stackedBarChartMargins = createStackedBarChart(stackedBarChartDataSetMargins, "Cost Margins");
		ChartComposite compositeStackedBarChartMargin = new ChartComposite(subSectionBody, SWT.NONE,
				stackedBarChartMargins, true);
		compositeStackedBarChartMargin.setLayoutData(gridDataPieChartCostMargins);

		CostConceptSummariesContentProvider cpBarChartCostMargins = new CostConceptSummariesContentProvider(
				adapterFactory);
		CostConceptTableLabelProvider lpBarChartCostMargins = new CostConceptTableLabelProvider(adapterFactory);

		barChartViewerCostMargins = new StackedBarChartViewer(stackedBarChartDataSetMargins,
				compositeStackedBarChartMargin);
		barChartViewerCostMargins.setContentProvider(cpBarChartCostMargins);
		barChartViewerCostMargins.setLabelProvider(lpBarChartCostMargins);

		treeViewer.addSelectionChangedListener(new CostTableChangeListener());

		Font font = treeTable.getFont();
		FontDescriptor boldDescriptor = FontDescriptor.createFrom(font).setStyle(SWT.BOLD);
		boldFont = boldDescriptor.createFont(Display.getDefault());

		subTitleCostDistribution = new TextTitle();
		subTitleCostMargins = new TextTitle();

		stackedBarChartMargins.addSubtitle(subTitleCostMargins);
		pieChartCostDistribution.addSubtitle(subTitleCostDistribution);
		setChartInputsAndTitles(model);
	}

	private TextTitle subTitleCostDistribution;
	private TextTitle subTitleCostMargins;

	/**
	 * Method that correctly sets the inputs for the viewers
	 * 
	 * @param inputObject the root object to be used as input for the viewer
	 */
	private void setChartInputsAndTitles(Object inputObject) {
		CostConceptHelper cch = new CostConceptHelper();
		if (cch.initBeansForObject(inputObject) && (cch.costSummary != null)) {
			subTitleCostDistribution.setText("Cost distribution of equipment and summaries in: " + cch.bSei.getName());
			subTitleCostMargins
					.setText("Amount of cost and margin of equipment and summaries in: " + cch.bSei.getName());
			barChartViewerCostMargins.setInput(inputObject);
			pieChartViewerCostDistribution.setInput(inputObject);
		}
	}

	/**
	 * The Table Change Listener to update the Selection to the two Pie Charts
	 * 
	 * @author fisc_ph
	 *
	 */
	class CostTableChangeListener implements ISelectionChangedListener {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection tSelection = (IStructuredSelection) event.getSelection();
			Object object = tSelection.getFirstElement();
			setChartInputsAndTitles(object);
		}
	}

	/**
	 * Method to create the PieChart
	 * 
	 * @param dataset The DatSet to be used with the Pie Chart
	 * @param title   the Title to be used
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
	 * Method to create the PieChart
	 * 
	 * @param dataset The DatSet to be used with the Pie Chart
	 * @param title   the Title to be used
	 * @return The chart for further processing
	 */
	private JFreeChart createStackedBarChart(final CategoryDataset dataset, final String title) {
		final JFreeChart chart = ChartFactory.createStackedBarChart3D(title, null, "cost in base-unit", dataset);
		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setForegroundAlpha(PLOT_ALPHA);

		return chart;
	}

	/**
	 * Method to create the standard column
	 * 
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
			treeViewer.setInput(new Object[] { model });
			// CHECKSTYLE:OFF
			treeViewer.expandToLevel(3);
			// CHECKSTYLE:ON
		}
		if ((pieChartViewerCostDistribution != null) && (barChartViewerCostMargins != null)) {
			setChartInputsAndTitles(model);
		}
	}

	/**
	 * The common label provider to display cost summary and cost equipment CAs
	 * correctly it also supports the interface needed by the pie chart viewers
	 * 
	 * @author fisc_ph
	 *
	 */
	class CostConceptTableLabelProvider extends VirSatTransactionalAdapterFactoryLabelProvider
			implements IKeyColumnValueLabelProvider, IKeyValueLabelProvider {

		private CostConceptHelper cch = new CostConceptHelper();

		/**
		 * Constructor with injected EMF AdapterfActory for correct notification etc.
		 * 
		 * @param adapterFactory The EMF AdapterFactory to display the correct content
		 */
		CostConceptTableLabelProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}

		@Override
		public Font getFont(Object object) {
			if (cch.initBeansForObject(object)) {
				if (cch.costSummary != null) {
					return boldFont;
				}
			}
			return super.getFont(object);
		}

		private static final int COLOR_GRAY_VAL = 240;
		private final Color colorGrey = new Color(null, COLOR_GRAY_VAL, COLOR_GRAY_VAL, COLOR_GRAY_VAL);

		@Override
		public Color getBackground(Object object) {
			if (cch.initBeansForObject(object)) {
				if (cch.costSummary != null) {
					return colorGrey;
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

			if (cch.initBeansForObject(object)) {
				if (cch.costSummary != null) {
					return super.getColumnImage(cch.costSummary.getTypeInstance(), columnIndex);
				} else if (cch.costEquipment != null) {
					return super.getColumnImage(cch.costEquipment.getTypeInstance(), columnIndex);
				} else {
					return super.getColumnImage(object, columnIndex);
				}
			}

			return super.getColumnImage(object, columnIndex);
		}

		@Override
		public String getColumnText(Object object, int columnIndex) {
			TreeColumn column = treeViewer.getTree().getColumn(columnIndex);

			if (cch.initBeansForObject(object)) {
				if (column == colName.getColumn()) {
					return cch.bSei.getName();
				}

				if (cch.costSummary != null) {
					redirectNotification(cch.costSummary, object, true);
					if (column == colCost.getColumn()) {
						return cch.costSummary.getCostBean().getValueWithUnit();
					} else if (column == colMargin.getColumn()) {
						return cch.costSummary.getMarginBean().getValueWithUnit();
					} else if (column == colCostWithMargin.getColumn()) {
						return cch.costSummary.getCostWithMarginBean().getValueWithUnit();
					}
				}

				if (cch.costEquipment != null) {
					redirectNotification(cch.costEquipment, object, true);
					if (column == colCost.getColumn()) {
						return cch.costEquipment.getCostBean().getValueWithUnit();
					} else if (column == colMargin.getColumn()) {
						return cch.costEquipment.getMarginBean().getValueWithUnit();
					} else if (column == colCostWithMargin.getColumn()) {
						return cch.costEquipment.getCostWithMarginBean().getValueWithUnit();
					}
				}
			}
			return "";
		}

		@Override
		public String getKey(Object object) {
			return cch.initBeansForObject(object) ? cch.bSei.getName() : null;
		}

		@Override
		public double getValue(String row, Object object) {
			if (cch.initBeansForObject(object)) {
				if (cch.costSummary != null) {
					redirectNotification(cch.costSummary, object, true);
					if (ROW_COST.equals(row)) {
						return cch.costSummary.getCostBean().getValueToBaseUnit();
					} else if (ROW_COST_MARGIN.equals(row)) {
						return cch.costSummary.getCostMarginBean().getValueToBaseUnit();
					}
					return cch.costSummary.getCostWithMarginBean().getValueToBaseUnit();
				}
				if (cch.costEquipment != null) {
					redirectNotification(cch.costEquipment, object, true);
					if (ROW_COST.equals(row)) {
						return cch.costEquipment.getCostBean().getValueToBaseUnit();
					} else if (ROW_COST_MARGIN.equals(row)) {
						return cch.costEquipment.getCostMarginBean().getValueToBaseUnit();
					}
					return cch.costEquipment.getCostWithMarginBean().getValueToBaseUnit();
				}
			}
			return 0;
		}

		private static final String ROW_COST = "cost";
		private static final String ROW_COST_MARGIN = "margin";
		private final String[] rows = new String[] { ROW_COST, ROW_COST_MARGIN };

		@Override
		public String[] getRows(Object object) {
			return rows;
		}

		@Override
		public double getValue(Object object) {
			return getValue(null, object);
		}
	}
}
