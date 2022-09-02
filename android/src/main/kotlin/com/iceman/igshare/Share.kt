package com.iceman.igshare

import android.content.ClipData
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

internal class Share(private val shareActivity: ShareActivity) {
    private val type: String by lazy {
        "image/*"
    }

    fun shareToInstagram(mediaPath: String) {
        val activity = shareActivity.getActivity()
        val packageName = shareActivity.getPackageName()
        val share = Intent(Intent.ACTION_SEND)
        val file = copyToShareCacheFolder(mediaPath)
        val fileProvider = FileProvider.getUriForFile(activity, packageName, file)
        share.setType(type)
        share.setPackage("com.instagram.android")
        share.clipData = ClipData.newRawUri("", fileProvider)
        share.putExtra(Intent.EXTRA_STREAM, fileProvider)
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        activity.startActivity(Intent.createChooser(share, "Share To"))
    }

    fun openGooglePlay(idApplication: String) {
        val activity = shareActivity.getActivity()
        val detailMarketLocation = "market://details?id=$idApplication"
        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(detailMarketLocation)))
    }

    private fun copyToShareCacheFolder(mediaPath: String): File {
        val cacheDir = File(shareActivity.getActivity().cacheDir, "cache_image")
        val mediaFile = File(mediaPath)
        if (!cacheDir.exists())
            cacheDir.mkdir()
        val newFile = File(cacheDir, mediaFile.name)
        mediaFile.copyTo(newFile, true)
        return newFile
    }
}