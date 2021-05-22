package com.example.mafiaapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Club entity in database
 */
@Parcelize
@Entity(tableName = "club_table")
data class Club(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val name: String
) : Parcelable
