package com.example.lab1_k

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val mHandler: Handler = object :
        Handler(Looper.getMainLooper()) {
        override fun handleMessage(inputMessage: Message) {
            if (inputMessage.what == 0) {
                txt_network.text = inputMessage.obj.toString()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (isNetworkAvailable()) {
            Log.d("ABC", "Internet is working")
            val myRunnable = Connection(mHandler)
            val myThread = Thread(myRunnable)
            myThread.start()
        } else {
            Log.d("ABC", "Internet not working")
        }
    }

    private fun isNetworkAvailable(): Boolean =
          (this.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager).isDefaultNetworkActive

}