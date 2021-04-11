package kz.smartideagroup.pillikan.common.crash_report

import com.google.gson.annotations.SerializedName
import kz.smartideagroup.pillikan.common.utils.DEVICE_TYPE_ANDROID

data class CrashBody(
    @SerializedName("Action")
    val Action: String,
    @SerializedName("ExtraData")
    val ExtraData: String,
    @SerializedName("DeviceType")
    val DeviceType: Int = DEVICE_TYPE_ANDROID,
    @SerializedName("DeviceInfo")
    val DeviceInfo: String,
    @SerializedName("Level")
    val Level: String
)