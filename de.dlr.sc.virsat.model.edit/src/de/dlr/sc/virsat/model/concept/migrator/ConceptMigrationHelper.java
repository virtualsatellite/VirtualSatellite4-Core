/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.migrator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Class containing utility functions for performing migration
 * @author muel_s8
 *
 */

public class ConceptMigrationHelper {
	
	private Concept concept;
	private Repository repository;
	
	/**
	 * Default constructor
	 * @param concept the concept to migrate
	 */
	public ConceptMigrationHelper(Concept concept) {
		this.concept = concept;
		this.repository = (Repository) concept.eContainer();
	}
	
	/**
	 * Gets the repository of the concept that will be migrated
	 * @return the repository
	 */
	public Repository getRepository() {
		return repository;
	}
	
	/**
	 * Gets a structural element from the wrapped concept given a name
	 * @param structuralElementName the name of the structural element
	 * @return the structural element
	 */
	public StructuralElement getStructuralElement(String structuralElementName) {
		return ActiveConceptHelper.getStructuralElement(concept, structuralElementName);
	}
	
	/**
	 * Gets a category from the wrapped concept given a name
	 * @param categoryName the name of the category
	 * @return the category
	 */
	public Category getCategory(String categoryName) {
		return ActiveConceptHelper.getCategory(concept, categoryName);
	}
	
	/**
	 * Get a property from a category from the wrapped concept given the appropriate names
	 * @param propertyName the property name
	 * @param categoryName the category name
	 * @return the property
	 */
	public AProperty getProperty(String propertyName, String categoryName) {
		Category category = ActiveConceptHelper.getCategory(concept, categoryName);
		return ActiveConceptHelper.getProperty(category, propertyName);
	}
	
