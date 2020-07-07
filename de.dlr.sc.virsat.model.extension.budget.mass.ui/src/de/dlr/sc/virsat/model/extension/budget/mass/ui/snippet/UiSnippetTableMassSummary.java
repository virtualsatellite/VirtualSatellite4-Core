/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.mass.ui.snippet;

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
import de.dlr.sc.virsat.model.extension.budget.mass.ui.snippet.chart.provider.MassConceptSummariesContentProvider;
import de.dlr.sc.virsat.model.extension.budget.mass.util.MassConceptHelper;
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
 * Summary of all nested equipment masses
 * 
 */
public class UiSnippetTableMassSummary extends AUiSnippetTableMassSummary {
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
	protected TreeViewerColumn colMass;
	protected TreeViewerColumn colMassWithMargin;
	protected TreeViewerColumn colMargin;
	
	protected PieChartViewer pieChartViewerMassDistribution;
	protected StackedBarChartViewer barChartViewerMassMargins;
	
	private Font boldFont;
	private JFreeChart pieChartMassDistribution;
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
		colMass = createTreeViewerColumn(treeViewer, "Mass");
		colMassWithMargin = createTreeViewerColumn(treeViewer, "Mass With Margin");
		colMargin = createTreeViewerColumn(treeViewer, "Margin");
			
		// Now wrap the CP so that only CA Container and SEIs are presented by the CP		
		VirSatFilteredWrappedTreeContentProvider cpTree = new VirSatFilteredWrappedTreeContentProvider(new VirSatTransactionalAdapterFactoryContentProvider(adapterFactory).setUpdateCaContainerLabels(true)) {
			@Override
			public Object[] getElements(Object rootObject) {
				Object[] objects = (Object[]) rootObject;
				return new Object [] {objects[0]};
			}
		};

		MassConceptTableLabelProvider lpTree = new MassConceptTableLabelProvider(adapterFactory);
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
		pieChartMassDistribution = createPieChart(pieChartDatasetMassDistribution, "Mass Distribution");
		ChartComposite compositePieChartMassDistribution = new ChartComposite(subSectionBody, SWT.NONE, pieChartMassDistribution, true);
		compositePieChartMassDistribution.setLayoutData(gridDataPieChartMassDistribution);
		
		MassConceptSummariesContentProvider cpPieChartDistribution = new MassConceptSummariesContentProvider(adapterFactory);
		MassConceptTableLabelProvider lpPieChartDistribution = new MassConceptTableLabelProvider(adapterFactory);
		
		pieChartViewerMassDistribution = new PieChartViewer(pieChartDatasetMassDistribution, compositePieChartMassDistribution);
		pieChartViewerMassDistribution.setContentProvider(cpPieChartDistribution);
		pieChartViewerMassDistribution.setLabelProvider(lpPieChartDistribution);
		
		GridData gridDataPieChartMassMargins = createDefaultGridData();
		gridDataPieChartMassMargins.horizontalSpan = 1;
		gridDataPieChartMassMargins.minimumHeight = DEFAULT_TABLE_HEIGHT;
		gridDataPieChartMassMargins.heightHint = DEFAULT_TABLE_HEIGHT * 2;
		gridDataPieChartMassMargins.widthHint = DEFAULT_TABLE_WIDTH / 2;

		DefaultCategoryDataset stackedBarChartDataSetMargins = new DefaultCategoryDataset();
		stackedBarChartMargins = createStackedBarChart(stackedBarChartDataSetMargins, "Mass Margins");
		ChartComposite compositeStackedBarChartMargin = new ChartComposite(subSectionBody, SWT.NONE, stackedBarChartMargins, true);
		compositeStackedBarChartMargin.setLayoutData(gridDataPieChartMassMargins);
		
		MassConceptSummariesContentProvider cpBarChartMassMargins = new MassConceptSummariesContentProvider(adapterFactory);
		MassConceptTableLabelProvider lpBarChartMassMargins = new MassConceptTableLabelProvider(adapterFactory);
		
		barChartViewerMassMargins = new StackedBarChartViewer(stackedBarChartDataSetMargins, compositeStackedBarChartMargin);
		barChartViewerMassMargins.setContentProvider(cpBarChartMassMargins);
		barChartViewerMassMargins.setLabelProvider(lpBarChartMassMargins);
		
		treeViewer.addSelectionChangedListener(new MassTableChangeListener());

		Font font = treeTable.getFont();
		FontDescriptor boldDescriptor = FontDescriptor.createFrom(font).setStyle(SWT.BOLD);
		boldFont = boldDescriptor.createFont(Display.getDefault());
		
		subTitleMassDistribution = new TextTitle();
		subTitleMassMargins = new TextTitle();
		
