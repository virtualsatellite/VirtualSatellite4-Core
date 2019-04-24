package org.eclipse.emf.codegen.ecore.templates.editor;

import org.eclipse.emf.codegen.ecore.genmodel.*;

public class Advisor
{
  protected static String nl;
  public static synchronized Advisor create(String lineSeparator)
  {
    nl = lineSeparator;
    Advisor result = new Advisor();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + "import java.io.File;" + NL + "import java.util.Arrays;";
  protected final String TEXT_7 = NL + "import java.util.Iterator;";
  protected final String TEXT_8 = NL + "import java.util.List;";
  protected final String TEXT_9 = NL + NL + "import org.eclipse.equinox.app.IApplication;" + NL + "import org.eclipse.equinox.app.IApplicationContext;" + NL + "" + NL + "import org.eclipse.jface.action.GroupMarker;" + NL + "import org.eclipse.jface.action.IAction;" + NL + "import org.eclipse.jface.action.IMenuManager;" + NL + "import org.eclipse.jface.action.MenuManager;" + NL + "import org.eclipse.jface.action.Separator;" + NL + "import org.eclipse.jface.dialogs.MessageDialog;" + NL + "import org.eclipse.jface.window.Window;";
  protected final String TEXT_10 = NL + "import org.eclipse.swt.SWT;";
  protected final String TEXT_11 = NL + "import org.eclipse.swt.graphics.Point;" + NL + "import org.eclipse.swt.widgets.Display;";
  protected final String TEXT_12 = NL + "import org.eclipse.swt.widgets.FileDialog;" + NL + "import org.eclipse.swt.widgets.Shell;";
  protected final String TEXT_13 = NL + "import org.eclipse.ui.IEditorDescriptor;" + NL + "import org.eclipse.ui.IFolderLayout;" + NL + "import org.eclipse.ui.IPageLayout;" + NL + "import org.eclipse.ui.IPerspectiveFactory;" + NL + "import org.eclipse.ui.IWorkbench;" + NL + "import org.eclipse.ui.IWorkbenchActionConstants;" + NL + "import org.eclipse.ui.IWorkbenchPage;" + NL + "import org.eclipse.ui.IWorkbenchWindow;" + NL + "import org.eclipse.ui.PartInitException;" + NL + "import org.eclipse.ui.PlatformUI;" + NL + "import org.eclipse.ui.actions.ActionFactory;";
  protected final String TEXT_14 = NL + "import org.eclipse.ui.actions.ContributionItemFactory;";
  protected final String TEXT_15 = NL + "import org.eclipse.ui.application.ActionBarAdvisor;" + NL + "import org.eclipse.ui.application.IActionBarConfigurer;" + NL + "import org.eclipse.ui.application.IWorkbenchConfigurer;" + NL + "import org.eclipse.ui.application.IWorkbenchWindowConfigurer;" + NL + "import org.eclipse.ui.application.WorkbenchAdvisor;" + NL + "import org.eclipse.ui.application.WorkbenchWindowAdvisor;" + NL + "" + NL + "import org.eclipse.emf.common.ui.URIEditorInput;" + NL + "import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;" + NL + "import org.eclipse.emf.common.util.URI;";
  protected final String TEXT_16 = NL + "import org.eclipse.emf.common.util.UniqueEList;";
  protected final String TEXT_17 = NL + "import org.eclipse.emf.edit.ui.action.LoadResourceAction;" + NL + "import org.eclipse.emf.edit.ui.util.EditUIUtil;" + NL + "" + NL + "import ";
  protected final String TEXT_18 = ";";
  protected final String TEXT_19 = NL + NL + NL + "/**" + NL + " * Customized {@link WorkbenchAdvisor} for the RCP application." + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public final class ";
  protected final String TEXT_20 = " extends WorkbenchAdvisor" + NL + "{";
  protected final String TEXT_21 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final String copyright = ";
  protected final String TEXT_22 = ";";
  protected final String TEXT_23 = NL;
  protected final String TEXT_24 = NL + "\t/**" + NL + "\t * The default file extension filters for use in dialogs." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_25 = NL + "\tprivate static final String[] FILE_EXTENSION_FILTERS = ";
  protected final String TEXT_26 = "(String[])";
  protected final String TEXT_27 = ".FILE_EXTENSION_FILTERS.toArray(new String[0]);";
  protected final String TEXT_28 = NL + "\tprivate static final String[] FILE_EXTENSION_FILTERS = getFileExtensionFilters();" + NL + "" + NL + "\t/**" + NL + "\t * Returns the default file extension filters. This method should only be used to initialize {@link #FILE_EXTENSION_FILTERS}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static String[] getFileExtensionFilters()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_29 = " result = new ";
  protected final String TEXT_30 = "();";
  protected final String TEXT_31 = NL + "\t\tresult.addAll(";
  protected final String TEXT_32 = ".FILE_EXTENSION_FILTERS);";
  protected final String TEXT_33 = NL + "\t\treturn ";
  protected final String TEXT_34 = "(String[])";
  protected final String TEXT_35 = "result.toArray(new String[0]);" + NL + "\t}";
  protected final String TEXT_36 = " ";
  protected final String TEXT_37 = NL + NL + "\t/**" + NL + "\t * This looks up a string in the plugin's plugin.properties file." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static String getString(String key)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_38 = ".INSTANCE.getString(key);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This looks up a string in plugin.properties, making a substitution." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static String getString(String key, Object s1)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_39 = ".INSTANCE.getString(key, new Object [] { s1 });" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * RCP's application" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static class Application implements IApplication" + NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic Object start(IApplicationContext context) throws Exception" + NL + "\t\t{" + NL + "\t\t\tWorkbenchAdvisor workbenchAdvisor = new ";
  protected final String TEXT_40 = "();" + NL + "\t\t\tDisplay display = PlatformUI.createDisplay();" + NL + "\t\t\ttry" + NL + "\t\t\t{" + NL + "\t\t\t\tint returnCode = PlatformUI.createAndRunWorkbench(display, workbenchAdvisor);" + NL + "\t\t\t\tif (returnCode == PlatformUI.RETURN_RESTART)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\treturn IApplication.EXIT_RESTART;" + NL + "\t\t\t\t}" + NL + "\t\t\t\telse" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\treturn IApplication.EXIT_OK;" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t\tfinally" + NL + "\t\t\t{" + NL + "\t\t\t\tdisplay.dispose();" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * @see org.eclipse.equinox.app.IApplication#stop()" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic void stop()" + NL + "\t\t{" + NL + "\t\t\t// Do nothing." + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * RCP's perspective" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static class Perspective implements IPerspectiveFactory" + NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * Perspective ID" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic static final String ID_PERSPECTIVE = \"";
  protected final String TEXT_41 = "Perspective\";";
  protected final String TEXT_42 = NL + NL + "\t\t/**" + NL + "\t\t * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic void createInitialLayout(IPageLayout layout)" + NL + "\t\t{" + NL + "\t\t\tlayout.setEditorAreaVisible(true);" + NL + "\t\t\tlayout.addPerspectiveShortcut(ID_PERSPECTIVE);" + NL + "" + NL + "\t\t\tIFolderLayout right = layout.createFolder(\"right\", IPageLayout.RIGHT, (float)0.66, layout.getEditorArea());";
  protected final String TEXT_43 = NL + "\t\t\tright.addView(IPageLayout.ID_OUTLINE);" + NL + "" + NL + "\t\t\tIFolderLayout bottonRight = layout.createFolder(\"bottonRight\", IPageLayout.BOTTOM, (float)0.60, \"right\");";
  protected final String TEXT_44 = NL + "\t\t\tbottonRight.addView(IPageLayout.ID_PROP_SHEET);" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * RCP's window advisor" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static class WindowAdvisor extends WorkbenchWindowAdvisor" + NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * @see WorkbenchWindowAdvisor#WorkbenchWindowAdvisor(org.eclipse.ui.application.IWorkbenchWindowConfigurer)" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic WindowAdvisor(IWorkbenchWindowConfigurer configurer)" + NL + "\t\t{" + NL + "\t\t\tsuper(configurer);" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t/**" + NL + "\t\t * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowOpen()" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */";
  protected final String TEXT_45 = NL + "\t\t@Override";
  protected final String TEXT_46 = NL + "\t\tpublic void preWindowOpen()" + NL + "\t\t{" + NL + "\t\t\tIWorkbenchWindowConfigurer configurer = getWindowConfigurer();" + NL + "\t\t\tconfigurer.setInitialSize(new Point(600, 450));" + NL + "\t\t\tconfigurer.setShowCoolBar(false);" + NL + "\t\t\tconfigurer.setShowStatusLine(true);" + NL + "\t\t\tconfigurer.setTitle(getString(\"_UI_Application_title\"));";
  protected final String TEXT_47 = NL + "\t\t}" + NL + "\t\t" + NL + "\t\t/**" + NL + "\t\t * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#createActionBarAdvisor(org.eclipse.ui.application.IActionBarConfigurer)" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */";
  protected final String TEXT_48 = NL + "\t\t@Override";
  protected final String TEXT_49 = NL + "\t\tpublic ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer)" + NL + "\t\t{" + NL + "\t\t\treturn new WindowActionBarAdvisor(configurer);" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * RCP's action bar advisor" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static class WindowActionBarAdvisor extends ActionBarAdvisor" + NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * @see ActionBarAdvisor#ActionBarAdvisor(org.eclipse.ui.application.IActionBarConfigurer)" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic WindowActionBarAdvisor(IActionBarConfigurer configurer)" + NL + "\t\t{" + NL + "\t\t\tsuper(configurer);" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t/**" + NL + "\t\t * @see org.eclipse.ui.application.ActionBarAdvisor#fillMenuBar(org.eclipse.jface.action.IMenuManager)" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */";
  protected final String TEXT_50 = NL + "\t\t@Override";
  protected final String TEXT_51 = NL + "\t\tprotected void fillMenuBar(IMenuManager menuBar)" + NL + "\t\t{" + NL + "\t\t\tIWorkbenchWindow window = getActionBarConfigurer().getWindowConfigurer().getWindow();" + NL + "\t\t\tmenuBar.add(createFileMenu(window));" + NL + "\t\t\tmenuBar.add(createEditMenu(window));" + NL + "\t\t\tmenuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));" + NL + "\t\t\tmenuBar.add(createWindowMenu(window));" + NL + "\t\t\tmenuBar.add(createHelpMenu(window));\t\t\t\t\t" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t/**" + NL + "\t\t * Creates the 'File' menu." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected IMenuManager createFileMenu(IWorkbenchWindow window)" + NL + "\t\t{" + NL + "\t\t\tIMenuManager menu = new MenuManager(getString(\"_UI_Menu_File_label\"),";
  protected final String TEXT_52 = NL + "\t\t\tIWorkbenchActionConstants.M_FILE);    " + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));" + NL + "\t" + NL + "\t\t\tIMenuManager newMenu = new MenuManager(getString(\"_UI_Menu_New_label\"), \"new\");";
  protected final String TEXT_53 = NL + "\t\t\tnewMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));" + NL + "\t" + NL + "\t\t\tmenu.add(newMenu);" + NL + "\t\t\tmenu.add(new Separator());" + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));" + NL + "\t\t\tmenu.add(new Separator());" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.CLOSE.create(window));" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.CLOSE_ALL.create(window));" + NL + "\t\t\tmenu.add(new Separator());" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.SAVE.create(window));" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.SAVE_AS.create(window));" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.SAVE_ALL.create(window));" + NL + "\t\t\tmenu.add(new Separator());" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.QUIT.create(window));" + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));" + NL + "\t\t\treturn menu;" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Creates the 'Edit' menu." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected IMenuManager createEditMenu(IWorkbenchWindow window)" + NL + "\t\t{" + NL + "\t\t\tIMenuManager menu = new MenuManager(getString(\"_UI_Menu_Edit_label\"),";
  protected final String TEXT_54 = NL + "\t\t\tIWorkbenchActionConstants.M_EDIT);" + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_START));" + NL + "\t" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.UNDO.create(window));" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.REDO.create(window));" + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.UNDO_EXT));" + NL + "\t\t\tmenu.add(new Separator());" + NL + "\t" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.CUT.create(window));" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.COPY.create(window));" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.PASTE.create(window));" + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.CUT_EXT));" + NL + "\t\t\tmenu.add(new Separator());" + NL + "\t" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.DELETE.create(window));" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.SELECT_ALL.create(window));" + NL + "\t\t\tmenu.add(new Separator());" + NL + "\t" + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.ADD_EXT));" + NL + "\t" + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_END));" + NL + "\t\t\tmenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));" + NL + "\t\t\treturn menu;" + NL + "\t\t}" + NL + "\t" + NL + "\t\t/**" + NL + "\t\t * Creates the 'Window' menu." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected IMenuManager createWindowMenu(IWorkbenchWindow window)" + NL + "\t\t{" + NL + "\t\t\tIMenuManager menu = new MenuManager(getString(\"_UI_Menu_Window_label\"),";
  protected final String TEXT_55 = NL + "\t\t\tIWorkbenchActionConstants.M_WINDOW);" + NL + "\t" + NL + "\t\t\taddToMenuAndRegister(menu, ActionFactory.OPEN_NEW_WINDOW.create(window));" + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));";
  protected final String TEXT_56 = NL + "\t\t\tmenu.add(ContributionItemFactory.OPEN_WINDOWS.create(window));";
  protected final String TEXT_57 = NL + "\t" + NL + "\t\t\treturn menu;" + NL + "\t\t}" + NL + "\t" + NL + "\t\t/**" + NL + "\t\t * Creates the 'Help' menu." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected IMenuManager createHelpMenu(IWorkbenchWindow window)" + NL + "\t\t{" + NL + "\t\t\tIMenuManager menu = new MenuManager(getString(\"_UI_Menu_Help_label\"), IWorkbenchActionConstants.M_HELP);";
  protected final String TEXT_58 = NL + "\t\t\t// Welcome or intro page would go here" + NL + "\t\t\t// Help contents would go here" + NL + "\t\t\t// Tips and tricks page would go here" + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.HELP_START));" + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.HELP_END));" + NL + "\t\t\tmenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));" + NL + "\t\t\treturn menu;" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t/**" + NL + "\t\t * Adds the specified action to the given menu and also registers the action with the" + NL + "\t\t * action bar configurer, in order to activate its key binding." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected void addToMenuAndRegister(IMenuManager menuManager, IAction action)" + NL + "\t\t{" + NL + "\t\t\tmenuManager.add(action);" + NL + "\t\t\tgetActionBarConfigurer().registerGlobalAction(action);" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * About action for the RCP application." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static class AboutAction extends WorkbenchWindowActionDelegate" + NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic void run(IAction action)" + NL + "\t\t{" + NL + "\t\t\tMessageDialog.openInformation(getWindow().getShell(), getString(\"_UI_About_title\"),";
  protected final String TEXT_59 = NL + "\t\t\tgetString(\"_UI_About_text\"));";
  protected final String TEXT_60 = NL + "\t\t}" + NL + "\t}" + NL + "\t";
  protected final String TEXT_61 = NL + "\t/**" + NL + "\t * Open action for the objects from the ";
  protected final String TEXT_62 = " model." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static class OpenAction extends WorkbenchWindowActionDelegate" + NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * Opens the editors for the files selected using the file dialog." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic void run(IAction action)" + NL + "\t\t{" + NL + "\t\t\tString[] filePaths = openFilePathDialog(getWindow().getShell(), SWT.OPEN, null);" + NL + "\t\t\tif (filePaths.length > 0)" + NL + "\t\t\t{" + NL + "\t\t\t\topenEditor(getWindow().getWorkbench(), URI.createFileURI(filePaths[0]));" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_63 = NL + "\t" + NL + "\t/**" + NL + "\t * Open URI action for the objects from the ";
  protected final String TEXT_64 = " model." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static class OpenURIAction extends WorkbenchWindowActionDelegate" + NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * Opens the editors for the files selected using the LoadResourceDialog." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic void run(IAction action)" + NL + "\t\t{" + NL + "\t\t\tLoadResourceAction.LoadResourceDialog loadResourceDialog = new LoadResourceAction.LoadResourceDialog(getWindow().getShell());" + NL + "\t\t\tif (Window.OK == loadResourceDialog.open())" + NL + "\t\t\t{";
  protected final String TEXT_65 = NL + "\t\t\t\tfor (URI uri : loadResourceDialog.getURIs())" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\topenEditor(getWindow().getWorkbench(), uri);" + NL + "\t\t\t\t}";
  protected final String TEXT_66 = NL + "\t\t\t\tfor (Iterator i = loadResourceDialog.getURIs().iterator(); i.hasNext();)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\topenEditor(getWindow().getWorkbench(), (URI)i.next());" + NL + "\t\t\t\t}";
  protected final String TEXT_67 = NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}" + NL + "\t";
  protected final String TEXT_68 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static String[] openFilePathDialog(Shell shell, int style, String[] fileExtensionFilters)" + NL + "\t{" + NL + "\t\treturn openFilePathDialog(shell, style, fileExtensionFilters, (style & SWT.OPEN) != 0, (style & SWT.OPEN) != 0, (style & SWT.SAVE) != 0);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static String[] openFilePathDialog(Shell shell, int style, String[] fileExtensionFilters, boolean includeGroupFilter, boolean includeAllFilter, boolean addExtension)" + NL + "\t{" + NL + "\t\tFileDialog fileDialog = new FileDialog(shell, style);" + NL + "\t\tif (fileExtensionFilters == null)" + NL + "\t\t{" + NL + "\t\t\tfileExtensionFilters = FILE_EXTENSION_FILTERS;" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t// If requested, augment the file extension filters by adding a group of all the other filters (*.ext1;*.ext2;...)" + NL + "\t\t// at the beginning and/or an all files wildcard (*.*) at the end." + NL + "\t\t//" + NL + "\t\tincludeGroupFilter &= fileExtensionFilters.length > 1;" + NL + "\t\tint offset = includeGroupFilter ? 1 : 0;" + NL + "\t\t" + NL + "\t\tif (includeGroupFilter || includeAllFilter)" + NL + "\t\t{" + NL + "\t\t\tint size = fileExtensionFilters.length + offset + (includeAllFilter ? 1 : 0);" + NL + "\t\t\tString[] allFilters = new String[size];" + NL + "\t\t\t";
  protected final String TEXT_69 = " group = includeGroupFilter ? new ";
  protected final String TEXT_70 = "() : null;" + NL + "\t\t\t" + NL + "\t\t\tfor (int i = 0; i < fileExtensionFilters.length; i++)" + NL + "\t\t\t{" + NL + "\t\t\t\tif (includeGroupFilter)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tif (i != 0)" + NL + "\t\t\t\t\t{" + NL + "\t\t\t\t\t\tgroup.append(';');" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\tgroup.append(fileExtensionFilters[i]);" + NL + "\t\t\t\t}" + NL + "\t\t\t\tallFilters[i + offset] = fileExtensionFilters[i];" + NL + "\t\t\t}" + NL + "\t\t\t" + NL + "\t\t\tif (includeGroupFilter)" + NL + "\t\t\t{" + NL + "\t\t\t\tallFilters[0] = group.toString();" + NL + "\t\t\t}" + NL + "\t\t\tif (includeAllFilter)" + NL + "\t\t\t{" + NL + "\t\t\t\tallFilters[allFilters.length - 1] = \"*.*\";";
  protected final String TEXT_71 = NL + "\t\t\t}" + NL + "\t\t\t" + NL + "\t\t\tfileDialog.setFilterExtensions(allFilters);" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{" + NL + "\t\t\tfileDialog.setFilterExtensions(fileExtensionFilters);" + NL + "\t\t}" + NL + "\t\tfileDialog.open();" + NL + "\t\t" + NL + "\t\tString[] filenames = fileDialog.getFileNames();" + NL + "\t\tString[] result = new String[filenames.length];" + NL + "\t\tString path = fileDialog.getFilterPath() + File.separator;" + NL + "\t\tString extension = null;" + NL + "\t\t" + NL + "\t\t// If extension adding requested, get the dotted extension corresponding to the selected filter." + NL + "\t\t//" + NL + "\t\tif (addExtension)" + NL + "\t\t{" + NL + "\t\t\tint i = fileDialog.getFilterIndex();" + NL + "\t\t\tif (i != -1 && (!includeAllFilter || i != fileExtensionFilters.length))" + NL + "\t\t\t{" + NL + "\t\t\t\ti = includeGroupFilter && i == 0 ? 0 : i - offset;" + NL + "\t\t\t\tString filter = fileExtensionFilters[i];" + NL + "\t\t\t\tint dot = filter.lastIndexOf('.');" + NL + "\t\t\t\tif (dot == 1 && filter.charAt(0) == '*')" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\textension = filter.substring(dot);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t// Build the result by adding the selected path and, if needed, auto-appending the extension." + NL + "\t\t//" + NL + "\t\tfor (int i = 0; i < filenames.length; i++)" + NL + "\t\t{" + NL + "\t\t\tString filename = path + filenames[i];" + NL + "\t\t\tif (extension != null)" + NL + "\t\t\t{" + NL + "\t\t\t\tint dot = filename.lastIndexOf('.');" + NL + "\t\t\t\tif (dot == -1 || !Arrays.asList(fileExtensionFilters).contains(\"*\" + filename.substring(dot)))";
  protected final String TEXT_72 = NL + "\t\t\t\t{" + NL + "\t\t\t\t\tfilename += extension;" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t\tresult[i] = filename;" + NL + "\t\t}" + NL + "\t\treturn result;" + NL + "\t}";
  protected final String TEXT_73 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static boolean openEditor(IWorkbench workbench, URI uri)" + NL + "\t{" + NL + "\t\tIWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();" + NL + "\t\tIWorkbenchPage page = workbenchWindow.getActivePage();" + NL + "\t\t" + NL + "\t\tIEditorDescriptor editorDescriptor = EditUIUtil.getDefaultEditor(uri, null);" + NL + "\t\tif (editorDescriptor == null)" + NL + "\t\t{" + NL + "\t\t\tMessageDialog.openError(" + NL + "\t\t\t\tworkbenchWindow.getShell()," + NL + "\t\t\t\tgetString(\"_UI_Error_title\"),";
  protected final String TEXT_74 = NL + "\t\t\t\tgetString(\"_WARN_No_Editor\", uri.lastSegment()));";
  protected final String TEXT_75 = NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{" + NL + "\t\t\t\tpage.openEditor(new URIEditorInput(uri), editorDescriptor.getId());" + NL + "\t\t\t}" + NL + "\t\t\tcatch (PartInitException exception)" + NL + "\t\t\t{" + NL + "\t\t\t\tMessageDialog.openError(" + NL + "\t\t\t\t\tworkbenchWindow.getShell()," + NL + "\t\t\t\t\tgetString(\"_UI_OpenEditorError_label\"),";
  protected final String TEXT_76 = NL + "\t\t\t\t\texception.getMessage());" + NL + "\t\t\t\treturn false;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn true;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_77 = NL + "\t\t@Override";
  protected final String TEXT_78 = NL + "\tpublic String getInitialWindowPerspectiveId()" + NL + "\t{" + NL + "\t\treturn Perspective.ID_PERSPECTIVE;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @see org.eclipse.ui.application.WorkbenchAdvisor#initialize(org.eclipse.ui.application.IWorkbenchConfigurer)" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_79 = NL + "\t\t@Override";
  protected final String TEXT_80 = NL + "\tpublic void initialize(IWorkbenchConfigurer configurer)" + NL + "\t{" + NL + "\t\tsuper.initialize(configurer);" + NL + "\t\tconfigurer.setSaveAndRestore(true);" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @see org.eclipse.ui.application.WorkbenchAdvisor#createWorkbenchWindowAdvisor(org.eclipse.ui.application.IWorkbenchWindowConfigurer)" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_81 = NL + "\t\t@Override";
  protected final String TEXT_82 = NL + "\tpublic WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer)" + NL + "\t{" + NL + "\t\treturn new WindowAdvisor(configurer);" + NL + "\t}" + NL + "}";
  protected final String TEXT_83 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2004-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

    GenModel genModel = (GenModel)argument;
    String _ListOfString = "List" + (genModel.useGenerics() ? "<String>" : "");
    String _UniqueEListOfString = "UniqueEList" + (genModel.useGenerics() ? "<String>" : "");
    String _StringBuilder = genModel.getImportedName(genModel.useGenerics() ? "java.lang.StringBuilder" : "java.lang.StringBuffer");
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genModel.getEditorPluginPackageName());
    stringBuffer.append(TEXT_5);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_6);
    }
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_7);
    }
    if (genModel.getAllGenPackagesWithConcreteClasses().size() > 1) {
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    if (genModel.getAllGenPackagesWithConcreteClasses().size() > 1) {
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getQualifiedEditorPluginClassName());
    stringBuffer.append(TEXT_18);
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_20);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_23);
    }
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_24);
    if (genModel.getAllGenPackagesWithConcreteClasses().size() == 1) {
    stringBuffer.append(TEXT_25);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_26);
    }
    stringBuffer.append(genModel.getAllGenPackagesWithConcreteClasses().get(0).getImportedEditorClassName());
    stringBuffer.append(TEXT_27);
    } else {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(_ListOfString);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(_UniqueEListOfString);
    stringBuffer.append(TEXT_30);
    for (GenPackage genPackage : genModel.getAllGenPackagesWithConcreteClasses()) {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genPackage.getImportedEditorClassName());
    stringBuffer.append(TEXT_32);
    }
    stringBuffer.append(TEXT_33);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_35);
    }
    stringBuffer.append(TEXT_36);
    }
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genModel.getQualifiedEditorPluginClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_44);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_47);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_48);
    }
    stringBuffer.append(TEXT_49);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_50);
    }
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_55);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_56);
    }
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_60);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_62);
    }
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_64);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_65);
    } else {
    stringBuffer.append(TEXT_66);
    }
    stringBuffer.append(TEXT_67);
    if (!genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_68);
    stringBuffer.append(_StringBuilder);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(_StringBuilder);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_72);
    }
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_76);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_77);
    }
    stringBuffer.append(TEXT_78);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_79);
    }
    stringBuffer.append(TEXT_80);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_81);
    }
    stringBuffer.append(TEXT_82);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_83);
    return stringBuffer.toString();
  }
}
