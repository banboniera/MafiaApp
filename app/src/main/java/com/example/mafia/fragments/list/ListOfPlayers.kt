package com.example.mafia.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mafia.R
import com.example.mafia.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_list_of_players.view.*

/**
 * Fragment for list of players
 */
class ListOfPlayers : Fragment() {

    private val args by navArgs<ListOfPlayersArgs>()
    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_of_players, container, false)

        //Sets a label of navigation to Club name
        findNavController().currentDestination?.label = args.club.name + " players"

        // Recyclerview
        val adapter = ListPlayerAdapter(args.club)
        val recyclerView = view.rvPlayers
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // PlayerViewModel
        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        playerViewModel.readPlayersInClub(args.club.id)
            .observe(viewLifecycleOwner, Observer { player ->
                adapter.setData(player)
            })

        // Adds on click listener to the plus button for adding new player
        view.fabAddPlayer.setOnClickListener {
            val action = ListOfPlayersDirections.actionListOfPlayersToAddPlayer(args.club)
            findNavController().navigate(action)
        }

        // Adds on click listener to the start game button for starting new game
        view.btn_start_game.setOnClickListener {
            val action = ListOfPlayersDirections.actionListOfPlayersToGameFragment(
                adapter.getPlayers(),
                args.club
            )
            findNavController().navigate(action)
        }

        return view
    }

}