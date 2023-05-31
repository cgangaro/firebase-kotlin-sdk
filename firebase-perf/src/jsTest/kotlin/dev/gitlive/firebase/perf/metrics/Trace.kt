package dev.gitlivecgangaro.firebase.perf.metrics

import dev.gitlivecgangaro.firebase.Firebase
import dev.gitlivecgangaro.firebase.FirebaseOptions
import dev.gitlivecgangaro.firebase.apps
import dev.gitlivecgangaro.firebase.initialize
import dev.gitlivecgangaro.firebase.perf.FirebasePerformance
import dev.gitlivecgangaro.firebase.perf.performance
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class JsTraceTest {

    private lateinit var performance: FirebasePerformance

    @BeforeTest
    fun initializeFirebase() {
        Firebase
            .takeIf { Firebase.apps(dev.gitlivecgangaro.firebase.perf.context).isEmpty() }
            ?.apply {
                initialize(
                    dev.gitlivecgangaro.firebase.perf.context,
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

        performance = Firebase.performance


    }

    @Test
    fun testGetAttribute() {
        val trace = performance.newTrace("testGetAttribute")
        trace.start()
        trace.putAttribute("Test_Get_Attribute", "Test Get Attribute Value")

        assertEquals("Test Get Attribute Value", trace.getAttribute("Test_Get_Attribute"))
        trace.stop()
    }

    @Test
    fun testPutAttribute() {
        val trace = performance.newTrace("testPutAttribute")
        trace.start()
        trace.putAttribute("Test_Put_Attribute", "Test Put Attribute Value")

        assertEquals("Test Put Attribute Value", trace.getAttribute("Test_Put_Attribute"))
        trace.stop()
    }
}