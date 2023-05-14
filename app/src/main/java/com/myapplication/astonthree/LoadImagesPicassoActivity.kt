package com.myapplication.astonthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class LoadImagesPicassoActivity : AppCompatActivity() {
    lateinit var imageviewforPicasso:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso_load_images)
        imageviewforPicasso = findViewById<ImageView>(R.id.imageview_load_images)
        loadAndSetImageToImageView()
    }
    fun loadAndSetImageToImageView() {

        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imageviewforPicasso,object : Callback {
            override fun onSuccess() {
                Log.d("onSucces", "load Success")
            }

            override fun onError(e: Exception?) {
                toastError()
            }
        })


    }
    fun toastError(){
        Toast.makeText(this, "dont load image", Toast.LENGTH_LONG).show()
    }
}