/*******************************************************************************
 * Copyright (c) 2008-2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.mat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ArrayInstanceImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyinstancesSwitch;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;
import us.hebi.matlab.mat.types.Cell;
 
/**
 * Class for exporting data to .mat
 */
public class MatImporter {
	private PropertyInstanceHelper piHelper = new PropertyInstanceHelper();
	private StructuralElementInstance sei;
	private EditingDomain editingDomain;
	
	/**
	 * checks if mat and sei fit together
	 * @param sei sei which should be changed
	 * @param matFile MatFile that includes all Information
	 * @return 
	 */
	public CompoundCommand importSei(EditingDomain editingDomain, StructuralElementInstance sei, MatFile mat) throws IOException {
		CompoundCommand importCommand = new CompoundCommand();
		this.editingDomain = editingDomain;
		this.sei = sei;
		if (checkIfCorrectSei(sei, mat)) {
			Struct struct = mat.getStruct(sei.getName());
			importSei(importCommand, sei, struct);
		}
		return importCommand;
	}

	/**
	 * split up children and CategoryAssinments
	 * @param importCommand 
	 * @param sei sei which should be changed
	 * @param seiStruct MatStruct that includes all Information
	 */
	public void importSei(CompoundCommand importCommand, StructuralElementInstance sei, Struct seiStruct) {
		if (seiStruct.getFieldNames().contains(MatHelper.CHILDREN)) {
			Struct matChildren = seiStruct.getStruct(MatHelper.CHILDREN);
			EList<StructuralElementInstance> seiChildren = sei.getChildren();
			for (StructuralElementInstance seiChild : seiChildren) {
				importSei(importCommand, seiChild, matChildren.getStruct(seiChild.getName()));
			}
			seiStruct.remove(MatHelper.CHILDREN);
		}
		EList<CategoryAssignment> seiCas = sei.getCategoryAssignments();
		importCas(importCommand, seiCas, seiStruct);
	}

	/**
	 * split up CategoryAssinment
	 * @param importCommand 
	 * @param seiCas List of CategoryAssinments which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	private void importCas(CompoundCommand importCommand, EList<CategoryAssignment> seiCas, Struct struct) {
		
		List<String> nameMatCas = struct.getFieldNames();
		List<String> nameSeiCas = new ArrayList<String>();
		for (CategoryAssignment seiCa : seiCas) {
			nameSeiCas.add(seiCa.getName());
		}

		for (int i = 0; i < seiCas.size(); i++) {
			if (nameMatCas.contains(seiCas.get(i).getName())) { //import all given CategoryAssinments
				importGivenCa(importCommand, seiCas.get(i), struct.get(seiCas.get(i).getName()));
				nameMatCas.remove(seiCas.get(i).getName());
			}
		}
	}
	
	/**
	 * split up PropertyInstances
	 * @param seiCa CategoryAssinments which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	private void importGivenCa(CompoundCommand importCommand, CategoryAssignment seiCa, Struct struct) {
		EList<APropertyInstance> seiAPIs = seiCa.getPropertyInstances();
		List<String> nameMatAPIs = struct.getFieldNames();
		
		//import all given APropertyInstances
		for (int i = 0; i < seiAPIs.size(); i++) {
			APropertyInstance pi = seiAPIs.get(i);
			if (nameMatAPIs.contains(pi.getType().getName())) {
				// Calculated pis are not writeable since they are recomputed as soon as 
				// the calculcation builder is executed. They should not be re-imported, 
				// or otherwise the entire command becomes unexecutable.
				if (!getPiHelper().isCalculated(pi)) {
					if (!(seiAPIs.get(i) instanceof ArrayInstanceImpl)) {
						importGivenAPI(importCommand, pi, struct.get(pi.getType().getName()));
					} else {
						importGivenAPI(importCommand, pi, struct);
					}
				}
				nameMatAPIs.remove(pi.getType().getName());
			}
		}
	}

	/**
	 * import a given Property
	 * @param importCommand 
	 * @param seiAPI PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	private void importGivenAPI(CompoundCommand importCommand, APropertyInstance seiAPI, Struct struct) {
		getRightPropertyBySei(importCommand, seiAPI, struct);
	}

	/**
	 * import a given Property as Instance of
	 * 
	 * return value is not needed or used
	 * @param importCommand 
	 * @param element PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	private Boolean getRightPropertyBySei(CompoundCommand importCommand, APropertyInstance element, Struct struct) {
		Boolean done = new PropertyinstancesSwitch<Boolean>() {
			@Override
			public Boolean caseUnitValuePropertyInstance(UnitValuePropertyInstance object) {
				return contentOfProperty(importCommand, object, struct);
			}

			@Override
			public Boolean caseResourcePropertyInstance(ResourcePropertyInstance object) {
				return contentOfProperty(importCommand, object, struct);
			}

			@Override
			public Boolean caseEnumUnitPropertyInstance(EnumUnitPropertyInstance object) {
				return contentOfProperty(importCommand, object, struct);
			}

			@Override
			public Boolean caseValuePropertyInstance(ValuePropertyInstance object) {
				return contentOfProperty(importCommand, object, struct);
			}

			@Override
			public Boolean caseReferencePropertyInstance(ReferencePropertyInstance object) {
				return contentOfProperty(importCommand, object, struct);
			}

			@Override
			public Boolean caseEReferencePropertyInstance(EReferencePropertyInstance object) {
				return contentOfProperty(importCommand, object, struct);
			}

			@Override
			public Boolean caseComposedPropertyInstance(ComposedPropertyInstance object) {
				return contentOfProperty(importCommand, object, struct);
			}

			@Override
			public Boolean caseArrayInstance(ArrayInstance object) {
				return contentOfProperty(importCommand, object, struct.getCell(object.getType().getName()));
			}
		}.doSwitch(element);
		return done;
	}
	
	/**
	 * import a given ArrayInstance
	 * 
	 * updates everything inside it
	 * @param importCommand 
	 * @param element PropertyInstance which should be changed
	 * @param cell MatCell that includes all Information
	 */
	protected Boolean contentOfProperty(CompoundCommand importCommand, ArrayInstance element, Cell cell) {
		EList<APropertyInstance> propertyInstances = element.getArrayInstances();
		int [] dims = cell.getDimensions();
		for (int i = 0; i < dims[0]; i++) {
			importGivenAPI(importCommand, propertyInstances.get(i), cell.get(i));
		}
		return true;
	}

