/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.swtbot.test;

import static org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartName;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withMnemonic;

import java.lang.reflect.Field;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefViewer;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.forms.widgets.Section;
import org.hamcrest.Matcher;
import org.hamcrest.core.StringStartsWith;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.util.DVLMItemNaming;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.swtbot.util.SwtBotDebugHelper;
import de.dlr.sc.virsat.swtbot.util.SwtBotSection;
import de.dlr.sc.virsat.swtbot.util.SwtThreadWatcher;

/**
 * Base class for performing SWTBot tests.
 */
public class ASwtBotTestCase {
  
	protected static final String ENV_VARIABLE_SWTBOT_SCREENSHOT = "SWTBOT_SCREENSHOT";
	protected static final String ENV_VARIABLE_SWTBOT_SCREENSHOT_TRUE = "true";
	protected static final String SWTBOT_TEST_PROJECTNAME = "SWTBotTestProject";
	protected static final String SWTBOT_CANVAS_FIELD_REFLECTION_NAME = "canvas";
	protected static final int SWTBOT_GENERAL_WAIT_TIME = 50;  
	protected static final int MAX_TEST_CASE_TIMEOUT_SECONDS = 90;
	protected static final int EDIT_UNDO_MENU_POSITION = 0;
	protected static final int EDIT_REDO_MENU_POSITION = 1;
	
	protected SWTWorkbenchBot bot;
	protected IProject project;
	protected Concept conceptPs;
	protected Concept conceptTest; 
	protected int screenCaptureNumber = 1;
	protected WorkspaceBuilderInterlockedExecution buildCounter;
	protected enum DiagramType { interfaces, stateMachines }


	@Rule
	public DisableOnDebug testGlobalTimeoutRule = new DisableOnDebug(Timeout.seconds(MAX_TEST_CASE_TIMEOUT_SECONDS));
	
	@Rule 
	public TestName testMethodNameRule = new TestName();
	
	@Rule
	public TestWatcher testThreadWatcherRule = new SwtThreadWatcher();
	
