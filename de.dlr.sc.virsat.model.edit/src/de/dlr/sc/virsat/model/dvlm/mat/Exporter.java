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

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
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
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyinstancesSwitch;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.Array;
import us.hebi.matlab.mat.types.Cell;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;

/**
 * Class for exporting data to .mat
 */
public class Exporter {
	

	/**
	 * creates a new .mat with everything inside a sei
	 * @param seiRoot sei to export
	 */
	public MatFile exportSei(StructuralElementInstance seiRoot) throws IOException {
		MatFile matfile = Mat5.newMatFile();		
		Struct myStruct = Mat5.newStruct();
		myStruct.set("type", Mat5.newString(seiRoot.getType().getName()))
			.set("uuid", Mat5.newString(seiRoot.getUuid().toString()))
			.set("children", Mat5.newString(seiRoot.getChildren().toString()));

		for (CategoryAssignment ca : seiRoot.getCategoryAssignments()) {
			myStruct.set(ca.getName(), exportCatAs(ca));
		}

		matfile.addArray(seiRoot.getName(), myStruct);
		return matfile;
	}

	/**
	 * creates a new .mat with everything inside the selected CategoryAssinments
	 * @param cas list of CategoryAssinments
	 */	
	public MatFile exportCas(EList<CategoryAssignment> cas) throws IOException {
		MatFile matfile = Mat5.newMatFile();		
		Struct myStruct = Mat5.newStruct();
		for (CategoryAssignment ca : cas) {
			myStruct.set(ca.getName(), exportCatAs(ca));
		}
		matfile.addArray("inputs", myStruct);
		return matfile;
	}

	/**
	 * creates a new .mat-struct with all properties of an CategoryAssinment
	 * @param ca CategoryAssignment
	 */
	private Struct exportCatAs(CategoryAssignment ca) {
		Struct struct = Mat5.newStruct();
		EList<APropertyInstance> propertyInstances = ca.getPropertyInstances();

		for (APropertyInstance pi : propertyInstances) {
			Array propertyArray = getRightProperty(pi);
			struct.set(pi.getType().getName(), propertyArray);
		}

		return struct;
	}

	/**
	 * hands back right property with all values
	 * @param element APropertyInstance
	 */
	private Array getRightProperty(APropertyInstance element) {
		Array propertyArray = new PropertyinstancesSwitch<Array>() {
			@Override
			public Array caseUnitValuePropertyInstance(UnitValuePropertyInstance object) {
				return contentOfProperty(object);
			}

			@Override
			public Array caseResourcePropertyInstance(ResourcePropertyInstance object) {
				return contentofProperty(object);
			}

			@Override
			public Array caseEnumUnitPropertyInstance(EnumUnitPropertyInstance object) {
				return contentofProperty(object);
			}

			@Override
			public Array caseValuePropertyInstance(ValuePropertyInstance object) {
				return contentOfProperty(object);
			}

			@Override
			public Array caseReferencePropertyInstance(ReferencePropertyInstance object) {
				return contentofProperty(object);
			}

			@Override
			public Array caseEReferencePropertyInstance(EReferencePropertyInstance object) {
				return contentofProperty(object);
			}

			@Override
			public Array caseComposedPropertyInstance(ComposedPropertyInstance object) {
				return contentofProperty(object);
			}

			@Override
			public Array caseArrayInstance(ArrayInstance object) {
				return contentofProperty(object);
			}

		}.doSwitch(element);
		return propertyArray;
	}

	//create a struct for the fitting PropertyInstance
	/**
	 * creates a new .mat-struct with all information about UnitValuePropertyInstance
	 * only unit and value are saved
	 * @param element UnitValuePropertyInstance
	 */
	private Array contentOfProperty(UnitValuePropertyInstance element) {
		Struct struct = Mat5.newStruct();

		if (element.getType() instanceof FloatProperty) {
			BeanPropertyFloat bpf = new BeanPropertyFloat(element);
			struct.set("unit", Mat5.newString(bpf.getUnit()));
			struct.set("value", (bpf.getValue() == Double.NaN) ? Mat5.newString("") : Mat5.newScalar(bpf.getValue()));
		} else if (element.getType() instanceof IntProperty) {
			BeanPropertyInt bpi = new BeanPropertyInt(element);
			struct.set("unit", Mat5.newString(bpi.getUnit()));
			struct.set("value", (!bpi.isSet()) ? Mat5.newString("") : Mat5.newScalar(bpi.getValue()));
		}
		return struct;
	}

