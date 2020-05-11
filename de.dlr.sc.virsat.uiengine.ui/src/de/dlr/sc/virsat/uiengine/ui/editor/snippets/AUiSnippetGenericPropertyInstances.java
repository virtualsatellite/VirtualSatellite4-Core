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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.jface.databinding.swt.ISWTObservable;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.ide.IDE;

import de.dlr.sc.virsat.build.marker.ui.MarkerImageProvider;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferencePropertyHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.util.DVLMReferenceCheck;
import de.dlr.sc.virsat.model.ui.editor.input.VirSatUriEditorInput;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatTransactionalAdapterFactoryContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.uieingine.ui.DVLMEditorPlugin;
import de.dlr.sc.virsat.uieingine.ui.dnd.ADropSelectionTargetListener;
import de.dlr.sc.virsat.uieingine.ui.dnd.DropHelper;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.BooleanPropertyCellEditingSupport;
import de.dlr.sc.virsat.uiengine.ui.databinding.properties.VirSatEMFEditObservables;
import de.dlr.sc.virsat.uiengine.ui.dialog.ReferenceSelectionDialog;
import de.dlr.sc.virsat.uiengine.ui.dialog.SelectOrUploadFileDialog;

/**
 * use this class to display all properties and of one selected category. This
 * UI Snippet is useful when displaying Categories which are referenced by an
 * array. The array table should allow to drill down and open another editor.
 * This UI part should be within the newly opened editor to give full access to
 * the properties of that kind of embedded category. At the moment this is also
 * the only possibility to edit the units of a category.
 * 
 * @author fisc_ph
 *
 */
public abstract class AUiSnippetGenericPropertyInstances extends AUiCategorySectionSnippet implements IUiSnippet {

	protected static final String SECTION_HEADING = "Property Section: References, Compositions, Values and Units";

	protected CategoryAssignment caModel;
	protected CategoryAssignmentHelper caHelper;
	protected MarkerImageProvider mip;
	protected Image imageCalculated;
	
	// defaults to STYLE_NONE
	private int style;

	private static final String BUTTON_SELECT_REFERENCE_TEXT = "Select Reference";
	private static final String BUTTON_SELECT_FILE_TEXT = "Select / Upload File";
	private static final String BUTTON_DRILL_DOWN_TEXT = "Drill-Down";
	private static final String BUTTON_LOAD_RESOURCE_TEXT = "Load Resource";
	private static final String BUTTON_OPEN_EDITOR_TEXT = "Open Editor";
	private static final String BUTTON_CHECK_OVERRIDE_TEXT = "Override";
	protected static final int STYLE_NONE = 0b00000000;
	protected static final int STYLE_NO_ENUM_UNIT_COMBO_BOX = 0b00000001;

	protected static final int UI_LAYOUT_NR_COLUMNS = 6;
	protected static final int UI_LAYOUT_SPAN_COLUMNS_2 = 2;
	protected static final int UI_LAYOUT_SPAN_COLUMNS_3 = 3;

	protected Map<String, Label> mapPropertyToLabelPropertyName = new HashMap<>();
	protected Map<String, Label> mapPropertyToLabelPropertyIcon = new HashMap<>();
	protected Map<String, Text> mapPropertyToTextValue = new HashMap<>();
	protected Map<String, Text> mapPropertyToTextUri = new HashMap<>();
	protected Map<String, Text> mapPropertyToTextComposedName = new HashMap<>();
	protected Map<String, Text> mapPropertyToTextReferenceName = new HashMap<>();
	protected Map<String, Button> mapPropertyToButtonSelectReference = new HashMap<>();
	protected Map<String, Button> mapPropertyToButtonOverride = new HashMap<>();
	protected Map<String, ComboViewer> mapPropertyToComboViewerBooleanValue = new HashMap<>();
	protected Map<String, ComboViewer> mapPropertyToComboViewerUnit = new HashMap<>();
	protected Map<String, ComboViewer> mapPropertyToComboViewerEnum = new HashMap<>();

	protected Map<Control, ControlDecoration> mapControlToDecoration = new HashMap<>();
	
	/**
	 * Dummy enum to model no selected unit in the unit combobox (it doesn't
	 * accept null elements)
	 */
	enum NoUnit {
		NOUNIT
	};
	
	/**
	 * Dummy enum to model no selected enum value in the enum value combo box (it doesn't
	 * accept null elements)
	 * @author muel_s8
	 *
	 */
	protected enum NoEnum {
		NOENUM
	}

	/**
	 * Constructor for this class to instantiate a UI Snippet
	 * 
	 * @param conceptId
	 *            The ID (Full qualified ID of the Category) of the Concept in
	 *            which to look for the Category
	 * @param categoryId
	 *            The ID (short ID )of the category to be displayed for the
	 *            properties
	 */
	public AUiSnippetGenericPropertyInstances(String conceptId, String categoryId) {
		this(conceptId, categoryId, STYLE_NONE);
	}
	
	/**
	 * Constructor for this class to instantiate a UI Snippet
	 * 
	 * @param conceptId
	 *            The ID (Full qualified ID of the Category) of the Concept in
	 *            which to look for the Category
	 * @param categoryId
	 *            The ID (short ID )of the category to be displayed for the
	 *            properties
	 * @param style
	 * 		      determines style of UiSnippetSection
	 */
	public AUiSnippetGenericPropertyInstances(String conceptId, String categoryId, int style) {
		super(conceptId, categoryId);
		mip = new MarkerImageProvider(new VirSatProblemMarkerHelper());
		this.style = style;
		
		imageCalculated = DVLMEditorPlugin.getPlugin().getImageRegistry().get(DVLMEditorPlugin.IMAGE_CALCULATED);
	}
	

	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
		initializeHelperForModel(initModel);

		Composite sectionBody = createSectionBody(toolkit, SECTION_HEADING, null, 1);
		sectionBody.setLayout(new GridLayout(UI_LAYOUT_NR_COLUMNS, false));

