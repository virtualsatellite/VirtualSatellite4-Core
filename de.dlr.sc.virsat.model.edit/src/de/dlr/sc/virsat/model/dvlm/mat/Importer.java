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

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
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
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.Array;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;

/**
 * Class for exporting data to .mat
 */
public class Importer {

	public void importSei(StructuralElementInstance sei, File file) throws IOException {
		MatFile mat = Mat5.readFromFile(file);
		//maybe check TODO ask!
		if (checkIfCorrectSei(sei, mat)) {
			Struct struct = mat.getStruct(sei.getName());
			importSei(sei, struct);
		}
	}

	private void importSei(StructuralElementInstance sei, Struct struct) {
		//import children recursively
		if (struct.getFieldNames().contains("children")) {
			Struct matChildren = struct.getStruct("children");
			EList<StructuralElementInstance> seiChildren = sei.getChildren();
			for (StructuralElementInstance seiChild : seiChildren) {
				importSei(seiChild, matChildren.getStruct(seiChild.getName()));
			}
			struct.remove("children");
			
		}
		struct.remove("type");
		struct.remove("uuid");
		EList<CategoryAssignment> seiCas = sei.getCategoryAssignments();
		importCas(seiCas, struct);
	}

	private void importCas(EList<CategoryAssignment> seiCas, Struct struct) {
		
		List<String> nameMatCas = struct.getFieldNames();
		List<String> nameSeiCas = new ArrayList<String>();
		for (CategoryAssignment seiCa : seiCas) {
			nameSeiCas.add(seiCa.getName());
		}

		//import all given CategoryAssinments and delete old ones
		for (CategoryAssignment seiCa : seiCas) {
			if (nameMatCas.contains(seiCa.getName())) {
				importGivenCa(seiCa, struct.get(seiCa.getName()));
				nameMatCas.remove(seiCa.getName());
			} else {
				seiCas.remove(seiCa);
			}
		}

		//import all new CategoryAssinments
		//for (String nameMatCa : nameMatCas) {
		//	seiCas.add(importNewCa(struct.get(nameMatCa),nameMatCa));
		//}
	}

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
		getRightProperty(seiAPI, struct);
	}

	private Boolean getRightProperty(APropertyInstance element, Struct struct) {
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
		return null;
	}

	protected Boolean contentOfProperty(ComposedPropertyInstance object, Struct struct) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Boolean contentOfProperty(EReferencePropertyInstance object, Struct struct) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Boolean contentOfProperty(ReferencePropertyInstance object, Struct struct) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Boolean contentOfProperty(ValuePropertyInstance object, Struct struct) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Boolean contentOfProperty(EnumUnitPropertyInstance object, Struct struct) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Boolean contentOfProperty(ResourcePropertyInstance object, Struct struct) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Boolean contentOfProperty(UnitValuePropertyInstance object, Struct struct) {
		// TODO Auto-generated method stub
		return null;
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
			String matSeiType = shorter(seiStruct.get("type").toString()); 
			String matSeiUUID = shorter(seiStruct.get("uuid").toString());
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
