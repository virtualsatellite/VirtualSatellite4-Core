/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.dnd;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;

import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopiedNameHelper;
import de.dlr.sc.virsat.model.extension.ps.Activator;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.ps.model.ElementRealization;
import de.dlr.sc.virsat.model.extension.ps.model.ProductStorageDomain;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;

/**
 * This is a helper class to create the correct command for replacing an inheritance
 * of a SEI with another SEI.
 */
public class ProductStructureDragAndDropInheritanceCommandHelper {

	protected Map<Class<? extends IBeanStructuralElementInstance>, Class<? extends IBeanStructuralElementInstance>> mapDropBeanToCreateBean;
	protected BeanStructuralElementInstanceFactory bsf;
	
	/**
	 * Constructor instantiating the internal map of which drop objects
	 * expect which child object to be created.
	 */
	public ProductStructureDragAndDropInheritanceCommandHelper() {
		
		// Populate map with information about which Bean to create for which drop target
		mapDropBeanToCreateBean = new HashMap<>();
		
		mapDropBeanToCreateBean.put(ConfigurationTree.class, ElementConfiguration.class);
		mapDropBeanToCreateBean.put(ElementConfiguration.class, ElementConfiguration.class);

		mapDropBeanToCreateBean.put(AssemblyTree.class, ElementOccurence.class);
		mapDropBeanToCreateBean.put(ElementOccurence.class, ElementOccurence.class);

		mapDropBeanToCreateBean.put(ProductStorageDomain.class, ElementRealization.class);
		mapDropBeanToCreateBean.put(ElementRealization.class, ElementRealization.class);
		
		bsf = new BeanStructuralElementInstanceFactory();
	}
	
	/**
	 * Call this method to create a command to add or replace the SEI in the list of superSEIs
	 * @param ed The editing domain for which the command should be created
	 * @param dragObject the object which is dragged and shall be used for replacing
	 * @param dropOperation the kind of operation
	 * @param dropSei the SEI where the superSEIs should be changed
	 * @return The command to add or replace a superSEI
	 * @throws CoreException 
	 */
	public Command createDropCommand(VirSatTransactionalEditingDomain ed, Collection<Object> dragObjects, IBeanStructuralElementInstance dropBeanSei) {
		// Identify the dragObject and create a Bean for it;
		Object dragObject = dragObjects.iterator().hasNext() ? dragObjects.iterator().next() : null;
		
		// In case the drag object is a SEI cast it to a bean
		if (dragObject instanceof StructuralElementInstance) {
			try {
				dragObject = bsf.getInstanceFor((StructuralElementInstance) dragObject);
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(
						Status.WARNING,
						Activator.getPluginId(),
						"Encountered a problem when trying to cast a Bean SEI: ",
						e
				));
			}
		}
		
		// if the dragObject is a BEAN Sei continue
		if (dragObject instanceof IBeanStructuralElementInstance) {
			// Create the new SEI that will be placed as new child
			IBeanStructuralElementInstance dragBeanSei = (IBeanStructuralElementInstance) dragObject; 
			IBeanStructuralElementInstance createSeiBean = createBeanStructuralElementInstanceForDrop(dragBeanSei, dropBeanSei);
			
			// if it could be created prepare the commands for adding it as child and
			// for adding the inheritance link.
			if (createSeiBean != null) {
				Command addSeiCommand = CreateAddSeiWithFileStructureCommand.create(
						ed,
						dropBeanSei.getStructuralElementInstance(),
						createSeiBean.getStructuralElementInstance()
				);
				Command setInheritanceCommand = createSeiBean.addSuperSei(
						ed,
						dragBeanSei
				);
				
				CompoundCommand commandCreateSeiAndSetInheritance = new CompoundCommand();
				commandCreateSeiAndSetInheritance.append(addSeiCommand);
				commandCreateSeiAndSetInheritance.append(setInheritanceCommand);
	
				return commandCreateSeiAndSetInheritance;
			}
		}
			
		// In all other case hand back an unexecutable command
		return UnexecutableCommand.INSTANCE;
	}	
	
	/**
	 * Call this method to create a BeanSei for a given drop Target SEI
	 * @param ed The editing domain in which the object will be dropped
	 * @param beanDragSei The objects preferably SEIs which are dragged
	 * @param dropSei the SEI to which the objects shall get dropped
	 * @return A BeanSEI corresponding to the drop target with correct name and discipline
	 */
	public IBeanStructuralElementInstance createBeanStructuralElementInstanceForDrop(IBeanStructuralElementInstance dragBeanSei, IBeanStructuralElementInstance dropBeanSei) {
		try {
			// Identify the concept
			Concept concept = ActiveConceptHelper.getConcept(dropBeanSei.getStructuralElementInstance().getType());
			
			// Use the created bean to decide which type of bean should be created for adding to the dropSEI
			Class<? extends IBeanStructuralElementInstance> dropBeanSeiClass = dropBeanSei.getClass();
			Class<? extends IBeanStructuralElementInstance> createBeanSeiClass = mapDropBeanToCreateBean.get(dropBeanSeiClass);
			
			// If there is a suggestion to it and if it was successful to identify the concept
			// Then it it is possible to create a new BeanSei and to set it up correctly
			if (createBeanSeiClass != null && concept != null) {
				Constructor<? extends IBeanStructuralElementInstance> createBeanSeiConstructor = createBeanSeiClass.getDeclaredConstructor(Concept.class);
				IBeanStructuralElementInstance createBeanSei = (IBeanStructuralElementInstance) createBeanSeiConstructor.newInstance(concept);
				StructuralElementInstance createSei = createBeanSei.getStructuralElementInstance();
				StructuralElementInstance dropSei = dropBeanSei.getStructuralElementInstance();
				
				// Now copy the name and the disciplines accordingly
				Discipline discipline = dropBeanSei.getStructuralElementInstance().getAssignedDiscipline(); 
				createSei.setAssignedDiscipline(discipline);
				String name = dragBeanSei.getName(); 
				createSei.setName(name);
				
				// Now fix naming clashes
				DVLMCopiedNameHelper dcnh = new DVLMCopiedNameHelper();
				dcnh.updateCopiedNames(dropSei.getChildren(), Collections.singleton(createSei));

				return createBeanSei;
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Activator.getDefault().getLog().log(new Status(
					Status.WARNING,
					Activator.getPluginId(),
					"Encountered a problem when trying to create a product structure drag and drop command: ",
					e
			));
		}
		
		// in case of error or if it is not possible to create a correct SEI return null
		return null;
	}
}
