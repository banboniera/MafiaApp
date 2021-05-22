package com.example.mafia.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mafia.R
import com.example.mafiaapp.data.Club
import kotlinx.android.synthetic.main.club_item.view.*

/**
 * Adapter for recycler view of clubs list
 */
class ListClubAdapter : RecyclerView.Adapter<ListClubAdapter.MyViewHolder>() {

    private var clubList = emptyList<Club>()

    /**
     * Provide a reference to the MyViewHolder
     */
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.club_item, parent, false)
        )
    }

    // Return the size of club dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return clubList.size
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = clubList[position]
        holder.itemView.tvName.text = currentItem.name

        // Adds on click listener to the items in dataset for navigating to the club details
        holder.itemView.rowLayout.setOnClickListener {
            val action = ListOfClubsDirections.actionListOfClubsToUpdateClubFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    /**
     * Function for setting data to the clubs dataset
     */
    fun setData(clubs: List<Club>) {
        this.clubList = clubs
        notifyDataSetChanged()
    }
}