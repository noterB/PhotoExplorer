package com.blsoft.photoexplorer

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by bretonlefebvre on 10/10/17.
 *
 * OneVision Software Inc
 */
class SquareFrameLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}