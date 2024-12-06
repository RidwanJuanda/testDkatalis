package com.example.testdkatalis.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import kotlin.jvm.JvmOverloads
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import java.lang.IllegalStateException

class LinearItemDecoration : ItemDecoration {
    private var includeEdge = false
    private val spacing: Int
    private var spacingTop = 0
    private var spacingBottom = 0
    private var spacingRight = 0

    @JvmOverloads
    constructor(space: Int, includeEdge: Boolean = true) {
        spacing = space
        this.includeEdge = includeEdge
    }

    constructor(
        space: Int,
        spaceTop: Int,
        spaceBottom: Int,
        spaceRight: Int
    ) {
        spacing = space
        spacingBottom = spaceBottom
        spacingTop = spaceTop
        spacingRight = spaceRight
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val isReverseLayout: Boolean
        val position = parent.getChildAdapterPosition(view) // item position
        val orientation: Int
        val itemCount: Int = if (parent.adapter == null) {
            0
        } else {
            parent.adapter!!.itemCount
        }
        val layoutManager = parent.layoutManager
        if (layoutManager is LinearLayoutManager) {
            orientation = layoutManager.orientation
            isReverseLayout = layoutManager.reverseLayout
        } else {
            throw IllegalStateException("Please use LinearLayoutManager!")
        }

        /* IF ORIENTATION HORIZONTAL */if (orientation == LinearLayoutManager.HORIZONTAL) {
            if (includeEdge) {
                outRect.top = spacing
                outRect.bottom = spacing
            } else {
                outRect.top = spacingTop
                outRect.bottom = spacingBottom
            }
            if (position == 0) {
                if (isReverseLayout) {
                    if (includeEdge) {
                        outRect.right = spacing
                    } else {
                        outRect.right = spacingRight
                    }
                    outRect.left = spacing / 2
                } else {
                    if (includeEdge) {
                        outRect.left = spacing
                    } else {
                        outRect.left = spacingRight
                    }
                    outRect.right = spacing / 2
                }
            } else if (position == itemCount - 1) {
                if (isReverseLayout) {
                    if (includeEdge) {
                        outRect.left = spacing
                    } else {
                        outRect.left = spacingRight
                    }
                    outRect.right = spacing / 2
                } else {
                    if (includeEdge) {
                        outRect.right = spacing
                    } else {
                        outRect.right = spacingRight
                    }
                    outRect.left = spacing / 2
                }
            } else {
                outRect.left = spacing / 2
                outRect.right = spacing / 2
            }
            return
        }

        /* IF ORIENTATION VERTICAL */if (includeEdge) {
            outRect.left = spacing
            outRect.right = spacing
        } else {
            outRect.left = spacingRight
            outRect.right = spacingRight
        }
        if (position == 0) {
            if (isReverseLayout) {
                if (includeEdge) {
                    outRect.bottom = spacing
                } else {
                    outRect.bottom = spacingBottom
                }
                outRect.top = spacing / 2
            } else {
                if (includeEdge) {
                    outRect.top = spacing
                } else {
                    outRect.top = spacingTop
                }
                outRect.bottom = spacing / 2
            }
        } else if (position == itemCount - 1) {
            if (isReverseLayout) {
                if (includeEdge) {
                    outRect.top = spacing
                } else {
                    outRect.top = spacingTop
                }
                outRect.bottom = spacing / 2
            } else {
                if (includeEdge) {
                    outRect.bottom = spacing
                } else {
                    outRect.bottom = spacingBottom
                }
                outRect.top = spacing / 2
            }
        } else {
            outRect.top = spacing / 2
            outRect.bottom = spacing / 2
        }
    }
}