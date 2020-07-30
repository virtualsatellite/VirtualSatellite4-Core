/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.inheritance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper;
import org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.util.DVLMApplicableForCheck;

/**
 * This class has all capabilities to update the inherited instances of other Structural Element Instances
 * @author fisc_ph
 *
 */
public class InheritanceCopier implements IInheritanceCopier {

	/**
	 * Standard Constructor of the Inheritance Copier that initializes
	 * the underlying EcoreCopier and the standard VirSat Access Rights behavior
	 */
	public InheritanceCopier() {
		inheritanceCopier = new EcoreInheritanceCopier();
		userContext = UserRegistry.getInstance();
	}
	
	/**
	 * Constructor for the Inheritance Copier that allows to override
	 * the standard VirSat AccessRights behavior. This method is primarily used for
	 * the ModelAPI to allow it to write to all SEIs when performing an update on
	 * the inheritance.
	 * @param userContext A customized UserContext to verify write Permissions
	 */
	public InheritanceCopier(IUserContext userContext) {
		inheritanceCopier = new EcoreInheritanceCopier();
		this.userContext = userContext;
	}
	
	private IUserContext userContext;
	

	/**
	 * Use this method to decide if a the SubSEI needs to be changed because of changes of the superSEI it inherits from
	 * over one level
	 * Cares about Override Flags and Inheritance Links  
	 * @param subSei SEI which inherits 
	 * @return boolean value telling if Update is needed
	 */
	protected boolean needsUpdateStep(StructuralElementInstance subSei) {

		DVLMApplicableForCheck applicableForCheck = new DVLMApplicableForCheck(subSei, true);
		
		// Features and references will be compared based on an adapted EcoreUtil.EqualityHelper
		EqualityHelper equalityHelper = new EcoreUtil.EqualityHelper() {
			private static final long serialVersionUID = -7815822744942349290L;
			
			@Override
			protected boolean haveEqualReference(EObject eObject1, EObject eObject2, EReference reference) {
				// Containments are also considered to be a type of reference. But for example when we are checking if
				// a TI which is actually a CA is equal, this method would call the nested equal checks on the contained 
				// property instances. But they are actually checked individually by this implemented algorithm.
				// Therefore this code does not need to follow that nesting and in fact should only check for
				// references of RPIs
				if (PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE == reference) {
					return super.haveEqualReference(eObject1, eObject2, reference);
				}
				return true;
			}
			
			@Override
			protected boolean haveEqualFeature(EObject subEObject, EObject superEObject, EStructuralFeature feature) {
				// First of all find out if the object that is compared to its source is set
				// into an override state. In such a case, we don't actually compare
				// because we expect differences. Therefore all sub Objects which have the
				// override state are considered equal to their super object.
				if (subEObject instanceof IOverridableInheritanceLink) {
					IOverridableInheritanceLink overidableInheritanceLinkSubObject = (IOverridableInheritanceLink) subEObject;
					
					// In case the object is set to override, it is assumed to be equal in all respects including name, comment, etc.
					if (overidableInheritanceLinkSubObject.isOverride()) {
						return true;
					}
				}
			
				// Now check for special features which have a different meaning, such as the UUID
				// in terms of inheritance objects can still be the same even with an different UUID
				if (feature == InheritancePackage.Literals.IINHERITANCE_LINK__SUPER_TIS) {
					return true;
				} else  if (feature == GeneralPackage.Literals.IUUID__UUID) {
					return true;
				} else if (feature == InheritancePackage.Literals.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE) {
					// the override flag itself does not have impact on inheritance equality !!!
					return true;
				} else if (feature == InheritancePackage.Literals.IINHERITANCE_LINK__IS_INHERITED) {
					return true;
				}

				// All other features have to be compared
				return super.haveEqualFeature(subEObject, superEObject, feature);
			}
		};
		
		Set<IInheritanceLink> subTis = InheritanceCopier.getAllTypeInstances(subSei);
		Set<IInheritanceLink> allInheritedSuperTis = new HashSet<>();
		
		// First we start looping over all subTIs. here we compare each individual TI including
		// CAs and PIs with the TI it has the latest update from. Latest update means the TI it
		// finally inherited from in case of multi-inheritance. Only the last values which have
		// been written are the accepted ones. E.G. A Value 5 from an EC might be overwritten by
		// a Value 10 from an ER on Assembly level. Therefore the EO should contain 10 not 5.
		for (IInheritanceLink subTi : subTis) {
			// in this list, we remember all TIs from where we inherited. This list is needed
			// to identify a TI from one of the superSEIs which has not yet been inherited.
			List<IInheritanceLink> superTisOfSubTi = subTi.getSuperTis();
			if (!superTisOfSubTi.isEmpty()) {
				allInheritedSuperTis.addAll(superTisOfSubTi);
				IInheritanceLink lastSuperTi = subTi.getSuperTis().get(superTisOfSubTi.size() - 1);
				// If one of the TIs is not equal we can stop and ask for an update
				boolean tisAreEqual = equalityHelper.equals(subTi, lastSuperTi);
				if (!tisAreEqual) {
					return true;
				}
			}
		}

		// After all existing TIs are compared, we now check if one of the SuperSEIs contains a TI
		// that is not yet inherited. In such a case we will ask for an update
		for (StructuralElementInstance superSei : subSei.getSuperSeis()) {
			Set<IInheritanceLink> superTis = InheritanceCopier.getAllTypeInstances(superSei);
			superTis.removeAll(allInheritedSuperTis);
			
			for (IInheritanceLink superTi : superTis) {
				if (superTi instanceof CategoryAssignment) {
					if (applicableForCheck.isValidObject(superTi)) {
						return true;
					}
				}
			}
		}
		
		// At this point we know everything is equal
		return false;
	}
	
