package io.thumbcat.oss.gtcompanion.mpp.domain.api

import io.thumbcat.oss.gtcompanion.mpp.domain.entity.Cohort
import io.thumbcat.oss.gtcompanion.mpp.domain.entity.SerializableCohort
import io.thumbcat.oss.gtcompanion.mpp.domain.entity.toDomain
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

interface CohortsApi {
    suspend fun fetchCohorts(version: String): List<Cohort>
    suspend fun getCurrentVersion(): String
}

class HardPackagedJsonCohortsApi internal constructor(
    private val json: Json
) : CohortsApi {

    companion object {
        const val CURRENT_VERSION: String = "1.0.0"
    }

    private val cohortsJson: String = """
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
    """.trimIndent()

    override suspend fun fetchCohorts(version: String): List<Cohort> {
        return json.parse(SerializableCohort.serializer().list, cohortsJson).map { it.toDomain() }
    }

    override suspend fun getCurrentVersion(): String = CURRENT_VERSION
}