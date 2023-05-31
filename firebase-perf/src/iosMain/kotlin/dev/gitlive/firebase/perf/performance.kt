package dev.gitlivecgangaro.firebase.perf

import cocoapods.FirebasePerformance.FIRPerformance
import dev.gitlivecgangaro.firebase.Firebase
import dev.gitlivecgangaro.firebase.FirebaseApp
import dev.gitlivecgangaro.firebase.FirebaseException
import dev.gitlivecgangaro.firebase.perf.metrics.Trace

actual val Firebase.performance get() =
    FirebasePerformance(FIRPerformance.sharedInstance())

actual fun Firebase.performance(app: FirebaseApp) =
    FirebasePerformance(FIRPerformance.sharedInstance())

actual class FirebasePerformance(val ios: FIRPerformance) {

    actual fun newTrace(traceName: String): Trace = Trace(ios.traceWithName(traceName))

    actual fun isPerformanceCollectionEnabled(): Boolean = ios.isDataCollectionEnabled()

    actual fun setPerformanceCollectionEnabled(enable: Boolean) {
        ios.dataCollectionEnabled = enable
    }
}

actual open class FirebasePerformanceException(message: String) : FirebaseException(message)