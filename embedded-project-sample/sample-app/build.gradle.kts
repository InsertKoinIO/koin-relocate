plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
}


allprojects {

    val kotzillaVersion: String by project

    group = "io.kotzilla"
    version = kotzillaVersion
}