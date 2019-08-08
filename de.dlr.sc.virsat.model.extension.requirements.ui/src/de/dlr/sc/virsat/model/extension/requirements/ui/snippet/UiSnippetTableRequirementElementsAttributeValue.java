/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.snippet;

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.build.marker.ui.EsfMarkerImageProvider;
import de.dlr.sc.virsat.build.marker.ui.MarkerImageProvider;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.ui.propertyinstance.util.PreferencedPropertyInstanceValueSwitchFactory;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;
import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementElementsAttributeValue extends AUiSnippetTableRequirementElementsAttributeValue
		implements IUiSnippet {

	private static final String VALUE_PROPERTY_NAME = "value";
	private static final int VALUE_COLUMN_SIZE = 700;
	
	protected PropertyInstanceValueSwitch valueSwitch = PreferencedPropertyInstanceValueSwitchFactory.createInstance();
	protected EsfMarkerImageProvider emip = new EsfMarkerImageProvider();
	

	/**
	 * Constructor for this editor snippet
	 */
	public UiSnippetTableRequirementElementsAttributeValue() {
		this.style = STYLE_EDITOR_BUTTON;
		valueSwitch.setShowLocationForReferenceValues(false);
	}

	@Override
	protected void createTableColumns(EditingDomain editingDomain) {

		for (AProperty property : categoryModel.getAllProperties()) {

			TableViewerColumn colProperty = (TableViewerColumn) createDefaultColumn(property.getName());
			colProperty.getColumn().setToolTipText(property.getDescription());
			colProperty.setEditingSupport(createEditingSupport(editingDomain, property));
			
			if (property.getName().equals(VALUE_PROPERTY_NAME)) {
				colProperty.getColumn().setWidth(VALUE_COLUMN_SIZE);
			}
		}

	}

	// Show attributes in the order of their creation
	@Override
	protected void setUpTableViewer(EditingDomain editingDomain, FormToolkit toolkit) {
		super.setUpTableViewer(editingDomain, toolkit);
		
		List<?> tableObjects = getTableObjects();
		
		// Sort the array entries by their index
		columnViewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				int lhsIndex = tableObjects.indexOf(e1);
				int rhsIndex = tableObjects.indexOf(e2);
				return Integer.compare(lhsIndex, rhsIndex);
			}
		});
	}

	
	/**
	 * this method overrides the default label provider by not returning the name and 
	 * by not showing the attribute references complete location path
	 * 
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

					if (columnIndex > numProperties) {
						// columns for inherited properties
						if (ca.isIsInherited()) {
							Set<IInheritanceLink> rootTis = InheritanceCopier.getRootSuperTypeInstance(ca);
							if (rootTis.isEmpty()) {
								Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(),
										"AUiSnippetGenericCategoryAssignmentTable: Failed to get root instance"));
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
						int propertyIndex = columnIndex;
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
					// First check if its a composed property instance so we can get the actual
					// category assignment
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
					// but we actually display PI content in the columns in some cases we display
					// referenced content
					// such as the name of an IFT that is referenced by an IFE
					CategoryAssignment ca = (CategoryAssignment) object;

					// The other columns now display the PIs given with this CA
					int propertyIndex = columnIndex;
					APropertyInstance propertyInstance = ca.getPropertyInstances().get(propertyIndex);
					// we have to make sure to redirect notifications to get the viewers update
					// right
					// The content provider does not know its is displaying PI's of the CA's
					// therefore a change on the PI's have to be redirected correctly
					redirectNotification(propertyInstance, object);
					// Here we check if for example we show a ReferencePropertyInstance, in such
					// case we usually
					/// want to display the image of the actually referenced TI rather than the
					// image of the RPI
					// in case things change here, we have to redirect that notification as well.
					// This is probably less
					// important for the image than for the name, etc. since the image will most
					// likely not be changed
					// by some user interaction.
					ATypeInstance ti = valueSwitch.doSwitch(propertyInstance);
					redirectNotification(ti, object);

					superImage = super.getColumnImage(ti, columnIndex);

					// the resource marker is not on the referenced TI but on the RPI
					Image problemImage = mip.getProblemImageForEObject(propertyInstance);
					// And finally hand back an original image or override it with some marker
					// related one
					return (problemImage != null) ? problemImage : superImage;

				}

				return superImage;
			}
		};

		return labelProvider;
	}
	
	
	@Override
	protected List<APropertyInstance> getTableObjects() {
		return getArrayInstance(model).getArrayInstances();
	}

}
