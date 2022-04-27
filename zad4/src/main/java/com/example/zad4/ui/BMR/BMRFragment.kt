package com.example.zad4.ui.BMR

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.text.NumberFormat

import com.example.zad4.databinding.FragmentBmrBinding



    class BMRFragment : Fragment() {
        private var binding: FragmentBmrBinding? = null
        private var weight = 0.0 // weight amount entered by the user
        private var height = 0.0 // height amount entered by the user
        private var age = 0
        private var weightTextView // shows formatted weight
                : TextView? = null
        private var heightTextView // shows formatted height
                : TextView? = null
        private var BMRTextView // shows calculated BMR
                : TextView? = null
        private var ageTextView // shows formatted age
                : TextView? = null
        private var maleRadioButton // shows sex option
                : RadioButton? = null
        private var femaleRadioButton // shows sex option
                : RadioButton? = null

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
        ): View? {
            val BMRViewModel: com.example.zad4.ui.BMR.BMRViewModel = ViewModelProvider(this).get(
                com.example.zad4.ui.BMR.BMRViewModel::class.java
            )
            binding = FragmentBmrBinding.inflate(inflater, container, false)
            val root: View = binding!!.getRoot()

            // binding elements
            weightTextView = binding!!.weightTextView
            heightTextView = binding!!.heightTextView
            ageTextView = binding!!.ageTextView
            BMRTextView = binding!!.bmrTextView
            maleRadioButton = binding!!.sexMale
            femaleRadioButton = binding!!.sexFemale

            // setting up the watchers for input fields
            val weightEditText: EditText = binding!!.weightEditText
            weightEditText.addTextChangedListener(weightEditTextWatcher)
            val heightEditText: EditText = binding!!.heightEditText
            heightEditText.addTextChangedListener(heightEditTextWatcher)
            val ageEditText: EditText = binding!!.ageEditText
            ageEditText.addTextChangedListener(ageEditTextWatcher)

            // setting up watchers for radio buttons
            maleRadioButton!!.setOnClickListener(genderClickListener)
            femaleRadioButton!!.setOnClickListener(genderClickListener)
            return root
        }

        override fun onDestroyView() {
            super.onDestroyView()
            binding = null
        }

        private fun calculate() {
            // calculate the BMR based on the radio button selected
            if (maleRadioButton!!.isChecked) {
                val BMR = 66.5 + 13.75 * weight + 5.003 * height * 100 - 6.75 * age
                com.example.zad4.ui.BMR.BMRFragment.Companion.numberFormat.setMaximumFractionDigits(
                    2
                )
                BMRTextView?.setText(
                    com.example.zad4.ui.BMR.BMRFragment.Companion.numberFormat.format(
                        BMR
                    )
                )
            }
            if (femaleRadioButton!!.isChecked) {
                val BMR = 655.1 + 9.563 * weight + 1.850 * height * 100 - 4.676 * age
                com.example.zad4.ui.BMR.BMRFragment.Companion.numberFormat.setMaximumFractionDigits(
                    2
                )
                BMRTextView?.setText(
                    com.example.zad4.ui.BMR.BMRFragment.Companion.numberFormat.format(
                        BMR
                    )
                )
            }
        }

        // listener object for the EditText's text-changed events
        private val weightEditTextWatcher: TextWatcher = object : TextWatcher {
            // called when the user modifies the weight
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                try { // get BMI and display double formatted value
                    weight = s.toString().toDouble()
                    weightTextView?.setText(
                        com.example.zad4.ui.BMR.BMRFragment.Companion.numberFormat.format(
                            weight
                        )
                    )
                } catch (e: NumberFormatException) { // if s is empty or non-numeric
                    weightTextView!!.text = ""
                    weight = 0.0
                }
                calculate() // update the BMI TextViews
            }

            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
            }
        }

        // listener object for the EditText's text-changed events
        private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
            // called when the user modifies the height
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                try { // get BMI amount and display double formatted value
                    height = s.toString().toDouble() / 100
                    heightTextView?.setText(
                        com.example.zad4.ui.BMR.BMRFragment.Companion.numberFormat.format(
                            height
                        )
                    )
                } catch (e: NumberFormatException) { // if s is empty or non-numeric
                    heightTextView!!.text = ""
                    height = 0.0
                }
                calculate() // update the BMI TextViews
            }

            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
            }
        }

        // listener object for the EditText's text-changed events
        private val ageEditTextWatcher: TextWatcher = object : TextWatcher {
            // called when the user modifies the height
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                try { // get BMI amount and display double formatted value
                    age = s.toString().toInt()
                    ageTextView?.setText(
                        com.example.zad4.ui.BMR.BMRFragment.Companion.numberFormat.format(
                            age.toLong()
                        )
                    )
                } catch (e: NumberFormatException) { // if s is empty or non-numeric
                    ageTextView!!.text = ""
                    age = 0
                }
                calculate() // update the BMI TextViews
            }

            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
            }
        }

        // listener object for the radio button-changed events
        private val genderClickListener = View.OnClickListener { view: View ->
            val checked = (view as RadioButton).isChecked
            if (checked) {
                calculate()
            }
        }

        companion object {
            private val numberFormat = NumberFormat.getNumberInstance()
        }
    }
