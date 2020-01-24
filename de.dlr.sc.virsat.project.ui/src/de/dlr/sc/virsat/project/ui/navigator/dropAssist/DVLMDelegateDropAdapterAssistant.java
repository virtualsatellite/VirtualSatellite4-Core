/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.dropAssist;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.Activator;

/**
 * Drop Assistant for VirSat Navigator that delegates calls to concept specific drop assistants
 */
public class DVLMDelegateDropAdapterAssistant extends CommonDropAdapterAssistant {

	protected Map<String, CommonDropAdapterAssistant> mapConceptIdToDelegateDropAdapter;
	
	protected CommonDropAdapterAssistant defaultCopyPasteDropAdapter;
	protected CommonDropAdapterAssistant defaultInheritanceDropAdapter;
	
	public static final String EXTENSION_POINT_NAVIGATOR = "de.dlr.sc.virsat.project.ui.navigator";
	public static final String EXTENSION_POINT_DROP_ASSISTANT_CONTRIBUTION = "DVLMConceptDropAssistant";
	
	public static final String EXTENSION_POINT_DROP_ASSISTANT_CONCEPT_ID = "conceptId";
	public static final String EXTENSION_POINT_DROP_ASSISTANT_IMPLEMENTATION = "class";
	
	@Override
	protected void doInit() {
		initDropAssistantMap();
		
		defaultCopyPasteDropAdapter = new DVLMDefaultCopyAndPasteDropAdapterAssistant();
		defaultInheritanceDropAdapter = new DVLMDefaultInheritanceDropAdapterAssistant();
		defaultCopyPasteDropAdapter.init(getContentService());
	}

	/**
	 * Reads out the extension point to initialize the map of
	 * concept ids vs. drop adapters
	 */
	protected void initDropAssistantMap() {
		// Re initialize the map of drop assistants
		mapConceptIdToDelegateDropAdapter = new HashMap<>();
	
		// Now start looping over all Navigator extensions to find the possible registered drop assistants
		IConfigurationElement[] navigatorExtensions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_NAVIGATOR);
		for (IConfigurationElement navigatorExtension : navigatorExtensions) {
			if (EXTENSION_POINT_DROP_ASSISTANT_CONTRIBUTION.equals(navigatorExtension.getName())) {
				String conceptId = navigatorExtension.getAttribute(EXTENSION_POINT_DROP_ASSISTANT_CONCEPT_ID);
				CommonDropAdapterAssistant dropAdapter;
				try {
					dropAdapter = (CommonDropAdapterAssistant) navigatorExtension.createExecutableExtension(EXTENSION_POINT_DROP_ASSISTANT_IMPLEMENTATION);
					dropAdapter.init(getContentService());
					if (!mapConceptIdToDelegateDropAdapter.containsKey(conceptId)) {
						mapConceptIdToDelegateDropAdapter.put(conceptId, dropAdapter);
					} else {
						Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Cannot register two drop adapter for the same concept: " + conceptId));
					}
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to create executable extension for drop adapter in concept: " + navigatorExtension.getContributor() + "->" + conceptId + " because: " + e.getMessage()));
				}
			}
		}
	}

	/**
	 * Call this method to get a drop adapter for a given object.
	 * The method checks if the object belongs to a concept and hands
	 * back the drop adapter which is in the map if it exists
	 * @param object the object for which to find a specialized drop adapter 
	 * @return null in case there is no specific drop adapter for the given object.
	 */
	protected CommonDropAdapterAssistant getDelegateDropAdapter(Object object) {
		// To call a concept drop assistant the object where to drop at needs
		// to be a dvlm instance. Once this is clear it is possible to detect the
		// type of the object and the concept corresponding to it.
		Concept concept = null;
		if (object instanceof StructuralElementInstance) {
			StructuralElementInstance sei = (StructuralElementInstance) object;
			concept = ActiveConceptHelper.getConcept(sei.getType());
		} else if (object instanceof ATypeInstance) {
			ATypeInstance ti = (ATypeInstance) object;
			concept = ActiveConceptHelper.getConcept(ti.getType());
		}
	
		// In case we found a concept try to get a drop adapter for it
		if (concept != null) {
			// now ask the map for the drop assistant and hand it back
			String conceptId = concept.getName();
			CommonDropAdapterAssistant dropAdapterAssitant = mapConceptIdToDelegateDropAdapter.get(conceptId);
			return dropAdapterAssitant;
		}
		
		return null;
	}
	
	private CommonDropAdapterAssistant delegateDropAdapter;
	
	/**
	 * This method gets usually called by the delegates from the validate methods.
	 * It first tries to identify a delegate drop adapter. If it cannot be found, it sets
	 * the default copy and paste drop adapter as the delegate on. Then it runs the delegation method
	 * with the identified drop adapter.
	 * @param dropTarget the object where to drop at
	 * @param delegateFunction the method to be executed with the delegated drop adapter
	 * @return the result of the method call.
	 */
	protected IStatus selectDelegateDropAdapter(Object dropTarget, Function<CommonDropAdapterAssistant, IStatus> delegateFunction) {
		delegateDropAdapter = getDelegateDropAdapter(dropTarget);

		if (delegateDropAdapter == null) {
			delegateDropAdapter = defaultCopyPasteDropAdapter;
		}
			
		IStatus delegateStatus = delegateFunction.apply(delegateDropAdapter);
		
		// If there is a cancel status try the default inheritance drop adapter
		if (!delegateStatus.isOK()) {
			delegateDropAdapter = defaultInheritanceDropAdapter;
			delegateStatus = delegateFunction.apply(delegateDropAdapter);
		}

		// If there is still a cancel status try the default copy and paste adapter
		if (!delegateStatus.isOK()) {
			delegateDropAdapter = defaultCopyPasteDropAdapter;
			delegateStatus = delegateFunction.apply(delegateDropAdapter);
		}
		
		return delegateStatus; 
	}
	
	@Override
	public IStatus validateDrop(Object target, int operation, TransferData transferType) {
		// Set the delegated drop adapter when validating
		return selectDelegateDropAdapter(target,
			dropAdapter -> dropAdapter.validateDrop(target, operation, transferType));
	}

	@Override
	public IStatus handleDrop(CommonDropAdapter aDropAdapter, DropTargetEvent aDropTargetEvent, Object aTarget) {
		// use the drop adapter which was valid
		return delegateDropAdapter.handleDrop(aDropAdapter, aDropTargetEvent, aTarget);
	}

	@Override
	public IStatus validatePluginTransferDrop(IStructuredSelection aDragSelection, Object aDropTarget) {
		return selectDelegateDropAdapter(aDropTarget,
			dropAdapter -> dropAdapter.validatePluginTransferDrop(aDragSelection, aDropTarget));
	}

	@Override
	public IStatus handlePluginTransferDrop(IStructuredSelection aDragSelection, Object aDropTarget) {
		return delegateDropAdapter.handlePluginTransferDrop(aDragSelection, aDropTarget);
	}

	@Override
	public void setCommonDropAdapter(CommonDropAdapter dropAdapter) {
		super.setCommonDropAdapter(dropAdapter);
		
		mapConceptIdToDelegateDropAdapter.values().forEach(delegateDropAdapter -> delegateDropAdapter.setCommonDropAdapter(dropAdapter));
		
		defaultCopyPasteDropAdapter.setCommonDropAdapter(dropAdapter);
	}
}
