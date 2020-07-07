/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.contentProvider;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * This class provides a Content provider that allows to filter content to specific
 * classes or categories and assignments of a given ID. it wraps another ContentProvider
 * that needs to be handed over to this given one 
 * 
 * 
 * @author fisc_ph
 *
 */
public class VirSatFilteredWrappedTreeContentProvider extends AFilteredContentProvider implements ITreeContentProvider {

	protected ITreeContentProvider wrappedContentProvider;
	private boolean checkContainedForFilter;
	private boolean checkExtendedTypesForFilter;
	
	/**
	 * Constructor of the Content Provider without adapter factory
	 * @param wrappedContentProvider Composed Adapter factory to be used by the content provider
	*/
	public VirSatFilteredWrappedTreeContentProvider(ITreeContentProvider wrappedContentProvider) {
		super();
		this.wrappedContentProvider = wrappedContentProvider;
		filterChildrenClasses = new HashSet<>();
		filterChildrenCategoryIds = new HashSet<>();
		filterChildrenStructuralElementIds = new HashSet<>();
		filterChildrenFunctions = new HashSet<>();
	}

	/**
	 * Constructor for backwards compatibility and our special use case of transactional
	 * content provider. Most content provider we need are based on the transactional one.
	 * the TCP will be used as the wrapped CP
	 * @param adapterFactory the adapterfactory used for the transactional content provider
	 */
	public VirSatFilteredWrappedTreeContentProvider(AdapterFactory adapterFactory) {
		this(new VirSatTransactionalAdapterFactoryContentProvider(adapterFactory));
	}

	private Set<String> filterChildrenCategoryIds;
	private Set<String> filterChildrenStructuralElementIds;
	private Set<Class<?>> filterChildrenClasses;
	private Set<Function<Object, Boolean>> filterChildrenFunctions;

	/**
	 * This method adds a class filter to the GetChildren Method
	 * @param filterClass the class that should explicitly  be returned by the getChildren Method
	 * @return returns the current instance to easily concatenate various filters.
	 */
	public VirSatFilteredWrappedTreeContentProvider addClassFilterToGetChildren(Class<?> filterClass) {
		filterChildrenClasses.add(filterClass);
		return (this);
	}

	/**
	 * This method adds a class filter to both GetChildren and GetElements Method
	 * @param filterClass the class that should explicitly  be returned by one of these methods
	 * @return returns the current instance to easily concatenate various filters.
	 */
	public VirSatFilteredWrappedTreeContentProvider addClassFilter(Class<?> filterClass) {
		addClassFilterToGetElement(filterClass);
		addClassFilterToGetChildren(filterClass);
		return (this);
	}

	/**
	 * Use this method to add an ID for a specific type id of the Category and Property Concept
	 * @param filterId the ID of the object that should be returned by the GetChildren method
	 * @return returns the current instance to easily concatenate various filters.
	 */
	public VirSatFilteredWrappedTreeContentProvider addCategoryIdFilterToGetChildren(String filterId) {
		filterChildrenCategoryIds.add(filterId);
		return (this);
	}
	
	/**
	 * Use this method to add an ID for a specific type id of the StructuralElement Concept
	 * @param filterId the ID of the object that should be returned by the GetChildren method
	 * @return returns the current instance to easily concatenate various filters.
	 */
	public VirSatFilteredWrappedTreeContentProvider addStructuralElementIdFilterToGetChildren(String filterId) {
		filterChildrenStructuralElementIds.add(filterId);
		return (this);
	}

	/**
	 * Use this method to add a custom filtering function
	 * @param filter the filter that the objects returned by the GetChildren method should satisfy
	 * @return returns the current instance to easily concatenate various filters.
	 */
	public VirSatFilteredWrappedTreeContentProvider addFunctionFilterToGetChildren(Function<Object, Boolean> filter) {
		filterChildrenFunctions.add(filter);
		return (this);
	}

	/**
	 * Use this method to add an ID for a specific type id of the Category and Property Concept
	 * @param filterId the ID of the object that should be returned by the GetElement and GetChildren method
	 * @return returns the current instance to easily concatenate various filters.
	 */
	public VirSatFilteredWrappedTreeContentProvider addCategoryIdFilter(String filterId) {
		addCategoryIdFilterToGetElement(filterId);
		addCategoryIdFilterToGetChildren(filterId);
		return (this);
	}

	/**
	 * Use this method to add an ID for a specific type id of the StructuralElement Concept
	 * @param filterId the ID of the object that should be returned by the GetElement and GetChildren method
	 * @return returns the current instance to easily concatenate various filters.
	 */
	public VirSatFilteredWrappedTreeContentProvider addStructuralElementIdFilter(String filterId) {
		addStructuralElementIdFilterToGetElement(filterId);
		addStructuralElementIdFilterToGetChildren(filterId);
		return (this);
	}
	
	/**
	 * Use this method to add a custom filtering function
	 * @param filter the filter that the objects returned by the GetElement and GetChildren methods should satisfy
	 * @return returns the current instance to easily concatenate various filters.
	 */
	public VirSatFilteredWrappedTreeContentProvider addFunctionFilter(Function<Object, Boolean> filter) {
		addFunctionFilterToGetElement(filter);
		addFunctionFilterToGetChildren(filter);
		return (this);
	}
	
