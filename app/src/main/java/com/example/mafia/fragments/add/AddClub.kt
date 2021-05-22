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
import com.example.mafia.R
import com.example.mafia.viewmodel.ClubViewModel
import com.example.mafiaapp.data.Club
import kotlinx.android.synthetic.main.fragment_add_club.*
import kotlinx.android.synthetic.main.fragment_add_club.view.*

/**
 * Fragment for adding club
 */
class AddClub : Fragment() {

    private lateinit var clubViewModel: ClubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_club, container, false)

        //View model of club
        clubViewModel = ViewModelProvider(this).get(ClubViewModel::class.java)

        //Button add on click listener for add club to database
        view.btn_add.setOnClickListener {
            addClubToDatabase()
        }

        return view
    }

    /**
     * Function for adding club to database
     */
    private fun addClubToDatabase() {
        val name = etAddClubName.text.toString()

        if (inputCheck(name)) {
            // Create Club object
            val club = Club(0, name)

            // Add Club to Database
            clubViewModel.addClub(club)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            // Navigate Back
            findNavController().navigate(R.id.action_addClub_to_listOfClubs)
        } else {
            Toast.makeText(requireContext(), "Please fill name field.", Toast.LENGTH_LONG)
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

}