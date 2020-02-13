---
name: Release
about: Create a Release for a new Version of Virtual Satellite
title: 'Release Version 4.x.x '
labels: release
assignees: ''

---

Virtual Satellite Release Version 4.x.x

This ticket captures all release related work of the VirtualSatellite4-Core release

1. Perform version update:
- [ ] Checkout/Update the Development branch
- [ ] Remove current integration branch (Make sure no one else is integrating at the moment) 
- [ ] Create new integration branch from development branch
- [ ] Run ant script to update version numbers
- [ ] Check if there are unreleased changes on the Ecore data model (open dvlm.ecore, check if History > Release (not yet released is empty))
  - [ ] Create a Edapt release of new changes (View "Operation Browser", click "Release" with "not yet released" selected, set the next version)
  - [ ] Generate model and edit code from Ecore (Open dvlm.genmodel, right click on the context menu)
  - [ ] Adjust DVLM version number in ConceptLanguage.xtext and run GenerateConceptLanguage.mwe2
  - [ ] Adjust DVLM version number in EquationDSL.xtext and run GenerateEquationDSL.mwe2
  - [ ] Regenerate concept.xmi of all relevant projects (launch "VirSat Core Concept IDE", import projects (with nested), build all)
- [ ] Update the Java API Doc being registered in the Java doc plugin
- [ ] Make sure application launches without errors from the product launcher
- [ ] Merge integration branch into development branch (Pull Request named "Integration 4.x.x - Remerge Versions")

2. Perform integration on integration branch:
- [ ] Apply all needed fixes
- [ ] Update the release notes

3. Update master/release branch:
- [ ] Merge integration branch into master branch (Pull Request named "Release 4.x.x")
- [ ] Create Release Tag
 
4. Merge back integration branch:
- [ ] Merge integration branch into development branch (Pull Request named "Integration 4.x.x - Remerge Fixes")
 
Well Done!! You should have a new Virtual Satellite Release :rocket:

