/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.cad;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.mechanical.Activator;
import de.dlr.sc.virsat.model.extension.mechanical.cad.command.CopyResourceCommand;
import de.dlr.sc.virsat.model.extension.mechanical.cad.util.CadHelper;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * This class imports a JSON representation of system model
 *
 */
public class CadImporter {

	private EditingDomain editingDomain;

	/**
	 * Main method that imports a JSON representation of our system and updates the
	 * model accordingly
	 * 
	 * @param editingDomain
	 *            the editing domain of the current project
	 * @param jsonObject
	 *            the JSON Object
	 * @param mapJsonUuidToSEI
	 *            the mapping of JSON element IDs to the existing trees
	 * 
	 * @return the emf command to execute the import
	 */
	public Command transform(EditingDomain editingDomain, JsonObject jsonObject,
			Map<String, StructuralElementInstance> mapJsonUuidToSEI) {

		CompoundCommand importCommand = new CompoundCommand();
		this.editingDomain = editingDomain;

		boolean commandCreationWorked = true;

		// Import parts
		for (JsonObject part : CadHelper.getListOfAllJSONParts(jsonObject)) {
			String uuidPart = part.getString(CadProperties.UUID);
			if (mapJsonUuidToSEI.containsKey(uuidPart)) {
				commandCreationWorked &= updateSeiFromPart(importCommand, mapJsonUuidToSEI.get(uuidPart), part);
			}
		}

		// Import products
		for (JsonObject product : CadHelper.getListOfAllJSONProducts(jsonObject)) {
			String uuidProduct = product.getString(CadProperties.UUID);
			if (mapJsonUuidToSEI.containsKey(uuidProduct)) {
				commandCreationWorked &= updateSeiFromProduct(importCommand, mapJsonUuidToSEI.get(uuidProduct),
						product);
			}
		}

		if (commandCreationWorked) {
			return importCommand;
		} else {
			return UnexecutableCommand.INSTANCE;
		}

	}

	/**
	 * Maps from the IDs of JSON elements to existing model elements in the Virtual
	 * Satellite model
	 * 
	 * @param jsonContent
	 *            the JSON content to be imported
	 * @param existingTree
	 *            a tree element the imported JSON should be mapped to
	 * @return a map from UUID in the JSON file to their existing tree elements in
	 *         the model
	 */
	public Map<String, StructuralElementInstance> mapJsonUuidToSEI(JsonObject jsonContent,
			IBeanStructuralElementInstance existingTree) {

		Map<String, StructuralElementInstance> mapExisitingElementToUUID = new HashMap<String, StructuralElementInstance>();
		Map<String, IBeanStructuralElementInstance> mapSEIsToUuid = createMapOfTreeSEIsToUuid(existingTree);

		for (JsonObject object : CadHelper.getListOfAllJSONElements(jsonContent)) {
			String uuid = object.getString(CadProperties.UUID);
			IBeanStructuralElementInstance mappedElement = mapSEIsToUuid.get(uuid);
			if (mapSEIsToUuid.containsKey(uuid) && mappedElement != null) {
				mapExisitingElementToUUID.put(uuid, mappedElement.getStructuralElementInstance());
			}

		}

		return mapExisitingElementToUUID;

	}

	/**
	 * Method to get all unmapped JSON elements that do not have a representation in
	 * the existing trees
	 * 
	 * @param jsonRoot
	 *            the JSON root element to look for unmapped elements in
	 * @param mapJSONtoSEI
	 *            the Map of JSONObject IDs to SEIs in the model created by method
	 *            {@link #mapJSONtoSEI(JsonObject, IBeanStructuralElementInstance)}
	 * @return a list of unmapped elements
	 */
	public List<JsonObject> getUnmappedJSONObjects(JsonObject jsonRoot,
			Map<String, StructuralElementInstance> mapJSONtoSEI) {

		List<JsonObject> unmappedElements = new ArrayList<>();

		for (JsonObject object : CadHelper.getListOfAllJSONElements(jsonRoot)) {
			String uuid = object.getString(CadProperties.UUID);
			if (!mapJSONtoSEI.containsKey(uuid)) {
				unmappedElements.add(object);
			}
		}

		return unmappedElements;

	}

