/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.json.UriAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;


/**
 * Class to wrap Resource Property Instance
 *
 */
public class BeanPropertyResource extends ABeanProperty<ResourcePropertyInstance, URI> {

	/**
	 * Default constructor
	 */
	public BeanPropertyResource() {
		
	}
	
	/**
	 * Standard constructor
	 * @param rpi the resource property instance
	 */
	public BeanPropertyResource(ResourcePropertyInstance rpi) {
		super(rpi);
	}
	
	/**
	 * this method set an specified value
	 * @param ed editing domain
	 * @param uri the specified value
	 * @return the creating command
	 */
	public Command setValue(EditingDomain ed, URI uri) {
		String uriString = uri != null ? uri.toPlatformString(false) : null;
		return SetCommand.create(ed, ti, PropertyinstancesPackage.Literals.RESOURCE_PROPERTY_INSTANCE__RESOURCE_URI, uriString);
	}
	
	/**
	 * this method set the specified value to the bean property string
	 * @param value the specified value
	 */
	public void setValue(URI value) {
		ti.setUri(value);
	}
	
	/**
	 * this method returns the bean property string value
	 * @return the bean property string value
	 */
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(UriAdapter.class)
	@Schema(
		//dataType = "String",
		description = "Platform String of a URI")
	public URI getValue() {
		return ti.getUri();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ABeanObject) {
			ABeanObject<?> rhsBeanProperty = (ABeanObject<?>) obj;
			return ti.equals(rhsBeanProperty.getTypeInstance());
		} else if (obj instanceof CategoryAssignment) {
			return ti.equals(obj);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return ti.hashCode();
	}

	@Override
	public boolean isSet() {
		return ti.getUri() != null;
	}

	@Override
	public void unset() {
		ti.setUri(null);
	}

	/**
	 * Gets the file referenced by the URI
	 * @return the file
	 */
	public IFile getFile() {
		URI resourceUri = ti.getUri();
		if (resourceUri != null) {
			IPath resourcePath = new Path(resourceUri.toPlatformString(true));
			return ResourcesPlugin.getWorkspace().getRoot().getFile(resourcePath);
		}
		
		return null;
	}
	
	@Schema(
			description = "Always returns constant: \"resource\"", 
			example = "resource",
			accessMode = AccessMode.READ_ONLY)
	@Override
	public BeanPropertyType getPropertyType() {
		return BeanPropertyType.RESOURCE;
	}
	
	@XmlElement
	public boolean getOverride() {
		return ti.isOverride();
	}
	
	public void setOverride(boolean override) {
		ti.setOverride(override);
	}
}
