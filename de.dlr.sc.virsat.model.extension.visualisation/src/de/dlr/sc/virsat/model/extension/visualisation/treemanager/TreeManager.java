/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.treemanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;

import com.google.protobuf.ByteString;

import de.dlr.sc.virsat.model.extension.visualisation.Activator;
import de.dlr.sc.virsat.model.extension.visualisation.IVisualisationTreeManager;
import de.dlr.sc.virsat.model.extension.visualisation.shape.IShapeEditObserver;
import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;
import de.dlr.sc.virsat.model.extension.visualisation.shape.VisualisationShape;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.networking.CommunicationHandler;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.networking.CommunicationServer;
import de.dlr.sc.visproto.VisProto.Geometry;
import de.dlr.sc.visproto.VisProto.GeometryFile;
import de.dlr.sc.visproto.VisProto.VisualisationMessage;
import de.dlr.sc.visproto.VisProto.SceneGraph;
import de.dlr.sc.visproto.VisProto.SceneGraphNode;
import de.dlr.sc.visproto.VisProto.SceneGraphNodeOrBuilder;
import de.dlr.sc.visproto.VisProto.SceneGraphNode.Builder;


/**
 * This class is responsible for the management of the ProtoBuf-SceneGraph
 */
public class TreeManager implements IVisualisationTreeManager, IPausableSender {
	
	//global root node created on initialisation which always exists
	public static final String VISUALISATION_GLOBAL_ROOT_ID = "0";

	private Activator activator = new Activator();
	private SceneGraph.Builder mSceneGraph = SceneGraph.newBuilder();
	private Map<String, SceneGraphNode.Builder> protoNodeMap = new HashMap<String, SceneGraphNode.Builder>();
	private Map<String, SceneGraphNode.Builder> parentNodes = new HashMap<String, SceneGraphNode.Builder>();
	private Map<String, GeometryFile> protoGeometryFileMap = new HashMap<String, GeometryFile>();
	private Map<String, Integer> hashNodeMap = new HashMap<String, Integer>();
	private IShapeEditObserver observer;
	private CommunicationHandler sceneGraphCommunicationHandler;
	private CommunicationHandler geometryFileCommunicationHandler;

	private boolean sendingPaused = false;
	
	/**
	 * Log root node
	 */
	public TreeManager() {
		Builder sceneGraphRoot = mSceneGraph.getNodeBuilder();
		sceneGraphRoot.setID(VISUALISATION_GLOBAL_ROOT_ID);
		sceneGraphRoot.setColor(0);
		protoNodeMap.put(VISUALISATION_GLOBAL_ROOT_ID, sceneGraphRoot);
	}

	/**
	 * Search for id and returns the mapped object
	 * 
	 * @param id id of the node
	 * @throws IllegalArgumentException if a node with the given id is not found
	 * @return matching node
	 */
	private SceneGraphNode.Builder getNode(String id) throws IllegalArgumentException {
		if (protoNodeMap.get(id) != null) {
			return protoNodeMap.get(id);
		} else {
			activator.getLog().log(new Status(Status.ERROR, Activator.getPluginId(), id + " not found", null));
			throw new IllegalArgumentException("ID not found");
		}
	}
	

	/**
	 * Return the shape with the given id
	 * 
	 * @param id The ID of the shape
	 * @throws IllegalArgumentException if a shape with the given id is not found
	 * @return matching Shape
	 */
	public Shape getShape(String id) {
		return convertProtobufNodeToShape(getNode(id));
	}

	/**
	 * @return number of created shapes (excluding the global root which is always present)
	 */
	public int getShapesCount() {
		return protoNodeMap.size() - 1;
	}
	
