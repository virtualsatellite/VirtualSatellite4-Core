/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.ReleaseUtils;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.migration.execution.MigratorRegistry;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.model.ecore.xmi.impl.DvlmXMIResourceFactoryImpl;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.dmf.DmfResource;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * This class implements the VirSat specific EMF ResourceSet. One ResourceSet is
 * associated with one project. It is not planned to have cross project
 * references. Demand loading etc. will work in one project but not outside.
 */
public class VirSatResourceSet extends ResourceSetImpl implements ResourceSet {

	// Dealing with resource problems

	/**
	 * Interface for listening to changes of the diagnostics
	 */
	public interface IDiagnosticListener {
		/**
		 * Triggered upon any resource in the resource set having its diagnostic
		 * changed
		 * 
		 * @param resource
		 *            the resource whose diagnostics have changed
		 */
		void onDiagnosticChanged(Resource resource);
	}

	private Map<Resource, Diagnostic> resourceToDiagnosticMap = new ConcurrentHashMap<Resource, Diagnostic>();
	private List<IDiagnosticListener> diagnosticListeners = new ArrayList<>();

	protected EContentAdapter problemIndicationAdapter = new EContentAdapter() {
		@Override
		public void notifyChanged(Notification notification) {
			if (notification.getNotifier() instanceof Resource) {
				int featureID = notification.getFeatureID(Resource.class);
				switch (featureID) {
					case Resource.RESOURCE__IS_LOADED:
					case Resource.RESOURCE__ERRORS:
					case Resource.RESOURCE__WARNINGS:
						Resource resource = (Resource) notification.getNotifier();
						updateDiagnostic(resource);
						notifyDiagnosticListeners(resource);
						break;
					default:
						break;
				}
			} else {
				super.notifyChanged(notification);
			}
		}

		@Override
		protected void setTarget(Resource target) {
			basicSetTarget(target);
		}

		@Override
		protected void unsetTarget(Resource target) {
			basicUnsetTarget(target);
			resourceToDiagnosticMap.remove(target);

			notifyDiagnosticListeners(target);
		}
	};

	/**
	 * This content adapter checks the EMF resources for NULL objects.
	 * Usually their content lists are free of NULL objects and don't allow to have
	 * them included. In some cases it can still happen. This class is supposed
	 * to spot these rare cases.
	 */
	protected EContentAdapter resourceNullContentAdapter = new EContentAdapter() {
		@Override
		public void notifyChanged(Notification notification) {
			if (notification.getNotifier() instanceof Resource) {
				Resource resource = (Resource) notification.getNotifier();
				// Check if these notification is triggered on the contents of a resource
				int featureID = notification.getFeatureID(Resource.class);
				int eventType = notification.getEventType();
				if (featureID == Resource.RESOURCE__CONTENTS) {
					// Check if the new value is a null object
					Object newValue = notification.getNewValue();
					if ((newValue == null) && (eventType != Notification.REMOVE)) {
						StringBuilder errorMessage = new StringBuilder();
						errorMessage.append("Found NULL object in EMF Resource\n");
						errorMessage.append("---------------------------------\n");
						String uri = resource.getURI().toPlatformString(true);
						errorMessage.append("Resource: " + uri + "\n");
						errorMessage.append("Event Type: " + eventType + "\n");
						errorMessage.append("Stacktrace:\n");
						
						for (StackTraceElement se : Thread.currentThread().getStackTrace()) {
							errorMessage.append(se.toString() + "\n");
						}
						
						// Now throw the message on the log and create a diagnostics report
						Activator.getDefault().getLog().log(
							new Status(Status.ERROR, Activator.getPluginId(), errorMessage.toString())
						);
						
						// Now where it is certain that there is something wrong with the content of the resource,
						// it is time to update the diagnostics, so that the editors will display a reasonable message
						// as well.
						updateDiagnostic(resource);
						notifyDiagnosticListeners(resource);
					}
				}
			}
			
			super.notifyChanged(notification);
		}
		
		@Override
		protected void selfAdapt(Notification notification) {
			Object object = notification.getNotifier();
			if (object instanceof ResourceSet) {
				super.selfAdapt(notification);
			}
		};

		@Override
		protected void setTarget(EObject target) {
			// Don't add adapter to eobjects
		};
		
		@Override
		protected void unsetTarget(EObject target) {
			// Adapter is not added to eobjects, thus it does not need to be removed.
		};
		
		@Override
		protected void setTarget(Resource target) {
			// Make sure that not children of the resource will get this adapter
			basicSetTarget(target);
		}

		@Override
		protected void unsetTarget(Resource target) {
			basicUnsetTarget(target);
			resourceToDiagnosticMap.remove(target);
			notifyDiagnosticListeners(target);
		}
	};
	
	/**
	 * Add the problem indication adapter, enabling this resource set to receive
	 * changes in its resources and update the diagnostics accordingly.
	 */
	public void addProblemIndicationAdapter() {
		eAdapters().add(problemIndicationAdapter);
		eAdapters().add(resourceNullContentAdapter);
	}

