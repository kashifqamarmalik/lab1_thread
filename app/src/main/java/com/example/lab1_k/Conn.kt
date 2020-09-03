package com.example.lab1_k

import android.os.Handler
import android.util.Log
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Conn(mHand: Handler) : Runnable {
    private val myHandler = mHand
    override fun run() {
        try {
            val myUrl = URL("http://www.randomtext.me/api/")
            val myConn = myUrl.openConnection()
                    as HttpURLConnection
            myConn.requestMethod = "GET"


            /*myConn.doOutput = true
            val ostream = myConn.getOutputStream()
            ostream.bufferedWriter().use {
                it.write("fn=${fnmae}&ln=${lname}")
            }*/


            val istream: InputStream = myConn.inputStream
            val allText = istream.bufferedReader().use {
                it.readText()
            }
            val result = StringBuilder()
            result.append(allText)
            val str = result.toString()
            Log.d("ABC",str)

            val msg = myHandler.obtainMessage()
            msg.what = 0
            msg.obj = str
            myHandler.sendMessage(msg)
        } catch (e: Exception) { }
    }
}