/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.project.ui.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * This is the Perspective Factory for the initial Phase A View.
 * @author fisc_ph
 *
 */
public class CorePerspective implements IPerspectiveFactory {

	public static final String ID_LEFT_FOLDER = "de.dlr.sc.virsat.perspective.core.LEFT";
	public static final String ID_RIGHT_FOLDER = "de.dlr.sc.virsat.perspective.core.RIGHT";
	public static final String ID_BOTTOM_LEFT_FOLDER = "de.dlr.sc.virsat.perspective.core.BOTTOMLEFT";
	public static final String ID_BOTTOM_RIGHT_FOLDER = "de.dlr.sc.virsat.perspective.core.BOTTOMRIGHT";
	public static final String ID_LEFT_MID_FOLDER = "de.dlr.sc.virsat.perspective.core.LEFT_MID";
	
	public static final String ID_PROPERTY_SHEET = "org.eclipse.ui.views.PropertySheet";
	public static final String ID_PROBLEM_VIEW = "org.eclipse.ui.views.ProblemView";
	public static final String ID_CONTENT_OUTLINE = "org.eclipse.ui.views.ContentOutline";
	public static final String ID_UI_ENGINE_PALETTE = "de.dlr.sc.virsat.uiengine.ui.palette";
	public static final String ID_NAVIGATOR_VIEW = "de.dlr.sc.virsat.project.ui.navigator.view";
	public static final String ID_PROJECT_EXPLORER = "org.eclipse.ui.navigator.ProjectExplorer";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		
		final float QUARTER = 0.25f;
		final float TWOTHIRDS = 0.66f;
		
		// Create a folder to bottom left and Add the problem view to the left bottom side		
		IFolderLayout bottomrightFolder = layout.createFolder(ID_BOTTOM_RIGHT_FOLDER, IPageLayout.BOTTOM, TWOTHIRDS, editorArea);
		bottomrightFolder.addView(ID_PROPERTY_SHEET);
		bottomrightFolder.addView(ID_PROBLEM_VIEW);

		// Create a folder to the bottom right and add the outline view to it
		IFolderLayout bottomleftFolder = layout.createFolder(ID_BOTTOM_LEFT_FOLDER, IPageLayout.LEFT, QUARTER, ID_BOTTOM_RIGHT_FOLDER);
		bottomleftFolder.addView(ID_CONTENT_OUTLINE);

		// Create a right folder to hold the widget gallery
		IFolderLayout rightFolder = layout.createFolder(ID_RIGHT_FOLDER, IPageLayout.RIGHT, 1 - QUARTER, editorArea);
		rightFolder.addView(ID_UI_ENGINE_PALETTE);
		
		// Create a folder to left and Add the navigator to the left side
		IFolderLayout leftFolder = layout.createFolder(ID_LEFT_FOLDER, IPageLayout.LEFT, QUARTER, editorArea);
		
		leftFolder.addView(ID_NAVIGATOR_VIEW);
		leftFolder.addView(ID_PROJECT_EXPLORER);
	}
}