	/**
	 * import a given ComposedPropertyInstance
	 * 
	 * updates everything inside it
	 * @param element PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	protected Boolean contentOfProperty(CompoundCommand importCommand, ComposedPropertyInstance element, Struct struct) {
		CategoryAssignment ca = element.getTypeInstance();	
		importGivenCa(importCommand, ca, struct);
		return true;
	}

	/**
	 * import a given EReferencePropertyInstance
	 * 
	 * updates value
	 * @param element PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	protected Boolean contentOfProperty(CompoundCommand importCommand, EReferencePropertyInstance element, Struct struct) {
		if (element.getType() instanceof EReferenceProperty) {
			BeanPropertyEReference<EReferenceProperty> bpe = new BeanPropertyEReference<EReferenceProperty>(element);
			if ("''".equals(struct.get(MatHelper.URI).toString())) {
				importCommand.append(bpe.setValue(editingDomain, null));
			} else {
				URI uri = URI.createURI(shorter(struct.get(MatHelper.URI).toString()), true);
				Resource res = new ResourceSetImpl().getResource(uri, true);
				EObject eReferenceValue = res.getEObject(uri.fragment());
				importCommand.append(bpe.setValue(editingDomain, eReferenceValue));
				
			}
		}
		return true;
	}

	/**
	 * import a given ReferencePropertyInstance
	 * 
	 * updates reference
	 * @param element PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	protected Boolean contentOfProperty(CompoundCommand importCommand, ReferencePropertyInstance element, Struct struct) {
		if (element.getType() instanceof ReferenceProperty) {
			if ("''".equals(struct.get(MatHelper.UUID).toString())) {
				importCommand.append(SetCommand.create(editingDomain, element, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, null));
			} else {
				EList<Resource> res = sei.eResource().getResourceSet().getResources();
				for (Resource re : res) {
					EObject ref = re.getEObject(shorter(struct.get(MatHelper.UUID).toString()));
					if (ref != null) {
						if (ref instanceof ATypeInstance) {
							importCommand.append(SetCommand.create(editingDomain, element, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, ref));
						} 
					}
				}
			}
		}
		return true;
	}

	/**
	 * import a given ValuePropertyInstance
	 * 
	 * updates value
	 * @param element PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	protected Boolean contentOfProperty(CompoundCommand importCommand, ValuePropertyInstance element, Struct struct) {
		if (element.getType() instanceof BooleanProperty) {
			BeanPropertyBoolean bpb = new BeanPropertyBoolean(element);
			if (!struct.get(MatHelper.VALUE).toString().equals("''")) {
				if (struct.get(MatHelper.VALUE).toString().equals("true")) {
					importCommand.append(bpb.setValue(editingDomain, true));
				} else {
					importCommand.append(bpb.setValue(editingDomain, false));
				}
			} else {
				Command cmd = SetCommand.create(editingDomain, element, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, null);
				editingDomain.getCommandStack().execute(cmd);
			}
		} else if (element.getType() instanceof StringProperty) {
			BeanPropertyString bps = new BeanPropertyString(element);
			if (!struct.get(MatHelper.VALUE).toString().equals("''")) {
				importCommand.append(bps.setValue(editingDomain, shorter(struct.get(MatHelper.VALUE).toString())));
			} else {
				Command cmd = SetCommand.create(editingDomain, element, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, null);
				editingDomain.getCommandStack().execute(cmd);
			}
		}
		return true;
	}

	/**
	 * import a given EnumUnitPropertyInstance
	 * 
	 * updates name and unit. value is updates automatically
	 * @param element PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	private Boolean contentOfProperty(CompoundCommand importCommand, EnumUnitPropertyInstance element, Struct struct) {
		if (element.getType() instanceof EnumProperty) {
			BeanPropertyEnum bpe = new BeanPropertyEnum(element);
			importCommand.append(bpe.setValue(editingDomain, shorter(struct.get(MatHelper.NAME).toString())));
			if (!struct.get(MatHelper.UNIT).toString().equals("''")) {
				importCommand.append(bpe.setUnit(editingDomain, shorter(struct.get(MatHelper.UNIT).toString())));
			}
		}
		return true;
	}

	/**
	 * import a given ResourcePropertyInstance
	 * 
	 * updates uri
	 * @param element PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	private Boolean contentOfProperty(CompoundCommand importCommand, ResourcePropertyInstance element, Struct struct) {
		if (element.getType() instanceof ResourceProperty) {
			BeanPropertyResource bpr = new BeanPropertyResource(element);
			if ("''".equals(struct.get(MatHelper.URI).toString())) {
				importCommand.append(bpr.setValue(editingDomain, null));
			} else {
				importCommand.append(bpr.setValue(editingDomain, URI.createURI(shorter(struct.get(MatHelper.URI).toString()), true)));
			}
		}
		return true;
	}

	/**
	 * import a given UnitValuePropertyInstance
	 * 
	 * updates value and unit
	 * @param element PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	private Boolean contentOfProperty(CompoundCommand importCommand, UnitValuePropertyInstance element, Struct struct) {
		if (element.getType() instanceof FloatProperty) {
			BeanPropertyFloat bpf = new BeanPropertyFloat(element);
			if (!struct.get(MatHelper.UNIT).toString().equals("''")) {
				importCommand.append(bpf.setUnit(editingDomain, shorter(struct.get(MatHelper.UNIT).toString())));
			}
			if ("NaN".equals(struct.get(MatHelper.VALUE).toString()) || "''".equals(struct.get(MatHelper.VALUE).toString())) {
				Command cmd = SetCommand.create(editingDomain, element, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, null);
				importCommand.append(cmd);
			} else {
				importCommand.append(bpf.setValue(editingDomain, Double.valueOf(struct.get(MatHelper.VALUE).toString())));
			}
		} else if (element.getType() instanceof IntProperty) {
			BeanPropertyInt bpi = new BeanPropertyInt(element);
			if (!struct.get(MatHelper.UNIT).toString().equals("''")) {
				importCommand.append(bpi.setUnit(editingDomain, shorter(struct.get(MatHelper.UNIT).toString())));
			}
			if ("NaN".equals(struct.get(MatHelper.VALUE).toString()) || "''".equals(struct.get(MatHelper.VALUE).toString())) {
				importCommand.append(SetCommand.create(editingDomain, element, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, null));
			} else {
				double value = Double.valueOf(struct.get(MatHelper.VALUE).toString());
				importCommand.append(bpi.setValue(editingDomain, (long) value));
			}
		}
		return true;
	}

	/**
	 * hands back boolean that represents if the MatFile and the sei are equal
	 * @param sei StructuralElementInstance to test
	 * @param mat MatFile to test
	 */
	public boolean checkIfCorrectSei(StructuralElementInstance sei, MatFile mat) {
		try {
			Struct seiStruct = mat.getStruct(sei.getName());
			return checkIfCorrectSei(sei, seiStruct);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * hands back boolean that represents if the struct and the sei are equal
	 * @param sei StructuralElementInstance to test
	 * @param seiStruct Struct to test
	 */
	private boolean checkIfCorrectSei(StructuralElementInstance sei, Struct seiStruct) {
		List<String> structFields = seiStruct.getFieldNames();

		//check Type and Uuid
		if (structFields.contains("type") && structFields.contains("uuid")) {
			String matSeiType = shorter(seiStruct.getChar("type").toString()); 
			String matSeiUUID = shorter(seiStruct.getChar("uuid").toString());
			structFields.remove("type");
			structFields.remove("uuid");
			if (!matSeiType.equals(sei.getType().getName()) || !matSeiUUID.equals(sei.getUuid().toString())) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Delete First and Last Character of a string.
	 * It is needed because matlab generates '' around strings
	 * @param str String that should be shorted
	 */
	public String shorter(String str) {
		return str.substring(1, str.length() - 1);
	}
	
	/**
	 * Override this method to inject a different property instance helper.
	 * This is useful, e.g., in test cases
	 * @return the pi helper
	 */
	protected PropertyInstanceHelper getPiHelper() {
		return piHelper;
	}
}