		Category viewerCategory = acHelper.getCategory(conceptId, categoryId);

		// Loop over all properties and create according widgets, don't do that
		// for the arrays which will be placed in
		// some individual tables etc.
		for (AProperty property : ActiveConceptHelper.getNonArrayProperties(viewerCategory)) {
			createCommonPropertyWidgets(toolkit, sectionBody, property);
			createCustomPropertyWidgets(toolkit, editingDomain, sectionBody, property);
		}
	}

	/**
	 * Method to create common widgets for all properties - Diagnostic icon,
	 * Name, Override button
	 * 
	 * @param toolkit
	 *            the toolkit which should be used to create the widgets
	 * @param sectionBody
	 *            the section body in which the widgets should be placed
	 * @param property
	 *            the property on which these widgets should act
	 */
	private void createCommonPropertyWidgets(FormToolkit toolkit, Composite sectionBody, AProperty property) {
		Label labelPropertyIcon = toolkit.createLabel(sectionBody, "");
		labelPropertyIcon.setLayoutData(new GridData());
		Image dummyImage = mip.getOkImage();
		labelPropertyIcon.setImage(dummyImage);

		Label labelPropertyName = toolkit.createLabel(sectionBody, property.getName());
		labelPropertyName.setLayoutData(createDefaultGridData());

		Button buttonPropertyOverride = toolkit.createButton(sectionBody, BUTTON_CHECK_OVERRIDE_TEXT, SWT.CHECK);
		buttonPropertyOverride.setLayoutData(new GridData());

		String propertyFqn = property.getFullQualifiedName();
		mapPropertyToLabelPropertyIcon.put(propertyFqn, labelPropertyIcon);
		mapPropertyToButtonOverride.put(propertyFqn, buttonPropertyOverride);
		mapPropertyToLabelPropertyName.put(propertyFqn, labelPropertyName);
	}
	
	/**
	 * Get UiSnippetSection style
	 * @return style determines style of UiSnippetSection
	 */
	protected int getStyle() {
		return style;
	}
	
	/**
	 * Set UiSnippetSection style
	 * @param style determines style of UiSnippetSection
	 */
	protected void setStyle(int style) {
		this.style = style;
	}

	/**
	 * Method to create property specific widgets
	 * 
	 * @param toolkit
	 *            the toolkit which should be used to create the widgets
	 * @param editingDomain
	 *            the editing domain
	 * @param sectionBody
	 *            the section body in which the widgets should be placed
	 * @param property
	 *            the property on which these widgets should act
	 */
	protected void createCustomPropertyWidgets(FormToolkit toolkit, EditingDomain editingDomain, Composite sectionBody,	AProperty property) {
		if (property instanceof EnumProperty) {
			createEnumUnitPropertyWidgets(toolkit, sectionBody, (EnumProperty) property);
		} else if (property instanceof AQudvTypeProperty) {
			createUnitValuePropertyWidgets(toolkit, sectionBody, (AQudvTypeProperty) property);
		} else if (property instanceof BooleanProperty) {
			createBooleanPropertyWidgets(toolkit, sectionBody, (BooleanProperty) property);
		} else if (property instanceof StringProperty) {
			createStringPropertyWidgets(toolkit, sectionBody, (StringProperty) property);
		} else if (property instanceof ReferenceProperty) {
			createReferencePropertyWidgets(toolkit, editingDomain, sectionBody, (ReferenceProperty) property);
		} else if (property instanceof EReferenceProperty) {
			createEReferencePropertyWidgets(toolkit, editingDomain, sectionBody, (EReferenceProperty) property);
		} else if (property instanceof ComposedProperty) {
			createComposedPropertyWidgets(toolkit, sectionBody, (ComposedProperty) property);
		} else if (property instanceof ResourceProperty) {
			createResourcePropertyWidgets(toolkit, editingDomain, sectionBody, (ResourceProperty) property);
		}
	}

	/**
	 * Method to create QUDV property widgets - value text input and unit
	 * ComboBox
	 * 
	 * @param toolkit
	 *            the toolkit which should be used to create the widgets
	 * @param sectionBody
	 *            the section body in which the widgets should be placed
	 * @param property
	 *            the property on which these widgets should act
	 */
	private void createEnumUnitPropertyWidgets(FormToolkit toolkit, Composite sectionBody, EnumProperty property) {
		ComboViewer comboViewerEnum = new ComboViewer(sectionBody, SWT.NONE);
		Combo comboEnum = comboViewerEnum.getCombo();
		GridData gridData = createDefaultGridData();
		gridData.horizontalSpan =  (style & STYLE_NO_ENUM_UNIT_COMBO_BOX) == 0 ? UI_LAYOUT_SPAN_COLUMNS_2 : UI_LAYOUT_SPAN_COLUMNS_3;
		comboEnum.setLayoutData(gridData);

		String propertyFqn = property.getFullQualifiedName();
		mapPropertyToComboViewerEnum.put(propertyFqn, comboViewerEnum);

		comboViewerEnum.setContentProvider(createContentProviderForEnumProperty());
		comboViewerEnum.setLabelProvider(createLabelProviderForEnumProperty());

		comboViewerEnum.setComparator(createEnumComboboxComparator());

		if ((style & STYLE_NO_ENUM_UNIT_COMBO_BOX) == 0) {
			createUnitComboBox(sectionBody, property);
		}
	}

	/**
	 * Creates a comparator to sort Enum values in the combo box
	 * Alphabetically by default
	 * @return comparator
	 */
	protected ViewerComparator createEnumComboboxComparator() {
		return new ViewerComparator();
	}

	/**
	 * Creates content provider for enum properties
	 * @return content provider
	 */
	protected VirSatTransactionalAdapterFactoryContentProvider createContentProviderForEnumProperty() {
		VirSatTransactionalAdapterFactoryContentProvider filteredContentProvider = new VirSatTransactionalAdapterFactoryContentProvider(adapterFactory) {
			public Object[] getElements(Object rootObject) {
				List<Object> elements = new ArrayList<>(Arrays.asList(super.getElements(rootObject)));
				elements.add(0, NoEnum.NOENUM);
				return elements.toArray();
			};
		};
		return filteredContentProvider;
	}

	/**
	 * Creates label provider for enum properties
	 * @return label provider
	 */
	protected VirSatTransactionalAdapterFactoryLabelProvider createLabelProviderForEnumProperty() {
		VirSatTransactionalAdapterFactoryLabelProvider labelProvider = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory) {
			@Override
			public String getText(Object object) {
				if (object instanceof NoEnum) {
					return "";
				}
				return super.getText(object);
			}
		};
		return labelProvider;
	}
	
	/**
	 * Method to create QUDV property widgets - value text input and unit
	 * ComboBox
	 * 
	 * @param toolkit
	 *            the toolkit which should be used to create the widgets
	 * @param sectionBody
	 *            the section body in which the widgets should be placed
	 * @param property
	 *            the property on which these widgets should act
	 */
	private void createUnitValuePropertyWidgets(FormToolkit toolkit, Composite sectionBody,	AQudvTypeProperty property) {
		Text textPropertyValue = toolkit.createText(sectionBody, "");
		GridData gridData = createDefaultGridData();
		gridData.horizontalSpan = UI_LAYOUT_SPAN_COLUMNS_2;
		textPropertyValue.setLayoutData(gridData);

		String propertyFqn = property.getFullQualifiedName();
		mapPropertyToTextValue.put(propertyFqn, textPropertyValue);

		addControlDecoration(textPropertyValue);
		
		createUnitComboBox(sectionBody, property);
	}
	
	/**
	 * Call this method to add a control decorator to the given SWT control
	 * @param swtControl the SWT control to add a decorator to.
	 */
	private void addControlDecoration(Control swtControl) {
		ControlDecoration swtControlDecoration = new ControlDecoration(swtControl, SWT.LEFT | SWT.TOP);
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
		swtControlDecoration.setImage(fieldDecoration.getImage());
		swtControlDecoration.hide();
		mapControlToDecoration.put(swtControl, swtControlDecoration);
	}

	/**
	 * Creates ComboBox for selecting a unit for this property
	 * 
	 * @param sectionBody
	 *            the section body in which the widget should be placed
	 * @param property
	 *            the property on which these widget should act
	 */
	protected void createUnitComboBox(Composite sectionBody, AQudvTypeProperty property) {
		VirSatFilteredWrappedTreeContentProvider filteredContentProvider = new VirSatFilteredWrappedTreeContentProvider(adapterFactory) {
			public Object[] getElements(Object rootObject) {
				Object[] elements = super.getElements(rootObject);
				Object[] elementsWithNullItem = new Object[elements.length + 1];
				elementsWithNullItem[0] = NoUnit.NOUNIT;
				System.arraycopy(elements, 0, elementsWithNullItem, 1, elements.length);
				return elementsWithNullItem;
			};
		};
		filteredContentProvider.addClassFilterToGetChildren(AUnit.class);
		filteredContentProvider.addClassFilterToGetElement(AUnit.class);

		ComboViewer comboViewerUnit = new ComboViewer(sectionBody, SWT.NONE);
		Combo comboUnit = comboViewerUnit.getCombo();
		comboUnit.setLayoutData(createDefaultGridData());

		comboViewerUnit.setContentProvider(filteredContentProvider);
		comboViewerUnit.setLabelProvider(new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory) {
			@Override
			public String getText(Object object) {
				if (object instanceof NoUnit) {
					return "";
				}
				return super.getText(object);
			}
		});

		String propertyFqn = property.getFullQualifiedName();
		mapPropertyToComboViewerUnit.put(propertyFqn, comboViewerUnit);
		comboViewerUnit.setComparator(new ViewerComparator());

		String quantityKindName = property.getQuantityKindName();
		EObject container = model;
		while (quantityKindName == null && container != null) {
			if (container instanceof ComposedPropertyInstance) {
				ComposedPropertyInstance cpi = (ComposedPropertyInstance) container;
				ComposedProperty cp = (ComposedProperty) cpi.getType();
				quantityKindName = cp.getQuantityKindName();
			}
			container = container.eContainer();
		}

		if (quantityKindName != null) {
			String quantityKindToShow = quantityKindName;
			comboViewerUnit.addFilter(new ViewerFilter() {
				@Override
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof NoUnit) {
						return true;
					}
					return ((AUnit) element).getQuantityKind().getName().equals(quantityKindToShow);
				}
			});
		}
	}

	/**
	 * Method to create property specific widgets
	 * 
	 * @param toolkit
	 *            the toolkit which should be used to create the widgets
	 * @param sectionBody
	 *            the section body in which the widgets should be placed
	 * @param property
	 *            the property on which these widgets should act
	 */
	protected void createBooleanPropertyWidgets(FormToolkit toolkit, Composite sectionBody, BooleanProperty property) {
		ComboViewer comboViewerValue = new ComboViewer(sectionBody, SWT.RIGHT);
		Combo comboValue = comboViewerValue.getCombo();
		GridData gridData = createDefaultGridData();
		gridData.horizontalSpan = UI_LAYOUT_SPAN_COLUMNS_3;
		comboValue.setLayoutData(gridData);
		comboViewerValue.setContentProvider(new ArrayContentProvider());
		comboViewerValue.setInput(BooleanPropertyCellEditingSupport.BOOL_LITERALS);

		String propertyFqn = property.getFullQualifiedName();
		mapPropertyToComboViewerBooleanValue.put(propertyFqn, comboViewerValue);
	}

	/**
	 * Method to create property specific widgets
	 * 
	 * @param toolkit
	 *            the toolkit which should be used to create the widgets
	 * @param sectionBody
	 *            the section body in which the widgets should be placed
	 * @param property
	 *            the property on which these widgets should act
	 */
	protected void createStringPropertyWidgets(FormToolkit toolkit, Composite sectionBody, StringProperty property) {
		Text textPropertyValue = toolkit.createText(sectionBody, "");
		GridData gridData = createDefaultGridData();
		gridData.horizontalSpan = UI_LAYOUT_SPAN_COLUMNS_3;
		textPropertyValue.setLayoutData(gridData);

		String propertyFqn = property.getFullQualifiedName();
		mapPropertyToTextValue.put(propertyFqn, textPropertyValue);
	}

	/**
	 * Method to create property specific widgets
	 * 
	 * @param toolkit
	 *            the toolkit which should be used to create the widgets
	 * @param editingDomain
	 *            the editing domain which should be used when a new reference
	 *            is selected and added to the corresponding property instance
	 * @param sectionBody
	 *            the section body in which the widgets should be placed
	 * @param property
	 *            the property on which these widgets should act
	 */
	protected void createReferencePropertyWidgets(FormToolkit toolkit, EditingDomain editingDomain,	Composite sectionBody, ReferenceProperty property) {
		String propertyFqn = property.getFullQualifiedName();
		String fqnAti = getLabelOfReferencedAti(propertyFqn);

		Text textPropertyReferenceName = toolkit.createText(sectionBody, fqnAti);
		textPropertyReferenceName.setEditable(false);
		textPropertyReferenceName.setLayoutData(createDefaultGridData());
		
		DropHelper.createDropTarget(textPropertyReferenceName, new ADropSelectionTargetListener(editingDomain) {
			@Override
			public Command createDropCommand(StructuredSelection selection) {
				APropertyInstance propertyInstance = caHelper.getPropertyInstance(property);
				if (!DVLMReferenceCheck.isValid(propertyInstance, selection.getFirstElement())) {
					return UnexecutableCommand.INSTANCE;
				}
				
				Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, selection.getFirstElement());
				return cmd;
			}
		});
		
		Button buttonSelectReference = toolkit.createButton(sectionBody, BUTTON_SELECT_REFERENCE_TEXT, SWT.PUSH);
		buttonSelectReference.setLayoutData(createDefaultGridData());

		Button buttonDrillDown = toolkit.createButton(sectionBody, BUTTON_DRILL_DOWN_TEXT, SWT.PUSH);
		buttonDrillDown.setLayoutData(createDefaultGridData());

		mapPropertyToTextReferenceName.put(propertyFqn, textPropertyReferenceName);
		mapPropertyToButtonSelectReference.put(propertyFqn, buttonSelectReference);

		buttonSelectReference.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				executeReferenceSelectionDialog(editingDomain, propertyFqn);
			}
		});

		buttonDrillDown.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) caHelper
						.getPropertyInstance(property);
				ATypeInstance referencedTypeInstance = propertyInstance.getReference();
				if (referencedTypeInstance != null) {
					VirSatUriEditorInput.openDrillDownEditor(referencedTypeInstance);
				}
			}
		});
	}
	
	/**
	 * Method to create property specific widgets
	 * 
	 * @param toolkit
	 *            the toolkit which should be used to create the widgets
	 * @param editingDomain
	 *            the editing domain which should be used when a new reference
	 *            is selected and added to the corresponding property instance
	 * @param sectionBody
	 *            the section body in which the widgets should be placed
	 * @param property
	 *            the property on which these widgets should act
	 */
	protected void createEReferencePropertyWidgets(FormToolkit toolkit, EditingDomain editingDomain,	Composite sectionBody, EReferenceProperty property) {
		String propertyFqn = property.getFullQualifiedName();
		String fqnAti = getLabelOfReferencedAti(propertyFqn);

		Text textPropertyReferenceName = toolkit.createText(sectionBody, fqnAti);
		textPropertyReferenceName.setEditable(false);
		textPropertyReferenceName.setLayoutData(createDefaultGridData());
		
		DropHelper.createDropTarget(textPropertyReferenceName, new ADropSelectionTargetListener(editingDomain) {
			@Override
			public Command createDropCommand(StructuredSelection selection) {
				APropertyInstance propertyInstance = caHelper.getPropertyInstance(property);
				if (!DVLMReferenceCheck.isValid(propertyInstance, selection.getFirstElement())) {
					return UnexecutableCommand.INSTANCE;
				}
				
				Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, selection.getFirstElement());
				return cmd;
			}
		});
		
		Button buttonSelectReference = toolkit.createButton(sectionBody, BUTTON_SELECT_REFERENCE_TEXT, SWT.PUSH);
		buttonSelectReference.setLayoutData(createDefaultGridData());

		Button buttonLoadReosurce = toolkit.createButton(sectionBody, BUTTON_LOAD_RESOURCE_TEXT, SWT.PUSH);
		buttonLoadReosurce.setLayoutData(createDefaultGridData());
		buttonLoadReosurce.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				Shell currentShell = Display.getCurrent().getActiveShell();
				Dialog resourceDialog = new LoadResourceAction.LoadResourceDialog(currentShell, editingDomain);
				resourceDialog.open();
			}
		});

		mapPropertyToTextReferenceName.put(propertyFqn, textPropertyReferenceName);
		mapPropertyToButtonSelectReference.put(propertyFqn, buttonSelectReference);

		buttonSelectReference.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				executeEReferenceSelectionDialog(editingDomain, propertyFqn);
			}
		});

	}

	/**
	 * Method to get the FullQualifiedName of the ReferencePropertyInstance
	 * which belongs to the ReferenceProperty in this CA
	 * 
	 * @param propertyFqn
	 *            The FullQualifiedName of the ReferenceProperty to be looked up
	 *            in the caModel
	 * @return The FullQualifiedName of the according ReferencePropertyInstance
	 */
	private String getLabelOfReferencedAti(String propertyFqn) {
		APropertyInstance api = caHelper.getPropertyInstance(propertyFqn);

		if (!(api instanceof ReferencePropertyInstance) && !(api instanceof EReferencePropertyInstance)) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(),	"AUiSnippetGenericPropertyInstances: Failed to get ReferencePropertyInstances"));
			return "";
		}

		String atiLabel = new PropertyInstanceValueSwitch().getValueString(api);
		return atiLabel;
	}

	/**
	 * Method to create property specific widgets
	 * 
	 * @param toolkit
	 *            the toolkit which should be used to create the widgets
	 * @param editingDomain
	 *            the editing domain which should be used when a new reference
	 *            is selected and added to the corresponding property instance
	 * @param sectionBody
	 *            the section body in which the widgets should be placed
	 * @param property
	 *            the property on which these widgets should act
	 */
	protected void createResourcePropertyWidgets(FormToolkit toolkit, EditingDomain editingDomain, Composite sectionBody, ResourceProperty property) {
		Text textResourceUri = toolkit.createText(sectionBody, "");
		textResourceUri.setEditable(false);
		textResourceUri.setLayoutData(createDefaultGridData());

		Button buttonSelectReference = toolkit.createButton(sectionBody, BUTTON_SELECT_FILE_TEXT, SWT.PUSH);
		buttonSelectReference.setLayoutData(createDefaultGridData());

		Button buttonOpenEditor = toolkit.createButton(sectionBody, BUTTON_OPEN_EDITOR_TEXT, SWT.PUSH);
		buttonOpenEditor.setLayoutData(createDefaultGridData());

		String propertyFqn = property.getFullQualifiedName();

		mapPropertyToTextUri.put(propertyFqn, textResourceUri);
		mapPropertyToButtonSelectReference.put(propertyFqn, buttonSelectReference);

		// First get the propertyInstance
		ResourcePropertyInstance propertyInstance = (ResourcePropertyInstance) caHelper.getPropertyInstance(propertyFqn);

		// if file is uploaded then only enable Open Editor button

		try {
			if (propertyInstance.getUri() != null) {
				buttonOpenEditor.setEnabled(true);
			} else {
				buttonOpenEditor.setEnabled(false);
			}
		} catch (Exception e) {
			DVLMEditorPlugin.getPlugin().getLog().log(new Status(Status.WARNING, DVLMEditorPlugin.ID, Status.WARNING, "File not uploaded: " + propertyInstance, e));
		}

		buttonSelectReference.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				executeResourceSelectionDialog(editingDomain, propertyFqn);
				// if file is uploaded then only enable Open Editor button
				try {
					if (propertyInstance.getUri() != null) {
						buttonOpenEditor.setEnabled(true);

					} else {
						buttonOpenEditor.setEnabled(false);
					}
				} catch (Exception e1) {
					DVLMEditorPlugin.getPlugin().getLog().log(new Status(Status.WARNING, DVLMEditorPlugin.ID, Status.WARNING, "File not uploaded: " + propertyInstance, e1));
				}
			}
		});

		buttonOpenEditor.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				BeanPropertyResource beanPropertyResource = new BeanPropertyResource(propertyInstance);
				IFile resourceFile = beanPropertyResource.getFile();

				// Now try to identify the standard editor for this file and
				// open it
				IWorkbench workbench = PlatformUI.getWorkbench();
				IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
				IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
				try {
					IDE.openEditor(workbenchPage, resourceFile, true);
				} catch (PartInitException e1) {
					DVLMEditorPlugin.getPlugin().getLog().log(new Status(Status.WARNING, DVLMEditorPlugin.ID, Status.WARNING, "Failed to open defualt editor for file: " + resourceFile, e1));
				}
			}
		});
	}

	/**
	 * Method to create property specific widgets
	 * 
	 * @param toolkit
	 *            the toolkit which should be used to create the widgets
	 * @param sectionBody
	 *            the section body in which the widgets should be placed
	 * @param property
	 *            the property on which these widgets should act
	 */
	protected void createComposedPropertyWidgets(FormToolkit toolkit, Composite sectionBody, ComposedProperty property) {
		Text textPropertyComposedName = toolkit.createText(sectionBody, "");
		textPropertyComposedName.setEditable(false);
		textPropertyComposedName.setLayoutData(createDefaultGridData());

		Button buttonDrillDown = toolkit.createButton(sectionBody, BUTTON_DRILL_DOWN_TEXT, SWT.PUSH);
		GridData gridData = createDefaultGridData();
		gridData.horizontalSpan = UI_LAYOUT_SPAN_COLUMNS_2;
		buttonDrillDown.setLayoutData(gridData);

		String propertyFqn = property.getFullQualifiedName();

		mapPropertyToTextComposedName.put(propertyFqn, textPropertyComposedName);

		buttonDrillDown.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) caHelper.getPropertyInstance(property);
				ATypeInstance referencedTypeInstance = propertyInstance.getTypeInstance();
				if (referencedTypeInstance != null) {
					VirSatUriEditorInput.openDrillDownEditor(referencedTypeInstance);
				}
			}
		});
	}
	
	/**
	 * This method calls the dialog for adding a new Reference to the given
	 * property
	 * 
	 * @param editingDomain
	 *            the editing domain for which the dialog should be called and
	 *            should execute on
	 * @param propertyFqn
	 *            the resource property which should be used for this dialog
	 */
	private void executeResourceSelectionDialog(EditingDomain editingDomain, String propertyFqn) {

		// We can do some aggressive casting here, this code should never be
		// called in case we do
		// not have a reference property instance.
		ResourcePropertyInstance propertyInstance = (ResourcePropertyInstance) caHelper.getPropertyInstance(propertyFqn);

		// Call the dialog to select or uplaod a file
		Shell currentShell = Display.getCurrent().getActiveShell();
		IFolder propertyDocumentsFolder = VirSatProjectCommons.getDocumentFolder(propertyInstance);
		SelectOrUploadFileDialog dialog = new SelectOrUploadFileDialog(currentShell, SWT.OPEN, propertyDocumentsFolder);
		String fileUriResult = dialog.open();
		
		// process the result and call the command to store the dialog result if
		// needed
		if (fileUriResult != SelectOrUploadFileDialog.RESULT_NULL) {
			Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.RESOURCE_PROPERTY_INSTANCE__RESOURCE_URI, fileUriResult);
			editingDomain.getCommandStack().execute(cmd);
		}
	}

	/**
	 * This method calls the dialog for adding a new Reference to the given
	 * property
	 * 
	 * @param editingDomain
	 *            the editing domain for which the dialog should be called and
	 *            should execute on
	 * @param propertyFqn
	 *            the reference property which should be set by this dialog
	 */
	protected void executeReferenceSelectionDialog(EditingDomain editingDomain, String propertyFqn) {
		// We can do some aggressive casting here, this code should never be
		// called in case we do
		// not have a reference property instance.
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) caHelper.getPropertyInstance(propertyFqn);
		ATypeDefinition referencePropertyType = ((ReferenceProperty) propertyInstance.getType()).getReferenceType();
		ATypeInstance toSelect = propertyInstance.getReference();

		ReferenceSelectionDialog dialog = createReferenceDialogAndSetInput(propertyInstance, referencePropertyType);
		dialog.setInitialSelection(toSelect);
		dialog.setAllowMultiple(false);
		dialog.setDoubleClickSelects(true);
		if (dialog.open() == Dialog.OK) {
			Object selection = dialog.getFirstResult();
			if (selection instanceof ATypeInstance) {
				ATypeInstance selectedTypeInstance = (ATypeInstance) selection;
				Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, selectedTypeInstance);
				editingDomain.getCommandStack().execute(cmd);
			}
		}
	}
	
	protected void executeLoadResourceDialog() {
		
	}
	
	/**
	 * This method calls the dialog for adding a new Reference to the given
	 * property
	 * 
	 * @param editingDomain
	 *            the editing domain for which the dialog should be called and
	 *            should execute on
	 * @param propertyFqn
	 *            the reference property which should be set by this dialog
	 */
	protected void executeEReferenceSelectionDialog(EditingDomain editingDomain, String propertyFqn) {
		// We can do some aggressive casting here, this code should never be
		// called in case we do
		// not have a reference property instance.
		EReferencePropertyInstance propertyInstance = (EReferencePropertyInstance) caHelper.getPropertyInstance(propertyFqn);
		EReferenceProperty propertyDefinition = ((EReferenceProperty) propertyInstance.getType());
		
		EReferencePropertyHelper propertyHelper = new EReferencePropertyHelper();
		EClass referencePropertyType = propertyHelper.getResolvedEClassType(propertyDefinition);

		Set<String> supportedFileExtensions = new HashSet<String>();
		supportedFileExtensions.add(propertyHelper.getEPackageOfType(propertyDefinition).getName());
		
		ReferenceSelectionDialog dialog = createReferenceDialogAndSetInput(propertyInstance, referencePropertyType, supportedFileExtensions);

		dialog.setAllowMultiple(false);
		dialog.setDoubleClickSelects(true);
		if (dialog.open() == Dialog.OK) {
			Object selection = dialog.getFirstResult();
			if (selection instanceof EObject) {
				EObject selectedTypeInstance = (EObject) selection;
				Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.EREFERENCE_PROPERTY_INSTANCE__REFERENCE, selectedTypeInstance);
				editingDomain.getCommandStack().execute(cmd);
			}
		}
	}
	
	
	/**
	 * An overridable method to set dialog input
	 * property
	 * 
	 * @param input the input for the dialog
	 * @param referencePropertyType the reference property which should be set by this dialog
	 *            
	 * @return the dialog with the input
	 */
	protected ReferenceSelectionDialog createReferenceDialogAndSetInput(EObject input, ATypeDefinition referencePropertyType) {
		ReferenceSelectionDialog dialog = ReferenceSelectionDialog.createRefernceSelectionDialog(Display.getCurrent().getActiveShell(), referencePropertyType, adapterFactory);
		dialog.setInput(input.eResource());
		return dialog;
	}
	
	/**
	 * An overridable method to set dialog input
	 * property
	 * 
	 * @param input the input for the dialog
	 * @param referencePropertyType the reference property which should be set by this dialog
	 *            
	 * @return the dialog with the input
	 */
	protected ReferenceSelectionDialog createReferenceDialogAndSetInput(EObject input, EClass referencePropertyType, Set<String> fileEndings) {
		ReferenceSelectionDialog dialog = ReferenceSelectionDialog.createERefernceSelectionDialog(Display.getCurrent().getActiveShell(), referencePropertyType, fileEndings, adapterFactory);
		dialog.setInput(input.eResource());
		return dialog;
	}

	@Override
	protected void initializeHelperForModel(EObject model) {
		super.initializeHelperForModel(model);
		if (model instanceof CategoryAssignment) {
			caModel = (CategoryAssignment) model;
		} else {
			caModel = null;
		}
		caHelper = new CategoryAssignmentHelper(caModel);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void setDataBinding(DataBindingContext dbCtx, EditingDomain editingDomain, EObject model) {
		initializeHelperForModel(model);

		// Loop over all the property instances get their type
		// and see if this section has some widget for it
		for (APropertyInstance propertyInstance : caModel.getPropertyInstances()) {
			AProperty property = (AProperty) propertyInstance.getType();

			String propertyFqn = property.getFullQualifiedName();
			// Now try to get the widgets which is registered to the property
			Button buttonCheckOverride = mapPropertyToButtonOverride.get(propertyFqn);
			Text textValue = mapPropertyToTextValue.get(propertyFqn);
			Text textUri = mapPropertyToTextUri.get(propertyFqn);
			Text textComposedName = mapPropertyToTextComposedName.get(propertyFqn);
			ComboViewer comboViewerUnit = mapPropertyToComboViewerUnit.get(propertyFqn);
			ComboViewer comboViewerEnum = mapPropertyToComboViewerEnum.get(propertyFqn);
			ComboViewer comboViewerValue = mapPropertyToComboViewerBooleanValue.get(propertyFqn);

			if (buttonCheckOverride != null) {
				IValueProperty<EObject, ?> overrideProperty = EMFEditProperties.value(editingDomain,
						InheritancePackage.Literals.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE);
				dbCtx.bindValue(WidgetProperties.buttonSelection().observe(buttonCheckOverride),
						overrideProperty.observe(propertyInstance));
			}

			if (textValue != null) {
				IValueProperty<EObject, ?> vpiValueProperty = EMFEditProperties.value(editingDomain,
						PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE);
				
				// For the text fields with values we wrap the observable into a Proxy that is
				// capable of correctly redirecting set requests to the canExecute method of the
				// commands which or underlying to the EMFEditProperties
				dbCtx.bindValue(WidgetProperties.text(SWT.Modify).observe(textValue),
						VirSatEMFEditObservables.proxy(vpiValueProperty.observe(propertyInstance)));
			}

			if (textUri != null) {
				IValueProperty<EObject, ?> rpiUriProperty = EMFEditProperties.value(editingDomain,
						PropertyinstancesPackage.Literals.RESOURCE_PROPERTY_INSTANCE__RESOURCE_URI);
				dbCtx.bindValue(WidgetProperties.text(SWT.Modify).observe(textUri),
						rpiUriProperty.observe(propertyInstance));
			}

			if (textComposedName != null) {
				FeaturePath featurePath = FeaturePath.fromList(
						PropertyinstancesPackage.Literals.COMPOSED_PROPERTY_INSTANCE__TYPE_INSTANCE,
						GeneralPackage.Literals.INAME__NAME);
				IValueProperty<EObject, ?> cpiTypeProperty = EMFEditProperties.value(editingDomain, featurePath);
				dbCtx.bindValue(WidgetProperties.text(SWT.Modify).observe(textComposedName),
						cpiTypeProperty.observe(propertyInstance));
			}

			if (comboViewerEnum != null) {
				
				// now do the DataBinding
				IConverter noEnumToNullConverter = new Converter(Object.class, Object.class) {
					@Override
					public Object convert(Object fromObject) {
						if (fromObject instanceof NoEnum) {
							return null;
						} else {
							return fromObject;
						}
					}
				};

				IConverter nullToNoEnumConverter = new Converter(Object.class, Object.class) {
					@Override
					public Object convert(Object fromObject) {
						if (fromObject == null) {
							return NoEnum.NOENUM;
						} else {
							return fromObject;
						}
					}
				};
				
				comboViewerEnum.setInput(property);
				IValueProperty<EObject, ?> uvpiUnitProperty = EMFEditProperties.value(editingDomain, PropertyinstancesPackage.Literals.ENUM_UNIT_PROPERTY_INSTANCE__VALUE);
				dbCtx.bindValue(ViewerProperties.singleSelection().observe(comboViewerEnum), uvpiUnitProperty.observe(propertyInstance),
						new UpdateValueStrategy().setConverter(noEnumToNullConverter),
						new UpdateValueStrategy().setConverter(nullToNoEnumConverter));
			}

			if (comboViewerUnit != null) {
				// Now try to get the Repository to get to the unit management
				VirSatResourceSet virSatResourceSet = VirSatResourceSet.getVirSatResourceSet(model);
				SystemOfUnits systemOfUnits = virSatResourceSet.getUnitManagement().getSystemOfUnit();
				comboViewerUnit.setInput(systemOfUnits);

				// now do the DataBinding
				IConverter noUnitToNullConverter = new Converter(Object.class, Object.class) {
					@Override
					public Object convert(Object fromObject) {
						if (fromObject instanceof NoUnit) {
							return null;
						} else {
							return fromObject;
						}
					}
				};

				IConverter nullToNoUnitConverter = new Converter(Object.class, Object.class) {
					@Override
					public Object convert(Object fromObject) {
						if (fromObject == null) {
							return NoUnit.NOUNIT;
						} else {
							return fromObject;
						}
					}
				};

				IValueProperty<EObject, ?> uvpiUnitProperty = EMFEditProperties.value(editingDomain,
						PropertyinstancesPackage.Literals.IUNIT_PROPERTY_INSTANCE__UNIT);
				dbCtx.bindValue(ViewerProperties.singleSelection().observe(comboViewerUnit),
						uvpiUnitProperty.observe(propertyInstance),
						new UpdateValueStrategy().setConverter(noUnitToNullConverter),
						new UpdateValueStrategy().setConverter(nullToNoUnitConverter));
			}

			if (comboViewerValue != null) {
				IValueProperty<EObject, ?> uvpiValueProperty = EMFEditProperties.value(editingDomain,
						PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE);
				dbCtx.bindValue(ViewerProperties.singleSelection().observe(comboViewerValue),
						uvpiValueProperty.observe(propertyInstance));
			}
		}
		
		// Add binding change listeners to every binding, so it can update the decorators
		// accordingly in case they are added to the bound SWT widget
		for (ValidationStatusProvider provider : dbCtx.getValidationStatusProviders()) {
			if (provider instanceof Binding) {
				Binding binding = (Binding) provider;
				binding.getValidationStatus().addChangeListener(new BindingChangeListener(binding));
			}
		}
	}
	
	/**
	 * Implementation of a change listener which knows about the
	 * Binding it should work on. This class is used together with
	 * the validation providers of the DataBindingContext and it
	 * reacts to changes in both ways from and to the model.
	 * Once data changes the listener will try to update the decorator of the
	 * involved SWT widget
	 * @author fisc_ph
	 *
	 */
	private class BindingChangeListener implements IChangeListener {
		
		private Binding binding;
		
		/**
		 * Constructor with the Binding context of the SWT and EMF object
		 * @param binding The binding from the Binding Context
		 */
		private BindingChangeListener(Binding binding) {
			this.binding = binding;
		}

		@Override
		public void handleChange(ChangeEvent event) {
			IStatus status = (IStatus) binding.getValidationStatus().getValue();
			Control control = null;
			if (binding.getTarget() instanceof ISWTObservable) {
				ISWTObservable swtObservable = (ISWTObservable) binding.getTarget();
				control = (Control) swtObservable.getWidget();
			}
			ControlDecoration decoration = mapControlToDecoration.get(control);
			if (decoration != null) {
				if (status.isOK()) {
					decoration.hide();
				} else {
					if (status.getSeverity() == Status.ERROR) {
						FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
						decoration.setImage(fieldDecoration.getImage());
					} else if (status.getSeverity() == Status.WARNING) {
						FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_WARNING);
						decoration.setImage(fieldDecoration.getImage());
					} else if (status.getSeverity() == Status.INFO) {
						FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION);
						decoration.setImage(fieldDecoration.getImage());
					}
					decoration.setDescriptionText(status.getMessage());
					decoration.show();
				}
			}
		}
	}

	@Override
	public void setSelection(ISelection selection) {
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
	}

	@Override
	public ISelection getSelection() {
		return null;
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
	}

	/**
	 * This method hands back an image representing the state of the displayed
	 * Property instance that represents the given property. This method
	 * identifies from the currently displayed model which is a CA the
	 * corresponding PI by the given property and then searches for Resource
	 * Markers that are relevant for it. In case there are resource markers the
	 * correct image indicating a warning etc will be selected.
	 * 
	 * @param aprop
	 *            the property Definition for which to find resource markers in
	 *            the currently opened model / CA
	 * @return an image showing the correct state
	 */
	private Image getProblemImage(AProperty aprop) {
		Image image = null;
		APropertyInstance propertyInstance = caHelper.getPropertyInstance(aprop);
		image = mip.getProblemImageForEObject(propertyInstance);
		return image;
	}

	/**
	 * Get the tooltip text for problem image
	 * 
	 * @param aprop Aproperty for wich to geth the correct image representing a problem
	 * @return tooltip text
	 */
	private String getProblemImageToolTip(AProperty aprop) {
		APropertyInstance propertyInstance = caHelper.getPropertyInstance(aprop);
		return mip.getProblemImageToolTipForEObject(propertyInstance);
	}

	@Override
	public void updateState(boolean state) {
		boolean uiEnabled = RightsHelper.hasSystemUserWritePermission(model) & state;

		PropertyInstanceHelper piHelper = new PropertyInstanceHelper();
		
		// Update all text value fields depending on rights
		for (Entry<String, Text> entry : mapPropertyToTextValue.entrySet()) {
			String propertyFqn = entry.getKey();
			Text textValue = entry.getValue();
			APropertyInstance pi = caHelper.getPropertyInstance(propertyFqn);
			textValue.setEditable(uiEnabled && !piHelper.isCalculated(pi));
		}

		// Update all ComboBoxes for booleanValues
		for (ComboViewer comboViewerBooleanValue : mapPropertyToComboViewerBooleanValue.values()) {
			comboViewerBooleanValue.getCombo().setEnabled(uiEnabled);
		}

		// Update all ComboxBoxes for selecting the Enums
		for (ComboViewer comboViewerUnit : mapPropertyToComboViewerEnum.values()) {
			comboViewerUnit.getCombo().setEnabled(uiEnabled);
		}

		// Update all ComboxBoxes for selecting the Units
		for (ComboViewer comboViewerUnit : mapPropertyToComboViewerUnit.values()) {
			comboViewerUnit.getCombo().setEnabled(uiEnabled);
		}

		for (Entry<String, Text> entry : mapPropertyToTextReferenceName.entrySet()) {
			String propertyFqn = entry.getKey();
			Text textReferenceName = entry.getValue();
			if (textReferenceName != null) {
				textReferenceName.setText(getLabelOfReferencedAti(propertyFqn));
			}
		}

		// update all buttons for selecting a reference
		for (Button buttonSelectReference : mapPropertyToButtonSelectReference.values()) {
			buttonSelectReference.setEnabled(uiEnabled);
		}

		// update all buttons for selecting the override state
		boolean caHasSuperTiLink = !caModel.getSuperTis().isEmpty();
		boolean caIsInherited = !((StructuralElementInstance) CategoryAssignmentHelper
				.getContainerFor((ATypeInstance) model)).getSuperSeis().isEmpty();
		for (Button buttonOverride : mapPropertyToButtonOverride.values()) {
			if (!caHasSuperTiLink) {
				buttonOverride.setVisible(false);
			} else {
				buttonOverride.setVisible(true);
				buttonOverride.setEnabled(uiEnabled && caIsInherited);
			}
		}
		
		// update icons for problems in properties
		for (Entry<String, Label> entry : mapPropertyToLabelPropertyIcon.entrySet()) {
			String apropFqn = entry.getKey();
			Label label = entry.getValue();
			APropertyInstance propertyInstance = caHelper.getPropertyInstance(apropFqn);
			
			AProperty property = (AProperty) propertyInstance.getType();
			Image problemImage = getProblemImage(property);
			if (problemImage == null) {
				if (piHelper.isCalculated(propertyInstance)) {
					// Give precedence for problem marker icons
					// If there is no problem and the property is calculated
					// Then we show the calculated icon
					label.setVisible(true);
					label.setImage(imageCalculated);
					label.setToolTipText("This field is calculated by some equation");
				} else {
					label.setVisible(false);
				}
			} else {
				label.setVisible(true);
				label.setImage(problemImage);
				label.setToolTipText(getProblemImageToolTip(property));
			}
		}
	}
	
	@Override
	protected Set<EObject> getPossiblyMarkedObjects() {
		Set<EObject> possiblyMarkedObjects = new HashSet<EObject>();
		List<AProperty> properties = ActiveConceptHelper.getNonArrayProperties(getCategory());
		for (AProperty property : properties) {
			APropertyInstance pi = caHelper.getPropertyInstance(property);
			possiblyMarkedObjects.add(pi);
		}
		return possiblyMarkedObjects;
	}
}
