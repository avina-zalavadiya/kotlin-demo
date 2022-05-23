package com.example.models

import io.ktor.auth.*
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    @BsonId
    val _id: String = ObjectId().toString(),
    val firstName: String?,
    val lastName: String?,
    val email: String,
    val username: String,
    val password: String,
    val token: String?
) : Principal


