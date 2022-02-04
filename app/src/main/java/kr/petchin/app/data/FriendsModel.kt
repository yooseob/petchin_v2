package kr.petchin.app.data

import com.google.gson.annotations.SerializedName

class FriendsModel {
}

data class FriendsResponse (
    @SerializedName("recordCount")
    val totalRecord: Int = 0,

    @SerializedName("entry")
    val items : List<FriendsResultResponse>
)

data class FriendsResultResponse (


    @SerializedName("idx")
    val idx: Int = 0,

    @SerializedName("r_name")
    val title: String? = null,

    @SerializedName("r_mainImg")
    val img: String? = null,

    @SerializedName("r_intro")
    val intro: String? = null,

    @SerializedName("regdate")
    val r_regdate: String? = null
)