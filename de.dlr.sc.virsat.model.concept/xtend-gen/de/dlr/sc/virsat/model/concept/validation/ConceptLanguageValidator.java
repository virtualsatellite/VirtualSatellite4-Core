/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.validation;

import de.dlr.sc.virsat.model.concept.validation.AbstractConceptLanguageValidator;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IIntrinsicTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import java.util.ArrayList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

/**
 * Class for simple checks that don't require special environment setup.
 * Unit tests code uses this validator
 */
@SuppressWarnings("all")
public class ConceptLanguageValidator extends AbstractConceptLanguageValidator {
  public static final String DUPLICATE_PROPERTY_NAME = "duplicatePropertyName";
  
  public static final String DUPLICATE_CATEGORY_NAME = "duplicateCategoryName";
  
  public static final String DEFAULT_VALUE_ON_ARRAY_INFO = "defaultValueForArrayInfo";
  
  @Check
  public void CheckDuplicateNameInScope(final Concept concept) {
    EList<Category> categories = concept.getCategories();
    ArrayList<String> categoryNames = CollectionLiterals.<String>newArrayList();
    for (final Category category : categories) {
      {
        String categoryName = category.getName();
        boolean _contains = categoryNames.contains(categoryName);
        if (_contains) {
          this.warning(("Concept cannot have Categories with equal name! See: " + categoryName), 
            ConceptsPackage.Literals.CONCEPT__CATEGORIES, 
            ConceptLanguageValidator.DUPLICATE_CATEGORY_NAME);
        } else {
          categoryNames.add(categoryName);
        }
      }
    }
  }
  
  @Check
  public void CheckDuplicateNameInScope(final Category category) {
    EList<AProperty> properties = category.getAllProperties();
    ArrayList<String> propertyNames = CollectionLiterals.<String>newArrayList();
    for (final AProperty property : properties) {
      {
        String propertyName = property.getName();
        boolean _contains = propertyNames.contains(propertyName);
        if (_contains) {
          this.warning(("Categories cannot have Properties with equal name! See: " + propertyName), 
            CategoriesPackage.Literals.CATEGORY__PROPERTIES, 
            ConceptLanguageValidator.DUPLICATE_PROPERTY_NAME);
        } else {
          propertyNames.add(propertyName);
        }
      }
    }
  }
  
  @Check
  public void InfoOnDefaultValueForArrayProperties(final AProperty property) {
    if ((property instanceof IIntrinsicTypeProperty)) {
      if (((property.getArrayModifier() != null) && (((IIntrinsicTypeProperty)property).getDefaultValue() != null))) {
        this.info("The default value will be applied to all array elements", 
          PropertydefinitionsPackage.Literals.IINTRINSIC_TYPE_PROPERTY__DEFAULT_VALUE, 
          ConceptLanguageValidator.DEFAULT_VALUE_ON_ARRAY_INFO);
      }
    }
  }
}
