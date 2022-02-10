package kr.petchin.app.ui.friend

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    }
}