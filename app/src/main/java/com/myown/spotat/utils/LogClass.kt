package com.myown.spotat.utils

import android.util.Log

class LogClass {

    companion object {
        val ERROR_MSG: Int = 1
        val DEBUG_MSG: Int = 2
        val INFO_MSG: Int = 3
    }

    fun setLogMsg(tag : String, msgContent : String, msgType : Int) {

        val msg : String= "___" + msgContent

        when (msgType) {
            ERROR_MSG -> Log.e(tag, msg)
            DEBUG_MSG -> Log.d(tag, msg)
            INFO_MSG -> Log.i(tag, msg)
            else -> Log.w(tag, msg)
        }
    }
}