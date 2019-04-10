/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.vtkClient;

import java.awt.EventQueue;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.model.extension.visualisation.Activator;
import de.dlr.sc.virsat.model.extension.visualisation.ui.calculations.CalculateColor;
import de.dlr.sc.visproto.VisProto.SceneGraphNode;
import vtk.vtkActor;
import vtk.vtkAxesActor;
import vtk.vtkCamera;
import vtk.vtkConeSource;
import vtk.vtkCubeSource;
import vtk.vtkCylinderSource;
import vtk.vtkLinearTransform;
import vtk.vtkPanel;
import vtk.vtkPolyDataAlgorithm;
import vtk.vtkPolyDataMapper;
import vtk.vtkProp;
import vtk.vtkRenderer;
import vtk.vtkSTLReader;
import vtk.vtkSphereSource;
import vtk.vtkTransform;

/**
 * VtkManager is responsible for the visualization part
 */
public class VtkTreeManager extends vtkPanel {
	
	private Activator activator = new Activator();
	private Map<String, vtkPolyDataAlgorithm> sourceMap = new HashMap<String, vtkPolyDataAlgorithm>();
	private Map<String, vtkPolyDataMapper> mapperMap = new HashMap<String, vtkPolyDataMapper>();
	private Map<String, ActorVtk> actorMap = new HashMap<String, ActorVtk>();
	private Map<String, SceneGraphNode> protoNodeMap = new HashMap<String, SceneGraphNode>();
	private Vector<SceneGraphNode> vecUpdate = new Vector<SceneGraphNode>();
	private Vector<vtkProp> vecInvalidActors = new Vector<vtkProp>();
	private Vector<vtkProp> vecNewActors = new Vector<vtkProp>();
	private static VtkTreeManager instance = null;
	private vtkAxesActor localAxes = new vtkAxesActor();
	private vtkAxesActor globalAxes = new vtkAxesActor();
	private ActorVtk selectedActor = null;
	private vtkActor highlightActor = new vtkActor();
	private vtkCubeSource highlightSource = new vtkCubeSource();
	private vtkPolyDataMapper highlightMapper = new vtkPolyDataMapper();
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a vtk TreeMananger
	 * 
	 */
	private VtkTreeManager() {
		super();
		//initGui();
	}

	/**
	 * Initialize some basic rendering and visualization settings
	 * 
	 */
	public void initGui() {

		final double NEAR_PLANE_PRECISION = 0.0001;
		final double NEAR_PLANE_TOLERANCE = 0.00001;
		final double FAR_PLANE_DISTANCE = 30000.0;
		final int CAMERA_FAR_PLANE_DISTANCE = 30000;

		// Camera
		// As we have a 16-bit buffer, 30k each direction should suit
		this.GetRenderer().GetActiveCamera().SetClippingRange(NEAR_PLANE_PRECISION, FAR_PLANE_DISTANCE);
		this.GetRenderer().SetNearClippingPlaneTolerance(NEAR_PLANE_TOLERANCE);

		this.GetRenderer().ResetCameraClippingRange(-CAMERA_FAR_PLANE_DISTANCE, CAMERA_FAR_PLANE_DISTANCE,
				-CAMERA_FAR_PLANE_DISTANCE, CAMERA_FAR_PLANE_DISTANCE, -CAMERA_FAR_PLANE_DISTANCE,
				CAMERA_FAR_PLANE_DISTANCE);
		
		initAxes();
	}
	
	/**
	 * Initialize default local and global axes
	 */
	private void initAxes() {
		final double GLOBAL_AXIS_LENGTH = 4;
		final double LOCAL_AXIS_LENGTH = 10; 
		globalAxes.SetTotalLength(GLOBAL_AXIS_LENGTH, GLOBAL_AXIS_LENGTH, GLOBAL_AXIS_LENGTH);
		globalAxes.SetAxisLabels(1);
		localAxes.SetVisibility(1);
		localAxes.SetTotalLength(LOCAL_AXIS_LENGTH, LOCAL_AXIS_LENGTH, LOCAL_AXIS_LENGTH);
		localAxes.SetAxisLabels(0);
		
		vecNewActors.add(globalAxes);
		vecNewActors.add(localAxes);
		vecNewActors.add(highlightActor);
	}


