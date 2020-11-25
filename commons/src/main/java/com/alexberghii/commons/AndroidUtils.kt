package com.alexberghii.commons

import android.app.Activity
import android.app.DownloadManager
import android.net.Uri
import android.os.Environment


object AndroidUtils {

    fun downloadImage(url: String, activity: Activity) {
        val fileName = url.substring(url.lastIndexOf('/') + 1)

        val request = DownloadManager.Request(Uri.parse(url)).apply {
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        }
        val manager = activity.getSystemService(Activity.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }
}