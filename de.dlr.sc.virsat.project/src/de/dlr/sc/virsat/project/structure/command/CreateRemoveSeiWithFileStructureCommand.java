/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.project.structure.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import de.dlr.sc.virsat.model.dvlm.resource.command.RemoveResourceCommand;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.command.DeleteStructuralElementInstanceCommand;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralElementInstanceHelper;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Class that creates a command to remove a StructuralElementInstance and its file structure
 */
public class CreateRemoveSeiWithFileStructureCommand {
	
	/**
	 * Hidden constructor
	 */
	private CreateRemoveSeiWithFileStructureCommand() {
	}
	

	/**
	 * Creates a command to remove a given StructuralElementInstance and its file structure
	 * @param seiToRemove StructuralElementInstance to remove
	 * @param deleteResourceOperation the actual abstract operation to delete the resources
	 * @return Command that removes seiToRemove and its file structure
	 */
	public static Command create(StructuralElementInstance seiToRemove, Function<IFolder,  ? extends AbstractOperation> deleteResourcesOperationFunction) {
		CompoundCommand removeSeiCommand = doCreate(seiToRemove, deleteResourcesOperationFunction);
		return removeSeiCommand;
	}

	/**
	 * Creates a command to remove multiple StructuralElementInstances and their file structures.
	 * The command makes sure that children are not deleted twice in case they are part of the selection
	 * @param seisToRemove A list of SEIs to be removed
	 * @param deleteResourceOperation the actual abstract operation to delete the resources
	 * @return Command that will delete the selection of SEIs and their children
	 */
	public static Command create(Collection<StructuralElementInstance> seisToRemove, Function<IFolder, ? extends AbstractOperation> deleteResourcesOperationFunction) {
		CompoundCommand removeAllSeisCommand = new CompoundCommand();
		ArrayList<StructuralElementInstance> seisToDelete = StructuralElementInstanceHelper.cleanFromIndirectSelectedChildren(seisToRemove);
		for (StructuralElementInstance sei : seisToDelete) {
			removeAllSeisCommand.append(CreateRemoveSeiWithFileStructureCommand.create(sei, deleteResourcesOperationFunction));
		}
	
		return removeAllSeisCommand;
	}
	
	/**
	 * Creates a command to remove a given StructuralElementInstance and its file structure
	 * @param seiToRemove StructuralElementInstance to remove
	 * @param deleteResourceOperation the actual abstract operation to delete the resources
	 * @return Command that removes seiToRemove and its file structure
	 */
	protected static CompoundCommand doCreate(StructuralElementInstance seiToRemove, Function<IFolder,  ? extends AbstractOperation> deleteResourcesOperationFunction) {
		VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(seiToRemove);
		CompoundCommand compoundCommand =  new CompoundCommand();

		if (editingDomain != null) {
			VirSatResourceSet virSatResourceSet = editingDomain.getResourceSet();
			IProject project = virSatResourceSet.getProject();
	
			List<StructuralElementInstance> seisToRemove = seiToRemove.getDeepChildren();
			seisToRemove.add(seiToRemove);
			 
			for (StructuralElementInstance iste : seisToRemove) {
				Command recordedRemoveCommand = DeleteStructuralElementInstanceCommand.create(editingDomain, iste);	
				Command removeDvlmResourcesCommand = createRemoveDvlmResourcesCommand(project, virSatResourceSet, iste);
				Command removeFilesCommand = new RemoveFileStructureCommand(project, iste, deleteResourcesOperationFunction);
				
				//compoundCommand.append(resyncSeiResourceCommand);
				compoundCommand.append(recordedRemoveCommand);
				compoundCommand.append(removeDvlmResourcesCommand);
				compoundCommand.append(removeFilesCommand);
			}
		}
		
		return compoundCommand;
	}
	
	/**
	 * Creates a compound command for removing all .dvlm* resources in the folder of the structural element instance
	 * that is to be deleted. 
	 * @param project the project
	 * @param virSatResourceSet the resource seit
	 * @param iste the structural element instance
	 * @return a compound command for removing the resources
	 */
	protected static Command createRemoveDvlmResourcesCommand(IProject project, VirSatResourceSet virSatResourceSet, StructuralElementInstance iste)  {
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(project);
		Set<Resource> emfResources = new HashSet<>();
		IFolder seiFolder = projectCommons.getStructuralElemntInstanceFolder(iste);
		IResourceVisitor dvlmResourceVisitor = new IResourceVisitor() {
			@Override
			public boolean visit(IResource resource) throws CoreException {
				if ((resource instanceof IFile) && VirSatProjectCommons.isDvlmFile((IFile) resource)) {
					URI resourceUri = URI.createPlatformResourceURI(resource.getFullPath().toString(), true);
					Resource emfResource = virSatResourceSet.getResource(resourceUri, false);
					emfResources.add(emfResource);
				}
				return true;
			}
		};
		
		try {
			seiFolder.accept(dvlmResourceVisitor);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"Could not create command to remove resource" + e.getMessage()));
		}
		
		return new RemoveResourceCommand(virSatResourceSet, emfResources);
	}
}
