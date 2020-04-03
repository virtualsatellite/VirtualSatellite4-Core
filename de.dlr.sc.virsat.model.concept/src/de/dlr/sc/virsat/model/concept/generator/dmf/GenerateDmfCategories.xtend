/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.dmf

import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumPropertyHelper
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil
import java.io.ByteArrayOutputStream
import java.io.IOException
import org.eclipse.core.runtime.Path
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil
import org.eclipse.emf.common.util.BasicMonitor
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl
import org.eclipse.emf.importer.ecore.EcoreImporter
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind
import java.util.Date
import java.util.Calendar
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import de.dlr.sc.virsat.model.concept.generator.ereference.ExternalGenModelHelper
import java.util.ArrayList
import java.util.List
import java.util.Set
import java.util.HashSet
import de.dlr.sc.virsat.model.concept.resources.ConceptResourceLoader

/**
 * This generator generates an eCore model with eClasses out of described categories
 */
class GenerateDmfCategories {

	public static final String GENMODEL_TYPE_ECORE_EXTENSION = "genmodel";
	public static final String MODEL_TYPE_ECORE_EXTENSION = "ecore";
	public static final String MODEL_CONCEPT_ECORE_FILENAME = "concept." + MODEL_TYPE_ECORE_EXTENSION;
	public static final String GENMODEL_CONCEPT_ECORE_FILENAME = "concept." + GENMODEL_TYPE_ECORE_EXTENSION;
	public static final String MODEL_DVLM_ECORE_URI = "/de.dlr.sc.virsat.model/model/dvlm.ecore";


	public static final String MODEL_DMF_DOBJECT_NAME = "DObject";
	public static final String MODEL_NS_PREFIX_PREFIX = "dmf_";
	
	EClass dvlmDObject;
	ResourceSet ecoreModelResourceSet;
	Resource targetResource;
	Resource dvlmResource;
	String platformPluginUriStringForEcoreModel;
	Set<EPackage> eReferenceEPackages = new HashSet
	
