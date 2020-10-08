/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.structural;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.IBeanDelete;
import de.dlr.sc.virsat.model.concept.types.IBeanName;
import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.concept.types.category.BeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Interface to a Bean that will represent a StructuralElementInstance
 * This interface is used by all generated beans from a concept
 * This class gives simplified access to the internal StructuralElementInstance
 * @author fisc_ph
 *
 */
public interface IBeanStructuralElementInstance extends IBeanUuid, IBeanDelete, IBeanName {

	/**
	 * Call this method to get the full qualified name of the underlying StructuralElement
	 * @return The FQN of the category as String
	 */
	String getFullQualifiedSturcturalElementName();
	
	/**
	 * To receive the current Structural Element Instance behind this object
	 * @return A StructuralElementInstance Object
	 */
	StructuralElementInstance getStructuralElementInstance();
	
	/**
	 * Method to associate anew StructuralElementInstancwe with the object
	 * @param sei the StructuralElementInsatnce to be set
	 */
	void setStructuralElementInstance(StructuralElementInstance sei);
	
	/**
	 * Method to ad a Category Assignment  Bean to the Structural Element Instance Bean
	 * @param bca The Category Assignment bean to be added
	 */
	void add(IBeanCategoryAssignment bca);
	
	/**
	 * Method to ad a Category Assignment  Bean to the Structural Element Instance Bean
	 * @param ed The EditingDomain in which the command should act.
	 * @param bca The Category Assignment bean to be added
	 * @return the command to manipulate the structural element instance
	 */
	Command add(EditingDomain ed, IBeanCategoryAssignment bca);
	
	/**
	 * Method to remove A Category Assignment Bean from the StructuralElement Instance Bean
	 * @param bca the Category Assignment bean to be removed
	 */
	void remove(IBeanCategoryAssignment bca);

	/**
	 * Method to remove A Category Assignment Bean from the StructuralElement Instance Bean
	 * @param ed The EditingDomain in which the command should act.
	 * @param bca the Category Assignment bean to be removed
	 * @return the command to manipulate the structural element instance
	 */
	Command remove(EditingDomain ed, IBeanCategoryAssignment bca);
	
	/**
	 * Method to add a Child SEI Bean to the Structural Element Instance Bean
	 * @param bsei The SEI bean to be added
	 */
	void add(IBeanStructuralElementInstance bsei);
	
	/**
	 * Method to add a Child SEI Beans to the Structural Element Instance Bean
	 * @param ed The EditingDomain in which the command should act.
	 * @param bsei The SEI bean to be added
	 * @return the command to manipulate the structural element instance
	 */
	Command add(EditingDomain ed, IBeanStructuralElementInstance bsei);
	
	/**
	 * Method to remove A Child SEI Bean from the StructuralElement Instance Bean
	 * @param bsei the SEI bean to be removed
	 */
	void remove(IBeanStructuralElementInstance bsei);

	/**
	 * This method hands back all child Bean wrapped StructuralElementInstances of a given Type 
	 * @param beanSeiClazz The Type to be filtered for as java class
	 * @param <SEI_TYPE> the generic of the BeanStructuralElementInstance
	 * @return all children which could be found in the current Bean SEI that are of the given Type
	 */
	<SEI_TYPE extends IBeanStructuralElementInstance> List<SEI_TYPE> getChildren(Class<SEI_TYPE> beanSeiClazz);
	
	/**
	 * This method hands back all child Bean wrapped StructuralElementInstances of the abstract type ABeanStructuralElementInstance.
	 * The abstract type is used, because un-/marshalling can't handle the multiple inheritance
	 * in the interface.
	 * @return all children which could be found in the current Bean SEI
	 */
	List<ABeanStructuralElementInstance> getChildren();
	
	/**
	 * Set method to set all children via ABeanStructuralElementInstance.
	 * The abstract type is used, because un-/marshalling can't handle the multiple inheritance
	 * in the interface.
	 * @param newBeanSeis a List of ABeanStructuralElementInstance wrapping StructuralElementInstances
	 */
	void setChildren(List<ABeanStructuralElementInstance> newBeanSeis);
	