	/**
	 * Add a diagnostic listener
	 * 
	 * @param diagnosticListener
	 *            the diagnostic listener to be added
	 */
	public void addDiagnosticListener(IDiagnosticListener diagnosticListener) {
		diagnosticListeners.add(diagnosticListener);
	}

	/**
	 * Remove a diagnostic listener
	 * 
	 * @param diagnosticListener
	 *            the diagnostic listener to be removed
	 */
	public void removeDiagnosticListener(IDiagnosticListener diagnosticListener) {
		diagnosticListeners.remove(diagnosticListener);
	}

	/**
	 * Notify the diagnostic listeners that a change in the diagnostics of a
	 * resource has occured
	 * 
	 * @param resource
	 *            the resource whose diagnostics have changed
	 */
	public void notifyDiagnosticListeners(Resource resource) {
		for (IDiagnosticListener diagnosticListener : diagnosticListeners) {
			diagnosticListener.onDiagnosticChanged(resource);
		}
	}


	/**
	 * Gets the internal map from the resources to the diagnostics
	 * 
	 * @return the map giving to each resource the associated diagnostics if
	 *         existent
	 */
	public Map<Resource, Diagnostic> getResourceToDiagnosticsMap() {
		return resourceToDiagnosticMap;
	}

	/**
	 * Clear all registered ResourceSets
	 */
	public static void clear() {
		VirSatEditingDomainRegistry.INSTANCE.clear();
	}

	/**
	 * this creates some unmanaged VirSatResource Set which will not cause any
	 * UI changes It is created for our invocation of builders and validation of
	 * the model.
	 * 
	 * @param project
	 *            the project to create a resource set for
	 * @return the specialized unmanaged VirSatResourceSet
	 */
	public static VirSatResourceSet createUnmanagedResourceSet(IProject project) {
		return new VirSatResourceSet(project);
	}

	private boolean needsMigration;

	/**
	 * Boolean if resource set is opened, that is the associated project is
	 * opened and the resource set contains data that is up-to date with the
	 * DVLM model
	 * 
	 * @return true iff the resource set is considered open
	 */

	public boolean isOpen() {
		return !needsMigration && project.isOpen();
	}

