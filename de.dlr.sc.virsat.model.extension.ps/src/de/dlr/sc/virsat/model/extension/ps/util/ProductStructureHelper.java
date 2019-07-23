/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.util;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.command.DeleteStructuralElementInstanceCommand;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopier;
import de.dlr.sc.virsat.model.extension.ps.Activator;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;

/**
 * Class for creating configuration tree from a product tree
 * @author bell_er
 */
public class ProductStructureHelper {
	/**
	 * Class for creating configuration tree from a product tree
	 * @author bell_er
	 */
	private ProductStructureHelper() {
		
	}

	/**
	 * @return the concept name
	 */
	public static String getConcept() {
		return Activator.getPluginId();
	}
	
	/**
	 * Creates the Tree which will be displayed on Generate page
	 * @param sc initially selected Structural Element Instance
	 * @return at corresponding tree
	 * @author bell_er
	 */
	public static ABeanStructuralElementInstance createTreeModel(StructuralElementInstance sc) {
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(sc);
		Concept concept = (Concept) sc.getType().eContainer();
		String structuralElementName = null;
		String childrenTypeName = null;
		String scTypeName = sc.getType().getName();

		if (scTypeName.equals(ProductTree.class.getSimpleName())) {
			structuralElementName = ConfigurationTree.class.getSimpleName();
			childrenTypeName = ElementConfiguration.class.getSimpleName();
		} else if (scTypeName.equals(ConfigurationTree.class.getSimpleName())) {
			structuralElementName = AssemblyTree.class.getSimpleName();
			childrenTypeName = ElementOccurence.class.getSimpleName();
		}

		StructuralElement structuralElement = ActiveConceptHelper.getStructuralElement(concept, structuralElementName);
		ABeanStructuralElementInstance absc = createBean(structuralElement, ed.getResourceSet().getRepository().getAssignedDiscipline());

		for (int i = 0; i < sc.getChildren().size(); i++) {
			ABeanStructuralElementInstance child = getChildren(sc.getChildren().get(i), childrenTypeName, concept);
			absc.add(child);
		}

		return absc;	
	}
	
	/**
	 * Creates the Tree recursively by copying all the elements of sc
	 * @param sc all the elements of sc will be copied in the assembly tree
	 * @param structuralElementName name of the structural element
	 * @param concept the concept
	 * @author bell_er
	 * @return bsc corresponding ABeanStructuralElementInstance
	 */
	public static ABeanStructuralElementInstance getChildren(StructuralElementInstance sc, String structuralElementName, Concept concept) {
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(sc);
		StructuralElement structuralElement = ActiveConceptHelper.getStructuralElement(concept, structuralElementName);
		ABeanStructuralElementInstance bsc = createBean(structuralElement, ed.getResourceSet().getRepository().getAssignedDiscipline());
		ABeanStructuralElementInstance childbsc;

		for (int i = 0; i < sc.getChildren().size(); i++) {		
			childbsc = getChildren(sc.getChildren().get(i), structuralElementName, concept);
			bsc.add(childbsc);
		}	
		bsc.setName(sc.getName());
		// add inheritance
		bsc.getStructuralElementInstance().getSuperSeis().add(sc);
		return bsc;
	}
	
