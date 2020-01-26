@file:Suppress("UnstableApiUsage")

rootProject.name = "GTCompanion"

enableFeaturePreview("GRADLE_METADATA")

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            val module = Deps.Plugins.moduleByPluginId[requested.id.id] ?: return@eachPlugin
            useModule(module)
        }
    }
    repositories {
        jcenter()
        google()

        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
        maven { url = uri("https://dl.bintray.com/jetbrains/kotlin-native-dependencies") }
        maven { url = uri("https://dl.bintray.com/icerockdev/plugins") }

        gradlePluginPortal()
    }
}

include(":app")
include(":mpp-library")

listOf(
    Modules.MultiPlatform.domain,
    Modules.MultiPlatform.Feature.cohortsExplorer,
    Modules.MultiPlatform.Feature.diceProbability
).forEach { include(it.name) }