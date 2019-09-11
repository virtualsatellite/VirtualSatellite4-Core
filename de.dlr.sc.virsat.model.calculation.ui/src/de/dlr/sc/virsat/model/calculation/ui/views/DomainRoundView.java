/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.ui.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

import de.dlr.sc.virsat.commons.datastructures.DependencyTree;
import de.dlr.sc.virsat.model.calculation.compute.EquationHelper;
import de.dlr.sc.virsat.model.calculation.compute.ExpressionHelper;
import de.dlr.sc.virsat.model.calculation.marker.VirSatEquationMarkerHelper;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain.IResourceEventListener;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatTransactionalAdapterFactoryContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * View that displays which disciplines have to pull and update their project
 * so that all equations are up to date.
 * @author muel_s8
 *
 */

public class DomainRoundView extends ViewPart {

	public static final String VIEW_ID = "de.dlr.sc.virsat.model.calculation.ui.views.domainRoundView";
	private static final String TITLE_TEXT_START_TOKEN = "Domain Round";
	private static final String TABLE_COLUMN_INDEX_NAME = "Index";
	private static final String TABLE_COLUMN_DISCIPLINE_NAME = "Discipline";
	private static final int TABLE_COLUMN_INDEX_SIZE = 50;
	private static final int TABLE_COLUMN_DISCIPLINE_SIZE = 200;

	private static final int COLUMN_INDEX = 0;
	private static final int COLUMN_DISCIPLINE_NAME = 1;
	
	private ComposedAdapterFactory adapterFactory;
	private FormToolkit toolkit;
	private ScrolledForm form;
	private TableViewer tableViewer;
	private boolean isDisposed;
	
	private VirSatResourceSet resourceSet;
	