	/**
	 * Create a new Node
	 * 
	 * @param node Protobuf node which should be visualised
	 * @param parent Parent vtk node
	 */
	private void create(SceneGraphNode node, ActorVtk parent) {
		activator.getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Creating " + node.getID(), null));
		ActorVtk newObject = new ActorVtk(node.getID());
		if (node.hasBox()) {
			newObject = createBox(node, parent);
		} else if (node.hasBall()) {
			newObject = createBall(node, parent);
		} else if (node.hasCone()) {
			newObject = createCone(node, parent);
		} else if (node.hasCylinder()) {
			newObject = createCylinder(node, parent);
		} else if (node.hasGeometry()) {
			newObject = createGeometry(node, parent);
		} else {
			ActorVtk root = new ActorVtk(node.getID());
			root.SetUserTransform(parent.GetUserTransform());
			actorMap.put(node.getID(), root);
		}
		
		for (int i = 0; i < node.getChildrenCount(); ++i) {
			create(node.getChildren(i), actorMap.get(newObject.getId()));
		}
	}

	
	/**
	 * Create VTK Geometry Actor
	 * 
	 * @param node 				Node in the SceneGraph containing the Geometry
	 * @param model 			the parent VTK actor 
	 * @return geometryActor 	the Geometry actor
	 */
	private ActorVtk createGeometry(SceneGraphNode node, ActorVtk model) {
		final double GEOMETRY_AMBIENT_REFLECTION = 0.2;
		final double GEOMETRY_DIFFUSE_REFLECTION = 1;
		
		vtkSTLReader importer = new vtkSTLReader();
		vtkPolyDataMapper geometryMapper = new vtkPolyDataMapper();
		ActorVtk geometryActor = model.newChild(node.getID());
		
		if (!node.getGeometry().getOriginFilepath().equals("")) {
			importer.SetFileName(getAbsoluteFilePath(node.getGeometry().getOriginFilepath()));
			geometryMapper.SetInputConnection(importer.GetOutputPort());
			geometryActor.SetMapper(geometryMapper);
		} 
		
		vtkLinearTransform vlt = model.GetUserTransform();
		geometryActor.SetUserTransform(calculateTransform(node, vlt));
		geometryActor.GetProperty().SetAmbient(GEOMETRY_AMBIENT_REFLECTION);
		geometryActor.GetProperty().SetDiffuse(GEOMETRY_DIFFUSE_REFLECTION);
		double[] color = CalculateColor.caluclateIntToRGBDouble(node.getColor());
		geometryActor.GetProperty().SetColor(color[0], color[1], color[2]);
		geometryActor.GetProperty().SetOpacity(1 - node.getOpacity());

		vecNewActors.add(geometryActor);
		sourceMap.put(node.getID(), importer);
		mapperMap.put(node.getID(), geometryMapper);
		actorMap.put(node.getID(), geometryActor);
		return geometryActor;
	}

	/**
	 * 
	 * @param node parallel protobuf node
	 * @param model vtkActor Parent
	 * @return ActorVtk
	 */
	private ActorVtk createBox(SceneGraphNode node, ActorVtk model) {
		final double BOX_AMBIENT_REFLECTION = 0.2;
		final double BOX_DIFFUSE_REFLECTION = 1;
		

		vtkCubeSource box = new vtkCubeSource();
		box.SetXLength(node.getBox().getX());
		box.SetYLength(node.getBox().getY());
		box.SetZLength(node.getBox().getZ());

		vtkPolyDataMapper boxMapper = new vtkPolyDataMapper();
		boxMapper.SetInputConnection(box.GetOutputPort());

		ActorVtk boxActor = model.newChild(node.getID());
		boxActor.SetMapper(boxMapper);

		vtkLinearTransform vlt = model.GetUserTransform();

		boxActor.SetUserTransform(calculateTransform(node, vlt));

		boxActor.GetProperty().SetAmbient(BOX_AMBIENT_REFLECTION);
		boxActor.GetProperty().SetDiffuse(BOX_DIFFUSE_REFLECTION);

		double[] color = CalculateColor.caluclateIntToRGBDouble(node.getColor());
		boxActor.GetProperty().SetColor(color[0], color[1], color[2]);
		boxActor.GetProperty().SetOpacity(1 - node.getOpacity());

		vecNewActors.add(boxActor);
		sourceMap.put(node.getID(), box);
		mapperMap.put(node.getID(), boxMapper);
		actorMap.put(node.getID(), boxActor);
		return boxActor;
	}

