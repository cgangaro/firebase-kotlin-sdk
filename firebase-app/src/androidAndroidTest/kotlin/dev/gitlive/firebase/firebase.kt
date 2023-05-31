/*
 * Copyright (c) 2020 GitLive Ltd.  Use of this source code is governed by the Apache 2.0 license.
 */

@file:JvmName("tests")
package dev.gitlivecgangaro.firebase

import androidx.test.platform.app.InstrumentationRegistry

actual val context: Any = InstrumentationRegistry.getInstrumentation().targetContext

actual fun runTest(test: suspend () -> Unit) = kotlinx.coroutines.test.runTest { test() }