	/**
	 * creates a new .mat-struct with all information about ValuePropertyInstance
	 * only Value is saved
	 * @param element ValuePropertyInstance
	 */
	private Struct contentOfProperty(ValuePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof BooleanProperty) {
			BeanPropertyBoolean bpb = new BeanPropertyBoolean();
			bpb.setATypeInstance(element);
			struct.set("value", (!bpb.isSet()) ? Mat5.newString("") : Mat5.newLogicalScalar(bpb.getValue()));
		} else if (element.getType() instanceof StringProperty) {
			BeanPropertyString bps = new BeanPropertyString();
			bps.setATypeInstance(element);
			struct.set("value", (!bps.isSet()) ? Mat5.newString("") : Mat5.newString(bps.getValue()));
		}
		return struct;
	}

	/**
	 * creates a new .mat-struct with all information about EReferencePropertyInstance
	 * only uuid, reference and reference-class are saved
	 * @param element EReferencePropertyInstance
	 */
	private Array contentofProperty(EReferencePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof EReferenceProperty) {
			
			BeanPropertyEReference<EReferenceProperty> bpe = new BeanPropertyEReference<EReferenceProperty>();
			bpe.setATypeInstance(element);
			struct.set("reference", (!bpe.isSet()) ? Mat5.newString("") : Mat5.newString(bpe.getValue().toString()));
			struct.set("reference-class", (!bpe.isSet()) ? Mat5.newString("") : Mat5.newString(bpe.getClass().getName()));
		}
		return struct;
	}
	
	/**
	 * creates a new .mat-struct with all information about ReferencePropertyInstance
	 * only uuid and fullQualifiedInstanceName are saved
	 * @param element ReferencePropertyInstance
	 */
	private Array contentofProperty(ReferencePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof ReferenceProperty) {
			struct.set("uuid", Mat5.newString(element.getUuid().toString()));
			struct.set("name", Mat5.newString(element.getFullQualifiedInstanceName()));
		}

		return struct;
	}
	
	/**
	 * creates a new .mat-struct with all information about ResourcePropertyInstance
	 * only uri is saved
	 * @param element ResourcePropertyInstance
	 */
	private Array contentofProperty(ResourcePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof ResourceProperty) {
			BeanPropertyResource bpr = new BeanPropertyResource(element);
			struct.set("uri", (!bpr.isSet()) ? Mat5.newString("") : Mat5.newString(bpr.getValue().toString()));
		}
		return struct;
	}
	
	/**
	 * creates a new .mat-struct with all information about EnumUnitPropertyInstance
	 * only unit,value and name are saved
	 * @param element EnumUnitPropertyInstance
	 */	
	private Array contentofProperty(EnumUnitPropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof EnumProperty) {
			BeanPropertyEnum bpe = new BeanPropertyEnum(element);
			struct.set("unit", Mat5.newString(bpe.getUnit()));
			struct.set("value", (!bpe.isSet()) ? Mat5.newString("") : Mat5.newScalar(bpe.getEnumValue()));
			struct.set("name", (!bpe.isSet()) ? Mat5.newString("") : Mat5.newString(bpe.getValue()));
		}
		return struct;
	}

	/**
	 * creates a new .mat-struct with all information about ArrayInstance
	 * @param element ArrayInstance
	 */	
	private Array contentofProperty(ArrayInstance element) {
		EList<APropertyInstance> propertyInstances = element.getArrayInstances();
		Cell cell = Mat5.newCell(propertyInstances.size(), 1);
		for (int i = 0; i < propertyInstances.size(); i++) {
			APropertyInstance instanceElement = propertyInstances.get(i);
			Array propertyArray = getRightProperty(instanceElement);
			cell.set(i, propertyArray);
		}
		return cell;
	}
	
	/**
	 * creates a new .mat-struct with all information about ComposedPropertyInstance
	 * @param element ComposedPropertyInstance
	 */	
	private Array contentofProperty(ComposedPropertyInstance element) {
		return exportCatAs(element.getTypeInstance());
	}
	
	//Delete First and Last Character
	public String shorter(String str) {
		return str.substring(1, str.length() - 1);
	}
}
