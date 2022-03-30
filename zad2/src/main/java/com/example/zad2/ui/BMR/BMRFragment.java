package com.example.zad2.ui.BMR;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zad2.databinding.FragmentBmrBinding;

public class BMRFragment extends Fragment {

    private FragmentBmrBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BMRViewModel BMRViewModel =
                new ViewModelProvider(this).get(BMRViewModel.class);

        binding = FragmentBmrBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.;
//        BMRViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}