package ru.arthurknight.homework.common.divider

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class GridDividerItemDecoration : RecyclerView.ItemDecoration() {

    var skipHeaderDivider = true

    private var divider: Drawable? = null
    private val bounds = Rect()
    private var horizontalSpacing = 0
    private var verticalSpacing = 0

    fun setDrawable(drawable: Drawable) {
        divider = drawable
        divider?.intrinsicWidth?.let { horizontalSpacing = it }
        divider?.intrinsicHeight?.let { verticalSpacing = it }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.save()
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.layoutManager?.getDecoratedBoundsWithMargins(child, bounds)

            val right: Int = bounds.right + child.translationX.roundToInt()
            val left: Int = bounds.left - child.translationX.roundToInt()
            val bottom: Int = bounds.bottom + child.translationY.roundToInt()
            val top: Int = bounds.top - child.translationY.roundToInt()

            divider?.setBounds(left, top, right, bottom)
            divider?.draw(canvas)
        }
        canvas.restore()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val gridLayoutManager = parent.layoutManager as? GridLayoutManager ?: return
        val position = gridLayoutManager.getPosition(view)
        if (position < 0) return
        val spanCount = gridLayoutManager.spanCount
        val positionalSpanSize = gridLayoutManager.spanSizeLookup.getSpanSize(position)

        val isHeader = positionalSpanSize == spanCount
        // Заголовок не нужно выравнивать.
        if (skipHeaderDivider && isHeader) return

        val isRightInRow = isHeader || position % spanCount == 0

        outRect.top = verticalSpacing
        outRect.left = 0
        outRect.right = if (isRightInRow) 0 else horizontalSpacing
        outRect.bottom = 0
    }
}