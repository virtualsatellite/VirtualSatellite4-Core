/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.catia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.mechanical.catia.util.CatiaHelper;
import de.dlr.sc.virsat.model.extension.visualisation.Activator;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * This class imports a JSON representation of a product tree
 *
 */
public class CatiaImporter {

	private EditingDomain editingDomain;

	public static final String POSITION_BASE_UNIT = "Millimeter";
	public static final String SIZE_BASE_UNIT = "Millimeter";
	public static final String ROTATION_BASE_UNIT = "Degree";

	/**
	 * Main method that creates the JSON representation of a configuration tree
	 * 
	 * @param editingDomain 
	 * 			  the editing domain of the current project
	 * @param jsonObject
	 *            the Json Object
	 * @param mapJSONtoSEI
	 *            the mapping of JSON elements to the existing trees
	 * 
	 * @return the emf command to execute the import
	 */
	public Command transform(EditingDomain editingDomain, JsonObject jsonObject, Map<String, StructuralElementInstance> mapJSONtoSEI) {

		CompoundCommand importCommand = new CompoundCommand();
		this.editingDomain = editingDomain;

		// Import parts
		for (JsonObject part : CatiaHelper.getListOfAllJSONParts(jsonObject)) {
			updateSeiFromPart(importCommand, mapJSONtoSEI.get(part.getString(CatiaProperties.UUID)), part);
		}

		// Import products
		for (JsonObject product : CatiaHelper.getListOfAllJSONProducts(jsonObject)) {
			updateSeiFromProduct(importCommand, mapJSONtoSEI.get(product.getString(CatiaProperties.UUID)), product);
		}

		return importCommand;
	}

