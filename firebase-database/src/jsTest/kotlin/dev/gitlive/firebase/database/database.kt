package dev.gitlivecgangaro.firebase.database

actual val emulatorHost: String = "localhost"

actual val context: Any = Unit
actual fun runTest(test: suspend () -> Unit) {
    runTest { test() }
}