	/**
	 * This method serialized the data model into the given format
	 * @param fileNameExtension the extension of the target format to serialize to. Can be either XMI or XML for the  moment
	 * @param dataModel access to the root model object
	 * @param fsa access to the local file system
	 */
	def serializeModel(Concept dataModel, IFileSystemAccess fsa) {
		// If the DMF flag is not set, don't generate the ECore model for the concept
		if (!dataModel.DMF) {
			return;
		}
		
		// Ok, lets start generating the ecore.
		val resourceRegistry = Resource.Factory.Registry.INSTANCE;
		resourceRegistry.extensionToFactoryMap.put(MODEL_TYPE_ECORE_EXTENSION, new EcoreResourceFactoryImpl());
		resourceRegistry.extensionToFactoryMap.put(GENMODEL_TYPE_ECORE_EXTENSION, new EcoreResourceFactoryImpl());

		// Create a resource set and a resource to store it as an eCore meta model
		try {
			initResources(dataModel);
			
			val ePackage = createEPackageFromConcept(dataModel);
			createCategoryEClassesInEPackage(dataModel, ePackage);
			
			val outputStream = new ByteArrayOutputStream();
			targetResource.save(outputStream, null); 
			
			fsa.generateFile(GenerateDmfCategories.MODEL_CONCEPT_ECORE_FILENAME, ConceptOutputConfigurationProvider::GENERATOR_OUTPUT_ID_CONCEPT, outputStream.toString());
			
			val ecoreImporter = new EcoreImporter();
			val monitor = new BasicMonitor();
			
			ecoreImporter.genModelFileName = GenerateDmfCategories.GENMODEL_CONCEPT_ECORE_FILENAME;		
			ecoreImporter.genModelContainerPath = new Path("/" + dataModel.name + "/concept/");
			ecoreImporter.addGenModelToResource(true);
			
			ecoreImporter.modelLocation	= platformPluginUriStringForEcoreModel;
      		
      		for (Resource resource : ecoreModelResourceSet.resources) {
      			if (resource.contents.size > 0) {
      				val resourceEPackage = resource.contents.get(0) as EPackage;
      				ecoreImporter.EPackages += resourceEPackage;
      			}
      		}
      		eReferenceEPackages.forEach[
      			ecoreImporter.EPackages.add(it);
      		]
      		
      		ecoreImporter.adjustEPackages(monitor);
      		val genEPackage = ecoreImporter.EPackages.get(0);
      		ecoreImporter.getEPackageConvertInfo(genEPackage).convert = true;
      		
			val genModels = ecoreImporter.externalGenModels;
			
      		genModels.forEach[
      			it.genPackages.forEach[
      				// Check that we dont create reference loops
      				if (it.basePackage !== null && !it.basePackage.equals(dataModel.name)) {
      					ecoreImporter.referencedGenPackages += it;
      					ecoreImporter.getReferenceGenPackageConvertInfo(it).validReference = true;
      				}
      			]
      		]
      		
      		ecoreImporter.prepareGenModelAndEPackages(monitor);
      		ecoreImporter.genModel.genPackages.get(0).basePackage = dataModel.name;
      		ecoreImporter.genModel.modelDirectory = ecoreImporter.genModel.modelDirectory + "-dmf";
      		ecoreImporter.genModel.editDirectory = ecoreImporter.genModel.editDirectory + "-dmf";
      		ecoreImporter.genModel.editorDirectory = ecoreImporter.genModel.editorDirectory + "-dmf";
      		ecoreImporter.genModel.testsDirectory = ecoreImporter.genModel.testsDirectory + "-dmf";
      		ecoreImporter.genModel.copyrightText = 
"Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

SPDX-License-Identifier: EPL-2.0";
      		
      		ecoreImporter.genModel.genPackages.forEach[
      			it.resource = GenResourceKind.XMI_LITERAL;
      		]
      		
      		ecoreImporter.saveGenModelAndEPackages(monitor);
      		
      		ecoreImporter.genModel.reconcile();
  			ecoreImporter.genModel.canGenerate = true;
      		
      		val generator = GenModelUtil.createGenerator(ecoreImporter.genModel);
      		generator.generate(ecoreImporter.genModel , GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, monitor);
      		generator.generate(ecoreImporter.genModel , GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, monitor);
      		
      		ecoreModelResourceSet.resources.clear();
      		
   		} catch (IOException e) {
			throw new RuntimeException(e);	
		}
	}
	
	/**
	 * This method creates the Ecore EPackage from a concept where all categories will
	 * be stored in
	 * @param coneptModel The concept Model that describes all categories and their properties
	 */
	def createEPackageFromConcept(Concept conceptModel) {
		var conceptName = conceptModel.name;
		conceptName = VirSatEcoreUtil.getNameOfFqn(conceptName);
		
		val ePackage = EcoreFactory.eINSTANCE.createEPackage;
		ePackage.name = conceptName;
		ePackage.nsPrefix = MODEL_NS_PREFIX_PREFIX + conceptName;
		ePackage.nsURI = ActiveConceptHelper.getDmfNsUriForConcept(conceptModel);
		
		targetResource.getContents().add(ePackage);
		
		return ePackage;
	}
	
