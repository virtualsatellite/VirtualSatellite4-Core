/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopier;

/**
 * This class extends the EcoreUtil by specific implementations needed by VirSat
 * and special operations on the DVLM
 * @author fisc_ph
 *
 */
public class VirSatEcoreUtil extends EcoreUtil {
	
	/**
	 * Call this method to find all objects of a given type in a collection 
	 * @param collection the collection in which to search for the objects
	 * @param type the class that specifies the type to look for
	 * @param <T> the type of object that should be looked for
	 * @param resolve whether to resolve proxies or not
	 * @return the list of objects found
	 */
	public static <T extends EObject> List<T> getAllContentsOfType(Collection<?> collection, Class<T> type, boolean resolve) {
		List<T> result = new ArrayList<T>();
		collection.forEach((collectionObject) -> {
			if (collectionObject instanceof EObject) {
				TreeIterator<?> iterator = getAllContents((EObject) collectionObject, resolve);
				result.addAll(getAllContentsOfType(iterator, type)); 
			}
		});
		return result;
	}
	
	/**
	 * Call this method to find all objects of a given type in a resource 
	 * @param resource the resource in which to search for the objects
	 * @param type the class that specifies the type to look for
	 * @param <T> the type of object that should be looked for
	 * @param resolve whether to resolve proxies or not
	 * @return the list of objects found
	 */
	public static <T extends EObject> Iterator<EObject> getAllContentsOfType(Resource resource, Class<T> type, boolean resolve) {
		if (resource != null) {
			List<EObject> result = new ArrayList<EObject>();
			TreeIterator<?> iterator = getAllContents(resource, true);
			result.addAll(getAllContentsOfType(iterator, type)); 
			return result.iterator();
		} else {
			return Collections.emptyIterator();
		}
	}
	
	/**
	 * This method gets all content of a specific type from a given iterator
	 * @param iterator The iterator to be used for the search
	 * @param type the type that should be searched for
	 * @param <T> the actual type class
	 * @return a list of objects from the given type that were found in the iterator
	 */
	@SuppressWarnings("unchecked")
	public static <T extends EObject> List<T> getAllContentsOfType(TreeIterator<?> iterator, Class<T> type) {
		List<T> result = new ArrayList<T>();
		iterator.forEachRemaining((iteratorObject) -> {
			if (type.isAssignableFrom(iteratorObject.getClass())) {
				result.add((T) iteratorObject);
			}
		});
		return result;
	}
	
	/**
	 * Call this method to find all objects of a given type in a ResourceSet 
	 * @param resourceSet the resourceSet in which to search for the objects
	 * @param skipResource a resource which should be skipped when looking for the containments of the given type. Can be set to null.
	 * @param type the class that specifies the type to look for
	 * @param <T> the type of object that should be looked for
	 * @param resolve whether to resolve proxies or not
	 * @return the list of objects found
	 */
	public static <T extends EObject> List<T> getAllContentsOfType(ResourceSet resourceSet, Resource skipResource, Class<T> type, boolean resolve) {
		List<T> result = new ArrayList<T>();
		resolveAll(resourceSet);
		resourceSet.getResources().forEach((resource) -> {
			if (resource != skipResource) {
				TreeIterator<?> iterator = getAllContents(resource, resolve);
				result.addAll(getAllContentsOfType(iterator, type));
			}
		});
		
		return result;
	}
	
	/**
	 * Call this method to find all objects of a given type in a set of ResourceSet 
	 * @param resourceSets a set of ResourceSets in which to search for the objects
	 * @param skipResource a resource which should be skipped when looking for the containments of the given type. Can be set to null.
	 * @param type the class that specifies the type to look for
	 * @param <T> the type of object that should be looked for
	 * @param resolve whether to resolve proxies or not
	 * @return the list of objects found
	 */
	public static <T extends EObject> List<T> getAllContentsOfTypefromResourceSets(Collection<ResourceSet> resourceSets, Resource skipResource, Class<T> type, boolean resolve) {
		List<T> result = new ArrayList<T>();
		for (ResourceSet resourceSet :  resourceSets) {
			result.addAll(getAllContentsOfType(resourceSet, skipResource, type, resolve));
		}
		
		return result;
	}
	
