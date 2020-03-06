package io.thumbcat.oss.gtcompanion.domain

sealed class Version {
    abstract val version: String
    abstract val major: String
    abstract val minor: String
    abstract val patch: String
    abstract val preReleaseIdentifiers: List<String>
    abstract val buildMetadataIdentifiers: List<String>

    object Latest : Version() {
        override val version: String
            get() = ""
        override val major: String
            get() = ""
        override val minor: String
            get() = ""
        override val patch: String
            get() = ""
        override val preReleaseIdentifiers: List<String>
            get() = emptyList()
        override val buildMetadataIdentifiers: List<String>
            get() = emptyList()
    }

    abstract class Semantic : Version()
}

@ExperimentalStdlibApi
data class SemanticVersionV2(override val version: String) : Version.Semantic() {
    override val major: String
    override val minor: String
    override val patch: String
    override val preReleaseIdentifiers: List<String>
    override val buildMetadataIdentifiers: List<String>

    init {
        val matchResult: MatchResult = requireNotNull(semver2Regex.matchEntire(version)) {
            "failed to regex match: '$version'"
        }
        val parsed: ParsedSemver2Properties = matchResult.parseSemver2Properties()
        this.major = parsed.major
        this.minor = parsed.minor
        this.patch = parsed.patch
        val pri: MutableList<String> = mutableListOf()
        val bmi: MutableList<String> = mutableListOf()
        parsed.preReleaseIdentifiers.parseDotSeparatedList().forEach {
            pri.add(it)
        }
        parsed.buildMetadataIdentifiers.parseDotSeparatedList().forEach {
            bmi.add(it)
        }
        preReleaseIdentifiers = pri.toList()
        buildMetadataIdentifiers = bmi.toList()
    }
}

private const val semver2RegexString: String =
    """^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(?:-((?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\.(?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\+([0-9a-zA-Z-]+(?:\.[0-9a-zA-Z-]+)*))?$"""

/**
 * A [Regex] constant using [semver2RegexString] as the pattern.
 */
private val semver2Regex = Regex(semver2RegexString)

private data class ParsedSemver2Properties(
    val major: String,
    val minor: String,
    val patch: String,
    val preReleaseIdentifiers: String,
    val buildMetadataIdentifiers: String
)

@ExperimentalStdlibApi
private fun String?.verifyIsAscii(): String? {
    if (this != null) {
        if (!isAscii()) {
            throw NonAsciiStringException("'$this' contained non-US_ASCII characters")
        }
    }
    return this
}

/**
 * Attempts to parse dot-separated values from the provided [String].
 *
 * @throws NonAsciiStringException if [String] is NOT null and CANNOT be US_ASCII encoded.
 * @return a [List] of non-null [String] values parsed from [String] split by '.', or null if [String] are null.
 */
@ExperimentalStdlibApi
private fun String.parseDotSeparatedList(): List<String> {
    this.verifyIsAscii()
    return this
        .trimStart('.')
        .trimEnd('.')
        .splitToSequence(".")
        .map { it.trim() }
        .mapNotNull { if (it.isNotBlank()) it else null }
        .toList()
}

/**
 * Attempts to parse [MatchGroup.value] at the given [MatchGroupCollection] [index].
 *
 * @throws NonAsciiStringException if [MatchGroup.value] at [index] is NOT null and CANNOT be US_ASCII encoded.
 * @throws MatchGroupIndexNotFoundException if [required] is true and the provided [MatchGroupCollection] does not contain a [MatchGroup] at [index].
 * @return the parsed non-null [String] value, otherwise an empty [String].
 */
@ExperimentalStdlibApi
private fun MatchGroupCollection.getValue(index: Int, required: Boolean = true): String {
    val value: String = try {
        val mg: MatchGroup? = get(index)
        val value = mg?.value?.verifyIsAscii() ?: ""
        value
    } catch (e: IndexOutOfBoundsException) {
        throw MatchGroupIndexNotFoundException(
            "expected a ${MatchGroupCollection::class.simpleName} with size > $index"
        )
    }
    return when {
        required && value.isBlank() -> throw MatchGroupIndexNotFoundException(
            "expected a ${MatchGroupCollection::class.simpleName} with a value at index $index"
        )
        else -> value
    }
}

/**
 * Parse the individual semver2 properties from a non-null [MatchResult.groups] of unknown origin.
 *
 * @throws InvalidMatchGroupCollectionException if the [MatchGroupCollection] could not be interpreted.
 * @return a non-null [ParsedSemver2Properties].
 */
@ExperimentalStdlibApi
private fun MatchResult.parseSemver2Properties(): ParsedSemver2Properties {
    val matchGroups = this.groups
    return try {
        ParsedSemver2Properties(
            major = matchGroups.getValue(index = 1),
            minor = matchGroups.getValue(index = 2),
            patch = matchGroups.getValue(index = 3),
            preReleaseIdentifiers = matchGroups.getValue(index = 4, required = false),
            buildMetadataIdentifiers = matchGroups.getValue(index = 5, required = false)
        )
    } catch (e: MatchGroupIndexNotFoundException) {
        throw InvalidMatchGroupCollectionException(
            e.message ?: "null message", e
        )
    } catch (e: NonAsciiStringException) {
        throw InvalidMatchGroupCollectionException(
            e.message ?: "null message", e
        )
    }
}

class MatchGroupIndexNotFoundException(message: String) : RuntimeException(message)

class InvalidMatchGroupCollectionException(message: String, cause: Throwable) : RuntimeException(message, cause)

class NonAsciiStringException(message: String) : RuntimeException(message)
