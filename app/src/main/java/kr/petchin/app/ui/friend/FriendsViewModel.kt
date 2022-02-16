package kr.petchin.app.ui.friend

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.newThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.petchin.app.data.FriendsResultResponse
import kr.petchin.app.lib.Constants.TAG
import kr.petchin.app.repository.Repository


class FriendsViewModel(private val repository: Repository) : ViewModel() {

    val _friendResponse : MutableLiveData<List<FriendsResultResponse>> = MutableLiveData()
    var _totalCnt = MutableLiveData<Int>()
    private val disposables = CompositeDisposable()

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

         repository.getFriendsListRx(page)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe({Log.d("Log1", "Logging");},{});

         repository.getFriendsListRx(page)
             .subscribeOn(newThread())
             .observeOn(mainThread())
             .subscribe({
                 Log.d("Log", "Logging");
             },{})

    }
    fun getListRx2(page: Int) {
        Log.d("RXRX", "getListRx:")
        repository.getFriendsListRx(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                _friendResponse.postValue(it.items)
                _totalCnt.postValue(it.totalRecord)
                Log.d("Log1", it.items.toString())
            }, {
                Log.d(TAG, "getRepository Error: ${it.message}")
            })

    }
}