package kz.smartideagroup.pillikan.content.home.welcome.models

data class MainCategories(
    val id: Int = 0,
    val name: Int? = null,
    val title: String? = null,
    val iconResource: Int,
    val startColor: Int,
    val endColor: Int,
    val status: Int? = null,
    val isNew: Boolean = false
)
