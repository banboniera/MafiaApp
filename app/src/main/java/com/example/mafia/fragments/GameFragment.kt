package com.example.mafia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mafia.R
import com.example.mafia.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_game.view.*

/**
 * Fragment for game
 */
class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()
    private val adapter = GameAdapter()
    private lateinit var playerViewModel: PlayerViewModel

    //Checks if there is enough players for game
    override fun onCreate(savedInstanceState: Bundle?) {
        if (args.players.size < 10) {
            Toast.makeText(
                requireContext(),
                "Please choose at least ten players.",
                Toast.LENGTH_LONG
            ).show()
            val action = GameFragmentDirections.actionGameFragmentToListOfPlayers(args.club)
            findNavController().navigate(action)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        //Sets a label of navigation to Game of and Club name
        findNavController().currentDestination?.label = "Game of " + args.club.name

        // Recyclerview
        val recyclerView = view.rvPlayersInGame
        recyclerView.adapter = this.adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // PlayerViewModel
        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

        adapter.setData(args.players.toList())

        // Adds on click listener to win of citizens
        view.btn_citizen_won.setOnClickListener {
            checkResults("Citizens")
            val action = GameFragmentDirections.actionGameFragmentToListOfPlayers(args.club)
            findNavController().navigate(action)
        }

        // Adds on click listener to win of mafia
        view.btn_mafia_won.setOnClickListener {
            checkResults("Mafia")
            val action = GameFragmentDirections.actionGameFragmentToListOfPlayers(args.club)
            findNavController().navigate(action)
        }

        return view
    }

    /**
     * Function for adding results of the game to database
     * @property [team] name of team which won
     */
    private fun checkResults(team: String) {
        val players = adapter.getPlayers()
        val roles = adapter.getRoles()
        when (team) {
            "Mafia" -> {
                for ((i, role) in roles.withIndex()) {
                    when (role) {
                        "Mafia" -> {
                            players[i].mafiaWin += 1
                            playerViewModel.updatePlayer(players[i])
                        }
                        "Don" -> {
                            players[i].donWin += 1
                            playerViewModel.updatePlayer(players[i])
                        }
                        "Citizen" -> {
                            players[i].citizenLose += 1
                            playerViewModel.updatePlayer(players[i])
                        }
                        "Serif" -> {
                            players[i].serifLose += 1
                            playerViewModel.updatePlayer(players[i])
                        }
                    }
                }
            }
            "Citizens" -> {
                for ((i, role) in roles.withIndex()) {
                    when (role) {
                        "Citizen" -> {
                            players[i].citizenWin += 1
                            playerViewModel.updatePlayer(players[i])
                        }
                        "Serif" -> {
                            players[i].serifWin += 1
                            playerViewModel.updatePlayer(players[i])
                        }
                        "Mafia" -> {
                            players[i].mafiaLose += 1
                            playerViewModel.updatePlayer(players[i])
                        }
                        "Don" -> {
                            players[i].donLose += 1
                            playerViewModel.updatePlayer(players[i])
                        }
                    }
                }
            }
        }
    }
}