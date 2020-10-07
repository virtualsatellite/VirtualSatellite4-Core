/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.swtbot.test;

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertText;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassEquipment;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;

public class ExporterImporterWizardTest extends ASwtBotTestCase {
	
	private static final String TEST_EXPORT_FOLDER = "SWTBOT_TEST_EXPORT_FILES";
	private Path exportFolderPath;
	private SWTBotTreeItem ct;
	private SWTBotTreeItem ec;
	
	@Override
	public void before() throws Exception {
		super.before();
		exportFolderPath = VirSatFileUtils.createAutoDeleteTempDirectory(TEST_EXPORT_FOLDER);
		
		SWTBotTreeItem repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		ct = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		ec = addElement(ElementConfiguration.class, conceptPs, ct);
	}
	
	@Test
	public void testExcelExportImportFEA() {
		SWTBotTreeItem interfaceEnd = addElement(InterfaceEnd.class, conceptFea, ec);
		openEditor(interfaceEnd);
		
		openVirSatExporter("Excel Export Wizard");
		
		// Configure the export
		SWTBotTreeItem wizardEC = bot.tree()
				.expandNode(SWTBOT_TEST_PROJECTNAME, "Repository", "CT: ConfigurationTree", "EC: ElementConfiguration");
		wizardEC.select();
		bot.checkBox("Use default template").click();
		bot.comboBox().setText(exportFolderPath.toString());
		
		// Perform the export
		bot.button("Finish").click();
		
		// Cause a change
		assertText(InterfaceEnd.class.getSimpleName(), bot.textWithLabel("Name"));
		rename(interfaceEnd, "changedName");
		assertText("changedName", bot.textWithLabel("Name"));
		
		// Assert that we correctly exported a file
		File excelExportFile = exportFolderPath.resolve("ConfigurationTree.ElementConfiguration.xlsx").toFile();
		assertTrue("A file has been successfully created.", excelExportFile.exists());
		
		// Workaround to shells sometimes not being valid anymore after closing a wizard
		// See https://wiki.eclipse.org/SWTBot/Troubleshooting#WidgetNotFoundException_when_stepping_through_SWTBot_test_in_Eclipse_debugger for details
		bot.shell().activate();

		openVirSatImporter("Excel Import Wizard");
		
		// Configure the import
		wizardEC = bot.tree()
				.expandNode(SWTBOT_TEST_PROJECTNAME, "Repository", "CT: ConfigurationTree", "EC: ElementConfiguration");
		wizardEC.select();
		bot.comboBox().setText(excelExportFile.getPath());
		
		// Perform the import
		bot.button("Finish").click();
		
		// Check that the imported name has been applied
		assertText(InterfaceEnd.class.getSimpleName(), bot.textWithLabel("Name"));
	}

	@Test
	public void testHTMLExportFEA() {
		SWTBotTreeItem interfaceEnd = addElement(InterfaceEnd.class, conceptFea, ec);
		openEditor(interfaceEnd);
		
		openVirSatExporter("Functional Electrical Architecture to HTML Export Wizard");
		
		// Configure the export
		SWTBotTreeItem wizardEC = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository", "CT: ConfigurationTree");
		wizardEC.select();
		bot.comboBox().setText(exportFolderPath.toString());
		
		// Perform the export
		bot.button("Finish").click();
		
		// Assert that we correctly exported the HTML files
		File htmlExportIndexFile = exportFolderPath.resolve("index.htm").toFile();
		Path resourcesFolderPath = exportFolderPath.resolve("resources");
		File htmlExportCTFile = resourcesFolderPath.resolve("ConfigurationTree.htm").toFile();
		File htmlExportECFile = resourcesFolderPath.resolve("ElementConfiguration.htm").toFile();
		assertTrue("Index file has been successfully created.", htmlExportIndexFile.exists());
		assertTrue("ConfigurationTree file has been successfully created.", htmlExportCTFile.exists());
		assertTrue("ElementConfiguration file has been successfully created.", htmlExportECFile.exists());
		
		// Workaround to shells sometimes not being valid anymore after closing a wizard
		// See https://wiki.eclipse.org/SWTBot/Troubleshooting#WidgetNotFoundException_when_stepping_through_SWTBot_test_in_Eclipse_debugger for details
		bot.shell().activate();
	}
	
	@Test
	public void testMatExportImport() {
		Concept conceptMass = ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.budget.mass.Activator.getPluginId() + "/concept/concept.xmi");	
		SWTBotTreeItem massEquipment = addElement(MassEquipment.class, conceptMass, ec);
		openEditor(massEquipment);
		renameField(MassEquipment.PROPERTY_MASS, "45.0");
		
		openVirSatExporter("Mat Export Wizard");
		
		// Configure the export
		SWTBotTreeItem wizardEC = bot.tree()
				.expandNode(SWTBOT_TEST_PROJECTNAME, "Repository", "CT: ConfigurationTree", "EC: ElementConfiguration");
		wizardEC.select();
		Path matExportFilePath = exportFolderPath.resolve("export.mat");
		bot.comboBox().setText(matExportFilePath.toString());
		
		// Perform the export
		bot.button("Finish").click();
		
		// Cause a change
		renameField(MassEquipment.PROPERTY_MASS, "30.0");
		
		// Assert that we correctly exported a file
		assertTrue("A file has been successfully created.", matExportFilePath.toFile().exists());
		
		// Workaround to shells sometimes not being valid anymore after closing a wizard
		// See https://wiki.eclipse.org/SWTBot/Troubleshooting#WidgetNotFoundException_when_stepping_through_SWTBot_test_in_Eclipse_debugger for details
		bot.shell().activate();

		openVirSatImporter("Mat Import Wizard");
		
		// Configure the import
		wizardEC = bot.tree()
				.expandNode(SWTBOT_TEST_PROJECTNAME, "Repository", "CT: ConfigurationTree", "EC: ElementConfiguration");
		wizardEC.select();
		bot.comboBox().setText(matExportFilePath.toString());
		
		// Perform the import
		bot.button("Finish").click();
		
		// Check that the imported name has been applied
		assertText("45.0", bot.textWithLabel(MassEquipment.PROPERTY_MASS));
	}
	
	/**
	 * Helper function to open the given virsat exporter
	 * @param exporterName the name of the exporter
	 */
	private void openVirSatExporter(String exporterName) {
		// Open the export menu
		bot.menu("File").menu("Export...").click();
		waitForEditingDomainAndUiThread();
		
		// Go to the Virtual Satellite category and select the exporter
		SWTBotTreeItem virSatExporters = bot.tree().getTreeItem("Virtual Satellite");
		virSatExporters.expand();
		virSatExporters.getNode(exporterName).select();
		bot.button("Next >").click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Helper function to open the given virsat importer
	 * @param importerName the name of the importer
	 */
	private void openVirSatImporter(String importerName) {
		bot.menu("File").menu("Import...").click();
		waitForEditingDomainAndUiThread();
		
		// Go to the Virtual Satellite category and select the importer
		SWTBotTreeItem virSatImporters = bot.tree().getTreeItem("Virtual Satellite");
		virSatImporters.expand();
		virSatImporters.getNode(importerName).select();
		bot.button("Next >").click();
		waitForEditingDomainAndUiThread();
	}
	
}
