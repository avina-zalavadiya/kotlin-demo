package com.example

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import  com.example.util.withTestServer

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestServer () {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }
}