	/**
	 * 
	 * @param node parallel protobuf node
	 * @param model vtkActor Parent
	 * @return ActorVtk
	 */
	private ActorVtk createBall(SceneGraphNode node, ActorVtk model) {
		final double SPHERE_AMBIENT_REFLECTION = 0.2;
		final double SPHERE_DIFFUSE_REFLECTION = 1;
		final int SPHERE_PHI_RES = 100;
		final int SPHERE_THETA_RES = 100;

		vtkSphereSource ball = new vtkSphereSource();
		ball.SetRadius(node.getBall().getRadius());
		ball.SetPhiResolution(SPHERE_PHI_RES);
		ball.SetThetaResolution(SPHERE_THETA_RES);

		vtkPolyDataMapper ballMapper = new vtkPolyDataMapper();
		ballMapper.SetInputConnection(ball.GetOutputPort());

		ActorVtk ballActor = model.newChild(node.getID());
		ballActor.SetMapper(ballMapper);

		vtkLinearTransform vlt = model.GetUserTransform();
		ballActor.SetUserTransform(calculateTransform(node, vlt));

		ballActor.GetProperty().SetAmbient(SPHERE_AMBIENT_REFLECTION);
		ballActor.GetProperty().SetDiffuse(SPHERE_DIFFUSE_REFLECTION);

		double[] color = CalculateColor.caluclateIntToRGBDouble(node.getColor());
		ballActor.GetProperty().SetColor(color[0], color[1], color[2]);
		ballActor.GetProperty().SetOpacity(1 - node.getOpacity());

		vecNewActors.add(ballActor);
		sourceMap.put(node.getID(), ball);
		mapperMap.put(node.getID(), ballMapper);
		actorMap.put(node.getID(), ballActor);

		return ballActor;
	}

	/**
	 * 
	 * @param node parallel protobuf node
	 * @param model vtkActor Parent
	 * @return ActorVtk
	 */
	private ActorVtk createCone(SceneGraphNode node, ActorVtk model) {
		final double CONE_AMBIENT_REFLECTION = 0.2;
		final double CONE_DIFFUSE_REFLECTION = 1;
		final int RESOLUTION_MULTIPLIER = 30;
		//This constant is needed, because normally vtk builds the cone laying on the side (0°). Vista creates the cone upright
		final int ROTATION_OFFSET = 90;

		vtkConeSource cone = new vtkConeSource();
		cone.SetRadius(node.getCone().getRadius());
		cone.SetHeight(node.getCone().getHeight());
		cone.SetResolution((int) node.getCone().getRadius() * RESOLUTION_MULTIPLIER);

		vtkPolyDataMapper coneMapper = new vtkPolyDataMapper();
		coneMapper.SetInputConnection(cone.GetOutputPort());

		ActorVtk coneActor = model.newChild(node.getID());
		coneActor.SetMapper(coneMapper);

		vtkLinearTransform vlt = model.GetUserTransform();
		coneActor.SetUserTransform(calculateTransform(node, vlt));

		coneActor.GetProperty().SetAmbient(CONE_AMBIENT_REFLECTION);
		coneActor.GetProperty().SetDiffuse(CONE_DIFFUSE_REFLECTION);

		double[] color = CalculateColor.caluclateIntToRGBDouble(node.getColor());
		coneActor.GetProperty().SetColor(color[0], color[1], color[2]);
		coneActor.GetProperty().SetOpacity(1 - node.getOpacity());
		//Rotate the object so that the default orientation is the same like in vista
		coneActor.RotateZ(ROTATION_OFFSET);

		vecNewActors.add(coneActor);

		sourceMap.put(node.getID(), cone);
		mapperMap.put(node.getID(), coneMapper);
		actorMap.put(node.getID(), coneActor);

		return coneActor;

	}

