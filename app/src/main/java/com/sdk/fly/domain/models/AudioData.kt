package com.sdk.fly.domain.models

data class AudioData(
    val bytes: ByteArray,
    val format: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AudioData

        if (!bytes.contentEquals(other.bytes)) return false
        if (format != other.format) return false

        return true
    }

    override fun hashCode(): Int {
        var result = bytes.contentHashCode()
        result = 31 * result + format.hashCode()
        return result
    }
}