		stackedBarChartMargins.addSubtitle(subTitleMassMargins);
		pieChartMassDistribution.addSubtitle(subTitleMassDistribution);
		setChartInputsAndTitles(model);
	}
	
	private TextTitle subTitleMassDistribution;
	private TextTitle subTitleMassMargins;
	
	/**
	 * Method that correctly sets the inputs for the viewers
	 * @param inputObject the root object to be used as input for the viewer
	 */
	private void setChartInputsAndTitles(Object inputObject) {
		MassConceptHelper mch = new MassConceptHelper();
		if (mch.initBeansForObject(inputObject) && (mch.massSummary != null)) {
			subTitleMassDistribution.setText("Mass distribution of equipment and summaries in: " + mch.bSei.getName());
			subTitleMassMargins.setText("Amount of mass and margin of equipment and summaries in: " + mch.bSei.getName());
			barChartViewerMassMargins.setInput(inputObject);
			pieChartViewerMassDistribution.setInput(inputObject);
		}
	}
	
	/**
	 * The Table Change Listener to update the Selection to the two Pie Charts
	 * @author fisc_ph
	 *
	 */
	class MassTableChangeListener implements ISelectionChangedListener {
		
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection tSelection = (IStructuredSelection) event.getSelection();
			Object object = tSelection.getFirstElement();
			setChartInputsAndTitles(object);
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
	 * Method to create the PieChart
	 * @param dataset The DatSet to be used with the Pie Chart
	 * @param title the Title to be used
	 * @return The chart for further processing
	 */
	private JFreeChart createStackedBarChart(final CategoryDataset dataset, final String title) {
		final JFreeChart chart = ChartFactory.createStackedBarChart3D(title, null, "mass in base-unit", dataset);
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
			//CHECKSTYLE:OFF
			treeViewer.expandToLevel(3);		
			//CHECKSTYLE:ON
		}
		if ((pieChartViewerMassDistribution != null) && (barChartViewerMassMargins != null)) {
			setChartInputsAndTitles(model);
		}
	}
		
	/**
	 * The common label provider to display mass summary and mass equipment CAs correctly
	 * it also supports the interface needed by the pie chart viewers
	 * @author fisc_ph
	 *
	 */
	class MassConceptTableLabelProvider 
		extends VirSatTransactionalAdapterFactoryLabelProvider 
		implements IKeyColumnValueLabelProvider, IKeyValueLabelProvider {
		
		private MassConceptHelper mch = new MassConceptHelper();
		
		/**
		 * Constructor with injected EMF AdapterfActory for correct notification etc.
		 * @param adapterFactory The EMF AdapterFactory to display the correct content
		 */ 
		MassConceptTableLabelProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}
		
		@Override
		public Font getFont(Object object) {
			if (mch.initBeansForObject(object)) {
				if (mch.massSummary != null) {
					return boldFont;
				} 
			}
			return super.getFont(object);
		}
		
		private static final int COLOR_GRAY_VAL = 240;
		private final Color colorGrey = new Color(null, COLOR_GRAY_VAL, COLOR_GRAY_VAL, COLOR_GRAY_VAL);
		
		@Override
		public Color getBackground(Object object) {
			if (mch.initBeansForObject(object)) {
				if (mch.massSummary != null) {
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
			
			if (mch.initBeansForObject(object)) {
				if (mch.massSummary != null) {
					return super.getColumnImage(mch.massSummary.getTypeInstance(), columnIndex);
				} else if (mch.massEquipment != null) {
					return super.getColumnImage(mch.massEquipment.getTypeInstance(), columnIndex);
				} else {
					return super.getColumnImage(object, columnIndex);
				}
			}
			
			return super.getColumnImage(object, columnIndex);
		}
		
		@Override
		public String getColumnText(Object object, int columnIndex) {
			TreeColumn column = treeViewer.getTree().getColumn(columnIndex);

			if (mch.initBeansForObject(object)) {
				if (column == colName.getColumn()) {
					return mch.bSei.getName();
				} 
				
				if (mch.massSummary != null) {
					redirectNotification(mch.massSummary, object, true);
					if (column == colMass.getColumn()) {
						return mch.massSummary.getMassBean().getValueWithUnit();
					} else if (column == colMargin.getColumn()) {
						return mch.massSummary.getMarginBean().getValueWithUnit();
					} else if (column == colMassWithMargin.getColumn()) {
						return mch.massSummary.getMassWithMarginBean().getValueWithUnit();
					} 
				}
				
				if (mch.massEquipment != null) {
					redirectNotification(mch.massEquipment, object, true);
					if (column == colMass.getColumn()) {
						return mch.massEquipment.getMassBean().getValueWithUnit();
					} else if (column == colMargin.getColumn()) {
						return mch.massEquipment.getMarginBean().getValueWithUnit();
					} else if (column == colMassWithMargin.getColumn()) {
						return mch.massEquipment.getMassWithMarginBean().getValueWithUnit();
					} 
				}
			}
			return "";
		}

		@Override
		public String getKey(Object object) {
			return mch.initBeansForObject(object) ? mch.bSei.getName() : null;
		}

		@Override
		public double getValue(String row, Object object) {
			if (mch.initBeansForObject(object)) {
				if (mch.massSummary != null) {
					redirectNotification(mch.massSummary, object, true);
					if (ROW_MASS.equals(row)) {
						return mch.massSummary.getMassBean().getValueToBaseUnit();
					} else 	if (ROW_MASS_MARGIN.equals(row)) {
						return mch.massSummary.getMassMarginBean().getValueToBaseUnit();
					}
					return mch.massSummary.getMassWithMarginBean().getValueToBaseUnit();
				}
				if (mch.massEquipment != null) {
					redirectNotification(mch.massEquipment, object, true);
					if (ROW_MASS.equals(row)) {
						return mch.massEquipment.getMassBean().getValueToBaseUnit();
					} else 	if (ROW_MASS_MARGIN.equals(row)) {
						return mch.massEquipment.getMassMarginBean().getValueToBaseUnit();
					}
					return mch.massEquipment.getMassWithMarginBean().getValueToBaseUnit();
				}
			}
			return 0;
		}

		private static final String ROW_MASS = "mass";
		private static final String ROW_MASS_MARGIN = "margin";
		private final String [] rows = new String [] {ROW_MASS, ROW_MASS_MARGIN}; 
		
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
