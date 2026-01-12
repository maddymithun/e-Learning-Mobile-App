package com.example.composecleanarchitecture.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.composecleanarchitecture.utils.language.LocaleHelperActivityDelegateImpl
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
abstract class BaseActivity : ComponentActivity() {
    private val localeDelegate = LocaleHelperActivityDelegateImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localeDelegate.onCreate(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        viewRelatedTask()
    }

    abstract fun viewRelatedTask()

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localeDelegate.attachBaseContext(base))
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        super.applyOverrideConfiguration(
            localeDelegate.applyOverrideConfiguration(baseContext, overrideConfiguration)
        )
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onResume() {
        super.onResume()
        localeDelegate.onResumed(this)
    }

    override fun onPause() {
        super.onPause()
        localeDelegate.onPaused()
    }

    open fun updateLocale(locale: Locale, isActivityRestart: Boolean) {
        localeDelegate.setLocale(this, locale, isActivityRestart)
    }
}