	private ExpressionHelper exprHelper = new ExpressionHelper();
	private EquationHelper dependencyHelper = new EquationHelper(exprHelper);
	private VirSatEquationMarkerHelper vemHelper = new VirSatEquationMarkerHelper();
	private List<Discipline> disciplineOrder = new ArrayList<Discipline>();
	
	ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart part, ISelection sel) {
			VirSatSelectionHelper selectionHelper = new  VirSatSelectionHelper(sel);
			VirSatTransactionalEditingDomain ed = selectionHelper.getEditingDomain();
			if (ed != null) {
				updateTable(ed.getResourceSet());
			}
		}
	};
	
	private IResourceEventListener eventListener = (Set<Resource> newResources, int event) -> {
		Display.getDefault().asyncExec(() -> {
			updateTable(resourceSet);
		});
	};

	@Override
	public void createPartControl(Composite parent) {
		Display display = parent.getDisplay();
		toolkit = new FormToolkit(display);
		form = toolkit.createScrolledForm(parent);
	
		// Add some heading style to the thing
		toolkit.decorateFormHeading(form.getForm());
		form.setImage(getTitleImage());     
		form.setText(TITLE_TEXT_START_TOKEN);
		
		// Basic grid layout with 1 element per row
		GridLayout layout = new GridLayout(1, true);
		layout.horizontalSpacing = 2;
		layout.verticalSpacing = 2;
		form.getBody().setLayout(layout);
		
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	    Table table = toolkit.createTable(form.getBody(), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
	    table.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
	    table.setHeaderVisible(true);
	    tableViewer = new TableViewer(table);
	    
	    addColumnToTable(TABLE_COLUMN_INDEX_NAME, TABLE_COLUMN_INDEX_SIZE, SWT.NONE);
	    addColumnToTable(TABLE_COLUMN_DISCIPLINE_NAME, TABLE_COLUMN_DISCIPLINE_SIZE, SWT.NONE);
	    
	    tableViewer.setContentProvider(getContentProvider());
	    tableViewer.setLabelProvider(getLabelProvider());
	    
		// Very important to get the scrollBars right.... another story of Eclipse WTF....
		form.reflow(true);
		
		isDisposed = false;
		getSite().getPage().addSelectionListener(listener);
		VirSatTransactionalEditingDomain.addResourceEventListener(eventListener);
	}
	
	/**
	 * Attaches a new column to the current table
	 * @param name the name of the new column
	 * @param width the width of the new column
	 * @param style style of the column
	 */
	private void addColumnToTable(String name, int width, int style) {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.NONE | style);
		column.getColumn().setText(name);
		column.getColumn().setWidth(width);
		column.getColumn().setResizable(true);
		column.getColumn().setMoveable(true);
	}
	
	@Override
	public void dispose() {
		isDisposed = true;
		VirSatTransactionalEditingDomain.removeResourceEventListener(eventListener);
		getSite().getPage().removeSelectionListener(listener);
		super.dispose();
	}

	@Override
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}
	
	/**
	 * Updates the table given the resource set
	 * @param resourceSet the resource set
	 */
	private void updateTable(VirSatResourceSet resourceSet) {
		if (resourceSet != null && isDisposed) {
			return;
		}
		
		this.resourceSet = resourceSet;
		
		List<Equation> equations = getAllEquationsInProject(resourceSet);
		DependencyTree<EObject> tree = dependencyHelper.createDependencyTree(equations);
		List<EObject> linearDependencies = tree.getLinearOrder();
		disciplineOrder.clear();
		
		Discipline current = null;
		for (EObject eObject : linearDependencies) {
			Discipline discipline = RightsHelper.getDiscipline(eObject);
			
			// Check if this object has an assigned discipline and if so
			// If it is a different discipline than the current one
			if (discipline != null && discipline != current && !disciplineOrder.contains(discipline)) {
				
				// Next verify that this object is actually out of date
				Set<IMarker> markers = vemHelper.getAllEvaluationOutOfDateProblemMarkers(eObject);
				boolean isOutOfDate = !markers.isEmpty();
				
				if (isOutOfDate) {
					disciplineOrder.add(discipline);
					current = discipline;
				}
			}
		}
		
		if (disciplineOrder.isEmpty()) {
			form.setText(TITLE_TEXT_START_TOKEN + " - Equations are up to date");
		} else {
			form.setText(TITLE_TEXT_START_TOKEN + " - Equations not up to date; the following discipline have to update");
		}
		
		tableViewer.setInput(disciplineOrder);
		tableViewer.refresh();
	}
	
	/**
	 * the method getAllEquationsInProject crawls through the whole project and gathers all Equations.
	 * It also resolves all proxied elements in our project
	 * @param resourceSet resource set of the project
	 * @return a list of Equations
	 */
	@SuppressWarnings("unchecked")
	private List<Equation> getAllEquationsInProject(VirSatResourceSet resourceSet) {
		List<Equation> equations = new ArrayList<>();
		
		try {
			resourceSet.getRepository();
			EcoreUtil.resolveAll(resourceSet);
			
			if (resourceSet.hasError()) {
				return Collections.EMPTY_LIST;
			}
			
			List<IEquationSectionContainer> equationSectionContainers = VirSatEcoreUtil.getAllContentsOfType(resourceSet, null, IEquationSectionContainer.class, true);
			
			for (IEquationSectionContainer container : equationSectionContainers) {
				EquationSection section = container.getEquationSection();
				if (section != null) {
					equations.addAll(section.getEquations());
				}
			}
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "Could not read active Equation from Repository", e));
		}
	
		return equations;
	};
	
	/**
	 * Get the content provider
	 * @return a content provider that just turns a list to an array
	 */
	public IStructuredContentProvider getContentProvider() {
		return new VirSatTransactionalAdapterFactoryContentProvider(adapterFactory) {
			@Override
			public Object[] getElements(Object inputElement) {
				return ((List<?>) inputElement).toArray(); 
			}
		};
	}

	/**
	 * Get the label provider
	 * @return a label provider for displaying the discipline and the index
	 */
	public ITableLabelProvider getLabelProvider() {
		return new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory) {
			
			@Override
			public Image getColumnImage(Object object, int columnIndex) {
				if (columnIndex == COLUMN_DISCIPLINE_NAME) {
					return super.getColumnImage(object, columnIndex);
				} else {
					return null;
				}
			}
			
			@Override
			public String getColumnText(Object element, int columnIndex) {
				Discipline discipline = (Discipline) element;
				
				switch (columnIndex) {
					case COLUMN_INDEX:
						return "(" + (disciplineOrder.indexOf(discipline) + 1) + ")";
					case COLUMN_DISCIPLINE_NAME:
						return discipline.getName();
					default:
						return super.getColumnText(element, columnIndex);
				}
			}
		};
	}
}
