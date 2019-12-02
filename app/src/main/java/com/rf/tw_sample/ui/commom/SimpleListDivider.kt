package com.rf.tw_sample.ui.commom

import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView
import com.rf.tw_sample.R

class SimpleListDivider : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val dividerLeft = parent.paddingStart
        val dividerRight = parent.width

        val childCount = parent.childCount

        val range = 0 until childCount
        for (i in range) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom =
                dividerTop + parent.context.resources.getDimensionPixelSize(R.dimen.divider_size)
            val divider = parent.context.getDrawable(R.drawable.divider)
            divider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            divider.draw(c)
        }
    }
}