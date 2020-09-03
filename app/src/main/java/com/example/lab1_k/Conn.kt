package com.example.lab1_k

import android.os.Handler
import android.util.Log
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Connection(mHand: Handler): Runnable {

    private val myHandler = mHand

    override fun run() {
        try {
            val myUrl = URL("https://www.randomtext.me/api/")
            val myConn = myUrl.openConnection() as HttpURLConnection
            myConn.requestMethod = "GET"

            val iStream: InputStream = myConn.inputStream
            val allText = iStream.bufferedReader().use {
                it.readText()
            }

            val result = StringBuilder()
            result.append(allText)
            val str = result.toString()
             Log.d("ABC", "${str}")
            val msg = myHandler.obtainMessage()
            msg.what = 0
            msg.obj = str
            myHandler.sendMessage(msg)

        } catch (e: Exception) {

        }
    }

}