	/**
	 * Creates and returns the corresponding bean
	 * @param se the structural element 
	 * @param discipline the discipline
	 * @author bell_er
	 * @return corresponding bean
	 */
	private static ABeanStructuralElementInstance createBean(StructuralElement se, Discipline discipline) {		
		BeanStructuralElementInstanceFactory beanFactory = new BeanStructuralElementInstanceFactory();

		try {
			ABeanStructuralElementInstance beanSei = (ABeanStructuralElementInstance) beanFactory.getInstanceFor(se);
			StructuralInstantiator structuralInstantiator = new StructuralInstantiator();
			StructuralElementInstance sei = structuralInstantiator.generateInstance(se, se.getName());
			beanSei.setStructuralElementInstance(sei);
			beanSei.getStructuralElementInstance().setAssignedDiscipline(discipline);
			return beanSei;
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "failed to create bean", e));
		}
		return null;
	}

	/**
	 * @param list a list of StructuralElementInstances
	 * @param uuid the desired elements uuid
	 * @return the element inside the list which has the desired uuid
	 */
	private static StructuralElementInstance getSeibyUuid(List<StructuralElementInstance> list, VirSatUuid uuid) {
		for (StructuralElementInstance sei : list) {
			if (sei.getUuid().equals(uuid)) {
				return sei;
			}
		}
		return null;
	}

	/**
	 * @param sei a StructuralElementInstance
	 * @param list a list of StructuralElementInstances
	 * @return true if sei is in the list false otherwise
	 */
	private static boolean isContained(StructuralElementInstance sei, List<StructuralElementInstance> list) {
		return getSeibyUuid(list, sei.getUuid()) != null;
	}

	/**
	 * @param ed the editing domain
	 * @param mainSei the name of mainSei will change
	 * @param changedSei the name of changedSei will be copied to mainSei
	 * @param cmd compound command
	 */
	private static void checkName(EditingDomain ed, StructuralElementInstance mainSei, StructuralElementInstance changedSei, CompoundCommand cmd) {
		if (!mainSei.getName().equals(changedSei.getName())) {
			Command recordingCmd = new RecordingCommand((TransactionalEditingDomain) ed, "Update Name", "Updates the name of the Sei") {
				@Override
				protected void doExecute() {
					mainSei.setName(changedSei.getName());
				}
			};
			cmd.append(recordingCmd);		
		}
	}

	/**
	 * @param ed the editing domain
	 * @param mainSei our main sei in the editing domain
	 * @param changedSei copy of the mainSei
	 * @param cmd the compound command
	 */
	private static void addRemoveInheritance(EditingDomain ed, StructuralElementInstance mainSei, StructuralElementInstance changedSei, CompoundCommand cmd) {
		Command updateInheritance = new RecordingCommand((TransactionalEditingDomain) ed, "Update Inheritance Link", "Deletes the existing Links then adds the ones from the copy") {
			@Override
			protected void doExecute() {
				mainSei.getSuperSeis().clear();
				mainSei.getSuperSeis().addAll(changedSei.getSuperSeis());
			}
		};
		cmd.append(updateInheritance);
	}
	
	/**
	 * @param activeConcept the concept
	 * @param parent where to add
	 * @return the created  StructuralElementInstance
	 */
	public static StructuralElementInstance createStructuralElementInstance(Concept activeConcept, StructuralElementInstance parent) {
		String structuralElementName = parent.getType().getName();
		StructuralElement structuralElement = ActiveConceptHelper.getStructuralElement(activeConcept, structuralElementName);
		StructuralInstantiator structuralInstantiator = new StructuralInstantiator();
		StructuralElementInstance structuralElementInstance = structuralInstantiator.generateInstance(structuralElement, structuralElementName);
		structuralElementInstance.setAssignedDiscipline(parent.getAssignedDiscipline());
		return structuralElementInstance;
	}
	
	/**
	 * Duplicates a node in the tree
	 * @param sc the structural element instance to be duplicated
	 * @author bell_er
	 */
	public static void duplicate(StructuralElementInstance sc) {
		StructuralElementInstance parent = (StructuralElementInstance) sc.eContainer();
		StructuralElementInstance copy = (StructuralElementInstance) DVLMCopier.makeCopy(sc);		
		parent.getChildren().add(copy);
	}
	
	/**
	 * @param ed the editing domain
	 * @param mainRootSc the sei which will be changed	
	 * @param rootSc the copiedSei
	 */
	public static void createAndExecuteCompaundCommandForMerge(EditingDomain ed, StructuralElementInstance mainRootSc, StructuralElementInstance rootSc) {
		CompoundCommand cmd  = new CompoundCommand();

		List<StructuralElementInstance> mainRootScChildren = mainRootSc.getDeepChildren();
		List<StructuralElementInstance> rootScChildren = rootSc.getDeepChildren();
		for (StructuralElementInstance sei : mainRootScChildren) {
			if (!isContained(sei, rootScChildren)) { // Delete deleted items  if
				DeleteStructuralElementInstanceCommand deleteCommand =  DeleteStructuralElementInstanceCommand.create(ed, sei);
				cmd.append(deleteCommand);
			} else { // update existing items
				StructuralElementInstance changedSei = getSeibyUuid(rootScChildren, sei.getUuid());
				checkName(ed, sei, changedSei, cmd);
				addRemoveInheritance(ed, sei, changedSei, cmd);
			}
		}
		for (StructuralElementInstance sei : rootScChildren) { // add new items
			if (!isContained(sei, mainRootScChildren)) {	
				StructuralElementInstance parentSei = (StructuralElementInstance) sei.eContainer();
				if (parentSei.equals(rootSc)) {
					Command addCommand = CreateAddSeiWithFileStructureCommand.create(ed, mainRootSc, sei);
					cmd.append(addCommand);
				} else if (isContained(sei.getParent(), mainRootScChildren)) {
					StructuralElementInstance mainParentSei =  getSeibyUuid(mainRootScChildren, parentSei.getUuid());
					Command addCommand = CreateAddSeiWithFileStructureCommand.create(ed, mainParentSei, sei);
					cmd.append(addCommand);
				}
			} 
		}
		ed.getCommandStack().execute(cmd);
	}
}	