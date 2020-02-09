package io.thumbcat.oss.gtcompanion.mpp.domain

import kotlinx.serialization.Serializable

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list

fun cohorts(): List<Cohort> =
    Json(JsonConfiguration.Stable).parse(
        Cohort.serializer().list, """
            [
                {
                    "category": {
                        "key": "Guardian",
                        "name": "Guardian"
                    },
                    "champion": {
                        "name": "Finvarr",
                        "key": "Finvarr"
                    },
                    "follower": {
                        "key": "ShadowSentinels",
                        "name": "Shadow Sentinels",
                        "count": 3,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Guardian",
                        "name": "Guardian"
                    },
                    "champion": {
                        "name": "Halftusk",
                        "key": "Halftusk"
                    },
                    "follower": {
                        "key": "Froglodytes",
                        "name": "Froglodytes",
                        "count": 3,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Guardian",
                        "name": "Guardian"
                    },
                    "champion": {
                        "name": "Mournblade",
                        "key": "Mournblade"
                    },
                    "follower": {
                        "key": "Knightshades",
                        "name": "Knightshades",
                        "count": 3,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Guardian",
                        "name": "Guardian"
                    },
                    "champion": {
                        "name": "Rhodri",
                        "key": "Rhodri"
                    },
                    "follower": {
                        "key": "HouseholdGuard",
                        "name": "Household Guard",
                        "count": 4,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Maelstrom",
                        "name": "Maelstrom"
                    },
                    "champion": {
                        "name": "Blackjaw",
                        "key": "Blackjaw"
                    },
                    "follower": {
                        "key": "UnburntReavers",
                        "name": "Unburnt Reavers",
                        "count": 5,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Maelstrom",
                        "name": "Maelstrom"
                    },
                    "champion": {
                        "name": "Grimgut",
                        "key": "Grimgut"
                    },
                    "follower": {
                        "key": "Retchlings",
                        "name": "Retchlings",
                        "count": 5,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Maelstrom",
                        "name": "Maelstrom"
                    },
                    "champion": {
                        "name": "Titus",
                        "key": "Titus"
                    },
                    "follower": {
                        "key": "GlorySeekers",
                        "name": "Glory Seekers",
                        "count": 5,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Shaper",
                        "name": "Shaper"
                    },
                    "champion": {
                        "name": "Nia",
                        "key": "Nia"
                    },
                    "follower": {
                        "key": "Quartzlings",
                        "name": "Quartzlings",
                        "count": 3,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Shaper",
                        "name": "Shaper"
                    },
                    "champion": {
                        "name": "Raith'Marid",
                        "key": "RaithMarid"
                    },
                    "follower": {
                        "key": "Splashlings",
                        "name": "Splashlings",
                        "count": 3,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Shaper",
                        "name": "Shaper"
                    },
                    "champion": {
                        "name": "Rattlebone",
                        "key": "Rattlebone"
                    },
                    "follower": {
                        "key": "Hexlings",
                        "name": "Hexlings",
                        "count": 5,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Shaper",
                        "name": "Shaper"
                    },
                    "champion": {
                        "name": "Shayle",
                        "key": "Shayle"
                    },
                    "follower": {
                        "key": "Landslide",
                        "name": "Landslide",
                        "count": 1,
                        "size": "large"
                    }
                },
                {
                    "category": {
                        "key": "Slayer",
                        "name": "Slayer"
                    },
                    "champion": {
                        "name": "Lorsann",
                        "key": "Lorsann"
                    },
                    "follower": {
                        "key": "MistwoodRangers",
                        "name": "Mistwood Rangers",
                        "count": 3,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Slayer",
                        "name": "Slayer"
                    },
                    "champion": {
                        "name": "Rangosh",
                        "key": "Rangosh"
                    },
                    "follower": {
                        "key": "RedBandits",
                        "name": "Red Bandits",
                        "count": 5,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Slayer",
                        "name": "Slayer"
                    },
                    "champion": {
                        "name": "Sneaky Peet",
                        "key": "SneakyPeet"
                    },
                    "follower": {
                        "key": "SneakyStabbers",
                        "name": "Sneaky Stabbers",
                        "count": 3,
                        "size": "small"
                    }
                },
                {
                    "category": {
                        "key": "Slayer",
                        "name": "Slayer"
                    },
                    "champion": {
                        "name": "Morrigan",
                        "key": "Morrigan"
                    },
                    "follower": {
                        "key": "ColdBones",
                        "name": "Cold Bones",
                        "count": 5,
                        "size": "small"
                    }
                }
            ]
    """.trimIndent())

@Serializable
data class Cohort(
    val champion: Champion,
    val follower: Follower,
    val category: Category
) {
    @Serializable
    data class Category(
        val key: String,
        val name: String
    )
}

@Serializable
data class Follower(
    val key: String,
    val name: String,
    val count: Int,
    val size: Size
) {
    @Serializable
    enum class Size {
        small, large
    }
}

@Serializable
data class Champion(
    val key: String,
    val name: String
)