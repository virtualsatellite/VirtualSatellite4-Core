/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;

/**
 * This class helps to get access to the categories and concepts
 * which are stored in the repository and which are already active;
 * The class is based on the concept of FullQualified Identifiers
 * AFQID for a Property has three parts "concept.category.property"
 * The FQID may also be of two parts only: "concept.property"
 */
public class ActiveConceptHelper {

	private Repository repository;
	
	public static final String FQID_DELIMITER = ".";
	public static final String FQID_DELIMITER_REGEX = "\\.";
	public static final String EMPTY_NAME = "";

	public static final int FQID_CONCPET = 0;
	public static final int FQID_CATEGORY = 1;
	public static final int FQID_PROPERTY = 2;
	
	/**
	 * Constructor to work on the Repository of the current project
	 * @param repo the current repository
	 */
	public ActiveConceptHelper(Repository repo) {
		this.repository = repo;
	}

	/** 
	 * Method to set the repository on which this class should act
	 * @param repo the repository object on which this class should act
	 * @return the current ActiveCategoryHelper
	 */
	public ActiveConceptHelper setRepository(Repository repo) {
		this.repository = repo;
		return this;
	}
	
	/**
	 * Method to access a TypeDefinition by the three parts of the path.
	 * The path consists of concept.category.property or just concept. category.
	 * @param qualifiedPath The path as list of qualified name objects
	 * @return the type definition  or null in case it could not be found
	 */
	public ATypeDefinition getTypeDefinitionByFQP(List<IQualifiedName> qualifiedPath) {
		if (qualifiedPath.size() == 2) {
			String conceptName = qualifiedPath.get(FQID_CONCPET).getName();
			String categoryName = qualifiedPath.get(FQID_CATEGORY).getName();
			Category category =  getCategory(conceptName, categoryName);
			return category;
		} else {
			String conceptName = qualifiedPath.get(FQID_CONCPET).getName();
			String categoryName = qualifiedPath.get(FQID_CATEGORY).getName();
			String propertyName = qualifiedPath.get(FQID_PROPERTY).getName();
			Category category =  getCategory(conceptName, categoryName);
			AProperty property = getProperty(category, propertyName);
			return property;
		}
	}
	
	/**
	 * method to get a category from an active concept
	 * @param conceptId the concept id of which to get the category
	 * @param categoryId the category id of the category being part of the concept
	 * @return the category in case it could be found
	 */
	public Category getCategory(String conceptId, String categoryId) {
		Concept concept = getConcept(conceptId);
		
		if (concept != null) {
			return getCategory(concept, categoryId);
		}
		return null;
	}
	
	/**
	 * Method to access a property within a given category
	 * @param category the catgeory in which to look for the property
	 * @param propertyId the id of the property to look for
	 * @return the property stated by the id or null in case it oculd not be found
	 */
	public static AProperty getProperty(Category category, String propertyId) {
		for (AProperty property : category.getAllProperties()) {
			if (propertyId.equals(property.getName())) {
				return property;
			}
		}
		return null;
	}

	/**
	 * method to get a property from a category from an active concept
	 * @param conceptId the concept id of which to get the category
	 * @param categoryId the category id of the category being part of the concept
	 * @param propertyId the id of the property to look for
	 * @return the category in case it could be found
	 */
	public AProperty getProperty(String conceptId, String categoryId, String propertyId) {
		Category category = getCategory(conceptId, categoryId);
		if (category == null) {
			return null;
		}
		for (AProperty property : category.getAllProperties()) {
			if (propertyId.equals(property.getName())) {
				return property;
			}
		}
		return null;
	}
	
	/**
	 * Method to return the concept in which a typeDefinition is contained.
	 * @param typeDefinition the typedDfinition such as a category or a property.
	 * @return the concept which contains this type definition
	 */
	public static Concept getConcept(ATypeDefinition typeDefinition) {
		return getConceptForEObject(typeDefinition);
	}
	
	/**
	 * Method to return the concept in which a StructuralElement is contained.
	 * @param structuralElement the StructuralElement such as a category or a property.
	 * @return the concept which contains this type definition
	 */
	public static Concept getConcept(StructuralElement structuralElement) {
		return getConceptForEObject(structuralElement);
	}
	
