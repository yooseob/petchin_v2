package kr.petchin.app.ui.talk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.petchin.app.repository.Repository

class TalkViewModelFactory(
    private val repository : Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TalkViewModel(repository) as T
    }
}
/*
class TalkViewModelFactory(private val param: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(TalkViewModel::class.java)) {

            TalkViewModel(param) as T

        } else {

            throw IllegalArgumentException()

        }

    }

}

 */