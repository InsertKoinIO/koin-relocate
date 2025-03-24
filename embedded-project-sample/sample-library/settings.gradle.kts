enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
//        mavenLocal()
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
//        mavenLocal()
        maven {
            name = "kotzilla"
            url = uri("https://repository.kotzilla.io/repository/Koin-Embedded/")
        }
        google()
        mavenCentral()
    }
}

include(
    ":android-library",
)