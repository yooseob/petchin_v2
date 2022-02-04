package kr.petchin.app.data


data class talkListData(var idx: Int, var title: String, var img: String, var intro: String, var regdate: String) {
//var iconDrawable: Drawable? = null
}

class talkListDataResult {
    /*
    //manual matching
    @SerializedName("recordCount")
    private val totalRecord: Int = 0

    @SerializedName("idx")
    private val idx: Int = 0

    @SerializedName("r_name")
    private val title: String? = null

    @SerializedName("r_mainImg")
    private val img: String? = null

    @SerializedName("r_intro")
    private val intro: String? = null

    @SerializedName("regdate")
    private val regdate: String? = null

    //auto matching
    //private val title: String? = null
    // toString()을 Override 해주지 않으면 객체 주소값을 출력함

    override fun toString(): String {
        return "PostResult{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", bodyValue='" + bodyValue + '\'' +
                '}'
    }
    */
}