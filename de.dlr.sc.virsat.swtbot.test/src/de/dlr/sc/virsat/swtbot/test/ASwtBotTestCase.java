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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
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
import org.junit.rules.Timeout;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.util.DVLMItemNaming;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.swtbot.util.SWTBotSection;
import de.dlr.sc.virsat.swtbot.util.VirSatWaitForProjectBuilder;

/**
 * Base class for performing SWTBot tests.
 * @author muel_s8
 *
 */
public class ASwtBotTestCase {
	
	private static final String ENV_VARIABLE_SWTBOT_SCREENSHOT = "SWTBOT_SCREENSHOT";
	private static final String ENV_VARIABLE_SWTBOT_SCREENSHOT_TRUE = "true";
	
	public static final int SWTBOT_GENERAL_WAIT_TIME = 250;
	
	protected SWTWorkbenchBot bot;
	protected Concept conceptPs;
	protected Concept conceptTest;
	
	protected static final String PROJECTNAME = "SWTBotTestProject";
	protected IProject project;
	
	protected static final int MAX_TEST_CASE_TIMEOUT_SECONDS = 90;
	
	@Rule
	public DisableOnDebug globalTimeout = new DisableOnDebug(Timeout.seconds(MAX_TEST_CASE_TIMEOUT_SECONDS));
	
	@Rule 
	public TestName testMethodName = new TestName();
	
	private int captureNumber = 1;
	
