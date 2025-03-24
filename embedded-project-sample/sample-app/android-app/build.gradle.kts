plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
//    alias(libs.plugins.kotzilla)
}

val androidCompileSDK : String by project
val androidMinSDK : String by project

android {
    compileSdk = androidCompileSDK.toInt()
    defaultConfig {
        minSdk = androidMinSDK.toInt()
        applicationId = "io.kotzilla.sample.androidx"
        namespace = "io.kotzilla.sample.androidx"
        versionCode = 1
        versionName = "1.1-0.13.7-Beta1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = ".debug"
        }
        val release by getting {
            isMinifyEnabled = true
            applicationIdSuffix = ".release"
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {
    implementation(libs.koin4.android)
    implementation(libs.android.fragment)
    implementation(libs.android.appcompat)

//    //Local Binary
    implementation(libs.sample.library)
//    //Dev
//    implementation(project(":android-library"))

}

//kotzilla {
//    displayLogs = true
//    projectFile = "kotzilla-staging.json"
//    site = "https://gateway-staging.kotzilla.io"
//}
