package com.example.practicapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.practicapi.databinding.ActivitySecondActivitiBinding
import com.google.gson.Gson
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


private val jokes: MutableList<contact> = mutableListOf()
@SuppressLint("StaticFieldLeak")
private lateinit var binding:ActivitySecondActivitiBinding
private var pos: Int = -1
open class SecondActiviti : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondActivitiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val executorService: ExecutorService=Executors.newSingleThreadExecutor()
        binding.button4.setOnClickListener {
            binding.textView1.text= executorService.submit(Callable {
                httpRequest("https://geek-jokes.sameerkumar.website/api?format=json")
            }).get().joke
        }

    }

    @Throws(IOException::class)
    fun httpRequest(urlString: String):contact{
        val url=URL(urlString)
        val connection=url.openConnection() as HttpURLConnection
        connection.requestMethod="GET"
        var data:Int=connection.inputStream.read()
        var str=""
        while (data !=-1){
            str +=data.toChar()
            data=connection.inputStream.read()
        }
        val temp = Gson().fromJson(str, contact::class.java)
        Log.d("API_QWE", "${temp}")
        return temp
    }

}