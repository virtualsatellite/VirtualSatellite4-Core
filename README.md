# Virtual Satellite 4 - CORE

Virtual Satellite is a DLR open source software for model based systems engineering (MBSE).

## Project Status

Status [![Build Status](https://travis-ci.org/DLR-SC/Overtarget.svg?branch=development)](https://travis-ci.org/DLR-SC/Overtarget) [![Coverage Status](https://codecov.io/gh/DLR-SC/overtarget/branch/development/graph/badge.svg)](https://codecov.io/gh/DLR-SC/overtarget) [![Download virtualsatellite](https://img.shields.io/sourceforge/dt/overtarget.svg)](https://sourceforge.net/projects/virtualsatellite/files/development/) for *Development* build.

Status [![Build Status](https://travis-ci.org/DLR-SC/Overtarget.svg?branch=integration)](https://travis-ci.org/DLR-SC/Overtarget) [![Coverage Status](https://codecov.io/gh/DLR-SC/Overtarget/branch/integration/graph/badge.svg)](https://codecov.io/gh/DLR-SC/Overtarget) [![Download virtualsatellite](https://img.shields.io/sourceforge/dt/overtarget.svg)](https://sourceforge.net/projects/virtualsatellite/files/integration/) for *Integration* build.

Status [![Build Status](https://travis-ci.org/DLR-SC/Overtarget.svg?branch=master)](https://travis-ci.org/DLR-SC/Overtarget) [![Coverage Status](https://codecov.io/gh/DLR-SC/Overtarget/branch/master/graph/badge.svg)](https://codecov.io/gh/DLR-SC/Overtarget) [![Download virtualsatellite](https://img.shields.io/sourceforge/dt/overtarget.svg)](https://sourceforge.net/projects/virtualsatellite/files/release/) for *Master* build.

## Purpose

Virtual Satellite 4 is the new evolution of MBSE. With a customizable data model it can be tailored to the various needs of individual engineering tasks and project requirements. Rather than the historic approach of trying to create the data model and system engineering language that can handle all possible tasks, the new approach focuses on necessities leading to simple and easy to use applications. The data model of a Virtual Satellite 4 application can be extended by a concept. Such a concept is a set of data model extensions plus functionality to provide corresponding user interfaces and further functionality.

## Requirements 

Virtual Satellite is based on Java / Eclipse and provides an installable feature with plug-ins for your personal eclipse IDE. The following infrastructure is required:
 - Java Development Kit (JDK) 8
 - Eclipse Oxygen or newer
   - Including Checkstyle
   - Including Spotbugs
   - Including M2E
 - Maven 3
 - Windows 7 or Linux Computer

## Quickstart

1. Open your Eclipse and switch to the Git Perspective.
2. Clone this repository.
3. Import all projects and working-sets via the ProjectSet file in _de.dlr.sc.virsat/projectSet_
6. Execute the Virtual Satellite build from _de.dlr.sc.virsat/launchers_
7. Inspect the build artifacts for the p2 repository in _de.dlr.sc.virsat.p2updatesite/target_

## Travis CI and Releases

Tarvis CI is set-up to start a build job for every branch and every new commit to the repository. It executes all relevant tests such as jUnit, SWTBot, Checkstyle, SpotBugs, etc. Making a successful pull-request into development requires all tests to pass.

Starting a Travis CI job on development or integration deploys all relevant artifacts.

For creating a new release, create a tag starting with *Release_* on the *master* branch. All artifacts are automatically deployed.

## Provided Features

- Basic modeling concepts such as product structures, mass budgeting, visualization, etc.
- Core engines such as Calculcation Engine, UI Engine, etc.
- Xtext based Concept IDE Tools for creating new concepts, including code generators

## Downloads and Deployment

Deployments are done using GitHub Releases as well as sourceforge: [![Download virtualsatellite](https://sourceforge.net/sflogo.php?type=13&group_id=3065053)](https://sourceforge.net/projects/virtualsatellite/files/)

## Software Engineering Requirements

This project follows DLR Software Engineering Requirements for application class 1 as documented in the [Checklist](se_checklist_app_class_1.md).
 
## Contribution

To contribute to this project follow the given steps:

1. Create your own fork of the project.
2. Apply your changes.
3. Create a pull-request of your change to our development branch.

To increase chance that we accept your pull-request, make sure all tests are working. The best indicator is the Travis CI job. Next we will review your pull-request, give comments and maybe accept it.

## License

Copyright 2019 German Aerospace Center (DLR)

The German Aerospace Center (DLR) makes available all content in this plug-in ("Content").  Unless otherwise indicated below, the Content is provided to you under the terms and conditions of the Eclipse Public License Version 2.0 ("EPL").  A copy of the EPL is available at https://www.eclipse.org/legal/epl-2.0. For purposes of the EPL, "Program" will mean the Content.

If you did not receive this Content directly from German Aerospace Center (DLR), the Content is being redistributed by another party ("Redistributor") and different terms and conditions may apply to your use of any object code in the Content.  Check the Redistributor's license that was provided with the Content.  If no such license exists, contact the Redistributor.  Unless otherwise indicated below, the terms and conditions of the EPL still apply to this content.<p>

## Third Party Licenses

Third party licenses are named in the plug-ins where used in the respective _aboutfiles_ folders. At runtime third party licenses can be viewed in the usual Eclipse About dialog. Also check out the [Notice](NOTICE.md) file.
