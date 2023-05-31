/*
 * Copyright (c) 2020 GitLive Ltd.  Use of this source code is governed by the Apache 2.0 license.
 */

package dev.gitlivecgangaro.firebase.perf

import dev.gitlivecgangaro.firebase.Firebase
import dev.gitlivecgangaro.firebase.FirebaseOptions
import dev.gitlivecgangaro.firebase.apps
import dev.gitlivecgangaro.firebase.initialize
import kotlinx.coroutines.CoroutineScope
import kotlin.test.*

expect val emulatorHost: String
expect val context: Any
expect fun runTest(test: suspend CoroutineScope.() -> Unit)

class FirebasePerformanceTest {

    @BeforeTest
    fun initializeFirebase() {
        Firebase
            .takeIf { Firebase.apps(context).isEmpty() }
            ?.apply {
                initialize(
                    context,
                    FirebaseOptions(
                        applicationId = "1:846484016111:ios:dd1f6688bad7af768c841a",
                        apiKey = "AIzaSyCK87dcMFhzCz_kJVs2cT2AVlqOTLuyWV0",
                        databaseUrl = "https://fir-kotlin-sdk.firebaseio.com",
                        storageBucket = "fir-kotlin-sdk.appspot.com",
                        projectId = "fir-kotlin-sdk",
                        gcmSenderId = "846484016111"
                    )
                )
            }
    }

    @Test
    fun testNewTrace() = runTest {

        val performance = Firebase.performance

        val trace = performance.newTrace("Test Trace")

        assertNotNull(trace)
    }

    @Test
    fun testPerformanceCollectionEnabled() = runTest {

        val performance = Firebase.performance

        performance.setPerformanceCollectionEnabled(false)

        assertFalse(performance.isPerformanceCollectionEnabled())

        performance.setPerformanceCollectionEnabled(true)

        assertTrue(performance.isPerformanceCollectionEnabled())
    }
}
