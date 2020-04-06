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

import java.util.Collection;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.commands.ICommandImageService;
import org.eclipse.ui.forms.widgets.FormToolkit;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor;
import de.dlr.sc.virsat.model.ui.editor.input.VirSatUriEditorInput;
import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.uiengine.ui.editor.GenericActionBarContributor;
import de.dlr.sc.virsat.uiengine.ui.editor.GenericEditor;
import de.dlr.sc.virsat.uiengine.ui.excel.ExcelExportWizard;

/**
 * abstract class ui snippet generic table implements the ui snippet for generic tables
 */
public abstract class AUiSnippetGenericTable extends AUiCategorySectionSnippet {

	protected static final String SECTION_HEADING_PREFIX = "Section for: ";
	
	protected ColumnViewer columnViewer;
	protected int style;

	private Button buttonRemove;
	private Button buttonAdd;
	private Button buttonEditor;
	private Button exportToExcel;
	private Button checkBoxAddRemoveCategoryAssignment;
	private static final String BUTTON_EXCEL = "Export to Excel";
	public static final int STYLE_NONE   		= 0b00000000;
	public static final int STYLE_ADD_BUTTON    = 0b00000001;
	public static final int STYLE_REMOVE_BUTTON = 0b00000010;
	public static final int STYLE_EDITOR_BUTTON = 0b00000100;
	
	public static final int DEFAULT_TABLE_HEIGHT = 200;
	public static final int DEFAULT_TABLE_WIDTH = 600;
	public static final int DEFAULT_COLUMN_SIZE = 200;

	public static final String BUTTON_ADD_TEXT = "Add ";
	public static final String BUTTON_REMOVE_TEXT = "Remove ";
	public static final String BUTTON_EDIT_TEXT = "Drill-Down";
	
	public static final int MOUSE_EVENT_RIGHT_CLICK_BUTTON = 3;
	
	/**
	 * Constructor for this class to instantiate a UI Snippet
	 * 
	 * @param conceptId The ID of the Concept in which to look for the Category
	 * @param categoryId The ID of the Category 
	 * @param style 
	 */
	public AUiSnippetGenericTable(String conceptId, String categoryId, int style) {
		super(conceptId, categoryId);
		this.style = style;
	}
	
	protected Composite sectionBody;
	
	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
	
		sectionBody = createSectionBody(toolkit, SECTION_HEADING_PREFIX + getTypeInformationFull(), null, 1);

