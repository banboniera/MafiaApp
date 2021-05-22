package com.example.mafia.repository

import androidx.lifecycle.LiveData
import com.example.mafiaapp.MafiaDao
import com.example.mafiaapp.data.Club

/**
 * Repository for handling operations with club
 */
class ClubRepository(private val mafiaDao: MafiaDao) {

    val readAllData: LiveData<List<Club>> = mafiaDao.readAllClubs()

    suspend fun addClub(club: Club) {
        mafiaDao.addClub(club)
    }

    suspend fun updateClub(club: Club) {
        mafiaDao.updateClub(club)
    }

    suspend fun deleteClub(club: Club){
        mafiaDao.deleteClub(club)
    }

    suspend fun deleteAllPlayersInClub(clubId: Int){
        mafiaDao.deleteAllPlayersInClub(clubId)
    }

}