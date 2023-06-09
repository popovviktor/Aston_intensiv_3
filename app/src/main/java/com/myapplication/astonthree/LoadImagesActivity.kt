package com.myapplication.astonthree

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import java.io.InputStream
import java.net.URL


class LoadImagesActivity : AppCompatActivity() {
    lateinit var imageviewforPicasso:ImageView
    lateinit var imagewithoutlibraries:ImageView
    lateinit var edit_url_load:EditText
    lateinit var progressbar:ProgressBar
    lateinit var timerNotChangedEditPath:CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_images)
        initParamsForStartActivity()
        initChangedListenerForEditPathImage()
    }

    fun initChangedListenerForEditPathImage(){
        var lastClickTime = 0L
        val DELAY: Long = 2000
        edit_url_load.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val currentTime = SystemClock.uptimeMillis()
                if (lastClickTime == 0L || currentTime - lastClickTime > DELAY) {
                    lastClickTime = currentTime
                    closeTimerWithoutChangedEditPath()
                    startLoadimageProgress()
                    loadAndSetImageToImageView(edit_url_load.text.toString())
                    loadImageWithoutNotStockLibraries(edit_url_load.text.toString())
                }else{
                    startTimerWithoutChangedEditPath()
                }
            }
        })
    }
    fun initParamsForStartActivity(){
        imageviewforPicasso = findViewById(R.id.imageview_for_picasso)
        imagewithoutlibraries = findViewById(R.id.imageViewWithoutLibraries)
        edit_url_load = findViewById(R.id.edit_url_load)
        progressbar = findViewById(R.id.progressBarLoadImage)
        startLoadimageProgress()
        loadAndSetImageToImageView(edit_url_load.text.toString())
        loadImageWithoutNotStockLibraries(edit_url_load.text.toString())
        inittimer()
    }
    fun inittimer(){
        timerNotChangedEditPath = object :CountDownTimer(2000,100){
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                loadAndSetImageToImageView(edit_url_load.text.toString())
                loadImageWithoutNotStockLibraries(edit_url_load.text.toString())
            }
        }
    }
    fun startTimerWithoutChangedEditPath(){
        timerNotChangedEditPath.start()
    }
    fun closeTimerWithoutChangedEditPath(){
        timerNotChangedEditPath.cancel()
    }
    fun loadAndSetImageToImageView(path:String) {
        if (path.length>0){
            Picasso.get().load(path).into(imageviewforPicasso,object : Callback {
                override fun onSuccess() {
                    endLoadImageProgress()
                }
                override fun onError(e: Exception?) {
                    toastError()
                    endLoadImageProgress()
                }
            })
        }
    }
    private suspend fun getImageBitmap(url: String) {
        withContext(Dispatchers.IO){
            try {
                val inputStream = URL(url).getContent() as InputStream
                val drawable = Drawable.createFromStream(inputStream,"src")
                imagewithoutlibraries.setImageDrawable(drawable)
            }catch (ex:java.lang.Exception){
                Log.e("ex","Exception")
            }
        }
    }
    fun loadImageWithoutNotStockLibraries(url: String) {
        runBlocking { getImageBitmap(url) }
    }
    fun toastError(){
        val errorMessage = getString(R.string.Toast_text_Error)
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
    fun startLoadimageProgress(){
        progressbar.visibility = View.VISIBLE
    }
    fun endLoadImageProgress(){
        progressbar.visibility = View.INVISIBLE
    }
}