	/**
	 * This method creates the classes corresponding to categories
	 * @param conceptModel the concept model which contains all categories
	 * @param ePackage the ePackage in which the category based eclasses should be stored
	 */
	def createCategoryEClassesInEPackage(Concept conceptModel, EPackage ePackage) {
		// Loop over all categories
		conceptModel.categories.forEach[
			
			// Create the eclass
			val catEClass = EcoreFactory.eINSTANCE.createEClass;
			ePackage.EClassifiers += catEClass;
			catEClass.name = it.name;
			catEClass.abstract = it.isIsAbstract;
			
			// Create the attributes and references
			it.properties.forEach[
				val propEStructuralFeature = new PropertydefinitionsSwitch<EStructuralFeature>() {
					
					override caseStringProperty(StringProperty object) {
						val attribute = EcoreFactory.eINSTANCE.createEAttribute;
						attribute.EType = EcorePackage.Literals.ESTRING;
						return attribute;
					}
					
					override caseFloatProperty(FloatProperty object) {
						val attribute = EcoreFactory.eINSTANCE.createEAttribute;
						attribute.EType = EcorePackage.Literals.EDOUBLE;
						return attribute;
					}
					
					override caseIntProperty(IntProperty object) {
						val attribute = EcoreFactory.eINSTANCE.createEAttribute;
						attribute.EType = EcorePackage.Literals.EINT;
						return attribute;
					}
					
					override caseEnumProperty(EnumProperty object) {
						val attribute = EcoreFactory.eINSTANCE.createEAttribute;
						val eEnumType = createEEnum(object); 
						attribute.EType = eEnumType;
						ePackage.EClassifiers += eEnumType;
						return attribute;
					}
					
					override caseBooleanProperty(BooleanProperty object) {
						val attribute = EcoreFactory.eINSTANCE.createEAttribute;
						attribute.EType = EcorePackage.Literals.EBOOLEAN;
						return attribute;
					}
					
					override caseResourceProperty(ResourceProperty object) {
						val attribute = EcoreFactory.eINSTANCE.createEAttribute;
						attribute.EType = EcorePackage.Literals.ESTRING;
						return attribute;
					}
					
					override caseComposedProperty(ComposedProperty cp) {
						val eReference = EcoreFactory.eINSTANCE.createEReference;
						eReference.containment = true;
						return eReference;
					}
					
					override caseReferenceProperty(ReferenceProperty rp) {
						// in case a null is returned, the reference most likely points to a property
						// this cannot be implemented with the simple Ecore based DMF model and therefore a
						// null is returned as structural feature. Such a null will not be processed in further code
						if(rp.referenceType instanceof AProperty) {
							return null;
						}
						
						val eReference = EcoreFactory.eINSTANCE.createEReference;
						return eReference;
					}
					
					override caseEReferenceProperty(EReferenceProperty object) {
						val eReference = EcoreFactory.eINSTANCE.createEReference;
						eReference.EType = object.referenceType
						eReferenceEPackages += eReference.EType.eContainer as EPackage
						new ExternalGenModelHelper().resolveGenPackage(object)
						return eReference;
					}
					
				}.doSwitch(it);
				
				// In case no StructuralFeature for the Property could be created
				// than just simply return and go to the next property
				if (propEStructuralFeature === null) {
					return;
				}
				
				// Set the name of the structural feature
				propEStructuralFeature.name = it.name;
				catEClass.EStructuralFeatures += propEStructuralFeature;
				
				// Set the cardinalities of the structural feature
				if (it.arrayModifier !== null && it.arrayModifier instanceof StaticArrayModifier) {
					val sam = it.arrayModifier as StaticArrayModifier;
					propEStructuralFeature.lowerBound = 0;
					propEStructuralFeature.upperBound = sam.arraySize;
				} else if (it.arrayModifier !== null && it.arrayModifier instanceof DynamicArrayModifier) {
					propEStructuralFeature.lowerBound = 0;
					propEStructuralFeature.upperBound = -1;
				} else {
					propEStructuralFeature.lowerBound = 0;
					propEStructuralFeature.upperBound = 1;
				}
			]
		]
		
		// Loop over all categories to set all references and super types
		conceptModel.categories.forEach[
			
			// Create the eclass
			val catEClass = ePackage.getEClassifier(it.name) as EClass;
			
			if (it.extendsCategory !== null) {
				val referencedEClass = findTypeDefinitionInEcoreResource(it.extendsCategory);
				catEClass.ESuperTypes += referencedEClass;
			}
			if (catEClass.ESuperTypes.isEmpty) {
				catEClass.ESuperTypes += dvlmDObject
			}
			
			// Create the attributes and references
			it.properties.forEach[
				new PropertydefinitionsSwitch<EStructuralFeature>() {
					override caseComposedProperty(ComposedProperty cp) {
						val referencedEClass = findTypeDefinitionInEcoreResource(cp.type);
						val eReference = catEClass.getEStructuralFeature(it.name) as EReference;
						eReference.EType = referencedEClass;
						eReference.containment = true;
						return eReference;
					}
					
					override caseReferenceProperty(ReferenceProperty rp) {
						val referencedEClass = findTypeDefinitionInEcoreResource(rp.referenceType);
						val eReference = catEClass.getEStructuralFeature(it.name) as EReference;
						if (eReference !== null) {
							eReference.EType = referencedEClass;
						}
						return eReference;
					}
				}.doSwitch(it);
			]
		]
	}


