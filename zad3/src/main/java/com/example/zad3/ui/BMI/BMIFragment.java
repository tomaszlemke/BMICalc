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

    private String graph = "<html>\n" +
            "  <head>\n" +
            "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
            "    <script type=\"text/javascript\">\n" +
            "      google.charts.load('current', {'packages':['corechart']});\n" +
            "      google.charts.setOnLoadCallback(drawChart);\n" +
            "      function drawChart() {\n" +
            "        var data = google.visualization.arrayToDataTable([\n" +
            "          ['Niedowaga', 14, 14, 18.4, 18.4],\n" +
            "          ['Prawidłowa', 18.5, 18.5, 24.9, 24.9],\n" +
            "          ['Nadwaga', 25, 25, 29.9, 29.9],\n" +
            "          ['Otyłość', 30, 30, 34.9, 34.9],\n" +
            "          ['Poważna otyłość', 35, 35, 50, 50]\n" +
            "          // Treat the first row as data.\n" +
            "        ], true);\n" +
            "\n" +
            "        var options = {\n" +
            "          legend: 'none',\n" +
            "          bar: { groupWidth: '100%' }, // Remove space between bars.\n" +
            "          candlestick: {\n" +
            "            fallingColor: { strokeWidth: 0, fill: '#a52714' }, // red\n" +
            "            risingColor: { strokeWidth: 0, fill: '#0f9d58' }   // green\n" +
            "          }\n" +
            "        };\n" +
            "\n" +
            "        var chart = new google.visualization.CandlestickChart(document.getElementById('chart_div'));\n" +
            "        chart.draw(data, options);\n" +
            "      }\n" +
            "    </script>\n" +
            "  </head>\n" +
            "  <body>\n" +
            "    <div id=\"chart_div\" style=\"width: 480px; height: 240px;\"></div>\n" +
            "  </body>\n" +
            "</html>\n";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BMIViewModel BMIViewModel =
                new ViewModelProvider(this).get(BMIViewModel.class);

        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        WebView myWebView = binding.webview;
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

         String encodedHtml = Base64.encodeToString(graph.getBytes(),
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