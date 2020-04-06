/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapCellLabelProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.build.marker.ui.MarkerImageProvider;
import de.dlr.sc.virsat.build.marker.util.VirSatValidationMarkerHelper;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.uieingine.ui.dnd.ADropSelectionTargetListener;
import de.dlr.sc.virsat.uieingine.ui.dnd.DropHelper;

/**
 * Abstract Class for EStructuralFeatures which are Arrays or Composed and need a Table
 * @author lobe_el
 *
 */
public abstract class AUiSnippetEStructuralFeatureTable extends AUiEStructuralFeatureSectionSnippet {
	
	private static final String SECTION_HEADING = "Table Section for: ";
	
	protected TableViewer tableViewer;
	
	protected static final int DEFAULT_TABLE_SIZE = 200;
	protected static final int DEFAULT_COLUMN_SIZE = 150;
	
	private MarkerImageProvider mip;
	
	/**
	 * Constructor for the Snippet
	 * @param eStructuralFeature The EStructuralfeature the Snippet belongs to
	 */
	public AUiSnippetEStructuralFeatureTable(EStructuralFeature eStructuralFeature) {
		super(eStructuralFeature);
		mip = new MarkerImageProvider(new VirSatValidationMarkerHelper());
	}
	
	/**
	 * @return The Heading of the Section
	 */
	protected String getSectionHeading() {
		return SECTION_HEADING;
	}

	/**
	 * @return The Description of the Section placed below the Heading
	 */
	protected String getSectionDescription() {
		return null;
	}
	
	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
		
		Composite sectionBody = createSectionBody(toolkit, getSectionHeading(), getSectionDescription(), 1);

		// Placing the table
		Table table = createDefaultTable(toolkit, sectionBody);
		table.setLinesVisible(true);
		setUpTableViewer(editingDomain, table);
				
