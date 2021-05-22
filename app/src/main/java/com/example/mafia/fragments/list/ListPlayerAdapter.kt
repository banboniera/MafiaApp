package com.example.mafia.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mafia.R
import com.example.mafiaapp.data.Club
import com.example.mafiaapp.data.Player
import kotlinx.android.synthetic.main.player_item.view.*

/**
 * Adapter for recycler view of players list
 */
class ListPlayerAdapter(private var club: Club) :
    RecyclerView.Adapter<ListPlayerAdapter.MyViewHolder>() {

    private var playerList = emptyList<Player>()
    private val playerListPlaying: MutableList<Player> = mutableListOf()

    /**
     * Provide a reference to the MyViewHolder
     */
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        )
    }

    // Return the size of players dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return playerList.size
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = playerList[position]
        holder.itemView
        holder.itemView.tvNickname.text = currentItem.nickname
        holder.itemView.apply {
            //Adds on click listener to check box in dataset for adding or removing players from current game
            cbPlaying.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    playerListPlaying.add(playerList[position])
                    Toast.makeText(context, playerListPlaying.size.toString(), Toast.LENGTH_SHORT)
                        .show()
                } else {
                    playerListPlaying.remove(playerList[position])
                }
            }
            // Adds on click listener to the items in dataset for navigating to the player details
            rowPlayer.setOnClickListener {
                val action =
                    ListOfPlayersDirections.actionListOfPlayersToUpdatePlayer(currentItem, club)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    /**
     * Function for setting data to the players dataset
     */
    fun setData(players: List<Player>) {
        this.playerList = players
        notifyDataSetChanged()
    }

    /**
     * Function for getting players for game
     * @return array of players [Array<Player>] that have been chosen for game
     */
    fun getPlayers(): Array<Player> {
        return this.playerListPlaying.toTypedArray()
    }

}