	/**
	 * if the object itself is contained in the contents of its resource it is the RootElement in this resource
	 * if it has no resource, it is also not the head of it ;)
	 * @param eObject to be checked 
	 * @return if this object is the root element in its resource
	 */
	public static boolean isRootComponentofResource(EObject eObject) {
		Resource resource = eObject.eResource(); 
		if (resource == null) {
			return false;
		} else {
			return resource.getContents().contains(eObject);
		}
	}
	
	public static final String ATTRIBUTE_FQN_DELIMITER = ".";
	
	/**
	 * get the FQN consisting of the Ecore package/Class/Attribute
	 * @param eAttribute the Ecore Attribute to get the FQN for
	 * @return an FQN delimited by a point "."
	 */
	public static String getFullQualifiedAttributeName(EAttribute eAttribute) {
		EClass eClass = eAttribute.getEContainingClass();
				
		String classFqn = getFullQualifiedClassName(eClass);
		String attributeFqn = eAttribute.getName();

		return classFqn + ATTRIBUTE_FQN_DELIMITER + attributeFqn;
	}
	
	/**
	 * get the FQN consisting of the Ecore package/Class
	 * @param eClass the Ecore Attribute to get the FQN for
	 * @return an FQN delimited by a point "."
	 */
	public static String getFullQualifiedClassName(EClass eClass) {
		EPackage ePackage = eClass.getEPackage();
		
		String packageFqn =  ePackage.getName();
		EPackage superEPackage = ePackage.getESuperPackage();
		while (superEPackage != null) {
			packageFqn = superEPackage.getName() + ATTRIBUTE_FQN_DELIMITER + packageFqn;
			superEPackage = superEPackage.getESuperPackage();
		}
				
		String classFqn = eClass.getName();

		return packageFqn + ATTRIBUTE_FQN_DELIMITER + classFqn;
	}
	
	/**
	 * Returns a self-contained copy of the eObject.
	 * 
	 * @param eObject the object to copy.
	 * @param <T> Generic Type that should extend eObject
	 * @return the copy.
	 * @see Copier
	 */
	public static <T extends EObject> T copy(T eObject) {
		Copier copier = new DVLMCopier();
		EObject result = copier.copy(eObject);
		copier.copyReferences();

		@SuppressWarnings("unchecked")
		T t = (T) result;
		return t;
	}

	/**
	 * Returns a collection of the self-contained copies of each {@link EObject}
	 * in eObjects.
	 * 
	 * @param eObjects the collection of objects to copy.
	 * @param <T> Generic Type that should extend eObject
	 * @return the collection of copies.
	 * @see Copier
	 */
	public static <T> Collection<T> copyAll(Collection<? extends T> eObjects) {
		Copier copier = new DVLMCopier();
		Collection<T> result = copier.copyAll(eObjects);
		copier.copyReferences();
		return result;
	}
	
	/**
	 * Hand back the CategoryAssignmentContainer for a given TypeInstance
	 * @param object the TypeInstance for which to get back its container
	 * @param containerClazz The class type of the container to look for
	 * @param <T> Generic Type of the containerClazz that has to be an EObject
	 * @return the ICategroyAssignmentContainer that contains the given TypeInstance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends EObject> T getEContainerOfClass(EObject object, Class<T> containerClazz) {

		EObject eContainerObject = object.eContainer();
		
		while ((eContainerObject != null) && !(containerClazz.isAssignableFrom(eContainerObject.getClass()))) {
			eContainerObject = eContainerObject.eContainer();
		}
		
		return (T) eContainerObject;
	}
	
	/**
	 * Gets the last segment of a full qualified name
	 * @param fqn the full qualified name
	 * @return the last segment of the full qualified name
	 */
	public static String getNameOfFqn(String fqn) {
		int lastDot = fqn.lastIndexOf('.');
		if (lastDot == -1) {
			return fqn;
		}
		return fqn.substring(lastDot + 1, fqn.length());
	}
	
