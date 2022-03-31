package com.example.zad2.ui.recipes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zad2.databinding.FragmentRecipesBinding;

public class RecipesFragment extends Fragment {

    private FragmentRecipesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecipesViewModel notificationsViewModel =
                new ViewModelProvider(this).get(RecipesViewModel.class);

        binding = FragmentRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Uri breakfastAndBrunch = Uri.parse("https://www.allrecipes.com/recipes/78/breakfast-and-brunch/");
        Uri snacks = Uri.parse("https://www.allrecipes.com/recipes/76/appetizers-and-snacks/");
        Uri mainDishes = Uri.parse("https://www.allrecipes.com/recipes/80/main-dish/");
        Uri quickEasy = Uri.parse("https://www.allrecipes.com/recipes/1947/everyday-cooking/quick-and-easy/");
        Uri desserts = Uri.parse("https://www.allrecipes.com/recipes/79/desserts/");

        final Button button = binding.breakfast;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, breakfastAndBrunch);
                startActivity(webIntent);
            }
        });

        final Button button2 = binding.snacks;
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, snacks);
                startActivity(webIntent);
            }
        });

        final Button button3 = binding.mainDishes;
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, mainDishes);
                startActivity(webIntent);
            }
        });

        final Button button4 = binding.quickEasy;
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, quickEasy);
                startActivity(webIntent);
            }
        });

        final Button button5 = binding.desserts;
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, desserts);
                startActivity(webIntent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}