	@Override
	public void editShape(Shape shape) {
		switch (shape.shape) {
			case GEOMETRY:
				if (geometryFilePathHasChanged(shape.id, shape.geometryFile)) {
					editGeometry(shape.id, shape.geometryFile);
					createGeometryFileBuilder(shape.id);
					geometryFileCommunicationHandler.sendGeometryFile(protoGeometryFileMap.get(shape.id));
				}
				break;
			case BOX:
				editBox(shape.id, shape.sizeX, shape.sizeY, shape.sizeZ);
				break;
			case CYLINDER:
				editCylinder(shape.id, shape.radius, shape.sizeY);
				break;
			case CONE:
				editCone(shape.id, shape.radius, shape.sizeY);
				break;
			case SPHERE:
				editSphere(shape.id, shape.radius);
				break;
			case NONE:
				break;
			default:
				String errorMessage = "Error at editing shape with id=" + shape.id + ". Unknown type of shape " + shape.shape.toString();
				activator.getLog().log(new Status(Status.ERROR, Activator.getPluginId(), errorMessage, null));
				throw new RuntimeException(errorMessage);
		}
		editTransformNode(shape.id, shape.positionX, shape.positionY, shape.positionZ,
				shape.rotationX, shape.rotationY, shape.rotationZ, shape.color, shape.transparency);
		if (!protoNodeMap.get(shape.id).hasGeometry() && protoGeometryFileMap.containsKey(shape.id)) {
			protoGeometryFileMap.remove(shape.id);
		}
		if (sendingPossible()) {
			sceneGraphCommunicationHandler.sendSceneGraph(mSceneGraph.build());
		}
	}

	/**
	 * Checks whether a SceneGraphNode reference to a STL-File is still valid.
	 * @param id 			ID of the SceneGraphNode containing a Geometry-Shape.
	 * @param geometryFile 	GeometryFile Resource which potentially references a different STL-File.
	 * @return 				true if referenced STL-File has changed.
	 */
	private boolean geometryFilePathHasChanged(String id, URI geometryFile) {
		String currentGeometryPath = protoNodeMap.get(id).getGeometry().getOriginFilepath();
		String nextGeometryPath = getGeometryFilePathString(geometryFile);	
		return !currentGeometryPath.equals(nextGeometryPath);
	}

	/**
	 * Edit the Geometry-Shape in case the referenced Resource has changed.
	 * 
	 * @param id 				ID of the corresponding SceneGraphNode.
	 * @param geometryFilePath 	Path to new Resource.
	 */
	private void editGeometry(String id, URI geometryFilePath) {
		SceneGraphNode.Builder node = getNode(id);
		Geometry.Builder geometry = node.getGeometryBuilder();
		geometry.setOriginFilepath(getGeometryFilePathString(geometryFilePath));
	}

	/**
	 * Edit the box
	 * @param id ID
	 * @param x sizeX
	 * @param y sizeY
	 * @param z sizeZ
	 */
	private void editBox(String id, float x, float y, float z) {
		SceneGraphNode.Builder node = getNode(id);
		node.getBoxBuilder().setX(x).setY(y).setZ(z);
	}

	/**
	 * Edit cone
	 * @param id id
	 * @param radius radius
	 * @param height height
	 */
	private void editCone(String id, float radius, float height) {
		SceneGraphNode.Builder node = getNode(id);
		node.getConeBuilder().setRadius(radius).setHeight(height);
	}

	/**
	 * Edit Sphere
	 * @param id id
	 * @param radius radius
	 */
	private void editSphere(String id, float radius) {
		SceneGraphNode.Builder node = getNode(id);
		node.getBallBuilder().setRadius(radius);
	}

	/**
	 * Edit cylinder
	 * @param id id
	 * @param radius radius
	 * @param height height
	 */
	private void editCylinder(String id, float radius, float height) {
		SceneGraphNode.Builder node = getNode(id);
		node.getCylinderBuilder().setRadius(radius).setHeight(height);
	}

	/**
	 * Edit TransformNode
	 * 
	 * @param id id of transformnode
	 * @param posX relative position x
	 * @param posY relative position y
	 * @param posZ relative position z
	 * @param rotX relative rotation x
	 * @param rotY relative rotation y
	 * @param rotZ relative rotation z
	 * @param color color in integer representation 0xRRGGBB
	 * @param opacity opacity
	 */
	private void editTransformNode(String id, double posX, double posY, double posZ, double rotX, double rotY,
			double rotZ, int color, float opacity) {
		editPosition(id, posX, posY, posZ);
		editOrientation(id, rotX, rotY, rotZ);
		editOpacity(id, opacity);
		editColor(id, color);
	}

	/**
	 * Edit relative position
	 * @param id id
	 * @param x relative x
	 * @param y relative y
	 * @param z relative z
	 */
	private void editPosition(String id, double x, double y, double z) {
		SceneGraphNode.Builder node = getNode(id);
		node.getPositionBuilder().setX(x).setY(y).setZ(z);
	}

	/**
	 * Edit relative orientation
	 * @param id id
	 * @param x angle x
	 * @param y angle y
	 * @param z angle z
	 */
	private void editOrientation(String id, double x, double y, double z) {
		SceneGraphNode.Builder node = getNode(id);
		node.getOrientationBuilder().setX(x).setY(y).setZ(z);
	}

