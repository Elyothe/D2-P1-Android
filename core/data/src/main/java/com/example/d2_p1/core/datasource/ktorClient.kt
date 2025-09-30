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
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val BaseUrl = "http://10.0.2.2:5262/api/space/"

// Client Ktor partagé
private val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.ALL
    }
}

/**
 * GET - Récupérer un espace par ID
 */
suspend fun getSpaceById(id: Int): Space? {
    return try {
        client.get("${BaseUrl}$id/details").body()
    } catch (e: Exception) {
        Log.e("API", "Erreur getSpaceById: ${e.message}", e)
        null
    }
}

/**
 * POST - Créer un espace
 */
suspend fun createSpace(space: Space): Space? {
    return try {
        client.post("${BaseUrl}create") {
            contentType(ContentType.Application.Json)
            setBody(space)
        }.body()
    } catch (e: Exception) {
        Log.e("API", "Erreur createSpace: ${e.message}", e)
        null
    }
}

/**
 * PUT - Modifier un espace existant
 */
suspend fun updateSpace(space: Space): Boolean {
    return try {
        client.put("${BaseUrl}${space.id}/update") {
            contentType(ContentType.Application.Json)
            setBody(space)
        }
        true
    } catch (e: Exception) {
        Log.e("API", "Erreur updateSpace: ${e.message}", e)
        false
    }
}

/**
 * DELETE - Supprimer un espace
 */
suspend fun deleteSpace(id: Int): Boolean {
    return try {
        client.delete("${BaseUrl}${id}/delete")
        true
    } catch (e: Exception) {
        Log.e("API", "Erreur deleteSpace: ${e.message}", e)
        false
    }
}

suspend fun getSpaces(): List<Space> {
    return try {
        client.get("${BaseUrl}  all").body()
    } catch (e: Exception) {
        Log.e("API", "Erreur getSpaces: ${e.message}", e)
        emptyList()
    }
}