		setUpTableViewer(editingDomain, toolkit);
		createButtons(toolkit, editingDomain, sectionBody);
		createWrappingActions(editingDomain);
		hookContextMenu(editingDomain, columnViewer, categoryId);
	}

	protected IAction actionAddCategory;
	protected IAction actionEditCategory;
	
	/**
	 * This method gets called by the views selection change listener.
	 * It is needed to to update the actions of the viewers context menu according
	 * to the states of the commands the actions refer to.
	 * @param editingDomain The editing domain to build the commands internally
	 */
	protected void updateActionEnabledState(EditingDomain editingDomain) {
		Concept activeConcept = acHelper.getConcept(conceptId);
		List<?> selectionAsList = columnViewer.getStructuredSelection().toList();
		
		Command commandAddCategory = createAddCommand(editingDomain, activeConcept);
		boolean commandAddCanExecute = commandAddCategory.canExecute();
		actionAddCategory.setEnabled(commandAddCanExecute);

		boolean commandEditCanExecute = false;
		
		if (!selectionAsList.isEmpty()) {
			commandEditCanExecute = true;
		}
		
		actionEditCategory.setEnabled(commandEditCanExecute);
	}
	
	/**
	 * This method is called after all SWT is created and before the context menu
	 * is hooked to the viewer. The actions actually wrap the commands that are used
	 * to add, remove or edit new categories.
	 * @param editingDomain the editing domain to be able to create the commands and wrap them into the actions
	 */
	protected void createWrappingActions(EditingDomain editingDomain) {
		Concept activeConcept = acHelper.getConcept(conceptId);
		ATypeDefinition type = getType();

		// Get the registered images for decorating the actions
		Image categoryImage = getTableLabelProvider().getColumnImage(type, 0);
		ImageDescriptor categoryImageDescriptor = ImageDescriptor.createFromImage(categoryImage);
		ImageDescriptor openEditorImageDescriptor = null;
		
		if (site != null) {
			openEditorImageDescriptor = site.getService(ICommandImageService.class).getImageDescriptor(Activator.COMMAND_ID_OPEN_EDITOR);
		}
		
		actionAddCategory = new Action(BUTTON_ADD_TEXT + getTypeInformation(), categoryImageDescriptor) {
			@Override
			public void run() {
				super.run();
				Command commandAddCategory = createAddCommand(editingDomain, activeConcept);
				editingDomain.getCommandStack().execute(commandAddCategory);
			}
		};
		
		actionEditCategory = new Action(BUTTON_EDIT_TEXT + getTypeInformation(), openEditorImageDescriptor) {
			@Override
			public void run() {
				drillDownTableSelections();
			}
		};
	}

	/**
	 * Standard method to set up the tableViewer
	 * @param editingDomain The EditingDomain to get the Features from
	 * @param toolkit toolkit to create underlying SWT widget
	 */
	protected void setUpTableViewer(EditingDomain editingDomain, FormToolkit toolkit) {
		columnViewer = createColumnViewer(toolkit);
		columnViewer.setContentProvider(getTableContentProvider());
		
		createTableColumns(editingDomain);
		
		if (columnViewer instanceof TableViewer) {
			setupTableViewerKeyboardNavigation((TableViewer) columnViewer);
		}
		
		ITableLabelProvider labelProvider = getTableLabelProvider();
		columnViewer.setLabelProvider(labelProvider);
		
		addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				if (buttonEditor != null) {
					List<?> selectedObjects = columnViewer.getStructuredSelection().toList();
	
					boolean validSelectionForDrillDown = false;
					boolean validSelectionForRemove = false;
					
					// Loop over all selected objects and try to extract the CategoryAssignments
					for (Object selectedObject : selectedObjects) {
						CategoryAssignment ca = getSelectedCategoryAssigment(selectedObject);
						if (ca != null) {
							validSelectionForDrillDown = true;							
							break;
						}
					}
					buttonEditor.setEnabled(validSelectionForDrillDown);
					
					Command cmd = createDeleteCommand(editingDomain, selectedObjects);
					if (cmd.canExecute()) {
						validSelectionForRemove = true;
					}
					setButtonRemoveEnabled(validSelectionForRemove);
				}
			}
		});
		
		// Until now only the Order in the Inheritance Table is relevant (this is done by an own 
		// EStructuralFeatureSnippet), hence here the Table can be sorted by the first column
		columnViewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				String lhString = labelProvider.getColumnText(e1, 0);
				String rhString = labelProvider.getColumnText(e2, 0);
				
				if (lhString != null && rhString != null) { 
					return lhString.compareTo(rhString);
				} else {
					return 0;
				}
			}
		});
		
		setTableViewerInput();
	}
	
	/**
	 * Creates the necessary ColumnViewer
	 * @param toolkit toolkit to create underlying swt widget
	 * @return column viewer
	 */
	protected ColumnViewer createColumnViewer(FormToolkit toolkit) {
		Table table = createDefaultTable(toolkit, sectionBody);
				
		table.setData("org.eclipse.swtbot.widget.key", "table" + categoryId);
		return new TableViewer(table);
	}

	/**
	 * Add a focus manager to the table viewer to navigate throw the table by using keyboard commands such as TAB
	 * @param tableViewer the table viewer
	 */
	protected void setupTableViewerKeyboardNavigation(TableViewer tableViewer) {
		// The Focus Cell Manager is needed for the editor support of a table to correctly manage and display which cell
		// of the editor is currently selected and highlighted. The way the FocusCellOwnerDraw... is instantiated, it will not
		// remove row selection but will overlay it with cell selection.
		TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(tableViewer, new FocusCellOwnerDrawHighlighter(tableViewer, false));

		// The editor strategy is also needed for the editor support to decide, when a cell editor should be activated.
		ColumnViewerEditorActivationStrategy activationSupport = new ColumnViewerEditorActivationStrategy(tableViewer) {
			
			protected ViewerCell lastSelectedCell = null;
			
			@Override
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				ViewerCell selectedCell = ((ViewerCell) event.getSource());

				// Only open the cell editor on a single selection event. If more than one row is selected, don't open an editor
				boolean singleSelect = tableViewer.getStructuredSelection().size() == 1;
				
				// Open an editor on double click of the left mouse button
				boolean isLeftMouseSelect = event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION && ((MouseEvent) event.sourceEvent).button == 1;
				
				// Also open the editor if a single left click is performed on an already selected cell.
				// but don't open the editor on a single click if it has not been selected before.
				// After checking remember the current selection for the next check.
				boolean isLeftMouseSelectAgain = event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION && ((MouseEvent) event.sourceEvent).button == 1 && selectedCell.equals(lastSelectedCell);
				lastSelectedCell = selectedCell;
				
				// Also in case return is pressed a cell editor should be opened.
				boolean isReturnPressed = (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR);
				
				// Now bring together all behavior with the standard behavior of traversing and programmatic calls to cell editors
				return isReturnPressed || singleSelect && (isLeftMouseSelect
						|| isLeftMouseSelectAgain
						|| event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC
						|| event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
						);
			}
		};

		TableViewerEditor.create(tableViewer, focusCellManager, activationSupport, ColumnViewerEditor.TABBING_HORIZONTAL 
				| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
				| ColumnViewerEditor.TABBING_VERTICAL 
				| ColumnViewerEditor.KEYBOARD_ACTIVATION
		);
	}
	
	/**
	 * this abstract method can be implemented to get the label provider
	 * @return the table label provider
	 */
	protected abstract ITableLabelProvider getTableLabelProvider();

	/**
	 * this abstract method can be implemented to get the content provider
	 * @return the content provider
	 */
	protected abstract IStructuredContentProvider getTableContentProvider();

	/**
	 * this abstract method can be implemented to create a table column
	 * @param editingDomain 
	 */
	protected abstract void createTableColumns(EditingDomain editingDomain);
	
	/**
	 * Method to give a table with the standard format 
	 * @param toolkit The Toolkit which creates the Table
	 * @param sectionBody The Composite in which the table is created mainly the sectionBody
	 * @return The standard table
	 */
	protected Table createDefaultTable(FormToolkit toolkit, Composite sectionBody) {
		GridData gridDataTable = createDefaultGridData();
		gridDataTable.horizontalSpan = 1;
		gridDataTable.minimumHeight = DEFAULT_TABLE_HEIGHT;
		gridDataTable.heightHint = DEFAULT_TABLE_HEIGHT;
		gridDataTable.widthHint = DEFAULT_TABLE_WIDTH;

		Table table = toolkit.createTable(sectionBody, SWT.MULTI | SWT.FULL_SELECTION);
		table.setLayoutData(gridDataTable);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		return table;
	}
	
	/**
	 * Method to create the standard table column. This method need to be overridden to create a tree column if necessary. e.g., in case of TreeViewer
	 * @param columnText The Name of the Column
	 * @return The Column itself
	 */
	protected ViewerColumn createDefaultColumn(String columnText) {
		TableViewerColumn column = new TableViewerColumn((TableViewer) columnViewer, SWT.NONE);
		column.getColumn().setText(columnText);
		column.getColumn().setWidth(DEFAULT_COLUMN_SIZE);
		
		return column;
	}
	
	/**
	 * Create the buttons for this snippet
	 * @param toolkit the toolkit
	 * @param editingDomain the editing domain
	 * @param sectionBody the composite
	 */
	protected void createButtons(FormToolkit toolkit, EditingDomain editingDomain, Composite sectionBody) {
		Composite compositeButtons = toolkit.createComposite(sectionBody);
		compositeButtons.setLayoutData(new GridData());
		compositeButtons.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		if (getTypeCardinality() == 1) {
			createCheckBoxButton(toolkit, editingDomain, compositeButtons);
		} else {
			createAddButton(toolkit, editingDomain, compositeButtons);
			createRemoveButton(toolkit, editingDomain, compositeButtons);
		}
		createEditorButton(toolkit, compositeButtons);
		createExcelButton(toolkit, compositeButtons, columnViewer);
	}

	/**
	 * This method hooks the context menu to the given viewer. The method assigns an individual
	 * ID consisting of the editorId#categoryId so, other plugins can contribute to the context menu
	 * by the extension points
	 * @param editingDomain the domain for creating the correct commands in the context menu
	 * @param viewer the viewer to which the context menu will be registered to
	 * @param id the category id that shall be used for the unique ID of the context menu
	 */
	private void hookContextMenu(EditingDomain editingDomain, Viewer viewer, String id) {
		MenuManager menuMgr = new MenuManager(); 
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				updateActionEnabledState(editingDomain);
				AUiSnippetGenericTable.this.fillContextMenu(editingDomain, manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		
		if (site != null) {
			IEditorSite edSite = (IEditorSite) site;
			edSite.registerContextMenu(GenericEditor.EDITOR_ID + "#" + id, menuMgr, viewer, false);
		}
		
		columnViewer.addSelectionChangedListener((selectionChangedEvent) -> {
			updateActionEnabledState(editingDomain);
		});

		// Add a focus listener, so in case the table is empty and it is displaying the contents
		// of an array instance for example, the selection should be redirected to the owner / container object
		// this way it is possible to paste PropertyInstances into a Table showing the content of an empty ArrayInstance 
		columnViewer.getControl().addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (getSelection().isEmpty()) {
					Object viewerInput = columnViewer.getInput();
					if (site != null) {
						site.getSelectionProvider().setSelection(new StructuredSelection(viewerInput));
					}
				}
			}
		});
	}
	
	/**
	 * Method to finally fill the context menu with content
	 * @param editingDomain The current editing domain
	 * @param manager the menu manager of the context menu
	 */
	protected void fillContextMenu(EditingDomain editingDomain, IMenuManager manager) {
		
		manager.add(new Separator(IWorkbenchActionConstants.GROUP_EDITOR));
		if ((style & STYLE_EDITOR_BUTTON) != 0) {
			manager.add(actionEditCategory);
		}
		manager.add(new Separator(IWorkbenchActionConstants.GROUP_ADD));
		manager.add(actionAddCategory);

		// Try to fill in the context menu as provided by the editors action bar contributor
		IEditorActionBarContributor actionBarContributor = site.getActionBarContributor();
		if (actionBarContributor instanceof GenericActionBarContributor) {
			GenericActionBarContributor genericAtionBarContributor = (GenericActionBarContributor) actionBarContributor;
			genericAtionBarContributor.menuAboutToShow(manager);
		}
		
		fillContextMenuAdditions(editingDomain, manager);
	}
	
	/**
	 * Override this method to fill extra content to the menu manager in the Additions section
	 * @param editingDomain The current editing domain
	 * @param manager the menu manager of the context menu
	 */
	protected void fillContextMenuAdditions(EditingDomain editingDomain, IMenuManager manager) {
	}
	
	/**
	 * this abstract method can be implemented to get the type informations
	 * @return the type informations
	 */
	protected abstract String getTypeInformation();
	
	/**
	 * this method can be used to give more detailed type information. Per default returns
	 * getTypeInformation() 
	 * @return the extended type informations
	 */
	protected String getTypeInformationFull() {
		return getTypeInformation();
	}
	
	/**
	 * This abstract method can be implemented to get the type
	 * @return the type
	 */
	protected abstract ATypeDefinition getType();

	/**
	 * Gets the cardinality of the type shown in this table.
	 * @return The cardinality of the type shown in this table, 0 if the type has no cardinality
	 */
	protected int getTypeCardinality() {
		ATypeDefinition type = getType();
		if (type instanceof IApplicableFor) {
			return ((IApplicableFor) type).getCardinality();
		} else {
			// If it is not an applicable for element just, it doesnt have cardinality information
			// so just return 0
			return 0;
		}
	}
	
	/**
	 * this method creates the remove button and his functionality
	 * @param toolkit  the toolkit to be used to create the button
	 * @param editingDomain  the editing domain an which the button might act on
	 * @param compositeButtons  the composite in which the button should be placed
	 */
	protected void createRemoveButton(FormToolkit toolkit, EditingDomain editingDomain, Composite compositeButtons) {
		if ((style & STYLE_REMOVE_BUTTON) != 0) {
			buttonRemove = toolkit.createButton(compositeButtons, BUTTON_REMOVE_TEXT + getTypeInformation(), SWT.PUSH);
			buttonRemove.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					List<?> selection = columnViewer.getStructuredSelection().toList();
					if (!selection.isEmpty()) {
						Command cmd = createDeleteCommand(editingDomain, selection);
						editingDomain.getCommandStack().execute(cmd);
					}
				}
	
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			checkWriteAccess(buttonRemove);
		}
	}

	/**
	 * this method creates the export button and his functionality
	 * @param toolkit  the toolkit to be used to create the button
	 * @param compositeButtons  the composite in which the button should be placed
	 * @param columnViewer to be exported
	 */
	protected void createExcelButton(FormToolkit toolkit, Composite compositeButtons, ColumnViewer columnViewer) {
		exportToExcel = toolkit.createButton(compositeButtons, BUTTON_EXCEL, SWT.PUSH);
		exportToExcel.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ExcelExportWizard wizard = new ExcelExportWizard(columnViewer,  getTypeInformation());
				WizardDialog dialog = new WizardDialog(e.widget.getDisplay().getActiveShell(), wizard);
				dialog.open(); 			
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}
	
	/**
	 * this method creates the button for adding the drill down functionality to the given table
	 * @param toolkit  the toolkit to be used to create the button
	 * @param compositeButtons  the composite in which the button should be placed
	 */
	protected void createEditorButton(FormToolkit toolkit, Composite compositeButtons) {
		if ((style & STYLE_EDITOR_BUTTON) != 0) {
			buttonEditor = toolkit.createButton(compositeButtons, BUTTON_EDIT_TEXT, SWT.PUSH);
			buttonEditor.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					drillDownTableSelections();
				}
	
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			buttonEditor.setEnabled(false);
		}
	}
	
	/**
	 * this method creates the add button and his functionality
	 * @param toolkit  the toolkit to be used to create the button
	 * @param editingDomain  the editing domain an which the button might act on
	 * @param compositeButtons  the composite in which the button should be placed
	 */
	protected void createAddButton(FormToolkit toolkit, EditingDomain editingDomain, Composite compositeButtons) {
		if ((style & STYLE_ADD_BUTTON) != 0) {
			buttonAdd = toolkit.createButton(compositeButtons, BUTTON_ADD_TEXT + getTypeInformation(), SWT.PUSH);
			buttonAdd.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Concept activeConcept = acHelper.getConcept(conceptId);
					Command addCommand = createAddCommand(editingDomain, activeConcept);
					editingDomain.getCommandStack().execute(addCommand);
				}
	
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			checkWriteAccess(buttonAdd);
		}
	}

	/**
	 * Adding CheckBox button. Needed for CAs with cardinality 1
	 * @param toolkit 
	 * @param editingDomain 
	 * @param sectionBody 
	 */
	protected void createCheckBoxButton(FormToolkit toolkit, EditingDomain editingDomain, Composite sectionBody) {
		checkBoxAddRemoveCategoryAssignment = new Button(sectionBody, SWT.CHECK);
		checkBoxAddRemoveCategoryAssignment.setText(categoryId.replaceAll("(?!^)([A-Z])", " $1")); // e.g. "SystemMassParameters" => "System Mass Parameters"
		checkBoxAddRemoveCategoryAssignment.setSelection(getCheckBoxAddRemoveCAValue());
		checkBoxAddRemoveCategoryAssignment.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (checkBoxAddRemoveCategoryAssignment.getSelection()) {
					Concept activeConcept = acHelper.getConcept(conceptId);
					Command addCommand = createAddCommand(editingDomain, activeConcept);
					editingDomain.getCommandStack().execute(addCommand);
				} else {
					Collection<?> elementsToDelete = getTableObjects();
					Command cmd = createDeleteCommand(editingDomain, elementsToDelete);
					editingDomain.getCommandStack().execute(cmd);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		checkWriteAccess(checkBoxAddRemoveCategoryAssignment);
	}
	
	/**
	 * Gets the model objects that should be currently displayed
	 * @return collection of objects
	 */
	protected Collection<?extends EObject> getTableObjects() {
		return CategoryAssignmentHelper.getCategoryAssignmentsOfType((ICategoryAssignmentContainer) model, getCategory().getFullQualifiedName());
	}

	/**
	 * Updates the value of the CheckBox depending on whether there is a corresponding CA or not
	 * @return true if the CheckBox should be checked or false otherwise
	 */
	protected boolean getCheckBoxAddRemoveCAValue() {
		return !getTableObjects().isEmpty();
	}

	/**
	 * Call this method to open another editor for each currently selected
	 * Category Assignment in the displayed Table.
	 */
	private void drillDownTableSelections() {
		List<?> selectedObjects = columnViewer.getStructuredSelection().toList();

		// Loop over all selected objects and try to extract the CategoryAssignments
		for (Object selectedObject : selectedObjects) {
			CategoryAssignment selectedCa = getSelectedCategoryAssigment(selectedObject);
			
			// Open the editor in case a category assignment was successfully detected
			if (selectedCa != null) {
				VirSatUriEditorInput.openDrillDownEditor(selectedCa);
			}
		}
	}
	
	/**
	 * Gets the corresponding category assignment from a selected object in a table
	 * @param selectedObject an object selected in the table viewer
	 * @return the category assignment corresponding to the selected object, if it exists
	 */
	
	private CategoryAssignment getSelectedCategoryAssigment(Object selectedObject) {
		CategoryAssignment selectedCa = null;
		
		// In case the selection is already a categoryAssignment than just open it
		if (selectedObject instanceof CategoryAssignment) {
			selectedCa = (CategoryAssignment) selectedObject;
		} else if (selectedObject instanceof ReferencePropertyInstance) {
			// In case the current table displays reference properties than
			// the category assignment that should be opened in a separate editor
			// has to be extracted before.
			ReferencePropertyInstance selectedRpi = (ReferencePropertyInstance) selectedObject;
			ATypeInstance referencedTypeInstance = selectedRpi.getReference();

			// The reference property can also reference other property instances not just
			// CatgeoryAssignments therefore it needs to be filtered.
			if (referencedTypeInstance instanceof CategoryAssignment) {
				selectedCa = (CategoryAssignment) referencedTypeInstance;
			}
		} else if (selectedObject instanceof ComposedPropertyInstance) {
			ComposedPropertyInstance selectedCpi = (ComposedPropertyInstance) selectedObject;
			selectedCa = selectedCpi.getTypeInstance();
		} else if (selectedObject instanceof UnitValuePropertyInstance) {
			UnitValuePropertyInstance selectedUvpi = (UnitValuePropertyInstance) selectedObject;
			EObject eContainer = selectedUvpi.eContainer();
			if (eContainer instanceof ArrayInstance) {
				eContainer = eContainer.eContainer();
			}
			selectedCa = (CategoryAssignment) eContainer;
		}
		
		return selectedCa;
	}
	
	/**
	 * this abstract method can be implemented for the add command creating
	 * @param editingDomain The editing domain which will be used to construct the actual command
	 * @param activeConcept the currently active concept that is displayed by the current table
	 * @return the create command 
	 */
	protected abstract Command createAddCommand(EditingDomain editingDomain, Concept activeConcept);

	/**
	 * this method create the delete command
	 * @param editingDomain The editing domain which will be used to construct the actual command
	 * @param objects The objects to delete
	 * @return the create command 
	 */
	protected Command createDeleteCommand(EditingDomain editingDomain, Collection<?> objects) {
		CompoundCommand deleteCommands = new CompoundCommand();
		BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
		
		for (Object object : objects) {
			if (object instanceof CategoryAssignment || object instanceof ComposedPropertyInstance) {
				
				// For Category Assignments we see if we can construct a bean and ask it for the delete command
				// This way we can get customized delete commands from the bean
				
				CategoryAssignment ca = object instanceof ComposedPropertyInstance ? ((ComposedPropertyInstance) object).getTypeInstance() : (CategoryAssignment) object;
				try {
					IBeanCategoryAssignment beanCa = beanCaFactory.getInstanceFor(ca);
					Command deleteCommand = beanCa.delete(editingDomain);
					deleteCommands.append(deleteCommand);
				} catch (CoreException e) {
					
					// If we fail to create a bean (usually due to not having the concept locally installed)
					// we use the default delete command
					
					Command deleteCommand = DeleteCommand.create(editingDomain, ca);
					deleteCommands.append(deleteCommand);
				}
			} else {
				// Non-Category Assignment objects are just normally deleted
				
				Command deleteCommand = DeleteCommand.create(editingDomain, object);
				deleteCommands.append(deleteCommand);
			}
		}
		
		return deleteCommands;
	}

	/**
	 * this method set the table viewer input 
	 */
	protected void setTableViewerInput() {
		columnViewer.setInput(model);
	}
	
	@Override
	public void setDataBinding(DataBindingContext dbCtx, EditingDomain editingDomain, EObject model) {
		initializeHelperForModel(model);
		setTableViewerInput();
	}

	@Override
	public void setSelection(ISelection selection) {
		columnViewer.setSelection(selection);
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		columnViewer.addSelectionChangedListener(listener);
	}

	@Override
	public ISelection getSelection() {
		return columnViewer.getSelection();
	}

	/**
	 * Call this method to get the structured Selection of the current Viewer
	 * @return a Structured Selection of the viewer
	 */
	public IStructuredSelection getStructuredSelection() {
		return columnViewer.getStructuredSelection();
	}
	
	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		columnViewer.removeSelectionChangedListener(listener);
	}

	/**
	 * Method to set the ButtonAdd enabled or not 
	 * @param enabled If it is enabled
	 */
	public void setButtonAddEnabled(boolean enabled) {
		if (buttonAdd != null) {
			buttonAdd.setEnabled(enabled);
		}
	}

	/**
	 * Method to set the ButtonRemove enabled or not 
	 * @param validSelection If it is a valid selection
	 */
	public void setButtonRemoveEnabled(boolean validSelection) {
		if (buttonRemove != null) {
			buttonRemove.setEnabled(validSelection);
		}
	}

	
	@Override
	public void updateState(boolean state) {
		super.updateState(state);
		if (checkBoxAddRemoveCategoryAssignment != null) {
			checkBoxAddRemoveCategoryAssignment.setSelection(getCheckBoxAddRemoveCAValue());
		}
	}
	
	@Override
	protected Collection<? extends EObject> getPossiblyMarkedObjects() {
		return getTableObjects();
	}
}
