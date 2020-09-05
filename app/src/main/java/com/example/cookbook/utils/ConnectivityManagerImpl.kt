package com.example.cookbook.utils

import android.content.Context
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import androidx.appcompat.app.AlertDialog
import javax.inject.Inject

class ConnectivityManagerImpl @Inject constructor(private val context: Context) :
    ConnectivityManager {

    override fun isConnected(): Boolean {
        return if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.P) {
            checkNetworkConnectivityBeforeApi29()
        } else {
            checkNetworkConnectivityAfterApi29()
        }
    }

    override fun showAlertDialog(context: Context, onRetry: () -> Unit) {
        val alertDialog = AlertDialog.Builder(context).apply {
            setTitle("Info")
            setMessage("Internet not available, Cross check your internet connectivity and try again")
            setNeutralButton("RETRY") { _, _ ->
                onRetry()
            }
        }.create()

        alertDialog.show()
    }

    private fun checkNetworkConnectivityAfterApi29(): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    private fun checkNetworkConnectivityBeforeApi29(): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}