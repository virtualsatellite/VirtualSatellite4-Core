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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

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
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.Array;
import us.hebi.matlab.mat.types.Cell;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;

/**
 * Class for exporting data to .mat
 */
public class MatExporter {

	/**
	 * creates a new .mat with everything inside the given sei
	 * @param seiRoot sei to export
	 */
	public MatFile exportSei(StructuralElementInstance seiRoot) {
		MatFile matfile = Mat5.newMatFile();		
		Struct struct = Mat5.newStruct();
		struct.set(MatHelper.TYPE, Mat5.newString(seiRoot.getType().getName()))
			.set(MatHelper.UUID, Mat5.newString(seiRoot.getUuid().toString()));
		if (seiRoot.getChildren().size() > 0) {
			Struct children = Mat5.newStruct();
			for (StructuralElementInstance sei : seiRoot.getChildren()) {
				MatFile child = exportSei(sei);
				children.set(sei.getName(), child.getArray(sei.getName()));
			}
			struct.set(MatHelper.CHILDREN, children);
		}
		
		for (CategoryAssignment ca : seiRoot.getCategoryAssignments()) {
			struct.set(ca.getName(), exportCatAs(ca));
		}

		matfile.addArray(seiRoot.getName(), struct);
		return matfile;
	}

	/**
	 * creates a new .mat with everything inside the selected CategoryAssinments
	 * @param cas list of CategoryAssinments
	 */
	public MatFile exportCas(EList<CategoryAssignment> cas) {
		MatFile matfile = Mat5.newMatFile();
		Struct struct = Mat5.newStruct();
		for (CategoryAssignment ca : cas) {
			struct.set(ca.getName(), exportCatAs(ca));
		}
		matfile.addArray(MatHelper.INPUTS, struct);
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
				return contentOfProperty(object);
			}

			@Override
			public Array caseEnumUnitPropertyInstance(EnumUnitPropertyInstance object) {
				return contentOfProperty(object);
			}

			@Override
			public Array caseValuePropertyInstance(ValuePropertyInstance object) {
				return contentOfProperty(object);
			}

			@Override
			public Array caseReferencePropertyInstance(ReferencePropertyInstance object) {
				return contentOfProperty(object);
			}

			@Override
			public Array caseEReferencePropertyInstance(EReferencePropertyInstance object) {
				return contentOfProperty(object);
			}

			@Override
			public Array caseComposedPropertyInstance(ComposedPropertyInstance object) {
				return contentOfProperty(object);
			}

			@Override
			public Array caseArrayInstance(ArrayInstance object) {
				return contentOfProperty(object);
			}

		}.doSwitch(element);
		return propertyArray;
	}

	/**
	 * creates a new .mat-struct with all information about UnitValuePropertyInstance
	 * only unit and value are saved
	 * @param element UnitValuePropertyInstance
	 */
	private Array contentOfProperty(UnitValuePropertyInstance element) {
		Struct struct = Mat5.newStruct();

		if (element.getType() instanceof FloatProperty) {
			BeanPropertyFloat bpf = new BeanPropertyFloat(element);
			struct.set(MatHelper.UNIT, Mat5.newString(bpf.getUnit()));
			struct.set(MatHelper.VALUE, (bpf.getValue() == Double.NaN) ? Mat5.newString("") : Mat5.newScalar(bpf.getValue()));
		} else if (element.getType() instanceof IntProperty) {
			BeanPropertyInt bpi = new BeanPropertyInt(element);
			struct.set(MatHelper.UNIT, Mat5.newString(bpi.getUnit()));
			struct.set(MatHelper.VALUE, (!bpi.isSet()) ? Mat5.newString("") : Mat5.newScalar(bpi.getValue()));
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
			BeanPropertyBoolean bpb = new BeanPropertyBoolean(element);
			struct.set(MatHelper.VALUE, (!bpb.isSet()) ? Mat5.newString("") : Mat5.newLogicalScalar(bpb.getValue()));
		} else if (element.getType() instanceof StringProperty) {
			BeanPropertyString bps = new BeanPropertyString(element);
			struct.set(MatHelper.VALUE, (!bps.isSet()) ? Mat5.newString("") : Mat5.newString(bps.getValue()));
		}
		return struct;
	}

	/**
	 * creates a new .mat-struct with all information about EReferencePropertyInstance
	 * only uuid, reference and reference-class are saved
	 * @param element EReferencePropertyInstance
	 */
	private Array contentOfProperty(EReferencePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		EObject value = element.getReference();
		if (value != null && value.eResource() != null) {
			struct.set(MatHelper.REF, Mat5.newString(value.toString()));
			struct.set(MatHelper.URI, Mat5.newString(EcoreUtil.getURI(value).toString()));
			
		} else {
			struct.set(MatHelper.REF, Mat5.newString(""));
			struct.set(MatHelper.URI, Mat5.newString(""));
		}
		return struct;
	}

	/**
	 * creates a new .mat-struct with all information about ReferencePropertyInstance
	 * only uuid and fullQualifiedInstanceName are saved
	 * @param element ReferencePropertyInstance
	 */
	private Array contentOfProperty(ReferencePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof ReferenceProperty) {
			ATypeInstance referencedTypeInstance = element.getReference();
			if (element.getReference() != null) {
				struct.set(MatHelper.UUID, Mat5.newString(referencedTypeInstance.getUuid().toString()));
				struct.set(MatHelper.FULLNAME, Mat5.newString(referencedTypeInstance.getFullQualifiedInstanceName()));
			} else {
				struct.set(MatHelper.UUID, Mat5.newString(""));
				struct.set(MatHelper.FULLNAME, Mat5.newString(""));
			}
		}
		return struct;
	}

	/**
	 * creates a new .mat-struct with all information about ResourcePropertyInstance
	 * only uri is saved
	 * @param element ResourcePropertyInstance
	 */
	private Array contentOfProperty(ResourcePropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof ResourceProperty) {
			BeanPropertyResource bpr = new BeanPropertyResource(element);
			struct.set(MatHelper.URI, (!bpr.isSet()) ? Mat5.newString("") : Mat5.newString(bpr.getValue().toString()));
		}
		return struct;
	}

	/**
	 * creates a new .mat-struct with all information about EnumUnitPropertyInstance
	 * only unit,value and name are saved
	 * @param element EnumUnitPropertyInstance
	 */
	private Array contentOfProperty(EnumUnitPropertyInstance element) {
		Struct struct = Mat5.newStruct();
		if (element.getType() instanceof EnumProperty) {
			BeanPropertyEnum bpe = new BeanPropertyEnum(element);
			struct.set(MatHelper.UNIT, Mat5.newString(bpe.getUnit()));
			struct.set(MatHelper.VALUE, (!bpe.isSet()) ? Mat5.newString("") : Mat5.newScalar(bpe.getEnumValue()));
			struct.set(MatHelper.NAME, (!bpe.isSet()) ? Mat5.newString("") : Mat5.newString(bpe.getValue()));
		}
		return struct;
	}

	/**
	 * creates a new .mat-struct with all information about ArrayInstance
	 * @param element ArrayInstance
	 */
	private Array contentOfProperty(ArrayInstance element) {
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
	private Array contentOfProperty(ComposedPropertyInstance element) {
		return exportCatAs(element.getTypeInstance());
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
