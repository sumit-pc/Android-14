import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

}

kotlin {
    jvmToolchain(17)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

android {
    namespace = AndroidConfig.Namespace
    compileSdk = AndroidConfig.CompileSdk
    buildToolsVersion = AndroidConfig.BuildTools
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConfig.KotlinCompilerExtensionVersion
    }
    defaultConfig {
        applicationId = AndroidConfig.Namespace
        minSdkPreview = AndroidConfig.MinSdk
        targetSdkPreview = AndroidConfig.TargetSdk
        versionCode = 1
        versionName = AndroidConfig.VersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    androidResources {
        generateLocaleConfig = true
    }
}

dependencies {
    implementation(Libraries.kotlinStdlib)
    implementation(Libraries.coreKtx)
    implementation(Libraries.appcompat)
    implementation(Libraries.lifecycle)
    implementation(Libraries.coil)
    implementation(Libraries.navigationCompose)
    implementation(platform(Libraries.composeBom))
    implementation(Libraries.composeActivity)
    implementation(Libraries.composeRuntimeLiveData)
    implementation(Libraries.composeUi)
    implementation(Libraries.composeMaterial3)
    implementation(Libraries.composeUiToolingPreview)
    debugImplementation(Libraries.composeUiTooling)
    testImplementation(Libraries.junit)
}

fun readProperties(propertiesFile: File) = Properties().apply {
    propertiesFile.inputStream().use { load(it) }
}

task("getVersionName") {
    println(AndroidConfig.VersionName)
}