	/**
	 * Edit transparency
	 * @param id id
	 * @param factor factor
	 */
	private void editOpacity(String id, float factor) {
		SceneGraphNode.Builder node = getNode(id);
		node.setOpacity(factor);
	}

	/**
	 * Edit color
	 * @param id id
	 * @param intColor color in integer representation 0xRRGGBB
	 */
	private void editColor(String id, int intColor) {
		SceneGraphNode.Builder node = getNode(id);
		node.setColor(intColor);
	}
	
	/**
	 * Returns the relative path to the Resource. If the path is not set (null) it 
	 * will return an empty string.
	 * @param geometryFile 		Resource to retrieve path of.
	 * @return String			Path to resource.
	 */
	private String getGeometryFilePathString(URI geometryFile) {
		if (geometryFile == null) {
			return "";
		} else {
			return geometryFile.toPlatformString(true);
		}
	}

	/**
	 * Create a new visualized Shape
	 */
	@Override
	public void createShape(Shape shape) {
		if (!protoNodeMap.containsKey(shape.id)) {
			switch (shape.shape) {
				case GEOMETRY:
					createGeometry(shape.id, shape.geometryFile);
					if (!protoNodeMap.get(shape.id).getGeometry().getOriginFilepath().equals("")) {
						createGeometryFileBuilder(shape.id);
						geometryFileCommunicationHandler.sendGeometryFile(protoGeometryFileMap.get(shape.id));
					}
					break;
				case BOX:
					createBox(shape.id, shape.sizeX, shape.sizeY, shape.sizeZ);
					break;
				case CYLINDER:
					createCylinder(shape.id, shape.radius, shape.sizeY);
					break;
				case CONE:
					createCone(shape.id, shape.radius, shape.sizeY);
					break;
				case SPHERE:
					createSphere(shape.id, shape.radius);
					break;
				case NONE:
					createNone(shape.id);
					break;
				default:
					String errorMessage = "Error at creating new shape. Unknown type of shape " + shape.shape.toString();
					activator.getLog().log(new Status(Status.ERROR, Activator.getPluginId(), errorMessage, null));
					throw new RuntimeException(errorMessage);
			}
			editTransformNode(shape.id, shape.positionX, shape.positionY, shape.positionZ,
					shape.rotationX, shape.rotationY, shape.rotationZ,
					shape.color, shape.transparency);
			addChildNode(VISUALISATION_GLOBAL_ROOT_ID, shape.id);
			if (sendingPossible()) {
				sceneGraphCommunicationHandler.sendSceneGraph(mSceneGraph.build());
			}
		} else {
			activator.getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "ID already exists", null));
			throw new RuntimeException("Error at creating new shape. ID already exist");
		}
	}

	/**
	 * Creates a SceneGraphNode containing a Geometry-Shape.
	 * @param id 				ID of this Geometry-Shape
	 * @param geometryFilePath 	Path to Resource of this Geometry-Shape
	 */
	private void createGeometry(String id, URI geometryFilePath) {
		SceneGraphNode.Builder node = SceneGraphNode.newBuilder();
		node.setID(id);
		node.getGeometryBuilder().setOriginFilepath(getGeometryFilePathString(geometryFilePath));
		protoNodeMap.put(id, node);
	}
	
	/**
	 * Creates a GeometryFile and writes STL data into it.
	 * @param id 	SceneGraphNode this GeometryFile is associated with.
	 */
	private void createGeometryFileBuilder(String id) {
		GeometryFile.Builder geometryFileBuilder = GeometryFile.newBuilder();
		geometryFileBuilder.setNodeID(id);
		SceneGraphNode.Builder geometry = protoNodeMap.get(id);
		
		String relativeDocumentPath = geometry.getGeometry().getOriginFilepath();
		String currentWorkspacePath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toOSString();
		Path path = Paths.get(currentWorkspacePath, relativeDocumentPath);
		
		try {
			byte[] geometryFileBytes = Files.readAllBytes(path);
			geometryFileBuilder.setFileContent(ByteString.copyFrom(geometryFileBytes));
			protoGeometryFileMap.put(id, geometryFileBuilder.build());
		} catch (IOException e) {
			activator.getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Error reading file " + path.toString(), e));
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets all values of geometryFileMap
	 * @return 		values of protoGeometryFileMap
	 */
	public Collection<GeometryFile> getProtoGeometryFileMapValues() {
		return protoGeometryFileMap.values();
	}

	/**
	 * Create cone
	 * @param id id
	 * @param radius radius
	 * @param height height
	 */
	private void createCone(String id, float radius, float height) {
		SceneGraphNode.Builder node = SceneGraphNode.newBuilder();
		node.setID(id);
		node.getConeBuilder().setRadius(radius).setHeight(height);
		protoNodeMap.put(id, node);

	}

	/**
	 * Create box
	 * @param id id
	 * @param x sizex
	 * @param y sizey
	 * @param z sizez
	 */
	private void createBox(String id, float x, float y, float z) {
		SceneGraphNode.Builder node = SceneGraphNode.newBuilder();
		node.setID(id);
		node.getBoxBuilder().setX(x).setY(y).setZ(z);
		protoNodeMap.put(id, node);
	}

	/**
	 * Create sphere
	 * 
	 * @param id id
	 * @param radius radius
	 */
	private void createSphere(String id, float radius) {
		SceneGraphNode.Builder node = SceneGraphNode.newBuilder();
		node.setID(id);
		node.getBallBuilder().setRadius(radius);
		protoNodeMap.put(id, node);
	}

	/**
	 * Create cylinder
	 * 
	 * @param id id
	 * @param radius radius
	 * @param height height
	 */
	private void createCylinder(String id, float radius, float height) {
		SceneGraphNode.Builder node = SceneGraphNode.newBuilder();
		node.setID(id);
		node.getCylinderBuilder().setRadius(radius).setHeight(height);
		protoNodeMap.put(id, node);
	}
	/**
	 * Create a none shape
	 * @param id id of shape
	 */
	private void createNone(String id) {
		SceneGraphNode.Builder node = SceneGraphNode.newBuilder();
		node.setID(id);
		node.getNoneBuilder();
		protoNodeMap.put(id, node);
	}

	/**
	 * Set a parent child relation between two nodes
	 * 
	 * @param idP ID Parent
	 * @param idC ID Child
	 */
	private void addChildNode(String idP, String idC) {
		SceneGraphNode.Builder parentBuild = getNode(idP);
		SceneGraphNode.Builder childBuild = getNode(idC);
		addChildNode(parentBuild, childBuild);
	}
	
	/**
	 * Set a parent child relation between two nodes
	 * @param parentBuild Parent Builder
	 * @param childBuild Child Builder
	 */
	private void addChildNode(SceneGraphNode.Builder parentBuild, SceneGraphNode.Builder childBuild) {
		parentNodes.put(childBuild.getID(), parentBuild);
		parentBuild.addChildren(childBuild);
		refreshMapFrom(parentBuild.getChildrenBuilder(parentBuild.getChildrenCount() - 1));
	}

	/**
	 * Refreshes the map of builders for the passed node and its subtree
	 * @param nodeBuilder 
	 */
	private void refreshMapFrom(Builder nodeBuilder) {
		protoNodeMap.put(nodeBuilder.getID(), nodeBuilder);
		hashNodeMap.put(nodeBuilder.getID(), convertProtobufNodeToShape(nodeBuilder).hashCode());
		for (Builder childBuilder : nodeBuilder.getChildrenBuilderList()) {
			refreshMapFrom(childBuilder);
		}
	}

	@Override
	public void setParent(String idChild, String idParent) {
		removeFromCurrentParent(idChild);
		addChildNode(idParent, idChild);
		if (sendingPossible()) {
			sceneGraphCommunicationHandler.sendSceneGraph(mSceneGraph.build());
		}
	}

	/**
	 * Removes node with the given id from its parent
	 * @param idChild 
	 */
	private void removeFromCurrentParent(String idChild) {
		Builder parent = parentNodes.get(idChild);
		parent.removeChildren(getChildIndex(parent, idChild));
		parentNodes.remove(idChild);
	}

	/**
	 * Gets the index of a child node with given id in parent. If it is not present, throws IllegalArgumentException
	 * @param parent 
	 * @param idChild 
	 * @return index of the child node
	 */
	private int getChildIndex(Builder parent, String idChild) {
		for (int i = 0; i < parent.getChildrenCount(); i++) {
			if (parent.getChildrenOrBuilder(i).getID().equals(idChild)) {
				return i;
			}
		}
		String errorMessage = "Child with id=" + idChild + " is not found in parent with id=" + parent.getID();
		activator.getLog().log(new Status(Status.ERROR, Activator.getPluginId(), errorMessage, null));
		throw new IllegalArgumentException(errorMessage);
	}

	/**
	 * Get the SceneGraph
	 * 
	 * @return SceneGraph
	 */
	public SceneGraph.Builder getSceneGraph() {
		return mSceneGraph;
	}

	@Override
	public void removeShape(String id) {
		removeFromCurrentParent(id);
		Builder removedNode = removeNodeFromMaps(id);
		for (Builder child : removedNode.getChildrenBuilderList()) {
			addChildNode(mSceneGraph.getNodeBuilder(), child);
		}
		
		if (sendingPossible()) {
			sceneGraphCommunicationHandler.sendSceneGraph(mSceneGraph.build());
		}
	}

	/**
	 * Removes the node from all maps
	 * @param id of the node to remove
	 * @return a builder for the removed node for possible operations on its children
	 */
	private Builder removeNodeFromMaps(String id) {
		Builder nodeToRemove = protoNodeMap.get(id);
		protoNodeMap.remove(id);
		hashNodeMap.remove(id);
		parentNodes.remove(id);
		if (protoGeometryFileMap.containsKey(id)) {
			protoGeometryFileMap.remove(id);
		}
		return nodeToRemove;
	}
	
	@Override
	public void removeShapeWithSubtree(String id) {
		removeFromCurrentParent(id);
		removeNodeRecursively(id);
		if (sendingPossible()) {
			sceneGraphCommunicationHandler.sendSceneGraph(mSceneGraph.build());
		}
	}

	/**
	 * Removes a given node with its subtree from maps
	 * @param id of the node to remove
	 */
	private void removeNodeRecursively(String id) {
		Builder removedNode = removeNodeFromMaps(id);
		for (Builder child : removedNode.getChildrenBuilderList()) {
			removeNodeRecursively(child.getID());
		}
	}

	/**
	 * Sets the SceneGraph Communication Handler
	 * 
	 * @param sceneGraphCommunicationHandler 	Handler for SceneGraph messaging.
	 */
	public void setSceneGraphCommunicationHandler(CommunicationHandler sceneGraphCommunicationHandler) {
		this.sceneGraphCommunicationHandler = sceneGraphCommunicationHandler;
	}

	/**
	 * Sets the Geometry Communication Handler
	 * 
	 * @param geometryFileCommunicationHandler	Handler for GeometryFile messaging.
	 */
	public void setGeometryFileCommunicationHandler(CommunicationHandler geometryFileCommunicationHandler) {
		this.geometryFileCommunicationHandler = geometryFileCommunicationHandler;
	}

	/**
	 * Checks if there is a registered node with the ide
	 * @param id Id of the node
	 * @return <b>true:</b> has node with id <b>false:</b> no node with id
	 */
	public boolean hasShape(String id) {
		return protoNodeMap.containsKey(id);
	}
	/**
	 * Checks if a node is a parent of another node
	 * @param idChild ID of the child
	 * @param idParent ID of the parent
	 * @return <b> true:</b> the node is a parent of the child
	 */
	public boolean isParent(String idChild, String idParent) {
		SceneGraphNode.Builder parent = getNode(idParent);
		for (int i = 0; i < parent.getChildrenCount(); ++i) {
			if (parent.getChildren(i).getID().equals(idChild)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Delete the whole SceneGraph content
	 */
	public void deleteWholeSceneGraph() {
		while (mSceneGraph.getNodeOrBuilder().getChildrenCount() > 0) {
			SceneGraphNodeOrBuilder rootElement = mSceneGraph.getNodeOrBuilder().getChildrenOrBuilder(0);
			removeShapeWithSubtree(rootElement.getID());
		}
	}

	@Override
	public void setShapeEditObserver(IShapeEditObserver observer) {
		this.observer = observer;
	}
	
	/**
	 * Updates the tree from the new message
	 * @param visualisationMessage only SceneGraph message and 
	 */
	public void update(VisualisationMessage visualisationMessage) {
		if (visualisationMessage.hasSceneGraph()) {
			SceneGraph receivedSceneGraph = visualisationMessage.getSceneGraph();
			
			if (this.isDifferentSceneGraph(receivedSceneGraph)) {
				SceneGraph.Builder receivedSceneGraphBuilder = receivedSceneGraph.toBuilder();
				mSceneGraph = receivedSceneGraphBuilder;
				if (observer != null) {
					this.notifyChanges(receivedSceneGraphBuilder.getNode());
				}
				protoNodeMap.clear();
				this.refreshMapFrom(mSceneGraph.getNodeBuilder());
				if (sceneGraphCommunicationHandler instanceof CommunicationServer) {
					sceneGraphCommunicationHandler.sendSceneGraph(mSceneGraph.build());
				}
			} else {
				activator.getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Received SceneGraph is equal to the current one", null));
			}	
		} else if (visualisationMessage.hasGeometryFile()) {
			GeometryFile receivedGeometryFile = visualisationMessage.getGeometryFile();
			protoGeometryFileMap.put(receivedGeometryFile.getNodeID(), receivedGeometryFile);
		}
	}
	
	/**
	 * Checks if the serialized Protobuf tree is different from the current one
	 * @param receivedSceneGraphBuilder received SceneGraph
	 * @return true if the given SceneGraph is different from the current one, false otherwise
	 */
	public synchronized boolean isDifferentSceneGraph(SceneGraph receivedSceneGraphBuilder) {
		return !receivedSceneGraphBuilder.equals(mSceneGraph.build());
	}
	
	/**
	 * Checks recursively which node changed. This is necessary to determine which structural element in DVLM model should be edited
	 * @param node Current node
	 */
	private void notifyChanges(SceneGraphNode node) {
		Shape shape = convertProtobufNodeToShape(node);
		if (hashNodeMap.containsKey(shape.id)) {
			if (shape.hashCode() != hashNodeMap.get(node.getID())) {
				observer.notifyShapeEdited(shape);
				activator.getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Edit " + shape.id, null));
			}
		}
		for (int i = 0; i < node.getChildrenCount(); ++i) {
			notifyChanges(node.getChildren(i));
		}
	}

	/**
	 * Creates a shape object from the parameters of a protobuf node
	 * @param node Protobuf node which holds the visualisation parameters
	 * @return Shape
	 */
	private Shape convertProtobufNodeToShape(SceneGraphNodeOrBuilder node) {
		Shape shape = new Shape();
		shape.id = node.getID();
		shape.color = node.getColor();
		shape.transparency = node.getOpacity();
		shape.positionX = node.getPosition().getX();
		shape.positionY = node.getPosition().getY();
		shape.positionZ = node.getPosition().getZ();
		shape.rotationX = node.getOrientation().getX();
		shape.rotationY = node.getOrientation().getY();
		shape.rotationZ = node.getOrientation().getZ();
		if (node.hasBall()) {
			shape.radius = node.getBall().getRadius();
			shape.shape = VisualisationShape.SPHERE;
		} else if (node.hasBox()) {
			shape.sizeX = node.getBox().getX();
			shape.sizeY = node.getBox().getY();
			shape.sizeZ = node.getBox().getZ();
			shape.shape = VisualisationShape.BOX;
		} else if (node.hasCone()) {
			shape.radius = node.getCone().getRadius();
			shape.sizeY = node.getCone().getHeight();
			shape.shape = VisualisationShape.CONE;
		} else if (node.hasCylinder()) {
			shape.radius = node.getCylinder().getRadius();
			shape.sizeY = node.getCylinder().getHeight();
			shape.shape = VisualisationShape.CYLINDER;
		} else if (node.hasGeometry()) {
			shape.geometryFile = URI.createURI(node.getGeometry().getOriginFilepath());
			shape.shape = VisualisationShape.GEOMETRY;
		} else if (node.hasNone()) {
			shape.shape = VisualisationShape.NONE;
		}
		return shape;
	}

	@Override
	public void pauseSending() {
		sendingPaused = true;
	}

	@Override
	public void resumeSending() {
		sendingPaused = false;
		if (sceneGraphCommunicationHandler != null) {
			sceneGraphCommunicationHandler.sendSceneGraph(mSceneGraph.build());
		}
	}
	
	/**
	 * Checks if communicationServer is set and sending is not paused
	 * @return true or false
	 */
	private boolean sendingPossible() {
		return (sceneGraphCommunicationHandler != null) && !sendingPaused;
	}

	@Override
	public void reloadGeometryFile(String shapeId) {
		createGeometryFileBuilder(shapeId);
		geometryFileCommunicationHandler.sendGeometryFile(protoGeometryFileMap.get(shapeId));
	}
}
