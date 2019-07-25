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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * This class creates the JSON representation of a product structure tree.
 *
 */

public class CatiaExporter {
	
	private String geometryFilesPath;
	private Set<IBeanStructuralElementInstance> parts;
	
	/**
	 * Sets the path where all the geometry files are supposed to be (this class doesn't copy files)
	 * @param geometryFilesPath 
	 */
	public void setGeometryFilesPath(String geometryFilesPath) {
		this.geometryFilesPath = geometryFilesPath;
	}

	/**
	 * Main method that creates the JSON representation of a configuration tree
	 * @param configurationTree the configuration tree
	 * @return the json root object
	 */
	public JsonObject transform(ConfigurationTree configurationTree) {
		parts = new HashSet<>();
		JsonObject json = new JsonObject();

		JsonObject jsonProducts = transformProduct(configurationTree);
		if (jsonProducts != null) {
			json.put(CatiaProperties.PRODUCTS.getKey(), jsonProducts); 
		}
		
		json.put(CatiaProperties.PARTS.getKey(), transformParts(parts)); 
		
		return json;
	}

	/**
	 * Creates the JSON representation for a collection of parts
	 * @param parts the parts to transform
	 * @return the json representation
	 */
	public JsonArray transformParts(Collection<? extends IBeanStructuralElementInstance> parts) {
		JsonArray jsonParts = new JsonArray();
		
		for (IBeanStructuralElementInstance part : parts) {
			JsonObject jsonPart = transformPart(part);
			jsonParts.add(jsonPart);
		}
		
		return jsonParts;
	}

	/**
	 * Transforms a part bean into json oblect
	 * @param part bean with Visualisation attached
	 * @return json object for the part
	 */
	public JsonObject transformPart(IBeanStructuralElementInstance part) {
		JsonObject jsonPart = transformElement(part);

		Visualisation vis = part.getFirst(Visualisation.class);
		
		if (vis != null) {
			fillPartVisualisationFields(vis, jsonPart);
		} else {
			fillPartDummyVisualisationFields(jsonPart);
		}
		
		return jsonPart;
	}
	
	
	/**
	 * Creates a JSON object for a single virtual satellite element
	 * @param element the element definition
	 * @return the JSON object
	 */
	public JsonObject transformElement(IBeanStructuralElementInstance element) {
		JsonObject jsonElement = new JsonObject();
		jsonElement.put(CatiaProperties.NAME.getKey(), element.getName());
		jsonElement.put(CatiaProperties.UUID.getKey(), element.getUuid());
		return jsonElement;
	}

	/**
	 * Creates a JSON product object 
	 * @param productBean bean to get visualisation values from
	 * @return JSON product object
	 */
	private JsonObject transformProduct(IBeanStructuralElementInstance productBean) {
		Visualisation vis = productBean.getFirst(Visualisation.class);

		JsonObject jsonProduct = transformElement(productBean);
		
		if (vis != null) {
			fillProductVisualisationFields(vis, jsonProduct);
		} else if (hasDeepVisualisation(productBean)) {
			fillProductDummyVisualisationFields(jsonProduct);
		} else {
			return null;
		}
		
		IBeanStructuralElementInstance part = getPartForProduct(productBean);
		parts.add(part);
		
		jsonProduct.put(CatiaProperties.PRODUCT_ED_UUID.getKey(), part.getUuid());
		jsonProduct.put(CatiaProperties.PRODUCT_REFERENCE_NAME.getKey(), part.getName());
		
		List<IBeanStructuralElementInstance> subProducts = productBean.getChildren(IBeanStructuralElementInstance.class);
		JsonArray children = new JsonArray();
		for (IBeanStructuralElementInstance subProduct: subProducts) {
			JsonObject jsonSubProduct = transformProduct(subProduct);
			if (jsonSubProduct != null) {
				children.add(jsonSubProduct);
			}
		}
		jsonProduct.put(CatiaProperties.PRODUCT_CHILDREN.getKey(), children);
		
		return jsonProduct;
	}

	private IBeanStructuralElementInstance getPartForProduct(IBeanStructuralElementInstance productBean) {
		//todo get proper part
		return productBean;
	}

