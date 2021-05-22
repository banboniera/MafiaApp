package com.example.mafia.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mafia.R
import com.example.mafia.viewmodel.PlayerViewModel
import com.example.mafiaapp.data.Player
import kotlinx.android.synthetic.main.fragment_update_player.*
import kotlinx.android.synthetic.main.fragment_update_player.view.*


class UpdatePlayerFragment : Fragment() {

    private val args by navArgs<UpdatePlayerFragmentArgs>()
    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_player, container, false)

        findNavController().currentDestination?.label = args.currentPlayer.nickname

        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

        view.etUpdateNickname.setText(args.currentPlayer.nickname)
        view.etUpdateFirstName.setText(args.currentPlayer.firstName)
        view.etUpdateLastName.setText(args.currentPlayer.lastName)
        view.btn_update_player.setOnClickListener {
            updatePlayer()
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deletePlayer()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updatePlayer() {
        val nickname = etUpdateNickname.text.toString()
        val firstName = etUpdateFirstName.text.toString()
        val lastName = etUpdateLastName.text.toString()

        if (inputCheck(nickname)) {
            val updatePlayer = Player(
                args.currentPlayer.id,
                args.currentPlayer.clubId,
                nickname,
                firstName,
                lastName
            )
            playerViewModel.updatePlayer(updatePlayer)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            val action = UpdatePlayerFragmentDirections.actionUpdatePlayerToListOfPlayers(args.club)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Please enter name of club!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(nickname: String): Boolean {
        return !(TextUtils.isEmpty(nickname))
    }

    private fun deletePlayer() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            playerViewModel.deletePlayer(args.currentPlayer)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentPlayer.nickname}",
                Toast.LENGTH_SHORT
            ).show()
            val action = UpdatePlayerFragmentDirections.actionUpdatePlayerToListOfPlayers(args.club)
            findNavController().navigate(action)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Are you sure you want to delete ${args.currentPlayer.nickname}")
        builder.create().show()
    }

}