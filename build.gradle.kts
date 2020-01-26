plugins {
    id("com.android.library") apply false
    id("com.android.application") apply false
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    kotlin("android.extensions") apply false
    kotlin("plugin.serialization") apply false
    id("dev.icerock.mobile.multiplatform") apply false
    id("com.gradle.build-scan")
}

allprojects {
    repositories {
        commonRepositories()
    }

    // workaround for https://youtrack.jetbrains.com/issue/KT-27170
    configurations.create("compileClasspath")
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}