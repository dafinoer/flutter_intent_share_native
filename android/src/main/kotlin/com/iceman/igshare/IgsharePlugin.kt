package com.iceman.igshare

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StandardMethodCodec

/** IgsharePlugin */
class IgsharePlugin : FlutterPlugin, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    private lateinit var channel: MethodChannel
    private lateinit var shareActivity: ShareActivity
    private lateinit var share: Share

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        shareActivity = ShareActivity()
        share = Share(shareActivity)
        val methodChannelHandler = MethodCallHandler(share = share,
                shareActivity = shareActivity)

        channel = MethodChannel(
                flutterPluginBinding.binaryMessenger, methodChannelHandler.TAG,
                StandardMethodCodec.INSTANCE
        )
        channel.setMethodCallHandler(methodChannelHandler)
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        shareActivity.onAttachedToActivity(binding)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        shareActivity.onDetachedFromActivityForConfigChanges()
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        shareActivity.onReattachedToActivityForConfigChanges(binding = binding)
    }

    override fun onDetachedFromActivity() {
        shareActivity.onDetachedFromActivity()
    }
}
