package com.example.zad2.ui.BMR;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zad2.databinding.FragmentBmrBinding;

import java.text.NumberFormat;

public class BMRFragment extends Fragment {

    private FragmentBmrBinding binding;

    private static final NumberFormat numberFormat =
            NumberFormat.getNumberInstance();

    private double weight = 0.0; // weight amount entered by the user
    private double height = 0.0; // height amount entered by the user
    private int age = 0;
    private TextView weightTextView; // shows formatted weight
    private TextView heightTextView; // shows formatted height
    private TextView BMRTextView; // shows calculated BMI
    private TextView ageTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BMRViewModel BMRViewModel =
                new ViewModelProvider(this).get(BMRViewModel.class);

        binding = FragmentBmrBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        weightTextView = binding.weightTextView;
        heightTextView = binding.heightTextView;
        ageTextView = binding.ageTextView;
        BMRTextView = binding.bmrTextView;

        EditText weightEditText = binding.weightEditText;
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        EditText heightEditText = binding.heightEditText;
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        EditText ageEditText = binding.ageEditText;
        ageEditText.addTextChangedListener(ageEditTextWatcher);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void calculate() {
        // calculate the BMI
        double BMR = 66.5 + (13.75 * weight) + (5.003 * height * 100) - (6.75 * age);

        double BMR2 = 655.1 + (9.563 * weight) + (1.850 * height * 100) - (4.676 * age);

        // display BMI formatted as number with 2 fraction digits
        numberFormat.setMaximumFractionDigits(2);
        BMRTextView.setText(numberFormat.format(BMR));
    }


    // listener object for the EditText's text-changed events
    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        // called when the user modifies the weight
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get BMI and display double formatted value
                weight = Double.parseDouble(s.toString()) ;
                weightTextView.setText(numberFormat.format(weight));

            } catch (NumberFormatException e) { // if s is empty or non-numeric
                weightTextView.setText("");
                weight = 0.0;
            }

            calculate(); // update the BMI TextViews
        }


        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };

    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        // called when the user modifies the height
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get BMI amount and display double formatted value
                height = Double.parseDouble(s.toString())/100;
                heightTextView.setText(numberFormat.format(height));

            } catch (NumberFormatException e) { // if s is empty or non-numeric
                heightTextView.setText("");
                height = 0.0;
            }

            calculate(); // update the BMI TextViews
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }

    };

    private final TextWatcher ageEditTextWatcher = new TextWatcher() {
        // called when the user modifies the height
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get BMI amount and display double formatted value
                age = Integer.parseInt(s.toString());
                ageTextView.setText(numberFormat.format(age));

            } catch (NumberFormatException e) { // if s is empty or non-numeric
                ageTextView.setText("");
                age = 0;
            }

            calculate(); // update the BMI TextViews
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }

    };

}