	/**
	 * This method hands back all deep child Bean wrapped StructuralElementInstances of a given Type 
	 * @param beanSeiClazz The Type to be filtered for as java class
	 * @param <SEI_TYPE> the generic of the BeanStructuralElementInstance
	 * @return all deep children which could be found in the current Bean SEI that are of the given Type
	 */
	<SEI_TYPE extends IBeanStructuralElementInstance> List<SEI_TYPE> getDeepChildren(Class<SEI_TYPE> beanSeiClazz);
	
	/**
	 * Method to remove A Child SEI Bean from the StructuralElement Instance Bean
	 * @param ed The EditingDomain in which the command should act.
	 * @param bsei the SEI bean to be removed
	 * @return the command to manipulate the structural element instance
	 */
	Command remove(EditingDomain ed, IBeanStructuralElementInstance bsei);
	
	/**
	 * Get method to get all CategoryAssignments of a special given type
	 * @param catBeanClazz The class type of the Category Assignment Bean to look for
	 * @param <BEAN_TYPE> the generic type for the bean
	 * @return a List of the given Category assignment beans. Operations on that list will not reflect into the underlying StructuralElementInstance
	 */
	<BEAN_TYPE extends IBeanCategoryAssignment> List<BEAN_TYPE> getAll(Class<BEAN_TYPE> catBeanClazz);
	
	/**
	 * Get method to get the first CategoryAssignment of a special given type
	 * Intended for use on category assignments with Cardinality 1
	 * @param catBeanClazz The class type of the Category Assignment Bean to look for
	 * @param <BEAN_TYPE> the generic type for the bean
	 * @return the given Category assignment bean or null if none is attached
	 */
	<BEAN_TYPE extends IBeanCategoryAssignment> BEAN_TYPE getFirst(Class<BEAN_TYPE> catBeanClazz);
	
	/**
	 * Get method to get all CategoryAssignments of the generic type BeanCategoryAssignment
	 * The generic type is used, because we don't want to un-/marshall the concrete type.
	 * @return a List of the given Category assignment beans. Operations on that list will not reflect into the underlying StructuralElementInstance
	 */
	List<BeanCategoryAssignment> getCategoryAssignments();
	
	/**
	 * Set method to set all CategoryAssignments via BeanCategoryAssignments.
	 * The generic type is used, because we don't want to un-/marshall the concrete type.
	 * @param newCaBeans a List of BeanCategoryAssignments wrapping CategoryAssignments
	 */
	void setCategoryAssignments(List<BeanCategoryAssignment> newCaBeans);
	
	/**
	 * Call this method to create a command to add a Bean SEI into the list of inheritances
	 * @param ed the editing domain to be used for creating the command
	 * @param bsei the Bean SEI that should be added to the list
	 * @return the command that will add the bean SEI to the list when executed
	 */
	Command addSuperSei(EditingDomain ed, IBeanStructuralElementInstance bsei);
	
	/**
	 * This method adds a Bean SEI to the inheritanceLink list
	 * @param bsei the Bean SEI to be added to the List
	 */
	void addSuperSei(IBeanStructuralElementInstance bsei);
	
	/**
	 * This method creates a command to remove a BEAN Sei form the current inheritance link list
	 * @param ed the editing domain to be used to create the command
	 * @param bsei the Bean SEI that should be removed from the list
	 * @return the command that will remove the bean SEI form the list
	 */
	Command removeSuperSei(EditingDomain ed, IBeanStructuralElementInstance bsei);

	/**
	 * This method removes a bean SEI from the list of inheritance links
	 * @param bsei the Bean SEI to be removed from the list
	 */
	void removeSuperSei(IBeanStructuralElementInstance bsei);

	/**
	 * This method hands back all super Bean wrapped StructuralElementInstances of a given Type to which
	 * the current one is inheriting from 
	 * @param beanSeiClazz The Type to be filtered for as java class
	 * @param <SEI_TYPE> the generic of the BeanStructuralElementInstance
	 * @return all super Bean Seis which could be found the current Bean SEI inheirts from that are of the given Type
	 */
	<SEI_TYPE extends IBeanStructuralElementInstance> List<SEI_TYPE> getSuperSeis(Class<SEI_TYPE> beanSeiClazz);

