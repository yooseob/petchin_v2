package kr.petchin.app.repository

import io.reactivex.rxjava3.core.Single
import kr.petchin.app.data.FriendsResponse
import kr.petchin.app.data.talkListData
import kr.petchin.app.data.TalkListResponse
import kr.petchin.app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

//코루틴 singleton class->object로 변경
class RepositoryImpl : Repository {

    override fun getTalkList(page: Int): Call<TalkListResponse> {
        return RetrofitInstance.api.getTalkList(page, "")
    }

    override suspend fun getFriendsList(page: Int): Response<FriendsResponse> {
        return RetrofitInstance.api.getFriendsList(page, "")
    }

    override fun getFriendsListRx(page: Int): Single<FriendsResponse> {
        return RetrofitInstance.retrofitRx.getFriendsListRx(page, "")
    }


}
/*
    //통신 call 구현
    override fun getTalkList(page: String) : Response<talkListResponse>{
        //var call : Call<talkListResponse?>? = RetrofitInstance.api.getPosts("")
        //call.enqueue(new Callback<talkListResponse>)
        var model : talkListResponse
        RetrofitInstance.api.getTalkList(page, "")
            .enqueue(object: Callback<talkListResponse>{
                override fun onFailure(call: Call<talkListResponse>, t: Throwable) {
                    //todo 실패처리
                    Log.d("Retrofit error1=",t.toString())
                }

                override fun onResponse(call: Call<talkListResponse>, response: Response<talkListResponse>) {
                    //todo 성공처리
                    //Log.d("Retrofit BODY",response.body().toString())

                    if(response.isSuccessful){
                        response.body()?.let{
                            model = response.body()!!
                            //Log.d("GGGGGGGG=",model.toString())

                        }
                    }else{
                        Log.d("Retrofit error2=","request failure: ${response.message()}")                    }

                }
            })

            return model
    }

    //통신 respose 구형

*/
