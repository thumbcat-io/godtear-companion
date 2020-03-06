import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("dev.icerock.mobile.multiplatform")
    kotlin("plugin.serialization")
    id("de.mannodermaus.android-junit5")
    id("org.jmailen.kotlinter")
}

android {
    compileSdkVersion(Versions.compile_sdk)
    defaultConfig {
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)
        versionCode = 1
        versionName = "0.1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument("runnerBuilder", "de.mannodermaus.junit5.AndroidJUnit5Builder")
        vectorDrawables.useSupportLibrary = true
    }
    // JUnit 5 will bundle in files with identical paths; exclude them
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
        exclude("META-INF/LICENSE*")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dataBinding {
        isEnabled = true
    }
    dexOptions {
        javaMaxHeapSize = "2g"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
        }
    }
}

kotlin {
    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    version = "0.1.0"

    sourceSets["androidTest"].dependencies {
        implementation(Deps.AndroidXTest.runner)
        implementation(Deps.AndroidXTest.junit)
        implementation(Deps.junit_jupiter_api)
        implementation(Deps.AndroidXTest.core_junit5)
        implementation(Deps.KotlinTest.junit)
        implementation(Deps.KotlinTest.annotations)
        implementation(Deps.KotlinTest.reflect)
        runtimeOnly(Deps.junit_vintage_engine)
        runtimeOnly(Deps.AndroidXTest.runner_junit5)
        implementation("org.robolectric:robolectric:4.0")
    }
}

val mppLibs = listOf(
    Deps.Libs.MultiPlatform.settings,
    Deps.Libs.MultiPlatform.napier,
    Deps.Libs.MultiPlatform.moko_mvvm,
    Deps.Libs.MultiPlatform.stately,
    Deps.Libs.MultiPlatform.sql_delight_driver,
    Deps.Libs.MultiPlatform.sql_delight_runtime,
    Deps.Libs.MultiPlatform.koin
)

val mppModules = listOf(
    Modules.MultiPlatform.domain,
    Modules.MultiPlatform.app,
    Modules.MultiPlatform.Infrastructure.persistence
)

setupFramework(
    exports = mppLibs + mppModules
)

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlin_std_lib)
    mppLibrary(Deps.Libs.MultiPlatform.kotlin_reflect)
    mppLibraryConfigured(Deps.Libs.MultiPlatform.coroutines) {
        version {
            strictly("1.3.3-native-mt")
        }
    }
    mppLibrary(Deps.Libs.MultiPlatform.serialization)

    androidLibrary(Deps.Libs.Android.lifecycle_viewmodel)

    mppLibs.forEach { mppLibrary(it) }
    mppModules.forEach { mppModule(it) }
}

val iOSTest: Task by tasks.creating {
    val device = project.findProperty("iosDevice")?.toString() ?: "iPhone 8"
    dependsOn("linkDebugTestIos")
    group = JavaBasePlugin.VERIFICATION_GROUP
    description = "Runs tests for target 'ios' on an iOS simulator"

    doLast {
        val binary =
            kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getTest(
                "DEBUG"
            ).outputFile
        exec {
            commandLine("xcrun", "simctl", "spawn", "--standalone", device, binary.absolutePath)
        }
    }
}

// dependencies graph generator
apply(from = "https://raw.githubusercontent.com/JakeWharton/SdkSearch/master/gradle/projectDependencyGraph.gradle")
