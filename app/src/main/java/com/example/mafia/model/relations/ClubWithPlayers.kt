package com.example.mafiaapp.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Relation between Club and Player
 */
data class ClubWithPlayers(
        @Embedded val club: Club,
        @Relation(
                parentColumn = "id",
                entityColumn = "clubId"
        )
        val players: List<Player>
)