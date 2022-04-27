package com.example.zad4.ui.BMI

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zad4.databinding.FragmentBmiBinding
import java.text.NumberFormat

class BMIFragment : Fragment() {
    private var weight = 0.0 // weight amount entered by the user
    private var height = 0.0 // height amount entered by the user
    private var weightTextView // shows formatted weight
            : TextView? = null
    private var heightTextView // shows formatted height
            : TextView? = null
    private var BMITextView // shows calculated BMI
            : TextView? = null
    private var binding: FragmentBmiBinding? = null
    private val graph = """<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" >
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Niedowaga', 14, 14, 18.4, 18.4],
          ['Prawidłowa', 18.5, 18.5, 24.9, 24.9],
          ['Nadwaga', 25, 25, 29.9, 29.9],
          ['Otyłość', 30, 30, 34.9, 34.9],
          ['Poważna otyłość', 35, 35, 50, 50]
          // Treat the first row as data.
        ], true);

        var options = {
          legend: 'none',
          bar: { groupWidth: '100%' }, // Remove space between bars.
          candlestick: {
            fallingColor: { strokeWidth: 0, fill: '#a52714' }, // red
            risingColor: { strokeWidth: 0, fill: '#0f9d58' }   // green
          }
        };

        var chart = new google.visualization.CandlestickChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="chart_div" style="width: 480px; height: 240px;"></div>
  </body>
</html>
"""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val BMIViewModel: com.example.zad3.ui.BMI.BMIViewModel = ViewModelProvider(this).get(
            com.example.zad3.ui.BMI.BMIViewModel::class.java
        )
        binding = FragmentBmiBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()
        val myWebView: WebView = binding!!.webview
        val webSettings = myWebView.settings
        webSettings.javaScriptEnabled = true
        val encodedHtml = Base64.encodeToString(
            graph.toByteArray(),
            Base64.NO_PADDING
        )
        myWebView.loadData(encodedHtml, "text/html", "base64")
        weightTextView = binding!!.weightTextView
        heightTextView = binding!!.heightTextView
        BMITextView = binding!!.bmiTextView

        // set amountEditText's TextWatcher
        val weightEditText: EditText = binding!!.weightEditText
        weightEditText.addTextChangedListener(weightEditTextWatcher)
        val heightEditText: EditText = binding!!.heightEditText
        heightEditText.addTextChangedListener(heightEditTextWatcher)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun calculate() {
        // calculate the BMI
        val BMI = weight / (height * height)

        // display BMI formatted as number with 2 fraction digits
        numberFormat.maximumFractionDigits = 2
        BMITextView!!.text = numberFormat.format(BMI)
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
                weightTextView!!.text = numberFormat.format(weight)
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
    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the height
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            try { // get BMI amount and display double formatted value
                height = s.toString().toDouble() / 100
                heightTextView!!.text = numberFormat.format(height)
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

    companion object {
        private val numberFormat = NumberFormat.getNumberInstance()
    }
}