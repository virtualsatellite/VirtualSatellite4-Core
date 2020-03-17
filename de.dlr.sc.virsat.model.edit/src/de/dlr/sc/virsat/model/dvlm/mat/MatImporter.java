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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

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
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ArrayInstanceImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyinstancesSwitch;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;
import us.hebi.matlab.mat.types.Cell;
 
/**
 * Class for exporting data to .mat
 */
public class MatImporter {
	StructuralElementInstance sei;
	
	/**
	 * checks if mat and sei fit together
	 * @param sei sei which should be changed
	 * @param matFile MatFile that includes all Information
	 */
	public void importSei(StructuralElementInstance sei, MatFile mat) throws IOException {
		this.sei = sei;
		if (checkIfCorrectSei(sei, mat)) {
			Struct struct = mat.getStruct(sei.getName());
			importSei(sei, struct);
		}
	}

	/**
	 * split up children and CategoryAssinments
	 * @param sei sei which should be changed
	 * @param seiStruct MatStruct that includes all Information
	 */
	public void importSei(StructuralElementInstance sei, Struct seiStruct) {
		if (seiStruct.getFieldNames().contains(MatHelper.CHILDREN)) {
			Struct matChildren = seiStruct.getStruct(MatHelper.CHILDREN);
			EList<StructuralElementInstance> seiChildren = sei.getChildren();
			for (StructuralElementInstance seiChild : seiChildren) {
				importSei(seiChild, matChildren.getStruct(seiChild.getName()));
			}
			seiStruct.remove(MatHelper.CHILDREN);
		}
		EList<CategoryAssignment> seiCas = sei.getCategoryAssignments();
		importCas(seiCas, seiStruct);
	}

