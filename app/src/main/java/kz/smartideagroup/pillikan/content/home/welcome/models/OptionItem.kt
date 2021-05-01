package kz.smartideagroup.pillikan.content.home.welcome.models

import java.io.Serializable

data class OptionItem(
    var actionId: Int,
    var title: String,
    var icon: Int? = null,
    var color: Int,
    var isLast: Boolean = false,
    var budgeCount: Int = 0
): Serializable
