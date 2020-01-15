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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.tree.BeanStructuralTreeTraverser;
import de.dlr.sc.virsat.model.concept.types.structural.tree.IBeanStructuralTreeTraverserMatcher;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * This class creates the JSON representation of a product structure tree.
 *
 */

public class CadExporter {
	
	private String geometryFilesPath;
	private Set<IBeanStructuralElementInstance> parts;
	private Map<IBeanStructuralElementInstance, JsonObject> mapBeansToJsonProducts;
	private Set<Visualisation> geometryVisualisations;
	
	/**
	 * Sets the path where all the geometry files are supposed to be (this class doesn't copy files)
	 * @param geometryFilesPath 
	 */
	public void setGeometryFilesPath(String geometryFilesPath) {
		this.geometryFilesPath = geometryFilesPath;
	}

	/**
	 * Main method that creates the JSON representation of a tree
	 * @param root the root of the tree
	 * @return the json root object
	 */
	public JsonObject transform(IBeanStructuralElementInstance root) {
		parts = new HashSet<>();
		geometryVisualisations = new HashSet<>();
		JsonObject json = new JsonObject();

		JsonObject jsonProducts = transformProduct(root);
		if (jsonProducts != null) {
			json.put(CadProperties.PRODUCTS.getKey(), jsonProducts); 
		}
		
		json.put(CadProperties.PARTS.getKey(), transformParts(parts)); 
		
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
	 * Transforms a part bean into json object
	 * @param part bean with Visualisation attached
	 * @return json object for the part
	 */
	public JsonObject transformPart(IBeanStructuralElementInstance part) {
		JsonObject jsonPart = transformElement(part);

		Visualisation vis = part.getFirst(Visualisation.class);
		
		if (vis != null) {
			transformPartVisualisationFields(vis, jsonPart);
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
		jsonElement.put(CadProperties.NAME.getKey(), element.getName());
		jsonElement.put(CadProperties.UUID.getKey(), element.getUuid());
		return jsonElement;
	}

	
	/**
	 * Creates a JSON product object 
	 * @param productBean bean to get visualisation values from
	 * @return JSON product object
	 */
	private JsonObject transformProduct(IBeanStructuralElementInstance productBean) {
		mapBeansToJsonProducts = new HashMap<>();
		BeanStructuralTreeTraverser traverser = new BeanStructuralTreeTraverser();
		
		traverser.traverse(productBean, new IBeanStructuralTreeTraverserMatcher() {
			Visualisation visCa;
			boolean hasVisCa;
			boolean isRoot;
			
			@Override
			public boolean isMatching(IBeanStructuralElementInstance treeNode) {
				visCa = treeNode.getFirst(Visualisation.class);
				hasVisCa = visCa != null;
				isRoot = treeNode == productBean;
				
				return isRoot || hasVisCa;
			}
			
			@Override
			public void processMatch(IBeanStructuralElementInstance treeNode, IBeanStructuralElementInstance matchingParent) {
				JsonObject jsonProduct = transformElement(treeNode);
				mapBeansToJsonProducts.put(treeNode, jsonProduct);
				jsonProduct.put(CadProperties.PRODUCT_CHILDREN.getKey(), new JsonArray());

				if (hasVisCa) {
					transformProductVisualisationFields(visCa, jsonProduct);

					// In case the Visualization has a shape of none, it should not be exported as a shape
					// within the product tree only the rotation and translation are needed.
					// this goes back to ticket https://github.com/virtualsatellite/VirtualSatellite4-Core/issues/220
					boolean isNoneShape = visCa.getShape().equals(Visualisation.SHAPE_NONE_NAME);
					if (!isNoneShape) {
						IBeanStructuralElementInstance part = getPartForProduct(treeNode);
						parts.add(part);
						jsonProduct.put(CadProperties.PRODUCT_PART_UUID.getKey(), part.getUuid());
						jsonProduct.put(CadProperties.PRODUCT_PART_NAME.getKey(), part.getName());
					}
				}
				
				if (matchingParent != null) {
					JsonObject parentJsonObject = mapBeansToJsonProducts.get(matchingParent);
					
					// See if there is already an array for children on the parent
					JsonArray jsonArrayChildren = parentJsonObject.getCollection(CadProperties.PRODUCT_CHILDREN);
					jsonArrayChildren.add(jsonProduct);
				}
			}
		});
		
		// if there are no Visualisations in the whole tree, there will be no parts,
		// therefore we don't export anything
		if (!parts.isEmpty()) {
			return mapBeansToJsonProducts.get(productBean);
		} else {
			return null;
		}
	}

	/**
	 * @param productBean 
	 * @return corresponding part bean for the given product
	 */
	private IBeanStructuralElementInstance getPartForProduct(IBeanStructuralElementInstance productBean) {
		InheritanceCopier ic = new InheritanceCopier();
		List<StructuralElementInstance> orderedSuperSeis = ic.getSuperSeisInheritanceOrder(productBean.getStructuralElementInstance());

		List<Visualisation> orderedSuperVisualisations = orderedSuperSeis.stream()
				.map(x -> new BeanStructuralElementInstance(x))
				.flatMap(x -> x.getAll(Visualisation.class).stream())
				.collect(Collectors.toList());
		
		if (orderedSuperVisualisations.isEmpty()) {
			return productBean;
		}
		
		IBeanStructuralElementInstance partBean = orderedSuperVisualisations.get(0).getParent();
		Collections.reverse(orderedSuperVisualisations);
		
		for (Visualisation superVisualisation : orderedSuperVisualisations) {
			if (overridesAnyPartValue(superVisualisation)) {
				partBean = superVisualisation.getParent();
				break;
			}
		}
		
		return partBean;
	}

	/**
	 * Checks if the given visualisation bean overrides any values that go into json part
	 * @param visualisation 
	 * @return true iff part information is overriden
	 */
	private boolean overridesAnyPartValue(Visualisation visualisation) {
		return visualisation.getSizeXBean().getTypeInstance().isOverride()
				|| visualisation.getSizeYBean().getTypeInstance().isOverride()
				|| visualisation.getSizeZBean().getTypeInstance().isOverride()
				|| visualisation.getRadiusBean().getTypeInstance().isOverride()
				|| visualisation.getColorBean().getTypeInstance().isOverride()
				|| visualisation.getShapeBean().getTypeInstance().isOverride()
				|| visualisation.getGeometryFileBean().getTypeInstance().isOverride();
	}

	/**
	 * Fill Visualisation fields in the given jsonProduct from the given visualisation bean
	 * @param vis visualisation bean
	 * @param jsonProduct 
	 */
	private void transformProductVisualisationFields(Visualisation vis, JsonObject jsonProduct) {
		jsonProduct.put(CadProperties.PRODUCT_POS_X.getKey(), vis.getPositionXBean().getValueToBaseUnit());
		jsonProduct.put(CadProperties.PRODUCT_POS_Y.getKey(), vis.getPositionYBean().getValueToBaseUnit());
		jsonProduct.put(CadProperties.PRODUCT_POS_Z.getKey(), vis.getPositionZBean().getValueToBaseUnit());
		jsonProduct.put(CadProperties.PRODUCT_ROT_X.getKey(), vis.getRotationXBean().getValueToBaseUnit());
		jsonProduct.put(CadProperties.PRODUCT_ROT_Y.getKey(), vis.getRotationYBean().getValueToBaseUnit());
		jsonProduct.put(CadProperties.PRODUCT_ROT_Z.getKey(), vis.getRotationZBean().getValueToBaseUnit());
	}

	/**
	 * Fills geometry path key in the given json from the given visualisation bean
	 * @param vis 
	 * @param jsonObject 
	 * @param geometryPathKey 
	 */
	private void fillGeometryField(Visualisation vis, JsonObject jsonObject, JsonKey geometryPathKey) {
		if (vis.getShape().equals(Visualisation.SHAPE_GEOMETRY_NAME)) {
			URI geometryUri = vis.getGeometryFile();
			if (geometryUri != null) {
				String geometryFileName = geometryUri.lastSegment();
				Path filePath = Paths.get(geometryFilesPath, geometryFileName);
				jsonObject.put(geometryPathKey.getKey(), filePath.toString());
				geometryVisualisations.add(vis);
			}
		}
	}

	/**
	 * Fill Visualisation fields in the given jsonPart from the given visualisation bean
	 * @param vis visualisation bean
	 * @param jsonPart 
	 */
	private void transformPartVisualisationFields(Visualisation vis, JsonObject jsonPart) {
		jsonPart.put(CadProperties.PART_LENGTH_X.getKey(), vis.getSizeXBean().getValueToBaseUnit());
		jsonPart.put(CadProperties.PART_LENGTH_Y.getKey(), vis.getSizeYBean().getValueToBaseUnit());
		jsonPart.put(CadProperties.PART_LENGTH_Z.getKey(), vis.getSizeZBean().getValueToBaseUnit());
		jsonPart.put(CadProperties.PART_RADIUS.getKey(), vis.getRadiusBean().getValueToBaseUnit());
		jsonPart.put(CadProperties.PART_COLOR.getKey(), vis.getColor());
		jsonPart.put(CadProperties.PART_SHAPE.getKey(), vis.getShape());
		
		fillGeometryField(vis, jsonPart, CadProperties.PART_STL_PATH);
	}

	/**
	 * This method should be called after {@link #transform(IBeanStructuralElementInstance)}
	 * @return set of Visualisation beans with external geometry
	 */
	public Set<Visualisation> getGeometryVisualisations() {
		return geometryVisualisations;
	}
}
