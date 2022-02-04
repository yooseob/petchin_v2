package kr.petchin.app.ui.friend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.petchin.app.repository.Repository


class FriendsViewModelFactory(
    private val repository : Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FriendsViewModel(repository) as T
    }
}