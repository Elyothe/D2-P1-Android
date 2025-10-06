package com.example.d2_p1.core.datasource

import com.example.d2_p1.core.data.models.Space

object MockData {

    val spaces = listOf(
        Space(
            id = 1,
            name = "Salle 1",
            category = "Salle de cours",
            maxCapacity = 25,
            resources = listOf("1 grand écran", "1 tableau blanc", "12 prises", "1 télé"),
            description = "Salle standard de cours"
        ),
        Space(
            id = 2,
            name = "Salle 11",
            category = "Salle de cours",
            maxCapacity = 20,
            resources = listOf("1 cafetière", "1 tableau mobile", "8 prises", "1 télé"),
            description = "Salle conviviale utilisée pour petits groupes"
        ),
        Space(
            id = 3,
            name = "Salle 12",
            category = "Salle de cours",
            maxCapacity = 30,
            resources = listOf("1 vidéoprojecteur", "2 enceintes", "1 tableau blanc", "10 prises", "1 télé"),
            description = "Salle équipée pour présentations"
        ),
        Space(
            id = 4,
            name = "Salle 14",
            category = "Salle de cours",
            maxCapacity = 28,
            resources = listOf("3 écrans", "1 tableau blanc", "15 prises", "1 télé"),
            description = "Salle multimédia pour exercices pratiques"
        ),
        Space(
            id = 5,
            name = "Salle 16",
            category = "Salle de cours",
            maxCapacity = 22,
            resources = listOf("Wifi «Campus_16»", "1 tableau blanc", "18 prises", "1 télé"),
            description = "Salle connectée pour travaux en ligne"
        ),
        Space(
            id = 6,
            name = "Salle 17",
            category = "Salle de cours",
            maxCapacity = 24,
            resources = listOf("1 vidéoprojecteur", "1 paperboard", "1 tableau blanc", "12 prises", "1 télé"),
            description = "Salle adaptée aux cours hybrides"
        ),
        Space(
            id = 7,
            name = "Salle 2",
            category = "Salle de cours",
            maxCapacity = 26,
            resources = listOf("1 tableau blanc", "2 enceintes", "14 prises", "1 télé"),
            description = "Salle standard de cours"
        ),
        Space(
            id = 8,
            name = "Salle 21",
            category = "Salle de cours",
            maxCapacity = 18,
            resources = listOf("1 téléviseur", "1 câble HDMI", "1 tableau blanc", "10 prises", "1 télé"),
            description = "Petite salle pour TD"
        ),
        Space(
            id = 9,
            name = "Salle 22",
            category = "Salle de cours",
            maxCapacity = 20,
            resources = listOf("1 vidéoprojecteur", "wifi", "1 tableau blanc", "12 prises", "1 télé"),
            description = "Salle de travail en groupe"
        ),
        Space(
            id = 10,
            name = "Salle 23",
            category = "Salle de cours",
            maxCapacity = 16,
            resources = listOf("1 paperboard", "1 tableau mobile", "8 prises", "1 télé"),
            description = "Salle de travail en groupe"
        ),
        Space(
            id = 11,
            name = "Salle 24",
            category = "Salle de cours",
            maxCapacity = 32,
            resources = listOf("2 vidéoprojecteurs", "4 enceintes", "sonorisation", "20 prises", "1 télé"),
            description = "Grande salle pour conférences"
        ),
        Space(
            id = 12,
            name = "Salle 25",
            category = "Bureau",
            maxCapacity = 4,
            resources = listOf("6 écrans", "1 imprimante", "6 prises", "1 télé"),
            description = "Bureau administratif"
        ),
        Space(
            id = 13,
            name = "Salle 26A",
            category = "Salle de cours",
            maxCapacity = 15,
            resources = listOf("1 écran tactile", "wifi", "1 tableau blanc", "8 prises", "1 télé"),
            description = "Salle pour petits ateliers"
        ),
        Space(
            id = 14,
            name = "Salle 26B",
            category = "Salle de cours",
            maxCapacity = 15,
            resources = listOf("1 vidéoprojecteur", "1 tableau blanc", "8 prises", "1 télé"),
            description = "Salle jumelle de la 26A"
        ),
        Space(
            id = 15,
            name = "Salle 27",
            category = "Salle de cours",
            maxCapacity = 28,
            resources = listOf("1 vidéoprojecteur", "2 enceintes", "1 tableau blanc", "14 prises", "1 télé"),
            description = "Salle standard équipée"
        ),
        Space(
            id = 16,
            name = "Salle 3",
            category = "Salle de cours",
            maxCapacity = 20,
            resources = listOf("1 tableau blanc", "wifi", "10 prises", "1 télé"),
            description = "Salle classique"
        ),
        Space(
            id = 17,
            name = "Salle 4",
            category = "Salle de cours",
            maxCapacity = 22,
            resources = listOf("1 vidéoprojecteur", "1 tableau blanc", "12 prises", "1 télé"),
            description = "Salle équipée pour présentations"
        ),
        Space(
            id = 18,
            name = "Salle 5",
            category = "Salle de cours",
            maxCapacity = 18,
            resources = listOf("1 paperboard", "2 enceintes", "8 prises", "1 télé"),
            description = "Salle pour ateliers"
        ),
        Space(
            id = 19,
            name = "Salle 6",
            category = "Salle de cours",
            maxCapacity = 20,
            resources = listOf("1 vidéoprojecteur", "wifi", "1 tableau blanc", "14 prises", "1 télé"),
            description = "Salle de cours hybride"
        ),
        Space(
            id = 20,
            name = "Salle 7",
            category = "Salle de cours",
            maxCapacity = 25,
            resources = listOf("1 tableau blanc", "12 prises", "1 télé"),
            description = "Salle polyvalente"
        ),
        Space(
            id = 21,
            name = "Salle 7B",
            category = "Salle de cours",
            maxCapacity = 14,
            resources = listOf("1 écran plat", "1 câble HDMI", "1 tableau blanc", "6 prises", "1 télé"),
            description = "Petite salle interactive"
        ),
        Space(
            id = 22,
            name = "Amphithéatre",
            category = "Amphithéatre",
            maxCapacity = 120,
            resources = listOf("2 vidéoprojecteurs", "6 micros", "8 enceintes", "sonorisation", "40 prises"),
            description = "Grande salle pour conférences et événements"
        ),
        Space(
            id = 23,
            name = "CDI",
            category = "Bureau",
            maxCapacity = 12,
            resources = listOf("2 ordinateurs fixes", "1 imprimante", "wifi", "20 prises", "1 télé"),
            description = "Centre de documentation et d'information"
        )
    )
}