	/**
	 * 
	 * @param node parallel protobuf node
	 * @param model vtkActor Parent
	 * @return ActorVtk
	 */
	private ActorVtk createCylinder(SceneGraphNode node, ActorVtk model) {
		final double CYLINDER_AMBIENT_REFLECTION = 0.2;
		final double CYLINDER_DIFFUSE_REFLECTION = 1;
		final int RESOLUTION_MULTIPLIER = 30;
		final int MIN_RESOLUTION = 30;

		vtkCylinderSource cylinder = new vtkCylinderSource();
		cylinder.SetRadius(node.getCylinder().getRadius());
		cylinder.SetHeight(node.getCylinder().getHeight());
		int resolution = (int) node.getCylinder().getRadius() * RESOLUTION_MULTIPLIER;
		if (resolution < MIN_RESOLUTION) {
			resolution = MIN_RESOLUTION;
		}
		cylinder.SetResolution(resolution);

		vtkPolyDataMapper cylinderMapper = new vtkPolyDataMapper();
		cylinderMapper.SetInputConnection(cylinder.GetOutputPort());

		ActorVtk cylinderActor = model.newChild(node.getID());
		cylinderActor.SetMapper(cylinderMapper);

		vtkLinearTransform vlt = model.GetUserTransform();

		cylinderActor.SetUserTransform(calculateTransform(node, vlt));
		cylinderActor.GetProperty().SetAmbient(CYLINDER_AMBIENT_REFLECTION);
		cylinderActor.GetProperty().SetDiffuse(CYLINDER_DIFFUSE_REFLECTION);
		double[] color = CalculateColor.caluclateIntToRGBDouble(node.getColor());
		cylinderActor.GetProperty().SetColor(color[0], color[1], color[2]);
		cylinderActor.GetProperty().SetOpacity(1 - node.getOpacity());

		vecNewActors.add(cylinderActor);
		sourceMap.put(node.getID(), cylinder);
		mapperMap.put(node.getID(), cylinderMapper);
		actorMap.put(node.getID(), cylinderActor);

		return cylinderActor;
	}
	/**
	 * Create invisible none shape
	 * @param node parallel protobuf node
	 * @param model vtkActor Parent
	 */
	private void createNone(SceneGraphNode node, ActorVtk model) {
		final double NONE_SIZE = 0.1;
		ActorVtk noneActor = createBox(node, model);
		vecNewActors.removeElement(noneActor);
		noneActor.GetProperty().SetOpacity(0);
		actorMap.put(node.getID(), noneActor);
		vecNewActors.addElement(noneActor);
		vtkCubeSource noneSource = (vtkCubeSource) sourceMap.get(node.getID());
		noneSource.SetXLength(NONE_SIZE);
		noneSource.SetYLength(NONE_SIZE);
		noneSource.SetZLength(NONE_SIZE);
		sourceMap.put(node.getID(), noneSource);
	}
	

