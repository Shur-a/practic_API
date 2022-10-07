package com.example.practicapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.practicapi.databinding.ActivitySecondActivitiBinding
import com.example.practicapi.databinding.ActivityTalesBinding
import com.google.gson.Gson
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityTalesBinding
class Tales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTalesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val executorService:ExecutorService=Executors.newSingleThreadExecutor()
        binding.button5.setOnClickListener {
            Log.d("qwe", "few")
            binding.textView4.text= executorService.submit(Callable {
                httpRequest("http://smartroom.ectsserver.edu/api/room/light/setcolor/0")
            }).get()
        }
        binding.button6.setOnClickListener {
            binding.textView4.text=executorService.submit(Callable {
                httpRequest(("http://smartroom.ectsserver.edu/api/room/light/setcolor/1"))
            }).get()
        }
        binding.button3.setOnClickListener {
            binding.textView4.text=executorService.submit(Callable {
                httpRequest(("http://smartroom.ectsserver.edu/api/room/light/setcolor/2"))
            }).get()
        }
        binding.button7.setOnClickListener {
            binding.textView4.text=executorService.submit(Callable {
                httpRequest(("http://smartroom.ectsserver.edu/api/room/light/poweron/0"))
            }).get()
        }
        binding.button8.setOnClickListener {
            binding.textView4.text=executorService.submit(Callable {
                httpRequest(("http://smartroom.ectsserver.edu/api/room/light/poweron/1"))
            }).get()
        }
        binding.button9.setOnClickListener {
            binding.textView4.text=executorService.submit(Callable {
                httpRequest(("http://smartroom.ectsserver.edu/api/room/light/poweron/2"))
            }).get()
        }
        binding.button10.setOnClickListener {
            binding.textView4.text=executorService.submit(Callable {
                httpRequest(("http://smartroom.ectsserver.edu/api/room/light/poweron/3"))
            }).get()
        }

    }
    @Throws(IOException::class)
    fun httpRequest(urlString: String):String{
        val url= URL(urlString)
        val connection=url.openConnection() as HttpURLConnection
        connection.requestMethod="GET"
        var data:Int=connection.inputStream.read()
        var str=""
        while (data !=-1){
            str +=data.toChar()
            data=connection.inputStream.read()
        }
        Log.d("API_QWE",str)
        return str
    }
}