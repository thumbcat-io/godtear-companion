plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)
    buildToolsVersion = Versions.Android.buildToolsVersion
    defaultConfig {
        applicationId = Versions.Android.applicationId
        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)
        versionCode = Versions.Android.versionCode
        versionName = Versions.Android.versionName
        testInstrumentationRunner = Versions.Android.testInstrumentationRunner
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
    }
//    compileOptions {
//        targetCompatibility = JavaVersion.VERSION_1_8
//        sourceCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        withGroovyBuilder {
//            setProperty("jvmTarget", "1.8")
//        }
//    }
    sourceSets {
        getByName("main") {
            setRoot("src/main")
            java.srcDirs("src/main/kotlin")
        }
        getByName("test") {
            setRoot("src/test")
            java.srcDirs("src/test/kotlin")
        }
    }
    packagingOptions {
        pickFirst("META-INF/kotlinx-serialization-runtime.kotlin_module")
    }
}

//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation(fileTree("libs") { include("*.jar") })
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
