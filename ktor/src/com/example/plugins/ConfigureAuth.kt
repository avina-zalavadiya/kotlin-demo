package com.example.plugins

import com.example.controller.UserController
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import org.koin.ktor.ext.inject
import utils.JwtProvider

fun Application.configureAuth(){
    val userController by inject<UserController>()
    install(Authentication) {
        jwt {
            verifier(JwtProvider.verifier)
            authSchemes(JwtProvider.authSchema)
            validate { credential ->
                if (credential.payload.audience.contains(JwtProvider.audience)) {
                    userController.getUserByEmail(credential.payload.claims["email"]?.asString())
                } else null
            }
        }
    }
}