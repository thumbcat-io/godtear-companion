import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("org.jmailen.kotlinter")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdkVersion(Versions.compile_sdk)
    buildToolsVersion = Versions.build_tools_version
    defaultConfig {
        applicationId = "io.thumbcat.oss.gtcompanion"
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)
        versionCode = 1
        versionName = "0.1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument("runnerBuilder", "de.mannodermaus.junit5.AndroidJUnit5Builder")
        vectorDrawables.useSupportLibrary = true
    }
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
        exclude("META-INF/LICENSE*")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
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
}

dependencies {
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
    implementation(project(":mpp-library"))
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation(Deps.app_compat_x)
    implementation(Deps.core_ktx)
    implementation(Deps.Ktor.android_core)
    implementation(Deps.constraint_layout)
    implementation(Deps.SqlDelight.runtime_jdk)
    implementation(Deps.SqlDelight.driver_android)
    implementation(Deps.Coroutines.jdk)
    implementation(Deps.Coroutines.android)
    implementation(Deps.multiplatform_settings)
    implementation(Deps.koin_core)
    testImplementation(Deps.AndroidXTest.runner)
    testImplementation(Deps.AndroidXTest.junit)
    testImplementation(Deps.junit_jupiter_api)
    testImplementation(Deps.AndroidXTest.core_junit5)
    testImplementation(Deps.KotlinTest.junit)
    testImplementation(Deps.KotlinTest.annotations)
    testImplementation(Deps.KotlinTest.reflect)
    androidTestRuntimeOnly(Deps.junit_vintage_engine)
    androidTestRuntimeOnly(Deps.AndroidXTest.runner_junit5)
}

// dependencies graph generator
apply(from = "https://raw.githubusercontent.com/JakeWharton/SdkSearch/master/gradle/projectDependencyGraph.gradle")
