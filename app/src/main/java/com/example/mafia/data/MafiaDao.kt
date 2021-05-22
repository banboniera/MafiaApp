package com.example.mafiaapp

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mafiaapp.data.Club
import com.example.mafiaapp.data.Player


/**
 * Dao interface for all actions with database entices
 */
@Dao
interface MafiaDao {

    /**
     * Adds a new [club]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addClub(club: Club)

    /**
     * Updates existing [club]
     */
    @Update
    suspend fun updateClub(club: Club)

    /**
     * Deletes [club]
     */
    @Delete
    suspend fun deleteClub(club: Club)

    /**
     * Deletes all players in club by [clubId]
     */
    @Query("DELETE FROM player_table WHERE clubId=:clubId")
    suspend fun deleteAllPlayersInClub(clubId: Int)

    /**
     * Gets all [club] in database
     * @return list of all clubs
     */
    @Query("SELECT * FROM club_table ORDER BY id ASC")
    fun readAllClubs(): LiveData<List<Club>>

    /**
     * Adds a new [player]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayer(player: Player)

    /**
     * Updates [player]
     */
    @Update
    suspend fun updatePlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    /**
     * Gets all players of specific club
     * @return list of all players in club by [clubId]
     */
    @Transaction
    @Query("SELECT * FROM player_table WHERE clubId = :clubId")
    fun readPlayersInClub(clubId: Int): LiveData<List<Player>>

}
