# Koin Relocatino Scripts

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