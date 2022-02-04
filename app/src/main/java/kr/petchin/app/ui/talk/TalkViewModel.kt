package kr.petchin.app.ui.talk

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.petchin.app.data.TalkListResponse
import kr.petchin.app.data.TalkListResultResponse
import kr.petchin.app.lib.Constants.TAG
import kr.petchin.app.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class TalkViewModel(private val repository: Repository) : ViewModel() {

    //private val repository : Repository = RepositoryImpl()
    val _talkResponse : MutableLiveData<List<TalkListResultResponse>> = MutableLiveData()
    var _totalCnt = MutableLiveData<Int>()


    fun getList(page: Int){
        val response = repository.getTalkList(page)
        .enqueue(object: Callback<TalkListResponse> {
            override fun onFailure(call: Call<TalkListResponse>, t: Throwable) {
                //todo 실패처리
                Log.d("Retrofit error1=", t.toString())
            }

            override fun onResponse(call: Call<TalkListResponse>, response: Response<TalkListResponse>) {
                //todo 성공처리
                //Log.d("Retrofit BODY",response.body().toString())

                if (response.isSuccessful) {
                    response.body()?.let {
                        _talkResponse.postValue(it.items)  //백그라운드
                        //_talkResponse.value = response.body()   //메인쓰레드
                        _totalCnt.postValue(it.totalRecord)
                        //Log.d("GGGGGGGG=",it.items.toString())
                    }
                } else {
                    Log.d("Retrofit error2=", "request failure: ${response.message()}")
                }
            }
        })
    }





    // 코루틴 사용시
    fun getPost(page : Int) {
        viewModelScope.launch {
            val response = repository.getTalkList(page)
            //_talkResponse.value = response
        }
    }

    fun test(){
        Log.d(TAG, "test: 11111")
    }
    

}