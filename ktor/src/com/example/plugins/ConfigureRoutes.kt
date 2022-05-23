package com.example.plugins

import com.example.routes.registerTrainRoutes
import com.example.routes.registerUserRoutes
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*

fun Application.configureRouting() {
    registerUserRoutes()
    registerTrainRoutes()
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
