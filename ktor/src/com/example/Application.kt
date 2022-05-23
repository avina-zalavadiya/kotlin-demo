package com.example

import com.example.plugins.configureAuth
import com.example.plugins.configureDependencyInjection
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.mainModules(testing: Boolean) {
    configureSerialization()
    configureDependencyInjection()
    configureAuth()
    configureRouting()

}