	/**
	 * This method is used to update a given SEI. This method will crawl over all
	 * SuperSEIs one level before and start copying the needed CategoryAssignments. All copied CAs
	 * will be accumulated and handed back. The method can pre-load references to other
	 * SEIs that might be needed to setup copied Interfaces for example referencing IFEs
	 * @param subSei The SEI that should be Updated with its SuperSEIs
	 * @return a HashSet containing all copied CAs with updated references into the current tree.
	 */
	@SuppressWarnings("unchecked")
	public Set<CategoryAssignment> updateStep(StructuralElementInstance subSei) {
		boolean hasWritePermission = RightsHelper.hasWritePermission(subSei, userContext);
	
		if (hasWritePermission) {
			// Clean old super Tis that may no longer have valid links
			cleanSuperTis(subSei);
			cleanCas(subSei);
			
			// Do the Pre-loading here. Pre-loading means that we have to load all necessary
			// copied objects from the current tree, that might be referenced.
			// For example it could happen, that the update creates a new copy of an
			// interface which is referencing into another SEI, this reference has to be
			// copied into this tree as well, but the copied does not yet know the target
			// the core-copier is identifying this target by checking where the original
			// reference was copied to, and then references this copied target. But we have
			// not actually copied this object. Therefore we have to lift all potential
			// objects into this cache.
			// This also means, if a new superSei is set to a sei, there are no subTis yet, that
			// have a link to a superTi, therefore there is nothing to be loaded to the cache.
			// These objects have to placed into the cache once they are created. This is a an internal
			// functionality of the ecore copier.
			Set<StructuralElementInstance> potentiallyReferencedTreeSeis = getReferencesInTree(subSei);
			potentiallyReferencedTreeSeis.add(subSei);
			
			EcoreUtil.getAllContents(potentiallyReferencedTreeSeis, true).forEachRemaining((subTreeObj) -> {
				if (subTreeObj instanceof IInheritanceLink) {
					IInheritanceLink subTi = (IInheritanceLink) subTreeObj;
					for (IInheritanceLink superTi : subTi.getSuperTis()) {
						inheritanceCopier.put(superTi, subTi);
					}
				}
			});
	
			// Now once all relevant objects for linking are cached start looping
			// over all SuperSEIs and start copying the CAs one after the other
			Set<CategoryAssignment> allCopiedCas = new HashSet<>();
			for (StructuralElementInstance superSei : subSei.getSuperSeis()) {
				List<CategoryAssignment> copiedCas = getSubCategoryAssignments(subSei, superSei);
				for (CategoryAssignment copiedCa : copiedCas) {
					subSei.getCategoryAssignments().add(copiedCa);
				}
				allCopiedCas.addAll(copiedCas);
			}
			return allCopiedCas;
		}
		return Collections.EMPTY_SET;
	}
	
	private EcoreInheritanceCopier inheritanceCopier;

