package com.example.aidlapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var aidlService: IMyAidlInterface? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            aidlService = IMyAidlInterface.Stub.asInterface(binder)
            val result = aidlService?.add(2, 3)
            Log.d("AIDL", "Result from service: $result")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            aidlService = null
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyAidlService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
