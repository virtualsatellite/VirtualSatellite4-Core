/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.util;

import java.util.LinkedList;
import java.util.List;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;

/**
 * Class to provide access to values of properties in a CategoryAssignment
 * 
 * @author kova_an
 *
 */
public class CategoryAssignmentHelper {
	private CategoryAssignment ca;

	/**
	 * Constructor 
	 * 
	 * @param ca
	 *            The category assignment this helper should work on.
	 */
	public CategoryAssignmentHelper(CategoryAssignment ca) {
		this.ca = ca;
	}

	/**
	 * This method returns the property instance of the current category
	 * assignment which is bound to the given name, only for non-array
	 * properties
	 * 
	 * @param propertyName
	 *            the name of the property to which to get the instance for
	 * @return the instance in case it is found. Throws an exception in case it
	 *         cannot be found. Throws an exception if a property is an array
	 */
	public APropertyInstance getPropertyInstance(String propertyName) {
		APropertyInstance pi = tryGetPropertyInstance(propertyName);
		if (pi != null) {
			return pi;
		}
		
		throw new RuntimeException("Property " + propertyName + " not found in CategoryAssignment");
	}

	/**
	 * This method gets the property instance of the current category
	 * assignment which is bound to the given name, if it exisets. Only for non-array
	 * properties
	 * 
	 * @param propertyName
	 *            the name of the property to which to get the instance for
	 * @return the instance in case it is found, null otherwise
	 */
	public APropertyInstance tryGetPropertyInstance(String propertyName) {
		for (APropertyInstance propInstance : ca.getPropertyInstances()) {
			String propInstanceTypeName = propInstance.getType().getName();
			String propInstanceTypeFqn = propInstance.getType().getFullQualifiedName();
			if (propertyName.equals(propInstanceTypeName) || propertyName.equals(propInstanceTypeFqn)) {
				return propInstance;
			}
		}
		return null;
	}
	
	/**
	 * Call this method to get the PropertyInstance which is typed by the given
	 * Property
	 * 
	 * @param property
	 *            The property that will be used for the search
	 * @return the corresponding propertyInstance in the current category
	 *         assignment
	 */
	public APropertyInstance getPropertyInstance(AProperty property) {
		for (APropertyInstance propInstance : ca.getPropertyInstances()) {
			if (propInstance.getType().equals(property)) {
				return propInstance;
			}
		}
		return null;
	}

	/**
	 * Method to set another category assignment to this helper
	 * 
	 * @param ca
	 *            the new category assignment
	 * @return this helper.
	 */
	public CategoryAssignmentHelper setCategoryAssignment(CategoryAssignment ca) {
		this.ca = ca;
		return this;
	}

	/**
	 * this method get the type name of the the type instance
	 * 
	 * @param ti
	 *            the type instance
	 * @return the type name of the type instance
	 */
	public static String getTypeName(ATypeInstance ti) {
		return ti.getType().getName();
	}

	/**
	 * Use this method to hand back all CategoryAssignments of a given type from
	 * a given StructuralElementInstance. This method does not hand back sub-types of
	 * the given Category.
	 * 
	 * @param caContainer the Container for the Category Assignments
	 * @param cId the ID of the Category Type that should be searched for
	 * @return all Category Assignments of the given type
	 */
	public static List<CategoryAssignment> getCategoryAssignmentsOfType(ICategoryAssignmentContainer caContainer, String cId) {
		List<CategoryAssignment> cas = new LinkedList<>();

		for (CategoryAssignment ca : caContainer.getCategoryAssignments()) {
			ATypeDefinition t = ca.getType();
			if (categoryMatchesType(t, cId)) {
				cas.add(ca);
			}
		}

		return cas;
	}
	
	/**
	 * Use this method to hand back all CategoryAssignments of a given type from
	 * a given StructuralElementInstance. This method hands back sub-types of
	 * the given Category.
	 * 
	 * @param caContainer the Container for the Category Assignments
	 * @param cId the ID of the Category Type that should be searched for
	 * @return all Category Assignments that match the  given super type
	 */
	public static List<CategoryAssignment> getCategoryAssignmentsOfSuperType(ICategoryAssignmentContainer caContainer, String cId) {
		List<CategoryAssignment> cas = new LinkedList<>();

		for (CategoryAssignment ca : caContainer.getCategoryAssignments()) {
			Category t = (Category) ca.getType();
			while (t != null) {
				if (categoryMatchesType(t, cId)) {
					cas.add(ca);
				}
				t = t.getExtendsCategory();
			}
		}

		return cas;
	}
	
	/**
	 * Internal method to check of the given TypeDefinition matches
	 * a given type ID.
	 * @param td the TypeDefinition or Category to be checked for the Id
	 * @param cId the ID to be checked for
	 * @return true in case it matches
	 */
	private static boolean categoryMatchesType(ATypeDefinition td, String cId) {
		if (td != null) {
			return cId.equals(td.getFullQualifiedName());
		}
		return false;
	}

