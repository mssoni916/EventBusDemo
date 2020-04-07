package com.example.eventbusdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        EventBus.getDefault().register(this)

        val message: String = "Hello"
        Log.d("EB", "POSTED")
        Toast.makeText(this, "POSTED: $message", Toast.LENGTH_LONG).show()

        EventBus.getDefault().post(MessageEvent(message))
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe
    fun onOtherEvent(event: MessageEvent) {
        Log.d("EB", "RECEIVED")
        Toast.makeText(this, "RECEIVED:", Toast.LENGTH_LONG)
    }
}
