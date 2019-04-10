/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.maturity.ui.snippet;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.maturity.util.MaturityConceptHelper;
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
 * Category to describe the maturity of a part or product
 * 
 */
public class UiSnippetTableMaturity extends AUiSnippetTableMaturity {
	public static final int SINGLE_LINE_TABLE_HEIGHT = 35;
	
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
	protected TreeViewerColumn colTRL;
	protected TreeViewerColumn colLevel;
	
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
		colLevel = createTreeViewerColumn(treeViewer, "Maturity Level");
		colTRL = createTreeViewerColumn(treeViewer, "Technical Readiness Level");
	
		// Now wrap the CP so that only CA Container and SEIs are presented by the CP		
		VirSatFilteredWrappedTreeContentProvider cpTree = new VirSatFilteredWrappedTreeContentProvider(new VirSatTransactionalAdapterFactoryContentProvider(adapterFactory).setUpdateCaContainerLabels(true)) {
			@Override
			public Object[] getElements(Object rootObject) {
				Object[] objects = (Object[]) rootObject;
				return new Object [] {objects[0]};
			}
		};
		
		MaturityConceptTableLabelProvider lpTree = new MaturityConceptTableLabelProvider(adapterFactory);
		cpTree.addClassFilter(StructuralElementInstance.class);
		cpTree.addClassFilter(ICategoryAssignmentContainer.class);

		treeViewer.setContentProvider(cpTree);
		treeViewer.setLabelProvider(lpTree);
		treeViewer.setInput(new Object[] {model});

		Composite subSectionBody = toolkit.createComposite(sectionBody);
		subSectionBody.setLayout(new GridLayout(2, true));
		subSectionBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		createExcelButton(toolkit, sectionBody, treeViewer);
		GridData gridDatMaturity = createDefaultGridData();
		gridDatMaturity.horizontalSpan = 1;
		gridDatMaturity.minimumHeight = DEFAULT_TABLE_HEIGHT;
		gridDatMaturity.heightHint = DEFAULT_TABLE_HEIGHT * 2;
		gridDatMaturity.widthHint = DEFAULT_TABLE_WIDTH / 2;
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
	}
		
	/**
	 * The common label provider to display maturitz of the current and nested objects
	 * @author fisc_ph
	 *
	 */
	class MaturityConceptTableLabelProvider extends VirSatTransactionalAdapterFactoryLabelProvider {
		
		private MaturityConceptHelper mch = new MaturityConceptHelper();
		
		/**
		 * Constructor with injected EMF AdapterfActory for correct notification etc.
		 * @param adapterFactory The EMF AdapterFactory to display the correct content
		 */ 
		MaturityConceptTableLabelProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}
		
		@Override
		public Image getColumnImage(Object object, int columnIndex) {
			TreeColumn column = treeViewer.getTree().getColumn(columnIndex);

			if (column != colName.getColumn()) {
				return null;
			}
			
			if (mch.initBeansForObject(object)) {
				if (mch.maturity != null) {
					return super.getColumnImage(mch.maturity.getTypeInstance(), columnIndex);
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
				
				if (mch.maturity != null) {
					redirectNotification(mch.maturity, object, true);
					if (column == colTRL.getColumn()) {
						return mch.maturity.getTrl();
					} else if (column == colLevel.getColumn()) {
						return mch.maturity.getLevel();
					} 
				}				
			}
			return "";
		}
	}
}