	/**
	 * This method hands back all super Bean wrapped StructuralElementInstances of the abstract type ABeanStructuralElementInstance
	 * the current one is inheriting from 
	 * The abstract type is used, because un-/marshalling can't handle the multiple inheritance
	 * in the interface.
	 * @return all super Bean Seis
	 */
	List<ABeanStructuralElementInstance> getSuperSeis();

	/**
	 * Set method to set all super seis via ABeanStructuralElementInstance
	 * The abstract type is used, because un-/marshalling can't handle the multiple inheritance
	 * in the interface.
	 * @param newBeanSeis a List of ABeanStructuralElementInstance wrapping StructuralElementInstances
	 */
	void setSuperSeis(List<ABeanStructuralElementInstance> newBeanSeis);

	/**
	 * @param beanSeiClazz The Type of SuperSeis of interest
	 * @param <SEI_TYPE> the generic of the BeanStructuralElementInstance
	 * @return all Bean-wrapped StructuralElementInstances of a given Type which this one inherits directly or indirectly
	 */
	<SEI_TYPE extends IBeanStructuralElementInstance> Set<SEI_TYPE> getAllSuperSeis(Class<SEI_TYPE> beanSeiClazz);
	
	/**
	 * Call this method to get the parent SEI of this CA which is of a given type
	 * @param beanSeiClazz the BeanClass defining the type to look for
	 * @param <SEI_TYPE> Generic class type
	 * @return the type or null in case it could not be found
	 */
	<SEI_TYPE extends IBeanStructuralElementInstance> SEI_TYPE getParentOfClass(Class<SEI_TYPE> beanSeiClazz);
	
	/**
	 * Call this method to get the parent SEI bean of this bean
	 * The generic type is used, because we don't want to un-/marshall the concrete type.
	 * @return parent SEI bean or null if it could not be found
	 */
	BeanStructuralElementInstance getParent();
	
	/**
	 * Call this method to set the parent SEI bean of this bean.
	 * The generic type is used, because we don't want to un-/marshall the concrete type.
	 * @param newParent the new parent SEI bean
	 */
	void setParent(BeanStructuralElementInstance newParent);
	
	/**
	 * Removes all CA beans in the given List from this SEI bean
	 * @param beanList list containing IBeanCategoryAssignment elements
	 */
	void removeAllCategoryAssignment(List<? extends IBeanCategoryAssignment> beanList);

	/**
	 * Removes all SEI beans in the given List from this SEI bean
	 * @param beanList list containing IBeanStructuralElementInstance elements
	 */
	void removeAllStructuralElementInstance(List<IBeanStructuralElementInstance> beanList);

	/**
	 * Removes all CA beans in the given list from this SEI bean
	 * @param ed the editing domain to be used to create the command
	 * @param beanList list containing IBeanCategoryAssignment elements
	 * @return the command that will remove all the beans SEI form the list
	 */
	Command removeAllCategoryAssignment(EditingDomain ed, List<IBeanCategoryAssignment> beanList);

	/**
	 * Removes all SEI beans in the given list from this SEI bean
	 * @param ed the editing domain to be used to create the command
	 * @param beanList list containing IBeanStructuralElementInstance elements
	 * @return the command that will remove all the beans SEI form the list
	 */
	Command removeAllStructuralElementInstance(EditingDomain ed, List<IBeanStructuralElementInstance> beanList);

	/**
	 * Removes all Beans of this Type
	 * @param catBeanClazz The class type of the Category Assignment Bean to look for
	 * @param <BEAN_TYPE> Category assignement bean class
	 */
	<BEAN_TYPE extends IBeanCategoryAssignment> void removeAll(Class<BEAN_TYPE> catBeanClazz);
	
	/**
	 * Checks if the Bean has a Category Assignment of a given type.
	 * @param catBeanClazz The class type of the Category Assignment Bean to look for
	 * @param <BEAN_TYPE> the generic type for the bean
	 * @return boolean if there is a Category
	 */
	<BEAN_TYPE extends IBeanCategoryAssignment> boolean hasCategory(Class<BEAN_TYPE> catBeanClazz);
	
	/**
	 * Checks if the current Bean SEI can be a root element.
	 * @return true if this SEI is of SE that can be on root level
	 */
	boolean canBeRoot();
}