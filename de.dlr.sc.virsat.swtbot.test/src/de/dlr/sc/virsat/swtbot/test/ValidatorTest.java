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

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertSameWidget;
import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertText;
import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertVisible;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withTooltip;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.forms.widgets.Section;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.build.marker.ui.MarkerImageProvider;
import de.dlr.sc.virsat.build.validator.core.DvlmNamingConventionValidator;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.Document;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.project.ui.perspective.CorePerspective;
import de.dlr.sc.virsat.swtbot.util.SwtBotHyperlink;
import de.dlr.sc.virsat.swtbot.util.SwtBotSection;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.general.UiSnippetIName;

public class ValidatorTest extends ASwtBotTestCase {
	
	public static final String EMPTY_NAME_WARNING = DvlmNamingConventionValidator.WARNING_PREFIX + DvlmNamingConventionValidator.WARNING_EMPTY_NAME_SUFFIX;		
	
	private SWTBotTreeItem repositoryNavigatorItem;
	private SWTBotView problemView;
	
	@Before
	public void before() throws Exception {
		super.before();
		repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		problemView = openView(CorePerspective.ID_PROBLEM_VIEW);
	}
	
	@Test
	public void testValidateSeiName() {
		// Initially there are no warnings
		assertForTimes("Make sure the problems view is empty", SWTBOT_TRY_5_TIME, () -> !problemView.bot().tree().hasItems());
		
		// Create seis with incorrect names and then close the editors
		// Create relevant problematic seis in one test case to keep overhead time in swtbot low
		SWTBotTreeItem emptyNameSei = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		openEditor(emptyNameSei);
		renameField(UiSnippetIName.NAME_FIELD, "");
		
		SWTBotTreeItem dotSei = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		openEditor(dotSei);
		renameField(UiSnippetIName.NAME_FIELD, ".");
		
		SWTBotTreeItem noCamelCaseSei = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		openEditor(noCamelCaseSei);
		renameField(UiSnippetIName.NAME_FIELD, "12");
		
		SWTBotTreeItem duplicateNameSei1 = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		openEditor(duplicateNameSei1);
		renameField(UiSnippetIName.NAME_FIELD, "duplicateName");
		
		SWTBotTreeItem duplicateNameSei2 = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		openEditor(duplicateNameSei2);
		renameField(UiSnippetIName.NAME_FIELD, "duplicateName");
		
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		bot.closeAllEditors();
		
		// There should now exist a warning for every incorrect validation, except for
		// the no dots rule violation which is an error
		final int COUNT_EXPECTED_WARNINGS = 4;
		SWTBotTreeItem warnings = getWarnings(COUNT_EXPECTED_WARNINGS);
		assertNotNull(warnings);
		
		final int COUNT_EXPECTED_ERRORS = 1;
		SWTBotTreeItem errors = getErrors(COUNT_EXPECTED_ERRORS);
		assertNotNull(errors);
		
		// Double clicking on a warning should open the editor of the correct sei which is checked via the name field
		warnings.expand();
		warnings.getNode(EMPTY_NAME_WARNING).doubleClick();
		assertText("", bot.textWithLabel(UiSnippetIName.NAME_FIELD));
	}
	
	@Test
	public void testValidateCaName() {
		// Initially there are no warnings
		assertForTimes("Make sure the problems view is empty", SWTBOT_TRY_5_TIME, () -> !problemView.bot().tree().hasItems());
		
		// Create seis with incorrect names and then close the editors
		SWTBotTreeItem ct = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		SWTBotTreeItem ec = addElement(ElementConfiguration.class, conceptPs, ct);
		
		// Create relevant problematic cas in one test case to keep overhead time in swtbot low
		SWTBotTreeItem emptyCa = addElement(Document.class, conceptPs, ec);
		openEditor(emptyCa);
		renameField(UiSnippetIName.NAME_FIELD, "");
		
		SWTBotTreeItem dotCa = addElement(Document.class, conceptPs, ec);
		openEditor(dotCa);
		renameField(UiSnippetIName.NAME_FIELD, ".");
		
		SWTBotTreeItem noCamelCaseCa = addElement(Document.class, conceptPs, ec);
		openEditor(noCamelCaseCa);
		renameField(UiSnippetIName.NAME_FIELD, "12");
		
		SWTBotTreeItem duplicateNameCa1 = addElement(Document.class, conceptPs, ec);
		openEditor(duplicateNameCa1);
		renameField(UiSnippetIName.NAME_FIELD, "duplicateName");
		
		SWTBotTreeItem duplicateNameCa2 = addElement(Document.class, conceptPs, ec);
		openEditor(duplicateNameCa2);
		renameField(UiSnippetIName.NAME_FIELD, "duplicateName");
		
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		bot.closeAllEditors();
		
		// There should now exist a warning for every incorrect validation, except for
		// the no dots rule violation which is an error
		final int COUNT_EXPECTED_WARNINGS = 4;
		SWTBotTreeItem warnings = getWarnings(COUNT_EXPECTED_WARNINGS);
		assertNotNull(warnings);
		
		final int COUNT_EXPECTED_ERRORS = 1;
		SWTBotTreeItem errors = getErrors(COUNT_EXPECTED_ERRORS);
		assertNotNull(errors);
		
		// Double clicking on an error should open the editor of the containing sei
		errors.expand();
		final String EXPEDTED_ERROR = DvlmNamingConventionValidator.WARNING_PREFIX 
				+ ConfigurationTree.class.getSimpleName() + "."
				+ ElementConfiguration.class.getSimpleName() + ".."
				+ DvlmNamingConventionValidator.WARNING_DOTS_SUFFIX;
		errors.getNode(EXPEDTED_ERROR).doubleClick();
		assertText(ElementConfiguration.class.getSimpleName(), bot.textWithLabel(UiSnippetIName.NAME_FIELD));
	}