	/**
	 * Update an element in the Virtual Satellite model from a corresponding
	 * imported JSON part
	 * 
	 * @param importCommand
	 *            the compound command that handles the import
	 * @param sei
	 *            the structural element instance to be updated
	 * @param part
	 *            the JSON object part that is imported and corresponds to the SEI
	 * 
	 * @return returns if all required properties for the import could be found
	 */
	protected boolean updateSeiFromPart(CompoundCommand importCommand, StructuralElementInstance sei, JsonObject part) {
		BeanStructuralElementInstance beanSEI = new BeanStructuralElementInstance(sei);
		Visualisation visualisation = getVisualisation(beanSEI, importCommand);

		String name;

		double sizeX;
		double sizeY;
		double sizeZ;
		double radius;

		long color;
		String shape;
		String stlFile = null;
		try {
			name = part.getString(CadProperties.NAME);
			sizeX = part.getDouble(CadProperties.PART_LENGTH_X);
			sizeY = part.getDouble(CadProperties.PART_LENGTH_Y);
			sizeZ = part.getDouble(CadProperties.PART_LENGTH_Z);
			radius = part.getDouble(CadProperties.PART_RADIUS);

			color = part.getLong(CadProperties.PART_COLOR);
			shape = part.getString(CadProperties.PART_SHAPE);

		} catch (NullPointerException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"CadImport: Failed to perform import! Could not load all required properties", e));
			return false;
		}

		if (part.containsKey(CadProperties.PART_STL_PATH.getKey())) {
			stlFile = part.getString(CadProperties.PART_STL_PATH);
		}

		setName(importCommand, beanSEI, name);

		setFloatValue(importCommand, visualisation.getSizeXBean(), sizeX);
		setFloatValue(importCommand, visualisation.getSizeYBean(), sizeY);
		setFloatValue(importCommand, visualisation.getSizeZBean(), sizeZ);

		setFloatValue(importCommand, visualisation.getRadiusBean(), radius);

		setColor(importCommand, visualisation, color);
		setShape(importCommand, visualisation, shape, stlFile);

		return true;

	}

	/**
	 * Update an element in the Virtual Satellite model from a corresponding
	 * imported JSON product
	 * 
	 * @param importCommand
	 *            the compound command that handles the import
	 * @param sei
	 *            the structural element instance to be updated
	 * @param product
	 *            the JSON object product that is imported and corresponds to the
	 *            SEI
	 * 
	 * @return returns if all required properties for the import could be found
	 */
	protected boolean updateSeiFromProduct(CompoundCommand importCommand, StructuralElementInstance sei,
			JsonObject product) {

		if (!hasVisualisationProductProperties(product)) {
			return true;
		}

		BeanStructuralElementInstance beanSEI = new BeanStructuralElementInstance(sei);
		Visualisation visualisation = getVisualisation(beanSEI, importCommand);

		String name;

		double posX;
		double posY;
		double posZ;

		double rotX;
		double rotY;
		double rotZ;

		try {
			name = product.getString(CadProperties.NAME);

			posX = product.getDouble(CadProperties.PRODUCT_POS_X);
			posY = product.getDouble(CadProperties.PRODUCT_POS_Y);
			posZ = product.getDouble(CadProperties.PRODUCT_POS_Z);

			rotX = product.getDouble(CadProperties.PRODUCT_ROT_X);
			rotY = product.getDouble(CadProperties.PRODUCT_ROT_Y);
			rotZ = product.getDouble(CadProperties.PRODUCT_ROT_Z);
		} catch (NullPointerException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"CadImport: Failed to perform import! Could not load all required properties"));
			return false;
		}

		setName(importCommand, beanSEI, name);

		setFloatValue(importCommand, visualisation.getPositionXBean(), posX);
		setFloatValue(importCommand, visualisation.getPositionYBean(), posY);
		setFloatValue(importCommand, visualisation.getPositionZBean(), posZ);

		setFloatValue(importCommand, visualisation.getRotationXBean(), rotX);
		setFloatValue(importCommand, visualisation.getRotationYBean(), rotY);
		setFloatValue(importCommand, visualisation.getRotationZBean(), rotZ);

		return true;
	}

	/**
	 * Returns whether the part contains any visualisation properties
	 * 
	 * @param product
	 *            the JSON object of the product
	 * @return true if the product has any visualisation properties
	 */
	private boolean hasVisualisationProductProperties(JsonObject product) {
		return product.containsKey(CadProperties.PRODUCT_POS_X.getKey())
				|| product.containsKey(CadProperties.PRODUCT_POS_Y.getKey())
				|| product.containsKey(CadProperties.PRODUCT_POS_Z.getKey())
				|| product.containsKey(CadProperties.PRODUCT_ROT_X.getKey())
				|| product.containsKey(CadProperties.PRODUCT_ROT_Y.getKey())
				|| product.containsKey(CadProperties.PRODUCT_ROT_Z.getKey());
	}

	/**
	 * Convert the STL file path to a path in the workspace
	 * 
	 * @param stlPath
	 *            the external STL file path
	 * @param seiBean
	 *            the SEI bean
	 * @return the path of the internal file location
	 */
	private Path getLocalPath(String stlPath, IBeanStructuralElementInstance seiBean) {

		// Copy file to workspace
		Path cadSTLPath = Paths.get(stlPath);

		Path fileName = cadSTLPath.getFileName();
		if (fileName == null) {
			throw new IllegalArgumentException(
					"Invalid path to STL file. Can't extract internal directory: " + stlPath);
		}

		String stlName = fileName.toString();

		String documentPath = VirSatProjectCommons.getDocumentFolder(seiBean.getStructuralElementInstance())
				.getFullPath().toOSString();

		Path localPath = Paths.get(documentPath, stlName);
		return localPath;
	}

	/**
	 * Convert a path within the workspace to an absolute path by appending the
	 * workspace path
	 * 
	 * @param pathInWorkspace
	 *            a path within the workspace
	 * @return the absolute path
	 */
	private Path getAbsolutePath(Path pathInWorkspace) {
		String workspacePath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toOSString();
		return Paths.get(workspacePath, pathInWorkspace.toString());
	}

	/**
	 * Create a map of all UUIDs to their structural element instances in a tree and
	 * their super instances
	 * 
	 * @param existingTree
	 *            the existing tree element in the Virtual Satellite model
	 * @return a map that maps the UUID to all SEIs
	 */
	private Map<String, IBeanStructuralElementInstance> createMapOfTreeSEIsToUuid(
			IBeanStructuralElementInstance existingTree) {

		Map<String, IBeanStructuralElementInstance> mapSEIsToUuid = new HashMap<String, IBeanStructuralElementInstance>();

		for (IBeanStructuralElementInstance sei : existingTree.getDeepChildren(IBeanStructuralElementInstance.class)) {
			mapSEIsToUuid.put(sei.getUuid(), sei);

			// Also add relevant elements in other trees (the super elements) to the map
			for (IBeanStructuralElementInstance superSei : sei.getAllSuperSeis(IBeanStructuralElementInstance.class)) {
				mapSEIsToUuid.put(superSei.getUuid(), superSei);
			}
		}

		return mapSEIsToUuid;

	}

	/**
	 * Get the visualisation object of a SEI or if not exisiting create it
	 * 
	 * @param seiBean
	 *            the containing structural element instance bean
	 * @param importCommand
	 *            the compound command for the import
	 * @return the visualisation object
	 */
	private Visualisation getVisualisation(BeanStructuralElementInstance seiBean, CompoundCommand importCommand) {
		Visualisation visualisation = seiBean.getFirst(Visualisation.class);
		if (visualisation == null) {
			visualisation = createNewVisualisation(seiBean, importCommand);
		}
		return visualisation;
	}

	/**
	 * Create a new visualisation element from an exisitng SEI. The category is not
	 * yet added into the SEI
	 * 
	 * @param container
	 *            any structural element instance
	 * @param importCommand
	 *            the compound command for the import
	 * @return a new visualisation element
	 */
	private Visualisation createNewVisualisation(BeanStructuralElementInstance container,
			CompoundCommand importCommand) {

		Repository repository = VirSatResourceSet.getVirSatResourceSet(container.getStructuralElementInstance())
				.getRepository();
		ActiveConceptHelper activeConceptHelper = new ActiveConceptHelper(repository);
		Concept visConcept = activeConceptHelper.getConcept(de.dlr.sc.virsat.model.extension.visualisation.Activator.getPluginId());
		Visualisation visualisation = new Visualisation(visConcept);

		importCommand.append(container.add(editingDomain, visualisation));

		return visualisation;
	}

	/**
	 * Sets a double value to float bean if it is different than the current value
	 * 
	 * @param importCommand
	 *            the import command to append the command to if necessary
	 * @param floatBean
	 *            the float bean
	 * @param value
	 *            the value to be set
	 */
	private void setFloatValue(CompoundCommand importCommand, BeanPropertyFloat floatBean, double value) {
		if (floatBean.getValueToBaseUnit() != value) {
			importCommand.append(floatBean.setValueAsBaseUnit(editingDomain, value));
		}
	}

	/**
	 * Sets a shape value if it is different than the current shape
	 * 
	 * @param importCommand
	 *            the import command to append the command to if necessary
	 * @param visualisation
	 *            the current visualisation object
	 * @param value
	 *            the value to be set
	 * @param stlFile
	 *            the STL file path
	 */
	private void setShape(CompoundCommand importCommand, Visualisation visualisation, String value, String stlFile) {
		if (!visualisation.getShape().equals(value)) {
			importCommand.append(visualisation.setShape(editingDomain, value));
		}
		if (value.equals(Visualisation.SHAPE_GEOMETRY_NAME) && stlFile != null) {

			Path localPath = getLocalPath(stlFile, visualisation.getParent());
			importCommand.append(new CopyResourceCommand(Paths.get(stlFile), getAbsolutePath(localPath)));

			URI uri = URI.createPlatformResourceURI(localPath.toString(), false);
			
			// Do null-save comparison of URIs
			// URIs are not necessarily equal if their underlying path is equal
			String oldUriString = null;
			String newUriString = uri.toPlatformString(true);
			
			if (visualisation.getGeometryFile() != null) {
				oldUriString = visualisation.getGeometryFile().toPlatformString(true);
			} 
			if (!Objects.equals(oldUriString, newUriString)) {
				importCommand.append(visualisation.setGeometryFile(editingDomain, uri)); 
			}
		}
	}

	/**
	 * Sets a color value if it is different than the current color
	 * 
	 * @param importCommand
	 *            the import command to append the command to if necessary
	 * @param visualisation
	 *            the current visualisation object
	 * @param value
	 *            the value to be set
	 */
	private void setColor(CompoundCommand importCommand, Visualisation visualisation, long value) {
		if (visualisation.getColor() != value) {
			importCommand.append(visualisation.setColor(editingDomain, value));
		}
	}

	/**
	 * Sets the name value if it is different than the current name
	 * 
	 * @param importCommand
	 *            the import command to append the command to if necessary
	 * @param seiBean
	 *            the current structural element instance as bean
	 * @param value
	 *            the value to be set
	 */
	private void setName(CompoundCommand importCommand, BeanStructuralElementInstance seiBean, String value) {
		if (value != null && !seiBean.getName().equals(value)) {
			importCommand.append(seiBean.setName(editingDomain, value));
		}
	}

}
