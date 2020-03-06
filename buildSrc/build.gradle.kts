plugins {
    `kotlin-dsl`
}

private object Versions {
    const val kotlin_version = "1.3.61"
    // https://github.com/Kotlin/dokka
    const val dokka = "0.10.1"
    // https://github.com/jeremymailen/kotlinter-gradle
    const val kotlinter = "2.3.0"
    const val android = "3.5.3"
    const val sql_delight = "1.2.1"
    const val xcodesync = "0.2"
    const val android_junit5 = "1.5.2.0"
    const val mobile_multiplatform = "0.5.3"
}

repositories {
    mavenLocal()
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/dokka")
    maven(url = "https://kotlin.bintray.com/kotlinx")
    maven(url = "https://plugins.gradle.org/m2/") // required
    maven(url = "https://dl.bintray.com/icerockdev/plugins")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}")
    implementation("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin_version}")
    implementation("com.android.tools.build:gradle:${Versions.android}")
    implementation("com.squareup.sqldelight:gradle-plugin:${Versions.sql_delight}")
    implementation("co.touchlab:kotlinxcodesync:${Versions.xcodesync}")
    implementation("org.jmailen.gradle:kotlinter-gradle:${Versions.kotlinter}")
    implementation("de.mannodermaus.gradle.plugins:android-junit5:${Versions.android_junit5}")
    implementation("dev.icerock:mobile-multiplatform:${Versions.mobile_multiplatform}")
}

kotlinDslPluginOptions {
    // Set to false to silence the warning about the kotlin-dsl plugin enabling Kotlin compiler experimental features.
    experimentalWarning.set(false)
}
