package com.example.testdkatalis.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridSpacingItemDecoration : ItemDecoration {
    private val includeEdge: Boolean
    private val includeFooter: Boolean
    private val spanCount: Int
    private val spacing: Int
    private val topSpacing: Int

    /**
     * Add special offsets and drawing around each element in RecyclerView.
     *
     * @param spanCount      number of columns
     * @param spacing        the space between elements (in integer pixel format)
     * @param includeEdge    a flag for adding space on top, left, right, and bottom for whole
     * RecyclerView
     */
    @JvmOverloads
    constructor(
        spanCount: Int,
        spacing: Int,
        includeEdge: Boolean = false
    ) {
        this.spanCount = spanCount
        this.spacing = spacing
        this.includeEdge = includeEdge
        includeFooter = true
        topSpacing = -1
    }

    constructor(
        spanCount: Int,
        includeFooter: Boolean,
        spacing: Int,
        topSpacing: Int
    ) {
        this.spanCount = spanCount
        this.spacing = spacing
        this.topSpacing = topSpacing
        includeEdge = false
        this.includeFooter = includeFooter
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column
        val size = parent.adapter!!.itemCount
        if (includeEdge) {
            outRect.left =
                spacing - column * (spacing / spanCount) // spacing - column * ((1f / spanCount) * spacing)
            outRect.right =
                (column + 1) * (spacing / spanCount) // (column + 1) * ((1f / spanCount) * spacing)
            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            outRect.left = column * (spacing / spanCount) // column * ((1f / spanCount) * spacing)
            outRect.right =
                spacing - (column + 1) * (spacing / spanCount) // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                // item top
                if (topSpacing < 0) {
                    outRect.top = spacing
                } else {
                    if (includeFooter) {    // if using footer in most bottom
                        if (position < size - 1) {
                            outRect.top = spacing
                        } else {    // for footer view, add bottom padding
                            outRect.top = 0
                            outRect.bottom = spacing // item bottom
                        }
                    } else {
                        outRect.top = topSpacing - spacing
                    }
                }
            }
        }
    }
}