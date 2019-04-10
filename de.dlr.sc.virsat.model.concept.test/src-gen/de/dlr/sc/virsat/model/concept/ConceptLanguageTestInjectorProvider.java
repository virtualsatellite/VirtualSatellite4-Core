package de.dlr.sc.virsat.model.concept;

import com.google.inject.Injector;

public class ConceptLanguageTestInjectorProvider extends ConceptLanguageInjectorProvider {

	@Override
	protected Injector internalCreateInjector() {
		//Our test setup for just core validators
	    return new ConceptLanguageStandaloneTestSetup().createInjectorAndDoEMFRegistration();
	}	
}
