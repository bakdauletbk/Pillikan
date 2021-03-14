package kz.smartideagroup.pillikan.common.utils

import android.util.Patterns
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

fun TextInputEditText.kazakhstanPhoneNumberMask() {
    val slots = UnderscoreDigitSlotsParser().parseSlots("+7 (7__) ___ __ __")
    val formatWatcher: FormatWatcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
    formatWatcher.installOn(this)
}

fun CharSequence?.clearPhoneSymbols(): String {
    return this?.toString()?.filterNot { it == '(' || it == ')' }?.replace(" ", "") ?: EMPTY_STRING
}


fun TextInputEditText.removeBrackets(): String {
    return text?.toString()?.filterNot { it == '(' || it == ')' } ?: EMPTY_STRING
}


fun TextView.getString() = text.toString()

fun TextInputEditText.validatePhone(): Boolean {
    val lText = text.clearPhoneSymbols()
    if (lText.length == PHONE_LENGTH) {
        if (lText[INDEX_NOT_ALLOWED_SYMBOL_PHONE] == ALLOWED_SYMBOL_PHONE) {
            return true
        }
    }
    return false
}

fun String.validatePhone(): Boolean {
    if (clearPhoneSymbols().length == PHONE_LENGTH) {
            return true
    }
    return false
}

fun String.validatePassword(): Boolean {
    if (clearPhoneSymbols().length > PASSWORD_MIN_LENGTH) {
            return true
    }
    return false
}

fun String.validateEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

