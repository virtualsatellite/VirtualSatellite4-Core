/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Label;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.part.ViewPart;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModel;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModelIo;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.StartManagers;
import de.dlr.sc.virsat.model.extension.visualisation.ui.handler.CreateDeltaModelFolderHandler;
import de.dlr.sc.virsat.model.extension.visualisation.ui.vtkClient.VtkClientVisUpdateHandler;
import de.dlr.sc.virsat.model.extension.visualisation.ui.vtkClient.VtkTreeManager;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatProjectLabelProvider;

/**
 * Creates the visualisation view
 *
 */
public class VtkClientView extends ViewPart {
	
	private static final String EXT_INITIAL = "initial";
	private static final String EXT_COMPARE_GEO = "vdmg";
	private static final String EXT_COMPARE_PARAMETER = "vdmp";
	private static final String EXT_COMPARE_COLORMAP = "vcmm";
	private static final String COLOR_MAP_FILE_EXT_SPLIT = "_";
	
	private static final String COMPARE_INITIAL_FILE = "/resources/images/diagram_initial.png";
	private static final String COMPARE_GEO_DIAGRAM_FILE = "/resources/images/diagram_compare_geo.png";
	private static final String COMPARE_PARAMETER_DIAGRAM_FILE = "/resources/images/diagram_compare_para.png";
	private static final String COMPARE_COLORMAP_DIAGRAM_FILE = "/resources/images/diagram_colormap.png";
	private static final int COLORMAP_FILE_EXT_SEG_NUM = 3;
	private static final int COLORMAP_DIAGRAM_SEG_NUM = 6;
	private static final int COLORMAP_DIAGRAM_ALL_SHIFT = -5;
	private static final int COLORMAP_DIAGRAM_SEG_SHIFT = -4;
	private static final int COLORMAP_DIAGRAM_VALUE_DIGITLA = 10;
	
	private static final String NO_DELTA_MODEL_TO_VISUALISE = "No Delta";
	private static final String ANIMATION_LIST = "Animation List";
	private static final int ANIMATION_SLEEP_TIME = 1000;
	
	private static VtkClientView vtkViewer = null;
	public static final String ID = "de.dlr.sc.virsat.model.extension.visualisation.ui.vtkview";
	private Composite swtAwtComposite = null;
	private Composite northButtonComposite = null; 
	private Composite diagramComposite = null; 
	private Button filterRootElementsButton = null;
	private Button frontButton = null;
	private Button sideButton = null;
	private Button topButton = null;
	private Button checkBoxLocalAxesEnables = null;
	private Button checkBoxGlobalAxesEnables = null;
	private ComboViewer projectCombo = null;
	private ComboViewer deltaCombo = null;
	private ComboViewer animationProjectCombo = null;
	private Button animationRunButton = null;
	private Canvas canvasDiagram = null;
	
	private List<VirSatProjectResource> listAnimationProjectSelected = new ArrayList<VirSatProjectResource>();
	private List<VisualisationDeltaModel> listAnimationDeltaModel = new ArrayList<VisualisationDeltaModel>();
	private Map<StructuralElementInstance, Boolean> filteredRootSeisToVisualise = null;
	
	private IProject currentlySelectedProject = null;
	private IResourceChangeListener projectListener;
	private VisualisationDeltaModel currentDeltaModel = null;
	
