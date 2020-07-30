/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.dmf;

import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.concept.generator.ereference.ExternalGenModelHelper;
import de.dlr.sc.virsat.model.concept.resources.ConceptResourceLoader;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumPropertyHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.importer.ecore.EcoreImporter;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

/**
 * This generator generates an eCore model with eClasses out of described categories
 */
@SuppressWarnings("all")
public class GenerateDmfCategories {
  public static final String GENMODEL_TYPE_ECORE_EXTENSION = "genmodel";
  
  public static final String MODEL_TYPE_ECORE_EXTENSION = "ecore";
  
  public static final String MODEL_CONCEPT_ECORE_FILENAME = ("concept." + GenerateDmfCategories.MODEL_TYPE_ECORE_EXTENSION);
  
  public static final String GENMODEL_CONCEPT_ECORE_FILENAME = ("concept." + GenerateDmfCategories.GENMODEL_TYPE_ECORE_EXTENSION);
  
  public static final String MODEL_DVLM_ECORE_URI = "/de.dlr.sc.virsat.model/model/dvlm.ecore";
  
  public static final String MODEL_DMF_DOBJECT_NAME = "DObject";
  
  public static final String MODEL_NS_PREFIX_PREFIX = "dmf_";
  
  private EClass dvlmDObject;
  
  private ResourceSet ecoreModelResourceSet;
  
  private Resource targetResource;
  
  private Resource dvlmResource;
  
  private String platformPluginUriStringForEcoreModel;
  
  private Set<EPackage> eReferenceEPackages = new HashSet<EPackage>();
  