	/**
	 * Method to return the concept in which a given type is contained.
	 * @param conceptType the type stored in the concept as an EObject
	 * @return the concept which contains this type definition
	 */
	private static Concept getConceptForEObject(EObject conceptType) {
		EObject currentType = conceptType;
		EObject parentType = currentType.eContainer();
		while (!(currentType instanceof Concept) && (parentType != null)) {
			currentType = parentType;
			parentType = currentType.eContainer();
		}
		
		// Only in case the object we found is a concept it should be handed back
		// in case a type definition is not properly contained in a concept, other objects may be handed back
		// by the code above. Obviously they cannot be casted to a concept, thus a null should be returned		
		return (currentType instanceof Concept) ? (Concept) currentType : null;
	}
	
	/**
	 * Method to get the concept name to display in concept selection dialog
	 * @param concept the concept which name details are needed
	 * @return the display name plus FQN and version or the FQN and version when there is no display name
	 */
	public static String getConceptNameWithVersion(Concept concept) {
		String formattedVersion = " [" + concept.getVersion() + "]";
		if (concept.getDisplayName() == null) {
			return concept.getName() + formattedVersion;
		} else {
			return concept.getDisplayName() + " - " + concept.getName() + formattedVersion;
		}
	}
	
	/**
	 * Method to get just the concept from the active concepts of the active repository
	 * @param conceptId the id of the concept to look for
	 * @return the concept or null in case it could not be found
	 */
	public Concept getConcept(String conceptId) {
		for (Concept concept : repository.getActiveConcepts()) {
			if (concept.getName().equals(conceptId)) {
				return concept;
			}
		}
		return null;
	}

	/**
	 * Method to access a category from a given Concept
	 * @param concept the concept in which to look for the category
	 * @param categoryFqn the id or FQN of the category to look for
	 * @return the category or null it doesn't exist
	 */
	public static Category getCategory(Concept concept, String categoryFqn) {
		// Now take the final part of the FQN which is the category name and search for it.
		String [] fqnParts = categoryFqn.split(FQID_DELIMITER_REGEX);
		String categoryId = fqnParts[fqnParts.length - 1];
		
		// In case the array has more than one element it should consist of the concept FQN plus
		// the category name. Therefore the fqn of the category should be checked
		if (fqnParts.length > 1) {
			if (concept.getName() == null) {
				return null;
			}
			
			String rebuildFQN = concept.getName() + FQID_DELIMITER + categoryId;
			if (!rebuildFQN.equals(categoryFqn)) {
				return null;
			}
		}

		// Now search for the category
		for (Category cat : concept.getCategories()) {
			if (categoryId.equals(cat.getName())) {
				return cat;
			}
		}
		
		// In case nothing could be found, return a null pointer
		return null;
	}
	
	/**
	 * This method hands back the full qualified path of a type and the Concept as well
	 * @param type the category or property to start searching from
	 * @return the full qualified path being delimited with "."
	 */
	public static String getFullQualifiedId(EObject type) {
		List<EObject> objectPath = getFullQualifiedPath(type);
	
		String fullQualifiedPath = "";
		boolean delimit = false;
		
		for (EObject pathObject : objectPath) {
			String pathElement = extractFullQualifiedNameName(pathObject);
			
			fullQualifiedPath = ((delimit) ? pathElement + FQID_DELIMITER : pathElement) + fullQualifiedPath;
			delimit = true;
		}
		return fullQualifiedPath;
	}
	
	
	/**
	 * This method hands back all full qualified ids of a type including its own and the
	 * full qualified ids it extends
	 * @param typeDefinition the type
	 * @return all full qualified ids including the passed type and all extended types
	 */
	
	public static Set<String> getAllFullQualifiedIds(ATypeDefinition typeDefinition) {
		Set<String> fqIds = new HashSet<>();
		
		if (typeDefinition instanceof Category) {
			Category category = (Category) typeDefinition;
			while (category != null) {
				fqIds.add(ActiveConceptHelper.getFullQualifiedId(category));
				category = category.getExtendsCategory();
			}
		} else {
			fqIds.add(ActiveConceptHelper.getFullQualifiedId(typeDefinition));
		}
		
		return fqIds;
	}
	
	/**
	 * This method hands back all names of a type including its own and the
	 * names it extends
	 * @param typeDefinition the type
	 * @return all names including the passed type and all extended types
	 */
	
