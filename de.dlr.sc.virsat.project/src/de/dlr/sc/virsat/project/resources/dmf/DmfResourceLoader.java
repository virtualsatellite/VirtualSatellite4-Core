/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources.dmf;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumPropertyHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyinstancesSwitch;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.dmf.DObject;
import de.dlr.sc.virsat.model.dvlm.dmf.DmfFactory;
import de.dlr.sc.virsat.model.dvlm.dmf.UnresolveableDObject;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

/**
 * Loads a DMF Resource.
 * 
 * @author muel_s8
 *
 */

public class DmfResourceLoader {

	private Map<CategoryAssignment, DObject> loadedDObjects = new HashMap<>();

	/**
	 * Creates a dObject for every category assignment in the given structural
	 * element instance.
	 * 
	 * @param sei
	 *            the structural element instance
	 * @return a list of created dObjects
	 */

	public List<DObject> loadDmfResource(StructuralElementInstance sei) {
		final List<DObject> dObjects = new LinkedList<>();

		sei.getCategoryAssignments().forEach((ca) -> {
			EFactory eFactory = getEFactoryForCategoryAssignment(ca);
			if (eFactory != null) {
				// This is a ca for which we have a proper DMF model
				DObject dmfCaObject = getDObject(eFactory, ca);
				initializeAttributes(ca, dmfCaObject);
				loadedDObjects.put(ca, dmfCaObject);
				dObjects.add(dmfCaObject);
			} else {
				// We do not have a DMF model for this ca, so we create an
				// unresolveableDObject
				DObject unresolvableDObject = getUnresolveableDObject(ca);
				loadedDObjects.put(ca, unresolvableDObject);
				dObjects.add(unresolvableDObject);
			}
		});

		linkAllReferencedDObjects();
		return dObjects;
	}

	/**
	 * Performs a set on the given feature of the dObject. If the feature is an
	 * array, an add will be performed instead.
	 * 
	 * @param dObject
	 *            the dObject
	 * @param feature
	 *            the feature
	 * @param value
	 *            the value
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void dSet(DObject dObject, EStructuralFeature feature, Object value) {
		if (feature.isMany()) {
			final List list = (List) dObject.eGet(feature);
			list.add(value);
		} else {
			dObject.eSet(feature, value);
		}
	}

	/**
	 * Sets all references in the loaded dObjects
	 */
	private void linkAllReferencedDObjects() {
		loadedDObjects.keySet().forEach(ca -> {
			DObject dObject = loadedDObjects.get(ca);
			ca.getPropertyInstances().forEach(pi -> {
				new PropertyinstancesSwitch<DObject>() {
					@Override
					public DObject caseReferencePropertyInstance(ReferencePropertyInstance rpi) {
						if (rpi.getReference() != null) {
							CategoryAssignment referencedCa = (CategoryAssignment) rpi.getReference();
							DObject referencedDObject = loadedDObjects.get(referencedCa);
							String typeName = pi.getType().getName();
							EStructuralFeature feature = dObject.eClass().getEStructuralFeature(typeName);

							if (referencedDObject != null) {
								// We are referencing an object in the same
								// resource
								dSet(dObject, feature, referencedDObject);
							} else {
								// We have a cross reference to an object
								// outside of the current resource
								URI uriReferenced = referencedCa.eResource().getURI();
								String uriFragmentReferenced = referencedCa.eResource().getURIFragment(referencedCa);
								URI uri = uriReferenced.appendFragment(uriFragmentReferenced)
										.appendFileExtension(DmfResource.DMF_FILENAME_EXTENSION);

								EFactory eFactory = getEFactoryForCategoryAssignment(referencedCa);
								InternalEObject proxyObject = (InternalEObject) getDObject(eFactory, referencedCa);
								proxyObject.eSetProxyURI(uri);
								dSet(dObject, feature, proxyObject);
							}
						}
						return dObject;
					}

					@Override
					public DObject caseArrayInstance(ArrayInstance ai) {
						ai.getArrayInstances().forEach(pi -> {
							doSwitch(pi);
						});
						return dObject;
					}
				}.doSwitch(pi);
			});
		});
	}

	/**
	 * This method returns the eFactory of the simplified Ecore based Dvlm Model
	 * for a given Category Assignment from the core DVLM data model
	 * 
	 * @param ca
	 *            The Category Assignment for which to get the EFactory
	 * @return The EFactory of the simplified DVLM data model
	 */
	private EFactory getEFactoryForCategoryAssignment(CategoryAssignment ca) {
		Category category = (Category) ca.getType();
		Concept concept = ActiveConceptHelper.getConcept(category);

		String conceptNsUriString = ActiveConceptHelper.getDmfNsUriForConcept(concept);
		EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(conceptNsUriString);
		if (ePackage != null) {
			EFactory eFactory2 = ePackage.getEFactoryInstance();
			return eFactory2;
		} else {
			return null;
		}
	}

