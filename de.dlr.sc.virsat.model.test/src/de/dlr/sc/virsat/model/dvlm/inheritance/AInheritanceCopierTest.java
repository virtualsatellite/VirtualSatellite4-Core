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

import org.junit.After;
import org.junit.Before;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Abstract class for InheritanceCopier test cases.
 * This abstract class takes care of setting up and tearing down a
 * reasonable test scenario similar to what to be expect with
 * extened product structures.
 *
 */
public abstract class AInheritanceCopierTest {

	protected Repository repo;
	protected Concept concept;
	protected Category catSpec;
	protected Category catIft;
	protected CategoryAssignment caIftMil;
	protected CategoryAssignment caIftCan;
	protected Category catIfe;
	protected AProperty catIfeSn;
	protected Category catIf;
	protected ReferenceProperty catIfIfe1;
	protected ReferenceProperty catIfIfe2;
	protected ReferenceProperty catIfIft;
	protected StructuralElementInstance seiTc;
	protected StructuralElementInstance seiEdSc;
	protected StructuralElementInstance seiEdRw;
	protected StructuralElementInstance seiEdObc;
	protected StructuralElementInstance seiEcSc;
	protected StructuralElementInstance seiEcRwI;
	protected StructuralElementInstance seiEcRwII;
	protected StructuralElementInstance seiEcObc;
	protected StructuralElementInstance seiEo1Sc;
	protected StructuralElementInstance seiEo1RwI;
	protected StructuralElementInstance seiEo1RwII;
	protected StructuralElementInstance seiEo1Obc;
	protected StructuralElementInstance seiEo2Sc;
	protected StructuralElementInstance seiEo2RwI;
	protected StructuralElementInstance seiEo2RwII;
	protected StructuralElementInstance seiEo2Obc;
	protected StructuralElementInstance seiErRwA;
	protected StructuralElement seTc;
	protected StructuralElement seEd;
	protected StructuralElement seEc;
	protected StructuralElement seEo;
	protected StructuralElement seEr;
	protected RoleManagement rm;
	protected Discipline dA;
	protected Discipline dB;

	/**
	 * Builds the PT Test Data
	 */
	private void buildPT() {
		seiEdSc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEdRw = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEdObc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiEdSc.setName("Ed_SC");
		seiEdRw.setName("Ed_RW");
		seiEdObc.setName("Ed_Obc");
	
		seiEdSc.setType(seEd);
		seiEdRw.setType(seEd);
		seiEdObc.setType(seEd);
	
		seiEdSc.getChildren().add(seiEdRw);
		seiEdSc.getChildren().add(seiEdObc);
	}

	/**
	 * Builds the CT Test Data
	 */
	private void buildCT() {
		seiEcSc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEcRwI = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEcRwII = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEcObc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiEcSc.setName("Ec_SC");
		seiEcRwI.setName("Ec_RWI");
		seiEcRwII.setName("Ec_RWII");
		seiEcObc.setName("Ec_Obc");
	
		seiEcSc.setType(seEc);
		seiEcRwI.setType(seEc);
		seiEcRwII.setType(seEc);
		seiEcObc.setType(seEc);
	
		seiEcSc.getChildren().add(seiEcRwI);
		seiEcSc.getChildren().add(seiEcRwII);
		seiEcSc.getChildren().add(seiEcObc);
	
		seiEcSc.getSuperSeis().add(seiEdSc);
		seiEcRwI.getSuperSeis().add(seiEdRw);
		seiEcRwII.getSuperSeis().add(seiEdRw);
		seiEcObc.getSuperSeis().add(seiEdObc);
	}

