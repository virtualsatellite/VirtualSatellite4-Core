/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
 package de.dlr.sc.virsat.model.concept.generator.beans

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.concept.generator.ImportManager
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil
import de.dlr.sc.virsat.model.concept.list.IBeanList
import de.dlr.sc.virsat.model.concept.list.TypeSafeArrayInstanceList
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyInstanceList
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition
import de.dlr.sc.virsat.model.dvlm.categories.Category
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper
import org.eclipse.emf.common.command.Command
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.edit.command.SetCommand
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.core.runtime.CoreException
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance
import de.dlr.sc.virsat.model.concept.types.factory.BeanRegistry
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.Resource
import java.io.IOException
import de.dlr.sc.virsat.model.concept.Activator
import org.eclipse.core.runtime.Status
import de.dlr.sc.virsat.model.concept.list.TypeSafeEReferenceArrayInstanceList
import de.dlr.sc.virsat.model.concept.generator.ereference.ExternalGenModelHelper

/**
 * This class is the generator for the category beans of our model extension.
 * The beans will give easier access to the model of categories and properties.
 * Furthermore it will provide strong typing
 */
class GenerateCategoryBeans extends AGeneratorGapGenerator<Category> {
	
	static def getConcreteClassName(ATypeDefinition typeDefinition) {
		typeDefinition.name.toFirstUpper
	}
	
	static def getAbstractClassName(ATypeDefinition typeDefinition) {
		"A" + typeDefinition.name.toFirstUpper
	}
	
	override createConcreteClassFileName(Concept concept, Category conceptPart) {
		concept.packageFolder + "/" +conceptPart.concreteClassName + ".java"
	}
	
	override createAbstractClassFileName(Concept concept, Category conceptPart) {
		concept.packageFolder + "/" +conceptPart.abstractClassName + ".java"
	}
	
	override protected getPackage(Concept concept) {
		concept.name + "." + PACKAGE_FOLDER;
	}
	
	public static val PACKAGE_FOLDER = BeanRegistry.BEAN_PACKAGE_NAME;
	
	override serializeModel(Concept concept, IFileSystemAccess fsa) {
		concept.categories.forEach[
			// ************************************************************************************
			// Abstract Class
			// ************************************************************************************
			var fileOutputAClass = createAbstractClass(concept, it);
			fsa.generateFile(createAbstractClassFileName(concept, it), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);

			// ************************************************************************************
			// Concrete Class
			// ************************************************************************************
			var fileOutputClass = createConcreteClass(concept, it);
			fsa.generateFile(createConcreteClassFileName(concept, it), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);			
		]
	}

	/**
	 * Declare the package statement of the java file 
	 */
	override declarePackage(String pluginPackage) '''
	package «pluginPackage»;
	'''
	// *************************************************************************************
	// * generate imports
	// *************************************************************************************
	
	/**
	 * Write down all the import statements needed by this java file
	 */
	override declareImports(ImportManager importManager, Concept concept, Category conceptPart, String conceptPackage) '''
	// *****************************************************************
	// * Import Statements
	// *****************************************************************
	«IF !importManager.importedClasses.empty»
		«FOR clazz : importManager.importedClasses»
			import «clazz»;
		«ENDFOR»
  	«ENDIF»
	'''

	// *************************************************************************************
	// * generate class
	// *************************************************************************************
	
