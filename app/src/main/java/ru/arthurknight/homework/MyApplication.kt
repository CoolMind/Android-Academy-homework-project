@file:Suppress("unused")

package ru.arthurknight.homework

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // This flag should be set to true to enable VectorDrawable support for API < 21.
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}