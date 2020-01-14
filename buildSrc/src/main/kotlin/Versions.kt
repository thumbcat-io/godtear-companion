object Versions {
    object Android {
        const val buildToolsVersion = "29.0.2"
        const val minSdkVersion = 22
        const val targetSdkVersion = 29
        const val compileSdkVersion = 29
        const val applicationId = "io.thumbcat.oss.godtearcompanion"
        const val versionCode = 1
        const val versionName = "0.1.0"
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    const val kotlin = "1.3.61"

    private const val mokoResources = "0.6.0"
    private const val mokoNetwork = "0.2.0"
    private const val mokoUnits = "0.2.0-dev-2" // temporary dev version

    object Plugins {
        const val gradleBuildScan = "3.1.1"
        const val dokka = "0.10.0"
        const val kotlin = Versions.kotlin
        const val serialization = Versions.kotlin
        const val multiplatform = Versions.kotlin
        const val android = Versions.kotlin
        const val androidExtensions = Versions.kotlin
        const val androidGradle = "3.5.3" // https://developer.android.com/studio/releases/gradle-plugin
        const val mokoResources = Versions.mokoResources
        const val mokoNetwork = Versions.mokoNetwork
        const val mokoUnits = Versions.mokoUnits
    }

    object Libs {
        object Android {
            const val kotlinStdLib = kotlin
            const val coreKtx = "1.1.0"
            const val appCompat = "1.1.0"
            const val material = "1.0.0"
            const val constraintLayout = "1.1.3"
            const val lifecycle = "2.1.0"
            const val testExtJUnit = "1.1.1"
            const val espressoCore = "3.2.0"
            const val recyclerView = "1.1.0"
        }

        object MultiPlatform {
            const val kotlinStdLib = kotlin

            const val coroutines = "1.3.3"
            const val serialization = "0.14.0"
            const val ktorClient = "1.2.6"
            const val ktorClientLogging = ktorClient

            const val mokoParcelize = "0.2.0"
            const val mokoTime = "0.2.0"
            const val mokoGraphics = "0.2.0"
            const val mokoMvvm = "0.4.0"
            const val mokoResources = Versions.mokoResources
            const val mokoNetwork = Versions.mokoNetwork
            const val mokoFields = "0.2.0"
            const val mokoPermissions = "0.2.0"
            const val mokoMedia = "0.2.0"
            const val mokoUnits = Versions.mokoUnits
            const val mokoWidgets = "0.1.0-dev-5"

            const val napier = "1.1.0"
            const val settings = "0.5"
        }
    }
}
