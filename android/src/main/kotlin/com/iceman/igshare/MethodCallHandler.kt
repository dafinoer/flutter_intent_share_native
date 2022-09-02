package com.iceman.igshare

import android.widget.Toast
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

internal class MethodCallHandler(private val share: Share,
                                 private val shareActivity: ShareActivity
) : MethodChannel.MethodCallHandler {
    val TAG: String = "plugin.flutter.io/ig_share_feed"

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
            "shareToInstagramFromPath" -> {
                val argValue: String? = call.argument<String>("path_image")
                val isHaveInstagram = shareActivity.isPackageInstall()
                if (!isHaveInstagram) {
                    Toast.makeText(shareActivity.getActivity(), "Instagram not found", Toast.LENGTH_LONG).show()
                    share.openGooglePlay("com.instagram.android")
                    result.success(null)
                } else if (argValue != null) {
                    share.shareToInstagram(argValue)
                    result.success(null)
                } else {
                    result.error("Failed", "Share failed", "")
                }
            }
        }
    }

}