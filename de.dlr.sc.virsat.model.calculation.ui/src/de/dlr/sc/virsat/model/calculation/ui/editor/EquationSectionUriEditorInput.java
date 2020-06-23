/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.ui.editor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.util.StringInputStream;

import de.dlr.sc.virsat.model.calculation.resource.EquationSectionVirSatAwareXtextResourceSet;
import de.dlr.sc.virsat.model.calculation.resource.IEquationSectionProvider;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.Activator;

/**
 * This input handles the correct matching of the loaded DVLM file
 * to its contents of the equations. The DVLM file contains exactly
 * one structural element instance by convention. AT the moment we 
 * develop that each StructuralElementInstance contains equations
 * This class uses a file-proxy to explain the Xtext editor, that it
 * opens a file, but the actual method calls to the file interface 
 * are intercepted, and for example redirected for the file content.
 * That call actually triggers a  Xtext Calculation language serialization
 * of the equations of the StructuralElementInstance and hands them
 * back to the opened Xtext editor.
 * 
 * @author fisc_ph
 *
 */
public class EquationSectionUriEditorInput extends FileEditorInput implements IPersistableElement, IEquationSectionProvider {
	
	private URI uri;
	
	/**
	 * To construct an EditorInput that can load the Equations object from an EquationSectionContainer.
	 * Actually this EditorInput is file based but also works on the URI. XtextEditors seem to need a file
	 * no matter what otherwise they seem to operate incorrectly. It may happen in the future, that the file
	 * provided here is just sued for temporary purposes. But this is not yet implemented.
	 * @param resourceFile the file in which the EquationSectionContaienr is stored/persisted
	 * @param uri The URI of the actual EquationSectionContainer which can be the URI plus the fragment.
	 */
	public EquationSectionUriEditorInput(IFile resourceFile, URI uri) {
		super(resourceFile);
		this.resourceFile = resourceFile;
		this.uri = uri;
		
		try {
			// Create the proxy for the file
			proxyFile = createFileProxy();
			
			// Now we need to read the EquationSection object from the container resource
			equationSectionContainer = getEquationSectionContainerForUri();
			equationSection = equationSectionContainer.getEquationSection();
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private VirSatTransactionalEditingDomain editingDomain;
	private IEquationSectionContainer equationSectionContainer;
	private EquationSection equationSection;
	private VirSatResourceSet virSatResourceSet;
	private Resource virSatResource;
	private IFile resourceFile;
	private IFile proxyFile;
	
	/**
	 * method to retrieve the current ResourceSet which belongs to the project
	 * in which this EditorInput points to.
	 * @return the VirSatResourceSet
	 */
	public VirSatResourceSet getVirSatResourceSet() {
		return virSatResourceSet;
	}
	
	/**
	 * method to retrieve the current Resource which belongs to the project
	 * in which this EditorInput points to.
	 * @return the VirSatResourceSet
	 */
	public Resource getVirSatResource() {
		return virSatResource;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EquationSectionUriEditorInput)) {
			return false;
		}
		EquationSectionUriEditorInput other = (EquationSectionUriEditorInput) obj;
		return uri.equals(other.getUri());
	}
	
	/**
	 * Hands back the URI of this EditorInput
	 * @return the current URI
	 */
	private URI getUri() {
		return uri;
	}

	@Override
	public int hashCode() {
		return (HASH_CODE_PREFIX + uri.toString()).hashCode();
	}
	
	private static final String HASH_CODE_PREFIX = "EquationSectionEditorInput:";
	
