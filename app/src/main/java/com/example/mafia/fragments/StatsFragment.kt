package com.example.mafia.fragments

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
import kotlinx.android.synthetic.main.fragment_stats.view.*

/**
 * Fragment for stats
 */
class StatsFragment : Fragment() {

    private lateinit var playerViewModel: PlayerViewModel
    private val args by navArgs<StatsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        // Recyclerview
        val adapter = StatsAdapter(args.club)
        val recyclerView = view.rvStats
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // PlayerViewModel
        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        playerViewModel.readPlayersInClub(args.club.id)
            .observe(viewLifecycleOwner, Observer { player ->
                adapter.setData(player)
            })

        return view
    }
}