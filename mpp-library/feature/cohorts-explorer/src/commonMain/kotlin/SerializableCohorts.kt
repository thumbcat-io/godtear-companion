package io.thumbcat.oss.gtcompanion.mpp.cohortsexplorer

import kotlinx.serialization.Serializable

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list

fun cohorts(): List<Cohort> =
    Json(JsonConfiguration.Stable).parse(
        Cohort.serializer().list, """
        [
          {
            "champion": {
              "classification": {
                "key": "Guardian",
                "name": "Guardian"
              },
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
            "champion": {
              "classification": {
                "key": "Guardian",
                "name": "Guardian"
              },
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
            "champion": {
              "classification": {
                "key": "Guardian",
                "name": "Guardian"
              },
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
            "champion": {
              "classification": {
                "key": "Guardian",
                "name": "Guardian"
              },
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
            "champion": {
              "classification": {
                "key": "Maelstrom",
                "name": "Maelstrom"
              },
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
            "champion": {
              "classification": {
                "key": "Maelstrom",
                "name": "Maelstrom"
              },
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
            "champion": {
              "classification": {
                "key": "Maelstrom",
                "name": "Maelstrom"
              },
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
            "champion": {
              "classification": {
                "key": "Shaper",
                "name": "Shaper"
              },
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
            "champion": {
              "classification": {
                "key": "Shaper",
                "name": "Shaper"
              },
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
            "champion": {
              "classification": {
                "key": "Shaper",
                "name": "Shaper"
              },
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
            "champion": {
              "classification": {
                "key": "Shaper",
                "name": "Shaper"
              },
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
            "champion": {
              "classification": {
                "key": "Slayer",
                "name": "Slayer"
              },
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
            "champion": {
              "classification": {
                "key": "Slayer",
                "name": "Slayer"
              },
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
            "champion": {
              "classification": {
                "key": "Slayer",
                "name": "Slayer"
              },
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
            "champion": {
              "classification": {
                "key": "Slayer",
                "name": "Slayer"
              },
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
enum class FollowerSize {
    small, large
}

@Serializable
data class ChampionClass(
    val key: String,
    val name: String
)

@Serializable
data class Warband(
    val cohorts: List<Cohort>
)

@Serializable
data class Cohort(
    val champion: Champion,
    val follower: Follower
) {
    val key: String
        get() = champion.key
    val name: String
        get() = champion.name
    val classification: ChampionClass
        get() = champion.classification
}

@Serializable
data class Follower(
    val key: String,
    val name: String,
    val count: Int,
    val size: FollowerSize
)

@Serializable
data class Champion(
    val key: String,
    val name: String,
    val classification: ChampionClass
)
