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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
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
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyinstancesSwitch;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.Char;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.ObjectStruct;
import us.hebi.matlab.mat.types.Struct;
 
/**
 * Class for exporting data to .mat
 */
public class MatImporter {

	public void importSei(StructuralElementInstance sei, String matf) throws IOException {
		MatFile mat = Mat5.readFromFile(matf);
		//maybe check TODO ask!
		if (checkIfCorrectSei(sei, mat)) {
			Struct struct = mat.getStruct(sei.getName());
			Char a = struct.getChar("uuid");
			importSei(sei, struct);
		}
	}

	private void importSei(StructuralElementInstance sei, Struct seiStruct) {
		//import children recursively
		if (seiStruct.getFieldNames().contains(MatHelper.CHILDREN)) {
			Struct matChildren = seiStruct.getStruct(MatHelper.CHILDREN);
			EList<StructuralElementInstance> seiChildren = sei.getChildren();
			for (StructuralElementInstance seiChild : seiChildren) {
				importSei(seiChild, matChildren.getStruct(seiChild.getName()));
			}
			seiStruct.remove(MatHelper.CHILDREN);
		}
		seiStruct.remove(MatHelper.TYPE);
		seiStruct.remove(MatHelper.UUID);
		EList<CategoryAssignment> seiCas = sei.getCategoryAssignments();
		importCas(seiCas, seiStruct);
	}

	private void importCas(EList<CategoryAssignment> seiCas, Struct struct) {
		
		List<String> nameMatCas = struct.getFieldNames();
		List<String> nameSeiCas = new ArrayList<String>();
		for (CategoryAssignment seiCa : seiCas) {
			nameSeiCas.add(seiCa.getName());
		}

		for (CategoryAssignment seiCa : seiCas) {
			if (nameMatCas.contains(seiCa.getName())) { //import all given CategoryAssinments
				importGivenCa(seiCa, struct.get(seiCa.getName()));
				nameMatCas.remove(seiCa.getName());
			} else { //delete old ones
				seiCas.remove(seiCa);
			}
		}

		//import all new CategoryAssinments
		//for (String nameMatCa : nameMatCas) {
		//	seiCas.add(importNewCa(struct.get(nameMatCa),nameMatCa));
		//}
	}
	
	//TODO is this working for subcat

	private void importGivenCa(CategoryAssignment seiCa, Struct struct) {
		EList<APropertyInstance> seiAPIs = seiCa.getPropertyInstances();
		List<String> nameMatAPIs = struct.getFieldNames();
		
		//import all given APropertyInstances
		for (APropertyInstance seiAPI : seiAPIs) {
			if (nameMatAPIs.contains(seiAPI.getType().getName())) {
				importGivenAPI(seiAPI, struct.get(seiAPI.getType().getName()));
				nameMatAPIs.remove(seiAPI.getType().getName());
			} else {
				seiAPIs.remove(seiAPI);
			}
		}

		//import all new APropertyInstances
		//for (String nameMatAPI : nameMatAPIs) {
		//	seiAPIs.add(importNewAPI(struct.get(nameMatAPI)));
		//}
	}

//	private CategoryAssignment importNewCa(Struct struct, String nameMatCa) {
//		return null;
//	}
//
//	private APropertyInstance importNewAPI(Struct struct) {
//		return null;
//	}



	
	private void importGivenAPI(APropertyInstance seiAPI, Struct struct) {
		getRightPropertyBySei(seiAPI, struct);
	}

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
				return contentOfProperty(object, struct);
			}

		}.doSwitch(element);
		return done;
	}
	
	protected Boolean contentOfProperty(ArrayInstance object, Struct struct) {
		// TODO Auto-generated method stub
		return true;
	}

	protected Boolean contentOfProperty(ComposedPropertyInstance object, Struct struct) {
		// TODO Auto-generated method stub
		return true;
	}

	protected Boolean contentOfProperty(EReferencePropertyInstance object, Struct struct) {
		// TODO Auto-generated method stub
		return true;
	}

	protected Boolean contentOfProperty(ReferencePropertyInstance element, Struct struct) {
		if (element.getType() instanceof ReferenceProperty) {
			ATypeInstance rTI = element.getReference();
			rTI.setUuid(struct.get("uuid"));
			rTI.setComment(struct.get(MatHelper.FULLNAME));
		}
		return true;
	}

	protected Boolean contentOfProperty(ValuePropertyInstance element, Struct struct) {
		if (element.getType() instanceof BooleanProperty) {
			BeanPropertyBoolean bpb = new BeanPropertyBoolean(element);
			bpb.setValue(struct.get("value"));
		} else if (element.getType() instanceof StringProperty) {
			BeanPropertyString bps = new BeanPropertyString(element);
			bps.setValue(struct.get("value"));
		}
		return true;
	}

	private Boolean contentOfProperty(EnumUnitPropertyInstance element, Struct struct) {
		if (element.getType() instanceof EnumProperty) {
			BeanPropertyEnum bpe = new BeanPropertyEnum(element);
			bpe.setValue(struct.get("name"));
			bpe.setUnit(struct.get("unit"));
		}
		return true;
	}

	private Boolean contentOfProperty(ResourcePropertyInstance element, Struct struct) {
		if (element.getType() instanceof ResourceProperty) {
			BeanPropertyResource bpr = new BeanPropertyResource(element);
			bpr.setValue(struct.get("uri"));
		}
		return true;
	}

	private Boolean contentOfProperty(UnitValuePropertyInstance element, Struct struct) {
		if (element.getType() instanceof FloatProperty) {
			BeanPropertyFloat bpf = new BeanPropertyFloat(element);
			bpf.setUnit(struct.get("Unit"));
			bpf.setValue(struct.get("Value"));
		} else if (element.getType() instanceof IntProperty) {
			BeanPropertyInt bpi = new BeanPropertyInt(element);
			bpi.setUnit(struct.get("Unit"));
			bpi.setValue(struct.get("Value"));
		}
		return true;
	}

//	private String getRigthProperty(Struct struct) {
//		List<String> fields = struct.getFieldNames();
//		if (fields.contains("uri") && fields.size() == 1) {
//			return "Resource";
//		} else if (fields.contains("value") && fields.size() == 1) {
//			return "Value";
//		} else if (fields.contains("value") && fields.contains("unit") && fields.size() == 2) {
//			return "Float";
//		} else if (fields.contains("unit") && fields.contains("value") && fields.contains("name") && fields.size() == 3) {
//			return "Enum";
//		} else if (fields.contains("uuid") && fields.contains("fullQualifiedInstanceName") && fields.size() == 2) {
//			return "Reference";
//		} else if (fields.contains("reference") && fields.contains("reference-class") && fields.size() == 2) {
//			return "EReference";
//		}
//		return null;
//	}

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

		//check Category Assignments
//		
//		if (sei.getCategoryAssignments().size() == structFields.size()) {
//			if (sei.getCategoryAssignments().size() > 0) {
//				List<String> cas = new ArrayList<String>();
//				for (CategoryAssignment ca : sei.getCategoryAssignments()) {
//					cas.add(ca.getName());
//				}
//				if (!cas.equals(structFields)) {
//					return false;
//				}
//			}
//		} else {
//			return false;
//		}
		return true;
	}

	//Delete First and Last Character
	public String shorter(String str) {
		return str.substring(1, str.length() - 1);
	}
}
