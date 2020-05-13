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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;

import de.dlr.sc.virsat.qudv.ui.Activator;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * the wizard page for QUDV model export
 * @author scha_vo
 *
 */
public class QudvExportPage extends WizardPage implements Listener {

	// widgets
    private Combo destinationNameField;

    private Button destinationBrowseButton;
    
    private CheckboxTableViewer fTableViewer;
    
    private List<IProject> fSelectedProjects = new ArrayList<IProject>();

    //messages
    private static final String SELECT_DESTINATION_MESSAGE = "Select a directory to export to.";

    private static final String SELECT_DESTINATION_TITLE = "Export to directory";
	
    /**
     * public constructor
     * @param pageName the name of the page
     */
	public QudvExportPage(String pageName) {
		super(pageName);
		setTitle("QUDV Export Page");
	    setDescription("Exports QUDV Model to the file system");		
	}

	@Override
    public void createControl(Composite parent) {
    	
        initializeDialogUnits(parent);

        Composite workArea = new Composite(parent, SWT.NONE);
        setControl(workArea);

        workArea.setLayout(new GridLayout());
        workArea.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

        Label titel = new Label(workArea, SWT.NONE);
        titel.setText("Select the proxy of which you want to export the units");
        
        createListGroup(workArea);
        createDestinationGroup(workArea);
    }

    /**
     * creates a group of widgets to list the projects
     * @param parent the parent composite to which the controls are added
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
					IProject proj = (IProject) element; 	
					return "Unit-Management : " + proj.getName();
				}
				return "Boomm";
			}
        	
        });
        fTableViewer.addCheckStateListener(new ICheckStateListener() {
            public void checkStateChanged(CheckStateChangedEvent event) {
                if (event.getChecked()) {
                    fSelectedProjects.add((IProject) event.getElement());
                } else {
                    fSelectedProjects.remove(event.getElement());
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
    	if (fSelectedProjects.size() == 0) {
    		setErrorMessage("You have to select at least one Proxy.");
    		complete = false;
    	}
    	
    	if (destinationNameField.getText().length() == 0) {
    		setErrorMessage("Destination has to be specified");
    		complete = false;
    	}
    	if (complete) {
    		setErrorMessage(null);
    	}
    	setPageComplete(complete);
    }
	
	
    /**
     * handles the events
     * @param event the event to handle
     */
	public void handleEvent(Event event) {
		Widget source = event.widget;

        if (source == destinationBrowseButton) {
			handleDestinationBrowseButtonPressed();
		}

        updateEnablement();
		
	}

	/**
	 * creates a group of controls / widgets to deifne the destination 
	 * @param parent the parent composite to which the controls are added
	 */
	protected void createDestinationGroup(Composite parent) {
		Font font = parent.getFont();
        // destination specification group
        Composite destinationSelectionGroup = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        final int numColumns = 3;
        layout.numColumns = numColumns;
        destinationSelectionGroup.setLayout(layout);
        destinationSelectionGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));
        destinationSelectionGroup.setFont(font);

        Label destinationLabel = new Label(destinationSelectionGroup, SWT.NONE);
        destinationLabel.setText("To Directory:");
        destinationLabel.setFont(font);

        // destination name entry field
        destinationNameField = new Combo(destinationSelectionGroup, SWT.SINGLE | SWT.BORDER);
        destinationNameField.addListener(SWT.Modify, this);
        destinationNameField.addListener(SWT.Selection, this);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        final int widthHint = 250;
        data.widthHint = widthHint;
        destinationNameField.setLayoutData(data);
        destinationNameField.setFont(font);

        // destination browse button
        destinationBrowseButton = new Button(destinationSelectionGroup, SWT.PUSH);
        destinationBrowseButton.setText("Browse");
        destinationBrowseButton.addListener(SWT.Selection, this);
        destinationBrowseButton.setFont(font);
        setButtonLayoutData(destinationBrowseButton);

        new Label(parent, SWT.NONE); // vertical spacer
		
	}

	 /**
     * The Finish button was pressed.  Try to do the required work now and answer
     * a boolean indicating success.  If false is returned then the wizard will
     * not close.
     * @return boolean
     */
    public boolean finish() {
    	
    	for (IProject project : fSelectedProjects) {
    		//we need a copy of the systemOfUnits because if we don't make a copy, the SystemOfUnits will be removed since it was a reference
    		//my copy method uses Ecore.copier and generates new IDs. I don't think it matters at this point, but I avoid doing clone() or copy() of an object
    		SystemOfUnits systemOfUnits = QudvUnitHelper.getInstance().copySystemOfUnits(VirSatResourceSet.getResourceSet(project).getUnitManagement().getSystemOfUnit());
    		String destination = getDestinationValue() + "\\" + project.getName() + "." + Activator.QUDV_FILE_EXTENSION;
    		try {
    			QudvUnitHelper.getInstance().exportModeltoFile(systemOfUnits, destination);
    		} catch (IOException e) {
    			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), Status.WARNING, "QudvModelExport: failed to export to file " + e.getMessage(), null));
				return false;
			}  		
    	}
    
        // Save dirty editors if possible but do not stop if not all are saved
        // saveDirtyEditors();
        // about to invoke the operation so save our state
        // saveWidgetValues();

        return true;
        
    }
	 
    /**
     *	Open an appropriate destination browser so that the user can specify a source
     *	to import from
     */
    protected void handleDestinationBrowseButtonPressed() {
        DirectoryDialog dialog = new DirectoryDialog(getContainer().getShell(),
                SWT.SAVE | SWT.SHEET);
        dialog.setMessage(SELECT_DESTINATION_MESSAGE);
        dialog.setText(SELECT_DESTINATION_TITLE);
        dialog.setFilterPath(getDestinationValue());
        String selectedDirectoryName = dialog.open();

        if (selectedDirectoryName != null) {
            setErrorMessage(null);
            setDestinationValue(selectedDirectoryName);
        }
    }
    /**
     *	Answer the contents of self's destination specification widget
     *
     *	@return java.lang.String
     */
    protected String getDestinationValue() {
        return destinationNameField.getText().trim();
    }
    /**
     *	Set the contents of the receivers destination specification widget to
     *	@param value the passed value
     *
     */
    protected void setDestinationValue(String value) {
        destinationNameField.setText(value);
    }
}
