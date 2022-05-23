package com.example.routes

import com.example.constant.DEFAULT_ROUTE_PREFIX
import com.example.controller.TrainController
import io.ktor.application.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.trainRouting(trainController: TrainController) {
    route("$DEFAULT_ROUTE_PREFIX/train") {
            get {
                trainController.getTrains(this.context)
            }
            post("/add") {
                trainController.addTrain(this.context)
            }
        }

}

fun Application.registerTrainRoutes() {
    val trainController by inject<TrainController>()
    routing {
        trainRouting(trainController)
    }
}


