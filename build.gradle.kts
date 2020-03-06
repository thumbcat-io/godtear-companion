allprojects {
    repositories {
        mavenLocal()
        google()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven(url = "https://dl.bintray.com/icerockdev/moko")
        maven(url = "https://dl.bintray.com/aakira/maven")
    }

    // workaround for https://youtrack.jetbrains.com/issue/KT-27170
    configurations.create("compileClasspath")
}

tasks.register("clean", Delete::class) {
    group = "build"
    description = "cleans only the rootProject buildDir"
    delete(rootProject.buildDir)
}
