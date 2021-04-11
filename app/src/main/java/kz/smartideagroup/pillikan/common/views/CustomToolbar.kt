package kz.smartideagroup.pillikan.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.view_toolbar_logo.view.*
import kz.smartideagroup.pillikan.R

class CustomToolbar: Toolbar {
    constructor(context: Context) : super(context) { setup(context, null, 0) }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {setup(context, attrs, 0) }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setup(context, attrs, defStyleAttr)
    }

    private fun setup(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) {
        LayoutInflater.from(context).inflate(
            R.layout.view_toolbar_logo,
            this,
            true
        )
        setupTitle(context, attrs, defStyleAttr)
    }

    private fun setupTitle(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) {
        if (attrs != null) {
            val typedArray =
                context.obtainStyledAttributes(
                    attrs, R.styleable.LogoToolbar,
                    defStyleAttr,
                    0
                )
            try {
                val title = typedArray.getString(R.styleable.LogoToolbar_text)
                toolbar_title_tv.text = title
            } finally {
                typedArray.recycle()
            }
        }
    }

    fun setupTitle(titleResource: Int) {
        toolbar_title_tv.setText(titleResource)
    }

}