	/**
	 * Adds a new equation definition to a category
	 * @param eqDefinition the equation definition
	 * @param category the category
	 */
	public void addEquationDefinition(EquationDefinition eqDefinition, Category category) {
		category.getEquationDefinitions().add(eqDefinition);
		CategoryInstantiator categoryInstantiator = new CategoryInstantiator();
		EcoreUtil.getAllContents(repository.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof CategoryAssignment) {
				CategoryAssignment ca = (CategoryAssignment) object;
				Category cat = (Category) ca.getType();
				if (cat.isExtensionOf(category)) {
					categoryInstantiator.addEquationForEquationDefinitionIntoCategoryAssignment(ca, eqDefinition);
				}
			}
		});
	}
	
	/**
	 * Removes a equation definition from a category and removes the equations
	 * to the required instances in the model
	 * @param eqDefinition the equation definition
	 */
	public void removeEquationDefinition(EquationDefinition eqDefinition) {
		List<Equation> toRemove = new ArrayList<>();
		
		EcoreUtil.getAllContents(repository.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof CategoryAssignment) {
				CategoryAssignment ca = (CategoryAssignment) object;
				if (ca.getEquationSection() != null) {
					for (Equation eq : ca.getEquationSection().getEquations()) {
						if (eq.getDefinition() != null && eqDefinition.equals(eq.getDefinition())) {
							toRemove.add(eq);
						}
					}
				}
			}
		});
		
		for (Equation eq : toRemove) {
			EcoreUtil.delete(eq);
		}
	}
	
	/**
	 * Removes a equation definition from a category and removes the equations
	 * to the required instances in the model
	 * @param oldEqDefinition the equation definition
	 * @param newEqDefinition the equation definition
	 */
	public void changeEquationDefinition(EquationDefinition oldEqDefinition, EquationDefinition newEqDefinition) {
		Category category = (Category) oldEqDefinition.eContainer();
		
		// Remove the equations defined by the old equation definition
		List<Equation> toRemove = new ArrayList<>();
		EcoreUtil.getAllContents(repository.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof CategoryAssignment) {
				CategoryAssignment ca = (CategoryAssignment) object;
				if (ca.getEquationSection() != null) {
					for (Equation eq : ca.getEquationSection().getEquations()) {
						EquationDefinition eqDefinition = eq.getDefinition();
						if (eqDefinition != null && eqDefinition.equals(oldEqDefinition)) {
							if (eq.isOverride()) {
								// If the equation has been overriden
								// we dont remove it under the users ass
								// but instead remove the link to the equation definition
								eq.setDefinition(null);
							} else {
								// If the equation is not set to override then 
								// remove it
								toRemove.add(eq);
							}
						}
					}
				}
			}
		});
		for (Equation eq : toRemove) {
			EcoreUtil.delete(eq);
		}
		
		addEquationDefinition(newEqDefinition, category);
	}
	
	/**
	 * Adds a new property to a category of the given name and adds the property instances
	 * to the required instances in the model
	 * @param property the property to be added
	 * @param category the category
	 */
	public void addProperty(AProperty property, Category category) {
		category.getProperties().add(property);
		CategoryInstantiator categoryInstantiator = new CategoryInstantiator();
		
		EcoreUtil.getAllContents(repository.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof CategoryAssignment) {
				CategoryAssignment ca = (CategoryAssignment) object;
				Category cat = (Category) ca.getType();
				if (cat.isExtensionOf(category)) {
					categoryInstantiator.addPropertyInstancesForPropertyIntoCategoryAssignment(ca, property);
				}
			}
		});
	}
	
	/**
	 * Removes a property along with all property instances in some category assignment in the model
	 * @param property the property to remove
	 */
	public void removeProperty(AProperty property) {
		List<APropertyInstance> toRemove = new ArrayList<>();
		
		EcoreUtil.getAllContents(repository.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof CategoryAssignment) {
				CategoryAssignment ca = (CategoryAssignment) object;
				for (APropertyInstance pi : ca.getPropertyInstances()) {
					if (pi.getType().equals(property)) {
						toRemove.add(pi);
					}
				}
			}
		});
		
		for (APropertyInstance pi : toRemove) {
			EcoreUtil.delete(pi);
		}
		
		EcoreUtil.delete(property);
	}
	
	/**
	 * Moves a property from one category to another and removes/instantiates new property instances.
	 * Note that old data in the category assignments will be lost, use custom migration
	 * if that is not the desired case.
	 * @param property the property to move
	 * @param newCategory the new category
	 */
	public void moveProperty(AProperty property, Category newCategory) {
		removeProperty(property);
		addProperty(property, newCategory);
	}
	
	/**
	 * Change the type of a property to a new property, keeping old values intact if possible
	 * @param oldProperty the old property type
	 * @param newProperty the new property type
	 */
	public void changePropertyType(AProperty oldProperty, AProperty newProperty) {
		Category category = (Category) oldProperty.eContainer();
		category.getProperties().remove(oldProperty);
		category.getProperties().add(newProperty);
		
		EcoreUtil.getAllContents(repository.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof CategoryAssignment) {
				CategoryAssignment ca = (CategoryAssignment) object;
				for (APropertyInstance pi : ca.getPropertyInstances()) {
					if (pi.getType().equals(oldProperty)) {
						pi.setType(newProperty);
					}
				}
			}
		});
	}
	
	/**
	 * Add a new category to the concept
	 * @param category the new category
	 */
	public void addCategory(Category category) {
		concept.getCategories().add(category);
	}
	
	/**
	 * Remove an existing category from the concept
	 * @param category the category to remove
	 */
	public void removeCategory(Category category) {
		List<CategoryAssignment> toRemove = new ArrayList<>();
		
		EcoreUtil.getAllContents(repository.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof CategoryAssignment) {
				CategoryAssignment ca = (CategoryAssignment) object;
				if (ca.getType().equals(category)) {
					toRemove.add(ca);
				}
			}
		});
		
		for (CategoryAssignment ca : toRemove) {
			EcoreUtil.delete(ca);
		}
		
		EcoreUtil.delete(category);
	}
	
	/**
	 * Add a new structural element to the concept
	 * @param structuralElement the structural element to be added
	 */
	public void addStructuralElement(StructuralElement structuralElement) {
		concept.getStructuralElements().add(structuralElement);
	}
	
	/**
	 * Remove an existing structural element from the concept
	 * @param structuralElement the structural element to be removed
	 */
	public void removeStructuralElement(StructuralElement structuralElement) {
		List<StructuralElementInstance> toRemove = new ArrayList<>();
		
		EcoreUtil.getAllContents(repository.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof StructuralElementInstance) {
				StructuralElementInstance sei = (StructuralElementInstance) object;
				if (sei.getType().equals(structuralElement)) {
					toRemove.add(sei);
				}
			}
		});
		
		for (StructuralElementInstance sei : toRemove) {
			EObject parent = sei.eContainer();
			if (parent == null) {
				repository.getRootEntities().remove(sei);
			} else if (parent instanceof StructuralElementInstance) {
				StructuralElementInstance parentSei = (StructuralElementInstance) parent;
				parentSei.getChildren().remove(sei);
			}
			EcoreUtil.delete(sei);
		}
		
		EcoreUtil.delete(structuralElement);
	}
	
	/**
	 * Compares two version for which is considered the newer version
	 * @param version1 a version string
	 * @param version2 another version string
	 * @return returns 1 iff version1 is newer than version2, 0 iff version1 and version2 are the same and
	 * -1 iff version2 is newer than version 1
	 */
	
	public static int compareVersions(String version1, String version2) {
		String[] version1Array = version1.split("\\.");
		String[] version2Array = version2.split("\\.");
		
		for (int i = 0, j = 0; i < version1Array.length && j < version2Array.length; ++i, ++j) {
			int version1Number = Integer.valueOf(version1Array[i]);
			int version2Number = Integer.valueOf(version2Array[i]);
			
			int compare = Integer.compare(version1Number, version2Number);
			if (compare != 0) {
				return compare;
			}
		}
		
		if (version1Array.length < version2Array.length) {
			return -1;
		} else if (version2Array.length > version1Array.length) {
			return 1;
		}
		
		return 0;
	}
}