	/**
	 * The actual class that does the copying of CategroyAssignments and ProeprtyInstances
	 * from a Super SEI to a Sub SEI. It is based on the Ecore Copier, but adapted to realize
	 * the links between objects which reflect inheritance relations. They will be analyzed to decide,
	 * if an object will be copied, or just updated, or if it is in an override state, meaning nothing
	 * should be performed with that object. 
	 * @author fisc_ph
	 *
	 */
	private class EcoreInheritanceCopier extends EcoreUtil.Copier {
		private static final long serialVersionUID = -4913007196183404695L;
		
		@Override
		protected void copyReference(EReference eReference, EObject superEobject, EObject subEObject) {
			
			// No reliinking of the inheritance link should happen with the call to the cross referencer
			if (InheritancePackage.Literals.IINHERITANCE_LINK__SUPER_TIS == eReference) {
				return;
			}
			
			// Do not copy or update the reference if the override flag is set
			if (subEObject instanceof IOverridableInheritanceLink) {
				IOverridableInheritanceLink overidableInheritanceLinkSubObject = (IOverridableInheritanceLink) subEObject;
				if (overidableInheritanceLinkSubObject.isOverride()) {
					return;
				}
			}
			
			super.copyReference(eReference, superEobject, subEObject);
		}
		
		@Override
		protected void copyAttribute(EAttribute eAttribute, EObject superEobject, EObject subEObject) {
			// Do not copy the override flag :-D aaaaaaaahr
			if (InheritancePackage.Literals.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE == eAttribute) {
				return;
			}
			
			// Do not copy the attribute at all if the override flag is set for the target object
			if (subEObject instanceof IOverridableInheritanceLink) {
				IOverridableInheritanceLink overidableInheritanceLinkSubObject = (IOverridableInheritanceLink) subEObject;
				if (overidableInheritanceLinkSubObject.isOverride()) {
					return;
				}
			}

			// Do not copy a uuid the sub element has to have its own unique id. because it is unique ;-) understood :-D
			if (eAttribute != GeneralPackage.Literals.IUUID__UUID) {
				super.copyAttribute(eAttribute, superEobject, subEObject);
			}
		}			
		
		@Override
		protected EObject createCopy(EObject superEObject) {
			EObject subEObject = null;
			
			// In case that we want to create a copy of a CategroyAssignment or one of the PropertyInstances from
			// the SuperSei we should carefully check if the derived CAs or PIs already exist.
			if (superEObject instanceof IInheritanceLink) {
				IInheritanceLink superIInheritanceLink = (IInheritanceLink) superEObject;

				// Here we will ask, if the subSei has already an IInheritanceLink that is linked, therefore derived from the one we
				// just want to copy. In case an object with the link already exists, we don't need to copy it, otherwise we will create
				// a new eObject of that type and set the link accordingly. In Diamond inheritance cases, it can also happen that the 
				// CA or PI in the SubSei si already inherited but maybe by a different path to the common parent.
				IInheritanceLink subIInheritanceLink = getInheritedIInheritanceLinkFor(superIInheritanceLink, subSei);
				if (subIInheritanceLink == null) {
					// In case we try to copy/inherit a Category Assignment we have to first check,
					// if the Category is applicable for the target SEI type. If not we do not create
					// a copy at all. The data model would prevent such a CA to be attached as well,
					// but all the copying logic is not needed here and might create dangling references
					// since such an object would be cached in the copier.
					if (superIInheritanceLink instanceof CategoryAssignment && superIInheritanceLink.eContainer() instanceof StructuralElementInstance) {
						if (!applicableForCheck.isValidObject(superIInheritanceLink)) {
							return null;
						}
					}
					
					// All other objects are copied and handled as usual. PIs of a non-applicable CA will
					// not get copied, since they are nested to the CA, and the copying of the features will
					// not get called.
					subIInheritanceLink = (IInheritanceLink) super.createCopy(superEObject);
				} 

				// Update the type link. this is in preparation to the MultiInheritance problem.
				// remove it and add it to maintain the correct order of inheritance links
				subIInheritanceLink.getSuperTis().remove(superIInheritanceLink);
				subIInheritanceLink.getSuperTis().add(superIInheritanceLink);

				// Mark the type instance as inherited
				subIInheritanceLink.setIsInherited(true);
				
				// And finally hand back the TypeInstance Object as our EObject we need to continue processing
				subEObject = subIInheritanceLink;
			}
		
			return subEObject;
		}
		
