package kz.smartideagroup.pillikan.common.factories

import kz.smartideagroup.pillikan.common.factories.FixedConverter
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class FixedConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? =
        FixedConverter(
            retrofit.nextResponseBodyConverter<Any>(
                this,
                type,
                annotations
            )
        )
}