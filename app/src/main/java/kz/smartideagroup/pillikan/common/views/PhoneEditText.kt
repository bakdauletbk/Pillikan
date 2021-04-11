package kz.smartideagroup.pillikan.common.views

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class PhoneEditText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : TextInputEditText(context, attributeSet) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setMask()
    }

    private fun setMask() {
        val slots = UnderscoreDigitSlotsParser().parseSlots("+7 (7__) ___ __ __")
        val formatWatcher: FormatWatcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
        formatWatcher.installOn(this)
    }

}
