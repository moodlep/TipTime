package com.example.tiptime2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime2.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//    }
//}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    private fun calculateTip(){

        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null || cost == 0.0)
        {
            displayTip(0.0)
            return
        }

        // Get the tip percentage
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.2
            R.id.option_fifteen_percent -> 0.15
            else -> 0.1
        }

        // Calculate and display the tip
        var tip = cost * tipPercentage
        if(binding.roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }
        displayTip(tip)

    }

    private fun displayTip(tip: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}