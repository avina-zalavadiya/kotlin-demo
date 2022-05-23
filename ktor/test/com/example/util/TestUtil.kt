package com.example.util

import com.example.mainModules
import io.ktor.server.testing.*

fun withTestServer(block: TestApplicationEngine.() -> Unit) {
    withTestApplication({ mainModules(testing = true) }, block)
}
