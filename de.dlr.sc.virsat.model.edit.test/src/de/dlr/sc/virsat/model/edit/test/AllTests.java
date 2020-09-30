/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.edit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.model.concept.calculation.QualifiedEquationObjectHelperTest;
import de.dlr.sc.virsat.model.concept.lists.TypeSafeComposedPropertyBeanListTest;
import de.dlr.sc.virsat.model.concept.lists.TypeSafeEReferenceArrayInstanceListTest;
import de.dlr.sc.virsat.model.concept.lists.TypeSafeReferencePropertyBeanListTest;
import de.dlr.sc.virsat.model.concept.migrator.AMigratorTest;
import de.dlr.sc.virsat.model.concept.migrator.ConceptMigrationHelperTest;
import de.dlr.sc.virsat.model.concept.migrator.ConceptMigratorTest;
import de.dlr.sc.virsat.model.concept.migrator.CreateMigrateConceptToLatestCommandTest;
import de.dlr.sc.virsat.model.concept.types.category.BeanCategoryAssignmentTest;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBooleanTest;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReferenceTest;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnumTest;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFactoryTest;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloatTest;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyIntTest;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResourceTest;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyStringTest;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstanceTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.DVLMEnumValueDefinitionItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.DVLMPropertydefinitionsItemProviderAdapterFactoryTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.ArrayInstanceItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMComposedPropertyInstanceItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMERefererencePropertyInstanceItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMEnumUnitPropertyInstanceItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMPropertyinstancesItemProviderAdapterFactoryTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMRefererencePropertyInstanceItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMResourcePropertyInstanceItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMUnitValuePropertyInstanceItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMValuePropertyInstanceItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceHelperTest;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactoryTest;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoryAssignmentItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoryItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.command.SetArrayInstanceCommandTest;
import de.dlr.sc.virsat.model.dvlm.command.SetValuePropertyInstanceCommandTest;
import de.dlr.sc.virsat.model.dvlm.command.UndoableAddCommandTest;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElementTest;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapterTest;
import de.dlr.sc.virsat.model.dvlm.json.ABeanStructuralElementInstanceAdapterTest;
import de.dlr.sc.virsat.model.dvlm.json.AnyTypeAdapterTest;
import de.dlr.sc.virsat.model.dvlm.json.DoubleAdapterTest;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapterTest;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtilityTest;
import de.dlr.sc.virsat.model.dvlm.json.UriAdapterTest;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMRepositoryItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.qudv.provider.DVLMQudvItemProviderAdapterFactoryTest;
import de.dlr.sc.virsat.model.dvlm.qudv.provider.DVLMSystemOfQuantitiesItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvModelCommandFactoryTest;
import de.dlr.sc.virsat.model.dvlm.resource.command.RemoveResourceCommandTest;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagmentCheckCommandTest;
import de.dlr.sc.virsat.model.dvlm.structural.command.CreateAddStructuralElementInstanceCommandTest;
import de.dlr.sc.virsat.model.dvlm.structural.command.DeleteStructuralElementInstanceCommandTest;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStrcuturalItemProviderAdapterFactoryTest;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralElementInstanceItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralElementItemProviderTest;
import de.dlr.sc.virsat.model.dvlm.transactional.util.TransactionalEditingDomainHelperTest;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCommandParameterApplicableForCheckTest;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopiedNameHelperTest;
import de.dlr.sc.virsat.model.dvlm.util.DVLMItemNamingTest;
import de.dlr.sc.virsat.model.dvlm.util.command.VirSatRecordingCommandTest;
import junit.framework.JUnit4TestAdapter;

/**
 *
 */
@RunWith(Suite.class)

@SuiteClasses({
				DVLMCopiedNameHelperTest.class,
				RoleManagmentCheckCommandTest.class,
				DVLMResourceItemProviderTest.class,
				ArrayInstanceItemProviderTest.class,
				QudvModelCommandFactoryTest.class,
				CreateAddStructuralElementInstanceCommandTest.class,
				DVLMCommandParameterApplicableForCheckTest.class,
				ActiveConceptConfigurationElementTest.class,
				BeanPropertyResourceTest.class,
				BeanPropertyIntTest.class,
				BeanPropertyFloatTest.class,
				BeanPropertyStringTest.class,
				BeanPropertyBooleanTest.class,
				BeanPropertyEnumTest.class,
				BeanPropertyEReferenceTest.class,
				BeanPropertyFactoryTest.class,
				BeanCategoryAssignmentTest.class,
				TransactionalEditingDomainHelperTest.class,
				BeanStructuralElementInstanceTest.class,
				DVLMItemNamingTest.class,
				DVLMCategoriesItemProviderAdapterFactoryTest.class,
				DVLMCategoryAssignmentItemProviderTest.class,
				DVLMStructuralElementInstanceItemProviderTest.class,
				DVLMStrcuturalItemProviderAdapterFactoryTest.class,
				DVLMCategoryItemProviderTest.class,
				DVLMStructuralElementItemProviderTest.class,
				DVLMSystemOfQuantitiesItemProviderTest.class,
				DVLMQudvItemProviderAdapterFactoryTest.class,
				DVLMPropertyinstancesItemProviderAdapterFactoryTest.class,
				DVLMPropertydefinitionsItemProviderAdapterFactoryTest.class,
				DVLMEnumValueDefinitionItemProviderTest.class,
				DVLMEnumUnitPropertyInstanceItemProviderTest.class,
				DVLMComposedPropertyInstanceItemProviderTest.class,
				DVLMRefererencePropertyInstanceItemProviderTest.class,
				DVLMERefererencePropertyInstanceItemProviderTest.class,
				DVLMResourcePropertyInstanceItemProviderTest.class,
				DVLMUnitValuePropertyInstanceItemProviderTest.class,
				DVLMValuePropertyInstanceItemProviderTest.class,
				ConceptMigrationHelperTest.class,
				ConceptMigratorTest.class,
				RemoveResourceCommandTest.class,
				DeleteStructuralElementInstanceCommandTest.class,
				UndoableAddCommandTest.class,
				DVLMRepositoryItemProviderTest.class,
				VirSatRecordingCommandTest.class,
				AMigratorTest.class,
				CreateMigrateConceptToLatestCommandTest.class,
				QualifiedEquationObjectHelperTest.class,
				SetArrayInstanceCommandTest.class,
				SetValuePropertyInstanceCommandTest.class,
				PropertyInstanceHelperTest.class,
				TypeSafeEReferenceArrayInstanceListTest.class,
				TypeSafeReferencePropertyBeanListTest.class,
				TypeSafeComposedPropertyBeanListTest.class,
				ABeanObjectAdapterTest.class,
				AnyTypeAdapterTest.class,
				DoubleAdapterTest.class,
				JAXBUtilityTest.class,
				IUuidAdapterTest.class,
				UriAdapterTest.class,
				ABeanStructuralElementInstanceAdapterTest.class
				})

/**
 *
 * Test Collection
 *
 */
public class AllTests {

	/**
	 * Constructor
	 */
	private AllTests() {
	}

	/**
	 * Test Adapter
	 * @return Executable JUnit Tests
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}
}