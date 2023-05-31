package dev.gitlivecgangaro.firebase.installations

import dev.gitlivecgangaro.firebase.Firebase
import dev.gitlivecgangaro.firebase.FirebaseApp
import dev.gitlivecgangaro.firebase.FirebaseException
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy

expect val Firebase.installations: FirebaseInstallations

expect fun Firebase.installations(app: FirebaseApp): FirebaseInstallations

expect class FirebaseInstallations {
    suspend fun delete()
    suspend fun getId(): String
    suspend fun getToken(forceRefresh: Boolean): String
}

expect class FirebaseInstallationsException: FirebaseException

