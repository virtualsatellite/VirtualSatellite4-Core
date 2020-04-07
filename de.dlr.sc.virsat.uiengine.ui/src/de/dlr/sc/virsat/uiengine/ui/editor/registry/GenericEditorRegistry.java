/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.uieingine.ui.DVLMEditorPlugin;
import de.dlr.sc.virsat.uiengine.ui.editor.GenericEditor;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * This class reads the ExtensionPoint for the Generic Editor and sorts the UI Widgets which will be needed
 * @author fisc_ph
 *
 */
public class GenericEditorRegistry {
	
	/**
	 * Constructor for the UI SnippetRegistry
	 * @param genericEditor The Generic Editor instance in which this registry is operating
	 */
	public GenericEditorRegistry(GenericEditor genericEditor) {
		this.genericEditor = genericEditor;
	}
	
	/**
	 * To remember the registered section and to sort it
	 * @author fisc_ph
	 *
	 */
	private static class RegisteredUiSection  {
		/**
		 * Constructor which should be called with a ConfigurationElement from the Section Extension Point
		 * @param regElement 
		 */
		private RegisteredUiSection(IConfigurationElement regElement) {
			this.id = regElement.getAttribute("id").trim();
			this.ranking = Integer.parseInt(regElement.getAttribute("topRanking"));
		}
		
		/**
		 * Create a RegisteredUiSection class from a snippet, which sets the internal ID
		 * to the one that is referenced by the snippet. This is used to create "dummy" objects
		 * to search for corresponding entries in the lists and sets.
		 * @param registeredUiSnippet The SNippet from which to take the referenced Id
		 */
		private RegisteredUiSection(RegisteredUiSnippet registeredUiSnippet) {
			this.id = registeredUiSnippet.referencedSectionId.trim();
		}

		private String id;
		private int ranking;
		
		@Override
		public String toString() {
			return "UiSection Id: " + Objects.toString(id, "N/A") + " Ranking: " + ranking;
		}
		
		@Override
		public int hashCode() {
			return id.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			
			// Only compare on the Id and exlude the ranking
			if (obj instanceof RegisteredUiSection) {
				RegisteredUiSection other = (RegisteredUiSection) obj;
				return (this.id == null) ? false : this.id.equals(other.id);
			}
			return true;
		}
	}
	
	/**
	 * This class reads a Ui Snippet from the plugin.xml registry
	 * @author fisc_ph
	 *
	 */
	private static class RegisteredUiSnippet {
		
		private String id;
		private String referencedSectionId;
		private IUiSnippet uiSnippet;
		
		/**
		 * ready a reg element from the plugin.xml and extracts the id referenced section and
		 * already initializes the actual uiSnippet class
		 * @param regElement The regelement from the plugin.xml of which to create this class from
		 * @throws CoreException Throws an exception in case the specified class for the snippet cannot be initialized
		 */
		RegisteredUiSnippet(IConfigurationElement regElement) throws CoreException {
			this.id = regElement.getAttribute("id");
			this.referencedSectionId = regElement.getAttribute("section");
			this.uiSnippet = (IUiSnippet) regElement.createExecutableExtension("snippet");
		}
		
		@Override
		public String toString() {
			return "registeredUiSection id: " + Objects.toString(id, "N/A") + " referencedId: " + Objects.toString(referencedSectionId, "N/A");
		}
		
		@Override
		public int hashCode() {
			return id.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			// Same here only compare on the id
			if (obj instanceof RegisteredUiSnippet) {
				RegisteredUiSnippet other = (RegisteredUiSnippet) obj;
				return (this.id == null) ? false : this.id.equals(other.id);
			}
			return true;
		}
	}
	
	private GenericEditor genericEditor;
	
	private Map<RegisteredUiSection, List<RegisteredUiSnippet>> registeredSectionsAndSnippets;
	
	public static final String EXTENSION_POINT_ID_SNIPPETS = "de.dlr.sc.virsat.uiengine.ui.EditorUiSnippets";
	public static final String EXTENSION_POINT_ID_SECTIONS = "de.dlr.sc.virsat.uiengine.ui.EditorSection";
		
	/**
	 * Call this method to hand back all the UiSnippets for the current Editor 
	 * @return An Array of currently registered UISnippets
	 */
	public Set<IUiSnippet> getOrderedSnippets() {
		Set<IUiSnippet> uiSnippets = new LinkedHashSet<>();

		List<RegisteredUiSection> orderedUiSection = new ArrayList<>(registeredSectionsAndSnippets.keySet());
		orderedUiSection.sort((o1, o2) -> Integer.compare(o1.ranking, o2.ranking));
		
		orderedUiSection.forEach((registeredUiSection) -> {
			List<RegisteredUiSnippet> registeredSnippets = registeredSectionsAndSnippets.get(registeredUiSection);
			registeredSnippets.forEach((registeredUiSnippet) -> {
				uiSnippets.add(registeredUiSnippet.uiSnippet);	
			});
		});
	
		return Collections.unmodifiableSet(uiSnippets);
	}
	
