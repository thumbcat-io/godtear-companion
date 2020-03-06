import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.exclude

fun DependencyHandlerScope.mppLibraryConfigured(library: MultiPlatformLibrary, block: ExternalModuleDependency.() -> Unit) {
    library.android?.let { "androidMainImplementation"(it, block) }
    library.common?.let { "commonMainApi"(it, block) }
    library.iosX64?.let { "iosX64MainImplementation"(it, block) }
    library.iosArm64?.let { "iosArm64MainImplementation"(it, block) }
}

object Versions {
    const val min_sdk = 22
    const val target_sdk = 28
    const val compile_sdk = 28

    const val kotlin_version = "1.3.61"
    const val android_x = "1.1.0"
    const val android_x_junit5 = "1.2.0"
    const val build_tools_version = "29.0.0"
    const val sql_delight = "1.2.2"
    const val ktor = "1.2.6"
    const val multiplatform_settings = "0.5"
    const val coroutines = "1.3.3-native-mt"
    const val koin = "3.0.1-khan-SNAPSHOT"
    const val junit5 = "5.6.0"
    const val junit5_vintage = "5.6.0"
    const val serialization = "0.14.0"
    const val moko_mvvm = "0.4.0"

    object Libs {
        object Android {
            const val kotlin_std_lib = kotlin_version
            const val activity = "1.1.0"
            const val ads = "1.0.0-alpha04"
            const val annotation = "1.1.0"
            const val app_compat = "1.1.0"
            const val arch = "2.1.0"
            const val async_layout_inflater = "1.0.0"
            const val autofill = "1.0.0"
            const val benchmark = "1.0.0"
            const val biometric = "1.0.1"
            const val browser = "1.2.0"
            const val camera = "1.0.0-beta01"
            const val car = "1.0.0-alpha7"
            const val card_view = "1.0.0"
            const val collection = "1.1.0"
            const val concurrent = "1.0.0"
            const val constraint_layout = "1.1.3"
            const val content_pager = "1.0.0"
            const val coordinator_layout = "1.1.0"
            const val core = "1.2.0"
            const val cursor_adapter = "1.0.0"
            const val custom_view = "1.0.0"
            const val data_binding = "3.5.0"
            const val document_file = "1.0.1"
            const val drawer_layout = "1.0.0"
            const val dynamic_animation = "1.0.0"
            const val emoji = "1.0.0"
            const val enterprise = "1.0.0"
            const val exif_interface = "1.1.0"
            const val fragment = "1.2.2"
            const val grid_layout = "1.0.0"
            const val heif_writer = "1.0.0"
            const val interpolator = "1.0.0"
            const val jetifier = "1.0.0-beta09"
            const val lean_back = "1.0.0"
            const val legacy = "1.0.0"
            const val lifecycle = "2.2.0"
            const val loader = "1.1.0"
            const val local_broadcast_manager = "1.0.0"
            const val material = "1.1.0"
            const val media = "1.1.0"
            const val media2 = "1.0.3"
            const val media_router = "1.1.0"
            const val multidex = "2.0.1"
            const val navigation = "2.2.1"
            const val paging = "2.1.1"
            const val palette = "1.0.0"
            const val percent_layout = "1.0.0"
            const val preference = "1.1.0"
            const val print = "1.0.0"
            const val recommendation = "1.0.0"
            const val recycler_view = "1.1.0"
            const val remote_callback = "1.0.0-alpha02"
            const val room = "2.2.4"
            const val saved_state = "1.0.0"
            const val security = "1.0.0-alpha02"
            const val share_target = "1.0.0-rc02"
            const val slice = "1.0.0"
            const val sliding_pane_layout = "1.0.0"
            const val sqlite = "2.1.0"
            const val swipe_refresh_layout = "1.0.0"
            const val test = "1.2.0"
            const val text_classifier = "1.0.0-alpha03"
            const val transition = "1.3.1"
            const val tv_provider = "1.0.0"
            const val vector_drawable = "1.1.0"
            const val versioned_parcelable = "1.1.0"
            const val view_pager = "1.0.0"
            const val view_pager2 = "1.0.0"
            const val wear = "1.0.0"
            const val webkit = "1.1.0"
            const val window = "1.0.0-alpha01"
            const val work = "2.3.2"
        }

        object MultiPlatform {
            const val coroutines = "1.3.3-native-mt"
            const val serialization = "0.14.0"
            const val ktor_client = "1.2.6"
            const val ktor_client_logging = ktor_client
            const val moko_mvvm = "0.4.0"
            const val moko_parcelize = "0.2.0"
            const val napier = "1.1.0"
            const val settings = "0.5"
            const val stately = "0.9.5"
            const val stately_collections = stately
            const val sql_delight = "1.2.1"
            const val koin = "3.0.1-khan-SNAPSHOT"
        }
    }
}

