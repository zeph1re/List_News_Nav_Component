package binar.ganda.list_news_nav_component.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUserPost(
    @SerializedName("address")
    val address: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("umur")
    val umur: Int,
    @SerializedName("username")
    val username: String
) : Parcelable
