package com.example.asn1

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.asn1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }
        binding.counterText.text= numberAmount.toString();
        binding.countDownBtn.setOnClickListener{
            RemoveAmount()
        }
        binding.countUpBtn.setOnClickListener{
            addAmount()
        }
        binding.toastBtn.setOnClickListener{
            Toast.makeText(this, "current amount is $numberAmount",
                    Toast.LENGTH_SHORT).show()
        }
    }

     var numberAmount: Int=0

    private fun addAmount(){
        numberAmount += 1
        binding.counterText.text= numberAmount.toString();
    }
    private fun RemoveAmount(){
        numberAmount -= 1
        binding.counterText.text= numberAmount.toString();
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("numberAmount", numberAmount)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        numberAmount = savedInstanceState.getInt("numberAmount")

    }
}