	/**
	 * Fill Visualisation fields in the given jsonProduct from the given visualisation bean
	 * @param vis visualisation bean
	 * @param jsonProduct 
	 */
	private void fillProductVisualisationFields(Visualisation vis, JsonObject jsonProduct) {
		jsonProduct.put(CatiaProperties.PRODUCT_POS_X.getKey(), vis.getPositionXBean().getValueToBaseUnit());
		jsonProduct.put(CatiaProperties.PRODUCT_POS_Y.getKey(), vis.getPositionYBean().getValueToBaseUnit());
		jsonProduct.put(CatiaProperties.PRODUCT_POS_Z.getKey(), vis.getPositionZBean().getValueToBaseUnit());
		jsonProduct.put(CatiaProperties.PRODUCT_ROT_X.getKey(), vis.getRotationXBean().getValueToBaseUnit());
		jsonProduct.put(CatiaProperties.PRODUCT_ROT_Y.getKey(), vis.getRotationYBean().getValueToBaseUnit());
		jsonProduct.put(CatiaProperties.PRODUCT_ROT_Z.getKey(), vis.getRotationZBean().getValueToBaseUnit());
		jsonProduct.put(CatiaProperties.PRODUCT_SHAPE.getKey(), vis.getShape());
		
		if (vis.getShape().equals(Visualisation.SHAPE_GEOMETRY_NAME)) {
			URI geometryUri = vis.getGeometryFile();
			if (geometryUri != null) {
				String geometryFileName = geometryUri.lastSegment();
				Path filePath = Paths.get(geometryFilesPath, geometryFileName);
				jsonProduct.put(CatiaProperties.PRODUCT_STL_PATH.getKey(), filePath.toString());
			}
		}
	}

	/**
	 * Fill Visualisation fields in the given jsonProduct with zeroes and shape none
	 * @param jsonProduct 
	 */
	private void fillProductDummyVisualisationFields(JsonObject jsonProduct) {
		jsonProduct.put(CatiaProperties.PRODUCT_POS_X.getKey(), 0);
		jsonProduct.put(CatiaProperties.PRODUCT_POS_Y.getKey(), 0);
		jsonProduct.put(CatiaProperties.PRODUCT_POS_Z.getKey(), 0);
		jsonProduct.put(CatiaProperties.PRODUCT_ROT_X.getKey(), 0);
		jsonProduct.put(CatiaProperties.PRODUCT_ROT_Y.getKey(), 0);
		jsonProduct.put(CatiaProperties.PRODUCT_ROT_Z.getKey(), 0);
		jsonProduct.put(CatiaProperties.PRODUCT_SHAPE.getKey(), Visualisation.SHAPE_NONE_NAME);
	}

	/**
	 * Fill Visualisation fields in the given jsonPart from the given visualisation bean
	 * @param vis visualisation bean
	 * @param jsonPart 
	 */
	private void fillPartVisualisationFields(Visualisation vis, JsonObject jsonPart) {
		jsonPart.put(CatiaProperties.PART_LENGTH_X.getKey(), vis.getSizeXBean().getValueToBaseUnit());
		jsonPart.put(CatiaProperties.PART_LENGTH_Y.getKey(), vis.getSizeYBean().getValueToBaseUnit());
		jsonPart.put(CatiaProperties.PART_LENGTH_Z.getKey(), vis.getSizeZBean().getValueToBaseUnit());
		jsonPart.put(CatiaProperties.PART_RADIUS.getKey(), vis.getRadiusBean().getValueToBaseUnit());
		jsonPart.put(CatiaProperties.PART_COLOR.getKey(), vis.getColor());
		
		jsonPart.put(CatiaProperties.PART_SHAPE.getKey(), vis.getShape());
		
		if (vis.getShape().equals(Visualisation.SHAPE_GEOMETRY_NAME)) {
			URI geometryUri = vis.getGeometryFile();
			if (geometryUri != null) {
				String geometryFileName = geometryUri.lastSegment();
				Path filePath = Paths.get(geometryFilesPath, geometryFileName);
				jsonPart.put(CatiaProperties.PART_STL_PATH.getKey(), filePath.toString());
			}
		}
	}

	/**
	 * Fill Visualisation fields in the given jsonPart with zeroes
	 * @param jsonPart 
	 */
	private void fillPartDummyVisualisationFields(JsonObject jsonPart) {
		jsonPart.put(CatiaProperties.PART_LENGTH_X.getKey(), 0);
		jsonPart.put(CatiaProperties.PART_LENGTH_Y.getKey(), 0);
		jsonPart.put(CatiaProperties.PART_LENGTH_Z.getKey(), 0);
		jsonPart.put(CatiaProperties.PART_RADIUS.getKey(), 0);
		jsonPart.put(CatiaProperties.PART_COLOR.getKey(), 0);
		
		jsonPart.put(CatiaProperties.PART_SHAPE.getKey(), Visualisation.SHAPE_NONE_NAME);
	}
	
	/**
	 * @param productBean 
	 * @return true if this bean has a deep child with visualisation
	 */
	private boolean hasDeepVisualisation(IBeanStructuralElementInstance productBean) {
		List<IBeanStructuralElementInstance> deepChildren = productBean.getDeepChildren(IBeanStructuralElementInstance.class);
		return deepChildren.stream().anyMatch(child -> child.getFirst(Visualisation.class) != null);
	}
}