  /**
   * This method serialized the data model into the given format
   * @param fileNameExtension the extension of the target format to serialize to. Can be either XMI or XML for the  moment
   * @param dataModel access to the root model object
   * @param fsa access to the local file system
   */
  public void serializeModel(final Concept dataModel, final IFileSystemAccess fsa) {
    try {
      boolean _isDMF = dataModel.isDMF();
      boolean _not = (!_isDMF);
      if (_not) {
        return;
      }
      final Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
      Map<String, Object> _extensionToFactoryMap = resourceRegistry.getExtensionToFactoryMap();
      EcoreResourceFactoryImpl _ecoreResourceFactoryImpl = new EcoreResourceFactoryImpl();
      _extensionToFactoryMap.put(GenerateDmfCategories.MODEL_TYPE_ECORE_EXTENSION, _ecoreResourceFactoryImpl);
      Map<String, Object> _extensionToFactoryMap_1 = resourceRegistry.getExtensionToFactoryMap();
      EcoreResourceFactoryImpl _ecoreResourceFactoryImpl_1 = new EcoreResourceFactoryImpl();
      _extensionToFactoryMap_1.put(GenerateDmfCategories.GENMODEL_TYPE_ECORE_EXTENSION, _ecoreResourceFactoryImpl_1);
      try {
        this.initResources(dataModel);
        final EPackage ePackage = this.createEPackageFromConcept(dataModel);
        this.createCategoryEClassesInEPackage(dataModel, ePackage);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        this.targetResource.save(outputStream, null);
        fsa.generateFile(GenerateDmfCategories.MODEL_CONCEPT_ECORE_FILENAME, ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_CONCEPT, outputStream.toString());
        final EcoreImporter ecoreImporter = new EcoreImporter();
        final BasicMonitor monitor = new BasicMonitor();
        ecoreImporter.setGenModelFileName(GenerateDmfCategories.GENMODEL_CONCEPT_ECORE_FILENAME);
        String _name = dataModel.getName();
        String _plus = ("/" + _name);
        String _plus_1 = (_plus + "/concept/");
        Path _path = new Path(_plus_1);
        ecoreImporter.setGenModelContainerPath(_path);
        ecoreImporter.addGenModelToResource(true);
        ecoreImporter.setModelLocation(this.platformPluginUriStringForEcoreModel);
        EList<Resource> _resources = this.ecoreModelResourceSet.getResources();
        for (final Resource resource : _resources) {
          int _size = resource.getContents().size();
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            EObject _get = resource.getContents().get(0);
            final EPackage resourceEPackage = ((EPackage) _get);
            List<EPackage> _ePackages = ecoreImporter.getEPackages();
            _ePackages.add(resourceEPackage);
          }
        }
        final Consumer<EPackage> _function = (EPackage it) -> {
          ecoreImporter.getEPackages().add(it);
        };
        this.eReferenceEPackages.forEach(_function);
        ecoreImporter.adjustEPackages(monitor);
        final EPackage genEPackage = ecoreImporter.getEPackages().get(0);
        ModelConverter.EPackageConvertInfo _ePackageConvertInfo = ecoreImporter.getEPackageConvertInfo(genEPackage);
        _ePackageConvertInfo.setConvert(true);
        final List<GenModel> genModels = ecoreImporter.getExternalGenModels();
        final Consumer<GenModel> _function_1 = (GenModel it) -> {
          final Consumer<GenPackage> _function_2 = (GenPackage it_1) -> {
            if (((it_1.getBasePackage() != null) && (!it_1.getBasePackage().equals(dataModel.getName())))) {
              List<GenPackage> _referencedGenPackages = ecoreImporter.getReferencedGenPackages();
              _referencedGenPackages.add(it_1);
              ModelConverter.ReferencedGenPackageConvertInfo _referenceGenPackageConvertInfo = ecoreImporter.getReferenceGenPackageConvertInfo(it_1);
              _referenceGenPackageConvertInfo.setValidReference(true);
            }
          };
          it.getGenPackages().forEach(_function_2);
        };
        genModels.forEach(_function_1);
        ecoreImporter.prepareGenModelAndEPackages(monitor);
        GenPackage _get_1 = ecoreImporter.getGenModel().getGenPackages().get(0);
        _get_1.setBasePackage(dataModel.getName());
        GenModel _genModel = ecoreImporter.getGenModel();
        String _modelDirectory = ecoreImporter.getGenModel().getModelDirectory();
        String _plus_2 = (_modelDirectory + "-dmf");
        _genModel.setModelDirectory(_plus_2);
        GenModel _genModel_1 = ecoreImporter.getGenModel();
        String _editDirectory = ecoreImporter.getGenModel().getEditDirectory();
        String _plus_3 = (_editDirectory + "-dmf");
        _genModel_1.setEditDirectory(_plus_3);
        GenModel _genModel_2 = ecoreImporter.getGenModel();
        String _editorDirectory = ecoreImporter.getGenModel().getEditorDirectory();
        String _plus_4 = (_editorDirectory + "-dmf");
        _genModel_2.setEditorDirectory(_plus_4);
        GenModel _genModel_3 = ecoreImporter.getGenModel();
        String _testsDirectory = ecoreImporter.getGenModel().getTestsDirectory();
        String _plus_5 = (_testsDirectory + "-dmf");
        _genModel_3.setTestsDirectory(_plus_5);
        GenModel _genModel_4 = ecoreImporter.getGenModel();
        _genModel_4.setCopyrightText(
          "Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.\r\n\r\nThis program and the accompanying materials are made available under the\r\nterms of the Eclipse Public License 2.0 which is available at\r\nhttp://www.eclipse.org/legal/epl-2.0.\r\n\r\nSPDX-License-Identifier: EPL-2.0");
        final Consumer<GenPackage> _function_2 = (GenPackage it) -> {
          it.setResource(GenResourceKind.XMI_LITERAL);
        };
        ecoreImporter.getGenModel().getGenPackages().forEach(_function_2);
        ecoreImporter.saveGenModelAndEPackages(monitor);
        ecoreImporter.getGenModel().reconcile();
        GenModel _genModel_5 = ecoreImporter.getGenModel();
        _genModel_5.setCanGenerate(true);
        final Generator generator = GenModelUtil.createGenerator(ecoreImporter.getGenModel());
        generator.generate(ecoreImporter.getGenModel(), GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, monitor);
        generator.generate(ecoreImporter.getGenModel(), GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, monitor);
        this.ecoreModelResourceSet.getResources().clear();
      } catch (final Throwable _t) {
        if (_t instanceof IOException) {
          final IOException e = (IOException)_t;
          throw new RuntimeException(e);
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * This method creates the Ecore EPackage from a concept where all categories will
   * be stored in
   * @param coneptModel The concept Model that describes all categories and their properties
   */
  public EPackage createEPackageFromConcept(final Concept conceptModel) {
    String conceptName = conceptModel.getName();
    conceptName = VirSatEcoreUtil.getNameOfFqn(conceptName);
    final EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName(conceptName);
    ePackage.setNsPrefix((GenerateDmfCategories.MODEL_NS_PREFIX_PREFIX + conceptName));
    ePackage.setNsURI(ActiveConceptHelper.getDmfNsUriForConcept(conceptModel));
    this.targetResource.getContents().add(ePackage);
    return ePackage;
  }
  
  /**
   * This method creates the classes corresponding to categories
   * @param conceptModel the concept model which contains all categories
   * @param ePackage the ePackage in which the category based eclasses should be stored
   */
  public void createCategoryEClassesInEPackage(final Concept conceptModel, final EPackage ePackage) {
    final Consumer<Category> _function = (Category it) -> {
      final EClass catEClass = EcoreFactory.eINSTANCE.createEClass();
      EList<EClassifier> _eClassifiers = ePackage.getEClassifiers();
      _eClassifiers.add(catEClass);
      catEClass.setName(it.getName());
      catEClass.setAbstract(it.isIsAbstract());
      final Consumer<AProperty> _function_1 = (AProperty it_1) -> {
        final EStructuralFeature propEStructuralFeature = new PropertydefinitionsSwitch<EStructuralFeature>() {
          @Override
          public EStructuralFeature caseStringProperty(final StringProperty object) {
            final EAttribute attribute = EcoreFactory.eINSTANCE.createEAttribute();
            attribute.setEType(EcorePackage.Literals.ESTRING);
            return attribute;
          }
          
          @Override
          public EStructuralFeature caseFloatProperty(final FloatProperty object) {
            final EAttribute attribute = EcoreFactory.eINSTANCE.createEAttribute();
            attribute.setEType(EcorePackage.Literals.EDOUBLE);
            return attribute;
          }
          
          @Override
          public EStructuralFeature caseIntProperty(final IntProperty object) {
            final EAttribute attribute = EcoreFactory.eINSTANCE.createEAttribute();
            attribute.setEType(EcorePackage.Literals.EINT);
            return attribute;
          }
          
          @Override
          public EStructuralFeature caseEnumProperty(final EnumProperty object) {
            final EAttribute attribute = EcoreFactory.eINSTANCE.createEAttribute();
            final EEnum eEnumType = GenerateDmfCategories.this.createEEnum(object);
            attribute.setEType(eEnumType);
            EList<EClassifier> _eClassifiers = ePackage.getEClassifiers();
            _eClassifiers.add(eEnumType);
            return attribute;
          }
          
          @Override
          public EStructuralFeature caseBooleanProperty(final BooleanProperty object) {
            final EAttribute attribute = EcoreFactory.eINSTANCE.createEAttribute();
            attribute.setEType(EcorePackage.Literals.EBOOLEAN);
            return attribute;
          }
          
          @Override
          public EStructuralFeature caseResourceProperty(final ResourceProperty object) {
            final EAttribute attribute = EcoreFactory.eINSTANCE.createEAttribute();
            attribute.setEType(EcorePackage.Literals.ESTRING);
            return attribute;
          }
          
          @Override
          public EStructuralFeature caseComposedProperty(final ComposedProperty cp) {
            final EReference eReference = EcoreFactory.eINSTANCE.createEReference();
            eReference.setContainment(true);
            return eReference;
          }
          
          @Override
          public EStructuralFeature caseReferenceProperty(final ReferenceProperty rp) {
            ATypeDefinition _referenceType = rp.getReferenceType();
            if ((_referenceType instanceof AProperty)) {
              return null;
            }
            final EReference eReference = EcoreFactory.eINSTANCE.createEReference();
            return eReference;
          }
          
          @Override
          public EStructuralFeature caseEReferenceProperty(final EReferenceProperty object) {
            final EReference eReference = EcoreFactory.eINSTANCE.createEReference();
            eReference.setEType(object.getReferenceType());
            EObject _eContainer = eReference.getEType().eContainer();
            GenerateDmfCategories.this.eReferenceEPackages.add(((EPackage) _eContainer));
            new ExternalGenModelHelper().resolveGenPackage(object);
            return eReference;
          }
        }.doSwitch(it_1);
        if ((propEStructuralFeature == null)) {
          return;
        }
        propEStructuralFeature.setName(it_1.getName());
        EList<EStructuralFeature> _eStructuralFeatures = catEClass.getEStructuralFeatures();
        _eStructuralFeatures.add(propEStructuralFeature);
        if (((it_1.getArrayModifier() != null) && (it_1.getArrayModifier() instanceof StaticArrayModifier))) {
          IArrayModifier _arrayModifier = it_1.getArrayModifier();
          final StaticArrayModifier sam = ((StaticArrayModifier) _arrayModifier);
          propEStructuralFeature.setLowerBound(0);
          propEStructuralFeature.setUpperBound(sam.getArraySize());
        } else {
          if (((it_1.getArrayModifier() != null) && (it_1.getArrayModifier() instanceof DynamicArrayModifier))) {
            propEStructuralFeature.setLowerBound(0);
            propEStructuralFeature.setUpperBound((-1));
          } else {
            propEStructuralFeature.setLowerBound(0);
            propEStructuralFeature.setUpperBound(1);
          }
        }
      };
      it.getProperties().forEach(_function_1);
    };
    conceptModel.getCategories().forEach(_function);
    final Consumer<Category> _function_1 = (Category it) -> {
      EClassifier _eClassifier = ePackage.getEClassifier(it.getName());
      final EClass catEClass = ((EClass) _eClassifier);
      Category _extendsCategory = it.getExtendsCategory();
      boolean _tripleNotEquals = (_extendsCategory != null);
      if (_tripleNotEquals) {
        final EClass referencedEClass = this.findTypeDefinitionInEcoreResource(it.getExtendsCategory());
        EList<EClass> _eSuperTypes = catEClass.getESuperTypes();
        _eSuperTypes.add(referencedEClass);
      }
      boolean _isEmpty = catEClass.getESuperTypes().isEmpty();
      if (_isEmpty) {
        EList<EClass> _eSuperTypes_1 = catEClass.getESuperTypes();
        _eSuperTypes_1.add(this.dvlmDObject);
      }
      final Consumer<AProperty> _function_2 = (AProperty it_1) -> {
        new PropertydefinitionsSwitch<EStructuralFeature>() {
          @Override
          public EStructuralFeature caseComposedProperty(final ComposedProperty cp) {
            final EClass referencedEClass = GenerateDmfCategories.this.findTypeDefinitionInEcoreResource(cp.getType());
            EStructuralFeature _eStructuralFeature = catEClass.getEStructuralFeature(it_1.getName());
            final EReference eReference = ((EReference) _eStructuralFeature);
            eReference.setEType(referencedEClass);
            eReference.setContainment(true);
            return eReference;
          }
          
          @Override
          public EStructuralFeature caseReferenceProperty(final ReferenceProperty rp) {
            final EClass referencedEClass = GenerateDmfCategories.this.findTypeDefinitionInEcoreResource(rp.getReferenceType());
            EStructuralFeature _eStructuralFeature = catEClass.getEStructuralFeature(it_1.getName());
            final EReference eReference = ((EReference) _eStructuralFeature);
            if ((eReference != null)) {
              eReference.setEType(referencedEClass);
            }
            return eReference;
          }
        }.doSwitch(it_1);
      };
      it.getProperties().forEach(_function_2);
    };
    conceptModel.getCategories().forEach(_function_1);
  }
  
  /**
   * This method creates and EEnum Class out of the information of
   * the EnumProperty
   */
  public EEnum createEEnum(final EnumProperty property) {
    final EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
    eEnum.setName(new EnumPropertyHelper().getDmfEEnumName(property));
    final Consumer<EnumValueDefinition> _function = (EnumValueDefinition it) -> {
      final EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
      eEnumLiteral.setName(it.getName());
      eEnumLiteral.setLiteral(it.getName());
      double _parseDouble = Double.parseDouble(it.getValue());
      eEnumLiteral.setValue(((int) _parseDouble));
      EList<EEnumLiteral> _eLiterals = eEnum.getELiterals();
      _eLiterals.add(eEnumLiteral);
    };
    property.getValues().forEach(_function);
    return eEnum;
  }
  
  /**
   * This method initializes the resource set by creating the target resource
   * and getting DVLM Ecore model and the DObject.
   */
  public EClass initResources(final Concept conceptModel) {
    EClass _xblockexpression = null;
    {
      ResourceSetImpl _resourceSetImpl = new ResourceSetImpl();
      this.ecoreModelResourceSet = _resourceSetImpl;
      String _name = conceptModel.getName();
      String _plus = ("/" + _name);
      String _plus_1 = (_plus + "/concept/");
      String _plus_2 = (_plus_1 + GenerateDmfCategories.MODEL_CONCEPT_ECORE_FILENAME);
      this.platformPluginUriStringForEcoreModel = _plus_2;
      this.targetResource = this.ecoreModelResourceSet.createResource(URI.createPlatformResourceURI(this.platformPluginUriStringForEcoreModel, true));
      final URI dvlmUri = URI.createPlatformPluginURI(GenerateDmfCategories.MODEL_DVLM_ECORE_URI, true);
      this.dvlmResource = this.ecoreModelResourceSet.getResource(dvlmUri, true);
      _xblockexpression = this.dvlmDObject = this.getDObjectFromDvlm(this.dvlmResource);
    }
    return _xblockexpression;
  }
  
  /**
   * Gets the Ecore model resource set
   */
  public ResourceSet getEcoreModelResourceSet() {
    return this.ecoreModelResourceSet;
  }
  
  /**
   * This method finds a property in its Ecore representation rather than in the .concept xText based
   * file. This is needed to create correct references in one Ecore based category model to another one
   */
  private EClass findTypeDefinitionInEcoreResource(final ATypeDefinition ap) {
    EObject _get = ap.eResource().getContents().get(0);
    Concept concept = ((Concept) _get);
    URI ecoreUri = ConceptResourceLoader.getInstance().getConceptDMFResourceUri(concept.getName());
    if ((ecoreUri == null)) {
      final URI rpUri = ap.eResource().getURI();
      final String ecorePath = rpUri.toString().replace(".concept", ".ecore").replace(".xmi", ".ecore");
      ecoreUri = URI.createURI(ecorePath);
    }
    Resource ecoreResource = this.ecoreModelResourceSet.getResource(ecoreUri, true);
    final Function1<Object, Boolean> _function = (Object it) -> {
      if ((it instanceof EClass)) {
        final EClass eClass = ((EClass) it);
        boolean _equals = eClass.getName().equals(ap.getName());
        if (_equals) {
          return Boolean.valueOf(true);
        }
      }
      return Boolean.valueOf(false);
    };
    Object _findFirst = IteratorExtensions.<Object>findFirst(EcoreUtil.<Object>getAllContents(ecoreResource, true), _function);
    final EClass referencedEClass = ((EClass) _findFirst);
    return referencedEClass;
  }
  
  /**
   * This method hands back the DObject from the DVLM Ecore which
   * is the base class for all implementations of the category eClasses
   */
  private EClass getDObjectFromDvlm(final Resource dvlmResource) {
    final Function1<Object, Boolean> _function = (Object it) -> {
      if ((it instanceof EClass)) {
        final EClass eClass = ((EClass) it);
        boolean _equals = eClass.getName().equals(GenerateDmfCategories.MODEL_DMF_DOBJECT_NAME);
        if (_equals) {
          return Boolean.valueOf(true);
        }
      }
      return Boolean.valueOf(false);
    };
    Object _findFirst = IteratorExtensions.<Object>findFirst(EcoreUtil.<Object>getAllContents(dvlmResource, true), _function);
    return ((EClass) _findFirst);
  }
}
