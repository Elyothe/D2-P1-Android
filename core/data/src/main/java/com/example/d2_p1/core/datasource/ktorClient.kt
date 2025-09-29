package com.example.d2_p1.core.datasource

import android.util.Log
import com.example.d2_p1.core.data.models.Space
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


val BaseUrl = "http://localhost:5262/api/"

val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
    install(Logging){
        logger = Logger.ANDROID
        level = LogLevel.ALL
    }
}


//Call Space
suspend fun getSpace(): List<Space> {
    return try {
        val spaces: List<Space> =
            client.get("${BaseUrl}space").body()
        spaces
    } catch (e: Exception) {
        Log.e("API", "Erreur getSpace: ${e.message}", e)
        emptyList()
    }
}