	/**
	 * split up CategoryAssinment
	 * @param seiCas List of CategoryAssinments which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	private void importCas(EList<CategoryAssignment> seiCas, Struct struct) {
		
		List<String> nameMatCas = struct.getFieldNames();
		List<String> nameSeiCas = new ArrayList<String>();
		for (CategoryAssignment seiCa : seiCas) {
			nameSeiCas.add(seiCa.getName());
		}

		for (int i = 0; i < seiCas.size();) {
			if (nameMatCas.contains(seiCas.get(i).getName())) { //import all given CategoryAssinments
				importGivenCa(seiCas.get(i), struct.get(seiCas.get(i).getName()));
				nameMatCas.remove(seiCas.get(i).getName());
				i++;
			} else {
				seiCas.remove(i);
			}
		}

		//import all new CategoryAssinments
		//for (String nameMatCa : nameMatCas) {
		//	seiCas.add(importNewCa(struct.get(nameMatCa),nameMatCa));
		//}
	}
	
	/**
	 * split up PropertyInstances
	 * @param seiCa CategoryAssinments which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	private void importGivenCa(CategoryAssignment seiCa, Struct struct) {
		EList<APropertyInstance> seiAPIs = seiCa.getPropertyInstances();
		List<String> nameMatAPIs = struct.getFieldNames();
		
		//import all given APropertyInstances
		for (int i = 0; i < seiAPIs.size();) {
			if (nameMatAPIs.contains(seiAPIs.get(i).getType().getName())) {
				if (!(seiAPIs instanceof ArrayInstanceImpl)) {
					importGivenAPI(seiAPIs.get(i), struct.get(seiAPIs.get(i).getType().getName()));
				} else {
					importGivenAPI(seiAPIs.get(i), struct);
				}
				nameMatAPIs.remove(seiAPIs.get(i).getType().getName());
				i++;
			} else {
				seiAPIs.remove(seiAPIs.get(i));
			}
		}

		//import all new APropertyInstances
		//for (String nameMatAPI : nameMatAPIs) {
		//	seiAPIs.add(importNewAPI(struct.get(nameMatAPI)));
		//}
	}

	/**
	 * import a given Property
	 * @param seiAPI PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	private void importGivenAPI(APropertyInstance seiAPI, Struct struct) {
		getRightPropertyBySei(seiAPI, struct);
	}

	/**
	 * import a given Property as Instance of
	 * 
	 * return value is not needed or used
	 * @param element PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	private Boolean getRightPropertyBySei(APropertyInstance element, Struct struct) {
		Boolean done = new PropertyinstancesSwitch<Boolean>() {
			@Override
			public Boolean caseUnitValuePropertyInstance(UnitValuePropertyInstance object) {
				return contentOfProperty(object, struct);
			}

			@Override
			public Boolean caseResourcePropertyInstance(ResourcePropertyInstance object) {
				return contentOfProperty(object, struct);
			}

			@Override
			public Boolean caseEnumUnitPropertyInstance(EnumUnitPropertyInstance object) {
				return contentOfProperty(object, struct);
			}

			@Override
			public Boolean caseValuePropertyInstance(ValuePropertyInstance object) {
				return contentOfProperty(object, struct);
			}

			@Override
			public Boolean caseReferencePropertyInstance(ReferencePropertyInstance object) {
				return contentOfProperty(object, struct);
			}

			@Override
			public Boolean caseEReferencePropertyInstance(EReferencePropertyInstance object) {
				return contentOfProperty(object, struct);
			}

			@Override
			public Boolean caseComposedPropertyInstance(ComposedPropertyInstance object) {
				return contentOfProperty(object, struct);
			}

			@Override
			public Boolean caseArrayInstance(ArrayInstance object) {
				return contentOfProperty(object, struct.getCell(object.getType().getName()));
			}

		}.doSwitch(element);
		return done;
	}
	
	/**
	 * import a given ArrayInstance
	 * 
	 * updates everything inside it
	 * @param element PropertyInstance which should be changed
	 * @param cell MatCell that includes all Information
	 */
	protected Boolean contentOfProperty(ArrayInstance element, Cell cell) {
		EList<APropertyInstance> propertyInstances = element.getArrayInstances();
		int [] dims = cell.getDimensions();
		for (int i = 0; i < dims[0]; i++) {
			importGivenAPI(propertyInstances.get(i), cell.get(i));
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
	protected Boolean contentOfProperty(ComposedPropertyInstance element, Struct struct) {
		CategoryAssignment ca = element.getTypeInstance();	
		importGivenCa(ca, struct);
		return true;
	}

	/**
	 * import a given EReferencePropertyInstance
	 * 
	 * updates value
	 * @param element PropertyInstance which should be changed
	 * @param struct MatStruct that includes all Information
	 */
	protected Boolean contentOfProperty(EReferencePropertyInstance element, Struct struct) {
		if (element.getType() instanceof EReferenceProperty) {
			BeanPropertyEReference<EReferenceProperty> bpe = new BeanPropertyEReference<EReferenceProperty>(element);
			if ("''".equals(struct.get(MatHelper.URI).toString())) {
				bpe.unset();
			} else {
				URI uri = URI.createPlatformPluginURI(shorter(struct.get(MatHelper.URI).toString()), true);
				Resource res = new ResourceSetImpl().getResource(uri, true);
				EObject eReferenceValue = res.getEObject(uri.fragment());
				element.setReference(eReferenceValue);
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
	protected Boolean contentOfProperty(ReferencePropertyInstance element, Struct struct) {
		if (element.getType() instanceof ReferenceProperty) {
			if ("''".equals(struct.get(MatHelper.UUID).toString())) {
				element.setReference(null);
			} else {
				//StructuralElementInstance sei2 = sei;
				EList<Resource> res = sei.eResource().getResourceSet().getResources();
				for (Resource re : res) {
					EObject ref = re.getEObject(shorter(struct.get(MatHelper.UUID).toString()));
					if (ref != null) {
						if (ref instanceof ATypeInstance) {
							element.setReference((ATypeInstance) ref);
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
	protected Boolean contentOfProperty(ValuePropertyInstance element, Struct struct) {
		if (element.getType() instanceof BooleanProperty) {
			BeanPropertyBoolean bpb = new BeanPropertyBoolean(element);
			if (!struct.get(MatHelper.VALUE).toString().equals("''")) {
				if (struct.get(MatHelper.VALUE).toString().equals("true")) {
					bpb.setValue(true);
				} else {
					bpb.setValue(false);
				}
			} else {
				bpb.unset();
			}
		} else if (element.getType() instanceof StringProperty) {
			BeanPropertyString bps = new BeanPropertyString(element);
			if (!struct.get(MatHelper.VALUE).toString().equals("''")) {
				bps.setValue(shorter(struct.get(MatHelper.VALUE).toString()));
			} else {
				bps.unset();
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
	private Boolean contentOfProperty(EnumUnitPropertyInstance element, Struct struct) {
		if (element.getType() instanceof EnumProperty) {
			BeanPropertyEnum bpe = new BeanPropertyEnum(element);
			bpe.setValue(shorter(struct.get(MatHelper.NAME).toString()));
			bpe.setUnit(shorter(struct.get(MatHelper.UNIT).toString()));
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
	private Boolean contentOfProperty(ResourcePropertyInstance element, Struct struct) {
		if (element.getType() instanceof ResourceProperty) {
			BeanPropertyResource bpr = new BeanPropertyResource(element);
			if ("''".equals(struct.get(MatHelper.URI).toString())) {
				bpr.unset();
			} else {
				bpr.setValue(URI.createURI(shorter(struct.get(MatHelper.URI).toString()), true));
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
	private Boolean contentOfProperty(UnitValuePropertyInstance element, Struct struct) {
		if (element.getType() instanceof FloatProperty) {
			BeanPropertyFloat bpf = new BeanPropertyFloat(element);
			bpf.setUnit(shorter(struct.get(MatHelper.UNIT).toString()));
			if ("NaN".equals(struct.get(MatHelper.VALUE).toString()) || "''".equals(struct.get(MatHelper.VALUE).toString())) {
				bpf.unset();
			} else {
				bpf.setValue(Double.valueOf(struct.get(MatHelper.VALUE).toString()));
			}
		} else if (element.getType() instanceof IntProperty) {
			BeanPropertyInt bpi = new BeanPropertyInt(element);
			bpi.setUnit(shorter(struct.get(MatHelper.UNIT).toString()));
			if ("NaN".equals(struct.get(MatHelper.VALUE).toString()) || "''".equals(struct.get(MatHelper.VALUE).toString())) {
				bpi.unset();
			} else {
				double value = Double.valueOf(struct.get(MatHelper.VALUE).toString());
				bpi.setValue((long) value);
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
		EList<StructuralElementInstance> seiChildren = sei.getChildren();
		List<String> structFields = seiStruct.getFieldNames();

		//check Type and Uuid
		if (structFields.contains("type") && structFields.contains("uuid")) {
			String matSeiType = shorter(seiStruct.getChar("type").toString()); 
			String matSeiUUID = shorter(seiStruct.getChar("uuid").toString());
			structFields.remove(0);
			structFields.remove(0);
			if (!matSeiType.equals(sei.getType().getName()) || !matSeiUUID.equals(sei.getUuid().toString())) {
				return false;
			}
		} else {
			return false;
		}

		//check Children
		if (!structFields.contains("children")) {
			if (seiChildren.size() != 0) {
				return false;
			}
		} else {
			if (seiChildren.size() == seiStruct.getStruct("children").getFieldNames().size()) {
				Struct children = seiStruct.getStruct("children");
				structFields.remove(0);
				for (StructuralElementInstance child : seiChildren) {
					if (!checkIfCorrectSei(child, children.getStruct(child.getName()))) {
						return false;
					}
				}
			} else {
				return false;
			}
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
}