	 /**
	 * Updates the (maybe) old parameters in the visualisation SceneGraph
	 * @param node current node
	 * @param parent vtk parent of the node
	 */
	public void updateAttributes(SceneGraphNode node, ActorVtk parent) {
		
		updateTransformAttributes(node, parent);
		if (node.hasBox()) {
			updateBoxAtrributes(node, parent);
		} else if (node.hasBall()) {
			updateSphereAttributes(node, parent);
		} else if (node.hasCone()) {
			updateConeAttributes(node, parent);
		} else if (node.hasCylinder()) {
			updateCylinderAttributes(node, parent);
		} else if (node.hasGeometry()) {
			updateGeometryAttributes(node, parent);
		} else if (node.hasNone()) {
			updateNoneAttribute(node, parent);
		}
	}
	/**
	 * Updates transformattributes (position, rotation) of a node.
	 * @param node parallel protobuf node
	 * @param parent parent vtk node
	 */
	private void updateTransformAttributes(SceneGraphNode node, ActorVtk parent) {
		ActorVtk actor = actorMap.get(node.getID());
		vtkLinearTransform vlt = parent.GetUserTransform();
		actor.SetUserTransform(calculateTransform(node, vlt));
		
		double[] color = CalculateColor.caluclateIntToRGBDouble(node.getColor());
		actor.GetProperty().SetColor(color[0], color[1], color[2]);
		
		actor.GetProperty().SetOpacity(1 - node.getOpacity());
		//If there is a selected object transform the local coordinate system and the bounding box (highlighted) according to the new parameters
		if (selectedActor != null) {
			if (selectedActor.getId().equals(actor.getId())) {
				localAxes.SetUserTransform(parent.GetUserTransform());
				highlightActor.SetUserTransform(actor.GetUserTransform());
			}
		}
		
		
		
	}
	 /**
	 * Create the transformation of a child node according to the parmamerts from protobuf node and parents transformation
	 * @param node current protobuf node
	 * @param linearTransform linear transformation of from parent
	 * @return calculated new transform of child
	 */
	private vtkTransform calculateTransform(SceneGraphNode node, vtkLinearTransform linearTransform) {
		vtkTransform transform = new vtkTransform();
		transform.SetInput(linearTransform);
		transform.Translate(node.getPosition().getX(), node.getPosition().getY(), node.getPosition().getZ());
		transform.RotateZ(node.getOrientation().getZ());
		transform.RotateY(node.getOrientation().getY());
		transform.RotateX(node.getOrientation().getX());
		return transform;
	}
	 /** Update box shape specific attributes
	 * @param node parallel protobuf node
	 * @param parent 
	 */
	private void updateBoxAtrributes(SceneGraphNode node, ActorVtk parent) {
		ActorVtk actor = actorMap.get(node.getID());
		//Checks if the shape of the object changed
		if (sourceMap.get(node.getID()) instanceof vtkCubeSource) {
			vtkCubeSource box = (vtkCubeSource) sourceMap.get(actor.getId());
			box.SetXLength(node.getBox().getX());
			box.SetYLength(node.getBox().getY());
			box.SetZLength(node.getBox().getZ());
	
		} else {
			deleteNodeFromSceneGraph(actor);
			createBox(node, parent);
		}
	}
	
	/**
	 * Update sphere shape specific attributes
	 * @param node parallel protobuf node
	 * @param parent 
	 */
	private void updateSphereAttributes(SceneGraphNode node, ActorVtk parent) {
		ActorVtk actor = actorMap.get(node.getID());
		//Checks if the shape of the object changed
		if (sourceMap.get(node.getID()) instanceof vtkSphereSource) {
			vtkSphereSource ball = (vtkSphereSource) sourceMap.get(node.getID());
			ball.SetRadius(node.getBall().getRadius());
		} else {
			deleteNodeFromSceneGraph(actor);
			createBall(node, parent);
		}
	}
	
	/**
	 * Update cone shape specific attributes
	 * @param node parallel protobuf node
	 * @param parent 
	 */
	private void updateConeAttributes(SceneGraphNode node, ActorVtk parent) {
		final int RESOLUTION_MULTIPLIER = 30;
		ActorVtk actor = actorMap.get(node.getID());
		//Checks if the shape of the object changed
		if (sourceMap.get(node.getID()) instanceof vtkConeSource) {
			vtkConeSource cone = (vtkConeSource) sourceMap.get(node.getID());
			cone.SetRadius(node.getCone().getRadius());
			cone.SetHeight(node.getCone().getHeight());
			cone.SetResolution((int) node.getCone().getRadius() * RESOLUTION_MULTIPLIER);
			
		} else {
			deleteNodeFromSceneGraph(actor);
			createCone(node, parent);
		}
	}
	
