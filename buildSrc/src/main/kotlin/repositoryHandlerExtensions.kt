import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.commonRepositories() {
    google()
    jcenter()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { setUrl("https://kotlin.bintray.com/kotlinx") }
    maven { setUrl("https://dl.bintray.com/jetbrains/kotlin-native-dependencies") }
    maven { setUrl("https://kotlin.bintray.com/ktor") }
    maven { setUrl("https://dl.bintray.com/icerockdev/moko") }
    maven { setUrl("https://dl.bintray.com/aakira/maven") }
    maven { setUrl("http://dl.bintray.com/lukaville/maven") }
}
