package kz.smartideagroup.pillikan.common.helpers

object StringUtils {

    fun isValid(string: String?): Boolean {
        return string != null && !string.isEmpty() && string != "null"
    }
}