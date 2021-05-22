package com.example.mafia.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mafia.R
import com.example.mafiaapp.data.Player
import kotlinx.android.synthetic.main.player_in_game.view.*
import kotlinx.android.synthetic.main.player_item.view.tvNickname
import kotlin.random.Random

/**
 * Adapter for recycler view of players in current game
 */
class GameAdapter : RecyclerView.Adapter<GameAdapter.MyViewHolder>() {

    private var playerList = emptyList<Player>()
    private var roles = MutableList(10) { "Citizen" }

    /**
     * Provide a reference to the MyViewHolder
     */
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        var random: Int
        val activeRoles: MutableList<Int> = mutableListOf()

        // Generates 4 places from 1 to 10 on which players will have active roles
        while (activeRoles.size < 4) {
            random = Random.nextInt(0, 10)
            if (!activeRoles.contains(random)) {
                activeRoles.add(random)
            }
        }

        // Setting serif
        random = Random.nextInt(0, 4)
        this.roles[activeRoles[random]] = "Serif"
        activeRoles.remove(activeRoles[random])

        // Setting Don
        random = Random.nextInt(0, 3)
        this.roles[activeRoles[random]] = "Don"
        activeRoles.remove(activeRoles[random])

        // Setting Mafia
        for (role in activeRoles) {
            this.roles[role] = "Mafia"
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.player_in_game, parent, false)
        )
    }

    // Return the size of player dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return playerList.size
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = playerList[position]
        val place: String = "${position + 1}."
        holder.itemView.tvPlace.text = place
        holder.itemView.tvNickname.text = currentItem.nickname
        holder.itemView.tvRole.text = this.roles[position]

        // Sets avatars for players in game based on their role
        when (this.roles[position]) {
            "Don" -> holder.itemView.ivAvatar.setImageResource(R.drawable.don)
            "Mafia" -> holder.itemView.ivAvatar.setImageResource(R.drawable.mafia)
            else -> {
                when (Random.nextInt(1, 6)) {
                    1 -> holder.itemView.ivAvatar.setImageResource(R.drawable.c1)
                    2 -> holder.itemView.ivAvatar.setImageResource(R.drawable.c2)
                    3 -> holder.itemView.ivAvatar.setImageResource(R.drawable.c3)
                    4 -> holder.itemView.ivAvatar.setImageResource(R.drawable.c4)
                    5 -> holder.itemView.ivAvatar.setImageResource(R.drawable.c5)
                }
            }
        }
    }

    /**
     * Function for setting data to the players dataset
     */
    fun setData(players: List<Player>) {
        this.playerList = players

        // Shuffles players in game
        this.playerList = this.playerList.shuffled()
        this.playerList = this.playerList.take(10)
        notifyDataSetChanged()
    }

    /**
     * Function for getting players in game
     * @return list of players in game [List<Player>]
     */
    fun getPlayers(): List<Player> {
        return this.playerList
    }

    /**
     * Function for getting players roles in game
     * @return list of roles [List<String>]
     */
    fun getRoles(): List<String> {
        return this.roles
    }
}