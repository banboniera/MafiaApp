package com.example.mafia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mafia.repository.PlayerRepository
import com.example.mafiaapp.MafiaDatabase
import com.example.mafiaapp.data.Club
import com.example.mafiaapp.data.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View model for preparing club for viewing
 */
class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PlayerRepository

    init {
        val mafiaDao = MafiaDatabase.getDatabase(application).mafiaDao()
        repository = PlayerRepository(mafiaDao)
    }

    fun addPlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlayer(player)
        }
    }

    fun updatePlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePlayer(player)
        }
    }

    fun deletePlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePlayer(player)
        }
    }

    fun readPlayersInClub(clubId: Int): LiveData<List<Player>> {
        return repository.readPlayersInClub(clubId)
    }

}