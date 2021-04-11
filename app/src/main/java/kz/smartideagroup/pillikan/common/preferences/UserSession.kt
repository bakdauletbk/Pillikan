package kz.smartideagroup.pillikan.common.preferences

import android.content.SharedPreferences
import kz.smartideagroup.pillikan.common.utils.TOKEN_BEARER
import kz.smartideagroup.pillikan.content.sign_in.models.SignInResponse

class UserSession(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_ID = "KEY_ID"
        const val KEY_PHONE = "KEY_PHONE"
        const val KEY_EMAIL = "KEY_EMAIL"
        const val KEY_IS_PIN = "KEY_IS_PIN"
        const val KEY_IS_SYNC = "KEY_IS_SYNC"
        const val KEY_TOPICS = "KEY_TOPICS"
        const val KEY_CITY_ID = "KEY_CITY_ID"
        const val KEY_BALANCE = "KEY_BALANCE"
        const val KEY_USERNAME = "KEY_USERNAME"
        const val KEY_BIRTHDAY = "KEY_BIRTHDAY"
        const val KEY_REGION_ID = "KEY_REGION_ID"
        const val KEY_PROMO_CODE = "KEY_PROMO_CODE"
        const val KEY_QR_CODE_URL = "KEY_QR_CODE_URL"
        const val KEY_IS_AUTHORIZE = "KEY_IS_AUTHORIZE"
        const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
    }

    fun getAccessToken(): String? = prefs.getString(KEY_ACCESS_TOKEN, null)
    fun setAccessToken(access_token: String) {
        prefs.edit().putString(KEY_ACCESS_TOKEN, access_token).apply()
    }

    fun getIsAuthorize(): Boolean = prefs.getBoolean(KEY_IS_AUTHORIZE, false)
    fun setIsAuthorize(isAuthorize: Boolean) {
        prefs.edit().putBoolean(KEY_IS_AUTHORIZE, isAuthorize).apply()
    }

    fun getIsPin(): Boolean = prefs.getBoolean(KEY_IS_PIN, false)
    fun setIsPin(isPin: Boolean) {
        prefs.edit().putBoolean(KEY_IS_PIN, isPin).apply()
    }

    fun getIsSync(): Boolean = prefs.getBoolean(KEY_IS_SYNC, false)
    fun setIsSync(isSync: Boolean) {
        prefs.edit().putBoolean(KEY_IS_SYNC, isSync).apply()
    }

    fun getId(): Int = prefs.getInt(KEY_ID, 0)
    fun setId(id: Int) {
        prefs.edit().putString(KEY_ID, id.toString()).apply()
    }

    fun getBalance(): Int = prefs.getInt(KEY_BALANCE, 0)
    fun setBalance(balance: Int) {
        prefs.edit().putInt(KEY_ID, balance).apply()
    }

    fun getCityId(): Int = prefs.getInt(KEY_CITY_ID, 0)
    fun setCityId(cityId: Int) {
        prefs.edit().putInt(KEY_CITY_ID, cityId).apply()
    }

    fun getUserName(): String? = prefs.getString(KEY_USERNAME, null)
    fun setUserName(userName: String) {
        prefs.edit().putString(KEY_USERNAME, userName).apply()
    }

    fun getEmail(): String? = prefs.getString(KEY_EMAIL, null)
    fun setEmail(email: String) {
        prefs.edit().putString(KEY_EMAIL, email).apply()
    }

    fun getPhone(): String? = prefs.getString(KEY_PHONE, null)
    fun setPhone(phone: String) {
        prefs.edit().putString(KEY_PHONE, phone).apply()
    }

    fun getTopics(): String? = prefs.getString(KEY_TOPICS, null)
    fun setTopics(topics: String) {
        prefs.edit().putString(KEY_TOPICS, topics).apply()
    }

    fun saveCurrentUser(user: SignInResponse?){
        setIsAuthorize(true)
        setAccessToken(TOKEN_BEARER + user?.token!!.accessToken)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

}