	@Test
	public void testWarningInGenericEditor() {
		// Create sei with some warning
		SWTBotTreeItem emptyNameSei = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		openEditor(emptyNameSei);
		renameField(UiSnippetIName.NAME_FIELD, "");
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		
		// Check that there is a warning icon with the correct tooltip in the name section
		// Note that icons are implemented via label with image
		SwtBotSection nameSection = getSWTBotSection(UiSnippetIName.SECTION_HEADING);
		List<Label> iconLabels = bot.getFinder()
				.findControls(nameSection.widget, allOf(widgetOfType(Label.class), withTooltip(EMPTY_NAME_WARNING)), true);
		SWTBotLabel swtIcon = new SWTBotLabel(iconLabels.get(0));
		Image actualIcon = swtIcon.image();
		Image expectedIcon = new MarkerImageProvider(null).getProblemImageForSeverity(IMarker.SEVERITY_WARNING);
		
		assertVisible(swtIcon);
		assertEquals(expectedIcon, actualIcon);
		
		// Collapse the name section
		nameSection.click();
		
		// Check that clicking the header link collapses all sections which have no warning (i.e. all except the name section)
		// and expands those with a warning (i.e. the name section)
		SwtBotHyperlink swtBotHyperlink = getSWTBotHyperlink(EMPTY_NAME_WARNING);
		swtBotHyperlink.click();
		
		List<SwtBotSection> expandedSwtBotSections = getExpandedSections();
		assertEquals(1, expandedSwtBotSections.size());
		assertSameWidget(nameSection.widget, expandedSwtBotSections.get(0).widget);
		
		// Check that the header label has the right tooltip text
		String tooltip = swtBotHyperlink.getToolTipText();
		assertEquals(EMPTY_NAME_WARNING, tooltip);
	}
	
	@Test
	public void testErrorInGenericEditor() {
		// Create sei with an error in a contained sei
		SWTBotTreeItem ct = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		SWTBotTreeItem ec = addElement(ElementConfiguration.class, conceptPs, ct);
		SWTBotTreeItem dotCa = addElement(Document.class, conceptPs, ec);
		openEditor(dotCa);
		renameField(UiSnippetIName.NAME_FIELD, ".");
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		
		bot.closeAllEditors();
		openEditor(ec);
		
		// Check that there is an error icon in the document table
		SwtBotSection documentSection = getSWTBotSection(Document.class);
		SWTBotTable documentTable = documentSection.getSWTBotTable();
		SWTBotTableItem documentNameItem = documentTable.getTableItem(0).click(0);
		
		Image actualIcon = getImageForTableItem(documentNameItem);
		Image expectedIcon = new MarkerImageProvider(null).getProblemImageForSeverity(IMarker.SEVERITY_ERROR);
		assertEquals(expectedIcon, actualIcon);
		
		// Collapse the document section
		documentSection.click();
		
		// Check that clicking the header link collapses all sections which have no error (i.e. all except the document section)
		// and expands those with an error (i.e. the document section)
		final String EXPEDTED_ERROR = DvlmNamingConventionValidator.WARNING_PREFIX 
				+ ConfigurationTree.class.getSimpleName() + "."
				+ ElementConfiguration.class.getSimpleName() + ".."
				+ DvlmNamingConventionValidator.WARNING_DOTS_SUFFIX;
		SwtBotHyperlink swtBotHyperlink = getSWTBotHyperlink(EXPEDTED_ERROR);
		swtBotHyperlink.click();
		
		List<SwtBotSection> expandedSwtBotSections = getExpandedSections();
		assertEquals(1, expandedSwtBotSections.size());
		assertSameWidget(documentSection.widget, expandedSwtBotSections.get(0).widget);
		
		// Check that the header label has the right tooltip text
		String tooltip = swtBotHyperlink.getToolTipText();
		assertEquals(EXPEDTED_ERROR, tooltip);
	}
	
	/**
	 * Gets the currently expanded sections
	 * @return a list containing only expanded sections
	 */
	private List<SwtBotSection> getExpandedSections() {
		List<? extends Section> sections = bot.getFinder().findControls(widgetOfType(Section.class));
		List<SwtBotSection> expandedSwtBotSections = sections.stream()
				.map(SwtBotSection::new)
				.filter(SwtBotSection::isExpanded)
				.collect(Collectors.toList());
		return expandedSwtBotSections;
	}
	
	/**
	 * Gets the warnings entry from the problem view
	 * @param countExpectedWarnings the expected number of warnings
	 * @return the warning entry
	 */
	private SWTBotTreeItem getWarnings(int countExpectedWarnings) {
		String plural = (countExpectedWarnings > 1) ? "s" : "";
		return problemView.bot().tree().getTreeItem("Warnings (" + countExpectedWarnings + " item" + plural + ")");
	}
	
	/**
	 * Gets the error entry from the problem view
	 * @param countExpectedErrors the expected number of errors
	 * @return the warning entry
	 */
	private SWTBotTreeItem getErrors(int countExpectedErrors) {
		String plural = (countExpectedErrors > 1) ? "s" : "";
		return problemView.bot().tree().getTreeItem("Errors (" + countExpectedErrors + " item" + plural + ")");
	}
	
	/**
	 * Gets the image of a table item
	 * @param swtBotTableItem the swtbot table item
	 * @return the image of the table item
	 */
	private Image getImageForTableItem(SWTBotTableItem swtBotTableItem) {
		return syncExec(new Result<Image>() {
			@Override
			public Image run() {
				return swtBotTableItem.widget.getImage();
			}
		});
	}
}
