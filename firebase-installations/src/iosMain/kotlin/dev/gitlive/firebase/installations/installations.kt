package dev.gitlivecgangaro.firebase.installations

import cocoapods.FirebaseInstallations.*
import dev.gitlivecgangaro.firebase.Firebase
import dev.gitlivecgangaro.firebase.FirebaseApp
import dev.gitlivecgangaro.firebase.FirebaseException
import kotlinx.coroutines.CompletableDeferred
import platform.Foundation.*

actual val Firebase.installations
    get() = FirebaseInstallations(FIRInstallations.installations())

actual fun Firebase.installations(app: FirebaseApp) : FirebaseInstallations = TODO("Come back to issue")
//        = FirebaseInstallations(FIRInstallations.installationsWithApp(app.ios))

actual class FirebaseInstallations internal constructor(val ios: FIRInstallations) {

    actual suspend fun delete() = ios.await { deleteWithCompletion(completion = it) }

    actual suspend fun getId(): String = ios.awaitResult { installationIDWithCompletion(completion = it) }

    actual suspend fun getToken(forceRefresh: Boolean): String {
        val result: FIRInstallationsAuthTokenResult = ios.awaitResult { authTokenForcingRefresh(forceRefresh = forceRefresh, completion = it) }

        return result.authToken
    }
}

actual class FirebaseInstallationsException(message: String): FirebaseException(message)

suspend inline fun <T> T.await(function: T.(callback: (NSError?) -> Unit) -> Unit) {
    val job = CompletableDeferred<Unit>()
    function { error ->
        if(error == null) {
            job.complete(Unit)
        } else {
            job.completeExceptionally(FirebaseInstallationsException(error.toString()))
        }
    }
    job.await()
}

suspend inline fun <T, reified R> T.awaitResult(function: T.(callback: (R?, NSError?) -> Unit) -> Unit): R {
    val job = CompletableDeferred<R?>()
    function { result, error ->
        if(error == null) {
            job.complete(result)
        } else {
            job.completeExceptionally(FirebaseInstallationsException(error.toString()))
        }
    }
    return job.await() as R
}
