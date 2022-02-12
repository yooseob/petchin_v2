package kr.petchin.app.ui.friend

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.petchin.app.data.FriendsResponse
import kr.petchin.app.data.FriendsResultResponse
import kr.petchin.app.repository.Repository

class FriendsViewModel(private val repository: Repository) : ViewModel() {

    val _friendResponse : MutableLiveData<List<FriendsResultResponse>> = MutableLiveData()
    var _totalCnt = MutableLiveData<Int>()

    fun test(){
        Log.d("2222222", "test: 222222")
    }

    fun getPost(page : Int) {
        viewModelScope.launch {
            val response = repository.getFriendsList(page)
            response.body()?.let {
                _friendResponse.value = it.items
            }
        }
    }

    fun getList(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getFriendsList(page).let { response ->
                if(response.isSuccessful) {
                    response.body()?.let {
                        _friendResponse.postValue(it.items)
                        _totalCnt.postValue(it.totalRecord)
                    }
                }
            }
        }
    }

     fun getListRx(page: Int) {
         Log.d("RXRX", "getListRx:")
        repository.getFriendsListRx(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({  { println(it) } }, { e -> println("RX="+e.toString()) })

    }
    fun getListRx2(page: Int) {
        Log.d("RXRX", "getListRx:")
        repository.getFriendsListRx(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                // onNext
                Log.d("!!!!!!!!!!!!!!!!", "get channel : $it")
            }, {
                // onError

            }, //{
                // onComplete
            //})
            )
    }
}