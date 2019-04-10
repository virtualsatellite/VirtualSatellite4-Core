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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import org.eclipse.ui.internal.ide.filesystem.FileSystemStructureProvider;

import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.qudv.ui.Activator;

//Sorry for that but I have to suppress the warnings for the discouraged access to FileSystemStructureProvider 
//CHECKSTYLE:OFF
@SuppressWarnings("restriction")
//CHECKSTYLE:ON
/**
 * first page of the import wizard
 * @author scha_vo
 *
 */
public class QudvImportPage extends WizardPage implements Listener {

	/**
	 * public constructor to import unit and quantity 
	 * @param pageName the name of the page
	 */
	public QudvImportPage(String pageName) {
		super(pageName);
		setTitle("QUDV Import Page");
	    setDescription("Imports QUDV Model to a repository");
	}

	// widgets
    protected Combo sourceNameField;
    
    protected Button sourceBrowseButton;
	
    private CheckboxTableViewer fTableViewer;
    
    private List<File> fSelectedImportFiles = new ArrayList<File>();
     
    //A boolean to indicate if the user has typed anything
    private boolean entryChanged = false;
    
    private SystemOfUnits systemOfUnits = null;
    
    private FileSystemStructureProvider fileSystemStructureProvider = new FileSystemStructureProvider();
     
    //messages
    private static final String SELECT_DESTINATION_MESSAGE = "Select a directory to import from.";

