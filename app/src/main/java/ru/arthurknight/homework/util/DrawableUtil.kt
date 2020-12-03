package ru.arthurknight.homework.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

object DrawableUtil {

    fun getDrawable(context: Context, @DrawableRes drawableRes: Int): Drawable =
        ContextCompat.getDrawable(context, drawableRes)!!
}