	/**
	 * initialize
	 * 
	 * @throws PartInitException Exception
	 * @param site site
	 */
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		vtkViewer = this;
	}

	/**
	 * return the current viewer
	 * @return current viewer
	 */
	public static VtkClientView getViewer() {
		return vtkViewer;
	}

	@Override
	public void createPartControl(final Composite parent) {
		final int COUNT_VIEW_BUTTONS = 10;
		
		parent.setLayout(new GridLayout(1, true));
		northButtonComposite = new Composite(parent, SWT.NONE);
		northButtonComposite.setLayout(new GridLayout(COUNT_VIEW_BUTTONS, false));
		northButtonComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, false));
		swtAwtComposite = new Composite(parent, SWT.EMBEDDED);
		swtAwtComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		diagramComposite = new Composite(parent, SWT.NONE);
		diagramComposite.setLayoutData(new GridData(GridData.BEGINNING, GridData.END, true, false));
		
		canvasDiagram = new Canvas(diagramComposite, SWT.NONE);
		canvasDiagram.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
				
		createCustomControls();
		createVtkPanel(parent);	
		createDiagram(EXT_INITIAL);
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					//workaround against "choosePixelFormat failed" error on rendering vtk panel
					final int HUNDRED = 100;
					Thread.sleep(HUNDRED);
				} catch (InterruptedException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Thread interrupted.", e));
				}
				VtkClientVisUpdateHandler.getInstance().updateVisualisationScene();
			}
		});
	}

	@Override
	public void setFocus() {
		if (swtAwtComposite != null) {
			swtAwtComposite.setFocus();
		}
	}

	@Override
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(projectListener);
		
		swtAwtComposite.dispose();
		northButtonComposite.dispose();
		diagramComposite.dispose();
		filterRootElementsButton.dispose();
		frontButton.dispose();
		sideButton.dispose();
		topButton.dispose();
		checkBoxLocalAxesEnables.dispose();
		checkBoxGlobalAxesEnables.dispose();
		canvasDiagram.dispose();
		swtAwtComposite = null;
		northButtonComposite = null;
		diagramComposite = null;
		projectListener = null;
		StartManagers.stopVis();
		VtkTreeManager.getInstance().clearVtkTreeManager();
		super.dispose();
	}
	/**
	 * Create diagram
	 * @param ext of delta model file to decide the diagram 
	 */
	private void createDiagram(String ext) {	
		Image image = null;
		try {
			if (ext.equals(EXT_COMPARE_GEO)) {
				image = new Image(canvasDiagram.getDisplay(), Activator.getFileFromPlugin(COMPARE_GEO_DIAGRAM_FILE).openStream());
			} else if (ext.equals(EXT_COMPARE_PARAMETER)) {
				image = new Image(canvasDiagram.getDisplay(), Activator.getFileFromPlugin(COMPARE_PARAMETER_DIAGRAM_FILE).openStream());
			} else if (ext.equals(EXT_INITIAL))	{
				image = new Image(canvasDiagram.getDisplay(), Activator.getFileFromPlugin(COMPARE_INITIAL_FILE).openStream());
			} else if (ext.contains(EXT_COMPARE_COLORMAP)) {
				image = new Image(canvasDiagram.getDisplay(), Activator.getFileFromPlugin(COMPARE_COLORMAP_DIAGRAM_FILE).openStream());
			}
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to load image", e));
		}	
		if (image != null) {
			int width = image.getBounds().width;
			int height = image.getBounds().height;
			canvasDiagram.setSize(width, height);
			canvasDiagram.setBackgroundImage(image);
			if (ext.contains(EXT_COMPARE_COLORMAP)) {
				GC gc = new GC(image);	
				String[] strs = ext.split(COLOR_MAP_FILE_EXT_SPLIT);
				if (strs.length >= COLORMAP_FILE_EXT_SEG_NUM) {
					String stringMin = strs[1].replaceAll(",", ".");
					String stringMax = strs[2].replaceAll(",", ".");
					double min = Double.parseDouble(stringMin);
					double max = Double.parseDouble(stringMax);
					for (int i = 0; i < COLORMAP_DIAGRAM_SEG_NUM; i++) {
						double value = i * ((max - min) / (COLORMAP_DIAGRAM_SEG_NUM - 1)) + min;
						int xPosition = 0;
						int yPosition = height / 2;
						if (i == 0)	{
							xPosition = 0;
						} else {
							xPosition = i * ((width / (COLORMAP_DIAGRAM_SEG_NUM - 1)) + COLORMAP_DIAGRAM_SEG_SHIFT) + COLORMAP_DIAGRAM_ALL_SHIFT;
						}
						value = (double) Math.round(value * COLORMAP_DIAGRAM_VALUE_DIGITLA) / COLORMAP_DIAGRAM_VALUE_DIGITLA;
						gc.drawText(Double.toString(value), xPosition, yPosition);
					}		 							
					canvasDiagram.drawBackground(gc, 0, 0, width, height);
				}				
			}
		}		
	}
	
	/**
	 * Create vtk panel and display it
	 * @param parent parents composite
	 */
	private void createVtkPanel(final Composite parent) {
		final Rectangle sh3dbnds = parent.getBounds();
		sh3dbnds.x = 0;
		sh3dbnds.y = 0;
		swtAwtComposite.setBounds(sh3dbnds);
		
		final Frame awt = SWT_AWT.new_Frame(swtAwtComposite);
		final Rectangle bounds = swtAwtComposite.getBounds();
		awt.setBounds(0, 0, bounds.width, bounds.height);
		awt.setLayout(new BorderLayout());
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Starting view", null));
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (!StartManagers.startVis(currentlySelectedProject, currentDeltaModel)) {
					Label errorLabel = new Label("Please open a project before opening this view");
					awt.add(errorLabel);
				} else {
					awt.add(VtkTreeManager.getInstance());
					VtkTreeManager.getInstance().initGui();
				}
			}
		});
	}
	/**
	 * Create buttons and checkboxes
	 */
	private void createCustomControls() {
		createProjectCombo();
		createDeltaCombo();
		
		createCustomizeRootElementsButton();
		
		checkBoxLocalAxesEnables = new Button(northButtonComposite, SWT.CHECK);
		checkBoxGlobalAxesEnables = new Button(northButtonComposite, SWT.CHECK);
		
		frontButton = new Button(northButtonComposite, SWT.PUSH);
		sideButton = new Button(northButtonComposite, SWT.PUSH);
		topButton = new Button(northButtonComposite, SWT.PUSH);
		
		frontButton.setText("Front View");
		sideButton.setText("Side View");
		topButton.setText("Top View");
		checkBoxLocalAxesEnables.setText("Local Axes");
		checkBoxGlobalAxesEnables.setText("Global Axes");
		
		checkBoxGlobalAxesEnables.setSelection(true);
		checkBoxLocalAxesEnables.setSelection(true);	
		
		createAnimationProjectCombo();
		
		animationRunButton = new Button(northButtonComposite, SWT.PUSH);
		animationRunButton.setText("Run");
		
		attachListenersToButtons();
	}

	
	/**
	 * Create drop down list for project selection
	 */
	private void createProjectCombo() {
		projectCombo = new ComboViewer(northButtonComposite, SWT.READ_ONLY | SWT.DROP_DOWN);
		
		projectCombo.setContentProvider(new ArrayContentProvider());
		projectCombo.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof IProject) {
					IProject project = (IProject) element;
					return project.getName();
				}
				return "UNKNOWN ELEMENT";
			}
		});
		
		projectCombo.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection structuredSelection = (IStructuredSelection) event.getSelection();
				IProject newProject = (IProject) structuredSelection.getFirstElement();
				if (newProject != currentlySelectedProject) {
					currentlySelectedProject = newProject;
					setDeltaComboInput();
					
					StartManagers.stopVis();
					StartManagers.startVis(currentlySelectedProject, currentDeltaModel);
				}
			}
		});
		
		projectListener = new IResourceChangeListener() {
			@Override
			public void resourceChanged(IResourceChangeEvent event) {
				Display.getDefault().asyncExec(() -> {
					setProjectComboInput();
				});
			}
		};
		ResourcesPlugin.getWorkspace().addResourceChangeListener(projectListener);
		
		setProjectComboInput();
	}
	
	/**
	 * Sets the input for the project combo box
	 */
	private void setProjectComboInput() {
		currentDeltaModel = null;
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		List<IProject> allProjects = VirSatProjectCommons.getAllVirSatProjects(workspace);
		projectCombo.setInput(allProjects);
		
		if (!allProjects.isEmpty()) {
			if (currentlySelectedProject == null) {
				currentlySelectedProject = allProjects.get(0);
			} 
			projectCombo.setSelection(new StructuredSelection(currentlySelectedProject));
		} else {
			currentlySelectedProject = null;
		}
	}
	
	/**
	 * Create drop down list for project animation
	 */
	private void createAnimationProjectCombo() {
		animationProjectCombo = new ComboViewer(northButtonComposite, SWT.READ_ONLY | SWT.DROP_DOWN);
			
		animationProjectCombo.setContentProvider(new ArrayContentProvider());
		animationProjectCombo.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof VirSatProjectResource) {
					VirSatProjectResource resource = (VirSatProjectResource) element;
					return resource.getWrappedProject().getName();
				} else if (element.equals(ANIMATION_LIST)) {
					return (String) element;
				}
				return "UNKNOWN ELEMENT";
			}
		});

		List<Object> dropDownItems = new ArrayList<>();		
		dropDownItems.add(ANIMATION_LIST);
		animationProjectCombo.setInput(dropDownItems);
		animationProjectCombo.setSelection(new StructuredSelection(ANIMATION_LIST));

	}
	/**
	 * Fill animation project combo viewer with selected project
	 * @param listAniProSelected selected projects for animation
	 * @param listAniDeltaModel list of VisualisationDeltaModel for visualization
	 */
	public void setAnimationProjectComboInput(List<VirSatProjectResource> listAniProSelected, List<VisualisationDeltaModel> listAniDeltaModel) {
		listAnimationProjectSelected.clear();
		listAnimationDeltaModel.clear();
		listAnimationProjectSelected = listAniProSelected;
		listAnimationDeltaModel = listAniDeltaModel;
		
		List<Object> dropDownItems = new ArrayList<>();		
		dropDownItems.add(ANIMATION_LIST);
		dropDownItems.addAll(listAniProSelected);
		
		animationProjectCombo.setInput(dropDownItems);
		animationProjectCombo.setSelection(new StructuredSelection(ANIMATION_LIST));
	}


	/**
	 * Create drop down list for delta model selection
	 */
	private void createDeltaCombo() {
		deltaCombo = new ComboViewer(northButtonComposite, SWT.READ_ONLY | SWT.DROP_DOWN);
		
		deltaCombo.setContentProvider(new ArrayContentProvider());
		deltaCombo.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof IResource) {
					IResource resource = (IResource) element;
					return resource.getName();
				} else if (element.equals(NO_DELTA_MODEL_TO_VISUALISE)) {
					return (String) element;
				}
				return "UNKNOWN ELEMENT";
			}
		});
		
		setDeltaComboInput();
		
		deltaCombo.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				VisualisationDeltaModel newDeltaModel = null;
				
				IStructuredSelection structuredSelection = (IStructuredSelection) event.getSelection();
				Object selectedDeltaModelObject = structuredSelection.getFirstElement();
				if (selectedDeltaModelObject instanceof IFile) {
					IFile selectedDeltaModelFile = (IFile) selectedDeltaModelObject;
					try {
						newDeltaModel = VisualisationDeltaModelIo.readModel(selectedDeltaModelFile.getContents());
					} catch (CoreException e) {
						Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Error retrieving delta model from file " + selectedDeltaModelFile.getName(), e));		
					}
				}
				
				if (newDeltaModel != currentDeltaModel) {
					currentDeltaModel = newDeltaModel;
					StartManagers.stopVis();
					StartManagers.startVis(currentlySelectedProject, currentDeltaModel);
					if (selectedDeltaModelObject instanceof IFile) {
						IFile selectedDeltaModelFile = (IFile) selectedDeltaModelObject;
						String ext = selectedDeltaModelFile.getFileExtension();
						if (ext != null) {
							createDiagram(ext);
						} else {
							createDiagram(EXT_INITIAL);
						}
					} else {
						createDiagram(EXT_INITIAL);
					}
				}
			}
		});
	}

	/**
	 * Creates a button with a dialog window to select root entities to visualise
	 */
	private void createCustomizeRootElementsButton() {
		filterRootElementsButton = new Button(northButtonComposite, SWT.PUSH);
		filterRootElementsButton.setText("Filter root elements");
		
		filterRootElementsButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (currentlySelectedProject != null) {
					VirSatResourceSet resourceSet = VirSatResourceSet.getResourceSet(currentlySelectedProject);
					Repository repository = resourceSet.getRepository();
					IStructuredContentProvider contentProvider = (repo) -> ((Repository) repo).getRootEntities().toArray();
					VirSatProjectLabelProvider labelProvider = new VirSatProjectLabelProvider();
					
					ListSelectionDialog rootSeisSelectionDialog = new ListSelectionDialog(northButtonComposite.getShell(), repository, contentProvider, labelProvider,
							"Please select root elements to visualize"
							);
					updateFilteredRootsWithCurrentRoots(repository.getRootEntities());
					rootSeisSelectionDialog.setInitialSelections(
							filteredRootSeisToVisualise != null ? getFilteredSeisToVisualise().toArray() : repository.getRootEntities().toArray());
					
					if (rootSeisSelectionDialog.open() == Dialog.OK) {
						Object[] selectedObjects = rootSeisSelectionDialog.getResult();
						if (selectedObjects.length == repository.getRootEntities().size()) {
							filteredRootSeisToVisualise = null;
						} else {
							Set<Object> selectedSeis = new HashSet<>(Arrays.asList(selectedObjects));
							filteredRootSeisToVisualise = new HashMap<>();
							repository.getRootEntities()
								.forEach(x -> filteredRootSeisToVisualise.put(x, selectedSeis.contains(x)));
						}
						StartManagers.applyRootEntitiesFilter(getFilteredSeisToVisualise());
					}
				}
			}
		});
		
	}

	/**
	 * @return A list of root SEIs that are selected for visualisation or null if filtering is not applied
	 */
	private List<StructuralElementInstance> getFilteredSeisToVisualise() {
		if (filteredRootSeisToVisualise == null) {
			return null;
		} else {
			return filteredRootSeisToVisualise.entrySet().stream()
					.filter(e -> e.getValue().equals(true))
					.map(e -> e.getKey())
					.collect(Collectors.toList());
		}
	}

	/**
	 * If filtering was applied, removes all filter records for root SEIs that are not present in the given list
	 * and adds all new root SEIs to the filter with true value (so that they will be shown)
	 * @param repositoryRootSeis list of procject repository root SEIs
	 */
	private void updateFilteredRootsWithCurrentRoots(List<StructuralElementInstance> repositoryRootSeis) {
		if (filteredRootSeisToVisualise != null) {
			filteredRootSeisToVisualise.entrySet().removeIf(e -> !repositoryRootSeis.contains(e.getKey()));
			repositoryRootSeis.stream()
				.filter(x -> !filteredRootSeisToVisualise.containsKey(x))
				.forEach(x -> filteredRootSeisToVisualise.put(x, true));
		}
	}
	
	/**
	 * Fill delta model combo viewer with delta models from currently selected project
	 */
	public void addDeltaComboInput() {
		List<Object> dropDownItems = new ArrayList<>();
		dropDownItems.add(NO_DELTA_MODEL_TO_VISUALISE);
		
		if (currentlySelectedProject != null) {
			IFolder folder = currentlySelectedProject.getFolder(CreateDeltaModelFolderHandler.VIS_DELTA_MODEL_FOLDER);
			if (folder.exists()) {
				IResource[] resources;
				try {
					resources = folder.members();
					dropDownItems.addAll(Arrays.asList(resources));
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Error retrieving delta folder contents", e));		
				}
			}
		}
		ISelection selected = deltaCombo.getSelection();
		deltaCombo.setInput(dropDownItems);
		deltaCombo.setSelection(selected);
	}
	/**
	 * Fill delta model combo viewer with delta models from currently selected project
	 */
	private void setDeltaComboInput() {
		List<Object> dropDownItems = new ArrayList<>();
		dropDownItems.add(NO_DELTA_MODEL_TO_VISUALISE);
		
		if (currentlySelectedProject != null) {
			IFolder folder = currentlySelectedProject.getFolder(CreateDeltaModelFolderHandler.VIS_DELTA_MODEL_FOLDER);
			if (folder.exists()) {
				IResource[] resources;
				try {
					resources = folder.members();
					dropDownItems.addAll(Arrays.asList(resources));
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Error retrieving delta folder contents", e));		
				}
			}
		}
		deltaCombo.setInput(dropDownItems);
		deltaCombo.setSelection(new StructuredSelection(NO_DELTA_MODEL_TO_VISUALISE));
	}
	
	/**
	 * Attach the listeners to the buttons to set the position of the active camera
	 */
	private void attachListenersToButtons() {
		final double CAMERA_OFFSET = 6d;
		frontButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				double[] position = {0d, 0d, CAMERA_OFFSET};
				double[] viewUpVector = {0d, 1d, 0d};
				VtkTreeManager.getInstance().setCameraPosition(position, viewUpVector);
				
			}
		});
		sideButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				double[] position = {CAMERA_OFFSET, 0d, 0d};
				double[] viewUpVector = {0d, 1d, 0d};
				VtkTreeManager.getInstance().setCameraPosition(position, viewUpVector);
				
			}
		});
		topButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				double[] position = {0d, CAMERA_OFFSET, 0d};
				double[] viewUpVector = {0d, 0d, 1d};
				VtkTreeManager.getInstance().setCameraPosition(position, viewUpVector);
				
			}
		});
		checkBoxLocalAxesEnables.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				Button btn = (Button) event.getSource();
				if (btn.getSelection()) {
					VtkTreeManager.getInstance().setLocalAxesVisible(true);
				} else {
					VtkTreeManager.getInstance().setLocalAxesVisible(false);
				}
			}
		});
		checkBoxGlobalAxesEnables.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				Button btn = (Button) event.getSource();
				if (btn.getSelection()) {
					VtkTreeManager.getInstance().setGlobalAxesVisible(true);
				} else {
					VtkTreeManager.getInstance().setGlobalAxesVisible(false);
				}
			}
		});
		
        animationRunButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				int numOfProject = listAnimationProjectSelected.size();
				if (numOfProject > 1 && numOfProject == (listAnimationDeltaModel.size() + 1)) {
					createDiagram(EXT_COMPARE_GEO);
					for (int i = 1; i < numOfProject; i++) {
						StartManagers.stopVis();
						StartManagers.startVis(listAnimationProjectSelected.get(i).getWrappedProject(), listAnimationDeltaModel.get(i - 1));
						sleep(ANIMATION_SLEEP_TIME);
					}	
					
				}
				
			}
		});
	}
	/**
	 * sleep for more clear visualisation
	 * @param mililis sleep time
	 */
    private void sleep(int mililis) {
    	try {
			Thread.sleep(mililis);
		} catch (InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Sleep was interrupted"));	
		}
    }

}