    private static final String SELECT_DESTINATION_TITLE = "Import from directory";
    
    
	@Override
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);
		
		Composite workArea = new Composite(parent, SWT.NONE);
        setControl(workArea);

        workArea.setLayout(new GridLayout());
        workArea.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

        Label titel = new Label(workArea, SWT.NONE);
        titel.setText("Select the directory and file you want to import units from");
        
		createRootDirectoryGroup(workArea);
		createListGroup(workArea);
		
		setPageComplete(false);
		
	}

	/**
     *	Create the group for creating the root directory
     *  @param parent the composite to which stuff is added
     */
    protected void createRootDirectoryGroup(Composite parent) {
        Composite sourceContainerGroup = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        final int numColumns = 3;
        layout.numColumns = numColumns;
        sourceContainerGroup.setLayout(layout);
        sourceContainerGroup.setFont(parent.getFont());
        sourceContainerGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

        Label groupLabel = new Label(sourceContainerGroup, SWT.NONE);
        groupLabel.setText("From Directory:");
        groupLabel.setFont(parent.getFont());

        // source name entry field
        sourceNameField = new Combo(sourceContainerGroup, SWT.BORDER);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        final int widthHint = 250;
        data.widthHint = widthHint;
        sourceNameField.setLayoutData(data);
        sourceNameField.setFont(parent.getFont());

        sourceNameField.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                updateFromSourceField();
            }
        });

        sourceNameField.addKeyListener(new KeyListener() {
            /*
             * @see KeyListener.keyPressed
             */
            public void keyPressed(KeyEvent e) {
                //If there has been a key pressed then mark as dirty
                entryChanged = true;
				if (e.character == SWT.CR) {
					entryChanged = false;
					updateFromSourceField();
				}
            }

            /*
             * @see KeyListener.keyReleased
             */
            public void keyReleased(KeyEvent e) {
            }
        });

        sourceNameField.addFocusListener(new FocusListener() {
            /*
             * @see FocusListener.focusGained(FocusEvent)
             */
            public void focusGained(FocusEvent e) {
                //Do nothing when getting focus
            }

            /*
             * @see FocusListener.focusLost(FocusEvent)
             */
            public void focusLost(FocusEvent e) {
                //Clear the flag to prevent constant update
                if (entryChanged) {
                    entryChanged = false;
                    updateFromSourceField();
                }

            }
        });

        // source browse button
        sourceBrowseButton = new Button(sourceContainerGroup, SWT.PUSH);
        sourceBrowseButton.setText("Browse");
        sourceBrowseButton.addListener(SWT.Selection, this);
        sourceBrowseButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
        sourceBrowseButton.setFont(parent.getFont());
        setButtonLayoutData(sourceBrowseButton);
    }
    
    /**
     * creates the widgets to define the widgets for listing the projects
     * @param parent the parent composite to which the controls will be added
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
        
        fTableViewer.setContentProvider(new ArrayContentProvider()  {
        	@Override
			public Object[] getElements(Object element) {
        		List<File> listOfQudvFiles = new ArrayList<File>();
        		if (element instanceof Object[]) {
					Object[] oArray = (Object[]) element;
					for (int i = 0; i < oArray.length; i = i + 1) {
						Object o = oArray[i]; 
						listOfQudvFiles = checkForFileExtensionAndAddtoList(o, listOfQudvFiles);
					}
					return listOfQudvFiles.toArray();
				}
			
                if (element instanceof Collection) {
                	@SuppressWarnings("rawtypes")
					Collection coll = (Collection) element; 
                	for (Object o : coll) {
                		listOfQudvFiles = checkForFileExtensionAndAddtoList(o, listOfQudvFiles);
                	}
                	return listOfQudvFiles.toArray();
                }
                return new Object[0];
			}
        	/*
        	 * this method checks a given object if it is of tyoe File and has the right file extension
        	 * if yes it adds the object to the listofQudvFiles and returns the list with new object  
        	 */
        	private List<File> checkForFileExtensionAndAddtoList(Object o, List<File> listOfQudvFiles) {
        		if (o instanceof File) {
        			File f = (File) o;
        			String name = f.getName();
        			int pos = name.lastIndexOf('.');
        			String ext = name.substring(pos + 1);
        			if (ext.equals(Activator.QUDV_FILE_EXTENSION)) {
        				listOfQudvFiles.add(f);
        			}
        		}
        		return listOfQudvFiles;
        	}	
        });
        fTableViewer.setInput(fileSystemStructureProvider.getChildren(new File(getSourceValue())).toArray());
        
        fTableViewer.setLabelProvider(new LabelProvider() {

			@Override
			public Image getImage(Object element) {
				return Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/unit_underline.gif").createImage();
			}
				
			@Override
			public String getText(Object element) {
				if (element instanceof File) {
					File f = (File) element;
					return f.getName();
				}
				//LOGGER.debug("no *.qudv file found in this location");
				//this case should never happen
				return "case that should never happen";
			}
        	
        });
        fTableViewer.addCheckStateListener(new ICheckStateListener() {
            public void checkStateChanged(CheckStateChangedEvent event) {
                if (event.getChecked()) {
                    fSelectedImportFiles.add((File) event.getElement());
                } else {
                    fSelectedImportFiles.remove(event.getElement());
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
    	if (fSelectedImportFiles.size() == 0) {
    		setErrorMessage("You have to select at least one file.");
    		complete = false;
    	}
    	
    	if (sourceNameField.getText().length() == 0) {
    		setErrorMessage("Source directory has to be specified");
    		complete = false;
    	}
    	if (complete) {
    		setErrorMessage(null);
    	}
    	setPageComplete(complete);
    }
    
    /**
     *	Open an appropriate destination browser so that the user can specify a source
     *	to import from
     */
    protected void handleSourceBrowseButtonPressed() {
        DirectoryDialog dialog = new DirectoryDialog(getContainer().getShell(),
                SWT.SAVE | SWT.SHEET);
        dialog.setMessage(SELECT_DESTINATION_MESSAGE);
        dialog.setText(SELECT_DESTINATION_TITLE);
        dialog.setFilterPath(getSourceValue());
        String selectedDirectoryName = dialog.open();

        if (selectedDirectoryName != null) {
            setErrorMessage(null);
            setSourceName(selectedDirectoryName);
        }
    }
    
    /**
     * Update the receiver from the source name field.
     */

    private void updateFromSourceField() {

        setSourceName(sourceNameField.getText());
        // Update enablements when this is selected
        updateEnablement();
    }
    
    /**
     * Sets the source name of the import to be the supplied path.
     * Adds the name of the path to the list of items in the
     * source combo and selects it.
     *
     * @param path the path to be added
     */
    protected void setSourceName(String path) {

        if (path.length() > 0) {

            String[] currentItems = this.sourceNameField.getItems();
            int selectionIndex = -1;
            for (int i = 0; i < currentItems.length; i++) {
                if (currentItems[i].equals(path)) {
					selectionIndex = i;
				}
            }
            if (selectionIndex < 0) {
                int oldLength = currentItems.length;
                String[] newItems = new String[oldLength + 1];
                System.arraycopy(currentItems, 0, newItems, 0, oldLength);
                newItems[oldLength] = path;
                this.sourceNameField.setItems(newItems);
                selectionIndex = oldLength;
            }
            this.sourceNameField.select(selectionIndex);
            
            resetSelection();
            updateEnablement();
        }
    }
    
    /**
     * The Finish button was pressed.  Try to do the required work now and answer
     * a boolean indicating success.  If false is returned then the wizard will
     * not close.
     * @return boolean
     */
    public boolean finish() {
    	
    	for (File f : fSelectedImportFiles) {
    		String fullPathToFile = f.getAbsolutePath();
    		try {
    			systemOfUnits = QudvUnitHelper.getInstance().importModelFromFile(fullPathToFile);
			} catch (IOException e) {
				Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), Status.WARNING, "QudvModelExport: failed to import to file " + e.getMessage(), null));
				return false;
			}  		
    	}

        return true;
        
    }
    /**
     * public getter method which gets the system of units
     * @return the system of units
     */
    public SystemOfUnits getSystemOfUnits() {
    	return systemOfUnits;
    } 
    
    /**
     *	Repopulate the view based on the currently entered directory.
     */
    protected void resetSelection() {
    	fTableViewer.setInput(fileSystemStructureProvider.getChildren(new File(getSourceValue())).toArray());
    }

    /**
     *	Answer the contents of self's destination specification widget
     *
     *	@return java.lang.String
     */
    protected String getSourceValue() {
        return sourceNameField.getText().trim(); 
    }
    
    
	@Override
	public void handleEvent(Event event) {
		Widget source = event.widget;

        if (source == sourceBrowseButton) {
			handleSourceBrowseButtonPressed();
		}

        updateEnablement();
		
	}
    
}
