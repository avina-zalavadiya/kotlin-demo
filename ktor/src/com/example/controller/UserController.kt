package com.example.controller

import com.example.models.User
import com.example.repository.UserRepository

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import org.bson.types.ObjectId
import utils.Cipher
import utils.JwtProvider
import java.util.*


class UserController(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider
) {
    private val base64Encoder = Base64.getEncoder()
    private fun generateJwtToken(user: User): String? {
        return jwtProvider.createJWT(user)
    }

    suspend fun login(applicationCall: ApplicationCall) {
        val user = applicationCall.receive<User>()
        val loggedUser = userRepository.findByEmail(user.email)
        if (loggedUser?.password == String(base64Encoder.encode(Cipher.encrypt(user.password)))) {
            applicationCall.respond(loggedUser.copy(token = "JWT ${generateJwtToken(loggedUser)}"))
        } else {
            applicationCall.respondText("email or password invalid!", status = HttpStatusCode.Unauthorized)
        }
    }

    suspend fun getUsers(applicationCall: ApplicationCall) {
        try {
            val users = userRepository.getAllUsers()
            if (users.isNotEmpty()) {
                applicationCall.respond(users)
            } else {
                applicationCall.respondText("No users found", status = HttpStatusCode.NotFound)
            }
        }catch (e:Exception){
            println(e)
        }
    }

    suspend fun getUser(applicationCall: ApplicationCall) {
        val id = applicationCall.parameters["id"] ?: return applicationCall.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        val user = applicationCall.parameters["id"]?.let { userRepository.getUserById(userId = it) }
            ?: return applicationCall.respondText(
                "No user with id $id",
                status = HttpStatusCode.NotFound
            )
        applicationCall.respond(user)
    }

    suspend fun updateUser(applicationCall: ApplicationCall) {
        val id = applicationCall.parameters["id"] ?: return applicationCall.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        val updateUserInfo = applicationCall.receive<User>()
        val user = applicationCall.parameters["id"]?.let { userRepository.updateUserById(userId = it, updateUserInfo) }
            ?: return applicationCall.respondText(
                "No user with id $id",
                status = HttpStatusCode.NotFound
            )
        applicationCall.respond(user)
    }

    suspend fun addUser(applicationCall: ApplicationCall) {
        try {
            val user = applicationCall.receive<User>()
            userRepository.findByEmail(user.email).apply {
                require(this == null) {
                    return applicationCall.respondText("Email already registered!", status = HttpStatusCode.BadRequest)

                }
            }
            val addedUser = userRepository.registerUser(
                user.copy(
                    _id = ObjectId().toString(),
                    password = String(base64Encoder.encode(Cipher.encrypt(user.password)))
                )
            )
            applicationCall.respond(addedUser)
        } catch(e: Exception){
          println(e)
        }
    }

    suspend fun deleteUser(applicationCall: ApplicationCall) {
        applicationCall.parameters["id"] ?: return applicationCall.respond(HttpStatusCode.BadRequest)
        val user = applicationCall.parameters["id"]?.let { userRepository.deleteUserById(userId = it) }
            ?: return applicationCall.respondText(
                "Not Found",
                status = HttpStatusCode.NotFound
            )

        if (user) {
            applicationCall.respondText("user removed correctly", status = HttpStatusCode.Accepted)
        } else {
            applicationCall.respondText("Not Found", status = HttpStatusCode.NotFound)
        }

    }

    suspend fun getUserByEmail(email: String?): User {
        return email.let {
            require(!it.isNullOrBlank()) { "User not logged or with invalid email." }
            userRepository.findByEmail(it)!!
        }
    }
}