	/**
	 * Call this method to get the first instance of a given CA from a SEI or Container. Does not search for nested occurrences of the CA.
	 * @param caContainer The SEI or Container from where to get the first instance from
	 * @param caId The id if the Category to search for
	 * @return The found Assignment or null if it wasn't found
	 */
	public static CategoryAssignment getFirstCategoryAssignment(ICategoryAssignmentContainer caContainer, String caId) {
		for (CategoryAssignment ca : caContainer.getCategoryAssignments()) {
			ATypeDefinition c = ca.getType();
			if (c != null) {
				if (caId.equals(c.getFullQualifiedName())) {
					return ca;
				}
			}
		}

		return null;
	}
	
	/**
	 * Use this method to hand back all CategoryAssignments of a given type from
	 * a given StructuralElementInstance while also considering category
	 * assignments that may be nested in others through composed property
	 * instances
	 * 
	 * @param caContainer
	 *            the Container for the Category Assignments
	 * @param caId
	 *            the ID of the Category Type that should be searched for
	 * @return all Category Assignments of the given type
	 */
	public static List<CategoryAssignment> getNestedCategoryAssignments(ICategoryAssignmentContainer caContainer, String caId) {
		List<CategoryAssignment> cas = new LinkedList<>();

		for (CategoryAssignment ca : caContainer.getCategoryAssignments()) {
			cas.addAll(getNestedCategoryAssignments(ca, caId));
		}

		return cas;
	}

	/**
	 * Use this method to hand back all CategoryAssignments of a given type from
	 * a given StructuralElementInstance while also considering category
	 * assignments that may be nested in others through composed property
	 * instances
	 * 
	 * @param ca
	 *            the Category Assignments
	 * @param caId
	 *            the ID of the Category Type that should be searched for
	 * @return all Category Assignments of the given type
	 */
	public static List<CategoryAssignment> getNestedCategoryAssignments(CategoryAssignment ca, String caId) {
		List<CategoryAssignment> cas = new LinkedList<>();

		ATypeDefinition c = ca.getType();
		if (c != null) {
			String fqn = c.getFullQualifiedName();
			if (caId.equals(fqn)) {
				cas.add(ca);
			}
		}

		for (APropertyInstance pi : ca.getPropertyInstances()) {
			if (pi instanceof ComposedPropertyInstance) {
				ComposedPropertyInstance cpi = (ComposedPropertyInstance) pi;
				CategoryAssignment caNested = cpi.getTypeInstance();
				ATypeDefinition cNested = caNested.getType();
				if (cNested != null) {
					if (caId.equals(cNested.getFullQualifiedName())) {
						cas.add(caNested);
					}
				}
			} else if (pi instanceof ArrayInstance) {
				ArrayInstance ai = (ArrayInstance) pi;
				for (APropertyInstance arrayPi : ai.getArrayInstances()) {
					if (arrayPi instanceof ComposedPropertyInstance) {
						ComposedPropertyInstance cpi = (ComposedPropertyInstance) arrayPi;
						CategoryAssignment caNested = cpi.getTypeInstance();
						ATypeDefinition cNested = caNested.getType();
						if (cNested != null) {
							if (caId.equals(cNested.getFullQualifiedName())) {
								cas.add(caNested);
							}
						}
					}	
				}
			}
		}

		return cas;
	}
	
	/**
	 * Hand back the CategoryAssignmentContainer for a given TypeInstance
	 * 
	 * @param typeInstance
	 *            the TypeInstance for which to get back its container
	 * @return the ICategroyAssignmentContainer that contains the given
	 *         TypeInstance
	 */
	public static ICategoryAssignmentContainer getContainerFor(ATypeInstance typeInstance) {
		return VirSatEcoreUtil.getEContainerOfClass(typeInstance, ICategoryAssignmentContainer.class);
	}
	
	/**
	 * Call this method to receive the Repository for a given Type Instance. The method traverses
	 * the assigned type and concept to find the repository
	 * @param ti The TypeInstance for which to get the Repository
	 * @return the repository in case it could be found otherwise null
	 */
	public static Repository getRepository(ATypeInstance ti) {
		ATypeDefinition td = ti.getType();
		if (td != null) {
			Concept concept = ActiveConceptHelper.getConcept(td);
			return (concept != null) ? (Repository) concept.eContainer() : null;
		}
		
		return null;
	}
	
	/**
	 * Call this method to get the SystemOfUnits for a given TypeInstance
	 * @param ti The TypeInstance for which to get the SOU for
	 * @return the System of Units in case it could be found
	 */
	public static SystemOfUnits getSystemOfUnits(ATypeInstance ti) {
		Repository repo = getRepository(ti);
		return repo.getUnitManagement().getSystemOfUnit();
	}	
	
}
