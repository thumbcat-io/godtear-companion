package io.thumbcat.oss.gtcompanion.persistence

import io.thumbcat.oss.gtcompanion.domain.Version
import io.thumbcat.oss.gtcompanion.domain.entity.Champion
import io.thumbcat.oss.gtcompanion.domain.entity.Cohort
import io.thumbcat.oss.gtcompanion.domain.entity.CohortCategory
import io.thumbcat.oss.gtcompanion.domain.entity.FollowerUnit
import io.thumbcat.oss.gtcompanion.domain.entity.ModelSize
import io.thumbcat.oss.gtcompanion.domain.gateway.CohortsRepository
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class HardCodedJsonCohortsRepository(private val json: Json) : CohortsRepository {

    @Serializable
    data class CohortSerializable(
        val champion: ChampionSerializable,
        val follower: FollowerUnitSerializable,
        val category: CohortCategorySerializable
    )

    @Serializable
    data class CohortCategorySerializable(
        val key: String,
        val name: String
    )

    @Serializable
    data class ChampionSerializable(
        val key: String,
        val name: String
    )

    @Serializable
    data class FollowerUnitSerializable(
        val key: String,
        val name: String,
        val count: Int,
        val size: String
    )

    override suspend fun getAll(version: Version): List<Cohort> {
        return json.parse(CohortSerializable.serializer().list, cohortsJson).map { it.toDomain() }
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
                    "size": "SMALL"
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
                    "size": "SMALL"
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
                    "size": "SMALL"
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
                    "size": "SMALL"
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
                    "size": "SMALL"
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
                    "size": "SMALL"
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
                    "size": "SMALL"
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
                    "size": "SMALL"
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
                    "size": "SMALL"
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
                    "size": "SMALL"
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
                    "size": "LARGE"
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
                    "size": "SMALL"
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
                    "size": "SMALL"
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
                    "size": "SMALL"
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
                    "size": "SMALL"
                }
            }
        ]
    """.trimIndent()

    private fun CohortSerializable.toDomain(): Cohort = Cohort(
        champion = champion.toDomain(),
        followerUnit = follower.toDomain(),
        category = when (category.key.toUpperCase()) {
            "SHAPER" -> CohortCategory.Shaper(
                key = category.key,
                name = category.name
            )
            "MAELSTROM" -> CohortCategory.Maelstrom(
                key = category.key,
                name = category.name
            )
            "GUARDIAN" -> CohortCategory.Guardian(
                key = category.key,
                name = category.name
            )
            "SLAYER" -> CohortCategory.Slayer(
                key = category.key,
                name = category.name
            )
            else -> throw IllegalArgumentException("unsupported cohort category: $category")
        }
    )

    private fun ChampionSerializable.toDomain(): Champion = Champion(
        key = key,
        name = name
    )

    private fun FollowerUnitSerializable.toDomain(): FollowerUnit = FollowerUnit(
        key = key,
        name = name,
        modelCount = count,
        modelSize = when (size) {
            "SMALL" -> ModelSize.Small(key = size, name = size.toLowerCase().capitalize())
            "LARGE" -> ModelSize.Large(key = size, name = size.toLowerCase().capitalize())
            else -> throw IllegalArgumentException("unsupported model size: $this")
        }
    )

    private fun Cohort.toSerializable(): CohortSerializable = CohortSerializable(
        champion = champion.toSerializable(),
        follower = followerUnit.toSerializable(),
        category = category.toSerializable()
    )

    private fun Champion.toSerializable(): ChampionSerializable = ChampionSerializable(
        key = key,
        name = name
    )

    private fun FollowerUnit.toSerializable(): FollowerUnitSerializable = FollowerUnitSerializable(
        key = key,
        name = name,
        count = modelCount,
        size = modelSize.key
    )

    private fun CohortCategory.toSerializable(): CohortCategorySerializable = CohortCategorySerializable(
        key = key,
        name = name
    )
}
