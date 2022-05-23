package com.example.routes

import com.example.constant.DEFAULT_ROUTE_PREFIX
import com.example.controller.UserController
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.userRouting(userController: UserController) {
    route("$DEFAULT_ROUTE_PREFIX/login") {
        post {
            userController.login(this.context)
        }
    }
    route("$DEFAULT_ROUTE_PREFIX/register") {
        post {
            userController.addUser(this.context)
        }
    }
    route("$DEFAULT_ROUTE_PREFIX/user") {
        authenticate {
            get {
                userController.getUsers(this.context)
            }
            put("{id}") {
                userController.updateUser(this.context)
            }
            get("{id}") {
                userController.getUser(this.context)
            }
            delete("{id}") {
                userController.deleteUser(this.context)
            }
        }
    }
}

fun Application.registerUserRoutes() {
    val userController by inject<UserController>()
    routing {
        userRouting(userController)
    }
}


