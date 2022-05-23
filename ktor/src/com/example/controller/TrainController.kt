package com.example.controller


import com.example.models.Train
import com.example.repository.TrainRepository
import com.mongodb.BasicDBObject
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import org.bson.types.ObjectId


class TrainController(
    private val trainRepository: TrainRepository,
) {

    suspend fun getTrains(applicationCall: ApplicationCall) {
        try {
            val criteria = BasicDBObject()

            if(applicationCall.parameters["name"] !== null){
                criteria.append("name", "${applicationCall.parameters["name"]}")
            }
            if(applicationCall.parameters["departureTime"] !== null){
                criteria.append("departureTime", "${applicationCall.parameters["departureTime"]}")
            }
            if(applicationCall.parameters["destination"] !== null){
                criteria.append("destination", "${applicationCall.parameters["destination"]}")
            }

            val trains = trainRepository.getAllTrains(criteria)
            if (trains.isNotEmpty()) {
                applicationCall.respond(trains)
            } else {
                applicationCall.respondText("No trains found", status = HttpStatusCode.NotFound)
            }
        }catch (e:Exception){
            println(e)
        }
    }

    suspend fun addTrain(applicationCall: ApplicationCall) {
        try {
            val train = applicationCall.receive<Train>()
            val addedTrain = trainRepository.addTrain(
                train.copy(
                    _id = ObjectId().toString(),
                )
            )
            applicationCall.respond(addedTrain)
        } catch(e: Exception){
            println(e)
        }
    }

}
