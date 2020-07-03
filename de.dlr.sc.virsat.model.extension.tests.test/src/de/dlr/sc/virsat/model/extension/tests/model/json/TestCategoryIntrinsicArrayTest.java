/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model.json;

import static org.junit.Assert.assertEquals;
import static de.dlr.sc.virsat.model.extension.tests.test.TestActivator.assertEqualsNoWs;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.TestClass;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesFactoryImpl;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.ATestCategoryIntrinsicArrayTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanA;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArray;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryIntrinsicArrayTest extends ATestCategoryIntrinsicArrayTest {

	private JAXBUtility jaxbUtility;
	private BeanPropertyString beanPropertyString;
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryIntrinsicArray.class});
		
		// Create a test string bean
		ValuePropertyInstance vpi = PropertyinstancesFactoryImpl.eINSTANCE.createValuePropertyInstance();
		vpi.setValue("testString");
		beanPropertyString = new BeanPropertyString(vpi);
		beanPropertyString.getATypeInstance().setUuid(new VirSatUuid("d7ed5b62-f096-4347-88ef-eae61c5f166b"));
	}
	
//	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		TestCategoryIntrinsicArray testArray = new TestCategoryIntrinsicArray(concept);
		testArray.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		
		// TODO: static
//		testArray.getTestStringArrayStatic().get(0).getATypeInstance().setUuid(new VirSatUuid("4efe0002-f081-49c0-9917-6f4a6e7dd9ce"));
//		testArray.getTestStringArrayStatic().get(0).getATypeInstance().setUuid(new VirSatUuid("6ad3d35a-a0b4-48e8-9bfd-e6edf438eee5"));
//		testArray.getTestStringArrayStatic().get(0).getATypeInstance().setUuid(new VirSatUuid("8fd96e3b-5bf3-41e1-a02a-64f8bff99107"));
//		testArray.getTestStringArrayStatic().get(0).getATypeInstance().setUuid(new VirSatUuid("c38d7185-fcc3-480c-bfb4-28e6fcc09d34"));
		
		// Add it to the lists
//		testArray.getTestStringArrayStatic().set(0, beanPropertyString);
		
		testArray.getTestStringArrayDynamic().add(beanPropertyString);
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(testArray, sw);
		
		System.out.println(sw.toString());
		
		String expectedJson = TestActivator.getResourceContentAsString("/resources/json/TestCategoryIntrinsicArray_Marshaling.json");
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
	@Test
	public void testJsonUnMarshalling() throws JAXBException, IOException {
		TestCategoryIntrinsicArray testArray = new TestCategoryIntrinsicArray(concept);
		testArray.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(testArray.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString("/resources/json/TestCategoryIntrinsicArray_Marshaling.json");
		System.out.println(inputJson);
		StringReader sr = new StringReader(inputJson);

		TestCategoryIntrinsicArray createdArray = jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryIntrinsicArray.class).getValue();
		assertEquals(testArray, createdArray);
	}
	
//	@XmlRootElement(name = "employee")
//	@XmlAccessorType (XmlAccessType.FIELD)
//	public static class Employee 
//	{
//		private Integer id;
//		private String firstName;
//		private String lastName;
//		private double income;
//		public Integer getId() {
//			return id;
//		}
//		public void setId(Integer id) {
//			this.id = id;
//		}
//		public String getFirstName() {
//			return firstName;
//		}
//		public void setFirstName(String firstName) {
//			this.firstName = firstName;
//		}
//		public String getLastName() {
//			return lastName;
//		}
//		public void setLastName(String lastName) {
//			this.lastName = lastName;
//		}
//		public double getIncome() {
//			return income;
//		}
//		public void setIncome(double income) {
//			this.income = income;
//		}
//	}
//	
//	@XmlRootElement(name = "employees")
//	@XmlAccessorType (XmlAccessType.FIELD)
//	public static class Employees 
//	{
//	    @XmlElement(name = "employee")
//	    private List<Employee> employees = null;
//	 
//	    public List<Employee> getEmployees() {
//	        return employees;
//	    }
//	 
//	    public void setEmployees(List<Employee> employees) {
//	        this.employees = employees;
//	    }
//	}
//	
//	@Test
//	public void testListInObj() throws JAXBException {
//		jaxbUtility = new JAXBUtility();
//		jaxbUtility.getProperties().put(JAXBContextProperties.MEDIA_TYPE, "application/json");
//		jaxbUtility.getProperties().put(JAXBContextProperties.JSON_INCLUDE_ROOT, true);
//		jaxbUtility.getProperties().put(JAXBContextProperties.JSON_WRAPPER_AS_ARRAY_NAME, true);
//		JAXBContext context = jaxbUtility.createContext(new Class[] {Employees.class});
//		
//		Employees employees = new Employees();
//		employees.setEmployees(new ArrayList<Employee>());
//	    //Create two employees 
//	    Employee emp1 = new Employee();
//	    emp1.setId(1);
//	    emp1.setFirstName("Lokesh");
//	    emp1.setLastName("Gupta");
//	    emp1.setIncome(100.0);
//	     
//	    Employee emp2 = new Employee();
//	    emp2.setId(2);
//	    emp2.setFirstName("John");
//	    emp2.setLastName("Mclane");
//	    emp2.setIncome(200.0);
//	     
//	    //Add the employees in list
//	    employees.getEmployees().add(emp1);
//	    employees.getEmployees().add(emp2);
//		
//		assertEquals(employees.getEmployees().size(), 2);
//		
//		Marshaller jsonMarshaller = context.createMarshaller();
//		StringWriter sw = new StringWriter();
//		jsonMarshaller.marshal(employees, sw);
//		
//		String json = sw.toString();
//		System.out.println(json);
//		
//		Unmarshaller jsonUnmarshaller = context.createUnmarshaller();
//		StringReader sr = new StringReader(json);
//
//		Employees createdCls = jsonUnmarshaller.unmarshal(new StreamSource(sr), Employees.class).getValue();
//		assertEquals(employees.getEmployees().get(1).firstName, createdCls.getEmployees().get(1).firstName);
//	}
	
}
