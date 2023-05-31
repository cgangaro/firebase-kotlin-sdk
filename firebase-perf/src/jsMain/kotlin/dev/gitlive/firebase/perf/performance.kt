package dev.gitlivecgangaro.firebase.perf

import dev.gitlivecgangaro.firebase.Firebase
import dev.gitlivecgangaro.firebase.FirebaseApp
import dev.gitlivecgangaro.firebase.FirebaseException
import dev.gitlivecgangaro.firebase.firebase
import dev.gitlivecgangaro.firebase.perf.metrics.Trace

actual val Firebase.performance: FirebasePerformance
    get() = rethrow {
        dev.gitlivecgangaro.firebase.performance
        FirebasePerformance(firebase.performance())
    }

actual fun Firebase.performance(app: FirebaseApp): FirebasePerformance = rethrow {
    dev.gitlivecgangaro.firebase.performance
    FirebasePerformance(firebase.performance(app.js))
}

actual class FirebasePerformance internal constructor(val js: firebase.performance) {

    actual fun newTrace(traceName: String): Trace = rethrow {
        Trace(js.trace(traceName))
    }

    actual fun isPerformanceCollectionEnabled(): Boolean = js.dataCollectionEnabled

    actual fun setPerformanceCollectionEnabled(enable: Boolean) {
        js.dataCollectionEnabled = enable
    }

    fun isInstrumentationEnabled(): Boolean = js.instrumentationEnabled

    fun setInstrumentationEnabled(enable: Boolean) {
        js.instrumentationEnabled = enable
    }
}

actual open class FirebasePerformanceException(code: String, cause: Throwable) :
    FirebaseException(code, cause)

internal inline fun <R> rethrow(function: () -> R): R {
    try {
        return function()
    } catch (e: Exception) {
        throw e
    } catch (e: dynamic) {
        throw errorToException(e)
    }
}

internal fun errorToException(error: dynamic) = (error?.code ?: error?.message ?: "")
    .toString()
    .lowercase()
    .let { code ->
        when {
            else -> {
                println("Unknown error code in ${JSON.stringify(error)}")
                FirebasePerformanceException(code, error)
            }
        }
    }
