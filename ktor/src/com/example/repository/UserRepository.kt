package com.example.repository

import com.example.models.User
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserRepository(database: CoroutineDatabase) {

    private val userCollection: CoroutineCollection<User> = database.getCollection()

    suspend fun registerUser(user: User): User {
        userCollection.insertOne(user)
        return  user
    }

    suspend fun getAllUsers(): List<User> = userCollection.find().toList()

    suspend fun getUserById(userId:String) : User? {
        return  userCollection.findOne(User::_id eq userId)
    }

    suspend fun deleteUserById(userId:String) : Boolean {
        return  userCollection.deleteOne(User::_id eq  userId).wasAcknowledged()
    }

    suspend fun updateUserById(userId: String,user: User) :  User {
         userCollection.updateOne(User::_id eq userId, user)
        return user

    }

    suspend fun findByEmail(email: String): User? {
        return userCollection
            .find(User::email eq (email))
            .limit(1)
            .first()
    }
}