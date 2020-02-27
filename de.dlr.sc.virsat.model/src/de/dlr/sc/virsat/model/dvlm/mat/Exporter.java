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

import org.eclipse.emf.common.util.EList;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.FloatPropertyImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.IntPropertyImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ArrayInstanceImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ComposedPropertyInstanceImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EReferencePropertyInstanceImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EnumUnitPropertyInstanceImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ReferencePropertyInstanceImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ResourcePropertyInstanceImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.UnitValuePropertyInstanceImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.ValuePropertyInstanceImpl;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.Array;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;

/**
 * Class for exporting data to .mat
 */
public class Exporter {
	
	private Exporter(StructuralElementInstance seiRoot) throws IOException {
		exportSei(seiRoot);
	}

	/**
	 * creates a new .mat with everything inside a sei
	 * @param seiRoot sei to export
	 */
	public static MatFile exportSei(StructuralElementInstance seiRoot) throws IOException {
		MatFile matfile = Mat5.newMatFile();		
		Struct myStruct = Mat5.newStruct();
		myStruct.set("Type", Mat5.newString(seiRoot.getType().getName()))
			.set("UUID", Mat5.newString(seiRoot.getUuid().toString()))
			.set("Children", Mat5.newString(seiRoot.getChildren().toString()));
		for (int i = 0; i < seiRoot.getCategoryAssignments().size(); i++) {
			CategoryAssignment ca = seiRoot.getCategoryAssignments().get(i);
			myStruct.set(ca.getName(), exportCatAs(ca));
		}
		matfile.addArray(seiRoot.getName(), myStruct);
		return matfile;
	}
	
	/**
	 * creates a new .mat with everything inside the selected CategoryAssinments
	 * @param cas List of categoryAssinments
	 */	
	public static MatFile exportCas(EList<CategoryAssignment> cas) throws IOException {
		MatFile matfile = Mat5.newMatFile();		
		Struct myStruct = Mat5.newStruct();
		for (int i = 0; i < cas.size(); i++) {
			CategoryAssignment ca = cas.get(i);
			myStruct.set(ca.getName(), exportCatAs(ca));
		}
		matfile.addArray("Input", myStruct);
		return matfile;
	}

	/**
	 * creates a new .mat-Struct with all Propertys of an CategoryAssinment
	 * @param ca CategoryAssignment
	 */
	public static Struct exportCatAs(CategoryAssignment ca) {
		Struct struct = Mat5.newStruct();
		EList<APropertyInstance> propertyInstances = ca.getPropertyInstances();

		for (int i = 0; i < propertyInstances.size(); i++) {
			APropertyInstance element = propertyInstances.get(i);

			switch (element.getClass().getSimpleName()) {
				case("ResourcePropertyInstanceImpl"):
					struct.set(propertyInstances.get(i).getType().getName(), contentofProperty((ResourcePropertyInstanceImpl) element));
					break;

				case("EnumUnitPropertyInstanceImpl"):
					struct.set(propertyInstances.get(i).getType().getName(), contentofProperty((EnumUnitPropertyInstanceImpl) element));
					break;

				case("ValuePropertyInstanceImpl"):
					struct.set(propertyInstances.get(i).getType().getName(), contentofProperty((ValuePropertyInstanceImpl) element));
					break;

				case("UnitValuePropertyInstanceImpl"):
					struct.set(propertyInstances.get(i).getType().getName(), contentofProperty((UnitValuePropertyInstanceImpl) element));
					break;

				case("ReferencePropertyInstanceImpl"):
					struct.set(propertyInstances.get(i).getType().getName(), contentofProperty((ReferencePropertyInstanceImpl) element));
					break;

				case("EReferencePropertyInstanceImpl"):
					struct.set(propertyInstances.get(i).getType().getName(), contentofProperty((EReferencePropertyInstanceImpl) element));
					break;

				case("ComposedPropertyInstanceImpl"):
					struct.set(propertyInstances.get(i).getType().getName(), contentofProperty((ComposedPropertyInstanceImpl) element));
					break;

				case("ArrayInstanceImpl"):
					struct.set(propertyInstances.get(i).getType().getName(), contentofProperty((ArrayInstanceImpl) element));
					break;

				default:
					struct.set(propertyInstances.get(i).getType().getName(), contentofProperty(element));
			}
		}
		return struct;
	}
	
//	}
//	ATypeDefinition type = element.getType();
//	if (type instanceof StringProperty) {
//		struct.set(propertyInstances.get(i).getType().getName(), contentofProperty(element));
//	} else if (type instanceof IntProperty) {
//		struct.set(propertyInstances.get(i).getType().getName(), contentofProperty(element));
//	} else if (type instanceof FloatProperty) {
//		struct.set(propertyInstances.get(i).getType().getName(), contentofProperty(element));
//	} else if (type instanceof BooleanProperty) {
//		struct.set(propertyInstances.get(i).getType().getName(), contentofProperty(element));
//	} else if (type instanceof Resource) {
//		struct.set(propertyInstances.get(i).getType().getName(), contentofProperty(element));
//	} else if (type instanceof EnumProperty) {
//		struct.set(propertyInstances.get(i).getType().getName(), contentofProperty(element));
//	} else {
//		struct.set(propertyInstances.get(i).getType().getName(), contentofProperty(element));
//	}
//}
	
