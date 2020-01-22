/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

/**
 * Dependency tree for objects.
 * @author muel_s8
 *
 * @param <T> the class of objects that will be store in the dependency tree.
 */

public class DependencyTree<T> {

	private Map<T, List<T>> children;
	private Map<T, List<T>> parents;
	private Map<T, Integer> heights;
	private Set<T> roots;
	
	/**
	 * Public constructor
	 */
	public DependencyTree() {
		children = new HashMap<>();
		parents = new HashMap<>();
		heights = new HashMap<>();
		roots = new HashSet<>();
	}
	
	/**
	 * Add a list of dependencies to an object
	 * @param object the object which has dependencies
	 * @param dependencies the dependencies for the object
	 */
	
	public void addDependencies(T object, List<T> dependencies) {
		// Add the object, if it is not yet in the tree, to the tree and then add
		// all its dependencies
		if (children.containsKey(object)) {
			List<T> existingDependencies = children.get(object);
			for (T dependency : dependencies) {
				if (!existingDependencies.contains(dependency)) {
					existingDependencies.add(dependency);
				}
			}
		} else {
			children.put(object, dependencies);
			parents.put(object, new ArrayList<>());
			heights.put(object, 0);
		}
		
		dependencies.forEach((dependency) -> {
			addDependencies(dependency, new ArrayList<>());
			parents.get(dependency).add(object);
		});
		
		// Update heights
		Queue<T> toProcess = new LinkedList<>();
		toProcess.add(object);
		updateHeights(toProcess);
		
		// Update the roots
		if (parents.get(object).size() == 0) {
			roots.add(object);
		}
		
		roots.removeAll(dependencies);
	}
	
	/**
	 * Internal update of node heights
	 * @param toProcess nodes who, together with their successors, will have their height information
	 * updated.
	 */
	private void updateHeights(Queue<T> toProcess) {
		Set<T> processed = new HashSet<T>();
		processed.addAll(toProcess);
		
		while (!toProcess.isEmpty()) {
			T node = toProcess.poll();
			List<T> tmpDependencies = children.get(node);
			
			int heightNode = heights.get(node);
			
			for (T dependency : tmpDependencies) {
				if (!processed.contains(dependency)) {
					toProcess.add(dependency);
					processed.add(dependency);
				}
				
				int height = heights.get(dependency);
				height = Math.max(heightNode + 1, height);
				heights.put(dependency, height);
			}
		}
	}
	
	/**
	 * Get the nodes in the tree
	 * @return a set containing all nodes in the tree
	 */
	public Set<T> getNodes() {
		return children.keySet();
	}
	
	/**
	 * Add a list of dependencies to an object
	 * @param object the object which has dependencies
	 * @param dependencies the dependencies for the object
	 */
	
	public void addDependencies(T object, T[] dependencies) {
		addDependencies(object, new LinkedList<T>(Arrays.asList(dependencies)));
	}
	
	/**
	 * Removes a node from the dependency tree. Also searches for new root nodes that might result
	 * from the deletion of the node
	 * @param node the node to be removed
	 */
	public void removeNode(T node) {
		children.remove(node);
		for (Entry<T, List<T>> childEntry : children.entrySet()) {
			T object = childEntry.getKey();
			List<T> dependencies = childEntry.getValue();
			dependencies.remove(node);

			List<T> succParents = parents.get(object);
			succParents.remove(node);
			
			if (succParents.size() == 0) {
				roots.add(object);
			}
		}
	}
	
