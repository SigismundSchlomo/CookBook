package com.example.cookbook.utils

import android.content.Context

interface ConnectivityManagerWrapper {

    fun isConnected(): Boolean

    fun showAlertDialog(context: Context, onRetry: () -> Unit)

}