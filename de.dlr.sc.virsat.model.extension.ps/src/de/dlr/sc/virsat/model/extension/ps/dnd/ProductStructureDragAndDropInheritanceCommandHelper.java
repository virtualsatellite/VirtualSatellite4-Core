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
	
	public ProductStructureDragAndDropInheritanceCommandHelper() {
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
	
	
	public IBeanStructuralElementInstance createBeanStructuralElementInstanceForDrop(VirSatTransactionalEditingDomain ed, Collection<Object> dragObjects, StructuralElementInstance dropSei) {
		Object dragObject = dragObjects.iterator().next();
		
		try {
			if (dragObject instanceof StructuralElementInstance) {
				VirSatResourceSet virSatResourceSet = ed.getResourceSet();
				Repository currentRepository = virSatResourceSet.getRepository();
				Concept concept = new ActiveConceptHelper(currentRepository).getConcept(Activator.getPluginId());
				
				BeanStructuralElementInstanceFactory bsf = new BeanStructuralElementInstanceFactory();
				IBeanStructuralElementInstance dropBeanSei = bsf.getInstanceFor(dropSei);
				
				Class<? extends IBeanStructuralElementInstance> dropBeanSeiClass = dropBeanSei.getClass();
				Class<? extends IBeanStructuralElementInstance> createBeanSeiClass = mapDropBeanToCreateBean.get(dropBeanSeiClass);
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
		return null;
	}
}
