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
            : com.example.zad4.ui.game.CannonView? = null
    private var binding: FragmentGameBinding? = null

    // called when Fragment's view needs to be created
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentGameBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()
        val view: View = inflater.inflate(R.layout.fragment_game, container, false)
        requireActivity().volumeControlStream = AudioManager.STREAM_MUSIC
        // get a reference to the CannonView
        cannonView = view.findViewById<View>(R.id.cannonView) as com.example.zad4.ui.game.CannonView
        requireActivity().volumeControlStream = AudioManager.STREAM_MUSIC
        return view
    }

    // set up volume control once Activity is created
    //    @Override
    //    public void onActivityCreated(Bundle savedInstanceState) {
    //        super.onActivityCreated(savedInstanceState);
    //
    //        // allow volume buttons to set game volume
    //        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
    //    }
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