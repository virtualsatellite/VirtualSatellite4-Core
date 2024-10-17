---
name: Release - HotFix
about: Create a HotFix - Release for an existing Version of Virtual Satellite
title: 'Release Version 4.x.x '
labels: release
assignees: ''

---

Virtual Satellite Release Version 4.xx.x - HotFix

This ticket captures all release related work of VirtualSatellite4-Core 

1. Create LTS Branches if not existing:
- [ ] Checkout/Update the `Release_4.xx.x` tag
- [ ] Create new `master_lts/Release_4.xx.x` if it does not exist

2. Create Branches (optional), in case several changes need to be hot fixed:
- [ ] Remove old `integration` branch
- [ ] Create new `integration` branch and checkout if necessary or do the following steps with your `hotfix/task_123_branch`

3. apply all fixes
 
4. Update master/release branch:
- [ ] Merge `hotfix/task_123_branch`or `integration` branch into `master_lts` branch (Pull Request named "Release 4.x.x- HotFixes")

5. Update Versions
- [ ] Run ant script to update version numbers (open build.xml and start the updateVersions task)
- [ ] Check if the server APIs had changes and update the version numbers in the servlets if necessary
- [ ] Check if there are unreleased changes on the Ecore data model (open dvlm.ecore, check if History > Release (not yet released is empty))
  - [ ] Create a Edapt release of new changes (Open the DVLM-Ecore model; open the view "Operation Browser", click "Release" with "not yet released" selected, set the next version)
  - [ ] Generate model and edit code from Ecore (Open dvlm.genmodel, right click on the context menu)
  - [ ] Adjust DVLM version number in factory overwrite: in the plugin.xml of the de.dlr.sc.virsat.model project. (Extension point: org.eclipse.emf.ecore.factory_override)
  - [ ] Adjust DVLM version number in ConceptLanguage.xtext and run GenerateConceptLanguage.mwe2 
  - [ ] Adjust DVLM version number in EquationDSL.xtext and run GenerateEquationDSL.mwe2
  - [ ] Regenerate concept.xmi of all relevant projects (launch "VirSat Core Concept IDE", import projects (with nested), build all)
- [ ] Check that Java API Doc is working in our Concept-IDE - If not update the Java API Doc being registered in the Java doc plugin
    - To check if it works, create a VirSat App and test if the Java API and Model APi from VirSat is backed by JavaDoc
- [ ] Make sure application launches without errors from the product launcher
- [ ] Merge `hotfix/task_123_branch`or `integration` branch into `master_lts` branch (Pull Request named "Release 4.x.x")
- [ ] Create GitHub Release including a Tag on  `master_lts` branch. Name it "Release_4.xx.x".
 
6. Merge back integration branch:
- [ ] Merge `integration` or `hotfix` branch into `development` branch (Pull Request named "Integration 4.x.x - Remerge Fixes")
 
Well Done!! You should have a HotFix Virtual Satellite Release :rocket:

