/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package testProject_ResourceAccessBuilderTest;
public class PluginXml {
	public static class commands {
		public static class CommandcreateStudyApp {
			public static final String ID = "de.dlr.sc.virsat.model.extension.testResource.ui.command.createStudyApp";
			public static final String NAME = "Example Study App";
		}	
		public static class CommandcreateReplaceITApp {
			public static final String ID = "de.dlr.sc.virsat.model.extension.testResource.ui.command.createReplaceITApp";
			public static final String NAME = "Replace Interface Types App";
		}	
		public static class CommandcreateAddUnitApp {
			public static final String ID = "de.dlr.sc.virsat.model.extension.testResource.ui.command.createAddUnitApp";
			public static final String NAME = "Add Unit App";
		}	
	}
	public static class conceptImages {
	}
	public static class uiSnippets {
	}
	public static class handlers {
		public static class HandlerCreateAndOpenExampleStudyAppHandler {
			public static final String CLASSNAME = "de.dlr.sc.virsat.model.extension.testResource.ui.handler.CreateAndOpenExampleStudyAppHandler";
			public static final String COMMANDID = "de.dlr.sc.virsat.model.extension.testResource.ui.command.createStudyApp";
		}	
		public static class HandlerCreateAndOpenReplaceInterfaceTypesAppHandler {
			public static final String CLASSNAME = "de.dlr.sc.virsat.model.extension.testResource.ui.handler.CreateAndOpenReplaceInterfaceTypesAppHandler";
			public static final String COMMANDID = "de.dlr.sc.virsat.model.extension.testResource.ui.command.createReplaceITApp";
		}	
		public static class HandlerCreateAndOpenAddUnitAppHandler {
			public static final String CLASSNAME = "de.dlr.sc.virsat.model.extension.testResource.ui.handler.CreateAndOpenAddUnitAppHandler";
			public static final String COMMANDID = "de.dlr.sc.virsat.model.extension.testResource.ui.command.createAddUnitApp";
		}	
	}
	public static class conceptMigrators {
		public static class Migrator1_0 {
			public static final String CLASSNAME = "testProject.migrator.Migrator1v0";
			public static final String ID = "testProject";
			public static final String VERSION = "1.0";
			public static final String XMI = "concept/concept_v1_0.xmi";
		}	
	}
	public static class concept {
		public static class Concept {
			public static final String ID = "testProject";
			public static final String VERSION = "1.0";
			public static final String XMI = "concept/concept.xmi";
		}	
	}
	public static class ExtensionPoints {
		
	}
}

