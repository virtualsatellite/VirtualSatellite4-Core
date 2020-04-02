/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.ui.diagram.feature;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.model.ui.editor.input.VirSatUriEditorInput;
import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.uiengine.ui.editor.GenericEditor;



/**
 * Adds the option to open the editor of an category assignment
 * @author muel_s8
 *
 */
public class VirsatCategoryAssignmentOpenEditorFeature extends AbstractCustomFeature {

	/**
     * calls super
     * @param fp the IFeatureProvider
     *
     */
	public VirsatCategoryAssignmentOpenEditorFeature(IFeatureProvider fp) {		
		super(fp);		
	}
	@Override
    public String getName() {
        return "Open Editor";
    }
 
    @Override
    public String getDescription() {
        return "Opens the editor";
    }   
    @Override
    public boolean canExecute(ICustomContext context) {
        // allow rename if exactly one pictogram element
        // representing a EClass is selected
        boolean ret = false;
        PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof IBeanCategoryAssignment) {
                ret = true;
            }
        }
        return ret;
    }
	@Override
	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof IBeanCategoryAssignment) {
            	IBeanCategoryAssignment ie = (IBeanCategoryAssignment) bo;
        		
        		Resource resource = ie.getTypeInstance().eResource();
        		if (resource == null) {
        			// If the resource is null, then we do not allow for opening the editor
        			return;
        		}
        		
        		URI resourceUri = resource.getURI();
        		if (!VirSatEcoreUtil.isRootComponentofResource(ie.getTypeInstance())) {
        			resourceUri = resourceUri.appendFragment(resource.getURIFragment(ie.getTypeInstance()));
        		}
        		try {
        			IWorkbench workbench = PlatformUI.getWorkbench();
        			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
        			IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
        			CategoryAssignment ca = ie.getTypeInstance();
        			if (ca != null) {
        				URIEditorInput editorInput = new VirSatUriEditorInput(resourceUri, ca);
        				workbenchPage.openEditor(editorInput, GenericEditor.EDITOR_ID);
        			} else {
        				workbenchPage.openEditor(new VirSatUriEditorInput(resourceUri), GenericEditor.EDITOR_ID);
        			}
        		} catch (PartInitException ex) {
        			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Could not open Editor for: " + resourceUri, ex));
        		}        	       	
            }
        }
	}
	
	@Override
	public boolean canUndo(IContext context) {
		return false;
	}
	
	@Override
	public String getImageId() {
		return "OpenEditor";
	}
}



