package com.example.repository

import com.example.models.Train
import org.bson.conversions.Bson
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

class TrainRepository(database: CoroutineDatabase) {

    private val trainCollection: CoroutineCollection<Train> = database.getCollection()

    suspend fun addTrain(train: Train): Train {
        trainCollection.insertOne(train)
        return  train
    }

    suspend fun getAllTrains(query: Bson): List<Train> =
             trainCollection.find(query).toList()
}




