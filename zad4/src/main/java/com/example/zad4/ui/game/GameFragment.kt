package com.example.zad4.ui.game

import android.media.AudioManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.zad4.R
import com.example.zad4.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private var cannonView // custom view to display the game
            : CannonView? = null
    private var binding: FragmentGameBinding? = null

    // called when Fragment's view needs to be created
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentGameBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val view: View = inflater.inflate(R.layout.fragment_game, container, false)
        requireActivity().volumeControlStream = AudioManager.STREAM_MUSIC
        // get a reference to the CannonView
        cannonView = view.findViewById<View>(R.id.cannonView) as CannonView
        requireActivity().volumeControlStream = AudioManager.STREAM_MUSIC
        return view
    }

    // when MainActivity is paused, terminate the game
    override fun onPause() {
        super.onPause()
        cannonView!!.stopGame() // terminates the game
    }

    // when MainActivity is paused, MainActivityFragment releases resources
    override fun onDestroy() {
        super.onDestroy()
        cannonView!!.releaseResources()
    }
}