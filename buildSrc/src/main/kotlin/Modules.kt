object Modules {
    object MultiPlatform {
        val domain = MultiPlatformModule(
            name = ":mpp-library:domain",
            exported = false
        )

        val app = MultiPlatformModule(
            name = ":mpp-library:app",
            exported = false
        )

        object Infrastructure {
            val persistence = MultiPlatformModule(
                name = ":mpp-library:infra:persistence",
                exported = false
            )
        }
    }
}