object Deps {
    const val junit_jupiter_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    const val junit_jupiter_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    const val junit_jupiter_params = "org.junit.jupiter:junit-jupiter-params:${Versions.junit5}"
    const val junit_vintage_engine = "org.junit.vintage:junit-vintage-engine:${Versions.junit5_vintage}"
    const val app_compat_x = "androidx.appcompat:appcompat:${Versions.Libs.Android.app_compat}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.Libs.Android.core}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.Libs.Android.constraint_layout}"
    const val multiplatform_settings = "com.russhwolf:multiplatform-settings:${Versions.multiplatform_settings}"
    const val koin_core = "co.touchlab:koin-core:${Versions.koin}"

    object AndroidXTest {
        const val core = "androidx.test:core:${Versions.android_x}"
        const val core_junit5 = "de.mannodermaus.junit5:android-test-core:${Versions.android_x_junit5}"
        const val junit = "androidx.test.ext:junit:${Versions.android_x}"
        const val runner = "androidx.test:runner:${Versions.android_x}"
        const val runner_junit5 = "de.mannodermaus.junit5:android-test-runner:${Versions.android_x_junit5}"
        // const val rules = "androidx.test:rules:${Versions.android_x}"
    }

    object KotlinTest {
        const val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin_version}"
        const val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin_version}"
        const val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin_version}"
        const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin_version}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_version}"
    }

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutines}"
        const val jdk = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object SqlDelight {
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sql_delight}"
        const val runtime_jdk = "com.squareup.sqldelight:runtime-jvm:${Versions.sql_delight}"
        const val driver_ios = "com.squareup.sqldelight:ios-driver:${Versions.sql_delight}"
        const val driver_android = "com.squareup.sqldelight:android-driver:${Versions.sql_delight}"
    }

    object Ktor {
        const val common_core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val common_json = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val jvm_core = "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
        const val android_core = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val jvm_json = "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
        const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val ios_core = "io.ktor:ktor-client-core-native:${Versions.ktor}"
        const val ios_json = "io.ktor:ktor-client-json-native:${Versions.ktor}"
        const val common_serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val android_serialization = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
        const val ios_serialization = "io.ktor:ktor-client-serialization-native:${Versions.ktor}"
    }

    val coroutinesExcludeNative: ExternalModuleDependency.() -> Unit = {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-core-native")
    }

    object Libs {
        object Android {
            val kotlin_std_lib = AndroidLibrary(
                name = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Libs.Android.kotlin_std_lib}"
            )
            val kotlin_reflect = AndroidLibrary(
                name = "org.jetbrains.kotlin:kotlin-reflect:${Versions.Libs.Android.kotlin_std_lib}"
            )
            val app_compat = AndroidLibrary(
                name = "androidx.appcompat:appcompat:${Versions.Libs.Android.app_compat}"
            )
            val material = AndroidLibrary(
                name = "com.google.android.material:material:${Versions.Libs.Android.material}"
            )
            val recycler_view = AndroidLibrary(
                name = "androidx.recyclerview:recyclerview:${Versions.Libs.Android.recycler_view}"
            )
            val constraint_layout = AndroidLibrary(
                name = "androidx.constraintlayout:constraintlayout:${Versions.Libs.Android.constraint_layout}"
            )
            val lifecycle = AndroidLibrary(
                name = "androidx.lifecycle:lifecycle-extensions:${Versions.Libs.Android.lifecycle}"
            )
            val lifecycle_viewmodel = AndroidLibrary(
                name = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Libs.Android.lifecycle}"
            )
            val lifecycle_livedata = AndroidLibrary(
                name = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Libs.Android.lifecycle}"
            )
            val lifecycle_viewmodel_savedstate = AndroidLibrary(
                name = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.Libs.Android.lifecycle}"
            )
            val lifecycle_common = AndroidLibrary(
                name = "androidx.lifecycle:lifecycle-common-java8:${Versions.Libs.Android.lifecycle}"
            )
            val lifecycle_livedata_rx = AndroidLibrary(
                name = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.Libs.Android.lifecycle}"
            )
        }

        object MultiPlatform {
            val kotlin_std_lib = MultiPlatformLibrary(
                android = Android.kotlin_std_lib.name,
                common = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin_version}"
            )
            val kotlin_reflect = MultiPlatformLibrary(
                android = Android.kotlin_reflect.name,
                common = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_version}"
            )
            val ktor_client = MultiPlatformLibrary(
                android = "io.ktor:ktor-client-android:${Versions.Libs.MultiPlatform.ktor_client}",
                common = "io.ktor:ktor-client-core:${Versions.Libs.MultiPlatform.ktor_client}",
                ios = "io.ktor:ktor-client-ios:${Versions.Libs.MultiPlatform.ktor_client}"
            )
            val ktor_client_logging = MultiPlatformLibrary(
                android = "io.ktor:ktor-client-logging-jvm:${Versions.Libs.MultiPlatform.ktor_client_logging}",
                common = "io.ktor:ktor-client-logging:${Versions.Libs.MultiPlatform.ktor_client_logging}",
                ios = "io.ktor:ktor-client-logging-native:${Versions.Libs.MultiPlatform.ktor_client_logging}"
            )
            val coroutines = MultiPlatformLibrary(
                android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Libs.MultiPlatform.coroutines}",
                common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.Libs.MultiPlatform.coroutines}",
                ios = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.Libs.MultiPlatform.coroutines}"
            )
            val serialization = MultiPlatformLibrary(
                android = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.Libs.MultiPlatform.serialization}",
                common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.Libs.MultiPlatform.serialization}",
                ios = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.Libs.MultiPlatform.serialization}"
            )
            val moko_parcelize = MultiPlatformLibrary(
                common = "dev.icerock.moko:parcelize:${Versions.Libs.MultiPlatform.moko_parcelize}",
                iosX64 = "dev.icerock.moko:parcelize-iosx64:${Versions.Libs.MultiPlatform.moko_parcelize}",
                iosArm64 = "dev.icerock.moko:parcelize-iosarm64:${Versions.Libs.MultiPlatform.moko_parcelize}"
            )
            val moko_mvvm = MultiPlatformLibrary(
                common = "dev.icerock.moko:mvvm:${Versions.Libs.MultiPlatform.moko_mvvm}",
                iosX64 = "dev.icerock.moko:mvvm-iosx64:${Versions.Libs.MultiPlatform.moko_mvvm}",
                iosArm64 = "dev.icerock.moko:mvvm-iosarm64:${Versions.Libs.MultiPlatform.moko_mvvm}"
            )
            val settings = MultiPlatformLibrary(
                common = "com.russhwolf:multiplatform-settings:${Versions.Libs.MultiPlatform.settings}",
                iosX64 = "com.russhwolf:multiplatform-settings-iosx64:${Versions.Libs.MultiPlatform.settings}",
                iosArm64 = "com.russhwolf:multiplatform-settings-iosarm64:${Versions.Libs.MultiPlatform.settings}"
            )
            val multiplatform_settings_test = MultiPlatformLibrary(
                common = "com.russhwolf:multiplatform-settings-test:${Versions.Libs.MultiPlatform.settings}"
            )
            val napier = MultiPlatformLibrary(
                android = "com.github.aakira:napier-android:${Versions.Libs.MultiPlatform.napier}",
                common = "com.github.aakira:napier:${Versions.Libs.MultiPlatform.napier}",
                ios = "com.github.aakira:napier-ios:${Versions.Libs.MultiPlatform.napier}"
            )
            val stately = MultiPlatformLibrary(
                common = "co.touchlab:stately:${Versions.Libs.MultiPlatform.stately}"
            )
            val stately_collections = MultiPlatformLibrary(
                common = "co.touchlab:stately-collections:${Versions.Libs.MultiPlatform.stately_collections}"
            )
            val sql_delight_driver = MultiPlatformLibrary(
                android = "com.squareup.sqldelight:android-driver:${Versions.Libs.MultiPlatform.sql_delight}",
                ios = "com.squareup.sqldelight:ios-driver:${Versions.Libs.MultiPlatform.sql_delight}"
            )
            val sql_delight_runtime = MultiPlatformLibrary(
                android = "com.squareup.sqldelight:runtime-jvm:${Versions.Libs.MultiPlatform.sql_delight}",
                common = "com.squareup.sqldelight:runtime:${Versions.Libs.MultiPlatform.sql_delight}",
                iosArm64 = "com.squareup.sqldelight:runtime-iosarm64:${Versions.Libs.MultiPlatform.sql_delight}",
                iosX64 = "com.squareup.sqldelight:runtime-iosx64:${Versions.Libs.MultiPlatform.sql_delight}"
            )
            val koin = MultiPlatformLibrary(
                common = "co.touchlab:koin-core:${Versions.Libs.MultiPlatform.koin}"
            )
        }
    }
}
