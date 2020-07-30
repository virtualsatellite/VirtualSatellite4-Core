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
import java.util.Set;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.build.marker.ui.EsfMarkerImageProvider;
import de.dlr.sc.virsat.build.marker.ui.MarkerImageProvider;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.util.DVLMIsActiveCheck;
import de.dlr.sc.virsat.model.ui.propertyinstance.util.PreferencedPropertyInstanceValueSwitchFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;
import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.uieingine.ui.dnd.ADropSelectionTargetListener;
import de.dlr.sc.virsat.uieingine.ui.dnd.DropHelper;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.PropertyInstanceEditingSupportFactory;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.emfattributes.EStringCellEditingSupport;

/**
 * this abstract class UI snippet generic category assignment table extends abstract class ui snippet generic table and implements the interface
 * UI snippet for the generic category assignment table view part.
 *   
 * @author leps_je
 *
 */
public abstract class AUiSnippetGenericCategoryAssignmentTable extends AUiSnippetGenericTable implements IUiSnippet {

	/**
	 * This class implements the Tables behavior for some methods that
	 * might be changed for implementations such as a tree table.
	 * This is used for example in the CEF implementations of this table
	 * which need some other behavior than the standard table provides.
	 * E.g. parameters showing their mdoe values etc.
	 * @author fisc_ph
	 *
	 */
	public class UiSnippetGenericTableImpl {
		
		protected EsfMarkerImageProvider emip = new EsfMarkerImageProvider();
		protected PropertyInstanceValueSwitch valueSwitch = PreferencedPropertyInstanceValueSwitchFactory.createInstance();
		
		/**
		 * Implementation of the createTableColumns Method
		 * @param editingDomain the editing domain which shall be used to provide the editing support in the columns
		 */
		protected void createTableColumns(EditingDomain editingDomain) {
			TableViewerColumn colName = (TableViewerColumn) createDefaultColumn(COLUMN_TEXT_NAME);
			
			//we know that createDefaultColumn returns tableViewerColumn so we cast it to tableViewerColumn
			colName.setEditingSupport(new EStringCellEditingSupport(editingDomain, (TableViewer) columnViewer, GeneralPackage.Literals.INAME__NAME));

			if (hideNameColumn) {
				colName.getColumn().setWidth(0);
			}
			
			for (AProperty property : categoryModel.getAllProperties()) {
				
				TableViewerColumn colProperty = (TableViewerColumn) createDefaultColumn(property.getName());
				colProperty.getColumn().setToolTipText(property.getDescription());
				colProperty.setEditingSupport(createEditingSupport(editingDomain, property));
			}
			
			if (model instanceof StructuralElementInstance) {
				StructuralElement se = ((StructuralElementInstance) model).getType();
				if (se.isIsCanInheritFromAll() || se.getCanInheritFrom().size() > 0) {
					createDefaultColumn(COLUMN_TEXT_ORIGIN);
				}
			}
		}
		
		/**
		 * this method get the content provider
		 * @return the content provider
		 */
		protected IStructuredContentProvider getTableContentProvider() {
			VirSatFilteredWrappedTreeContentProvider contentProvider = new VirSatFilteredWrappedTreeContentProvider(adapterFactory);
			contentProvider.addClassFilterToGetElement(CategoryAssignment.class);
			contentProvider.addCategoryIdFilter(fullQualifiedCategoryId);
			return contentProvider;
		}
		
