plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("dev.icerock.mobile.multiplatform")
    id("dev.icerock.mobile.multiplatform-resources")
}

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)
    buildToolsVersion = Versions.Android.buildToolsVersion
    defaultConfig {
        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)
        testInstrumentationRunner = Versions.Android.testInstrumentationRunner
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
        exclude("META-INF/*.md")
    }
}

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)
    mppLibrary(Deps.Libs.MultiPlatform.serialization)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClient)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClientLogging)

    mppLibrary(Deps.Libs.MultiPlatform.mokoParcelize)
    mppLibrary(Deps.Libs.MultiPlatform.mokoNetwork)
    mppLibrary(Deps.Libs.MultiPlatform.mokoResources)

    mppLibrary(Deps.Libs.MultiPlatform.settings)
    mppLibrary(Deps.Libs.MultiPlatform.napier)

    androidTestImplementation(Deps.Libs.Android.testRunner.name)
    androidTestUtil(Deps.Libs.Android.testOrchestrator.name)
    commonTestImplementation(kotlin("test-common"))
    commonTestImplementation(kotlin("test-annotations-common"))
    commonTestImplementation(kotlin("test-junit5"))
}

multiplatformResources {
    multiplatformResourcesPackage = "io.thumbcat.oss.gtcompanion.mpp.domain"
}

val generateMRTasks = listOf(
    "generateMRandroidMain",
    "generateMRcommonMain",
    "generateMRiosArm64Main",
    "generateMRiosX64Main"
)

val compileKotlinIosArm64: Task by tasks.getting {
    dependsOn(generateMRTasks)
}

val compileKotlinIosX64: Task by tasks.getting {
    dependsOn(generateMRTasks)
}

tasks.register("iosTest")  {
    val  device = project.findProperty("iosDevice") as? String ?: "iPhone 11 Pro"
    dependsOn("linkDebugTestIosX64", "generateMRAll")
    group = JavaBasePlugin.VERIFICATION_GROUP
    description = "Runs tests for target 'iosX64' on an iOS simulator"

    doLast {
        val  binary = (kotlin.targets["iosX64"] as org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget).binaries.getTest("DEBUG").outputFile
        exec {
            commandLine("xcrun", "simctl", "spawn", "--standalone", device, binary.absolutePath)
        }
    }
}