	/**
	 * Builds the AT Test Data
	 */
	private void buildATs() {
		seiEo1Sc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEo1RwI = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEo1RwII = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEo1Obc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiEo1Sc.setName("Eo1_SC");
		seiEo1RwI.setName("Eo1_RWI");
		seiEo1RwII.setName("Eo1_RWII");
		seiEo1Obc.setName("Eo_1Obc");
	
		seiEo1Sc.setType(seEo);
		seiEo1RwI.setType(seEo);
		seiEo1RwII.setType(seEo);
		seiEo1Obc.setType(seEo);
	
		seiEo1Sc.getChildren().add(seiEo1RwI);
		seiEo1Sc.getChildren().add(seiEo1RwII);
		seiEo1Sc.getChildren().add(seiEo1Obc);
		
		seiEo1Sc.getSuperSeis().add(seiEcSc);
		seiEo1RwI.getSuperSeis().add(seiEcRwI);
		seiEo1RwII.getSuperSeis().add(seiEcRwII);
		seiEo1Obc.getSuperSeis().add(seiEcObc);
	
		// Now construct the StructuralElements to represent the AssemblyTree
		seiEo2Sc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEo2RwI = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEo2RwII = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEo2Obc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiEo2Sc.setName("Eo2_SC");
		seiEo2RwI.setName("Eo2_RWI");
		seiEo2RwII.setName("Eo2_RWII");
		seiEo2Obc.setName("Eo2_Obc");
	
		seiEo2Sc.setType(seEo);
		seiEo2RwI.setType(seEo);
		seiEo2RwII.setType(seEo);
		seiEo2Obc.setType(seEo);
	
		seiEo2Sc.getChildren().add(seiEo2RwI);
		seiEo2Sc.getChildren().add(seiEo2RwII);
		seiEo2Sc.getChildren().add(seiEo2Obc);
		
		seiEo2Sc.getSuperSeis().add(seiEcSc);
		seiEo2RwI.getSuperSeis().add(seiEcRwI);
		seiEo2RwII.getSuperSeis().add(seiEcRwII);
		seiEo2Obc.getSuperSeis().add(seiEcObc);
	}