	/**
	 *	Entry method to write the class body 
	 */
	override declareAClass(Concept concept, Category category, ImportManager importManager) '''
	«importManager.register(CategoryAssignment)»
	«IF category.extendsCategory === null» 
	«importManager.register(ABeanCategoryAssignment)»
	«ELSE»
	«importManager.register(category.extendsCategory)»
	«ENDIF»
	«importManager.register(IBeanCategoryAssignment)»
	
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	«ConceptGeneratorUtil.generateAClassHeader(category)»
	public abstract class A«category.name.toFirstUpper» extends «declareExtendedClass(category)» implements IBeanCategoryAssignment {

		«val fullQualifiedCategoryId = ActiveConceptHelper.getFullQualifiedId(category)»
		public static final String FULL_QUALIFIED_CATEGORY_NAME = "«fullQualifiedCategoryId»";
		
		/**
	 	* Call this method to get the full qualified name of the underlying category
	 	* @return The FQN of the category as String
	 	*/
		public String getFullQualifiedCategoryName() {
			return FULL_QUALIFIED_CATEGORY_NAME;
		}
		
		«declarePropertyNameConstants(category)»
		
		«declareEnumValues(category)»
		
		«declareConstructor(category, importManager)»
		
		«declareAttributes(category, importManager)»
		
	}
	'''
	
	protected def declareExtendedClass(Category category) {
		if (category.extendsCategory !== null) {
			return category.extendsCategory.name.toFirstUpper
		} else {
			return "ABeanCategoryAssignment"
		}
	}
	
	protected def declarePropertyNameConstants(Category category) '''
		// property name constants
		«FOR property : category.properties»
			public static final String PROPERTY_«property.name.toUpperCase» = "«property.name»";
		«ENDFOR»
	'''
	
	protected def declareEnumValues(Category category)'''
		«FOR enumeration : category.properties.filter(typeof(EnumProperty)).filter[!values.empty]»
			// «enumeration.name.toFirstUpper» enumeration value names
			«FOR value : enumeration.values»
				public static final String «enumeration.name.toUpperCase»_«value.name»_NAME = "«value.name»";
			«ENDFOR»
			// «enumeration.name.toFirstUpper» enumeration values
			«FOR value : enumeration.values.filter[value !== null]»
				public static final String «enumeration.name.toUpperCase»_«value.name»_VALUE = "«value.value»";
			«ENDFOR»
		«ENDFOR»
	'''

	override declareClass(Concept concept, Category category, ImportManager importManager) '''
	«importManager.register(Concept)»
	«importManager.register(CategoryAssignment)»
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	«ConceptGeneratorUtil.generateClassHeader(category)»
	public «declareIfAbstract(category)» class «category.name.toFirstUpper» extends A«category.name.toFirstUpper» {
		
		/**
		 * Constructor of Concept Class
		 */
		public «category.name.toFirstUpper»() {
			super();
		}
	
		/**
		 * Constructor of Concept Class which will instantiate 
		 * a CategoryAssignment in the background from the given concept
		 * @param concept the concept where it will find the correct Category to instantiate from
		 */
		public «category.name.toFirstUpper»(Concept concept) {
			super(concept);
		}	
	
		/**
		 * Constructor of Concept Class that can be initialized manually by a given Category Assignment
		 * @param categoryAssignment The category Assignment to be used for background initialization of the Category bean
		 */
		public «category.name.toFirstUpper»(CategoryAssignment categoryAssignment) {
			super(categoryAssignment);
		}
	}
	'''
	
	protected def declareIfAbstract(Category category) {
		if (category.isAbstract) {
			return "abstract";
		}
		
		return "";
	}

	// *************************************************************************************
	// * generate constructors
	// *************************************************************************************
	/**
	 * Method to create the constructor of the java category bean
	 */		
	protected def declareConstructor(Category category, ImportManager importManager) '''
	«importManager.register(Concept)»
	«importManager.register(Category)»
	«importManager.register(ActiveConceptHelper)»
	«importManager.register(CategoryInstantiator)»
	
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	public A«category.name.toFirstUpper»() {
	}
	
	public A«category.name.toFirstUpper»(Concept concept) {
		Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, "«category.name»");
		CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, "«category.name»");
		setTypeInstance(categoryAssignement);
	}

	public A«category.name.toFirstUpper»(CategoryAssignment categoryAssignement) {
		setTypeInstance(categoryAssignement);
	}

	'''
	// *************************************************************************************
	// * generate attributes
	// *************************************************************************************
	protected def declareAttributes(Category category, ImportManager importManager) '''
		«FOR property : category.properties»
			«declareSingleAttributesOrArray(property, importManager)»
		«ENDFOR»
	'''
	
