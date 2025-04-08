package de.osca.android.core.solingen.custom.appointments.entity

import com.google.gson.annotations.SerializedName

data class Appointment(
    @SerializedName("url")
    val link: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("subtitle")
    val subtitle: String = "keine Beschreibung vorhanden",
    @SerializedName("enabled")
    val enabled: Boolean = true,
    @SerializedName("visible")
    val visible: Boolean = true,
    @SerializedName("position")
    val position: Int = 0,
    @SerializedName("seque")
    val seque: String = "",
    @SerializedName("ref")
    val ref: String = ""
) {
}