	/**
	 * This listener tracks the closing of the associated project.
	 */
	private IResourceChangeListener projectClosedListener = new IResourceChangeListener() {
		public void resourceChanged(IResourceChangeEvent event) {
			// Step out if event seems to be corrupt
			if (event == null || event.getDelta() == null) {
				return;
			}

			// Otherwise give it a try and see if some project got opened
			try {
				event.getDelta().accept(new IResourceDeltaVisitor() {
					public boolean visit(IResourceDelta delta) throws CoreException {
						if (delta.getResource() == project && !project.isOpen()) {
							ResourcesPlugin.getWorkspace().removeResourceChangeListener(projectClosedListener);
							VirSatEditingDomainRegistry.INSTANCE.removeEd(project);
						}

						return true;
					}
				});
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(),
						"Tried to get rid of loaded project. It is possible that there was no Editing Domain existing!"));
			}
		}
	};

	private static boolean xmiExtensionInitialized = false;

	/**
	 * Static method to get or create a ResourceSet for a project
	 * 
	 * @param project the project for which to get the ResourceSet
	 * @return the ResourceSet which is already present or a new one if needed.
	 */
	public static VirSatResourceSet getResourceSet(IProject project) {
		return getResourceSet(project, true);
	}
	
	/**
	 * Static method to get or create a ResourceSet for a project
	 * 
	 * @param project the project for which to get the ResourceSet
	 * @param handleExternalWorkspaceChanges if true, a listener will check for external workspace changes
	 * and react to synchronize the workspace e.g. by means of reloads. Note: Most unit test cases probably
	 * want this flagged turn off! Otherwise if they do something without the editing domain it might trigger
	 * an unwanted reload of resources.
	 * @return the ResourceSet which is already present or a new one if needed.
	 */
	public static VirSatResourceSet getResourceSet(IProject project, boolean handleExternalWorkspaceChanges) {
		// Initialize the projects map
		if (!xmiExtensionInitialized) {
			// Setting up the resources factory to deal with the model extension
			Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
			Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
			m.put(VirSatProjectCommons.FILENAME_EXTENSION, new DvlmXMIResourceFactoryImpl());

			xmiExtensionInitialized = true;
		}

		// Get the resourceSet for the given project
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
		VirSatResourceSet resourceSet = null;
		if (ed == null) {
			resourceSet = new VirSatResourceSet(project);
			resourceSet.addProblemIndicationAdapter();

			ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceSet.projectClosedListener);

			boolean needsMigration = resourceSet.checkMigration();
			if (needsMigration) {
				IExtensionRegistry registry = Platform.getExtensionRegistry();
				IConfigurationElement[] elements = registry
						.getConfigurationElementsFor(IResourceMigratorQuestion.EXTENSION_POINT_ID);
				// We assume we always want to migrate. Only in the case the
				// user sais no, we don't
				boolean shallMigrate = true;

				try {
					// Try to see if we can post a question to the user. Get the
					// first registered Question
					// and ask the user
					IResourceMigratorQuestion resourceMigrator = (IResourceMigratorQuestion) elements[0]
							.createExecutableExtension("class");
					shallMigrate = resourceMigrator.questionMigration(project);
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR,
							"Failed to question the user if we should migarte the DataSet", e));
				}

				// now check if we shall do the migration and just do it!
				if (shallMigrate) {
					resourceSet.performMigration(new NullProgressMonitor());
				} else {
					// In case the data set needs a migration but it has not
					// been performed,
					// the project needs to be closed, so builders don't build
					// it anymore, etc.
					try {
						project.close(new NullProgressMonitor());
					} catch (CoreException e) {
						Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR,
								"Failed to close non migrated project", e));
					}
				}
			}

			ed = VirSatEditingDomainRegistry.INSTANCE.createEd(project, resourceSet);
			if (!handleExternalWorkspaceChanges) {
				ed.stopWorkspaceChangeListener();
			}
		} else {
			resourceSet = ed.getResourceSet();
		}

		return resourceSet;
	}

	/**
	 * Checks if the file at the specified URI requires migration, that is, its
	 * meta-model is of an older release version. If we do not have the latest
	 * release, perform the migration.
	 * 
	 * @return true iff migration is required
	 */
	public boolean checkMigration() {
		needsMigration = false;

		// Use the repository to determine whether we need to migrate or not
		// since we always
		// have a repository and all files in one project should be of the same
		// DVLM version
		URI repositoryUri = URI.createPlatformResourceURI(projectCommons.getRepositoryFile().getFullPath().toString(),
				true);

		String nsURI = ReleaseUtils.getNamespaceURI(repositoryUri);
		final Migrator migrator = MigratorRegistry.getInstance().getMigrator(nsURI);
		if (migrator != null) {
			final Release release = migrator.getRelease(repositoryUri).iterator().next();
			if (!release.isLatestRelease()) {
				needsMigration = true;
			}
		}

		return needsMigration;
	}

	/**
	 * This method calls the actual migration
	 * 
	 * @param monitor
	 *            progress monitor
	 * @return true iff migration was successful
	 */
	public boolean performMigration(IProgressMonitor monitor) {

		URI repositoryUri = URI.createPlatformResourceURI(projectCommons.getRepositoryFile().getFullPath().toString(),
				true);
		String nsURI = ReleaseUtils.getNamespaceURI(repositoryUri);
		Migrator migrator = MigratorRegistry.getInstance().getMigrator(nsURI);
		Release release = migrator.getRelease(repositoryUri).iterator().next();
		try {
			migrator.migrateAndSave(Collections.singletonList(repositoryUri), release, null, monitor);
			needsMigration = false;
			return true;
		} catch (MigrationException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR,
					"Failed to migrate a VirSatResourceSet", e));
		}

		return false;
	}

	private VirSatProjectCommons projectCommons;
	protected Map<Object, Object> saveOptions = new HashMap<Object, Object>();

	/**
	 * Constructor for the ResourceSet with a given project. A ResourceSet is
	 * bound to exactly one project
	 * 
	 * @param project
	 *            The project to which this resourceSet is bound.
	 */
	protected VirSatResourceSet(IProject project) {
		this.project = project;
		this.projectCommons = new VirSatProjectCommons(project);

		checkMigration();

		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		saveOptions.put(Resource.OPTION_LINE_DELIMITER, Resource.OPTION_LINE_DELIMITER_UNSPECIFIED);
		saveOptions.put(XMIResource.OPTION_PROCESS_DANGLING_HREF, XMIResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
	}

	private IProject project;

	/**
	 * Access the project resource to which this ResourceSet is bound to.
	 * 
	 * @return the project workspace resource
	 */
	public IProject getProject() {
		return project;
	}

	/**
	 * Method to receive the named resource
	 * 
	 * @return the EMF resource which is stored in this ResourceSet
	 */
	public Resource getRepositoryResource() {
		return getRepositoryResource(true);
	}

	/**
	 * Method to receive the named resource
	 * 
	 * @param createResource
	 *            set to to true if the resource should be created in case it
	 *            does not yet exist
	 * @return the EMF resource which is stored in this ResourceSet
	 */
	public Resource getRepositoryResource(boolean createResource) {
		IFile fileResource = projectCommons.getRepositoryFile();
		return safeGetResource(fileResource, createResource);
	}

	/**
	 * Method to receive the named resource
	 * 
	 * @return the EMF resource which is stored in this ResourceSet
	 */
	public Resource getRoleManagementResource() {
		return getRoleManagementResource(true);
	}

	/**
	 * Method to receive the named resource
	 * 
	 * @param createResource
	 *            set to to true if the resource should be created in case it
	 *            does not yet exist
	 * @return the EMF resource which is stored in this ResourceSet
	 */
	public Resource getRoleManagementResource(boolean createResource) {
		IFile fileResource = projectCommons.getRoleManagementFile();
		return safeGetResource(fileResource, createResource);
	}

	/**
	 * Method to receive the named resource
	 * 
	 * @return the EMF resource which is stored in this ResourceSet
	 */
	public Resource getUnitManagementResource() {
		return getUnitManagementResource(true);
	}

	/**
	 * Method to receive the named resource
	 * 
	 * @param createResource
	 *            set to to true if the resource should be created in case it
	 *            does not yet exist
	 * @return the EMF resource which is stored in this ResourceSet
	 */
	public Resource getUnitManagementResource(boolean createResource) {
		IFile fileResource = projectCommons.getUnitManagementFile();
		return safeGetResource(fileResource, createResource);
	}

	/**
	 * Method to create or access a given resource for a given
	 * StructuralElementInstance
	 * 
	 * @param sei
	 *            The StructuralElementInstance for which to get the
	 *            corresponding resource for
	 * @return the new or existing resource.
	 */
	public Resource getStructuralElementInstanceResource(StructuralElementInstance sei) {
		IFile fileResource = projectCommons.getStructuralElementInstanceFile(sei);
		return safeGetResource(fileResource, true);
	}

	/**
	 * Call this method to get a new resource for a given SEI and directly add
	 * it to the contents of the new resource
	 * 
	 * @param sei
	 *            the sei for which to create a new resource
	 * @return the resource with the embedded SEI.
	 */
	public Resource getAndAddStructuralElementInstanceResource(StructuralElementInstance sei) {
		Resource res = getStructuralElementInstanceResource(sei);
		res.getContents().add(sei);
		return res;
	}

	/**
	 * Method to create or access a given resource for a given
	 * StructuralElementInstance
	 * 
	 * @param sei
	 *            The StructuralElementInstance for which to get the
	 *            corresponding resource for
	 * @return the new or existing resource.
	 */
	public boolean removeStructuralElementInstanceResource(StructuralElementInstance sei) {
		IFile fileResource = projectCommons.getStructuralElementInstanceFile(sei);
		Resource resource = safeGetResource(fileResource, true);
		// Since we are unloading the resource in the next step, we have to take
		// out the EObjects first
		// otherwise they will be put into a Proxy state, which may create
		// problems during UNDO / REDO
		// operations. We unload the resource as a signal for defining the dirty
		// state of resources
		// The dirty state is calculated by really looking to the contents of
		// the resource. The isLoaded flag
		// will tell us if we can read the resource or if we should skip,
		// because it is closed and removed anyway
		boolean removedFolders = projectCommons.removeFolderStructure(sei, null);
		boolean removedResource = getResources().remove(resource);
		return removedFolders & removedResource;
	}

	/**
	 * Safe method to return the EMF resource stored in an IFile workspace
	 * Resource If the File does not exist the resource gets created. In case it
	 * exists it will just be opened. The method checks if the resource maybe
	 * just exists in the memory within the ResourceSet
	 * 
	 * @param fileResource
	 *            the FileResource to be opened
	 * @param forceCreate
	 *            only when set to true, the resource will be created, if set to
	 *            false the method will only try to load from file system or
	 *            hand back the in memory version of the resource
	 * @return the EMF resource either created or opened from the given file
	 */
	public Resource safeGetResource(IFile fileResource, boolean forceCreate) {
		URI fileUri = URI.createPlatformResourceURI(fileResource.getFullPath().toString(), true);
		Resource resource = null;

		if (fileResource.exists()) {
			resource = this.getResource(fileUri, true);
		} else {
			// Ok, we know that we do not have a file yet. So apparently it is a
			// new resource. But calling all the time create will always hand back a new
			// resource and confuse other parts of the application. To avoid this
			// we check if there is a resource already created under the given name
			// in case it is we ill use that one instead of creating a complete new one.
			resource = getAlreadyCreatedResource(fileUri);
			if (resource == null && forceCreate) {
				resource = this.createResource(fileUri);
				// Do an initial safe to actually create the file on the file
				// system Otherwise we only have the file structures created by the
				// VirSatProjectCommons
				saveResource(resource, null, true);
			}
		}

		try {
			fileResource.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR,
					"Failed to create/open resource " + e.getMessage(), e));
		}
		return resource;
	}
	
	/**
	 * Convenience method to get an EMF resource from an eclipse IResource
	 * 
	 * @param file The file as IResource
	 * @param loadOnDemand weather the resource should be created if not yet in the resource set
	 * @return the resource or null if not existing
	 */
	public Resource getResource(IResource file, boolean loadOnDemand) {
		if (file instanceof IFile) {
			URI fileUri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			return this.getResource(fileUri, loadOnDemand);
		}
		return null;
	}

	/**
	 * This method checks if there is some kind of resource loaded or not,
	 * persisted or not which is in the resource set. If it finds one with the
	 * given uri it will be returned.
	 * 
	 * @param fileUri
	 *            The filUri for which to find a resource
	 * @return the resource in case it exists otherwise null
	 */
	private Resource getAlreadyCreatedResource(URI fileUri) {
		for (Resource resource : getResources()) {
			URI uri = resource.getURI();
			if (uri.equals(fileUri)) {
				return resource;
			}
		}
		return null;
	}
	
	/**
	 * Get a list of all DVLM resources
	 * @return the list of DVLM resources
	 */
	public List<Resource> getDvlmResources() {
		List<Resource> dvlmResources = new ArrayList<Resource>();
		for (Resource resource : getResources()) {
			if (VirSatProjectCommons.isDvlmFile(resource)) {
				dvlmResources.add(resource);
			}
		}
		return dvlmResources;
	}
		

	/**
	 * Method to receive the named core object
	 * 
	 * @return the Object which is stored in the given resource
	 */
	public Repository getRepository() {
		Resource resource = getRepositoryResource(false);
		if ((resource != null) && !resource.getContents().isEmpty()) {
			return (Repository) resource.getContents().get(0);
		}
		return null;
	}

	/**
	 * Method to receive the named core object
	 * 
	 * @return the Object which is stored in the given resource
	 */
	public RoleManagement getRoleManagement() {
		Resource resource = getRoleManagementResource(false);
		if ((resource != null) && !resource.getContents().isEmpty()) {
			return (RoleManagement) resource.getContents().get(0);
		}
		return null;
	}

	/**
	 * Method to receive the named core object
	 * 
	 * @return the Object which is stored in the given resource
	 */
	public UnitManagement getUnitManagement() {
		Resource resource = getUnitManagementResource(false);
		if ((resource != null) && !resource.getContents().isEmpty()) {
			return (UnitManagement) resource.getContents().get(0);
		}
		return null;
	}

	/**
	 * the method getAllCategoryAssignments crawls through the whole project and gathers all CategoryAssignments.
	 * It also resolves all proxy elements in our project
	 * @return a list of CategoryAssignments
	 */
	public Set<StructuralElementInstance> getAllSeisInProject() {
		Set<StructuralElementInstance> allSeis = new HashSet<>();
		
		try {
			if (!isOpen()) {
				return allSeis;
			}
			
			Repository repo = getRepository();
			loadAllDvlmResources();
			
			EcoreUtil.getAllContents(this, true).forEachRemaining((object) -> {
				if (object instanceof StructuralElementInstance) {
					StructuralElementInstance sei = (StructuralElementInstance) object;
					EObject rootEObject = EcoreUtil.getRootContainer(sei, true);
					boolean isRepoComponent = repo.getRootEntities().contains(rootEObject);
					// Root components are not contained in the Repository, but here we want
					// to find the components which may have a dangling reference. This happens
					// sometimes when deleting a component
					if (isRepoComponent) {
						allSeis.add(sei);
					}
				}
			});
			
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "Could not get seis from resourceSet", e));
		}
	
		return allSeis;
	};
	
	/**
	 * This Method initializes the core resources needed for a virsat data
	 * model. the data model objects are attached to their respective Resource
	 * and nested into one project specific resource set.
	 * 
	 * @param pm
	 *            the IProgressMonitor
	 * @param ed
	 *            the TransactionalEditingDomain
	 * @return Command the aggregated command with several steps to initialize
	 *         our resources in a project
	 */
	public Command initializeModelsAndResourceSet(IProgressMonitor pm, TransactionalEditingDomain ed) {
		return new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				VirSatResourceSet.this.initializeModelsAndResourceSet();
			}
		};
	}

	/**
	 * Call this method to initialize a complete ResourceSet for a given project
	 * without having the recording command and all extra bits
	 */
	public void initializeModelsAndResourceSet() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.INFO,
				"VirSatResourceSet: Started initializing Model and ResourceSet", null));

		Repository repository = DVLMFactory.eINSTANCE.createRepository();

		UnitManagement unitManagement = UnitsFactory.eINSTANCE.createUnitManagement();
		RoleManagement roleManagement = RolesFactory.eINSTANCE.createRoleManagement();

		unitManagement.setSystemOfUnit(QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU",
				"This is the system of units for this study", "N/A"));

		Discipline systemDiscipline = RolesFactory.eINSTANCE.createDiscipline();
		systemDiscipline.setName("System");

		IUserContext userRegistry = UserRegistry.getInstance();
		String currentUserName = userRegistry.getUserName();
		systemDiscipline.setUser(currentUserName);

		roleManagement.getDisciplines().add(systemDiscipline);

		repository.setRoleManagement(roleManagement);
		repository.setUnitManagement(unitManagement);

		// Assign the model elements to their respective resources
		getRepositoryResource().getContents().add(repository);
		getUnitManagementResource().getContents().add(unitManagement);
		getRoleManagementResource().getContents().add(roleManagement);

		repository.setAssignedDiscipline(systemDiscipline);
		unitManagement.setAssignedDiscipline(systemDiscipline);
		roleManagement.setAssignedDiscipline(systemDiscipline);

		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.INFO,
				"VirSatResourceSet: Finished initializing Model and ResourceSet", null));
	}

	/**
	 * Initializing the new StructuralElementInstance means writing it to its
	 * resource
	 * 
	 * @param sei
	 *            the StructuralElementInstance which should be used for
	 *            initialization
	 */
	public void initializeStructuralElementInstance(StructuralElementInstance sei) {
		Resource resource = getStructuralElementInstanceResource(sei);
		resource.getContents().add(sei);
	}

	/**
	 * Method to reload all resources currently in the resourceSet
	 */
	public void realoadAll() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.INFO,
				"VirSatResourceSet: Reloading All resources)", null));

		// Take a copy of the resources since a demand load of a resource
		// which is not yet part of the resource set may modify the list of
		// contents
		// thus we might run into a concurrent modification exception
		Collection<Resource> resources = new ArrayList<>(getResources());
		resources.forEach((resource) -> {
			resource.unload();
			resourceToDiagnosticMap.remove(resource);
		});

		resources.forEach((resource) -> {
			try {
				resource.load(Collections.EMPTY_MAP);
				updateDiagnostic(resource);
			} catch (IOException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
						"VirSatResourceSet: Failed to reload Resource " + e.getMessage()));
			}
		});
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.INFO,
				"VirSatResourceSet: Finished Reloading All resources)", null));
	}

	/**
	 * This method reloads a resource with the standard VirSat settings. at the
	 * moment they are an empty map
	 * 
	 * @param resource
	 *            the resource to be reloaded
	 * @return the resource that has been reloaded
	 */
	public Resource reloadResource(Resource resource) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.INFO,
				"VirSatResourceSet: Reloading resource (" + resource.getURI().toPlatformString(true) + ")", null));
		if (resource.isLoaded()) {
			resource.unload();
			resourceToDiagnosticMap.remove(resource);
			try {
				resource.load(Collections.EMPTY_MAP);
				updateDiagnostic(resource);
			} catch (IOException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
						"VirSatResourceSet: Failed to reload Resource " + e.getMessage()));
			}
		}
		return resource;
	}

	@Override
	protected void demandLoad(Resource resource) throws IOException {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.INFO,
				"VirSatResourceSet: Demand load resource (" + resource.getURI().toPlatformString(true) + ")", null));
		super.demandLoad(resource);
	}

	@Override
	protected Resource demandCreateResource(URI uri) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.INFO,
				"VirSatResourceSet: Demand create resource (" + uri.toPlatformString(true) + ")", null));
		return super.demandCreateResource(uri);
	}

	/**
	 * Use this method to save a resource in this resource set
	 * 
	 * @param resource the resource to be saved
	 * @param userContext The user context to determine write permissions
	 */
	public void saveResource(Resource resource, IUserContext userContext) {
		saveResource(resource, userContext, false);
	}

	/**
	 * Method to remove a resource from the resource set and to remove it from
	 * the diagnostics ass well
	 * 
	 * @param resource
	 *            the resource to be removed
	 * @return the resource that was just removed
	 */
	public Resource removeResource(Resource resource) {
		if (resource != null) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.INFO,
					"VirSatResourceSet: Removing resource for URI (" + resource.getURI().toPlatformString(true) + ")",
					null));
		
			getResources().remove(resource);
			resourceToDiagnosticMap.remove(resource);
		}
		return resource;
	}

	/**
	 * Use this method to save a resource in this resource set
	 * 
	 * @param resource
	 *            the resource to be saved
	 * @param overrideWritePermissions
	 *            set this flag to allow for saving the resource. E.g. when
	 *            changing the assigned discipline
	 */
	public void saveResource(Resource resource,  IUserContext userContext, boolean overrideWritePermissions) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(),
				"VirSatResourceSet: Started saving Resource (" + resource.getURI().toPlatformString(true) + ") for Project (" + project.getName() + ")"));

		if (!resource.isLoaded()) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(),
					"VirSatResourceSet: Attempted to save unloaded resource (" + resource.getURI().toPlatformString(true) + ")"));
		}

		try {
			// Only save the resource if we actually have the right to do this.
			if (overrideWritePermissions || hasWritePermission(resource, userContext)) {
				resource.save(saveOptions);
			}
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR,
					"Failed to save Resource " + e.getMessage(), e));
		}
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(),
				"VirSatResourceSet: Finished saving Resource (" + resource.getURI().toPlatformString(true) + ") for Project (" + project.getName() + ")"));
	}

	/**
	 * Use this method to assign a new discipline to a resource / eObject. The
	 * method will force a save on the resource to persist the newly assigned
	 * discipline
	 * 
	 * @param disciplineContainer
	 *            the eObject containing the assigned discipline to be changed
	 * @param discipline
	 *            the new discipline to be set
	 */
	public void assignDiscipline(IAssignedDiscipline disciplineContainer, Discipline discipline) {
		boolean hasWritePermission = RightsHelper.hasSystemUserWritePermission(disciplineContainer);

		if (hasWritePermission) {
			disciplineContainer.setAssignedDiscipline(discipline);
			Resource resource = disciplineContainer.eResource();
			VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(discipline);
			ed.saveResourceIgnorePermissions(resource);
		}
	}

	/**
	 * Use this method to find out if you have write access to the given EMF
	 * resource
	 * 
	 * @param resource The resource which should be checked for write access
	 * @return true in case write access is given otherwise false
	 */
	public boolean hasWritePermission(Resource resource, IUserContext userContext) {
		boolean hasWritePermission = true;
		if (!resource.getContents().isEmpty()) {
			EObject eObject = resource.getContents().get(0);
			if (eObject instanceof IAssignedDiscipline) {
				hasWritePermission = RightsHelper.hasWritePermission(eObject, userContext);
			}
		}
		return hasWritePermission;
	}

	/**
	 * Method to save all resources in the current ResourceSet
	 * 
	 * @param pm
	 *            the progress monitor to track the progress of the save
	 *            operation
	 */
	public void saveAllResources(IProgressMonitor pm, IUserContext userContect) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(),
				"VirSatResourceSet: Started saving all resources for Project (" + project.getName() + ")"));
		for (Resource resource : this.getResources()) {
			if (!resource.getContents().isEmpty()) {
				saveResource(resource, userContect);
			}
		}
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(),
				"VirSatResourceSet: Finished saving all resources for Project (" + project.getName() + ")"));
	}

	/**
	 * Method to check if some resource in this resource set has an error
	 * 
	 * @return true iff all resources are error free
	 */
	public boolean hasError() {
		for (Resource resource : this.getResources()) {
			if (!resource.getErrors().isEmpty()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if a resource has been changed by performing a byte-by-byte
	 * comparison. Method copied from: ResourceImpl in the EMF library. NOTE:
	 * Support for compression using ZIP streams has been removed since carrying
	 * this over would be a pain and we dont use it right now.
	 * 
	 * @param resource
	 *            the resource which will be checked if it is different than the
	 *            one currently saved on the filesystem
	 * @return true iff the resource on the file system is different than the
	 *         passed in memory resource
	 */
	public boolean isChanged(Resource resource) {
		if (resource instanceof DmfResource) {
			return false;
		} else {
			boolean isChanged = VirSatResourceSetUtil.isChanged(resource, saveOptions, loadOptions);
			return isChanged;
		}
	}

	/**
	 * This method hands back a VirSatResourceSet for an eobject or null in case
	 * there iss none
	 * 
	 * @param eObject
	 *            the object for which to get the managing VirSatResourceSet
	 * @return null in case it could not be found
	 */
	public static VirSatResourceSet getVirSatResourceSet(EObject eObject) {
		Resource resource = eObject.eResource();
		if (resource != null) {
			ResourceSet resourceSet = resource.getResourceSet();
			if ((resourceSet != null) && (resourceSet instanceof VirSatResourceSet)) {
				return (VirSatResourceSet) resourceSet;
			}
		}
		return null;
	}

	/**
	 * Call this method to properly destroy and unregister this resourceSet from
	 * its maps and listeners. This method also unregisters this resourceSet
	 * from the EditingDomainRegistration
	 */
	public void dispose() {
		eAdapters().remove(problemIndicationAdapter);
		VirSatEditingDomainRegistry.INSTANCE.removeEd(project);
	}

	@Override
	public Resource getResource(URI uri, boolean loadOnDemand) {
		URI uriWithoutFragment = uri.trimFragment();
		Resource resource = super.getResource(uriWithoutFragment, loadOnDemand);
		return resource;
	}

	@Override
	public Resource createResource(URI uri) {
		URI uriWithoutFragment = uri.trimFragment();
		Resource resource = super.createResource(uriWithoutFragment);
		updateDiagnostic(resource);
		return resource;
	}

	/**
	 * Updates the resources diagnostic entry in the resourceToDiagnosticMap
	 * 
	 * @param resource the resource of which to update the diagnostic
	 * @return true in case that the resource to diagnostics map has been changed
	 */
	public boolean updateDiagnostic(Resource resource) {
		boolean changes = false;
		// Only run diagnostic on DVLM files as other resource might result in a huge chain of
		// resource that have to be resolved (e.g. diagrams also have references to the diagram infrastructure...)
		if (resource != null && VirSatProjectCommons.isDvlmFile(resource)) {
			// Run individualDiagnostics and merge them
			
			BasicDiagnostic resourceDiagnostic = analyzeResourceProblems(resource);
			BasicDiagnostic resourceNullDiagnostic = analyzeResourceNullProblems(resource);
			resourceDiagnostic.merge(resourceNullDiagnostic);
			// In case there are null objects which should not be in the resource
			// something went seriously wrong. No other analysis has to be executed
			// If there are no null diagnostics, continue with the other checks
			if (resourceDiagnostic.getSeverity() == Diagnostic.OK) {
				BasicDiagnostic modelDiagnostic = analyzeModelProblems(resource);
				resourceDiagnostic.merge(modelDiagnostic);
			}

			// Now check severity levels, and set the diagnostics map accordingly.
			// The map will be used by the editors to display the error messages etc.
			if (resourceDiagnostic.getSeverity() != Diagnostic.OK) {
				resourceToDiagnosticMap.put(resource, resourceDiagnostic);
				changes = true;
				Activator.getDefault().getLog().log(new Status(
					Status.INFO,
					Activator.getPluginId(),
					"VirSatResourceSet: Current Diagnostic Map adding Resource (" + resource.getURI().toPlatformString(true) + ")"
				));
			} else {
				if (resourceToDiagnosticMap.containsKey(resource)) {
					Activator.getDefault().getLog().log(new Status(
						Status.INFO,
						Activator.getPluginId(),
						"VirSatResourceSet: Current Diagnostic Map removing Resource (" + resource.getURI().toPlatformString(true) + ")"
					));
					resourceToDiagnosticMap.remove(resource);
					changes = true;
				}
			}
		}
		return changes;
	}

	/**
	 * Returns a diagnostic describing the errors and warnings listed in the
	 * resource and the specified exception (if any).
	 * 
	 * @param resource the resource
	 * @return the diagnostic describing error/warning
	 */
	public BasicDiagnostic analyzeResourceProblems(Resource resource) {
		boolean hasErrors = !resource.getErrors().isEmpty();
		boolean hasWarnings = !resource.getWarnings().isEmpty();
		BasicDiagnostic returnDiagnostic = VirSatEcoreUtil.createDiagnosticOk("Resource Diagnostics:");
		
		// Now process the diagnostics that are part of the resource
		if (hasErrors || hasWarnings) {
			returnDiagnostic.merge(new BasicDiagnostic(
				hasErrors ? Diagnostic.ERROR : Diagnostic.WARNING,
				Activator.getPluginId(), 0,
				"Problems encountered in resource: " + resource.getURI().toPlatformString(true),
				new Object[] { resource }
			));
			
			// Compute the diagnostics from the resource and add them to the given one.
			returnDiagnostic.merge(EcoreUtil.computeDiagnostic(resource, true));
		} 
		
		return returnDiagnostic;
	}

	/**
	 * Analyzes the resource on special null errors. We have seen some rare cases,
	 * where NPEs were caused by resource content which was null. Usually this should
	 * never happen in EMF, still this check will report in case such issues may happen again
	 * @param resource the resource to be checked
	 * @return The diagnostics regarding null content in a resource
	 */
	public BasicDiagnostic analyzeResourceNullProblems(Resource resource) {
		List<EObject> resourceContents = resource.getContents();
		BasicDiagnostic returnDiagnostic = VirSatEcoreUtil.createDiagnosticOk("Resource Null Diagnostics:");
		
		// Loop over the contents to detect a null
		for (EObject object : resourceContents) {
			if (object == null) {
				// Add a diagnostic
				returnDiagnostic.merge(new BasicDiagnostic(
						Diagnostic.ERROR,
						Activator.getPluginId(), 0,
						"Error! Found NULL object in resource content: " + resource.getURI().toPlatformString(true),
						new Object[] { resource }
					));
				
				// Log the result
				Activator.getDefault().getLog().log(new Status(
					Status.ERROR,
					Activator.getPluginId(),
					"Found NULL object in Resource content: " + resource.getURI().toPlatformString(true)
				));
				
				// don't test the other objects anymore. One diagnostic about a null object is enough
				break;
			}
		}
		
		return returnDiagnostic;
	}
	
	/**
	 * This method creates EMF Diagnostics on the Model Object in case of detected problems
	 * 
	 * @param resource The resource to be checked for model issues
	 * @return the diagnostic on model issues
	 */
	public BasicDiagnostic analyzeModelProblems(Resource resource) {
		BasicDiagnostic returnDiagnostic = VirSatEcoreUtil.createDiagnosticOk("Model Diagnostics:");
		
		// Now check the state of each eObject if it can be resolved and if it is contained
		EcoreUtil.getAllProperContents(resource, true).forEachRemaining((object) -> {
			if (object instanceof EObject) {
				EObject eObject = (EObject) object;

				// Check all cross references
				eObject.eCrossReferences().forEach((eReferencedObject) -> {
					// Check containment of all other objects
					if (eReferencedObject.eResource() == null) {
						returnDiagnostic.merge(new BasicDiagnostic(
								Diagnostic.WARNING,
								Activator.getPluginId(),
								0,
								"Found a dangling reference due to an uncontained object. Press save to fix.",
								new Object[] { eReferencedObject, eObject }
						));
					}
				});
			}
		});
	
		return returnDiagnostic;
	}
	
	/**
	 * Call this method to load the repository and resolve all connected resources 
	 */
	public void loadAllResources() {
		// Get the repository first, so that the most important resource is loaded
		// all other resources should be referenced by this resource as an entry point.
		getRepositoryResource();
		EcoreUtil.resolveAll(this);
	}
	
	/**
	 * Load and resolve all DVLM resources in this resource set
	 */
	public void loadAllDvlmResources() {
		for (Resource resource : getResources()) {
			if (VirSatProjectCommons.isDvlmFile(resource)) {
				EcoreUtil.resolveAll(resource);
			}
		}
	}
}
