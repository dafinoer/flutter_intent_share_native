package com.iceman.igshare

import android.app.Activity
import android.content.pm.PackageManager
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

internal class ShareActivity : ActivityAware {
    private lateinit var activity: Activity

    fun getActivity(): Activity {
        return activity
    }

    fun getPackageName(): String {
        return activity.packageName
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onDetachedFromActivityForConfigChanges() {

    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onDetachedFromActivity() {

    }

    fun isPackageInstall(): Boolean {
        val packageManager: PackageManager = activity.packageManager

        return try {
            packageManager.getPackageInfo("com.instagram.android", 0)
            true
        } catch (packageError: PackageManager.NameNotFoundException) {
            false
        }
    }
}