	/**
	 * Map the imported JSON elements to existing model elements in the Virtual
	 * Satellite model and return the ones for which no mapping can be found
	 * 
	 * @param jsonContent
	 *            the JSON content to be imported
	 * @param existingTree
	 *            a tree element the imported JSON should be mapped to
	 * @return a map of existing tree elements to their UUID in the JSON file
	 */
	public Map<String, StructuralElementInstance> mapJSONtoSEI(JsonObject jsonContent,
			IBeanStructuralElementInstance existingTree) {

		Map<String, StructuralElementInstance> mapExisitingElementToUUID = new HashMap<String, StructuralElementInstance>();
		Map<String, IBeanStructuralElementInstance> mapSEIsToUuid = createMapOfTreeSEIsToUuid(existingTree);

		for (JsonObject object : CatiaHelper.getListOfAllJSONElements(jsonContent)) {
			String uuid = object.getString(CatiaProperties.UUID);
			IBeanStructuralElementInstance mappedElement = mapSEIsToUuid.get(uuid);
			if (mappedElement != null) {
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
	 *            the JSON root element to look for
	 * @param mapJSONtoSEI
	 *            the Map of SEIs to JSONObjects created by method
	 *            {@link #mapJSONtoSEI(JsonObject, IBeanStructuralElementInstance)}
	 * @return a list of unmapped elements
	 */
	public List<JsonObject> getUnmappedJSONObjects(JsonObject jsonRoot,
			Map<String, StructuralElementInstance> mapJSONtoSEI) {

		List<JsonObject> unmappedElements = new ArrayList<>();

		for (JsonObject object : CatiaHelper.getListOfAllJSONElements(jsonRoot)) {
			String uuid = object.getString(CatiaProperties.UUID);
			if (mapJSONtoSEI.get(uuid) == null) {
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
	 */
	protected void updateSeiFromPart(CompoundCommand importCommand, StructuralElementInstance sei, JsonObject part) {
		BeanStructuralElementInstance beanSEI = new BeanStructuralElementInstance(sei);
		Visualisation visualisation = getVisualisation(beanSEI, importCommand);
		
		double sizeX;
		double sizeY;
		double sizeZ;
		double radius;

		long color;
		String shape;
		String stlFile = null;
		try {
			sizeX = part.getDouble(CatiaProperties.PART_LENGTH_X);
			sizeY = part.getDouble(CatiaProperties.PART_LENGTH_Y);
			sizeZ = part.getDouble(CatiaProperties.PART_LENGTH_Z);
			radius = part.getDouble(CatiaProperties.PART_RADIUS);

			color = part.getLong(CatiaProperties.PART_COLOR);
			shape = part.getString(CatiaProperties.PART_SHAPE);
			
		} catch (NullPointerException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), 
					"CatiaImport: Failed to perform import! Could not load all required properties", e));
			return;
		}
		
		if (part.containsKey(CatiaProperties.PART_STL_PATH.getKey())) {
			stlFile = part.getString(CatiaProperties.PART_STL_PATH);
		}

		importCommand.append(visualisation.setSizeX(editingDomain, sizeX));
		importCommand.append(visualisation.getSizeXBean().setUnit(editingDomain, SIZE_BASE_UNIT));
		importCommand.append(visualisation.setSizeY(editingDomain, sizeY));
		importCommand.append(visualisation.getSizeYBean().setUnit(editingDomain, SIZE_BASE_UNIT));
		importCommand.append(visualisation.setSizeZ(editingDomain, sizeZ));
		importCommand.append(visualisation.getSizeZBean().setUnit(editingDomain, SIZE_BASE_UNIT));
		importCommand.append(visualisation.setRadius(editingDomain, radius));
		importCommand.append(visualisation.getRadiusBean().setUnit(editingDomain, SIZE_BASE_UNIT));

		importCommand.append(visualisation.setShape(editingDomain, shape));
		importCommand.append(visualisation.setColor(editingDomain, color));

		if (shape.equals(Visualisation.SHAPE_GEOMETRY_NAME) && stlFile != null) {
			importCommand
					.append(visualisation.setGeometryFile(editingDomain, copyAndGetPlatformResource(stlFile, beanSEI)));
		}

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
	 */
	protected void updateSeiFromProduct(CompoundCommand importCommand, StructuralElementInstance sei,
			JsonObject product) {
		BeanStructuralElementInstance beanSEI = new BeanStructuralElementInstance(sei);
		Visualisation visualisation = getVisualisation(beanSEI, importCommand);

		double posX;
		double posY;
		double posZ;

		double rotX;
		double rotY;
		double rotZ;
		
		long color;
		String shape;
		String stlFile = null;
		
		try {
			posX = product.getDouble(CatiaProperties.PRODUCT_POS_X);
			posY = product.getDouble(CatiaProperties.PRODUCT_POS_Y);
			posZ = product.getDouble(CatiaProperties.PRODUCT_POS_Z);

			rotX = product.getDouble(CatiaProperties.PRODUCT_ROT_X);
			rotY = product.getDouble(CatiaProperties.PRODUCT_ROT_Y);
			rotZ = product.getDouble(CatiaProperties.PRODUCT_ROT_Z);
			
			shape = product.getString(CatiaProperties.PRODUCT_SHAPE);
			color = product.getLong(CatiaProperties.PART_COLOR);
			
		} catch (NullPointerException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), 
					"CatiaImport: Failed to perform import! Could not load all required properties", e));
			return;
		}
		
		if (product.containsKey(CatiaProperties.PART_STL_PATH.getKey())) {
			stlFile = product.getString(CatiaProperties.PART_STL_PATH);
		}

		importCommand.append(visualisation.setPositionX(editingDomain, posX));
		importCommand.append(visualisation.getPositionYBean().setUnit(editingDomain, POSITION_BASE_UNIT));

		importCommand.append(visualisation.setPositionY(editingDomain, posY));
		importCommand.append(visualisation.getPositionYBean().setUnit(editingDomain, POSITION_BASE_UNIT));

		importCommand.append(visualisation.setPositionZ(editingDomain, posZ));
		importCommand.append(visualisation.getPositionZBean().setUnit(editingDomain, POSITION_BASE_UNIT));

		importCommand.append(visualisation.setRotationX(editingDomain, rotX));
		importCommand.append(visualisation.getRotationXBean().setUnit(editingDomain, ROTATION_BASE_UNIT));

		importCommand.append(visualisation.setRotationY(editingDomain, rotY));
		importCommand.append(visualisation.getRotationYBean().setUnit(editingDomain, ROTATION_BASE_UNIT));

		importCommand.append(visualisation.setRotationZ(editingDomain, rotZ));
		importCommand.append(visualisation.getRotationZBean().setUnit(editingDomain, ROTATION_BASE_UNIT));

		importCommand.append(visualisation.setShape(editingDomain, shape));
		importCommand.append(visualisation.setColor(editingDomain, color));

		if (shape.equals(Visualisation.SHAPE_GEOMETRY_NAME) && stlFile != null) {
			importCommand
					.append(visualisation.setGeometryFile(editingDomain, copyAndGetPlatformResource(stlFile, beanSEI)));
		}

	}

	/**
	 * Copy a given stl resource to the workspace and return its URI
	 * 
	 * @param stlPath
	 *            the stl resource's path
	 * @param seiBean
	 *            a structural element instance bean
	 * @return the URI
	 */
	private URI copyAndGetPlatformResource(String stlPath, BeanStructuralElementInstance seiBean) {

		URI stlURI = null;
		// Copy file to workspace
		Path catiaSTLPath = Paths.get(stlPath);
		try {
			String workspacePath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toOSString();
			String documentPath = VirSatProjectCommons.getDocumentFolder(seiBean.getStructuralElementInstance())
					.getFullPath().toOSString();
			String stlName = catiaSTLPath.getFileName().toString();
			Path localPath = Paths.get(documentPath, stlName);
			Path workspace = Paths.get(workspacePath, localPath.toString());

			Files.copy(catiaSTLPath, workspace, StandardCopyOption.REPLACE_EXISTING);
			stlURI = URI.createPlatformResourceURI(localPath.toString(), false);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return stlURI;

	}

	/**
	 * Create a map of all structural element instances in a tree to their UUID
	 * 
	 * @param existingTree
	 *            the existing tree element in the Virtual Satellite model
	 * @return a map that maps all SEIs to their UUID
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
		if (visualisation != null) {
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

		VirSatTransactionalEditingDomain virSatEditingDomain = VirSatEditingDomainRegistry.INSTANCE
				.getEd(container.getStructuralElementInstance());
		Repository repository = virSatEditingDomain.getResourceSet().getRepository();
		ActiveConceptHelper activeConceptHelper = new ActiveConceptHelper(repository);
		Concept visConcept = activeConceptHelper.getConcept(Activator.getPluginId());
		Visualisation visualisation = new Visualisation(visConcept);

		importCommand.append(AddCommand.create(editingDomain, container.getStructuralElementInstance(),
				CategoriesPackage.Literals.ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS,
				visualisation.getTypeInstance()));

		return visualisation;
	}

}