	protected def declareSingleAttributesOrArray(AProperty property, ImportManager importManager) '''
		«IF property.arrayModifier === null»
			// *****************************************************************
			// * Attribute: «property.name»
			// *****************************************************************
			«declareAttributesSetterAndGetter(property, importManager)»
			
		«ELSE»
			// *****************************************************************
			// * Array Attribute: «property.name»
			// *****************************************************************
			«declareArrayAttributesSetterAndGetter(property, importManager)»
			
		«ENDIF»
	'''
	
	// *************************************************************************************
	// * generate the Array Properties
	// *************************************************************************************
	protected def declareSafeAccessArrayMethod(AProperty property, ImportManager importManager) '''
	«importManager.register(ArrayInstance)»
	private void «propertyMethodSafeAccess(property)» {
		if («property.name».getArrayInstance() == null) {
			«property.name».setArrayInstance((ArrayInstance) helper.getPropertyInstance("«property.name»"));
		}
	}
	'''
	protected def declareArrayAttributesSetterAndGetter(AProperty property, ImportManager importManager) {
		return new PropertydefinitionsSwitch<CharSequence>() {
			override caseFloatProperty(FloatProperty property) {
				importManager.register(BeanPropertyFloat);
				importManager.register(IBeanList);
				importManager.register(TypeSafeArrayInstanceList);
			
				return '''
				private IBeanList<BeanPropertyFloat> «property.name» = new TypeSafeArrayInstanceList<>(BeanPropertyFloat.class);
				
				«declareSafeAccessArrayMethod(property, importManager)»
					
				public IBeanList<BeanPropertyFloat> «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseIntProperty(IntProperty property) {
				importManager.register(BeanPropertyInt);
				importManager.register(IBeanList);
				importManager.register(TypeSafeArrayInstanceList);
			
				return '''
				private IBeanList<BeanPropertyInt> «property.name» = new TypeSafeArrayInstanceList<>(BeanPropertyInt.class);
				
				«declareSafeAccessArrayMethod(property, importManager)»
					
				public IBeanList<BeanPropertyInt> «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseBooleanProperty(BooleanProperty property) {
				importManager.register(BeanPropertyBoolean);
				importManager.register(IBeanList);
				importManager.register(TypeSafeArrayInstanceList);
			
				return '''
				private IBeanList<BeanPropertyBoolean> «property.name» = new TypeSafeArrayInstanceList<>(BeanPropertyBoolean.class);
				
				«declareSafeAccessArrayMethod(property, importManager)»
				
				public IBeanList<BeanPropertyBoolean> «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseStringProperty(StringProperty property) {
				importManager.register(BeanPropertyString);
				importManager.register(IBeanList);
				importManager.register(TypeSafeArrayInstanceList);
			
				return '''
				private IBeanList<BeanPropertyString> «property.name» = new TypeSafeArrayInstanceList<>(BeanPropertyString.class);
				
				«declareSafeAccessArrayMethod(property, importManager)»
				
				public IBeanList<BeanPropertyString> «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseResourceProperty(ResourceProperty object) {
				importManager.register(BeanPropertyResource);
				importManager.register(IBeanList);
				importManager.register(TypeSafeArrayInstanceList);
			
				return '''
				private IBeanList<BeanPropertyResource> «property.name» = new TypeSafeArrayInstanceList<>(BeanPropertyResource.class);
				
				«declareSafeAccessArrayMethod(property, importManager)»
				
				public IBeanList<BeanPropertyResource> «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseEnumProperty(EnumProperty property) {
				importManager.register(BeanPropertyEnum);
				importManager.register(IBeanList);
				importManager.register(TypeSafeArrayInstanceList);
			
				return '''
				private IBeanList<BeanPropertyEnum> «property.name» = new TypeSafeArrayInstanceList<>(BeanPropertyEnum.class);
				
				«declareSafeAccessArrayMethod(property, importManager)»
				
				public IBeanList<BeanPropertyEnum> «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseComposedProperty(ComposedProperty property) {
				importManager.register(property.type)
				importManager.register(IBeanList);
				importManager.register(TypeSafeComposedPropertyInstanceList)
				return '''
				private IBeanList<«property.type.name»> «property.name» = new TypeSafeComposedPropertyInstanceList<>(«property.type.name».class);
				
				«declareSafeAccessArrayMethod(property, importManager)»
				
				public IBeanList<«property.type.name»> «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseReferenceProperty(ReferenceProperty property) {
				if (property.referenceType instanceof Category) {
					importManager.register(property.referenceType)
					importManager.register(IBeanList);
					importManager.register(TypeSafeReferencePropertyInstanceList)
					return '''
					private IBeanList<«property.referenceType.name»> «property.name» = new TypeSafeReferencePropertyInstanceList<>(«property.referenceType.name».class);
				
					«declareSafeAccessArrayMethod(property, importManager)»
				
					public IBeanList<«property.referenceType.name»> «propertyMethodGet(property)»() {
						«propertyMethodSafeAccess(property)»;
						return «property.name»;
					}
					'''	
				} else {
					var referencedProperty = property.referenceType as AProperty;
					var referencedPropertyType = getReferencePropertyType(referencedProperty);
					importManager.register(TypeSafeReferencePropertyInstanceList)
					importManager.register(IBeanList);
					return '''
					private IBeanList<«referencedPropertyType.name»> «property.name» = new TypeSafeReferencePropertyInstanceList<>(«referencedPropertyType.name».class);
				
					«declareSafeAccessArrayMethod(property, importManager)»
				
					public IBeanList<«referencedPropertyType.name»> «propertyMethodGet(property)»() {
						«propertyMethodSafeAccess(property)»;
						return «property.name»;
					}
					'''	
				}	
			}
			
			override caseEReferenceProperty(EReferenceProperty property) {
				importManager.register(BeanPropertyEReference);
				importManager.register(IBeanList);
				importManager.register(TypeSafeEReferenceArrayInstanceList);
				
				val typeClass = new ExternalGenModelHelper().getEObjectClass(property)
				val genPackageSpecified = typeClass !== null
				if(genPackageSpecified) {
					importManager.register(typeClass)
				}
				val referenceType = '''«IF genPackageSpecified»«property.referenceType.name»«ELSE»EObject«ENDIF»'''
			
				return '''
				private IBeanList<BeanPropertyEReference<«referenceType»>> «property.name» = new TypeSafeEReferenceArrayInstanceList<«referenceType»>();
				
				«declareSafeAccessArrayMethod(property, importManager)»
				
				public IBeanList<BeanPropertyEReference<«referenceType»>> «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''	
			}			
		}.doSwitch(property)
	}

	// *************************************************************************************
	// * generate the NON Array Properties
	// *************************************************************************************
	protected def declareSafeAccessAttributeMethod(AProperty property, Class<? extends APropertyInstance> castTypeClass) '''
	private void «propertyMethodSafeAccess(property)» {
		if («property.name».getTypeInstance() == null) {
			«property.name».setTypeInstance((«castTypeClass.simpleName») helper.getPropertyInstance("«property.name»"));
		}
	}
	'''
	
	/**
	 * Entry method to write the needed getters and setters
	 * This method actually dispatches due to the type of property that is used 
	 */
	protected def declareAttributesSetterAndGetter(AProperty property, ImportManager importManager) {
		return new PropertydefinitionsSwitch<CharSequence>() {
			override caseFloatProperty(FloatProperty property) {
				importManager.register(BeanPropertyFloat);
				importManager.register(Command);
				importManager.register(EditingDomain);
				importManager.register(UnitValuePropertyInstance);
			
				return '''
				private BeanPropertyFloat «property.name» = new BeanPropertyFloat();

				«declareSafeAccessAttributeMethod(property, UnitValuePropertyInstance)»
				
				public Command «propertyMethodSet(property)»(EditingDomain ed, double value) {
					«propertyMethodSafeAccess(property)»;
					return this.«property.name».setValue(ed, value);
				}

				public void «propertyMethodSet(property)»(double value) {
					«propertyMethodSafeAccess(property)»;
					this.«property.name».setValue(value);
				}

				public double «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name».getValue();
				}
				
				public boolean «propertyMethodIsSet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name».isSet();
				}
				
				public BeanPropertyFloat «propertyMethodGet(property)»Bean() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseIntProperty(IntProperty property) {
				importManager.register(BeanPropertyInt);
				importManager.register(Command);
				importManager.register(EditingDomain);
				importManager.register(UnitValuePropertyInstance);
				return '''
				private BeanPropertyInt «property.name» = new BeanPropertyInt();

				«declareSafeAccessAttributeMethod(property, UnitValuePropertyInstance)»

				public Command «propertyMethodSet(property)»(EditingDomain ed, long value) {
					«propertyMethodSafeAccess(property)»;
					return this.«property.name».setValue(ed, value);
				}

				public void «propertyMethodSet(property)»(long value) {
					«propertyMethodSafeAccess(property)»;
					this.«property.name».setValue(value);
				}

				public long «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name».getValue();
				}
				
				public boolean «propertyMethodIsSet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name».isSet();
				}
				
				public BeanPropertyInt «propertyMethodGet(property)»Bean() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseBooleanProperty(BooleanProperty property) {
				importManager.register(BeanPropertyBoolean);
				importManager.register(Command);
				importManager.register(EditingDomain);
				importManager.register(ValuePropertyInstance);
				
				return '''
				private BeanPropertyBoolean «property.name» = new BeanPropertyBoolean();

				«declareSafeAccessAttributeMethod(property, ValuePropertyInstance)»
				
				public Command «propertyMethodSet(property)»(EditingDomain ed, boolean value) {
					«propertyMethodSafeAccess(property)»;
					return this.«property.name».setValue(ed, value);
				}

				public void «propertyMethodSet(property)»(boolean value) {
					«propertyMethodSafeAccess(property)»;
					this.«property.name».setValue(value);
				}

				public boolean «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name».getValue();
				}
				
				public BeanPropertyBoolean «propertyMethodGet(property)»Bean() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseStringProperty(StringProperty property) {
				importManager.register(BeanPropertyString);
				importManager.register(Command);
				importManager.register(EditingDomain);
				importManager.register(ValuePropertyInstance);
				
				return '''
				private BeanPropertyString «property.name» = new BeanPropertyString();
				
				«declareSafeAccessAttributeMethod(property, ValuePropertyInstance)»
				
				public Command «propertyMethodSet(property)»(EditingDomain ed, String value) {
					«propertyMethodSafeAccess(property)»;
					return this.«property.name».setValue(ed, value);
				}

				public void «propertyMethodSet(property)»(String value) {
					«propertyMethodSafeAccess(property)»;
					this.«property.name».setValue(value);
				}

				public String «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name».getValue();
				}
				
				public BeanPropertyString «propertyMethodGet(property)»Bean() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseResourceProperty(ResourceProperty object) {
				importManager.register(BeanPropertyResource);
				importManager.register(Command);
				importManager.register(EditingDomain);
				importManager.register(ResourcePropertyInstance);
				importManager.register(URI);
				
				return '''
				private BeanPropertyResource «property.name» = new BeanPropertyResource();
				
				«declareSafeAccessAttributeMethod(property, ResourcePropertyInstance)»
				
				public Command «propertyMethodSet(property)»(EditingDomain ed, URI value) {
					«propertyMethodSafeAccess(property)»;
					return this.«property.name».setValue(ed, value);
				}

				public void «propertyMethodSet(property)»(URI value) {
					«propertyMethodSafeAccess(property)»;
					this.«property.name».setValue(value);
				}

				public URI «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name».getValue();
				}
				
				public BeanPropertyResource «propertyMethodGet(property)»Bean() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseEnumProperty(EnumProperty property) {
				importManager.register(BeanPropertyEnum);
				importManager.register(Command);
				importManager.register(EditingDomain);
				importManager.register(EnumUnitPropertyInstance);
				
				return '''
				private BeanPropertyEnum «property.name» = new BeanPropertyEnum();
				
				«declareSafeAccessAttributeMethod(property, EnumUnitPropertyInstance)»
				
				public Command «propertyMethodSet(property)»(EditingDomain ed, String value) {
					«propertyMethodSafeAccess(property)»;
					return this.«property.name».setValue(ed, value);
				}

				public void «propertyMethodSet(property)»(String value) {
					«propertyMethodSafeAccess(property)»;
					this.«property.name».setValue(value);
				}

				public String «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name».getValue();
				}

				public double «propertyMethodGet(property)»Enum() {
					«propertyMethodSafeAccess(property)»;
					return «property.name».getEnumValue();
				}
				
				public BeanPropertyEnum «propertyMethodGet(property)»Bean() {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseComposedProperty(ComposedProperty property) {
				importManager.register(ComposedPropertyInstance);
				importManager.register(property.type);
				
				return '''
				private «property.type.name» «property.name» = new «property.type.name»();
				
				private void «propertyMethodSafeAccess(property)» {
					if («property.name».getTypeInstance() == null) {
						ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) helper.getPropertyInstance("«property.name»");
						«property.name».setTypeInstance(propertyInstance.getTypeInstance());
					}
				}
				
				public «property.type.name» «propertyMethodGet(property)» () {
					«propertyMethodSafeAccess(property)»;
					return «property.name»;
				}
				'''
			}
			
			override caseReferenceProperty(ReferenceProperty property) {
				if (property.referenceType instanceof Category) {
					importManager.register(ReferencePropertyInstance);
					importManager.register(CategoryAssignment);
					importManager.register(BeanCategoryAssignmentFactory);
					importManager.register(Command);
					importManager.register(SetCommand);
					importManager.register(EditingDomain);
					importManager.register(PropertyinstancesPackage);
					importManager.register(CoreException);
					importManager.register(property.referenceType)
				
					return '''
					private «property.referenceType.name» «property.name»;
					
					private void «propertyMethodSafeAccess(property)» {
						ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("«property.name»");
						CategoryAssignment ca = (CategoryAssignment) propertyInstance.getReference();
						
						if (ca != null) {
							if («property.name» == null) {
								«propertyMethodCreate(property)»(ca);
							}
							«property.name».setTypeInstance(ca);
						} else {
							«property.name» = null;
						}
					}
					
					private void «propertyMethodCreate(property)»(CategoryAssignment ca) {
						try {
							BeanCategoryAssignmentFactory beanFactory = new BeanCategoryAssignmentFactory();
							«property.name» = («property.referenceType.name») beanFactory.getInstanceFor(ca);
						} catch (CoreException e) {
							
						}
					}
									
					public «property.referenceType.name» «propertyMethodGet(property)»() {
						«propertyMethodSafeAccess(property)»;
						return «property.name»;
					}
					
					public Command «propertyMethodSet(property)»(EditingDomain ed, «property.referenceType.name» value) {
						ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("«property.name»");
						CategoryAssignment ca = value.getTypeInstance();
						return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, ca);
					}
					
					public void «propertyMethodSet(property)»(«property.referenceType.name» value) {
						ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("«property.name»");
						if (value != null) {
							propertyInstance.setReference(value.getTypeInstance());
						} else {
							propertyInstance.setReference(null);
						}
					}
					'''	
				} else {
					importManager.register(ReferencePropertyInstance);
					importManager.register(CategoryAssignment);
					importManager.register(Command);
					importManager.register(SetCommand);
					importManager.register(EditingDomain);
					importManager.register(PropertyinstancesPackage);
					var referencedProperty = property.referenceType as AProperty;
					var referencedPropertyType = getReferencePropertyType(referencedProperty);
					importManager.register(referencedPropertyType);
					return '''
					private «referencedPropertyType.simpleName» «property.name» = new «referencedPropertyType.simpleName»();

					private void «propertyMethodSafeAccess(property)» {
						ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("«property.name»");
						«property.name».setATypeInstance(propertyInstance.getReference());
					}

					public «referencedPropertyType.name» «propertyMethodGet(property)»Bean() {
						«propertyMethodSafeAccess(property)»;
						return «property.name»;
					}
					
					public Command «propertyMethodSet(property)»Bean(EditingDomain ed, «referencedPropertyType.simpleName» value) {
						ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("«property.name»");
						return SetCommand.create(ed, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, value.getTypeInstance());
					}
					
					public void «propertyMethodSet(property)»Bean(«referencedPropertyType.simpleName» value) {
						ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance("«property.name»");
						propertyInstance.setReference(value.getTypeInstance());
					}
					'''
				}	
			}	
			override caseEReferenceProperty(EReferenceProperty property) {
				importManager.register(CategoryAssignment);
				importManager.register(Command);
				importManager.register(EditingDomain);
				importManager.register(BeanPropertyEReference);
				importManager.register(EReferencePropertyInstance)
				
				val typeClass = new ExternalGenModelHelper().getEObjectClass(property)
				val genPackageSpecified = typeClass !== null
				if(genPackageSpecified) {
					importManager.register(typeClass)
				} else {
					importManager.register(EObject);
				}
				val referenceType = '''«IF genPackageSpecified»«property.referenceType.name»«ELSE»EObject«ENDIF»'''
				
				return '''
				private BeanPropertyEReference<«referenceType»> «property.name» = new BeanPropertyEReference<«referenceType»>();
				
				«declareSafeAccessAttributeMethod(property, EReferencePropertyInstance)»
				
				public Command «propertyMethodSet(property)»(EditingDomain ed, «referenceType» value) {
					«propertyMethodSafeAccess(property)»;
					return this.«property.name».setValue(ed, value);
				}
				
				public void «propertyMethodSet(property)»(«referenceType» value) {
					«propertyMethodSafeAccess(property)»;
					this.«property.name».setValue(value);
				}
				
				public «referenceType» «propertyMethodGet(property)»() {
					«propertyMethodSafeAccess(property)»;
					return «property.name».getValue();
				}
				
				'''	
			}			
		}.doSwitch(property)
	}

	// *************************************************************************************
	// * utils
	// *************************************************************************************
	protected def propertyMethodGet(AProperty property) {
		return "get" + property.name.toFirstUpper;
	}
	
	protected def propertyMethodSet(AProperty property) {
		return "set" + property.name.toFirstUpper;
	}
	
	protected def propertyMethodCreate(AProperty property) {
		return "create" + property.name.toFirstUpper;
	}
	
	protected def propertyMethodIsSet(AProperty property) {
		return "isSet" + property.name.toFirstUpper;
	}
	
	protected def propertyMethodSafeAccess(AProperty property) {
		return "safeAccess" + property.name.toFirstUpper +"()";
	}
	
	
	/**
	 * This method hands back the class type
	 */
	static def getReferencePropertyType(AProperty refProperty) {
		return new PropertydefinitionsSwitch<Class<?>> {
			override caseFloatProperty(FloatProperty object) 		{ return BeanPropertyFloat; }
			override caseBooleanProperty(BooleanProperty object) 	{ return BeanPropertyBoolean; }
			override caseIntProperty(IntProperty object) 			{ return BeanPropertyInt; }
			override caseEnumProperty(EnumProperty object) 			{ return BeanPropertyEnum; }
			override caseStringProperty(StringProperty object) 		{ return BeanPropertyString; }
			override caseResourceProperty(ResourceProperty object) 	{ return BeanPropertyResource; }
		}.doSwitch(refProperty)
	}
}

