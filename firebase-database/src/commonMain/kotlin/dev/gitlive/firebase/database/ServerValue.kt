package dev.gitlivecgangaro.firebase.database

import dev.gitlivecgangaro.firebase.FirebaseDecoder
import dev.gitlivecgangaro.firebase.FirebaseEncoder
import dev.gitlivecgangaro.firebase.SpecialValueSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException

/** Represents a Firebase ServerValue. */
@Serializable(with = ServerValueSerializer::class)
expect class ServerValue internal constructor(nativeValue: Any){
    internal val nativeValue: Any

    companion object {
        val TIMESTAMP: ServerValue
        fun increment(delta: Double): ServerValue
    }
}

/** Serializer for [ServerValue]. Must be used with [FirebaseEncoder]/[FirebaseDecoder].*/
object ServerValueSerializer: SpecialValueSerializer<ServerValue>(
    serialName = "ServerValue",
    toNativeValue = ServerValue::nativeValue,
    fromNativeValue = { raw ->
        raw?.let(::ServerValue) ?: throw SerializationException("Cannot deserialize $raw")
    }
)
