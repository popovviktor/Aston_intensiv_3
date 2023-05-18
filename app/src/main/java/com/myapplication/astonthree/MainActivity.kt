package com.myapplication.astonthree

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btn_countries:Button
    lateinit var btn_load_images:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButtonsAndTheirListners()

    }
    fun initButtonsAndTheirListners(){
        btn_countries = findViewById(R.id.button_countries)
        btn_load_images = findViewById(R.id.button_load_images)
        btn_countries.setOnClickListener {
            startCountriesActivity()
        }
        btn_load_images.setOnClickListener {
            startLoadImages()
        }
    }
    fun startCountriesActivity(){
        val intent = Intent(this,CountrysAndFlagsActivity::class.java)
        startActivity(intent)
    }
    fun startLoadImages(){
        val intent = Intent(this,LoadImagesActivity::class.java)
        startActivity(intent)
    }
}