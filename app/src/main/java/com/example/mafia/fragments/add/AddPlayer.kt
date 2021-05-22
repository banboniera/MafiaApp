package com.example.mafia.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mafia.R
import com.example.mafia.viewmodel.PlayerViewModel
import com.example.mafiaapp.data.Player
import kotlinx.android.synthetic.main.fragment_add_club.view.*
import kotlinx.android.synthetic.main.fragment_add_player.*

/**
 * Fragment for adding player
 */
class AddPlayer : Fragment() {

    private val args by navArgs<AddPlayerArgs>()
    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_player, container, false)

        //View model of club
        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

        //Button add on click listener for add player to database
        view.btn_add.setOnClickListener {
            addPlayerToDatabase()
        }

        return view
    }

    /**
     * Function for adding player to database
     */
    private fun addPlayerToDatabase() {
        val nickname = etAddNickname.text.toString()
        val firstName = etAddFirstName.text.toString()
        val lastName = etAddLastName.text.toString()

        if (inputCheck(nickname)) {
            // Create Player object
            val player = Player(0, args.club.id, nickname, firstName, lastName)

            // Add Player to Database
            playerViewModel.addPlayer(player)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            // Navigate Back
            val action = AddPlayerDirections.actionAddPlayerToListOfPlayers(args.club)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Please fill out nickname field.", Toast.LENGTH_LONG)
                .show()
        }
    }

    /**
     * Function for checking input
     * @return Is input correct [Boolean]
     */
    private fun inputCheck(nickname: String): Boolean {
        return !(TextUtils.isEmpty(nickname))
    }

}