	/**
	 * Update cylinder shape specific attributes
	 * @param node parallel protobuf node
	 * @param parent 
	 */
	private void updateCylinderAttributes(SceneGraphNode node, ActorVtk parent) {
		final int RESOLUTION_MULTIPLIER = 30;
		final int MIN_RESOLUTION = 30;
		ActorVtk actor = actorMap.get(node.getID());
		//Checks if the shape of the object changed
		if (sourceMap.get(node.getID()) instanceof vtkCylinderSource) {
			vtkCylinderSource cylinder = (vtkCylinderSource) sourceMap.get(node.getID());
			cylinder.SetRadius(node.getCylinder().getRadius());
			cylinder.SetHeight(node.getCylinder().getHeight());
			int resolution = (int) node.getCylinder().getRadius() * RESOLUTION_MULTIPLIER;
			if (resolution < MIN_RESOLUTION) {
				resolution = MIN_RESOLUTION;
			}
			cylinder.SetResolution(resolution);
			
		} else {
			deleteNodeFromSceneGraph(actor);
			createCylinder(node, parent);
		}
	}
	
	/**
	 * Updates Geometry-Shape specific attributes
	 * @param node 		SceneGraphNode corresponding to this Geometry-Shape
	 * @param parent 	parent VTK actor
	 */
	private void updateGeometryAttributes(SceneGraphNode node, ActorVtk parent) {
		ActorVtk actor = actorMap.get(node.getID());
		
		deleteNodeFromSceneGraph(actor);
		createGeometry(node, parent);
	}
	
	/**
	 * Returns the absolute file path to a geometry file loaded into the study.
	 * @param geometryFilePath	relative file path to geometry file.
	 * @return File path as string
	 */
	private String getAbsoluteFilePath(String geometryFilePath) {
		String currentWorkspacePath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toOSString();
		return Paths.get(currentWorkspacePath, geometryFilePath).toString();
	}
	
	/**
	 * Update none shape specific attributes
	 * @param node parallel protobuf node
	 * @param parent 
	 */
	private void updateNoneAttribute(SceneGraphNode node, ActorVtk parent) {
		ActorVtk actor = actorMap.get(node.getID());
		final double NONE_SIZE = 0.1;
		//Checks if the shape of the object changed
		if (sourceMap.get(node.getID()) instanceof vtkCubeSource) {
			vtkCubeSource box = (vtkCubeSource) sourceMap.get(actor.getId());
			box.SetXLength(NONE_SIZE);
			box.SetYLength(NONE_SIZE);
			box.SetZLength(NONE_SIZE);
			actor.GetProperty().SetColor(0, 0, 0);
			actor.GetProperty().SetOpacity(0);
		} else {
			deleteNodeFromSceneGraph(actor);
			createNone(node, parent);
		}
	}
	

	/**
	 * Force the visualisation SceneGraph to update
	 * 
	 * @param node Rootnode
	 */
	public void forceSceneryUpdate(SceneGraphNode node) {
		vecUpdate.add(node);
		vtkTransform vt = new vtkTransform();
		ActorVtk rootActor = new ActorVtk(node.getID());
		rootActor.SetUserTransform(vt);
		parseFromSceneGraphNode(node, rootActor, null);
		protoNodeMap.clear();
		getProtoSceneGraphMap(node);
		deleteNode();
		vecUpdate.clear();
	}

	/**
	 * Checks if there are outdated nodes which have to be deleted
	 * 
	 */
	private void deleteNode() {
		for (Entry<String, ActorVtk> entry : actorMap.entrySet()) {
			// remove all outdates Nodes
			if (!protoNodeMap.containsKey(entry.getKey())) {
				String id = entry.getKey();
				activator.getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Unregister Node " + id, null));
				ActorVtk actor = entry.getValue();
				deleteNodeFromSceneGraph(actor);
				sourceMap.remove(id);
				mapperMap.remove(id);
			}
		}
	}

