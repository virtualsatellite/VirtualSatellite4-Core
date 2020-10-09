# Virtual Satellite 4 - CORE

Virtual Satellite is a DLR open source software for model based systems engineering (MBSE).

## Project Status

Status [![Build Status](https://travis-ci.com/virtualsatellite/VirtualSatellite4-Core.svg?branch=development)](https://travis-ci.com/virtualsatellite/VirtualSatellite4-Core) [![Coverage Status](https://codecov.io/gh/virtualsatellite/VirtualSatellite4-Core/branch/development/graph/badge.svg)](https://codecov.io/gh/virtualsatellite/VirtualSatellite4-Core) [![Download virtualsatellite](https://img.shields.io/sourceforge/dt/virtualsatellite.svg)](https://sourceforge.net/projects/virtualsatellite/files/development/) for *Development* build.

Status [![Build Status](https://travis-ci.com/virtualsatellite/VirtualSatellite4-Core.svg?branch=integration)](https://travis-ci.com/virtualsatellite/VirtualSatellite4-Core) [![Coverage Status](https://codecov.io/gh/virtualsatellite/VirtualSatellite4-Core/branch/integration/graph/badge.svg)](https://codecov.io/gh/virtualsatellite/VirtualSatellite4-Core) [![Download virtualsatellite](https://img.shields.io/sourceforge/dt/virtualsatellite.svg)](https://sourceforge.net/projects/virtualsatellite/files/integration/) for *Integration* build.

Status [![Build Status](https://travis-ci.com/virtualsatellite/VirtualSatellite4-Core.svg?branch=master)](https://travis-ci.com/virtualsatellite/VirtualSatellite4-Core) [![Coverage Status](https://codecov.io/gh/virtualsatellite/VirtualSatellite4-Core/branch/master/graph/badge.svg)](https://codecov.io/gh/virtualsatellite/VirtualSatellite4-Core) [![Download virtualsatellite](https://img.shields.io/sourceforge/dt/virtualsatellite.svg)](https://sourceforge.net/projects/virtualsatellite/files/release/) for *Master* build.

## Purpose

Virtual Satellite 4 is the new evolution of MBSE. With a customizable data model it can be tailored to the various needs of individual engineering tasks and project requirements. Rather than the historic approach of trying to create the data model and system engineering language that can handle all possible tasks, the new approach focuses on necessities leading to simple and easy to use applications. The data model of a Virtual Satellite 4 application can be extended by a concept. Such a concept is a set of data model extensions plus functionality to provide corresponding user interfaces and further functionality.

## Requirements 

Virtual Satellite is based on Java / Eclipse and provides an installable feature with plug-ins for your personal eclipse IDE. The following infrastructure is required:
 - Java Development Kit (JDK) 8 - 64 bit
 - Windows 7 or Linux Computer - 64 bit

## Quickstart for users

If you just want to use Virtual Satellite feel free to download it from the [Releases](https://github.com/virtualsatellite/VirtualSatellite4-Core/releases) section here on GitHub.
A manual is provided as PDF in the upcoming distributions from version 4.10.x onwards. The latest changes on the manual is written in ascii doc and can be found here: [de.dlr.sc.virsat.docs.feature/src/docs/VirSat_Core_User_Manual.adoc](de.dlr.sc.virsat.docs.feature/src/docs/VirSat_Core_User_Manual.adoc)

## Quickstart for developers

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

## Contribution

We are happy to receive your contributions. Nevertheless in such a big project there is a lot to respect and to obey. 
One thing to respect are legal requirements such as authorship rights and privacy protection. 
Therefore, before we can accept your contributions we need you to sign our *Contributor License Agreement (CLA)*.
The CLA is provided in *English* language only:

[Contributor License Agreement CLA](cla/20190724_DLR_Individual_Contributor_License_Agreement_Virtual_Satellite_en_v1.2.pdf)

Before you sign it and send it to us, you have to read the privacy policy as well.
The privacy policy is available in *English* and *German (Deutsch)* language: 

To contribute to this project follow the given steps:

[Declaration of consent data processing and privacy policy](cla/20190724_Declaration_of_consent_data_processing_and_privacy_policy_Virtual_Satellite_en_v1.2.pdf)

[Einverständniserklärung Datenverarbeitung und Datenschutz](cla/20190724_Einverständniserklärung_Datenverabreitung_und_Datenschutz_VirtualSatellite_de_v1.2.pdf)

If you agree to the CLA and privacy policy, please fill out the CLA and send it back to us. The detailed process of how to submit the documents is described in the documents themself.

Once you are an authorized committer feel free to contribute. We will not activate your account within our organization. Therefore use Pull-Requests to contribute:

1. Create your own fork of the project.
2. Apply your changes.
3. Make sure you own all relevant rights to make this contribution.
4. Make sure you are obeying legal requirements.
5. Create a pull-request of your change to our development branch.

To increase chance that we accept your pull-request, make sure all tests are working. The best indicator is the Travis CI job. Next we will review your pull-request, give comments and maybe accept it.

## License

Copyright 2019 German Aerospace Center (DLR)

The German Aerospace Center (DLR) makes available all content in this plug-in ("Content").  Unless otherwise indicated below, the Content is provided to you under the terms and conditions of the Eclipse Public License Version 2.0 ("EPL").  A copy of the EPL is available at https://www.eclipse.org/legal/epl-2.0. For purposes of the EPL, "Program" will mean the Content.

If you did not receive this Content directly from German Aerospace Center (DLR), the Content is being redistributed by another party ("Redistributor") and different terms and conditions may apply to your use of any object code in the Content.  Check the Redistributor's license that was provided with the Content.  If no such license exists, contact the Redistributor.  Unless otherwise indicated below, the terms and conditions of the EPL still apply to this content.

## Third Party Licenses

Third party licenses are named in the plug-ins where used in the respective _aboutfiles_ folders. At runtime third party licenses can be viewed in the usual Eclipse About dialog. Also check out the [Notice](NOTICE.md) file.

## Responsible in the sense of § 55 2nd paragraph of Rundfunkstaatsvertrag (German Interstate Treaty on Broadcasting)

Rolf Hempel, Linder Höhe, 51147 Cologne, Germany 

Philipp M. Fischer, 38108 Braunschweig, Germany



The GitHub portal is maintained by

Deutsches Zentrum fuer Luft- und Raumfahrt e. V. (DLR) | German Aerospace Center (DLR)

Institute for Software Technology

Linder Höhe

51147 Koeln

Germany

## Publisher
???

## Liability
DLR cannot be held liable either for mistakes in editorial or technical aspects, nor for omissions, nor for the correctness of the content. In particular, DLR does not guarantee completeness or correctness of information contained in external websites which can be accessed via links from DLR's websites. Despite accurate research on the content of such linked external websites, DLR cannot be held liable for their content. Only the content providers of such external sites are liable for their content. Should you notice any mistake in technical or editorial aspects of the DLR site, please do not hesitate to inform us.

Technical Maintenance
email: dlr-infomaster@dlr.de

## DLR images and video – terms and conditions of use
The rights to all images and videos used on this website are owned by the German Aerospace Center (Deutsches Zentrum für Luft- und Raumfahrt e.V.; DLR), unless otherwise stated.

Where expressly stated, DLR images and videos are covered by a Creative Commons Attribution 3.0 Germany (CC BY 3.0) licence. This licence grants permission to reproduce or distribute the work, to make the work and/or its contents publically available, to alter or edit the work and/or its contents and to make commercial use of the work, provided that you explicitly mention DLR as its source in a clearly legible format. Examples: 'Photo: DLR, CC-BY 3.0', 'Images: DLR, CC-BY 3.0', 'Video: DLR, CC-BY 3.0'.

The licence has no influence on the following rights:
The rights to which everyone is entitled through copyright law or legal permission (in some countries, established as the fundamental doctrine of fair use); the copyright entitlements of the legal owner; rights of other persons, either with regard to the object of the licence itself or in reference to its use, for example the personal rights of photographed persons.

Note: In the event of distribution, you must notify others of all licence conditions applicable to this work. You will find detailed information and a legally binding licence contract here.

Images and videos by DLR not indicated 'CC-BY 3.0' are not licenced under the Creative Commons licence model. Permission of use for these images and videos is granted as follows: They may be used by press and other media, or for personal information or educational purposes. Commercial use is subject to DLR permission.

Certain images (photos) and videos on the DLR web portal originate from other sources. If you wish to use these images or videos, please contact the copyright holder.

Certain images and videos may have multiple copyright holders. In such cases, it is common practice to apply the conditions of use applied by the copyright holder mentioned in the source reference. In cases of doubt, please contact this person or entity. These images and videos are identified accordingly.

The Creative Commons licence does not extend to the DLR logo, its constituent elements or other elements of DLR's corporate visual identity such as the "blue marble". The DLR logo must not be used by persons who are not DLR employees or who have not been granted permission for its use by DLR.

Should you have any questions, please contact:

DLR German Aerospace Center

Public Affairs and Communications Department, Communications Section

Linder Höhe

51147 Cologne

Tel: +49 2203 601-2116


Fax: +49 2203 601-3249
E-mail: bildredaktion@dlr.de