	@After
	public void tearDown() {
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Before
	public void setUp() throws Exception {
		UserRegistry.getInstance().setSuperUser(true);
		repo = DVLMFactory.eINSTANCE.createRepository();
		
		// Build StructuralElement concept for this test. will consist of Definitions and Configurations
		seTc = StructuralFactory.eINSTANCE.createStructuralElement();
		seEd = StructuralFactory.eINSTANCE.createStructuralElement();
		seEc = StructuralFactory.eINSTANCE.createStructuralElement();
		seEo = StructuralFactory.eINSTANCE.createStructuralElement();
		seEr = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seEd.setName("TypesContainer");
		seEd.setName("Definition");
		seEc.setName("Configuration");
		seEo.setName("Occurrence");
		seEr.setName("Realization");
		
		seEc.getCanInheritFrom().add(seEd);
		seEo.getCanInheritFrom().add(seEc);
		seEo.getCanInheritFrom().add(seEr);
		seEr.getCanInheritFrom().add(seEd);
		
		seEd.getApplicableFor().add(seEd);
		seEc.getApplicableFor().add(seEc);
		seEo.getApplicableFor().add(seEo);
		
		// Now construct the instances for the StructuralElements for the ProductTree
		buildPT();
		
		// Now construct the StructuralElements to represent the ConfigurationTree
		buildCT();
	
		// Now construct the StructuralElements to represent the AssemblyTree
		buildATs();
		
		// Create the Types Container for the InterfaceTypes
		seiTc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiTc.setName("TypesContainer");
		seiTc.setType(seTc);
		
		// Create SEI to represent a Realization 
		seiErRwA = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiErRwA.setName("RwA");
		seiErRwA.setType(seEr);
	
		seiErRwA.getSuperSeis().add(seiEdRw);
		seiEo1RwI.getSuperSeis().add(seiErRwA);
		
		// Build up the Specification Category
		catSpec = CategoriesFactory.eINSTANCE.createCategory();
		catSpec.setName("Specification");
		catSpec.getApplicableFor().add(seEr);
	
		// Build up the InterfaceTypes
		catIft = CategoriesFactory.eINSTANCE.createCategory();
		catIft.setName("InterfaceType");
		catIft.getApplicableFor().add(seTc);
	
		// Build up the InterfaceTypes
		caIftMil = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		caIftMil.setType(catIft);
		caIftMil.setName("Ift_Mil");
		seiTc.getCategoryAssignments().add(caIftMil);
		
		caIftCan = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		caIftCan.setType(catIft);
		caIftCan.setName("Ift_Can");
		seiTc.getCategoryAssignments().add(caIftCan);
		
		// Build up a simple Category for the test case that represents an InterfaceEnd
		catIfe = CategoriesFactory.eINSTANCE.createCategory();
		catIfeSn = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		catIfe.setName("InterfacEnd");
		catIfeSn.setName("SerialNumber");
	
		catIfe.getProperties().add(catIfeSn);
		catIfe.setIsApplicableForAll(true);
		
		// Build up a simple Category for the test case that represents an InterfaceEnd
		catIf = CategoriesFactory.eINSTANCE.createCategory();
		catIfIfe1 = PropertydefinitionsFactory.eINSTANCE.createReferenceProperty();
		catIfIfe2 = PropertydefinitionsFactory.eINSTANCE.createReferenceProperty();
		catIfIft = PropertydefinitionsFactory.eINSTANCE.createReferenceProperty();
		catIf.setName("Interface");
		catIfIfe1.setName("InterfaceEnd1");
		catIfIfe2.setName("InterfaceEnd2");
		catIfIft.setName("InterfaceType");
		
		catIfIfe1.setReferenceType(catIfe);
		catIfIfe2.setReferenceType(catIfe);
		catIfIft.setReferenceType(catIft);
	
		catIf.getProperties().add(catIfIfe1);
		catIf.getProperties().add(catIfIfe2);
		catIf.getProperties().add(catIfIft);
		
		catIf.getApplicableFor().add(seEc);
		catIf.getApplicableFor().add(seEo);
		catIf.getApplicableFor().add(seEr);
		
		repo = DVLMFactory.eINSTANCE.createRepository();
		concept = ConceptsFactory.eINSTANCE.createConcept();
		
		concept.getStructuralElements().add(seEd);
		concept.getStructuralElements().add(seEc);
		concept.getStructuralElements().add(seEr);
		concept.getStructuralElements().add(seEo);
		concept.getStructuralElements().add(seTc);
		
		concept.getCategories().add(catIf);
		concept.getCategories().add(catIft);
		concept.getCategories().add(catIfe);
		concept.getCategories().add(catSpec);
		
		repo.getActiveConcepts().add(concept);
		
		repo.getRootEntities().add(seiTc);
		repo.getRootEntities().add(seiEcSc);
		repo.getRootEntities().add(seiEdSc);
		repo.getRootEntities().add(seiEo1Sc);
		repo.getRootEntities().add(seiEo2Sc);
		repo.getRootEntities().add(seiErRwA);
	}
	
	/**
	 * Use this method to attach a CA to define an interface to a given SEI
	 * @param sei the SEI to attach the IF to
	 * @param instanceName the name of the IF Instance
	 * @param caIfe1 the CA representing the first IFE
	 * @param caIfe2 the CA representing the second IFE
	 * @param caIft the CA representing the Interface Type IFT
	 * @return a CA of the IF that was just added to the SEI
	 */
	public CategoryAssignment attachInterface(StructuralElementInstance sei, String instanceName, CategoryAssignment caIfe1, CategoryAssignment caIfe2, CategoryAssignment caIft) {
		CategoryAssignment caIf = new CategoryInstantiator().generateInstance(catIf, instanceName);
		ReferencePropertyInstance piIfe1 = (ReferencePropertyInstance) caIf.getPropertyInstances().get(0);
		ReferencePropertyInstance piIfe2 = (ReferencePropertyInstance) caIf.getPropertyInstances().get(1);
		ReferencePropertyInstance piIft = (ReferencePropertyInstance) caIf.getPropertyInstances().get(2);
		
		piIfe1.setReference(caIfe1);
		piIfe2.setReference(caIfe2);
		
		piIft.setReference(caIft);
		
		sei.getCategoryAssignments().add(caIf);
		
		return caIf;
	}

	/**
	 * Method to add a CA representing a IFE to the given SEI
	 * @param sei the SEI to add the new IFE to
	 * @param instanceName the Instance name of the IFE to be added
	 * @return A CA representing the just generated IFE
	 */
	public CategoryAssignment attachInterfaceEnd(StructuralElementInstance sei, String instanceName) {
		CategoryAssignment caIfe = new CategoryInstantiator().generateInstance(catIfe, instanceName);
		
		sei.getCategoryAssignments().add(caIfe);
		
		return caIfe;
	}		
	
	/**
	 * Method to attach a Specification to a Realization
	 * @param sei the SEI representing a Realization
	 * @param specName the name of the Specification
	 * @return the CA representing the Specification
	 */
	public CategoryAssignment attachSpecification(StructuralElementInstance sei, String specName) {
		CategoryAssignment caSpec = new CategoryInstantiator().generateInstance(catSpec, specName);
		
		sei.getCategoryAssignments().add(caSpec);
		
		return caSpec;
	}		
	
	/**
	 * Method to set SerialNumber Property of Interface End 
	 * @param ca CA of a InterfaceEnd 
	 * @param serialNumber Property to be set
	 * @return VPI with newly set serialNumber
	 */
	public ValuePropertyInstance setInterfaceEndSn(CategoryAssignment ca, String serialNumber) {
		ValuePropertyInstance vpi = (ValuePropertyInstance) ca.getPropertyInstances().get(0);
		
		vpi.setValue(serialNumber);
		return vpi;
	}
	
	/**
	 * Method to get SerialNumber Property of Interface End 
	 * @param ca CA of a InterfaceEnd 
	 * @return serialNumber to be returned
	 */
	public String getInterfaceEndSn(CategoryAssignment ca) {
		ValuePropertyInstance vpi = (ValuePropertyInstance) ca.getPropertyInstances().get(0);
		
		return vpi.getValue();
	}

}