	//create a struct for the fitting PropertyInstance
	/**
	 * creates a new .mat-Struct with all Information about PropertyInstance
	 * only UUID instanceType and Property Type are saved
	 * @param castElement PropertyInstance
	 */
	private static Array contentofProperty(APropertyInstance castElement) {
		Struct struct = Mat5.newStruct();
		struct.set("instanceTyp", Mat5.newString(castElement.getClass().getSimpleName()));
		struct.set("propertyTyp", Mat5.newString(castElement.getType().getClass().getSimpleName()));
		struct.set("UUID", Mat5.newString(castElement.getUuid().toString()));
		return struct;
	}
	
	/**
	 * creates a new .mat-Struct with all Information about UnitValuePropertyInstance
	 * only UUID Quantity , unit and Value are saved
	 * @param castElement PropertyInstance
	 */
	private static Array contentofProperty(UnitValuePropertyInstanceImpl castElement) {
		Struct struct = Mat5.newStruct();
		struct.set("UUID", Mat5.newString(castElement.getUuid().toString()));
		if (castElement.getType().getClass().getSimpleName().equalsIgnoreCase("FloatPropertyImpl")) {
			FloatPropertyImpl castElementF = (FloatPropertyImpl) castElement.basicGetType();
			struct.set("Quantity", Mat5.newString(castElementF.getQuantityKindName() + ""));
			struct.set("Unit", Mat5.newString(castElementF.getUnitName() + ""));
			struct.set("Value", Mat5.newScalar(Float.parseFloat("1")));
		}
		
		if (castElement.getType().getClass().getSimpleName().equalsIgnoreCase("IntPropertyImpl")) {
			IntPropertyImpl castElementF = (IntPropertyImpl) castElement.basicGetType();
			struct.set("Quantity", Mat5.newString(castElementF.getQuantityKindName() + ""));
			struct.set("Unit", Mat5.newString(castElementF.getUnitName() + ""));
			struct.set("Value", Mat5.newScalar(Integer.parseInt("1")));
		}
		return struct;
	}
	
	/**
	 * creates a new .mat-Struct with all Information about UnitValuePropertyInstance
	 * only UUID Quantity , unit and Value are saved
	 * @param castElement PropertyInstance
	 */
	private static Struct contentofProperty(ValuePropertyInstanceImpl castElement) {
		Struct struct = Mat5.newStruct();
		struct.set("instanceTyp", Mat5.newString(castElement.getClass().getSimpleName()));
		struct.set("propertyTyp", Mat5.newString(castElement.getType().getClass().getSimpleName()));
		struct.set("UUID", Mat5.newString(castElement.getUuid().toString()));
		struct.set("Value", Mat5.newString(castElement.getValue() + ""));
		return struct;
	}
	
	private static Array contentofProperty(ArrayInstanceImpl castElement) {
		Struct struct = Mat5.newStruct();
		struct.set("instanceTyp", Mat5.newString(castElement.getClass().getSimpleName()));
		struct.set("propertyTyp", Mat5.newString(castElement.getType().getClass().getSimpleName()));
		struct.set("UUID", Mat5.newString(castElement.getUuid().toString()));

		return struct;
	}
	
	private static Array contentofProperty(ComposedPropertyInstanceImpl castElement) {
		Struct struct = Mat5.newStruct();
		struct.set("instanceTyp", Mat5.newString(castElement.getClass().getSimpleName()));
		struct.set("propertyTyp", Mat5.newString(castElement.getType().getClass().getSimpleName()));
		struct.set("UUID", Mat5.newString(castElement.getUuid().toString()));
		return struct;
	}
	
	private static Array contentofProperty(EReferencePropertyInstanceImpl castElement) {
		Struct struct = Mat5.newStruct();
		struct.set("instanceTyp", Mat5.newString(castElement.getClass().getSimpleName()));
		struct.set("propertyTyp", Mat5.newString(castElement.getType().getClass().getSimpleName()));
		struct.set("UUID", Mat5.newString(castElement.getUuid().toString()));
		return struct;
	}
	

	private static Array contentofProperty(ReferencePropertyInstanceImpl castElement) {
		Struct struct = Mat5.newStruct();
		struct.set("instanceTyp", Mat5.newString(castElement.getClass().getSimpleName()));
		struct.set("propertyTyp", Mat5.newString(castElement.getType().getClass().getSimpleName()));
		struct.set("UUID", Mat5.newString(castElement.getUuid().toString()));
		return struct;
	}
	/**
	 * creates a new .mat-Struct with all Information about ResourcePropertyInstance
	 * only UUID instanceType and Property Type are saved
	 * @param castElement PropertyInstance
	 */
	private static Array contentofProperty(ResourcePropertyInstanceImpl castElement) {
		Struct struct = Mat5.newStruct();
		struct.set("instanceTyp", Mat5.newString(castElement.getClass().getSimpleName()));
		struct.set("propertyTyp", Mat5.newString(castElement.getType().getClass().getSimpleName()));
		struct.set("UUID", Mat5.newString(castElement.getUuid().toString()));
		return struct;
	}
	
	private static Array contentofProperty(EnumUnitPropertyInstanceImpl castElement) {
		Struct struct = Mat5.newStruct();
		struct.set("instanceTyp", Mat5.newString(castElement.getClass().getSimpleName()));
		struct.set("propertyTyp", Mat5.newString(castElement.getType().getClass().getSimpleName()));
		struct.set("UUID", Mat5.newString(castElement.getUuid().toString()));
		struct.set("Value", Mat5.newString(castElement.getValue() + ""));
		return struct;
	}
	
	//Delete First and Last Character
	public static String shorter(String str) {
		return str.substring(1, str.length() - 1);
	}
}
