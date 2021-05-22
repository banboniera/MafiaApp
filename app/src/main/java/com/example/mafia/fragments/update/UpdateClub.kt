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
import com.example.mafia.viewmodel.ClubViewModel
import com.example.mafiaapp.data.Club
import kotlinx.android.synthetic.main.fragment_update_club.*
import kotlinx.android.synthetic.main.fragment_update_club.view.*

/**
 * Fragment for updating club
 */
class UpdateClub : Fragment() {

    private val args by navArgs<UpdateClubArgs>()
    private lateinit var clubViewModel: ClubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_club, container, false)

        //Sets a label of navigation to Club name
        findNavController().currentDestination?.label = args.currentClub.name

        clubViewModel = ViewModelProvider(this).get(ClubViewModel::class.java)
        view.etUpdateClubName.setText(args.currentClub.name)

        // Adds on click listener to update button for updating club
        view.btn_update.setOnClickListener {
            updateClub()
        }


        // Adds on click listener to start button for starting interacting with club
        view.btn_start.setOnClickListener {
            val action =
                UpdateClubDirections.actionUpdateClubFragmentToListOfPlayers(args.currentClub)
            findNavController().navigate(action)
        }

        // Adds on click listener to start button for showing statistics of players in club
        view.btn_stats.setOnClickListener {
            val action = UpdateClubDirections.actionUpdateClubToStatsFragment(args.currentClub)
            findNavController().navigate(action)
        }

        setHasOptionsMenu(true)

        return view
    }

    /**
     * Function for updating club
     */
    private fun updateClub() {
        val name = etUpdateClubName.text.toString()

        if (inputCheck(name)) {
            // Create Club object
            val updateClub = Club(args.currentClub.id, name)

            // Update Club in Database
            clubViewModel.updateClub(updateClub)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()

            // Navigate Back
            findNavController().navigate(R.id.action_updateClubFragment_to_listOfClubs)
        } else {
            Toast.makeText(requireContext(), "Please enter name of club!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    /**
     * Function for checking input
     * @return Is input correct [Boolean]
     */
    private fun inputCheck(name: String): Boolean {
        return !(TextUtils.isEmpty(name))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteClub()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteClub() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            clubViewModel.deleteAllPlayersInClub(args.currentClub.id)
            clubViewModel.deleteClub(args.currentClub)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentClub.name}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateClubFragment_to_listOfClubs)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Are you sure you want to delete ${args.currentClub.name}")
        builder.create().show()
    }
}