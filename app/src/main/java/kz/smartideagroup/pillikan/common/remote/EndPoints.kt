package kz.smartideagroup.pillikan.common.remote

object EndPoints {
    const val SIGN_IN = "api/s/v1/manual/token/mobile"
    const val SIGN_IN_SMS = "api/v1/auth/auth-sms"
    const val SEND_SMS = "api/v1/auth/reset-password-request"
    const val SIGN_UP = "api/v1/auth/sign-up"
    const val SIGN_UP_SMS = "api/v1/auth/sms"
    const val GET_SLIDER_ITEMS = "api/a/slider/list"
    const val REG_NEW_CRASH = "/v1/error/add"
    const val NEW_RETAIL_LIST = "/api/a/cb/retail/find/all"
}