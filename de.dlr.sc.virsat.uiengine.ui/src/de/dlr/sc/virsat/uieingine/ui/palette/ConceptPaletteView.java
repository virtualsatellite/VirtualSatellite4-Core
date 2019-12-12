/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uieingine.ui.palette;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.nebula.animation.movement.ExpoOut;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryDragSourceEffect;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.ui.provider.ActiveConceptsContentProvider;

/**
 * This view implements a concept palette
 * @author muel_s8
 *
 */

public class ConceptPaletteView extends ViewPart {

	private Gallery gallery;

	public static final int OPERATIONS = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;
	public static final int ITEM_HEIGHT = 41;
	public static final int ITEM_WIDTH = 140;
	public static final int ANIMATION_LENGTH = 500;
	public static final int GROUP_IMAGE_WIDTH = 16;
	public static final int GROUP_MARGIN = 1;
	
	@Override
	public void createPartControl(Composite parent) {
		createGallery(parent);
		enableDragAndDrop();
		fillGallery();
	}
	
	/**
	 * Creates the gallery
	 * @param parent the parent of the gallery
	 */
	private void createGallery(Composite parent) {
		gallery = new Gallery(parent, SWT.V_SCROLL | SWT.MULTI);
		gallery.setDragDetect(true);
		
		DefaultGalleryGroupRenderer gr = createGalleryGroupRenderer();
		gallery.setGroupRenderer(gr);
		
		DefaultGalleryItemRenderer ir = new DefaultGalleryItemRenderer();
		gallery.setItemRenderer(ir);
	}
	
	/**
	 * Creates the necessary objects to allow for drag and drop of
	 * gallery items.
	 */
	private void enableDragAndDrop() {
		DragSource dragSource = new DragSource(gallery, OPERATIONS);
		dragSource.setDragSourceEffect(new GalleryDragSourceEffect(gallery));
		Transfer[] types = new Transfer[] { LocalSelectionTransfer.getTransfer() };
		dragSource.setTransfer(types);
		
		dragSource.addDragListener(new DragSourceListener() {
			@Override
			public void dragFinished(DragSourceEvent event) {
			}

			@Override
			public void dragSetData(DragSourceEvent event) {
			}
			
			@Override
			public void dragStart(DragSourceEvent event) {
				// Input the gallery selection into the local selection transfer service
				Object data = gallery.getSelection()[0].getData();
				ISelection selection = new StructuredSelection(data);
				event.data = data;
				LocalSelectionTransfer.getTransfer().setSelection(selection);
			}
		}); 
	}
	
	/**
	 * Creates a group for each concept and fills each group with items
	 * created from the respective concept elements.
	 */
	private void fillGallery() {
		// Grab all registered concepts in the ide
		Object[] acces = new ActiveConceptsContentProvider().getElements(Platform.getExtensionRegistry());
		
		for (Object object : acces) {
			ActiveConceptConfigurationElement acce = (ActiveConceptConfigurationElement) object;
			Concept concept = acce.loadConceptFromPlugin();
			
			// Create the actual item entries for the concept
			List<IConceptTypeDefinition> conceptTypeDefinitions = new ArrayList<>();
			conceptTypeDefinitions.addAll(concept.getStructuralElements());
			conceptTypeDefinitions.addAll(concept.getNonAbstractCategories());
			
			// Create the group entry in the palette
			if (!conceptTypeDefinitions.isEmpty()) {
				GalleryItem conceptGroup = new GalleryItem(gallery, SWT.NONE);
				if (concept.getDisplayName() != null) {
					conceptGroup.setText(concept.getDisplayName());
				} else {
					conceptGroup.setText(concept.getName());
				}
				conceptGroup.setImage(getImage(concept));
				conceptGroup.setExpanded(false);
				
				conceptTypeDefinitions.forEach(conceptTypeDefinition -> createItem(conceptGroup, conceptTypeDefinition));
			}
		}
	}
	
	/**
	 * Creates the gallery group renderer
	 * @return the gallery group renderer
	 */
	private DefaultGalleryGroupRenderer createGalleryGroupRenderer() {
		DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer();
		gr.setItemHeight(ITEM_HEIGHT);
		gr.setItemWidth(ITEM_WIDTH);
		gr.setAnimation(true);
		gr.setAnimationLength(ANIMATION_LENGTH);
		gr.setAnimationCloseMovement(new ExpoOut());
		gr.setAnimationOpenMovement(new ExpoOut());
		gr.setMaxImageWidth(GROUP_IMAGE_WIDTH);
		gr.setMinMargin(GROUP_MARGIN);
		return gr;
	}
	
	/**
	 * Creates a gallery item for the given concept object
	 * @param conceptGroup the gallery concept group
	 * @param conceptTypeDefinition the concept type definition
	 */
	private void createItem(GalleryItem conceptGroup, IConceptTypeDefinition conceptTypeDefinition) {
		GalleryItem item = new GalleryItem(conceptGroup, SWT.NONE);
		item.setText(conceptTypeDefinition.getName());
		item.setImage(getImage(conceptTypeDefinition));
		item.setData(conceptTypeDefinition);
	}
	
	/**
	 * Uses the adapter factory to get the icon of the given qualifiedName
	 * @param qualifiedName the qualifiedName
	 * @return the icon
	 */
	public Image getImage(IQualifiedName qualifiedName) {
		Object identifier = DVLMEditPlugin.getImageRegistry().get(qualifiedName.getFullQualifiedName());
		Image image = ExtendedImageRegistry.INSTANCE.getImage(identifier);
		return image;
	}

	@Override
	public void setFocus() {
		// No additional implementation
	}
}
