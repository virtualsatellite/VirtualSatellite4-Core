/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator;

import java.util.HashSet;
import java.util.Set;

import de.dlr.sc.virsat.model.concept.generator.beans.GenerateCategoryBeans;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;

/**
 * This class can be used to collect all imports and serialize
 * them at the end of the file generation
 * @author fisc_ph
 *
 */
public class ImportManager {
	private Set<String> importedClasses;
	private String currentPackage;
	
	/**
	 * Method to return all classes which have been imported to this manager
	 * @return a list of strings representing the class names
	 */
	public Set<String> getImportedClasses() {
		return importedClasses;
	}
	
	/**
	 * Standard Constructor
	 */
	public ImportManager() {
		importedClasses = new HashSet<>();
	}
	
	/**
	 * Constructor to set package
	 * @param currentPackage package to which imports will be ignored
	 */
	public ImportManager(String currentPackage) {
		importedClasses = new HashSet<>();
		setPackage(currentPackage);
	}
	
	/**
	 * Set the currentPackage
	 * @param currentPackage package to which imports will be ignored
	 */
	public void setPackage(String currentPackage) {
		this.currentPackage = currentPackage;
	}
	
	/**
	 * use this method to register a class to be imported
	 * @param clazz The class to be imported in the generated file
	 * @return the SImpleName of the class
	 */
	public String serialize(Class<?> clazz) {
		String fullQualifiedClassName = clazz.getName();
		checkPackageAndImport(fullQualifiedClassName);
		return clazz.getSimpleName();
	}

	/**
	 * use this method to register a class to be imported
	 * @param clazz The class to be imported in the generated file
	 */
	public void register(Class<?> clazz) {
		String fullQualifiedClassName = clazz.getName();
		checkPackageAndImport(fullQualifiedClassName);
	}

	/**
	 * Method to get the right import statement for ATypeDefinitons. It also deals with cross references
	 * @param aTypeDefinition The Type Definition for which to get the correct import statement
	 * @return a string for what class with Package should be imported
	 */
	private String getImportedClass(IQualifiedName aTypeDefinition) {
		String importClass;
		if (aTypeDefinition instanceof StructuralElement) {
			StructuralElement se = (StructuralElement) aTypeDefinition;
			Concept concept = (Concept) se.eContainer();
			importClass = concept.getName() + "." + GenerateCategoryBeans.PACKAGE_FOLDER + "." + se.getName();
		} else if (aTypeDefinition instanceof Category) {
			Category category = (Category) aTypeDefinition;
			Concept concept = (Concept) category.eContainer();
			importClass = concept.getName() + "." + GenerateCategoryBeans.PACKAGE_FOLDER + "." + category.getName();
		} else {
			AProperty property = (AProperty) aTypeDefinition;
			Category category = (Category) property.eContainer();
			Concept concept = (Concept) category.eContainer();
			importClass = concept.getName() + "." + GenerateCategoryBeans.PACKAGE_FOLDER + "." + category.getName() + "." + property.getName();
		}

		return importClass;
	}
	
	/**
	 * Use this method to add a type definition to the imported classes 
	 * @param aTypeDefinition The TypeDefinition which should be imported
	 * @return the type name
	 */
	public String serialize(IQualifiedName aTypeDefinition) {
		String importedClass = getImportedClass(aTypeDefinition);
		checkPackageAndImport(importedClass);
		return aTypeDefinition.getName();
	}
	
	/**
	 * Use this method to add a type definition to the imported classes 
	 * @param aTypeDefinition The TypeDefinition which should be imported
	 * @return the type name
	 */
	public String serializeTest(IQualifiedName aTypeDefinition) {
		String importedClass = getImportedClass(aTypeDefinition);
		checkPackageAndImport(importedClass + "Test");
		return aTypeDefinition.getName() + "Test";
	}

	/**
	 * Use this method to add a type definition to the imported classes 
	 * @param aTypeDefinition The TypeDefinition which should be imported
	 */
	public void register(IQualifiedName aTypeDefinition) {
		String importedClass = getImportedClass(aTypeDefinition);
		checkPackageAndImport(importedClass);
	}
	
	/**
	 * Method to register a class using its fullQualified name
	 * @param fullQualifiedImport The class fqn as string
	 */
	public void register(String fullQualifiedImport) {
		checkPackageAndImport(fullQualifiedImport);
	}
	
	/**
	 * Method to only register classes not in the current package
	 * @param fullQualifiedImport the class fqn as string
	 */
	private void checkPackageAndImport(String fullQualifiedImport) {
		if (!isInCurrentPackage(fullQualifiedImport)) {
			importedClasses.add(fullQualifiedImport);
		}
	}
	
	/**
	 * Method to check if a class is in the current package
	 * @param fullQualifiedImport the class fqn as string
	 */
	private boolean isInCurrentPackage(String fullQualifiedImport) {
		boolean inPackage = false;
		
		if (currentPackage != null) {
			String classPackage = fullQualifiedImport.substring(0, fullQualifiedImport.lastIndexOf("."));			
			return classPackage.equals(currentPackage);
		}
		
		return inPackage;
	}
}