	/**
	 * Call this method to get all the references to a collection of objects which are about to be deleted. This method
	 * also hands back references to contained objects of the delete. The method also filters out the references which
	 * are about to be deleted anyway. The method does not account for superTI links from the IInheritanceLink interface.
	 * @param deletedObjects a collection of objects to be deleted
	 * @param resSet the resourceSet from which the objects will be deleted
	 * @return a Map with the objects that are referenced providing a list with all the objects actually referencing
	 */
	public static Map<EObject, List<EObject>> getReferencingObjectsForDelete(Collection<? extends EObject> deletedObjects, ResourceSet resSet) {
		// This map brings the referencing objects into relation with the ones to be deleted
		// All objects which are referenced by another one from outside the containment will be found in this map.
		Map<EObject, List<EObject>> mapDeletedObjectReferencedBy = new HashMap<>();
		
		// Find all contained objects that may get deleted with this call
		Collection<EObject> containedDeletedObjects = getAllContentsOfType(deletedObjects, EObject.class, true);
		containedDeletedObjects.addAll(deletedObjects);

		// Now loop over all elements and check if they are having a
		// reference coming from outside the containment.
		for (EObject deletedObject : containedDeletedObjects) {
			// the Usage cross references crawls the whole resourceSet and hands back Settings with
			// The object and the feature referencing the object in question. Their might a performance hit
			// with this code on large models. This is something to be solved later by some indexing. Actually
			// EMF already supports some indexing through special adapters that we don't yet use.
			Collection<Setting> deletedObjectReferences = UsageCrossReferencer.find(deletedObject, resSet);
			
			// Now loop over all Settings
			deletedObjectReferences.forEach((setting) -> {
				// Get the object which is referencing ours and remember it 
				// in case it is of type IInstance. All references from other objects
				// are ignored for the moment. We have to check how this works for Equations etc.
				EObject referencingObject = setting.getEObject();
				EStructuralFeature referencingFeature = setting.getEStructuralFeature();
				
				// This actually checks if the reference will be deleted as well
				// and if the referencing object is having a reference by inheritance, which
				// is not actually accounted as a reference
				boolean isReferenceConatined = isAncestor(containedDeletedObjects, referencingObject);
				boolean isInheritanceLink = referencingFeature == InheritancePackage.Literals.IINHERITANCE_LINK__SUPER_TIS;
				
				// So finally if it is a reference from outside the containment, than remember it to be handed back
				if ((!isInheritanceLink) && (!isReferenceConatined) && (referencingObject instanceof IInstance)) {
					// Create the List of referencing objects for the to be deleted object in case it does not yet exist
					if (!mapDeletedObjectReferencedBy.containsKey(deletedObject)) {
						mapDeletedObjectReferencedBy.put(deletedObject, new ArrayList<EObject>());
					}
					
					// and now remember the referencing object in the list.
					mapDeletedObjectReferencedBy.get(deletedObject).add((IInstance) referencingObject);
				}
			});
		}
		
		// Finally hand back the map, it will be an empty map, if there are no references outside the containment
		return mapDeletedObjectReferencedBy;
	}
	
	/**
	 * This method creates an instance of a BasicDiagnostic with OK Status 
	 * @param message A message to be placed into the OK diagnostic
	 * @return the diagnostic
	 */
	public static BasicDiagnostic createDiagnosticOk(String message) {
		return new BasicDiagnostic(
				Diagnostic.OK,
				"de.dlr.sc.virsat.model.ecore",
				0,
				message,
				null);
	}
}
