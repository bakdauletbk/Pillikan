package kz.smartideagroup.pillikan.common.utils

import android.content.Context
import android.telecom.PhoneAccountHandle
import android.telecom.TelecomManager
import android.util.Base64
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kz.smartideagroup.pillikan.common.remote.exceptions_util.ExceptionModel
import retrofit2.Response


object UtilsObject {

    fun getCurrentUserPhoneNumber(context: Context): String? {
        val telephonyManager: TelecomManager =
            context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
        val phoneAccountHandle: PhoneAccountHandle? = telephonyManager.simCallManager
        return when (phoneAccountHandle != null) {
            true -> telephonyManager.getLine1Number(phoneAccountHandle)
            false -> null
        }
    }

    fun <T> handleErrorMessage(response: Response<T>): String? {
        val type = object : TypeToken<ExceptionModel>() {}.type
        val errorBody: ExceptionModel = Gson().fromJson(response.errorBody()!!.charStream(), type)
        return errorBody.message()
    }

    fun formatNumber(phone: String): String {
        var num = ""
        var c: Char
        for (i in phone.indices) {
            c = phone[i]
            if (c == '(' || c == ')' || c == ' ' || c == '+' || c == '-') continue
            num += c
        }
        return num
    }

    fun encodeToBase64(string: String): String {
        return Base64.encodeToString(
            formatNumber(string).toByteArray(),
            Base64.NO_WRAP
        )
    }

}