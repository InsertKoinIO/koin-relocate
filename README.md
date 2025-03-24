# Koin Relocation Scripts

Those scripts help to rebuild & package Koin project with a different name. The interest is for <b>SDK & Library development</b>, to avoid conflict between embedded Koin version and any consuming application.

Example of Koin embeded version: [Kotzilla Repository](https://repository.kotzilla.io/#browse/browse:Koin-Embedded)

```kotlin
maven { 'https://repository.kotzilla.io/repository/kotzilla-platform/' }
```

Contact us: [Koin Team](mailto:koin@kotzilla.io)

# Setup & Run

## Setup
Open `relocate.properties` file to specify:
- RELOCATION_PREFIX - Prefix to relocate Koin package. "embedded", will transform `org.koin.*` to `embedded.koin.*`
- TARGET_KOIN_VERSION - Koin version tag to use
- KOIN_MODULES - list of Koin modules to rename with prefix. I.e `koin-core` will become `embeded-koin-core`
- BUILD_DIR - folder where Koin built artifacts are copied

## Run the Script

> Requirements: JDK 17 environment

Just run `./relocate.sh`. The output build will be in `./build` or $BUILD_DIR

## Get the Jar/Aar

Once executed, open the `./build` or $BUILD_DIR folder to get your aar/jar artifacts, to add to your project

# Project Sample

The `embedded-project-sample` folder contains sample for Koin embedded SDK Library.

- sample-library - Android library project, using Koin embedded version in 3.5.6
- sample-app - Android Application using Koin 4.0.2 and sample-library

## Install Library

from `embedded-project-sample/sample-library` run `install.sh`

## Run App & Library

from `embedded-project-sample/sample-app`, run the Android app
