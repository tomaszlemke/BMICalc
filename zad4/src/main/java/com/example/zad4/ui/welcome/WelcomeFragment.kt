package com.example.zad4.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.zad4.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private var binding: FragmentWelcomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val welcomeViewModel: WelcomeViewModel =
            ViewModelProvider(this)[WelcomeViewModel::class.java]

        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()
        val textView: TextView = binding!!.textWelcome

        welcomeViewModel.text.observe(
            viewLifecycleOwner,
            Observer { text: String? ->
                textView.text = text
            })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}