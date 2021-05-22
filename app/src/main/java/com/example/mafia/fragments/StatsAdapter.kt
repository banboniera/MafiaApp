package com.example.mafia.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mafia.R
import com.example.mafiaapp.data.Club
import com.example.mafiaapp.data.Player
import kotlinx.android.synthetic.main.stats_item.view.*

/**
 * Adapter for recycler view of players in stats fragment
 */
class StatsAdapter(private var club: Club) : RecyclerView.Adapter<StatsAdapter.MyViewHolder>() {

    private var playerList = emptyList<Player>()

    /**
     * Provide a reference to the MyViewHolder
     */
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.stats_item, parent, false)
        )
    }

    // Return the size of player dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return playerList.size
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = playerList[position]
        holder.itemView.tvNickname.text = currentItem.nickname
        var text: String =
            currentItem.citizenWin.toString() + "/" + currentItem.citizenLose.toString()
        holder.itemView.tvCitizen.text = text

        text = currentItem.serifWin.toString() + "/" + currentItem.serifLose.toString()
        holder.itemView.tvSerif.text = text

        text = currentItem.mafiaWin.toString() + "/" + currentItem.mafiaLose.toString()
        holder.itemView.tvMafia.text = text

        text = currentItem.donWin.toString() + "/" + currentItem.donLose.toString()
        holder.itemView.tvDon.text = text

        // Adds on click listener to the items in dataset for navigating to the updating player stats
        holder.itemView.rowStats.setOnClickListener {
            val action =
                StatsFragmentDirections.actionStatsFragmentToUpdateStats(currentItem, this.club)
            holder.itemView.findNavController().navigate(action)
        }
    }

    /**
     * Function for setting data to the players dataset
     */
    fun setData(players: List<Player>) {
        this.playerList = players
        notifyDataSetChanged()
    }
}