	/**
	 * This method creates and EEnum Class out of the information of
	 * the EnumProperty
	 */	
	def EEnum createEEnum(EnumProperty property) {
		val eEnum = EcoreFactory.eINSTANCE.createEEnum();
		eEnum.name = new EnumPropertyHelper().getDmfEEnumName(property);
		
		property.values.forEach[
			val eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral;
			eEnumLiteral.name = it.name;
			eEnumLiteral.literal = it.name;
			eEnumLiteral.value = Double.parseDouble(it.value) as int;
			eEnum.ELiterals += eEnumLiteral; 
		]
		
		return eEnum;
	}

	/** 
	 * This method initializes the resource set by creating the target resource
	 * and getting DVLM Ecore model and the DObject.
	 */
	def initResources(Concept conceptModel) {
		ecoreModelResourceSet = new ResourceSetImpl();
		platformPluginUriStringForEcoreModel = "/" + conceptModel.name + "/concept/" + GenerateDmfCategories.MODEL_CONCEPT_ECORE_FILENAME;
		targetResource = ecoreModelResourceSet.createResource(URI.createPlatformResourceURI(platformPluginUriStringForEcoreModel, true));
		val dvlmUri = URI.createPlatformPluginURI(MODEL_DVLM_ECORE_URI, true);
		dvlmResource = ecoreModelResourceSet.getResource(dvlmUri, true);
		dvlmDObject = getDObjectFromDvlm(dvlmResource);
	}
	
	/**
	 * Gets the Ecore model resource set
	 */
	def getEcoreModelResourceSet() {
		return ecoreModelResourceSet;
	}

	/**
	 * This method finds a property in its Ecore representation rather than in the .concept xText based 
	 * file. This is needed to create correct references in one Ecore based category model to another one
	 */
	private def findTypeDefinitionInEcoreResource(ATypeDefinition ap) {
		// Check in which resource the referenced property is lying
		// and bend the resource from the xtext based concept file, to the
		// ecore based categories model. After that load the resource and find
		// the eclass which is referenced by its name 
		var concept = ap.eResource.contents.get(0) as Concept
		var ecoreUri = ConceptResourceLoader.instance.getConceptDMFResourceUri(concept.name)
		if(ecoreUri === null) {
			//If concept is not registered via extension then check next to the concept file
			val rpUri = ap.eResource.URI;
			val ecorePath = rpUri.toString.replace(".concept", ".ecore").replace(".xmi", ".ecore");
			ecoreUri = URI.createURI(ecorePath);
		}
		var ecoreResource = ecoreModelResourceSet.getResource(ecoreUri, true);

		val referencedEClass = EcoreUtil.getAllContents(ecoreResource, true).findFirst[
			if (it instanceof EClass) {
				val eClass = it as EClass;
				if (eClass.name.equals(ap.name)) {
					return true;
				}
			}	
			return false;	
		] as EClass;
		
		return referencedEClass;
	}
	
	/**
	 * This method hands back the DObject from the DVLM Ecore which
	 * is the base class for all implementations of the category eClasses
	 */
	private def getDObjectFromDvlm(Resource dvlmResource) {
		return EcoreUtil.getAllContents(dvlmResource, true).findFirst[
			if (it instanceof EClass) {
				val eClass = it as EClass;
				if (eClass.name.equals(MODEL_DMF_DOBJECT_NAME)) {
					return true;
				}
			}	
			
			return false;	
		] as EClass;
	}
}