	@Override
	public String getName() {
		try {
			if (equationSectionContainer instanceof IName) {
				IName name = (IName) equationSectionContainer;
				return "Equations of " + name.getName();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return "Equations of Unresolved";
	}
	
	@Override
	public IFile getFile() {
		return proxyFile;
	}
	
	/**
	 * Call this method to find out if the resource itself is in a dirty state.
	 * We will need this information to correctly store the XtextEditor in case it is saved
	 * The equations are a subset of the total XMI model. Thus when the Xtext editor saves
	 * it will update the part of the EMF DVLM model of e.g. a StructuralElementInstance
	 * and then it needs to hit the save functionality through the editing domain
	 * @return true in case the resource is considered dirty
	 */
	public boolean isDirtyResource() {
		return editingDomain.isDirty(virSatResource);
	}
	
	/**
	 * This method accesses the ResourceSet which belongs to the resourceFile
	 * Associated to this EditorInput. From this file the resource set as well
	 * as the final resource is accessed to load it and ask it for its content.
	 * This method should get called regularly to load directly from the resource
	 * and for not missing resource updates and reloads, which are handled by the
	 * VirSat Resource Set. This happens for example if a resource is changed in
	 * a text editor directly in the XMI.
	 * @return The EquationSectionCOntainer of the currently loaded resource
	 * @throws IllegalArgumentException throws exception
	 * @throws IOException throws exception
	 */
	private IEquationSectionContainer getEquationSectionContainerForUri() throws IllegalArgumentException, IOException {
		IProject project = resourceFile.getProject();
		virSatResourceSet = VirSatResourceSet.getResourceSet(project);
		editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(virSatResourceSet);
		
		// Load the resource through the editing domain.
		try {
			virSatResource = editingDomain.getResourceSet().getResource(uri, true);
		} catch (Exception e) {
			virSatResource = editingDomain.getResourceSet().getResource(uri, false);
		}

		EObject editorModelObject = null;
		String uriFragment = uri.fragment();
		if ((uriFragment != null) && (!uriFragment.isEmpty())) {
			editorModelObject = virSatResource.getEObject(uriFragment);
		} else {
			editorModelObject = virSatResource.getContents().get(0);
		}

		return (IEquationSectionContainer) editorModelObject;
	}
	
	/**
	 * This method creates the file Proxy that redirects the question for
	 * the files content. rather than handing back the correct DVLM file content
	 * it will make sure that the equations part will be taken and handed back
	 * @return the file Proxy
	 * @throws IllegalArgumentException throws exception
	 * @throws IOException throws exception
	 */
	private IFile createFileProxy() throws IllegalArgumentException, IOException {
		return (IFile) Proxy.newProxyInstance(EquationSectionUriEditorInput.class.getClassLoader(), new Class<?>[] { IFile.class },
				new EquationSectionInvocationHandler());
	}	

	/**
	 * This method creates the serialized String of the EquationSection Object.
	 * It hands back the objects as manually handwritten mathematical formulas/equations.
	 * @return The serialized equations.
	 */
	public String getSerializedInput() {
		try {
			equationSectionContainer = getEquationSectionContainerForUri();
			equationSection = equationSectionContainer.getEquationSection();
	
			if (equationSection == null) {
				return EMPTY_EQUATIONS;
			}
			
			URI resUri = URI.createFileURI("temporaryCalculations.dvlmeq");
			
			IResourceServiceProvider rsp = IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(resUri);
			EquationSectionVirSatAwareXtextResourceSet equationSectionResourceSet = rsp.get(EquationSectionVirSatAwareXtextResourceSet.class); 
			
			EcoreUtil.resolveAll(equationSection);
			EquationSection copyiedEquationSecition = EcoreUtil2.copy(equationSection);
			
			Resource xtextReequationSectionXtextResource = equationSectionResourceSet.createFromEquationSectionContainerResource(resUri, virSatResource, this);
			xtextReequationSectionXtextResource.getContents().add(copyiedEquationSecition);
			OutputStream outStream = new ByteArrayOutputStream();
			
			// removed .noValidation() due to xtext change https://github.com/eclipse/xtext-core/issues/48
			// Apparently we deactivated a so called CSV validator which was doing a pre validation if the
			// EMF model can be serialized. This CSV validator has been removed in xtext and is therefore not needed anymore.
			xtextReequationSectionXtextResource.save(outStream, SaveOptions.newBuilder().getOptions().toOptionsMap());
			
			return outStream.toString();
		} catch (IOException | RuntimeException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Failed transform Equation Section XText to VirSat Model due to syntactically incorrect input"));
		}
		return (equationSection.getSerializedStatements() != null) ? equationSection.getSerializedStatements() : EMPTY_EQUATIONS;
	}
	
	private static final String EMPTY_EQUATIONS = "";
	
	/**
	 * This InvokationHandler is placed between the calls to the underlying file
	 * It will bend the getContents method to our content of the DataModel. The
	 * SystemCOmponent contains the formulas.
	 *   
	 * @author fisc_ph
	 *
	 */
	class EquationSectionInvocationHandler implements InvocationHandler {
		
		/**
		 * COnstructor of the InvokationHandler. The UUID is needed to access the right content
		 * of the correct SystemComponent
		 * @throws IOException throws exception
		 */
		EquationSectionInvocationHandler() throws IOException {
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			//System.out.println("Invocation: " + method.getName());
			
			if (method.getName().equals("equals")) {
				return proxy == args[0] || resourceFile == args[0] || resourceFile.equals(args[0]);
			}
			
			if (method.getName().equals("isConflicting")) {
				return proxy == args[0] || resourceFile == args[0];
				//return false;
			}
			
			// Just digest the call to set contents We may consider to move the deserialization here
			// so that the Xtext language is changed into the actual EMF model. But be aware,
			// if we don't digest this call,w e would write the serialized equations into the
			// XMI file of the StructuralElementInstance. This would actually destroy it.
			if (method.getName().equals("setContents")) {
				return null;
			}
				
			// The invocation to get the content is redirected to our data model
			if (method.getName().equals("getContents")) {
				String serializedModel = getSerializedInput();
				return new StringInputStream(serializedModel);
			}
			
			// The ReadOnly method tells if the User is allowed to access and alter the content  
			if (method.getName().equals("isReadOnly")) {
				return false;
			}
			
			// All other calls are directly forwarded to the File object
			return method.invoke(resourceFile, args);
		}
	}

	public static final String MEMENTO_ID_URI = "de.dlr.sc.virsat.model.calculation.ui.editor.EquationSectionUriEditorInput.uri";
	
	@Override
	public String getFactoryId() {
		return EquationSectionXtextEditorRestoreFactory.FACTORY_ID;
	}
	
	@Override
	public void saveState(IMemento memento) {
		String uriString = uri.toPlatformString(true);
		memento.putString(MEMENTO_ID_URI, uriString);
	}
	
	@Override
	public IPersistableElement getPersistable() {
		return this;
	}

	/**
	 * Saves the resource which is attached to this EditorInput
	 */
	public void saveResource() {
		if (editingDomain.isDirty(virSatResource)) {
			editingDomain.saveAll();
		}
	}
	
	/**
	 * Use this method to merge back the EquationsSection
	 * @param equationSection the equation section
	 * @param serializedEquations the serialized equations
	 */
	public void mergeBackEquationsSection(EquationSection equationSection, String serializedEquations) {
		try {
			this.equationSection = equationSection;
			this.equationSection.setSerializedStatements(serializedEquations);
			Command setEquationSection = SetCommand.create(editingDomain, equationSectionContainer, CalculationPackage.Literals.IEQUATION_SECTION_CONTAINER__EQUATION_SECTION, equationSection);
			editingDomain.getCommandStack().execute(setEquationSection);
			
			
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public EquationSection getEquationSection() {
		if (equationSection == null) {
			try {
				equationSectionContainer = getEquationSectionContainerForUri();
				equationSection = equationSectionContainer.getEquationSection();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (equationSection == null) {
				equationSection = CalculationFactory.eINSTANCE.createEquationSection();
				Command setEquationSection = SetCommand.create(editingDomain, equationSectionContainer, CalculationPackage.Literals.IEQUATION_SECTION_CONTAINER__EQUATION_SECTION, equationSection);
				editingDomain.getCommandStack().execute(setEquationSection);
			}
		}
		return equationSection;
	}
}
