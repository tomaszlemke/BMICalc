package com.example.zad3.ui.BMI;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zad3.databinding.FragmentBmiBinding;

import java.text.NumberFormat;

public class BMIFragment extends Fragment {


    private static final NumberFormat numberFormat =
            NumberFormat.getNumberInstance();

    private double weight = 0.0; // weight amount entered by the user
    private double height = 0.0; // height amount entered by the user
    private TextView weightTextView; // shows formatted weight
    private TextView heightTextView; // shows formatted height
    private TextView BMITextView; // shows calculated BMI

    private FragmentBmiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BMIViewModel BMIViewModel =
                new ViewModelProvider(this).get(BMIViewModel.class);

        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        WebView myWebView = binding.webview;
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String unencodedHtml =
                "<html> <head> <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script> <script type=\"text/javascript\"> google.charts.load('current', {'packages':['corechart']}); google.charts.setOnLoadCallback(drawChart); function drawChart() { var data = google.visualization.arrayToDataTable([ ['Mon', 20, 28, 38, 45], ['Tue', 31, 38, 55, 66], ['Wed', 50, 55, 77, 80], ['Thu', 77, 77, 66, 50], ['Fri', 68, 66, 22, 15] // Treat first row as data as well. ], true); var options = { legend:'none' }; var chart = new google.visualization.CandlestickChart(document.getElementById('chart_div')); chart.draw(data, options); } </script> </head> <body> <div id=\"chart_div\" style=\"width: 900px; height: 500px;\"></div> </body> </html>";
                String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(),
                Base64.NO_PADDING);
        myWebView.loadData(encodedHtml, "text/html", "base64");

       // myWebView.loadUrl("https://example.com/");



        weightTextView = binding.weightTextView;
        heightTextView = binding.heightTextView;
        BMITextView = binding.bmiTextView;

        // set amountEditText's TextWatcher
        EditText weightEditText = binding.weightEditText;
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        EditText heightEditText = binding.heightEditText;
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void calculate() {
        // calculate the BMI
        double BMI = weight / (height * height);

        // display BMI formatted as number with 2 fraction digits
        numberFormat.setMaximumFractionDigits(2);
        BMITextView.setText(numberFormat.format(BMI));
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
}