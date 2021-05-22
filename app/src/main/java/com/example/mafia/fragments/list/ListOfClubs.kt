package com.example.mafia.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mafia.R
import com.example.mafia.viewmodel.ClubViewModel
import kotlinx.android.synthetic.main.fragment_list_of_clubs.view.*

/**
 * Fragment for list of clubs
 */
class ListOfClubs : Fragment() {

    private lateinit var clubViewModel: ClubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_of_clubs, container, false)

        // Recyclerview
        val adapter = ListClubAdapter()
        val recyclerView = view.rvClubs
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ClubViewModel
        clubViewModel = ViewModelProvider(this).get(ClubViewModel::class.java)
        clubViewModel.readAllData.observe(viewLifecycleOwner, Observer { club ->
            adapter.setData(club)
        })

        // Adds on click listener to the plus button for adding new club
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listOfClubs_to_addClub)
        }

        return view
    }

}