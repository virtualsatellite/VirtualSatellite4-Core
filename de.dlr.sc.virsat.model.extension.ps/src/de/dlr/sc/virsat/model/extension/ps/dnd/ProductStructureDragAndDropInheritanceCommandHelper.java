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
import de.dlr.sc.virsat.model.dvlm.Repository;
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
import de.dlr.sc.virsat.project.editingDomain.commands.dnd.VirSatDragAndDropInheritanceCommandHelper;
import de.dlr.sc.virsat.project.editingDomain.commands.dnd.VirSatDragAndDropInheritanceCommandHelper.DndOperation;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;

/**
 * This is a helper class to create the correct command for replacing an inheritance
 * of a SEI with another SEI.
 */
public class ProductStructureDragAndDropInheritanceCommandHelper {

	protected Map<Class<? extends IBeanStructuralElementInstance>, Class<? extends IBeanStructuralElementInstance>> mapDropBeanToCreateBean;
	
	/**
	 * Constructor
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
	}
	
	/**
	 * Call this method to create a command to add or replace the SEI in the list of superSEIs
	 * @param ed The editing domain for which the command should be created
	 * @param dragObject the object which is dragged and shall be used for replacing
	 * @param dropOperation the kind of operation
	 * @param dropSei the SEI where the superSEIs should be changed
	 * @return The command to add or replace a superSEI
	 */
	public Command createDropCommand(VirSatTransactionalEditingDomain ed, Collection<Object> dragObjects, StructuralElementInstance dropSei) {
		IBeanStructuralElementInstance createSeiBean = createBeanStructuralElementInstanceForDrop(ed, dragObjects, dropSei);
		
		if (createSeiBean != null) {
			StructuralElementInstance createSei = createSeiBean.getStructuralElementInstance();
			Command addSeiCommand = CreateAddSeiWithFileStructureCommand.create(
					ed,
					dropSei,
					createSei
			);
			Command setInheritanceCommand = VirSatDragAndDropInheritanceCommandHelper.createDropCommand(
					ed,
					dragObjects,
					DndOperation.ADD_INHERITANCE,
					createSei
			);
			
			CompoundCommand commandCreateSeiAndSetInheritance = new CompoundCommand();
			commandCreateSeiAndSetInheritance.append(addSeiCommand);
			commandCreateSeiAndSetInheritance.append(setInheritanceCommand);

			return commandCreateSeiAndSetInheritance;
		}
		
		return UnexecutableCommand.INSTANCE;
	}	
	
	/**
	 * Call this method to create a BeanSei for a given drop Target SEI
	 * @param ed The editing domain in which the object will be dropped
	 * @param dragObjects The objects preferably SEIs which are dragged
	 * @param dropSei the SEI to which the objects shall get dropped
	 * @return A BeanSEI with corresponding to the drop target with correct name and discipline
	 */
	public IBeanStructuralElementInstance createBeanStructuralElementInstanceForDrop(VirSatTransactionalEditingDomain ed, Collection<Object> dragObjects, StructuralElementInstance dropSei) {
		// When creating a new object and setting the inheritance link at the same time
		// then there should be only one element in the list anyway, so get the first one.
		Object dragObject = dragObjects.iterator().next();
		
		try {
			if (dragObject instanceof StructuralElementInstance) {
				// Identify the concept
				VirSatResourceSet virSatResourceSet = ed.getResourceSet();
				Repository currentRepository = virSatResourceSet.getRepository();
				Concept concept = new ActiveConceptHelper(currentRepository).getConcept(Activator.getPluginId());
				
				// Create a bean for the given dropSEI
				BeanStructuralElementInstanceFactory bsf = new BeanStructuralElementInstanceFactory();
				IBeanStructuralElementInstance dropBeanSei = bsf.getInstanceFor(dropSei);
				
				// Use the created bean to decide which type of bean should be created for adding to the dropSEI
				Class<? extends IBeanStructuralElementInstance> dropBeanSeiClass = dropBeanSei.getClass();
				Class<? extends IBeanStructuralElementInstance> createBeanSeiClass = mapDropBeanToCreateBean.get(dropBeanSeiClass);
				
				// If there is a suggestion to it and if it was successful to identify the concept
				// Then it it is possible to create a new BeanSei and to set it up correctly
				if (createBeanSeiClass != null && concept != null) {
					Constructor<? extends IBeanStructuralElementInstance> createBeanSeiConstructor = createBeanSeiClass.getDeclaredConstructor(Concept.class);
					IBeanStructuralElementInstance createBeanSei = (IBeanStructuralElementInstance) createBeanSeiConstructor.newInstance(concept);
					StructuralElementInstance createSei = createBeanSei.getStructuralElementInstance();
					
					// Now copy the name and the disciplines accordingly
					StructuralElementInstance dragSei = (StructuralElementInstance) dragObject;
					Discipline discipline = dropSei.getAssignedDiscipline(); 
					createSei.setAssignedDiscipline(discipline);
					String name = dragSei.getName(); 
					createSei.setName(name);
					
					// Now fix naming clashes
					DVLMCopiedNameHelper dcnh = new DVLMCopiedNameHelper();
					dcnh.updateCopiedNames(dropSei.getChildren(), Collections.singleton(createSei));
	
					return createBeanSei;
				}
			}
		} catch (CoreException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Activator.getDefault().getLog().log(new Status(
					Status.INFO,
					Activator.getPluginId(),
					"Encountered a problem when trying to create a product structure drag and drop command: " + e.getMessage()
			));
		}
		
		// in case of error or if it is not possible to create a correct SEI return null
		return null;
	}
}