	@Before
	public void before() throws Exception {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Before: " + testMethodNameRule.getMethodName()));

		bot = new SWTWorkbenchBot();

		buildCounter = new WorkspaceBuilderInterlockedExecution(); 
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: About to clean up editing domain"));
		VirSatEditingDomainClipBoard.INSTANCE.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();  
		VirSatTransactionalEditingDomain.clearAccumulatedRecourceChangeEvents();

		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Start loading concepts"));

		conceptPs = ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId() + "/concept/concept.xmi");
		conceptTest =  ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.tests.Activator.getPluginId() + "/concept/concept.xmi");
		
		closeWelcomeScreen();
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Open perspective"));
		openCorePerspective();
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Create a project"));
		buildCounter.executeInterlocked(() -> {
			project = createProjectWithConcepts(SWTBOT_TEST_PROJECTNAME);
		});
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Done with Before: " + testMethodNameRule.getMethodName()));
	}
	
	@After
	public void tearDown() throws CoreException {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Tear Down: " + testMethodNameRule.getMethodName()));
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Tear Down Builder States:\n" + SwtBotDebugHelper.printBuilderStates()));
		
		// Execute the tear down interlocked, since it removes the project and containing files from the workspace
		buildCounter.executeInterlocked(() -> {
			IWorkspace ws = ResourcesPlugin.getWorkspace();
			try {
				ws.run(monitor -> {
					// Create a screenshot of the last state of the application if it is desired
					if (ENV_VARIABLE_SWTBOT_SCREENSHOT_TRUE.equalsIgnoreCase(System.getenv(ENV_VARIABLE_SWTBOT_SCREENSHOT))) {
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Generating Screen Shot"));
						generateScreenshot();
					}
					
					// Now reset the workbench and remove the project
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Resetting Workbench"));
					
					//bot.resetWorkbench();					
					/* BUG: Resetting the workbench this way causes problems with Graphiti's UI palette during 
					 * test case tearDown. It seems as if SWTBot's closeAllEditors method invoked kills off its 
					 * own SWTBot Shell for not being attributed an Eclipse or Limbo Shell respectively.
					 * 
					 * Furthermore resetActivePerspective method was removed as it leads to thread locking 
					 * issues on linux systems.
					 */
					
					// Instead
					bot.saveAllEditors();
					bot.closeAllEditors();
					bot.defaultPerspective().activate();
					//
					
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Deleting project"));
					ws.getRoot().getProject(SWTBOT_TEST_PROJECTNAME).delete(true, monitor);
				}, null);
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "ASwtBotTestCase: Error when trying to clean up workspace", e));
			}
		});
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Done: " + testMethodNameRule.getMethodName()));
	}
	
	/**
	 * Generates a screenshot with an internally generated name
	 */
	protected void generateScreenshot() {
		String fileName = generateScreenshotFileName();
		bot.captureScreenshot(fileName);
	}
	
	/**
	 * Generates a screenshot filename
	 * @return a screenshot filename
	 */
	protected String generateScreenshotFileName() {
		return "./../swtbot/" + this.getClass().getSimpleName() + "." + testMethodNameRule.getMethodName() + screenCaptureNumber++ + ".png";
	}
	
	/**
	 * Expands a single navigator item
	 * @param item the item toe expand
	 */
	protected void expand(SWTBotTreeItem item) { 
		item.expand(); 
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Clicks on a combo box
	 * @param comboboxLabel the label of the combobox
	 */
	protected void clickOnComboBox(String comboboxLabel) {
		bot.checkBoxWithLabel(comboboxLabel).click();
		waitForEditingDomainAndUiThread();
	}

	/**
	 * saves the editors
	 */
	protected void save() {
		bot.saveAllEditors();
		waitForEditingDomainAndUiThread();
	}
	/**
	 * Opens an editor
	 * @param item the name of the element
	 * @return SWTBotTreeItem the opened item
	 */
	protected SWTBotTreeItem openEditor(SWTBotTreeItem item) {
		SWTBotTreeItem newItem = item.doubleClick();
		waitForEditor(item);
		waitForEditingDomainAndUiThread();
		return newItem;
	}
	
	/**
	 * We can only drop tree items to canvas elements. SWTBotGefViewer's canvas element is not 
	 * capable of beeing accessed directly from external classes though. Further more SWTBotGefViewer's 
	 * canvas member gets populated during runtime only. Hence we use reflections to get a hold on it here.
	 * 
	 * @param item Tree item that is beeing dragged
	 * @param diagramEditor Graphiti diagram editor which the tree item is beeing dragged onto
	 */
	protected void dragTreeItemToDiagramEditor(SWTBotTreeItem item, SWTBotGefEditor diagramEditor) {
		item.dragAndDrop(getCanvasForDiagramEditor(diagramEditor));
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * We can only drop tree items to canvas elements. SWTBotGefViewer's canvas element is not 
	 * capable of beeing accessed directly from external classes though. Further more SWTBotGefViewer's 
	 * canvas member gets populated during runtime only. Hence we use reflections to get a hold on it here.
	 * 
	 * @param item Tree item that is beeing dragged
	 * @param diagramEditor Graphiti diagram editor which the tree item is beeing dragged onto
	 */
	protected void dragTreeItemToDiagramEditor(SWTBotTreeItem item, SWTBotGefEditor diagramEditor, int x, int y) {
		item.dragAndDrop(getCanvasForDiagramEditor(diagramEditor), new Point(x, y));
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Drags a tree item onto a specific edit part element within a specified graphiti diagram editor 
	 * @param item Tree item that is beeing dragged
	 * @param diagramEditor Graphiti diagram editor in which the editPart is present
	 * @param editPart editPart which the tree item is beeing dragged onto
	 */
	protected void dragTreeItemOnToEditPart(SWTBotTreeItem item, SWTBotGefEditor diagramEditor, SWTBotGefEditPart editPart) {
		Point centerForEditPart = getCenterForEditPart(editPart);
		item.dragAndDrop(getCanvasForDiagramEditor(diagramEditor), centerForEditPart);
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Returns the SWTBot Graphiti canvas for specified Graphiti diagram editor
	 * @param diagramEditor Graphiti diagram editor to get canvas for
	 * @return SWTBot Graphiti canvas
	 */
	protected SWTBotGefFigureCanvas getCanvasForDiagramEditor(SWTBotGefEditor diagramEditor) {
		SWTBotGefViewer viewer = diagramEditor.getSWTBotGefViewer();
		SWTBotGefFigureCanvas canvas = null;
		
		for (Field f : viewer.getClass().getDeclaredFields()) {
			if (SWTBOT_CANVAS_FIELD_REFLECTION_NAME.equals(f.getName())) {
				// Here we're bypassing Java's OO-Security model, which is generally not advisable. It's meant to be a workaround to access
				// otherwise inaccessible fields.
				f.setAccessible(true);
				try {
					canvas = (SWTBotGefFigureCanvas) f.get(viewer);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Can not access SWTBotGefViewer element or do a proper cast to canvas type", e));
				}
			}
		}
		return canvas;
	}
	
	/**
	 * Returns the absolute rectangular bounds for a SWTBotGefEditpart element
	 * @param SWTBotEditPart edit part to get bounds for
	 * @return Rectangle bounds for SWTBotGefEditPart
	 */
	protected Rectangle getBoundsForEditPart(SWTBotGefEditPart swtBotEditPart) {	
		IFigure figure = ((GraphicalEditPart) swtBotEditPart.part()).getFigure();
		Rectangle bounds = figure.getBounds().getCopy();
		figure.translateToAbsolute(bounds);		
		return bounds;
	}
	
	/**
	 * Returns Point object with center coordinates of an edit part element
	 * @param editPart edit part to get coordinates for
	 * @return Point of center coordinates
	 */
	protected Point getCenterForEditPart(SWTBotGefEditPart editPart) {
		Rectangle boundsForEditPart = getBoundsForEditPart(editPart);		
		return new Point(boundsForEditPart.getCenter().x, boundsForEditPart.getCenter().y);
	}
	
	/**
	 * Deletes edit part element in specified graphiti diagram editor
	 * @param diagramEditor Diagram editor on which to perform delete operation
	 * @param editPartName Name of EditPart to be deleted
	 */
	protected void deleteEditPartInDiagramEditor(SWTBotGefEditor diagramEditor, String editPartName) {
		diagramEditor.getEditPart(editPartName).select();		
		diagramEditor.clickContextMenu("Delete");
		waitForEditingDomainAndUiThread();
		bot.button("Yes").click();
	}
	
	/**
	 * Removes edit part element in specified graphiti diagram editor
	 * @param diagramEditor Diagram editor on which to perform remove operation
	 * @param editPartName Name of EditPart to be removed
	 */
	protected void removeEditPartInDiagramEditor(SWTBotGefEditor diagramEditor, String editPartName) {
		diagramEditor.getEditPart(editPartName).select();		
		diagramEditor.clickContextMenu("Remove");
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Checks if specified tree item is present in tree view
	 * @param item Tree item
	 * @return true if tree item is present in tree view, false otherwise
	 */
	protected boolean isTreeItemPresentInTreeView(SWTBotTreeItem item) {
		try {
			item.select();
			return true;
		} catch (WidgetNotFoundException e) {
			return false;
		}
	}
	
	/**
	 * @param treeItem treeItem under whose document folder to create a new Diagram
	 * @param type specifies the Diagram type
	 */
	protected void createNewDiagramForTreeItem(SWTBotTreeItem treeItem, DiagramType type) {
		treeItem.getNode("documents").contextMenu("New").contextMenu("Other...").click();
		waitForEditingDomainAndUiThread();
		bot.tree().expandNode("VirSat").getNode("VirSat Diagram").select();
		bot.button("Next >").click();
		waitForEditingDomainAndUiThread();
		bot.comboBox().setSelection(type.toString());
		bot.button("Finish").click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Returns SWTBot Graphiti diagram editor with specified editor title that has been already opened
	 * @param editorTitle title of opened diagram editor
	 * @return diagram editor
	 */
	protected SWTBotGefEditor getOpenedDiagramEditorbyTitle(String editorTitle) {
		SWTGefBot gefBot = new SWTGefBot();
		SWTBotGefEditor editor = gefBot.gefEditor(editorTitle);
		return editor;
	}
	
	/**
	 * Checks if edit part is present in a diagram editor
	 * @param diagramEditor Diagram Editor for which to check if Edit Part is present
	 * @param editPartName Name of Edit Part
	 * @return true if Edit Part is present in specified Diagram Editor, else false
	 */
	protected boolean isEditPartPresentInDiagramEditor(SWTBotGefEditor diagramEditor, String editPartName) {
		return !(diagramEditor.getEditPart(editPartName) == null);
	}
	
	/**
	 * Waits for the editor of the passed item to open.
	 * The editor is identified using the name of the item,
	 * so it can't distinguish between editors of items with the same name!
	 * @param item the item whose editor we are waiting for
	 */
	protected void waitForEditor(SWTBotTreeItem item) {
		String label = item.getText();
		Matcher<IEditorReference> matcher = withPartName(StringStartsWith.startsWith(label + " -> "));
		bot.waitUntil(Conditions.waitForEditor(matcher));
	}
	
	/**
	 * closes the dialog and waits
	 * @param buttonName the name of the button which closes the dialog
	 */
	protected void closeDialog(String buttonName) {
		bot.button(buttonName).click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Closes the initial Welcome Screen
	 */
	protected void closeWelcomeScreen() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTestCase: Close welcome screen if it exists"));
		for (SWTBotView view : bot.views()) {
			if (view.getTitle().equals("Welcome")) {
				view.close();
			}
		}
	}
	
	/**
	 * Creates the project
	 * @param projectName the name of the project
	 */
	protected void createProject(String projectName) {
		bot.viewById("de.dlr.sc.virsat.project.ui.navigator.view").toolbarButton("New VirSat Project").click();
		bot.textWithLabel("Project name:").setText(projectName);
		bot.button("Finish").click();
		waitForEditingDomainAndUiThread();
		try {
			ResourcesPlugin.getWorkspace().run((monitor) -> {
				project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			}, null);
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "ASwtBotTest: Builder States \n" + SwtBotDebugHelper.printBuilderStates()));
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "ASwtBotTest: Failed Reading Project", e));
		}
	}

	/**
	 * Add all concepts
	 * @param projectName the name of the project
	 * @throws  
	 */
	protected void addAllConcepts(String projectName) {
		waitForEditingDomainAndUiThread();
		bot.viewById("de.dlr.sc.virsat.project.ui.navigator.view").setFocus();
		waitForEditingDomainAndUiThread();
		SWTBotTreeItem projectItem = bot.tree().expandNode(projectName);
		waitForEditingDomainAndUiThread();
		projectItem.getNode(Repository.class.getSimpleName()).doubleClick();
		waitForEditingDomainAndUiThread();
		bot.button("Add from Registry").click();
		bot.button("Select All").click();
		bot.button("OK").click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * deletes an item
	 * @param item the item to be deleted
	 */
	protected void delete(SWTBotTreeItem item) {
		item.contextMenu().menu("Delete").click(); 
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Updates the currently active Graphiti diagram editor
	 * @param diagramEditor editor to be updated
	 */
	protected void updateActiveDiagram(SWTBotGefEditor diagramEditor) {
		diagramEditor.clickContextMenu("Update");
		waitForEditingDomainAndUiThread();
	}	
	
	/**
	 * sets the value of the given index
	 * @param name  of the field
	 * @param value the value to be set
	 */
	protected void setText(String name, String value) {
		bot.textWithLabel(name).setText(value);
		waitForEditingDomainAndUiThread(); 
	}
	
	protected void openCorePerspective() {
		bot.menu("Window").menu("Perspective").menu("Open Perspective").menu("Other...").click();
		waitForEditingDomainAndUiThread();
		bot.table().select("VirSat - Core (default)");
		bot.button("Open").click();
		waitForEditingDomainAndUiThread(); 
	}
	
	/**
	 * renames an item
	 * @param item the item to be renamed
	 * @param newName the new name
	 */
	protected void rename(SWTBotTreeItem item, String newName) {
		openEditor(item);
		bot.textWithLabel("Name").setText(newName);
		waitForEditingDomainAndUiThread(); 
	}
	
	/**
	 * pastes an item
	 * @param item the item where we paste
	 */
	protected void paste(SWTBotTreeItem item) {
		buildCounter.executeInterlocked(() -> {
			item.contextMenu().menu("Paste").click();
		});
	}
	
	/**
	 * cuts an item
	 * @param item the item which we cut
	 */
	protected void cut(SWTBotTreeItem item) {
		item.contextMenu().menu("Cut").click();
		waitForEditingDomainAndUiThread();
	}

	/**
	 * puts the value into label
	 * @param labelName the name of the label
	 * @param value the value to be set 
	 */
	protected void renameField(String labelName, String value) {
		bot.textWithLabel(labelName).setText(value);
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * copies an item
	 * @param item the item which is to be copied
	 */
	protected void copy(SWTBotTreeItem item) {
		item.contextMenu().menu("Copy").click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * undoes a deleted item
	 * @param item the item to be undoed
	 */
	protected void undo(SWTBotTreeItem item) {
		item.contextMenu().menu("Undo").click();	
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Undo the last command made
	 */
	protected void undo() {
		SWTBotMenu editMenu = bot.shell().menu().menu("Edit").click();
		String undoCommandLabel = editMenu.menuItems().get(EDIT_UNDO_MENU_POSITION);
		editMenu.menu(undoCommandLabel).click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Redo the last command that was undone
	 */
	protected void redo() {
		SWTBotMenu editMenu = bot.shell().menu().menu("Edit").click();
		String redoCommandLabel = editMenu.menuItems().get(EDIT_REDO_MENU_POSITION);
		editMenu.menu(redoCommandLabel).click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Add all concepts
	 * @param projectName the name of the project
	 * @return the created project
	 */
	protected IProject createProjectWithConcepts(String projectName) {
		waitForEditingDomainAndUiThread();
		createProject(projectName);
		waitForEditingDomainAndUiThread();
		addAllConcepts(projectName);
		waitForEditingDomainAndUiThread();
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}
	
	/**
	 * Adds some  elements
	 * @param clazz the class of the element to be added
	 * @param concept the concept of the elements
	 * @param parentItem the place to add the new element
	 * @return the newly added element
	 */
	protected SWTBotTreeItem addElement(Class<?> clazz, Concept concept, SWTBotTreeItem parentItem) {
		IConceptTypeDefinition ctd = ActiveConceptHelper.getStructuralElement(concept, clazz.getSimpleName());
		if (ctd == null) {
			ctd = ActiveConceptHelper.getCategory(concept, clazz.getSimpleName());
		} 
		waitForEditingDomainAndUiThread();
		String menuName = concept.getDisplayName();
		if (menuName == null) {
			menuName = concept.getName();
		}
		
		final String finalMenuName = menuName;
		final IConceptTypeDefinition finalCtd = ctd;
		buildCounter.executeInterlocked(() -> {
			parentItem.contextMenu().menu(finalMenuName).menu("Add " + finalCtd.getName()).click();
		});
		
		String shortName = DVLMItemNaming.getAbbreviation((IQualifiedName) ctd, "");
		String newItemName = shortName + ": " + ctd.getName(); 
		SWTBotTreeItem newItem = parentItem.getNode(newItemName);
		return newItem;
	}
	
	/**
	 * @param item the editor page
	 * @param clazz the section of the table
	 * @return it returns the desired SWTBotTable
	 */
	protected SWTBotTable getSWTBotTable(SWTBotTreeItem item, Class<?> clazz) {
		openEditor(item);
		SwtBotSection composite = getSWTBotSection(clazz);
		SWTBotTable table = composite.getSWTBotTable();
		return table;
	}
	
	/**
	 * @param item the editor page
	 * @param tableName the name of the table
	 * @return it returns the desired SWTBotTable
	 */
	protected SWTBotTable getSWTBotTable(SWTBotTreeItem item, String tableName) {
		openEditor(item);
		SwtBotSection composite = getSWTBotSection(tableName);
		SWTBotTable table = composite.getSWTBotTable();
		return table;
	}
	
	/**
	 * @param clazz the class name of the section
	 * @return it returns the desired section name
	 */
	protected String getSectionName(Class<?> clazz) {
		return "Section for: " + clazz.getSimpleName();
	}
	
	/**
	 * @param clazz the class name of the section
	 * @return it returns the desired section
	 */
	protected SwtBotSection getSWTBotSection(Class<?> clazz) {
		String sectionName = getSectionName(clazz);
		return getSWTBotSection(sectionName);
	}
	
	/**
	 * @param sectionName the name of the section
	 * @return it returns the desired section
	 */
	protected SwtBotSection getSWTBotSection(String sectionName) {
		@SuppressWarnings("unchecked")
		Matcher<Section> matcher = allOf(widgetOfType(Section.class), withMnemonic(sectionName));
		SwtBotSection composite = new SwtBotSection(bot.widget(matcher, 0), matcher);
		return composite;
	}	
	
	/**
	 * @param table the table to make changes on
	 * @param rowPosition the row positon on the table
	 * @param columnPosition the column position on the table
	 * @param oldValue the old value to change
	 * @param newValue the new value
	 */
	protected void setTableValue(SWTBotTable table, int rowPosition, int columnPosition, String oldValue, String newValue) {
		table.doubleClick(rowPosition, columnPosition);
		bot.text(oldValue).setText(newValue);		
	}
	/**
	 * Opens the inheritance dialog and selects the element 
	 * @param element the element that inherits
	 * @param rootName the name of the root object which contains the inheriting element
	 * @param elementNames the path towards to inheriting object from root object
	 */
	public void addInheritance(SWTBotTreeItem element, String rootName, String... elementNames) {
		openEditor(element);
		bot.button("Add Inheritance").click();
		
		SWTBotTreeItem dialogueElement = bot.tree().getTreeItem(rootName);
		
		for (String elementName : elementNames) {
			dialogueElement.expand();
			dialogueElement = dialogueElement.getNode(elementName);
		}
		
		dialogueElement.select();
		
		bot.button("OK").click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * returns the repository
	 * @param project the project
	 * @return rep the repository
	 */
	public static Repository getRepository(IProject project) {
		VirSatResourceSet resSetRepositoryTarget = VirSatResourceSet.getResourceSet(project);
		Repository rep = resSetRepositoryTarget.getRepository();
		return rep;		
	}
	
	/**
	 * A Runnable lock to make sure that the display thread executed all messages
	 */
	static class WaitForRunnable implements Runnable {
		
		private Boolean gotExecuted = false;
		
		@Override
		public synchronized void run() {
			gotExecuted = true;
			notify();
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Wait For Runnable UI Thread: " + Thread.currentThread()));
		}
		
		/**
		 * Call this method to make sure the runnable got executed
		 * THis method blocks until the runnable got called.
		 */
		synchronized void waitForExecution() {
			while (!gotExecuted) {
				try {
					wait(SWTBOT_GENERAL_WAIT_TIME);
				} catch (InterruptedException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Could not go to sleep Thread: " + Thread.currentThread()));
				}
			}
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Runnable got Executed Thread: " + Thread.currentThread()));
		}
	}
	
	/**
	 * This method runs a log command on the UI thread in synced/blocking mode. The UI Thread needs to have finished before
	 * the code can return from here. The method also checks if the queue of notifications in the Editing Domain is empty
	 * @throws InterruptedException 
	 */
	protected static void waitForEditingDomainAndUiThread() {
		
		// Start waiting for the Editing Domain
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		// Wait a little time, so we give other UI threads / runnables the chance to get started or queued in between
		try {
			Thread.sleep(SWTBOT_GENERAL_WAIT_TIME);
		} catch (InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "SWTBot Test: Thread Interrupted", e));
		}

		// Now throw in a runnable to the queue but execute it blocking, thus this method will only leave in case
		// all other runnables queued before have been executed.
		WaitForRunnable defaultDisplayWaitFor = new WaitForRunnable();
		Display.getDefault().asyncExec(defaultDisplayWaitFor);
		defaultDisplayWaitFor.waitForExecution();

		// Add some grace time just for the res
		try {
			Thread.sleep(SWTBOT_GENERAL_WAIT_TIME);
		} catch (InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "SWTBot Test: Thread Interrupted", e));
		}
	}
	
	/**
	 * This class is used to interlock an execution of code with the Workspace Builders.
	 * This is useful when e.g. saving editors and making sure, everything in the files
	 * and UI is updated.
	 */
	static class WorkspaceBuilderInterlockedExecution {
		
		/**
		 * The runnable in this method is interlocked with the execution of the workspace builders.
		 * First the method will join all scheduled workspace builders and wait for the UI to refresh.
		 * Then it will execute the runnable and wait again to join builders and UI refresh.
		 * @param runnable the runnable to be executed
		 */
		public void executeInterlocked(Runnable runnable) {
			try {
				// Now wait that already scheduled jobs are definitely done and wait for the ED and UI thread to finalize
				Activator.getDefault().getLog().log(new Status(Status.OK, Activator.getPluginId(), "ASwtBotTest.InterlockedBuildCounter: Wait for jobs to be done before execution and counting"));
				Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
				waitForEditingDomainAndUiThread();
	
				// Now execute the runnable and wait for some time to allow for builders to be scheduled
				Activator.getDefault().getLog().log(new Status(Status.OK, Activator.getPluginId(), "ASwtBotTest.InterlockedBuildCounter: About to execute interlocked runnable"));
				runnable.run();
				Thread.sleep(SWTBOT_GENERAL_WAIT_TIME);

				// Now wait that all scheduled builders are done and update the UI
				Activator.getDefault().getLog().log(new Status(Status.OK, Activator.getPluginId(), "ASwtBotTest.InterlockedBuildCounter: Wait for jobs to be done after execution and counting"));
				Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
				waitForEditingDomainAndUiThread();
				
				Activator.getDefault().getLog().log(new Status(Status.OK, Activator.getPluginId(), "ASwtBotTest.InterlockedBuildCounter: Counted all VirSat Builders and waited for all other builders"));
			} catch (InterruptedException e) {
				Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "ASwtBotTest.InterlockedBuildCounter: Thread got interupted", e));
			}
		}
	}
}
