package com.example.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Train(
    @BsonId
    val _id: String = ObjectId().toString(),
    val source: String,
    val destination: String,
    val departureTime: String,
    val name: String
)