		private StructuralElementInstance subSei;
		private DVLMApplicableForCheck applicableForCheck;
		
		/**
		 * Call this method to copy all EObjects to an subSei. The new CategoryAssignments are not attached to the subSei but returned.
		 * @param <T> generic Type which has to subclass CategoryAssignment 
		 * @param subSei the SubSei which will be used to compare if the copied objects are already part of it meaning inherited
		 * @param caS the CategroyAssignments to be copied/inherited
		 * @return A List of new inherited CategoryAssignments that can be attached to the subSei
		 */
		public <T extends CategoryAssignment> Collection<T> copyAll(StructuralElementInstance subSei, Collection<? extends T> caS) {
			this.subSei = subSei;
			this.applicableForCheck = new DVLMApplicableForCheck(subSei, true);
			return super.copyAll(caS);
		}
		
		@Override
		public <T> Collection<T> copyAll(Collection<? extends T> eObjects) {
			if (subSei == null) {
				throw new RuntimeException("Method should be called via copyAll(StructuralElementInstance subSei, Collection<? extends T> eObjects) !!!");
			}
			return super.copyAll(eObjects);
		}
	};
	
	/**
	 * Use this method to receive the CAs the subSei should update from the SuperSei.
	 * @param subSei Element which we want to update later on
	 * @param superSei One of the Element it inherits from
	 * @return List of the copied CAs from the superSei 
	 */
	protected List<CategoryAssignment> getSubCategoryAssignments(StructuralElementInstance subSei, StructuralElementInstance superSei) {

		// Now do the actual copy of the CategoryAssignments from the SuperStructuralElementInstance
		List<CategoryAssignment> superCas = superSei.getCategoryAssignments();
		Collection<CategoryAssignment> copiedCas = inheritanceCopier.copyAll(subSei, superCas);
		inheritanceCopier.copyReferences();
		
		return new LinkedList<CategoryAssignment>(copiedCas);
	}
	
	/**
	 *
	 * This method is used by creating copies of objects. The Idea is to ask the Sub SEI if it already
	 * contains CAs which are derived from the Super SEI. The copy method wants to create a copy of a CA
	 * of the Super SEI which has to be created on the Sub SEI in case it does not yet exist, but it has to
	 * be reused for an update in case it does already exist. In case that this method gets called from
	 * the canUpdate method the behavior needs to be slightly different. If we hand back an already copied
	 * CA from the SubSEI the update method would copy the features into it. For this case we expect some new 
	 * CAs, hence this method should return null, triggering the copy method to create some new objects.
	 * <br>
	 * <b>Long Story Short</b>: This method checks if a Ti of the sub SEI and the super SEI have a common ancestor. If
	 * yes, the one in the subSei is regarded as a copy of the TI in sueprSei. This can happen in case of multi-
	 * inheritance with diamond inheritance. In this case, only the superTi of the Ti in the subSei needs to be
	 * updated.
	 * 
	 * @param superIInheritanceLink The type instance for which a new copy or an existing one in the SubSEI should be created/updated
	 * @param subSei The sub SEI where the inheritance should copy to
	 * @return an existing type instance in the SubSEI which has a link to the given TypeInstance or null in case it does not yet exist
	 */
	protected IInheritanceLink getInheritedIInheritanceLinkFor(IInheritanceLink superIInheritanceLink, StructuralElementInstance subSei) {
		// If not try to find the copy by a common ancestor
		Set<IInheritanceLink> rootTisSuper = getRootSuperTypeInstance(superIInheritanceLink);
		
		// Loop over all objects and see if the current sub SEI contains a TypeInstance which has a link to the super SEI's type instance
		TreeIterator<Object> treeIter = EcoreUtil.getAllContents(subSei.getCategoryAssignments(), true);
		
		while (treeIter.hasNext()) {
			Object potentialIInheritanceLink = treeIter.next();
			if (potentialIInheritanceLink instanceof IInheritanceLink) {
				IInheritanceLink subIInheritanceLink = (IInheritanceLink) potentialIInheritanceLink;
				
				Set<IInheritanceLink> rootTisSub = getRootSuperTypeInstance(subIInheritanceLink);
				if (!Collections.disjoint(rootTisSuper, rootTisSub)) {
					return subIInheritanceLink;
				}
				
			}
		}
		
		return null;
	}
	
