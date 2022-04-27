package com.example.zad4.ui.recipes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zad4.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {
    private var binding: FragmentRecipesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val recipesViewModel: com.example.zad4.ui.recipes.RecipesViewModel =
            ViewModelProvider(this).get(
                com.example.zad4.ui.recipes.RecipesViewModel::class.java
            )
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()

        // setting URL for the buttons
        val breakfastAndBrunch =
            Uri.parse("https://www.allrecipes.com/recipes/78/breakfast-and-brunch/")
        val snacks = Uri.parse("https://www.allrecipes.com/recipes/76/appetizers-and-snacks/")
        val mainDishes = Uri.parse("https://www.allrecipes.com/recipes/80/main-dish/")
        val quickEasy =
            Uri.parse("https://www.allrecipes.com/recipes/1947/everyday-cooking/quick-and-easy/")
        val desserts = Uri.parse("https://www.allrecipes.com/recipes/79/desserts/")

        // binding all buttons with listeners and URL's
        val button: Button = binding!!.breakfast
        button.setOnClickListener {
            val webIntent =
                Intent(Intent.ACTION_VIEW, breakfastAndBrunch)
            startActivity(webIntent)
        }
        val button2: Button = binding!!.snacks
        button2.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, snacks)
            startActivity(webIntent)
        }
        val button3: Button = binding!!.mainDishes
        button3.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, mainDishes)
            startActivity(webIntent)
        }
        val button4: Button = binding!!.quickEasy
        button4.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, quickEasy)
            startActivity(webIntent)
        }
        val button5: Button = binding!!.desserts
        button5.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, desserts)
            startActivity(webIntent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}