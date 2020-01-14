object Modules {
    object MultiPlatform {
        val domain = MultiPlatformModule(
            name = ":mpp-library:domain",
            exported = true
        )

        object Feature {
            val cohortsExplorer = MultiPlatformModule(
                name = ":mpp-library:feature:cohorts-explorer",
                exported = true
            )
        }
    }
}