	/**
	 * Set checkExtendedTypesForFilter flag
	 * @param checkExtendedTypesForFilter if set to true, an element will pass a filter if its category OR an extended category pass the filter
	 */
	public void setCheckExtendedTypesForFilter(boolean checkExtendedTypesForFilter) {
		this.checkExtendedTypesForFilter = checkExtendedTypesForFilter;
	}

	/**
	 * Set checkContainedForFilter flag
	 * @param checkContainedForFilter if set to true, an element will pass a filter if it OR a contained child pass the filter
	 */
	public void setCheckContainedForFilter(boolean checkContainedForFilter) {
		this.checkContainedForFilter = checkContainedForFilter;
	}
	
	@Override
	public Object[] getElements(Object rootObject) {
		Object[] allObjects = wrappedContentProvider.getElements(rootObject);
		Object[] filteredClasses = filterClasses(allObjects, filterElementClasses);
		Object[] filteredInstances = filterIds(filteredClasses, filterElementCategoryIds, filterElementStructuralElementIds);
		Object[] filteredFunctions = filterFunctions(filteredInstances, filterElementFunctions);
		return filteredFunctions;
	}
	
	@Override
	public Object[] getChildren(Object parentObject) {
		Object[] allObjects = wrappedContentProvider.getChildren(parentObject);
		Object[] filteredClasses = filterClasses(allObjects, filterChildrenClasses);
		Object[] filteredInstances = filterIds(filteredClasses, filterChildrenCategoryIds, filterChildrenStructuralElementIds);
		Object[] filteredFunctions = filterFunctions(filteredInstances, filterChildrenFunctions);
		return filteredFunctions;
	}

	@Override
	protected Object[] filterIds(Object[] objects, Set<String> categoryIdFilters, Set<String> structuralElementIdFilters) {
		Set<Object> filteredObjects = new HashSet<>();
		
		// In case there are no filters specified we return all objects
		if (categoryIdFilters.isEmpty() && structuralElementIdFilters.isEmpty()) {
			return objects;
		}

		// Now filter the
		for (Object object : objects) {
			if (object instanceof ATypeInstance && !categoryIdFilters.isEmpty()) {
				ATypeInstance aTypeInstance = (ATypeInstance) object;
				
				if (checkContainedForFilter) {
					EcoreUtil.getAllContents(aTypeInstance, true).forEachRemaining((containedObject) -> {
						if (containedObject instanceof ATypeInstance) {
							ATypeInstance containedTypeInstance = (ATypeInstance) containedObject;
							if (containedTypeInstance.getType() != null && !Collections.disjoint(categoryIdFilters, getFullQualifiedIds(containedTypeInstance.getType()))) {
								filteredObjects.add(object);
							}
						}
					});
				}
				
				if (aTypeInstance.getType() != null && !Collections.disjoint(categoryIdFilters, getFullQualifiedIds(aTypeInstance.getType()))) {
					filteredObjects.add(object);
				}
			} else if (object instanceof StructuralElementInstance && !structuralElementIdFilters.isEmpty()) {
				StructuralElementInstance sei = (StructuralElementInstance) object;
				
				if (checkContainedForFilter) {
					EcoreUtil.getAllContents(sei, true).forEachRemaining((containedObject) -> {
						if (containedObject instanceof StructuralElementInstance) {
							StructuralElementInstance containedSei = (StructuralElementInstance) containedObject;
							if (containedSei.getType() != null && structuralElementIdFilters.contains(ActiveConceptHelper.getFullQualifiedId(containedSei.getType()))) {
								filteredObjects.add(object);
							}
						}
					});
				}
				
				if (sei.getType() != null && structuralElementIdFilters.contains(ActiveConceptHelper.getFullQualifiedId(sei.getType()))) {
					filteredObjects.add(object);
				}
			} else {
				// All other objects will not be touched by the filter
				filteredObjects.add(object);
			}
		}
		return filteredObjects.toArray();
	}
	
	/**
	 * Gets the full qualified ids related to a type. If flag  
	 * checkExtendedTypesForFilter has been set, then all full qualified ids of the extended types
	 * will be returned. Otherwise only the full qualified id of the passed type.
	 * @param type the type
	 * @return a set of full qualified ids
	 */
	private Set<String> getFullQualifiedIds(ATypeDefinition type) {
		if (checkExtendedTypesForFilter) {
			return ActiveConceptHelper.getAllFullQualifiedIds(type);
		} else {
			Set<String> fqId = new HashSet<>();
			fqId.add(ActiveConceptHelper.getFullQualifiedId(type));
			return fqId;
		}
	}

	/**
	 * this method gives direct access to the elements without being filtered
	 * @param rootObject The object of which to get the elements
	 * @return an array of objects which are elements of the given one
	 */
	protected Object[] getNonFilteredElements(Object rootObject) {
		return wrappedContentProvider.getElements(rootObject);
	}

	/**
	 * this method gives direct access to the getChildren method without filtering
	 * @param parentObject the object of which to get the children
	 * @return all objects which are children of the given one
	 */
	protected Object[] getNonFilteredChildren(Object parentObject) {
		return wrappedContentProvider.getChildren(parentObject);
	}

	@Override
	public void dispose() {
		wrappedContentProvider.dispose();
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		wrappedContentProvider.inputChanged(viewer, oldInput, newInput);
	}

	@Override
	public Object getParent(Object element) {
		return wrappedContentProvider.getParent(element);
	}

	@Override
	public boolean hasChildren(Object element) {
		return this.getChildren(element).length > 0;
	}

	public ITreeContentProvider getWrappedContentProvider() {
		return wrappedContentProvider;
	}
}
