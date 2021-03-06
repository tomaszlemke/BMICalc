package com.example.zad3.ui.game;

import android.media.AudioManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zad3.R;
import com.example.zad3.databinding.FragmentGameBinding;

public class GameFragment extends Fragment {

    private CannonView cannonView; // custom view to display the game
    private FragmentGameBinding binding;
    // called when Fragment's view needs to be created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = FragmentGameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View view =
                inflater.inflate(R.layout.fragment_game, container, false);
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
        // get a reference to the CannonView
        cannonView = (CannonView) view.findViewById(R.id.cannonView);
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);

        return view;
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
    @Override
    public void onPause() {
        super.onPause();
        cannonView.stopGame(); // terminates the game
    }

    // when MainActivity is paused, MainActivityFragment releases resources
    @Override
    public void onDestroy() {
        super.onDestroy();
        cannonView.releaseResources();
    }
}

/*********************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and * Pearson Education, *
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this   *
 * book have used their * best efforts in preparing the book. These efforts      *
 * include the * development, research, and testing of the theories and programs *
 * * to determine their effectiveness. The authors and publisher make * no       *
 * warranty of any kind, expressed or implied, with regard to these * programs   *
 * or to the documentation contained in these books. The authors * and publisher *
 * shall not be liable in any event for incidental or * consequential damages in *
 * connection with, or arising out of, the * furnishing, performance, or use of  *
 * these programs.                                                               *
 *********************************************************************************/