		/**
		 * this method get the label provider
		 * @return the table column provider
		 */
		protected ITableLabelProvider getTableLabelProvider() {
			VirSatTransactionalAdapterFactoryLabelProvider labelProvider;
			labelProvider = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory) {
				
				private MarkerImageProvider mip = new MarkerImageProvider(new VirSatProblemMarkerHelper());
				
				@Override
				public String getColumnText(Object object, int columnIndex) {
					if (object == null) {
						return "";
					}
					
					if (object instanceof ComposedPropertyInstance) {
						ComposedPropertyInstance cpi = (ComposedPropertyInstance) object;
						CategoryAssignment ca = cpi.getTypeInstance();
						redirectNotification(ca, object);
						return getColumnText(cpi.getTypeInstance(), columnIndex);
					}

					super.getColumnText(object, columnIndex);
					
					if (object instanceof CategoryAssignment) {
						CategoryAssignment ca = (CategoryAssignment) object;
						int numProperties = ca.getPropertyInstances().size();
						
						// COlumn 0 is always the name where as column 1 means the first property thus accessing it by 0
						if (columnIndex == 0) {
							return ca.getName();
						} else if (columnIndex > numProperties) {
							// columns for inherited properties
							if (ca.isIsInherited()) {
								Set<IInheritanceLink> rootTis = InheritanceCopier.getRootSuperTypeInstance(ca);
								if (rootTis.isEmpty()) {
									Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "AUiSnippetGenericCategoryAssignmentTable: Failed to get root instance"));
									return ""; 
								} 
								IInheritanceLink rootTi = rootTis.iterator().next();
								if (rootTi instanceof IInstance) {
									String rootFqn = ((IInstance) rootTi).getFullQualifiedInstanceName();
									return rootFqn;
								}
							}
							return "";
						} else {
							int propertyIndex = columnIndex - 1;
							APropertyInstance propertyInstance = ca.getPropertyInstances().get(propertyIndex);
							redirectNotification(propertyInstance, object);
							ATypeInstance ti = valueSwitch.doSwitch(propertyInstance);
							redirectNotification(ti, object);
							
							if (propertyInstance instanceof ArrayInstance) {
								ArrayInstance ai = (ArrayInstance) propertyInstance;
								for (APropertyInstance piArrayInstance : ai.getArrayInstances()) {
									ATypeInstance tiArrayInstance = valueSwitch.doSwitch(piArrayInstance);
									redirectNotification(tiArrayInstance, object);
								}
							}
							
							return valueSwitch.getValueString(propertyInstance);
						}
					}
					return ((IName) object).getName();
				}
				
