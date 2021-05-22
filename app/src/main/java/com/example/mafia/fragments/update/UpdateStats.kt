package com.example.mafia.fragments.update

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
import kotlinx.android.synthetic.main.fragment_update_player.view.btn_update_player
import kotlinx.android.synthetic.main.fragment_update_stats.*
import kotlinx.android.synthetic.main.fragment_update_stats.view.*


class UpdateStats : Fragment() {

    private val args by navArgs<UpdateStatsArgs>()
    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_stats, container, false)

        findNavController().currentDestination?.label = args.player.nickname

        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

        view.etCitizenWin.setText(args.player.citizenWin.toString())
        view.etCitizenLose.setText(args.player.citizenLose.toString())
        view.etSerifWin.setText(args.player.serifWin.toString())
        view.etSerifLose.setText(args.player.serifLose.toString())
        view.etMafiaWin.setText(args.player.mafiaWin.toString())
        view.etMafiaLose.setText(args.player.mafiaLose.toString())
        view.etDonWin.setText(args.player.donWin.toString())
        view.etDonLose.setText(args.player.donLose.toString())
        view.btn_update_player.setOnClickListener {
            updatePlayer()
        }

        return view
    }

    private fun updatePlayer() {
        val citizenWin = Integer.parseInt(etCitizenWin.text.toString())
        val citizenLose = Integer.parseInt(etCitizenLose.text.toString())
        val serifWin = Integer.parseInt(etSerifWin.text.toString())
        val serifLose = Integer.parseInt(etSerifLose.text.toString())
        val mafiaWin = Integer.parseInt(etMafiaWin.text.toString())
        val mafiaLose = Integer.parseInt(etMafiaLose.text.toString())
        val donWin = Integer.parseInt(etDonWin.text.toString())
        val donLose = Integer.parseInt(etDonLose.text.toString())

        if (inputCheck(
                citizenWin,
                citizenLose,
                serifWin,
                serifLose,
                mafiaWin,
                mafiaLose,
                donWin,
                donLose
            )
        ) {
            val updatePlayer = Player(
                args.player.id,
                args.player.clubId,
                args.player.nickname,
                args.player.firstName,
                args.player.lastName,
                citizenWin,
                citizenLose,
                serifWin,
                serifLose,
                mafiaWin,
                mafiaLose,
                donWin,
                donLose
            )
            playerViewModel.updatePlayer(updatePlayer)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            val action = UpdateStatsDirections.actionUpdateStatsToStatsFragment(args.club)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Please enter name of club!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(
        citizenWin: Int,
        citizenLose: Int,
        serifWin: Int,
        serifLose: Int,
        mafiaWin: Int,
        mafiaLose: Int,
        donWin: Int,
        donLose: Int
    ): Boolean {
        return citizenWin > 0 && citizenLose > 0  && serifWin > 0 && serifLose > 0 && mafiaWin > 0 && mafiaLose > 0 && donWin > 0 && donLose > 0
    }

}