	/**
	 * Trims the dependency tree such that it only contains nodes that have a root node from which
	 * one of the passed nodes can be reached.
	 * @param nodes the nodes which the root nodes of others nodes have to connect to or they will be removed.
	 * @return a set containing all nodes that have been removed
	 */
	public Set<T> trim(Collection<T> nodes) {
		// Nodes that will be removed in the trimming process
		Set<T> removeNodes = new HashSet<T>();
		
		// For each root node compute the reachable fragment
		for (T root : roots) {
			Set<T> reachable = new HashSet<T>();
			Queue<T> toProcess = new LinkedList<T>();
			
			toProcess.add(root);
			reachable.add(root);
			
			while (!toProcess.isEmpty()) {
				T node = toProcess.poll();
				
				
				List<T> dependencies = children.get(node);
				for (T dependency : dependencies) {
					if (!reachable.contains(dependency)) {
						toProcess.add(dependency);
						reachable.add(dependency);
					}
				}
				
				List<T> dependents = parents.get(node);
				for (T dependent : dependents) {
					if (!reachable.contains(dependent)) {
						toProcess.add(dependent);
						reachable.add(dependent);
					}
				}
			}
			
			// We can reach one of the key nodes
			if (Collections.disjoint(reachable, nodes)) {
				removeNodes.addAll(reachable);
			}
		}
		
		// Finally remove the nodes we dont want in the tree
		for (T node : removeNodes) {
			removeNode(node);
		}
		
		return removeNodes;
	}
	
	
	/**
	 * Checks if this is really a tree or if it has cycles
	 * @return null if cycle free, otherwise an object on a cycle
	 */
	
	public T hasCycle() {
		// Check for all nodes if there is an edge to a node of lower height
	
		for (Entry<T, List<T>> childEntry : children.entrySet()) {
			T object = childEntry.getKey();
			List<T> dependencies = childEntry.getValue();
			int height = heights.get(object);
			for (T dependency : dependencies) {
				int heightDependency = heights.get(dependency);
				if (heightDependency < height || dependency == object) {
					// We have an edge to a node with lower height, this means we have a cycle
					return object;
				}
			}
		}
		
		return null;
		
	}
	
	/**
	 * Calculates a linearization of the tree adhering to the condition that nodes at higher heights
	 * will always be prioritized, even if they have no dependencies.
	 * @return a linearizaiton of the partial order tree
	 */
	
	public List<T> getLinearOrder() {
		List<T> linear = new ArrayList<>();
		
		Queue<T> toProcess = new LinkedList<>();
		Set<T> processed = new HashSet<T>();
		toProcess.addAll(roots);
		
		while (!toProcess.isEmpty()) {
			T object = toProcess.poll();
			if (!processed.contains(object)) {
				linear.add(object);
				processed.add(object);
			}
			
			List<T> dependencies = children.get(object);
			if (dependencies != null) {
				for (T dependency : dependencies) {
					List<T> dependencyParents = parents.get(dependency);
					if (linear.containsAll(dependencyParents)) {
						toProcess.add(dependency);
					}
				}
				
			}
		}
		
		Collections.reverse(linear);
		
		return linear;
	}
	
	/**
	 * Cycle visitor
	 * @author muel_s8
	 *
	 * @param <T> object type, should be same as dependency tree
	 */
	public interface ICycleVisitor<T> {
		/**
		 * Called when handling a cycle
		 * @param node on a cycle
		 */
		void visit(T node);
	}
	
	/**
	 * Internal node height update using the linearized order
	 */
	private void updateHeights() {
		List<T> linearized = getLinearOrder();
		for (int i = 0; i < linearized.size(); ++i) {
			heights.put(linearized.get(i), linearized.size() - i);
		}
	}
	
	/**
	 * Removes all cycles in the tree
	 * @param visitor a visitor called everything we remove a cycle
	 */
	public void removeCycles(ICycleVisitor<T> visitor) {
		updateHeights();
		
		T cycleNode = hasCycle();		
		
		while (cycleNode != null) {
			visitor.visit(cycleNode);
			removeNode(cycleNode);
			cycleNode = hasCycle();
		} 
	}
	
	/**
	 * Creates string in the dot format of this dependency graph
	 * @param name of the graph
	 * @return a string in the dot format
	 */
	public String toDot(String name) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("digraph " + name + " {\n");
		
		List<T> linearized = getLinearOrder();
		
		for (int i = 0; i < linearized.size(); ++i) {
			T item = linearized.get(i);
			sb.append(i + " [label=\"" + item.toString() + "\"]\n");
		}
		
		for (int i = 0; i < linearized.size(); ++i) {
			T item = linearized.get(i);
			List<T> itemChildren = children.get(item);
			for (T child : itemChildren) {
				int indexChild = linearized.indexOf(child);
				sb.append(indexChild + "->" + i + "\n");
			}
		}
		
		sb.append("}");
		
		return sb.toString();
	}
}
