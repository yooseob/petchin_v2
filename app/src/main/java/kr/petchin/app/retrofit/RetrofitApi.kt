package kr.petchin.app.retrofit

import io.reactivex.rxjava3.core.Single
import kr.petchin.app.data.FriendsResponse
import kr.petchin.app.data.TalkListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {


/*
    @Headers(
        "X-NCP-APIGW-API-KEY-ID: (발급 받은 Client ID)",
        "X-NCP-APIGW-API-KEY: (발급 받은 Secret Key)"
    )
 */
    @GET("api/getrecipelist.asp")
    fun getTalkList(
        @Query("p") page: Int,
        @Query("keyword") keyword: String //요구하는 기본인자를 @Query형태로
    ): Call<TalkListResponse> //반환하는 값 rxJava나 코루틴사용시는 response로 변경

    @GET("api/getrecipelist.asp")
    suspend fun getFriendsList(
        @Query("p") page: Int,
        @Query("keyword") keyword: String //요구하는 기본인자를 @Query형태로
    ): Response<FriendsResponse>//반환하는 값 rxJava나 코루틴사용시는 response로 변경

    @GET("api/getrecipelist.asp")
    suspend fun getFriendsListRx(
        @Query("p") page: Int,
        @Query("keyword") keyword: String //요구하는 기본인자를 @Query형태로
    ): Single<FriendsResponse>//반환하는 값 rxJava나 코루틴사용시는 response로 변경


/*
@GET("api/getrecipelist.asp")
    suspend fun getPost() : talkListResponse

    fun getAddress(
        @Query("page") page: String,
        @Query("output") output: String = "json"
    ): Call<talkListResponse>

    @GET("getrecipelist.asp/{val}")
    fun getPosts(@Path("") post: String?): Call<talkListResponse?>?
*/
}