	/**
	 * This method helps to use the factory to create a corresponding simplified
	 * DVLM model object based on the DObjects from a given Category Assignment
	 * 
	 * @param eFactory
	 *            the EFactory to be used to create the DObject based CA
	 * @param ca
	 *            the Category Assignment from which to create the simplified
	 *            DVLM model object from
	 * @return the simplified DVLM model Object
	 */
	private DObject getDObject(EFactory eFactory, CategoryAssignment ca) {
		Category category = (Category) ca.getType();
		String catName = category.getName();

		// Try to get the reflective EClass for the DMF object to be created
		// based on the CA input.
		EClassifier eClassifier = eFactory.getEPackage().getEClassifier(catName);
		if (eClassifier instanceof EClass) {

			EObject eObject = eFactory.create((EClass) eClassifier);
			if (eObject instanceof DObject) {
				DObject dObject = (DObject) eObject;
				dObject.setName(ca.getName());
				VirSatUuid uuid = ca.getUuid();
				dObject.setUuid(uuid);
			}
			return (DObject) eObject;
		}
		return null;
	}

	/**
	 * Gets the eEnum for an enum property
	 * @param eFactory the factory
	 * @param ep the enum property
	 * @return the eEnum
	 */
	private EEnum getEEnum(EFactory eFactory, EnumProperty ep) {
		String enumName = new EnumPropertyHelper().getDmfEEnumName(ep);

		EClassifier eClassifier = eFactory.getEPackage().getEClassifier(enumName);
		
		if (eClassifier instanceof EEnum) {

			EEnum eEnum = (EEnum) eClassifier;
			return  eEnum;
		}
		return null;
	}
	
	/**
	 * Creates a new unresolvable dObject instance for the given category
	 * assignment
	 * 
	 * @param ca the category assignment
	 * @return a new unresolvable dObject instance
	 */
	private UnresolveableDObject getUnresolveableDObject(CategoryAssignment ca) {
		UnresolveableDObject unresolvableDObject = DmfFactory.eINSTANCE.createUnresolveableDObject();
		unresolvableDObject.setName(ca.getName());
		VirSatUuid uuid = ca.getUuid();
		unresolvableDObject.setUuid(uuid);
		return unresolvableDObject;
	}

	/**
	 * Initializes the attributes of a dObject using the data of a category
	 * assignment
	 * 
	 * @param ca
	 *            the category assignment
	 * @param dObject
	 *            the dObject
	 */
	private void initializeAttributes(CategoryAssignment ca, DObject dObject) {
		ca.getPropertyInstances().forEach(pi -> {
			AProperty property = (AProperty) pi.getType();
			String propertyName = property.getName();
			EStructuralFeature feature = dObject.eClass().getEStructuralFeature(propertyName);

			new PropertyinstancesSwitch<DObject>() {
				@Override
				public DObject caseValuePropertyInstance(ValuePropertyInstance vpi) {
					return new PropertydefinitionsSwitch<DObject>() {
						@Override
						public DObject caseBooleanProperty(BooleanProperty bpd) {
							Boolean value = Boolean.valueOf(vpi.getValue());
							dSet(dObject, feature, value);
							return dObject;
						}

						@Override
						public DObject caseStringProperty(StringProperty spd) {
							String value = vpi.getValue();
							dSet(dObject, feature, value);
							return dObject;
						}

						@Override
						public DObject caseFloatProperty(FloatProperty fpd) {
							UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) vpi;
							if (uvpi.getValue() != null) {
								double value = uvpi.getValueToBaseUnit();
								dSet(dObject, feature, value);
							}
							return dObject;
						}

						@Override
						public DObject caseIntProperty(IntProperty ipd) {
							UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) vpi;
							if (uvpi.getValue() != null) {
								int value = (int) uvpi.getValueToBaseUnit();
								dSet(dObject, feature, value);
							}
							return dObject;
						}

					}.doSwitch(vpi.getType());
				};

				/**
				 * This method hands back the DMF ENUM object for a given EnumUnitPropertyInstance
				 * from the DVLM Model. The Enum will be set to the current DObject
				 * @param eupi The EnumUnitPropertyInstance which holds the current Enum value
				 * @return the DObject containing the enum attribute that has been set
				 */
				public DObject caseEnumUnitPropertyInstance(EnumUnitPropertyInstance eupi) {
					EnumValueDefinition evd = eupi.getValue();
					if (evd != null) {
						EFactory eFactory = getEFactoryForCategoryAssignment(ca);
						EnumProperty ep = (EnumProperty) eupi.getType();
						EEnum eEnum = getEEnum(eFactory, ep);
						EEnumLiteral eEnumLiteral = eEnum.getEEnumLiteralByLiteral(evd.getName());
						dSet(dObject, feature, eEnumLiteral.getInstance());
					}
					return dObject;
				}
				
				@Override
				public DObject caseResourcePropertyInstance(ResourcePropertyInstance rpi) {
					dSet(dObject, feature, rpi.getResourceUri());
					return dObject;
				};

				@Override
				public DObject caseEReferencePropertyInstance(EReferencePropertyInstance object) {
					dSet(dObject, feature, object.getReference());
					return dObject;
				}

				@Override
				public DObject caseComposedPropertyInstance(ComposedPropertyInstance cpi) {
					CategoryAssignment caContained = cpi.getTypeInstance();
					EFactory eFactory = getEFactoryForCategoryAssignment(caContained);
					DObject containedDObject = getDObject(eFactory, caContained);
					initializeAttributes(caContained, containedDObject);
					loadedDObjects.put(caContained, containedDObject);
					dSet(dObject, feature, containedDObject);
					return dObject;
				};

				@Override
				public DObject caseArrayInstance(ArrayInstance ai) {
					ai.getArrayInstances().forEach(pi -> {
						doSwitch(pi);
					});
					return dObject;
				}

			}.doSwitch(pi);
		});
	}

}
