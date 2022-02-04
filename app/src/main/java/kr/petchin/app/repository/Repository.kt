package kr.petchin.app.repository

import kr.petchin.app.data.FriendsResponse
import kr.petchin.app.data.talkListData
import kr.petchin.app.data.TalkListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

//singleton
interface Repository {
    //fun getUsers(): Single<List<talkListResponse>>
    fun getTalkList(page : Int) : Call<TalkListResponse>
    suspend fun getFriendsList(page : Int) : Response<FriendsResponse>
    //@GET("posts/{post}")
    //fun getPosts(@Path("post") post: String?): Call<PostResult?>?
}