				@Override
				public Image getColumnImage(Object object, int columnIndex) {
					if (object instanceof ComposedPropertyInstance) {
						// First check if its a composed property instance so we can get the actual category assignment
						ComposedPropertyInstance cpi = (ComposedPropertyInstance) object;
						CategoryAssignment ca = cpi.getTypeInstance();
						redirectNotification(ca, object);
						return getColumnImage(cpi.getTypeInstance(), columnIndex);
					}
					
					// Retrieve the standard image of the whatever object
					Image superImage = super.getColumnImage(object, columnIndex);
					
					if (object instanceof ComposedPropertyInstance) {
						ComposedPropertyInstance cpi = (ComposedPropertyInstance) object;
						CategoryAssignment ca = cpi.getTypeInstance();
						redirectNotification(ca, object);
						return getColumnImage(cpi.getTypeInstance(), columnIndex);
					}

					if (object instanceof CategoryAssignment) {
						// The tables are always fed with CategoryAssignments by their content providers
						// but we actually display PI content in the columns in some cases we display referenced content
						// such as the name of an IFT that is referenced by an IFE
						CategoryAssignment ca = (CategoryAssignment) object;
						int numProperties = ca.getPropertyInstances().size();

						// COlumn 0 is always the name where as column 1 means the first property thus accessing it by 0
						if (columnIndex == 0) {
							// Here we ask, if the CA itself has warning or error. S
							// Now the method is improved so we can decide on the EStructural feature that should be used for filtering	
							Image problemImage = emip.getProblemImageForStructuralFeatureInEobject(ca, GeneralPackage.Literals.INAME__NAME);
							return (problemImage != null) ? problemImage : superImage;
						} else if (columnIndex > numProperties) {
							if (ca.isIsInherited()) {
								return superImage;
							} 
							return null;
						} else {
							// The other columns now display the PIs given with this CA
							int propertyIndex = columnIndex - 1;
							APropertyInstance propertyInstance = ca.getPropertyInstances().get(propertyIndex);
							// we have to make sure to redirect notifications to get the viewers update right
							// The content provider does not know its is displaying PI's of the CA's
							// therefore a change on the PI's have to be redirected correctly
							redirectNotification(propertyInstance, object);
							// Here we check if for example we show a ReferencePropertyInstance, in such case we usually
							///want to display the image of the actually referenced TI rather than the image of the RPI
							// in case things change here, we have to redirect that notification as well. This is probably less
							// important for the image than for the name, etc. since the image will most likely not be changed
							// by some user interaction.
							ATypeInstance ti = valueSwitch.doSwitch(propertyInstance);
							redirectNotification(ti, object);
							
							superImage = super.getColumnImage(ti, columnIndex);
												
							// the resource marker is not on the referenced TI but on the RPI
							Image problemImage = mip.getProblemImageForEObject(propertyInstance);
							// And finally hand back an original image or override it with some marker related one
							return (problemImage != null) ? problemImage : superImage;
						}
					}
					
					return superImage;
				}
			};
			
			return labelProvider;		
		}
		
		/**
		 *  
		 * @return PropertyInstanceValueSwitch 
		 */
		public PropertyInstanceValueSwitch getPropertyInstanceValueSwitch() {
			return valueSwitch;
		}

		/**
		 * Implementation of the method to create the actual Viewer for this table
		 * @param toolkit The toolkit to be used for creating the viewer
		 * @return A ColumnViewer as used in the table
		 */
		protected ColumnViewer createColumnViewer(FormToolkit toolkit) {
			return AUiSnippetGenericCategoryAssignmentTable.super.createColumnViewer(toolkit);
		}
		
		/**
		 * Method to hand back the current ColumnVuiewwer of the table
		 * @return The current column viewer
		 */
		protected ColumnViewer getColumnViewer() {
			return columnViewer;
		}
		
		/**
		 * Method to access the current Body Section
		 * @return the current body section
		 */
		protected Composite getSectionBody() {
			return sectionBody;
		}
		
		/**
		 * Method to access the current Active Concept Helper
		 * @return the current ACHelper
		 */
		protected ActiveConceptHelper getActiveConceptHelper() {
			return acHelper;
		}
		
		/**
		 * Method to hand back the ConceptID
		 * @return the current ConceptID
		 */
		protected String getConceptId() {
			return conceptId;
		}
		
		/**
		 * Method to hand back the current AdapterFactory
		 * @return the adapter factory of the current table.
		 */
		protected ComposedAdapterFactory getAdapterFactory() {
			return adapterFactory;
		}
		/**
		 * Method implementation for creating a column in the current table Q/''Q>
		 * @param columnText the name of the column
		 * @return the created column
		 */
		protected ViewerColumn createDefaultColumn(String columnText) {
			return AUiSnippetGenericCategoryAssignmentTable.super.createDefaultColumn(columnText);
		}

		/**
		 * Method implementation for adding actions to the tables context menu
		 * @param editingDomain the editing domain to be used for the actions redirecting to commands
		 * @param manager the menu manager in which to create the menu entries
		 */
		protected void fillContextMenuAdditions(EditingDomain editingDomain, IMenuManager manager) {
			AUiSnippetGenericCategoryAssignmentTable.super.fillContextMenuAdditions(editingDomain, manager);
		}
	}
	
	/**
	 * Override this method to fill extra content to the menu manager in the Additions section
	 * @param editingDomain The current editing domain
	 * @param manager the menu manager of the context menu
	 */
	protected void fillContextMenuAdditions(EditingDomain editingDomain, IMenuManager manager) {
		snippetImplementation.fillContextMenuAdditions(editingDomain, manager);
	}
	
	protected String fullQualifiedCategoryId;
	protected Category categoryModel;
	protected boolean hideNameColumn = false;
	protected UiSnippetGenericTableImpl snippetImplementation;
	
	private static final String COLUMN_TEXT_NAME = "Name";
	private static final String COLUMN_TEXT_ORIGIN = "Is Inherited From";
	
	/**
	 * COnstructor for this class to instantiate a UI Snippet
	 * 
	 * @param conceptId The ID of the Concept in which to look for the Category
	 * @param categoryId The ID of the Category
	 * @param fullQualifiedCategoryId The full qualified Name which means the conceptID + plus nested IDs of the categories
	 * @param style 
	 */
	public AUiSnippetGenericCategoryAssignmentTable(String conceptId, String categoryId, String fullQualifiedCategoryId, int style) {
		super(conceptId, categoryId, style);
		this.fullQualifiedCategoryId = fullQualifiedCategoryId;
		this.snippetImplementation = createImplementation();
	}
	
	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
		
		DropHelper.createDropTarget(columnViewer.getControl(), new ADropSelectionTargetListener(editingDomain) {
			@Override
			public Command createDropCommand(StructuredSelection selection) {
				Concept activeConcept = acHelper.getConcept(conceptId);
				Object selectedObject = selection.getFirstElement();
				
				if (selectedObject instanceof Category && isAddEnabled()) {
					Category cat = (Category) selectedObject;
					if (cat.getName().equals(categoryId)) {
						Command dropCommand = createAddCommand(editingDomain, activeConcept);
						return dropCommand;
					}
				}
				
				return UnexecutableCommand.INSTANCE;
			}
		});
	}
	
	
	/**
	 * This method instantiate the behavior of the current table.
	 * Overload this method to provide a different implementation for different
	 * table behavior such as in CEFF
	 * @return the Implementation class for the current Table
	 */
	protected UiSnippetGenericTableImpl createImplementation() {
		return new UiSnippetGenericTableImpl();
	}

	@Override
	protected ColumnViewer createColumnViewer(FormToolkit toolkit) {
		return snippetImplementation.createColumnViewer(toolkit);
	}

	@Override
	protected ViewerColumn createDefaultColumn(String columnText) {
		return snippetImplementation.createDefaultColumn(columnText);
	}	

	/**
	 * this method get the content provider
	 * @return the content provider
	 */
	protected IStructuredContentProvider getTableContentProvider() {
		return snippetImplementation.getTableContentProvider();
	}
	
	/**
	 * this method get the label provider
	 * @return the table column provider
	 */
	protected ITableLabelProvider getTableLabelProvider() {
		return snippetImplementation.getTableLabelProvider();		
	}
			
	@Override
	protected String getTypeInformation() {
		return categoryId;
	}
	
	@Override
	protected ATypeDefinition getType() {
		return categoryModel;
	}
	
	@Override
	protected Collection<? extends EObject> getTableObjects() {
		return CategoryAssignmentHelper.getCategoryAssignmentsOfType((ICategoryAssignmentContainer) model, categoryModel.getFullQualifiedName());
	}
	
	@Override
	protected void initializeHelperForModel(EObject newModel) {
		super.initializeHelperForModel(newModel);
		categoryModel = getCategory();
	}
	
	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
		snippetImplementation.createTableColumns(editingDomain);
	}

	@Override
	public boolean isActive(EObject model) {
		initializeHelperForModel(model);
		boolean hasActiveConcept = categoryModel != null;
		if (!hasActiveConcept) {
			return false;
		}
		
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(categoryModel, categoryId);
		boolean isCategoryAssignmentContainer = model instanceof ICategoryAssignmentContainer;
		DVLMIsActiveCheck check = new DVLMIsActiveCheck(model, true);
		return isCategoryAssignmentContainer && check.isValidObject(ca);
	}
	
	@Override
	public void updateState(boolean state) {
		super.updateState(state);
		
		// Disable the Add Button if the full cardinality is reached
		boolean isValid = isAddEnabled();
		setButtonAddEnabled(isValid);
		//disable remove button if no object is selected
		setButtonRemoveEnabled(!columnViewer.getSelection().isEmpty());
	}
	/**
	 * An overridable method for creating an Editing Support
	 * @param editingDomain the editing domain
	 * @param property the property
	 * @return the editing support
	 */
	protected EditingSupport createEditingSupport(EditingDomain editingDomain, AProperty property) {
		return PropertyInstanceEditingSupportFactory.INSTANCE.createEditingSupportFor(editingDomain, columnViewer, property);
		
	}
	
	/**
	 * Checks if the add button should be enabled
	 * @return true iff the add button is enabled
	 */
	private boolean isAddEnabled() {
		EditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(model);
		Concept activeConcept = acHelper.getConcept(conceptId);
		Command addCommand = createAddCommand(ed, activeConcept);
		return addCommand.canExecute();
	}
}