	/**
	 * call this method to actually read the extension point
	 */
	public void readPlatformRegistry() {
		registeredSectionsAndSnippets = new HashMap<>();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		
		// Read all sections first and then order them
		IConfigurationElement[] configSections = registry.getConfigurationElementsFor(EXTENSION_POINT_ID_SECTIONS);
		for (IConfigurationElement regElement : configSections) {
			RegisteredUiSection registeredUiSection = new RegisteredUiSection(regElement);
			List<RegisteredUiSnippet> registeredUiSnippets = new ArrayList<>();
			registeredSectionsAndSnippets.put(registeredUiSection, registeredUiSnippets);
		}
		
		Set<RegisteredUiSnippet> registeredUiSnippets = new HashSet<>();
		
		// Loop over all registered sections and find the corresponding UI Snippets
		IConfigurationElement[] configSnippets = registry.getConfigurationElementsFor(EXTENSION_POINT_ID_SNIPPETS);
		for (IConfigurationElement regElement : configSnippets) {
			try {
				RegisteredUiSnippet registeredUiSnippet = new RegisteredUiSnippet(regElement);
				if (!registeredUiSnippets.add(registeredUiSnippet)) {
					registeredUiSnippets.remove(registeredUiSnippet);
					registeredUiSnippets.add(registeredUiSnippet);
				}
			} catch (CoreException e) {
				DVLMEditorPlugin.getPlugin().log(new Status(IStatus.ERROR, DVLMEditorPlugin.ID, "Could not initialize UI Snippet", e));
			}
		}
		
		// Loop over all registered sections and find the corresponding UI Snippets
		for (RegisteredUiSnippet registeredUiSnippet : registeredUiSnippets) {
			RegisteredUiSection referencedUiSection = new RegisteredUiSection(registeredUiSnippet);
			registeredSectionsAndSnippets.get(referencedUiSection).add(registeredUiSnippet); 
		}
	}
	
	/**
	 * This method allows to run a given function on all UiSnippets which are assignable for the given model
	 * @param model The model to which the snippet should be compatible to
	 * @param filter a list of snippets that should be triggered, all snippets not contained in this list will not get triggered 
	 * @param operation the operation that should be executed on the actual snippet that is compatible with the model
	 */
	public void executeForOrderedAssignableSnippets(EObject model, Set<IUiSnippet> filter, Consumer<IUiSnippet> operation) {
		boolean editorInvalid = false;
		
		for (IUiSnippet uiSnippet : getOrderedSnippets()) {
	    	try {
	    		// Only try to update the snippet if it is part of the filter
	    		if (filter.contains(uiSnippet)) {
					if (uiSnippet.isActive(model)) {
		    			operation.accept(uiSnippet);
		    		} else {
		    			// in case the snippet is not active for the object than we assume, that the editor is not well
		    			// formed and needs to be shutdown. It means that the editor contains UI snippets for some
		    			// concepts which are not active or valid anymore.
		    			editorInvalid = true;
		    		}
	    		}
	    	} catch (Exception e) {
	    		DVLMEditorPlugin.getPlugin().log(new Status(IStatus.ERROR, DVLMEditorPlugin.ID, "Could not execute consumer function on UI Snippet", e));
	    	}
		}
		
		if (editorInvalid) {
			Display.getDefault().asyncExec(() -> genericEditor.close(true));
		}
	}
	
	/**
	 * This method allows to run a given function on all UiSnippets which are assignable for the given model
	 * @param model The model to which the snippet should be compatible to
	 * @param operation the operation that should be executed on the actual snippet that is compatible with the model
	 */
	public void executeForOrderedAssignableSnippets(EObject model, Consumer<IUiSnippet> operation) {
		for (IUiSnippet uiSnippet : getOrderedSnippets()) {
	    	try {
				if (uiSnippet.isActive(model)) {
	    			operation.accept(uiSnippet);
	    		}
	    	} catch (Exception e) {
	    		DVLMEditorPlugin.getPlugin().log(new Status(IStatus.ERROR, DVLMEditorPlugin.ID, "Could not execute consumer function on UI Snippet", e));
	    	}
		}
	}
}
