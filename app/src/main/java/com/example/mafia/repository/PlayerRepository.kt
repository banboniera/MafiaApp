package com.example.mafia.repository

import androidx.lifecycle.LiveData
import com.example.mafiaapp.MafiaDao
import com.example.mafiaapp.data.Player

/**
 * Repository for handling operations with player
 */
class PlayerRepository(private val mafiaDao: MafiaDao) {

    suspend fun addPlayer(player: Player) {
        mafiaDao.addPlayer(player)
    }

    suspend fun updatePlayer(player: Player) {
        mafiaDao.updatePlayer(player)
    }

    suspend fun deletePlayer(player: Player) {
        mafiaDao.deletePlayer(player)
    }

    fun readPlayersInClub(clubId: Int): LiveData<List<Player>> {
        return mafiaDao.readPlayersInClub(clubId)
    }
}