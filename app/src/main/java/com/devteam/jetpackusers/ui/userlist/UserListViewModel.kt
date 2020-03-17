package com.devteam.jetpackusers.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devteam.jetpackusers.repository.DataRepository
import com.devteam.jetpackusers.utils.Logger

class UserListViewModel : ViewModel() {
    val dataRepository = DataRepository()
    var pageNo = 1

    val users = liveData {
        val page = dataRepository.getUserFor(pageNo)
        Logger.d("**** ViewModel - received the response - $page")
        emit(page.data)
    }
}