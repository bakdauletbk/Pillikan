package kz.smartideagroup.pillikan.common.views

import android.content.Context
import android.text.InputFilter
import android.text.Spanned
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class CardValidityEditText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : TextInputEditText(context, attributeSet) {

    companion object {
        const val DATE_SEPARATOR = "/"
        private const val EMPTY_STRING = ""
        private const val MAX_SYMBOLS = 5
        private const val FIRST_SYMBOLS_GROUP = 1
        private const val SECOND_SYMBOLS_GROUP = 2
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initFilters()
    }

    private fun initFilters() {
        filters = arrayOf(
            InputFilter.LengthFilter(MAX_SYMBOLS),
            CardValidityInputFilter(),
            ValidCharInputFilter()
        )
    }

    private class CardValidityInputFilter : InputFilter {
        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            if (dest.toString().trim().length > MAX_SYMBOLS) return null
            // Positions for separating group of digits
            return if (source.length == FIRST_SYMBOLS_GROUP && dstart == SECOND_SYMBOLS_GROUP) {
                DATE_SEPARATOR + source
            } else {
                null
            }
        }
    }

    private class ValidCharInputFilter : InputFilter {
        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            for (i in start until end) {
                if (source[i].toString() == DATE_SEPARATOR) return null
                if (Character.isWhitespace(source[i])) return EMPTY_STRING
                if (!Character.isDigit(source[i])) return EMPTY_STRING
            }
            return null
        }
    }
}
