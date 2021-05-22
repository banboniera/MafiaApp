package com.example.mafiaapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mafiaapp.data.Club
import com.example.mafiaapp.data.Player

@Database(
    entities = [
        Club::class,
        Player::class
    ],
    version = 1
)
/**
 * Class which implements database
 */
abstract class MafiaDatabase : RoomDatabase() {

    abstract fun mafiaDao(): MafiaDao

    companion object {
        @Volatile
        private var INSTANCE: MafiaDatabase? = null

        /**
         * Function for getting database instance
         * @return instance of database [MafiaDatabase]
         */
        fun getDatabase(context: Context): MafiaDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                synchronized(this) {
                    return INSTANCE ?: Room.databaseBuilder(
                        context.applicationContext,
                        MafiaDatabase::class.java,
                        "mafia_db"
                    ).build().also {
                        INSTANCE = it
                    }
                }
            }
        }
    }
}