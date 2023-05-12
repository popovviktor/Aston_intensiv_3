package com.myapplication.astonthree

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btn_countries:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_countries = findViewById(R.id.button_countries)
        btn_countries.setOnClickListener {
            startCountriesActivity()
        }
    }

    fun startCountriesActivity(){
        val intent = Intent(this,CountrysAndFlagsActivity::class.java)
        startActivity(intent)
    }
}