	/**
	 * Use this method to get an unordered list of all the SEIs the given one depends on
	 * including itself
	 * @param updateSei The SEI of which to get all the SEIs it depends on
	 * @return List of SEIs that are super SEIs to the given one
	 */
	protected Set<StructuralElementInstance> getSuperSeisInheritanceUnordered(StructuralElementInstance updateSei) {
		Set<StructuralElementInstance> inheritanceUnordered = new HashSet<>();
		LinkedList<StructuralElementInstance> processingList = new LinkedList<>();

		// Put first element to processing list
		processingList.add(updateSei);
	
		// Process the current list of nodes until it is empty
		while (!processingList.isEmpty()) {
			// get the first element from the processing list and remove it
			// put it in the unordered list
			StructuralElementInstance processingSei = processingList.removeFirst();
			if (!inheritanceUnordered.contains(processingSei)) {
				inheritanceUnordered.add(processingSei);
			}
			// now loop over all elements that the current node inherits from
			// add them as next nodes to the processing list, but don't add them
			// if they have already been processed, which means that they are already 
			// in the list of unordered inheritance nodes
			processingSei.getSuperSeis().forEach((superSei) -> {
				if (!inheritanceUnordered.contains(superSei)) {
					if (!processingList.contains(superSei)) {
						processingList.add(superSei);
					}
				}
			}); 
		}
		
		return inheritanceUnordered;
	}
	
	/**
	 * Call this method to order all SEIs which the updateSei inherits from. ordering means bring them in order in terms of inheritance.
	 * @param updateSei the SEI which wants to be updated 
	 * @return the List of SEIs which have to be updated first
	 */
	public List<StructuralElementInstance> getSuperSeisInheritanceOrder(StructuralElementInstance updateSei) {
		Set<StructuralElementInstance> unorderedSuperSeis = getSuperSeisInheritanceUnordered(updateSei);
		List<StructuralElementInstance> orderedSuperSeis = orderByInheritance(unorderedSuperSeis);
		return orderedSuperSeis;
	}
	
	/**
	 * Call this method to order all SEIs. ordering means bring them in order in terms of inheritance.
	 * @param updateSei the SEI which wants to be updated 
 	 * @param repo The Repository in which all SEIs reside
	 * @return the List of SEIs which have to be updated first
	 */
	protected List<StructuralElementInstance> getInheritanceOrder(StructuralElementInstance updateSei, Repository repo) {
		List<StructuralElementInstance> orderedSuperSeis = getSuperSeisInheritanceOrder(updateSei);
		List<StructuralElementInstance> orderedSubSeis = getSubSeisInheritanceOrder(updateSei, repo);
		List<StructuralElementInstance> orderedSeis = orderedSuperSeis;
		orderedSeis.addAll(orderedSubSeis);
		
		return orderedSeis;
	}
	
	/**
	 * Call this method to order all SEIs. ordering means bring them in order in terms of inheritance.
	 * @param unorderedSeis List of unordered Seis 
	 * @return the List of Seis which have to be updated first
	 */
	protected List<StructuralElementInstance> orderByInheritance(Set<StructuralElementInstance> unorderedSeis) {
		
		List<StructuralElementInstance> orderedList = new ArrayList<>(unorderedSeis.size());
		LinkedList<StructuralElementInstance> processingList = new LinkedList<>(unorderedSeis);
		LinkedList<StructuralElementInstance> notUpdatableList = new LinkedList<>();
		
		while (!processingList.isEmpty()) {
			StructuralElementInstance processingSei = processingList.removeFirst();
			
			// a sei can be updated once all super seis that are also in unorderedSeis have been updated
			// i.e. are in orderedList
			Set<StructuralElementInstance> superSeisOfProcessingSeiInGivenSet = new HashSet<>(processingSei.getSuperSeis());
			superSeisOfProcessingSeiInGivenSet.retainAll(unorderedSeis);
			boolean seiCanUpdate = orderedList.containsAll(superSeisOfProcessingSeiInGivenSet);
			
			// if it can be updated, the processing SEI is removed from the processing List 
			// and is added to the end of the orderedList
			// the List of until now not updatable elements can now be checked again
			if (seiCanUpdate) {
				orderedList.add(processingSei);
				processingList.addAll(notUpdatableList);
				notUpdatableList.clear();
			} else {
				notUpdatableList.add(processingSei);
			}
		}
		
		// check if the notUpdatableList has Elements which could not be updated at all, 
		// therefore they are involved in cyclic dependencies or inherit from such elements
		if (!notUpdatableList.isEmpty()) {
			throw new RuntimeException("Cyclic Dependencies in Inheritance");
		}
		
		return orderedList;
	}
	
