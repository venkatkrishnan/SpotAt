package com.myown.spotat.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Build

class ConnectionDetector {

    lateinit var context : Context

    constructor(context : Context) {
        this.context = context
    }

    /**
     * Checking for all possible internet providers
     * **/
    fun isConnectingToInternet() : Boolean {
        val connectivity : ConnectivityManager? = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(connectivity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val networks : Array<Network> = connectivity.allNetworks
                for(network : Network in networks) {
                    val networkInfo : NetworkInfo = connectivity.getNetworkInfo(network)
                    if(networkInfo.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            } else {
                val networksInfo : Array<NetworkInfo>? = connectivity.allNetworkInfo
                if(networksInfo != null) {
                    for(networkInfo : NetworkInfo in networksInfo) {
                        if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }
}