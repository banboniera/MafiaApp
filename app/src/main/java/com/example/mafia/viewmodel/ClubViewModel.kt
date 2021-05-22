package com.example.mafia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mafia.repository.ClubRepository
import com.example.mafiaapp.MafiaDatabase
import com.example.mafiaapp.data.Club
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View model for preparing club for viewing
 */
class ClubViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Club>>
    private val repository: ClubRepository

    init {
        val mafiaDao = MafiaDatabase.getDatabase(application).mafiaDao()
        repository = ClubRepository(mafiaDao)
        readAllData = repository.readAllData
    }

    fun addClub(club: Club) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addClub(club)
        }
    }

    fun updateClub(club: Club) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateClub(club)
        }
    }

    fun deleteClub(club: Club) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteClub(club)
        }
    }

    fun deleteAllPlayersInClub(clubId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllPlayersInClub(clubId)
        }
    }

}