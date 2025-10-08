package com.example.d2_p1.core.datasource

import com.example.d2_p1.core.data.models.Reservation
import com.example.d2_p1.core.data.models.Space
import com.example.d2_p1.core.data.models.StatusEnum
import com.example.d2_p1.core.data.models.User

object MockData {
    // Utilisateurs fictifs pour les réservations
    private val users = listOf(
        User(
            id = 1,
            mail = "alice@example.com",
            displayName = "Alice",
            isActive = true,
            roleId = 1
        ),
        User(id = 2, mail = "bob@example.com", displayName = "Bob", isActive = true, roleId = 1),
        User(id = 3, mail = "charlie@example.com", displayName = "Charlie", isActive = true, roleId = 1)
    )

    val spaces = mutableListOf(
        Space(
            id = 1,
            name = "Salle 1",
            category = "Salle de cours",
            maxCapacity = 25,
            resources = listOf("1 grand écran", "1 tableau blanc", "12 prises", "1 télé"),
            description = "Salle standard de cours",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 1,
                    spaceId = 1,
                    userId = 1,
                    attendeesCount = 10,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 2,
                    spaceId = 1,
                    userId = 2,
                    attendeesCount = 8,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                ),
                Reservation(
                    id = 3,
                    spaceId = 1,
                    userId = 3,
                    attendeesCount = 12,
                    startAt = "2025-10-31T10:00:00",
                    endAt = "2025-10-31T11:00:00",
                    status = StatusEnum.CANCELLED,
                    space = null,
                    user = users[2]
                )
            )
        ),
        Space(
            id = 2,
            name = "Salle 11",
            category = "Salle de cours",
            maxCapacity = 20,
            resources = listOf("1 cafetière", "1 tableau mobile", "8 prises", "1 télé"),
            description = "Salle conviviale utilisée pour petits groupes",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 4,
                    spaceId = 2,
                    userId = 1,
                    attendeesCount = 6,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 5,
                    spaceId = 2,
                    userId = 2,
                    attendeesCount = 7,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 3,
            name = "Salle 12",
            category = "Salle de cours",
            maxCapacity = 30,
            resources = listOf("1 vidéoprojecteur", "2 enceintes", "1 tableau blanc", "10 prises", "1 télé"),
            description = "Salle équipée pour présentations",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 6,
                    spaceId = 3,
                    userId = 3,
                    attendeesCount = 15,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[2]
                )
            )
        ),
        Space(
            id = 4,
            name = "Salle 14",
            category = "Salle de cours",
            maxCapacity = 28,
            resources = listOf("3 écrans", "1 tableau blanc", "15 prises", "1 télé"),
            description = "Salle multimédia pour exercices pratiques",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 7,
                    spaceId = 4,
                    userId = 1,
                    attendeesCount = 20,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 8,
                    spaceId = 4,
                    userId = 2,
                    attendeesCount = 18,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                ),
                Reservation(
                    id = 9,
                    spaceId = 4,
                    userId = 3,
                    attendeesCount = 22,
                    startAt = "2025-10-31T10:00:00",
                    endAt = "2025-10-31T11:00:00",
                    status = StatusEnum.CANCELLED,
                    space = null,
                    user = users[2]
                )
            )
        ),
        Space(
            id = 5,
            name = "Salle 16",
            category = "Salle de cours",
            maxCapacity = 22,
            resources = listOf("Wifi «Campus_16»", "1 tableau blanc", "18 prises", "1 télé"),
            description = "Salle connectée pour travaux en ligne",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 10,
                    spaceId = 5,
                    userId = 1,
                    attendeesCount = 5,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 11,
                    spaceId = 5,
                    userId = 2,
                    attendeesCount = 6,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 6,
            name = "Salle 17",
            category = "Salle de cours",
            maxCapacity = 24,
            resources = listOf("1 vidéoprojecteur", "1 paperboard", "1 tableau blanc", "12 prises", "1 télé"),
            description = "Salle adaptée aux cours hybrides",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 12,
                    spaceId = 6,
                    userId = 3,
                    attendeesCount = 10,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[2]
                ),
                Reservation(
                    id = 13,
                    spaceId = 6,
                    userId = 1,
                    attendeesCount = 12,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 14,
                    spaceId = 6,
                    userId = 2,
                    attendeesCount = 14,
                    startAt = "2025-10-31T10:00:00",
                    endAt = "2025-10-31T11:00:00",
                    status = StatusEnum.CANCELLED,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 7,
            name = "Salle 2",
            category = "Salle de cours",
            maxCapacity = 26,
            resources = listOf("1 tableau blanc", "2 enceintes", "14 prises", "1 télé"),
            description = "Salle standard de cours",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 15,
                    spaceId = 7,
                    userId = 1,
                    attendeesCount = 10,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 16,
                    spaceId = 7,
                    userId = 2,
                    attendeesCount = 12,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                ),
                Reservation(
                    id = 17,
                    spaceId = 7,
                    userId = 3,
                    attendeesCount = 14,
                    startAt = "2025-10-31T10:00:00",
                    endAt = "2025-10-31T11:00:00",
                    status = StatusEnum.CANCELLED,
                    space = null,
                    user = users[2]
                )
            )
        ),
        Space(
            id = 8,
            name = "Salle 21",
            category = "Salle de cours",
            maxCapacity = 18,
            resources = listOf("1 téléviseur", "1 câble HDMI", "1 tableau blanc", "10 prises", "1 télé"),
            description = "Petite salle pour TD",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 18,
                    spaceId = 8,
                    userId = 1,
                    attendeesCount = 5,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 19,
                    spaceId = 8,
                    userId = 2,
                    attendeesCount = 6,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 9,
            name = "Salle 22",
            category = "Salle de cours",
            maxCapacity = 20,
            resources = listOf("1 vidéoprojecteur", "wifi", "1 tableau blanc", "12 prises", "1 télé"),
            description = "Salle de travail en groupe",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 20,
                    spaceId = 9,
                    userId = 3,
                    attendeesCount = 15,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[2]
                ),
                Reservation(
                    id = 21,
                    spaceId = 9,
                    userId = 1,
                    attendeesCount = 10,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 22,
                    spaceId = 9,
                    userId = 2,
                    attendeesCount = 12,
                    startAt = "2025-10-31T10:00:00",
                    endAt = "2025-10-31T11:00:00",
                    status = StatusEnum.CANCELLED,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 10,
            name = "Salle 23",
            category = "Salle de cours",
            maxCapacity = 16,
            resources = listOf("1 paperboard", "1 tableau mobile", "8 prises", "1 télé"),
            description = "Salle de travail en groupe",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 23,
                    spaceId = 10,
                    userId = 1,
                    attendeesCount = 5,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 24,
                    spaceId = 10,
                    userId = 2,
                    attendeesCount = 6,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 11,
            name = "Salle 24",
            category = "Salle de cours",
            maxCapacity = 32,
            resources = listOf("2 vidéoprojecteurs", "4 enceintes", "sonorisation", "20 prises", "1 télé"),
            description = "Grande salle pour conférences",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 25,
                    spaceId = 11,
                    userId = 3,
                    attendeesCount = 20,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[2]
                ),
                Reservation(
                    id = 26,
                    spaceId = 11,
                    userId = 1,
                    attendeesCount = 18,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 27,
                    spaceId = 11,
                    userId = 2,
                    attendeesCount = 22,
                    startAt = "2025-10-31T10:00:00",
                    endAt = "2025-10-31T11:00:00",
                    status = StatusEnum.CANCELLED,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 12,
            name = "Salle 25",
            category = "Bureau",
            maxCapacity = 4,
            resources = listOf("6 écrans", "1 imprimante", "6 prises", "1 télé"),
            description = "Bureau administratif",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 28,
                    spaceId = 12,
                    userId = 1,
                    attendeesCount = 3,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 29,
                    spaceId = 12,
                    userId = 2,
                    attendeesCount = 2,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 13,
            name = "Salle 26A",
            category = "Salle de cours",
            maxCapacity = 15,
            resources = listOf("1 écran tactile", "wifi", "1 tableau blanc", "8 prises", "1 télé"),
            description = "Salle pour petits ateliers",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 30,
                    spaceId = 13,
                    userId = 3,
                    attendeesCount = 10,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[2]
                ),
                Reservation(
                    id = 31,
                    spaceId = 13,
                    userId = 1,
                    attendeesCount = 8,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[0]
                )
            )
        ),
        Space(
            id = 14,
            name = "Salle 26B",
            category = "Salle de cours",
            maxCapacity = 15,
            resources = listOf("1 vidéoprojecteur", "1 tableau blanc", "8 prises", "1 télé"),
            description = "Salle jumelle de la 26A",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 32,
                    spaceId = 14,
                    userId = 2,
                    attendeesCount = 12,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[1]
                ),
                Reservation(
                    id = 33,
                    spaceId = 14,
                    userId = 3,
                    attendeesCount = 10,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[2]
                )
            )
        ),
        Space(
            id = 15,
            name = "Salle 27",
            category = "Salle de cours",
            maxCapacity = 28,
            resources = listOf("1 vidéoprojecteur", "2 enceintes", "1 tableau blanc", "14 prises", "1 télé"),
            description = "Salle standard équipée",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 34,
                    spaceId = 15,
                    userId = 1,
                    attendeesCount = 20,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 35,
                    spaceId = 15,
                    userId = 2,
                    attendeesCount = 18,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                ),
                Reservation(
                    id = 36,
                    spaceId = 15,
                    userId = 3,
                    attendeesCount = 22,
                    startAt = "2025-10-31T10:00:00",
                    endAt = "2025-10-31T11:00:00",
                    status = StatusEnum.CANCELLED,
                    space = null,
                    user = users[2]
                )
            )
        ),
        Space(
            id = 16,
            name = "Salle 3",
            category = "Salle de cours",
            maxCapacity = 20,
            resources = listOf("1 tableau blanc", "wifi", "10 prises", "1 télé"),
            description = "Salle classique",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 37,
                    spaceId = 16,
                    userId = 1,
                    attendeesCount = 5,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 38,
                    spaceId = 16,
                    userId = 2,
                    attendeesCount = 6,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 17,
            name = "Salle 4",
            category = "Salle de cours",
            maxCapacity = 22,
            resources = listOf("1 vidéoprojecteur", "1 tableau blanc", "12 prises", "1 télé"),
            description = "Salle équipée pour présentations",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 39,
                    spaceId = 17,
                    userId = 3,
                    attendeesCount = 10,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[2]
                ),
                Reservation(
                    id = 40,
                    spaceId = 17,
                    userId = 1,
                    attendeesCount = 12,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 41,
                    spaceId = 17,
                    userId = 2,
                    attendeesCount = 14,
                    startAt = "2025-10-31T10:00:00",
                    endAt = "2025-10-31T11:00:00",
                    status = StatusEnum.CANCELLED,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 18,
            name = "Salle 5",
            category = "Salle de cours",
            maxCapacity = 18,
            resources = listOf("1 paperboard", "2 enceintes", "8 prises", "1 télé"),
            description = "Salle pour ateliers",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 42,
                    spaceId = 18,
                    userId = 1,
                    attendeesCount = 5,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 43,
                    spaceId = 18,
                    userId = 2,
                    attendeesCount = 6,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 19,
            name = "Salle 6",
            category = "Salle de cours",
            maxCapacity = 20,
            resources = listOf("1 vidéoprojecteur", "wifi", "1 tableau blanc", "14 prises", "1 télé"),
            description = "Salle de cours hybride",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 44,
                    spaceId = 19,
                    userId = 3,
                    attendeesCount = 10,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[2]
                ),
                Reservation(
                    id = 45,
                    spaceId = 19,
                    userId = 1,
                    attendeesCount = 12,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 46,
                    spaceId = 19,
                    userId = 2,
                    attendeesCount = 14,
                    startAt = "2025-10-31T10:00:00",
                    endAt = "2025-10-31T11:00:00",
                    status = StatusEnum.CANCELLED,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 20,
            name = "Salle 7",
            category = "Salle de cours",
            maxCapacity = 25,
            resources = listOf("1 tableau blanc", "12 prises", "1 télé"),
            description = "Salle polyvalente",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 47,
                    spaceId = 20,
                    userId = 1,
                    attendeesCount = 10,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 48,
                    spaceId = 20,
                    userId = 2,
                    attendeesCount = 12,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                ),
                Reservation(
                    id = 49,
                    spaceId = 20,
                    userId = 3,
                    attendeesCount = 14,
                    startAt = "2025-10-31T10:00:00",
                    endAt = "2025-10-31T11:00:00",
                    status = StatusEnum.CANCELLED,
                    space = null,
                    user = users[2]
                )
            )
        ),
        Space(
            id = 21,
            name = "Salle 7B",
            category = "Salle de cours",
            maxCapacity = 14,
            resources = listOf("1 écran plat", "1 câble HDMI", "1 tableau blanc", "6 prises", "1 télé"),
            description = "Petite salle interactive",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 50,
                    spaceId = 21,
                    userId = 1,
                    attendeesCount = 5,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 51,
                    spaceId = 21,
                    userId = 2,
                    attendeesCount = 6,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 22,
            name = "Amphithéatre",
            category = "Amphithéatre",
            maxCapacity = 120,
            resources = listOf("2 vidéoprojecteurs", "6 micros", "8 enceintes", "sonorisation", "40 prises"),
            description = "Grande salle pour conférences et événements",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 52,
                    spaceId = 22,
                    userId = 3,
                    attendeesCount = 50,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[2]
                ),
                Reservation(
                    id = 53,
                    spaceId = 22,
                    userId = 1,
                    attendeesCount = 60,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 54,
                    spaceId = 22,
                    userId = 2,
                    attendeesCount = 70,
                    startAt = "2025-10-31T10:00:00",
                    endAt = "2025-10-31T11:00:00",
                    status = StatusEnum.CANCELLED,
                    space = null,
                    user = users[1]
                )
            )
        ),
        Space(
            id = 23,
            name = "CDI",
            category = "Bureau",
            maxCapacity = 12,
            resources = listOf("2 ordinateurs fixes", "1 imprimante", "wifi", "20 prises", "1 télé"),
            description = "Centre de documentation et d'information",
            photoUrl = null,
            isActive = true,
            reservations = listOf(
                Reservation(
                    id = 55,
                    spaceId = 23,
                    userId = 1,
                    attendeesCount = 3,
                    startAt = "2025-10-31T08:00:00",
                    endAt = "2025-10-31T09:00:00",
                    status = StatusEnum.CONFIRMED,
                    space = null,
                    user = users[0]
                ),
                Reservation(
                    id = 56,
                    spaceId = 23,
                    userId = 2,
                    attendeesCount = 2,
                    startAt = "2025-10-31T09:00:00",
                    endAt = "2025-10-31T10:00:00",
                    status = StatusEnum.PENDING,
                    space = null,
                    user = users[1]
                )
            )
        )
    )
}