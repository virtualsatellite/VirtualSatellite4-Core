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
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;

/**
 * Class for exporting data to .mat
 */
public class Importer {

	public void importSei(StructuralElementInstance sei, File file) throws IOException {
		MatFile mat = Mat5.readFromFile(file);
		//maybe check TODO ask!
		//check for name, uuid and children (recursively)
		//if (checkIfCorrectSei(sei, mat)) {
		Struct struct = mat.getStruct(sei.getName());
		importSei(sei, struct);
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
		for (String nameMatCa : nameMatCas) {
			seiCas.add(importNewCa(struct.get(nameMatCa)));
		}
	}

	private CategoryAssignment importNewCa(Struct struct) {
		return null;
	}

	private void importGivenCa(CategoryAssignment seiCa, Struct struct) {
		EList<APropertyInstance> seiPI = seiCa.getPropertyInstances();
		
		
	
	}


	
	private String getRigthProperty(Struct struct) {
		List<String> fields = struct.getFieldNames();
		if (fields.contains("uri") && fields.size() == 1) {
			return "Resource";
		} else if (fields.contains("value") && fields.size() == 1) {
			return "Value";
		} else if (fields.contains("value") && fields.contains("unit") && fields.size() == 2) {
			return "Float";
		} else if (fields.contains("unit") && fields.contains("value") && fields.contains("name") && fields.size() == 3) {
			return "Enum";
		} else if (fields.contains("uuid") && fields.contains("fullQualifiedInstanceName") && fields.size() == 2) {
			return "Reference";
		} else if (fields.contains("reference") && fields.contains("reference-class") && fields.size() == 2) {
			return "EReference";
		}
		return null;
	}

	/**
	 * hands back boolean that represents if the MatFile and the sei are equal
	 * @param sei StructuralElementInstance to test
	 * @param mat MatFile to test
	 */
	protected boolean checkIfCorrectSei(StructuralElementInstance sei, MatFile mat) {
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
		
		if (sei.getCategoryAssignments().size() == structFields.size()) {
			if (sei.getCategoryAssignments().size() > 0) {
				List<String> cas = new ArrayList<String>();
				for (CategoryAssignment ca : sei.getCategoryAssignments()) {
					cas.add(ca.getName());
				}
				if (!cas.equals(structFields)) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	//Delete First and Last Character
	public String shorter(String str) {
		return str.substring(1, str.length() - 1);
	}
}