	@Before
	public void before() throws Exception {
		bot = new SWTWorkbenchBot();

		conceptPs = ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId() + "/concept/concept.xmi");
		conceptTest =  ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.tests.Activator.getPluginId() + "/concept/concept.xmi");
		
		for (SWTBotView view : bot.views()) {
			if (view.getTitle().equals("Welcome")) {
				bot.viewByTitle("Welcome").close();
			}
		}
		
		project = createProjectWithConcepts(PROJECTNAME);
	}
	
	@After
	public void tearDown() throws CoreException {
		if (ENV_VARIABLE_SWTBOT_SCREENSHOT_TRUE.equalsIgnoreCase(System.getenv(ENV_VARIABLE_SWTBOT_SCREENSHOT))) {
			generateScreenshot();
		}
		
		bot.closeAllEditors();
		ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECTNAME).delete(true, null);
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
		return "./../swtbot/" + this.getClass().getSimpleName() + "." + testMethodName.getMethodName() + captureNumber++ + ".png";
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
	 * @author bell_Er
	 *
	 */
	protected void clickOnComboBox(String comboboxLabel) {
		bot.checkBoxWithLabel(comboboxLabel).click();
		waitForEditingDomainAndUiThread();
	}

	/**
	 * saves the editors
	 *
	 */
	protected void save() {
		bot.saveAllEditors();
		waitForEditingDomainAndUiThread();
	}
	/**
	 * Opens an editor
	 * @param item the name of the element
	 * @author bell_Er
	 * @return SWTBotTreeItem the opened item
	 *
	 */
	protected SWTBotTreeItem openEditor(SWTBotTreeItem item) {
		SWTBotTreeItem newItem = item.doubleClick();
		waitForEditor(item);
		waitForEditingDomainAndUiThread();
		return newItem;
	}
	
	/**
	 * Waits for the editor of the passed item to open.
	 * The editor is identified using the name of the item,
	 * so it cant distinguish between editors of items with the same name!
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
	 * @author bell_Er
	 *
	 */
	protected void closeDialog(String buttonName) {
		bot.button(buttonName).click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Creates the project
	 * @param projectName the name of the project
	 * @author bell_Er
	 *
	 */
	protected void createProject(String projectName) {
		bot.viewById("de.dlr.sc.virsat.project.ui.navigator.view").toolbarButton("New VirSat Project").click();
		bot.textWithLabel("Project name:").setText(projectName);
		bot.button("Finish").click();
		waitForEditingDomainAndUiThread();
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}

	/**
	 * Add all concepts
	 * @param projectName the name of the project
	 * @author bell_Er
	 * @throws  
	 *
	 */
	protected void addAllConcepts(String projectName) {
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
	 * @author bell_Er
	 *
	 */
	protected void delete(SWTBotTreeItem item) {
		item.contextMenu().menu("Delete").click(); 
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * sets the value of the given index
	 * @param name  of the field
	 * @param value the value to be set
	 * @author bell_Er
	 *
	 */
	protected void setText(String name, String value) {
		bot.textWithLabel(name).setText(value);
		waitForEditingDomainAndUiThread(); 
	}
	
	/**
	 * renames an item
	 * @param item the item to be renamed
	 * @param newName the new name
	 * @author bell_Er
	 *
	 */
	protected void rename(SWTBotTreeItem item, String newName) {
		openEditor(item);
		bot.textWithLabel("Name").setText(newName);
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * pastes an item
	 * @param item the item where we paste
	 * @author bell_Er
	 *
	 */
	protected void paste(SWTBotTreeItem item) {
		item.contextMenu().menu("Paste").click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * cuts an item
	 * @param item the item which we cut
	 * @author bell_Er
	 *
	 */
	protected void cut(SWTBotTreeItem item) {
		item.contextMenu().menu("Cut").click();
		waitForEditingDomainAndUiThread();
	}

	/**
	 * puts the value into label
	 * @param labelName the name of the label
	 * @param value the value to be set 
	 * @author bell_Er
	 *
	 */
	protected void renameField(String labelName, String value) {
		bot.textWithLabel(labelName).setText(value);
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * copies an item
	 * @param item the item which is to be copied
	 * @author bell_Er
	 *
	 */
	protected void copy(SWTBotTreeItem item) {
		item.contextMenu().menu("Copy").click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * undoes a deleted item
	 * @param item the item to be undoed
	 * @author bell_Er
	 *
	 */
	protected void undo(SWTBotTreeItem item) {
		item.contextMenu().menu("Undo").click();	
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Add all concepts
	 * @param projectName the name of the project
	 * @return the created project
	 * @author bell_Er
	 *
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
	 * This method makes sure all builders are executed.
	 * It further checks that all UI Threads ahve executed.
	 */
	protected void waitForAllBuildersAndUiThread() {
		bot.waitUntil(new VirSatWaitForProjectBuilder(1));
		bot.waitUntil(Conditions.waitForJobs(ResourcesPlugin.FAMILY_AUTO_BUILD, "eclipse auto builders (inheritance builder)"));
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Adds some  elements
	 * @param clazz the class of the element to be added
	 * @param concept the concept of the elements
	 * @param parentItem the place to add the new element
	 * @return the newly added element
	 * @author bell_Er
	 *
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
		
		parentItem.contextMenu().menu(menuName).menu("Add " + ctd.getName()).click();
		waitForEditingDomainAndUiThread();
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
		SWTBotSection composite = getSWTBotSection(clazz);
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
		SWTBotSection composite = getSWTBotSection(tableName);
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
	protected SWTBotSection getSWTBotSection(Class<?> clazz) {
		String sectionName = getSectionName(clazz);
		return getSWTBotSection(sectionName);
	}
	
	/**
	 * @param sectionName the name of the section
	 * @return it returns the desired section
	 */
	protected SWTBotSection getSWTBotSection(String sectionName) {
		@SuppressWarnings("unchecked")
		Matcher<Section> matcher = allOf(widgetOfType(Section.class), withMnemonic(sectionName));
		SWTBotSection composite = new SWTBotSection(bot.widget(matcher, 0), matcher);
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
		table.click(rowPosition, columnPosition);
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
	 *
	 */
	public static Repository getRepository(IProject project) {
		VirSatResourceSet resSetRepositoryTarget = VirSatResourceSet.getResourceSet(project);
		Repository rep = resSetRepositoryTarget.getRepository();
		return rep;
		
	}
	
	
	
	/**
	 * A Runnable lock to make sure that the display thread executed all messages
	 *
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
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		// Wait a little time, so we give other UI threads / runnables to get started or queued in between
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
}
