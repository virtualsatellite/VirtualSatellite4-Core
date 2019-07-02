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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.graphics.Image;

import de.dlr.sc.virsat.build.marker.ui.EsfMarkerImageProvider;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.model.ui.propertyinstance.util.PreferencedPropertyInstanceValueSwitchFactory;
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
public class UiSnippetTableRequirementsSpecificationRequirementsRequirement extends AUiSnippetTableRequirementsSpecificationRequirementsRequirement implements IUiSnippet {

	private static final String COLUMN_TEXT_STATUS = "Status";
	private static final String COLUMN_ATTRIBUTE_SEPARATOR = " / ";
	
	private static final int STATUS_COLUMN = 0;
	
	private static final int REQUIREMENT_STATUS_PROPERTY_NUMBER = 2;
	private static final int REQUIREMENT_ELEMENTS_PROPERTY_NUMBER = 1;
	
	private static final int TYPE_COLUMN_WIDTH = 100;
	
	protected Set<RequirementType> requirementTypes = new HashSet<RequirementType>();
	protected int maxNumberAttributes = 0;
	
	protected PropertyInstanceValueSwitch valueSwitch = PreferencedPropertyInstanceValueSwitchFactory.createInstance();
	protected EsfMarkerImageProvider emip = new EsfMarkerImageProvider();
	
	/**
	 * 
	 */
	public UiSnippetTableRequirementsSpecificationRequirementsRequirement() {
		super();
		valueSwitch.setShowEnumValueDefinitionValues(false);
	}



	/**
	 * Implementation of the createTableColumns Method
	 * @param editingDomain the editing domain which shall be used to provide the editing support in the columns
	 */
	protected void createTableColumns(EditingDomain editingDomain) {
		
		//Status column
		TableViewerColumn colProperty = (TableViewerColumn) createDefaultColumn(COLUMN_TEXT_STATUS);
		colProperty.setEditingSupport(createEditingSupport(editingDomain, categoryModel.getProperties().get(REQUIREMENT_STATUS_PROPERTY_NUMBER)));
		colProperty.getColumn().setWidth(TYPE_COLUMN_WIDTH);

		if (model instanceof CategoryAssignment) {
			RequirementsSpecification requirementsSpecification = new RequirementsSpecification((CategoryAssignment) model);
			
			//Find all necessary requirement types
			for (Requirement requirement :  requirementsSpecification.getRequirements()) {
				RequirementType requirementType = requirement.getReqType();
				requirementTypes.add(requirementType);
				if (requirementType.getAttributes().size() > maxNumberAttributes) {
					maxNumberAttributes = requirementType.getAttributes().size();
				}
			}
			
			//Add necessary table columns
			for (int i = 0; i < maxNumberAttributes; i++) {
				String columnName = "";
				for (RequirementType requirementType : requirementTypes) {
				
					String name = requirementType.getAttributes().get(i).getName();
					
					//Add separator if column is used for different attributes
					if (!columnName.equals("") && !columnName.equals(name)) {
						columnName += COLUMN_ATTRIBUTE_SEPARATOR;
					}
				
					if (requirementType.getAttributes().size() > i && !columnName.equals(name)) {
						columnName += name;
					}
				}
				createDefaultColumn(columnName);
				
			}
		}
		
	}
	
	/**
	 * this method get the label provider
	 * @return the table column provider
	 */
	protected ITableLabelProvider getTableLabelProvider() {
		VirSatTransactionalAdapterFactoryLabelProvider labelProvider;
		labelProvider = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory) {
			
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
					
					// COlumn 0 is always the name where as column 1 means the first property thus accessing it by 0
					if (columnIndex == STATUS_COLUMN) {

						APropertyInstance propertyInstance = ca.getPropertyInstances().get(REQUIREMENT_STATUS_PROPERTY_NUMBER);
						redirectNotification(propertyInstance, object);
						ATypeInstance ti = valueSwitch.doSwitch(propertyInstance);
						redirectNotification(ti, object);
						
						return valueSwitch.getValueString(propertyInstance);
						
					} else if (columnIndex > STATUS_COLUMN) {
						APropertyInstance propertyInstance = ca.getPropertyInstances().get(REQUIREMENT_ELEMENTS_PROPERTY_NUMBER);
						redirectNotification(propertyInstance, object);
						
						if (propertyInstance instanceof ArrayInstance) {
							int attIndex = columnIndex - 1; // Status Column 
							return getValueOfAttributeIndex((ArrayInstance) propertyInstance, attIndex);
						}
					}
				}
				return null;
			}
			
			@Override
			public Image getColumnImage(Object object, int columnIndex) {
					return null;
			}
				
		};
		
		return labelProvider;		
	}
	
	/**
	 * Return the value to the array element of a given index
	 * @param arrayInstance the array
	 * @param attributeIndex the index
	 * @return the value
	 */
	protected String getValueOfAttributeIndex(ArrayInstance arrayInstance, int attributeIndex) {
		String attributValue = null;
		for (APropertyInstance instance : arrayInstance.getArrayInstances()) {
			
			if (instance instanceof ComposedPropertyInstance) {
				ComposedPropertyInstance prInstance = (ComposedPropertyInstance) instance;
				AttributeValue value = new AttributeValue(prInstance.getTypeInstance());
				
				//Find out initial column index of attribute
				RequirementType requirementType = value.getAttType().getParentCaBeanOfClass(RequirementType.class);
				if (requirementType.getAttributes().indexOf(value.getAttType()) == attributeIndex) {
					return value.getValue();
				}
					
			}	
		}
		
		return attributValue;
	}
	
}