	public static Set<String> getAllNames(ATypeDefinition typeDefinition) {
		Set<String> fqIds = new HashSet<>();
		
		if (typeDefinition instanceof Category) {
			Category category = (Category) typeDefinition;
			while (category != null) {
				fqIds.add(category.getName());
				category = category.getExtendsCategory();
			}
		} else {
			fqIds.add(typeDefinition.getName());
		}
		
		return fqIds;
	}

	/**
	 * Method to extract the name attribute of the IQualifiedName interface
	 * using EMFs reflective methods. This function is needed for the EDAPT
	 * which is loading the model using dynamic EMF but is not using the same
	 * instances of the model packages. Accordingly standard matching does not work.
	 * @param fqnEobject The object of which to get the name as stored in the IFullQualifiedName interface
	 * @return the value of the name attribute found
	 */
	private static String extractFullQualifiedNameName(EObject fqnEobject) {
		final String fqnFqnName = VirSatEcoreUtil.getFullQualifiedAttributeName(GeneralPackage.Literals.IQUALIFIED_NAME__NAME);
		for (EAttribute eAttribute : fqnEobject.eClass().getEAllAttributes()) {
			String fqnEAttributeName = VirSatEcoreUtil.getFullQualifiedAttributeName(eAttribute);
			if (fqnFqnName.equals(fqnEAttributeName)) {
				return (String) fqnEobject.eGet(eAttribute);
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the properties that are not arrays all others should not be displayed in this UI SNippert
	 * @param viewerCategory The category which should be displayed with this snippet
	 * @return the list of properties which are not arrays
	 */
	public static List<AProperty> getNonArrayProperties(Category viewerCategory) {
		LinkedList<AProperty> propertyList = new LinkedList<>();
		for (AProperty property : viewerCategory.getAllProperties()) {
			if (property.getArrayModifier() == null) {
				propertyList.add(property);
			}
		}
		return propertyList;
	}

	/**
	 * This method retrieves the Path elements to build the full qualified path of an object
	 * @param type the type of which to get the full qualified path
	 * @return the path as list of qualified name objects
	 */
	public static List<EObject> getFullQualifiedPath(EObject type) {
		if (!isSafeAssignableFrom(GeneralPackage.Literals.IQUALIFIED_NAME, type)) {
			return Collections.emptyList();
		}
		
		List<EObject> objectPath = new ArrayList<>();
		EObject potentialPathObject = type;
		do {
			objectPath.add(potentialPathObject);
			EObject eContainerObject = potentialPathObject.eContainer();  
			if (isSafeAssignableFrom(GeneralPackage.Literals.IQUALIFIED_NAME, eContainerObject)) {
				potentialPathObject = eContainerObject;
			} else {
				break;
			}
		} while (potentialPathObject != null);
		return objectPath;
	}
	
	/**
	 * This method hands back the full qualified path of a CatgeoryAssignment
	 * @param ca the category assignment from where to start looking for the full qualified path
	 * @return the full qualified path being delimited
	 */
	public static String getFullQualifiedCategoryId(ATypeInstance ca) {
		ATypeDefinition type = ca.getType();
		if (type == null) {
			return EMPTY_NAME;
		}
		return getFullQualifiedId(type);
	}

	/**
	 * Call this method to retrieve a Structural Element from a Concept by its name
	 * @param concept The concept from which to retrieve the StructuralElement
	 * @param seName the Name of the StructuralElement
	 * @return the StructuralElement in case it was found, otherwise null
	 */
	public static StructuralElement getStructuralElement(Concept concept, String seName) {
		for (StructuralElement se : concept.getStructuralElements()) {
			if (seName.equals(se.getName())) {
				return se;
			}
		}
		return null;
	}
	
	/**
	 * Method to be used together with dynamic EMF and EDAPT. The method can check if 
	 * a certain class is a super type of another one or not. Rather than using the direct comparison by
	 * the objects delivered by EMFs model package factories, it tries to compare the objects by the full qualified IDs
	 * as they are used within the DVLM
	 * @param eTypeClazz a Type from an EMF model to which the given object should be assigned to
	 * @param eObject an EObject that should be assigned
	 * @return true in case both types match
	 */
	public static boolean isSafeAssignableFrom(EClass eTypeClazz, EObject eObject) {
		// If there is no objects it is definitely not assignable
		if (eObject == null) {
			return false;
		}
		
		EClass eObjectClass = eObject.eClass();
		final String fqnTypeClazz = VirSatEcoreUtil.getFullQualifiedClassName(eTypeClazz);
		final String fqnObjectClazz = VirSatEcoreUtil.getFullQualifiedClassName(eObjectClass);

		// If the object is of the same type as the one where it should be assigned to, than the result is true;
		if (fqnTypeClazz.equals(fqnObjectClazz)) {
			return true;
		}
		
		// Otherwise start looping over all potential types
		if ((fqnTypeClazz != null) && (!fqnTypeClazz.isEmpty())) {

			for (EClass eObjectSuperType : eObjectClass.getEAllSuperTypes()) {
				String fqnObjectSuperType = VirSatEcoreUtil.getFullQualifiedClassName(eObjectSuperType); 
				if (fqnTypeClazz.equals(fqnObjectSuperType)) {
					return true;
				}
			}
		} 
		
		return false;
	}

	/**
	 * This method hands back the full Qualified path of an object that is instantiated within
	 * the DVLM. For example you can hand over a ProeprtyInstance and ask which FullQualifiedName
	 * would lead to this specific object.
	 * @param namedLeafObject A Leaf object representing a ValueProeprtyInstance for example
	 * @return a full qualified path such as StructuralElementInstance.StructuralElementInstance.CategoryAssignment.propertyName
	 */
	public static String getFullQualifedNameForInstance(IInstance namedLeafObject) {
		return getFullQualifedNameForInstance(null, namedLeafObject);
	}

	/**
	 * Call this method to get a container qualified name CQN. It means handing in a PI or CA
	 * hands back the full qualified name of it but only till the leaf of the Container which is 
	 * usually the containing SEI
	 * @param namedLeafObject The Type Instance object for which to get The CQN
	 * @return the String of the CQN
	 */
	public static String getContainerQualifedNameForInstance(ATypeInstance namedLeafObject) {
		ICategoryAssignmentContainer caContainer = namedLeafObject.getCategoryAssignmentContainer();
		return getFullQualifedNameForInstance(caContainer, namedLeafObject);
	}
	
	/**
	 * This method hands back the full Qualified path of an object that is instantiated within
	 * the DVLM. For example you can hand over a ProeprtyInstance and ask which FullQualifiedName
	 * would lead to this specific object. Additionally this method allows to truncate parts of the FQN beginning from the root
	 * This is needed for example when you want to know the FQN of an IFE from a RW e.g. RW1.canBus1 but no the AOCS etc. parts.
	 * @param rootObject the object at which to stop handing back the name of the FQN
	 * @param namedLeafObject A Leaf object representing a ValueProeprtyInstance for example
	 * @return a full qualified path such as StructuralElementInstance.StructuralElementInstance.CategoryAssignment.propertyName
	 */
	public static String getFullQualifedNameForInstance(Object rootObject, IInstance namedLeafObject) {
		List<String> nameParts = new ArrayList<>();
		
		// Find all parts of the name
		EObject currentObject = namedLeafObject;
		while (currentObject != null) {
			EObject parentObject = currentObject.eContainer();
			
			if (currentObject instanceof StructuralElementInstance) {
				StructuralElementInstance sei = (StructuralElementInstance) currentObject;
				parentObject = sei.getParent();
			}
			
			if (currentObject instanceof APropertyInstance) {
				ATypeDefinition propertyType = ((APropertyInstance) currentObject).getType();
				String typeName = propertyType.getName();
				if (parentObject instanceof ArrayInstance) {
					// handle the cases of an intrinsic property in an array
					// the FQN should look like:
					// StructuralElementInstance.StructuralElementInstance.CategoryAssignment.propertyName[index]
					
					ArrayInstance ai = (ArrayInstance) parentObject;
					int index = ai.getArrayInstances().indexOf(currentObject);
					typeName += "[" + index + "]";
					
					// since we directly add the index to the name we don't need to add the name
					// of the array instance object. Therefore we can just skip it and traverse it
					parentObject = ai.eContainer();
					nameParts.add(typeName);
				} else {
					nameParts.add(typeName);
				}
			} else if (currentObject instanceof CategoryAssignment) {
				CategoryAssignment namedCategoryAssignment = (CategoryAssignment) currentObject;
				if (parentObject instanceof ComposedPropertyInstance) {
					ComposedPropertyInstance cpi = (ComposedPropertyInstance) parentObject;
					// handle the cases of a composed property
					// the FQN should look like:
					// StructuralElementInstance.StructuralElementInstance.CategoryAssignment.ComposedPropertyInstanceName.composedPropertyName
					ATypeDefinition cp = cpi.getType();
					String typeName = cp.getName();
					EObject parentOfComposedPropertyInstance = cpi.eContainer();
					if (parentOfComposedPropertyInstance instanceof ArrayInstance) {
						// handle the cases of an intrinsic property in an array
						// the FQN should look like:
						// StructuralElementInstance.StructuralElementInstance.CategoryAssignment.propertyName[index]
						
						ArrayInstance ai = (ArrayInstance) parentOfComposedPropertyInstance;
						int index = ai.getArrayInstances().indexOf(parentObject);
						typeName += "[" + index + "]";
						
						// since we directly add the index to the name we dont need to add the name
						// of the array instance object. Therefore we can just skip it and traverse it
						parentObject = ai.eContainer();
						nameParts.add(typeName);
					} else {
						nameParts.add(typeName);
						// So in this case we don't need to display the name of the CategoryAssingment.
						// We assume that we are currently with a Category Assignment
						parentObject = cpi.eContainer();
					}

				} else {
					nameParts.add(namedCategoryAssignment.getName());
				}
			} else if (currentObject instanceof IName) {
				IName namedCurrentObject = (IName) currentObject;
				nameParts.add(namedCurrentObject.getName());
			}
			
			// Now crawl one level up closer to the root
			if (currentObject == rootObject) {
				// Now Stop in the next iteration
				currentObject = null;  
			} else {
				// if there is no specific root object stated at which to stop building the FQN,
				// then go on until you find the very end.
				currentObject = parentObject;
			}
		}
		
		// now build the name
		boolean isFirst = true;
		String concatenatedFullQualifiedName = "";
		Collections.reverse(nameParts);
		for (String namePart : nameParts) {
			String nonNullNamePart;
			if (namePart == null) {
				nonNullNamePart = UNSET_NAME_PART;
			} else {
				nonNullNamePart = namePart;
			}

			if (isFirst) {
				concatenatedFullQualifiedName = nonNullNamePart.replaceAll("\\s", "");
				isFirst = false;
			} else {
				concatenatedFullQualifiedName += FQID_DELIMITER + nonNullNamePart.replaceAll("\\s", "");
			}
		}
		
		return concatenatedFullQualifiedName;
	}
	
	public static final String UNSET_NAME_PART = "unknown";
	
	public static final String MODEL_URI_PREFIX = "http://www.virsat.sc.dlr.de/dmf/v";

	/**
	 * Gets the nsUri for the DMF Ecore Model from a concept
	 * @param concept the concept
	 * @return a string representation of the nsUri
	 */
	public static final String getDmfNsUriForConcept(Concept concept) {
		String conceptName = VirSatEcoreUtil.getNameOfFqn(concept.getName());
		return MODEL_URI_PREFIX + concept.getVersion() + "/" + VirSatEcoreUtil.getNameOfFqn(conceptName);
	}

	/**
	 * The method hands back the ConceptID of an Imported NameSpace. An
	 * Imported NameSpace always consists of the ConceptID + the ElemenID.
	 * E.G. de.dlr.testConcept.testCategory
	 * @param conceptImport The Import Object for importing the NameSpace
	 * @return the cleaned ID of the concept to be imported
	 */
	public static String getConceptFromImport(ConceptImport conceptImport) {
		String importedNameSpace = conceptImport.getImportedNamespace();
		int lastIndexOfDot =  importedNameSpace.lastIndexOf(FQID_DELIMITER);
		String importedConceptId = importedNameSpace.substring(0, lastIndexOfDot);
		return importedConceptId;
	}
	
	/**
	 * This method hands back the IDs of concepts which are imported
	 * @param concept the concept for which to get all imported concepts
	 * @return A set of Strings with all IDs
	 */
	public static Set<String> getConceptDependencies(Concept concept) {
		Set<String> importedConceptIds = new HashSet<>();
		concept.getImports().forEach(conceptImport -> {
			String cleanedConceptImport = getConceptFromImport(conceptImport);
			importedConceptIds.add(cleanedConceptImport);
		});
		return importedConceptIds;
	}
}
