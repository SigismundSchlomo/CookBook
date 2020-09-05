package com.example.cookbook.utils

import android.content.Context

interface ConnectivityManager {

    fun isConnected(): Boolean

    fun showAlertDialog(context: Context, onRetry: () -> Unit)

}