	/**
	 * Delete a node with its subtree from the visualisation SceneGraph
	 * 
	 * @param actor node which should be deleted
	 */
	private void deleteNodeFromSceneGraph(ActorVtk actor) {
		vecInvalidActors.add(actor);
	}

	/**
	 * Updates the Map where the Proto SceneGraph nodes are linked
	 * 
	 * @param node current node
	 */
	private void getProtoSceneGraphMap(SceneGraphNode node) {
		protoNodeMap.put(node.getID(), node);
		for (int i = 0; i < node.getChildrenCount(); ++i) {
			getProtoSceneGraphMap(node.getChildren(i));
		}
	}

	/**
	 * Iterates through the Proto SceneGraph and creates or updates the
	 * visualisation nodes
	 * 
	 * @param node current node
	 * @param parent vtk Parent
	 * @param sgParent Protobuf Parent
	 * 
	 */
	private void parseFromSceneGraphNode(SceneGraphNode node, ActorVtk parent, SceneGraphNode sgParent) {
		if (actorMap.containsKey(node.getID())) {
			SceneGraphNode mapNode = protoNodeMap.get(node.getID());
			// has to be improved node==mapNode?*/
			if (mapNode.getChildrenCount() <= node.getChildrenCount()) {
				updateAttributes(node, parent);
				for (int i = 0; i < node.getChildrenCount(); ++i) {
					parseFromSceneGraphNode(node.getChildren(i), actorMap.get(node.getID()), node);
				}
				/* Update param of existiong nodes */

			} else { /* Node was moved */
				protoNodeMap.clear();
				// mark all nodes as invalid
				deleteNode();
				sourceMap.clear();
				mapperMap.clear();
				actorMap.clear();
				SceneGraphNode rootnode = vecUpdate.elementAt(0);
				// build the whole scene once again
				forceSceneryUpdate(rootnode);
			}

		} else { /* Node was not found */
			activator.getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Node " + node.getID() + "not found in map. Creating...", null));
			create(node, parent);
		}
	}

	/**
	 * Get singleton instance of VtkTreeManager
	 * 
	 * @return instance of VtkTreeManager
	 */
	public static synchronized VtkTreeManager getInstance() {
		if (instance == null) {
			instance = new VtkTreeManager();
		}
		return instance;
	}

	/**
	 * Get the vector of invalid nodes (should be removed)
	 * 
	 * @return Vector of invalid nodes
	 */
	public  Vector<vtkProp> getInvalidActors() {
		return vecInvalidActors;
	}
	
	/**
	 * Get the vector of new nodes (hasve to be added)
	 * @return Vector of new nodes
	 */
	public Vector<vtkProp> getNewActors() {
		return vecNewActors;
	}

