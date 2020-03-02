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
	public MatFile exportCas(EList<CategoryAssignment> cas) throws IOException {
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
	 * creates a new .mat-Struct with all Properties of an CategoryAssinment
	 * @param ca CategoryAssignment
	 */
	public Struct exportCatAs(CategoryAssignment ca) {
		Struct struct = Mat5.newStruct();
		EList<APropertyInstance> propertyInstances = ca.getPropertyInstances();

		for (int i = 0; i < propertyInstances.size(); i++) {
			APropertyInstance element = propertyInstances.get(i);
			Array propertyArray = getrightProperty(element);
			
			struct.set(propertyInstances.get(i).getType().getName(), propertyArray);
		}
		return struct;
	}
	
	/**
	 * hands back write property with all values
	 * @param element APropertyInstance
	 */
	public Array getrightProperty(APropertyInstance element) {
		Array propertyArray = new PropertyinstancesSwitch<Array>() {
			@Override
			public Array caseUnitValuePropertyInstance(UnitValuePropertyInstance object) {
				return contentofProperty(object);
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
				return contentofProperty(object);
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
	 * creates a new .mat-Struct with all Information about UnitValuePropertyInstance
	 * only UUID Quantity , unit and Value are saved
	 * @param castElement PropertyInstance
	 */
	private Array contentofProperty(UnitValuePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		
		if (element.getType() instanceof FloatProperty) {
			BeanPropertyFloat bpf = new BeanPropertyFloat(element);
			struct.set("Unit", Mat5.newString(bpf.getUnit()));
			struct.set("Value", (bpf.getValue() == Double.NaN) ? Mat5.newString("") : Mat5.newScalar(bpf.getValue()));
		} else if (element.getType() instanceof IntProperty) {
			BeanPropertyInt bpi = new BeanPropertyInt(element);
			struct.set("Unit", Mat5.newString(bpi.getUnit()));
			struct.set("Value", (!bpi.isSet()) ? Mat5.newString("") : Mat5.newScalar(bpi.getValue()));
		}
		return struct;
	}
	
	/**
	 * creates a new .mat-Struct with all Information about UnitValuePropertyInstance
	 * only unit and Value are saved
	 * @param castElement PropertyInstance
	 */
	private Struct contentofProperty(ValuePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof BooleanProperty) {
			BeanPropertyBoolean bpb = new BeanPropertyBoolean();
			bpb.setATypeInstance(element);
			struct.set("Value", (!bpb.isSet()) ? Mat5.newString("") : Mat5.newLogicalScalar(bpb.getValue()));
		} else if (element.getType() instanceof StringProperty) {
			BeanPropertyString bps = new BeanPropertyString();
			bps.setATypeInstance(element);
			struct.set("Value", (!bps.isSet()) ? Mat5.newString("") : Mat5.newString(bps.getValue()));
		}
		return struct;
	}
	
	/**
	 * creates a new .mat-Struct with all Information about EReferencePropertyInstance
	 * only UUID Referenz and Referenc-Class are saved
	 * @param castElement PropertyInstance
	 */
	private Array contentofProperty(EReferencePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof EReferenceProperty) {
			
			BeanPropertyEReference<EReferenceProperty> bpe = new BeanPropertyEReference<EReferenceProperty>();
			bpe.setATypeInstance(element);
			struct.set("UUID", Mat5.newString(element.getUuid().toString()));
			struct.set("Referenc", (!bpe.isSet()) ? Mat5.newString("") : Mat5.newString(bpe.getValue().toString()));
			struct.set("Referenc-Class", (!bpe.isSet()) ? Mat5.newString("") : Mat5.newString(bpe.getClass().getName()));
		}
		return struct;
	}
	
	/**
	 * creates a new .mat-Struct with all Information about ReferencePropertyInstance
	 * only UUID Referenz and Referenc-Class are saved
	 * @param castElement PropertyInstance
	 */
	private Array contentofProperty(ReferencePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof ReferenceProperty) {
			struct.set("UUID", Mat5.newString(element.getUuid().toString()));
		}
		
		return struct;
	}
	
	/**
	 * creates a new .mat-Struct with all Information about ResourcePropertyInstance
	 * only UUID instanceType and Property Type are saved
	 * @param castElement PropertyInstance
	 */
	private Array contentofProperty(ResourcePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof ResourceProperty) {
			BeanPropertyResource bpr = new BeanPropertyResource();
			bpr.setATypeInstance(element);			
			struct.set("URI", (!bpr.isSet()) ? Mat5.newString("") : Mat5.newString(bpr.getValue().toString()));
			struct.set("File", (!bpr.isSet()) ? Mat5.newString("") : Mat5.newString(bpr.getFile().getName()));
		}
		return struct;
	}
	
	/**
	 * creates a new .mat-Struct with all Information about EnumUnitPropertyInstance
	 * only unit and value are saved
	 * @param element PropertyInstance
	 */	
	private Array contentofProperty(EnumUnitPropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof EnumProperty) {
			BeanPropertyEnum bpe = new BeanPropertyEnum();
			bpe.setATypeInstance(element);
			struct.set("Unit", Mat5.newString(bpe.getUnit()));
			struct.set("Value", (!bpe.isSet()) ? Mat5.newString("") : Mat5.newScalar(bpe.getEnumValue()));
		}
		return struct;
	}

	/**
	 * creates a new .mat-Struct with all Information about ArrayInstance
	 * @param element PropertyInstance
	 */	
	private Array contentofProperty(ArrayInstance element) {
		Struct struct = Mat5.newStruct();
		EList<APropertyInstance> propertyInstances = element.getArrayInstances();
		for (int i = 0; i < propertyInstances.size(); i++) {
			APropertyInstance instanceElement = propertyInstances.get(i);
			Array propertyArray = getrightProperty(instanceElement);
			
			struct.set(propertyInstances.get(i).getType().getName(), propertyArray);
		}
		return struct;
	}
	
	/**
	 * creates a new .mat-Struct with all Information about ComposedPropertyInstance
	 * @param element ComposedPropertyInstance
	 */	
	private Array contentofProperty(ComposedPropertyInstance element) {
		return exportCatAs(element.getTypeInstance());
	}
	
	//Delete First and Last Character
	public static String shorter(String str) {
		return str.substring(1, str.length() - 1);
	}
}
