/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.arthurknight.homework.common.divider

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import kotlin.math.roundToInt

/**
 * DividerItemDecoration is a {@link RecyclerView.ItemDecoration} that can be used as a divider
 * between items of a {@link LinearLayoutManager}. It supports both {@link #LinearLayout.HORIZONTAL} and
 * {@link #LinearLayout.VERTICAL} orientations.
 *
 * <pre>
 *     mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
 *             mLayoutManager.getOrientation());
 *     recyclerView.addItemDecoration(mDividerItemDecoration);
 * </pre>
 */

// https://gist.githubusercontent.com/bipinvaylu/2714d9d429dc72b0108c05be52b609e0/raw/74b1d26538c534fe1aaa772b3f2890fc01882fcd/DividerItemDecoration.kt
// Find out original java version: https://gist.github.com/johnwatsondev/720730cf6b8c59fa6abe4f31dbaf59d7

@SuppressLint("LogNotTimber")
class DividerItemDecoration(private val orientation: Int, private val isShowInLastItem: Boolean) :
    ItemDecoration() {

    private lateinit var divider: Drawable

    /**
     * Sets the [Drawable] for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    fun setDrawable(drawable: Drawable) {
        divider = drawable
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null) {
            return
        }
        if (orientation == LinearLayout.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int = parent.paddingLeft
        val right: Int = parent.width - parent.paddingRight
        canvas.clipRect(
            left, parent.paddingTop, right,
            parent.height - parent.paddingBottom
        )
        val childCount: Int = if (isShowInLastItem) {
            parent.childCount
        } else {
            parent.childCount - 1
        }
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val decoratedBottom = parent.layoutManager!!.getDecoratedBottom(child)
            val bottom = decoratedBottom + child.translationY.roundToInt()
            val top = bottom - divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }
        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val top: Int = parent.paddingTop
        val bottom: Int = parent.height - parent.paddingBottom
        canvas.clipRect(
            parent.paddingLeft, top,
            parent.width - parent.paddingRight, bottom
        )
        val childCount: Int = if (isShowInLastItem) {
            parent.childCount
        } else {
            parent.childCount - 1
        }
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val decoratedRight = parent.layoutManager!!.getDecoratedRight(child)
            val right = decoratedRight + child.translationX.roundToInt()
            val left = right - divider.intrinsicWidth
            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }
        canvas.restore()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
        val itemCount = state.itemCount
        if (isShowInLastItem) {
            if (orientation == LinearLayout.VERTICAL) {
                outRect[0, 0, 0] = divider.intrinsicHeight
            } else {
                outRect[0, 0, divider.intrinsicWidth] = 0
            }
        } else if (itemPosition == itemCount - 1) {
            // We didn't set the last item when mIsShowInLastItem's value is false.
            outRect.setEmpty()
        } else {
            if (orientation == LinearLayout.VERTICAL) {
                outRect[0, 0, 0] = divider.intrinsicHeight
            } else {
                outRect[0, 0, divider.intrinsicWidth] = 0
            }
        }
    }
}