package ru.arthurknight.homework.util

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

object DrawableUtil {

    fun getDrawable(context: Context, @DrawableRes drawableRes: Int): Drawable =
        ContextCompat.getDrawable(context, drawableRes)!!

    /**
     * Изменить цвет SVG-изображения.
     */
    fun ImageView.setSvgColor(@ColorRes color: Int) =
        setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN)

}