	/**
	 * Use this method to find the SEIs which are the sub SEIs of the given one. This method will be needed
	 * to detect by the Eclipse Builder which SEI needs to be updated depending on which one was currently altered
	 * and saved by the user.
	 * @param superSei The Super SEI for which to find the SubSeis
	 * @param repo The Study repository which will be used to look for containments. This parameter may be null.
	 * @return A List of SEIs representing the Subs
	 */
	protected List<StructuralElementInstance> getSubSeisInheritanceOrder(StructuralElementInstance superSei, Repository repo) {
		List<StructuralElementInstance> subSeis = new LinkedList<>();

		// Put the first SEI to the processing list.
		LinkedList<StructuralElementInstance> processingList = new LinkedList<>();
		processingList.add(superSei);
		
		Set<StructuralElementInstance> allRepoSeis = getAllRepoSeis(repo);
		
		// An empty processing list tells us we ware finished with searching
		while (!processingList.isEmpty()) {
			
			// Now pop the SEI from the processing list and add it to the ones we have found
			StructuralElementInstance processingSei = processingList.removeFirst();
			subSeis.add(processingSei);
			
			// Now check by which one the current SEI is directly referenced. And add these ones
			// to the processing list. Thus they will be checked for being a super SEI for someone 
			// else in the next iteration
			Collection<EStructuralFeature.Setting> usages;
			// In case we know the Repo search in all SEI containment trees
			usages = UsageCrossReferencer.find(processingSei, allRepoSeis);
			
			// Now find the structural Feature which indicates the InheritsFrom list
			for (EStructuralFeature.Setting setting : usages) {
				EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
				if (eStructuralFeature == InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS) {
					// Pick the object from where the inheritance comes from
					EObject eObject = setting.getEObject();
					// and place it into the processing list, but only if it is not yet in our list of
					// returned elements. because this will indicate, that we have analyzed it already
					// additionally it should not be in processing list more than once
					if (!subSeis.contains(eObject)) {
						if (!processingList.contains((StructuralElementInstance) eObject)) {
							processingList.add((StructuralElementInstance) eObject);
						}
					}
				}
			}
		}

		// finally remove the SEI which was an input to the method
		// we only want the sub elements but no other ones.
		subSeis.remove(superSei);
		
		return subSeis;
	}

	
	/**
	 * Call this method to find out which SEI are or may be referenced by the given SEI. May reference means
	 * which SEIs in the current tree will be referenced if RPIs from the SuperSEIs will get inherited.
	 * @param subSei The SEI for which to find the referenced SEIs in the current Tree
	 * @return a HashSet with all referenced SEIs in the current Tree
	 */
	protected Set<StructuralElementInstance> getReferencesInTree(StructuralElementInstance subSei) {
		Set<StructuralElementInstance> referencedSubSeis = new HashSet<>();
		Set<StructuralElementInstance> referencedSuperSeis = new HashSet<>();
		
		// Find out which SEIs are SuperSEIs to the given ones. See
		// If theses SuperSEI contain any RPI and collect all the SEI which
		// these references target to. We cannot do this search in the current Tree
		// The SubTree since we don't know yet if the references have already been copied
		// We assume the SuperTree is consistent and therefore we have to use it
		// to find the references or potential references of the current tree
		for (StructuralElementInstance superSei : subSei.getSuperSeis()) {
			EcoreUtil.getAllContents(superSei, true).forEachRemaining((iObject) -> {
				if (iObject instanceof ReferencePropertyInstance) {
					ReferencePropertyInstance rpi = (ReferencePropertyInstance) iObject;
					ATypeInstance referencedTypeInstance = rpi.getReference();
					if (referencedTypeInstance != null) {
						StructuralElementInstance referencedSei = (StructuralElementInstance) referencedTypeInstance.getCategoryAssignmentContainer();
						referencedSuperSeis.add(referencedSei);
					}
				}
			});
		}
		
		// Since inheritance is considered to go across trees and references are supposed to stay within trees
		// we now have to find the common subset of StructuralElements. Therefore identify all SEIs which are part of the current tree.
		// After that loop over all of them and find out which ones inherit from one of the referenced SEIs in the SuperTRee
		Set<StructuralElementInstance>  subTreeSeis = getCompleteTree(subSei);
		
		for (StructuralElementInstance subTreeSei: subTreeSeis) {
			if (!Collections.disjoint(subTreeSei.getSuperSeis(), referencedSuperSeis)) {
				referencedSubSeis.add(subTreeSei);
			}
		}
		
		// should not contain itself
		referencedSubSeis.remove(subSei);
		
		return referencedSubSeis;
	}
	
