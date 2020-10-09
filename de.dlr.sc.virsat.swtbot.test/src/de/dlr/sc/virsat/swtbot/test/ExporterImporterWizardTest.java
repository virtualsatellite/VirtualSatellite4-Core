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
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

public class ExporterImporterWizardTest extends ASwtBotTestCase {
	
	private static final String TEST_EXPORT_FOLDER = "SWTBOT_TEST_EXPORT_FILES";
	private static final String[] CT_PATH = { SWTBOT_TEST_PROJECTNAME, "Repository", "CT: ConfigurationTree" };
	private static final String[] EC_PATH = { SWTBOT_TEST_PROJECTNAME, "Repository", "CT: ConfigurationTree", "EC: ElementConfiguration" };
	
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
		bot.tree().expandNode(EC_PATH).select();
		bot.checkBox("Use default template").click();
		bot.comboBox().setText(exportFolderPath.toString());
		finishWizard();
		
		// Cause a change
		assertText(InterfaceEnd.class.getSimpleName(), bot.textWithLabel("Name"));
		rename(interfaceEnd, "changedName");
		assertText("changedName", bot.textWithLabel("Name"));
		
		// Assert that we correctly exported a file
		final String EXPECTED_EXCEL_FILE_NAME = "ConfigurationTree.ElementConfiguration.xlsx";
		File excelExportFile = exportFolderPath.resolve(EXPECTED_EXCEL_FILE_NAME).toFile();
		assertTrue("A file has been successfully created.", excelExportFile.exists());

		openVirSatImporter("Excel Import Wizard");
		
		// Configure the import
		bot.tree().expandNode(EC_PATH).select();
		bot.comboBox().setText(excelExportFile.getPath());
		finishWizard();
		
		// Check that the imported name has been applied
		assertText(InterfaceEnd.class.getSimpleName(), bot.textWithLabel("Name"));
	}

	@Test
	public void testHTMLExportFEA() {
		SWTBotTreeItem interfaceEnd = addElement(InterfaceEnd.class, conceptFea, ec);
		openEditor(interfaceEnd);
		openVirSatExporter("Functional Electrical Architecture to HTML Export Wizard");
		
		// Configure the export
		bot.tree().expandNode(CT_PATH).select();
		bot.comboBox().setText(exportFolderPath.toString());
		finishWizard();
		
		// Assert that we correctly exported the HTML files
		File htmlExportIndexFile = exportFolderPath.resolve("index.htm").toFile();
		Path resourcesFolderPath = exportFolderPath.resolve("resources");
		File htmlExportCTFile = resourcesFolderPath.resolve("ConfigurationTree.htm").toFile();
		File htmlExportECFile = resourcesFolderPath.resolve("ElementConfiguration.htm").toFile();
		assertTrue("Index file has been successfully created.", htmlExportIndexFile.exists());
		assertTrue("ConfigurationTree file has been successfully created.", htmlExportCTFile.exists());
		assertTrue("ElementConfiguration file has been successfully created.", htmlExportECFile.exists());
	}
	
	@Test
	public void testMatExportImport() {
		Concept conceptMass = ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.budget.mass.Activator.getPluginId() + "/concept/concept.xmi");	
		SWTBotTreeItem massEquipment = addElement(MassEquipment.class, conceptMass, ec);
		openEditor(massEquipment);
		
		final String OLD_VALUE = "45.0";
		final String NEW_VALUE = "30.0";
		
		renameField(MassEquipment.PROPERTY_MASS, OLD_VALUE);
		
		openVirSatExporter("Mat Export Wizard");
		
		// Configure the export
		bot.tree().expandNode(EC_PATH).select();
		Path matExportFilePath = exportFolderPath.resolve("export.mat");
		bot.comboBox().setText(matExportFilePath.toString());
		finishWizard();
		
		// Assert that we correctly exported a file
		assertTrue("A file has been successfully created.", matExportFilePath.toFile().exists());
		
		// Cause a change
		renameField(MassEquipment.PROPERTY_MASS, NEW_VALUE);

		openVirSatImporter("Mat Import Wizard");
		
		// Configure the import
		bot.tree().expandNode(EC_PATH).select();
		bot.comboBox().setText(matExportFilePath.toString());
		finishWizard();
		
		// Check that the imported value has been applied
		assertText(OLD_VALUE, bot.textWithLabel(MassEquipment.PROPERTY_MASS));
	}
	
	@Test
	public void testCadExportImport() {
		Concept conceptVisualisation = ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.visualisation.Activator.getPluginId() + "/concept/concept.xmi");	
		SWTBotTreeItem visualisation = addElement(Visualisation.class, conceptVisualisation, ec);
		openEditor(visualisation);
		
		// In order for a object to be exported, it must have a shape
		bot.comboBoxWithLabel(Visualisation.PROPERTY_SHAPE).setSelection(Visualisation.SHAPE_BOX_NAME);
		
		final String OLD_VALUE = "45.0";
		final String NEW_VALUE = "30.0";
		
		renameField(Visualisation.PROPERTY_POSITIONX, OLD_VALUE);
		
		openVirSatExporter("Cad Export Wizard");
		
		// Configure the export
		bot.tree().expandNode(CT_PATH).select();
		Path cadExportFilePath = exportFolderPath.resolve("export.json");
		bot.comboBox().setText(cadExportFilePath.toString());
		finishWizard();
		
		// Assert that we correctly exported a file
		assertTrue("A file has been successfully created.", cadExportFilePath.toFile().exists());
		
		// Cause a change
		renameField(Visualisation.PROPERTY_POSITIONX, NEW_VALUE);

		openVirSatImporter("Cad Import Wizard");
		
		// Configure the import
		bot.tree().expandNode(CT_PATH).select();
		bot.comboBox().setText(cadExportFilePath.toString());
		finishWizard();
		
		// Check that the imported value has been applied
		assertText(OLD_VALUE, bot.textWithLabel(Visualisation.PROPERTY_POSITIONX));
	}
	
	/**
	 * Helper function to open the given virsat exporter
	 * @param exporterName the name of the exporter
	 */
	private void openVirSatExporter(String exporterName) {
		bot.menu("File").menu("Export...").click();
		openVirSatWizard(exporterName);
	}
	
	/**
	 * Helper function to open the given virsat importer
	 * @param importerName the name of the importer
	 */
	private void openVirSatImporter(String importerName) {
		bot.menu("File").menu("Import...").click();
		openVirSatWizard(importerName);
	}
	
	/**
	 * Go to the Virtual Satellite category and select the wizard
	 * @param wizardName the wizard to select
	 */
	private void openVirSatWizard(String wizardName) {
		bot.tree().expandNode("Virtual Satellite", wizardName).select();
		bot.button("Next >").click();
		waitForEditingDomainAndUiThread();
	}
	
	/**
	 * Closes a import/export wizard by pressing the finish button.
	 */
	private void finishWizard() {
		finishDialog();
		
		// Workaround to shells sometimes not being valid anymore after closing a wizard
		// See https://wiki.eclipse.org/SWTBot/Troubleshooting#WidgetNotFoundException_when_stepping_through_SWTBot_test_in_Eclipse_debugger for details
		bot.shell().activate();
	}
}
