package com.example.aidlapp

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyAidlService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }
    private val binder = object: IMyAidlInterface.Stub() {
        override fun add(x:Int,y:Int):Int {
            return x+y
        }
    }

}


//class MyAidlService : IMyAidlInterface.Stub()