	/**
	 * Call this method to get all SEIs of the current tree
	 * @param sei a SEI within the tree
	 * @return A HashSet with all SEIs which are part of the Tree
	 */
	protected Set<StructuralElementInstance> getCompleteTree(StructuralElementInstance sei) {
		Set<StructuralElementInstance> treeSeis = new HashSet<>();
		
		StructuralElementInstance rootSei = (StructuralElementInstance) EcoreUtil.getRootContainer(sei, true);
		treeSeis.add(rootSei);
		
		EcoreUtil.getAllContents(rootSei, true).forEachRemaining((iObject) -> {
			if (iObject instanceof StructuralElementInstance) {
				treeSeis.add((StructuralElementInstance) iObject);
			}
		});
		
		return treeSeis;
	}
	
	/**
	 * This method hands back the origin/ root TI in terms of inheritance from a given TI
	 * @param subTi the SUbTi of which to find the superRootTi
	 * @return A Set of Tis that represent a root. Usually this set should contain just one instance
	 */
	public static Set<IInheritanceLink> getRootSuperTypeInstance(IInheritanceLink subTi) {
		Set<IInheritanceLink> rootTis = new HashSet<IInheritanceLink>();
		LinkedList<IInheritanceLink> processingTis = new LinkedList<IInheritanceLink>();
		LinkedList<IInheritanceLink> processedTis = new LinkedList<IInheritanceLink>();
		
		processingTis.add(subTi);
		
		while (!processingTis.isEmpty()) {
			IInheritanceLink processingTi = processingTis.removeFirst();
			processedTis.add(processingTi);
			
			// This if identifies a real root node
			if (processingTi.getSuperTis().isEmpty()) {
				rootTis.add(processingTi);
			}
			
			// Add superTis to processing List
			processingTi.getSuperTis().forEach((superTi) -> {
				if (!processedTis.contains(superTi)) {
					processingTis.add(superTi);
				}
			});
		}

		return rootTis;
	}
	
