package com.stamasoft.ptithom.kotlin_allinone_maximum

import android.app.TimePickerDialog
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var picker: TimePicker
    private lateinit var btnGet: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val switch1: Switch = findViewById(R.id.switch1)

        val checkBoxKotlin: CheckBox = findViewById(R.id.checkBox)
        val checkBoxJava: CheckBox = findViewById(R.id.checkBox2)

        val buttonResult: Button = findViewById(R.id.button_result)
        val textViewBasics: TextView = findViewById(R.id.textViewBasics)

        val radioButton: RadioButton = findViewById(R.id.radioButton)

        val toggleButton: ToggleButton = findViewById(R.id.toggleButton)

        val textViewResults: TextView = findViewById(R.id.textViewResults)
        val seekBar: SeekBar = findViewById(R.id.seekBar)
        val seekBar2: SeekBar = findViewById(R.id.seekBar2)
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)

        // Switch
        switch1.setOnCheckedChangeListener { _, isChecked ->
            // Show a toast message based on the switch state
            if (isChecked) {
                Toast.makeText(this, "Switch is ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Switch is OFF", Toast.LENGTH_SHORT).show()
            }
        }
        // CheckBox
        buttonResult.setOnClickListener {
            // Initialize an empty list to hold the selected options
            val selectedOptions = mutableListOf<String>()

            // Check the state of each CheckBox and add the text to the list if selected
            if (checkBoxKotlin.isChecked) {
                selectedOptions.add(checkBoxKotlin.text.toString())
            }
            if (checkBoxJava.isChecked) {
                selectedOptions.add(checkBoxJava.text.toString())
            }

            // Update the TextView with the selected options joined by a comma
            textViewBasics.text = if (selectedOptions.isNotEmpty()) {
                selectedOptions.joinToString(", ")
            } else {
                "No options selected"
            }
        }
          //RadioButton
        radioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Radio Button Enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Radio Button Disabled", Toast.LENGTH_SHORT).show()
            }
        }
        // Set an OnCheckedChangeListener on the ToggleButton
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            // Update the TextView based on the toggle button state
            if (isChecked) {
                textViewBasics.text = "Toggle Button is On"
            } else {
                textViewBasics.text = "Toggle Button is Off"
            }
        }
        //SeekBar and RatingBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textViewResults.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textViewResults.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            textViewResults.text = "$rating"
        }

        //TimePicker
        textView = findViewById(R.id.clockView)
        picker = findViewById(R.id.timePicker)
        picker.setIs24HourView(true)

        btnGet = findViewById(R.id.clockButton)

        btnGet.setOnClickListener {
            val hour: Int
            val minute: Int
            val amPm: String

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                hour = picker.hour
                minute = picker.minute
            } else {
                hour = picker.currentHour
                minute = picker.currentMinute
            }
            amPm = if (hour > 12) {
                hour - 12
                "PM"
            } else {
                "AM"
            }
            val tpdata = "$hour:$minute $amPm"
            textView.text = tpdata
        }

        //Webview
        val webView: WebView = findViewById(R.id.webview)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.loadUrl("https://www.amazon.com")
    }
}
