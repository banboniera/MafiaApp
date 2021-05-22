package com.example.mafiaapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Player entity in database
 */
@Parcelize
@Entity(tableName = "player_table")
data class Player(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val clubId: Int = 0,
        val nickname: String = "",
        val firstName: String = "",
        val lastName: String = "",
        var citizenWin: Int = 0,
        var citizenLose: Int = 0,
        var serifWin: Int = 0,
        var serifLose: Int = 0,
        var mafiaWin: Int = 0,
        var mafiaLose: Int = 0,
        var donWin: Int = 0,
        var donLose: Int = 0

) : Parcelable