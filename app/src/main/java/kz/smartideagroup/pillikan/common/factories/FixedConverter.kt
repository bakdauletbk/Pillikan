package kz.smartideagroup.pillikan.common.factories

import okhttp3.ResponseBody
import retrofit2.Converter

class FixedConverter(
    private val parent: Converter<ResponseBody, Any>
) : Converter<ResponseBody, Any> {

    override fun convert(value: ResponseBody): Any? = if (value.contentLength() != 0L) {
        parent.convert(value)
    } else {
        null
    }
}