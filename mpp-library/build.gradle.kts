plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("android.extensions")
//    kotlin("plugin.serialization")
    id("dev.icerock.mobile.multiplatform")
    id("dev.icerock.mobile.multiplatform-resources")
    id("org.jetbrains.dokka")
}

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)
    buildToolsVersion = Versions.Android.buildToolsVersion
    defaultConfig {
        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)
        testInstrumentationRunner = Versions.Android.testInstrumentationRunner
    }
}

val mppLibs = listOf(
    Deps.Libs.MultiPlatform.settings,
    Deps.Libs.MultiPlatform.napier,
    Deps.Libs.MultiPlatform.mokoParcelize,
    Deps.Libs.MultiPlatform.mokoResources,
    Deps.Libs.MultiPlatform.mokoMvvm,
    Deps.Libs.MultiPlatform.mokoUnits,
    Deps.Libs.MultiPlatform.mokoWidgets
)
val mppModules = listOf(
    Modules.MultiPlatform.domain,
    Modules.MultiPlatform.Feature.cohortsExplorer
)

setupFramework(
    exports = mppLibs + mppModules
)

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)
//    mppLibrary(Deps.Libs.MultiPlatform.serialization)

    mppLibs.forEach { mppLibrary(it) }
    mppModules.forEach { mppModule(it) }
}

multiplatformResources {
    multiplatformResourcesPackage = "io.thumbcat.oss.gtcompanion.mpp"
}

// dependencies graph generator
apply(from = "https://raw.githubusercontent.com/JakeWharton/SdkSearch/master/gradle/projectDependencyGraph.gradle")