		// Put another composite under the table to add the buttons
		createButtons(toolkit, sectionBody);
		addButtonSelectionListeners(composite, editingDomain);
		
		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener);
		
		createDropTarget(editingDomain, table);
	}

	/**
	 * Ovrridable method to add a Drop target to the given Table
	 * @param editingDomain the editing domain maybe needed to steer the needed commands
	 * @param table the table to which to add the drop target
	 */
	protected void createDropTarget(EditingDomain editingDomain, Table table) {
		DropHelper.createDropTarget(table, new ADropSelectionTargetListener(editingDomain) {
			@Override
			public Command createDropCommand(StructuredSelection selection) {
				Command cmd = AddCommand.create(editingDomain, model, getEStructuralFeature(), selection.toList());
				return cmd;
			}
		});
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);
	}
	
	/**
	 * Standard method to set up the tableViewer
	 * @param editingDomain The EditingDomain to get the Features from
	 * @param table The Table which should be set in the TableViewer
	 */
	protected void setUpTableViewer(EditingDomain editingDomain, Table table) {
		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(getTableContentProvider());
		
		createTableColumns(editingDomain);
		
		if (getTableLabelProvider() != null) {
			tableViewer.setLabelProvider(getTableLabelProvider());
		}
	
		setTableViewerInput(editingDomain);
	}
	
	/**
	 * Method to set up the Columns in the TableViewer
	 * @param editingDomain The EditingDomain where to get the properties from
	 */
	protected abstract void createTableColumns(EditingDomain editingDomain);
	
	/**
	 * Method to get the Content Provider for the table
	 * @return The ContentProvider
	 */
	@SuppressWarnings("rawtypes")
	protected IContentProvider getTableContentProvider() {
		IContentProvider cp = tableViewer.getContentProvider();
		if (cp != null) {
			return cp;
		}
		return new ObservableListContentProvider();
	}
	
	/**
	 * a label provider providing the required input for the table
	 * the standard is without a label provider
	 * @return the label provider
	 */
	protected ITableLabelProvider getTableLabelProvider() {
		return null;
	}
	
	/**
	 * Method to set the Input for the TableViewer
	 * @param editingDomain The EditingDomain to get the Properties from
	 */
	protected void setTableViewerInput(EditingDomain editingDomain) {
		@SuppressWarnings("unchecked")
		IListProperty<EObject, ?> property = EMFEditProperties.list(editingDomain, getEStructuralFeature());
		tableViewer.setInput(property.observe(model));
	}
	
	/**
	 * Method to give a table with the standard format 
	 * @param toolkit The Toolkit which creates the Table
	 * @param sectionBody The Composite in which the table is created mainly the sectionBody
	 * @return The standard table
	 */
	protected Table createDefaultTable(FormToolkit toolkit, Composite sectionBody) {
		GridData gridDataTable = createDefaultGridData();
		gridDataTable.horizontalSpan = 1;
		gridDataTable.minimumHeight = DEFAULT_TABLE_SIZE;
		gridDataTable.heightHint = DEFAULT_TABLE_SIZE;

		Table table = toolkit.createTable(sectionBody, SWT.MULTI | SWT.FULL_SELECTION);
		table.setLayoutData(gridDataTable);
		table.setHeaderVisible(true);
		
		return table;
	}

	/**
	 * Method to create the standard column
	 * @param columnText The Name of the Column
	 * @deprecated use {@link AUiSnippetEStructuralFeatureTable#createDefaultColumn(TableViewer, String)} instead
	 * @return The Column itself
	 */
	@Deprecated
	protected TableViewerColumn createDefaultColumn(String columnText) {
		return createDefaultColumn(tableViewer, columnText);
	}
	
	/**
	 * Method to create the standard column
	 * @param tableViewer The tableViewer to which to add a column to
	 * @param columnText The Name of the Column
	 * @return The Column itself
	 */
	protected TableViewerColumn createDefaultColumn(TableViewer tableViewer, String columnText) {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.NONE);
		column.getColumn().setText(columnText);
		column.getColumn().setWidth(DEFAULT_COLUMN_SIZE);
				
		return column;
	}
	
	/**
	 * The Method to give the standard LabelProvider for the Column
	 * @param editingDomain The EditingDomain to get the Features from
	 * @param withImageProvider Boolean whether there should be an Image in the Cell which symbolizes some errors etc 
	 * @param columnEsfPath The Path to the Feature, most often simply one Attribute
	 * @return The ColumnLabelProvider
	 */
	@SuppressWarnings("rawtypes")
	protected CellLabelProvider getDefaultColumnLabelProvider(EditingDomain editingDomain, boolean withImageProvider, EStructuralFeature... columnEsfPath) {
		return getDefaultColumnLabelProvider(editingDomain, (ObservableListContentProvider) getTableContentProvider(), withImageProvider, columnEsfPath);
	}
	
	/**
	 * The Method to give the standard LabelProvider for the Column
	 * @param editingDomain The EditingDomain to get the Features from
	 * @param contentProvider The ContentProvider which is used for this table and is needed to match the content vs the columns
	 * @param withImageProvider Boolean whether there should be an Image in the Cell which symbolizes some errors etc 
	 * @param columnEsfPath The Path to the Feature, most often simply one Attribute
	 * @return The ColumnLabelProvider
	 */
	@SuppressWarnings("rawtypes")
	protected CellLabelProvider getDefaultColumnLabelProvider(EditingDomain editingDomain, ObservableListContentProvider contentProvider, boolean withImageProvider, EStructuralFeature... columnEsfPath) {
		@SuppressWarnings("unchecked")
		IObservableSet<EObject> knownElements = contentProvider.getKnownElements();
		
		@SuppressWarnings("unchecked")
		IValueProperty<EObject, ?> property = EMFEditProperties.value(editingDomain, FeaturePath.fromList(columnEsfPath));
		IObservableMap<EObject, ?> attributeMap = property.observeDetail(knownElements);
		
		if (withImageProvider) {
			return new ObservableMapCellLabelProvider(attributeMap) {
				@Override
				public void update(ViewerCell cell) {
					super.update(cell);
					
					Image problemImage = mip.getProblemImageForEObject((EObject) cell.getElement());
					EObject eObject = (EObject) cell.getElement();
					
					if (problemImage != null) { 
						cell.setImage(problemImage);
					} else if (eObject != null) {
						IItemLabelProvider labelProvider = (IItemLabelProvider) adapterFactory.adapt(eObject, IItemLabelProvider.class);
						Image image = ExtendedImageRegistry.INSTANCE.getImage(labelProvider.getImage(eObject));
						cell.setImage(image);
					}
				}
			};
		}
		return new ObservableMapCellLabelProvider(attributeMap);
	}
	
	/**
	 * Method to create the Button below the table 
	 * @param toolkit The Toolkit from which the buttons are created 
	 * @param sectionBody The sectionBody in which the Composite with the Buttons is placed
	 * @return The Composite which contains the Buttons
	 */
	protected Composite createButtons(FormToolkit toolkit, Composite sectionBody) {
		Composite compositeButtons = toolkit.createComposite(sectionBody);
		compositeButtons.setLayoutData(new GridData());
		compositeButtons.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		return compositeButtons;
	}
	
	/**
	 * If there are Buttons in the Section they should get SelectionListeners
	 * In most cases below a table there will be Buttons 
	 * @param composite The Composite in which the Buttons are placed
	 * @param editingDomain The EditingDomain 
	 */
	protected void addButtonSelectionListeners(Composite composite, EditingDomain editingDomain) {
		
	}

	@Override
	public void setDataBinding(DataBindingContext dbCtx, EditingDomain editingDomain, EObject model) {
		super.setDataBinding(dbCtx, editingDomain, model);
		setTableViewerInput(editingDomain);
	}
	
	@Override
	public void setSelection(ISelection selection) {
		tableViewer.setSelection(selection);
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		tableViewer.addSelectionChangedListener(listener);
	}

	@Override
	public ISelection getSelection() {
		return tableViewer.getSelection();
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		tableViewer.removeSelectionChangedListener(listener);
	}
	
	private IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {

		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			
			// Step out if event seems to be corrupt
			if (event == null || event.getDelta() == null) {
		        return;
			}
			
			try {
				event.getDelta().accept(new IResourceDeltaVisitor() {
					@Override
					public boolean visit(IResourceDelta delta) throws CoreException {
						// The general Idea is to open the workspace resource in case it is a DVLM resource
						// by loading it with the current VirSatResourceSet. Once loaded we have an EMF Resource
						// which we can crawl for UUID objects. UUID because markers are only attached to UUID objects
						// Once found we trigger all notifiers to this label provider to update on the contained UUID objects
						// the list could be quite long and bigger than the actual displayed objects
						IResource wsResource = delta.getResource();
					
						boolean isCorrectKind = delta.getKind() == IResourceDelta.CHANGED;
						boolean isChangedMarker = (delta.getFlags() & IResourceDelta.MARKERS) != 0;

						// Only react to marker changes, and then try to load the EMF resource
						if (isChangedMarker && isCorrectKind && (wsResource instanceof IFile) && VirSatProjectCommons.isDvlmFile((IFile) wsResource)) {
							Display.getDefault().asyncExec(() -> {
								if (!tableViewer.getTable().isDisposed()) {
									tableViewer.refresh();
								}
							});
						}
						return true;
					}
				});
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed to handle resource change in VirSatTransactionalAdapterFactoryLabelProvider!", e));
			}
		}
	};
	
	@Override
	protected Set<IMarkerHelper> getMarkerHelpers() {
		return Collections.singleton(mip.getVpmHelper());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Set<EObject> getPossiblyMarkedObjects() {
		Set<EObject> possiblyMarkedObjects = new HashSet<>(super.getPossiblyMarkedObjects());
		EStructuralFeature eStructuralFeature = getEStructuralFeature();
		if (eStructuralFeature.isMany()) {
			List<EObject> containedObjects = (List<EObject>) model.eGet(getEStructuralFeature());
			possiblyMarkedObjects.addAll(containedObjects);
		}
		return possiblyMarkedObjects;
	}
}
