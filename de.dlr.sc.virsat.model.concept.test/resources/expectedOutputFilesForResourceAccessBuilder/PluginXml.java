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
	public static class Commands {
	}
	public static class ConceptImages {
	}
	public static class UiSnippets {
	}
	public static class Handlers {
	}
	public static class ConceptMigrators {
		public static class Extensiontests1_0 {
			public static final String CLASSNAME = "de.dlr.sc.virsat.model.extension.tests.migrator.Migrator1v0";
			public static final String ID = "de.dlr.sc.virsat.model.extension.tests";
			public static final String VERSION = "1.0";
			public static final String XMI = "concept/concept_v1_0.xmi";
		}
		public static class Extensiontests1_1 {
			public static final String CLASSNAME = "de.dlr.sc.virsat.model.extension.tests.migrator.Migrator1v1";
			public static final String ID = "de.dlr.sc.virsat.model.extension.tests";
			public static final String VERSION = "1.1";
			public static final String XMI = "concept/concept_v1_1.xmi";
		}
	}
	public static class Concept {
		public static class Extensiontests1_1 {
			public static final String ID = "de.dlr.sc.virsat.model.extension.tests";
			public static final String VERSION = "1.1";
			public static final String XMI = "concept/concept.xmi";
		}
	}
	public static class ExtensionPoints {
	}
}