	/**
	 * This method hands back a flat list of TIs which are contained in the given Structural Element
	 * @param sei The SEi of which to get the TIs
	 * @return A HashSet of all TIs
	 */
	public static Set<IInheritanceLink> getAllTypeInstances(StructuralElementInstance sei) {
		Set<IInheritanceLink> allTis = new HashSet<>();
		
		EcoreUtil.getAllContents(sei.getCategoryAssignments(), true).forEachRemaining((potentialIInheritanceLink) -> {
			if (potentialIInheritanceLink instanceof IInheritanceLink) {
				IInheritanceLink subIInheritanceLink = (IInheritanceLink) potentialIInheritanceLink;
				allTis.add(subIInheritanceLink);
			}
		});
		
		return allTis;
	}
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceCopier#needsUpdateInOrder(de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance, de.dlr.sc.virsat.model.dvlm.Repository)
	 */
	@Override
	public boolean needsUpdateInOrder(StructuralElementInstance subSei) {
		List<StructuralElementInstance> inheritanceOrder = getSuperSeisInheritanceOrder(subSei);
		
		for (StructuralElementInstance processingSei : inheritanceOrder) {
			if (needsUpdateStep(processingSei)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This method is used to find all SEIs in the given Repo. This method is needed, to
	 * resolve all Proxies and to find all the SEIs which may reference a given one.
	 * Since we cannot be sure, that all resources are well resolved, we use this method, to be
	 * sure, that we know all StructuralElementInstances
	 * @param repo The repository of which to load all SEIs
	 * @return a Set of SEIs referenced in the Repo
	 */
	protected Set<StructuralElementInstance> getAllRepoSeis(Repository repo) {
		Set<StructuralElementInstance> allSeis = new HashSet<>();
		
		EcoreUtil.getAllContents(repo.getRootEntities(), true).forEachRemaining((potentialSei) -> {
			if (potentialSei instanceof StructuralElementInstance) {
				StructuralElementInstance sei = (StructuralElementInstance) potentialSei;
				allSeis.add(sei);
			}
		});
		
		return allSeis;
	}
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceCopier#updateInOrder(de.dlr.sc.virsat.model.dvlm.Repository, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Set<CategoryAssignment> updateAllInOrder(Repository repo, IProgressMonitor monitor) {
		final int THREE_TASKS = 3;
		SubMonitor subMonitor = SubMonitor.convert(monitor, THREE_TASKS);
		subMonitor.beginTask("Get Inheritance Order", THREE_TASKS);
		// get all Seis which are in the Project
		Set<StructuralElementInstance> allSeis = getAllRepoSeis(repo);
		subMonitor.worked(1);
		
		// Now order all SEIs and update them accordingly
		List<StructuralElementInstance> inheritanceOrder = orderByInheritance(allSeis);
		Set<CategoryAssignment> collectedCas = new HashSet<>();
		subMonitor.worked(1);
		
		SubMonitor loopMonitor = SubMonitor.convert(subMonitor.newChild(1), inheritanceOrder.size());
		loopMonitor.beginTask("Inheriting from SEI...", inheritanceOrder.size());
		for (StructuralElementInstance processingSei : inheritanceOrder) {
			Set<CategoryAssignment> copiedCas = updateStep(processingSei);
			collectedCas.addAll(copiedCas);
			loopMonitor.worked(1);
		}
		return collectedCas;
	}
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceCopier#updateInOrder(de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance, de.dlr.sc.virsat.model.dvlm.Repository, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Set<CategoryAssignment> updateInOrderFrom(StructuralElementInstance subSei, Repository repo, IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
		subMonitor.beginTask("Get Inheritance Order", 2);
		List<StructuralElementInstance> inheritanceOrder = getInheritanceOrder(subSei, repo);
		Set<CategoryAssignment> collectedCas = new HashSet<>();
		subMonitor.worked(1);
		
		SubMonitor loopMonitor = SubMonitor.convert(subMonitor.newChild(1), inheritanceOrder.size());
		loopMonitor.beginTask("Inheriting from SEI...", inheritanceOrder.size());
		for (StructuralElementInstance processingSei : inheritanceOrder) {
			Set<CategoryAssignment> copiedCas = updateStep(processingSei);
			collectedCas.addAll(copiedCas);
			loopMonitor.worked(1);
		}
		return collectedCas;
	}
	
	/**
	 * Remove all inherited CAs in the SEI that no longer have a valid super Ti
	 * @param sei the sei to be cleaned of stale inherited CAs
	 */
	public void cleanCas(StructuralElementInstance sei) {
		List<CategoryAssignment> cas = new ArrayList<>(sei.getCategoryAssignments());
		for (CategoryAssignment ca : cas) {
			boolean isInherited = ca.isIsInherited();
			boolean hasNoSuperTis = ca.getSuperTis().isEmpty();
			if (isInherited && hasNoSuperTis) {
				EcoreUtil.delete(ca);
			}
		}
	}
	
	/**
	 * Clean the links of the contained type instances in a structural element instance that have no valid link
	 * Cleaning means if the current subSei has a typeInstance which is linked to a typeInstance , that is not
	 * contained in one of the superSeis anymore, then it has to be removed from the subSei.
	 * @param sei the structural element instance to be cleaned
	 */
	public void cleanSuperTis(StructuralElementInstance sei) {
		List<StructuralElementInstance> superSeis = sei.getSuperSeis();
		
		// First step is to get all type instances from all superSeis
		Set<IInheritanceLink> allSuperTis = new HashSet<>();
		for (StructuralElementInstance superSei : superSeis) {
			Set<IInheritanceLink> superTis = getAllTypeInstances(superSei);
			allSuperTis.addAll(superTis);
		}
		
		// Now get all the type instances in the subSei
		// And loop over all the current typeInstances
		// rather than removing we only keep the ones which 
		// are present in the superSei
		Set<IInheritanceLink> childTypeInstances = getAllTypeInstances(sei);
		for (IInheritanceLink childTi : childTypeInstances) {
			childTi.getSuperTis().retainAll(allSuperTis);
		}
	}
}
