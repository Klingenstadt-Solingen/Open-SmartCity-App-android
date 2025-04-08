package de.osca.android.core.solingen.custom.services.domain

import com.google.gson.annotations.SerializedName
import de.osca.android.essentials.domain.entity.ImageObject

data class ServiceData(
    @SerializedName("objectId")
    val objectId: String? = null,
    @SerializedName("enabled")
    val enabled: Boolean = false,
    @SerializedName("visible")
    val visible: Boolean = false,
    @SerializedName("iconName")
    val iconName: String? = null,
    @SerializedName("iconPath")
    val iconPath: String? = null,
    @SerializedName("iconMimetype")
    val iconMimetype: String? = null,
    @SerializedName("seque")
    val segue: String? = null,
    @SerializedName("position")
    val position: Int = 0,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("link")
    val url: String? = null,
    @SerializedName("subtitle")
    val subTitle: String? = null,
    @SerializedName("image")
    val image: ImageObject? = null,
) {
    fun getIconUrl() = "$iconPath${if(iconPath?.get(iconPath.length-1) == '/') "" else "/" }$iconName$iconMimetype"
    val segueType get() = SegueServiceType.getRoute(segue ?: "", url, title)
    val isAvailable get() = enabled && visible
}