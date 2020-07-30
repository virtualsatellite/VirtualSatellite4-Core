/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.qudv.ui.wizards.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * wizard page to map the import to a study 
 * @author scha_vo
 *
 */
public class QudvImportMapToProxyPage extends WizardPage {

	/**
	 * 
	 * @param pageName the name of the page
	 */
	public QudvImportMapToProxyPage(String pageName) {
		super(pageName);
		setTitle("QUDV Import Page");
	    setDescription("Imports QUDV Model to a repository");
	}
	
    private CheckboxTableViewer fTableViewer;
    
    private List<IProject> fSelectedProject = new ArrayList<IProject>();
    
	@Override
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);
		
		Composite workArea = new Composite(parent, SWT.NONE);
        setControl(workArea);

        workArea.setLayout(new GridLayout());
        workArea.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

        Label titel = new Label(workArea, SWT.NONE);
        titel.setText("Map Import to Proxy");
        
		createListGroup(workArea);
		setPageComplete(false);
	}
	
	/**
	 * creates the widgtes to list the projects
	 * @param parent the parent composite to which the widgets will be added
	 */
    private void createListGroup(Composite parent) {
		Composite listComposite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginWidth = 0;
        layout.makeColumnsEqualWidth = false;
        listComposite.setLayout(layout);

        listComposite.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.FILL_BOTH));

        Table table = new Table(listComposite, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        fTableViewer = new CheckboxTableViewer(table);
        table.setLayout(new TableLayout());
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        final int heightHint = 300;
        data.heightHint = heightHint;
        table.setLayoutData(data);
        
        fTableViewer.setContentProvider(new ArrayContentProvider());
        // Try to get all VirSat projects and add them to the content
     	IWorkspace workspace =  ResourcesPlugin.getWorkspace();
     	List<IProject> virSatProjects = VirSatProjectCommons.getAllVirSatProjects(workspace); 
        fTableViewer.setInput(virSatProjects.toArray());
        
        fTableViewer.setLabelProvider(new LabelProvider() {

			@Override
			public Image getImage(Object element) {
				return null;
			}

			@Override
			public String getText(Object element) {
				if (element instanceof IProject) {
					IProject project = (IProject) element;
					return "Unit-Management : " + project.getName();
				}
				return "Boomm";
			}
        	
        });
        fTableViewer.addCheckStateListener(new ICheckStateListener() {
            public void checkStateChanged(CheckStateChangedEvent event) {
                if (event.getChecked()) {
                    fSelectedProject.add((IProject) event.getElement());
                } else {
                    fSelectedProject.remove(event.getElement());
                }
                updateEnablement();
            }
        });
	}
    
    /**
	 * this method handles the control enablement. It deactivates the controls when they should not be possible to be editable
	 */
    private void updateEnablement() {
    	boolean complete = true;
    	if (fSelectedProject.size() == 0) {
    		setErrorMessage("You have to select at least one Proxy.");
    		complete = false;
    	}
    	if (complete) {
    		setErrorMessage(null);
    	}
    	setPageComplete(complete);
    }
    
    
    /**
     * The Finish button was pressed.  Try to do the required work now and answer
     * a boolean indicating success.  If false is returned then the wizard will
     * not close.
     * @param systemOfUnits the system of Units
     * @return boolean
     */
    public boolean finish(SystemOfUnits systemOfUnits) {
    	
    	SystemOfUnits copiedSoU = null;
    	
    	// for each proxy - copy and set the new, copied SystemOfUnits and notify the listeners
    	for (IProject project : fSelectedProject) {
    		copiedSoU = QudvUnitHelper.getInstance().copySystemOfUnits(systemOfUnits);
    		VirSatResourceSet.getResourceSet(project).getUnitManagement().setSystemOfUnit(copiedSoU);
    	}
    
        // Save dirty editors if possible but do not stop if not all are saved
        // saveDirtyEditors();
        // about to invoke the operation so save our state
        // saveWidgetValues();

        return true;
        
    }
    
}