	/**
	 * Clear the invalids nodes vector
	 */
	public void clearInvalidActors() {
		vecInvalidActors.clear();
	}
	/**
	 * Clear the new nodes vector
	 */
	public void clearNewActors() {
		vecNewActors.clear();
	}
	/**
	 * Clear the vtk manager
	 */
	public void clearVtkTreeManager() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				VtkTreeManager.getInstance().GetRenderer().RemoveAllViewProps();
				VtkTreeManager.getInstance().GetRenderer().Delete();
				instance = null;
			}
			
		});
		
	}
	/**
	 * Checks if the given id belongs to a visualized object and create local xyz axis with the ojects position and rotation.
	 * @param uuid ID which might belong to a visualized object
	 */
	public void checkIdAndCreateAxes(String uuid) {
		
		if (uuid != null) {
			if (actorMap.containsKey(uuid)) {
				selectedActor = actorMap.get(uuid);
				vtkTransform vt = getParentWorldTransform(selectedActor);
				localAxes.SetUserTransform(vt);
				vecNewActors.clear();
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						VtkClientVisUpdateHandler.getInstance().updateVisualisationScene();
					}
					
				});
				
			}
		}
		
		
	}

	/**
	 * Calculates the transformation of vtkActor's parent
	 * @param child 
	 * @return transformation of vtkActor's parent
	 */
	private vtkTransform getParentWorldTransform(ActorVtk child) {
		String id = child.getId();
		vtkLinearTransform ltChildGlobal = child.GetUserTransform();
		vtkTransform transformChildGlobal = new vtkTransform();
		vtkTransform transformChildRelative = new vtkTransform();
		transformChildGlobal.SetInput(ltChildGlobal);
		vtkTransform transformParentGlobal = transformChildGlobal;
		if (protoNodeMap.containsKey(id)) {
			SceneGraphNode node = protoNodeMap.get(id);
			transformChildRelative.RotateX(-node.getOrientation().getX());
			transformChildRelative.RotateY(-node.getOrientation().getY());
			transformChildRelative.RotateZ(-node.getOrientation().getZ());
			transformChildRelative.Translate(-node.getPosition().getX(), -node.getPosition().getY(), -node.getPosition().getZ());
		}
		transformParentGlobal.Concatenate(transformChildRelative);
		return transformParentGlobal;
	}
	/**
	 * Moves the camera toa certain position with focussing the global origin. The viewUp vector is necessary, because we have to set the ViewUp vector for the camera which differs if you select the top-down view
	 * @param position 
	 * @param viewUpVector  
	 */
	public void setCameraPosition(double[] position, double[] viewUpVector) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				vtkRenderer renderer = VtkTreeManager.getInstance().GetRenderer();
				vtkCamera camera = renderer.GetActiveCamera();
				
				camera.SetPosition(position);
				camera.SetFocalPoint(0, 0, 0);
				camera.SetViewUp(viewUpVector);

				renderer.ResetCameraClippingRange();
				VtkClientVisUpdateHandler.getInstance().updateVisualisationScene();
			}
		});
	}
	
	/**
	 * This function highlights a visualized objects if the editor of this object (SEI or CA) is activated.
	 * The highlighting is done with the help of a visible bounding box
	 * @param id ID of highlighted object
	 */
	public void highlightObject(String id) {
		if (id != null) {
			if (actorMap.containsKey(id)) {
				if (selectedActor != null) {
					if (selectedActor.GetMapper() != null) {
						double[] bounds = selectedActor.GetMapper().GetBounds();
						highlightSource.SetBounds(bounds);
						highlightMapper.SetInputConnection(highlightSource.GetOutputPort());
						highlightActor.SetMapper(highlightMapper);
						highlightActor.GetProperty().SetColor(1, 1, 1);
						highlightActor.SetUserTransform(selectedActor.GetUserTransform());
						highlightActor.GetProperty().SetRepresentationToWireframe();
						highlightActor.GetProperty().BackfaceCullingOff();
						highlightActor.GetProperty().SetAmbient(1.0);
						highlightActor.GetProperty().SetDiffuse(0.0);
						highlightActor.GetProperty().SetSpecular(0.0);
						highlightActor.SetVisibility(1);
					}
				}
			} else {
				highlightActor.SetVisibility(0);
			}
		} else {
			highlightActor.SetVisibility(0);
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				VtkClientVisUpdateHandler.getInstance().updateVisualisationScene();
				
			}
		});
		
	}
	/**
	 * Sets the visibility of the local axes
	 * @param isVisible 
	 */
	public void setLocalAxesVisible(boolean isVisible) {
		int visible = (isVisible) ? 1 : 0;
		localAxes.SetVisibility(visible);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				VtkClientVisUpdateHandler.getInstance().updateVisualisationScene();
			}
			
		});
	}
	/**
	 * Sets the visibility of the global axes
	 * @param isVisible 
	 */
	public void setGlobalAxesVisible(boolean isVisible) {
		int visible = (isVisible) ? 1 : 0;
		globalAxes.SetVisibility(visible);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				VtkClientVisUpdateHandler.getInstance().updateVisualisationScene();
			}
			
		});
	}
}
