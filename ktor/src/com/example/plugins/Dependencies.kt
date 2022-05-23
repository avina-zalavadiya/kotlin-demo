package com.example.plugins

import com.example.controller.TrainController
import com.example.controller.UserController
import com.example.repository.TrainRepository
import com.example.repository.UserRepository
import com.mongodb.MongoClientSettings
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.logger.SLF4JLogger
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import utils.JwtProvider


fun Application.configureDependencyInjection(){
    val client = KMongo.createClient(MongoClientSettings.builder().applyToConnectionPoolSettings {
        it.maxSize(1).build()
    }.build()).coroutine
    val database = client.getDatabase("kotlin-poc")

    val module = module {
        single { JwtProvider }
        single { database  }
        single { UserRepository(get()) }
        single { UserController(get(),get()) }

        single { TrainRepository(get()) }
        single { TrainController(get()) }
    }
    install(Koin) {
        SLF4JLogger()
        modules(arrayListOf(module))
    }
}